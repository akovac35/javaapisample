package si.zpiz.sample.infrastructure.book_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class CreateBookHandler implements IMediatorHandler<CreateBookCommand, BookDbo> {
    private final BookDboRepository bookDboRepository;
    private AuthorDboRepository authorDboRepository;

    public CreateBookHandler(BookDboRepository bookDboRepository, AuthorDboRepository authorDboRepository) {
        this.bookDboRepository = bookDboRepository;
        this.authorDboRepository = authorDboRepository;
    }

    @Transactional
    @Override
    public BookDbo handle(CreateBookCommand request) {
        AuthorDbo author = null;
        if (request.getAuthorUniqueId() != null) {
            author = authorDboRepository.findByUniqueId(request.getAuthorUniqueId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Author with unique id %s was not found", request.getAuthorUniqueId())));
        }

        BookDbo book = new BookDbo();
        book.setTitle(request.getTitle());
        book.setAuthor(author);
        book.setYear(request.getYear());
        book.setPublisher(request.getPublisher());

        book = bookDboRepository.save(book);
        return book;
    }
}

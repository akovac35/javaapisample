package si.zpiz.sample.infrastructure.book_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class UpdateBookHandler implements IMediatorHandler<UpdateBookCommand, BookDbo> {
    private final BookDboRepository bookDboRepository;
    private AuthorDboRepository authorDboRepository;

    public UpdateBookHandler(BookDboRepository bookDboRepository, AuthorDboRepository authorDboRepository) {
        this.bookDboRepository = bookDboRepository;
        this.authorDboRepository = authorDboRepository;
    }

    @Transactional
    @Override
    public BookDbo handle(UpdateBookCommand request) throws MediatorException {
        BookDbo dbo = bookDboRepository.findByUniqueId(request.getUniqueId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with unique id %s was not found", request.getUniqueId())));

        AuthorDbo author = null;
        if (request.getAuthorUniqueId() != null) {
            author = authorDboRepository.findByUniqueId(request.getAuthorUniqueId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Author with unique id %s was not found", request.getAuthorUniqueId())));
        }

        dbo.setTitle(request.getTitle());
        dbo.setAuthor(author);
        dbo.setYear(request.getYear());
        dbo.setPublisher(request.getPublisher());

        dbo = bookDboRepository.save(dbo);
        return dbo;
    }

}

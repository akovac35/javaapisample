package si.zpiz.sample.infrastructure.book_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class DeleteBookHandler implements IMediatorHandler<DeleteBookCommand, Void> {
    private BookDboRepository bookDboRepository;

    public DeleteBookHandler(BookDboRepository bookDboRepository) {
        this.bookDboRepository = bookDboRepository;
    }

    @Override
    @Transactional
    public Void handle(DeleteBookCommand request) throws MediatorException {
        bookDboRepository.deleteByUniqueId(request.getUniqueId());
        return null;
    }

}

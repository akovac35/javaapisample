package si.zpiz.sample.infrastructure.book_related.command_query;

import java.util.Optional;

import org.springframework.stereotype.Service;

import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class GetBookHandler implements IMediatorHandler<GetBookQuery, Optional<BookDbo>> {
    private final BookDboRepository bookDboRepository;

    public GetBookHandler(BookDboRepository bookDboRepository) {
        this.bookDboRepository = bookDboRepository;
    }

    @Override
    public Optional<BookDbo> handle(GetBookQuery request) throws MediatorException {
        return bookDboRepository.findByUniqueId(request.getUniqueId());
    }

}

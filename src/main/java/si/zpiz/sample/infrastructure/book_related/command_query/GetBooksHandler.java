package si.zpiz.sample.infrastructure.book_related.command_query;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class GetBooksHandler implements IMediatorHandler<GetBooksQuery, Page<BookDbo>> {
    private final BookDboRepository bookDboRepository;

    public GetBooksHandler(BookDboRepository bookDboRepository) {
        this.bookDboRepository = bookDboRepository;
    }

    @Override
    public Page<BookDbo> handle(GetBooksQuery request) throws MediatorException {
        if (request.getMode() == GetBooksQueryMode.BY_TITLE_CONTAINING_IGNORE_CASE) {
            if (request.getQuery() == null || request.getQuery().isEmpty()) {
                throw new MediatorException("Query string is empty");
            }

            return bookDboRepository.findByTitleContainingIgnoreCase(request.getQuery(),
                    request.getPaged().getPageable());
        }

        if (request.getMode() == GetBooksQueryMode.STRINGS_CONTAINING_IGNORE_CASE) {
            if (request.getQuery() == null || request.getQuery().isEmpty()) {
                throw new MediatorException("Query string is empty");
            }

            return bookDboRepository.findByStringsContainingIgnoreCase(request.getQuery(),
                    request.getPaged().getPageable());
        }

        if (request.getMode() == GetBooksQueryMode.ALL) {
            return bookDboRepository.findAll(request.getPaged().getPageable());
        }

        throw new MediatorException("Unknown query mode");
    }

}

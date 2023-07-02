package si.zpiz.sample.infrastructure.author_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class DeleteAuthorHandler implements IMediatorHandler<DeleteAuthorCommand, Void> {
    private AuthorDboRepository authorDboRepository;

    public DeleteAuthorHandler(AuthorDboRepository authorDboRepository) {
        this.authorDboRepository = authorDboRepository;
    }

    @Override
    @Transactional
    public Void handle(DeleteAuthorCommand request) throws MediatorException {
        authorDboRepository.deleteByUniqueId(request.getUniqueId());
        return null;
    }

}

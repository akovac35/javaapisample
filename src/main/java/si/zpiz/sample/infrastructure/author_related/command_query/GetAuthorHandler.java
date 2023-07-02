package si.zpiz.sample.infrastructure.author_related.command_query;

import java.util.Optional;

import org.springframework.stereotype.Service;

import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class GetAuthorHandler implements IMediatorHandler<GetAuthorQuery, Optional<AuthorDbo>> {
    private AuthorDboRepository authorDboRepository;

    public GetAuthorHandler(AuthorDboRepository authorDboRepository) {
        this.authorDboRepository = authorDboRepository;
    }

    @Override
    public Optional<AuthorDbo> handle(GetAuthorQuery request) throws MediatorException {
        Optional<AuthorDbo> author = authorDboRepository.findByUniqueId(request.getUniqueId());
        return author;
    }

}

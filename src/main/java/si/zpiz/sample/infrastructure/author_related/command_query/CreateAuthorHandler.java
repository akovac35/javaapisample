package si.zpiz.sample.infrastructure.author_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class CreateAuthorHandler implements IMediatorHandler<CreateAuthorCommand, AuthorDbo> {
    private AuthorDboRepository authorDboRepository;

    public CreateAuthorHandler(AuthorDboRepository authorDboRepository) {
        this.authorDboRepository = authorDboRepository;
    }

    @Override
    @Transactional
    public AuthorDbo handle(CreateAuthorCommand request) throws MediatorException {
        AuthorDbo author = new AuthorDbo();
        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());

        author = authorDboRepository.save(author);
        return author;
    }

}

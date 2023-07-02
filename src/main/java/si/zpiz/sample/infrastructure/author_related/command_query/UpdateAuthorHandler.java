package si.zpiz.sample.infrastructure.author_related.command_query;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.author_related.AuthorDboRepository;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;

@Service
public class UpdateAuthorHandler implements IMediatorHandler<UpdateAuthorCommand, AuthorDbo> {
    private AuthorDboRepository authorDboRepository;

    public UpdateAuthorHandler(AuthorDboRepository authorDboRepository) {
        this.authorDboRepository = authorDboRepository;
    }

    @Override
    @Transactional
    public AuthorDbo handle(UpdateAuthorCommand request) throws MediatorException {
        AuthorDbo author = authorDboRepository.findByUniqueId(request.getUniqueId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Author with unique id %s was not found", request.getUniqueId())));

        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());

        author = authorDboRepository.save(author);
        return author;
    }

}
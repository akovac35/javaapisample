package si.zpiz.sample.infrastructure.initialization_related;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.author_related.command_query.CreateAuthorCommand;
import si.zpiz.sample.infrastructure.book_related.command_query.CreateBookCommand;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;
import si.zpiz.sample.infrastructure.mediator.Mediator;
import si.zpiz.sample.infrastructure.user_details_related.command_query.CreateUserDetailsCommand;

@Service
public class InitializeSampleDataHandler implements IMediatorHandler<InitializeSampleDataCommand, Void> {
    private Mediator mediator;

    public InitializeSampleDataHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    @Transactional
    public Void handle(InitializeSampleDataCommand request) throws MediatorException {
        CreateAuthorCommand createAuthorCommand = new CreateAuthorCommand("John", "Doe");
        AuthorDbo author = mediator.send(createAuthorCommand);

        for (int i = 0; i < 20; i++) {
            CreateBookCommand createBookCommand = new CreateBookCommand(
                    "Title " + i,
                    2000 + i,
                    "Publisher " + i,
                    author.getUniqueId());

            mediator.send(createBookCommand);
        }

        CreateUserDetailsCommand createAdminUserDetailsCommand = new CreateUserDetailsCommand(
                "admin",
                "password",
                java.util.Arrays.asList("ADMIN", "USER"));
        mediator.send(createAdminUserDetailsCommand);

        CreateUserDetailsCommand createUserUserDetailsCommand = new CreateUserDetailsCommand(
                "user",
                "password",
                java.util.Arrays.asList("USER"));
        mediator.send(createUserUserDetailsCommand);

        return null;
    }

}

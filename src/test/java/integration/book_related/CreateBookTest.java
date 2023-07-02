package integration.book_related;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import integration.IntegrationTestConfiguration;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.author_related.command_query.CreateAuthorCommand;
import si.zpiz.sample.infrastructure.book_related.command_query.CreateBookCommand;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class CreateBookTest {
    @Autowired
    private Mediator mediator;

    @Test
    public void create_WithoutAuthor_Works() throws MediatorException {
        CreateBookCommand command = new CreateBookCommand();
        command.setAuthorUniqueId(null);
        command.setPublisher("publisher");
        command.setTitle("title");
        command.setYear(2000);

        BookDbo dbo = mediator.send(command);
        Assert.notNull(dbo, "BookDbo is null");
        Assert.isTrue(dbo.getId() != null, "BookDbo was not saved to db");
    }

    @Test
    public void create_WithAuthor_Works() throws MediatorException {
        CreateAuthorCommand authorCommand = new CreateAuthorCommand();
        authorCommand.setFirstName("first");
        authorCommand.setLastName("last");
        AuthorDbo author = mediator.send(authorCommand);

        CreateBookCommand command = new CreateBookCommand();
        command.setAuthorUniqueId(author.getUniqueId());
        command.setPublisher("publisher");
        command.setTitle("title");
        command.setYear(2000);

        BookDbo dbo = mediator.send(command);
        Assert.notNull(dbo, "BookDbo is null");
        Assert.isTrue(dbo.getId() != null, "BookDbo was not saved to db");
        Assert.isTrue(dbo.getAuthor() != null, "AuthorDbo should not be null");
    }
}

package integration.author_related;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import integration.IntegrationTestConfiguration;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.author_related.command_query.CreateAuthorCommand;
import si.zpiz.sample.infrastructure.author_related.command_query.DeleteAuthorCommand;
import si.zpiz.sample.infrastructure.book_related.command_query.CreateBookCommand;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class DeleteAuthorTest {
    @Autowired
    private Mediator mediator;

    @Test
    private void deleting_Author_Sets_Book_Author_To_Null() throws MediatorException {
        CreateAuthorCommand createAuthorCommand = new CreateAuthorCommand();
        AuthorDbo author = mediator.send(createAuthorCommand);

        List<BookDbo> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CreateBookCommand createBookCommand = new CreateBookCommand();
            createBookCommand.setAuthorUniqueId(author.getUniqueId());
            createBookCommand.setPublisher("publisher");
            createBookCommand.setTitle("title");
            createBookCommand.setYear(2000);
            BookDbo book = mediator.send(createBookCommand);
            books.add(book);

            Assert.isTrue(book.getAuthor() != null, "AuthorDbo should not be null");
            Assert.isTrue(book.getRowVersion() == 0, "RowVersion should be 0");
        }

        DeleteAuthorCommand deleteAuthorCommand = new DeleteAuthorCommand();
        deleteAuthorCommand.setUniqueId(author.getUniqueId());
        mediator.send(deleteAuthorCommand);

        for (BookDbo book : books) {
            Assert.isTrue(book.getAuthor() == null, "AuthorDbo should be null");
            Assert.isTrue(book.getRowVersion() == 1, "RowVersion should be 1");
        }
    }
}

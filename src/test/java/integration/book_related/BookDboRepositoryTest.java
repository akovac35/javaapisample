package integration.book_related;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import integration.IntegrationTestConfiguration;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.book_related.BookDboRepository;

@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class BookDboRepositoryTest {
    private static UUID uuid1;

    @Autowired
    private BookDboRepository bookDboRepository;

    @BeforeAll
    public static void beforeAll() {
        uuid1 = UUID.randomUUID();
    }

    @Test
    public void test1_create_BookDbo_Works() {
        BookDbo dbo = new BookDbo();
        dbo.setUniqueId(uuid1);
        dbo.setTitle("title");
        dbo.setAuthor(null);
        dbo.setYear(2020);
        dbo.setPublisher("publisher");

        bookDboRepository.save(dbo);

        Assert.isTrue(bookDboRepository.findByUniqueId(uuid1).isPresent(), "book dbo was not saved");
    }

    @Test
    public void test2_verify_BookDbo_Exists() {
        Optional<BookDbo> dbo = bookDboRepository.findByUniqueId(uuid1);

        Assert.isTrue(dbo.isPresent(), "book dbo was not saved");
    }

    /* This test should delete all books but then transaction should roll back */
    @Test
    @Transactional
    public void test3_delete_BookDbo_Works() {
        bookDboRepository.deleteByUniqueId(uuid1);
        Optional<BookDbo> dbo = bookDboRepository.findByUniqueId(uuid1);
        Assert.isTrue(dbo.isEmpty(), "book dbo was not deleted");
    }

    @Test
    public void test4_verify_BookDbo_Exists() {
        Optional<BookDbo> dbo = bookDboRepository.findByUniqueId(uuid1);

        Assert.isTrue(dbo.isPresent(), "book dbo should exist");
    }
}

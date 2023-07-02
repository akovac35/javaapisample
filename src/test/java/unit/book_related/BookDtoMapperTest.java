package unit.book_related;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.domain.book_related.BookDto;
import si.zpiz.sample.domain.book_related.BookDtoMapper;
import unit.UnitTestConfiguration;

@SpringBootTest
@ContextConfiguration(classes = UnitTestConfiguration.class)
public class BookDtoMapperTest {
    @Autowired
    private BookDtoMapper bookDtoMapper;

    @Test
    public void testMapToDto() {
        BookDbo dbo = new BookDbo();
        dbo.setTitle("Title");
        dbo.setAuthor(null);
        dbo.setYear(2021);
        dbo.setPublisher("Publisher");

        BookDto dto = bookDtoMapper.fromDbo(dbo);
        assertEquals("Title", dto.getTitle());
        assertEquals(null, dto.getAuthor());
        assertEquals(2021, dto.getYear());
        assertEquals("Publisher", dto.getPublisher());
        assertEquals(dbo.getUniqueId(), dto.getUniqueId());
    }
}
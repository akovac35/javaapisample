package si.zpiz.sample.domain.book_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import si.zpiz.sample.domain.author_related.AuthorDto;
import si.zpiz.sample.domain.author_related.AuthorDtoMapper;

@Component
public class BookDtoMapper {
    @Autowired
    private AuthorDtoMapper authorDtoMapper;

    public BookDto fromDbo(BookDbo dbo) {
        AuthorDto author = null;
        if (dbo.getAuthor() != null) {
            author = authorDtoMapper.fromDbo(dbo.getAuthor());
        }

        BookDto dto = new BookDto(dbo.getTitle(),
                author,
                dbo.getYear(),
                dbo.getPublisher(),
                dbo.getUniqueId());
        return dto;
    }
}

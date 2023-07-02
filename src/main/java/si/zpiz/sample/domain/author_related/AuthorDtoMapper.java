package si.zpiz.sample.domain.author_related;

import org.springframework.stereotype.Component;

@Component
public class AuthorDtoMapper {
    public AuthorDto fromDbo(AuthorDbo dbo) {
        AuthorDto dto = new AuthorDto(
                dbo.getFirstName(),
                dbo.getLastName(),
                dbo.getUniqueId());
        return dto;
    }
}

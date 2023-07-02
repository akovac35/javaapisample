package si.zpiz.sample.domain.author_related;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AuthorDto {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private UUID uniqueId;
}

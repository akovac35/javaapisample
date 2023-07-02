package si.zpiz.sample.infrastructure.author_related.command_query;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorCommand implements IMediatorRequest<AuthorDbo> {
    @NotBlank
    @Size(min = 5, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String lastName;

    @NotNull
    private UUID uniqueId;
}

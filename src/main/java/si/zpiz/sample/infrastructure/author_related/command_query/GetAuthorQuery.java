package si.zpiz.sample.infrastructure.author_related.command_query;

import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAuthorQuery implements IMediatorRequest<Optional<AuthorDbo>> {
    private UUID uniqueId;
}

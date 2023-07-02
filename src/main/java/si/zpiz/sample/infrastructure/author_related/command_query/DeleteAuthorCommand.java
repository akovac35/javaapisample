package si.zpiz.sample.infrastructure.author_related.command_query;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAuthorCommand implements IMediatorRequest<Void> {
    @NotNull
    private UUID uniqueId;
}

package si.zpiz.sample.infrastructure.initialization_related;

import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
public class InitializeSampleDataCommand implements IMediatorRequest<Void> {
}
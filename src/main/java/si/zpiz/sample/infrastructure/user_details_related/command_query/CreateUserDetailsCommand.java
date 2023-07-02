package si.zpiz.sample.infrastructure.user_details_related.command_query;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.user_details_related.UserDetailsDbo;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDetailsCommand implements IMediatorRequest<UserDetailsDbo> {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private List<String> authorities;
}

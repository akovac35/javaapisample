package si.zpiz.sample.infrastructure.user_related.command_query;

import org.springframework.security.oauth2.jwt.Jwt;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateCommand implements IMediatorRequest<Jwt> {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

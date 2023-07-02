package si.zpiz.sample.domain.user_related;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AuthenticationTokenDto {
    @NonNull
    private String token;
}

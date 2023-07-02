package si.zpiz.sample.domain.persistence;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {
    @NotNull
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
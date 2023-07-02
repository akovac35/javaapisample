package si.zpiz.sample.domain.user_details_related;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDtoMapper {
    public UserDetailsDto fromDbo(UserDetailsDbo dbo) {
        return new UserDetailsDto(
                dbo.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                dbo.getPassword(),
                dbo.getUsername(),
                dbo.isAccountNonExpired(),
                dbo.isAccountNonLocked(),
                dbo.isCredentialsNonExpired(),
                dbo.isEnabled());
    }
}

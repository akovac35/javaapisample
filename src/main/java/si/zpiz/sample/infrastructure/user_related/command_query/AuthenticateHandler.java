package si.zpiz.sample.infrastructure.user_related.command_query;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import si.zpiz.sample.domain.user_details_related.UserDetailsDbo;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.IMediatorHandler;
import si.zpiz.sample.infrastructure.user_details_related.UserDetailsDboRepository;

@Service
@Slf4j
public class AuthenticateHandler implements IMediatorHandler<AuthenticateCommand, Jwt> {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtEncoder encoder;
    private UserDetailsDboRepository userDetailsDboRepository;

    public AuthenticateHandler(UserDetailsDboRepository userDetailsDboRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtEncoder encoder) {
        this.userDetailsDboRepository = userDetailsDboRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.encoder = encoder;
    }

    @Override
    public Jwt handle(AuthenticateCommand request) throws MediatorException {
        log.debug("username: {}", request.getUsername());

        UserDetailsDbo user = userDetailsDboRepository.findByUsernameIgnoreCase(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s was not found", request.getUsername())));

        // TODO check if user is enabled etc.

        if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            Instant now = Instant.now();
            long expiry = 36000L;
            String scope = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(request.getUsername())
                    // the scope claim is a standard way of conveying the authorities granted to the
                    // user in spring security
                    .claim("scope", scope)
                    .build();
            return this.encoder.encode(JwtEncoderParameters.from(claims));
        }

        throw new BadCredentialsException("Bad credentials");
    }

}

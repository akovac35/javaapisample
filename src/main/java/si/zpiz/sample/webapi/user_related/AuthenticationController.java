package si.zpiz.sample.webapi.user_related;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import si.zpiz.sample.domain.misc.ErrorDto;
import si.zpiz.sample.domain.user_related.AuthenticationTokenDto;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.Mediator;
import si.zpiz.sample.infrastructure.user_related.command_query.AuthenticateCommand;

@RestController
@RequestMapping("/api/authentication")
@ApiResponses(value = {
                @ApiResponse(responseCode = "400", content = {
                                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                }),
                @ApiResponse(responseCode = "401", content = {
                                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                }),
                @ApiResponse(responseCode = "403", content = {
                                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                }),
                @ApiResponse(responseCode = "404", content = {
                                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                }),
                @ApiResponse(responseCode = "500", content = {
                                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                })
})
public class AuthenticationController {
        @Autowired
        private Mediator mediator;

        @PostMapping("/token")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<AuthenticationTokenDto> token(@RequestBody @Valid AuthenticateCommand command)
                        throws MediatorException {
                Jwt jwt = mediator.send(command);
                return ResponseEntity.ok(new AuthenticationTokenDto(jwt.getTokenValue()));
        }

        @GetMapping("/getCurrentAuthorities")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<Collection<? extends GrantedAuthority>> getCurrentAuthorities() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return ResponseEntity.ok(authentication.getAuthorities());
        }
}

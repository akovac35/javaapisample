package si.zpiz.sample.webapi.author_related;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.author_related.AuthorDto;
import si.zpiz.sample.domain.author_related.AuthorDtoMapper;
import si.zpiz.sample.domain.misc.ErrorDto;
import si.zpiz.sample.infrastructure.author_related.command_query.CreateAuthorCommand;
import si.zpiz.sample.infrastructure.author_related.command_query.DeleteAuthorCommand;
import si.zpiz.sample.infrastructure.author_related.command_query.GetAuthorQuery;
import si.zpiz.sample.infrastructure.author_related.command_query.UpdateAuthorCommand;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@RestController
@RequestMapping("/api/authors")
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
public class AuthorController {
        @Autowired
        private AuthorDtoMapper authorDtoMapper;

        @Autowired
        private Mediator mediator;

        @PostMapping(path = "/createAuthor")
        @ApiResponse(responseCode = "201")
        public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid CreateAuthorCommand author)
                        throws MediatorException {
                AuthorDbo dbo = mediator.send(author);
                AuthorDto dto = authorDtoMapper.fromDbo(dbo);

                return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }

        @GetMapping(path = "/getAuthor")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<?> getAuthor(@RequestParam @NotNull UUID uniqueId) throws MediatorException {
                Optional<AuthorDbo> dbo = mediator.send(new GetAuthorQuery(uniqueId));
                Optional<AuthorDto> dto = dbo.map(book -> authorDtoMapper.fromDbo(book));

                if (dto.isEmpty()) {
                        ErrorDto error = new ErrorDto();
                        error.setMessage("Author not found");
                        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }

        @PutMapping(path = "/updateAuthor")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<AuthorDto> updateAuthor(@RequestBody @Valid UpdateAuthorCommand author)
                        throws MediatorException {
                AuthorDbo dbo = mediator.send(author);
                AuthorDto dto = authorDtoMapper.fromDbo(dbo);

                return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        @DeleteMapping(path = "/deleteAuthor")
        @PreAuthorize("hasAuthority('ADMIN')")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<Void> deleteAuthor(@RequestParam @NotNull UUID uniqueId) throws MediatorException {
                mediator.send(new DeleteAuthorCommand(uniqueId));
                return new ResponseEntity<>(HttpStatus.OK);
        }
}

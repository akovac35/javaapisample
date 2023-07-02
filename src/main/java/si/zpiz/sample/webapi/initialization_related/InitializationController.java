package si.zpiz.sample.webapi.initialization_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.initialization_related.InitializeSampleDataCommand;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@RestController
@RequestMapping("/api/initialization")
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
public class InitializationController {
        @Autowired
        private Mediator mediator;

        @PostMapping(path = "/initializeSampleData")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<Void> initializeSampleData(@RequestBody @Valid InitializeSampleDataCommand cmd)
                        throws MediatorException {
                mediator.send(cmd);

                return new ResponseEntity<>(HttpStatus.OK);
        }
}

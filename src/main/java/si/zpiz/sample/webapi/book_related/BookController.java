package si.zpiz.sample.webapi.book_related;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.domain.book_related.BookDto;
import si.zpiz.sample.domain.book_related.BookDtoMapper;
import si.zpiz.sample.domain.misc.ErrorDto;
import si.zpiz.sample.infrastructure.book_related.command_query.CreateBookCommand;
import si.zpiz.sample.infrastructure.book_related.command_query.GetBookQuery;
import si.zpiz.sample.infrastructure.book_related.command_query.GetBooksQuery;
import si.zpiz.sample.infrastructure.book_related.command_query.UpdateBookCommand;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@RestController
@RequestMapping("/api/books")
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
public class BookController {
        @Autowired
        private BookDtoMapper bookDtoMapper;

        @Autowired
        private Mediator mediator;

        @PostMapping(path = "/createBook")
        @ApiResponse(responseCode = "201")
        public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookCommand book) throws MediatorException {
                BookDbo dbo = mediator.send(book);
                BookDto dto = bookDtoMapper.fromDbo(dbo);

                return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }

        @PutMapping(path = "/updateBook")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<BookDto> updateBook(@RequestBody @Valid UpdateBookCommand book) throws MediatorException {
                BookDbo dbo = mediator.send(book);
                BookDto dto = bookDtoMapper.fromDbo(dbo);

                return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        @GetMapping(path = "/getBook")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<?> getBook(@RequestParam @NotNull UUID uniqueId) throws MediatorException {
                Optional<BookDbo> dbo = mediator.send(new GetBookQuery(uniqueId));
                Optional<BookDto> dto = dbo.map(book -> bookDtoMapper.fromDbo(book));

                if (dto.isEmpty()) {
                        ErrorDto error = new ErrorDto();
                        error.setMessage("Book not found");
                        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }

        @PostMapping(path = "/getBooks")
        @ApiResponse(responseCode = "200")
        public ResponseEntity<Page<BookDto>> getBooks(@RequestBody @Valid GetBooksQuery query)
                        throws MediatorException {
                Page<BookDbo> dbos = mediator.send(query);
                Page<BookDto> dtos = dbos.map(book -> bookDtoMapper.fromDbo(book));

                return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
}
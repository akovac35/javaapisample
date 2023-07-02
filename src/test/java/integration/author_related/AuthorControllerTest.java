package integration.author_related;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import integration.IntegrationTestConfiguration;
import si.zpiz.sample.infrastructure.author_related.command_query.DeleteAuthorCommand;
import si.zpiz.sample.infrastructure.mediator.Mediator;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Mediator mediator;

    public void deleteAuthor_Works() throws Exception {
        when(mediator.send(any(DeleteAuthorCommand.class))).thenReturn(null);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("uniqueId", "29d1b123-b679-4ee5-8e22-2d4d89684244");

        mockMvc.perform(delete("/api/authors/deleteAuthor")
                .params(requestParams))
            .andExpect(status().isOk());
    }
}

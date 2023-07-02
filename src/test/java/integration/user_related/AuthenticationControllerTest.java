package integration.user_related;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import integration.IntegrationTestConfiguration;
import si.zpiz.sample.domain.persistence.GrantedAuthorityImpl;
import si.zpiz.sample.domain.user_details_related.UserDetailsDbo;
import si.zpiz.sample.infrastructure.user_details_related.UserDetailsDboRepository;
import si.zpiz.sample.infrastructure.user_related.command_query.AuthenticateCommand;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private UserDetailsDboRepository userDetailsDboRepository;

    @Test
    public void token_Works() throws Exception {
        UserDetailsDbo userDetailsDbo = new UserDetailsDbo();
        userDetailsDbo.setUsername("admin");
        userDetailsDbo.setPassword(passwordEncoder.encode("password"));
        userDetailsDbo.setEnabled(true);
        userDetailsDbo
                .setAuthorities(Arrays.asList(new GrantedAuthorityImpl("ADMIN"), new GrantedAuthorityImpl("USER")));

        when(userDetailsDboRepository.findByUsernameIgnoreCase(any())).thenReturn(Optional.of(userDetailsDbo));

        AuthenticateCommand command = new AuthenticateCommand();
        command.setUsername("admin");
        command.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/authentication/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("token")));
    }
}

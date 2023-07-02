package si.zpiz.sample.infrastructure.misc;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import si.zpiz.sample.domain.misc.ErrorDto;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.error("Authentication error", authException);

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(authException.getMessage());
        errorDto.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDto.setFromClass(authException.getStackTrace()[0].getClassName());
        errorDto.setControllerName(null);
        errorDto.setMethodName(null);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(errorDto);

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(json);
    }

}
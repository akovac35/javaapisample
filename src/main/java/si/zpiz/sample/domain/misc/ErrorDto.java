package si.zpiz.sample.domain.misc;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
    private String message;
    private int status;
    private String fromClass;
    private String controllerName;
    private String methodName;
}

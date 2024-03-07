package api.finances.exception.customExceptions;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    private String title;
    private Integer code;
    private String message;
    private String error;
    private LocalDateTime date;
    private List<FieldsValidation> validations;

    @Getter
    @AllArgsConstructor
    static class FieldsValidation {
        private String field;
        private String message;
    }
}

package by.home.hryhoryeu.vote.web.validators;

import by.home.hryhoryeu.vote.dto.ReplyDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ReplyValidator {

    public void validate(ReplyDTO replyDTO, Errors errors) {
            if (replyDTO.getValue() == null) {
                errors.reject("Выберите вариант ответа");
            }
    }
}

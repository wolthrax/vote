package by.home.hryhoryeu.vote.web.validators;

import by.home.hryhoryeu.vote.entities.Vote;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Map;

@Component
public class VoteValidator {
    public void validate(Vote vote, Errors errors) {

        if (vote.getUserName().equals("") || vote.getUserName() == null) {
            errors.reject("Введите имя пользователя");
        }

        if (vote.getTopic().equals("") || vote.getTopic() == null) {
            errors.reject("Введите название голосования");
        }

        if (vote.getDescription().equals("") || vote.getDescription() == null) {
            errors.reject("Введите описание голосования");
        }

        for (Map.Entry<String, Integer> entry : vote.getOptions().entrySet()) {
            if (entry.getKey().equals("") || entry.getKey() == null) {
                errors.reject("Одно или несколько полей для вариантов ответа не заполнены");
                break;
            }
        }
    }
}

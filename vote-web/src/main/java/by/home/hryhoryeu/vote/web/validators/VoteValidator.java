package by.home.hryhoryeu.vote.web.validators;

import by.home.hryhoryeu.vote.entities.Vote;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class VoteValidator {
    public void validate(Vote vote, Errors errors) {

        if (vote.getTopic().equals("") || vote.getTopic() == null) {
            errors.reject("Введите название голосования");
        }

        if (vote.getDescription().equals("") || vote.getDescription() == null) {
            errors.reject("Введите описание голосования");
        }
    }
}

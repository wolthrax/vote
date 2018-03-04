package by.home.hryhoryeu.vote.web.controllers;

import by.home.hryhoryeu.vote.dto.ReplyDTO;
import by.home.hryhoryeu.vote.dto.ResponseDTO;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.managers.IVoteManager;
import by.home.hryhoryeu.vote.web.validators.ReplyValidator;
import by.home.hryhoryeu.vote.web.validators.VoteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/vote")
public class VoteController {

    @Autowired
    private IVoteManager voteManager;

    @Autowired
    private ReplyValidator replyValidator;

    @Autowired
    private VoteValidator voteValidator;

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Vote> getVoteById(@PathVariable("id") Long id) {

        Vote vote = voteManager.getVoteById(id);
        if (vote != null)
            return new ResponseEntity<>(vote, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVote(@RequestBody Vote vote, Errors errors) {

        voteValidator.validate(vote, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }

        else {
            ResponseDTO responseDTO = voteManager.addVote(vote);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "reply", method = RequestMethod.POST)
    public ResponseEntity<?> sendReply(@RequestBody ReplyDTO reply, Errors errors) {

        replyValidator.validate(reply, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        else {
            voteManager.sendReply(reply);
            Vote vote = voteManager.getVoteById(reply.getId());
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }

    }



    @RequestMapping(path = "remove/{id}/{pwd}", method = RequestMethod.DELETE)
    public HttpStatus removeVote(@PathVariable("id") Long id, @PathVariable("pwd") String pwd) {

        voteManager.removeVote(id, pwd);
        return HttpStatus.OK;

    }

    @RequestMapping(path = "/top", method = RequestMethod.GET)
    public ResponseEntity<?> getVoteById() {

        List<Vote> voteList = voteManager.getTopVotes();
        if (voteList != null)
            return new ResponseEntity<>(voteList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    private List<String> createErrorString(Errors errors) {

        List<String> errorList = new ArrayList<>();

        for (ObjectError objectError : errors.getAllErrors()) {
            errorList.add(objectError.getCode());
        }

        return errorList;
    }
}

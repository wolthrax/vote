package by.home.hryhoryeu.vote.web.controllers;

import by.home.hryhoryeu.vote.dto.ReplyDTO;
import by.home.hryhoryeu.vote.dto.ResponseDTO;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.managers.IVoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vote")
public class VoteController {

    @Autowired
    private IVoteManager voteManager;

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Vote> getVoteById(@PathVariable("id") Long id) {

        Vote vote = voteManager.getVoteById(id);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> addVote(@RequestBody Vote vote) {

        ResponseDTO responseDTO = voteManager.addVote(vote);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @RequestMapping(path = "reply", method = RequestMethod.POST)
    public HttpStatus sendReply(@RequestBody ReplyDTO reply) {


        voteManager.sendReply(reply);
        return HttpStatus.OK;

    }

    @RequestMapping(path = "remove/{id}/{pwd}", method = RequestMethod.DELETE)
    public HttpStatus removeVote(@PathVariable("id") Long id, @PathVariable("pwd") String pwd) {

        voteManager.removeVote(id, pwd);
        return HttpStatus.OK;

    }

}

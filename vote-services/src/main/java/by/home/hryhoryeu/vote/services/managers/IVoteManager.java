package by.home.hryhoryeu.vote.services.managers;

import by.home.hryhoryeu.vote.dto.ReplyDTO;
import by.home.hryhoryeu.vote.dto.ResponseDTO;
import by.home.hryhoryeu.vote.entities.Vote;

public interface IVoteManager {

    Vote getVoteById(Long id);
    ResponseDTO addVote(Vote vote);
    void sendReply(ReplyDTO reply);
    void removeVote(Long id, String password);

}

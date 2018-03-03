package by.home.hryhoryeu.vote.services.managers.impl;

import by.home.hryhoryeu.vote.dba.dao.VoteRepository;
import by.home.hryhoryeu.vote.dto.ReplyDTO;
import by.home.hryhoryeu.vote.dto.ResponseDTO;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.managers.IVoteManager;
import by.home.hryhoryeu.vote.services.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

@Service
@Transactional
public class VoteManagerImpl implements IVoteManager{

    private final VoteRepository voteRepository;

    @Value("${vote.link}")
    private String pathLink;

    @Autowired
    public VoteManagerImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVoteById(Long id) {
       return voteRepository.getVoteById(id);
    }

    @Override
    public ResponseDTO addVote(Vote vote) {

        String password = Generator.getURL();
        vote.setPassword(password);
        vote.setTotalVotes(0);
        Long id = voteRepository.save(vote).getId();

        String voteLink = pathLink + "/vote/view/" + id;
        String removeLink = pathLink + "/vote/view/remove/" + id + "/" + password;

        ResponseDTO responseDTO = new ResponseDTO(voteLink, removeLink);

        return responseDTO;
    }

    @Override
    public void sendReply(ReplyDTO reply) {

        Vote vote = getVoteById(reply.getId());
        Map<String, Integer> options = vote.getOptions();
        options.replace(reply.getValue(), options.get(reply.getValue())+1);
        vote.setOptions(options);
        vote.setTotalVotes(vote.getTotalVotes() + 1);
        voteRepository.save(vote);
    }

    @Override
    public void removeVote(Long id, String password) {

        byte[] hash = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        voteRepository.deleteByIdAndPassword(id, Arrays.toString(hash));
    }
}

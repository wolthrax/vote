package by.home.hryhoryeu.vote.services.managers.impl;

import by.home.hryhoryeu.vote.dba.dao.VoteRepository;
import by.home.hryhoryeu.vote.dto.ReplyDTO;
import by.home.hryhoryeu.vote.dto.ResponseDTO;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.managers.IVoteManager;
import by.home.hryhoryeu.vote.services.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
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

        String password = Generator.getPsw();
        vote.setPassword(password);
        vote.setTotalVotes(0);
        Long id = voteRepository.save(vote).getId();

        String voteLink = pathLink + "/vote/view/" + id;
        String removeLink = pathLink + "/vote/view/remove/" + id + "/" + password;
        String resultLink = pathLink + "/vote/view/results/" + id;

        ResponseDTO responseDTO = new ResponseDTO(voteLink, removeLink, resultLink);

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
    public Boolean removeVote(Long id, String password) {

        Boolean removed = false;

        byte[] hash = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Vote vote = voteRepository.getByIdAndPassword(id, Arrays.toString(hash));
        if (vote != null) {
            voteRepository.deleteByIdAndPassword(id, Arrays.toString(hash));
            removed = true;
        }
        return removed;
    }

    @Override
    public List<Vote> getTopVotes() {
        Pageable topTen = new PageRequest(0, 10);
        return voteRepository.findTop(topTen);
    }
}

package by.home.hryhoryeu.vote.dba.dao;

import by.home.hryhoryeu.vote.entities.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote getVoteById(Long id);
    void deleteByIdAndPassword(Long id, String password);
    void findByIdAndPassword(Long id, String password);
}

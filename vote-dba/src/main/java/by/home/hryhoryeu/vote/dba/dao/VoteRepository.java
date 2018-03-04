package by.home.hryhoryeu.vote.dba.dao;

import by.home.hryhoryeu.vote.entities.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote getVoteById(Long id);
    void deleteByIdAndPassword(Long id, String password);
    Vote getByIdAndPassword(Long id, String password);

    @Query("select v from Vote v ORDER BY v.totalVotes desc")
    List<Vote> findTop(Pageable pageable);
}

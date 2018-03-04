package by.home.hryhoryeu.vote;

import by.home.hryhoryeu.vote.dba.dao.VoteRepository;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.utils.Generator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(VoteRepository voteRepository) {
		return (args) -> {

			Vote vote = new Vote();
			vote.setUserName("Maxim");
			vote.setPassword(Generator.getPsw());
			vote.setTopic("C++ or java");
			vote.setDescription("Do you like?");
			vote.setTotalVotes(0);

			Map<String, Integer> map = new HashMap<>();
			map.put("C++", 15);
			map.put("Java", 20);

			vote.setOptions(map);
			voteRepository.save(vote);

		};
	}

}

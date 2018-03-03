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
}

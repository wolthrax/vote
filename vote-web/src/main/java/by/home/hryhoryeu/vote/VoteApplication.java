package by.home.hryhoryeu.vote;

import by.home.hryhoryeu.vote.dba.dao.VoteRepository;
import by.home.hryhoryeu.vote.entities.Vote;
import by.home.hryhoryeu.vote.services.utils.Generator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@SpringBootApplication
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDb(VoteRepository voteRepository) {
		return (args) -> {

			voteRepository.save(buildVote("Maksim", "Соблюдение поста",
					"Соблюдаете ли вы Великий пост?",
					322,
					Collections.unmodifiableMap(Stream.of(
					new AbstractMap.SimpleEntry<>("Да", 20),
					new AbstractMap.SimpleEntry<>("Да, за одно и к лету похудею.", 23),
					new AbstractMap.SimpleEntry<>("Стараюсь, но невсегда получается.", 50),
					new AbstractMap.SimpleEntry<>("Нет", 76),
					new AbstractMap.SimpleEntry<>("Не соблюдаю по состоянию здоровья.", 55),
					new AbstractMap.SimpleEntry<>("Нет, невижу в этом смысл.", 98))
					.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));

			voteRepository.save(buildVote("Maksim",
					"Средство самозащиты",
					"Самое лучшее средство для самозащиты",
					131,
					Collections.unmodifiableMap(Stream.of(
							new AbstractMap.SimpleEntry<>("Газовый балончик", 66),
							new AbstractMap.SimpleEntry<>("Шокер", 24),
							new AbstractMap.SimpleEntry<>("Травматический пистолет", 36),
							new AbstractMap.SimpleEntry<>("Нож", 5))
							.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));

			voteRepository.save(buildVote("Maksim",
					"Общественный транспорт",
					"Передвигаетесь ли вы на общественном транспорте?",
					9,
					Collections.unmodifiableMap(Stream.of(
							new AbstractMap.SimpleEntry<>("Да", 4),
							new AbstractMap.SimpleEntry<>("Нет", 5))
							.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));

			voteRepository.save(buildVote("Maksim",
					"Выбор матраса",
					"На что при выборе матраса Вы обратите внимание в первую очередь?",
					36,
					Collections.unmodifiableMap(Stream.of(

							new AbstractMap.SimpleEntry<>("На жесткость и эластичность", 1),
							new AbstractMap.SimpleEntry<>("На основание (пружинные / беспружинные блоки)", 2),
							new AbstractMap.SimpleEntry<>("На натуральность материалов (кокос, латекс, сизаль и т.п.)", 3),
							new AbstractMap.SimpleEntry<>("На количество и толщину слоев", 4),
							new AbstractMap.SimpleEntry<>("На гарантию и срок службы", 5),
							new AbstractMap.SimpleEntry<>("На внешний вид матраса (дизайн)", 6),
							new AbstractMap.SimpleEntry<>("На цену", 7),
							new AbstractMap.SimpleEntry<>("Мне все равно", 8))
							.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));

		};
	}

	private Vote buildVote(String name, String topic, String description, int totalVotes, Map<String, Integer> options) {
		Vote vote = new Vote();
		vote.setUserName(name);
		vote.setPassword(Generator.getPsw());
		vote.setTopic(topic);
		vote.setDescription(description);
		vote.setTotalVotes(totalVotes);
		vote.setOptions(options);
		return vote;
	}

}

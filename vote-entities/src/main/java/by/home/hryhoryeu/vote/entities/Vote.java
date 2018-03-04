package by.home.hryhoryeu.vote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Entity
public class Vote implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    private String password;

    private String topic;

    private String description;

    @Column(name = "TOTAL_VOTES")
    private Integer totalVotes;

    @ElementCollection(targetClass = java.lang.Integer.class)
    @MapKeyClass(String.class)
    @MapKeyColumn(name = "option")
    @CollectionTable(name = "vote_options", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "value")
    private Map<String, Integer> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        byte[] hash = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.password = Arrays.toString(hash);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Map<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Integer> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) &&
                Objects.equals(userName, vote.userName) &&
                Objects.equals(password, vote.password) &&
                Objects.equals(topic, vote.topic) &&
                Objects.equals(description, vote.description) &&
                Objects.equals(totalVotes, vote.totalVotes) &&
                Objects.equals(options, vote.options);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, password, topic, description, totalVotes, options);
    }
}

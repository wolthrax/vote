package by.home.hryhoryeu.vote.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyDTO {

    private Long id;
    private String value;

    @JsonCreator
    public ReplyDTO(@JsonProperty("id") Long id, @JsonProperty("value")String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}

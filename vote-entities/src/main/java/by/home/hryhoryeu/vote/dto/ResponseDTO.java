package by.home.hryhoryeu.vote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {

    private final String voteLink;
    private final String removeLink;
    private final String resultLink;

    @JsonCreator
    public ResponseDTO(@JsonProperty("voteLink") String voteLink,
                       @JsonProperty("removeLink")String removeLink,
                       @JsonProperty("resultLink")String resultLink) {
        this.voteLink = voteLink;
        this.removeLink = removeLink;
        this.resultLink = resultLink;
    }

    public String getVoteLink() {
        return voteLink;
    }

    public String getRemoveLink() {
        return removeLink;
    }

    public String getResultLink() {
        return resultLink;
    }
}

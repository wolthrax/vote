package by.home.hryhoryeu.vote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {

    private String voteLink;
    private String removeLink;

    @JsonCreator
    public ResponseDTO(@JsonProperty("voteLink") String voteLink,
                       @JsonProperty("removeLink")String removeLink) {
        this.voteLink = voteLink;
        this.removeLink = removeLink;
    }

    public String getVoteLink() {
        return voteLink;
    }

    public String getRemoveLink() {
        return removeLink;
    }
}

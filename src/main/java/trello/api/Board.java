package trello.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    private String name;
    private String id;
    private boolean closed;
    private String desc;
    private boolean defaultLists;
    private boolean defaultLabels;

    public boolean isDefaultLists() {
        return defaultLists;
    }

    public void setDefaultLists(boolean defaultLists) {
        this.defaultLists = defaultLists;
    }

    public boolean isDefaultLabels() {
        return defaultLabels;
    }

    public void setDefaultLabels(boolean defaultLabels) {
        this.defaultLabels = defaultLabels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty
    public void setId(String boardId) {
        this.id = boardId;
    }

    @JsonIgnore
    public boolean isClosed() {
        return closed;
    }

    @JsonProperty
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

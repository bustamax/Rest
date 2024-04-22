package pojo;

import lombok.Data;

@Data
public class Board {

    private String name;
    private String key;
    private String token;
    private Boolean defaultLists;
    private Boolean defaultLabels;

}

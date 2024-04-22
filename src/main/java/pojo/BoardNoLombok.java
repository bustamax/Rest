package pojo;

public class BoardNoLombok {
    private String name;
    private String desc;
    private Boolean defaultLabels;
    private Boolean defaultLists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getDefaultLabels() {
        return defaultLabels;
    }

    public void setDefaultLabels(Boolean defaultLabels) {
        this.defaultLabels = defaultLabels;
    }

    public Boolean getDefaultLists() {
        return defaultLists;
    }

    public void setDefaultLists(Boolean defaultLists) {
        this.defaultLists = defaultLists;
    }
}

package Enums;

public enum TypeMap {
    CLASSICAL("Classical"),
    KILL_ALL("Kill all");

    private  TypeMap(String type) {
        this.typeMap = type;
    }

    private String typeMap;

    public String getTypeMap() {
        return typeMap;
    }
}

package fr.superprof.pathfinding;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    ROAD (' '),
    BLOCK ('x'),
    START ('S'),
    END ('F');

    private static final Map<Character, Type> ENUM_MAP = new HashMap<>();
    static {
        for (Type t : Type.values()) {
            ENUM_MAP.put(t.getType(), t);
        }
    }

    private Character type;

    Type(Character type) {
        this.type = type;
    }

    public static Type fromChar(Character c) {
        return ENUM_MAP.get(c);
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }
}

package com.mx.model;

import java.util.Objects;

public class Section {
    public int sid;
    public String name;

    public Section() {
    }

    public Section(int sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section section = (Section) o;
        return Objects.equals(name, section.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

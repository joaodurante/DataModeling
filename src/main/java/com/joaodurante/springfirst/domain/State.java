package com.joaodurante.springfirst.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<City> cities;

    public State(){}
    public State(Integer id, String name){
        this.id = id;
        this.name = name;
        cities = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public List<City> getCities(){ return cities; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return id.equals(state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

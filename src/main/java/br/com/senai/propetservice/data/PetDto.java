package br.com.senai.propetservice.data;

import br.com.senai.propetservice.models.User;

import java.io.Serializable;

public class PetDto implements Serializable {
    private Long id;

    private String name;


    private User user;

    private Float weight;

    private String description;

    public PetDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

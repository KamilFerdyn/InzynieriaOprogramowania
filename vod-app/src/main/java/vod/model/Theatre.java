package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatre")
public class Theatre {


    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @Column(name = "logo")
    private String logo; //url logo w przypadku UI będzie zaciągany dynamicznie

    @ManyToMany(mappedBy = "theatres")
    @JsonIgnore
    private List<Art> arts = new ArrayList<>();//struktura kolekcyjna związaną z granymi filmami, uproszczone
//relacja wiele do wiele


    @Transient
    @JsonIgnore
    private int demo;



    public Theatre(int id, String name, String logo) {//konsturktor
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Theatre() {//bezparametrowy
    }
//settery, gettery i to String - później będziemy korzystać z wynalazku Lombok
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Art> getArts() {
        return arts;
    }

    public void setArts(List<Art> arts) {
        this.arts = arts;
    }

    public void addArt(Art m) {
        this.arts.add(m);
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}

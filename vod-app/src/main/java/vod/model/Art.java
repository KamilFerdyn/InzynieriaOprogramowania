package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String poster;//url

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;//relacja do rezysera - kolejny obiekt danych w uproszczeniu założenie że jeden film ma 1 reżysera
    private float rating;//rating


    @ManyToMany
    @JoinTable(
            name="art_theatre",
            joinColumns = @JoinColumn(name="art_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="theatre_id", referencedColumnName = "id")
    )




    @JsonIgnore
    private List<Theatre> theatres = new ArrayList<>();
//relacja wiele do wiele - bidirectional

    public Art(int id, String title, String poster, Director director, float rating) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.director = director;
        this.rating = rating;
    }

    public Art() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }

    public void addTheatre(Theatre c) {
        this.theatres.add(c);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Art movie = (Art) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return poster != null ? poster.equals(movie.poster) : movie.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Art{" +
                "title='" + title + '\'' +
                ", director=" + director +
                ", rating=" + rating +
                '}';
    }
}

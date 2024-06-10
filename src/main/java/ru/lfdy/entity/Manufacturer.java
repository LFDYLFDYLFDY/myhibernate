package ru.lfdy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('manufacturers_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

//   @OneToMany(mappedBy = "manufacturerId")
//   @OneToMany
//    @JoinColumn(name ="manufacturer_id")
//    private List<Products> productses = new ArrayList<>();
  @OneToMany(mappedBy = "manId")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
   private List<Products> productses = new ArrayList<>();

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", title='" + title + '\'' +

                '}';
    }
}
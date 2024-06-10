package ru.lfdy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

//@Data
@Entity
@Table(name = "products")
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('products_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 256)
    private String title;

    @Column(name = "price", precision = 8, scale = 2)
    private BigDecimal price;

//    @Column(name = "manufacturer_id")
//    private Integer manufacturerId;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "manufacturer_id")
   private Manufacturer manId;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", manId=" + manId+
                '}';
    }
}
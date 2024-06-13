package ru.lfdy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.OptimisticLock;

@Data
@Entity
@Table(name = "big_items")
public class BigItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "val")
    private Integer val;

    @Column(name = "junkfield")
   @OptimisticLock(excluded = true)
    private Integer junkfield;


    @Version()
    long version;

    public BigItem(Integer val) {
        this.val = val;
    }

    public BigItem() {
    }
}
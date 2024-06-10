package ru.lfdy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "big_items")
public class BigItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('big_items_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "val")
    private Integer val;

    @Column(name = "junkfield")
    private Integer junkfield;

    @ColumnDefault("nextval('big_items_version_seq'")
    @Column(name = "version", nullable = false)
    private Integer version;

}
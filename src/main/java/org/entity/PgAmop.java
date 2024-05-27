package org.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_amop", uniqueConstraints = {
        @UniqueConstraint(name = "pg_amop_fam_strat_index", columnNames = {"amopfamily", "amoplefttype", "amoprighttype", "amopstrategy"}),
        @UniqueConstraint(name = "pg_amop_opr_fam_index", columnNames = {"amopopr", "amoppurpose", "amopfamily"})
})
public class PgAmop {
    @Id
    @Lob
    @Column(name = "oid", nullable = false)
    private String oid;

    @Lob
    @Column(name = "amopfamily", nullable = false)
    private String amopfamily;

    @Lob
    @Column(name = "amoplefttype", nullable = false)
    private String amoplefttype;

    @Lob
    @Column(name = "amoprighttype", nullable = false)
    private String amoprighttype;

    @Column(name = "amopstrategy", nullable = false)
    private Short amopstrategy;

    @Lob
    @Column(name = "amopopr", nullable = false)
    private String amopopr;

    @Lob
    @Column(name = "amopmethod", nullable = false)
    private String amopmethod;
    @Lob
    @Column(name = "amopsortfamily", nullable = false)
    private String amopsortfamily;

/*
 TODO [Reverse Engineering] create field to map the 'amoppurpose' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "amoppurpose", columnDefinition = ""char" not null")
    private Object amoppurpose;
*/
}
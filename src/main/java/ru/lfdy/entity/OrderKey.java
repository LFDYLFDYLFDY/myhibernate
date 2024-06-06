package ru.lfdy.entity;

import jakarta.persistence.criteria.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.List;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class OrderKey implements Serializable{
    static final long serialVersionUID = 1L;

      @Column(name = "person_id")
      private Long personID;

      @Column(name = "product_id")
      private Long productID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderKey orderKey = (OrderKey) o;
        return Objects.equals(getPersonID(), orderKey.getPersonID()) &&
               Objects.equals(getProductID(), orderKey.getProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonID(),getProductID());
    }
}

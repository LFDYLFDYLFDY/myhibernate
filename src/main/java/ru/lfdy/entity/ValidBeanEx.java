package ru.lfdy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

import java.util.Set;

public class ValidBeanEx {
    @Id
    @GeneratedValue
    @Column (name = "id")
    Long id;

    @Email
    @Column(name = "email")
    String email;
    @NotNull
    @Size(min = 6, max = 6)
    String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public ValidBeanEx(String postalCode, String email, Long id) {
        this.postalCode = postalCode;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ValidBeanEx{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;

    }

    public static void main(String[] args) {
        ValidBeanEx obj = new ValidBeanEx("12346500", "test@gmail.com", 1l);
        System.out.println(obj.toString());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        validator.validate(obj);
        Set<ConstraintViolation<ValidBeanEx>> vialations  = validator.validate(obj);
        for (ConstraintViolation<ValidBeanEx> o: vialations){
            System.out.println(o);
        }


    }
}

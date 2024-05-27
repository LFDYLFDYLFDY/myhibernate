package org.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(schema = "todolist",name = "activity", indexes = {
        @Index(name = "user_uuid_idx", columnList = "uuid"),
        @Index(name = "user_activated_idx", columnList = "activated"),
        @Index(name = "activity_user_id_idx", columnList = "user_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "user_uniq", columnNames = {"user_id"})
})
public class Activity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid", nullable = false, length = Integer.MAX_VALUE)
    private String uuid;

    @Column(name = "activated", nullable = false)
    private Short activated;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private UserDatum user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Short getActivated() {
        return activated;
    }

    public void setActivated(Short activated) {
        this.activated = activated;
    }

    public UserDatum getUser() {
        return user;
    }

    public void setUser(UserDatum user) {
        this.user = user;
    }

}
package org.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "todolist", name = "user_data", indexes = {
        @Index(name = "username_index", columnList = "username")
}, uniqueConstraints = {
        @UniqueConstraint(name = "email_uniq", columnNames = {"email"}),
        @UniqueConstraint(name = "username_uniq", columnNames = {"username"})
})

public class UserDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "userpassword", nullable = false, length = Integer.MAX_VALUE)
    private String userpassword;

    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    private String username;

    @OneToOne(mappedBy = "user")
    private Activity activity;

    @OneToMany(mappedBy = "user")
    private Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Priority> priorities = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user")
    private Stat stat;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new LinkedHashSet<>();

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

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Priority> getPriorities() {
        return priorities;
    }

    public void setPriorities(Set<Priority> priorities) {
        this.priorities = priorities;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "UserDatum{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", username='" + username + '\'' +
                ", activity=" + activity +
                ", categories=" + categories +
                ", priorities=" + priorities +
                ", stat=" + stat +
                ", tasks=" + tasks +
                ", userRoles=" + userRoles +
                '}';
    }


}
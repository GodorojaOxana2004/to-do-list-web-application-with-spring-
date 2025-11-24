package example.com.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",unique = false,nullable = false, length = 60)
    private String name;

    @Column(name = "email",unique = true,nullable = false, length = 50)
    private String email;

    @Column(name = "password",nullable = false,length = 10000)
    private String password;

    @Column(name = "role",nullable = false)
    private UserRole role;

    public List<Record> getRecords () {
        return records;
    }

    public void setRecords (List<Record> records) {
        this.records = records;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Record> records;

    public int getId(){
        return id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public User () {
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public UserRole getRole () {
        return role;
    }

    public void setRole (UserRole role) {
        this.role = role;
    }

    public User (String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public boolean isAdmin(){
        return this.role == UserRole.ADMIN;
    }
    public boolean isSuperAdmin(){
        return this.role == UserRole.SUPER_ADMIN;
    }
    public boolean isSimpleUser(){
        return this.role == UserRole.USER;
    }
}

package org.example.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.Enum.ActivityLevel;
import org.example.Enum.Gender;
import org.example.Enum.Target;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email",  nullable = false)
    private String email;

    @Column(name ="password_hash", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    @Min(1) @Max(120)
    private int age;

    @Column(name = "weight", nullable = false)
    @Min(0)
    private double weight;

    @Column(name = "height", nullable = false)
    @Min(value = 50, message = "Рост должен быть хотя бы 50 см.")
    @Max(value = 250, message = "Рост должен быть менее 250 см.")
    private  double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "target", nullable = false)
    private Target target;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @DecimalMin("1.2")
    @DecimalMax("1.9")
    @Column(name = "activity_level", nullable = false)
    private double activityLevel;

    public User() {}

    public User(String name, String email, String password, int age, double weight, double height, Target    target,  Gender gender, double activityLevel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.target = target;
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public Target getTarget() { return target; }
    public void setTarget(Target target) { this.target = target; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public double getActivityLevel() { return activityLevel; }
    public void setActivityLevel(double activityLevel) { this.activityLevel = activityLevel; }
}

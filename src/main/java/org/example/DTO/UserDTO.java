package org.example.DTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.Enum.Gender;
import org.example.Enum.Target;

public class UserDTO {
    private Long id;

    @NotEmpty(message = "Имя пользователя не должно быть пустым")
    private String name;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен содержать как минимум 8 символов")
    private String password;

    @NotEmpty(message = "Поле возраст не должно быть пустым")
    private int age;

    @NotEmpty(message = "Поле вес не должно быть пустым")
    private double weight;

    @NotEmpty(message = "Поле рост не должно быть пустым")
    private  double height;

    @NotEmpty(message = "Поле цель не должно быть пустым")
    @Enumerated(EnumType.STRING)
    private Target target;

    @NotEmpty(message = "Поле пол не должно быть пустым")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty(message = "Поле уровень активности не должно быть пустым")
    private double activityLevel;

    public UserDTO() {}

    public UserDTO(String name, String email, String password, int age, double weight, double height, Target target, Gender gender, double activityLevel) {
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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

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

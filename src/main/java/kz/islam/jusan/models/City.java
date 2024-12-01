package kz.islam.jusan.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название города не должно быть пустым!")
    private String name;

    private String country;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }
}

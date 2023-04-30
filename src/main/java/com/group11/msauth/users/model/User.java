package com.group11.msauth.users.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


//    @Size(min = 6, max = 20)
    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false,name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name="is_staff")
    @JsonProperty("is_staff")
    private boolean isStaff = false;

    @Column(nullable = false, name="is_active")
    @JsonProperty("is_active")
    private boolean isActive = true;

    private String roles;

    @Column(updatable = false,name="created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

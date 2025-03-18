package nz.clem.book.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotEmpty(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @NotEmpty(message = "Email address is mandatory")
    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Email not well formated")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message ="Password should be 8 characters long at least")
    private String password;

}

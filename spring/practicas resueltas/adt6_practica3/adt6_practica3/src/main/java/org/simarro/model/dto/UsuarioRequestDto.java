package org.simarro.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    @NotBlank
    private String nombre;

    @Size(min = 6, max = 50, message = "Apellidos debe tener entre 6 y 50.")
    private String apellidos;

    @Email
    private String email;

    @Pattern(regexp = "^[A-Za-z0-9]{4,8}$")
    private String password;
}

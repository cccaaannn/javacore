package com.kurtcan.javacore.security.validation.password.data.dtos;

import com.kurtcan.javacore.security.validation.password.data.enums.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordValidationResult {
    private Boolean status;
    private List<PasswordConstraint> passwordConstraints;
}

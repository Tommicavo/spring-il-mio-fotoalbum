package org.java.spring.springilmiofotoalbum.restController;

import java.util.List;
import java.util.stream.Collectors;
import org.java.spring.springilmiofotoalbum.auth.db.pojo.User;
import org.java.spring.springilmiofotoalbum.auth.db.serv.UserService;
import org.java.spring.springilmiofotoalbum.db.dto.LoginForm_DTO;
import org.java.spring.springilmiofotoalbum.db.pojo.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm_DTO loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ValidationError> errors = fieldErrors.stream()
                    .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
            System.out.println("ERRORS:\n" + errors);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        User user = userService.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package fa.training.controllers;

import fa.training.dto.SignIn;
import fa.training.dto.UserDTO;
import fa.training.dto.response.JwtResponse;
import fa.training.entites.RoleName;
import fa.training.entites.Roles;
import fa.training.entites.Users;
import fa.training.model.DataRespose;
import fa.training.security.jwt.JwtProvider;
import fa.training.security.userprincal.UserPrinciple;
import fa.training.services.RolesService;
import fa.training.services.UserServics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserServics userServics;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        if (userServics.existsByUserName(userDTO.getUserName())) {
            return new ResponseEntity<>(new DataRespose("error", "the username existed! please try again!"),
                    HttpStatus.OK);
        }
        if (userServics.existsByEmail(userDTO.getEmail())) {
            return new ResponseEntity<>(new DataRespose("error", "the email existed! please try again!"),
                    HttpStatus.OK);
        }
        Users user = new Users(userDTO.getUserName(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(),
                userDTO.getName());
        Set<String> strRole = userDTO.getRoles();

        Set<Roles> roles = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    Roles adminRole = rolesService.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(()->
                            new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                default:
                    Roles userRole = rolesService.findByRoleName(RoleName.ROLE_USER).orElseThrow(()->
                            new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setDate(LocalDate.now());
        user.setRoles(roles);
        userServics.addUser(user);
        return new ResponseEntity<>(new DataRespose(user), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login (@Valid @RequestBody SignIn signIn) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUserName(),signIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getIdUser(), userPrinciple.getFuillName(),
                roles));
    }
}

package ac.su.ctctct.controller;


import ac.su.ctctct.domain.User;
import ac.su.ctctct.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {



//    // userProfile 조회
//    @GetMapping("/{username}/profile")
//    public ResponseForm<ProfileDTO> getUserProfile (@PathVariable String username) {
//        ProfileDTO userProfile = userService.getUserProfile(username);
//        return new ResponseForm<>(HttpStatus.OK, userProfile, "User profile retrieved successfully");
//    }
}


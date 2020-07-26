package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.PasswordForgotForm;
import pendzu.sduteam.models.User;
import pendzu.sduteam.models.VerificationToken;
import pendzu.sduteam.services.VerificationTokenService;
import pendzu.sduteam.services.impl.EmailService;
import pendzu.sduteam.services.impl.UserServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("api/sdu/")
public class ForgotPasswordRestAPIs {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping("user/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordForgotForm passwordForgotForm) {
        User user = userService.findByEmail(passwordForgotForm.getEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        emailService.sendEmail(
                passwordForgotForm.getEmail(),
                "Đổi mật khẩu",
                "Nhấn vào đường dẫn sau để đổi mật khẩu :" + "http://localhost:4200/recover-password" + "?token=" + token.getToken());
        return new ResponseEntity<>(passwordForgotForm, HttpStatus.OK);
    }

    @RequestMapping(value = "new-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> updatePassword(@RequestParam("token") String token, @RequestBody User user) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        boolean isExpired = verificationToken.isExpired();
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (isExpired) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User currentUser = userService.findByEmail(verificationToken.getUser().getEmail());
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String newPassword = passwordEncoder.encode(user.getPassword());
        currentUser.setPassword(newPassword);
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}

package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.User;
import pendzu.sduteam.services.impl.UserServiceImpl;

import java.util.List;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/userList")
    public List<User> showListUser(){
        return (List<User>) userService.findAll();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

}

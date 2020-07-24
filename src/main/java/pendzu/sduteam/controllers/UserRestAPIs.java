package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.User;
import pendzu.sduteam.services.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class UserRestAPIs {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin/user")
    public ResponseEntity<List<User>> showListUser(){
        return ResponseEntity.ok((List<User>) userService.findAll());
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/block/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable Long id){
        userService.blockUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/active/{id}")
    public ResponseEntity<Void> activeUser(@PathVariable Long id){
        userService.activeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/user-list")
    public ResponseEntity<List<User>> getAllUserPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userList = userService.findAllUserPagination(pageable);
        return new ResponseEntity(userList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUserById (@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<User> editUser (@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        this.userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

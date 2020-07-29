package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.message.request.SearchUserByName;
import pendzu.sduteam.models.*;
import pendzu.sduteam.services.IDiaryService;
import pendzu.sduteam.services.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class UserRestAPIs {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/admin/user")
    public ResponseEntity<List<User>> showListUser() {
        return ResponseEntity.ok((List<User>) userService.findAll());
    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/block/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/active/{id}")
    public ResponseEntity<Void> activeUser(@PathVariable Long id) {
        userService.activeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/user-list")
    public ResponseEntity<List<User>> getAllUserPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Set<Role> role = new HashSet<>();
        role.add(new Role(1L, RoleName.ROLE_USER));
        Page<User> userList = userService.findAllUserPagination(pageable, role);
        List<User> userArrayList = userList.getContent();
        List<User> finalList = new ArrayList<>();
        for (int i = 0; i < userArrayList.size(); i++) {
            Set<Role> rolelist = userArrayList.get(i).getRoles();
            for (Iterator<Role> it = rolelist.iterator(); it.hasNext(); ) {
                Role f = it.next();
                if (f.getId() == 1) {
                    finalList.add(userArrayList.get(i));
                }
            }
        }

        List<User> totalUserList = (List<User>) userService.findAll();

        Page<User> list = new PageImpl<>(finalList, pageable, totalUserList.size());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
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

    @GetMapping("/user")
    public ResponseEntity<?> getListAllUser(){
        List<User> users = (List<User>) userService.findAll();

        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/user/{id}/diary")
    public ResponseEntity<?> getAllDiaryByUser(@PathVariable Long id) {
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByUserId(id);

        if(diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @PostMapping("/user/search-by-name")
    public ResponseEntity<?> getListUserByName(@RequestBody SearchUserByName userForm) {
        if(userForm.getName().equals("") || userForm.getName() == null) {
            List<User> users = (List<User>) userService.findAll();

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        }
        List<User> users = (List<User>) userService.findUsersByNameContaining(userForm.getName());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

//    @GetMapping("/user/search-by-query")
//    public ResponseEntity<?> searchUserRegistByMonth(){
//        List<Object[]> users = userService.findAllUserViaQuery();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @PostMapping("/user/search-by-date")
    public ResponseEntity<?> searchByDate(@RequestBody AdminReport adminReport){
        List<User> userList = (List<User>) userService.findAllByStatusAndCreateDateBetween(1, LocalDateTime.from(adminReport.getFromDate()), LocalDateTime.from(adminReport.getToDate()));
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}

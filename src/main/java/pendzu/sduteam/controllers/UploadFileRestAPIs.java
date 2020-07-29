package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pendzu.sduteam.message.request.FileForm;
import pendzu.sduteam.message.request.MultiFileForm;
import pendzu.sduteam.message.respone.ResponseMessage;
import pendzu.sduteam.models.Album;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.models.User;
import pendzu.sduteam.services.IDiaryService;
import pendzu.sduteam.services.IUserService;
import pendzu.sduteam.services.impl.AlbumFirebaseServiceExtends;
import pendzu.sduteam.services.impl.DiaryFirebaseServiceExtends;
import pendzu.sduteam.services.impl.UserFirebaseServiceExtends;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sdu")
public class UploadFileRestAPIs {
    @Autowired
    private IUserService userService;

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private UserFirebaseServiceExtends userFirebaseServiceExtends;

    @Autowired
    private DiaryFirebaseServiceExtends diaryFirebaseServiceExtends;

    @Autowired
    private AlbumFirebaseServiceExtends albumFirebaseServiceExtends;

    @Autowired
    Environment environment;

    @PostMapping(value = "/user-avatar/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> uploadAvatarUser(@ModelAttribute FileForm fileForm, BindingResult result, @PathVariable Long id) throws IOException {
        try {
            if (result.hasErrors()) {
                return new ResponseEntity<>(new ResponseMessage("Upload avatar user fail"), HttpStatus.BAD_REQUEST);
            }
            MultipartFile multipartFile = fileForm.getFile();
            Optional<User> user = userService.findById(id);

            if (!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (multipartFile != null) {
                if(user.get().getBlobString() == null) {
                    String urlFile = userFirebaseServiceExtends.saveToFirebaseStorage(user.get() , multipartFile);
                    user.get().setAvatar(urlFile);
                } else {
                    userFirebaseServiceExtends.deleteFirebaseStorageFile(user.get());
                    String urlFile = userFirebaseServiceExtends.saveToFirebaseStorage(user.get() , multipartFile);
                    user.get().setAvatar(urlFile);
                }
            }
            userService.save(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/diary-file/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> uploadDiaryFile(@ModelAttribute FileForm fileForm, BindingResult result, @PathVariable Long id) throws IOException {

        try {
            if (result.hasErrors()) {
                return new ResponseEntity<>(new ResponseMessage("Upload Diary File Fail!"), HttpStatus.BAD_REQUEST);
            }
            MultipartFile multipartFile = fileForm.getFile();
            Optional<Diary> diary = diaryService.findById(id);
            if (!diary.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (multipartFile != null) {
                if (diary.get().getBlobString() == null) {
                    String urlFile = diaryFirebaseServiceExtends.saveToFirebaseStorage(diary.get(), multipartFile);
                    diary.get().setUrlFile(urlFile);
                } else {
                    diaryFirebaseServiceExtends.deleteFirebaseStorageFile(diary.get());
                    String urlFile = diaryFirebaseServiceExtends.saveToFirebaseStorage(diary.get(), multipartFile);
                    diary.get().setUrlFile(urlFile);
                }
            }
            diaryService.save(diary.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e ,  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

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
import pendzu.sduteam.models.Image;
import pendzu.sduteam.models.User;
import pendzu.sduteam.services.IAlbumService;
import pendzu.sduteam.services.IDiaryService;
import pendzu.sduteam.services.IImageService;
import pendzu.sduteam.services.IUserService;
import pendzu.sduteam.services.impl.AlbumFirebaseServiceExtends;
import pendzu.sduteam.services.impl.DiaryFirebaseServiceExtends;
import pendzu.sduteam.services.impl.ImageFirebaseServiceExtends;
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
    private IImageService imageService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private UserFirebaseServiceExtends userFirebaseServiceExtends;

    @Autowired
    private DiaryFirebaseServiceExtends diaryFirebaseServiceExtends;

    @Autowired
    private AlbumFirebaseServiceExtends albumFirebaseServiceExtends;

    @Autowired
    private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

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

  @PostMapping(value = "/album-avatar/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
  public ResponseEntity<?> uploadAlbumAvatar(@ModelAttribute FileForm fileForm, BindingResult result, @PathVariable Long id) throws IOException {
    try {
      if (result.hasErrors()) {
        return new ResponseEntity<>(new ResponseMessage("Upload avatar album fail"), HttpStatus.BAD_REQUEST);
      }
      MultipartFile multipartFile = fileForm.getFile();
      Optional<Album> album = albumService.findById(id);

      if (!album.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      if (multipartFile != null) {
        if(album.get().getBlobstring() == null) {
          String urlFile = albumFirebaseServiceExtends.saveToFirebaseStorage(album.get() , multipartFile);
          album.get().setAvatar(urlFile);
        } else {
          albumFirebaseServiceExtends.deleteFirebaseStorageFile(album.get());
          String urlFile = albumFirebaseServiceExtends.saveToFirebaseStorage(album.get() , multipartFile);
          album.get().setAvatar(urlFile);
        }
      }
      albumService.save(album.get());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value = "/album-add-image/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
  public ResponseEntity<?> uploadAlbumImages(@ModelAttribute MultiFileForm multiFileForm, BindingResult result, @PathVariable Long id) throws IOException {
    try {
      if (result.hasErrors()) {
        return new ResponseEntity<>(new ResponseMessage("Upload Image album fail"), HttpStatus.BAD_REQUEST);
      }

      MultipartFile[] multipartFiles = multiFileForm.getFiles();
      Optional<Album> album = albumService.findById(id);

      if (!album.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      if (multipartFiles != null) {
        for (int i = 0 ; i < multipartFiles.length ; i++) {
          Image image = new Image();
          imageService.save(image);
          String urlFile = imageFirebaseServiceExtends.saveToFirebaseStorage(image , multipartFiles[i]);
          image.setUrl(urlFile);
          image.setAlbum(album.get());
          imageService.save(image);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

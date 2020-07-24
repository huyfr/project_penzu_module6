package pendzu.sduteam.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAppService {

    private static FirebaseApp firebaseApp;

    private FirebaseAppService() {}

    public static FirebaseApp getFirebaseApp() throws IOException {

        if (firebaseApp == null) {
            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\Admin\\Desktop\\Project Sdu Team\\sduteam.json");

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://project-sduteam.firebaseio.com")
                    .setStorageBucket("project-sduteam.appspot.com")
                    .build();

            firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
        }
        return firebaseApp;
    }
}

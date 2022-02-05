package br.com.ot.firebase;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitializer {

	@PostConstruct
	public void initialize() {
		try {

			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(
					new ClassPathResource("ibet-30b3d-firebase-adminsdk-zn2f4-f676802db4.json").getInputStream()))
					.setDatabaseUrl("https://ibet-30b3d.firebaseio.com").build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

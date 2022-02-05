package br.com.ot.service.ms;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import br.com.ot.dto.Ppt;
import br.com.ot.dto.ResultadoDTO;

@Service
public class MSFirebaseService {

	public static final String COL_NAME = "Resultado";

	public String savePatientDetails(ResultadoDTO resultadoDTO, Ppt ppt)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME)
				.document(ppt.getMegasenaidconcurso().getIdconcurso().toString()).set(resultadoDTO);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public ResultadoDTO getPatientDetails(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		ResultadoDTO resultadoDTO = null;

		if (document.exists()) {
			resultadoDTO = document.toObject(ResultadoDTO.class);
			return resultadoDTO;
		} else {
			return null;
		}
	}

	public String updatePatientDetails(ResultadoDTO resultadoDTO, Ppt ppt)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME)
				.document(ppt.getMegasenaidconcurso().getIdconcurso().toString()).set(resultadoDTO);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deletePatient(String name) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
		return "Document with ID " + name + " has been deleted";
	}

}

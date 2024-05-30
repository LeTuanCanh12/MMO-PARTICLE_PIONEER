package BE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

public class Firestore {
	public void update(String key) throws Exception {
		// Đường dẫn tới tệp tin JSON chứa thông tin xác thực
		String credentialsPath = "D:\\toolx.json";

		// Thiết lập tùy chọn FirestoreOptions
		FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
				.setCredentials(GoogleCredentials.fromStream(new FileInputStream(credentialsPath))).build();

		// Tạo đối tượng Firestore
		com.google.cloud.firestore.Firestore firestore = firestoreOptions.getService();
		// Thêm dữ liệu vào Firestore
		DocumentReference docRef = firestore.collection("users").document(key);

		// lay du liẹu
		ApiFuture<DocumentSnapshot> futureGet = docRef.get();
		DocumentSnapshot document = futureGet.get();
		String turnNumber = document.getString("turn");
		// Tru key
		int rs = Integer.valueOf(turnNumber) - 1;

		ApiFuture<WriteResult> future = docRef.update("turn", String.valueOf(rs));

		WriteResult writeResult = future.get();
		
		firestore.close();
		
	}

	public static void main(String[] args) throws Exception {
		
	}
}
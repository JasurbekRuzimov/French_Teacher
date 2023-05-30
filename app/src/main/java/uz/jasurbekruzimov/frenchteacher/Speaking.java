package uz.jasurbekruzimov.frenchteacher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Speaking extends AppCompatActivity {

    EditText editText;
    Button button;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking);

        editText = findViewById(R.id.editTextTextPDFName);
        button = findViewById(R.id.button);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("PDF yuklash");

        editText.setOnClickListener(v -> {
            selectPDFFile();
        });

        button.setEnabled(false);

    }

    private void selectPDFFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF faylini tanlang"), 12);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            button.setEnabled(true);
            editText.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            button.setOnClickListener(v -> {
                uploadPDFFileFirebase(data.getData());
            });

        }
    }

    private void uploadPDFFileFirebase(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fayl yuklanmoqda...");
        progressDialog.show();



        StorageReference reference = storageReference.child("PDF yuklash/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(taskSnapshot -> {

            reference.getDownloadUrl().addOnSuccessListener(uri -> {
                putPDF putPDF = new putPDF(editText.getText().toString(), uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                Toast.makeText(this, "Fayl yuklandi", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            });
        }).addOnProgressListener(snapshot -> {
            double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
            progressDialog.setMessage("Yuklanmoqda: "+(int)progress+"%");

        });

    }
}
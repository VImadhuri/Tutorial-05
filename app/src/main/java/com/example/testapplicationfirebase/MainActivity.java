package com.example.testapplicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity<StudentID> extends AppCompatActivity {
    EditText txtID, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate,btnDelete;
    DatabaseReference dbRef;
    Student std;
    private DatabaseReference databaseReference;
    private DatabaseReference DeleteDatabaseReference;
    private DatabaseReference UpdateDatabaseReference;
    private Student Student;
    private DatabaseReference ReadDatabaseReference;




    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.EtID);
        txtName = findViewById(R.id.EtName);
        txtAdd = findViewById(R.id.EtAddress);
        txtConNo = findViewById(R.id.EtConNo);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        std = new Student();


    btnSave.setOnClickListener(new View.OnClickListener(new View.OnClickListener() {
        class StudentID {
        }

        @Override
        public void onClick(View v) {
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Student");

            try {
                if (TextUtils.isEmpty(txtID.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter the ID ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Student Name ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtAdd.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter Student Address ", Toast.LENGTH_SHORT).show();
                } else {
                    Student.setID(txtID.getText().toString().trim());
                    Student.setName(txtName.getText().toString().trim());
                    Student.setAdd(txtAdd.getText().toString().trim());
                    Student.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));


                    AtomicInteger ID_GENERATOR;

                    //                        databaseReference.push().setValue(student);
                    //                        AUTO GENERATE ID
//                        int studentID = ID_GENERATOR.getAndIncrement();

                    databaseReference.child(String.valueOf(StudentID));
                    databaseReference.setValue(Student);


                    Toast.makeText(getApplicationContext(), "Data Successfully Saved", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
            }
        }
    });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Student").child("St1");

                ReadDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtID.setText(dataSnapshot.child("sId").getValue().toString());
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtAdd.setText(dataSnapshot.child("address").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("contact").getValue().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Student");
                UpdateDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("St1")) {
                            try {
                                Student.setID(txtID.getText().toString().trim());
                                Student.setName(txtName.getText().toString().trim());
                                Student.setAdd(txtAdd.getText().toString().trim());
                                Student.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));

                                UpdateDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Student").child("St1");
                                UpdateDatabaseReference.setValue(Student);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Successfully Updated ", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Student");
                DeleteDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("St1")) {
                            DeleteDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Student").child("St1");
                            DeleteDatabaseReference.removeValue();
                            clearControls();

                            Toast.makeText(getApplicationContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No Source to Delete ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}







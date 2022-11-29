package ru.smallstash.med;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.smallstash.med.controllers.UserController;

public class MainActivity extends AppCompatActivity{

    private Button regBtn;
    private Button inputBtn;
    private EditText name;
    private EditText surname;
    private EditText thirdname;
    private EditText phoneNumber;
    private EditText email;
    private EditText password;
    private EditText password2;
    private UserController userController = new UserController();

    private TextView errorView;

    private EditText login;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regBtn = (Button) findViewById(R.id.regBtn);
        inputBtn = (Button) findViewById(R.id.InputBtn);

    }

    public void onClickSignUpBtn(View view) {
        setContentView(R.layout.reglayout);

    }

    public void onClickSignInBtn(View view) {
        setContentView(R.layout.sign_in_layout);
    }

    @SuppressLint("MissingInflatedId")
    public void onClickConfirmReg(View view) {

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        thirdname = findViewById(R.id.thirdname);
        phoneNumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        errorView = findViewById(R.id.errorLabel);

        //ПЕРЕДЕЛАТЬ
        if(!inputValidation(name)){return;};
        if(!inputValidation(surname)){return;};
        if(!inputValidation(thirdname)){return;};
        if(!inputValidation(phoneNumber)){return;};
        if(!inputValidation(email)){return;};
        if(!inputValidation(password)){return;};
        if(!inputValidation(password2)){return;};

        if (!password.getText().toString().equals(password2.getText().toString())
                && !password.getText().toString().equals("")) {
            errorView.setText(getResources().getString(R.string.passwordsAreNotTheSame));
            return;
        }
        userController.createNewUser(name.getText().toString(),
                surname.getText().toString(),
                thirdname.getText().toString(),
                phoneNumber.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
        setContentView(R.layout.activity_main);
    }

    public void onClickBackToMain(View view) {
        setContentView(R.layout.activity_main);
    }

    public boolean inputValidation(EditText editText){
        if(editText.getText().toString().equals("")) {
            errorView.setText(getResources().getString(R.string.inputEmpty));
            return false;
        }
        if(editText.getText().toString().equals(" ")) {
            errorView.setText(getString(R.string.inputEmpty));
            return false;
        }
        if (editText.getText().toString().length() < 2){
            errorView.setText(getString(R.string.inputHaveUnderTwoSim));
            return false;
        }
        return true;
    }

    public void onClickUserAuth(View view){
        login = findViewById(R.id.signInLogin);
        pass = findViewById(R.id.signInPassword);
        userController.userSignInValidation(login.getText().toString(), pass.getText().toString());
    }

}
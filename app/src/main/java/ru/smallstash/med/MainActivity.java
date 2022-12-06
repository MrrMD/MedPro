package ru.smallstash.med;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    private Spinner adminPanelHospitalSpinner;
    private Spinner adminPanelPostlSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter2;


    private Spinner hospitalSpinner;
    private String hospital;
    private Spinner postSpinner;
    private String post;
    private List<CheckBox> daysCheckBoxList = new ArrayList<>();
    private List<String> daysList = new ArrayList<>();
    private List<CheckBox> timesCheckBoxList = new ArrayList<>();
    private List<String> timesList = new ArrayList<>();
    private Button submitBtn;


    {
        System.out.println("Admin creating");
        userController.createNewUser("admin", "admin","Clinic Hospital №1");
        userController.createNewUser("123","123","123","123","123","123");

    }

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

        if(!inputsInit()){
            return;
        };

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

        if (!password.getText().toString().equals(password2.getText().toString())
                && !password.getText().toString().equals("")) {
            errorView.setText(getResources().getString(R.string.passwordsAreNotTheSame));
            return false;
        }
        if(userController.userEmailExistCheck(email.getText().toString())){
            errorView.setText("Пользователь с данной почтой уже существует");
            return false;
        }

        return true;
    }

    public void onClickUserAuth(View view){
        System.out.println("asd");
        login = findViewById(R.id.signInLogin);
        pass = findViewById(R.id.signInPassword);
        if(userController.userSignInValidation(login.getText().toString(), pass.getText().toString())){
            if(userController.isAdmin(login.getText().toString())){
                setContentView(R.layout.reg_employee_layout_1);
                return;
            }
            setContentView(R.layout.patientprofile);
        }
    }

    @SuppressLint("MissingInflatedId")
    public void onClickNextEmployeeRegLayout(View view){

        if(!inputsInit()){
            return;
        }

        setContentView(R.layout.reg_employee_layout_2);

        adminPanelHospitalSpinner = (Spinner) findViewById(R.id.adminPanelHospitalSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.hospitales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adminPanelPostlSpinner = (Spinner) findViewById(R.id.adminPanelPostlSpinner);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.posts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adminPanelHospitalSpinner.setAdapter(adapter);
        adminPanelPostlSpinner.setAdapter(adapter2);
    }

    public void onClickSubmit(View view){
        hospitalSpinner = findViewById(R.id.adminPanelHospitalSpinner);
        postSpinner  = findViewById(R.id.adminPanelPostlSpinner);
        submitBtn = findViewById(R.id.submit);

        hospital = hospitalSpinner.getSelectedItem().toString();
        post = postSpinner.getSelectedItem().toString();
        System.out.println(hospital);
        System.out.println(post);
        getAllChilds(daysCheckBoxList, findViewById(R.id.days));
        getAllChilds(daysCheckBoxList, findViewById(R.id.days2));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times1));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times2));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times3));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times4));

        checkCheckBoxes(timesCheckBoxList, timesList);
        checkCheckBoxes(daysCheckBoxList, daysList);

        userController.createNewUser(
                name.getText().toString(),
                surname.getText().toString(),
                thirdname.getText().toString(),
                phoneNumber.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                post,
                daysList,
                timesList,
                hospital);
        setContentView(R.layout.activity_main);
    }

    private boolean inputsInit(){
        name = findViewById(R.id.surname);
        surname = findViewById(R.id.name);
        thirdname = findViewById(R.id.thirdname);
        phoneNumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        errorView = findViewById(R.id.errorLabel);

        //ПЕРЕДЕЛАТЬ
        if(!inputValidation(name)){return false;};
        if(!inputValidation(surname)){return false;};
        if(!inputValidation(thirdname)){return false;};
        if(!inputValidation(phoneNumber)){return false;};
        if(!inputValidation(email)){return false;};
        if(!inputValidation(password)){return false;};
        if(!inputValidation(password2)){return false;};
        return true;
    }

    private void getAllChilds(List<CheckBox> list,LinearLayout linearLayout){
        int daysChildCount = linearLayout.getChildCount();
        for (int i = 0; i < daysChildCount; i++){
            System.out.println(linearLayout.getChildAt(i));
            list.add((CheckBox)(linearLayout.getChildAt(i)));
        }
    }

    private void checkCheckBoxes(List<CheckBox> checkBoxes, List<String> list){
        for (CheckBox box:checkBoxes) {
            if(box.isChecked()){
                list.add(box.getText().toString());
            }
        }
    }

    public void onClickBackToRegEmployee_One(View view){
        setContentView(R.layout.reg_employee_layout_1);
    }
    //DOCTORAPPOITMENT

    private String DAHospitalSpinnerSelected;
    private String DAPostSpinnerSelected;
    private Spinner DADoctorSpinner;
    private ArrayAdapter<String > DADoctorSpinnerAdapter;
    private Spinner DAPostSpinner;
    private List<String> doctorsList;
    @SuppressLint("MissingInflatedId")
    public void onClickDoctorAppointment(View view){

        setContentView(R.layout.doctorsappointment);

        Spinner DAHospitalSpinner = (Spinner) findViewById(R.id.DAHospitalSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.hospitales, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DAHospitalSpinner.setAdapter(adapter3);
        DAHospitalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DAHospitalSpinnerSelected = DAHospitalSpinner.getSelectedItem().toString();
                System.out.println(DAHospitalSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DAPostSpinner = (Spinner) findViewById(R.id.DAPostSpinner);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.posts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DAPostSpinner.setAdapter(adapter2);

        DAPostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DAPostSpinnerSelected = DAPostSpinner.getSelectedItem().toString();
                doctorsListInit();
                if(doctorsList == null){return;}
                DADoctorSpinner.setAdapter(DADoctorSpinnerAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void doctorsListInit(){
        DADoctorSpinner = (Spinner) findViewById(R.id.DADoctorSpinner);
        doctorsList = userController.getEmployeeByHospitalAndPost(DAHospitalSpinnerSelected, DAPostSpinnerSelected);
        DADoctorSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, doctorsList);
        DADoctorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
    }

    public void onClickHospitalSpinner(View view){
        System.out.println("123123");
    }

}




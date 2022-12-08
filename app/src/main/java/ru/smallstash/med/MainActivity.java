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

import ru.smallstash.med.controllers.OrdersController;
import ru.smallstash.med.controllers.UserController;
import ru.smallstash.med.entites.Admin;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Order;
import ru.smallstash.med.entites.Patient;

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
    private OrdersController ordersController = new OrdersController();
    private TextView errorView;

    private EditText login;
    private EditText pass;
    private Spinner adminPanelPostlSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter2;

    private String hospital;
    private Spinner postSpinner;
    private String post;
    private List<CheckBox> daysCheckBoxList = new ArrayList<>();
    private List<String> daysList = new ArrayList<>();
    private List<CheckBox> timesCheckBoxList = new ArrayList<>();
    private List<String> timesList = new ArrayList<>();
    private Button submitBtn;

    private Employee activeUser;
    private Admin activeAdmin;
    private Patient activePatient;

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
        login = findViewById(R.id.signInLogin);
        pass = findViewById(R.id.signInPassword);

        Object object = userController.userSignInValidation(login.getText().toString(), pass.getText().toString()) ;

        if(object != null){
            if(userController.isAdmin(login.getText().toString())){
                activeAdmin = (Admin) object;
                System.out.println("admin");
                setContentView(R.layout.activity_admin_profile);
                return;
            }
            activePatient = (Patient) object;
            System.out.println("Patient");
            setContentView(R.layout.patientprofile);
        }
    }

    public void onClickAddEmployee(View view){
        setContentView(R.layout.reg_employee_layout_1);
    }

    @SuppressLint("MissingInflatedId")
    public void onClickNextEmployeeRegLayout(View view){

        if(!inputsInit()){
            return;
        }

        setContentView(R.layout.reg_employee_layout_2);

        adminPanelPostlSpinner = (Spinner) findViewById(R.id.adminPanelPostlSpinner);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.posts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adminPanelPostlSpinner.setAdapter(adapter2);
    }

    @SuppressLint("MissingInflatedId")
    public void onClickSubmit(View view){
        postSpinner  = findViewById(R.id.adminPanelPostlSpinner);
        submitBtn = findViewById(R.id.submit);

        hospital = activeAdmin.getPrivateHospital();
        post = postSpinner.getSelectedItem().toString();
        System.out.println(hospital);
        System.out.println(post);
        daysCheckBoxList.clear();
        timesCheckBoxList.clear();
        getAllChilds(daysCheckBoxList, findViewById(R.id.days));
        getAllChilds(daysCheckBoxList, findViewById(R.id.days2));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times1));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times2));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times3));
        getAllChilds(timesCheckBoxList, findViewById(R.id.times4));
        daysList.clear();
        timesList.clear();

        timesList = checkCheckBoxes(timesCheckBoxList);
        daysList = checkCheckBoxes(daysCheckBoxList);

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

        setContentView(R.layout.reg_employee_layout_1);
    }

    private boolean inputsInit(){
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
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

    private List<String> checkCheckBoxes(List<CheckBox> checkBoxes){
        List<String> list = new ArrayList<>();
        for (CheckBox box:checkBoxes) {
            if(box.isChecked()){
                list.add(box.getText().toString());
            }
        }
        return list;
    }

    public void onClickBackToRegEmployee_One(View view){
        setContentView(R.layout.activity_admin_profile);
    }
    //DOCTORAPPOITMENT

    private Spinner postSpinnerDA;
    private Spinner doctorSpinnerDA;
    private Spinner daysSpinnerDA;
    private Spinner hospitalSpinnerDA;
    private Spinner timesSpinnerDA;

    private String hospitalSpinnerSelectedDA;
    private String postSpinnerSelectedDA;
    private Employee doctorSpinnerSelectedDA;

    private ArrayAdapter<Employee> doctorSpinnerAdapterDA;
    private ArrayAdapter<String> daysSpinnerAdapterDA;
    private ArrayAdapter<CharSequence> postSpinnerAdapterDA;
    private ArrayAdapter<String> timesSpinnerAdapterDA;

    private List<Employee> doctorsList;
    private List<String> doctorReceptionTimeList;
    private List<String> receptionDaysList;


    public void onClickBackToProfile(View view){
        setContentView(R.layout.patientprofile);
    }

    @SuppressLint("MissingInflatedId")
    public void onClickDoctorAppointment(View view){

        setContentView(R.layout.doctorsappointment);
        hospitalSpinnerDA = (Spinner) findViewById(R.id.DAHospitalSpinner);
        postSpinnerDA = (Spinner) findViewById(R.id.DAPostSpinner);

        ArrayAdapter<CharSequence> hospitalSpinnerAdapterDA = ArrayAdapter.createFromResource(this,
                R.array.hospitales, android.R.layout.simple_spinner_item);
        hospitalSpinnerAdapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalSpinnerDA.setAdapter(hospitalSpinnerAdapterDA);
        hospitalSpinnerDA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hospitalSpinnerSelectedDA = hospitalSpinnerDA.getSelectedItem().toString();
                hospitalsInit();
                postSpinnerDA.setAdapter(postSpinnerAdapterDA);
                if(daysSpinnerDA != null){
                    daysSpinnerDA.setAdapter(null);
                }
                if(timesSpinnerDA != null){
                    timesSpinnerDA.setAdapter(null);
                }
                if(doctorSpinnerDA!=null) {
                    doctorSpinnerDA.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        postSpinnerDA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(daysSpinnerDA != null){
                    daysSpinnerDA.setAdapter(null);
                }
                if(timesSpinnerDA != null){
                    timesSpinnerDA.setAdapter(null);
                }
                postSpinnerSelectedDA = postSpinnerDA.getSelectedItem().toString();
                doctorsListInit();
                if(doctorsList == null){return;}
                doctorSpinnerDA.setAdapter(doctorSpinnerAdapterDA);
                doctorSpinnerDA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        doctorSpinnerSelectedDA = (Employee) doctorSpinnerDA.getSelectedItem();
                        doctorsDaysInit();

                        if(receptionDaysList == null){return;}
                        daysSpinnerDA.setAdapter(daysSpinnerAdapterDA);
                        daysSpinnerDA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                doctorsTimesInit();
                                if(doctorReceptionTimeList == null){return;}
                                timesSpinnerDA.setAdapter(timesSpinnerAdapterDA);
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClickSubmitAppointment(View view){
        ordersController.newOrder(activePatient, doctorSpinnerSelectedDA, daysSpinnerDA.getSelectedItem().toString(),timesSpinnerDA.getSelectedItem().toString());
        setContentView(R.layout.patientprofile);
    }

    @SuppressLint("NewApi")
    public void obClickPatientOrders(View view){
        List<Order> list = activePatient.getOrders();
        list.forEach(System.out::println);
    }

    private void hospitalsInit(){
        postSpinnerAdapterDA = ArrayAdapter.createFromResource(this,
                R.array.posts, android.R.layout.simple_spinner_item);
        postSpinnerAdapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void doctorsListInit(){
        doctorSpinnerDA = (Spinner) findViewById(R.id.DADoctorSpinner);
        doctorsList = userController.getEmployeeByHospitalAndPost(hospitalSpinnerSelectedDA, postSpinnerSelectedDA);
        doctorSpinnerAdapterDA = new ArrayAdapter<Employee>(
                this, android.R.layout.simple_spinner_item, doctorsList);
        doctorSpinnerAdapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
    }

    private void doctorsDaysInit(){
        daysSpinnerDA = (Spinner) findViewById(R.id.DADaysSpinner);
        receptionDaysList = doctorSpinnerSelectedDA.getDays();
        daysSpinnerAdapterDA = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, doctorSpinnerSelectedDA.getDays());
        daysSpinnerAdapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
    }
    private void doctorsTimesInit(){
        timesSpinnerDA = (Spinner) findViewById(R.id.DATimeSpinner);
        doctorReceptionTimeList = doctorSpinnerSelectedDA.getReceptionHours();
        timesSpinnerAdapterDA = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, doctorReceptionTimeList);
        timesSpinnerAdapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
    }

}




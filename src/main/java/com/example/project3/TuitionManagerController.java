package com.example.project3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This is a controller class which contains methods and directions on what to execute when different buttons are clicked in the View file.
 * The controller class is connected to the TuitionManagerView file which is the graphical user interface
 * @author Yehun Kim, Apurva Desai
 * */
public class TuitionManagerController {
    private Major m = com.example.project3.Major.EE;
    @FXML
    private RadioButton rbEE, rbITI, rbCS, rbMATH, rbBAIT;
    @FXML
    private RadioButton Resident;
    @FXML
    private TextField fname, lname, creditsCompleted;
    @FXML
    private TextField efname, elname;
    @FXML
    private DatePicker eDOB, sDOB, rosterDOB;
    @FXML
    private TextField creditsEnrolled, amount;
    @FXML
    private Button enroll;
    @FXML
    private Button drop;
    @FXML
    private TextField sfname, slname;
    @FXML
    private Button scholarship;
    @FXML
    private RadioButton NonResident, International, Tristate;
    @FXML
    private RadioButton Ct;
    @FXML
    private RadioButton Ny;
    @FXML
    private CheckBox isStudyAbroad;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField loadFile;
    @FXML
    private MenuItem semesterend;
    @FXML
    private ToggleGroup Major, TriState, CheckResident;
    private Roster studentArray = new Roster();
    private Enrollment enrolledStudents = new Enrollment();

    private static final int SENIOR_UPPER_END = 120;

    /**
     * This method performs the following action every time the resident radio button is pressed
     * The actions perform include disabling radiobuttons such as international, tristate, Ny, Ct every time resident is clicked.
     * */
    @FXML
    protected void setResident() {
        this.International.setDisable(true);
        this.Tristate.setDisable(true);
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.isStudyAbroad.setDisable(true);
        this.International.setSelected(false);
        this.Tristate.setSelected(false);
        this.Ny.setSelected(false);
        this.Ct.setSelected(false);
        this.isStudyAbroad.setSelected(false);
    }

    /**
     * This method performs the following action every time the Non-resident radio button is pressed
     * The actions perform include enables radiobuttons such as international, tristate, Ny, Ct every time Non-resident is clicked.
     * */
    @FXML
    protected void setNonResident(){
        this.International.setDisable(false);
        this.Tristate.setDisable(false);
        this.Ny.setDisable(false);
        this.Ct.setDisable(false);
        this.isStudyAbroad.setDisable(false);
    }

    /**
     * This method performs the following action every time the tristate radio button is pressed
     * The actions perform include enables radiobuttons such as NY and CT
     * */
    @FXML
    protected void setTriState(){
        this.isStudyAbroad.setDisable(true);
        this.Ny.setDisable(false);
        this.Ct.setDisable(false);
    }
    /**
     * This method performs the following action every time the International radio button is pressed
     * The actions perform include disabling radiobuttons such as Ny, Ct every time International is clicked.
     * */
    @FXML
    protected void setInternational(){
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.isStudyAbroad.setDisable(false);
    }
    /**
     * This method takes a string parameter and checks if it is an integer or not.
     * @param s this is a string parameter which is passed to check if it is an integer or not
     * @return returns a boolean true if it is an integer or false if not
     */
    private boolean areCreditsValid(String s) {
        try {
            if ((Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= SENIOR_UPPER_END) == true) ;
            return true;
        } catch (Exception e) {
            textArea.appendText("Credits completed invalid: not an integer!" + "\n");
            return false;
        }
    }

    /**
     * This method takes in a date parameter and returns a boolean stating whether the date is valid or not.
     * @param date the date parameter to be checked
     * @return returns a boolean, if the date is valid then returns true, else prints a statement and returns false
     */
    private boolean NewIsValid(Date date){
        if(date.isYoung()){
            textArea.appendText("DOB Invalid " + date + " is younger than 16 years" + "\n");
            return false;
        }
        if(!date.isValid()){
            textArea.appendText("DOB Invalid " + date + " is not a calendar date" + "\n");
            return false;
        }
        return true;
    }

    /**
     * This method returns a boolean on whether there is an error in the textfield used for the method
     * @param info the array which contains information typed into the text field
     * @return returns a boolean false if there is a text field empty, otherwise true
     * */
    private boolean errorsInRoster(String[] info){
        try{
            rosterDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("credits text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and credits text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and credits text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and credits text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" credits text field is empty" + "\n");return false;}
        return true;
    }

    /**
     * This method sets the major of a student to EE
     * @param event this method is invoked every time the EE radio button is selected
     * */
    @FXML
    void EE(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbEE.getText());}

    /**
     * This method sets the major of a student to BAIT
     * @param event this method is invoked every time the BAIT radio button is selected
     * */
    @FXML
    void BAIT(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbBAIT.getText());
    }

    /**
     * This method sets the major of a student to ITI
     * @param event this method is invoked every time the ITI radio button is selected
     * */
    @FXML
    void ITI(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbITI.getText());
    }

    /**
     * This method sets the major of a student to CS
     * @param event this method is invoked every time the CS radio button is selected
     * */
    @FXML
    void CS(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbCS.getText());
    }

    /**
     * This method sets the major of a student to MATH
     * @param event this method is invoked every time the MATH radio button is selected
     * */
    @FXML
    void MATH(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbMATH.getText());
    }


    /**
     * This method takes in an array parameter and adds a student to the roster if the student is a resident
     * @param tokens is the array which contains all the information of the student as its elements
     * tokens[1] represents first name, tokens[2] represents last name, tokens[3] represents DOB and so on
     */
    private void addResidentStudent(String[] tokens) {
            Date date = new Date(tokens[2]);
            if (NewIsValid(date)) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new Resident(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " resident added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            }
    }

    /**
     * This method takes in an array parameter and adds a student to the roster if the student is a international study abroad student
     * @param tokens is the array which contains all the information of the student as its elements
     * tokens[1] represents first name, tokens[2] represents last name, tokens[3] represents DOB and so on
     */
    private void addInternationalStudentStudyAbroad(String[] tokens){
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new International(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), true);
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[2] + " is not a valid calendar date" + "\n");
            }
    }

    /**
     * This method takes in an array parameter and adds a student to the roster if the student is an International student
     * @param tokens is the array which contains all the information of the student as its elements
     * tokens[1] represents first name, tokens[2] represents last name, tokens[3] represents DOB and so on
     * */
    private void addInternationalStudent(String[] tokens){
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new International(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[2] + " is not a valid calendar date" + "\n");
            }
    }

    /**
     * This method takes in an array parameter and adds a student to the roster if the student is a Non-Resident student
     * @param tokens is the array which contains all the information of the student as its elements
     * tokens[1] represents first name, tokens[2] represents last name, tokens[3] represents DOB and so on
     * */
    private void addNonResidentStudent(String[] tokens){
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new NonResident(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " non resident student added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[3] + " is not a valid calendar date" + "\n");
            }
    }

    /**
     * This method takes in an array parameter and adds a student to the roster if the student is a Tri-State student
     * @param tokens is the array which contains all the information of the student as its elements
     * tokens[1] represents first name, tokens[2] represents last name, tokens[3] represents DOB and so on
     * */
    private void addTriStateStudent(String[] tokens, boolean b){
                if (areCreditsValid(tokens[3])) {
                        if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                            if (b == true) {
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "NY");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " (TriState NY) student added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }else{
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "CT");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " (TriState CT) student added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }
                            } else {
                                textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                            }

                        }
    }

    /**
     * This method take a String input of the form yyyy-mm-dd and converts it to the form mm/dd/yyyy
     * @param a the string of the date to be converted
     * @return returns a String of the converted form mm/dd/yyyy
     * */
    private String dateConverter(String a ){
        String[] date = a.split("-");
        return date[1] + "/" + date[2] + "/" + date[0];
    }

    /**
     * This method adds a student to the roster
     * @param action this method is invoked every time the add button is clicked
     * */
    @FXML
    void add(ActionEvent action){
        String[] info = new String[5];
        info[0] = fname.getText();
        info[1] = lname.getText();
            info[3] = creditsCompleted.getText();
            info[4] = m.name();
            if(errorsInRoster(info)) {
                info[2] = dateConverter(rosterDOB.getValue().toString());
                if (Resident.isSelected()) {
                    addResidentStudent(info);
                } else {
                    if (NonResident.isSelected() && International.isSelected() && isStudyAbroad.isSelected()) {
                        addInternationalStudentStudyAbroad(info);
                    } else {
                        if (NonResident.isSelected() && International.isSelected()) {
                            addInternationalStudent(info);
                        } else {
                            if (NonResident.isSelected() && (Ny.isSelected() || Ct.isSelected())) {
                                if (Ny.isSelected()) {
                                    addTriStateStudent(info, true);
                                } else {
                                    addTriStateStudent(info, false);
                                }
                            } else {
                                if (NonResident.isSelected()) {
                                    addNonResidentStudent(info);
                                }
                            }
                        }

                    }
                }
                fname.setText("");
                lname.setText("");
                creditsCompleted.setText("");
                rosterDOB.setValue(null);
            }
    }

    /**
     * This method is a helper method to the original remove method
     * It checks if the text fields have an error or not and whether the student is in the roster or not
     * @param array the array which contains information regarding the student to be removed
     * */
    private void remove(String[] array){
        if(errorsInRosterForRemove(array)){
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, 0);
            EnrollStudent enrolledStudent = new EnrollStudent(profile, 0);
            if (!studentArray.contains(student)) {
                textArea.appendText(profile.toString() + " is not in the roster.." + "\n");
            }else{
                if(enrolledStudents.contains(enrolledStudent)){
                    textArea.appendText("student is currently enrolled, so cannot be removed" + "\n");
                }
                else {
                    studentArray.remove(student);
                    textArea.appendText(profile.toString() + " removed from the roster." + "\n");
                }
            }
        }
    }

    /**
     * This method checks whether there are any exceptions. It check if any of the text fields is empty or not and returns a message
     * @param info the array which contains information of the different text fields
     * @return returns a boolean false if any field is empty, otherwise true.
     * */
    private boolean errorsInRosterForRemove(String[] info){
        try{
            rosterDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank()){
                textArea.appendText("first name, last name, and DOB is empty" + "\n");
                return false;
            }
            if(info[0].isBlank()){
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;
            }
            if(info[1].isBlank()){
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;
            }
            textArea.appendText("DOB is empty" + "\n");
            return false;
        }
        if(info[0].isBlank() && info[1].isBlank()){
            textArea.appendText("first name and last name is empty.");
            return false;
        }
        if(info[0].isBlank()){
            textArea.appendText("first name is empty" + "\n");
            return false;
        }
        if(info[1].isBlank()){
            textArea.appendText("last name is empty" + "\n");
            return false;
        }
        return true;
    }


    /**
     * This method removes a student from the roster
     * @param action this method is invoked every time the remove button is clicked
     * */
    @FXML
    void remove(ActionEvent action){
        String[] info = new String[3];
        info[0] = fname.getText();
        info[1] = lname.getText();
        if(errorsInRosterForRemove(info)) {
            String a = rosterDOB.getValue().toString();
            info[2] = dateConverter(a);
            remove(info);
            fname.setText("");
            lname.setText("");
            rosterDOB.setValue(null);
        }
    }

    /**
     * This is a helper method to the original changeMajor method, and changes the major of the student if the student is in the roster
     * @param array the array contains the information of the student
     * */
    private void changeMajor(String[] array){
        if(errorsInRosterForRemove(array)) {
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            Major majorEnum;
            if (!studentArray.contains(student)) {
                textArea.appendText(profile.toString() + " is not in the roster." + "\n");
            }
            if (studentArray.contains(student)) {
                majorEnum = m;
                student.setMajor(majorEnum);
                studentArray.changedMajor(student);
                textArea.appendText(profile.toString() + " major changed to " + m.name() + "\n");
            }
            fname.setText("");
            lname.setText("");
            creditsCompleted.setText("");
            rosterDOB.setValue(null);
        }
    }


    /**
     *This method changes the major of a student
     * @param event this mehtod is invoked every time the changeMajor button is clicked
     * */
    @FXML
    void changeMajor(ActionEvent event){
        String[] info = new String[3];
        info[0] = fname.getText();
        info[1] = lname.getText();
        if(errorsInRosterForRemove(info)){
            info[2] = rosterDOB.getValue().toString();
            changeMajor(info);
            fname.setText("");
            lname.setText("");
            rosterDOB.setValue(null);
        }
    }

    /**
     * This method checks if there are eny empty text fields or unexpected errors in the text fields for the enroll button
     * @param info the array that contains the input information from the text fields
     * @return returns false if there are any empty text fields, otherwise true
     * */
    private boolean errorsInRosterForEnroll(String[] info){
        try{
            eDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("credits text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and credits text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and credits text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and credits text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" credits text field is empty" + "\n");return false;}
        return true;
    }

    /**
     * This method checks if there are eny empty text fields or unexpected errors in the text fields for the drop button
     * @param info the array that contains the input information from the text fields
     * @return returns false if there are any empty text fields, otherwise true
     * */
    private boolean errorsInRosterForDrop(String[] info){
        try{
            eDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank()){
                textArea.appendText("first name, last name, and DOB is empty" + "\n");
                return false;
            }
            if(info[0].isBlank()){
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;
            }
            if(info[1].isBlank()){
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;
            }
            textArea.appendText("DOB is empty" + "\n");
            return false;
        }
        if(info[0].isBlank() && info[1].isBlank()){
            textArea.appendText("first name and last name is empty.");
            return false;
        }
        if(info[0].isBlank()){
            textArea.appendText("first name is empty" + "\n");
            return false;
        }
        if(info[1].isBlank()){
            textArea.appendText("last name is empty" + "\n");
            return false;
        }
        return true;
    }


    /**
     * This is a helper method to enroll a student, it checks for errors in text fields and whether student exists in the roster or not
     * @param array the array contains information of the student
     * */
    private void enrollStudent(String[] array){
        if(errorsInRosterForEnroll(array)){
            if(areCreditsValid(array[3])){
                Profile profile = new Profile(array[1], array[0], array[2]);
                Student student = new Resident(profile, (com.example.project3.Major) null, 0);
                if(studentArray.contains(student)){
                    if(studentArray.returnStudent(student) instanceof Resident){
                        if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                            EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                            enrolledStudents.add(Nstudent);
                            textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                        }else{
                            textArea.appendText("(Resident) " + array[3] + " : invalid credit hours" + "\n");}
                    }else{
                        if(studentArray.returnStudent(student) instanceof International){
                            if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                enrolledStudents.add(Nstudent);
                                textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                            }else{
                                textArea.appendText("(International ) " + array[3] + " : invalid credit hours" + "\n");}
                        }else{
                            if(studentArray.returnStudent(student) instanceof TriState){
                                if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                    EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                    enrolledStudents.add(Nstudent);
                                    textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                                }else{
                                    textArea.appendText("(TriState) " + array[3] + " : invalid credit hours" + "\n");}
                            }else{
                                if(studentArray.returnStudent(student) instanceof NonResident){
                                    if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                        EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                        enrolledStudents.add(Nstudent);
                                        textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                                    }else{
                                        textArea.appendText("(Non-Resident) " + array[3] + " : invalid credit hours" + "\n");
                                    }}}}}
                }else{
                    textArea.appendText("student is not in roster" + "\n");}}}}

    /**
     * This method enrolls a given student
     * @param event this method is invoked every time the enroll button is clicked
     * */
    @FXML
    void enroll(ActionEvent event){
        String[] info = new String[4];
        info[0] = efname.getText();
        info[1] = elname.getText();
        info[3] = creditsEnrolled.getText();
        if(errorsInRosterForEnroll(info)){
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            enrollStudent(info);
        }
    }

    /**
     * This is a helper method for the original drop method, it checks if the student is enrolled or not before dropping
     * @param array the array contains the information of the student
     * */
    private void dropStudent(String[] array){
        if(errorsInRosterForDrop(array)){
        Profile profile = new Profile(array[1], array[0], array[2]);
        EnrollStudent student = new EnrollStudent(profile, 0);
        if(enrolledStudents.contains(student)){
            enrolledStudents.remove(student);
            textArea.appendText( profile.toString() + " dropped" + "\n");
        }else{
            textArea.appendText(profile.toString()+ " is not enrolled" + "\n");
        }}
    }

    /**
     * This method drops a student that is currently enrolled
     * @param event this method is invoked every time the drop button is clicked
     * */
    @FXML
    void drop(ActionEvent event){
        String[] info = new String[3];
        info[0] = efname.getText();
        info[1] = elname.getText();
        if(errorsInRosterForDrop(info)){
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            dropStudent(info);
        }
    }

    /**
     * This method checks whether the scholarship being awarded is an Integer or not and is the Integer between 0 and 10000
     * @param s the string s is the scholarship which will be converted to integer
     * @return returns true if the given scholarship is an Integer and exists between 0 and 10000, false otherwise.
     * */
    private boolean checkScholarship(String s){
        try{
            if(Integer.parseInt(s)>=0 && Integer.parseInt(s)<=10000){
                return true;
            }
            if(Integer.parseInt(s)>10000 || Integer.parseInt(s)<0){
                textArea.appendText(s + ": invalid amount" + "\n");
                return false;
            }
        }
        catch (Exception e){
            textArea.appendText("Amount is not an integer" + "\n");
            return false;
        }
        return false;
    }

    /**
     * This method checks if there are eny empty text fields or unexpected errors in the text fields for the updateScholarship button
     * @param info the array that contains the input information from the text fields
     * @return returns false if there are any empty text fields, otherwise true
     * */
    private boolean errorsInRosterForScholarship(String[] info){
        try{
            sDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("amount text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and amount text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and amount text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and amount text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" amount text field is empty" + "\n");return false;}
        return true;
    }


    /**
     * This method is a helper method to the original updateScholarship method and awards scholarships to students
     * @param array the array contains the information of the student and the scholarship amount
     * */
    private void awardScholarship(String[] array) {
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            if (checkScholarship(amount.getText())) {
                if (studentArray.contains(student)) {
                    if (studentArray.returnStudent(student) instanceof Resident) {
                        EnrollStudent Nstudent = (new EnrollStudent(profile, 0));
                        if (enrolledStudents.contains(Nstudent)) {
                            ((Resident) studentArray.returnStudent(student)).setScholarship(Integer.parseInt(amount.getText()));
                            textArea.appendText(profile.toString() + ": scholarship amount updated" + "\n");
                        } else {
                            textArea.appendText("student is not enrolled" + "\n");
                        }
                    } else {
                        if (studentArray.returnStudent(student) instanceof International) {
                            textArea.appendText(profile.toString() + " (International) is not eligible for scholarship " + "\n");
                        } else {
                            if (studentArray.returnStudent(student) instanceof TriState) {
                                textArea.appendText(profile.toString() + " (TriState) is not eligible for scholarship " + "\n");
                            } else {
                                if (studentArray.returnStudent(student) instanceof NonResident) {
                                    textArea.appendText(profile.toString() + " (Non-Resident) is not eligible for scholarship " + "\n");
                                }
                            }
                        }
                    }
                } else {
                    textArea.appendText(profile.toString() + " is not in the roster" + "\n");
                }
            }
    }

    /**
     * This method awards scholarship to a student only if the student is a resident student
     * */
    @FXML
    void updateScholarship(ActionEvent event){
        String[] info = new String[4];
        info[0] = sfname.getText();
        info[1] = slname.getText();
        info[3] = amount.getText();
        if(errorsInRosterForScholarship(info)){
            info[2] = dateConverter(sDOB.getValue().toString());
            awardScholarship(info);
        }
    }

    /**
     * This method is a helper method for the original print method and prints the students sorted by their fname, lname, and DOB
     */
    private void helperForPrint() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* Student roster sorted by first name, last name, DOB **" + "\n");
            for (int i = 0; i < studentArray.getSize(); i++) {
                textArea.appendText(studentArray.print(i).toString() + "\n");
            }
            textArea.appendText("* end of roster ** " + "\n");
        } else {
            textArea.appendText("Student roster is empty!" + "\n");
        }
    }

    /**
     * This method prints the roster in an alphabetic order of first name, last name, DOB
     * @param event this method is invoked every time the */
    @FXML
    void print(ActionEvent event){
        textArea.clear();
        helperForPrint();
    }

    /**
     * This method is a helper method for the original printByStanding method and prints the students according to their standing
     * the array is sorted in the form of a student's standing
     * */
    private void helperForPrintByStanding() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* Student roster sorted by Standing **" + "\n");
            Student[] a = studentArray.printByStanding();
            for(int i = 0;  i< a.length; i++){
                textArea.appendText(a[i].toString() + "\n");
            }
            textArea.appendText("*end of roster**" + "\n");

        } else {
            textArea.appendText("Student roster is empty" + "\n");
        }
    }

    /**
     * This method prints students sorted by their standing
     * @param event this method is invoked every time the menu button printByStanding is pressed
     * */
    @FXML
    void printByStanding(ActionEvent event){
        textArea.clear();
        helperForPrintByStanding();
    }

    /**
     * This is a helper method to the original printBySchoolMajor method and prints the students sorted by their school, major*/
    private void helpForPrintBySchoolMajor() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* student roster sorted by school, major" + "\n");
            for(int i = 0; i < studentArray.getSize(); i++){
                textArea.appendText(studentArray.printBySchoolMajor(i).toString() + "\n");
            }
            textArea.appendText("*end of roster**" + "\n");
        } else {
            textArea.appendText("Student roster is empty!" + "\n");
        }
    }

    /**
     * This method prints student roster sorted by school, major
     * @param event this method is invoked every time the printBySchoolMajor menu item is clicked
     * */
    @FXML
    void printBySchoolMajor(ActionEvent event){
        textArea.clear();
        helpForPrintBySchoolMajor();
    }



    /**
     * This is a helper method for the tuition due method, it checks whether there are any students enrolled or not befor eprinting
     * */
    private void displayTuition(){
        if(enrolledStudents.getSize()!=0){
            DecimalFormat df = new DecimalFormat("0.00");
            textArea.appendText("** Tuition due*" + "\n");
                for(int i = 0; i < enrolledStudents.getSize(); i++){
                    Profile profile = enrolledStudents.returnProfile(i);
                    Student student = new Resident(profile, (com.example.project3.Major) null, 0);
                    if (studentArray.returnStudent(student) instanceof Resident) {
                        textArea.appendText(student.getProfile() + " (Resident) " + "enrolled " + enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: "+df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i)))  + "\n");
                    } else if (studentArray.returnStudent(student) instanceof International) {
                        textArea.appendText(student.getProfile() + " (International) enrolled " + enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: " +df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    } else if (studentArray.returnStudent(student) instanceof TriState) {
                        textArea.appendText(student.getProfile() + " (TriState " + ((TriState) studentArray.returnStudent(student)).getState()
                                + ") enrolled "+ enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: "+df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    } else if (studentArray.returnStudent(student) instanceof NonResident) {
                        textArea.appendText(student.getProfile() + " (Non-Resident) enrolled "+ enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: " + df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    }
                }
            textArea.appendText("* end of tuition due *" + "\n");
        }else{
            textArea.appendText("Student roster is empty!" + "\n");
        }}

    /**
     * This method prints all the students enrolled along with their tuition that is due
     * @param event this method is invoked every time the tuitionDue button is clicked
     * */
    @FXML
    void tuitionDue(ActionEvent event){
        textArea.clear();
        displayTuition();
    }

    /**
     * This method is a helper method for the printEnroll method
     * */
    private void printEnroll(){
        if(enrolledStudents.getSize()==0){
            textArea.appendText("Enrollment is empty!" + "\n");
        }else{
            textArea.appendText("** Enrollment * " + "\n");

            for(int i = 0; i < enrolledStudents.getSize(); i++){
                textArea.appendText(enrolledStudents.print(i).toString() + "\n");
            }

            textArea.appendText("* end of Enrollment **" + "\n");
        }
    }

    /**
     * This method prints the enrolled student list
     * @param event this method is invoked every time the enrollList menu item is clicked
     * */
    @FXML
    void enrollmentList(ActionEvent event){
        textArea.clear();
        printEnroll();
    }

    /**
     * This method updates the credits of students at Semester end
     * It adds the enrolled credits onto the completed credits
     * @return it returns an integer which describes how many students are eligible for graduation
     * */
    private int updateCredits(){
        int count = 0;
        for(int i = 0; i < enrolledStudents.getSize(); i++) {
            Profile profile = enrolledStudents.returnProfile(i);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            int a = enrolledStudents.getCreditsEnrolled(i) + studentArray.returnStudent(student).getCreditCompleted();
            studentArray.returnStudent(student).setCreditCompleted(a);
            if(a>=120){
                count++;
            }
        }
        return count;
    }

    /**
     * This is a helper method for the original semesterEnd method
     * */
    private void helpForSemesterEnd() {
        textArea.appendText("Credit completed has been updated." + "\n");
        textArea.appendText("** list of students eligible for graduation **" + "\n");
        for(int i = 0; i < enrolledStudents.getSize(); i++){
            Profile profile = enrolledStudents.returnProfile(i);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            /*int a = enrolledStudents.getCreditsEnrolled(i) + studentArray.returnStudent(student).getCreditCompleted();
            studentArray.returnStudent(student).setCreditCompleted(a);*/
            if(studentArray.returnStudent(student).getCreditCompleted()>=120){
                if (studentArray.returnStudent(student) instanceof Resident) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Resident)" + "\n" );
                } else if (studentArray.returnStudent(student) instanceof International) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (International) (Non-Resident)" + "\n" );
                } else if (studentArray.returnStudent(student) instanceof TriState) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Non-Resident) (TriState " + ((TriState) studentArray.returnStudent(student)).getState() + ")"  + "\n");
                } else if (studentArray.returnStudent(student) instanceof NonResident) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Non-Resident)" + "\n");
                }
            }
        }textArea.appendText("** end of list *" + "\n");
    }


    /**
     * This method prints the list of students that are eligible for graduation with their credits completed >=120
     * @param event this method is invoked only once when the SemesterEnd menu button is clicked
     * */
    @FXML
    void SemesterEnd(ActionEvent event){
        textArea.clear();
        int a = updateCredits();
        if(a!=0){
            helpForSemesterEnd();
            semesterend.setDisable(true);
        }else{
            textArea.appendText("no students eligible for graduation!" + "\n");
        }

    }

    /**
     * This method prints all the students enrolled in RBS
     * @param event this method is invoked every time the menu item RBS is clicked
     * */
    @FXML
    void printRBS(ActionEvent event){
        textArea.clear();
        String s = "RBS";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in RBS" + "\n");
        }else{
        textArea.appendText("* students in " + s + "**" + "\n");
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
            }
        }
        textArea.appendText("*end of roster**" + "\n");}
    }

    /**
     * This method prints all the students enrolled in SAS
     * @param event this method is invoked every time the menu item SAS is clicked
     * */
    @FXML
    void printSAS(ActionEvent event){
        textArea.clear();
        String s = "SAS";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SAS" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }

    /**
     * This method prints all the students enrolled in SOE
     * @param event this method is invoked every time the menu item SOE is clicked
     * */
    @FXML
    void printSOE(ActionEvent event){
        textArea.clear();
        String s = "SOE";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SOE" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }

    /**
     * This method prints all the students enrolled in SC&I
     * @param event this method is invoked every time the menu item SC&I is clicked
     * */
    @FXML
    void printSCI(ActionEvent event){
        textArea.clear();
        String s = "SC&I";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SC&I" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }

    /**
     * This method adds a resident student loaded from the text file
     * @param tokens the array which contains all the information of the student
     * */
    private void addResidentStudentFromRoster(String[] tokens) {
        if (tokens.length == 6) {
            Date date = new Date(tokens[3]);
            if (NewIsValid(date)) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            Student student = new Resident(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                            if (!studentArray.contains(student)) {
                                studentArray.add(student);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method adds an International student loaded from the text file
     * @param tokens the array which contains all the information of the student
     * */
    private void addInternationalStudentFromRoster(String[] tokens){
        if(tokens.length>=6) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            if(tokens.length==7){
                                Student student = new International(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]));
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }else {
                                Student student = new International(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method adds a Non-Resident student loaded from the text file
     * @param tokens the array which contains all the information of the student
     * */
    private void addNonResidentStudentsFromRoster(String[] tokens){
        if(tokens.length==6) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            Student student = new NonResident(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                            if (!studentArray.contains(student)) {
                                studentArray.add(student);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method adds a TriState student loaded from the text file
     * @param tokens the array which contains all the information of the student
     * */
    private void addTriStateStudentFromRoster(String[] tokens){
        if(tokens.length==7) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if(tokens[6].equalsIgnoreCase("NY") || tokens[6].equalsIgnoreCase("CT")) {
                            if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                                Student student = new TriState(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]), tokens[6]);
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method reads from a file and performs the functions designated to each command
     * @param event this method is invoked every time the button loadFromFile is clicked
     * */
    @FXML
    void loadFromFile(ActionEvent event){
        String a = loadFile.getText();
        if(a.compareTo("") ==0){
            textArea.appendText("no file name entered!" + "\n");
        }else{
        try{
            File file = new File(a);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String status = tokens[0];
                switch (status) {
                    case "R":
                        addResidentStudentFromRoster(tokens);
                        break;
                    case "N":
                        addNonResidentStudentsFromRoster(tokens);
                        break;
                    case "T":
                        addTriStateStudentFromRoster(tokens);
                        break;
                    case "I":
                        addInternationalStudentFromRoster(tokens);
                        break;
                    default:
                        textArea.appendText("Invalid student status in file." + "\n");
                        break;
                }
            }
            scanner.close();
            textArea.appendText("Student loaded to the roster." + "\n");
        }catch(FileNotFoundException e){
            textArea.appendText( "File not Found: " + a + "\n");
        }
    }
    loadFile.setText("");}

}
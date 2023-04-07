package com.example.project3;
/**
 * This class extends NonResident class and forms objects of students that are from the tristate of NY and CT
 * @author apurva desai, Yehum kim
 * */
public class TriState extends NonResident{

    private String state;

    int TUITION_DISCOUNT_NY = 4000;
    int TUITION_DISCOUNT_CT = 5000;
    private static final int FULL_TIME_CREDITS = 12;

    /**
     * This is a constructor which instantiates a TriState student
     * @param profile the profile of the tristate student
     * @param major  the major of the tristate student
     * @param creditsCompleted credits completed by the tristate student
     * @param state the state of the tristate student
     * */
    public TriState(Profile profile, Major major, int creditsCompleted, String state){
        super(profile,major, creditsCompleted);
        this.state = state;
    }

    /**
     * This is a constructor which instantiates a TriState student for the controller to print the student
     * @param profile the profile of the tristate student
     * @param major  the major of the tristate student
     * @param creditsCompleted credits completed by the tristate student
     * @param state the state of the tristate student
     */
    public TriState(Profile profile, String major, int creditsCompleted, String state){
        super(profile,major, creditsCompleted);
        this.state = state;
    }

    /**
     * This method returns a string of the state the student is from
     * @return returns a string of the state
     * */
    public String getState() {
        return state;
    }

    /**
     * This method returns a string of the student details
     * @return returns a string with all the information of a student such as fname, lname, DOB, major, and the state of residence*/
    @Override
    public String toString(){
        return super.toString() + "(Tri-State : " + getState().toUpperCase().toString() + " )";
    }

    /**
     * This method returns a double which is the tuition due for the student depending on the number of credits the student is enrolled for
     * This method using for the displayTuition in the controller to print the student's tuition
     * @param creditsEnrolled the credits enrolled by the student
     * @return returns the tuition due for the student
     * */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled<FULL_TIME_CREDITS){
            if(state.equalsIgnoreCase("NY")){
                return super.tuitionDue(creditsEnrolled);
            }
            if(state.equalsIgnoreCase("CT")){
                return super.tuitionDue(creditsEnrolled);
            }
        }else {
            if (state.equalsIgnoreCase("NY")) {
                return super.tuitionDue(creditsEnrolled) - TUITION_DISCOUNT_NY;
            }
            if (state.equalsIgnoreCase("CT")) {
                return super.tuitionDue(creditsEnrolled) - TUITION_DISCOUNT_CT;
            }
        }
        return 0;
    }
}
package com.example.project3;

/**
 * This class extends the Student class and makes an object of a nonresident student to store in the roster and then enroll the student
 * @author apurva desai, yehun kim
 * */
public class NonResident extends Student{
    private static final int PER_CREDIT_FEE = 966;
    private static final int TUITION = 29737;
    private static final int UNIVERSITY_FEE = 3268;
    private static final double PART_TIME_UNIVERSITY_FEE = (UNIVERSITY_FEE*0.8);
    private static final int FULL_TIME_STUDENT_CREDITS = 12;
    private static final int REGULAR_TUITION_CREDITS = 16;

    /**
     * This is a constructor which instantiates a NonResident student for the controller to print the student
     * @param profile the profile of the student of the nonresident student
     * @param major the major of the student of the nonresident student
     * @param creditsCompleted the credits completed by the nonresident student
     * */
    public NonResident(Profile profile, Major major, int creditsCompleted){
        super(profile, major, creditsCompleted);
    }

    /**
     * This is a constructor to instantiate a NonResident object
     * @param profile the profile of the student of the nonresident student
     * @param major the major of the student of the nonresident student
     * @param creditsCompleted the credits completed by the nonresident student
     * */
    public NonResident(Profile profile, String major, int creditsCompleted){
        super(profile, major, creditsCompleted);
    }

    /**
     * This method returns a double which is the tuition due for the student depending on the number of credits the student is enrolled for
     * calculating the tuition with the status of the student's creditEnrolled for the NonResident Student.
     * This method will be using for the displayTuition in the controller
     * @param creditsEnrolled the credits enrolled by the student
     * @return returns the tuition due for the student
     * */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuition =0;
        if(isValid(creditsEnrolled)){
            if(creditsEnrolled>=FULL_TIME_STUDENT_CREDITS &&creditsEnrolled<=REGULAR_TUITION_CREDITS){
                tuition = TUITION + UNIVERSITY_FEE;
            }
            if(creditsEnrolled<FULL_TIME_STUDENT_CREDITS){
                tuition = ((creditsEnrolled*PER_CREDIT_FEE) + PART_TIME_UNIVERSITY_FEE);
            }
            if(creditsEnrolled>REGULAR_TUITION_CREDITS){
                int extraCredits = creditsEnrolled-REGULAR_TUITION_CREDITS;
                tuition = (TUITION+ UNIVERSITY_FEE+ (PER_CREDIT_FEE*extraCredits));
            }
            return tuition;
        }else{
            return 0;
        }
    }

    /**
     * This method converts the Non-Resident student object to a string
     * @return returns a string of the non-resident student object + (Non-Resident)
     */
    public String toString(){
        return super.toString() + "(Non-Resident) ";
    }

    /**
     * This method returns a boolean, true if the student is a resident, false otherwise
     * @return returns false for Non-Resident
     * */
    @Override
    public boolean isResident() {
        return false;
    }
}
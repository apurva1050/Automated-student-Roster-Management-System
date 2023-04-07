package com.example.project3;
/**
 * This class makes an object of type enroll student
 * @author Yehun Kim, Apurva Desai
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * representation of the EnrollStudent's profile and creditEnrolled
     * @param profile lname, fname, dob
     * @param creditsEnrolled credit of enrollStudent
     */
    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled=creditsEnrolled;
    }
    public EnrollStudent(Profile profile){
        this.profile=profile;
    }

    /**
     * getting method to get a student profile
     * @return profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * getting method to get a student credit
     * @return returns the credits enrolled
     */
    public int getCreditsEnrolled() {
        return creditsEnrolled;
    }

    /**
     * setting method to set a student credit
     * @param creditsEnrolled the credits to be updated
     */


    /**
     * Override method to compare the enrollments based on their profile.
     * @param obj the object to be compared
     * @return the current profile of enrollStudent if it equals to the obj
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return this.profile.equals(enrollStudent.profile);
        }else{
            return false;
        }
    }

    /**
     * Override method to print the enrollStudent profile.
     * @return the string representation of the enrollStudent's profile
     */
    @Override
    public String toString() {
        return profile.toString() + ", Credits Enrolled: " + creditsEnrolled;
    }
}
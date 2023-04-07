package com.example.project3;
/**
 * A class that represents a student's profile information, including their
 * last name, first name, and date of birth.
 * @author yehun kim
 * @author Apurva Desai
 */
public class Profile implements Comparable<Profile>{
    private String lname;
    private String fname;
    private Date dob;


    /**
     * Constructs a new profile with the specified last name, first name, and
     * date of birth.
     *
     * @param lname The last name of the student.
     * @param fname The first name of the student.
     * @param dob The date of birth of the student.
     */
    public Profile(String lname, String fname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }


    /**
     * Constructs a new profile with the specified last name, first name, and
     * date of birth as a string.
     *
     * @param lastName The last name of the student.
     * @param firstName The first name of the student.
     * @param date The date of birth of the student as a string in the format "MM/DD/YYYY".
     */
    public Profile(String lastName, String firstName, String date){
        this.lname = lastName;
        this.fname = firstName;
        this.dob = new Date(date);
    }


    /**
     * Compares this profile to another profile for the order.
     * Compares: lastName, firstName, and DOB
     * Returns a negative integer if less than profile being compared to.
     * Returns a 0 if equal to profile being compared to.
     * Returns a positive integer if greater to profile being compared to.
     *
     * @param p The profile to compare to.
     * @return A negative integer, zero, or a positive integer as this profile is
     *         less than, equal to, or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile p) {
        if((lname.compareToIgnoreCase(p.lname)==0) && (fname.compareToIgnoreCase(p.fname))==0 && (dob.compareTo(p.dob))==0){
            return 0;}


        if((lname.compareToIgnoreCase(p.lname)==0) && (fname.compareToIgnoreCase(p.fname))==0 && (dob.compareTo(p.dob))>0){
            return 1;
        }else {
            if ((lname.compareToIgnoreCase(p.lname) == 0) && (fname.compareToIgnoreCase(p.fname) == 0) && (dob.compareTo(p.dob) < 0)) {
                return -1;}}
        if((lname.compareToIgnoreCase(p.lname)==0) && (fname.compareToIgnoreCase(p.fname)>0)){
            return 1;
        }else {
            if (lname.compareToIgnoreCase(p.lname) == 0 && fname.compareToIgnoreCase(p.fname) < 0) {
                return -1;}}
        if(lname.compareToIgnoreCase(p.lname)>0){
            return 1;
        }else{
            return -1;}}

    /**
     * Returns a string representation of this profile.
     *
     * @return A string representation of this profile.
     */
    @Override
    public String toString(){
        return fname+ " " + lname+ " "+ dob;
    }


    /**
     * Compares this profile to the specified object for equality. Returns true
     * if and only if the specified object is also a profile and its last name,
     * first name, and date of birth are equal to this profile's.
     *
     * @param o The object will be explicitly converted to p which gives us the profile
     * @return true if the specified object is equal to this profile, false it not.
     */
    @Override
    public boolean equals(Object o){
        Profile p = (Profile) o;
        if(lname.equalsIgnoreCase(p.lname)&& fname.equalsIgnoreCase(p.fname)&&dob.equals(p.dob)){
            return true;
        }
        else{
            return false;
        }
    }

}



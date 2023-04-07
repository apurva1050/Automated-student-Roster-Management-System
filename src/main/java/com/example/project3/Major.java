package com.example.project3;

/**
 * An enumeration representing the different Majors in the university
 *
 * @author Yehun Kim
 * @author Apurva Desai
 */
public enum Major {

    CS ("01:198", "SAS"),

    MATH ("01:640", "SAS"),

    EE ("14:332", "SOE"),

    ITI ("04:547", "SC&I"),

    BAIT ("33:136", "RBS");

    private final String code;
    private final String school;

    /**
     * Constructs a Major with the given code and school
     *
     * @param code the code of the major
     * @param school the school of the major
     */
    Major(String code, String school){
        this.code = code;
        this.school = school;
    }




    /**
     * Returns the school of the major
     * @return the school of the major
     */
    public String getSchool(){
        return school;
    }

    /**
     * Returns the code and school of the major in the format "code school"
     * @return the code and school of the major
     */
    public String getEnum(){return this.code + " " + this.school;}

    /**
     * Returns whether the given major string is a valid major
     * @param major1 the major to check
     * @return true if the major is valid, false otherwise
     */
    public static boolean isMajorValid(String major1){
        try{
        Major checkMajor = Major.valueOf(major1);
        return true;}
        catch(IllegalArgumentException e){
            return false;}

    }
}
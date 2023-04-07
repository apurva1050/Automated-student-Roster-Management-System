package com.example.project3;
/**
This class creates an object array which holds information for all the students
this array based linear structure starts with an intitial capacity of 4 and grows by the capacity of 4 when full
 @author Apurva Desai, Yehun Kim
 * */
public class Roster {
    private Student[] roster;
    private int size;
    private static final int NOT_FOUND = -1;

    private static final int INITIAL_SIZE_ROSTER = 4;
    private static final int FRESHMANUPPEREND = 29;
    private static final int SOPHOMORELOWEREND = 30;
    private static final int SOPHPMPREUPPEREND = 59;
    private static final int JUNIORLOWEREND = 60;
    private static final int JUNIORUPPEREND = 89;
    private static final int SENIORLOWEREND = 90;

    /**
     This is a constructor which initializes thee arrry and sets the size to 0.
     the size variable describes how many students there are in the array*/
    public Roster(){
        this.roster = new Student[INITIAL_SIZE_ROSTER];
        this.size = 0;
    }

    /**
     This is a getter method which returns the number of students in the list
     @return returns the integer number of students in array
     */
    public int getSize(){
        return size;
    }


    /**
     This is a method which searches the given student in the roster
     @param student the student to be searched
     @return returns the integer 1 is student found else it returns -1
     */
    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(student.equals(roster[i])){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     This is a method which takes in a student parameter and then returns the original instance of Student abstract class
     @param student the student whose original data is to be returned
     @return returns an instance of its subclasses */
    public Student returnStudent(Student student){
        return roster[find(student)];
    }

    /**
     This method increases the array capacity by 4
     this method makes another array double the size of the initial array
     */
    private void grow(){
        Student[] doubleRoster = new Student[roster.length + INITIAL_SIZE_ROSTER];
        for(int i = 0; i < roster.length; i++){
            doubleRoster[i] = roster[i];
        }
        roster = doubleRoster;
    }


    /**
     This method is  used in sorting and compares two  students
     @return returns true if student1 when compared to student2 is negative
     @param student1 the student parameter passed to compare
     @param student2 the student parameter that student1 is being compared with
      * */
    private static boolean less(Student student1, Student student2){
        return student1.compareTo(student2)<0;
    }


    /**
     This method is used in sorting and is used to compare two students based on their schools and majors
     @return returns true if the student1's school is alphabetically after student2's school
     @param student1 the student parameter passed to compare
     @param student2 the student paramter that student1 is being compared with
     */
    private static boolean less2(Student student1, Student student2){
        if(student1.compareBySchool(student2) ==0){
            return student1.compareByMajor(student2)<0;
        }else{
            return student1.compareBySchool(student2)<0;
        }

    }

    /**
     This is a method which exchanges two students in different indexes in the roster array which includes students
     @param i index of the first student
     @param j index of the second student
     */
    private static void exchange(Student[] roster, int i, int j){
        Student swap = roster[i];
        roster[i] = roster[j];
        roster[j] = swap;
    }

    /**
     This is a sorting method used to sort the roster which contains all the students alphabetically in order of lname,
     fname, and DOB
     @param roster the array which is to be sorted
     @param size integer which tells us how many students are present in the roster
     */
    private static void sort(Student[] roster, int size) {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less(roster[j], roster[min])) {
                    min = j;
                    exchange(roster, i, min);
                }
            }
        }
    }

    /**
     This is a sorting method used to sort the roster which contains all the students alphabetically in order of their majors
     @param roster the array which is to be sorted
     @param size integer which tells us how many students are present in the roster
     */
    private static void sortByMajor(Student[] roster, int size){
        for(int i = 0; i < size; i++){
            int min = i;
            for(int j = i+1; j < size; j++){
                if(less2(roster[j], roster[min])){
                    min = j;
                    exchange(roster, i, min);
                }
            }
        }
    }

    /**
     This is a method which adds a student to the roster.
     the roster is also sorted after adding the student
     @param student the student to be added
     */
    public boolean add(Student student){
        for(int i =0; i < size; i++){
            if(student.equals(roster[i])){
                return false;
            }
        }
        roster[size] = student;
        size++;
        if(size>1){
            sort(roster, size);
        }
        if(roster.length==size){
            grow();
        }
        return true;

    }


    /**
     This is a method which removes a student from the roster.
     @param student the student to be removed
     */
    public boolean remove(Student student){
        int k =0;
        for(int i = 0; i < size; i++){
            if(student.equals(roster[i])){
                k++;
            }
        }
        if(k==0){
            return false;
        }
        int tempVariable=0;
        for(int i = 0; i < size; i++){
            if(roster[i].equals(student)){
                tempVariable = i;
            }
        }
        roster[tempVariable] = null;
        for(int i = tempVariable; i < size; i++){
            roster[i] = roster[i+1];
        }
        size--;
        return true;
    }

    /**
     This is a method which checks whether the student is inn the array or not
     @param student the student to be checked
     @return returns true if student is in the array anf false otherwise
     */
    public boolean contains(Student student){
        for(int i = 0; i < size; i++){
            if(student.equals(roster[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * This method changes the major of a given student
     * @param student the student whose major is to be changed
     * @return returns a boolean, true if student is in roster and major changed, else otherwise*/
    public boolean changedMajor(Student student){
        if (find(student) >= 0){
            roster[find(student)].setMajor(student.getMajor());
            return true;
        }else{
            return false;
        }
    }


    /**
     This method returns a student which will be printed in the controller class in method printBySchool
     @param i the index of the student in the roster
     @return returns a student at index i in the roster
     */
    public Student printBySchool(int i ){
            return roster[i];
    }

    /**
     This method returns a student which will be printed in the controller class in method print
     @param i the index of the student in the roster
     @return returns a student at index i in the roster
     */
    public Student print(int i){
        sort(roster, size);
        return roster[i];
    }


    /**
     This method returns a student which will be printed in the controller class
     @param i the index of the student in the roster
     @return returns a student at index i in the roster
     */
    public Student printBySchoolMajor(int i){
        sortByMajor(roster, size);
        return roster[i];
        }


    /**
     This is a helper method for print by standing and returns the number of freshman, sophomores, juniors, and seniors
     the roster is also sorted after adding the student
     @param sizeOfStanding this is an array which contains the number of freshman, sophomore, juniors, and seniors
     as its elements array[0], array[1], array[2], array[30] respectively
     */
    private void helpForStanding(Integer[] sizeOfStanding){
        int sizeForFreshman=0;
        int sizeForSophomore=0;
        int sizeForJunior=0;
        int sizeForSenior=0;

        for(int i = 0; i < size; i++){
            if(roster[i].getCreditCompleted()<=FRESHMANUPPEREND){
                sizeForFreshman++;}
            if(roster[i].getCreditCompleted()>=SOPHOMORELOWEREND && roster[i].getCreditCompleted()<=SOPHPMPREUPPEREND){
                sizeForSophomore++;}

            if(roster[i].getCreditCompleted()>=JUNIORLOWEREND && roster[i].getCreditCompleted()<=JUNIORUPPEREND){
                sizeForJunior++;}

            if(roster[i].getCreditCompleted()>=SENIORLOWEREND){
                sizeForSenior++;}
        }
        sizeOfStanding[0]=sizeForFreshman;
        sizeOfStanding[1]=sizeForSophomore;
        sizeOfStanding[2]=sizeForJunior;
        sizeOfStanding[3]=sizeForSenior;
    }


    /**
     * This method returns a student array which is already sorted in terms of standing and is used in controller class to be printed
     * @return returns the student array to be printed
     * */
    public Student[] printByStanding(){
        Integer[] temp = new Integer[INITIAL_SIZE_ROSTER];
        helpForStanding(temp);
        Student[] freshman = new Student[temp[0]];
        Student[] sophomore = new Student[temp[1]];
        Student[] junior = new Student[temp[2]];
        Student[] senior = new Student[temp[3]];
        int counter = 0;
        for(int i = 0;  i< size;i++){
            if(roster[i].getCreditCompleted()<=FRESHMANUPPEREND){
                freshman[counter]=roster[i];
                counter++;}}
        int counter2 = 0;
        for(int i = 0;  i< size;i++){
            if(roster[i].getCreditCompleted()>=SOPHOMORELOWEREND && roster[i].getCreditCompleted()<=SOPHPMPREUPPEREND){
                sophomore[counter2]=roster[i];
                counter2++;}}
        int counter3 = 0;
        for(int i = 0;  i< size;i++){
            if(roster[i].getCreditCompleted()>=JUNIORLOWEREND && roster[i].getCreditCompleted()<=JUNIORUPPEREND){
                junior[counter3]=roster[i];
                counter3++;}}
        int counter4 = 0;
        for(int i = 0;  i< size;i++){
            if(roster[i].getCreditCompleted()>=SENIORLOWEREND){
                senior[counter4]=roster[i];
                counter4++;}}

        Student[] allSorted = new Student[freshman.length+ junior.length+ senior.length+ sophomore.length];

        int num=0;
        for(int i = 0; i < freshman.length;i++){
           allSorted[i] = freshman[i];
           num++;
        }

        for(int i = 0; i < junior.length;i++){
            allSorted[num] = junior[i];
            num++;
        }

        for(int i = 0; i < senior.length;i++){
            allSorted[num] = senior[i];
            num++;
        }

        for(int i = 0; i < sophomore.length;i++){
            allSorted[num] = sophomore[i];
            num++;
        }
        return allSorted;
    }
}
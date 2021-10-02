import java.util.HashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private Map<String, Double> assignmentMap = new HashMap<>();

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAssignmentMap(String assignmentName, Double assignmentGrade) {
        assignmentMap.put(assignmentName, assignmentGrade);
    }

    public Map<String, Double> getAssignmentMap() {
        return assignmentMap;
    }

    public void assignGrade(String assignmentName,double grade){
        setAssignmentMap(assignmentName, grade);
    }

    public double calculateAverageGrade(){
        double total = 0;
        int count = 0;
        for(double grade:assignmentMap.values()){
            total = total+ grade;
            count++;
        }
        double average = total/count;
        return Math.round(average*100.0)/100.0;
    }

    public String calculateLetterGrade(){
        String letterGrade;
        if (calculateAverageGrade() >= 90 && calculateAverageGrade() <=100){
            letterGrade = "A";
        }else if (calculateAverageGrade() >= 80 && calculateAverageGrade() <90){
            letterGrade = "B";
        }else if (calculateAverageGrade() >= 70 && calculateAverageGrade() <80) {
            letterGrade = "C";
        }else if (calculateAverageGrade() >= 60 && calculateAverageGrade() <70) {
            letterGrade = "D";
        }else{
            letterGrade = "F";
        }
        return letterGrade;
    }

}

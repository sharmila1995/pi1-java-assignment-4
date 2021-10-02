import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Assignment4 {
    private static Scanner input = new Scanner(System.in);

    public static void main (String... args) {
        greetings();
        List<Student> studentList = getStudentFromFile();
        getAssignmentGrades(studentList);
        createReport(studentList);
    }

    public static void greetings(){
        System.out.println("Welcome to the Student Report Card Generator\n" +
                "--------------------------------------------\n");
    }

    public static List<Student> getStudentFromFile(){
        Scanner myReader;
        boolean restart = true;
        List<Student> studentList = new ArrayList<>();
        do {
            try {
                System.out.print("Enter the student input file location: ");
                String fileLocation = input.nextLine();
                File readFile = new File(fileLocation);
                myReader = new Scanner(readFile);
                if (readFile.length()== 0)
                {
                    System.out.println("File is empty.");
                    continue;
                }
                System.out.println();

                while(myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    System.out.println("Creating Student " + line);
                    String[] studentArray = line.split(" ");
                    Student newStudent = new Student(studentArray[0], studentArray[1]);
                    studentList.add(newStudent);
                }
                restart = false;
            }catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            }
        }while(restart);
        return studentList;
    }

    public static void getAssignmentGrades(List<Student> studentList) {
        do {
            System.out.print("\nEnter the name of an Assignment: ");
            String assignmentName = input.nextLine();
            for (Student student : studentList) {
                    boolean incorrectGradeFormat = true;
                    do {
                        try{
                            System.out.print("Enter the grade for " + student.getFirstName() + " " + student.getLastName() + ": ");
                            double grade = Double.parseDouble(input.nextLine());
                            if (grade < 0 || grade >100){
                                System.out.println("Grade must be between 0 to 100. Please re-enter ");
                                continue;
                            }
                            student.assignGrade(assignmentName, grade);
                            incorrectGradeFormat = false;
                        }catch(NumberFormatException e)  {
                            System.out.println("Invalid input! ");
                        }
                    }while(incorrectGradeFormat);
            }
        }while(runAgain());
    }
    public static void createReport(List<Student> studentList){
        boolean restart = true;
        do{
            try {
                System.out.print("\nEnter output Directory: ");
                String outputFileLocation = input.nextLine();
                for (Student student : studentList) {
                    String filename = outputFileLocation + "/" + student.getFirstName() + "_" + student.getLastName() + ".txt";
                    File outputFile = new File(filename);
                    FileWriter writer = new FileWriter(outputFile);
                    writer.write(student.getFirstName() + " " + student.getLastName() + "\n");
                    writer.write("\nAverage: " + student.calculateAverageGrade() + "\n");
                    writer.write("Letter Grade: " + student.calculateLetterGrade() + "\n\n");
                    String assignmentInfo = student.getAssignmentMap().entrySet()
                            .stream().map(e -> e.getKey() + ": " + e.getValue() + "%").collect(Collectors.joining("\n"));
                    writer.write(assignmentInfo);
                    writer.close();
                }
                restart = false;
            }catch(FileNotFoundException e){
                System.out.println("Folder not found!");
            } catch(IOException e){
                e.printStackTrace();
            } finally{
                System.out.println("\nSuccessfully Generated Report Cards!");
            }
        }while(restart);

    }

    public static Boolean runAgain() {
        String choice;
        do {
            System.out.print("\nAnother Assignment? (y/n): ");
            choice = input.nextLine();
            if ((!choice.equals("y")) && (!choice.equals("n"))) {
                System.out.println("Error! Entry must be 'y' or 'n'. Try again. ");
            } else {
                break;
            }
        } while (true);
        return (choice.equals("y"));
    }

}
// /Users/sharmilashrestha/Desktop/MCC/zane-sharmila-anthony/Students.txt

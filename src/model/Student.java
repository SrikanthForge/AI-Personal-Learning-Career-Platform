package model;

public class Student {

    private String name;
    private String branch;
    private int year;
    private double cgpa;
    private String careerGoal;

    // Constructor
    public Student(String name, String branch, int year,
                   double cgpa, String careerGoal) {

        this.name = name;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
        this.careerGoal = careerGoal;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getYear() {
        return year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    // Display student profile
    public void displayProfile() {

        System.out.println();
        System.out.println("========== STUDENT PROFILE ==========");
        System.out.println("Name        : " + name);
        System.out.println("Branch      : " + branch);
        System.out.println("Year        : " + year);
        System.out.println("CGPA        : " + cgpa);
        System.out.println("Career Goal : " + careerGoal);
        System.out.println("=====================================");
    }
}
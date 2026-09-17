package model;

import java.util.ArrayList;

public class Career {

    private String careerName;
    private ArrayList<Skill> requiredSkills;

    // Constructor
    public Career(String careerName) {
        this.careerName = careerName;
        this.requiredSkills = new ArrayList<>();
    }

    // Add a required skill
    public void addRequiredSkill(Skill skill) {
        requiredSkills.add(skill);
    }

    // Get career name
    public String getCareerName() {
        return careerName;
    }

    // Get required skills
    public ArrayList<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    // Display career requirements
    public void displayCareerRequirements() {

        System.out.println();
        System.out.println("========== " + careerName + " ==========");
        System.out.println("Required Skills:");

        for (Skill skill : requiredSkills) {
            skill.displaySkill();
        }

        System.out.println("================================");
    }
}
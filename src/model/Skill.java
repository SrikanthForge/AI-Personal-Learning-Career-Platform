package model;

public class Skill {

    private String skillName;
    private int skillLevel;

    // Constructor
    public Skill(String skillName, int skillLevel) {

        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    // Getter for skill name
    public String getSkillName() {
        return skillName;
    }

    // Getter for skill level
    public int getSkillLevel() {
        return skillLevel;
    }

    // Update skill level
    public void updateSkillLevel(int newLevel) {

        if (newLevel >= 0 && newLevel <= 100) {
            this.skillLevel = newLevel;
        }
    }

    // Display skill
    public void displaySkill() {

        System.out.println(
                skillName + " : " + skillLevel + "/100"
        );
    }
}
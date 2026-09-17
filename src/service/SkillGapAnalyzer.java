package service;

import java.util.ArrayList;
import model.Skill;
import model.Career;

public class SkillGapAnalyzer {

    // Analyze the difference between current skills and required skills
    public void analyzeSkills(ArrayList<Skill> currentSkills, Career career) {

        System.out.println();
        System.out.println("========== SKILL-GAP ANALYSIS ==========");

        int totalRequired = career.getRequiredSkills().size();
        int skillsMet = 0;

        for (Skill requiredSkill : career.getRequiredSkills()) {

            String requiredName = requiredSkill.getSkillName();
            int requiredLevel = requiredSkill.getSkillLevel();

            int currentLevel = findCurrentSkillLevel(
                    currentSkills,
                    requiredName
            );

            int gap = requiredLevel - currentLevel;

            if (gap < 0) {
                gap = 0;
            }

            System.out.println(
                    requiredName
                    + " | Current: " + currentLevel
                    + " | Required: " + requiredLevel
                    + " | Gap: " + gap
            );

            if (currentLevel >= requiredLevel) {
                skillsMet++;
            }
        }

        double readiness = ((double) skillsMet / totalRequired) * 100;

        System.out.println("----------------------------------------");
        System.out.printf(
                "Career Readiness: %.2f%%%n",
                readiness
        );
        System.out.println(
                "Skills Meeting Requirement: "
                + skillsMet + "/" + totalRequired
        );
        System.out.println("========================================");
    }

    // Find the student's current level for a skill
    private int findCurrentSkillLevel(
            ArrayList<Skill> currentSkills,
            String skillName) {

        for (Skill skill : currentSkills) {

            if (skill.getSkillName().equalsIgnoreCase(skillName)) {
                return skill.getSkillLevel();
            }
        }

        return 0;
    }
}
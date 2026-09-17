package service;

import java.util.ArrayList;

import model.Skill;

public class SkillAnalytics {

    public void analyze(ArrayList<Skill> skills) {

        if (skills == null || skills.isEmpty()) {

            System.out.println();
            System.out.println("No skill data available.");
            return;
        }

        int totalSkills = skills.size();

        int totalLevel = 0;

        Skill highestSkill = skills.get(0);
        Skill lowestSkill = skills.get(0);

        int strongSkills = 0;
        int developingSkills = 0;
        int weakSkills = 0;

        for (Skill skill : skills) {

            int level = skill.getSkillLevel();

            totalLevel += level;

            if (level > highestSkill.getSkillLevel()) {
                highestSkill = skill;
            }

            if (level < lowestSkill.getSkillLevel()) {
                lowestSkill = skill;
            }

            if (level >= 70) {
                strongSkills++;
            }
            else if (level >= 40) {
                developingSkills++;
            }
            else {
                weakSkills++;
            }
        }

        double average =
                (double) totalLevel / totalSkills;

        System.out.println();
        System.out.println("========== SKILL ANALYTICS ==========");

        System.out.println(
                "Total Skills       : " + totalSkills
        );

        System.out.printf(
                "Average Skill Level: %.2f/100%n",
                average
        );

        System.out.println(
                "Highest Skill      : "
                + highestSkill.getSkillName()
                + " ("
                + highestSkill.getSkillLevel()
                + "/100)"
        );

        System.out.println(
                "Lowest Skill       : "
                + lowestSkill.getSkillName()
                + " ("
                + lowestSkill.getSkillLevel()
                + "/100)"
        );

        System.out.println();

        System.out.println(
                "Strong Skills      : "
                + strongSkills
        );

        System.out.println(
                "Developing Skills  : "
                + developingSkills
        );

        System.out.println(
                "Weak Skills        : "
                + weakSkills
        );

        System.out.println("====================================");
    }
}
package service;

import java.util.ArrayList;

import model.Skill;
import model.Career;

public class RoadmapGenerator {

    // Generate a personalized learning roadmap
    public void generateRoadmap(
            ArrayList<Skill> currentSkills,
            Career career) {

        System.out.println();
        System.out.println("========== PERSONALIZED LEARNING ROADMAP ==========");
        System.out.println("Target Career : " + career.getCareerName());
        System.out.println();

        ArrayList<RoadmapItem> roadmap =
                new ArrayList<>();

        // Analyze every required skill
        for (Skill requiredSkill : career.getRequiredSkills()) {

            int currentLevel = findCurrentSkillLevel(
                    currentSkills,
                    requiredSkill.getSkillName()
            );

            int requiredLevel =
                    requiredSkill.getSkillLevel();

            int gap = requiredLevel - currentLevel;

            if (gap > 0) {

                String priority;

                if (gap >= 20) {
                    priority = "HIGH";
                }
                else if (gap >= 10) {
                    priority = "MEDIUM";
                }
                else {
                    priority = "LOW";
                }

                roadmap.add(
                        new RoadmapItem(
                                requiredSkill.getSkillName(),
                                currentLevel,
                                requiredLevel,
                                gap,
                                priority
                        )
                );
            }
        }

        // Sort roadmap by largest skill gap first
        roadmap.sort(
                (item1, item2) ->
                        Integer.compare(
                                item2.gap,
                                item1.gap
                        )
        );

        // Display roadmap
        int step = 1;

        for (RoadmapItem item : roadmap) {

            System.out.println(
                    step + ". " + item.skillName
            );

            System.out.println(
                    "   Current Level : "
                    + item.currentLevel + "/100"
            );

            System.out.println(
                    "   Target Level  : "
                    + item.requiredLevel + "/100"
            );

            System.out.println(
                    "   Skill Gap     : "
                    + item.gap
            );

            System.out.println(
                    "   Priority      : "
                    + item.priority
            );

            System.out.println();

            step++;
        }

        // If no gaps exist
        if (roadmap.isEmpty()) {

            System.out.println(
                    "Congratulations!"
            );

            System.out.println(
                    "You currently meet all required skill levels."
            );
        }

        System.out.println(
                "=================================================="
        );
    }

    // Find student's current level for a skill
    private int findCurrentSkillLevel(
            ArrayList<Skill> currentSkills,
            String skillName) {

        for (Skill skill : currentSkills) {

            if (skill.getSkillName()
                    .equalsIgnoreCase(skillName)) {

                return skill.getSkillLevel();
            }
        }

        return 0;
    }

    // Inner class for roadmap items
    private static class RoadmapItem {

        private String skillName;
        private int currentLevel;
        private int requiredLevel;
        private int gap;
        private String priority;

        public RoadmapItem(
                String skillName,
                int currentLevel,
                int requiredLevel,
                int gap,
                String priority) {

            this.skillName = skillName;
            this.currentLevel = currentLevel;
            this.requiredLevel = requiredLevel;
            this.gap = gap;
            this.priority = priority;
        }
    }
}
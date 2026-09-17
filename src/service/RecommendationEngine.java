package service;

import java.util.ArrayList;
import java.util.Comparator;

import model.Skill;
import model.Career;

public class RecommendationEngine {

    // Inner class to store career and its match score
    private static class CareerMatch {

        private Career career;
        private double matchScore;

        public CareerMatch(Career career, double matchScore) {
            this.career = career;
            this.matchScore = matchScore;
        }
    }

    // Calculate career match score
    public double calculateMatchScore(
            ArrayList<Skill> currentSkills,
            Career career) {

        ArrayList<Skill> requiredSkills =
                career.getRequiredSkills();

        if (requiredSkills.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;

        for (Skill requiredSkill : requiredSkills) {

            int currentLevel = findCurrentSkillLevel(
                    currentSkills,
                    requiredSkill.getSkillName()
            );

            int requiredLevel =
                    requiredSkill.getSkillLevel();

            double skillScore =
                    ((double) currentLevel / requiredLevel) * 100;

            if (skillScore > 100) {
                skillScore = 100;
            }

            totalScore += skillScore;
        }

        return totalScore / requiredSkills.size();
    }

    // Find current skill level
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

    // Generate ranked career recommendations
    public void recommendCareers(
            ArrayList<Skill> currentSkills,
            ArrayList<Career> careers) {

        ArrayList<CareerMatch> matches =
                new ArrayList<>();

        // Calculate match score for every career
        for (Career career : careers) {

            double score =
                    calculateMatchScore(
                            currentSkills,
                            career
                    );

            matches.add(
                    new CareerMatch(career, score)
            );
        }

        // Sort careers from highest to lowest score
        matches.sort(
                Comparator.comparingDouble(
                        (CareerMatch match)
                                -> match.matchScore
                ).reversed()
        );

        System.out.println();
        System.out.println(
                "========== CAREER RECOMMENDATIONS =========="
        );

        int rank = 1;

        for (CareerMatch match : matches) {

            System.out.printf(
                    "%d. %-22s Match: %.2f%%%n",
                    rank,
                    match.career.getCareerName(),
                    match.matchScore
            );

            rank++;
        }

        System.out.println(
                "============================================"
        );
    }
}
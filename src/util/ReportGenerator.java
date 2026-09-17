package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Student;
import model.Skill;
import model.Career;
import service.SkillAnalytics;
import service.RecommendationEngine;

public class ReportGenerator {

    public void generateReport(
            Student student,
            ArrayList<Skill> skills,
            Career targetCareer,
            ArrayList<Career> careers) {

        File reportsFolder = new File("reports");

        if (!reportsFolder.exists()) {
            reportsFolder.mkdirs();
        }

        String filePath = "reports/career_report.txt";

        try (FileWriter writer = new FileWriter(filePath)) {

            // Report Header
            writer.write("====================================================\n");
            writer.write("       AI PERSONAL LEARNING & CAREER PLATFORM\n");
            writer.write("====================================================\n\n");

            writer.write("PERSONALIZED CAREER INTELLIGENCE REPORT\n");
            writer.write("----------------------------------------------------\n");

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            writer.write(
                    "Generated On : "
                    + LocalDateTime.now().format(formatter)
                    + "\n\n"
            );

            // Student Profile
            writer.write("1. STUDENT PROFILE\n");
            writer.write("----------------------------------------------------\n");
            writer.write("Name        : " + student.getName() + "\n");
            writer.write("Branch      : " + student.getBranch() + "\n");
            writer.write("Year        : " + student.getYear() + "\n");
            writer.write("CGPA        : " + student.getCgpa() + "\n");
            writer.write("Career Goal : " + student.getCareerGoal() + "\n\n");

            // Skills
            writer.write("2. CURRENT SKILL PROFILE\n");
            writer.write("----------------------------------------------------\n");

            for (Skill skill : skills) {

                writer.write(
                        String.format(
                                "%-25s : %3d/100\n",
                                skill.getSkillName(),
                                skill.getSkillLevel()
                        )
                );
            }

            writer.write("\n");

            // Skill Analytics
            int total = skills.size();
            int totalLevel = 0;

            Skill highest = skills.get(0);
            Skill lowest = skills.get(0);

            int strong = 0;
            int developing = 0;
            int weak = 0;

            for (Skill skill : skills) {

                int level = skill.getSkillLevel();

                totalLevel += level;

                if (level > highest.getSkillLevel()) {
                    highest = skill;
                }

                if (level < lowest.getSkillLevel()) {
                    lowest = skill;
                }

                if (level >= 70) {
                    strong++;
                }
                else if (level >= 40) {
                    developing++;
                }
                else {
                    weak++;
                }
            }

            double average =
                    (double) totalLevel / total;

            writer.write("3. SKILL ANALYTICS\n");
            writer.write("----------------------------------------------------\n");
            writer.write("Total Skills        : " + total + "\n");
            writer.write(
                    String.format(
                            "Average Skill Level : %.2f/100\n",
                            average
                    )
            );

            writer.write(
                    "Highest Skill       : "
                    + highest.getSkillName()
                    + " ("
                    + highest.getSkillLevel()
                    + "/100)\n"
            );

            writer.write(
                    "Lowest Skill        : "
                    + lowest.getSkillName()
                    + " ("
                    + lowest.getSkillLevel()
                    + "/100)\n"
            );

            writer.write("Strong Skills       : " + strong + "\n");
            writer.write("Developing Skills   : " + developing + "\n");
            writer.write("Weak Skills         : " + weak + "\n\n");

            // Career Target
            writer.write("4. TARGET CAREER\n");
            writer.write("----------------------------------------------------\n");
            writer.write(
                    "Target Career : "
                    + targetCareer.getCareerName()
                    + "\n\n"
            );

            // Skill Gap Analysis
            writer.write("5. SKILL-GAP ANALYSIS\n");
            writer.write("----------------------------------------------------\n");

            int skillsMet = 0;
            int totalRequired =
                    targetCareer.getRequiredSkills().size();

            for (Skill requiredSkill :
                    targetCareer.getRequiredSkills()) {

                int currentLevel =
                        findCurrentSkillLevel(
                                skills,
                                requiredSkill.getSkillName()
                        );

                int requiredLevel =
                        requiredSkill.getSkillLevel();

                int gap =
                        requiredLevel - currentLevel;

                if (gap < 0) {
                    gap = 0;
                }

                if (currentLevel >= requiredLevel) {
                    skillsMet++;
                }

                writer.write(
                        String.format(
                                "%-20s Current: %3d | Required: %3d | Gap: %3d\n",
                                requiredSkill.getSkillName(),
                                currentLevel,
                                requiredLevel,
                                gap
                        )
                );
            }

            double readiness =
                    ((double) skillsMet / totalRequired) * 100;

            writer.write("\n");

            writer.write(
                    String.format(
                            "Career Readiness : %.2f%%\n",
                            readiness
                    )
            );

            writer.write(
                    "Skills Meeting Requirement : "
                    + skillsMet
                    + "/"
                    + totalRequired
                    + "\n\n"
            );

            // Career Recommendations
            writer.write("6. CAREER RECOMMENDATIONS\n");
            writer.write("----------------------------------------------------\n");

            RecommendationEngine engine =
                    new RecommendationEngine();

            ArrayList<CareerMatch> matches =
                    new ArrayList<>();

            for (Career career : careers) {

                double score =
                        engine.calculateMatchScore(
                                skills,
                                career
                        );

                matches.add(
                        new CareerMatch(
                                career,
                                score
                        )
                );
            }

            matches.sort(
                    (a, b) ->
                            Double.compare(
                                    b.score,
                                    a.score
                            )
            );

            int rank = 1;

            for (CareerMatch match : matches) {

                writer.write(
                        String.format(
                                "%2d. %-22s Match: %.2f%%\n",
                                rank,
                                match.career.getCareerName(),
                                match.score
                        )
                );

                rank++;
            }

            writer.write("\n");

            // Learning Roadmap
            writer.write("7. PERSONALIZED LEARNING ROADMAP\n");
            writer.write("----------------------------------------------------\n");
            writer.write(
                    "Target Career : "
                    + targetCareer.getCareerName()
                    + "\n\n"
            );

            ArrayList<RoadmapItem> roadmap =
                    new ArrayList<>();

            for (Skill requiredSkill :
                    targetCareer.getRequiredSkills()) {

                int currentLevel =
                        findCurrentSkillLevel(
                                skills,
                                requiredSkill.getSkillName()
                        );

                int requiredLevel =
                        requiredSkill.getSkillLevel();

                int gap =
                        requiredLevel - currentLevel;

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

            roadmap.sort(
                    (a, b) ->
                            Integer.compare(
                                    b.gap,
                                    a.gap
                            )
            );

            int step = 1;

            for (RoadmapItem item : roadmap) {

                writer.write(
                        step
                        + ". "
                        + item.skillName
                        + "\n"
                );

                writer.write(
                        "   Current Level : "
                        + item.currentLevel
                        + "/100\n"
                );

                writer.write(
                        "   Target Level  : "
                        + item.requiredLevel
                        + "/100\n"
                );

                writer.write(
                        "   Skill Gap     : "
                        + item.gap
                        + "\n"
                );

                writer.write(
                        "   Priority      : "
                        + item.priority
                        + "\n\n"
                );

                step++;
            }

            if (roadmap.isEmpty()) {

                writer.write(
                        "All required skill levels have been achieved.\n\n"
                );
            }

            // Footer
            writer.write("====================================================\n");
            writer.write("                 END OF REPORT\n");
            writer.write("====================================================\n");

            System.out.println();
            System.out.println(
                    "✓ Career report generated successfully."
            );

            System.out.println(
                    "File: " + filePath
            );

        }
        catch (IOException e) {

            System.out.println();
            System.out.println(
                    "❌ Error while generating report."
            );

            System.out.println(
                    "Reason: " + e.getMessage()
            );
        }
    }


    private int findCurrentSkillLevel(
            ArrayList<Skill> skills,
            String skillName) {

        for (Skill skill : skills) {

            if (skill.getSkillName()
                    .equalsIgnoreCase(skillName)) {

                return skill.getSkillLevel();
            }
        }

        return 0;
    }


    private static class CareerMatch {

        private Career career;
        private double score;

        public CareerMatch(
                Career career,
                double score) {

            this.career = career;
            this.score = score;
        }
    }


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
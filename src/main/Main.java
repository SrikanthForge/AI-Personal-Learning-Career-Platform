package main;

import database.CareerDAO;
import database.ProgressDAO;
import database.ProgressHistoryDAO;
import database.SkillDAO;
import database.StudentDAO;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Career;
import model.Skill;
import model.Student;
import service.ProgressTracker;
import service.RecommendationEngine;
import service.RoadmapGenerator;
import service.SkillAnalytics;
import service.SkillGapAnalyzer;
import thread.AnalysisTask;
import util.FileManager;
import util.ReportGenerator;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =========================================================
        // STUDENT PROFILE
        // =========================================================

        Student student = new Student(
                "Srikanth",
                "CSE (AI & ML)",
                2,
                8.7,
                "AI/ML Engineer"
        );

        // =========================================================
        // STUDENT SKILLS
        // =========================================================
        // Skills are loaded from MySQL database after
        // the SkillDAO object is created below.
        // =========================================================

        ArrayList<Skill> skills = new ArrayList<>();

        // =========================================================
        // CAREERS
        // =========================================================

        ArrayList<Career> careers = new ArrayList<>();

        // ---------------------------------------------------------
        // 1. AI/ML Engineer
        // ---------------------------------------------------------

        Career aiMlEngineer =
                new Career("AI/ML Engineer");

        aiMlEngineer.addRequiredSkill(
                new Skill("Python", 80)
        );

        aiMlEngineer.addRequiredSkill(
                new Skill("DSA", 70)
        );

        aiMlEngineer.addRequiredSkill(
                new Skill("SQL", 60)
        );

        aiMlEngineer.addRequiredSkill(
                new Skill("Machine Learning", 85)
        );

        aiMlEngineer.addRequiredSkill(
                new Skill("Statistics", 75)
        );

        aiMlEngineer.addRequiredSkill(
                new Skill("Data Analysis", 70)
        );

        // ---------------------------------------------------------
        // 2. Data Scientist
        // ---------------------------------------------------------

        Career dataScientist =
                new Career("Data Scientist");

        dataScientist.addRequiredSkill(
                new Skill("Python", 80)
        );

        dataScientist.addRequiredSkill(
                new Skill("SQL", 70)
        );

        dataScientist.addRequiredSkill(
                new Skill("Machine Learning", 75)
        );

        dataScientist.addRequiredSkill(
                new Skill("Statistics", 85)
        );

        dataScientist.addRequiredSkill(
                new Skill("Data Analysis", 85)
        );

        // ---------------------------------------------------------
        // 3. Data Analyst
        // ---------------------------------------------------------

        Career dataAnalyst =
                new Career("Data Analyst");

        dataAnalyst.addRequiredSkill(
                new Skill("Python", 60)
        );

        dataAnalyst.addRequiredSkill(
                new Skill("SQL", 75)
        );

        dataAnalyst.addRequiredSkill(
                new Skill("Statistics", 65)
        );

        dataAnalyst.addRequiredSkill(
                new Skill("Data Analysis", 85)
        );

        // ---------------------------------------------------------
        // 4. Java Developer
        // ---------------------------------------------------------

        Career javaDeveloper =
                new Career("Java Developer");

        javaDeveloper.addRequiredSkill(
                new Skill("Java", 85)
        );

        javaDeveloper.addRequiredSkill(
                new Skill("DSA", 75)
        );

        javaDeveloper.addRequiredSkill(
                new Skill("SQL", 60)
        );

        // ---------------------------------------------------------
        // 5. Python Developer
        // ---------------------------------------------------------

        Career pythonDeveloper =
                new Career("Python Developer");

        pythonDeveloper.addRequiredSkill(
                new Skill("Python", 85)
        );

        pythonDeveloper.addRequiredSkill(
                new Skill("DSA", 65)
        );

        pythonDeveloper.addRequiredSkill(
                new Skill("SQL", 55)
        );

        // ---------------------------------------------------------
        // 6. Software Developer
        // ---------------------------------------------------------

        Career softwareDeveloper =
                new Career("Software Developer");

        softwareDeveloper.addRequiredSkill(
                new Skill("Java", 75)
        );

        softwareDeveloper.addRequiredSkill(
                new Skill("DSA", 80)
        );

        softwareDeveloper.addRequiredSkill(
                new Skill("SQL", 55)
        );

        // ---------------------------------------------------------
        // 7. Backend Developer
        // ---------------------------------------------------------

        Career backendDeveloper =
                new Career("Backend Developer");

        backendDeveloper.addRequiredSkill(
                new Skill("Java", 75)
        );

        backendDeveloper.addRequiredSkill(
                new Skill("DSA", 75)
        );

        backendDeveloper.addRequiredSkill(
                new Skill("SQL", 80)
        );

        // ---------------------------------------------------------
        // 8. Full Stack Developer
        // ---------------------------------------------------------

        Career fullStackDeveloper =
                new Career("Full Stack Developer");

        fullStackDeveloper.addRequiredSkill(
                new Skill("Java", 65)
        );

        fullStackDeveloper.addRequiredSkill(
                new Skill("DSA", 65)
        );

        fullStackDeveloper.addRequiredSkill(
                new Skill("SQL", 70)
        );

        fullStackDeveloper.addRequiredSkill(
                new Skill("Web Development", 85)
        );

        // ---------------------------------------------------------
        // 9. Cloud Engineer
        // ---------------------------------------------------------

        Career cloudEngineer =
                new Career("Cloud Engineer");

        cloudEngineer.addRequiredSkill(
                new Skill("Python", 60)
        );

        cloudEngineer.addRequiredSkill(
                new Skill("SQL", 50)
        );

        cloudEngineer.addRequiredSkill(
                new Skill("Cloud Computing", 85)
        );

        // ---------------------------------------------------------
        // 10. Cybersecurity Analyst
        // ---------------------------------------------------------

        Career cybersecurityAnalyst =
                new Career("Cybersecurity Analyst");

        cybersecurityAnalyst.addRequiredSkill(
                new Skill("Python", 55)
        );

        cybersecurityAnalyst.addRequiredSkill(
                new Skill("DSA", 50)
        );

        cybersecurityAnalyst.addRequiredSkill(
                new Skill("Cybersecurity", 85)
        );

        // =========================================================
        // ADD CAREERS TO LIST
        // =========================================================

        careers.add(aiMlEngineer);
        careers.add(dataScientist);
        careers.add(dataAnalyst);
        careers.add(javaDeveloper);
        careers.add(pythonDeveloper);
        careers.add(softwareDeveloper);
        careers.add(backendDeveloper);
        careers.add(fullStackDeveloper);
        careers.add(cloudEngineer);
        careers.add(cybersecurityAnalyst);

        // =========================================================
        // SERVICES
        // =========================================================

        SkillGapAnalyzer gapAnalyzer =
                new SkillGapAnalyzer();

        RecommendationEngine recommendationEngine =
                new RecommendationEngine();

        RoadmapGenerator roadmapGenerator =
                new RoadmapGenerator();

        ProgressTracker progressTracker =
                new ProgressTracker();

        SkillAnalytics skillAnalytics =
                new SkillAnalytics();

        FileManager fileManager =
                new FileManager();

        ReportGenerator reportGenerator =
                new ReportGenerator();

        // =========================================================
        // JDBC DAO OBJECTS
        // =========================================================

        StudentDAO studentDAO =
                new StudentDAO();

        SkillDAO skillDAO =
                new SkillDAO();

        CareerDAO careerDAO =
                new CareerDAO();

        ProgressDAO progressDAO =
                new ProgressDAO();

        ProgressHistoryDAO progressHistoryDAO =
                new ProgressHistoryDAO();

        // =========================================================
        // LOAD STUDENT SKILLS FROM MYSQL DATABASE
        // =========================================================
        // MySQL is the primary source of student skill data.
        // Student ID = 1 is used for the current student.
        // =========================================================

        ArrayList<Skill> databaseSkills =
                skillDAO.getStudentSkills(1);

        if (databaseSkills != null
                && !databaseSkills.isEmpty()) {

            skills = databaseSkills;

            System.out.println();
            System.out.println(
                    "✓ Student skills loaded from MySQL successfully."
            );

        } else {

            System.out.println();
            System.out.println(
                    "⚠ Unable to load student skills from database."
            );

            System.out.println(
                    "No student skill data is available."
            );
        }

        // =========================================================
        // MAIN MENU
        // =========================================================

        int choice = -1;

        while (choice != 0) {

            System.out.println();
            System.out.println(
                    "===================================================="
            );

            System.out.println(
                    " AI PERSONAL LEARNING & CAREER INTELLIGENCE PLATFORM"
            );

            System.out.println(
                    "===================================================="
            );

            System.out.println(
                    "\n===== STUDENT & CAREER ANALYSIS ====="
            );

            System.out.println(
                    "1.  View Student Profile"
            );

            System.out.println(
                    "2.  View Skills"
            );

            System.out.println(
                    "3.  Skill Analytics"
            );

            System.out.println(
                    "4.  Analyze Skill Gaps"
            );

            System.out.println(
                    "5.  Career Recommendations"
            );

            System.out.println(
                    "6.  Generate Learning Roadmap"
            );

            System.out.println(
                    "7.  Update Skill Progress"
            );

            System.out.println(
                    "8.  Save Skill Data"
            );

            System.out.println(
                    "9.  View Saved Data"
            );

            System.out.println(
                    "10. Run Complete Career Analysis"
            );

            System.out.println(
                    "11. Generate Career Report"
            );

            System.out.println(
                    "\n===== DATABASE (JDBC) ====="
            );

            System.out.println(
                    "12. View Student from Database"
            );

            System.out.println(
                    "13. View Skills from Database"
            );

            System.out.println(
                    "14. View Career Requirements from Database"
            );

            System.out.println(
                    "15. Update Skill in Database"
            );

            System.out.println(
                    "16. View Progress History"
            );

            System.out.println(
                    "\n0.  Exit"
            );

            System.out.print(
                    "\nEnter your choice: "
            );

            try {

                choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {

                    // =================================================
                    // 1. STUDENT PROFILE
                    // =================================================

                    case 1:

                        System.out.println(
                                "\n===== STUDENT PROFILE ====="
                        );

                        student.displayProfile();

                        break;

                    // =================================================
                    // 2. SKILLS
                    // =================================================

                    case 2:

                        System.out.println(
                                "\n===== STUDENT SKILLS ====="
                        );

                        if (skills.isEmpty()) {

                            System.out.println(
                                    "No skill data available."
                            );

                        } else {

                            for (Skill skill : skills) {

                                skill.displaySkill();
                            }
                        }

                        break;

                    // =================================================
                    // 3. SKILL ANALYTICS
                    // =================================================

                    case 3:

                        System.out.println(
                                "\n===== SKILL ANALYTICS ====="
                        );

                        skillAnalytics.analyze(
                                skills
                        );

                        break;

                    // =================================================
                    // 4. SKILL GAP ANALYSIS
                    // =================================================

                    case 4:

                        System.out.println(
                                "\n===== SKILL GAP ANALYSIS ====="
                        );

                        gapAnalyzer.analyzeSkills(
                                skills,
                                aiMlEngineer
                        );

                        break;

                    // =================================================
                    // 5. CAREER RECOMMENDATIONS
                    // =================================================

                    case 5:

                        System.out.println(
                                "\n===== CAREER RECOMMENDATIONS ====="
                        );

                        recommendationEngine.recommendCareers(
                                skills,
                                careers
                        );

                        break;

                    // =================================================
                    // 6. LEARNING ROADMAP
                    // =================================================

                    case 6:

                        System.out.println(
                                "\n===== PERSONALIZED LEARNING ROADMAP ====="
                        );

                        roadmapGenerator.generateRoadmap(
                                skills,
                                aiMlEngineer
                        );

                        break;

                    // =================================================
                    // 7. UPDATE SKILL PROGRESS
                    // =================================================
                    // Updated:
                    // Option 7 now synchronizes:
                    // MySQL + Java object + skills.txt
                    // Progress history is also recorded in MySQL.
                    // =================================================

                    case 7:

                        Skill updatedSkill =
                                progressTracker.updateSkill(
                                        skills
                                );

                        if (updatedSkill != null) {

                            // -----------------------------------------
                            // Find corresponding Skill ID
                            // -----------------------------------------

                            int skillId = -1;

                            for (int i = 0;
                                 i < skills.size();
                                 i++) {

                                if (skills.get(i)
                                        == updatedSkill) {

                                    skillId = i + 1;
                                    break;
                                }
                            }

                            // -----------------------------------------
                            // Synchronize with MySQL
                            // -----------------------------------------

                            if (skillId != -1) {

                                progressDAO.updateSkillLevel(
                                        1,
                                        skillId,
                                        updatedSkill.getSkillLevel()
                                );
                            }

                            // -----------------------------------------
                            // Save synchronized data to file
                            // -----------------------------------------

                            fileManager.saveSkills(
                                    skills
                            );

                            System.out.println(
                                    "\n===== SKILL SYNCHRONIZED SUCCESSFULLY ====="
                            );

                            System.out.println(
                                    "Skill       : "
                                            + updatedSkill.getSkillName()
                            );

                            System.out.println(
                                    "New Level   : "
                                            + updatedSkill.getSkillLevel()
                                            + "%"
                            );

                            System.out.println(
                                    "Database, Java object and file "
                                            + "are synchronized."
                            );
                        }

                        break;

                    // =================================================
                    // 8. SAVE SKILL DATA
                    // =================================================

                    case 8:

                        fileManager.saveSkills(
                                skills
                        );

                        System.out.println(
                                "Skill data saved successfully."
                        );

                        break;

                    // =================================================
                    // 9. VIEW SAVED DATA
                    // =================================================

                    case 9:

                        System.out.println(
                                "\n===== SAVED SKILL DATA ====="
                        );

                        fileManager.readSkills();

                        break;

                    // =================================================
                    // 10. COMPLETE CAREER ANALYSIS
                    // =================================================

                    case 10:

                        System.out.println(
                                "\n===== COMPLETE CAREER ANALYSIS ====="
                        );

                        AnalysisTask analysisTask =
                                new AnalysisTask(
                                        skills,
                                        aiMlEngineer,
                                        careers
                                );

                        Thread analysisThread =
                                new Thread(
                                        analysisTask,
                                        "Career-Analysis-Thread"
                                );

                        analysisThread.start();

                        try {

                            analysisThread.join();

                        } catch (
                                InterruptedException e) {

                            Thread.currentThread()
                                    .interrupt();

                            System.out.println(
                                    "Analysis thread interrupted."
                            );
                        }

                        break;

                    // =================================================
                    // 11. GENERATE REPORT
                    // =================================================

                    case 11:

                        System.out.println(
                                "\nGenerating career report..."
                        );

                        reportGenerator.generateReport(
                                student,
                                skills,
                                aiMlEngineer,
                                careers
                        );

                        break;

                    // =================================================
                    // 12. JDBC STUDENT
                    // =================================================

                    case 12:

                        studentDAO.displayStudent(
                                1
                        );

                        break;

                    // =================================================
                    // 13. JDBC SKILLS
                    // =================================================

                    case 13:

                        skillDAO.displayStudentSkills(
                                1
                        );

                        break;

                    // =================================================
                    // 14. JDBC CAREER REQUIREMENTS
                    // =================================================

                    case 14:

                        careerDAO.displayCareerRequirements(
                                1
                        );

                        break;

                    // =================================================
                    // 15. JDBC UPDATE SKILL
                    // =================================================

                    case 15:

                        try {

                            System.out.print(
                                    "Enter Skill ID: "
                            );

                            int skillId =
                                    scanner.nextInt();

                            System.out.print(
                                    "Enter New Skill Level (0-100): "
                            );

                            int newLevel =
                                    scanner.nextInt();

                            scanner.nextLine();

                            // -----------------------------------------
                            // Validate Skill ID
                            // -----------------------------------------

                            if (
                                    skillId < 1
                                            ||
                                    skillId > skills.size()
                            ) {

                                System.out.println(
                                        "Invalid Skill ID."
                                );

                                break;
                            }

                            // -----------------------------------------
                            // Validate Skill Level
                            // -----------------------------------------

                            if (
                                    newLevel < 0
                                            ||
                                    newLevel > 100
                            ) {

                                System.out.println(
                                        "Skill level must be between 0 and 100."
                                );

                                break;
                            }

                            // -----------------------------------------
                            // Update MySQL
                            // -----------------------------------------

                            progressDAO.updateSkillLevel(
                                    1,
                                    skillId,
                                    newLevel
                            );

                            // -----------------------------------------
                            // Synchronize Java Skill Object
                            // -----------------------------------------

                            Skill selectedSkill =
                                    skills.get(
                                            skillId - 1
                                    );

                            selectedSkill.updateSkillLevel(
                                    newLevel
                            );

                            // -----------------------------------------
                            // Save synchronized data to file
                            // -----------------------------------------

                            fileManager.saveSkills(
                                    skills
                            );

                            System.out.println(
                                    "\n===== SKILL SYNCHRONIZED SUCCESSFULLY ====="
                            );

                            System.out.println(
                                    "Skill       : "
                                            + selectedSkill.getSkillName()
                            );

                            System.out.println(
                                    "New Level   : "
                                            + selectedSkill.getSkillLevel()
                                            + "%"
                            );

                            System.out.println(
                                    "Database, Java object and file "
                                            + "are synchronized."
                            );

                        } catch (
                                InputMismatchException e) {

                            System.out.println(
                                    "Please enter valid numeric values."
                            );

                            scanner.nextLine();
                        }

                        break;

                    // =================================================
                    // 16. JDBC PROGRESS HISTORY
                    // =================================================

                    case 16:

                        progressHistoryDAO
                                .displayProgressHistory(
                                        1
                                );

                        break;

                    // =================================================
                    // EXIT
                    // =================================================

                    case 0:

                        System.out.println(
                                "\nThank you for using the "
                                        + "AI Personal Learning & Career "
                                        + "Intelligence Platform!"
                        );

                        break;

                    // =================================================
                    // INVALID OPTION
                    // =================================================

                    default:

                        System.out.println(
                                "Invalid choice. "
                                        + "Please select an option from 0-16."
                        );
                }

            } catch (
                    InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
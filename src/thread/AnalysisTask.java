package thread;

import java.util.ArrayList;

import model.Skill;
import model.Career;
import service.SkillGapAnalyzer;
import service.RecommendationEngine;
import service.RoadmapGenerator;

public class AnalysisTask implements Runnable {

    private ArrayList<Skill> studentSkills;
    private Career targetCareer;
    private ArrayList<Career> careers;

    private SkillGapAnalyzer analyzer;
    private RecommendationEngine recommendationEngine;
    private RoadmapGenerator roadmapGenerator;

    public AnalysisTask(
            ArrayList<Skill> studentSkills,
            Career targetCareer,
            ArrayList<Career> careers) {

        this.studentSkills = studentSkills;
        this.targetCareer = targetCareer;
        this.careers = careers;

        analyzer = new SkillGapAnalyzer();
        recommendationEngine = new RecommendationEngine();
        roadmapGenerator = new RoadmapGenerator();
    }

    @Override
    public void run() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       UPDATED CAREER ANALYSIS");
        System.out.println("==============================================");

        System.out.println(
                "Analysis running on thread: "
                + Thread.currentThread().getName()
        );

        System.out.println();

        analyzer.analyzeSkills(
                studentSkills,
                targetCareer
        );

        recommendationEngine.recommendCareers(
                studentSkills,
                careers
        );

        roadmapGenerator.generateRoadmap(
                studentSkills,
                targetCareer
        );

        System.out.println();
        System.out.println(
                "Career analysis thread completed successfully."
        );
    }
}
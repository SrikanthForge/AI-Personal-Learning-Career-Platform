package util;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Skill;

public class FileManager {

    private static final String FILE_PATH =
            "data/skills.txt";

    // Save skills to file
    public void saveSkills(ArrayList<Skill> skills) {

        File file = new File("data");

        if (!file.exists()) {
            file.mkdirs();
        }

        try (FileWriter writer =
                     new FileWriter(FILE_PATH)) {

            writer.write(
                    "===== STUDENT SKILL DATA =====\n"
            );

            for (Skill skill : skills) {

                writer.write(
                        skill.getSkillName()
                        + " : "
                        + skill.getSkillLevel()
                        + "/100\n"
                );
            }

            writer.write(
                    "==============================\n"
            );

            System.out.println();
            System.out.println(
                    "✓ Skill data saved successfully."
            );

            System.out.println(
                    "File: " + FILE_PATH
            );

        } catch (IOException e) {

            System.out.println();
            System.out.println(
                    "❌ Error while saving skill data."
            );

            System.out.println(
                    "Reason: " + e.getMessage()
            );
        }
    }


    // Read skills from file
    public void readSkills() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            System.out.println();
            System.out.println(
                    "❌ No saved skill data found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "========== SAVED SKILL DATA =========="
        );

        try (FileReader reader =
                     new FileReader(file);

             BufferedReader bufferedReader =
                     new BufferedReader(reader)) {

            String line;

            while ((line =
                    bufferedReader.readLine()) != null) {

                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println();
            System.out.println(
                    "❌ Error while reading skill data."
            );

            System.out.println(
                    "Reason: " + e.getMessage()
            );
        }

        System.out.println(
                "======================================"
        );
    }
}
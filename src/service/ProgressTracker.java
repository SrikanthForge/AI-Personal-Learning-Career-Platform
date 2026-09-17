package service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Skill;
import exception.InvalidInputException;
import exception.InvalidSkillException;

public class ProgressTracker {

    private Scanner scanner;

    public ProgressTracker() {
        scanner = new Scanner(System.in);
    }

    /*
     * Updates a skill and returns the updated Skill object.
     * Returning the object allows Main.java to synchronize
     * the change with the database.
     */
    public Skill updateSkill(ArrayList<Skill> skills) {

        if (skills == null || skills.isEmpty()) {

            System.out.println(
                    "No skills available to update."
            );

            return null;
        }

        System.out.println();
        System.out.println(
                "========== CURRENT SKILL PROGRESS =========="
        );

        for (int i = 0; i < skills.size(); i++) {

            Skill skill = skills.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + skill.getSkillName()
                    + " : "
                    + skill.getSkillLevel()
                    + "/100"
            );
        }

        System.out.println(
                "============================================"
        );

        int skillNumber =
                getSkillNumber(skills.size());

        Skill selectedSkill =
                skills.get(skillNumber - 1);

        int oldLevel =
                selectedSkill.getSkillLevel();

        System.out.println();

        System.out.println(
                "Selected Skill : "
                + selectedSkill.getSkillName()
        );

        System.out.println(
                "Current Level  : "
                + oldLevel
                + "/100"
        );

        int newLevel = getSkillLevel();

        selectedSkill.updateSkillLevel(newLevel);

        int difference =
                newLevel - oldLevel;

        System.out.println();

        System.out.println(
                "========== UPDATE SUCCESSFUL =========="
        );

        System.out.println(
                "Skill        : "
                + selectedSkill.getSkillName()
        );

        System.out.println(
                "Old Level    : "
                + oldLevel
                + "/100"
        );

        System.out.println(
                "New Level    : "
                + newLevel
                + "/100"
        );

        if (difference > 0) {

            System.out.println(
                    "Progress     : Improved by "
                    + difference
                    + " points"
            );

        } else if (difference < 0) {

            System.out.println(
                    "Progress     : Decreased by "
                    + Math.abs(difference)
                    + " points"
            );

        } else {

            System.out.println(
                    "Progress     : No change"
            );
        }

        System.out.println(
                "======================================="
        );

        return selectedSkill;
    }

    private int getSkillNumber(
            int totalSkills) {

        while (true) {

            System.out.print(
                    "Enter skill number to update: "
            );

            try {

                int skillNumber =
                        scanner.nextInt();

                validateSkillNumber(
                        skillNumber,
                        totalSkills
                );

                return skillNumber;

            } catch (InputMismatchException e) {

                System.out.println();

                System.out.println(
                        "❌ Invalid input!"
                );

                System.out.println(
                        "Please enter a numeric skill number."
                );

                scanner.nextLine();

            } catch (InvalidInputException e) {

                System.out.println();

                System.out.println(
                        "❌ "
                        + e.getMessage()
                );
            }
        }
    }

    private int getSkillLevel() {

        while (true) {

            System.out.print(
                    "Enter new skill level (0-100): "
            );

            try {

                int newLevel =
                        scanner.nextInt();

                validateSkillLevel(newLevel);

                return newLevel;

            } catch (InputMismatchException e) {

                System.out.println();

                System.out.println(
                        "❌ Invalid input!"
                );

                System.out.println(
                        "Please enter a numeric value between 0 and 100."
                );

                scanner.nextLine();

            } catch (InvalidSkillException e) {

                System.out.println();

                System.out.println(
                        "❌ "
                        + e.getMessage()
                );
            }
        }
    }

    private void validateSkillNumber(
            int skillNumber,
            int totalSkills)
            throws InvalidInputException {

        if (skillNumber < 1
                || skillNumber > totalSkills) {

            throw new InvalidInputException(
                    "Skill number must be between 1 and "
                    + totalSkills
                    + "."
            );
        }
    }

    private void validateSkillLevel(
            int level)
            throws InvalidSkillException {

        if (level < 0 || level > 100) {

            throw new InvalidSkillException(
                    "Skill level must be between 0 and 100."
            );
        }
    }
}
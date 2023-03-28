package calculator;

import enums.AgeCategory;

public final class AgeCalculator {
    private static final int BABY_UP_INTERVAL = 5;
    private static final int KID_UP_INTERVAL = 12;
    private static final int TEEN_UP_INTERVAL = 18;

    private AgeCalculator() {

    }

    /**
     * Method used to identify the suitable category for a child
     * depending on the child's age: baby, kid, teen, young adult.
     * @param age the age of a child
     * @return the suitable category
     */
    public static AgeCategory getAgeCategory(final int age) {
        if (age < BABY_UP_INTERVAL) {
            return AgeCategory.BABY;
        } else if (age < KID_UP_INTERVAL) {
            return AgeCategory.KID;
        } else if (age <= TEEN_UP_INTERVAL) {
            return AgeCategory.TEEN;
        }
        return AgeCategory.YOUNG_ADULT;
    }
}

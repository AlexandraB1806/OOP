package database;

import entities.Children;
import entities.SantaGifts;

import java.util.Arrays;
import java.util.List;

public final class Helpers {
    private Helpers() { }

    /**
     * Method used to add the information about children
     * in my database.
     * @param children that will be added
     */
    public static void addChildren(final List<Children> children) {
        for (int i = 0; i < children.size(); i++) {
            Database.getDatabase().addChildren(Arrays.asList(
                    new Children(children.get(i).getId(),
                            children.get(i).getLastName(),
                            children.get(i).getFirstName(),
                            children.get(i).getAge(),
                            children.get(i).getCity(),
                            children.get(i).getNiceScore(),
                            children.get(i).getGiftsPreferences()
            )));
        }
    }

    /**
     * Method used to add the information about gifts
     * in my database.
     * @param gifts that will be added
     */
    public static void addGifts(final List<SantaGifts> gifts) {
        for (int i = 0; i < gifts.size(); i++) {
            Database.getDatabase().addGifts(Arrays.asList(
                    new SantaGifts(gifts.get(i).getProductName(),
                            gifts.get(i).getPrice(),
                            gifts.get(i).getCategory()
                    )));
        }
    }

    /**
     * Method used to reset the database.
     */
    public static void clear() {
        Database.getDatabase().clearDatabase();
    }
}

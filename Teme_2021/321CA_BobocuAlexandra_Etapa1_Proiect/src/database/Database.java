package database;

import entities.Children;
import entities.SantaGifts;

import java.util.ArrayList;
import java.util.List;

public final class Database {
    private final List<Children> children = new ArrayList<>();
    private final List<SantaGifts> gifts = new ArrayList<>();

    public List<Children> getChildren() {
        return children;
    }

    public List<SantaGifts> getGifts() {
        return gifts;
    }

    private static Database instance = null;
    private Database() { }

    /**
     * Singleton lazy instantiation.
     * @return instance of database
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Add the children from InitialData in my database.
     * @param listChildren that will be added
     */
    public void addChildren(final List<Children> listChildren) {
        this.children.addAll(listChildren);
    }

    /**
     * Add the gifts from InitialData in my database.
     * @param listGifts that will be added
     */
    public void addGifts(final List<SantaGifts> listGifts) {
        this.gifts.addAll(listGifts);
    }

    /**
     * Clear the lists of children and gifts from the
     * database.
     */
    public void clearDatabase() {
        children.clear();
        gifts.clear();
    }
}

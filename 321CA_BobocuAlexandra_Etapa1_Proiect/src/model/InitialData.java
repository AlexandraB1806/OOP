package model;

import entities.Children;
import entities.SantaGifts;

import java.util.List;

public class InitialData {
    private List<Children> children;
    private List<SantaGifts> santaGiftsList;

    /**
     * Method used to obtain the children list.
     * @return children
     */
    public List<Children> getChildren() {
        return children;
    }

    /**
     * Method used to set the children list.
     * @param children given children
     */
    public void setChildren(final List<Children> children) {
        this.children = children;
    }

    /**
     * Method used to obtain the gifts list.
     * @return gifts
     */
    public List<SantaGifts> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * Method used to set the gifts list.
     * @param santaGiftsList given gifts
     */
    public void setSantaGiftsList(final List<SantaGifts> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}

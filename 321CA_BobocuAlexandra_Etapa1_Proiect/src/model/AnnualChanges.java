package model;

import entities.Children;
import entities.SantaGifts;

import java.util.List;

public class AnnualChanges {
    private Double newSantaBudget;
    private List<SantaGifts> newGifts;
    private List<Children> newChildren;
    private List<ChildUpdate> childrenUpdates;
    /**
     * Method used to obtain the new Santa's budget for a certain year.
     * @return new Santa's budget
     */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * Method used to set the new Santa's budget for a certain year.
     * @param newSantaBudget given Santa's budget
     */
    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     * Method used to obtain the new gifts.
     * @return gifts
     */
    public List<SantaGifts> getNewGifts() {
        return newGifts;
    }

    /**
     * Method used to set the new gifts.
     * @param newGifts given gifts
     */
    public void setNewGifts(final List<SantaGifts> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * Method used to obtain a list with new children.
     * @return children
     */
    public List<Children> getNewChildren() {
        return newChildren;
    }

    /**
     * Method used to set new children.
     * @param newChildren given children
     */
    public void setNewChildren(final List<Children> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * Method used to obtain the list of updates for some children.
     * @return the updates
     */
    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * Method used to set the list of updates for some children.
     * @param childrenUpdates the given updates
     */
    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}

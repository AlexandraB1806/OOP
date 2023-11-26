package calculator;

import entities.SantaGifts;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public class SantaGiftsDelivery {
    // List of gifts
    private final List<SantaGifts> santaGifts;
    // List of child's gift preferences
    private final List<Category> childPreferences;
    // Budget allocated for buying a gift for a child
    private Double allocatedBudget;

    public SantaGiftsDelivery(final List<SantaGifts> santaGifts,
                              final Double allocatedBudget,
                              final List<Category> childPreferences) {
        this.santaGifts = santaGifts;
        this.childPreferences = childPreferences;
        this.allocatedBudget = allocatedBudget;
    }

    /**
     * Method used to obtain the list of the most preferred gifts,
     * with a convenient price for a child.
     * @return the gifts for child
     */
    public List<SantaGifts> getSantaGifts() {
        // Create a list of gifts for a child
        // It retains the gifts filtered by preference and price
        List<SantaGifts> giftsForChild = new ArrayList<>();

        // Traverse the preferences' list of a child
        for (Category category: childPreferences) {
            // Check if the child's each preference for gift exist in the
            // Santa's gift list
            // Save the gifts from the searched category in a separated list
            List<SantaGifts> giftsFromCategory = findGiftFromCategory(category);
            // After finding the gifts from a specified category,
            // make sure to choose the gift with the most convenient price
            SantaGifts gift = findCheapestGift(giftsFromCategory);
            // Check if the gift was found and it's price does not
            // exceed the allocated budget for a child
            if (gift != null && gift.getPrice() < allocatedBudget) {
                // Put the found gift in the list of presents for a child
                giftsForChild.add(gift);
                // Update the allocated budget for a child
                allocatedBudget -= gift.getPrice();
            }
        }
        // Return the list of gifts
        return giftsForChild;
    }

    /**
     * Method used to find a gift from a category preferred by a child.
     * @param childGiftPreference the category of gift preferred by a child
     * @return the list of gifts from the preferred category existent in
     * Santa's list
     */
    private List<SantaGifts> findGiftFromCategory(final Category
                                                          childGiftPreference) {
        // Create a list of existent gifts in Santa's list
        // from the child's preferred category
        List<SantaGifts> santaGiftsFromACategory = new ArrayList<>();
        // Traverse the Santa's gift list
        for (SantaGifts santaGift : santaGifts) {
            // Found the searched gift
            if (santaGift.getCategory().equals(childGiftPreference)) {
                // Add the gift in the newly created list
                santaGiftsFromACategory.add(santaGift);
            }
        }
        // Return the list of gifts from a specific category
        return santaGiftsFromACategory;
    }

    /**
     * Method used to find the cheapest gift from a specific category.
     * @param santaGiftsList the gifts that Santa has
     * @return the cheapest gift
     */
    private SantaGifts findCheapestGift(final List<SantaGifts> santaGiftsList) {
        // Initialize the minimum price
        Double minPrice = Double.MAX_VALUE;
        // If we do not find the gift it remains null
        SantaGifts gift = null;

        // Search in the specified gift list
        for (SantaGifts santaGift: santaGiftsList) {
            if (santaGift.getPrice() < minPrice) {
                // Found cheap gift
                gift = santaGift;
                // Update the minimum price for search; in case we find a
                // cheaper gift
                minPrice = santaGift.getPrice();
            }
        }
        // Return the cheapest gift from the Santa's gift list
        return gift;
    }
}

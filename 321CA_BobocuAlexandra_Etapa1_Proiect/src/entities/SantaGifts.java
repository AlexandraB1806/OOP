package entities;

import enums.Category;

public class SantaGifts {
    private String productName;
    private Double price;
    private Category category;

    public SantaGifts() {

    }

    public SantaGifts(final String productName, final Double price,
                      final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    /**
     * Method used to obtain the name of a product.
     * @return product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method used to set the name of a product.
     * @param productName the name that needs to be given to a product
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * Method used to obtain the price of a product.
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Method used to set the price of a product.
     * @param price that needs to be given to a product
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * Method used to obtain the category of a product.
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Method used to set the category of a product.
     * @param category that needs to be given to a product
     */
    public void setCategory(final Category category) {
        this.category = category;
    }
}

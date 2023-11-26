package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to encapsulate the list of annual children.
 */
public class AnnualChildrenWrapper {
    private List<AnnualChildren> children;

    public AnnualChildrenWrapper() {
        children = new ArrayList<>();
    }

    /**
     * Method used to obtain the annual children.
     * @return children
     */
    public List<AnnualChildren> getChildren() {
        return children;
    }

    /**
     * Method used to set the annual children.
     * @param children given children
     */
    public void setChildren(final List<AnnualChildren> children) {
        this.children = children;
    }
}

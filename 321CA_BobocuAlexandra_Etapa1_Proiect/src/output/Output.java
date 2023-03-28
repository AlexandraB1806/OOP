package output;

import model.AnnualChildrenWrapper;

import java.util.List;

public class Output {
    private List<AnnualChildrenWrapper> annualChildren;

    public Output() {

    }

    /**
     * Method used to obtain the children every year
     * with all the information about them.
     * @return children every year
     */
    public List<AnnualChildrenWrapper> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * Method used to set the list of every year children.
     * @param annualChildren given children every year
     */
    public void setAnnualChildren(final List<AnnualChildrenWrapper>
                                          annualChildren) {
        this.annualChildren = annualChildren;
    }
}

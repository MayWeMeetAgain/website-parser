package utils.enums;

public enum PageCssClass {
    NEXT_PAGE("next_page"),
    COMPANIES_ITEM("companies-item");

    private final String text;

    PageCssClass(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}


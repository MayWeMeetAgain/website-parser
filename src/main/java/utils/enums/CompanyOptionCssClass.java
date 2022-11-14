package utils.enums;

public enum CompanyOptionCssClass {
    TITLE_CLASS("title"),
    IS_ACCREDITED("svg-icon--icon-accredited"),
    IS_HABR_MEMBER("company-icons__icon_habr"),
    RATING("rating"),
    LOCATION("location"),
    SIZE("size"),
    VACANCIES("vacancies_count");

    private final String text;

    CompanyOptionCssClass(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
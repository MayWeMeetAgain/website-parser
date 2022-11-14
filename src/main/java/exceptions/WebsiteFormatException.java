package exceptions;

public class WebsiteFormatException extends IllegalArgumentException {

    public WebsiteFormatException() {
        super("Website format does not match");
    }
}

package mat.study.store.product.exeption;

public class NonUniqueUserException extends RuntimeException {

    private final static String MSG = "User must be unique";

    public NonUniqueUserException() {
        super(MSG);
    }
}

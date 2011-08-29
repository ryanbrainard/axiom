package axiom.oauth.oauth1;

/**
 * @author Ryan Brainard
 * @since 2011-08-28
 */
enum ApiType {
    PARTNER('u'),
    ENTERPRISE('c');

    public final char code;

    ApiType(char code) {
        this.code = code;
    }
}
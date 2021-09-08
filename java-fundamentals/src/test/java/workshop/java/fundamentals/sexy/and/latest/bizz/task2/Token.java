package workshop.java.fundamentals.sexy.and.latest.bizz.task2;

public class Token {

    private final String tokenId;
    private TokenDetails details;
    private boolean active;

    public Token(String tokenId, TokenDetails details, boolean active) {
        this.tokenId = tokenId;
        this.details = details;
    }

    public String getTokenId() {
        return tokenId;
    }

    public TokenDetails getDetails() {
        return details;
    }

    public boolean isActive() {
        return active;
    }
}

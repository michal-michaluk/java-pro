package workshop.java.fundamentals.sexy.and.latest.bizz.task3;

public class Token {

    private final String tokenId;
    private TokenDetails details;
    private boolean active;

    public Token(String tokenId, TokenDetails details, boolean active) {
        this.tokenId = tokenId;
        this.details = details;
        this.active = active;
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

package workshop.java.fundamentals.sexy.and.latest.bizz.task1;

import workshop.java.fundamentals.sexy.and.latest.bizz.task1.TokenSnapshotV1.StatusV1;

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

    public TokenSnapshotV1 toSnapshot() {
        return new TokenSnapshotV1(
                tokenId,
                // TODO: fill details
                null,
                active ? StatusV1.VALID : StatusV1.INVALID
        );
    }
}

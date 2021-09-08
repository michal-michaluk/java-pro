package workshop.java.fundamentals.sexy.and.latest.bizz;

public class $ExitTest {

    // implement mapping of business class Token and hierarchy TokenDetails
    // into publicly exposed API TokenSnapshotV1

    // Task 1. use polymorphism
    // implement in package workshop.java.fundamentals.sexy.and.latest.bizz.task1
    // let class Token to convert itself into TokenSnapshotV1 by creating method Token::toSnapshot
    // let class TokenDetails to convert itself into DetailsV1
    // add into TokenDetails class: public abstract TokenSnapshotV1.DetailsV1 toDetails();
    // all type of details are mapped into DetailsV1 in naive way:
    // new DetailsV1(
    //     "CARD",
    //     card.getRfid(),
    //     card.getNumber(),
    //     null,
    //     null
    // );

    // Task 2. change TokenDetails hierarchy into sealed classes
    // implement in package workshop.java.fundamentals.sexy.and.latest.bizz.task2
    // implement mapping into TokenSnapshotV1 inside method TokenSnapshotV1.from(Token)
    // use if statement pattern matching inside that method
    // public static DetailsV1 from(TokenDetails details) {
    //    TokenDetails.Card card = (TokenDetails.Card) details;
    //    return new DetailsV1(
    //            "CARD",
    //            card.getRfid(),
    //            card.getNumber(),
    //            null,
    //            null
    //    );

    // Task 3. continue on top of your progress with Task 2. copy package:
    // workshop.java.fundamentals.sexy.and.latest.bizz.task2
    // into workshop.java.fundamentals.sexy.and.latest.bizz.task3
    // implement mapping into TokenSnapshotV1 inside method TokenSnapshotV1.from(Token)
    // but use switch pattern matching inside

    // discussion:
    // Which variant is better, what do you prefer?
}

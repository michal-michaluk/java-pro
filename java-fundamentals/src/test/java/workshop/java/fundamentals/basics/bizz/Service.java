package workshop.java.fundamentals.basics.bizz;

public class Service {

    private Database database;
    private RemoteService service;

    public Service(Database database, RemoteService service) {
        this.database = database;
        this.service = service;
    }

    public void doBusinessStuff() {
        String data = database.readData();
        String dataToSend = data + " to send";
        service.doYourJob(dataToSend);
    }
}

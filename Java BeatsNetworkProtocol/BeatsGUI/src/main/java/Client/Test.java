package Client;

/**
 * Created by Gebruiker on 5/23/2017.
 */
public class Test {



    public static void main(String[] args) {
        receivedDataHandler handler =  new receivedDataHandler();
        handler.addListener(new ResponseListener() {
            public void responseReceived() {
                System.out.println("ali");

            }
        });
    }
}

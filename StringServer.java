import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String allStrings = "";

    //this search engine returns the first word that contains the substring, not all of them
    //ALSO!!!! there is some problem with the encoding of the the string (container in the return statement)
    //not sure how to fix it
    public String handleRequest(URI url) {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                    allStrings += "\n" + parameters[1];
                    return allStrings;
            } 
            return "404 Not Found!";
        }
    }


class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}


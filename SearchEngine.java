import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> myStrings = new ArrayList<String>();

    //this search engine returns the first word that contains the substring, not all of them
    //ALSO!!!! there is some problem with the encoding of the the string (container in the return statement)
    //not sure how to fix it
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Max Rosenbloom's Strings");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                    myStrings.add(parameters[0]);
                    return String.format("saved!");
            } else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                    String subString = parameters[0];
                    String container = null;
                    for (int i = 0; i < myStrings.size(); i++) {
                        if (myStrings.get(i).contains(subString)) {
                            container = myStrings.get(i);
                            break;
                        } 
                    }
                    if (container != null) {
                        return String.format("%s contains the substring", container);
                    }
                    return String.format("No such string found.");
            }
            return "404 Not Found!";
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}


package ahuglajbclajep.simpapp.sendreq;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendRequests {
    public static void main(String[] args) {
        final Options options = new Options();

        options.addOption(Option.builder("u")
                .longOpt("url")
                .argName("url")
                .desc("URL, you want to requests")
                .hasArg()
                .required()
                .build());

        CommandLine cmd;
        HelpFormatter usage =new HelpFormatter();
        try {
            cmd = new DefaultParser().parse(options, args);

        } catch (ParseException e) {
            String header = "Send requests to url, and get responses";
            String footer = "If you find bugs, please report issues at https://github.com/ahuglajbclajep/send-requests-to-url/issues";
            usage.printHelp("gradlew run -P args=\"-u http://example.com\"", header, options, footer);
            return;
        }

        try {
            URL url = new URL(cmd.getOptionValue('u'));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

        } catch (MalformedURLException e){
            System.err.println("an unknown protocol is found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

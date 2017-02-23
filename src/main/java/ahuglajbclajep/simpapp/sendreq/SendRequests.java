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
                .desc("---")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("---")
                .build());

        CommandLine cmd;
        HelpFormatter usage =new HelpFormatter();
        try {
            cmd = new DefaultParser().parse(options, args);

        } catch (ParseException e) {
            usage.printHelp("hoge [options] <fuga>", options);
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

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

        options.addOption(Option.builder("c")
                .longOpt("counts")
                .argName("int")
                .desc("c--")
                .hasArg()
                .build());

        options.addOption(Option.builder("d")
                .longOpt("delay")
                .argName("int")
                .desc("d--")
                .hasArg()
                .build());

        options.addOption(Option.builder("o")
                .longOpt("offsets")
                .desc("o--")
                .build());

        CommandLine cmd;
        HelpFormatter usage = new HelpFormatter();
        try {
            cmd = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            String header = "Send requests to url, and get responses";
            String footer = "If you find bugs, please report issues at https://github.com/ahuglajbclajep/send-requests-to-url/issues";
            usage.printHelp("gradlew run -P args=\"-u http://example.com [options]\"", header, options, footer);
            return;
        }

        int counts = 1;
        try {
            if (cmd.hasOption('c') && Integer.parseInt(cmd.getOptionValue('c')) > 1) {
                counts = Integer.parseInt(cmd.getOptionValue('c'));
            }
        } catch (NumberFormatException e) {
        }

        int delay = 1;
        try {
            if (cmd.hasOption('d') && Integer.parseInt(cmd.getOptionValue('d')) > 1) {
                delay = Integer.parseInt(cmd.getOptionValue('d'));
            }
        } catch (NumberFormatException e) {
        }

        boolean offsets = false;
        if (cmd.hasOption('o')) {
            offsets = true;
        }

        for (int i = 0; i < counts; i++) {
            if (i != 0 || offsets) {
                try{
                    Thread.sleep(delay * 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            sendReq(cmd);
        }
    }


    private static void sendReq(CommandLine cmd) {
        try {
            URL url = new URL(cmd.getOptionValue('u'));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

        } catch (MalformedURLException e) {
            System.err.println("an unknown protocol is found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

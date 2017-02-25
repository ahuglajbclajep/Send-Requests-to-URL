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
                .desc("Destination url, required")
                .hasArg()
                .required()
                .build());

        options.addOption(Option.builder("t")
                .longOpt("trials")
                .argName("int")
                .desc("Number of trials")
                .hasArg()
                .build());

        options.addOption(Option.builder("i")
                .longOpt("interval")
                .argName("int")
                .desc("Interval of send, sec")
                .hasArg()
                .build());

        options.addOption(Option.builder("d")
                .longOpt("delay")
                .desc("Take a interval at first")
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

        int trials = 1;
        try {
            if (cmd.hasOption('t') && Integer.parseInt(cmd.getOptionValue('t')) > 1) {
                trials = Integer.parseInt(cmd.getOptionValue('t'));
            }
        } catch (NumberFormatException e) {}

        int interval = 1;
        try {
            if (cmd.hasOption('i') && Integer.parseInt(cmd.getOptionValue('i')) > 1) {
                interval = Integer.parseInt(cmd.getOptionValue('i'));
            }
        } catch (NumberFormatException e) {}

        boolean delay = false;
        if (cmd.hasOption('o')) {
            delay = true;
        }

        for (int i = 0; i < trials; i++) {
            if (i != 0 || delay) {
                try{
                    Thread.sleep(interval * 1000);
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

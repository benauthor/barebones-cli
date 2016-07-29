package com.fake.barebones;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

    private static final Logger log = Logger.getLogger(Parser.class.getName());
    private String[] args = null;
    private Options options = new Options();

    public Parser(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help");
        options.addOption("n", "wat", true, "some command line option");

    }

    public void parse() {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.getOptions().length == 0) {
                help();
            }
            if (cmd.hasOption("h")) {
                help();
            }

            if (cmd.hasOption("n"))
                log.log(Level.INFO, "WAT? " + cmd.getOptionValue("n"));

        } catch (ParseException e) {
            help();
        }
    }

    public void help() {
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("barebones [options]", options);
        System.exit(0);
    }
}

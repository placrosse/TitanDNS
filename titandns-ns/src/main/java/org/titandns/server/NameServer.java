/*
 * Copyright 2011 Brian Matthews and Arann Villing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.titandns.server;

import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This class encapsulates the program entry point.
 * 
 * @author Brian Matthews
 * @version 1.0.0-SNAPSHOT
 */
public class NameServer {

	/**
	 * Used to write application log messages.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(NameServer.class);

	public static final ResourceBundle BUNDLE = ResourceBundle
			.getBundle("messages");

	/**
	 * The default constructor.
	 */
	private NameServer() {
	}

	/**
	 * The program entry point. Use Google Guice to instantiate and configure
	 * the Name Server end-point.
	 * 
	 * @param args
	 *            The command line arguments.
	 */
	public static void main(final String[] args) {

		try {
			final NameServerModule module = new NameServerModule();

			// Parse the command line options

			final Options options = new Options();
			options.addOption("?", "help", false,
					NameServer.BUNDLE.getString("options.help"));
			options.addOption("v", "version", false,
					NameServer.BUNDLE.getString("options.version"));
			options.addOption("h", "hostname", true,
					NameServer.BUNDLE.getString("options.hostname"));
			options.addOption("p", "port", true,
					NameServer.BUNDLE.getString("options.port"));

			final CommandLineParser parser = new GnuParser();

			final CommandLine line = parser.parse(options, args);

			// Display the version information

			if (line.hasOption("v")) {
				final String name = BUNDLE.getString("version.name");
				final String copyright = BUNDLE.getString("version.copyright");
				System.out.println(name);
				System.out.println(copyright);
				System.exit(0);
			}

			// Display the usage information

			if (line.hasOption("?")) {
				final String name = BUNDLE.getString("version.name");
				final String copyright = BUNDLE.getString("version.copyright");
				final String usage = BUNDLE.getString("version.copyright");
				System.out.println(name);
				System.out.println(copyright);
				System.out.println();
				System.out.println(usage);
				System.exit(0);
			}

			// Override the default hostname

			if (line.hasOption("h")) {
				final String hostname = line.getOptionValue("h");
				module.setHostname(hostname);
			}

			// Override the default port number

			if (line.hasOption("p")) {
				final String portNumberStr = line.getOptionValue("p");
				final int portNumber = Integer.valueOf(portNumberStr);
				module.setPortNumber(portNumber);
			}

			// Launch the server

			final Injector injector = Guice.createInjector(module);

			final NameServerEndpoint serverEndpoint = injector
					.getInstance(NameServerEndpoint.class);

			serverEndpoint.run();

		} catch (final ParseException e) {
			// TODO: Decide on meaningful error message
			final String message = "";
			NameServer.LOGGER.error(message, e);
			System.exit(0);
		} catch (final NumberFormatException e) {
			// TODO: Decide on meaningful error message
			final String message = "";
			NameServer.LOGGER.error(message, e);
			System.exit(0);
		} catch (final Exception e) {
			// TODO: Decide on meaningful error message
			final String message = "";
			NameServer.LOGGER.error(message, e);
			System.exit(0);
		}
	}
}

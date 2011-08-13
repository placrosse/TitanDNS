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

import org.titandns.server.netty.NameServerEndpointImpl;

import com.google.inject.AbstractModule;

public class NameServerModule extends AbstractModule {

	/**
	 * The default host name or bind address is 0.0.0.0 which means the DNS
	 * server will listen on all interfaces.
	 */
	private static final String DEFAULT_HOSTNAME = "0.0.0.0";

	/**
	 * The default port number for the DNS protocol is 53.
	 */
	private static final int DEFAULT_PORT_NUMBER = 53;

	/**
	 * The host name or bind address on which the DNS server will listen.
	 */
	private String hostname;

	/**
	 * The port number at which the DNS server is listening.
	 */
	private int portNumber;

	/**
	 * The default constructor sets the {@link NameServerModule#hostname
	 * hostname} and {@link NameServerModule#portNumber portNumber} to the
	 * default values as defined by {@link NameServerModule#DEFAULT_HOSTNAME
	 * DEFAULT_HOSTNAME} and {@link NameServerModule#DEFAULT_PORT_NUMBER
	 * DEFAULT_PORT_NUMBER} respectively.
	 */
	public NameServerModule() {
		this.hostname = NameServerModule.DEFAULT_HOSTNAME;
		this.portNumber = NameServerModule.DEFAULT_PORT_NUMBER;
	}

	/**
	 * Used to override the default host name or bind address.
	 * 
	 * @param hostname
	 *            The new host name or bind address.
	 */
	public void setHostname(final String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Used to override the default port number.
	 * 
	 * @param portNumber
	 *            The new port number.
	 */
	public void setPortNumber(final int portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * Configure the dependency injector binding interfaces to implementations
	 * and constants to annotations.
	 */
	@Override
	public void configure() {
		this.bind(NameServerEndpoint.class).to(NameServerEndpointImpl.class);
		this.bindConstant().annotatedWith(Hostname.class).to(this.hostname);
		this.bindConstant().annotatedWith(PortNumber.class).to(this.portNumber);
	}
}

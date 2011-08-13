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

package org.titandns.server.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.titandns.server.Hostname;
import org.titandns.server.NameServerEndpoint;
import org.titandns.server.PortNumber;

import com.google.inject.Inject;

public class NameServerEndpointImpl implements NameServerEndpoint {

	/**
	 * The host name or bind address at which the DNS server is listening.
	 */
	private String hostname;

	/**
	 * The port number on which the DNS server is listening.
	 */
	private int port;

	@Inject
	public NameServerEndpointImpl(@Hostname final String hostname,
			@PortNumber final int port) {
		this.hostname = hostname;
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.titandns.server.NameServerEndpoint#run()
	 */
	public void run() {
		final ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new DNSMessageEncoder(),
						new DNSMessageDecoder(), new DNSMessageHandler());
			}
		});
		final InetSocketAddress address = new InetSocketAddress(this.hostname,
				this.port);
		bootstrap.bind(address);
	}
}

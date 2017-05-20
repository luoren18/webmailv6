package com.lawren.bysj.parse;

import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.sun.mail.imap.IMAPSSLStore;
import jodd.util.StringPool;

public class MyImapSslServer extends MyImapServer{
	protected static final String MAIL_IMAP_SOCKET_FACTORY_PORT = "mail.imap.socketFactory.port";
	protected static final String MAIL_IMAP_SOCKET_FACTORY_CLASS = "mail.imap.socketFactory.class";
	protected static final String MAIL_IMAP_SOCKET_FACTORY_FALLBACK = "mail.imap.socketFactory.fallback";
	protected static final int DEFAULT_SSL_PORT = 993;

	protected final String username;
	protected final String password;

	public MyImapSslServer(String host, String username, String password) {
		this(host, DEFAULT_SSL_PORT, username, password);
	}

	public MyImapSslServer(String host, int port, String username, String password) {
		super(host, port, username, password);
		this.username = username;
		this.password = password;
	}

	/**
	 * Sets the session property. May be set only before the session is
	 * created.
	 */
	public MyImapSslServer setProperty(String name, String value) {
		super.setProperty(name, value);
		return this;
	}

	@Override
	protected Properties createSessionProperties() {
		Properties props = new Properties();
		props.setProperty(MAIL_IMAP_PORT, String.valueOf(port));
		props.setProperty(MAIL_IMAP_SOCKET_FACTORY_PORT, String.valueOf(port));
		props.setProperty(MAIL_IMAP_PARTIALFETCH, "false");
		props.setProperty(MAIL_IMAP_SOCKET_FACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
		props.setProperty(MAIL_IMAP_SOCKET_FACTORY_FALLBACK, StringPool.FALSE);
		return props;
	}

	@Override
	protected Store getStore(Session session) throws NoSuchProviderException {
		URLName url = new URLName(PROTOCOL_IMAP, host, port, "", username, password);
		return new IMAPSSLStore(session, url);
	}
}

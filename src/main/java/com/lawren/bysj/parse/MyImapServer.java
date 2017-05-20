package com.lawren.bysj.parse;

import javax.mail.Authenticator;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import jodd.mail.ImapServer;
import jodd.mail.MailException;
public class MyImapServer extends ImapServer {

	public MyImapServer(String host) {
		super(host);
	}

	public MyImapServer(String host, Authenticator authenticator) {
		super(host, authenticator);
	}

	public MyImapServer(String host, int port, Authenticator authenticator) {
		super(host, port, authenticator);		
	}

	public MyImapServer(String host, int port, String username, String password) {
		super(host, port, username, password);
	}

	public MyImapServer(String host, int port) {
		super(host, port);
	}

	
	public MyReceiveMailSession createSession(String FOLDER) {
		Session session = Session.getInstance(sessionProperties, authenticator);
		Store store;
		try {
			store = getStore(session);
		} catch (NoSuchProviderException nspex) {
			throw new MailException("Failed to create IMAP session", nspex);
		}
		return new MyReceiveMailSession(session, store, FOLDER);
	}
	
	
	
}

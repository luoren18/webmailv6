package com.lawren.bysj.parse;

import javax.mail.Session;
import javax.mail.Store;

import jodd.mail.ReceiveMailSession;

public class MyReceiveMailSession extends ReceiveMailSession {

	private String DEFAULT_FOLDER = "收件箱";
	public MyReceiveMailSession(Session session, Store store,String DEFAULT_FOLDER) {
		super(session, store);
		this.DEFAULT_FOLDER=DEFAULT_FOLDER;
	}
	
	@Override
	public void useDefaultFolder() {
		closeFolderIfOpened();
		useFolder(DEFAULT_FOLDER);
	}
}

package com.lawren.bysj.parse;

import org.junit.Test;

import jodd.mail.ImapServer;
import jodd.mail.ImapSslServer;
import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;

public class TestPar {

	/**
	 * google imap ssl test
	 * 
	 */
	@Test
	public void test01(){
		ImapServer imapServer =
		        new ImapSslServer("imap.gmail.com", "lawren653815@gmail.com", "1435590129");
		    ReceiveMailSession session = imapServer.createSession();
		    session.open();
		    System.out.println(session.getMessageCount());
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					System.out.println(email.getAllMessages().get(0).getContent().length());
				}
			}
		    session.close(); 
	}
	
	/**
	 * google pop3 ssl test
	 * 
	 */
	@Test
	public void test02(){
		Pop3Server popServer = new Pop3SslServer("pop.gmail.com", "lawren653815@gmail.com", "1435590129");
	    ReceiveMailSession session = popServer.createSession();
		    session.open();
		    System.out.println(session.getMessageCount());
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
				}
			}
		    session.close();
	}

	
	
	/**
	 * sina imap ssl test
	 * 该方法也可以获取邮件，但效率较低
	 */
	@Test
	public void test03(){
		long start=System.currentTimeMillis();
		ImapServer imapServer =
		        new ImapSslServer("imap.sina.com", "luoren18@sina.cn", "abcd1234e");
		    ReceiveMailSession session = imapServer.createSession();
		    session.open();
		    System.out.println(session.getMessageCount());
		    for(String s:session.getAllFolders()){
		    	System.out.println(s);
		    }
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					System.out.println(email.getReceiveDate());
					System.out.println("是否看到"+email.isSeen());
					System.out.println(email.isAnswered());
					System.out.println(email.isRecent());
					System.out.println(email.getAllMessages().get(0).getContent().length());
				}
			}
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);//32256
	}
	
	
	/**
	 * sina pop3 ssl test
	 * 对于新浪邮箱而言 pop3的效率更高
	 */
	@Test
	public void test04(){
		long start=System.currentTimeMillis();
		Pop3Server popServer = new Pop3SslServer("pop.sina.com", "luoren18@sina.cn", "abcd1234e");
	    ReceiveMailSession session = popServer.createSession();
		    session.open();
		    System.out.println(session.getMessageCount());
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					System.out.println(email.getReceiveDate());
					System.out.println(email.getSentDate());
					System.out.println(email.getAllMessages().get(0).getContent().length());
					System.out.println(email.getHeader("Status"));
				}
			}
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);//6990
	}
	
	
	/**
	 * 163 imap ssl test
	 * 该方式无法获取163邮件
	 */
	@Test
	public void test05(){
		long start=System.currentTimeMillis();
		/*MyImapServer imapServer=new MyImapSslServer("imap.163.com", 143, "lawren653815@163.com", "abcd1234E");
		
		MyReceiveMailSession session=imapServer.createSession("INBOX");*/
		ImapServer imapServer =
		        new ImapServer("imap.163.com", 143, "lawren653815@163.com", "abcd1234E");
		    ReceiveMailSession session = imapServer.createSession();
		    session.open();
		    
		    System.out.println(session.getMessageCount());
		    /*ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					System.out.println(email.getAllMessages().get(0).getContent().length());
				}
			}*/
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);
	}
	
	
	/**
	 * 163 pop3 ssl test
	 * 
	 */
	@Test
	public void test06(){
		long start=System.currentTimeMillis();
		Pop3Server popServer = new Pop3SslServer("pop.163.com", "lawren653815@163.com", "abcd1234E");
	    ReceiveMailSession session = popServer.createSession();
		    session.open();
		    for(String f:session.getAllFolders()){
		    	System.out.println(f);
		    }
		    System.out.println(session.getMessageCount());
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					/*System.out.println("是否看到"+email.isSeen());
					System.out.println(email.isAnswered());
					System.out.println(email.isRecent());*/
					System.out.println(email.getReceiveDate());
					System.out.println(email.getSentDate());
					System.out.println(email.getAllMessages().get(0).getContent().length());
				}
			}
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);//6990
	}
	
	/**
	 * qq imap ssl test
	 * 该方式无法获取INBOX 的邮件
	 */
	@Test
	public void test07(){
		long start=System.currentTimeMillis();
		/*ImapServer imapServer =
		        new ImapSslServer("imap.qq.com", 993, "1435590129@qq.com", "ayqmikfwevdlgaca");*/
		MyImapServer imapServer=new MyImapSslServer("imap.qq.com", "1435590129@qq.com", "ayqmikfwevdlgaca");
		
		MyReceiveMailSession session=imapServer.createSession("INBOX");
		    /*ReceiveMailSession session = imapServer.createSession();*/
		    session.open();
		   /* System.out.println(session.getMessageCount());*/
		    for(String s:session.getAllFolders()){
		    	System.out.println(s);
		    }
		    System.out.println("----------------------------");
		   ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.getSubject());
					System.out.println(email.getAllMessages().get(0).getContent().length());
				}
			}
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);
	}
	
	
	/**
	 * qq pop3 ssl test
	 * 该方式可以获取qq邮箱的未读邮件
	 */
	@Test
	public void test08(){
		long start=System.currentTimeMillis();
		Pop3Server popServer = new Pop3SslServer("pop.qq.com", "1435590129@qq.com", "ayqmikfwevdlgaca");
	    ReceiveMailSession session = popServer.createSession();
		    session.open();
		    for(String f:session.getAllFolders()){
		    	System.out.println(f);
		    }
		    System.out.println(session.getMessageCount());
		    ReceivedEmail[] emails = session.receiveEmailAndMarkSeen();
		    if (emails!=null) {
				for(ReceivedEmail email:emails){
					System.out.println(email.isSeen());
					System.out.println(email.getSubject());
					System.out.println(email.isRecent());
					System.out.println(email.getAllMessages().get(0).getContent().length());
					System.out.println("----------------------");
				}
			}
		    session.close(); 
		    long end=System.currentTimeMillis();
		    System.out.println(end-start);//6990
	}
	public static void main(String[] args) {
		//ayqmikfwevdlgaca
	}
}

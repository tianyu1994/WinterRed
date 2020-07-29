package org.codeforworld.winterredserver.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class IdentifyingCodeUtils {

    private String propertiesUrl = "src/main/resources/identifyingCode.properties";
    private static String mailFrom = null;// 指明邮件的发件人
    private static String password_mailFrom = null;// 指明邮件的发件人登陆密码

    private static String mailTo = null; // 指明邮件的收件人

    private static String mailTittle = null;// 邮件的标题

    private static String mailText = null; // 邮件的文本内容

    /**
     * 更新验证码到配置文件
     * @param email
     * @param identifyingCode
     * @throws IOException
     */
    public Boolean updateFile(String email, String identifyingCode) throws IOException {
        Properties props = new Properties();
        FileInputStream iFile = new FileInputStream(propertiesUrl);
        props.load(iFile);
        iFile.close();

        FileOutputStream oFile = new FileOutputStream(propertiesUrl, false);
        props.setProperty(email, identifyingCode);
        props.store(oFile, "propertiesDemo");
        oFile.close();
        boolean isUpdateSuccess = checkFile(email, identifyingCode);
        return isUpdateSuccess;
    }

    public Boolean checkFile(String email, String identifyingCode) throws IOException {
        Properties props = new Properties();
        FileInputStream iFile = new FileInputStream(propertiesUrl);
        props.load(iFile);
        iFile.close();
        String identifyingCodeTemp = props.getProperty(email);
        return identifyingCodeTemp != null && identifyingCodeTemp.equals(identifyingCode);
    }

    public void sendEmail(String email, String identifyingCode) throws Exception{

        mailFrom = "1254926326@qq.com";
        password_mailFrom="nfokuqkhwxvagidf";
        mailTo = email;
        mailTittle="This is the identifying code!";
        mailText = "The identifying code:" + identifyingCode;

        Properties prop = System.getProperties();
        prop.setProperty("mail.host","smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");

//QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

// 获取默认session对象
        Session session = Session.getDefaultInstance(prop);

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com",mailFrom,password_mailFrom);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(mailFrom));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));

        //邮件标题
        mimeMessage.setSubject(mailTittle);

        //邮件内容
        mimeMessage.setContent(mailText,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }

    public static void main(String[] args)  throws Exception  {
        IdentifyingCodeUtils identifyingCodeUtils = new IdentifyingCodeUtils();
        boolean s = false;
        try {
            s = identifyingCodeUtils.checkFile("223","433");
            identifyingCodeUtils.sendEmail("1254926326@qq.com","123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}

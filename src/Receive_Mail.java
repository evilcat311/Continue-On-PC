import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class Receive_Mail {
    public static void main(String[] args){
        String Host_Email = "pop.gmail.com";
        String mailStrProt = "pop3";
        String Username = args[0];
        String Password = args[1];
        check(Host_Email, mailStrProt, Username, Password);

    }
    public static void check(String host, String storeType, String user, String Password){
        try{
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, Password);

            Folder emailFolder = store.getFolder("Inbox");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length----" + messages.length);
            int n = messages.length;
            for(int i = 0; i < n; i++){
                Message message = messages[i];
                System.out.println("----------------------------");
                System.out.println("Email number " + (i+1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }
            emailFolder.close();
            store.close();

        } catch (NoSuchProviderException e) { e.printStackTrace();}
        catch (MessagingException e){e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
}

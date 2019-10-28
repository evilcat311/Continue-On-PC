import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import java.io.IOException;
import java.util.List;



public class Process_Mail {
    public static String Process(List WhiteList, Message[] messages_array, Message message, int i) throws MessagingException, IOException {


        System.out.println(" messages.length----" + messages_array.length);
        System.out.println("----------------------------");
        System.out.println("Email number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());
        System.out.println("Content In Plain Text: ");
        Written_Boyo(message);
        System.out.println("Written Boyo outputs: " + Written_Boyo(message));
        return Written_Boyo(message);

    }

    public static String Written_Boyo(Part p) throws IOException, MessagingException {
        if(p.isMimeType("text/plain")){
            System.out.println("this message is plain text ");
            System.out.println("---------------------------");
            System.out.println((String) p.getContent());
            return (String)p.getContent();
        }
        else if(p.isMimeType("multipart/*")){
            Multipart mp = (Multipart) p.getContent();
            System.out.println("this message is a multi part ");
            System.out.println("---------------------------");
            int count = mp.getCount();
                for(int i=0; i<count; i++){
                    Written_Boyo(mp.getBodyPart(i));
                }

        }
        return null;
    }
}

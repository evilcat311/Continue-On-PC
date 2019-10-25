import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class Process_Mail {
    public static boolean Process(List WhiteList, Message[] messages_array, Message message, int i) throws MessagingException, IOException {


        System.out.println("\n messages.length----" + messages_array.length);
        System.out.println("----------------------------");
        System.out.println("Email number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());
        message.setFlag(Flags.Flag.DELETED, true);
        boolean tester = WhiteList.contains(message.getFrom()[0].toString());
        System.out.println(tester);
        System.out.println(message.getFrom()[0]);

        if(i >= messages_array.length){
            return false;
        }
        else{return true;}

    }
}

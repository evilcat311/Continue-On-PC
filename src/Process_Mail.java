import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import java.io.IOException;
import java.util.List;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;


public class Process_Mail {
    public static boolean Process(List WhiteList, Message[] messages_array, Message message, int i) throws MessagingException, IOException {


        System.out.println(" messages.length----" + messages_array.length);
        System.out.println("----------------------------");
        System.out.println("Email number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());
        System.out.println("Content In Plain Text: ");
        Written_Boyo(message);

        message.setFlag(Flags.Flag.DELETED, true);


        if(i >= messages_array.length){
            return false;
        }
        else{return true;}

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

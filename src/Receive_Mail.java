import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags;

public class Receive_Mail {
    public static void main(String[] args) throws FileNotFoundException {
        String Host_Email = "pop.gmail.com";
        String mailStrProt = "pop3";
        String Username = args[0];
        String Password = args[1];
        String token = ";";
        ArrayList WhiteList = new ArrayList<String> (Arrays.asList(Whitelist(token)));

        System.out.println(Arrays.toString(WhiteList.toArray()));


        check(Host_Email, mailStrProt, Username, Password, WhiteList);

    }
    public static void check(String host, String storeType, String user, String Password, List WhiteList){

        try{
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, Password);
            Folder emailFolder = store.getFolder("Inbox");
            emailFolder.open(Folder.READ_WRITE);
            Message[] messages_array = emailFolder.getMessages();
            int n = messages_array.length;
            for (int i = 0; i < n; i++) {
                Message message = messages_array[i];
                System.out.println(WhiteList.contains(message.getFrom()[0].toString()));
                System.out.println(message.getFrom()[0]);
                System.out.println("the i is " + i);
                Process_Mail.Process(WhiteList, messages_array, message, i);
                }

            emailFolder.close();
            store.close();

        } catch (NoSuchProviderException e) { e.printStackTrace();}
        catch (MessagingException e){e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

    }

        public static String[] Whitelist(String token) throws FileNotFoundException {
        Scanner Whitelist = new Scanner(new File("Deps/Whitelist.txt"));
        List<String> WhiteList = new ArrayList<String>();
        while(Whitelist.hasNext()){
            token = Whitelist.next();
            WhiteList.add(token);
        }
        Whitelist.close();
        String[] tester = WhiteList.toArray(new String[0]);
        for(int s = 0; s < tester.length; s++){
            tester[s] = tester[s] + "@mms.att.net";
        }
        return tester;
    }

}

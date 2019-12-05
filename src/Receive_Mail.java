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
    public static void main(String[] args) throws IOException, MessagingException {
        String Host_Email = "pop.gmail.com";
        String mailStrProt = "pop3";
        ArrayList<String> Credentials = new ArrayList<>();
        Credentials.add(Get_Credentials.Get_Username());
        Credentials.add(Get_Credentials.Get_Password());
        System.out.println(Credentials);
        String token = ";";
        ArrayList WhiteList = new ArrayList<> (Arrays.asList(Whitelist(token)));

        System.out.println(Arrays.toString(WhiteList.toArray()));


        check(Host_Email, mailStrProt, Credentials, WhiteList);

    }
    public static void check(String host, String storeType, ArrayList<String> Credentials, List WhiteList) throws MessagingException, IOException {
        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);

        Store store = emailSession.getStore("pop3s");
        String user = Credentials.get(0);
        String Password = Credentials.get(1);

        store.connect(host, user, Password);
        Folder emailFolder = store.getFolder("Inbox");
            Message[] messages_array = new Message[0];
            System.out.println(messages_array.length);
            while(true){

                if(messages_array.length != 0){
                    emailFolder.open(Folder.READ_WRITE);
                    int n = messages_array.length;
                    for(int i = 0; i < n; i++) {
                        Message message = messages_array[i];
                        System.out.println("\n---------------------------------------------");
                        System.out.println(WhiteList.contains(message.getFrom()[0].toString()));
                        System.out.println(message.getFrom()[0]);
                        System.out.println("the i is " + i);
                        message.setFlag(Flags.Flag.DELETED, true);
                        String Content = Process_Mail.Process(WhiteList, messages_array, message, i);
                        emailFolder.close();


                    }

                }
                else {
                    emailFolder.open(Folder.READ_WRITE);
                    messages_array = emailFolder.getMessages();
                    emailFolder.close();
                }
                if(messages_array.equals("ashd")){
                    emailFolder.close();
                    store.close();
                    System.exit(0);
                }
            }

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

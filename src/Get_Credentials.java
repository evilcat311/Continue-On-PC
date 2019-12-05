import java.util.Scanner;

public class Get_Credentials {

    public static String Get_Username(){
        String Username;
        Scanner ReadIn = new Scanner(System.in);
        System.out.println("Enter Username: ");
        Username = ReadIn.next();



        return Username;
    }

    public static String Get_Password(){
        String Password;
        Scanner ReadPassword = new Scanner(System.in);
        System.out.println("Enter Password: ");
        Password = ReadPassword.next();
        ReadPassword.close();

        return Password;
    }
}

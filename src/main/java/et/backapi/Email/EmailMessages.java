package et.backapi.Email;

import et.backapi.Entities.User;

public class EmailMessages {
    public static String createTitle(User user){
        return user.getUserFirstName() + " seu cadstro foi recebido!!";
    }

    public static String messageToNewUser(User user,String password){
        return "Olá " + user.getUserFirstName() +
                "! Seja muito bem-vindo(a) em nosso site" +
                "Os seus dados estão logo abaixo. \n\n" +
                "===================================== \n" +
                "Nome: " + user.getUserFirstName() + " " + user.getUserLastName() +
                "\nE-mail: " + user.getUserEmail() +
                "\nPassword: " + password + "\n" +
                "===================================== \n\n";
    }
}

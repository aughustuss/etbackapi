package et.backapi.Email;

import et.backapi.Entities.User;

public class EmailMessages {
    public static String createTitle(User user){
        return user.getUserFirstName() + " seu cadstro foi recebido!!";
    }

    public static String messageToNewUser(User user,String password){
        return "Olá " + user.getUserFirstName() +
                "! Seja muito bem-vindo(a) ao nosso site" +
                "Acesse o link para confirmar o seu e-mail. \n\n" +
                "===================================== \n" +
                "http://localhost:8000/confirmemail?email=" +
                user.getUserEmail() + "?codigo=" + user.getUserConfirmEmailToken();
    }
}

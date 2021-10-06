package ua.kiev.prog;

public class Menu {
    private static Users users = Users.getInstance();
    public Menu() {
    }
    public static String check(String str) {
        if ("all".equals(str)) {
            String logins = "All users: \n";
            for (String login : users.getUsers().keySet()) {
                logins = logins + login + "\n";
            }
            return logins;
        }
        if ("online".equals(str)) {
            String loginsOnline = "Online users: ";
            for (String login : users.getUsers().keySet()) {
                if (users.getUsers().get(login).isOnline())
                loginsOnline = loginsOnline + login + "\n";
            }
            return loginsOnline;
        }

        System.out.println(str);
        return str;
    }
}

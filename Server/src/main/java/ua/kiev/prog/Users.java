package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Users {
    private static final Users usersList = new Users();

    private final Gson gson;

    private final Map<String, User> users = new HashMap();

    public Map<String, User> getUsers() {
        return users;
    }

    public static Users getInstance() {
        return usersList;
    }

    private Users() {
        gson = new GsonBuilder().create();
    }

    public synchronized int add(User user) {
        for (String s : users.keySet()) {
            if (s.equals(user.getLogin())){
                return HttpServletResponse.SC_BAD_REQUEST;
            }
        }
        users.put(user.getLogin(), user);
        System.out.println("user " + user.getLogin()+ " added");
        return HttpServletResponse.SC_OK;
    }

    public synchronized int passwordCheck(String login, String pass){
        if (users.get(login).getPass().equals(pass)) {
            users.get(login).setOnline(true);
            return HttpServletResponse.SC_OK;

        }else
            return HttpServletResponse.SC_BAD_REQUEST;
    }

    public synchronized String toJSON() {
        return gson.toJson(users);
    }


    @Override
    public String toString() {
        return "Users{" +
                "gson=" + gson +
                ", users=" + users +
                '}';
    }
}

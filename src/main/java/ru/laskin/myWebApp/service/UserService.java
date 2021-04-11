package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.Main;
import ru.laskin.myWebApp.dao.UserDao;
import ru.laskin.myWebApp.model.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException throwables) {
            return null;
        }
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getAdminRole().equals("ADMIN")){
            user.setAdminRole("USER");
        }

        //присваиваем пользователю ключ
        user.setKey(UUID.randomUUID());
        userDao.saveUser(user);
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        if (user.getAdminRole() == ""){
            user.setAdminRole("USER");
        }
        userDao.updateUser(user);
    }

    public User getUserByLogin(String login) {
       return userDao.getUserByLogin(login);
    }

    public boolean checkUserRegistration(User user){
        return getUserByLogin(user.getLogin()) != null;
    }

    public static void sendEmail(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Здравствуйте, ");
        stringBuilder.append(user.getName() + "!\n");
        stringBuilder.append("Для подтверждения адреса электронной почты перейдите по ссылке:\n");
        stringBuilder.append("http://localhost:8080/confirmEmail/?userId=");
        stringBuilder.append(user.getUserId());
        stringBuilder.append("&key=");
        stringBuilder.append(user.getKey());
        String textMessage = stringBuilder.toString();

        final Properties properties = new Properties();
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(properties.getProperty("mail.smtps.user"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Test");
            message.setText(textMessage);

            Transport tr = session.getTransport();
            tr.connect(null, properties.getProperty("mail.password"));
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUuid(Integer userId, String key) {
        UUID uuid = UUID.fromString(key);
        User user = getUserById(userId);
        user.setRegistered(true);
        updateUser(user);
        return user.getKey().equals(uuid);
    }
}

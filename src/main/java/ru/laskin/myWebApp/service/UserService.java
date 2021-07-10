package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.Main;
import ru.laskin.myWebApp.dao.UserHiberDao;
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

    private final UserHiberDao userHiberDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserHiberDao userHiberDao, PasswordEncoder passwordEncoder) {
        this.userHiberDao = userHiberDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userHiberDao.getAllUsers();
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getAdminRole().equals("ADMIN")){
            user.setAdminRole("USER");
        }

        //присваиваем пользователю ключ
        user.setKey(UUID.randomUUID());
        userHiberDao.saveUser(user);
    }

    public User getUserById(int id){
        return userHiberDao.getUserById(id);
    }

    public void deleteUser(int id) {
        userHiberDao.deleteUser(id);
    }

    public void updateUser(User user) {
        if (user.getAdminRole().equals("")){
            user.setAdminRole("USER");
        }
        userHiberDao.updateUser(user);
    }

    public User getUserByLogin(String login) {
       return userHiberDao.getUserByLogin(login);
    }

    public boolean checkUserRegistration(User user){
        return getUserByLogin(user.getLogin()) != null;
    }

    public static void sendEmail(User user, int kod) {
        String textMessage = "Здравствуйте, " +
                user.getName() + "!\n" +
                "Для подтверждения адреса электронной почты перейдите по ссылке:\n" +
                "https://qtests.herokuapp.com/confirmEmail/?userId=" +
                user.getUserId() +
                "&key=" +
                user.getKey();

        String textMessage1 = "Здравствуйте, " +
                user.getName() + "!\n" +
                "Вы воспользовались процедурой восстановления логина в системе Тест\n" +
                "\n" +
                "Ваш логин: " + user.getLogin() + "\n" +
                "\n" +
                "Если Вы не обращались к процедуре восстановления пароля - просто проигнорируйте данное сообщение.\n" +
                "https://qtests.herokuapp.com/recovery/?userId=" +
                user.getUserId();

        final Properties properties = new Properties();
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(properties.getProperty("mail.smtps.user"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Test");

            if (kod == 1) {
                message.setText(textMessage);
            }
            if (kod == 2){
                message.setText(textMessage1);
            }

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

    public void changePassword(int id, String password) {
        password = (passwordEncoder.encode(password));
        userHiberDao.changePassword(id, password);
    }

    public User getUserByEmail(String email) {
       return userHiberDao.getUserByEmail(email);
    }
}

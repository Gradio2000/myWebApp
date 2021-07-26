package ru.laskin.myWebApp.service;

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

    public List<User> getAllUsers(int company_id) {
        return userHiberDao.getAllUsers(company_id);
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
                "Вы воспользовались процедурой восстановления пароля в системе QТест\n" +
                "\n" +
                "Ваш логин: " + user.getLogin() + "\n" +
                "\n" +
                "Если Вы не обращались к процедуре восстановления пароля - просто проигнорируйте данное сообщение.\n" +
                "Для восстановления пароля перейдите по ссылке https://qtests.herokuapp.com/recovery/?userId=" +
                user.getUserId() +  "&key=" + user.getKey();

        final Properties properties = new Properties();
        try {
            properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(properties.getProperty("mail.smtps.user"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("QTest регистрация/восстановление пароля");

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
       List<User> userList = userHiberDao.getUserByEmail(email);
       if (userList.isEmpty()){
           return null;
       }
       return userList.get(0);
    }

    public String prepareEmailTextForUser(String email) {
        User user = getUserByEmail(email);
        if (user == null){
            return  "Пользователь с email '" + email +"' не зарегистрирован в системе!";
        }
        else {
            if (user.isRegistered() == null || !user.isRegistered()){
                UserService.sendEmail(user, 1);
                return  "Пользователь с email '" + email +"' найден, но email не подтвержден! " +
                        "Мы повторно отправили Вам письмо для подтверждения email. Перейдите по ссылке в письме " +
                        "и после того, как подтвердите email повторно пройдите процедуру восстановления пароля!";
            }
            else {
                UserService.sendEmail(user, 2);
                return "На адрес Вашей электронной почты отправлено письмо, содержащее дальнейшие инструкции";
            }
        }
    }
}

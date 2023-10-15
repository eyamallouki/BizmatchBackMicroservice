package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository.IUserEmailRepository;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.User;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.UserMail;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Service
public class EmailServiceImpl implements IUserEmailRepository {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public void sendCodeByMail(UserMail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("douirifarouk3@gmail.com");
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject("Code Active");
        simpleMailMessage.setText(mail.getCode());
        javaMailSender.send(simpleMailMessage);
    }

    /*
    public void sendVerificationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUserEmail());
        user.setVerificationToken(verificationTokenService.generateVerificationToken());
        message.setSubject("Vérification du compte");
        message.setText("Bonjour " + user.getUserFirstName() + ",\n\n" +
                "Veuillez cliquer sur le lien ci-dessous pour activer votre compte :\n\n" +
                "http://localhost:4200/pages/verify/" + user.getVerificationToken());
        javaMailSender.send(message);
    }
*/
    public void sendVerificationEmail(User user) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject("Vérification du compte");
            helper.setTo(user.getUserEmail());

            // Charger le contenu du modèle HTML depuis une ressource de classe (classpath)
            ClassPathResource resource = new ClassPathResource("templates/email-template.html");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String htmlContent = new String(bytes, StandardCharsets.UTF_8);

            // Personnaliser le contenu HTML avec les informations de l'utilisateur
            htmlContent = htmlContent.replace("{{userName}}", user.getUserFirstName());

            // Remplacez également {{userFirstName}} par le prénom de l'utilisateur, si ce n'est pas déjà fait
            htmlContent = htmlContent.replace("{{userFirstName}}", user.getUserFirstName());

            // Remplacez {{verificationLink}} par le lien de vérification
            htmlContent = htmlContent.replace("{{verificationLink}}", "http://localhost:4200/pages/verify/" + user.getVerificationToken());

            helper.setText(htmlContent, true); // Le deuxième argument indique que c'est du HTML

            // Envoyer le courriel
            javaMailSender.send(message);
        } catch (Exception e) {
            // Gérer les exceptions
        }
    }



    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendCustomEmail(String to, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setTo(to);

            // Charger le contenu du modèle HTML depuis une ressource de classe (classpath)
            ClassPathResource resource = new ClassPathResource("templates/email-template.html");
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream); // Vous pouvez utiliser Apache Commons IO ou une autre bibliothèque pour la lecture
            String htmlContent = new String(bytes, StandardCharsets.UTF_8);

            helper.setText(htmlContent, true); // Le deuxième argument indique que c'est du HTML

            // Envoyer le courriel
            javaMailSender.send(message);
        } catch (Exception e) {
            // Gérer les exceptions
        }
    }

}

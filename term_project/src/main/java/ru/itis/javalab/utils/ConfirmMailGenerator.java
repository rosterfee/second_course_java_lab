package ru.itis.javalab.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConfirmMailGenerator {

    @Autowired
    Configuration configuration;

    @Value("${server.url}")
    private String serverUrl;

    public String getConfirmMail(String confirmCode) {

        Template template = null;
        try {
            template = configuration.getTemplate("confirm_mail.ftlh");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> model = new HashMap<>();
        model.put("server_url", serverUrl);
        model.put("confirm_code", confirmCode);

        StringWriter stringWriter = new StringWriter();

        try {
            template.process(model, stringWriter);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        System.out.println("письмо собрано");
        return stringWriter.toString();

    }

}

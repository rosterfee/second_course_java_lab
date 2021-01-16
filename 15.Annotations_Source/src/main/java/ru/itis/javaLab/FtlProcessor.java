package ru.itis.javaLab;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlFormAnnotation", "HtmlInputAnnotation"})
public class FtlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        List<HtmlForm> forms = new ArrayList<>();
        HtmlForm htmlForm;
        HtmlFormAnnotation htmlFormAnnotation;

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlFormAnnotation.class);
        for (Element element: annotatedElements) {
            htmlFormAnnotation = element.getAnnotation(HtmlFormAnnotation.class);
            htmlForm = HtmlForm.builder().
                    action(htmlFormAnnotation.action())
                    .method(htmlFormAnnotation.method())
                    .build();
            HtmlInput htmlInput;
            HtmlInputAnnotation htmlInputAnnotation;

            for (Element element1: element.getEnclosedElements()) {
                htmlInputAnnotation = element1.getAnnotation(HtmlInputAnnotation.class);
                htmlInput = HtmlInput.builder()
                        .type(htmlInputAnnotation.type())
                        .name(htmlInputAnnotation.name())
                        .placeholder(htmlInputAnnotation.placeholder())
                        .build();

                htmlForm.getInputs().add(htmlInput);
            }
            forms.add(htmlForm);
        }

        try {

            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources")));

            Template template = configuration.getTemplate("forms.ftl");

            String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + "forms.html";

            Map<String, Object> model = new HashMap<>();
            model.put("forms", forms);

            FileWriter fileWriter = new FileWriter(Paths.get(path).toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            template.process(model, bufferedWriter);

        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }

        return true;
    }
}

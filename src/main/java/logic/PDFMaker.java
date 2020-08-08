package logic;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PDFMaker {
    private static String parseThymeleafTemplate(User user) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("username", user.getFullName());
        context.setVariable("birthdate", user.getBirthday().toString());
        context.setVariable("email", user.getEmail());
        context.setVariable("message", user.getAdditionalMessage());

        return templateEngine.process("/pdf_template/PDFTemplate", context);
    }

    public static File generatePdfFromHtml(User user) throws DocumentException, IOException {

        File file = new File("output/" + user.getEmail() + ".pdf");
        if(file.exists())
            file.delete();
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(parseThymeleafTemplate(user));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
        return file;
    }
}

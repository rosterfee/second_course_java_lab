package java.ru.kpfu.itis.group905.kiyamdinov.app;

import com.beust.jcommander.Parameter;

import java.util.List;

public class Args {

    @Parameter(names = {"--mode"}, required = true)
    public String mode;

    @Parameter(names = {"--URLs"}, listConverter = UrlsConverter.class, required = true)
    public List<String> URLs;

    @Parameter(names = {"--path"}, required = true)
    public String path;

}

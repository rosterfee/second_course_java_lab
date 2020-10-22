package java.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageDownloader {

    public static void downloadImage(String URL, String path, String filename) throws IOException {

        URL url = new URL(URL);
        BufferedImage img = ImageIO.read(url);
        File file = new File(path + "/" + filename);
        ImageIO.write(img, "jpg", file);

    }
}

package image_cutter;

import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class photo_source {
    public static void main(String[] args) {
        // Getting the exact path for the folder and the destination folder
        File folder = new File("C:/Users/eliya/Desktop/shapes/circles");
        File destinationFolder = new File("C:/Users/eliya/Desktop/shapes/resized_circles");

        // Create the destination folder if it doesn't exist
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Find all image files (JPG and PNG)
        Collection<File> files = FileUtils.listFiles(folder, new String[]{"jpg", "png"}, false);

        // Iterate through each image file
        for (File file : files) {
            try {
                BufferedImage originalImage = ImageIO.read(file);

                // first we Resize the image
                BufferedImage resizedImage = resizeImage(originalImage, originalImage.getWidth() / 2, originalImage.getHeight() / 2);

                // then we Convert the resized image to grayscale
                BufferedImage grayscaleImage = convertToGrayscale(resizedImage);

                //and lastly we Save the grayscale image
                File newFile = new File(destinationFolder, file.getName());
                ImageIO.write(grayscaleImage, "jpg", newFile);

                System.out.println("Resized and converted to grayscale: " + file.getName());

            } catch (Exception e) {
                System.out.println("Error processing: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    // Function to resize an image
    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tempImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    //Function that convert to a gray colorway
    private static BufferedImage convertToGrayscale(BufferedImage colorImage) {
        BufferedImage grayImage = new BufferedImage(
                colorImage.getWidth(),
                colorImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY
        );

        Graphics2D g2d = grayImage.createGraphics();
        g2d.drawImage(colorImage, 0, 0, null);
        g2d.dispose();

    // eliya gvili adir bamarom 2025

        return grayImage;
    }
}

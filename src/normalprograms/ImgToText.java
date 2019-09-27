import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class ImgToText{
    private static double[][] pixels = null;
    private static StringBuilder sb = null;
    private static DecimalFormat formatter = new DecimalFormat("#0.00");
    private static char stringFetcher(double g){
        final char str;

		if (g >= 95.0) {
			str = ' ';
		} else if (g >= 90.0) {
			str = '.';
		} else if (g >= 85.0) {
			str = ',';
		} else if (g >= 80.0) {
			str = ':';
		} else if (g >= 75.0) {
			str = ';';
		} else if (g >= 70.0) {
			str = 'i';
		} else if (g >= 65.0) {
			str = 'r';
		} else if (g >= 60.0) {
			str = 't';
                } else if (g >= 55.0) {
                        str = 'j';
                } else if (g >= 50.0){
                        str = 'b';
                } else if (g >= 45.0){
                        str = 'd';
                } else if (g >= 40.0){
                        str = 'h';
                } else if (g >= 35.0){
                        str = '6';
                } else if (g >= 30.0){
                        str = '9';
                } else if (g >= 25.0){
                        str = '8';
                } else if (g >= 20.0){
                        str = 'B';
                } else if (g >= 15.0){
                        str = 'Q';
                } else if (g >= 10.0){
                        str = '&';
                } else if (g >= 5.0){
                        str = '#';
		} else {
			str = '@';
		}
		return str;
    }

    private static void SaveToFile(String sb){
            System.out.println(sb);
        File file = new File("Image.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(sb);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        private static BufferedImage resize(BufferedImage img, int height, int width) {
                Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resized.createGraphics();
                g2d.drawImage(tmp, 0, 0, null);
                g2d.dispose();
                return resized;
        }

    public static void main(String[] args) throws IOException {
        //getTextOutPut(new Image(ImgToText.class.getResource(args[0]).toExternalForm()));
                int width = 963;
                int height = 640; 
                BufferedImage image = null;
                BufferedImage img = null;
                        File input_file = new File(args[0]); // image file path
                        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                        image = ImageIO.read(input_file);
                        img = resize(image, image.getWidth()/2, image.getHeight()/2);
                        System.out.println("Reading complete. width - "+img.getWidth()+" height - "+img.getHeight());
                
                sb = new StringBuilder((img.getWidth() + 1) * img.getHeight());
                
                for (int y = 0; y < img.getHeight(); y++) {
                        if (sb.length() != 0)
                                sb.append("\n");
                        for (int x = 0; x < img.getWidth(); x++) {
                                int c = img.getRGB(x, y);
                                int r = (c >> 24) & 0xFF;
                                int g = (c >> 16) & 0xFF;
                                int b = (c >> 8) & 0xFF;
                                double avg = (r+g+b)/3;
                                sb.append(stringFetcher(
                                                Double.parseDouble(formatter.format((avg*Double.parseDouble(args[1])/255))) * 100));
                        }
                }
                SaveToFile(sb.toString());

    }
}
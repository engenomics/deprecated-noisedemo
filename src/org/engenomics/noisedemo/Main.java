package org.engenomics.noisedemo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        // Always a 14x28 pixel image
        BufferedImage k = ImageIO.read(new File("empty.png"));

        int[] pattern = new int[]{Color.RED.getRGB(), Color.RED.getRGB(), Color.GREEN.getRGB(), Color.YELLOW.getRGB(), Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.BLUE.getRGB(), Color.BLUE.getRGB(), Color.RED.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB(), Color.YELLOW.getRGB(), Color.GREEN.getRGB(), Color.GREEN.getRGB(), Color.BLUE.getRGB(), Color.RED.getRGB(), Color.YELLOW.getRGB(), Color.YELLOW.getRGB()};


        int c = 0;
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 28; j++) {
                int r = ThreadLocalRandom.current().nextInt(1, 100 + 1); // A number in {1, 2, 3, 4, ..., 99, 100}
                if (r == 6) {
                    k.setRGB(i, j, randomColor());
                    c++;
                } else if (r == 7) {
                    c--;
                } else if (r == 8) {
                    c += ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    c %= pattern.length;
                }


                c++;
                if (c >= pattern.length) {
                    c = 0;
                }

                k.setRGB(i, j, pattern[c]);
            }
        }


        ImageIO.write(k, "png", new File("result_noise3.png"));
    }

    private int randomColor() {
        int[] colors = new int[] {Color.RED.getRGB(), Color.GREEN.getRGB(), Color.YELLOW.getRGB(), Color.BLUE.getRGB()};
        return colors[ThreadLocalRandom.current().nextInt(0, 3 + 1)];
    }
}

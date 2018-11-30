package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Image;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.MultiResolutionImage;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 显示多分辨率的图片
 *
 * @author Watony Weng
 * @date 2018-11-30
 */

public class MultiSolutionImage {

    private static final Logger logger = LoggerFactory.getLogger(MultiSolutionImage.class);

    public static void main(String[] args) throws IOException {

        List<String> imgUrls = List
                .of("http://www.runoob.com/wp-content/themes/runoob/assets/img/runoob-logo@2x.png",
                        "http://www.runoob.com/wp-content/themes/runoob/assets/img/runoob-logo.png",
                        "http://www.runoob.com/wp-content/themes/runoob/assets/images/qrcode.png");

        List<Image> images = new ArrayList<>();
        for (String url : imgUrls) {
            images.add(ImageIO.read(new URL(url)));
        }

        // 读取所有图片
        MultiResolutionImage multiResolutionImage =
                new BaseMultiResolutionImage(images.toArray(new Image[0]));

        // 获取图片的所有分辨率
        List<Image> variants = multiResolutionImage.getResolutionVariants();
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Total number of images: {0}", variants.size()));
        }

        for (Image img : variants) {
            logger.info(MessageFormat.format("{0}", img));
        }

        // 根据不同尺寸获取对应的图像分辨率
        Image variant1 = multiResolutionImage.getResolutionVariant(156, 45);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Image for destination [{0},{1}] : [{2},{3}]", 156, 45,
                    variant1.getWidth(null), variant1.getHeight(null)));
        }

        Image variant2 = multiResolutionImage.getResolutionVariant(311, 89);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Image for destination [{0},{1}] : [{2},{3}]", 311, 89,
                    variant2.getWidth(null), variant2.getHeight(null)));
        }

        Image variant3 = multiResolutionImage.getResolutionVariant(622, 178);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Image for destination [{0},{1}] : [{2},{3}]", 622, 178,
                    variant3.getWidth(null), variant3.getHeight(null)));
        }

        Image variant4 = multiResolutionImage.getResolutionVariant(300, 300);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Image for destination [{0},{1}] : [{2},{3}]", 300, 300,
                    variant4.getWidth(null), variant4.getHeight(null)));
        }

    }
}

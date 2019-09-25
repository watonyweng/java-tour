package me.weitao.java.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.UUID;

/**
 * Base64编码
 *
 * @author Watony Weng
 * @date 2018/12/01
 */

public class Base64App {

    private static final Logger logger = LoggerFactory.getLogger(Base64App.class);

    public static void main(String[] args) {
        // 使用基本编码
        String base64encodedString = Base64.getEncoder()
                .encodeToString("Google?java8".getBytes(StandardCharsets.UTF_8));
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Base64字符串(基本): {0}", base64encodedString));
        }

        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("原始字符串: {0}", new String(base64decodedBytes, StandardCharsets.UTF_8)));
        }
        // 编码
        base64encodedString = Base64.getUrlEncoder()
                .encodeToString("Tutorials?java8".getBytes(StandardCharsets.UTF_8));
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Base64编码字符串(URL): {0}", base64encodedString));
        }

        // 生成随机字符串
        String random = UUID.randomUUID().toString();
        byte[] mimeBytes = random.getBytes(StandardCharsets.UTF_8);
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format("Base64编码字符串(MIME): {0}", mimeEncodedString));
        }
    }

}
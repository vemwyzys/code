package cn.gzho.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOTest {

    private static int MAX_SIZE_IN_MEMORY = 1024 * 1024 * 10;

    @Test
    public void testIo() throws IOException {
        Path tempFile = Files.createTempFile(null, null);
        Path fileName = tempFile.getFileName();
        System.out.println(fileName);
        tempFile.toFile().deleteOnExit();

        InputStream inputStream = IOTest.class.getClassLoader().getResourceAsStream("data");
        OutputStream outputStream = Files.newOutputStream(tempFile);

        InputStreamReader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        OutputStreamWriter out = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        int bytesRead, sizeInMemory = 0;
        char[] cbuf = new char[2048];
        while ((bytesRead = in.read(cbuf, 0, cbuf.length)) != -1) {
            sizeInMemory += bytesRead;
            out.write(cbuf, 0, bytesRead);
        }
    }

    @Test
    public void testLineFeedAndCarriageReturn() throws IOException {
        byte[] bytes = "dslfjeiofjsadfj\nldfsjeifjlsdakfj\rdslfjeifjsoaf\r\ndlsfjdisfoej".getBytes();

        Path tempFile = Files.createTempFile(null, null);
        tempFile.toFile().deleteOnExit();
        OutputStream outputStream = Files.newOutputStream(tempFile);
        outputStream.write(bytes);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile.toFile()));
        int num = 0;
        while (bufferedReader.readLine() != null) {
            num++;
        }
        System.out.println(num);
    }

    public static void main(String[] args) {
        char[] chars = "password".toCharArray();
        byte[] bytes = new byte[chars.length * 2];
        for (int i = 0; i < chars.length; i++) {
            bytes[i * 2] = (byte) (chars[i] >> 8);
            bytes[i * 2 + 1] = (byte) chars[i];
        }
        char[] chars2 = new char[bytes.length / 2];
        for (int i = 0; i < chars2.length; i++) {
//            chars2[i] = (char) ((bytes[i * 2] << 8) + (bytes[i * 2 + 1] & 0xFF));
            chars2[i] = (char) (((bytes[i * 2] & 0xff) << 8) + (bytes[i * 2 + 1] & 0xff));
        }
        String password = new String(chars2);
        System.out.println(password);
    }
}

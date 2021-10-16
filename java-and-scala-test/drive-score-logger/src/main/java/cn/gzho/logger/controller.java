package cn.gzho.logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-07 7:49 AM
 */
@RestController
public class controller {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/file")
    public void upload(@RequestBody MultipartFile file) {
        String str = null;
        try {
            str = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send("drive-log", str);
    }

    @GetMapping("/send")
    public void upload1() throws IOException {
//        String path= "/Users/ameng/GitHub/codeup/carlinx-drive-score/行程数据/1014.txt";
        String path= "/Users/ameng/GitHub/codeup/carlinx-drive-score/行程数据/24晚马哥.txt";
        String str =FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        kafkaTemplate.send("drive-log", str);
    }

    public static void main(String[] args) throws IOException {
        String str = "/Users/ameng/GitHub/codeup/carlinx-drive-score/行程数据/24晚马哥.txt";
        System.out.println(FileUtils.readFileToString(new File(str), StandardCharsets.UTF_8));
    }
}

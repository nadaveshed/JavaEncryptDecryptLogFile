package com.example.encryptdecryptlogfile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class FileEncryptorController {

    //url example: http://localhost:8080/
    @GetMapping("/")
    public String getItem() throws IOException {
        final String secretKey = "secretKey!";

        File f = new File("src/main/java/com/example/encryptdecryptlogfile/input.log");
        File encryptFile = new File("src/main/java/com/example/encryptdecryptlogfile/output.log");
        FileWriter keyStream = new FileWriter(encryptFile);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println("Encrypt: " + line);
            keyStream.write(AES.encrypt(line, secretKey));
        }

        keyStream.close();

        BufferedReader b= new BufferedReader(new FileReader(encryptFile));
        String line1;

        while ((line1 = b.readLine()) != null) {
            System.out.println("Decrypt: " + AES.decrypt(line1, secretKey));
        }
        return "Finish Encrypt Log File";
    }
}

package com.example.nfc.secondary;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

@Service("encoder")
public class EncodePass {

    private String _passDirectory;

    public void setPassDirectory(String passDirectory) {
        _passDirectory = passDirectory;
    }

    public String getPassDirectory() {
        return _passDirectory;
    }

    public String calculateSHA(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
        try (InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                sha1Digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = sha1Digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : hashBytes) {
            sb.append(String.format("%02x", hashByte));
        }
        return sb.toString();
    }

    public void createManifest() {
        String directoryString = getPassDirectory();
        File directory = new File(directoryString);
        try {
            String manifestFilePath = directoryString + "/manifest.json";
            FileWriter manifestWriter = new FileWriter(manifestFilePath);
            PrintWriter manifestPrinter = new PrintWriter(manifestWriter);

            manifestPrinter.println("{");

            File[] files = directory.listFiles();
            if (files != null) {
                boolean isFirstFile = true;
                for (File file : files) {
                    if (file.isFile() && (!file.getName().equals("manifest.json")) && (!file.getName().equals("signature")) && (!file.getName().equals("rhombus.pkpass"))) {
                        String filePath = file.getName();
                        String sha1Hash = calculateSHA(file);
                        if (file.getName().equals(".DS_Store")) {
                            continue;
                        } else {
                            if (!isFirstFile) {
                                manifestPrinter.println(",");
                            }
                            manifestPrinter.print("  \"" + filePath + "\" : \"" + sha1Hash + "\"");
                            isFirstFile = false;
                        }
                    }
                }
            }

            manifestPrinter.println();
            manifestPrinter.print("}");

            manifestPrinter.close();
            System.out.println("Manifest file generated successfully.");
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

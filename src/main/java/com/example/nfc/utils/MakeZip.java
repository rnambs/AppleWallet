package com.example.nfc.secondary;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.zip.*;

import org.springframework.core.io.InputStreamResource;

@Component("toZip")
public class MakeZip {
    
    private String pathToFiles;

    public MakeZip() {
        this.pathToFiles = "path/to/directory";
    }

    public String getPathToFiles() {
        return this.pathToFiles;
    }

    public void setPathToFiles(String pathToFiles) {
        this.pathToFiles = pathToFiles;
    }

    public InputStreamResource generateZip() {
        String sourceFolderPath = getPathToFiles();
        System.out.println("Directory path is: " + sourceFolderPath);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zipOut = new ZipOutputStream(baos)) {

            File sourceFolder = new File(sourceFolderPath);
            if (!sourceFolder.isDirectory()) {
                throw new FileNotFoundException("Source folder not found.");
            }

            zipDirectory(sourceFolder, "", zipOut);
            zipOut.finish();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            return new InputStreamResource(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions here
            return null;
        }
    }

    public void zipDirectory(File folderToZip, String folderName, ZipOutputStream zipOut) throws IOException {
        if (folderToZip.isHidden()) {
            return;
        }

        if (folderToZip.isDirectory()) {
            if (folderName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(folderName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(folderName + "/"));
                zipOut.closeEntry();
            }

            File[] children = folderToZip.listFiles();
            for (File childFile : children) {
                zipDirectory(childFile, folderName + "/" + childFile.getName(), zipOut);
            }
        } else {
            FileInputStream fis = new FileInputStream(folderToZip);
            ZipEntry zipEntry = new ZipEntry(folderName);
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }

            fis.close();
        }
    }
}

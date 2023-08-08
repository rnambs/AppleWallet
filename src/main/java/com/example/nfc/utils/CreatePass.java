package com.example.nfc.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import javax.activation.FileDataSource;

@Component
public class CreatePass {

    @Autowired
    public EncodePass encoder;

    // makezip object that is created for this specific instance
    @Autowired
    public MakeZip toZip;

    @Autowired
    public SignPass signer;

    @Autowired
    PassJsonGenerator createPassJsonFile;

    public InputStreamResource createPKPassFile() throws IOException {
        Path tempDir = null;
        try {
            tempDir = Files.createTempDirectory("pass");
            createPassJsonFile.createPassDotJson();
            encoder.setPassDirectory(tempDir.toString());
            encoder.createManifest();
            signer.setKeyStore(new FileDataSource("/path/to/keystore.jks"));
            signer.setKeyStoreType("JKS");
            signer.setKeyStorePassword("password".toCharArray());
            signer.setKeyName("key");
            signer.setCert(new FileDataSource("/path/to/signingCertificate.pem"));
            signer.setCa(new FileDataSource("/path/to/WWDR.pem"));
            byte[] res = signer.readManifestAsByteArray("/path/to/manifest.json");
            signer.createSignatureFile("/path/to/signature", res);
            toZip.setPathToFiles("/path/to/pass_directory");
            return toZip.generateZip();
        } catch (IOException e) {
            throw new Error(e);
        } finally {
            if (tempDir != null) {
                try {
                    Files.walk(tempDir)
                        .sorted((path1, path2) -> -path1.compareTo(path2))
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
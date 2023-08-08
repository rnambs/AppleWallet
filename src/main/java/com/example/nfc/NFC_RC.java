package com.example.nfc;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import com.example.nfc.secondary.CreatePass;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class NFC_RC {

    @Autowired
    public CreatePass resource;

    @PostMapping
	@RequestMapping("/pass")
    public ResponseEntity<InputStreamResource> handlePassUpload() throws IOException {
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.apple.pkpass"));
		String outputFileName = "rhombus.pkpass";
		headers.setContentDispositionFormData(outputFileName, outputFileName);
		headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setPragma("no-cache");
        headers.setExpires(0);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource.createPKPassFile());
    }
}


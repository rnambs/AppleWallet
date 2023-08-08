package com.example.nfc.secondary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PassJsonGenerator {
    public void createPassDotJson() {
        PassUpdater pass = new PassUpdater();
        pass.setDescription(pass.getDescription());
        pass.setFormatVersion(pass.getFormatVersion());
        pass.setOrganizationName(pass.getOrganizationName());
        pass.setPassTypeIdentifier(pass.getPassTypeIdentifier());
        pass.setSerialNumber(pass.getSerialNumber());
        pass.setTeamIdentifier(pass.getTeamIdentifier());
        pass.setBackgroundColor(pass.getBackgroundColor());
        pass.setForegroundColor(pass.getForegroundColor());
        pass.setLabelColor(pass.getLabelColor());

        // inside generic class
        PassUpdater.GenericData genericData = new PassUpdater.GenericData();
        List<PassUpdater.HeaderField> headerFields = new ArrayList<>();
        PassUpdater.HeaderField headerField = new PassUpdater.HeaderField();
        headerField.setKey();
        headerField.setLabel("STATUS");
        headerField.setValue("Active");
        headerField.setTextAlignment("PKTextAlignmentCenter");
        headerFields.add(headerField);

        List<PassUpdater.PrimaryField> primaryFields = new ArrayList<>();
        PassUpdater.PrimaryField primaryField = new PassUpdater.PrimaryField();
        primaryField.setKey("user-name");
        primaryField.setValue("RAHUL NAMBIAR");
        primaryField.setTextAlignment("PKTextAlignmentCenter");
        primaryFields.add(primaryField);

        List<PassUpdater.AuxiliaryField> auxiliaryFields = new ArrayList<>();
        PassUpdater.AuxiliaryField auxiliaryField = new PassUpdater.AuxiliaryField();
        auxiliaryField.setKey("org");
        auxiliaryField.setLabel("ORGANIZATION");
        auxiliaryField.setValue("Organization");
        auxiliaryField.setTextAlignment("PKTextAlignmentLeft");
        auxiliaryFields.add(auxiliaryField);

        List<PassUpdater.BackField> backFields = new ArrayList<>();
        PassUpdater.BackField backField1 = new PassUpdater.BackField();
        backField1.setKey("name");
        backField1.setLabel("Name");
        backField1.setValue("Rahul Nambiar");
        backFields.add(backField1);

        PassUpdater.BackField backField2 = new PassUpdater.BackField();
        backField2.setKey("org");
        backField2.setLabel("Organization");
        backField2.setValue("Organization");
        backFields.add(backField2);

        genericData.setHeaderFields(headerFields);
        genericData.setPrimaryFields(primaryFields);
        genericData.setAuxiliaryFields(auxiliaryFields);
        genericData.setBackFields(backFields);

        pass.setGeneric(genericData);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            objectWriter.writeValueAsString(pass);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            File outputFile = new File("path/to/pass.json");
            objectMapper.writeValue(outputFile, pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.nfc.secondary;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
// POJO for Jackson JSON serialization
@Component
public class PassUpdater {
    private String description;
    private int formatVersion;
    private String organizationName;
    private String passTypeIdentifier;
    private String serialNumber;
    private String teamIdentifier;
    private String backgroundColor;
    private String foregroundColor;
    private String labelColor;
    private GenericData generic;


    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("formatVersion")
    public int getFormatVersion() {
        return formatVersion;
    }

    @JsonProperty("formatVersion")
    public void setFormatVersion(int formatVersion) {
        this.formatVersion = formatVersion;
    }

    @JsonProperty("organizationName")
    public String getOrganizationName() {
        return organizationName;
    }

    @JsonProperty("organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @JsonProperty("passTypeIdentifier")
    public String getPassTypeIdentifier() {
        return passTypeIdentifier;
    }

    @JsonProperty("passTypeIdentifier")
    public void setPassTypeIdentifier(String passTypeIdentifier) {
        this.passTypeIdentifier = passTypeIdentifier;
    }

    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("teamIdentifier")
    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    @JsonProperty("teamIdentifier")
    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    @JsonProperty("backgroundColor")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @JsonProperty("foregroundColor")
    public String getForegroundColor() {
        return foregroundColor;
    }

    @JsonProperty("foregroundColor")
    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    @JsonProperty("labelColor")
    public String getLabelColor() {
        return labelColor;
    }

    @JsonProperty("labelColor")
    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    @JsonProperty("generic")
    public GenericData getGeneric() {
        return generic;
    }

    @JsonProperty("generic")
    public void setGeneric(GenericData generic) {
        this.generic = generic;
    }

    // Nested class to represent "generic" data
    public static class GenericData {
        private List<HeaderField> headerFields;
        private List<PrimaryField> primaryFields;
        private List<AuxiliaryField> auxiliaryFields;
        private List<BackField> backFields;

        @JsonProperty("headerFields")
        public List<HeaderField> getHeaderFields() {
            return headerFields;
        }

        @JsonProperty("headerFields")
        public void setHeaderFields(List<HeaderField> headerFields) {
            this.headerFields = headerFields;
        }

        @JsonProperty("primaryFields")
        public List<PrimaryField> getPrimaryFields() {
            return primaryFields;
        }

        @JsonProperty("primaryFields")
        public void setPrimaryFields(List<PrimaryField> primaryFields) {
            this.primaryFields = primaryFields;
        }

        @JsonProperty("auxiliaryFields")
        public List<AuxiliaryField> getAuxiliaryFields() {
            return auxiliaryFields;
        }

        @JsonProperty("auxiliaryFields")
        public void setAuxiliaryFields(List<AuxiliaryField> auxiliaryFields) {
            this.auxiliaryFields = auxiliaryFields;
        }

        @JsonProperty("backFields")
        public List<BackField> getBackFields() {
            return backFields;
        }

        @JsonProperty("backFields")
        public void setBackFields(List<BackField> backFields) {
            this.backFields = backFields;
        }
    }

    public static class HeaderField {
        private String key;
        private String label;
        private String value;
        private String textAlignment;

        public String getKey()
        {
            return key;
        }
        public void setKey(String key)
        {
            this.key = key;
        }
        public String getLabel()
        {
            return label;
        }
        public void setLabel(String label)
        {
            this.label = label;
        }
        public String getValue()
        {
            return value;
        }
        public void setValue(String value)
        {
            this.value = value;
        }
        public String getTextAlignment()
        {
            return textAlignment;
        }
        public void setTextAlignment(String textAlignment)
        {
            this.textAlignment = textAlignment;
        }
    }

    public static class PrimaryField {
        private String key;
        private String value;
        private String textAlignment;

        public String getKey()
        {
            return key;
        }
        public void setKey(String key)
        {
            this.key = key;
        }
        public String getValue()
        {
            return value;
        }
        public void setValue(String value)
        {
            this.value = value;
        }
        public String getTextAlignment()
        {
            return textAlignment;
        }
        public void setTextAlignment(String textAlignment)
        {
            this.textAlignment = textAlignment;
        }
        
    }

    public static class AuxiliaryField {
        private String key;
        private String label;
        private String value;
        private String textAlignment;

        public String getKey()
        {
            return key;
        }
        public void setKey(String key)
        {
            this.key = key;
        }
        public String getLabel()
        {
            return label;
        }
        public void setLabel(String label)
        {
            this.label = label;
        }
        public String getValue()
        {
            return value;
        }
        public void setValue(String value)
        {
            this.value = value;
        }
        public String getTextAlignment()
        {
            return textAlignment;
        }
        public void setTextAlignment(String textAlignment)
        {
            this.textAlignment = textAlignment;
        }
        
    }

    public static class BackField {
        private String key;
        private String label;
        private String value;

        public String getKey()
        {
            return key;
        }
        public void setKey(String key)
        {
            this.key = key;
        }
        public String getLabel()
        {
            return label;
        }
        public void setLabel(String label)
        {
            this.label = label;
        }
        public String getValue()
        {
            return value;
        }
        public void setValue(String value)
        {
            this.value = value;
        }
    }
}

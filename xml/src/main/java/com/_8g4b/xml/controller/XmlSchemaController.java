package com._8g4b.xml.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._8g4b.xml.service.XmlValidationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/xml")
@RequiredArgsConstructor
public class XmlSchemaController {

    private final XmlValidationService xmlValidationService;

    @GetMapping(value = "/schema", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXmlSchema() {
        String schema = xmlValidationService.getBookXmlSchema();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(schema);
    }

    @PostMapping(value = "/validate", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<ValidationResponse> validateXml(@RequestBody String xmlContent) {
        String xsd = xmlValidationService.getBookXmlSchema();
        boolean isValid = xmlValidationService.validateXmlAgainstXsd(xmlContent, xsd);

        return ResponseEntity.ok(new ValidationResponse(isValid, isValid ? "XML is valid" : "XML validation failed"));
    }

    public record ValidationResponse(boolean valid, String message) {
    }
}

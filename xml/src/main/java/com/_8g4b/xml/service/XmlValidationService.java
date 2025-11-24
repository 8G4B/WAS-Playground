package com._8g4b.xml.service;

import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class XmlValidationService {

    public boolean validateXmlAgainstXsd(String xmlContent, String xsdContent) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new StringReader(xsdContent)));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xmlContent)));
            log.info("XML validation successful");
            return true;
        } catch (Exception e) {
            log.error("XML validation failed: {}", e.getMessage());
            return false;
        }
    }

    public String getBookXmlSchema() {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
                           targetNamespace="http://www.8g4b.com/xml/books"
                           xmlns:tns="http://www.8g4b.com/xml/books"
                           elementFormDefault="qualified">

                    <xs:element name="book" type="tns:BookType"/>

                    <xs:complexType name="BookType">
                        <xs:sequence>
                            <xs:element name="title" type="xs:string"/>
                            <xs:element name="author" type="xs:string"/>
                            <xs:element name="isbn" type="xs:string" minOccurs="0"/>
                            <xs:element name="price" type="xs:double"/>
                        </xs:sequence>
                        <xs:attribute name="id" type="xs:long"/>
                    </xs:complexType>

                    <xs:element name="books" type="tns:BookListType"/>

                    <xs:complexType name="BookListType">
                        <xs:sequence>
                            <xs:element name="book" type="tns:BookType" minOccurs="0" maxOccurs="unbounded"/>
                        </xs:sequence>
                    </xs:complexType>

                </xs:schema>
                """;
    }
}

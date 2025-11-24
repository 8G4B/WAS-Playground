package com._8g4b.xml.service;

import java.io.StringReader;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com._8g4b.xml.model.Book;
import com._8g4b.xml.model.BookList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class XmlMarshallingService {

    private final JAXBContext bookContext;
    private final JAXBContext bookListContext;

    public XmlMarshallingService() throws JAXBException {
        this.bookContext = JAXBContext.newInstance(Book.class);
        this.bookListContext = JAXBContext.newInstance(BookList.class);
    }

    public String marshalBook(Book book) {
        try {
            StringWriter writer = new StringWriter();
            Marshaller marshaller = bookContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(book, writer);
            return writer.toString();
        } catch (JAXBException e) {
            log.error("Failed to marshal Book: {}", e.getMessage());
            throw new RuntimeException("XML marshalling failed", e);
        }
    }

    public Book unmarshalBook(String xmlContent) {
        try {
            Unmarshaller unmarshaller = bookContext.createUnmarshaller();
            return (Book) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (JAXBException e) {
            log.error("Failed to unmarshal Book: {}", e.getMessage());
            throw new RuntimeException("XML unmarshalling failed", e);
        }
    }

    public String marshalBookList(BookList bookList) {
        try {
            StringWriter writer = new StringWriter();
            Marshaller marshaller = bookListContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(bookList, writer);
            return writer.toString();
        } catch (JAXBException e) {
            log.error("Failed to marshal BookList: {}", e.getMessage());
            throw new RuntimeException("XML marshalling failed", e);
        }
    }

    public BookList unmarshalBookList(String xmlContent) {
        try {
            Unmarshaller unmarshaller = bookListContext.createUnmarshaller();
            return (BookList) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (JAXBException e) {
            log.error("Failed to unmarshal BookList: {}", e.getMessage());
            throw new RuntimeException("XML unmarshalling failed", e);
        }
    }
}

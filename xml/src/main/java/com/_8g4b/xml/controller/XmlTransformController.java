package com._8g4b.xml.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._8g4b.xml.model.Book;
import com._8g4b.xml.model.BookList;
import com._8g4b.xml.service.XmlMarshallingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/xml/transform")
@RequiredArgsConstructor
public class XmlTransformController {

    private final XmlMarshallingService xmlMarshallingService;

    @PostMapping(value = "/marshal/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> marshalBook(@RequestBody Book book) {
        String xml = xmlMarshallingService.marshalBook(book);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xml);
    }

    @PostMapping(value = "/unmarshal/book", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> unmarshalBook(@RequestBody String xmlContent) {
        Book book = xmlMarshallingService.unmarshalBook(xmlContent);
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/marshal/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> marshalBookList(@RequestBody BookList bookList) {
        String xml = xmlMarshallingService.marshalBookList(bookList);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(xml);
    }

    @PostMapping(value = "/unmarshal/books", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookList> unmarshalBookList(@RequestBody String xmlContent) {
        BookList bookList = xmlMarshallingService.unmarshalBookList(xmlContent);
        return ResponseEntity.ok(bookList);
    }
}

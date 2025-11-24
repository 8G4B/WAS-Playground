package com._8g4b.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "book")
@XmlRootElement(name = "book", namespace = "http://www.8g4b.com/xml/books")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "author", "isbn", "price"})
public class Book {

    @JacksonXmlProperty(isAttribute = true)
    @XmlAttribute(name = "id")
    private Long id;

    @NotBlank(message = "Title is required")
    @JacksonXmlProperty(localName = "title")
    @XmlElement(name = "title", required = true)
    private String title;

    @NotBlank(message = "Author is required")
    @JacksonXmlProperty(localName = "author")
    @XmlElement(name = "author", required = true)
    private String author;

    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "Invalid ISBN format")
    @JacksonXmlProperty(localName = "isbn")
    @XmlElement(name = "isbn")
    private String isbn;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @JacksonXmlProperty(localName = "price")
    @XmlElement(name = "price", required = true)
    private Double price;
}

package com.subham.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "anm")
public class Anm {
    @Id
    @GeneratedValue
    private int id;

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", docName='" + docName + '\'' +
                ", docType='" + docType + '\'' +
                ", price='" + price + '\'' +
                ", content='" + content + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @NotNull

    private String bookName;
    @NotNull

    private String authorName;

    private String docName;


    public Anm(int id, String bookName, String authorName, String docName, String docType, String price, String content, byte[] data) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.docName = docName;
        this.docType = docType;
        this.price = price;
        this.content = content;
        this.data = data;
    }


    private String docType;
    @NotNull
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    private String content;
    @Lob
    private byte[] data;





    public Anm() {

    }

    public Anm(String docname, String contentType, byte[] bytes) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setAuthorName() {
    }

    public void setBookName() {
    }

    public void setContent() {
    }

    public void setData() {
    }

    public void setDocName() {
    }

    public void setPrice() {
    }
}

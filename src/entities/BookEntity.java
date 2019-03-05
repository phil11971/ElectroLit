package entities;

import java.math.BigDecimal;
import java.util.Currency;

public class BookEntity {
    private int id_b;
    private String name;
    private int year_pub;
    private int cnt;
    private BigDecimal price;
    private int id_po;

    public BookEntity() {
    }

    public BookEntity(int id_b, String name, int year_pub, int cnt, BigDecimal price, int id_po) {
        this.id_b = id_b;
        this.name = name;
        this.year_pub = year_pub;
        this.cnt = cnt;
        this.price = price;
        this.id_po = id_po;
    }

    public int getId_b() {
        return id_b;
    }

    public void setId_b(int id_b) {
        this.id_b = id_b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_pub() {
        return year_pub;
    }

    public void setYear_pub(int year_pub) {
        this.year_pub = year_pub;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId_po() {
        return id_po;
    }

    public void setId_po(int id_po) {
        this.id_po = id_po;
    }
}

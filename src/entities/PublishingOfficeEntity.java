package entities;

public class PublishingOfficeEntity {
    private int id_po;
    private String name;
    private String legal_adr;

    public PublishingOfficeEntity() {
    }

    public PublishingOfficeEntity(int id_po) {
        this.id_po = id_po;
    }

    public PublishingOfficeEntity(int id_po, String name, String legal_adr) {
        this.id_po = id_po;
        this.name = name;
        this.legal_adr = legal_adr;
    }

    public int getId_po() {
        return id_po;
    }

    public void setId_po(int id_po) {
        this.id_po = id_po;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegal_adr() {
        return legal_adr;
    }

    public void setLegal_adr(String legal_adr) {
        this.legal_adr = legal_adr;
    }
}

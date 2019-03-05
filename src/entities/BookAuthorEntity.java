package entities;

public class BookAuthorEntity {
    private int id_b;
    private int id_a;

    public BookAuthorEntity() {
    }

    public BookAuthorEntity(int id_b, int id_a) {
        this.id_b = id_b;
        this.id_a = id_a;
    }

    public int getId_b() {
        return id_b;
    }

    public void setId_b(int id_b) {
        this.id_b = id_b;
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }
}

package entities;

public class ChapterEntity {
    private int id_c;
    private String name;
    private int id_b;

    public ChapterEntity() {
    }

    public ChapterEntity(int id_c) {
        this.id_c = id_c;
    }

    public ChapterEntity(String name, int id_b) {
        this.name = name;
        this.id_b = id_b;
    }

    public ChapterEntity(int id_c, String name, int id_b) {
        this.id_c = id_c;
        this.name = name;
        this.id_b = id_b;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_b() {
        return id_b;
    }

    public void setId_b(int id_b) {
        this.id_b = id_b;
    }
}

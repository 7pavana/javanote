public class Note {
    private int id;
    private String title;
    private String content;

    // Constructor to initialize note with id, title, and content
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Title
    public String getTitle() {
        return title;
    }

    // Getter for Content
    public String getContent() {
        return content;
    }

    // Override toString for displaying notes in the list
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title;
    }
}
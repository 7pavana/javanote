import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Note class to represent a note
class Note {
    private int id;
    private String title;
    private String content;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return title; // Display title in the JList
    }

    // Getters for id, title, and content can be added if needed
}

public class NoteManagerApp {

    private DefaultListModel<Note> noteListModel;
    private JList<Note> noteList;
    private JTextField titleField;
    private JTextArea contentArea;
    private int nextId = 1;
    private JFrame frame;

    public NoteManagerApp() {
        frame = new JFrame("Note Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        // Set the background color of the main frame to black
        frame.getContentPane().setBackground(Color.BLACK);

        noteListModel = new DefaultListModel<>();
        noteList = new JList<>(noteListModel);

        // Set up text fields with black background and gold text
        titleField = new JTextField(20);
        contentArea = new JTextArea(5, 20);

        titleField.setBackground(Color.BLACK);
        titleField.setForeground(Color.CYAN);
        titleField.setCaretColor(Color.CYAN); // Change caret (text cursor) color

        contentArea.setBackground(Color.BLACK);
        contentArea.setForeground(Color.CYAN);
        contentArea.setCaretColor(Color.CYAN); // Change caret color

        setupUI();
        frame.setVisible(true);
    }

    private void setupUI() {
        // Panel for creating notes
        JPanel createPanel = new JPanel(new BorderLayout());
        createPanel.setBackground(Color.BLACK);
        createPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN, 2), "Create a New Note", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.CYAN));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.setForeground(Color.CYAN);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);
        contentPanel.add(new JLabel("Content:"), BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        createPanel.add(inputPanel, BorderLayout.NORTH);
        createPanel.add(contentPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Note");
        addButton.setBackground(Color.CYAN);
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNote();
            }
        });
        createPanel.add(addButton, BorderLayout.SOUTH);

        // Panel for viewing and deleting notes
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBackground(Color.BLACK);
        viewPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN, 2), "Your Notes", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.CYAN));

        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteList.setBackground(Color.BLACK);
        noteList.setForeground(Color.CYAN);
        noteList.setSelectionBackground(Color.CYAN); // Highlight selected item with gold
        noteList.setSelectionForeground(Color.BLACK); // Text of selected item in black

        viewPanel.add(new JScrollPane(noteList), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected Note");
        deleteButton.setBackground(Color.CYAN);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
        viewPanel.add(deleteButton, BorderLayout.SOUTH);

        // Main panel layout
        frame.setLayout(new BorderLayout());
        frame.add(createPanel, BorderLayout.WEST);
        frame.add(viewPanel, BorderLayout.CENTER );
    }

    private void createNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        if (!title.isEmpty() && !content.isEmpty()) {
            Note newNote = new Note(nextId++, title, content);
            noteListModel.addElement(newNote);
            titleField.setText("");
            contentArea.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Title and content cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteNote() {
        int selectedIndex = noteList.getSelectedIndex();
        if (selectedIndex != -1) {
            noteListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a note to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NoteManagerApp());
    }
}
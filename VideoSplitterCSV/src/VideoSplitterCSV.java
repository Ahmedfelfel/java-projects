
/**
 * The `VideoSplitterCSV` class in Java sets up a GUI application for splitting videos based on
 * user-provided timestamps loaded from a CSV file.
 */

// The `import` statements at the beginning of a Java file are used to bring in external classes and
// packages that are needed in the current Java file. Here's a breakdown of what each of the imports in
// the provided code snippet is doing:
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VideoSplitterCSV {
// These lines of code are declaring private static fields in the `VideoSplitterCSV` class in Java:

    private static JTextField videoPathField;
    private static JTextField outputFolderField;
    private static JPanel segmentsPanel;
    private static List<SegmentInput> segmentInputs = new ArrayList<>();

    /**
     * The main function sets up the GUI components for a Video Splitter
     * application in Java, allowing users to choose a video file, select a save
     * folder, load a CSV file, view and split video segments.
     */
    public static void main(String[] args) {
        // The code snippet you provided is setting up the initial GUI components for the Video
        // Splitter application in Java. Here's a breakdown of what it does:
        JFrame frame = new JFrame("🎬 Video Splitter with CSV - by Felfel");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        videoPathField = new JTextField(40);
        JButton browseVideoButton = new JButton("📁 Choose Video File");
        browseVideoButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                videoPathField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        }
        );
        // The code snippet you provided is setting up the GUI components for a video splitter
        // application in Java. Here's a breakdown of what each part of the code is doing:
        outputFolderField = new JTextField(40);
        JButton browseOutputButton = new JButton("📂 Choose Save Folder");
        browseOutputButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                outputFolderField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        }
        );
        JButton loadCSVButton = new JButton("📥 Load CSV");
        loadCSVButton.addActionListener(e -> loadCSV());
        topPanel.add(new JLabel("🔸 Video Path :"));
        topPanel.add(videoPathField);
        topPanel.add(browseVideoButton);
        topPanel.add(new JLabel("🔸 Save Folder :"));
        topPanel.add(outputFolderField);
        topPanel.add(browseOutputButton);
        topPanel.add(loadCSVButton);
        segmentsPanel = new JPanel();
        segmentsPanel.setLayout(new BoxLayout(segmentsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(segmentsPanel);
        JButton splitButton = new JButton("✂️ Split Video");
        splitButton.setBackground(Color.GREEN);
        splitButton.setForeground(Color.WHITE);
        splitButton.addActionListener(e -> splitVideo());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(splitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * The `loadCSV` function reads a CSV file, parses its contents, creates
     * input fields based on the data, and displays them in a panel.
     */
    private static void loadCSV() {
        // This code snippet is responsible for loading a CSV file using a `JFileChooser` dialog in the
        // Java GUI application. Here's a breakdown of what it does:
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File csvFile = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                segmentsPanel.removeAll();
                segmentInputs.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String[] timeParts = parts[0].split(":");
                        String name = parts[1];
                        JTextField hour = new JTextField(timeParts[0], 2);
                        JTextField minute = new JTextField(timeParts[1], 2);
                        JTextField second = new JTextField(timeParts[2], 2);
                        JTextField title = new JTextField(name, 12);
                        JPanel row = new JPanel();
                        row.add(new JLabel("hour"));
                        row.add(hour);
                        row.add(new JLabel("minute"));
                        row.add(minute);
                        row.add(new JLabel("second"));
                        row.add(second);
                        row.add(new JLabel("name of segment"));
                        row.add(title);
                        segmentsPanel.add(row);
                        segmentInputs.add(new SegmentInput(hour, minute, second, title));
                    }
                }
                segmentsPanel.revalidate();
                segmentsPanel.repaint();
                JOptionPane.showMessageDialog(null, "✅ CSV file loaded successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "❌ Error loading CSV file: " + ex.getMessage());
            }
        }
    }

    /**
     * The `splitVideo` function extracts video and output folder paths,
     * validates input values, and splits the video based on user-provided
     * timestamps using FFmpeg.
     */
    private static void splitVideo() {
        // This part of the code is responsible for extracting the video path and output folder path
        // from the corresponding text fields in the GUI. It then checks if either the video path or
        // the output folder path is empty. If either of them is empty, it displays an error message
        // using `JOptionPane` to prompt the user to select both a video file and an output folder.
        String videoPath = videoPathField.getText().trim();
        String outputFolder = outputFolderField.getText().trim();
        if (videoPath.isEmpty() || outputFolder.isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ Please select a video file and output folder.");
            return;
        }
        List<String> timestamps = new ArrayList<>();
        List<String> names = new ArrayList<>();
        // This part of the code is iterating over the list of `SegmentInput` objects stored in the
        // `segmentInputs` list. For each `SegmentInput` object, it is attempting to extract the hour,
        // minute, second, and name values from the corresponding text fields.
        for (SegmentInput input : segmentInputs) {
            try {
                int h = Integer.parseInt(input.hour.getText().trim());
                int m = Integer.parseInt(input.minute.getText().trim());
                int s = Integer.parseInt(input.second.getText().trim());
                String name = input.name.getText().trim();
                timestamps.add(String.format("%02d:%02d:%02d", h, m, s));
                names.add(name);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Check the input values, they should be numbers for hour, minute, and second.");
                return;
            }
        }
        // This part of the code is responsible for splitting the video based on the timestamps
        // provided by the user. Here's a breakdown of what it does:
        try {
            for (int i = 0; i < timestamps.size() - 1; i++) {
                String start = timestamps.get(i);
                String end = timestamps.get(i + 1);
                String name = names.get(i);
                String outputName = String.format("%02d_%s.mp4", i + 1, name);
                ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-i", videoPath, "-ss", start, "-to", end, "-c", "copy", new File(outputFolder, outputName).getAbsolutePath());
                builder.inheritIO();
                Process process = builder.start();
                process.waitFor();
            }
            JOptionPane.showMessageDialog(null, "✅ Video split successfully!");
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "❌ Error splitting video check ffmpeg installation: " + e.getMessage());
        }
    }

    /**
     * The SegmentInput class in Java contains JTextField fields for hour,
     * minute, second, and name.
     */
    static class SegmentInput {

        JTextField hour, minute, second, name;

        SegmentInput(JTextField h, JTextField m, JTextField s, JTextField n) {
            hour = h;
            minute = m;
            second = s;
            name = n;
        }
    }

}

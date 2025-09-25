package dnc.metrics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    private final List<String[]> rows = new ArrayList<>();

    public void addRow(String... columns) {
        rows.add(columns);
    }

    public void writeToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        }
    }

    public void clear() {
        rows.clear();
    }
}
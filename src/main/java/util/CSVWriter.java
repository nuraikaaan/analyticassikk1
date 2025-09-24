package util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final FileWriter writer;

    public CSVWriter(String filename) throws IOException {
        writer = new FileWriter(filename);
        writer.write("Algorithm,n,Comparisons,Allocations,MaxDepth\n");
    }

    public void write(String algo, int n, int comps, int allocs, int depth) throws IOException {
        writer.write(String.format("%s,%d,%d,%d,%d\n", algo, n, comps, allocs, depth));
    }

    public void close() throws IOException {
        writer.close();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package file_handling;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFromBinaryFile {
    public ReadFromBinaryFile() {
    }

    public static void main(String[] arg) {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream("myFiles/output.dat"));
            System.out.println(input.readInt());
            System.out.println(input.readChar());
            System.out.println(input.readBoolean());
            System.out.println(input.readDouble());
            input.close();
        } catch (IOException var2) {
            System.err.println("File Reading Error!");
            System.exit(0);
        }

    }
}

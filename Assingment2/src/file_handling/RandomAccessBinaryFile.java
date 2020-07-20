//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package file_handling;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessBinaryFile {
    public RandomAccessBinaryFile() {
    }

    public static void main(String[] arg) {
        try {
            RandomAccessFile randomfile = new RandomAccessFile("myFiles/test.dat", "rw");

            int x;
            for(x = 1; x <= 3; ++x) {
                randomfile.writeInt(x);
            }

            randomfile.seek(randomfile.getFilePointer() - 4L);
            x = randomfile.readInt();
            x += 20;
            randomfile.seek(randomfile.getFilePointer() - 4L);
            randomfile.writeInt(x);
            randomfile.seek(0L);

            for(int i = 1; i <= 3; ++i) {
                System.out.println(" " + randomfile.readInt());
            }

            randomfile.close();
        } catch (IOException var4) {
            System.err.println("File Reading Error!");
            System.exit(0);
        }

    }
}

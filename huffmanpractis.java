import java.util.Comparator;
import java.util.PriorityQueue;

class huffmanvar {
    char c;
    int item;
    huffmanvar left;
    huffmanvar right;
}

class ImplementComparator implements Comparator<huffmanvar> {
    public int compare(huffmanvar x, huffmanvar y) {
        return x.item - y.item;
    }
}

public class huffmanpractis {
    private static PriorityQueue<huffmanvar> nodeQ;

    public static void huffmancode(huffmanvar root, String s) // here root is the root of the huffman tree and s is the
                                                              // string in which the code will be added
    {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            String s1 = String.format("%4s", s);
            System.out.println(" " + root.c + "   |   " + s1 + "    | " + s.length());
            return;
        }
        huffmancode(root.left, s + "0");
        huffmancode(root.right, s + "1");
    }

    public static void main(String[] args) {
        int n = 4;
        char[] name = { 'A', 'R', 'Y', 'N'};
        int[] freq = { 3, 2, 1, 1 };
        nodeQ = new PriorityQueue<huffmanvar>(n, new ImplementComparator());
        for (int i = 0; i < n; i++) {
            huffmanvar letr = new huffmanvar();
            letr.c = name[i];
            letr.item = freq[i];
            letr.left = null;
            letr.right = null;
            nodeQ.add(letr);
        }
        huffmanvar root = null;
        while (nodeQ.size() > 1) {
            huffmanvar x = nodeQ.peek();
            nodeQ.poll();
            huffmanvar y = nodeQ.peek();
            nodeQ.poll();
            huffmanvar f = new huffmanvar();
            f.item = x.item + y.item;
            f.left = x;
            f.right = y;
            f.c = '-';
            root = f;
            nodeQ.add(f);
        }
        System.out.println("Finally the huffman table will be: ");
        huffmancode(root, "");

    }
}

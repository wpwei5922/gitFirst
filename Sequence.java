interface Selector {
    boolean end();
    Object current();
    void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size) {
        items = new Object[size];
    }
    public void add(Object x) throws ArrayIndexOutOfBoundsException {
        //if(next < items.length)
        items[next++] = x;
    }
    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() {
            return i == items.length;
        }
        public Object current() {
            return items[i];
        }
        public void next() {
            if(i < items.length) i++;
        }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        final int total_elements = 11;
        try {
            for (int i = 0; i < total_elements; i++)
                sequence.add(Integer.toString(i));
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            System.err.println(ex);
        }
        finally {
            System.exit(0);
        }
        Selector selector = sequence.selector();
        while(!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
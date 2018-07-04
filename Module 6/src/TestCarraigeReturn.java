import java.util.concurrent.TimeUnit;

public class TestCarraigeReturn {

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            System.out.print(String.format("\rTesting %d\rAnother %d", i, i*10));
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

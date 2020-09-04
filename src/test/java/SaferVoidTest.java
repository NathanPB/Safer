import dev.nathanpb.safer.Safer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("all")
public class SaferVoidTest implements SaferTest {

    static {
        System.getProperties().setProperty("env", "test");
    }

    private void wrap(Class<? extends Throwable> type, Runnable r) {
        assertThrows(type, r::run);
        assertDoesNotThrow(() -> Safer.run(r));
    }

    @Test
    @Override
    public void nullPointerException() {
        wrap(NullPointerException.class, () -> {
            Integer i = null;
            i.byteValue();
        });
    }

    @Test
    @Override
    public void classCastException() {
        wrap(ClassCastException.class, () -> {
            Object a = "foo";
            Integer bar = (Integer) a;
        });
    }

    @Test
    @Override
    public void arithmeticException() {
        wrap(ArithmeticException.class, () -> {
            int foo = 0;
            int bar = 1;
            int foobar = bar / foo;
        });
    }


    @Test
    @Override
    public void noClassDefFoundError() {
        wrap(NoClassDefFoundError.class, () -> {
            throw new NoClassDefFoundError();
        });
    }

    @Test
    @Override
    public void runtimeException() {
        wrap(RuntimeException.class, () -> {
            throw new RuntimeException();
        });
    }
}

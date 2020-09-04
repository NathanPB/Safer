import dev.nathanpb.safer.Safer;
import org.junit.jupiter.api.Test;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("all")
public class SaferReturnTest implements SaferTest {

    static {
        System.getProperties().setProperty("env", "test");
    }

    private <T> void wrap(Class<? extends Throwable> type, T defaultValue, Supplier<T> r) {
        assertThrows(type, r::get);
        assertDoesNotThrow(() -> Safer.run(defaultValue, r));
        assertEquals(defaultValue, Safer.run(defaultValue, r));
    }

    @Test
    @Override
    public void nullPointerException() {
        wrap(NullPointerException.class, "string", () -> {
            Integer i = null;
            i.byteValue();
            return "dummy";
        });
    }

    @Test
    @Override
    public void classCastException() {
        wrap(ClassCastException.class, new Object(), () -> {
            Object a = "foo";
            Integer bar = (Integer) a;
            return a;
        });
    }

    @Test
    @Override
    public void arithmeticException() {
        wrap(ArithmeticException.class, Integer.MAX_VALUE, () -> {
            int foo = 0;
            int bar = 1;
            return bar / foo;
        });
    }

    @Test
    @Override
    public void noClassDefFoundError() {
        wrap(NoClassDefFoundError.class, 'c', () -> {
            throw new NoClassDefFoundError();
        });
    }

    @Test
    @Override
    public void runtimeException() {
        wrap(RuntimeException.class, Long.MAX_VALUE, () -> {
            throw new RuntimeException();
        });
    }
}

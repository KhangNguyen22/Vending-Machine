import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrivilegeTest {

    @Test
    public void testPrivilegesLength() {
        assertEquals(3, Privilege.values().length);
    }

    @Test
    public void testPrivilegeString() {
        assertEquals("[SUPERUSER, STAFF, USER]", Arrays.toString(Privilege.values()));
    }
}

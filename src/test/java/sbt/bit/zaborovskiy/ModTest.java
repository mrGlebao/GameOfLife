package sbt.bit.zaborovskiy;

import org.junit.jupiter.api.Test;

public class ModTest {

    /**
     * -1 -> n-1
     */
    @Test
    public void test() {
        System.out.println(-1 % 100);
        System.out.println(1 % 100);
        System.out.println(101 % 100);
        System.out.println(-101 % 100);
    }

}

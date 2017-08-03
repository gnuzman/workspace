package com.zzh.springbatch;

public class BatchTest {

    public enum STATUS {
        AAA(-1),BBB(2),CCC(3);
        private Integer value = 0;
        STATUS(Integer value) {
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(STATUS.AAA.value());
    }
}

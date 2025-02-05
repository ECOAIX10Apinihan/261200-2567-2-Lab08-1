import java.util.Random;

public class Main {

    // Thread task class
    static class NumberPrinter implements Runnable {
        private int threadNumber;

        public NumberPrinter(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 1; i <= 50; i++) {
                System.out.println("thread # " + threadNumber + ": " + i);
                try {
                    // สุ่มดีเลย์ระหว่าง 100 ถึง 500 มิลลิวินาที
                    Thread.sleep(random.nextInt(400) + 100);
                } catch (InterruptedException e) {
                    System.out.println("Thread #" + threadNumber + " interrupted.");
                }
            }
        }
    }

    public static void main(String[] args) {
        // สร้างและรัน threads
        Thread t1 = new Thread(new NumberPrinter(1));
        Thread t2 = new Thread(new NumberPrinter(2));
        Thread t3 = new Thread(new NumberPrinter(3));

        System.out.println("Threads starting...");
        t1.start();
        t2.start();
        t3.start();

        try {
            // รอให้ทุก thread ทำงานเสร็จ
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have finished.");
    }
    // Observations:
        /*
        Multithreading คืออะไร?
        Multithreading ใน Java คือการรันหลาย task พร้อมกันผ่านการสร้าง Thread โดยแต่ละ thread จะมีการประมวลผลแยกจากกัน

        start() เป็นคำสั่งที่บอกให้ JVM จัดการสลับการทำงานของแต่ละ thread ตามความเหมาะสม
        join() ใช้เพื่อรอให้ thread ทำงานเสร็จก่อนที่จะดำเนินการขั้นตอนถัดไป
        ทำไมลำดับการแสดงผลถึงเปลี่ยนไปในแต่ละครั้ง?

        Java ไม่มีการรับประกันว่า thread ใดจะเริ่มทำงานก่อนหรือแสดงผลก่อน เพราะการจัดการ thread ขึ้นอยู่กับ Thread Scheduler ของ JVM และระบบปฏิบัติการ
        การเรียกใช้ Thread.sleep() แบบสุ่มช่วยให้ thread หยุดทำงานชั่วคราว ส่งผลให้ thread อื่นได้ทำงานแทน
        ผลลัพธ์จึงเปลี่ยนแปลงไปในแต่ละครั้งที่รันโปรแกรม เนื่องจาก context switching ระหว่าง thread ที่เกิดขึ้น
        */
}

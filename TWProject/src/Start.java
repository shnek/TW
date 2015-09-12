import Monitor.MonitorRun;
import ProducerConsumer.ActiveObjectRun;

import java.io.*;


public class Start {
    int buffercapacity = 500;
    int numberOfElements = 100;
    FileWriter writer;
    public void go(){
        System.out.println("Going!");
        try {
            writer = new FileWriter("thefile3.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int j = 0; j < 50; j++){
            System.out.println(j);
            for(int i = 1; i < 100; i++){
                ActiveObjectRun activeObjectRun = new ActiveObjectRun(buffercapacity, i, i, numberOfElements, writer);
                activeObjectRun.run();
            }
            for(int i = 1; i < 100; i++){
                MonitorRun monitor = new MonitorRun(i, i, buffercapacity, numberOfElements, writer);
                monitor.run();
            }
//            for(int i = 1; i < 100; i++){
//                ActiveObjectRun activeObjectRun = new ActiveObjectRun(buffercapacity, 100*i, 100*i, numberOfElements, writer);
//                activeObjectRun.run();
//            }
//            for(int i = 1; i < 100; i++){
//                MonitorRun monitor = new MonitorRun(100*i, 100*i, buffercapacity, numberOfElements, writer);
//                monitor.run();
//            }
//            for(int i = 1; i < 1000; i++){
//                ActiveObjectRun activeObjectRun = new ActiveObjectRun(buffercapacity, j, j, numberOfElements, writer);
//                activeObjectRun.run();
//            }
//            for(int i = 1; i < 1000; i++){
//                MonitorRun monitor = new MonitorRun(j, j, buffercapacity, numberOfElements, writer);
//                monitor.run();
//            }

        }
    }




}

package com.test.main;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class TestForkJoinPool {
    public static void main(String[] args) throws Exception{
        //long[] array = new Random().longs(100_0000).toArray();
        //可用的处理器个数
        Runtime.getRuntime().availableProcessors();

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        //自定义线程大小
        //ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        //RecursiveAction无返回值  RecursiveTask有返回值
        RecursiveAction recursiveAction = new TestRecursiveAction(0,2000);

        //execute没返回值 submit有返回值
        forkJoinPool.submit(recursiveAction);
        //线程阻塞，等待所有任务完成
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();    //关闭线程池
    }
}

class TestRecursiveAction extends RecursiveAction {
    private int start ,end;

    public TestRecursiveAction(int start ,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < 50){
            for(int i = start;i<end;i++){
                System.out.println(Thread.currentThread().getName()+" i = "+i);
            }
        }else{
            //int mid = (start + end) >>> 1;
            int middle =(start+end)/2;
            TestRecursiveAction actionOne = new TestRecursiveAction(start,middle);
            TestRecursiveAction actionTwo = new TestRecursiveAction(middle,end);
            //不要同时调用两个子任务的fork()方法,直接调用compute()效率更高
            actionTwo.fork();
            actionOne.compute();
            //多个子任务时建议使用
            //invokeAll(actionOne, actionTwo);
        }
    }
}

package com.test.main;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class TestForkJoinPool {
    public static void main(String[] args) throws Exception{
        //自定义线程大小
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        RecursiveAction recursiveAction = new TestRecursiveAction(0,2000);

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
            int middle =(start+end)/2;
            RecursiveAction actionOne = new TestRecursiveAction(start,middle);
            RecursiveAction actionTwo = new TestRecursiveAction(middle,end);
            actionOne.fork();
            actionTwo.fork();
        }
    }
}

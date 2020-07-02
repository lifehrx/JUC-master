第七课 70:00

Executor ： 线程池工厂
    |
ExecutorService(看源码理解一下各个方法) submit
    |
Callable(Future接收返回值) = Runnable
    |
Executors 
ThreadPool ： 2种
Future ： 存储执行的任务将来产生的结果
FutureTask ： Future + Task
    |
CompletableFuture ： 底层十分复杂, 开发的时候用的少,可以尝试用.

4 种线程池 ： 底层都用的是ThreadPoolExecutor（看下源码）
fixed cached single scheduled
workstealing  forkjoin (workstealing 也是用forkjoin实现的为啥要用它呢？就是为了提供一个方便的接口)

ThreadpoolExecutor源码 【多个线程共享一个队列】
forkjoinPool 【各自使用自己的队列】

PStreamAPI ： parallelStream() 底层也是用了forkjoinPool，线程之间不需要同步的时候用。T13 结果看来会差3倍

写工具写中台，不要写业务。

Cached VS Fixed ：看线程数量的多少再决定用哪个（《Java 并发编程实战》）
Cached ： 任务量忽高忽低的，但是要保证任务来了就有线程去执行，任务不会堆积用Chached
Fixed ： 任务量平稳，比如评估了8个线程可以处理，用Fiexd
阿里：建议都不要用，自己估算，进行精确定义。


并发： concurrent 指任务提交 （a 运行一会儿 b再运行一会儿）

并行： parallel 指任务执行 （多个CPU同时处理）

并行是并发的子集：

在知道了各种线程池怎么用后-要把重点放在源码分析。

ThreadPoolExecutoryua.md：源码
第3块
上来先起核心线程-然后把任务扔到队列里-如果没有可以干活的线程了-加非核心线程（这3步是通过源码看出来的）

第4块：addworker源码比较难
 添加线程：
步骤1.先把线程数量加一 【在多线程的状态下】（在那29位中） 不断的试最终能通过自旋的方式加进去

runworker
步骤2.起这个新加的worker
worker（工人） ： 本身就是一把锁
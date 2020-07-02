总结：

1：对于map/set的选择使用 ： 没有排序是元素没有比较
HashMap (里面的元素是没有排序的)
TreeMap (里面的元素是没有排序的, 底层是用的红黑树实现, 但红黑树是有序的？)
LinkedHashMap (里面的元素是没有排序的)

Hashtable
Collections.sychronizedXXX

ConcurrentHashMap(CAS)  哈希表实现的高并发容器(CHM 缩写)
ConcurrentSkipListMap （没有ConcurrentTreeMap, 缺了一环, 但是CAS用在树的结构上十分复杂, 所以就有了跳表）
                        跳表就出现了（分层的链表, 把一些关键元素拿出分层） 是TreeMap 的升级

2：队列
ArrayList
LinkedList
Collections.synchronizedXXX
CopyOnWriteList
Queue ： 添加了很多线程友好的 API : offer peek poll
	CocurrentLinkedQueue //concurrentArrayQueue
	BlockingQueue
	LinkedBlockingQueue
	ArrayBlockingQueue
	TransferQueue   ： 多人的手递手
	SynchronusQueue ： 手递手
	DelayQueue执行定时任务
		
163:00
总结：
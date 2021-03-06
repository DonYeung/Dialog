package com.don.common;

/**
 * Created by drcom on 2017/7/18.
 */

import com.don.widget.MyOwnDialog;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Dialog管理队列
 * <p>可按优先级显示Dialog，优先级相同时，优先队尾显示</p>
 * <p>如：
 * <pre class="prettyprint">
 *     dialog1.setPriority(1);<br/>
 *     dialog1.show();<br/>
 *     dialog2.setPriority(2);<br/>
 *     dialog2.show();<br/>
 *     dialog3.setPriority(3);<br/>
 *     dialog3.show();<br/>
 *     dialog4.setPriority(2);<br/>
 *     dialog4.show();<br/>
 *     </pre>
 * 显示顺序为dialog1→dialog3→dialog4→dialog2。</p>
 * <br/>
 */

public class DialogQueue {

    /**
     * 优先级，入队时按优先级
     */
    public static final int DIALOG_PRIORITY_MIN = 1;
    public static final int DIALOG_PRIORITY_MEDIUM = 2;
    public static final int DIALOG_PRIORITY_MAX = 3;

    private LinkedList<MyOwnDialog> mQueue = new LinkedList<>();

    /**
     * 按优先级插入一个MyAlertDialog元素
     * @param dialog
     * @return
     */
    public boolean offer(MyOwnDialog dialog) {
        int priority = dialog.getPriority();

        // 从0开始遍历
        ListIterator<MyOwnDialog> iterator = mQueue.listIterator();

        boolean flag = false;

        while(iterator.hasNext()){
            MyOwnDialog tmp = iterator.next();
            if(priority <= tmp.getPriority()){
                iterator.add(dialog);
                flag = true;
                break;
            }
        }

        // 待插入Dialog的priority值最大
        if(!flag){
            mQueue.offer(dialog);
        }

        return true;
    }

    /**
     * 按队列顺序从队尾出队
     * @return 出队元素 如果队列为空，返回null
     */
    public MyOwnDialog poll() {
        if(mQueue.size() == 0){
            return null;
        }
        return mQueue.removeLast();
    }

    /**
     * 当前{@link DialogQueue}({@link DialogManager#dialogQueue})的大小
     * @return 当前{@link DialogQueue}的大小
     */
    public int size(){
        return mQueue.size();
    }
}
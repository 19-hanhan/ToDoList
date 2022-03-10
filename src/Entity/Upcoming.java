package Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Upcoming {

    private Date time; // 待办的唯一主键
    private String context; // 待办内容
    private Boolean complete; // 待办完成标志

    // 构造方法
    public Upcoming() {
        time = new Date(System.currentTimeMillis());
        context = "";
        complete = false;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "[time: " + df.format(time) + ", context: " + context + ", complete: " + complete + "]";
    }


    // get/set函数
    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }
    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
    public Boolean getComplete() { return complete; }
    public void setComplete(Boolean complete) { this.complete = complete; }

}
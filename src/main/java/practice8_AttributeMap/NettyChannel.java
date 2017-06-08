package practice8_AttributeMap;

/**
 * Created by jet on 2017/6/8.
 */
import java.util.Date;

public class NettyChannel {

    private String name;


    private Date createDate;


    public NettyChannel(String name,Date createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
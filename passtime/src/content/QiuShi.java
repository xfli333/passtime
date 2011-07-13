package content;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-13
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class QiuShi implements Serializable {
    public String id;
    public String content;
    public String imageUrl;
    public String createTime;
    public String originType;

    public QiuShi() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QiuShi qiuShi = (QiuShi) o;

        if (createTime != null ? !createTime.equals(qiuShi.createTime) : qiuShi.createTime != null) return false;
        if (id != null ? !id.equals(qiuShi.id) : qiuShi.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QiuShi{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", originType='" + originType + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }
}

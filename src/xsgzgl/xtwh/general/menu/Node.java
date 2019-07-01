package xsgzgl.xtwh.general.menu;

import java.util.List;

/**
 * your description.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-09-26 10:01
 */
public class Node {

    /**
     * 当前节点
     */
    private String id;

    /**
     * 父节点
     */
    private String pid;

    /**
     * 当前名称
     */
    private String name;

    /**
     * 对应页面
     */
    private String dyym;

    /**
     * 读写权
     */
    private String dxq;

    /**
     * 子节点集合
     */
    private List<Node> subNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDyym() {
        return dyym;
    }

    public void setDyym(String dyym) {
        this.dyym = dyym;
    }

    public String getDxq() {
        return dxq;
    }

    public void setDxq(String dxq) {
        this.dxq = dxq;
    }

    public List<Node> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<Node> subNodes) {
        this.subNodes = subNodes;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", dyym='" + dyym + '\'' +
                ", dxq='" + dxq + '\'' +
                ", subNodes=" + subNodes +
                '}';
    }
}

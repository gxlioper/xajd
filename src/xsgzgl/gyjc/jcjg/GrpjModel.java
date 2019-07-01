package xsgzgl.gyjc.jcjg;

import java.util.List;
import java.util.Map;

public class GrpjModel {

    private String xh;
    private String cwh;
    private String xm;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    private List<Map<String,String>> pjList;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCwh() {
        return cwh;
    }

    public void setCwh(String cwh) {
        this.cwh = cwh;
    }

    public List<Map<String, String>> getPjList() {
        return pjList;
    }

    public void setPjList(List<Map<String, String>> pjList) {
        this.pjList = pjList;
    }
}

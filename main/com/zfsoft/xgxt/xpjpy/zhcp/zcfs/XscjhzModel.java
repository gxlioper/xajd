package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.util.HashMap;
import java.util.Map;

/**
 * ѧ���ɼ�����model.
 * <p>����ʡ����ҽҩ�ߵ�ְҵѧУ</p>
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2017-12-06 16:52
 */
public class XscjhzModel {

    private String xh;  //ѧ��
    private String xm;  //����
    private String xb;  //�Ա�

    private String pjf; //ƽ����
    private String dyf; //������
    private String zhf; //�ۺϷ�
    private String pjfpm;   //ƽ��������
    private String dyfpm;   //����������
    private String zhfpm;   //�ۺϷ�����

    private Map<String,String> cjMap;   //�ɼ�map

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getPjf() {
        return pjf;
    }

    public void setPjf(String pjf) {
        this.pjf = pjf;
    }

    public String getDyf() {
        return dyf;
    }

    public void setDyf(String dyf) {
        this.dyf = dyf;
    }

    public String getZhf() {
        return zhf;
    }

    public void setZhf(String zhf) {
        this.zhf = zhf;
    }

    public String getPjfpm() {
        return pjfpm;
    }

    public void setPjfpm(String pjfpm) {
        this.pjfpm = pjfpm;
    }

    public String getDyfpm() {
        return dyfpm;
    }

    public void setDyfpm(String dyfpm) {
        this.dyfpm = dyfpm;
    }

    public String getZhfpm() {
        return zhfpm;
    }

    public void setZhfpm(String zhfpm) {
        this.zhfpm = zhfpm;
    }

    public Map<String, String> getCjMap() {
        return cjMap;
    }

    public void setCjMap(Map<String, String> cjMap) {
        this.cjMap = cjMap;
    }

    public XscjhzModel addXkcj(Map<String, String> xsMap) {

        String cj = xsMap.get("cj");
        String kcmc = xsMap.get("kcmc");
        if(cjMap == null){
            cjMap = new HashMap<String, String>();
            this.xh = xsMap.get("xh");
            this.xm = xsMap.get("xm");
            this.xb = xsMap.get("xb");
            this.pjf = xsMap.get("pjf");
            this.dyf = xsMap.get("dyf");
            this.zhf = xsMap.get("zhf");
            this.pjfpm = xsMap.get("pjfpm");
            this.dyfpm = xsMap.get("dyfpm");
            this.zhfpm = xsMap.get("zhfpm");
        }
        cjMap.put(kcmc,cj);
        return this;
    }
}

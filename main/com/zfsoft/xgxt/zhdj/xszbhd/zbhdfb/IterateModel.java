package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������: ���Ȳ鿴ʱ�õ���ѭ������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-13 14:34
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class IterateModel {
    private String xymc;//ѧԺ����
    private List<HashMap<String,String>> rslist;//ѭ����list
    private int listSize;

    public List<HashMap<String, String>> getRslist() {
        return rslist;
    }

    public void setRslist(List<HashMap<String, String>> rslist) {
        this.rslist = rslist;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }


    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
    }
}

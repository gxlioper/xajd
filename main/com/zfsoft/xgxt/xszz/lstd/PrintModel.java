package com.zfsoft.xgxt.xszz.lstd;

import xgxt.form.BaseForm;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：2018-09-27
 */
public class PrintModel extends BaseForm {
    private LstdForm lstdForm;
    private HashMap<String,String> xsjbxx;
    private List<HashMap<String,String>> jtcyList;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LstdForm getLstdForm() {
        return lstdForm;
    }

    public void setLstdForm(LstdForm lstdForm) {
        this.lstdForm = lstdForm;
    }

    public HashMap<String, String> getXsjbxx() {
        return xsjbxx;
    }

    public void setXsjbxx(HashMap<String, String> xsjbxx) {
        this.xsjbxx = xsjbxx;
    }

    public List<HashMap<String, String>> getJtcyList() {
        return jtcyList;
    }

    public void setJtcyList(List<HashMap<String, String>> jtcyList) {
        this.jtcyList = jtcyList;
    }
}

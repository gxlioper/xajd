package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述: 进度查看时用到的循环对象
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-13 14:34
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class IterateModel {
    private String xymc;//学院名称
    private List<HashMap<String,String>> rslist;//循环的list
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

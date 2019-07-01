package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.util.*;

/**
 * 班级成绩汇总model.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2017-12-07 14:06
 */
public class BjcjhzModel {
    private String xn;  //学年
    private String xqmc;    //学期名称
    private String bjmc;    //班级名称
    private String bzr; //班主任

    //班级课程集合
    private Map<String,Set<String>> bjkcMap = new LinkedHashMap<String, Set<String>>();
    //班级学生集合
    private Map<String,XscjhzModel> xscjhzModelMap = new LinkedHashMap<String, XscjhzModel>();

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getBzr() {
        return bzr;
    }

    public void setBzr(String bzr) {
        this.bzr = bzr;
    }

    public Map<String, Set<String>> getBjkcMap() {
        return bjkcMap;
    }

    public void setBjkcMap(Map<String, Set<String>> bjkcMap) {
        this.bjkcMap = bjkcMap;
    }

    public Map<String, XscjhzModel> getXscjhzModelMap() {
        return xscjhzModelMap;
    }

    public void setXscjhzModelMap(Map<String, XscjhzModel> xscjhzModelMap) {
        this.xscjhzModelMap = xscjhzModelMap;
    }

    public BjcjhzModel addAll(List<HashMap<String, String>> xsMapList){
        for(Map<String,String> xsMap:xsMapList){
            add(xsMap);
        }
        return this;
    }

    public BjcjhzModel add(Map<String,String> xsMap){
        String xh = xsMap.get("xh");
        String kclx = xsMap.get("kclx");
        String kcmc = xsMap.get("kcmc");
        XscjhzModel xscjhzModel = xscjhzModelMap.get(xh);
        Set<String> kcSet = bjkcMap.get(kclx);

        if(xscjhzModel == null){
            xscjhzModel = new XscjhzModel();
            xscjhzModelMap.put(xh,xscjhzModel);

            this.xn = xsMap.get("xn");
            this.xqmc = xsMap.get("xqmc");
            this.bjmc = xsMap.get("bjmc");
            this.bzr = xsMap.get("bzr");
        }
        xscjhzModel.addXkcj(xsMap);

        if(kcSet == null){
            kcSet = new TreeSet<String>();
            bjkcMap.put(kclx,kcSet);
        }
        kcSet.add(kcmc);
        return this;
    }
}

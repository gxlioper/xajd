package com.zfsoft.xgxt.comm.zdydr.model;

import org.apache.struts.action.ActionForm;

/**
 * 自定义个性化导入model.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2017-10-10 10:21
 */
public class ZdydrModel extends ActionForm {

    private String drl; //导入列
    private String drlmc;   //导入列名称
    private String lsjghs;  //格式化本列值
    private String gshxx;   //格式化数据错误提示信息
    private String sfzj;    //是否主键（唯一）
    private String sfbt;    //是否必填
    private String zdcd;    //最大长度
    private String drmkdm;  //导入模块代码
    private String xsxx;    //显示顺序
    private String sfwy;    //是否唯一

    public String getDrl() {
        return drl;
    }

    public void setDrl(String drl) {
        this.drl = drl;
    }

    public String getDrlmc() {
        return drlmc;
    }

    public void setDrlmc(String drlmc) {
        this.drlmc = drlmc;
    }

    public String getLsjghs() {
        return lsjghs;
    }

    public void setLsjghs(String lsjghs) {
        this.lsjghs = lsjghs;
    }

    public String getGshxx() {
        return gshxx;
    }

    public void setGshxx(String gshxx) {
        this.gshxx = gshxx;
    }

    public String getSfzj() {
        return sfzj;
    }

    public void setSfzj(String sfzj) {
        this.sfzj = sfzj;
    }

    public String getSfbt() {
        return sfbt;
    }

    public void setSfbt(String sfbt) {
        this.sfbt = sfbt;
    }

    public String getZdcd() {
        return zdcd;
    }

    public void setZdcd(String zdcd) {
        this.zdcd = zdcd;
    }

    public String getDrmkdm() {
        return drmkdm;
    }

    public void setDrmkdm(String drmkdm) {
        this.drmkdm = drmkdm;
    }

    public String getXsxx() {
        return xsxx;
    }

    public void setXsxx(String xsxx) {
        this.xsxx = xsxx;
    }

    public String getSfwy() {
        return sfwy;
    }

    public void setSfwy(String sfwy) {
        this.sfwy = sfwy;
    }
}

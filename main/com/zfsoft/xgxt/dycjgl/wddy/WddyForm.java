package com.zfsoft.xgxt.dycjgl.wddy;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.form.User;
import xgxt.utils.Pages;

public class WddyForm extends ActionForm {
    private String guid;//
    private String xn;//ѧ��
    private String nj;//�꼶
    private String xqdm;//ѧ�ڴ���
    private String xmdm;//��Ŀ����
    private String xh;//ѧ��
    private String fs;//����
    private String zgh;//¼����
    private String lrsj;//¼��ʱ��
    private String rszb;//�༶����ռ��
    private String xq;//ѧ������
    private String xydm;//ѧԺ����
    private String xymc;//ѧԺ����
    private String zydm;//רҵ����
    private String zymc;//רҵ����
    private String bjdm;//�༶����
    private String bjmc;//�༶����
    private String dyzcj;
    private String sfhg;
    private String dyxf;
    private User user;//user,�������ݷ�Χ����
    //�������
    private String bkqk;
    private String bksfhg;
    private Pages pages = new Pages();
    private ExportModel exportModel = new ExportModel();

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getXmdm() {
        return xmdm;
    }

    public void setXmdm(String xmdm) {
        this.xmdm = xmdm;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getRszb() {
        return rszb;
    }

    public void setRszb(String rszb) {
        this.rszb = rszb;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getDyzcj() {
        return dyzcj;
    }

    public void setDyzcj(String dyzcj) {
        this.dyzcj = dyzcj;
    }

    public String getSfhg() {
        return sfhg;
    }

    public void setSfhg(String sfhg) {
        this.sfhg = sfhg;
    }

    public String getDyxf() {
        return dyxf;
    }

    public void setDyxf(String dyxf) {
        this.dyxf = dyxf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBkqk() {
        return bkqk;
    }

    public void setBkqk(String bkqk) {
        this.bkqk = bkqk;
    }

    public String getBksfhg() {
        return bksfhg;
    }

    public void setBksfhg(String bksfhg) {
        this.bksfhg = bksfhg;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}

package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import org.apache.struts.action.ActionForm;

public class ZtbhjcszForm extends ActionForm {
    private String id;	//����id
    private String sqkg;	//���뿪��
    private String sqkssj;	//���뿪ʼʱ��
    private String sqjssj;	//�������ʱ��
    private String splc;	//��������

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqkg() {
        return sqkg;
    }

    public void setSqkg(String sqkg) {
        this.sqkg = sqkg;
    }

    public String getSqkssj() {
        return sqkssj;
    }

    public void setSqkssj(String sqkssj) {
        this.sqkssj = sqkssj;
    }

    public String getSqjssj() {
        return sqjssj;
    }

    public void setSqjssj(String sqjssj) {
        this.sqjssj = sqjssj;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }
}

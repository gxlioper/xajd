package com.zfsoft.xgxt.dycjgl.jcsz;

import org.apache.struts.action.ActionForm;

public class JcszForm extends ActionForm {
    private String id;	//����id
    private String kqkg;	//��������
    private String xn;//ѧ��
    private String xqdm;//ѧ��

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKqkg() {
        return kqkg;
    }

    public void setKqkg(String kqkg) {
        this.kqkg = kqkg;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }
}

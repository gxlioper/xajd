package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszForm extends ActionForm {
    private String id;
    private String sqkg;
    private String sqkssj;
    private String sqjssj;
    private String splc;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

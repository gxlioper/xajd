package com.zfsoft.xgxt.cxcy.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-05 14:01
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sqkg;//����
    private String splc;//��������
    private String sqkssj;//��ʼʱ��
    private String sqjssj;//����ʱ��
    private String isopen ;//��ǰʱ���Ƿ���

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

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
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

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }
}

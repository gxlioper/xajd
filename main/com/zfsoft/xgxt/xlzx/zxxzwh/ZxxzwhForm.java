package com.zfsoft.xgxt.xlzx.zxxzwh;

import org.apache.struts.action.ActionForm;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-12-26 10:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZxxzwhForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String zxxz;//��ѯ��֪


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZxxz() {
        return zxxz;
    }

    public void setZxxz(String zxxz) {
        this.zxxz = zxxz;
    }
}

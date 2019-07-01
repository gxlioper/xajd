package com.zfsoft.xgxt.xlzx.zxxzwh;

import org.apache.struts.action.ActionForm;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-26 10:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZxxzwhForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String zxxz;//咨询须知


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

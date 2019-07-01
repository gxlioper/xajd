/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jswh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JswhService extends SuperServiceImpl<JswhForm, JswhDao>{

	public boolean jsFp(String[] zgharr) throws Exception {
		return dao.jsFp(zgharr);
	}

	public boolean jsQxfp(String[] zgharr) throws Exception {
		return dao.jsQxfp(zgharr);
	}

	public boolean jsSfkyy(String[] zgharr) throws Exception {
		return dao.jsSfkyy(zgharr);
	}
	public boolean dektSave(String zgh, String zzshen) throws Exception {
		return dao.dektSave(zgh, zzshen);
	}
}

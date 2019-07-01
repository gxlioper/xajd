/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao>{

	public CsszForm getModel() throws Exception {
		return dao.getModel();
	}

	public boolean bcCssz(CsszForm t) throws Exception {
		boolean flag = false;
		flag = dao.delCssz();
		if(flag){
			flag=dao.runInsert(t);
		}
	return flag;
}

}

/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz;

import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz.JcsszForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcsszForm, JcszDao>{
	
private JcszDao dao = new JcszDao();
public JcsszForm getModel(JcsszForm t)throws Exception{
	return dao.getModel();
}

 public JcsszForm getModel()throws Exception{
		return getModel(new JcsszForm());
}
	public boolean saveJcsz(JcsszForm myForm) throws Exception {
		boolean flag = false;
		flag = dao.deleteJcsz(myForm);
	if(flag){
		flag=dao.runInsert(myForm);
	}
	return flag;
}

}

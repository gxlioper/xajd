/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.jcsz;



import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao>{

	public boolean saveJcsz(JcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}

	public JcszForm getModel(JcszForm t)throws Exception{
		return dao.getModel();
	}
	 public JcszForm getModel()throws Exception{
			return getModel(new JcszForm());
	}
	
	
	
	
}

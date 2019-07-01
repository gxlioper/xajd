/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.action.zjly;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XnxqwhService extends SuperServiceImpl<XnxqwhForm, XnxqwhDao>{

	public XnxqwhForm getModel() throws Exception {
		return dao.getModel();
	}

	public boolean bcXnxq(XnxqwhForm t) throws Exception {
		boolean flag = false;
		flag = dao.delXnxq();
		if(flag){
			flag=dao.runInsert(t);
		}
	return flag;
}

	
	
	
	
	
}

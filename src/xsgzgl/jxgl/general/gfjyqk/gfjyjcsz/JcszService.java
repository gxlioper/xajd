/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao>{
 
	private JcszDao dao = new JcszDao();
	
	
	public JcszService() {
		super.setDao(dao);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public JcszForm getModel(JcszForm t)throws Exception{
		return dao.getModel();
	}

	 public JcszForm getModel()throws Exception{
			return getModel(new JcszForm());
	}
	
	public boolean saveJcsz(JcszForm model) throws Exception {
		boolean flag = false;
			flag = dao.deleteJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
	
}

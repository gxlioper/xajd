/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.cssz;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class SspycsszService extends SuperServiceImpl<SspycsszForm, SspycsszDao>{
	SspycsszDao dao = new SspycsszDao();
	public SspycsszForm getModel()throws Exception{
		return dao.getModel();
	}
	public boolean saveCssz(SspycsszForm myForm) throws Exception {
		boolean flag = false;
		flag = dao.deleteCssz(myForm);
		if(flag){
			flag=dao.runInsert(myForm);
		}
		return flag;
	}
	
}

package xgxt.rcsw.xhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;

public class XhglService {
	
	
	XhglDAO dao=new XhglDAO();
	/**
	 * 获取表头
	 * @param lx
	 * @param rForm
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		//校徽发放
		if("xhff".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
			 cn =new String[] { "主键", "学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "经办人姓名",
				"发放时间","是否发放" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
//	 ====================================校徽发放 begin============================================
	/**
	 * 校徽管理查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXhgl(XhglForm model) throws Exception{
		return dao.queryXhgl(model);
	}
	
	/**
	 * 批量校徽发放
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean xhffBatch(XhglForm model) throws Exception{
		
		return dao.xhffBatch(model);
	}
	
	/**
	 * 批量取消校徽发放
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean qxxhffBatch(XhglForm model) throws Exception{
		
		return dao.qxxhffBatch(model);
	}
	
	/**
	 * 批量取消校徽发放
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addXhff(XhglForm model) throws Exception{
		
		return dao.addXhff(model);
	}
	
	/**
	 * 修改校徽发放(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateXhff(XhglForm myForm) throws Exception {
		
		return dao.updateXhff(myForm);
	}
	
	/**
	 * 获取校徽发放记录(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public HashMap<String,String> getXhglMap(XhglForm myForm) throws Exception {
		
		return dao.getXhglMap(myForm);
	}
//	 ====================================校徽发放 end============================================
	

}

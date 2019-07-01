package xgxt.rcsw.xhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;

public class XhglService {
	
	
	XhglDAO dao=new XhglDAO();
	/**
	 * ��ȡ��ͷ
	 * @param lx
	 * @param rForm
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		//У�շ���
		if("xhff".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
			 cn =new String[] { "����", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "����������",
				"����ʱ��","�Ƿ񷢷�" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
//	 ====================================У�շ��� begin============================================
	/**
	 * У�չ����ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXhgl(XhglForm model) throws Exception{
		return dao.queryXhgl(model);
	}
	
	/**
	 * ����У�շ���
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
	 * ����ȡ��У�շ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean qxxhffBatch(XhglForm model) throws Exception{
		
		return dao.qxxhffBatch(model);
	}
	
	/**
	 * ����ȡ��У�շ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addXhff(XhglForm model) throws Exception{
		
		return dao.addXhff(model);
	}
	
	/**
	 * �޸�У�շ���(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateXhff(XhglForm myForm) throws Exception {
		
		return dao.updateXhff(myForm);
	}
	
	/**
	 * ��ȡУ�շ��ż�¼(2011.12.26 qlj)
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public HashMap<String,String> getXhglMap(XhglForm myForm) throws Exception {
		
		return dao.getXhglMap(myForm);
	}
//	 ====================================У�շ��� end============================================
	

}

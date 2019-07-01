package xgxt.pjpy.comm.pjpy.xmsz;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.MakeQuery;

public class PjpyXmszService extends PjpyCommService {

	/**
	 * ��ʼ���б�
	 * 
	 * @param request
	 */
	public void initList(HttpServletRequest request) {
		// ��Ŀ�����б�
		request.setAttribute("xmxzList", getXmxzList());
		// ��Ŀ��Χ�б�
		request.setAttribute("xmfwList", getXmfwList());
		// ��Ŀ�����б�
		request.setAttribute("xmlxList", getSelectList("xmlx"));
		//�Ƿ�����
		request.setAttribute("sfqyList", getSelectList("sfqy"));
	}
	
	/**
	 * �޸Ŀ���״̬
	 * 
	 * @throws Exception
	 */
	public boolean updateSfqy(PjpyXmszForm myForm) throws Exception {
		
		PjpyXmszDAO dao=new PjpyXmszDAO();
		
		return dao.updateSfqy(myForm);
	}
	
	public boolean fzlnxm(PjpyXmszForm myForm) throws Exception{
		
		PjpyXmszDAO dao=new PjpyXmszDAO();
		return dao.fzlnxm(myForm);
	}
	/**
	 * ��ȡ��������б�
	 * @return
	 */
	public List<HashMap<String,String>> getShlcList(){
		PjpyXmszDAO dao=new PjpyXmszDAO();
		List<HashMap<String,String>> map=dao.getShlcList();
		return map;
	}
	/**
	 * �����Ƿ�������
	 * @param xmdm
	 * @return
	 */
	public boolean checkSfysq(String xmdm){
		PjpyXmszDAO dao=new PjpyXmszDAO();
		boolean b=dao.checkSfysq(xmdm);
		return b;
	}
	
	/**
	 * �ж�����ϵͳ���ñ������Ƿ�Ϊ��
	 * @return HashMap<String,String
	 */
	public boolean getPjXtszb(){
		PjpyXmszDAO dao=new PjpyXmszDAO();
		HashMap<String,String>xtszb=dao.getPjXtszb();
		
		//�жϷ��ؼ�¼��
		if("0".equalsIgnoreCase(xtszb.get("num"))){
			return false;
		}else {
			return true;
		}
		
	}
	/**
	 * ��ȡѧ������
	 * @param xq
	 * @return
	 */
	public String getXqmc(String xq){
		
		PjpyXmszDAO dao=new PjpyXmszDAO();
		return dao.getXqmc(xq);
	}
	
   public boolean xmdmExist(PjpyXmszForm model){
		
		PjpyXmszDAO dao=new PjpyXmszDAO();
		if(dao.xmdmExist(model)){
			return true;
		}
		return false;
		
	}
   
   /**
	 * ���Ʒ�»����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszList(PjpyXmszForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xg_pjpy_pjxmwh";
		String[] queryList = new String[] { "pjxn", "pjxq", "pjnd", "xmlx",
				"xmxz", "xmfw" };
		String[] queryLikeList = new String[] { "xmdm", "xmmc", "ywmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		String[] inputValue = myQuery.getInputList();
		String[] colList = new String[] { "pkValue", "xmdm",
				"xmmc", "sqfsm", "sqzqm", "lcmc", "sftbxm", "sfqy" };
		
		return getRsArrList(tableName, query, inputValue, colList, "", model);
	}
}

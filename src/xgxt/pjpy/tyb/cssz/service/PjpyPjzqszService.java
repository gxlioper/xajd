package xgxt.pjpy.tyb.cssz.service;

import java.util.HashMap;

import common.Globals;

import xgxt.action.Base;
import xgxt.pjpy.tyb.cssz.dao.PjpyPjzqszDAO;
import xgxt.pjpy.tyb.cssz.model.PjpyPjzqszModel;
import xgxt.utils.String.StringUtils;

public class PjpyPjzqszService {

	final String CHECKED = "checked";//�Ƿ�ѡ��
	
	PjpyPjzqszDAO dao = new PjpyPjzqszDAO();

	/**
	 * ��������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePjzqxx(PjpyPjzqszModel model) throws Exception{
		return dao.savePjzqxx(model);
	}
	
	/**
	 * ɾ������������Ϣ
	 * @return
	 * @throws Exception
	 */
	public boolean delPjzqxx() throws Exception {
		return dao.delPjzqxx();
	}
	
	/**
	 * �޸Ľ�ѧ������ʱ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjpjsj(PjpyPjzqszModel model) throws Exception{
		return dao.updateJxjpjsj(model);
	}
	

	/**
	 * ��ѯ����ʱ��,����
	 * @return
	 */
	public HashMap<String, String> queryPjpysj() {
		return dao.queryPjpysj();
	}
	
	/**
	 * �޸Ľ�ѧ������ʱ��,����
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updatePjsj(PjpyPjzqszModel model) throws Exception {
		boolean result = updateJxjpjsj(model);
		
		result = result ? delPjzqxx() : false;
		
		return result ? savePjzqxx(model) : false;
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getPjzq() {
		HashMap<String, String> rs = queryPjpysj();
		String xn = rs.get("xn");
		String xq = rs.get("xq");
		String result = "";

		if (CHECKED.equalsIgnoreCase(rs.get("nd"))) {// ����Ȳ���
			result = "nd";
		} else if (CHECKED.equalsIgnoreCase(xq)) {//��ѧ��,ѧ��
			result = "xq";
		} else if (CHECKED.equalsIgnoreCase(xn)
				&& !CHECKED.equalsIgnoreCase(xq)) {// ��ѧ��
			result = "xn";
		}
		return result;
	} 
	
	/**
	 * ��ȡ�۲�����
	 * @return
	 */
	public String getZczq() {
		HashMap<String, String> rs = queryPjpysj();
		String xn = rs.get("zcxn");
		String xq = rs.get("zcxq");
		String nd = rs.get("zcnd");
		String result = "";
		
		//û�������۲����ڣ����۲�����ͬ��������
		if(StringUtils.isNull(xn) 
				&& StringUtils.isNull(nd)
				&& StringUtils.isNull(xq)){
			xn = rs.get("xn");
			xq = rs.get("xq");
			nd = rs.get("nd");
		}

		if (CHECKED.equalsIgnoreCase(nd)) {// ����Ȳ���
			result = "nd";
		} else if (CHECKED.equalsIgnoreCase(xq)) {//��ѧ��,ѧ��
			result = "xq";
		} else if (CHECKED.equalsIgnoreCase(xn)
				&& !CHECKED.equalsIgnoreCase(xq)) {// ��ѧ��
			result = "xn";
		}
		return result;
	} 
	
	public HashMap<String, String> queryXtszbxx() {
		return dao.queryXtszbxx();
	}
	
	/**
	 * �Ƿ������ۺϲ�������
	 * @return boolean
	 * */
	public boolean hasZczq(){
		boolean flag = false;
		if(Globals.XXDM_LSXY.equalsIgnoreCase(Base.xxdm)){
			//��ˮѧԺ
			flag = true;
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//��ְͨҵ��ѧ
			flag = true;
		}
		
		return flag;
	}
}

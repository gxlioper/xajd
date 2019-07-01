package xgxt.rcsw.hcyhk.hcyhkff;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.utils.Pages;

public class HcyhkffService extends CommService {

	HcyhkffDAO dao=new HcyhkffDAO();
	
	/**
	 * ���Żݿ����Ų�ѯ
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkff(RcswForm model) throws Exception{
		return dao.queryHcyhkff(model);
	}
	
	/**
	 * ���Żݿ���������
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean plHcyhkff(RcswForm model) throws Exception{
		
		return dao.plHcyhkff(model);
	}
	
	/**
	 * ɾ�����Żݿ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delHcyhkff(RcswForm model) throws Exception{
		
		return dao.delHcyhkff(model);
	}

	/**
	 * ��ȡ���Żݿ���¼
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getHcyhkMap(RcswForm model){
		
		return dao.getHcyhkMap(model);
	}
	
	
	/**
	 * ���ӻ��Żݿ�
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addHcyhk(RcswForm myForm) throws Exception {
		
		return dao.addHcyhk(myForm);
	}
	
	/**
	 * ���ӻ��Żݿ�
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateHcyhk(RcswForm myForm) throws Exception {
		
		return dao.updateHcyhk(myForm);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("hcyhkff".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
			 cn =new String[] { "����", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "����������",
				"����ʱ��","�Ƿ񷢷�" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	/**
	 * 
	 * @����:��õ�������list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-21 ����09:44:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportList(RcswForm rf, User user) throws Exception{
		rf.setUser(user);
		Pages pages =rf.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		rf.setPages(pages);//
		return dao.getExportList(rf);
	}
}

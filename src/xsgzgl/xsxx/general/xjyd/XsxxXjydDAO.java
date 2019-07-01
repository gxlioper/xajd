package xsgzgl.xsxx.general.xjyd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xsxx.general.XsxxGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ѧ���춯_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxXjydDAO extends CommDAO {
	DAO dao = DAO.getInstance();
	
	public ArrayList<String[]> getXsxxXjydList(XsxxGeneralForm myForm,XsxxXjydModel model) 
			throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, 
			NoSuchMethodException{
		List<String> colList = new ArrayList<String>();
		String[] colArr = new String[] {"xh","xn","xqmc","xh", "xm", "ydq","ydh","ydlx","xjzt","sfzx","sfyby","xymc","zymc","ydhxymc","ydhzymc"};
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (");
		sql.append(" view_xsxx_xjyd ");
		sql.append(" )a " );
		sql.append(query);
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(myForm.getPages(), sql.toString(), inputV, dao
			.unionArray(colArr, colList.toArray(new String[] {})));
		return list;
	}
	
	//���汾����ѧ�����������ѧ�������Ϣ��ѧ����Ϣ��
	public boolean saveXsxx(String sql) throws Exception{
		String sql1 = "insert into xsxxb(xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm,bz,mz,sfyby,sfzx) select" +
										" a.xh,a.xm,(case when a.xb='1' then '��' else 'Ů' end) xb,a.sfzh,a.nj,a.xjztm,a.xz,a.bmdm,a.zydm,a.bjdm,a.bz,a.mz,a.sfyby,a.sfzx"+
										" from bks_xsjbxx a where not exists( select 1 from xsxxb b where a.xh=b.xh ) and "+"("+sql+")";
		return dao.runUpdate(sql1, new String[]{});
	}
	
	//ͬ������ѧ����Ϣ
	public boolean updateXsxx(String sql) throws Exception{
		boolean flag=false;
		String sql1 = "select a.xh, a.ydhxydm,a.ydhzydm,a.ydhbjdm,a.xjztm,a.sfyby,a.sfzx,a.ydhnj"+
								" from xg_xsxx_xjydb a  where 1=1 and "+"("+sql+")";
		List<HashMap<String, String>> list =  dao.getList(sql1, new String[]{}, new String[]{"xh","ydhxydm","ydhzydm","ydhbjdm","xjztm","sfyby","sfzx","ydhnj"});
		if(null!=list&&list.size()>0){
			String[] str=new String[list.size()];
			for(int i =0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				str[i] ="update xsxxb set xydm='"+map.get("ydhxydm")+"'," +
								"zydm='"+map.get("ydhzydm")+"',bjdm='"+map.get("ydhbjdm")+"'," +
								"xjztm='"+map.get("xjztm")+"',sfyby='"+map.get("sfyby")+"'," +
								"sfzx='"+map.get("sfzx")+"',nj='"+map.get("ydhnj")+"' where xh="+"'"+map.get("xh")+"'";
			}
			flag=dao.saveArrDate(str);
		}
		return flag;
		}

	public ArrayList<String[]> getXhAjax(XsxxGeneralForm myForm, User user) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "nj","xz","bjmc" };
		// �߼���ѯ����
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,xh,xm,xb,nj,xz,bjmc,rownum r from ( ");
		sql.append(" select guid,xh,xm,xb,nj,xz,bjmc,xydm,zydm,bjdm,case when sfzx='��У' and sfyby='��' then '��У' else '����У' end sfzx from( ");
		sql.append(" select xh guid,xh,xm,xb,nj,xz,bjmc,xydm,zydm,bjdm,nvl(sfzx,'��У') sfzx,nvl(sfyby,'��') sfyby from view_xsbfxx)) a ");
		sql.append(query);
		sql.append(qxSql);
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * ����ѧ����Ϣ
	 * @param myForm
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXsxx(XsxxGeneralForm myForm,String xh) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select xm,xb,lxdh from view_xsbfxx a where xh=? ");
		return dao.getList(sql.toString(), new String[]{xh}, new String[]{"xm","xb","lxdh"});
	}
}

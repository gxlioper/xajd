package xsgzgl.pjpy.general.tjcx.hjmdtj;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class HjmdtjDAO extends CommDAO {

	/**
	 * ������ͳ����ҳ���ݲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getHjmdtjCx(HjmdtjForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		String[] colList = new String[] { "xh","xm","sfzh","nj","xymc","zymc","bjmc","xb","mzmc","rxrq","xmmc","xmje","yhkh" };
		// �û�����
		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,a.* from view_xg_pjpy_hjmdtj a where 1 = 1 ").append(searchTj);
		// ====================SQLƴװ end================================
//		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonPageQuery(myForm.getPages(),sql.toString(), inputV, colList);	
		
		return list;
	}
	
	/**
	 * ������ͳ����ҳ���ݲ�ѯ(�ϼ�)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getHjmdtjCxAll(HjmdtjForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmmc||'('||count(xh)||'��)' title from VIEW_xg_pjpy_hjmdtj ");
		sql.append(query);
		sql.append("  group by xmmc");
		// ====================SQLƴװ end================================
		String title = dao.getOneRs(sql.toString(), inputV, "title");
		return title;
	}
	
	/**
	 * ���������ʷ��Ϣ��Ĭ�ϵ�һ����Ϊ�״β�ѯ��������������Ŀ
	 * @return
	 */
	public String getPjlsxmMrtj(){
		DAO dao = DAO.getInstance();
		String sql = "select dm from (select distinct xmmc dm,xmmc mc from xg_view_pjpy_pjlsxx order by xmmc ) where rownum = 1 ";
		return dao.getOneRs(sql.toString(), new String[]{}, "dm");
	}
	
	/**
	 * ���������ʷ��Ϣ��Ĭ�ϵ�һ����Ϊ�״β�ѯ������������ʱ��
	 * @return
	 */
	public String getPjzqMrtj(){
		DAO dao = DAO.getInstance();
		String sql = "select dm from (select distinct pjsj dm,pjsj mc from xg_view_pjpy_pjlsxx order by pjsj ) where rownum = 1 ";
		return dao.getOneRs(sql.toString(), new String[]{}, "dm");
	}
	
}
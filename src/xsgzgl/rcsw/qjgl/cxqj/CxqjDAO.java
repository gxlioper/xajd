package xsgzgl.rcsw.qjgl.cxqj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
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
 * Time:2012-7-17 ����13:13:22
 * </p>
 */

public class CxqjDAO extends CommDAO {

	/**
	 * ���������ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCxqjCx(CxqjForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		User user = myForm.getUser();
		String[] colList = new String[] { "id", "xh", "xm", "nj", "bjmc", "qjlx", "qjkssj", "qjjssj", "qjts","sfcxx","cxr","cxsj","xy","zy","bj" };
		// �û�����
		String userType = user.getUserType();
		// ====================��������===================================
		user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xh,xm,nj,bjmc,qjlx,qjkssj,qjjssj,qjts,sfcxx,sfcx,cxr,cxsj,xy,zy,bj,rownum r from (");
		sql.append(" select id,xh,xm,nj,bjmc,qjlx,qjkssj,qjjssj,qjts,sfcx sfcxx,(case when sfcx ='��' then '��' else '��' end) sfcx,cxr,cxsj,xy,zy,bj from view_xg_rcsw_qjgl_qjcxb) ");
		sql.append(query);
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * ������ٵ�����¼�鿴
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCkqjMap(CxqjForm myForm) {
		DAO dao = DAO.getInstance();
		String guid = myForm.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,(select xymc from view_xsjbxx c where b.xh = c.xh) xy,(select zymc from view_xsjbxx d where b.xh = d.xh) zy,nj,bjmc bj,qjlx,qjts,qjkssj,qjjssj,a.cxr,a.cxsj,cxly from xg_rcsw_qjgl_qjcxb a right join view_xg_rcsw_qjgl_qjcxb b on a.sqid=b.id where id=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * ���������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean cxqjChx(CxqjForm myForm, String str, String cxyy, String username) throws Exception {
		DAO dao = DAO.getInstance();
		List<String> sqlArr = new ArrayList<String>();
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String cxsj = df.format(date);
		
		String[] idVal = str.split("!@");
		for (String id : idVal) {
				String sql = "insert into xg_rcsw_qjgl_qjcxb (SQID,CXR,CXSJ,CXLY) values ('";
				sql += id;
				sql +="','";
				sql +=username;
				sql +="','";
				sql +=cxsj;
				sql +="','";
				sql +=cxyy;
				sql +="') ";
				sqlArr.add(sql);
			}
		boolean flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		return flag;
	}

	/**
	 * ���������Ϣȡ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean cxqjQx(CxqjForm myForm, String str) throws Exception {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from xg_rcsw_qjgl_qjcxb where sqid=?";
		String[] val = null;
		String[] idVal = str.split("!@");
		for (String id : idVal) {
			val = new String[1];
			val[0] = id;
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}
}
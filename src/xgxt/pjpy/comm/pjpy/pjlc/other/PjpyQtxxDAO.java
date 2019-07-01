package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-21 ����09:02:20</p>
 */
public class PjpyQtxxDAO extends CommDAO{
	
	//	 ---------------------------����������ճ̹��� begin-------------------------------------
	/**
	 * ��ȡ����������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getQtxxList(PjpyQtxxForm model) throws Exception{
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList=new String[]{"pkValue","xn","xh","xm","nj","bjmc","hjjls"};
		
		// �û�����
		
		//====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,a.xn||'!!@@!!'||a.xh pkValue,a.* from( ");
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.nj from  ");
		sql.append(" (select xn,xh,count(1)hjjls from xg_pjpy_qtjlb ");
		sql.append(" group by xn,xh)a left join view_xsjbxx b ");
		sql.append(" on a.xh=b.xh)a ");

		sql.append(query);
		
			
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
}

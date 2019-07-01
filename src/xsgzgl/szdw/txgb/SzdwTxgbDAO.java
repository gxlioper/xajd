package xsgzgl.szdw.txgb;

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
public class SzdwTxgbDAO extends CommDAO{
	
	//	 ---------------------------����������ճ̹��� begin-------------------------------------
	/**
	 * ��ѧ�ɲ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getTxgbList(SzdwTxgbForm model) throws Exception{
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList=new String[]{"pkValue","xn","xh","xm","nj","bjmc","zzmc","gbmc","zzjb"};
		
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
	
		sql.append(" select  xn||'!!@@!!'||a.xh||'!!@@!!'||zzmc||'!!@@!!'||zzjb||'!!@@!!'||gbmc ");
		sql.append(" pkValue,a.* from (select a.*, ");
		sql.append(" b.xydm,b.zydm,b.bjdm,b.xm,b.xb,b.xymc,b.zymc,b.bjmc,b.nj,rownum r from   ");
		sql.append(" xg_szdw_txgbb a left join view_xsjbxx b on a.xh=b.xh)a ");

		sql.append(query);
		
			
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * ��ѯ������ѧ�ɲ���Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getTxgbMap(SzdwTxgbForm model){
		
		DAO dao=DAO.getInstance();
		
		String pkValue=model.getPkValue();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select * from xg_szdw_txgbb ");
		sql.append("  where xn||'!!@@!!'||xh||'!!@@!!'||zzmc||'!!@@!!'||zzjb||'!!@@!!'||gbmc=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
		
	}
	
}

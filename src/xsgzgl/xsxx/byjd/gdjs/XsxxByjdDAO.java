package xsgzgl.xsxx.byjd.gdjs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XsxxByjdDAO extends BasicDAO{
	
	/**
	 * ��ȡ��ҵ������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getByjdList(BasicModel model) throws Exception{
		
		
		BasicService basicService=new BasicService();
		
		//�߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		//�û�����
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// �û�����
		String userType=user.getUserType();
		
		//====================��������===================================
		user.setUserStatus(userType);
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
		
		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xh pkValue,a.xh,a.xm, a.nj, a.xydm, a.xymc, a.zydm, a.zymc, a.bjdm, a.bjmc, ");
		sql.append(" case when b.xh is null then 'δ����' else '��ϸ' end sfyjd, ");
		sql.append(" case when b.xh is null then '��' else '��' end sf ");
		sql.append(" from view_xsjbxx a ");
		sql.append(" left join xg_xsxx_byjdb b on a.xh = b.xh)a ");
		
		sql.append(query);

		sql.append(" order by bjmc,xh");
		
		
		// ====================SQLƴװ end================================
		return  CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, model);
	}
	
	/**
	 * ��ȡ��ҵ������ϸ��Ϣ
	 * @param myForm
	 * @return 
	 */
	public HashMap<String,String>getByjdDetail(XsxxByjdForm myForm){
		
		String xh=myForm.getXh();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xsxx_byjdb where xh=? ");
		
		DAO dao=DAO.getInstance();
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
}

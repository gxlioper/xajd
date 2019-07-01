package xsgzgl.xsxx.qtxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class XsxxQtxxDAO extends BasicDAO{
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsQtxxList(BasicModel model) throws Exception{
		
		
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
		
		sql.append(" select xh||'!!@@!!'||xmdm pkValue,rownum r,a.* from (select a.*,b.xm,b.nj,b.bjdm,b.bjmc,b.zydm,  ");
		sql.append(" b.zymc,b.xydm,b.xymc ");
		sql.append(" from xg_xsxx_qtxxb a,  ");
		sql.append(" view_xsjbxx b where a.xh=b.xh)a ");
		
		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		// ====================SQLƴװ end================================
		return  CommonQueryDAO.commonQuery(sql.toString(),"", inputV, colList, model);
	}
	
	public HashMap<String,String>getQtxxDetail(XsxxQtxxForm myForm){
		
		String xh=myForm.getXh();
		
		String xmdm = myForm.getXmdm();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select b.xh xhV,xm,b.nj,b.bjdm,b.bjmc,b.zydm,  ");
		sql.append(" b.zymc,b.xydm,b.xymc,a.* ");
		sql.append(" from(select * from  xg_xsxx_qtxxb where xmdm=? )a right join ");
		sql.append(" view_xsjbxx b on a.xh=b.xh ");
		sql.append(" where b.xh=?  ");
		
		DAO dao=DAO.getInstance();
		
		System.out.println(sql);
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xh});
		
	}
}

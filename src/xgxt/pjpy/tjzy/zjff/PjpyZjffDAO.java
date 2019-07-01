package xgxt.pjpy.tjzy.zjff;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.BasicModel;

public class PjpyZjffDAO  extends BasicDAO{

	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{
		
		
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
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		String ryk=jbszModel.getRyk();
		
		sql.append(" select a.xmdm,a.xmje,a.xh,rownum r, sf ");
		sql.append(" ,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,a.xmmc ");
		sql.append(" ,a.pjxn||'!!@@!!'||a.pjxq||'!!@@!!'||a.pjnd||'!!@@!!'||a.xmdm||'!!@@!!'||a.xh pkValue, ");
		sql.append(" kzzd1 ");
		
		sql.append(" from ( select a.*, c.xm,c.nj,c.xydm,c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,");
		sql.append(" kzzd1 sf ");
		sql.append(" from (select a.* from (select  a.xmdm,a.xh,");
		sql.append(" case when kzzd1 is null then '��' else kzzd1 end kzzd1, ");
		sql.append(" a.sqjg,a.over,a.pjxn,a.pjxq,a.pjnd,b.sqzq, b.xmmc,b.xmje,b.sjje from xg_pjpy_pjxmsqb a ");
		sql.append(" left join xg_pjpy_pjxmwhb b   ");
		
		sql.append(" on a.xmdm = b.xmdm and sqzq = 'xn' ");
		sql.append(" and ( a.pjxn = b.pjxn and a.pjxq = '��'  and a.pjnd = '��'  ");
		sql.append(" or sqzq = 'xq' and a.pjxn = b.pjxn and a.pjxq = b.pjxq ");
		sql.append("  and a.pjnd = '��' or sqzq = 'nd' and a.pjnd = b.pjnd ");
		sql.append("  and a.pjxn = '��' and a.pjxq = '��') ");
		
		sql.append("  )a where a.sjje ='��' ");
		sql.append("  and sqjg='���ͨ��' and over='yes' ");
        sql.append("  )a left join( ");
        
        if("pj".equalsIgnoreCase(ryk)){
        	
        	sql.append(getRykInfo());
       
        }else{
        	sql.append(" select a.*,c.xymc,c.zymc,c.bjmc from");
        	sql.append(Base.xsxxb);
        	sql.append(" a left join ");
        	sql.append(" view_njxyzybj b on a.bjdm=b.bjdm ");
        }
        
        sql.append(" ) c on ");
        
        if("pj".equalsIgnoreCase(ryk)){
        	
        	 sql.append(" a.xh=c.xh and (sqzq = 'xn' and a.pjxn =  c.pjxn ");
        	 sql.append("  or sqzq = 'xq' and a.pjxn = c.pjxn ");
        	 sql.append("  and a.pjxq = c.pjxq ");
        	 sql.append("  or sqzq = 'nd' and a.pjnd =  c.pjnd)");
       
        }else{
        
        	 sql.append(" a.xh=c.xh ");
        }
        
        if(!"pj".equalsIgnoreCase(ryk)){
        	
       	 	sql.append(" view_njxyzybj d ");
       	 	sql.append(" on c.bjdm=d.bjdm ");
        }	
       
		sql.append(" )a ");
		
		sql.append(query);
		
		String orderBy=basicService.ArrayToStr(model.getOrderBy(), ",");
	
		if(!Base.isNull(orderBy)){
			sql.append(" order by ");
			sql.append(orderBy);
		}
		
		System.out.println(sql);
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * �޸���˽����δ��ˣ�
	 * @return
	 * @throws Exception 
	 */
	public boolean wshUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg ='δ���', over = 'no' ");
		sql.append(" where over = 'no' ");
		sql.append(" and exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select min(b.xh) minLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt = 'δ���' ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * �޸���˽�������ͨ����
	 * @return
	 * @throws Exception 
	 */
	public boolean shtgUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg ='���ͨ��',over='yes' ");
		sql.append(" where   ");
		
		sql.append("  exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select max(b.xh) maxLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt = 'ͨ��' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		sql.append(" or exists(select 1 from xg_pjpy_pjxmwhb b where sfsh='��' ");
		sql.append(" and (sqzq='xn' and c.pjxn=b.pjxn  ");
		sql.append(" or sqzq='xq' and c.pjxn=b.pjxn and c.pjxq=b.pjxq  ");
		sql.append(" or sqzq='nd' and c.pjnd=b.pjnd)  and c.xmdm=b.xmdm  ");
		
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * �޸���˽������˲�ͨ����
	 * @return
	 * @throws Exception 
	 */
	public boolean shbtgUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg = '��˲�ͨ��', over = 'yes' ");
		
		sql.append(" where  over = 'no' and  exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where ");
		sql.append("  a.shzt = '��ͨ��' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * �޸���˽��������У�
	 * @return
	 * @throws Exception 
	 */
	public boolean shzUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg = '�����', over = 'no' ");
		
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select min(b.xh) minLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt <> 'δ���' ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		sql.append(" and not exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where ");
		sql.append("  a.shzt = '��ͨ��' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		sql.append(" and  exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select max(b.xh) maxLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt <> 'ͨ��' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	public String getRykInfo(){
		
		String pjry_sql = " (select a.pjxn,"
			+ " a.pjxq,"
			+ " a.pjnd,"
			+ " a.xh,"
			+ " a.xm,"
			+ " a.xydm,"
			+ " a.zydm,"
			+ " a.bjdm,"
			+ " a.xb,"
			+ " b.xymc,"
			+ " c.zymc,"
			+ " d.bjmc,"
			+ " d.nj,"
			+ " a.sfysz"
			+ " from xg_pjpy_xsb a, xg_pjpy_xyb b, xg_pjpy_zyb c, xg_pjpy_bjb d"
			+ " where a.sfysz = '��'"
			+ " and a.xydm = b.xydm"
			+ " and a.zydm = c.zydm"
			+ " and a.bjdm = d.bjdm"
			+ " and a.pjxn = b.pjxn and a.pjxq = b.pjxq and a.pjnd = b.pjnd"
			+ " and a.pjxn = c.pjxn and a.pjxq = c.pjxq and a.pjnd = c.pjnd"
			+ " and a.pjxn = d.pjxn and a.pjxq = d.pjxq and a.pjnd = d.pjnd )";
		
		return pjry_sql;
	}
}

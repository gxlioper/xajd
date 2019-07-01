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
		
		//高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		//用户对象
		User user=model.getUser();
		
		String []colList=model.getColList();
		
		// 用户属性
		String userType=user.getUserType();
		
		//====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================
		
		// ====================SQL拼装================================
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
		sql.append(" case when kzzd1 is null then '否' else kzzd1 end kzzd1, ");
		sql.append(" a.sqjg,a.over,a.pjxn,a.pjxq,a.pjnd,b.sqzq, b.xmmc,b.xmje,b.sjje from xg_pjpy_pjxmsqb a ");
		sql.append(" left join xg_pjpy_pjxmwhb b   ");
		
		sql.append(" on a.xmdm = b.xmdm and sqzq = 'xn' ");
		sql.append(" and ( a.pjxn = b.pjxn and a.pjxq = '无'  and a.pjnd = '无'  ");
		sql.append(" or sqzq = 'xq' and a.pjxn = b.pjxn and a.pjxq = b.pjxq ");
		sql.append("  and a.pjnd = '无' or sqzq = 'nd' and a.pjnd = b.pjnd ");
		sql.append("  and a.pjxn = '无' and a.pjxq = '无') ");
		
		sql.append("  )a where a.sjje ='是' ");
		sql.append("  and sqjg='审核通过' and over='yes' ");
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
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, model);
		
		return list;
	}
	
	/**
	 * 修改审核结果（未审核）
	 * @return
	 * @throws Exception 
	 */
	public boolean wshUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg ='未审核', over = 'no' ");
		sql.append(" where over = 'no' ");
		sql.append(" and exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select min(b.xh) minLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt = '未审核' ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * 修改审核结果（审核通过）
	 * @return
	 * @throws Exception 
	 */
	public boolean shtgUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg ='审核通过',over='yes' ");
		sql.append(" where   ");
		
		sql.append("  exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select max(b.xh) maxLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt = '通过' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		sql.append(" or exists(select 1 from xg_pjpy_pjxmwhb b where sfsh='否' ");
		sql.append(" and (sqzq='xn' and c.pjxn=b.pjxn  ");
		sql.append(" or sqzq='xq' and c.pjxn=b.pjxn and c.pjxq=b.pjxq  ");
		sql.append(" or sqzq='nd' and c.pjnd=b.pjnd)  and c.xmdm=b.xmdm  ");
		
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * 修改审核结果（审核不通过）
	 * @return
	 * @throws Exception 
	 */
	public boolean shbtgUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg = '审核不通过', over = 'yes' ");
		
		sql.append(" where  over = 'no' and  exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where ");
		sql.append("  a.shzt = '不通过' ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * 修改审核结果（审核中）
	 * @return
	 * @throws Exception 
	 */
	public boolean shzUpdate() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" update xg_pjpy_pjxmsqb c ");
		sql.append(" set sqjg = '审核中', over = 'no' ");
		
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where a.shjb = (select min(b.xh) minLv ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where b.splc = a.lcid) ");
		sql.append(" and a.shzt <> '未审核' ");
		sql.append(" and a.pjxn = c.pjxn ");
		sql.append(" and a.pjxq = c.pjxq ");
		sql.append(" and a.pjnd = c.pjnd ");
		sql.append(" and a.xmdm=c.xmdm ");
		sql.append(" and a.xh=c.xh) ");
		
		sql.append(" and not exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where ");
		sql.append("  a.shzt = '不通过' ");
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
		sql.append(" and a.shzt <> '通过' ");
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
			+ " where a.sfysz = '是'"
			+ " and a.xydm = b.xydm"
			+ " and a.zydm = c.zydm"
			+ " and a.bjdm = d.bjdm"
			+ " and a.pjxn = b.pjxn and a.pjxq = b.pjxq and a.pjnd = b.pjnd"
			+ " and a.pjxn = c.pjxn and a.pjxq = c.pjxq and a.pjnd = c.pjnd"
			+ " and a.pjxn = d.pjxn and a.pjxq = d.pjxq and a.pjnd = d.pjnd )";
		
		return pjry_sql;
	}
}


package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import xgxt.action.BaseAction;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhDao;

/**
 * 日常行为信息维护 
 */

public class RcxwxxwhDao extends SuperDAOImpl<RcxwxxwhForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("XG_RCSW_NEW_RCXWXXWH");
		super.setClass(RcxwxxwhForm.class);
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t)
			throws Exception {
		return null;
	}

	/**
	 * 取model
	 * @throws Exception 
	 */
	@Override
	public RcxwxxwhForm getModel(RcxwxxwhForm model) throws Exception{		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select a.*,b.rcxwlbdldm,d.rcxwlbdm ");
		sql.append(" from XG_RCSW_NEW_RCXWXXWH a ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB b   ");
		sql.append(" on a.rcxwlbxldm = b.rcxwlbxldm ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB c   ");
		sql.append(" on b.rcxwlbdldm = c.rcxwlbdldm ");		
		sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d   ");
		sql.append(" on d.rcxwlbdm = c.rcxwlbdm ");		
		sql.append(" where a.id = ? ");
		return getModel(sql.toString(),new String[]{model.getId()});
	}
		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t5", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (  ");
		sql.append(" select a.*,b.rcxwlbmc,a.rcxwlbdlmc || '(' || (CASE WHEN c.ssxydm = 'qx' THEN '全校' ELSE d.bmmc END) || ')' dlxx  ");
		sql.append(" from view_new_dc_rcsw_rcswxxwhnew a left join ( ");
		List<String> params = new ArrayList<String>();
		RcxwdmwhDao rcxwdmwhDao = new RcxwdmwhDao();
		sql.append(rcxwdmwhDao.getRcxwlbListByYhsqSQL(user, params));
		sql.append(" ) b on a.rcxwlbdm=b.rcxwlbdm ");
		sql.append(" left join  XG_RCSW_NEW_RCXWDLDMB c on c.rcxwlbdldm=a.rcxwlbdldm ");
		sql.append(" left join  ZXBZ_XXBMDM d on d.bmdm=c.ssxydm ");
		sql.append(" where b.rcxwlbdm is not null ");
		sql.append(") t5 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(params.toArray(new String[]{}), inputV));
	}
	
	/**
	 * 获取行为小别集合
	 */
	public List<HashMap<String, String>> getXwxlList(String xwdldm,
			HttpServletRequest request) {
		String sql = "select rcxwlbxldm,rcxwlbxlmc from XG_RCSW_NEW_RCXWXLDMB where rcxwlbdldm=? and sfqy='1' order by rcxwlbxldm ";
		return dao.getList(sql, new String[] { xwdldm }, new String[] { "rcxwlbxldm", "rcxwlbxlmc" });
	}
	/**
	 * 获取行为大类集合
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		String rcxwlbdm = request.getParameter("rcxwlbdm");
		StringBuilder sql = new StringBuilder();
		BaseAction baseAction = new BaseAction();
		User user = baseAction.getUser(request);
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdm,b.rcxwlbdlmc, ");
		sql.append("CASE WHEN b.ssxydm = 'qx' THEN '全校' ELSE g.bmmc END ssxymc ,");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from XG_RCSW_NEW_RCXWDLDMB b  ");
		sql.append(" left join ZXBZ_XXBMDM g on g.bmdm=b.ssxydm ");
		if(!"xx".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" where b.ssxydm = '"+user.getUserDep()+"' or b.ssxydm='qx'");
		}
		sql.append(" ) where isopen='true' and rcxwlbdm=? order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[] { rcxwlbdm }, new String[] { "rcxwlbdldm", "rcxwlbdlmc","ssxymc" });
	}
	/**
	 * 获取行为类别集合
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXwlbList(User user) throws Exception {
		RcxwdmwhDao rcxwdmwhDao = new RcxwdmwhDao();
		List<String> params = new ArrayList<String>();
		String sql = rcxwdmwhDao.getRcxwlbListByYhsqSQL(user, params);
		return dao.getListNotOut(sql, params.toArray(new String[] { }));
	}
	
	/**
	 * 按照学号，学年，学期查询该日常行为信息 在表中有几条数据
	 */
	public String checkExistForSave(RcxwxxwhForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from XG_RCSW_NEW_RCXWXXWH where xh=? and xn=? and xq=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getXn(), model.getXq() }, "num");
		return num;
	}
	
	/**
	 * 获取审核状态
	 */
	public String checkExistForUpdate(RcxwxxwhForm model) {
		String sql = "select shzt from XG_RCSW_NEW_RCXWXXWH where id=? ";
		String shzt = dao.getOneRs(sql.toString(), new String[] { model.getId()}, "shzt");
		return shzt;
	}

	/**
	 * 获取审批流程ID
	 */
	public String getShlcID(String Rcxwlbdldm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_RCSW_NEW_RCXWDLDMB  where rcxwlbdldm = ? ");

		return dao.getOneRs(sql.toString(), new String[] { Rcxwlbdldm }, "splc");
	}
	
	/**
	 * 在删除日常行为结果同时删除日常行为维护
	 */
	public int delRcxwwhFromRcxwjg(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_RCSW_NEW_RCXWXXWH t1 where  ");
		sql.append(" (");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append("id=?");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 判断该行为大类是否有审核流程
	 */
	public String checkForSplc(String rcxwlbdldm)throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_RCSW_NEW_RCXWDLDMB  where rcxwlbdldm = ? ");
		return dao.getOneRs(sql.toString(), new String[] { rcxwlbdldm }, "splc");
	}
	
	/**
	 * 删除日常行为信息
	 */
	public int delXwxx(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_RCSW_NEW_RCXWXXWH ");
		sql.append(" where (shzt is null or shzt='0' or shzt='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 查看单条日常行为信息
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xn, xqmc , rcxwlbmc,rcxwlbxlmc,rcxwlbdlmc,fjlj,fjmc, ");
		sql.append(" CASE WHEN c.ssxydm = 'qx' THEN '全校' ELSE g.bmmc END ssxymc,");
		sql.append(" rcxwjlsj,gfly,a.bz,(case when rcxwfzlx='01' then '+'||fz when rcxwfzlx='02' then '-'||fz else '未知类型' end) fz, decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc,fssj,c.rcxwlbbz,jlr,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from XG_RCSW_NEW_RCXWXXWH a left join (select *  from (select a.*,b.ssxydm,b.rcxwlbdlmc,c.rcxwlbmc  from XG_RCSW_NEW_RCXWXLDMB a ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm left join XG_RCSW_NEW_RCXWLBDMB c on c.rcxwlbdm = b.rcxwlbdm )) c ");
		sql.append(" on a.rcxwlbxldm = c.rcxwlbxldm ");
		sql.append(" left join ZXBZ_XXBMDM g on g.bmdm=c.ssxydm ");
		sql.append(" left join  xqdzb d on a.xq = d.xqdm where a.id = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	
	public boolean updateRcxwxxwh(String id,String splc,String shzt) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_RCSW_NEW_RCXWXXWH ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where id = ?");
		inputV[0] = shzt;
		inputV[1] = splc;
		inputV[2] = id;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/** 
	 * 查询获取行为小类信息
	 */
	public List<HashMap<String,String>> getXwxlxx(HttpServletRequest request,String lbxldm) {
		String sql = "select a.rcxwlbxldm,a.rcxwlbxlmc,a.rcxwlbdldm,a.rcxwfzlx,(case when a.rcxwfzlx='01' then '加分' when a.rcxwfzlx='02' then '减分' else '未知类型' end) rcxwfzlxmc,"
					+"(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,"
					+"a.rcxwlbbz,(case when a.rcxwlbbz is null then '' when length(a.rcxwlbbz) <=10 then a.rcxwlbbz else substr(a.rcxwlbbz,0,10)||'......' end) rcxwlbbzsj,a.rcxwlbfz,(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then 'zdy' else 'gd' end) fzsfgd,a.rcxwlbzgfz,a.rcxwlbzdfz "
					+" from XG_RCSW_NEW_RCXWXLDMB a where rcxwlbxldm=? and rownum=1";
		return dao.getList(sql, new String[] {lbxldm}, new String[] { "rcxwlbdldm","rcxwfzlx","rcxwfzlxmc","fzqj","rcxwlbbz","rcxwlbfz","fzsfgd","rcxwlbzgfz","rcxwlbzdfz","rcxwlbbzsj" });
	}
	
	/** 
	 * 判断信息是否重复(学号、学期、学年、行为列表、发生时间)
	 */
	public List<HashMap<String,String>> getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String[] xwxlStr,String[] fssjStr){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbxlmc from XG_RCSW_NEW_RCXWXXWH a left join XG_RCSW_NEW_RCXWXLDMB b on a.rcxwlbxldm=b.rcxwlbxldm where 1=1 ");
		if(xwxlStr!=null && xwxlStr.length>0){
			sql.append(" and ");
			for (int i=0;i<xwxlStr.length;i++){
				if(i==(xwxlStr.length-1)){
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbxldm='"+xwxlStr[i]+"' and a.fssj='"+fssjStr[i]+"')");
				}else{
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbxldm='"+xwxlStr[i]+"' and a.fssj='"+fssjStr[i]+"') or ");
				}
			}
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xh","xn","xq","rcxwlbxlmc","fssj"});
	}

	/** 
	 * 验证是否可提交
	 */
	public String checkSfktj(String rcxwlbdm) {
		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_XLJK_CDGL_CDXXB t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and t2.cdid = ? ");
		
		num = dao.getOneRs(sql.toString(), new String[] { rcxwlbdm }, "num");
		return num;
	}
}

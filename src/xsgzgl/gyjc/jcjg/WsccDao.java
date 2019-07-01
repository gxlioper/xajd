/**
 * @部门:学工产品事业部
 * @日期：2018年5月28日 下午4:52:13 
 */  
package xsgzgl.gyjc.jcjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月28日 下午4:52:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsccDao extends SuperDAOImpl<WsccForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsccForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsccForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}



	public List<HashMap<String, String>> getList(WsccForm t,User user) throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder("select * from (select q.xydm,a.ccid,a.lddm,b.ldmc,a.qsh,a.jcsj,a.pjdj, c.bmmc  from xg_gygl_wsjc_wsccjgb a ");
		sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm ");
		sql.append(" left join xg_gygl_new_qsxxb q on  a.lddm=q.lddm and a.qsh = q.qsh ");
		sql.append(" left join ZXBZ_XXBMDM c on q.xydm = c.bmdm  )t  where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String,String> getQsjbxx(String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lddm,ldmc,ch,qsh,xymc from view_xg_gygl_new_qsxx where lddm = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{lddm,qsh});
	}

	public HashMap<String,String> getCcxx(String ccid, String lddm, String qsh) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.ccid,jcsj,pjdj,jcr,xm,a.fjid from xg_gygl_wsjc_wsccjgb a left join view_fdyxx d on a.jcr =d.zgh  where a.ccid= ? and lddm= ? and qsh= ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{ccid,lddm,qsh});

	}

	public List<HashMap<String,String>> getZtwspj(String ccid, String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_wsjc_wsccjgztpjb a left join xg_gygl_wsjc_qsztwspjdmb f  on a.pjdm = f.dm where ccid= ?  and lddm= ? and qsh= ? order by xsxh asc ");
		return dao.getListNotOut(sql.toString(),new String[]{ccid,lddm,qsh});
	}


	public List<HashMap<String,String>> getGrwspj(String ccid, String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select d.*,a.pjdm,b.mc,c.xm,b.xsxh from xg_gygl_wsjc_wscccwb d left join xg_gygl_wsjc_wsccjggrpjb a on d.ccid = a.ccid and d.lddm = a.lddm and d.qsh = a.qsh and d.cwh = a.cwh and d.xh = a.xh left join xg_gygl_wsjc_grxgpjdmb b on a.pjdm=b.dm left join xsxxb c on d.xh = c.xh  where d.ccid= ?  and d.lddm= ? and d.qsh= ? order by d.cwh ");
		return dao.getListNotOut(sql.toString(),new String[]{ccid,lddm,qsh});
	}

	public List<HashMap<String,String>> getOptions() {
		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_qsztwspjdmb   order by xsxh asc");

		return dao.getListNotOut(sql.toString(),new String[]{});

	}

	public List<HashMap<String,String>> getGrOptions() {

		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_grxgpjdmb   order by xsxh asc");

		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	public boolean delZtpj(WsccForm model) throws Exception {

			StringBuilder sql = new StringBuilder();
			sql.append(" delete from xg_gygl_wsjc_wsccjgztpjb where ccid = ? and lddm = ? and qsh = ? ");
			return dao.runUpdate(sql.toString(), new String[]{model.getCcid(),model.getLddm(),model.getQsh()});


	}

	public boolean insertZtpj(WsccForm model) throws SQLException {
		StringBuilder sql = new StringBuilder();
		String[] ztpjs =model.getZtpjs();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String ccid = model.getCcid();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < ztpjs.length; i++) {
			if(StringUtils.isNotNull(ztpjs[i])){
				paraList.add(new String[]{ccid,ldh,qsh,ztpjs[i]});
			}
		}
		
		if(paraList.size() == 0){
			return true;
		}
		
		sql.append(" insert into xg_gygl_wsjc_wsccjgztpjb(ccid,lddm,qsh,pjdm)values(?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

	public boolean delGrpj(WsccForm model) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String ccid = model.getCcid();
		String[] qwhs = model.getCwhs();
		for (int i = 0; i < qwhs.length; i++) {
			paraList.add(new String[]{ccid,ldh,qsh,qwhs[i]});
		}
		sql.append(" delete from xg_gygl_wsjc_wsccjggrpjb where ccid = ? and lddm = ? and qsh = ?  and cwh = ?  ");
		return dao.runBatchBoolean(sql.toString(),paraList);
	}

	public boolean insertGrpj(WsccForm model) throws SQLException {
		StringBuilder sql = new StringBuilder();
		String[] grpjs =model.getGrpjs();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String ccid = model.getCcid();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < grpjs.length; i++) {
			String grpj=grpjs[i];
			if(grpj!= null && grpj.indexOf("-")!= -1){
				String[] jq = grpj.split("-");
				paraList.add(new String[]{ccid,ldh,qsh,jq[0],jq[1],ccid,ldh,qsh,jq[0]});	
			}
		}
		
		if(paraList.size() == 0){
			return true;
		}

		sql.append(" insert into xg_gygl_wsjc_wsccjggrpjb(ccid,lddm,qsh,cwh,pjdm,xh)values(?,?,?,?,?,(select xh from  xg_gygl_wsjc_wscccwb  where ccid=? and  lddm= ? and qsh = ? and cwh = ?))");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

    public List<HashMap<String,String>> getJbz(String[] ztpjs) {
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();

		sql.append(" select jbz from xg_gygl_wsjc_qsztwspjdmb where dm  in(");
		for (int i = 0; i < ztpjs.length; i++) {
			sql.append("?");
			if(i != ztpjs.length-1){
				sql.append(",");
			}
			paraList.add(ztpjs[i]);
		}
		sql.append(" )");
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
    }

	public boolean updatePjdj(WsccForm model) throws Exception {
		StringBuilder sql = new StringBuilder("update xg_gygl_wsjc_wsccjgb  ");
		sql.append("  set pjdj = ? ");
		sql.append("where lddm= ?   ");
		sql.append("and qsh =? ");
		sql.append("and ccid =? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getPjdj(),model.getLddm(),model.getQsh(),model.getCcid()});

	}

	public List<HashMap<String,String>> getDCList(WsccForm model, User user) throws Exception{
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select * from (select q.xydm,a.ccid,a.lddm,ldmc as ldmc ,a.qsh as qsh,jcsj as jcsj ,pjdj, bmmc as bmmc  from xg_gygl_wsjc_wsccjgb a   ");
		sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm   ");
		sql.append(" left join    xg_gygl_new_qsxxb q on  a.lddm=q.lddm and a.qsh = q.qsh   ");
		sql.append(" left join ZXBZ_XXBMDM c on q.xydm = c.bmdm  )t  where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut( sql.toString(), inputV);

	}
}

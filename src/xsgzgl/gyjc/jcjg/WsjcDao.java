/**
 * @部门:学工产品事业部
 * @日期：2018年5月28日 下午4:27:08 
 */  
package xsgzgl.gyjc.jcjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月28日 下午4:27:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsjcDao  extends SuperDAOImpl<WsjcForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(WsjcForm.class);
		this.setKey("jcrq");
		this.setTableName("xg_gygl_wsjc_wsjcjgb");

		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsjcForm t) throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */



	public List<HashMap<String, String>> getList(WsjcForm t,User user) throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from ( select q.xydm,a.jcrq,a.lddm, b.ldmc,a.qsh,c.bmmc,a.jcsj,a.pjdj ");
		sql.append(" from xg_gygl_wsjc_wsjcjgb a ");
		sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm ");
		sql.append(" left join xg_gygl_new_qsxxb q on a.lddm=q.lddm and a.qsh = q.qsh ");
		sql.append(" left join ZXBZ_XXBMDM c on q.xydm = c.bmdm) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(),inputV);
	}

	public List<HashMap<String, String>> getDCList(WsjcForm t,User user) throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from ( select a.xydm,jcrq,a.lddm as lddm, ldmc as ldmc ,qsh as qsh,bmmc as bmmc,jcsj as jcsj,pjdj as pjdj ");
		sql.append(" from xg_gygl_wsjc_wsjcjgb a   ");
		sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm   ");
		sql.append("   left join ZXBZ_XXBMDM c on a.xydm = c.bmdm  ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut( sql.toString(),inputV);
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(WsjcForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	public HashMap<String,String> getQsjbxx(String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lddm,ldmc,ch,qsh,xymc from view_xg_gygl_new_qsxx where lddm = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{lddm,qsh});
	}

	public HashMap<String,String> getJcxx(String jcsj, String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select jcrq,jcsj,pjdj,jcr,xm,a.fjid from xg_gygl_wsjc_wsjcjgb a left join view_fdyxx d on a.jcr =d.zgh  where jcrq= ? and lddm= ? and qsh= ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{jcsj,lddm,qsh});

	}

	public List<HashMap<String,String>> getZtwspj(String jcrq, String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_wsjc_wsjcjgztpjb a left join xg_gygl_wsjc_qsztwspjdmb b  on a.pjdm = b.dm where jcrq= ?  and lddm= ? and qsh= ? order by xsxh asc ");
		return dao.getListNotOut(sql.toString(),new String[]{jcrq,lddm,qsh});
	}

	public List<HashMap<String,String>> getGrwspj(String jcrq, String lddm, String qsh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select d.*,a.pjdm,b.mc,c.xm,b.xsxh from XG_GYGL_WSJC_WSJCCWB d left join xg_gygl_wsjc_wsjcjggrpjb a on d.jcrq = a.jcrq and d.lddm = a.lddm and d.qsh = a.qsh and d.cwh = a.cwh and d.xh = a.xh left join xg_gygl_wsjc_grxgpjdmb b on a.pjdm=b.dm left join xsxxb c on d.xh = c.xh  where d.jcrq= ?  and d.lddm= ? and d.qsh= ? ");
		return dao.getListNotOut(sql.toString(),new String[]{jcrq,lddm,qsh});
	}

	public List<HashMap<String,String>> getOptions() {
		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_qsztwspjdmb  order by xsxh asc ");

		return dao.getListNotOut(sql.toString(),new String[]{});

	}

	public List<HashMap<String,String>> getGrOptions() {

		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_grxgpjdmb   order by xsxh asc");

		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	public boolean delZtpj(WsjcForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_wsjc_wsjcjgztpjb where jcrq = ? and lddm = ? and qsh = ? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getJcrq(),model.getLddm(),model.getQsh()});

	}

	public boolean insertZtpj(WsjcForm model) throws SQLException {
		StringBuilder sql = new StringBuilder();
		String[] ztpjs =model.getZtpjs();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String jcrq = model.getJcrq();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < ztpjs.length; i++) {
			if(StringUtils.isNotNull(ztpjs[i])){
				paraList.add(new String[]{jcrq,ldh,qsh,ztpjs[i]});
			}
		}

		if(paraList.size() == 0){
			return true;
		}
		
		sql.append(" insert into xg_gygl_wsjc_wsjcjgztpjb(jcrq,lddm,qsh,pjdm)values(?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

	public boolean delGrpj(WsjcForm model) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String jcrq = model.getJcrq();
		String[] qwhs = model.getCwhs();
		for (int i = 0; i < qwhs.length; i++) {
			paraList.add(new String[]{jcrq,ldh,qsh,qwhs[i]});
		}
		sql.append(" delete from xg_gygl_wsjc_wsjcjggrpjb where jcrq = ? and lddm = ? and qsh = ?  and cwh = ?  ");
		return dao.runBatchBoolean(sql.toString(),paraList);
	}

	public boolean insertGrpj(WsjcForm model) throws SQLException {

		StringBuilder sql = new StringBuilder();
		String[] grpjs =model.getGrpjs();
		List<String[]> paraList = new ArrayList<String[]>();
		String qsh = model.getQsh();
		String ldh = model.getLddm();
		String jcrq = model.getJcrq();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < grpjs.length; i++) {
			String grpj=grpjs[i];
			if(grpj!= null && grpj.indexOf("-")!= -1){
				String[] jq = grpj.split("-");
				paraList.add(new String[]{jcrq,ldh,qsh,jq[0],jq[1],jcrq,ldh,qsh,jq[0]});
			}
		}

		if(paraList.size() == 0){
			return true;
		}
		
		sql.append(" insert into xg_gygl_wsjc_wsjcjggrpjb(jcrq,lddm,qsh,cwh,pjdm,xh)values(?,?,?,?,?,(select xh from  xg_gygl_wsjc_wsjccwb  where jcrq=? and  lddm= ? and qsh = ? and cwh = ?))");
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

	public boolean updatePjdj(WsjcForm model) throws Exception {
		StringBuilder sql = new StringBuilder("update xg_gygl_wsjc_wsjcjgb  ");
		sql.append("  set pjdj = ? ");
		sql.append("where lddm= ?   ");
		sql.append("and qsh =? ");
		sql.append("and jcrq =? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getPjdj(),model.getLddm(),model.getQsh(),model.getJcrq()});

	}
}

/**
 * @部门:学工产品事业部
 * @日期：2015-8-10 上午11:39:31 
 */
package com.zfsoft.xgxt.khgl.khdxgl.khdxgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核对象管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-10 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhdxglDao extends SuperDAOImpl<KhdxglForm> {
	private static final String SFNZ="1";//是否内置(1:否,2：是)

	@Override
	public List<HashMap<String, String>> getPageList(KhdxglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(KhdxglForm t, User user) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,nvl(t2.yhs,0)yhs,case when t1.khlx='1' then '学生' else '教师' end khlxmc,");
		sql.append("case when t1.sfnz='1' then '否' else '是' end sfnzmc");
		sql.append("  from xg_khgl_khdx t1 left join ");
		sql.append(" (select count(a.zgh) yhs, '班主任' yhlx from fdyxxb a  where a.zgh in (select zgh from bzrbbb where bjdm in (select bjdm from view_njxyzybj))");
		sql.append(" union all select count(a.zgh) yhs, '辅导员' yhlx from fdyxxb a  where a.zgh in (select zgh from fdybjb where bjdm in (select bjdm from view_njxyzybj))");
		sql.append(" union all select count(a.zgh) yhs, '班主任+辅导员' yhlx from fdyxxb a  where a.zgh in (select zgh from (select zgh, bjdm from fdybjb union select zgh, bjdm from bzrbbb)");
		sql.append(" where bjdm in (select bjdm from view_njxyzybj))");
		sql.append(" union all select count(a.xh) yhs, '学生' yhlx from view_xsjbxx a union all");
		sql.append("  select count(1) yhs,khdxid yhlx from xg_khgl_khdx_xs group by khdxid");
		sql.append(" union all select count(1) yhs,khdxid yhlx from xg_khgl_khdx_js group by khdxid) t2");
		sql.append(" on (case when t1.sfnz = '2' then  t1.khdxmc else t1.khdxid  end )= t2.yhlx  ");
		sql.append("order by t1.sfnz desc) t where 1=1 and sczt='0' ");
		if (!StringUtil.isNull(t.getKhdxmc())) {
			params.add(t.getKhdxmc());
			sql.append(" and t.khdxmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * 
	 * @描述:考核对象详情（学生）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-17 上午08:52:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhdxOfStuList(KhdxglForm model, User user)
	throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
	
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from (select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm,nvl2(d.xh, '1', '0') as sfbgb, nvl2(d.xh, '是', '否') as sfbgbmc from view_xsjbxx a ");
		sql.append("left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH");
		sql.append(" where zzzt = '1') d on a.xh = d.xh where 1=1");
		
		//非内置对象
		if (SFNZ.equals(model.getSfnz())) {
			sql.append(" and a.xh in(select khxh from xg_khgl_khdx_xs where khdxid='"+model.getKhdxid()+"')");
		}
		if("wfp".equals(model.getFpzt())){
			if("1".equals(model.getPflx())){//评分类型为学生
				sql.append(" and a.xh not in(select distinct khdxr from xg_khgl_pfz_xs where pfzid='"+model.getPfzid()+"')");
			}else{
				sql.append(" and a.xh not in(select distinct khdxr from xg_khgl_pfz_js where pfzid='"+model.getPfzid()+"')");
			}
			
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
}
	public List<HashMap<String, String>> getStuList(KhdxglForm model, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*from(");
		sql.append("select nvl2(e.khdxr,'1','0')sfybpf,a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,nvl2(d.xh, '1', '0') as sfbgb,nvl2(d.xh, '是', '否') as sfbgbmc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a ");
		sql.append("left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH");
		sql.append(" where zzzt = '1') d on a.xh = d.xh left join ");
		sql.append(" (select a.*,b.khdxid from xg_khgl_khpf a left join xg_khgl_khxm b on a.xmid=b.xmid  where a.sftj='1' and khdxid='"+model.getKhdxid()+"')e");
		sql.append(" on a.xh=e.khdxr where 1=1");
		String fpzt = model.getFpzt();
		if (fpzt.equals("kfp")) {
			sql.append(" and a.xh not in(select khxh from xg_khgl_khdx_xs where khdxid='"+model.getKhdxid()+"')");
		} else {
			sql.append(" and a.xh in(select khxh from xg_khgl_khdx_xs where khdxid='"+model.getKhdxid()+"')");
		}
		sql.append(")a where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:考核对象详情（教师）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-17 上午09:04:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhdxOfTeaList(KhdxglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		KhdxglForm khdxForm=getModel(model);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select a.* from(select a.zgh yhm,a.xm,a.bmdm,a.bmmc,a.xb,  ");
		sql.append("case ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '校级' then '校级用户' ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '院级' then '院级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '校级' then '兼任学校用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '院级' then '兼任院系用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '校级' then '班级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '院级' then '班级用户' ");
		sql.append("end yhsf, ");
		sql.append("decode(a.fdydbs,'0','否','是') sffdy, ");
		sql.append("decode(a.bzrdbs,'0','否','是') sfbzr ");
		sql.append("from ( ");
		sql.append("select a.sfbl,a.zgh,a.xm,a.zw,a.lxdh,a.bmdm,a.kzzd5,b.bmmc,a.xl,a.zc,a.zzmm, ");
		sql.append("decode(a.xb,'1','男','2','女',a.xb) xb, ");
		sql.append("decode(b.bmlb,'5','院级','校级') bmjb, ");
		sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
		sql.append("decode(e.yhm,null,'否','是') sfyh, a.sfjryx ");
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm=b.bmdm ");

		sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
		sql.append("   from fdybjb c ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by c.zgh) c ");
		sql.append("on a.zgh=c.zgh ");

		sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
		sql.append("  from bzrbbb d ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by d.zgh) d ");

		sql.append("on a.zgh=d.zgh ");
		sql.append("left join yhb e ");
		sql.append("on a.zgh=e.yhm  ");
		sql.append(")a)a where 1=1");
		if (SFNZ.equals(model.getSfnz())) {
			sql.append(" and a.yhm in(select khzgh from xg_khgl_khdx_js where khdxid='"+model.getKhdxid()+"')");
		}else{
			if("班主任".equals(khdxForm.getKhdxmc())){
				sql.append(" and a.yhm in(select zgh from bzrbbb where bjdm in (select bjdm from view_njxyzybj))");
			}else if("辅导员".equals(khdxForm.getKhdxmc())){
				sql.append(" and a.yhm in(select zgh from fdybjb where bjdm in (select bjdm from view_njxyzybj))");
			}else{
				sql.append(" and a.yhm in (select zgh from (select zgh, bjdm from fdybjb union select zgh, bjdm");
                sql.append(" from bzrbbb ) where bjdm in (select bjdm from view_njxyzybj))");
			}
		}
		if("wfp".equals(model.getFpzt())){
			if("1".equals(model.getPflx())){//评分类型为学生
				sql.append(" and a.yhm not in(select distinct khdxr from xg_khgl_pfz_xs where pfzid='"+model.getPfzid()+"')");
			}else{
				sql.append(" and a.yhm not in(select distinct khdxr from xg_khgl_pfz_js where pfzid='"+model.getPfzid()+"')");
			}
			
		}
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(model, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> getTeaList(KhdxglForm model, User user)
	throws Exception {
	StringBuffer sql = new StringBuffer();
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(model.getSearchModel());
	String[] inputV = SearchService.getTjInput(model.getSearchModel());
	sql.append("select rownum r,nvl2(e.khdxr,'1','0')sfybpf,a.* from (select decode(b.xb,'1','男','2','女',b.xb) xb,b.zgh yhm,b.zgh,b.xm,b.bmdm,c.bmmc from fdyxxb b ");
	sql.append(" left join zxbz_xxbmdm c on b.bmdm= c.bmdm");
	sql.append(")a left join ");
	sql.append(" (select a.*,b.khdxid from xg_khgl_khpf a left join xg_khgl_khxm b on a.xmid=b.xmid  where a.sftj='1' and khdxid='"+model.getKhdxid()+"')e");
	sql.append(" on a.yhm=e.khdxr where 1=1");
	String fpzt = model.getFpzt();
	if (fpzt.equals("kfp")) {
		sql.append(" and a.yhm not in(select khzgh from xg_khgl_khdx_js where khdxid='"+model.getKhdxid()+"')");
	} else {
		sql.append(" and a.yhm in(select khzgh from xg_khgl_khdx_js where khdxid='"+model.getKhdxid()+"')");
	}
	sql.append(searchTj);
	sql.append("order by bmdm desc");
	return getPageList(model, sql.toString(), inputV);
}
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核对象
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhdx(String khdxid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_khgl_khdx t1 where t1.khdxid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { khdxid});
	}


	/**
	 *判断考核对象是否已存在
	 */
	public boolean isHave(KhdxglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_khdx where khdxmc=? ");
		if(null!=model.getKhdxid()){
			sql.append(" and khdxid<>'"+model.getKhdxid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getKhdxmc()}, "num");
		return Integer.parseInt(num)>0;
	}
	/**
	 *判断考核对象是否已使用
	 */
	public boolean isUsed(String value) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select distinct khdxid from xg_khgl_pfz where khdxid =?");
		sql.append(" union all select distinct khdxid from xg_khgl_khxm where khdxid =?");
		sql.append(" union all select distinct khdxid from xg_khgl_khb where khdxid =?)");
 		String num = dao.getOneRs(sql.toString(), new String[]{value,value,value}, "num");
		return Integer.parseInt(num)>0;
	}



	/**
	 * 
	 * 删除考核对象
	 */
	public boolean delKhdxgl(String Khdxid) throws Exception {
		String sql = "delete from xg_khgl_khdx where Khdxid=?";
		return dao.runUpdate(sql, new String[] { Khdxid });
	}
	/**
	 * 
	 * @描述:修改删除状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:00:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhdx(List<String[]> params) throws Exception {
		String sql = "update xg_khgl_khdx set sczt=? where khdxid=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:增加考核学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午09:08:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean KhxsInsert(List<String[]> params) throws Exception {
		String sql = "insert into xg_khgl_khdx_xs values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:增加考核教师
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午09:08:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean KhjsInsert(List<String[]> params) throws Exception {
		String sql = "insert into xg_khgl_khdx_js(khjsid,khdxid,khzgh) values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:考核学生取消分配
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午09:35:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean KhxsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_khdx_xs where khdxid=? and khxh=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:考核教师取消分配
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午09:08:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean KhjsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_khdx_js where khdxid=? and khzgh=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean pfcyJsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_pfz_xs where pfzid in(select pfzid from xg_khgl_pfz where khdxid = ?) and khdxr=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean pfcyXsDel(List<String[]> params) throws Exception {
		String sql = "delete from xg_khgl_pfz_js where pfzid in(select pfzid from xg_khgl_pfz where khdxid = ?) and khdxr=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @描述:考核对象列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:27:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhdxList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from xg_khgl_khdx where sczt='0'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	@Override
	public KhdxglForm getModel(KhdxglForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,case when t.khlx='1' then '学生' else '教师' end khlxmc from xg_khgl_khdx t where t.khdxid=?");
		return getModel(sql.toString(), new String[]{model.getKhdxid()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KhdxglForm.class);
		super.setKey("khdxid");
		super.setTableName("xg_khgl_khdx");

	}
	
}

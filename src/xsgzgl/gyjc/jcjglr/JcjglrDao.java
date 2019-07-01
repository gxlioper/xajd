package xsgzgl.gyjc.jcjglr;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.ArrayUtil;

public class JcjglrDao extends SuperDAOImpl<JcjglrForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JcjglrForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcjglrForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		List<String> paraList = new ArrayList<String>();
		sql.append(" select t.*, decode(t.js,'xx','校级','xy','院级','辅导员') jsmc from (");
		sql.append(" select distinct t.ccrq,");
		sql.append(" t.jzrq,");
		sql.append(" t.js,");
		sql.append(" t.aqjc,");
		sql.append(" t.jljc,");
		sql.append(" t.wsjc,");
		sql.append(" t.guid,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" nvl(sum(case when t1.tjzt = '1' then 1 else 0 end),0) tjs,");
		sql.append(" nvl(sum(case when t1.tjzt = '0' then 1 else 0 end),0) wtjs");
		sql.append(" from xg_jhzy_gygl_jcrc t");
		sql.append(" left join xg_jhzy_gygl_jcmx t1");
		sql.append(" on t.guid = t1.rcid");
		sql.append(" where exists (select 1");
		sql.append(" from xg_jhzy_gygl_fpjc t2");
		sql.append(" where t1.jjlx = t2.jjlx");
		sql.append(" and t1.xydm = t2.xydm");
		sql.append(" and t.js = t2.js");
		sql.append(" and t2.zgh = ?)");
		paraList.add(user.getUserName());
		sql.append(" group by t.ccrq,");
		sql.append(" t.jzrq,");
		sql.append(" t.js,");
		sql.append(" t.aqjc,");
		sql.append(" t.jljc,");
		sql.append(" t.wsjc,");
		sql.append(" t.guid,");
		sql.append(" t.xn,");
		sql.append(" t.xq");
		sql.append(" order by t.ccrq desc,t.jzrq desc)t where 1=1");
		sql.append(searchTj);
		ArrayUtil arrayutil = new ArrayUtil();
		//参数重组
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(JcjglrForm.class);
		this.setKey("guid");
		this.setTableName("xg_jhzy_gygl_jcmx");
	}
	
	/**
	 * @throws Exception 

	 * @描述: 获取文明等级List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-20 下午05:02:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCommWpdjList(JcjglrForm t,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.*,");
		sql.append(" decode(t.jjlx,'1','卫生检查','2','安全检查','3','纪律检查')jjlxmc,");
		sql.append(" case when t.tjzt = '1' then ");
		sql.append(" case when t.jjlx = '1' then");
		sql.append(" case when t.wgcnt = '0'");
		sql.append(" then 'A'");
		sql.append(" when t.wgcnt >= '1' and t.wgcnt <= '2'");
		sql.append(" then 'B'");
		sql.append(" else 'C' end");
		sql.append(" else");
		sql.append(" case when t.wgcnt = '0'");
		sql.append(" then 'A'");
		sql.append(" else 'C' end");
		sql.append(" end  ");
		sql.append(" else '' end dj");
		sql.append(" ,decode(t.tjzt,'0','未提交','已提交') tjztmc from ");
		sql.append("  (select distinct t.xydm,t2.xymc,rc.xn,rc.xq, t.lddm,t3.ldmc,t3.ch,t.qsh, t.jjlx, t.tjzt,t.guid mxid,t.rcid,t.tjsj,t4.xm tjrxm,");
		sql.append("  nvl(sum(case when pf.xh is not null then 1 else 0 end) over(partition by t.jjlx,t.guid order by pf.xh), 0)  wgcnt,");
		sql.append("  replace(wm_concat(pf.xh) over(partition by t.jjlx,t.guid order by pf.xh), ';', ',') xh,");
		sql.append("   row_number() over(partition by t.jjlx,t.guid order by pf.xh desc) as ddd");
		sql.append(" from xg_jhzy_gygl_jcmx t");
		sql.append(" left join xg_jhzy_gygl_jcrc rc ");
		sql.append(" on t.rcid = rc.guid");
		sql.append(" left join xg_jhzy_gygl_jcmxbz t1");
		sql.append(" on t.guid = t1.mxid");
		sql.append(" left join xg_jhzy_gygl_pfbz pf");
		sql.append(" on t1.pfid = pf.guid");
		sql.append(" left join (select distinct xydm,xymc from  view_njxyzybj) t2");
		sql.append(" on t.xydm = t2.xydm");
		sql.append(" left join view_xg_gygl_new_qsxx t3");
		sql.append(" on t.lddm || t.qsh = t3.lddm || t3.qsh");
		sql.append(" left join yhb t4");
		sql.append(" on t.tjr = t4.yhm");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotNull(t.getRcid())){
			sql.append(" and t.rcid = ?");
			paraList.add(t.getRcid());
		}
		
		if(StringUtils.isNotNull(t.getFlag()) && ("lr".equals(t.getFlag())  || StringUtils.isNotNull(t.getTjr()))){
			sql.append(" and exists(");
			sql.append(" select 1");
			sql.append(" from xg_jhzy_gygl_fpjc t4");
			sql.append(" where t.jjlx = t4.jjlx");
			sql.append(" and t.xydm = t4.xydm");
			sql.append(" and rc.js = t4.js");
			sql.append(" and t4.zgh = ?");
			sql.append(")");
			paraList.add(user.getUserName());
		}
		if("jgcx".equals(t.getFlag())){
			//默认此菜单只开给学校用户和学院用户
			sql.append(" and rc.js = ?");
			paraList.add(user.getUserStatus());
			if("xy".equals(user.getUserStatus())){
			  sql.append(" and t.xydm = ?");
			  paraList.add(user.getUserDep());
			}
		}
//		if(StringUtils.isNotNull(t.getXydm())){
//			sql.append(" and t.xydm = ?");
//			paraList.add(t.getXydm());
//		}
		if(StringUtils.isNotNull(t.getTjzt())){
			sql.append(" and t.tjzt = ?");
			paraList.add(t.getTjzt());
		}
		sql.append("  ");
		sql.append("  )t where 1=1 and ddd = 1");
		sql.append(searchTj);
		sql.append(" order by xn desc,xq desc,tjsj desc,lddm asc,qsh asc,jjlx asc");
		ArrayUtil arrayutil = new ArrayUtil();
		//参数重组
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:提交记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午05:48:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mxids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tjRecode(String[] mxids,String username) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String tjsj = GetTime.getTimeByFormat("yyyy-MM-dd");
		paraList.add(username);
		paraList.add(tjsj);
		sql.append(" update xg_jhzy_gygl_jcmx set tjzt = '1',tjr = ?,tjsj = ? where  guid in(");
		for (int i = 0; i < mxids.length; i++) {
			sql.append("?");
			paraList.add(mxids[i]);
			if(i != mxids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
		
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-26 下午07:25:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mxids
	 * @param username
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cxRecode(String[] mxids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update xg_jhzy_gygl_jcmx set tjzt = '0',tjr = '',tjsj = '' where  guid in(");
		for (int i = 0; i < mxids.length; i++) {
			sql.append("?");
			paraList.add(mxids[i]);
			if(i != mxids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除原先的明细标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午02:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delOldMxbz(String[] mxidflags) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_jhzy_gygl_jcmxbz where mxid in(");
		for (int i = 0; i < mxidflags.length; i++) {
			sql.append("?");
			paraList.add(mxidflags[i]);
			if(i != mxidflags.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午02:18:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mxidflags
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateMxid(JcjglrForm jcjglr,String fjid) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update xg_jhzy_gygl_jcmx set fjid = ? where rcid = ? and  lddm = ? and qsh =?");
		paraList.add(fjid);
		paraList.add(jcjglr.getRcid());
		paraList.add(jcjglr.getLddm());
		paraList.add(jcjglr.getQsh());
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入检查明细标准表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午02:27:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcjg
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertJcMxBz(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jhzy_gygl_jcmxbz(mxid,pfid,cwh,xh)");
		sql.append(" values(?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:提交记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午05:48:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mxids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tjRecodeForTrans(String[] mxidflags,String username) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String tjsj = GetTime.getTimeByFormat("yyyy-MM-dd");
		paraList.add(username);
		paraList.add(tjsj);
		sql.append(" update xg_jhzy_gygl_jcmx set tjzt = '1',tjr = ?,tjsj = ? where guid in(");
		for (int i = 0; i < mxidflags.length; i++) {
			sql.append("?");
			paraList.add(mxidflags[i]);
			if(i != mxidflags.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
		
	}
	
	/**
	 * 
	 * @描述: 提交时验证是否存在填写了记录无附件的情况
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午04:32:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isFjXbtCheck(String[] mxids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from");
		sql.append(" (select nvl(sum(case");
		sql.append(" when t1.mxid is not null then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end),");
		sql.append(" 0) mxnum,");
		sql.append(" nvl(sum(case");
		sql.append(" when t2.fid is not null then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end),");
		sql.append(" 0) fjnum,");
		sql.append(" t.guid");
		sql.append(" from xg_jhzy_gygl_jcmx t");
		sql.append(" left join xg_jhzy_gygl_jcmxbz t1");
		sql.append(" on t.guid = t1.mxid");
		sql.append(" left join xg_comm_fileupload_data t2");
		sql.append(" on t.fjid = t2.gid");
		sql.append(" where t.guid in(");
//		sql.append(" select rcid || lddm || qsh from xg_jhzy_gygl_jcmx where guid in(");
		for (int i = 0; i < mxids.length; i++) {
			sql.append("?");
			paraList.add(mxids[i]);
			if(i != mxids.length -1){
				sql.append(",");
			}
		}
//		sql.append(")");
		sql.append(" ) group by t.guid");
		sql.append(" ) where mxnum >0 and fjnum = 0");
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 在维护保存时判断附件是否必填
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午04:57:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFjxxList(String fjid,String[] fids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select *  from xg_comm_fileupload_data where  gid = ? ");
		paraList.add(fjid);
		if(fids != null && fids.length >0){
			sql.append("and fid not in(");
			for (int i = 0; i < fids.length; i++) {
				sql.append("?");
				if(i != fids.length-1){
					sql.append(",");
				}
				paraList.add(fids[i]);
			}
			sql.append(")");
		}
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取是否有提交项
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午10:09:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param lddm
	 * @param qsh
	 * @param jjlx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getSfytjx(String rcid,String lddm,String qsh,String jjlx){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_jhzy_gygl_jcmx where rcid = ? and lddm =? and qsh = ? and jjlx = ? and tjzt = '1' ");
		String cnt = dao.getOneRs(sql.toString(), new String[]{rcid,lddm,qsh,jjlx}, "cnt");
		return "0".equals(cnt)?false:true;
	}
	
	/**
	 * 
	 * @描述: 获取已填写的评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午10:22:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param lddm
	 * @param qsh
	 * @param jjlx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcxFxCx(String rcid,String lddm,String qsh,String jjlx){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.guid, t.mxid, t.pfid,t2.wsqkyq,t3.wsqkyq fjmc,t2.fjid");
		sql.append(" from xg_jhzy_gygl_jcmxbz t");
		sql.append(" left join xg_jhzy_gygl_jcmx t1");
		sql.append(" on t.mxid = t1.guid");
		sql.append(" left join xg_jhzy_gygl_pfbz t2");
		sql.append(" on t.pfid = t2.guid");
		sql.append(" left join xg_jhzy_gygl_pfbz t3");
		sql.append(" on t2.fjid = t3.guid");
		sql.append(" where t1.rcid = ?");
		sql.append(" and t1.lddm = ?");
		sql.append(" and t1.qsh = ?");
		sql.append(" and t1.jjlx = ?");
		sql.append(" order by t2.xh");
		return dao.getListNotOut(sql.toString(),new String[]{rcid,lddm,qsh,jjlx});
		
	}
	
	/**
	 * 
	 * @描述: 获取父级下拉框内容
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午11:20:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @param jjlx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFjSelectList(String xydm,String jjlx,String js){
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from xg_jhzy_gygl_pfbz t");
		sql.append(" where t.xydm = ?");
		sql.append(" and t.fjid = 'top'");
		sql.append(" and jjlx = ?");
		sql.append(" and js = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xydm,jjlx,js});
		
	} 
	
	/**
	 * 
	 * @描述: 获取子级下拉框内容
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午11:27:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjSelectList(String xydm,String jjlx,String js){
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from xg_jhzy_gygl_pfbz t");
		sql.append(" where t.xydm = ?");
		sql.append(" and t.fjid != 'top'");
		sql.append(" and jjlx = ?");
		sql.append(" and js = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xydm,jjlx,js});
	}
	
	/**
	 * 
	 * @描述: 获取寝室基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午11:35:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQsjbxx(String lddm,String qsh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ldmc,ch,qsh,xymc from view_xg_gygl_new_qsxx where lddm = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{lddm,qsh});
	}
	
	/**
	 * 
	 * @描述: 获取检查明细List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-25 上午11:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param lddm
	 * @param qsh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcmxList(String rcid,String lddm,String qsh,String username,String flag){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(rcid);
		paraList.add(lddm);
		paraList.add(qsh);
		sql.append(" select t.* ");
		sql.append(" from xg_jhzy_gygl_jcmx t");
		sql.append(" left join xg_jhzy_gygl_jcrc t1");
		sql.append(" on t.rcid = t1.guid");
		sql.append(" where t.rcid = ?");
		sql.append(" and t.lddm = ?");
		sql.append(" and t.qsh = ?");
		if(StringUtils.isNull(flag) && !"jgcx".equals(flag)){
			sql.append(" and exists (select 1");
			sql.append(" from xg_jhzy_gygl_fpjc t3");
			sql.append(" where t3.xydm = t.xydm");
			sql.append(" and t3.js = t1.js");
			sql.append(" and t3.jjlx = t.jjlx");
			sql.append(" and t3.zgh = ?)");
			paraList.add(username);
		}
		
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取文件信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-28 下午03:11:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getWjxxx(String fid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_comm_fileupload_data where fid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{fid});
	}
	

	
	/**
	 * 
	 * @描述: 获取已上传附件信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-28 下午04:13:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param lddm
	 * @param qsh
	 * @param jjlx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYscfjxx(String fjid){
		StringBuilder sql = new StringBuilder();
		sql.append("  select *  from xg_comm_fileupload_data where gid = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{fjid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 在维护保存时判断附件是否必填
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午04:57:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean  delFjxxByfid(String fjid,String[] fids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  delete from xg_comm_fileupload_data where  gid = ? ");
		paraList.add(fjid);
		if(fids != null && fids.length >0){
			sql.append(" and fid not in(");
			for (int i = 0; i < fids.length; i++) {
				sql.append("?");
				if(i != fids.length-1){
					sql.append(",");
				}
				paraList.add(fids[i]);
			}
			sql.append(")");
		}
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入文件名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-28 下午06:37:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fjbList
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertIntoFjb(List<String[]> fjbList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_comm_fileupload_data(fid,originalname,generatename,ext,filesize,uploadtime,gid)values(?,?,?,?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), fjbList);
	}
	
	/**
	 * 
	 * @描述:移动学工查询列表
	 * @作者：cq [工号：785]
	 * @日期：2017-4-24 下午02:27:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdxgList(JcjglrForm t, User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select b.guid,b.js,decode(b.js,'xx','校级','xy','院级','辅导员') jsmc, ");
		sql.append("to_char(to_date(b.ccrq,'yyyy-mm-dd'),'MM\"月\"dd\"日\"') ccrqday, ");
		sql.append("b.ccrq,b.jzrq,count(case when tjzt='1' then 1 else null end) tjs, ");
		sql.append("count(case when tjzt='0' then 1 else null end) wtjs, ");
		sql.append("to_char(floor(TO_NUMBER(TO_DATE(b.jzrq, 'YYYY-MM-DD HH24:MI:SS') + 1 - sysdate) * 24 * 60 * 60)) jzc, ");
		sql.append("to_char(floor(TO_NUMBER(TO_DATE(b.ccrq, 'YYYY-MM-DD HH24:MI:SS') - sysdate) * 24 * 60 * 60)) ksc ");
		sql.append("from xg_jhzy_gygl_jcmx a left join xg_jhzy_gygl_jcrc b on a.rcid=b.guid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.xydm=c.xydm and b.js=c.js and a.jjlx=c.jjlx ");
		sql.append("where zgh = ? ");
		sql.append("group by b.guid,b.js,b.ccrq,b.jzrq order by b.ccrq desc,b.jzrq desc )");
		
		return getPageList(t, sql.toString(), new String[]{user.getUserName()});
	}
	
	
	/**
	 * 
	 * @描述:根据日程ID查询学院列表
	 * @作者：cq [工号：785]
	 * @日期：2017-4-24 下午04:16:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyForRcid(String rcid, User user){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.guid,b.js,decode(b.js,'xx','校级','xy','院级','辅导员') jsmc, ");
		sql.append("a.xydm,(select bmmc from zxbz_xxbmdm t where a.xydm=t.bmdm) xymc, ");
		sql.append("b.ccrq,b.jzrq,count(case when tjzt='1' then 1 else null end) tjs, ");
		sql.append("count(case when tjzt='0' then 1 else null end) wtjs ");
		sql.append("from xg_jhzy_gygl_jcmx a left join xg_jhzy_gygl_jcrc b on a.rcid=b.guid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.xydm=c.xydm and b.js=c.js and a.jjlx=c.jjlx ");
		sql.append("where zgh = ? and a.rcid = ? ");
		sql.append("group by b.guid,b.js,a.xydm,b.ccrq,b.jzrq order by b.ccrq desc,b.jzrq desc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{user.getUserName(),rcid});
	}
	
	
	/**
	 * 
	 * @描述:移动学工录入查询list
	 * @作者：cq [工号：785]
	 * @日期：2017-4-25 下午02:16:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLrcxList(JcjglrForm t, User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> paramet = new ArrayList<String>();
		
		sql.append("select guid,js,ccrq,lddm,ldmc,qsh,qsxb,fjid from ( ");
		sql.append("select a.guid,decode(a.js,'xx','校级','xy','院级','辅导员')js,a.ccrq,b.tjzt,b.jjlx, ");
		sql.append("b.lddm,(select t.ldmc from xg_gygl_new_ldxxb t where b.lddm=t.lddm)ldmc, ");
		sql.append("b.qsh,(select decode(qsxb,'1','男','2','女',qsxb) ");
		sql.append("from xg_gygl_new_qsxxb t where b.lddm=t.lddm and t.qsh=b.qsh) qsxb,fjid ");
		sql.append("from xg_jhzy_gygl_jcrc a left join xg_jhzy_gygl_jcmx b on a.guid=b.rcid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.js=c.js and b.jjlx=c.jjlx and b.xydm=c.xydm ");
		sql.append("where a.guid = ? and c.zgh = ?  and tjzt = '1' ) ");
		
		paramet.add(t.getRcid());
		paramet.add(user.getUserName());
		if(StringUtils.isNotNull(t.getSearchModel().getInput_mhcx())){
			sql.append("where ldmc like '%'||?||'%' or qsh like '%'||?||'%' ");
			paramet.add(t.getSearchModel().getInput_mhcx());
			paramet.add(t.getSearchModel().getInput_mhcx());
		}
		
		sql.append("group by guid,js,ccrq,lddm,ldmc,qsh,qsxb,fjid order by lddm,qsh");
		
		return getPageList(t, sql.toString(), paramet.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @描述:获取录入查询明细
	 * @作者：cq [工号：785]
	 * @日期：2017-4-25 下午02:22:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLrcxMx(String rcid, User user, List<HashMap<String, String>> mxList){
		
		StringBuilder sql = new StringBuilder();
		List<String> paramet = new ArrayList<String>();
		
		sql.append("select a.lddm,a.qsh,decode(a.jjlx,'1','卫生检查','2','安全检查','3','纪律检查')jjlxmc, ");
		sql.append("case when a.jjlx='1' and (b.ct is null or b.ct = 0) then 'A' when a.jjlx='1' and b.ct between 1 and 2 then 'B' ");
		sql.append("when a.jjlx='1' and b.ct>2 then 'C' when (a.jjlx='2' or a.jjlx='3') and  (b.ct is null or b.ct = 0) then 'A' ");
		sql.append("else 'C' end dj ");
		sql.append("from xg_jhzy_gygl_jcmx a left join (select mxid,count(1) ct from xg_jhzy_gygl_jcmxbz where pfid is not null group by mxid) b ");
		sql.append("on a.guid=b.mxid left join xg_jhzy_gygl_jcrc c on a.rcid=c.guid ");
		sql.append("where a.rcid = ? and (a.xydm,a.jjlx,c.js) in (select xydm,jjlx,js from xg_jhzy_gygl_fpjc where zgh = ?) ");
		sql.append("and a.tjzt='1' and ( ");
		paramet.add(rcid);
		paramet.add(user.getUserName());
		for (int i = 0; i < mxList.size(); i++) {
			if(i>0){
				sql.append(" or ");
			}
			sql.append("(lddm = ? and qsh = ?) ");
			paramet.add(mxList.get(i).get("lddm"));
			paramet.add(mxList.get(i).get("qsh"));
		}
		sql.append(") order by lddm,qsh,a.jjlx");
		
		
		return dao.getListNotOut(sql.toString(), paramet.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:批量提交查询
	 * @作者：cq [工号：785]
	 * @日期：2017-4-26 上午08:54:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPlList(JcjglrForm t, User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		List<String> paramet = new ArrayList<String>();
		
		sql.append("select ccrq,jzrq,js,jsmc,lddm,ldmc, ");
		sql.append("qsh,wm_concat(ws) ws,wm_concat(aq) aq,wm_concat(jl) jl,fjid from ( ");
		sql.append("select a.ccrq,a.jzrq,a.js,b.lddm,decode(a.js, 'xx', '校级', 'xy', '院级', '辅导员') jsmc, ");
		sql.append("(select t1.ldmc from xg_gygl_new_ldxxb t1 where t1.lddm=b.lddm) ldmc, ");
		sql.append("b.qsh,case when b.jjlx='1' and d.ct>0 and b.fjid is null then 'false' when b.jjlx='1' then b.guid end ws, ");
		sql.append("case when b.jjlx='2' and d.ct>0 and b.fjid is null then 'false' when b.jjlx='2' then b.guid end aq, ");
		sql.append("case when b.jjlx='3' and d.ct>0 and b.fjid is null then 'false' when b.jjlx='3' then b.guid end jl,fjid ");
		sql.append("from xg_jhzy_gygl_jcrc a left join xg_jhzy_gygl_jcmx b on a.guid=b.rcid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.js=c.js and b.jjlx=c.jjlx and b.xydm=c.xydm ");
		sql.append("left join (select mxid,count(1) ct from xg_jhzy_gygl_jcmxbz where pfid is not null group by mxid) d on b.guid=d.mxid ");
		sql.append("where to_char(sysdate,'yyyy-mm-dd') between ccrq and jzrq and tjzt <> '1' ");
		if(StringUtils.isNotNull(t.getRcid())){
			sql.append("and a.guid = ? ");
			paramet.add(t.getRcid());
		}
		
		sql.append("and c.zgh = ? and b.tjzt<>'1') t ");
		paramet.add(user.getUserName());
		if(StringUtils.isNotNull(t.getSearchModel().getInput_mhcx())){
			sql.append("where ldmc like '%'||?||'%' or qsh like '%'||?||'%' ");
			paramet.add(t.getSearchModel().getInput_mhcx());
			paramet.add(t.getSearchModel().getInput_mhcx());
		}
		
		sql.append("group by lddm,ldmc,qsh,fjid,ccrq,jzrq,js,jsmc order by lddm,qsh ");
		
		return dao.getListNotOut(sql.toString(), paramet.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:卫生分录入，随机第一条记录
	 * @作者：cq [工号：785]
	 * @日期：2017-4-27 下午04:24:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getOneWsf(JcjglrForm model, User user) {
		
		StringBuilder sql = new StringBuilder();
		List<String> paramet = new ArrayList<String>();
		
		sql.append("select * from ( ");
		sql.append("select rcid,ccrq,jzrq,lddm,js,(select ldmc from xg_gygl_new_ldxxb t1 where t.lddm=t1.lddm)ldmc,");
		sql.append("(select xydm from xg_gygl_new_qsxxb t1 where t.lddm=t1.lddm and t.qsh=t1.qsh) xydm, ");
		sql.append("qsh,tjzt,wm_concat(ws)ws,wm_concat(aq)aq,wm_concat(jl)jl from( ");
		sql.append("select a.guid rcid,b.guid mxid,a.ccrq,a.jzrq,b.lddm,b.qsh,b.jjlx,a.js,b.tjzt,");
		sql.append("case when b.jjlx = '1' then b.guid end ws,case when b.jjlx = '2' then b.guid end aq, ");
		sql.append("case when b.jjlx = '3' then b.guid end jl ");
		sql.append("from xg_jhzy_gygl_jcrc a ");
		sql.append("left join xg_jhzy_gygl_jcmx b on a.guid = b.rcid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.js = c.js and b.jjlx = c.jjlx and b.xydm = c.xydm ");
		sql.append("where a.guid in (select guid from xg_jhzy_gygl_jcrc where to_char(sysdate,'yyyy-mm-dd') between ccrq and jzrq) and c.zgh = ? ");
		paramet.add(user.getUserName());
		if("0".equals(model.getTjzt())){
			sql.append("and b.tjzt <> 1  ");
		}
		if(StringUtils.isNotNull(model.getLddm())){
			sql.append("and lddm=? and qsh=? ");
			paramet.add(model.getLddm());
			paramet.add(model.getQsh());
		}
		sql.append(")t group by rcid,js,ccrq,jzrq,lddm,qsh,tjzt ");
		
		if(StringUtils.isNotNull(model.getRcid())){
			sql.append("order by decode(rcid, ? ,'',rcid) nulls first,lddm,qsh ");
			paramet.add(model.getRcid());
		}
		sql.append(") where rownum =1 order by lddm,qsh ");
		return dao.getMapNotOut(sql.toString(), paramet.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:根据楼栋代码和寝室号获取寝室成员
	 * @作者：cq [工号：785]
	 * @日期：2017-4-27 下午04:37:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQscy(String lddm, String qsh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,b.xm,b.xymc,a.cwh, ");
		sql.append("(select nvl2(t.xh,'寝室长','') from xg_gygl_new_gyglryb t ");
		sql.append("where rzzt = '在任' and a.lddm=t.lddm and a.qsh=t.qsh and a.xh=t.xh) qsz ");
		sql.append("from xg_gygl_new_cwxxb a ");
		sql.append("left join view_xsbfxx b on a.xh=b.xh ");
		sql.append("where lddm = ? and qsh = ? ");
		sql.append("order by to_number(cwh) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{lddm,qsh});
	}
	
	public List<HashMap<String, String>> getQsxx(){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm,(select ldmc from xg_gygl_new_ldxxb b where a.lddm=b.lddm)ldmc,ch,qsh ");
		sql.append("from xg_gygl_new_qsxxb a order by lddm,ch,qsh ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	public HashMap<String, String> getRczt(String rcid,User user){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.*,decode(b.js, 'xx', '校级', 'xy', '院级', '辅导员') jsmc, ");
		sql.append("case when trunc(sysdate,'dd') > to_date(b.jzrq,'YYYY-MM-DD') then 'false' else 'true' end zt, ");
		sql.append("count(case when tjzt = '1' then 1 else null end) tjcou, ");
		sql.append("count(case when tjzt = '1' then null else 1 end) wtjcou ");
		sql.append("from xg_jhzy_gygl_jcmx a left join xg_jhzy_gygl_jcrc b on a.rcid=b.guid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.xydm=c.xydm and b.js=c.js and a.jjlx=c.jjlx ");
		sql.append("where zgh = ? and a.rcid = ? ");
		sql.append("group by b.guid,b.ccrq,b.jzrq,b.wsjc,b.aqjc,b.jljc,b.js,b.xq,b.xn,b.ls,b.xydm ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{user.getUserName(),rcid});
		
	}
	/**
	 * 
	 * @描述:根据guid 和姓名获取检查日程状态和提交未提交数
	 * @作者：cq [工号：785]
	 * @日期：2017-5-2 下午04:07:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBzList(String jjlx, String js,String xydm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.guid rcid,b.guid bzid,b.jjlx,b.js ,a.wsqkyq fjwsqkyq,b.wsqkyq ");
		sql.append("from (select * from XG_JHZY_GYGL_PFBZ where fjid='top') a ");
		sql.append("left join (select * from XG_JHZY_GYGL_PFBZ where fjid<>'top') b on a.guid=b.fjid ");
		sql.append("where b.jjlx= ? and b.js = ? and a.xydm=? and b.guid is not null order by to_number(a.xh),to_number(b.xh) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{jjlx,js,xydm});
		
	}
	
	
	public List<HashMap<String, String>> getQsxx(JcjglrForm t, User user){
		
		StringBuilder sql = new StringBuilder();
		List<String> paramet = new ArrayList<String>();
		
		sql.append(" select t.* from view_xg_gygl_new_qsxx t where t.yzcws !='0' ");
		if(!StringUtils.isNull(t.getRcid())){
			sql.append("and (t.lddm,t.qsh) in ( ");
			sql.append("select b.lddm,b.qsh from xg_jhzy_gygl_jcrc a ");
			sql.append("left join xg_jhzy_gygl_jcmx b on a.guid = b.rcid ");
			sql.append("left join xg_jhzy_gygl_fpjc c on a.js = c.js and b.jjlx = c.jjlx and b.xydm = c.xydm ");
			sql.append("where a.guid = ? and c.zgh = ? and b.tjzt<>'1')");
			paramet.add(t.getRcid());
			paramet.add(user.getUserName());
		}else{
			sql.append(" and xydm in (select xydm from xg_jhzy_gygl_fpjc where zgh = '"+
					user.getUserName()
					+"' and  jjlx = ? and js=?)");
			paramet.add(t.getJjlx());
			paramet.add(t.getJs());
			
		}
		sql.append(" order by t.lddm,t.ch,t.qsh");
		return dao.getListNotOut(sql.toString(), paramet.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:根据一条结果查询获取评分标准
	 * @作者：cq [工号：785]
	 * @日期：2017-5-5 上午09:41:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultMap
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfbzForResu(JcjglrForm form, User user) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select b.lddm,(select t.ldmc from xg_gygl_new_ldxxb t where t.lddm=b.lddm) ldmc,b.qsh, ");
		sql.append("(select nvl2(t1.xh,'寝室长','') from xg_gygl_new_gyglryb t1 where rzzt = '在任' and b.lddm=t1.lddm and b.qsh=t1.qsh and d.xh=t1.xh) qsz, ");
		sql.append("b.rcid,b.guid mxid,b.jjlx,b.tjzt,d.pfid,e.fjwsqkyq,e.wsqkyq,b.fjid,d.xh,d.cwh,(select xm from xsxxb t where d.xh=t.xh)xm from xg_jhzy_gygl_jcrc a ");
		sql.append("left join xg_jhzy_gygl_jcmx b on a.guid = b.rcid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.js = c.js and b.jjlx = c.jjlx and b.xydm = c.xydm ");
		sql.append("left join xg_jhzy_gygl_jcmxbz d on b.guid=d.mxid ");
		sql.append("left join (select b.*,a.wsqkyq fjwsqkyq from xg_jhzy_gygl_pfbz a left join xg_jhzy_gygl_pfbz b on a.guid=b.fjid ");
		sql.append("where a.fjid='top' and b.guid is not null ) e on d.pfid=e.guid ");
		sql.append("where a.guid = ? and c.zgh = ? and b.lddm = ? and b.qsh = ? order by jjlx,d.cwh");
		
		return dao.getListNotOut(sql.toString(), new String[]{form.getRcid(),user.getUserName(),form.getLddm(),form.getQsh()});
	}
	
	/**
	 * 
	 * @描述: 查看时查询各项等级
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-5-9 下午02:56:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFxdjcxForView(String rcid,String xydm,String qsh,String lddm,String flag,String jcrq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		if(StringUtils.isNotNull(flag) && "jc".equals(flag)){
//			sql.append(" select   case");
//			sql.append(" when t.jjlx = '1' then");
//			sql.append(" case");
//			sql.append(" when t.wgcnt = '0' then");
//			sql.append(" 'A'");
//			sql.append(" when t.wgcnt >= '1' and t.wgcnt <= '2' then");
//			sql.append(" 'B'");
//			sql.append(" else");
//			sql.append(" 'C'");
//			sql.append(" end");
//			sql.append(" else");
//			sql.append(" case");
//			sql.append(" when t.wgcnt = '0' then");
//			sql.append(" 'A'");
//			sql.append(" else");
//			sql.append(" 'C'");
//			sql.append(" end");
//			sql.append(" end dj,t.jjlx,t.xh,t.xm,t.tjsj jcrq from (");
//			sql.append(" select nvl(sum(case when t2.xh is not null then 1 else 0 end),0) wgcnt,t.jjlx,t3.xm,t.tjsj,replace(wm_concat(t2.xh),';',',') xh  from xg_jhzy_gygl_jcmx t");
//			sql.append(" left join xg_jhzy_gygl_jcmxbz t1");
//			sql.append(" on t.guid = t1.mxid");
//			sql.append(" left join xg_jhzy_gygl_pfbz t2");
//			sql.append(" on t1.pfid = t2.guid");
//			sql.append(" left join yhb t3");
//			sql.append(" on t.tjr = t3.yhm");
//			sql.append(" where t.rcid = ? and t.xydm = ? and t.qsh = ? and t.lddm = ? and t.tjzt = '1'");
//			sql.append(" group by t.jjlx,xm,tjsj) t");
			sql.append(" select t.*,");
			sql.append("   case");
			sql.append(" when t.jjlx = '1' then");
			sql.append(" case");
			sql.append(" when t.wgcnt = '0' then");
			sql.append(" 'A'");
			sql.append(" when t.wgcnt >= '1' and t.wgcnt <= '2' then");
			sql.append(" 'B'");
			sql.append(" else");
			sql.append(" 'C'");
			sql.append(" end");
			sql.append(" else");
			sql.append(" case");
			sql.append(" when t.wgcnt = '0' then");
			sql.append(" 'A'");
			sql.append(" else");
			sql.append(" 'C'");
			sql.append(" end");
			sql.append(" end dj from (");
			sql.append(" select nvl(sum(case");
			sql.append(" when t2.xh is not null then");
			sql.append(" 1");
			sql.append(" else");
			sql.append(" 0");
			sql.append(" end) over(partition by t.jjlx order by t2.xh),");
			sql.append(" 0) wgcnt,");
			sql.append(" t.jjlx,");
			sql.append(" t3.xm,");
			sql.append(" t.tjsj jcrq,");
			sql.append(" replace(wm_concat(t2.xh)");
			sql.append(" over(partition by t.jjlx order by t2.xh),");
			sql.append(" ';',");
			sql.append(" ',') xh,");
			sql.append(" row_number() over(partition by t.jjlx order by t2.xh desc) as ddd");
			sql.append(" from xg_jhzy_gygl_jcmx t");
			sql.append(" left join xg_jhzy_gygl_jcmxbz t1");
			sql.append(" on t.guid = t1.mxid");
			sql.append(" left join xg_jhzy_gygl_pfbz t2");
			sql.append(" on t1.pfid = t2.guid");
			sql.append(" left join yhb t3");
			sql.append(" on t.tjr = t3.yhm");
			sql.append(" where t.rcid = ?");
			sql.append(" and t.xydm = ?");
			sql.append(" and t.qsh = ?");
			sql.append(" and t.lddm = ?");
			sql.append("  and t.tjzt = '1')t where ddd =1");
			
			paraList.add(rcid);
			paraList.add(xydm);
			paraList.add(qsh);
			paraList.add(lddm);
		}else if(StringUtils.isNotNull(flag) && "cc".equals(flag)){
//			sql.append(" select case");
//			sql.append("  when t.jjlx = '1' then");
//			sql.append(" case when t.wgcnt = '0' then");
//			sql.append(" 'A' when t.wgcnt >= '1' and t.wgcnt <= '2' then");
//			sql.append(" 'B'");
//			sql.append(" else");
//			sql.append(" 'C'");
//			sql.append(" end");
//			sql.append(" else");
//			sql.append(" case");
//			sql.append(" when t.wgcnt = '0' then");
//			sql.append(" 'A'");
//			sql.append(" else");
//			sql.append(" 'C'");
//			sql.append(" end");
//			sql.append(" end dj,");
//			sql.append(" t.jjlx,");
//			sql.append(" t.xh,");
//			sql.append(" t.xm,");
//			sql.append(" t.jcrq");
//			sql.append(" from (select nvl(sum(case");
//			sql.append(" when t1.xh is not null then");
//			sql.append(" 1");
//			sql.append(" else");
//			sql.append(" 0");
//			sql.append(" end),");
//			sql.append(" 0) wgcnt,");
//			sql.append(" t.jjlx,");
//			sql.append(" t3.xm,");
//			sql.append(" t.jcrq,");
//			sql.append(" replace(wm_concat(t1.xh), ';', ',') xh");
//			sql.append(" from xg_jhzy_gygl_ccmx t");
//			sql.append(" left join (select t.jcrq, t.xydm, t.lddm, t.qsh, t1.jjlx, t1.xh");
//			sql.append(" from xg_jhzy_gygl_ccmxbz t");
//			sql.append(" left join xg_jhzy_gygl_pfbz t1");
//			sql.append(" on t.pfid = t1.guid) t1");
//			sql.append(" on t.lddm || t.qsh || t.jcrq || t.jjlx =t1.lddm || t1.qsh || t1.jcrq || t1.jjlx");
//			sql.append(" left join yhb t3");
//			sql.append(" on t.tjr = t3.yhm");
//			sql.append(" where t.xydm = ?");
//			sql.append(" and t.qsh = ?");
//			sql.append(" and t.lddm = ?");
//			sql.append(" and t.jcrq = ?");
//			sql.append(" group by t.jjlx, xm, t.jcrq) t");
			sql.append(" select case");
			sql.append(" when t.jjlx = '1' then");
			sql.append(" case when t.wgcnt = '0' then");
			sql.append(" 'A' when t.wgcnt >= '1' and t.wgcnt <= '2' then");
			sql.append(" 'B'");
			sql.append(" else");
			sql.append(" 'C'");
			sql.append(" end");
			sql.append(" else");
			sql.append(" case");
			sql.append(" when t.wgcnt = '0' then");
			sql.append(" 'A'");
			sql.append(" else");
			sql.append(" 'C'");
			sql.append(" end");
			sql.append(" end dj,");
			sql.append(" t.*");
			sql.append(" from (");
			sql.append(" select ");
			sql.append(" t.jjlx,");
			sql.append(" t3.xm,");
			sql.append(" t.jcrq,");
			sql.append(" nvl(sum(case when t1.xh is not null then 1 else 0 end) over(partition by t.jjlx order by t1.xh), 0)  wgcnt,");
			sql.append(" replace(wm_concat(t1.xh) over(partition by t.jjlx order by t1.xh), ';', ',') xh,");
			sql.append(" row_number() over(partition by t.jjlx order by t1.xh desc) as ddd");
			sql.append(" from xg_jhzy_gygl_ccmx t");
			sql.append(" left join (select t.jcrq, t.xydm, t.lddm, t.qsh, t1.jjlx, t1.xh");
			sql.append(" from xg_jhzy_gygl_ccmxbz t");
			sql.append(" left join xg_jhzy_gygl_pfbz t1");
			sql.append(" on t.pfid = t1.guid) t1");
			sql.append(" on t.lddm || t.qsh || t.jcrq || t.jjlx =t1.lddm || t1.qsh || t1.jcrq || t1.jjlx");
			sql.append(" left join yhb t3");
			sql.append(" on t.tjr = t3.yhm");
			sql.append(" where t.xydm = ? ");
			sql.append(" and t.qsh = ? ");
			sql.append(" and t.lddm = ? ");
			sql.append(" and t.jcrq = ? ");
			sql.append(" ) t where ddd = 1");
			sql.append(" ");
			sql.append(" ");
			paraList.add(xydm);
			paraList.add(qsh);
			paraList.add(lddm);
			paraList.add(jcrq);
		}else {
			return new ArrayList<HashMap<String,String>>();
		}
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
		
	}
	
	/**
	 * 寝室检查list
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> qsjcList(JcjglrForm t, User user) throws Exception{
		
		List<String> paramet = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		sql.append("select * from (");
		sql.append("select rcid,ccrq,jzrq,js,xydm,xymc,lddm,ldmc,qsh,zgh,tjzt,jsmc, ");
		sql.append("case when (select count(1) from xg_jhzy_gygl_jcmx t1 where t1.lddm =t.lddm and t1.qsh = t.qsh and t1.rcid= t.rcid and tjzt = '0') > 0 ");
		sql.append("then '未检完' when count(decode(dj,'C',1,null))>0 then 'C' ");
		sql.append("when count(decode(dj,'B',1,null))>0 then 'B' when count(decode(dj,'A',1,null))>0 then 'A' end qsdj ");
		sql.append("from (select a.guid rcid,a.ccrq,a.jzrq,a.js,decode(a.js, 'xx', '校级', 'xy', '院级', '辅导员') jsmc, ");
		sql.append("b.xydm,(select bmmc from zxbz_xxbmdm t where t.bmdm=b.xydm) xymc, ");
		sql.append("b.lddm,(select ldmc from xg_gygl_new_ldxxb t where b.lddm=t.lddm) ldmc,b.qsh,c.zgh,b.tjzt, ");
		sql.append("case when b.jjlx = '1' and (d.ct is null or d.ct = 0) then 'A' ");
		sql.append("when b.jjlx = '1' and d.ct between 1 and 2 then 'B' when b.jjlx = '1' and d.ct > 2 then  'C' ");
		sql.append("when (b.jjlx = '2' or b.jjlx = '3') and (d.ct is null or d.ct = 0) then 'A' else 'C' end dj ");
		sql.append("from xg_jhzy_gygl_jcrc a left join xg_jhzy_gygl_jcmx b on a.guid = b.rcid ");
		sql.append("left join xg_jhzy_gygl_fpjc c on a.js = c.js and b.jjlx = c.jjlx and b.xydm = c.xydm ");
		sql.append("left join (select mxid, count(1) ct from xg_jhzy_gygl_jcmxbz where pfid is not null group by mxid) d ");
		sql.append("on b.guid = d.mxid) t where zgh = ? ");
		paramet.add(user.getUserName());
		if("1".equals(t.getTjzt())){
			sql.append("and ((to_char(sysdate,'yyyy-mm-dd') > jzrq and tjzt='1') "); 
			sql.append("or (to_char(sysdate,'yyyy-mm-dd') between ccrq and jzrq and tjzt ='1' ) ) ");
		}else{
			sql.append("and to_char(sysdate,'yyyy-mm-dd') between ccrq and jzrq and tjzt <> '1' ");
		}
		
		sql.append("group by rcid,ccrq,jzrq,js,xydm,xymc,lddm,ldmc,qsh,zgh,tjzt,jsmc ");
		sql.append("order by ccrq desc,js,lddm,qsh ");
		sql.append(" )where 1=1 ");
		sql.append(searchTj);
		ArrayUtil arrayutil = new ArrayUtil();
		//参数重组
		String[] inputVnew = arrayutil.unionArray(paramet.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}
	
}

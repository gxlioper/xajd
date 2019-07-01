package com.zfsoft.xgxt.jskp.lxsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;

public class LxsqDao extends SuperDAOImpl<LxsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(LxsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(LxsqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		/**由于后面需求改动，把这部分代码给隔离出来，现在上面这一段才符合学校的需求2018-03-07*/
		if("0".equals(new CsszDao().getSfsh())){
			sql.append(" select * from (");
			sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
			sql.append(" t5.shzt shzt1,decode(t5.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t5.shzt) shztmc,");
			sql.append(" t5.xh,t6.nj,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc");
			sql.append(" from xg_jskp_xmsqb t");
			sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
			sql.append(" on t.fzr = t2.yhm");
			sql.append(" left join zxbz_xxbmdm t3");
			sql.append(" on t.zdbm = t3.bmdm");
			sql.append(" left join xg_jskp_xmlbb t4");
			sql.append(" on t.xmlb = t4.xmlbdm");
			sql.append(" left join xg_jskp_xmsbb t5 on t5.sqid = t.sqid ");
			sql.append(" left join view_xsbfxx t6 on t5.xh = t6.xh ");
			sql.append(")a where 1=1");
			sql.append(searchTj);
			sql.append(searchTjByUser);
			sql.append(" order by lxsj desc");
		}else{
			sql.append(" select * from (");
			sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
			sql.append(" decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc");
			sql.append(" from xg_jskp_xmsqb t");
			sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
			sql.append(" on t.fzr = t2.yhm");
			sql.append(" left join zxbz_xxbmdm t3");
			sql.append(" on t.zdbm = t3.bmdm");
			sql.append(" left join xg_jskp_xmlbb t4");
			sql.append(" on t.xmlb = t4.xmlbdm");
			//sql.append(" where fzr = '"+user.getUserName()+"'");
			sql.append(")a where 1=1");
			sql.append(searchTj);
			sql.append(" order by lxsj desc");
		}
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LxsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_jskp_xmsqb");
	}
	
	/**
	 * 
	 * @描述: 负责人信息(学生)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 上午11:53:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxStu(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xsxxb where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{username});
	}
	
	/**
	 * 
	 * @描述: 负责人信息(老师)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 上午11:54:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxTea(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{username});
	}
	
	/**
	 * 
	 * @描述: 获取可获得的学号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 下午05:11:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAvailableXhList(String[] xhs,String sqid,String ryflag){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select * from xsxxb where xh in (");
		for (int i = 0; i < xhs.length; i++) {
			sql.append("?");
			if(i != xhs.length-1){
				sql.append(",");
			}
			paraList.add(xhs[i]);
		}
		sql.append(" )");
		if("xg".equals(ryflag)){
			sql.append(" and xh not in(");
			sql.append(" select xh from xg_jskp_xmsbb where xmid = ? union ");
			sql.append(" select xh from xg_jskp_xmsbjgb where xmid = ?");
			sql.append(")");
			paraList.add(sqid);
			paraList.add(sqid);
		}
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 上午09:16:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRy(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_jskp_xmsbb where xmid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>=0 ? true:false;
	}
	
	/**
	 * 
	 * @描述: 删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 上午10:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRys(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_jskp_xmsbb where xmid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 上午10:29:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRy(List<String[]> params) throws Exception{
		String sql = " insert into xg_jskp_xmsbb(xh,xmid,hjsj,sbsj,splcid,shzt,lxzt)values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*当参数设置不需要审核时，插入人员*/
	public boolean saveRyOne(List<String[]> params) throws Exception{
		String sql = " insert into xg_jskp_xmsbb(sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt,fjid)values(?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 下午02:36:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(LxsqForm lxsq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt ");
		sql.append(" from (select t.*,");
		sql.append(" case when t.lxsj < substr(t.lxsj, 0, 4) || '-' || t1.cs1 then");
		sql.append(" (substr(t.lxsj, 0, 4) - 1) || '-' || substr(t.lxsj, 0, 4)");
		sql.append(" else");
		sql.append(" substr(t.lxsj, 0, 4) || '-' || (substr(t.lxsj, 0, 4) + 1)");
		sql.append(" end xn");
		sql.append(" from (");
		sql.append(" select xmmc,xmid sqid,lxsj from xg_jskp_xmjgb where xmdl = 'gdx'");
		sql.append(" union ");
		sql.append(" select xmmc,sqid,lxsj from xg_jskp_xmsqb");
		sql.append("  )t");
		sql.append(" left join xg_zqb t1	");
		sql.append(" on 1 = 1) t");
		sql.append(" where xn =");
		sql.append(" (select case when ? < substr(?, 0, 4) || '-' || cs1 then");
		sql.append(" (substr(?, 0, 4) - 1) || '-' || substr(?, 0, 4)");
		sql.append(" else substr(?, 0, 4) || '-' || (substr(?, 0, 4) + 1) end xn");
		sql.append(" from XG_ZQB) and xmmc =?");
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getXmmc());
		if(StringUtils.isNotNull(lxsq.getSqid())){
			sql.append(" and sqid != ?");
			paraList.add(lxsq.getSqid());
		}
		//不存在为true,存在未false
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 验证学号、项目名称、立项时间是否重复
	 */
	public boolean checkForPara(LxsqForm lxsq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)count from xg_jskp_xmsqb a,xg_jskp_xmsbb b ");
		sql.append("where a.sqid = b.sqid and a.xmmc = ? and a.lxsj = ? and b.xh = ?");
		paraList.add(lxsq.getXmmc());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getXh());
		if(StringUtils.isNotNull(lxsq.getSqid())){
			sql.append(" and a.sqid != ?");
			paraList.add(lxsq.getSqid());
		}
		String count = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "count");
		return "0".equals(count) ? true : false;
	}
	
	/**
	 * 
	 * @描述:获取项目参与人员的学号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-12 下午02:31:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmcyryXhs(String xmid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,t1.XM,t.sqid from xg_jskp_xmsbb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH where t.xmid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xmid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-12 下午04:55:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyszList(LxsqForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.sqid,t1.xmmc,");
		sql.append(" t3.xmlbmc,");
		sql.append(" t.xh,");
		sql.append(" t4.xm,");
		sql.append(" decode(t.shzt,'0','未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '已退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" '1' sjly,");
		sql.append(" t.shzt");
		sql.append(" from xg_jskp_xmsbb t ");
		sql.append(" left join xg_jskp_xmsqb t1");
		sql.append(" on t.xmid = t1.sqid	");
		sql.append(" left join xg_jskp_xmlbb t3");
		sql.append(" on t1.xmlb = t3.xmlbdm");
		sql.append(" left join xsxxb t4");
		sql.append(" on t.xh = t4.xh");
		sql.append(" where t1.sqid = ?");
		sql.append(" union");
		sql.append(" select t.jgid sqid,t1.xmmc, t3.xmlbmc, t.xh, t4.xm, '通过' shztmc, t.sjly, '1' shzt");
		sql.append(" from xg_jskp_xmsbjgb t");
		sql.append(" left join xg_jskp_xmsqb t1");
		sql.append(" on t.xmid = t1.sqid");
		sql.append(" left join xg_jskp_xmlbb t3");
		sql.append(" on t1.xmlb = t3.xmlbdm");
		sql.append(" left join xsxxb t4");
		sql.append(" on t.xh = t4.xh");
		sql.append("  where t1.sqid = ?) where 1=1");
		paraList.add(t.getSqid());
		paraList.add(t.getSqid());
		if(StringUtils.isNotNull(t.getXh())){
			sql.append("and (xh like ? or xm like ?)");
			paraList.add("%"+t.getXh()+"%");
			paraList.add("%"+t.getXh()+"%");
		}
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 上午10:18:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRys(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsbb(sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt)values(?,?,?,?,?,?,?,?)");
		return dao.runBatchNotCommit(sql.toString(), params);
	}
	
	/**
	 * 
	 * @描述:获取部门名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 下午03:20:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBmmc(String bmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmmc from zxbz_xxbmdm where bmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{bmdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-20 下午04:24:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXmry(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < sqids.length; i++) {
			paraList.add(new String[]{sqids[i]});
		}
		sql.append(" delete from xg_jskp_xmsbb where sqid = ?");
		boolean rs = dao.runBatchBoolean(sql.toString(), paraList);
		if(rs){
			sql.setLength(0);
			sql.append(" delete from xg_jskp_xmsbjgb where jgid = ?");
			rs = dao.runBatchBoolean(sql.toString(), paraList);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 是否所有记录都未被审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-21 上午09:34:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotHaveShjl(String[] ywids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xtwh_shztb t ");
		sql.append(" left join xg_jskp_xmsbb t1 ");
		sql.append(" on t.ywid = t1.sqid ");
		sql.append(" where t1.shzt != '3' and t.shzt != '0' and t.ywid in(");//需要过滤到已提交但未审核的记录；还有已退回到申请人的记录
		for (int i = 0; i < ywids.length; i++) {
			sql.append("?");
			if(i != ywids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String rs = dao.getOneRs(sql.toString(),ywids, "cnt");
		return "0".equals(rs) ? true : false;
	}
	
	/**
	 * @描述: 判断学生的年级是否大于等于2017
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-17 下午04:15:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isStandardStu(String userName) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from view_xsbfxx where nj >= 2017 and xh = ? ");
		String rs = dao.getOneRs(sql.toString(), new String[]{userName}, "cnt");
		return "1".equals(rs) ? true : false;
	}
	
	/**
	 * @描述: 取项目类别表数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-23 上午11:22:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlbList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**取项目类别表数据，刨除能力素质一类*/
	public List<HashMap<String,String>> getXmlbListNotLikeNl() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb where xmlbmc not like '%能力素养%'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 取项目类别表数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-23 上午11:22:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZdbmList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm zdbm,bmmc zdbmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**短学期数据*/
	public List<HashMap<String,String>> getDxqList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select dxqdm,dxqmc from xg_jskp_dxq order by to_number(dxqdm)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 验证用人单位是否存在
	 */
	public String getZdbmdm(String zdbmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm zdbmdm from ZXBZ_XXBMDM where bmmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{zdbmmc}, "zdbmdm");
	}
	
	/**
	 * 验证项目类别是否存在
	 */
	public String getXmlbdm(String xmlbmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm from xg_jskp_xmlbb where xmlbmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * 验证负责人是否存在
	 */
	public String getFzr(String fzrxm){
		StringBuilder sql = new StringBuilder();
		sql.append("select gh from ( ");
		sql.append("select xh gh,xm from xsxxb ");
		sql.append("union all ");
		sql.append("select zgh gh,xm from fdyxxb ");
		sql.append(") where xm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{fzrxm}, "gh");
	}
	
	/**
	 * 验证参与人是否存在
	 */
	public String getCyr(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
	}
	
	/**
	 * 主键验证
	 */
	public boolean checkIsNotExists(String xh, String xmmc, String cysj){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*) num from (");
		sql.append("select * from xg_jskp_xmsqb a left join xg_jskp_xmsbb b on a.sqid = b.xmid");
		sql.append(") where xh = ? and xmmc = ? and lxsj = ? ");
		paraList.add(xh);
		paraList.add(xmmc);
		paraList.add(cysj);
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * 导入批量插入xg_jskp_xmsqb
	 */
	public int[] saveDrSqbData(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsqb( ");
		sql.append("sqid,xmmc,zdbm,xmlb,lxsj,fzr,fzrlxfs,zdls,zdlslxfs,lxly,splcid,shzt,zdf,zxf,sjly,lxxn,sjlrr");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * 导入批量插入xg_jskp_xmsbb
	 */
	public int[] saveDrSbbData(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsbb( ");
		sql.append("sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt");
		sql.append(" )values(?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @描述: 根据ID查询学生申报项目的信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-4 上午09:03:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuSbDataList(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
		sql.append(" t5.shzt shzt1,decode(t5.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t5.shzt) shztmc,");
		sql.append(" t5.xh,t6.nj,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc");
		sql.append(" from xg_jskp_xmsqb t");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
		sql.append(" on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.zdbm = t3.bmdm");
		sql.append(" left join xg_jskp_xmlbb t4");
		sql.append(" on t.xmlb = t4.xmlbdm");
		sql.append(" left join xg_jskp_xmsbb t5 on t5.sqid = t.sqid ");
		sql.append(" left join view_xsbfxx t6 on t5.xh = t6.xh ");
		sql.append(")a where ");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
}

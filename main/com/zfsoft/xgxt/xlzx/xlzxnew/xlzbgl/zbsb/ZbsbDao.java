package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh.ZbshForm;

public class ZbsbDao extends SuperDAOImpl<ZbsbForm> {
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	@Override
	public List<HashMap<String, String>> getPageList(ZbsbForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(ZbsbForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select '0' sblxx,");
		sql.append(" t1.*,");
		sql.append(" a.xqmc,");
		sql.append(" t2.sbsqid,");
		sql.append(" t2.xh,");
		sql.append(" t2.sbsj,");
		sql.append(" t2.sbzbid,");
		sql.append(" t2.ztqk,");
		sql.append(" t2.xlxsxxqk,");
		sql.append(" t2.bz,");
		sql.append(" t2.splcid,");
		sql.append(" t2.shzt,");
		sql.append(" decode(t2.shzt,'0','未提交', '1','通过','2','不通过','3',");
		sql.append(" '已退回','5','审核中','未上报') shztmc");
		sql.append(" from (select b.* from XG_XLJK_XLWYGL_new_ZBRCXXB b");
		sql.append(" where b.xn = ? and b.xq = ?)t1");
		sql.append(" left join (select a.* from XG_XLJK_XLWYGL_new_XSSBSQB a");
		sql.append(" where bjdm = (select bjdm from xsxxb where xh = ?)) t2 on t1.zbid = t2.sbzbid");
		sql.append(" left join xqdzb a on t1.xq = a.xqdm) order by sbsj desc");
		sql.append(" ");
		return getPageList(t, sql.toString(),new String[]{t.getXn(),t.getXq(),user.getUserName()});
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZbsbForm.class);
		this.setKey("sbsqid");
		this.setTableName("XG_XLJK_XLWYGL_new_XSSBSQB");
	}
	
	public HashMap<String,String> getXnXqmc(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xqmc from XG_XLJK_XLWYGL_ZBRCXXB a left join xqdzb b on a.xq = b.xqdm where zbid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zbid});
	}
	
	/**
	 * 
	 * @描述: 学生授权验证
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午01:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xssqCheck(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select decode(count(1), '1', 'Y', 'N') bjxlwy");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a");
		sql.append("  where a.xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjxlwy");
	}
	
	/**
	 * 
	 * @描述: 获取学生信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午03:32:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bjmc, a.BJDM, replace(wm_concat(c.xm), ';', ',') xm");
		sql.append(" from view_xsbfxx a");
		sql.append(" left join bzrbbb b");
		sql.append(" on a.BJDM = b.bjdm");
		sql.append(" left join yhb c");
		sql.append(" on b.zgh = c.yhm");
		sql.append(" where a.XH = ?");
		sql.append(" group by a.BJMC,a.BJDM");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 获取周报问题人员信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZbWtryInfo(String zbsqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.zbwtms,b.xm,b.xb from  xg_xljk_xlwygl_wtxxb a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.XH");
		sql.append(" where a.zbsqid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zbsqid});
	}
	
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述: 保存问题表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午05:34:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsb
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDataIntoWtb(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,zbsqid,xh,zbwtms) values(?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除问题表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午05:39:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delWtb(String zbsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where zbsqid = ?");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{zbsqid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取学生查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 下午01:45:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(ZbsbForm t, User user,String xhs) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,nj,xymc,bjmc,zymc from view_xsbfxx where bjdm = ?");
		List<String> paraList = new ArrayList<String>();
		paraList.add(t.getBjdm());
		if(StringUtils.isNotNull(xhs)){
			String[] xhArray = xhs.split(",");
			sql.append(" and xh not in(");
			for (int i = 0; i < xhArray.length; i++) {
				sql.append("?");
				paraList.add(xhArray[i]);
				if(i != xhArray.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		if(StringUtils.isNotNull(t.getXm())){
			sql.append(" and (xh like ? or xm like ?) ");
			paraList.add("%"+t.getXm()+"%");
			paraList.add("%"+t.getXm()+"%");
		}
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 检测任职日期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午10:32:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkRzrq(String xh){
		String nowDate = GetTime.getTimeByFormat("yyyy-mm-dd");
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt  from XG_XLJK_XLWYGL_new_XSSQXXB t where t.rzksrq <= ? and (t.rzjsrq >= ? or t.rzjsrq is null) and xh = ?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{nowDate,nowDate,xh}, "cnt"));
		return rs > 0 ? true :false;
	}
	
	/**
	 * 
	 * @描述: 验证是否已填写周报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午11:33:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from XG_XLJK_XLWYGL_new_XSSBSQB where bjdm =? and sbzbid = ?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{bjdm,sbzbid},"cnt"));
		return rs > 0 ? false : true;
	}
}

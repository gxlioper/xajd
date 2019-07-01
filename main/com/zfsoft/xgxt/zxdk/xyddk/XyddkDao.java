/**
 * @部门:学工产品事业部
 * @日期：2014-9-25 下午03:26:51 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-校园地贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-25 下午03:26:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkDao extends SuperDAOImpl<XyddkModel> {

	private static final String YSH = "ysh";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	protected void setTableInfo() {
		super.setClass(XyddkModel.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_xydksqb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append(" select t1.*,t2.nj,t2.xm,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t2.zybj,t3.bjmc zybjmc,t4.bjdm,t4.bjmc,t6.sydm,t6.symc, ");
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		sql.append(" from xg_zxdk_xydksqb t1 ");
		sql.append(" left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t3 on t3.bjdm = t2.zybj ");
		sql.append(" left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB t5 on t2.bjdm = t5.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t6 on t5.sydm = t6.sydm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @描述: 审核查询
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-28 上午08:54:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingList(XyddkModel t, User user)
			throws Exception {
		
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");	
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_zxdk_xydksqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '").append(user.getUserName()).append("')  ");
		
		if (YSH.equals(t.getShzt())){
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else{
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	

	/** 
	 * @描述:根据ID查询助学贷款信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午03:08:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		
		String sql = "select * from xg_zxdk_xydksqb where id = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
		
	}
	
	
	
	/**
	 * 
	 * @描述: 按学号、学年查询记录总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-23 下午05:10:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		StringBuilder sql = new StringBuilder("select count(1) c from xg_zxdk_xydksqb where xh = ? ");
		List<String> params  = new ArrayList<String>();
		params.add(t.getXh());
		if(!StringUtil.isNull(t.getXn())){
			sql.append(" and xn = ?");
			params.add(t.getXn());
		}
		
		if (!StringUtil.isNull(t.getId())){
			sql.append(" and id <> ?");
			params.add(t.getId());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "c");
	}
	
	
	/**
	 * 
	 * @描述:查询学生已贷款期限
	 * @作者：cq [工号：785]
	 * @日期：2015-11-26 下午03:48:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYdkqx(String xh){
		
		String sql = "select sum(dkqx) dkqx from xg_zxdk_xydksqb where xh = ? ";
		
		return dao.getOneRs(sql, new String[]{}, "dkqx");
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-15 上午11:42:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * booleanf 返回类型 
	 * @throws
	 */
	public boolean delBeforeShtg(XyddkModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_xydkjgb where xh = ? and xn = ?");
		return dao.runUpdate(sql.toString(), new String[]{model.getXh(),model.getXn()});
	}
	
	/**
	 * 
	 * @描述: 查询上级流程信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-22 下午03:07:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param gwid
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public  HashMap<String, String> getShlcInfo(String sqid,String gwid,String splc) {
		String sjgw = this.getUpSpgw(splc, gwid);
		StringBuilder sql = new StringBuilder();
		//sql.append("  select b.mc,c.xm shr,a.shsj,a.shzt,a.shyj,a.gwid,a.guid from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		sql.append("  select * from xg_xtwh_shztb where ywid = ? and gwid = ? ");
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[] { sqid,sjgw});
	}
	
	/**
	 * 
	 * @描述: 获取当前审核岗位上级岗位
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 下午03:15:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param gwid
	 * @return
	 * String 返回类型 
	 */
	public  String getUpSpgw(String splc,String gwid){
		String xh=ShlcUtil.getGwSpXh(splc, gwid);
		if(StringUtils.isNull(xh)){
			return null;
		}
		Integer xhI=Integer.parseInt(xh)-1;
		StringBuffer sb=new StringBuffer();
		sb.append("select spgw from xg_xtwh_spbz where splc=? and xh=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,xhI.toString()}, "spgw");
	}
	
	/**
	 * 
	 * @描述: 获取学生信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 下午04:36:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxxByHsd(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xz,nj,t1.jesx from view_xsxxb t");
		sql.append(" left join XG_ZXDK_DKSXB t1");
		sql.append(" on t.PYCC = t1.xlccdm ");
		sql.append(" where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 是否存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午11:19:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean  getWjmSameRs(String xh,String gid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num ");
		sql.append(" from XG_COMM_FILEUPLOAD_DATA t");
		sql.append(" where t.gid in (select filepath");
		sql.append(" from xg_zxdk_xydksqb");
		sql.append(" where filepath is not null");
		sql.append(" and xh = ?) and t.ORIGINALNAME in(");
		sql.append(" select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		sql.append(" )");
		String count = dao.getOneRs(sql.toString(), new String[]{xh,gid}, "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 检验是否存在相同学年的重复记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 下午02:10:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xns
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getXnXhSameRs(String[] xns,String xh,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num ");
		sql.append(" from xg_hsd_zxdk_ndkb " );
		sql.append(" where xh = ?");
		paraList.add(xh);
		sql.append(" and xn in(");
		for (int i = 0; i < xns.length; i++) {
			sql.append("?");
			if(i != xns.length -1){
				sql.append(",");
			}
			paraList.add(xns[i]);
			
		}
		sql.append(" )");
		/**
		 * 判断id是否为空，id不为空就是修改，必须排除本身
		 */
		if(StringUtils.isNotNull(id)){
			sql.append(" and id != ?");
			paraList.add(id);
		}
		String count = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 批量保存xg_hsd_zxdk_ndkb
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 下午03:40:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param parameter
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRsBatch(List<String[]> parameter){
		StringBuffer sql = new StringBuffer();
		boolean flag = true;
		sql.append(" insert into xg_hsd_zxdk_ndkb(id,xh,xn,nzsfdk,nxfdk,nshfdk,nzsfyje,nxfyje,dkzt)");
		sql.append(" values(?,?,?,?,?,?,?,?,?)");
		try {
			int[] rs = dao.runBatch(sql.toString(), parameter);
			for (int i = 0; i < rs.length; i++) {
				flag = (rs[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 根据删除xg_hsd_zxdk_ndkb中的记录，修改时使用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-11 上午10:13:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRs(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_hsd_zxdk_ndkb where id = ?");
		return dao.runUpdate(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @描述: 修改时判断是否存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-10 上午11:19:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean  getWjmSameRsUpdate(String xh,String gid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num ");
		sql.append(" from XG_COMM_FILEUPLOAD_DATA t");
		sql.append(" where t.gid in (select filepath");
		sql.append(" from xg_zxdk_xydksqb");
		sql.append(" where filepath is not null and filepath != ? ");
		sql.append(" and xh = ?) and t.ORIGINALNAME in(");
		sql.append(" select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		sql.append(" )");
		String count = dao.getOneRs(sql.toString(), new String[]{gid,xh,gid}, "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 根据id查找该记录对应的贷款金额记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-11 下午05:19:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNdkbList(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  xg_hsd_zxdk_ndkb where id = ?");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 华师大根据id删除年贷款表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-14 上午09:51:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delNdkb(String[] ids) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_hsd_zxdk_ndkb where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 删除放贷表数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-15 下午06:59:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delFdb(String[] ids) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_zxdk_hsd_xydfdb where id in ( select jgid from  xg_hsd_zxdk_ndkb where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		
		sql.append(")");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 检测表单里面文件名是否有相同
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-14 下午03:04:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean checkWjmIsSame(String gid){
		StringBuffer sql = new StringBuffer();
		sql.append("  select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		List<HashMap<String, String>> fileNameList =  dao.getListNotOut(sql.toString(), new String[]{gid});
		boolean flag = true;
		for (int i = 0; i < fileNameList.size()-1; i++) {
			for (int j = i+1; j < fileNameList.size(); j++) {
				if(fileNameList.get(i).get("originalname").equals(fileNameList.get(j).get("originalname"))){
					flag = false;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入放贷表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-15 下午07:09:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean InsertIntoFdb(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		String htbh = new DkjgDao().getModel(id).getHtbh();
		sql.append(" delete from xg_zxdk_hsd_xydfdb where id = (select jgid from XG_HSD_ZXDK_NDKB t where id = ?  and dkzt = 'xind') ");
		dao.runUpdate(sql.toString(),new String[]{id});
		sql = new StringBuffer();
		sql.append(" insert into xg_zxdk_hsd_xydfdb(id,xh,xn,xq,htbh)");
		sql.append(" select jgid id,xh,xn,?, '"+htbh+"' from XG_HSD_ZXDK_NDKB t where id = ?  and dkzt = 'xind'");
		return dao.runUpdate(sql.toString(), new String[]{Base.currXq,id});
	}
	
	/**
	 * 
	 * @描述: 验证本学年是否可申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-5-31 下午04:42:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfKsqCurrXn(String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(1) cnt  from xg_zxdk_xydksqb where xn = ? and xh = ? ");
		String cnt = dao.getOneRs(sql.toString(), new String[]{xn,xh}, "cnt");
		return "0".equals(cnt) ? true : false; 
		
	}
}

/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午10:53:13 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午10:53:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfSqDao extends SuperDAOImpl<HjxfSqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	private static final String  RDZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	@Override
	public List<HashMap<String, String>> getPageList(HjxfSqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfSqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '已退回',");
		sql.append(" '5',");
		sql.append(" '审核中',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" t.xn || t2.xqmc xnxq");
		sql.append(" from XG_XSZZ_NEW_HJXFSQB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh " +
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(HjxfSqForm.class);
		super.setKey("hjxfid");
		super.setTableName("XG_XSZZ_NEW_HJXFSQB");
	}
	
	
	//获取审批流
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_XSZZ_NEW_HJXFJBSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//获取spid
	public String getSqbh(HjxfSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select hjxfid from XG_XSZZ_NEW_HJXFSQB where xh = ? and  xn = ? and xq = ?");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "hjxfid");
	}
	
	/**
	 * 
	 * @描述:获取困难生贫困等级(如果为空显示家庭经济不困难)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-3-16 下午02:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsdj(HjxfSqForm model){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc djmc ");
		sql.append(" from XG_XSZZ_NEW_HJXFSQB z");
		sql.append("   left join xg_xszz_new_knsjgb t ");
		sql.append(" on z.xh = t.xh and z.xn = t.xn");
		sql.append(" left join ");
		sql.append(" xg_xszz_new_kndcdmb t1");
		sql.append(" on t.rddc = t1.dcdm where z.hjxfid = ? and (t.xq = 'on' or t.xq = ?)");
	    String result = dao.getOneRs(sql.toString(), new String[]{model.getHjxfid(),model.getXq()}, "djmc");
		if(StringUtils.isNotNull(result)){
			return result;
		}else{
			return "家庭经济不困难";
		}
		
	}
	
}

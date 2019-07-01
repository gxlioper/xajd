package com.zfsoft.xgxt.gygl.gypynew.gypysq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GypySqDao extends SuperDAOImpl<GypySqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GypySqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GypySqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*, t1.ldmc, t1.ch,");
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
		sql.append(" t.shzt) shztmc");
		sql.append(" from xg_gygl_new_xjqssqb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append("  and t.qsh = t1.qsh");
		sql.append(" ) where 1=1");
		if("stu".equals(user.getUserType())){
			sql.append("  and lddm||qsh in(select lddm||qsh from view_xg_gygl_new_cwxx where xh ='"+user.getUserName()+"') ");
		}else if("yes".equals(user.getGyglyQx())){
			sql.append("  and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(GypySqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_gygl_new_xjqssqb");
	}
	
	/**
	 * 
	 * @描述: 获取楼栋代码List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 下午05:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(User user,String gyglyQx){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String gyglysql = SearchService.getGyglyQx(gyglyQx, user.getUserName(), "gygl_gypynew_gypysq.do");
		if(StringUtils.isNotNull(gyglysql)){
			sql.append(" select distinct lddm,ldmc from view_xg_gygl_new_qsxx where 1=1");
			sql.append(gyglysql);
			sql.append(" order by lddm");
		}else{
			if("stu".equals(user.getUserType())){
				sql.append(" select lddm,ldmc from view_xg_gygl_new_cwxx where xh = ? order by lddm");
				paraList.add(user.getUserName());
			}else{
				sql.append(" select distinct lddm,ldmc from view_xg_gygl_new_cwxx");
				sql.append(" where bjdm in (select bjdm from bzrbbb where zgh = ?");
				sql.append(" union ");
				sql.append(" select bjdm from fdybjb where zgh = ?) order by lddm");
				paraList.add(user.getUserName());
				paraList.add(user.getUserName());
			}
		}
		 return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取层号List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-26 下午02:01:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(User user,String gyglyQx){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String gyglysql = SearchService.getGyglyQx(gyglyQx, user.getUserName(), "gygl_gypynew_gypysq.do");
		if(StringUtils.isNotNull(gyglysql)){
			sql.append(" select distinct lddm,ch from view_xg_gygl_new_qsxx where 1=1");
			sql.append(gyglysql);
			sql.append(" order by lddm,ch");
		}else{
			if("stu".equals(user.getUserType())){
				sql.append(" select lddm,ch from view_xg_gygl_new_cwxx where xh = ? order by lddm,ch");
				paraList.add(user.getUserName());
			}else{
				sql.append(" select distinct lddm,ch from view_xg_gygl_new_cwxx");
				sql.append(" where bjdm in (select bjdm from bzrbbb where zgh = ?");
				sql.append(" union ");
				sql.append(" select bjdm from fdybjb where zgh = ?) order by lddm,ch");
				paraList.add(user.getUserName());
				paraList.add(user.getUserName());
			}
		}
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-26 下午02:37:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param gyglyQx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(User user,String gyglyQx){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String gyglysql = SearchService.getGyglyQx(gyglyQx, user.getUserName(), "gygl_gypynew_gypysq.do");
		if(StringUtils.isNotNull(gyglysql)){
			sql.append(" select distinct lddm,ch,qsh from view_xg_gygl_new_qsxx where 1=1");
			sql.append(gyglysql);
			sql.append(" order by lddm,ch,qsh");
		}else{
			if("stu".equals(user.getUserType())){
				sql.append(" select lddm,ch,qsh from view_xg_gygl_new_cwxx where xh = ? order by lddm,ch,qsh");
				paraList.add(user.getUserName());
			}else{
				sql.append(" select distinct lddm,ch,qsh from view_xg_gygl_new_cwxx");
				sql.append(" where bjdm in (select bjdm from bzrbbb where zgh = ?");
				sql.append(" union ");
				sql.append(" select bjdm from fdybjb where zgh = ?) order by lddm,ch,qsh");
				paraList.add(user.getUserName());
				paraList.add(user.getUserName());
			}
		}
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午03:42:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from ( ");
		sql.append(" select lddm,qsh,sqsj,sqid from xg_gygl_new_xjqssqb union ");
		sql.append(" select lddm,qsh,sqsj,jgid sqid from xg_gygl_new_xjqsjgb");
		sql.append(" )");
		sql.append(" where sqsj = ?  and lddm = ? and qsh = ?");
		paraList.add(model.getSqsj());
		paraList.add(model.getLddm());
		paraList.add(model.getQsh());
		if(StringUtils.isNotNull(model.getSqid())){
			sql.append(" and sqid != ?");
			paraList.add(model.getSqid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 
	 * @描述: 获取寝室信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-28 下午02:25:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQshxx(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ldmc,ch,qsh,lddm from view_xg_gygl_new_qsxx where lddm = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getLddm(),model.getQsh()});
	}
	
	/**
	 * 
	 * @描述: 获取星级寝室申请基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:04:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXjqsSqJbxx(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.LDMC, t.qsh, t1.XYMC, t.sqsj, t.sqxj, t.gxsj, t.sfzcgx,t.sqly");
		sql.append(" from xg_gygl_new_xjqssqb t left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.LDDM and t.qsh = t1.QSH where t.sqid = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSqid()});
	}
	
	/**
	 * 
	 * @描述: 获取违纪个数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getWjNum(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) wjcs from xg_gygl_new_gyjlb where shzt = 'tg' and lddm = ? and qsh = ?");
		return dao.getOneRs(sql.toString(), new String[]{model.getLddm(),model.getQsh()}, "wjcs");
	}
	
	/**
	 * 
	 * @描述: 获取寝室所属班级，有可能是多个
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 上午11:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getQsssbj(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select wm_concat(bjmc) bjmc from (");
		sql.append(" select distinct bjmc from view_xg_gygl_new_cwxx where lddm =  ? and qsh = ?)");
		return dao.getOneRs(sql.toString(), new String[]{model.getLddm(),model.getQsh()}, "bjmc");
	}
	
	/**
	 * 
	 * @描述: 获取违纪信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-4 下午01:53:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjxx(GypySqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,t1.gyjllbmc,t2.XM,t.wjsj,t.wjxn,t3.xqmc from xg_gygl_new_gyjlb t");
		sql.append(" left join xg_gygl_new_gyjllbdmb t1");
		sql.append(" on t.gyjllbdm = t1.gyjllbdm");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t.xh = t2.XH");
		sql.append(" left join xqdzb t3");
		sql.append(" on t.wjxq = t3.xqdm");
		sql.append(" where shzt = 'tg' and lddm = ? and qsh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{model.getLddm(),model.getQsh()});
	}
}

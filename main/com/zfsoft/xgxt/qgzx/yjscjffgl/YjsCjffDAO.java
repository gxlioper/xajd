package com.zfsoft.xgxt.qgzx.yjscjffgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 经费酬金管理  研究生酬金发放dao
 * @作者：xiaxia 
 * @时间：2016-05-04 上午09:46:37 
 * @版本： V5.1.75
 * @修改记录: 
 */
public class YjsCjffDAO  extends SuperDAOImpl<YjsCjffForm>{

	@Override
	public List<HashMap<String, String>> getPageList(YjsCjffForm t)
			throws Exception {
		
		return null;
	}
	
	
	
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(YjsCjffForm model,User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select b.*,c.bmmc yrdwmc,substr(b.ffny, 0, 4) nd,substr(b.ffny, 6) yf ");
		sql.append(" from XG_QGZX_CJFF_YJS  b  left join view_xg_qgzx_yrdwdmb c on b.yrbm = c.bmdm )where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(searchTjQx);
		return getPageList(model, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_QGZX_CJFF_YJS");
		super.setKey("guid");
	}
	/**
	 *  是否存在发放信息
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-05-04 下午02:33:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isHaveFfxx(String guid,String xh,String xn,String yrdwdm,String gwmc,String ffny){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from XG_QGZX_CJFF_YJS t where t.xh=? and t.xn=? and t.yrbm=? and t.gwmc=? and ffny=? and guid<> nvl(?,'-1')");
		List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(), new String[]{xh,xn,yrdwdm,gwmc,ffny,guid});
		return null==list||list.size()<=0?false:true;
	}
	
	
	
}

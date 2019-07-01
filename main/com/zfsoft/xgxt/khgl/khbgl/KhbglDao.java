/**
 * @部门:学工产品事业部
 * @日期：2015-8-11 上午11:39:31 
 */
package com.zfsoft.xgxt.khgl.khbgl;


import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核表管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-11 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhbglDao extends SuperDAOImpl<KhbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(KhbglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(KhbglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,decode(t1.sfqy,'1','启用','停用')sfqymc, t2.khdxmc,nvl(t3.sts,'0')sts");
		sql.append(" from xg_khgl_khb t1 left join xg_khgl_khdx t2 on t1.khdxid = t2.khdxid left join (select count(1) sts,khbid from xg_khgl_tk_zbx");
		sql.append(" group by khbid)t3 on t1.khbid= t3.khbid order by t1.sfqy asc,t1.cjsj desc) t where 1=1  ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public KhbglForm getModel(KhbglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.khdxmc,nvl2(t3.num,'1','0') sfysy");
		sql.append(" from xg_khgl_khb t1 left join xg_khgl_khdx t2 on t1.khdxid = t2.khdxid ");
		sql.append(" left join (select count(1) num,khbid from ");
		sql.append(" xg_khgl_khpf where sftj='1' group by khbid)t3 on t1.khbid=t3.khbid ");
		sql.append(" where t1.khbid=? ");
		return getModel(sql.toString(), new String[]{t.getKhbid()});
	}
	/**
	 * 
	 * @描述:获取考核表对应考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 上午10:30:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhnrList(KhbglForm model)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,case when t1.pflx ='1' then '加分' else '减分' end pflxmc from xg_khgl_tk_zbx t1 where khbid=?");
		return dao.getListNotOut(sql.toString(), new String[]{model.getKhbid()});
	}

	public List<HashMap<String, String>> getTeaList(KhbglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select rownum r,a.* from (select b.zgh yhm,b.xm,b.bmdm,c.bmmc from fdyxxb b ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm= c.bmdm");
		sql.append(")a where 1=1");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhb(String khbid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,nvl2(t2.num,'1','0')sfypf ");
		sql.append("from xg_khgl_khb t1 left join (select count(1) num,khbid from xg_khgl_khpf where sftj='1' group by khbid)t2 on t1.khbid=t2.khbid");
		sql.append(" where t1.khbid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { khbid });
	}


	/**
	 *判断考核表是否已存在
	 */
	public boolean isHave(KhbglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_khb where khbmc=? ");
		if(null!=model.getKhbid()){
			sql.append(" and khbid<>'"+model.getKhbid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getKhbmc()}, "num");
		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @描述:判断考核表是否已使用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-18 上午11:16:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String,String> isUsed(String khbid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.khbmc,t2.num from xg_khgl_khb t1 left join (select count(1) num,khbid from ");
		sql.append(" xg_khgl_khxm_sz group by khbid)t2 on t1.khbid=t2.khbid where t1.khbid=?");
 		return dao.getMapNotOut(sql.toString(), new String[]{khbid});
	}


	/**
	 * 
	 * 删除考核表
	 */
	public boolean delKhbgl(String khbid) throws Exception {
		String sql = "delete from xg_khgl_khb where khbid=?";
		return dao.runUpdate(sql, new String[] { khbid });
	}
	/**
	 * 
	 * @描述:修改删除状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午04:00:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhb(List<String[]> params) throws Exception {
		String sql = "update xg_khgl_khb set sczt=? where khbid=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:删除考核表对应考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-20 下午02:11:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhnr(String[] khbid) throws Exception {
		StringBuffer sql = new StringBuffer();
		 sql.append("delete from xg_khgl_tk_zbx  where khbid in( ");
		for (int i = 0; i < khbid.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), khbid);
	}
	
	
	/**
	 * 
	 * @描述:考核表列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:27:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhbList(String khdxid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from xg_khgl_khb where khdxid=? and sfqy='1'");
		return dao.getListNotOut(sql.toString(), new String[]{khdxid});
	}
	/**
	 * 
	 * @描述:考核内容复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 上午11:05:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean khnrFz(List<String[]> params) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_khgl_tk_zbx(khbid,yjzb,ejzb,khnr,fzlx,fzzgf,fzzdf,xssx,pflx) values(?,?,?,?,?,?,?,?,?)");
		int[] result= dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:启用设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 上午11:57:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qySz(List<String[]> params) throws Exception {
		String sql = "update xg_khgl_khb set sfqy=? where khbid=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KhbglForm.class);
		super.setKey("khbid");
		super.setTableName("xg_khgl_khb");

	}
	
}

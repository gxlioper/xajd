/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:39:31 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.kycxgl.kyxmgl.KyxmglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 实践管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:39:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SjxmglDao extends SuperDAOImpl<SjxmglForm> {
	
	@Override
	public List<HashMap<String, String>> getPageList(SjxmglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	//申请列表
	@Override
	public List<HashMap<String, String>> getPageList(SjxmglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfKycxByUser(user, "t", "xh");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t4.bmmc ssdwmc, case when t1.jfys is null then '0' else '1' end sfytx,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		sql.append(" from XG_QGZX_SJXMXX t1 left join XG_QGZX_XMSX_SJ t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getPageListOfSh(SjxmglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t4.bmmc ssdwmc,");
		sql.append("t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append("decode(t6.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.xmid order by t6.shsj desc) rn ");
		sql.append(" from XG_QGZX_SJXMXX t1 left join XG_QGZX_XMSX_SJ t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" left join xg_xtwh_shztb t6 on t1.xmid = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw  left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exception
	 * 
	 * @描述:获取实践项目结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-30下午04:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getSjxmgl(String xmid,String ffyf) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t3.sjhm lxfs,t4.bmmc ssdwmc,t5.yffje,(to_number(t1.pzjf)-to_number(t5.yffje))syjf");
		sql.append(" from XG_QGZX_SJXMXX t1 left join XG_QGZX_XMSX_SJ t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" left join (select sum(cjje)yffje,xmid from XG_QGZX_SJXMCYFFXXB where ffyf=? group by xmid)t5 on t1.xmid=t5.xmid");
		sql.append(" where t1.xmid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { ffyf,xmid });
	}
	
	public HashMap<String,String> getSjxm(String xmid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc xmsxmc,t3.xm,t3.sjhm lxfs,t4.bmmc ssdwmc,t5.yffje,(to_number(t1.pzjf)-to_number(t5.yffje))syjf");
		sql.append(" from XG_QGZX_SJXMXX t1 left join XG_QGZX_XMSX_SJ t2 on t1.xmsxdm = t2.dm");
		sql.append(" left join view_xsjbxx t3 on t1.XH= t3.xh left join zxbz_xxbmdm t4 on t1.xmssdw=t4.bmdm");
		sql.append(" left join (select sum(cjje)yffje,xmid from XG_QGZX_SJXMCYFFXXB group by xmid)t5 on t1.xmid=t5.xmid");
		sql.append(" where t1.xmid=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xmid });
	}



	/**
	 *判断是否已存在
	 */
	public boolean isHaveSbjg(SjxmglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_QGZX_SJXMXX where xmmc=?");
		if(null!=model.getXmid()){
			sql.append(" and xmid<>'"+model.getXmid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXmmc()}, "num");
		return Integer.parseInt(num)>0;
	}

	public boolean delSjxmgl(String xmid) throws Exception {
		String sql = "delete from XG_QGZX_SJXMXX where xmid=?";
		return dao.runUpdate(sql, new String[] { xmid });
	}


	public List<HashMap<String,String>> getCyList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xmid,t1.xh,t1.xmfg,t1.xmnzt,t2.bjmc,t2.xm,t2.xb,t2.xymc,nvl(t2.lxdh,'无')lxdh");
		sql.append(" ,case when t1.xmnzt='1' then '正常' else '离岗' end xmnztmc from XG_QGZX_SJXMCYXXB t1 left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	
	public List<HashMap<String,String>> getTeaList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm,t3.bmmc xymc,nvl(t2.lxdh,'无')lxdh");
		sql.append(" from XG_QGZX_SJXMZDLSB t1 left join fdyxxb t2 on t1.zgh=t2.zgh left join zxbz_xxbmdm t3 on t2.bmdm=t3.bmdm ");
		sql.append("  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	public List<HashMap<String,String>> getGwxxList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.mc gwlbmc from XG_QGZX_SJGWXXB t1 left join XG_QGZX_XMGWLX t2 on t1.gwlb=t2.dm where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	
	public List<HashMap<String,String>> getBgztList(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc from XG_QGZX_YXZTBGB t1 left join XG_QGZX_XMYXZT t2 on t1.yxzt=t2.dm  order by t1.bgsj where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm});
	}
	
	public List<HashMap<String,String>> getYxztList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.* from XG_QGZX_XMYXZT t1");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String,String>> getXmfyList(String xmid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.* from XG_QGZX_KYXMFYB t1  where t1.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] { xmid });
	}
	@Override
	public SjxmglForm getModel(SjxmglForm myForm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm,t2.nj,t2.xymc,t2.sjhm from XG_QGZX_SJXMXX t1 left join view_xsjbxx t2 on t1.xh=t2.xh  where t1.xmid=?");
		return getModel(sql.toString(), new String[]{myForm.getXmid()});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(SjxmglForm.class);
		super.setKey("xmid");
		super.setTableName("XG_QGZX_SJXMXX");

	}
	/**
	 * 
	 * @描述:获取学生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-11-30 上午10:27:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(SjxmglForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhs();
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,nvl(a.sjhm,'无')lxdh,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		if(xhs.length>0){
			sql.append("  and a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(")a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getTeaList(SjxmglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] zghs = t.getZghs();
		sql.append("select rownum r,a.* from (select b.yhm zgh,b.xm,b.zdm,b.szbm bmdm,c.bmmc xymc,d.zmc,c.bmlb,nvl(e.lxdh,'无')lxdh from yhb b ");
		sql.append(" left join zxbz_xxbmdm c on b.szbm = c.bmdm left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm left join fdyxxb e on b.yhm=e.zgh where c.bmlb = '1' ");
		if(zghs.length>0){
			sql.append("  and b.yhm not in(");
			for (int i = 0; i < zghs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+zghs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(" )a where 1=1 ");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getBmList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select bmdm xydm,rownum rn,F_PINYIN(substr(bmmc,0,1)) xypy,F_PINYIN(substr(bmmc,0,1)) ||'-'||bmmc xymc ");
		sql.append(" from zxbz_xxbmdm ");
		sql.append(")");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xydm", "xymc" });
	}
	/**
	 * 
	 * @描述:项目属性列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-30 下午06:14:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmsxList()throws Exception{
		String sql ="select * from XG_QGZX_XMSX_SJ";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:岗位类型列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-7 上午10:55:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGwlbList()throws Exception{
		String sql ="select * from XG_QGZX_XMGWLX";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:删除学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-2 下午03:43:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_SJXMCYXXB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
		
	}
	//删除指导老师
	public boolean delJs(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_SJXMZDLSB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	
	//删除项目费用列表
	public boolean delXmfy(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_KYXMFYB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	/**
	 * 
	 * @描述:项目岗位删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-2 下午04:28:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delGw(String xmid) throws Exception{
		String sql ="delete from XG_QGZX_SJGWXXB where xmid=?";
		return dao.runUpdate(sql, new String[]{xmid});
	}
	public boolean gwPlbc(List<String[]> gwParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJGWXXB(xmid,gwlb,gwgzzy,zcyrs) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, gwParams);
		return dao.checkBatchResult(result);
	}
	public boolean jsPlbc(List<String[]> jsParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJXMZDLSB(xmid,zgh,zc,yjfx) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, jsParams);
		return dao.checkBatchResult(result);
	}
	
	public boolean xsPlbc(List<String[]> xsParams) throws SQLException {
		String sql = "insert into XG_QGZX_SJXMCYXXB(xmid,xh,xmfg,xmnzt) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, xsParams);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:获取审核流
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午08:45:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID(String kylb) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_QGZX_QZXM_CSSZ where xmlb=?");
		return dao.getOneRs(sql.toString(), new String[] {kylb}, "splc");
	}

}

/**
 * @部门:学工产品事业部
 * @日期：2015-9-8 下午02:58:17 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-9-8 下午02:58:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwfbydcjgDao extends SuperDAOImpl<RwfbydcjgModel> {

	@Override
	public List<HashMap<String, String>> getPageList(RwfbydcjgModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RwfbydcjgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t2.yhkh, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,x1.sydm,x1.symc,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append("from xg_zxdk_rwfbydcjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append("left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public RwfbydcjgModel getModel(String id) throws Exception {
		 	StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append("select t1.*,t3.mc yjxfmc,t4.mc dclbmc,t10.yhmc ");
			sql.append("from xg_zxdk_rwfbydcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
			sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ");
			sql.append("left join xg_zxdk_dclbdmb t4 on t1.dclb=t4.dm ) t where id = ? ");
			return getModel(sql.toString(),new String[]{id});
	}

	/**
	 * 
	 * @描述:验证是否已申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-7 下午06:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists(RwfbydcjgModel model) {	
		String sql = "select count(1) count from xg_zxdk_rwfbydcjgb where xh = ?";
		String num = dao.getOneRs(sql, new String[]{model.getXh()}, "count");
		return Integer.parseInt(num) > 0?true:false;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:审核的时候验证数据是否存在如果存在根据学号删除
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-10 上午11:31:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteByKey(String key) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_rwfbydcjgb where xh = ? ");
		return dao.runUpdate(sql.toString(), new String[]{key});
		
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_rwfbydcjgb");
		super.setKey("id");
		super.setClass(RwfbydcjgModel.class);
	}
	
	/**
	 *
	 * @描述:陕西师范大学兵役代偿结果list学生信息查看自定义表单配置用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 上午09:14:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRwdcjglist(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,");
		sql.append(" t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append(" from xg_zxdk_rwfbydcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append(" left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm  where t1.xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存代偿结果各次发放的信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月13日 下午3:57:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffcsList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveDcjgffsc(String xh,String xn,List<DcjgffcsModel> ffcsList) throws SQLException {
		String sql = "insert into xg_zxdk_rwfbydcjg_ffcsb (xh,xn,ffsj,ffje,ffnr) values(?,?,?,?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(DcjgffcsModel model:ffcsList){
			String [] param = {xh,xn,model.getFfsj(),model.getFfje(), model.getFfnr()};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return true;
	}

	/** 
	 * @描述:根据学号、学年查询代偿结果对应的发放次数信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 上午8:55:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcjgffcsList(String xh, String xn) {
		String sql = "select ffsj,ffje,ffnr from xg_zxdk_rwfbydcjg_ffcsb where xh = ? and xn = ? order by to_date(ffsj,'yyyy-mm-dd')";
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}

	/**
	 * @throws Exception  
	 * @描述:根据学号、学年删除代偿结果发放次数的相关信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 上午10:06:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteDcjgffsc(List<HashMap<String,String>> xhxnList) throws Exception {
		String sql = "delete from xg_zxdk_rwfbydcjg_ffcsb where xh = ? and xn = ?";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(HashMap<String,String> map:xhxnList){
			String [] param = {map.get("xh"),map.get("xn")};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return true;
	}

	/** 
	 * @描述:根据代偿结果id数字查询相关学号，学年集合
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月16日 下午12:38:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param idArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXhxnList(String[] idArr) {
		StringBuilder sql = new StringBuilder("select xh,xn from xg_zxdk_rwfbydcjgb where ");
		for (int i = 0 , n = idArr.length ; i < n ; i++){
			sql.append("id");
			sql.append("=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), idArr);
	}

	
	//=================个性化导入导出，自定义多表=====================
	/** 
	 * @描述:查询导入模版信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 上午10:05:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDrmbxx(String drmkdm) {
		String sql = "select drmkdm,drmkmc,drbmc from zfxg_drsj_drpz where drmkdm = ?";
		HashMap<String,String> drmbxx = dao.getMapNotOut(sql, new String[]{drmkdm});
		return drmbxx;
	}

	/** 
	 * @描述:查询导入规则列表信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 上午10:05:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrgzxxList(String drmkdm) {
		String sql = "select drl,drlmc,lsjgsh,gshxx,sfzj,sfbt,zdcd,sfwy from ZFXG_DRSJ_DRLPZ where drmkdm = ? order by to_number(xsxx)";
		List<HashMap<String,String>> drgzxxList = dao.getListNotOut(sql, new String[]{drmkdm});
		return drgzxxList;
	}

	/** 
	 * @描述: 查询导入辅助表信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 下午5:05:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrfzxxList(String drmkdm) {
		String sql = "select pz,fzmc from ZFXG_DRSJ_FZB where drmkdm = ?";
		return dao.getListNotOut(sql, new String[]{drmkdm});
	}

	/** 
	 * @描述:获取导入辅助表辅助代码信息列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月18日 下午6:09:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tableName
	 * @param outputValues
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFzdmxxList(String tableName, String[] outputValue,String sortCol) {
		String sql = "select * from " + tableName;
		if(!StringUtil.isNull(sortCol)){
			sql += " order by "+sortCol;
		}
		return dao.getList(sql, new String[]{}, outputValue);
	}

	/** 
	 * @描述:导入时验证根据sql配置查询某单元格数据并返回，如果存在则替换，如果不存在则错误
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月20日 下午4:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sql
	 * @param cellContents
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String changeCellData(String sql, String cellContents,String drl) {
		return dao.getOneRs(sql.toString(), new String[]{cellContents}, drl);
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存代偿结果信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月22日 下午5:32:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dcjgParaList
	 * @return
	 * int [] 返回类型 
	 * @throws 
	 */
	public int[] insertDcjgDataIntoDB(List<String[]> dcjgParaList) throws SQLException {
		StringBuilder sql = new StringBuilder("insert into xg_zxdk_rwfbydcjgb(xh,xn,rwqsfsqdc,xfje,dkbj,");
		sql.append("yhdm,dkhth,dkkssj,dkjssj,dclb,rwnf,twnf) values (?,?,?,?,?,?,?,?,?,?,?,?)");
		return dao.runBatch(sql.toString(), dcjgParaList);
	}

	/**
	 * @throws SQLException  
	 * @描述:批量保存代偿结果发放次数信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月22日 下午5:33:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dcjg_ffcsParaList
	 * @return
	 * int [] 返回类型 
	 * @throws 
	 */
	public int[] insertDcjgFfcsDataIntoDB(List<String[]> dcjg_ffcsParaList) throws SQLException {
		String sql = "insert into xg_zxdk_rwfbydcjg_ffcsb (xh,xn,ffsj,ffje,ffnr) values(?,?,?,?,?)";
		return dao.runBatch(sql, dcjg_ffcsParaList);
	}

	/** 
	 * @描述:验证不可重复（对代偿结果表）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月23日 下午12:43:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param drl
	 * @param cellContents
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRepeatForDr(String drl, String cellContents) {
		StringBuilder sql = new StringBuilder("select ");
		sql.append(drl);
		sql.append(" from xg_zxdk_rwfbydcjgb where ");
		sql.append(drl);
		sql.append("=?");
		List<HashMap<String,String>> resultList = dao.getListNotOut(sql.toString(), new String[]{cellContents});
		return resultList.size()>0;
	}

	/**
	 * @throws Exception 
	 * @描述:导出时查询所有记录，包含发放次数信息，对浙大
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月24日 上午10:52:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcListForZD(RwfbydcjgModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder("select * from (");
		sql.append("select t1.*, t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj  sjdh, ");
		sql.append("t2.dzyx,t2.yhkh,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc dclbmc,t10.mc  yjxfmc,t4.ffsj,t4.ffje,t4.ffnr");
		sql.append(" from xg_zxdk_rwfbydcjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf = t10.dm");
		sql.append(" left join dmk_yh t11 on t1.yhdm = t11.yhdm");
		sql.append(" left join xg_zxdk_dclbdmb t3 on t1.dclb = t3.dm");
		sql.append(" left join (select xh,xn, replace(wm_concat(ffsj),',','|') ffsj,replace(wm_concat(ffje),',','|') ffje,");
		sql.append("replace(wm_concat(replace(ffnr,',','，')),',','|') ffnr from xg_zxdk_rwfbydcjg_ffcsb group by xh,xn) t4");
		sql.append(" on t1.xh = t4.xh ) t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

}

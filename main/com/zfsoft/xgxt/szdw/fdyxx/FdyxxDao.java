/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午02:46:27 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XgzdModel;

/** 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-7-9 下午02:46:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyxxDao extends SuperDAOImpl<FdyxxModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("fdyxxb");
		super.setClass(FdyxxModel.class);
		super.setKey("zgh");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(FdyxxModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(FdyxxModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**查询辅导员信息***/
	public HashMap<String,String> getFdyInfo(String zgh){
		
		String sql = "select a.*,b.bmmc lxbmmc from fdyxxb a left join ZXBZ_XXBMDM b on a.lxbm = b.bmdm where zgh = ?";
		
		return dao.getMapNotOut(sql, new String[]{zgh});
	}

	public HashMap<String,String> getFdyInfo1(String zgh){

		String sql = "select a.* from view_fdyxx a where a.zgh = ?";

		return dao.getMapNotOut(sql, new String[]{zgh});
	}

	public boolean deleteShlc(FdyxxModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_shztb ");
		sql.append(" where ywid in(select sqid from xg_szdw_fdyxgsqb where zgh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("') ");
		String[] inputValue = { model.getZgh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}
	
	
	public boolean deleteXgsq(FdyxxModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_szdw_fdyxgsqb where zgh=? and shjg=? ");
		String[] inputValue = { model.getZgh(),Constants.SH_DSH };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}
	
	/***保存修改字段***/
	public boolean insertXgzd(List<XgzdModel> list, String sqid)
			throws Exception {
		int[] result = null;
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_szdw_fdyxgzdb(sqid,zd,zdz,xgqz) ");
		sb.append(" values(?,?,?,?)");
		for (XgzdModel xgzdModel : list) {
			String[] param = { sqid, xgzdModel.getZd(), xgzdModel.getZdz(),
					xgzdModel.getXgqz() };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}
	
	
	/***保存修改申请***/
	public boolean insertXgsq(FdyxxModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_szdw_fdyxgsqb(sqid,zgh,shjg) values(?,?,?)");
		String[] inputValue = { model.getSqid(), model.getZgh(), model.getShjg() };
		return dao.runUpdate(sql.toString(), inputValue);
	}

	/** 
	 * @描述:修改字段信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:25:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sqid,zd,zdz,xgqz from xg_szdw_fdyxgzdb ");
		sb.append(" where sqid=? ");
		return dao.getListNotOut(sb.toString(), new String[] { sqid });
	}

	public HashMap<String,String> getBmdm(String bmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm,bmmc from T_BMDMB where bmdm = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{bmdm});
	}
	/**
	 * @描述:按职工号查待审核信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:31:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDshByZgh(String zgh) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_szdw_fdyxgsqb  ");
		sb.append("  where  zgh=? and (shjg='0' or shjg='3') ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { zgh };
		return dao.getMapNotOut(sb.toString(), input);
	}

	/** 
	 * @描述:申请信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:32:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param ywShz
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDataByZgh(String zgh, String ywShz) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_szdw_fdyxgsqb  ");
		sb.append("  where  zgh=? and shjg=? ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { zgh, ywShz };
		return dao.getMapNotOut(sb.toString(), input);
	}

	/** 
	 * @描述:审核信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-7-16 上午10:32:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getShxxByXh(String zgh) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (select a.*,b.shsj,b.shyj from (select * from xg_szdw_fdyxgsqb");
		sb.append(" where zgh=? order by xgsj desc  ) a");
		sb.append(" left join xg_xtwh_shztb b on a.sqid=b.ywid and b.shsj is not null order by shsj desc)  ");
		sb.append(" where rownum =1");
		return dao.getMapNotOut(sb.toString(), new String[]{zgh});
	}
	
	
	
	public List<HashMap<String, String>> getWclPageList(FdyxxModel model, User user) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select b.*,c.*,decode(c.xb,'1','男','2','女',c.xb) xbmc,a.xgsj,a.shjg,e.mc,e.gwz,f.bmmc,row_number() over (partition by b.ywid order by b.shsj desc) as rn  ");
		sb.append(" from xg_szdw_fdyxgsqb a,xg_xtwh_shztb b,fdyxxb c,xg_xtwh_spgwyh d,xg_xtwh_spgw e,zxbz_xxbmdm f ");
		sb.append(" where a.zgh=c.zgh and a.sqid=b.ywid and b.gwid=d.spgw and e.id=b.gwid and c.bmdm=f.bmdm ");
		String shzt = model.getShzt();

		if (shzt != null && shzt.equals("tg")) {
			sb.append(" and b.shzt='1' ");
		} else if (shzt != null && !shzt.equals("dsh")) {
			sb.append(" and (b.shzt!='0' and b.shzt!='4')");
		} else {
			sb.append(" and (b.shzt='0' or b.shzt='4')");
		}

		sb.append(" and d.spyh='");
		sb.append(user.getUserName());
		sb.append("' ) a where rn=1 ");
		sb.append(searchTj);
		
		if ("xy".equals(user.getUserType())){
			sb.append(" and bmdm='").append(user.getUserDep()).append("'");
		}
		
		return getPageList(model, sb.toString(), inputV);
	}
	
	
	/**更新审核结果**/
	public boolean updateShztNotCommit(String ywid,String shzt) throws Exception{
		String sql = "update xg_szdw_fdyxgsqb set shjg=? where sqid=?";
		return dao.runUpdateNotCommit(sql, new String[]{shzt,ywid});
	}
	
	/**更新审核结果**/
	public boolean updateShzt(String ywid,String shzt) throws Exception{
		String sql = "update xg_szdw_fdyxgsqb set shjg=? where sqid=?";
		return dao.runUpdate(sql, new String[]{shzt,ywid});
	}
	
	/**删除获奖信息***/
	public boolean deleteHjxx(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_hjxx where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入获奖信息***/
	public boolean insertHjxx(String zgh,List<HjjlModel> hjxxList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_hjxx(zgh,hjjb,hjmc,bjdw,hjzrs,brpm,hjbz,hjsj,zd1,zd2,zd3,zd4,zd5,zd6) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if("10704".equals(Base.xxdm)){
			sql = "insert into xg_szdw_fdyxx_hjxx(zgh,hjjb,hjmc,bjdw,hjzrs,brpm,hjbz,hjsj,zd1,zd2,zd3,zd4,zd5,zd6,fjid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		List<String[]> params = new ArrayList<String[]>();
		
		for (HjjlModel hjjlModel : hjxxList){
			if("10704".equals(Base.xxdm)){
				params.add(new String[]{zgh,hjjlModel.getHjjb(),hjjlModel.getHjmc(),hjjlModel.getBjdw(),
						hjjlModel.getHjzrs(),hjjlModel.getBrpm(),hjjlModel.getHjbz(), hjjlModel.getHjsj(),
						hjjlModel.getZd1(),hjjlModel.getZd2(),hjjlModel.getZd3(),hjjlModel.getZd4(),hjjlModel.getZd5(),hjjlModel.getZd6(),hjjlModel.getFjid()});
			}else{
				params.add(new String[]{zgh,hjjlModel.getHjjb(),hjjlModel.getHjmc(),hjjlModel.getBjdw(),
						hjjlModel.getHjzrs(),hjjlModel.getBrpm(),hjjlModel.getHjbz(), hjjlModel.getHjsj(),
						hjjlModel.getZd1(),hjjlModel.getZd2(),hjjlModel.getZd3(),hjjlModel.getZd4(),hjjlModel.getZd5(),hjjlModel.getZd6()});
			}
			
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/***查询获奖信息***/
	public List<HashMap<String,String>> getHjxxList(String zgh){
		String sql = "select t.* from xg_szdw_fdyxx_hjxx t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	/**删除培训经历***/
	public boolean deletePxjl(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_pxjl where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入培训经历***/
	public boolean insertPxjl(String zgh,List<PxjlModel> pxjlList) throws Exception{
		String sql = "";
		if("10704".equals(Base.xxdm)){
			sql = "insert into xg_szdw_fdyxx_pxjl(zgh,pxmc,pxjb,zzdw,pxdd,pxkssj,pxjssj,zd1,zd2,zd3,zd4,fjid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		}else{
			sql = "insert into xg_szdw_fdyxx_pxjl(zgh,pxmc,pxjb,zzdw,pxdd,pxkssj,pxjssj,zd1,zd2,zd3,zd4) values (?,?,?,?,?,?,?,?,?,?,?)";
		}
		List<String[]> params = new ArrayList<String[]>();
		
		for (PxjlModel pxjlModel : pxjlList){
			if("10704".equals(Base.xxdm)){
				params.add(new String[]{zgh,pxjlModel.getPxmc(),pxjlModel.getPxjb(),
						pxjlModel.getZzdw(),pxjlModel.getPxdd(),pxjlModel.getPxkssj(),pxjlModel.getPxjssj(),
						pxjlModel.getZd1(),pxjlModel.getZd2(),pxjlModel.getZd3(),pxjlModel.getZd4(),pxjlModel.getFjid()});
			}else{
				params.add(new String[]{zgh,pxjlModel.getPxmc(),pxjlModel.getPxjb(),
						pxjlModel.getZzdw(),pxjlModel.getPxdd(),pxjlModel.getPxkssj(),pxjlModel.getPxjssj(),
						pxjlModel.getZd1(),pxjlModel.getZd2(),pxjlModel.getZd3(),pxjlModel.getZd4()});
			}
			
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询培训经历***/
	public List<HashMap<String,String>> getPxjlList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_pxjl where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	/**删除其它成果***/
	public boolean deleteQtcg(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_qtcg where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入其它成果***/
	public boolean insertQtcg(String zgh,List<QtcgModel> qtcgList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_qtcg(zgh,cgmc,cglx,cgjb,cgfbrq,cgzrs,cgbz) values (?,?,?,?,?,?,?)";
		if("10704".equals(Base.xxdm)){
			sql = "insert into xg_szdw_fdyxx_qtcg(zgh,cgmc,cglx,cgjb,cgfbrq,cgzrs,cgbz,fjid) values (?,?,?,?,?,?,?,?)";
		}
		List<String[]> params = new ArrayList<String[]>();
		
		for (QtcgModel qtcgModel : qtcgList){
			if("10704".equals(Base.xxdm)){
				params.add(new String[]{zgh,qtcgModel.getCgmc(),qtcgModel.getCglx(),
						qtcgModel.getCgjb(),qtcgModel.getCgfbrq(),qtcgModel.getCgzrs(),qtcgModel.getCgbz(),qtcgModel.getFjid()});
			}else{
				params.add(new String[]{zgh,qtcgModel.getCgmc(),qtcgModel.getCglx(),
						qtcgModel.getCgjb(),qtcgModel.getCgfbrq(),qtcgModel.getCgzrs(),qtcgModel.getCgbz()});
			}
			
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询其它成果***/
	public List<HashMap<String,String>> getQtcgList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_qtcg where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	
	
	/**删除科研项目***/
	public boolean deleteKyxm(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_kyxm where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入科研项目***/
	public boolean insertKyxm(String zgh,List<KyxmModel> kyxmList) throws Exception{
		String sql;
		if(Base.xxdm.equals("10704")){
			sql = "insert into xg_szdw_fdyxx_kyxm(zgh,xmmc,xmly,xmbh,lxrq,jsrq,xmfzr,kybz,fjid) values (?,?,?,?,?,?,?,?,?)";
		}else{			
			sql = "insert into xg_szdw_fdyxx_kyxm(zgh,xmmc,xmly,xmbh,lxrq,jsrq,xmfzr,kybz,xmzrs,brpm,xmjb,kyxmnd,kyxmdj) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		List<String[]> params = new ArrayList<String[]>();
		
		for (KyxmModel kyxmModel : kyxmList){
			if(Base.xxdm.equals("10704")){
				params.add(new String[]{zgh,kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
						kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getXmfzr(),kyxmModel.getKybz(),kyxmModel.getFjid()});
			}else{				
				params.add(new String[]{zgh,kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
						kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getXmfzr(),kyxmModel.getKybz(),
						kyxmModel.getXmzrs(),kyxmModel.getBrpm(),kyxmModel.getXmjb(),kyxmModel.getKyxmnd(), kyxmModel.getKyxmdj()});
			}
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询科研项目***/
	public List<HashMap<String,String>> getKyxmList(String zgh){
		String sql = "select t.* from xg_szdw_fdyxx_kyxm t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	
	/**删除发布论文***/
	public boolean deleteFblw(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_fblw where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入发布论文***/
	public boolean insertFblw(String zgh,List<FblwModel> fblwList) throws Exception{
		String sql;
		if(Base.xxdm.equals("10704")){
			sql = "insert into xg_szdw_fdyxx_fblw(zgh,lwmc,kwmc,kwjb,fbrq,kwjh,kwqh,kwbz,brpm,fjid) values (?,?,?,?,?,?,?,?,?,?)";
		}else{			
			sql = "insert into xg_szdw_fdyxx_fblw(zgh,lwmc,kwmc,kwjb,fbrq,kwjh,kwqh,kwbz,zzzrs,brpm,zd1,zd2) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		List<String[]> params = new ArrayList<String[]>();
		
		for (FblwModel fblwModel : fblwList){
			if(Base.xxdm.equals("10704")){
				params.add(new String[]{zgh,fblwModel.getLwmc(),fblwModel.getKwmc(),fblwModel.getKwjb(),
						fblwModel.getFbrq(),fblwModel.getKwjh(),fblwModel.getKwqh(),fblwModel.getKwbz(),
						fblwModel.getBrpm(),fblwModel.getFjid()});
			}else{				
				params.add(new String[]{zgh,fblwModel.getLwmc(),fblwModel.getKwmc(),fblwModel.getKwjb(),
						fblwModel.getFbrq(),fblwModel.getKwjh(),fblwModel.getKwqh(),fblwModel.getKwbz(),
						fblwModel.getZzzrs(),fblwModel.getBrpm(),fblwModel.getZd1(),fblwModel.getZd2()});
			}
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询发布论文***/
	public List<HashMap<String,String>> getFblwList(String zgh){
		String sql = "select t.* from xg_szdw_fdyxx_fblw t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除教学工作***/
	public boolean deleteJxgz(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_jxgz where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入教学工作***/
	public boolean insertJxgz(String zgh,List<JxgzModel> jxgzList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_jxgz(zgh,qzsj,skmc,skcc,jxgzl,pjjl,ndkhjl) values (?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (JxgzModel jxgzModel : jxgzList){
			params.add(new String[]{zgh,jxgzModel.getQzsj(),
					jxgzModel.getSkmc(), jxgzModel.getSkcc(), jxgzModel.getJxgzl(),
					jxgzModel.getPjjl(), jxgzModel.getNdkhjl() });
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询教学工作***/
	public List<HashMap<String,String>> getJxgzList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_jxgz where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除职业资格证书***/
	public boolean deleteZyzgzs(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_zyzgzs where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入职业资格证书***/
	public boolean insertZyzgzs(String zgh,List<ZyzgzsModel> zyzgzsList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_zyzgzs(zgh,zgmc,rdjg,zsbfsj,jbcc,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (ZyzgzsModel zyzgzsModel : zyzgzsList){
			params.add(new String[]{zgh, 
					zyzgzsModel.getZgmc(), zyzgzsModel.getRdjg(), zyzgzsModel.getZsbfsj(),
					zyzgzsModel.getJbcc(),zyzgzsModel.getZd1(),zyzgzsModel.getZd2(),zyzgzsModel.getZd3(),zyzgzsModel.getZd4(),
					zyzgzsModel.getZd5(),zyzgzsModel.getZd6(),zyzgzsModel.getZd7(),zyzgzsModel.getZd8() });
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询职业资格证书***/
	public List<HashMap<String,String>> getZyzgzsList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_zyzgzs where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除著作、教材情况***/
	public boolean deleteZzjcqk(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_zzjcqk where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入著作、教材情况***/
	public boolean insertZzjcqk(String zgh,List<ZzjcqkModel> zzjcqkList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_zzjcqk(zgh,zzjcmc,cbsj,cbs,jclb,cbqk,zd1,zd2,zd3,zd4) values (?,?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (ZzjcqkModel zzjcqkModel : zzjcqkList){
			params.add(new String[]{zgh, 
					zzjcqkModel.getZzjcmc(), zzjcqkModel.getCbsj(), zzjcqkModel.getCbs(),
					zzjcqkModel.getJclb(), zzjcqkModel.getCbqk(),zzjcqkModel.getZd1(),zzjcqkModel.getZd2(),
					zzjcqkModel.getZd3(),zzjcqkModel.getZd4()});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询著作、教材情况***/
	public List<HashMap<String,String>> getZzjcqkList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_zzjcqk where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	/**删除发明创造等情况***/
	public boolean deleteFmcz(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_fmcz where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入发明创造等情况***/
	public boolean insertFmcz(String zgh,List<FmczModel> fmczList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_fmcz(zgh,mc,sj,brpm,lx,bz) values (?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (FmczModel fmczModel : fmczList){
			params.add(new String[]{zgh, 
					fmczModel.getMc(), fmczModel.getSj(), fmczModel.getBrpm(),
					fmczModel.getLx(), fmczModel.getBz() });
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询发明创造等情况***/
	public List<HashMap<String,String>> getFmczList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_fmcz where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除教学科研成果（含竞赛）获奖情况***/
	public boolean deleteJxky(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_jxky where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入教学科研成果（含竞赛）获奖情况***/
	public boolean insertJxky(String zgh,List<JxkyModel> jxkyList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_jxky(zgh,cgmc,hjsj,bjdw,hjdj,hjlx,brpm,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13,zd14) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (JxkyModel jxkyModel : jxkyList){
			params.add(new String[]{zgh, 
					jxkyModel.getCgmc(), jxkyModel.getHjsj(), jxkyModel.getBjdw(),
					jxkyModel.getHjdj(),jxkyModel.getHjlx(), jxkyModel.getBrpm(),jxkyModel.getZd1(),jxkyModel.getZd2(),jxkyModel.getZd3(),
					jxkyModel.getZd4(),jxkyModel.getZd5(),jxkyModel.getZd6(),jxkyModel.getZd7(),jxkyModel.getZd8(),jxkyModel.getZd9(),
					jxkyModel.getZd10(),jxkyModel.getZd11(),jxkyModel.getZd12(),jxkyModel.getZd13(),jxkyModel.getZd14()});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询教学科研成果（含竞赛）获奖情况***/
	public List<HashMap<String,String>> getJxkyList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_jxky where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**2018-1-29  职工号：1529*/
	
	/**删除工作经历情况***/
	public boolean deleteGzjl(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_gzqk where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入工作经历情况***/
	public boolean insertGzjl(String zgh,List<GzqkModel> gzqkList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_gzqk(zgh,dwmc,qssj,jssj,zw) "
				+ "values (?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (GzqkModel gzqkModel : gzqkList){
			params.add(new String[]{zgh, 
					gzqkModel.getDwmc(),gzqkModel.getQssj(),gzqkModel.getJssj(),gzqkModel.getZw()
					});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询教工作经历情况***/
	public List<HashMap<String,String>> getGzjlList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_gzqk where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除职业培训情况***/
	public boolean deleteZypx(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_zypx where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入职业培训情况***/
	public boolean insertZypx(String zgh,List<ZypxModel> zypxList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_zypx(zgh,qssj,jssj,hjsj,pxmc,zbdw) "
				+ "values (?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (ZypxModel zypxModel : zypxList){
			params.add(new String[]{zgh, 
					zypxModel.getQssj(),zypxModel.getJssj(),zypxModel.getHjsj(),zypxModel.getPxmc(),zypxModel.getZbdw()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询职业培训情况***/
	public List<HashMap<String,String>> getZypxList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_zypx where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除项目学习情况***/
	public boolean deleteXmxx(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_xmxx where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入项目学习情况***/
	public boolean insertXmxx(String zgh,List<XmxxModel> xmxxList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_xmxx(zgh,qssj,jssj,xmmc,xxnr,zbdw) values (?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (XmxxModel xmxxModel : xmxxList){
			params.add(new String[]{zgh, 
					xmxxModel.getQssj(),xmxxModel.getJssj(),xmxxModel.getXmmc(),xmxxModel.getXxnr(),xmxxModel.getZbdw()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询项目学习情况***/
	public List<HashMap<String,String>> getXmxxList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_xmxx where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**删除高校交流情况***/
	public boolean deleteGxjl(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_gxjl where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入高校交流情况***/
	public boolean insertGxjl(String zgh,List<GxjlModel> gxjlList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_gxjl(zgh,qssj,jssj,xmmc,xxnr,zbdw) values (?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (GxjlModel gxjlModel : gxjlList){
			params.add(new String[]{zgh, 
					gxjlModel.getQssj(),gxjlModel.getJssj(),gxjlModel.getXmmc(),gxjlModel.getXxnr(),gxjlModel.getZbdw()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询高校交流情况***/
	public List<HashMap<String,String>> getGxjlList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_gxjl where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除教学情况***/
	public boolean deleteJxqk(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_jxqk where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入教学情况***/
	public boolean insertJxqk(String zgh,List<JxqkModel> jxqkList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_jxqk(zgh,xsskc,xsskcszbm,kes) values (?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (JxqkModel jxqkModel : jxqkList){
			params.add(new String[]{zgh, 
					jxqkModel.getXsskc(),jxqkModel.getXsskcszbm(),jxqkModel.getKes()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询教学情况***/
	public List<HashMap<String,String>> getJxqkList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_jxqk where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除科研情况论文发表等情况***/
	public boolean deleteKylwfb(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_kylwfb where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入科研情况论文发表等情况***/
	public boolean insertKylwfb(String zgh,List<KylwfbModel> kylwfbList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_kylwfb(zgh,lwmc,dyzz,fybm,fbkw,fbsj,qkh,kwlb,gtzz) values (?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (KylwfbModel kylwfbModel : kylwfbList){
			params.add(new String[]{zgh, 
					kylwfbModel.getLwmc(),kylwfbModel.getDyzz(),kylwfbModel.getFybm(),kylwfbModel.getFbkw(),kylwfbModel.getFbsj(),kylwfbModel.getQkh(),kylwfbModel.getKwlb()
					,kylwfbModel.getGtzz()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询科研情况论文发表等情况***/
	public List<HashMap<String,String>> getKylwfbList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_kylwfb where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除科研情况论文发表等情况***/
	public boolean deleteKyktfb(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_kyqkktfb where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入科研情况论文发表等情况***/
	public boolean insertKyktfb(String zgh,List<KyktfbModel> kyktfbList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_kyqkktfb(zgh,xmlb,ktbh,ktzcr,ktzcy,ktmc,xbjf,sfjt,jtsj,kygzl) values (?,?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (KyktfbModel kyktfbModel : kyktfbList){
			params.add(new String[]{zgh, 
					kyktfbModel.getXmlb(),kyktfbModel.getKtbh(),kyktfbModel.getKtzcr(),kyktfbModel.getKtzcy(),kyktfbModel.getKtmc(),
					kyktfbModel.getXbjf(),kyktfbModel.getSfjt(),kyktfbModel.getJtsj(),kyktfbModel.getKygzl()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询科研情况论文发表等情况***/
	public List<HashMap<String,String>> getKyktfbList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_kyqkktfb where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除资格证书情况***/
	public boolean deleteZgzs(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_zgzs where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入资格证书情况***/
	public boolean insertZgzs(String zgh,List<ZgzsModel> zgzsList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_zgzs(zgh,zsmc,hdsj) values (?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (ZgzsModel zgzsModel : zgzsList){
			params.add(new String[]{zgh, 
					zgzsModel.getZsmc(),zgzsModel.getHdsj()
			});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询资格证书情况***/
	public List<HashMap<String,String>> getZgzsList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_zgzs where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除辅导员工作期间所获得的个人荣誉情况***/
	public boolean deleteZxhjqk(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_zxhjqk where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入辅导员工作期间所获得的个人荣誉情况***/
	public boolean insertZxhjqk(String zgh,List<ZxhjqkModel> zxhjqkList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_zxhjqk(zgh,fybm,bsmc,hjsj,zbdw,hjlb,hjdj,gthjr) values (?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (ZxhjqkModel zxhjqkModel : zxhjqkList){
			params.add(new String[]{zgh,
					zxhjqkModel.getFybm(),zxhjqkModel.getBsmc(),zxhjqkModel.getHjsj(),zxhjqkModel.getZbdw(),zxhjqkModel.getHjlb(),
					zxhjqkModel.getHjdj(),zxhjqkModel.getGthjr()});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询辅导员工作期间所获得的个人荣誉情况***/
	public List<HashMap<String,String>> getZxhjqkList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_zxhjqk where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除辅导员工作期间学生所获得的荣誉情况***/
	public boolean deleteBjyyqk(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_bjyyqk where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入辅导员工作期间学生所获得的荣誉情况***/
	public boolean insertBjyyqk(String zgh,List<BjyyqkModel> bjyyqkList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_bjyyqk(zgh,fybm,xsxm,bsmc,hjsj,zbdw,hjlb,hjdj,gthjr) values (?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (BjyyqkModel bjyyqkModel : bjyyqkList){
			params.add(new String[]{zgh,
					bjyyqkModel.getFybm(),bjyyqkModel.getXsxm(),bjyyqkModel.getBsmc(),bjyyqkModel.getHjsj(),bjyyqkModel.getZbdw(),
					bjyyqkModel.getHjlb(),bjyyqkModel.getHjdj(),bjyyqkModel.getGthjr()
					});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询辅导员工作期间学生所获得的荣誉情况***/
	public List<HashMap<String,String>> getBjyyqkList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_bjyyqk where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**删除辅导员和行政工作***/
	public boolean deleteFdyxzgz(String zgh) throws Exception{
		String sql = "delete from xg_szdw_fdyxx_fdyxzgz where zgh=?";
		return dao.runDelete(sql, new String[]{zgh}) > 0;
	}
	
	/***插入辅导员和行政工作***/
	public boolean insertFdyxzgz(String zgh,List<FdyxzgzModel> fdyxzgzList) throws Exception{
		String sql = "insert into xg_szdw_fdyxx_fdyxzgz(zgh,qzsj,gzxb,gznr,sdbj,bjrs,gzkh"
				+ ",zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13,zd14,zd15,zd16) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (FdyxzgzModel fdyxzgzModel : fdyxzgzList){
			params.add(new String[]{zgh, 
					fdyxzgzModel.getQzsj(),fdyxzgzModel.getGzxb(),fdyxzgzModel.getGznr(),
					fdyxzgzModel.getSdbj(),fdyxzgzModel.getBjrs(),fdyxzgzModel.getGzkh(),
					fdyxzgzModel.getZd1(),fdyxzgzModel.getZd2(),fdyxzgzModel.getZd3(),fdyxzgzModel.getZd4()
					,fdyxzgzModel.getZd5(),fdyxzgzModel.getZd6(),fdyxzgzModel.getZd7(),fdyxzgzModel.getZd8()
					,fdyxzgzModel.getZd9(),fdyxzgzModel.getZd10(),fdyxzgzModel.getZd11(),fdyxzgzModel.getZd12()
					,fdyxzgzModel.getZd13(),fdyxzgzModel.getZd14(),fdyxzgzModel.getZd15(),fdyxzgzModel.getZd16() });
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/***查询辅导员和行政工作***/
	public List<HashMap<String,String>> getFdyxzgzList(String zgh){
		String sql = "select * from xg_szdw_fdyxx_fdyxzgz where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/**
	 * 
	 * @描述: 培训经历修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午10:18:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updatePxjl(String zgh, List<PxjlModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_pxjl ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_pxjl ");
		if(Base.xxdm.equals("10704")){
			sql.append("(pxid,zgh,pxmc,pxjb,zzdw,pxdd,pxkssj,pxjssj,zd1,zd2,zd3,zd4,fjid)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}else{			
			sql.append("(pxid,zgh,pxmc,pxjb,zzdw,pxdd,pxkssj,pxjssj,zd1,zd2,zd3,zd4)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
		}
		String guid = null;
		if (list != null && list.size() > 0) {
			for(PxjlModel pxjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				if(Base.xxdm.equals("10704")){
					String[] param = { guid, zgh, pxjlModel.getPxmc(),
							pxjlModel.getPxjb(), pxjlModel.getZzdw(), pxjlModel.getPxdd(),
							pxjlModel.getPxkssj(), pxjlModel.getPxjssj(),pxjlModel.getZd1(),pxjlModel.getZd2(),
							pxjlModel.getZd3(),pxjlModel.getZd4(),pxjlModel.getFjid()};
					paramList.add(param);
				}else{
					String[] param = { guid, zgh, pxjlModel.getPxmc(),
							pxjlModel.getPxjb(), pxjlModel.getZzdw(), pxjlModel.getPxdd(),
							pxjlModel.getPxkssj(), pxjlModel.getPxjssj(),pxjlModel.getZd1(),pxjlModel.getZd2(),
							pxjlModel.getZd3(),pxjlModel.getZd4()};
					paramList.add(param);
				}
				
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	/**
	 * 
	 * @描述: 教学工作修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午11:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateJxgz(String zgh, List<JxgzModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_jxgz ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_jxgz ");
		sql.append("(jxid,zgh,qzsj,skmc,skcc,jxgzl,pjjl,ndkhjl)");
		sql.append(" values(?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(JxgzModel jxgzModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, jxgzModel.getQzsj(),
						jxgzModel.getSkmc(), jxgzModel.getSkcc(), jxgzModel.getJxgzl(),
						jxgzModel.getPjjl(), jxgzModel.getNdkhjl() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 职业资格证书修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 上午11:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZyzgzs(String zgh, List<ZyzgzsModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_zyzgzs ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_zyzgzs ");
		sql.append("(zyzgzsid,zgh,zgmc,rdjg,zsbfsj,jbcc,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(ZyzgzsModel zyzgzsModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						zyzgzsModel.getZgmc(), zyzgzsModel.getRdjg(), zyzgzsModel.getZsbfsj(),
						zyzgzsModel.getJbcc(),zyzgzsModel.getZd1(),zyzgzsModel.getZd2(),zyzgzsModel.getZd3(),zyzgzsModel.getZd4(),
						zyzgzsModel.getZd5(),zyzgzsModel.getZd6(),zyzgzsModel.getZd7(),zyzgzsModel.getZd8()};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	/**
	 * 
	 * @描述: 著作、教材情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-16 下午04:28:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZzjcqk(String zgh, List<ZzjcqkModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_zzjcqk ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_zzjcqk ");
		sql.append("(zzjcqkid,zgh,zzjcmc,cbsj,cbs,jclb,cbqk,zd1,zd2,zd3,zd4)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(ZzjcqkModel zzjcqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						zzjcqkModel.getZzjcmc(), zzjcqkModel.getCbsj(), zzjcqkModel.getCbs(),
						zzjcqkModel.getJclb(), zzjcqkModel.getCbqk(),zzjcqkModel.getZd1(),zzjcqkModel.getZd2(),
						zzjcqkModel.getZd3(),zzjcqkModel.getZd4() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 发明创造等情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 上午09:31:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFmcz(String zgh, List<FmczModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_fmcz ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_fmcz ");
		sql.append("(fmczid,zgh,mc,sj,brpm,lx,bz)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(FmczModel fmczModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						fmczModel.getMc(), fmczModel.getSj(), fmczModel.getBrpm(),
						fmczModel.getLx(), fmczModel.getBz() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 教学科研成果（含竞赛）获奖情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午02:05:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateJxky(String zgh, List<JxkyModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_jxky ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_jxky ");
		sql.append("(jxkyid,zgh,cgmc,hjsj,bjdw,hjdj,hjlx,brpm,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13,zd14)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(JxkyModel jxkyModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						jxkyModel.getCgmc(), jxkyModel.getHjsj(), jxkyModel.getBjdw(),
						jxkyModel.getHjdj(),jxkyModel.getHjlx(), jxkyModel.getBrpm(),jxkyModel.getZd1(),jxkyModel.getZd2(),jxkyModel.getZd3(),
						jxkyModel.getZd4(),jxkyModel.getZd5(),jxkyModel.getZd6(),jxkyModel.getZd7(),jxkyModel.getZd8(),jxkyModel.getZd9(),
						jxkyModel.getZd10(),jxkyModel.getZd11(),jxkyModel.getZd12(),jxkyModel.getZd13(),jxkyModel.getZd14() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 获奖情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午03:22:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHjjl(String zgh, List<HjjlModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_hjxx ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_hjxx ");
		sql.append("(hjid,zgh,hjjb,hjmc,bjdw,hjzrs,brpm,hjbz,hjsj,zd1,zd2,zd3,zd4,zd5,zd6,fjid)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(HjjlModel hjjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = null;
				if(Base.xxdm.equals("10704")){
					param = new String[]{ guid, zgh, 
							hjjlModel.getHjjb(), hjjlModel.getHjmc(), hjjlModel.getBjdw(),
							hjjlModel.getHjzrs(), hjjlModel.getBrpm(), hjjlModel.getHjbz(), 
							hjjlModel.getHjsj(),hjjlModel.getZd1(),hjjlModel.getZd2(),hjjlModel.getZd3(),hjjlModel.getZd4(),hjjlModel.getZd5(),hjjlModel.getZd6(),hjjlModel.getFjid()};
					
				}else{
					param = new String[]{ guid, zgh, 
							hjjlModel.getHjjb(), hjjlModel.getHjmc(), hjjlModel.getBjdw(),
							hjjlModel.getHjzrs(), hjjlModel.getBrpm(), hjjlModel.getHjbz(), 
							hjjlModel.getHjsj(),hjjlModel.getZd1(),hjjlModel.getZd2(),hjjlModel.getZd3(),hjjlModel.getZd4(),hjjlModel.getZd5(),hjjlModel.getZd6() };
				}
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 论文发表情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午04:32:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFblw(String zgh, List<FblwModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_fblw ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		if("10704".equals(Base.xxdm)){
			sql.append("insert into xg_szdw_fdyxx_fblw ");
			sql.append("(lwid,zgh,lwmc,kwmc,kwjb,fbrq,kwjh,kwqh,kwbz,brpm,fjid)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		}else{			
			sql.append("insert into xg_szdw_fdyxx_fblw ");
			sql.append("(lwid,zgh,lwmc,kwmc,kwjb,fbrq,kwjh,kwqh,kwbz,zzzrs,brpm,zd1,zd2)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}
		String guid = null;
		if (list != null && list.size() > 0) {
			for(FblwModel fblwModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = null;
				if(Base.xxdm.equals("10704")){
					param = new String[]{ guid, zgh, 
							fblwModel.getLwmc(),fblwModel.getKwmc(),fblwModel.getKwjb(),
							fblwModel.getFbrq(),fblwModel.getKwjh(),fblwModel.getKwqh(),
							fblwModel.getKwbz(),fblwModel.getBrpm(),fblwModel.getFjid()};
				}else{					
					param = new String[]{ guid, zgh, 
							fblwModel.getLwmc(),fblwModel.getKwmc(),fblwModel.getKwjb(),
							fblwModel.getFbrq(),fblwModel.getKwjh(),fblwModel.getKwqh(),
							fblwModel.getKwbz(),fblwModel.getZzzrs(),fblwModel.getBrpm(),fblwModel.getZd1(),fblwModel.getZd2()};
				}
				
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	/**
	 * 
	 * @描述: 科研项目情况修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-18 上午09:08:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateKyxm(String zgh, List<KyxmModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_kyxm ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		if("10125".equals(Base.xxdm)){
			sql.append("insert into xg_szdw_fdyxx_kyxm ");
			sql.append("(kyid,zgh,xmmc,xmly,xmbh,lxrq,jsrq,xmfzr,kybz,kyxmnd,kyxmdj)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		}else if("10704".equals(Base.xxdm)) {//西安科技大学个性化
			sql.append("insert into xg_szdw_fdyxx_kyxm ");
			sql.append("(kyid,zgh,xmmc,xmly,xmbh,lxrq,jsrq,xmfzr,kybz,fjid)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		}else{
			sql.append("insert into xg_szdw_fdyxx_kyxm ");
			sql.append("(kyid,zgh,xmmc,xmly,xmbh,lxrq,jsrq,xmfzr,kybz,xmzrs,brpm,xmjb,kyxmnd,kyxmdj)");
			sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}
		String guid = null;
		if (list != null && list.size() > 0) {
			if("10125".equals(Base.xxdm)){
				for(KyxmModel kyxmModel : list) {
					guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
					String[] param = { guid, zgh, 
							kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
							kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getXmfzr(),
							kyxmModel.getKybz(),kyxmModel.getKyxmnd(), kyxmModel.getKyxmdj()};
					paramList.add(param);
				}
				dao.runBatch(sql.toString(), paramList);
			}else if("10704".equals(Base.xxdm)){//西安科技大学个性化
				for(KyxmModel kyxmModel : list) {
					guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
					String[] param = { guid, zgh, 
							kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
							kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getXmfzr(),
							kyxmModel.getKybz(),kyxmModel.getFjid()};
					paramList.add(param);
				}
				dao.runBatch(sql.toString(), paramList);
			}else{
				for(KyxmModel kyxmModel : list) {
					guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
					String[] param = { guid, zgh, 
							kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
							kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getXmfzr(),
							kyxmModel.getKybz(),kyxmModel.getXmzrs(),kyxmModel.getBrpm(),kyxmModel.getXmjb(),
							kyxmModel.getKyxmnd(), kyxmModel.getKyxmdj()};
					paramList.add(param);
				}
				dao.runBatch(sql.toString(), paramList);
			}
			
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述: 其它成果修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-17 下午04:32:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQtcg(String zgh, List<QtcgModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_qtcg ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_qtcg ");
		if("10704".equals(Base.xxdm)){
			sql.append("(zgh,cgmc,cglx,cgjb,cgfbrq,cgzrs,cgbz,brpm,fjid)");
			sql.append(" values(?,?,?,?,?,?,?,?,?)");
		}else{
			sql.append("(zgh,cgmc,cglx,cgjb,cgfbrq,cgzrs,cgbz,brpm)");
			sql.append(" values(?,?,?,?,?,?,?,?)");
		}
		
		String guid = null;
		if (list != null && list.size() > 0) {
			for(QtcgModel qtcgModel : list) {
				String[] param = null;
				if("10704".equals(Base.xxdm)){
					param =  new String[]{zgh,qtcgModel.getCgmc(),qtcgModel.getCglx(),qtcgModel.getCgjb(),
							qtcgModel.getCgfbrq(),qtcgModel.getCgzrs(),qtcgModel.getCgbz(),qtcgModel.getBrpm(),qtcgModel.getFjid()};
				}else{
					param =  new String[]{zgh,qtcgModel.getCgmc(),qtcgModel.getCglx(),qtcgModel.getCgjb(),
							qtcgModel.getCgfbrq(),qtcgModel.getCgzrs(),qtcgModel.getCgbz(),qtcgModel.getBrpm()};
					
				}
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}

	
	/**
	 * 
	 * @描述: 辅导员和行政工作修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-18 上午10:50:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFdyxzgz(String zgh, List<FdyxzgzModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_fdyxzgz ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_fdyxzgz ");
		sql.append("(xzgzid,zgh,qzsj,gzxb,gznr,sdbj,bjrs,gzkh,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13,zd14,zd15,zd16)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(FdyxzgzModel fdyxzgzModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						fdyxzgzModel.getQzsj(),fdyxzgzModel.getGzxb(),fdyxzgzModel.getGznr(),
						fdyxzgzModel.getSdbj(),fdyxzgzModel.getBjrs(),fdyxzgzModel.getGzkh(),
						fdyxzgzModel.getZd1(),fdyxzgzModel.getZd2(),fdyxzgzModel.getZd3(),fdyxzgzModel.getZd4()
						,fdyxzgzModel.getZd5(),fdyxzgzModel.getZd6(),fdyxzgzModel.getZd7(),fdyxzgzModel.getZd8()
						,fdyxzgzModel.getZd9(),fdyxzgzModel.getZd10(),fdyxzgzModel.getZd11(),fdyxzgzModel.getZd12()
						,fdyxzgzModel.getZd13(),fdyxzgzModel.getZd14(),fdyxzgzModel.getZd15(),fdyxzgzModel.getZd16()};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	
	//fdyxxb取值关联代码表
	public HashMap<String, String> getFdyxxMap(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.zgh,");
		sql.append(" t.xm,");
		sql.append(" decode(t.xb, '1', '男', '2', '女', t.xb) xb,");
		sql.append(" t.sfzh,");
		sql.append(" t1.mzmc mz,");
		sql.append(" t2.zzmmmc zzmm,");
		sql.append(" to_char(to_date(t.csrq, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-mm-dd') csrq,");
		sql.append(" t3.bmmc,");
		sql.append(" t4.zcmc,");
		sql.append(" t5.qxmc jg,");
		sql.append(" t.xl,");
		sql.append(" t.xw,");
		sql.append(" t.zjz,");
		sql.append(" to_char(to_date(t.lxgzsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-mm-dd') RZSJ,");
		sql.append(" t.byyx,");
		sql.append(" t.sxzy,");
		sql.append(" t.XSGZYJFX yjfx,");
		sql.append(" t.lxdh,");
		sql.append(" t.JTZZ,");
		sql.append(" t.zyzz,");
		sql.append(" t.CSFDYSJ sfjz,");
		sql.append(" t.bz");
		sql.append(" from fdyxxb t");
		sql.append(" left join mzdmb t1");
		sql.append(" on t.mz = t1.mzdm");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t.zzmm = t2.zzmmdm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.bmdm = t3.bmdm");
		sql.append(" left join zcb t4");
		sql.append(" on t.zc = t4.zcdm");
		sql.append(" left join dmk_qx t5");
		sql.append(" on t.jgxs = t5.qxdm");
		sql.append("  where t.zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 
	 * @描述:北京中医药大学教师身份保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-19 下午02:04:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zghs
	 * @param jssf
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savejssfPlwh(String[] zghs,String jssf) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update FDYXXB set jssf = ?");
		sql.append(" where zgh = ?");
		sql.append(" ");
		sql.append(" ");
		ArrayList<String[]> params = new ArrayList<String[]>();
		ArrayList<String> lsparams = null;
		for (int i = 0; i < zghs.length; i++) {
			lsparams = new ArrayList<String>();
			lsparams.add(jssf);
			lsparams.add(zghs[i]);
			params.add(lsparams.toArray(new String[]{}));
		}
		return dao.runBatch(sql.toString(), params).length > 0 ?true:false;
	}

	/** 
	 * @描述:获取带班期间学生荣誉(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-30 下午02:56:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsryList(String zgh) {
		String sql = "select t.* from XG_SZDW_FDYXX_BJYYQK t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}


	public List<HashMap<String, String>> getGrryList(String zgh) {
		String sql = "select t.* from XG_SZDW_FDYXX_ZXHJQK t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	
	public List<HashMap<String, String>> getKylwList(String zgh) {
		String sql = "select t.* from XG_SZDW_FDYXX_KYLWFB t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	public List<HashMap<String, String>> getKyktList(String zgh) {
		String sql = "select t.* from XG_SZDW_FDYXX_KYQKKTFB t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	public List<HashMap<String, String>> getGzjlList(String zgh,String num) {
		String sql = "select t.* from XG_SZDW_FDYXX_GZQK t where zgh = ? and  ROWNUM <= ?";
		return dao.getListNotOut(sql, new String[]{zgh,num});
	}

	/** 
	 * @描述:带班总人数(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-2-6 下午04:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getDbzrs(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(zrs) dbzrs from (" +
				" select count(xh)zrs from xsxxb a " +
				"left join fdybjb b on b.bjdm=a.bjdm where b.zgh = ? ");
		sql.append(" union ");
		sql.append(" select count(xh)zrs from xsxxb a " +
				"left join BZRBBB b on b.bjdm=a.bjdm where b.zgh = ?)");
		return dao.getOneRs(sql.toString(), new String[]{zgh,zgh},"dbzrs");
	}
	/**
	 * 
	 * @描述:华东交通大学
	 * @作者：jz[工号：1529]
	 * @日期：2018-1-29 下午11:19:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	//工作经历
	public boolean updateGzjl(String zgh, List<GzqkModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_gzqk ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_gzqk ");
		sql.append("(gzqkid,zgh,dwmc,qssj,jssj,zw)");
		sql.append(" values(?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(GzqkModel gzqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						gzqkModel.getDwmc(),gzqkModel.getQssj(),gzqkModel.getJssj(),gzqkModel.getZw() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//职业培训
	public boolean updateZypx(String zgh, List<ZypxModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_zypx ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_zypx ");
		sql.append("(zypxid,zgh,qssj,jssj,hjsj,pxmc,zbdw)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(ZypxModel zypxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						zypxModel.getQssj(),zypxModel.getJssj(),zypxModel.getHjsj(),zypxModel.getPxmc(),zypxModel.getZbdw() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//项目学习
	public boolean updateXmxx(String zgh, List<XmxxModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_xmxx ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_xmxx ");
		sql.append("(xmxxid,zgh,qssj,jssj,xmmc,xxnr,zbdw)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(XmxxModel xmxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						xmxxModel.getQssj(),xmxxModel.getJssj(),xmxxModel.getXmmc(),xmxxModel.getXxnr(),xmxxModel.getZbdw() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//高校交流
	public boolean updateGxjl(String zgh, List<GxjlModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_gxjl ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_gxjl ");
		sql.append("(gxjlid,zgh,qssj,jssj,xmmc,xxnr,zbdw)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(GxjlModel gxjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						gxjlModel.getQssj(),gxjlModel.getJssj(),gxjlModel.getXmmc(),gxjlModel.getXxnr(),gxjlModel.getZbdw()
				};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//教学情况
	public boolean updateJxqk(String zgh, List<JxqkModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_jxqk ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_jxqk ");
		sql.append("(jxqkid,zgh,xsskc,xsskcszbm,kes)");
		sql.append(" values(?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(JxqkModel jxqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						jxqkModel.getXsskc(),jxqkModel.getXsskcszbm(),jxqkModel.getKes()
				};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//科研情况论文发表
	public boolean updateKylwfb(String zgh, List<KylwfbModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_kylwfb ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_kylwfb ");
		sql.append("(kyqklwfbid,zgh,lwmc,dyzz,fybm,fbkw,fbsj,qkh,kwlb,gtzz)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(KylwfbModel kylwfbModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						kylwfbModel.getLwmc(),kylwfbModel.getDyzz(),kylwfbModel.getFybm(),kylwfbModel.getFbkw(),kylwfbModel.getFbsj(),kylwfbModel.getQkh(),kylwfbModel.getKwlb()
						,kylwfbModel.getGtzz()
				};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//科研情况课题发表
	public boolean updateKyktfb(String zgh, List<KyktfbModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_kyqkktfb ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_kyqkktfb ");
		sql.append("(kyqkktfbid,zgh,xmlb,ktbh,ktzcr,ktzcy,ktmc,xbjf,sfjt,jtsj,kygzl)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(KyktfbModel kyktfbModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						kyktfbModel.getXmlb(),kyktfbModel.getKtbh(),kyktfbModel.getKtzcr(),kyktfbModel.getKtzcy(),kyktfbModel.getKtmc(),
						kyktfbModel.getXbjf(),kyktfbModel.getSfjt(),kyktfbModel.getJtsj(),kyktfbModel.getKygzl()
				};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//资格证书
	public boolean updateZgzs(String zgh, List<ZgzsModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_zgzs ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_zgzs ");
		sql.append("(zgzsid,zgh,zsmc,hdsj)");
		sql.append(" values(?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(ZgzsModel zgzsModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						zgzsModel.getZsmc(),zgzsModel.getHdsj()
						};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//在校获奖情况（个人）
	public boolean updateZxhjqk(String zgh, List<ZxhjqkModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_zxhjqk ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_zxhjqk ");
		sql.append("(grhjqkid,zgh,fybm,bsmc,hjsj,zbdw,hjlb,hjdj,gthjr)");
		sql.append(" values(?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(ZxhjqkModel zxhjqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						zxhjqkModel.getFybm(),zxhjqkModel.getBsmc(),zxhjqkModel.getHjsj(),zxhjqkModel.getHjsj(),zxhjqkModel.getZbdw(),zxhjqkModel.getHjlb(),
						zxhjqkModel.getHjdj(),zxhjqkModel.getGthjr()
						};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//在校获奖情况（学生）
	public boolean updateBjyyqk(String zgh, List<BjyyqkModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_bjyyqk ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_bjyyqk ");
		sql.append("(xshjqkid,zgh,fybm,xsxm,bsmc,hjsj,zbdw,hjlb,hjdj,gthjr)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(BjyyqkModel bjyyqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						bjyyqkModel.getFybm(),bjyyqkModel.getXsxm(),bjyyqkModel.getBsmc(),bjyyqkModel.getHjsj(),bjyyqkModel.getZbdw(),
						bjyyqkModel.getHjlb(),bjyyqkModel.getHjdj(),bjyyqkModel.getGthjr()
						};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	//华东交通大学
	public String checkxszpSfcz(String zgh){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select * from FDY_PIC where zgh= ?");

		return dao.getOneRs("select count(*) cnt from FDY_PIC where zgh=?", new String[]{zgh}, "cnt");
	}
	
	/**
	 * @描述: 根据zgh获取bzrbbb、fdybjb带班总人数
	 * @作者：  xiaxia[工号：1104]
	 * @日期： 2018-2-7 下午02:31:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param zgh1
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String fdydbrs(String zgh , String zgh1){
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(zrs) dbrs from ( ");
		sql.append(" select count(xh) zrs from fdybjb b left join view_njxyzybj_all a on a.bjdm = b.bjdm left join xsxxb c on a.bjdm = c.bjdm where b.zgh = ?");
		sql.append(" union");
		sql.append(" select count(xh) zrs from BZRBBB b left join view_njxyzybj_all a on a.bjdm = b.bjdm left join xsxxb c on a.bjdm = c.bjdm where b.zgh = ?");
		sql.append(") ");
		return dao.getOneRs(sql.toString(), new String[]{zgh , zgh1} , "dbrs");
	}
	//现带班级 带班班级，班级人数
	public List<HashMap<String, String>> bjmcAbjrs(String zgh , String zgh1){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select * from (select a.bjdm, a.bjmc, count(c.xh) bjrs  from fdybjb b left join" +
				" view_njxyzybj_all a on a.bjdm = b.bjdm left join xsxxb c on a.bjdm = c.bjdm " +
				"where b.zgh = ? group by a.bjdm, a.bjmc union select a.bjdm, a.bjmc, " +
				"count(c.xh) bjrs from BZRBBB b left join view_njxyzybj_all a on a.bjdm = b.bjdm " +
				"left join xsxxb c on a.bjdm = c.bjdm where b.zgh = ? group by a.bjdm, a.bjmc) where bjrs != 0");
		return dao.getListNotOut(sql.toString(), new String[]{zgh , zgh1} );
	}
/**
 * @description	： 西安交通大学--获得证书
 * @author 		： CP（1352）
 * @date 		：2018-3-9 下午02:01:49
 * @param zgh
 * @return
 */
	public List<HashMap<String, String>> getHdzsList(String zgh) {
		String sql = "select t.* from xg_szdw_fdyxx_hdzs t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	/**
	 * @description	： 更新获得证书
	 * @author 		： CP（1352）
	 * @date 		：2018-3-9 下午02:58:49
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean updateHdzs(String zgh, List<HdzsModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_hdzs ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_hdzs ");
		sql.append("(hdzsid,zgh,zsmc,hdsj,bz)");
		sql.append(" values(?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(HdzsModel hdzsModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						hdzsModel.getZsmc(),hdzsModel.getHdsj(),hdzsModel.getBz()
						};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	/**
	 * @description	： 更新研究成果
	 * @author 		： CP（1352）
	 * @date 		：2018-3-9 下午02:59:08
	 * @param zgh
	 * @param yjcgList
	 * @return
	 * @throws Exception 
	 */
	public boolean updateYjcg(String zgh, List<YjcgModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_szdw_fdyxx_yjcg ");
		sql.append(" where zgh=?");
		String[] input = { zgh };
		dao.runDelete(sql.toString(), input); //删除原纪录
		
		sql = new StringBuffer();
		sql.append("insert into xg_szdw_fdyxx_yjcg ");
		sql.append("(yjcgid,zgh,cgmc,cglb,hdsj,gs)");
		sql.append(" values(?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(YjcgModel yjcgModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, zgh, 
						yjcgModel.getCgmc(),yjcgModel.getCglb(),yjcgModel.getHdsj(),yjcgModel.getGs()
						};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	/**
	 * @description	： 获取研究成果列表
	 * @author 		： CP（1352）
	 * @date 		：2018-3-9 下午03:09:40
	 * @param zgh
	 * @return
	 */
	public List<HashMap<String, String>> getYjcgList(String zgh) {
		String sql = "select t.* from xg_szdw_fdyxx_yjcg t where zgh = ?";
		return dao.getListNotOut(sql, new String[]{zgh});
	}

	public boolean updateJtcyxx(String zgh,List<JtcyModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from XG_JSXX_JTCYXX where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into XG_JSXX_JTCYXX(JTCYXM,gx,JTCYCSNY,JTCYZZMM,JTCYGZDW,JTCYZW,zgh) values (?,?,?,?,?,?,?)");
			for(JtcyModel item : list){
				String[] param = {item.getJtcyxm(),item.getGx(),item.getJtcycsny(),item.getJtcyzzmm(),item.getJtcygzdw(),item.getJtcyzw(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}

	public List<HashMap<String,String>> getJtcyxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from XG_JSXX_JTCYXX where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}

	public boolean updateGwydxx(String zgh,List<GwydModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from XG_JSXX_GWYDXXB where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into XG_JSXX_GWYDXXB(gwydkssj,gwydjssj,gwydbmdm,gwydgwdm,zgh) values (?,?,?,?,?)");
			for(GwydModel item : list){
				String[] param = {item.getGwydkssj(),item.getGwydjssj(),item.getGwydbmdm(),item.getGwydgwdm(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}

	public List<HashMap<String,String>> getGwydxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from XG_JSXX_GWYDXXB where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}

	public boolean updateJtjlxx(String zgh,List<JtjlModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from xg_jsxx_jtjlxxb where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into xg_jsxx_jtjlxxb(jtjlkssj,jtjljssj,jtjlszdw,jtjlszbm,jtjlgw,zgh) values (?,?,?,?,?,?)");
			for(JtjlModel item : list){
				String[] param = {item.getJtjlkssj(),item.getJtjljssj(),item.getJtjlszdw(),item.getJtjlszbm(),item.getJtjlgw(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}

	public List<HashMap<String,String>> getJtjlxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from xg_jsxx_jtjlxxb where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}

	public boolean updateZyjsgwxx(String zgh,List<ZyjsgwModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from xg_jsxx_zyjszwxx where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into xg_jsxx_zyjszwxx(zyjszwgwdjdm,zyjszwgwdm,zyjszwprsj,zgh) values (?,?,?,?)");
			for(ZyjsgwModel item : list){
				String[] param = {item.getZyjszwgwdjdm(),item.getZyjszwgwdm(),item.getZyjszwprsj(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}
	public List<HashMap<String,String>> getZyjsgwxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from xg_jsxx_zyjszwxx where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}
	public boolean updateXxjlxx(String zgh,List<XxjlModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from xg_jsxx_xxjlxxb where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into xg_jsxx_xxjlxxb(xxjlkssj,xxjljssj,xxjlxxmc,xxjlzymc,xxjlxw,xxjlxl,zgh) values (?,?,?,?,?,?,?)");
			for(XxjlModel item : list){
				String[] param = {item.getXxjlkssj(),item.getXxjljssj(),item.getXxjlxxmc(),item.getXxjlzymc(),item.getXxjlxw(),item.getXxjlxl(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}

	public List<HashMap<String,String>> getXxjlxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from xg_jsxx_xxjlxxb where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}

	public boolean updateGzjlxx(String zgh,List<GzjlModel> list) throws Exception {

		StringBuffer sql = null;
		sql = new StringBuffer();
		sql.append("delete from xg_jsxx_gzjlxxb where zgh = ?");
		boolean flag = dao.runUpdate(sql.toString(),new String[]{zgh});
		if(flag && !CollectionUtils.isEmpty(list)){
			List<String[]> paramList = new ArrayList<String[]>();
			sql = new StringBuffer();
			sql.append("insert into xg_jsxx_gzjlxxb(gzjlkssj,gzjljssj,gzjlgzdw,gzjlszbm,gzjlgw,gzjlbz,zgh) values (?,?,?,?,?,?,?)");
			for(GzjlModel item : list){
				String[] param = {item.getGzjlkssj(),item.getGzjljssj(),item.getGzjlgzdw(),item.getGzjlszbm(),item.getGzjlgw(),item.getGzjlbz(),zgh};
				paramList.add(param);
			}
			flag = dao.runBatchBoolean(sql.toString(),paramList);
		}
		return flag;
	}

	public List<HashMap<String,String>> getGzjlxx(String zgh){
		StringBuilder sql = new StringBuilder("select * from xg_jsxx_gzjlxxb where zgh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{zgh});
	}

	public List<HashMap<String,String>> getGwxx(String gwdjdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_JSXX_GWDMB where djdm = ? order by dm");
		return dao.getListNotOut(sql.toString(),new String[]{gwdjdm});
	}
}
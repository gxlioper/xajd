package xgxt.audit.spgc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;



/**
 * 审核具体操作类
 * @author Penghui.Qu
 */
public class AuditingManage implements AuditingInterface{


	/**
	 * 单个审核后调用此方法,继承后请重写
	 * @param o
	 * @return
	 */
	protected boolean auditingAfter(Object o) {
		
		return true;
	}
	
	/**
	 * 单个审核前调用此方法,继承后请重写
	 * @param o
	 * @return
	 */
	protected boolean auditingBefore(Object o) {
		
		return true;
	}

	
	/**
	 * 批量审核后调用此方法,继承后请重写
	 * @param o
	 * @return
	 */
	protected boolean batchAuditingAfter(Object o) {
		
		return true;
	}
	
	
	/**
	 * 批量审核前调用此方法,继承后请重写
	 * @param o
	 * @return
	 */
	protected boolean batchAuditingBefore(Object o) {
		
		return true;
	}
	
	
	/**
	 * 批量审核，每条记录审核后做的操作
	 * @param o
	 * @return
	 */
	protected boolean doAuditingAfter(Object o) {
		
		return true;
	}
	
	
	/**
	 * 批量审核，每条记录审核前的操作
	 * @param o
	 * @return
	 */
	protected boolean doAuditingBefore(Object o) {
		
		return true;
	}
	
	
	/**
	 * 保存审核过程
	 */
	public boolean saveAuditing(AuditingModel model,String shztb,
					         Object befroeParms,Object afterParms){
		boolean result = false;
		
		//调用审核前操作
		result = this.auditingBefore(befroeParms);
		//审核中
		result = true ? this.auditing(model,shztb) : false;
		//审核后
		result = true ? this.auditingAfter(afterParms) : false;
		
		return result;
	}
	
	/**
	 * 保存审核过程
	 */
	public boolean saveAuditing(AuditingModel model,String shztb){
		return this.saveAuditing(model, shztb, null, null);
	}
	
	
	/**
	 * 保存批量审核
	 */
	public boolean saveBatchAuditing(AuditingModel model, String[] pkValues, String shztb, Object befroeParms, Object afterParms) {
		
		boolean result = false;
		
		//批量审核前操作
		result = this.batchAuditingBefore(befroeParms);
		//批量审核中
		result = true ? this.batchAuditing(model, pkValues, shztb,null,null,false) : false;
		//批量审核后操作
		result = true ? this.batchAuditingAfter(afterParms) : false;
		
		return result;
	}

	
	/**
	 * 保存批量审核
	 */
	public boolean saveBatchAuditing(AuditingModel model, String[] pkValues, String shztb) {
		
		return this.saveBatchAuditing(model, pkValues, shztb, null, null);
	}
	
	
	
	/**
	 * 批量审核并且每条审核前后都有操作
	 */
	public boolean saveBatchAuditingAndDoSomething(AuditingModel model, String[] pkValues, String shztb, Object befroeParms, Object afterParms) {
		
		return this.batchAuditing(model, pkValues, shztb, befroeParms, afterParms,true);
	}

	/**
	 * 批量审核
	 * @param flg 审核前后是否有操作
	 */
	protected boolean batchAuditing(AuditingModel model,String[] pkValues,String shztb, Object befroeParms, Object afterParms,boolean flg){
		
		boolean result = false;
		DAO dao = DAO.getInstance();
		
		if (null != pkValues && pkValues.length > 0){
			
			for (int i = 0 ; i < pkValues.length ; i++){

				//查询当前审核记录的审核流程编号
				String sql = "select shlcid from "+ shztb +" where id=? and rownum=1";
				String shlcid = dao.getOneRs(sql, new String[]{pkValues[i]}, "shlcid");
				
				model.setId(pkValues[i]);
				model.setShlcid(shlcid);
				
				//审核前操作
				if (flg){
					this.doAuditingBefore(befroeParms);
				}
				
				//一条一条审过去
				result = auditing(model, shztb);
				
				//审核后操作
				if (flg){
					this.doAuditingAfter(afterParms);
				}
				
				//若审核失败，停止审核
				if (!result){
					break;
				}
			}
		}
		return result;
	}
	
	
	
	/**
	 * 保存审核过程
	 * @param model
	 * @param shztb
	 * @return
	 */
	protected boolean auditing(AuditingModel model,String shztb) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into ")
		   .append(shztb)
		   .append(" (id,shlcid,xtgwid,shzt,shyj,shr,sftj,nextspgw)")
		   .append("  values (?,?,?,?,?,?,?,?)");
		
		//String delSQL = "delete from xg_xsxx_xjydshztb where id=? and xtgwid=? and shr=? and sftj='否'";
		
		String id = model.getId();
		String shlcid = model.getShlcid();
		String xtgwid = getSpgw(model,shztb);
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getShr();
		String sftj = model.getSftj();
		String nextPost = "";
		model.setXtgwid(xtgwid);
		
		if ("退回".equalsIgnoreCase(shzt)){
			//退回-如果有目标岗位就退回目标岗位 ，没有则退回上一级
			if (StringUtils.isNotNull(model.getThgw())){
				nextPost = model.getThgw();
			} else {
				nextPost = getBackPost(model);
			}
		} else if (!"不通过".equals(shzt)){
			//如果审核不通过，下一个审批岗位为空（即：流程停止）
			nextPost = getNextPost(model);
		}
		model.setNextPost(nextPost);
		
		try {
			//boolean del = dao.runUpdate(delSQL, new String[]{id,xtgwid,shr});
			//if (del){
				//往审核表中插入一条审批记录
				return dao.insert(sql.toString(), new String[]{id,shlcid,xtgwid,shzt,shyj,shr,sftj,nextPost});
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	/**
	 * 查询当前的审核岗位
	 * @param model
	 * @param shztb
	 * @return
	 */
	public String getSpgw(AuditingModel model , String shztb){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("  select * from (select nextspgw,shsj from ")
		   .append(shztb).append("  a where id=?  and sftj='是'  ")
		   .append(" and exists (select 1 from (select spgw,spyh from xg_xtwh_spgwyh where spyh=?) b")
		   .append(" where a.nextspgw=b.spgw) order by shsj desc) where rownum=1");
		
		return dao.getOneRs(sql.toString(), new String[]{model.getId(),model.getShr()}, "nextspgw");
	}
	
	
	/**
	 * 获取下一个审批岗位
	 */
	private String getNextPost(AuditingModel model) {

		String nextSpgw = "";
		DAO dao = DAO.getInstance();

		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		try {
			String[] spgw = dao.getArray(sql, new String[]{model.getShlcid()}, "spgw");
			
			if (null != spgw && spgw.length > 0){
				
				if (StringUtils.isNotNull(model.getXtgwid())){
					int i = StringUtils.getStrIndexInArray(model.getXtgwid(), spgw);
					nextSpgw = i != spgw.length-1 ? spgw[i+1] : null;
				} else {
					nextSpgw = spgw[0];
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nextSpgw;
	}
	
	
	/**
	 * 获取前一个审批岗位
	 * @param model
	 * @return
	 */
	private String getBackPost(AuditingModel model) {

		String backSpgw = "";
		DAO dao = DAO.getInstance();

		String sql = "select spgw from xg_xtwh_spbz where splc=? order by xh";
		try {
			String[] spgw = dao.getArray(sql, new String[]{model.getShlcid()}, "spgw");
			
			if (null != spgw && spgw.length > 0){
				
				if (StringUtils.isNotNull(model.getXtgwid())){
					int i = StringUtils.getStrIndexInArray(model.getXtgwid(), spgw);
					//前一个岗位，如果没有审核岗位则退回申请人
					backSpgw = i != 0 ? spgw[i-1] : "Applicant";
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return backSpgw;
	}
	
	
	/**
	 * 查询可退回的审核流程岗位
	 * @param shlcid 审核流程编号ID
	 * @param xtgwid 当前的审核岗位ID
	 * @return
	 */
	public List<HashMap<String,String>> getKthXtgw(String shlcid,String xtgwid){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.id,b.mc from xg_xtwh_spbz a left join xg_xtwh_spgw b ")
		   .append(" on a.spgw=b.id where splc=? and xh < (select xh from (select a.xh,b.id,b.mc ")
		   .append(" from xg_xtwh_spbz a left join xg_xtwh_spgw b on a.spgw=b.id where splc=? ")
		   .append(" ) where id=?) order by a.xh");
		
		   
		return dao.getListNotOut(sql.toString(), new String[]{shlcid,shlcid,xtgwid});
	}

	
	/**
	 * 判断当前是否是后一级审核
	 * @return
	 */
	public boolean isLastAudit(AuditingModel model){
		
		String xtgwid = model.getXtgwid();
		String nextPost = getNextPost(model);
		
		if (StringUtils.isNotNull(xtgwid) && StringUtils.isNull(nextPost)){
			return true;
		}
		return false;
	}
	
}

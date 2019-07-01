package com.zfsoft.xgxt.xlzx.zxyyclnew;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.action.Base;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/** 
 * 咨询预约处理
 */

public class ZxyyclDao extends SuperDAOImpl<ZxyyclForm> {
	protected Log logger = LogFactory.getLog(ZxyyclDao.class);
	
	
	/**
	 * 根据id查询心理咨询处理信息
	 */
	public HashMap<String,String> getXlzxInfoByYyId(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select t.id zxid,t.yyid,t.zxlx,t.zxrq,t.zxdz,t.zxtell,t.xspj,t.xspjzt,t.xspjsj,t.zxsfk,t.zxsfksj,t.status zxstatus,t.bz,t.datazt from xg_xlzx_xlzxb t where datazt='1' and yyid=? ");
		logger.debug("sql---"+sql.toString());
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 保存至心理咨询表
	 */
	public boolean saveXlzxInfo(ZxyyclForm model)
			throws Exception {
		String apzxs = model.getApzxs();
		if(apzxs == null){
			apzxs = model.getZgh();
		}
		String[] input = {model.getId(),model.getYyid(),model.getZxlx(),model.getZxrq(),model.getQssj(),model.getJssj(),
				          model.getZxdz(),model.getZxtell(),model.getXspj(),model.getXspjsj(),model.getZxsfk(),
						  model.getZxsfksj(),model.getZxstatus(),model.getBz(),model.getXspjzt(),Base.currXn,
						  Base.currXq,apzxs,model.getSjddm()};
		
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into xg_xlzx_xlzxb ");
		sql.append("(id,yyid,zxlx,zxrq,qssj,jssj,zxdz,zxtell,xspj,xspjsj,zxsfk,zxsfksj,");
		sql.append("  status,bz,xspjzt,xn,xq,apzxs,sjddm) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		flag = dao.insert(sql.toString(), input);
		logger.debug("---插入心理咨询表："+sql+"\n--"+Arrays.toString(input));
		return flag;
	}


	/**
	 * 删除咨询信息
	 */
	public int delZxInfoByYyid(String[] id) throws Exception {
		if (id == null || id.length == 0){
			logger.error("删除咨询信息操作不能进行!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_xlzxb");
		sql.append(" where  ");
		
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("yyid=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), id);
	}

	/** 
	 * 修改心理咨询信息表
	 */
	public boolean updateXlzxInfo(ZxyyclForm model) throws Exception{
		StringBuffer inputS = new StringBuffer();
		HashMap<String,String>  xlzxInfoList = this.getXlzxInfoByYyId(model.getYyid());
		if(StringUtil.isNull(model.getYyid()) || xlzxInfoList==null || xlzxInfoList.size()==0){
			return false;
		}
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update xg_xlzx_xlzxb set ");
		if(!StringUtil.isNull(model.getZxrq())){
			sql.append(" zxrq = ? ,");
			inputS.append(model.getZxrq()+",");
		}
		if(!StringUtil.isNull(model.getQssj())){
			sql.append(" qssj = ? ,");
			inputS.append(model.getQssj()+",");
		}
		if(!StringUtil.isNull(model.getJssj())){
			sql.append(" jssj = ? ,");
			inputS.append(model.getJssj()+",");
		}
		if(!StringUtil.isNull(model.getZxdz())){
			sql.append(" zxdz = ? ,");
			inputS.append(model.getZxdz()+",");
		}
		if(!StringUtil.isNull(model.getZxtell())){
			sql.append(" zxtell = ? ,");
			inputS.append(model.getZxtell()+",");
		}
		if(!StringUtil.isNull(model.getXspj())){
			sql.append(" xspj = ? ,");
			inputS.append(model.getXspj()+",");
			sql.append(" xspjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),");
		}

		if(!StringUtil.isNull(model.getZxstatus())){
			sql.append(" status = ? ,");
			inputS.append(model.getZxstatus()+",");
			
			// 未咨询状态,学生评价状态设为空
			if("3".equals(model.getZxstatus())){
				sql.append(" xspjzt = '' ,");
			}
		}
		
		if(!StringUtil.isNull(model.getXspjzt())){
			sql.append(" xspjzt = ? ,");
			inputS.append(model.getXspjzt()+",");
		}
		if(!StringUtil.isNull(model.getZxsfk())){
			sql.append(" zxsfk = ? ,");
			inputS.append(model.getZxsfk()+",");
			sql.append(" zxsfksj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),");
		}
		if(!StringUtil.isNull(model.getBz())){
			sql.append(" bz = ? ,");
			inputS.append(model.getBz()+",");
		}
		if(!StringUtil.isNull(model.getApzxs())){
			sql.append(" apzxs = ? ,");
			inputS.append(model.getApzxs()+",");
		}
		if(!StringUtil.isNull(model.getDatazt())){
			sql.append(" datazt = ? ,");
			inputS.append(model.getDatazt()+",");
		}
		if(!StringUtil.isNull(model.getXlcsjg())){
			sql.append(" xlcsjg = ? ,");
			inputS.append(model.getXlcsjg()+",");
		}
		if(!StringUtil.isNull(model.getYwyw())){
			sql.append(" ywyw = ? ,");
			inputS.append(model.getYwyw()+",");
		}
		if(!StringUtil.isNull(model.getZyzlls())){
			sql.append(" zyzlls = ? ,");
			inputS.append(model.getZyzlls()+",");
		}
		if(!StringUtil.isNull(model.getZxcs())){
			sql.append(" zxcs = ? ,");
			inputS.append(model.getZxcs()+",");
		}
		if(!StringUtil.isNull(model.getSfja())){
			sql.append(" sfja = ? ,");
			inputS.append(model.getSfja()+",");
		}
		if(!StringUtil.isNull(model.getSczxsj())){
			sql.append(" sczxsj = ? ,");
			inputS.append(model.getSczxsj()+",");
		}
		if(!StringUtil.isNull(model.getBczxnr())){
			sql.append(" bczxnr = ? ,");
			inputS.append(model.getBczxnr()+",");
		}
		if(!StringUtil.isNull(model.getBcjjwt())){
			sql.append(" bcjjwt = ? ,");
			inputS.append(model.getBcjjwt()+",");
		}
		if(!StringUtil.isNull(model.getZxgsfs())){
			sql.append(" zxgsfs = ? ,");
			inputS.append(model.getZxgsfs()+",");
		}
		if(!StringUtil.isNull(model.getZxfknr())){
			sql.append(" zxfknr = ? ,");
			inputS.append(model.getZxfknr()+",");
		}
		if(!StringUtil.isNull(model.getSjddm())){
			sql.append(" sjddm = ? ,");
			inputS.append(model.getSjddm()+",");
		}
		if(!StringUtil.isNull(model.getZxwtlxdm())){
			sql.append(" zxwtlxdm = ? ,");
			inputS.append(model.getZxwtlxdm()+",");
		}
		//什么也没修改，则return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//去掉最后一个","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where yyid = ? ";
		String inputValues = inputS.toString()+model.getYyid();
		String[] inputValue = inputValues.split(",");
		boolean flag = dao.runUpdate(sqls, inputValue);
		logger.debug("---更新心理咨询处理信息---："+sqls+"\n"+Arrays.toString(inputValue));
		return flag;
	}
	
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxyyclForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxyyclForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
		
		return null;
	}


	public Boolean saveJzxx(HashMap<String, String> jzxxMap) throws SQLException {
		String[] input = {jzxxMap.get("sqid"),jzxxMap.get("jzxm"),jzxxMap.get("jzxb"),jzxxMap.get("jzlxdh"),jzxxMap.get("gx"),
						jzxxMap.get("jzdzyx"),jzxxMap.get("jtjq"),jzxxMap.get("fqzy"),jzxxMap.get("fxl"),
						jzxxMap.get("mqzy"),jzxxMap.get("mxl"),jzxxMap.get("jtdz"),jzxxMap.get("xssfzx"),
						jzxxMap.get("fdysfzx"),jzxxMap.get("lfmd")};

		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_XLZX_JZXXB ");
		sql.append("(sqid,jzxm,jzxb,jzlxdh,gx,jzdzyx,jtjq,fqzy,fxl,mqzy,mxl,jtdz,xssfzx,fdysfzx,lfmd)");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		flag = dao.insert(sql.toString(), input);
		logger.debug("---插入家长信息表："+sql+"\n--"+Arrays.toString(input));
		return flag;
	}

	public boolean deleteJzxx(String[] ids) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_XLZX_JZXXB where sqid in( ");
		for(int i=0;i<ids.length-1;i++){
			sql.append("?,");
		}
		sql.append("?)");
		return dao.runUpdate(sql.toString(),ids);
	}
}

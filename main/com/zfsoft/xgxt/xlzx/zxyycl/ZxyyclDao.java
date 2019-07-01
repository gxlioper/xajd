/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-22 ����08:55:33 
 */  
package com.zfsoft.xgxt.xlzx.zxyycl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ��ѯԤԼ���� 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-22 ����08:55:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxyyclDao extends SuperDAOImpl<ZxyyclForm> {
	protected Log logger = LogFactory.getLog(ZxyyclDao.class);
	
	
	/**
	 * ����id��ѯ������ѯ������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXlzxInfoByYyId(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select t.id zxid,t.yyid,t.zxlx,t.zxrq,t.zxdz,t.zxtell,t.xspj,t.xspjzt,t.xspjsj,t.zxsfk,t.zxsfksj,t.status zxstatus,t.bz,t.datazt from xg_xlzx_xlzxb t where datazt='1' and yyid=? ");
		logger.debug("sql---"+sql.toString());
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * ������������ѯ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveXlzxInfo(ZxyyclForm model)
			throws Exception {
		String[] input = {model.getId(),model.getYyid(),model.getZxlx(),model.getZxrq(),model.getQssj(),model.getJssj(),model.getZxdz(),model.getZxtell(),model.getXspj(),model.getXspjsj(),model.getZxsfk(),model.getZxsfksj(),model.getZxstatus(),model.getBz(),model.getXspjzt(),Base.currXn,Base.currXq};
		
		boolean flag = false;
		String sql = " insert into xg_xlzx_xlzxb (id,yyid,zxlx,zxrq,qssj,jssj,zxdz,zxtell,xspj,xspjsj,zxsfk,zxsfksj,status,bz,xspjzt,xn,xq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		flag = dao.insert(sql, input);
		logger.debug("---����������ѯ��"+sql+"\n--"+Arrays.toString(input));
		return flag;
	}


	/**
	 * 
	 * @����:ɾ����ѯ��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-26 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delZxInfoByYyid(String[] id) throws Exception {
		if (id == null || id.length == 0){
			logger.error("ɾ����ѯ��Ϣ�������ܽ���!");
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
	 * @����:�޸�������ѯ��Ϣ��
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-9-16 ����08:55:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
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
			
			// δ��ѯ״̬,ѧ������״̬��Ϊ��
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
		if(!StringUtil.isNull(model.getDatazt())){
			sql.append(" datazt = ? ,");
			inputS.append(model.getDatazt()+",");
		}
		//ʲôҲû�޸ģ���return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//ȥ�����һ��","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where yyid = ? ";
		String inputValues = inputS.toString()+model.getYyid();
		String[] inputValue = inputValues.split(",");
		boolean flag = dao.runUpdate(sqls, inputValue);
		logger.debug("---����������ѯ������Ϣ---��"+sqls+"\n"+Arrays.toString(inputValue));
		return flag;
	}
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxyyclForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxyyclForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		
		
		return null;
	}

	

}

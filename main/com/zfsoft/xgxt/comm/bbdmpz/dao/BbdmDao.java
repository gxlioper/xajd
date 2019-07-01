/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-26 ����11:06:50 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-26 ����11:06:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbdmDao extends SuperDAOImpl<BbdmModel> {

	private static final String sp = "#";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbdmModel t)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbdmModel t, User user)
			throws Exception {
		
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ���Զ��幦��ģ��Ķ�Ӧ����ÿ������ȡ��һ��ͼƬ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-26 ����01:55:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>>  getBbdmList(String mkdm) throws SystemException{
		if(mkdm == null || StringUtils.isNull(mkdm)){
			throw new SystemException("����ģ�����Ϊ�գ�");
		}
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm  where a.mkdm = ? and b.dyym = '1' order by a.bbmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{mkdm});
		
	}
	
	/**
	 * 
	 * @����:����ID��ȡ������Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-26 ����02:18:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getBbdm(String guid) throws SystemException{
		if(guid == null || StringUtils.isNull(guid)){
			throw new RuntimeException("�������guidΪ�գ�");
		}
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm where a.guid = ? order by b.dyym");
		
		return dao.getListNotOut(sql.toString(), new String[]{guid});
	}

	/**
	 * 
	 * @����:��ȡģ��·����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-26 ����02:19:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getBbmbInfo(String guid) throws SystemException{
		
		if(guid == null || StringUtils.isNull(guid)){
			throw new RuntimeException("�������guidΪ�գ�");
		}
		
		String sql = "select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym " +
				" from XG_COMMON_BBDMB a left join XG_COMMON_BBDYTPB b on a.mkdm = b.mkdm " +
				"and a.bbdm = b.bbdm and b.dyym= '1' where a.guid = ? ";
		
		return dao.getMapNotOut(sql, new String[]{guid});
	}
	
	/**
	 * 
	 * ��ȡģ����ز���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����12:59:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getBbdmCs(String mkdm) throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		return dao.getMapNotOut(sql, new String[]{mkdm});
	}
	
	/**
	 * 
	 */
	public BbdmModel getModel(String mkdm) throws Exception{
		
		String sql = "select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc from XG_COMMON_BBDMB a where a.mkdm = ?";
		
		return getModel(new BbdmModel() , sql, new String[]{mkdm});
	}
	
	/**
	 * 
	 */
	public BbdmModel getModelByGuid(String guid) throws Exception{
		

		StringBuffer sql = new StringBuffer("");
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm  where a.guid = ? and b.dyym = '1' order by a.bbmc");
		
		return getModel(new BbdmModel() , sql.toString() ,  new String[]{guid});
	}
	
	/**
	 * 
	 * @����:��ȡ��ǰѡ�еı���ID
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����05:02:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @param pk
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getDybb(String mkdm , String pk)throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		
		String[] tbInfo = dao.getOneRs(sql, new String[]{mkdm}, new String[]{"TN" , "ZDM" , "PKZDM"});
		
		String pkzdm = tbInfo[2];
		
		if(tbInfo[0] == null || "".equals(tbInfo[0].trim())){
			throw new RuntimeException("��������:����Ϊ��");
		}
		
		if(tbInfo[1] == null || "".equals(tbInfo[1].trim())){
			throw new RuntimeException("��������:�ǼǱ��ֶ���Ϊ��");
		}
		
		if(pkzdm == null || "".equals(pkzdm.trim())){
			throw new RuntimeException("��������:������Ϊ��");
		}
		
		if(pk == null || "".equals(pk.trim())){
			throw new RuntimeException("��������:����ֵΪ��");
		}
		
		List<String> param = new ArrayList<String>();
		
		String query = "select " + tbInfo[1] + " from " + tbInfo[0] + " where ";
		
		String[] pks = pk.split(sp);
		
		StringBuffer tj = new StringBuffer(" ");
		
		for (int i = 0; i < pkzdm.split(sp).length; i++) {
			tj.append(pkzdm.split(sp)[i]).append(" = ").append(" ? ");
			if(i < pkzdm.split(sp).length - 1)
				tj.append(" and ");
			param.add(pks[i]);
		}
		
		String finalSql = query + tj.toString();
		
		logger.debug("�ǼǱ�����sql:::" + finalSql + " , ����:::" +  param);
		
		
		return dao.getOneRs(finalSql, param.toArray(new String[]{}), new String[]{tbInfo[1]})[0];
	}
	
	/**
	 * 
	 * @����:���ñ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����05:04:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @param pk
	 * @param guid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean setupDybb(String mkdm , String pk , String guid) throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		
		String[] tbInfo = dao.getOneRs(sql, new String[]{mkdm}, new String[]{"TN" , "ZDM" , "PKZDM"});
		
		String pkzdm = tbInfo[2];

		List<String> param = new ArrayList<String>();
		
		String update  = "update " + tbInfo[0] + " set " + tbInfo[1] + " = ? where ";
		
		param.add(guid);
		
		if(tbInfo[0] == null || "".equals(tbInfo[0].trim())){
			throw new RuntimeException("��������:����Ϊ��");
		}
		
		if(tbInfo[1] == null || "".equals(tbInfo[1].trim())){
			throw new RuntimeException("��������:�ǼǱ��ֶ���Ϊ��");
		}
		
		if(pkzdm == null ||pkzdm == ""){
			throw new RuntimeException("��������:������Ϊ��");
		}
		
		if(pk == null || pk == ""){
			throw new RuntimeException("��������:����ֵΪ��");
		}
		
		String[] pks = pk.split(sp);
		
		StringBuffer tj = new StringBuffer(" ");
		
		for (int i = 0; i < pkzdm.split(sp).length; i++) {
			tj.append(pkzdm.split(sp)[i]).append(" = ").append(" ? ");
			if(i < pkzdm.split(sp).length - 1)
				tj.append(" and ");
			param.add(pks[i]);
		}
		
		String finalSql = update + tj.toString();
		
		logger.debug("�ǼǱ�����sql:::" + finalSql + " , ����:::" +  param);
		
		return dao.runUpdate(finalSql, param.toArray(new String[]{}));
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}

}

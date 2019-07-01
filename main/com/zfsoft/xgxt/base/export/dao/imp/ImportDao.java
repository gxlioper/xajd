/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-27 ����09:50:20 
 */
package com.zfsoft.xgxt.base.export.dao.imp;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;

import com.zfsoft.xgxt.base.export.dao.IImportDao;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: <ת��mybaitΪjdbcʵ��>
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-27 ����09:50:20
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ImportDao implements IImportDao {
	private DAO dao = DAO.getInstance();

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.IImportDao#batchInsertData(java.util
	 * .HashMap)
	 */

	public int batchInsertData(HashMap<String, Object> hashMap) {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into ");
		sb.append(hashMap.get("tableName"));
		sb.append("(");
		List columnList = (List) hashMap.get("columnList");
		for (int i = 0; i < columnList.size(); i++) {
			ImportModel im = (ImportModel) columnList.get(i);
			sb.append(im.getZdm());
			if (i + 1 != columnList.size()) {
				sb.append(",");
			}
		}
		sb.append(")");
		List<List<ImportModel>> listM = (List<List<ImportModel>>) hashMap
				.get("dataList");
		int m=0;
		for (List<ImportModel> listim : listM) {
			sb.append(" select ");
			int j = 0;
			for (ImportModel im : listim) {
				sb.append(getFormatSingle(im.getValue(), im.getZdlx()));
				if (j + 1 != listim.size()) {
					sb.append(",");
				}
				j++;
			}
			sb.append(" from dual");
			if(m+1!=listM.size()){
				sb.append(" union all ");
			}
			m++;
		}
		int i = -1;
		try {
			Statement s = dao.getDb().getConnection().createStatement();
			i = s.executeUpdate(sb.toString());
		} catch (SQLException e) {
			throw new RuntimeException("�������!" + sb.toString(),e);
		}
		return i;
	}
	/**
	 * ���û����롾���Ի���
	 */
	public boolean batchInsertData_yhzgl(HashMap<String, Object> hashMap) {
		List<String> sql = new ArrayList<String>();
		List<List<ImportModel>> listM = (List<List<ImportModel>>) hashMap.get("dataList");
		int m=0;
		for (List<ImportModel> listim : listM) {
			String yhm = (String) listim.get(0).getValue();
			String zdm = (String) listim.get(1).getValue();
			sql.add("update yhb set qx = '1', zdm = zdm||'," + zdm + "' where yhm = '" + yhm + "' ");
			sql.add("delete yhqxb a where a.yhm = '" + yhm + "' and exists (select 1 from yhzqxb b where b.zdm = '" + zdm + "' and a.gnmkdm=b.gnmkdm)  ");
			sql.add("insert into yhqxb select '" + yhm + "' yhm,a.gnmkdm,a.dxq from yhzqxb a where zdm = '" + zdm + "' ");
		}
		boolean flag = true;
		try {
			int[] res = dao.runBatch(sql.toArray(new String[]{}));
			for (int i = 0; i < res.length; i++) {
				flag = (res[i] <0) ? false : true;
				if (!flag)
					break;
			}
		} catch (SQLException e) {
			throw new RuntimeException("���û��������!",e);
		}
		return flag;
	}

	/**
	 * 
	 * @����:��ֵ���з��Ÿ�ʽ�� </BR>��֧�֣�VARCHAR|��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-2 ����11:13:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @param zdlx
	 * @return String ��������
	 */
	private String getFormatSingle(Object value, String zdlx) {
		if (null == value) {
			return null;
		}
		String newV = value.toString();
		if ("VARCHAR2".equals(zdlx)) {
			newV = "'" + newV;
			newV += "'";
		}
		return newV;
	}

	/*
	 * ����: @seecom.zfsoft.xgxt.base.export.dao.IImportDao#
	 * getColumnValidateListByDrmkdmAndDrbm
	 * (com.zfsoft.xgxt.base.export.model.ImportModel)
	 */

	public List<ImportModel> getColumnValidateListByDrmkdmAndDrbm(
			ImportModel model) {
		StringBuffer sb = new StringBuffer();
		sb.append("	select t1.*, t2.yzmc, t2.yzlmc,t2.yzsm,t2.yzlbm");
		sb.append("	from zfxg_dr_drbmpzb t1");
		sb.append("	left join zfxg_dr_dryzb t2");
		sb.append("	on t1.dryzbh = t2.dryzbh");
		sb.append("	where t1.drmkdm = ?");
		sb.append("	and t1.drbm = ?");
		sb.append("	order by to_number(t1.xssx) asc");
		List<ImportModel> listN = new ArrayList<ImportModel>();
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] { model.getDrmkdm(), model.getDrbm() });
		for (HashMap<String, String> hm : list) {
			ImportModel im = new ImportModel();
			try {
				BeanUtils.copyProperties(im, hm);
				listN.add(im);
			} catch (Exception e) {
				throw new RuntimeException("ת��ΪImportModel�������");
			}
		}
		return listN;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.IImportDao#getImportColumnListByDrmkdmAndDrbm
	 * (com.zfsoft.xgxt.base.export.model.ImportModel)
	 */

	public List<ImportModel> getImportColumnListByDrmkdmAndDrbm(
			ImportModel model) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.* from (");
		sb
				.append("select t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc,min(to_number(t1.xssx)) xssx,count(1) hbdrs from (");
		sb
				.append("select t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc,t1.drmbzdmc,min(to_number(t1.xssx)) xssx");
		sb.append("	from zfxg_dr_drbmpzb t1");
		sb.append("	where t1.drmkdm = ?");
		sb.append("	and t1.drbm = ?");
		sb
				.append(" group by t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc,t1.drmbzdmc");
		sb
				.append("	) t1  group by t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc");
		sb.append("	) t1 order by to_number(t1.xssx) asc");
		List<ImportModel> listN = new ArrayList<ImportModel>();
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] { model.getDrmkdm(), model.getDrbm() });
		for (HashMap<String, String> hm : list) {
			ImportModel im = new ImportModel();
			try {
				BeanUtils.copyProperties(im, hm);
				listN.add(im);
			} catch (Exception e) {
				throw new RuntimeException("ת��ΪImportModel�������");
			}
		}
		return listN;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.IImportDao#getImportTableListByDrmkdm
	 * (java.lang.String)
	 */

	public List<ImportModel> getImportTableListByDrmkdm(String drmkdm) {
		List<ImportModel> listN = new ArrayList<ImportModel>();
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.* from (");
		sb
				.append(" select t1.drmkdm, t1.drbm, t1.drbzwmc,min(to_number(t1.xssx)) xssx");
		sb.append("	from zfxg_dr_drbmpzb t1");
		sb.append("	where t1.drmkdm = ?");
		sb.append("	group by t1.drmkdm, t1.drbm, t1.drbzwmc");
		sb.append("	) t1 order by to_number(t1.xssx) asc");
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] { drmkdm });
		for (HashMap<String, String> hm : list) {
			ImportModel im = new ImportModel();
			try {
				BeanUtils.copyProperties(im, hm);
				listN.add(im);
			} catch (Exception e) {
				throw new RuntimeException("ת��ΪImportModel�������");
			}
		}
		return listN;
	}

	/*
	 * ����: @seecom.zfsoft.xgxt.base.export.dao.IImportDao#
	 * getValidateColumnListByDrmkdmAndDrbm
	 * (com.zfsoft.xgxt.base.export.model.ImportModel)
	 */

	public List<ImportModel> getValidateColumnListByDrmkdmAndDrbm(
			ImportModel model) {
		List<ImportModel> listN = new ArrayList<ImportModel>();
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.* from (");
		sb
				.append(" select t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.drmbzdmc,t1.zdm,t1.zdmc,t1.zdlx,min(to_number(t1.xssx)) xssx");
		sb.append(" from zfxg_dr_drbmpzb t1");
		sb.append("	where t1.drmkdm = ?");
		sb.append("	and t1.drbm = ?");
		sb
				.append(" group by t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.drmbzdmc,t1.zdm,t1.zdmc,t1.zdlx");
		sb.append(" ) t1 order by to_number(t1.xssx) asc");
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] { model.getDrmkdm(), model.getDrbm() });
		for (HashMap<String, String> hm : list) {
			ImportModel im = new ImportModel();
			try {
				BeanUtils.copyProperties(im, hm);
				listN.add(im);
			} catch (Exception e) {
				throw new RuntimeException("ת��ΪImportModel�������");
			}
		}
		return listN;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.IImportDao#getValidateValueList(java
	 * .util.HashMap)
	 */

	public List<HashMap<String, String>> getValidateValueList(
			HashMap<String, String> parame) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(parame.get("columnName"));
		sb.append(" from ");
		sb.append(parame.get("tableName"));
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] {});
		return list;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.IImportDao#getValidateValueMap(java
	 * .util.HashMap)
	 */

	public List<HashMap<String, String>> getValidateValueMap(
			HashMap<String, String> parame) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(parame.get("columnName"));
		sb.append(" COLUMNNAME from ");
		sb.append(parame.get("tableName"));
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] {});
		return list;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#batchDelete(java.util.Map)
	 */

	public int batchDelete(Map<String, Object> map) {
		// TODO �Զ����ɷ������
		return 0;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#batchDelete(java.util.List)
	 */

	public int batchDelete(List list) {
		// TODO �Զ����ɷ������
		return 0;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#batchUpdate(java.util.Map)
	 */

	@SuppressWarnings("unchecked")
	public int batchUpdate(Map<String, Object> map) {
		int result = 0;
		try {
			List<List<ImportModel>> dataList = (List<List<ImportModel>>)map.get("dataList");
			String tableName = (String)map.get("tableName");
			List<ImportModel> columnList= (List<ImportModel>)map.get("columnList");
			List<String> uniqueZdmList = (List<String>)map.get("uniqueZdmList");
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("update ");
			sb.append(tableName);
			sb.append(" set ");
			String zdm = null;
			for (int i = 0; i < columnList.size(); i++) {
				ImportModel importModel = columnList.get(i);
				zdm = importModel.getZdm();
				if(i > 0){
					sb.append(",");
				}
				sb.append(zdm);
				sb.append("=?");
			}
			
			sb.append(" where ");

			for (int i = 0; i < uniqueZdmList.size(); i++) {
				String uniqueZdm = uniqueZdmList.get(i);
				if(i > 0){
					sb.append(" and ");
				}
				sb.append(uniqueZdm);
				sb.append("=?");			
			}
			
			List<String[]> paramsList = new ArrayList<String[]>();
			String[] params = null;
			
			for (List<ImportModel> importModelList : dataList) {
				params = new String[columnList.size() + uniqueZdmList.size()];
				ImportModel importModel = null;
				String value = null;
				for (int i = 0; i < importModelList.size(); i++) {
					importModel = importModelList.get(i);
					value = importModel.getValue() + "";
					params[i] = value;
				}
				for (int i = 0; i < uniqueZdmList.size(); i++) {
					String uniqueZdm = uniqueZdmList.get(i);
					for (int j = 0; j < importModelList.size(); j++) {
						importModel = importModelList.get(j);
						if(uniqueZdm.equals(importModel.getZdm())){
							value = importModel.getValue() + "";
							params[importModelList.size() + i] = value;
						}
					}
				}
				paramsList.add(params);
			}

			dao.runBatch(sb.toString(), paramsList);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/** 
	 * @����:���Ž��趨�ơ����¡����ӡ�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-1 ����06:33:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dataList
	 * @param tableName
	 * @param columnList
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int updateImportData_dtjs(List<List<ImportModel>> dataList,
			String tableName, List<ImportModel> columnList){
		int result = 0;
		try {
			List<String> uniqueZdmList = new ArrayList<String>();
			// ������
			columnList.clear();
			// ����ֶ�
			ImportModel model1 = new ImportModel();
			model1.setZdm("XH");
			uniqueZdmList.add("XH");
			columnList.add(model1);
			ImportModel model2 = new ImportModel();
			model2.setZdm("kssj");	
			columnList.add(model2);
			ImportModel model3 = new ImportModel();
			model3.setZdm("jddm");
			uniqueZdmList.add("jddm");
			columnList.add(model3);
			// ����
			StringBuffer sb = new StringBuffer();
			sb.append("update ");
			sb.append(tableName);
			sb.append(" set ");
			String zdm = null;
			for (int i = 0; i < columnList.size(); i++) {
				ImportModel importModel = columnList.get(i);
				zdm = importModel.getZdm();
				if(i > 0){
					sb.append(",");
				}
				sb.append(zdm);
				sb.append("=?");
			}
			
			sb.append(" where ");

			for (int i = 0; i < uniqueZdmList.size(); i++) {
				String uniqueZdm = uniqueZdmList.get(i);
				if(i > 0){
					sb.append(" and ");
				}
				sb.append(uniqueZdm);
				sb.append("=?");			
			}
			
			List<String[]> paramsList = new ArrayList<String[]>();
			String[] params = null;
			
			for (List<ImportModel> importModelList : dataList) {
				params = new String[columnList.size() + uniqueZdmList.size()];
				ImportModel importModel = null;
				String value = null;
				for (int i = 0; i < importModelList.size(); i++) {
					importModel = importModelList.get(i);
					value = importModel.getValue() + "";
					params[i] = value;
				}
				for (int i = 0; i < uniqueZdmList.size(); i++) {
					String uniqueZdm = uniqueZdmList.get(i);
					for (int j = 0; j < importModelList.size(); j++) {
						importModel = importModelList.get(j);
						if(uniqueZdm.equals(importModel.getZdm())){
							value = importModel.getValue() + "";
							params[importModelList.size() + i] = value;
						}
					}
				}
				paramsList.add(params);
			}

			dao.runBatch(sb.toString(), paramsList);
			
			// ����
			sb = new StringBuffer();
			sb.append(" insert into ");
			sb.append(tableName);
			sb.append("(");
			for (int i = 0; i < columnList.size(); i++) {
				ImportModel im = (ImportModel) columnList.get(i);
				sb.append(im.getZdm());
				if (i + 1 != columnList.size()) {
					sb.append(",");
				}
			}
			sb.append(")");
			sb.append(" select b.* from ( ");
			List<List<ImportModel>> listM = dataList;
			int m=0;
			for (List<ImportModel> listim : listM) {
				sb.append(" select ");
				int j = 0;
				for (ImportModel im : listim) {
					sb.append(getFormatSingle(im.getValue(), im.getZdlx())).append(" " + im.getZdm());
					if (j + 1 != listim.size()) {
						sb.append(",");
					}
					j++;
				}
				sb.append(" from dual");
				if(m+1!=listM.size()){
					sb.append(" union all ");
				}
				m++;
			}
			sb.append(" ) b where not exists (select 1 from XG_DTJS_XSDTXXJLB a where a.xh=b.xh and a.jddm=b.jddm ) and b.jddm is not null ");
			int i = -1;
			try {
				Statement s = dao.getDb().getConnection().createStatement();
				i = s.executeUpdate(sb.toString());
			} catch (SQLException e) {
				throw new RuntimeException("�������!" + sb.toString(),e);
			}
			
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getCount(java.lang.Object)
	 */

	public int getCount(ImportModel t) {
		// TODO �Զ����ɷ������
		return 0;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getModel(java.lang.String)
	 */

	public ImportModel getModel(String id) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getModel(java.lang.Object)
	 */

	public ImportModel getModel(ImportModel t) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getModelList(java.lang.Object)
	 */

	public List<ImportModel> getModelList(ImportModel t) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getModelList(java.lang.String[])
	 */

	public List<ImportModel> getModelList(String... str) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getModelListByScope(java.
	 * lang.Object)
	 */

	public List<ImportModel> getModelListByScope(ImportModel t) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getPagedByScope(java.lang.Object)
	 */

	public List<ImportModel> getPagedByScope(ImportModel t) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.export.dao.BaseDao#getPagedList(java.lang.Object)
	 */

	public List<ImportModel> getPagedList(ImportModel t) {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.export.dao.BaseDao#insert(java.lang.Object)
	 */

	public int insert(ImportModel t) {
		// TODO �Զ����ɷ������
		return 0;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.export.dao.BaseDao#update(java.lang.Object)
	 */

	public int update(ImportModel t) {
		// TODO �Զ����ɷ������

		return 0;
	}
	/*
	 * <mapper namespace="com.zfsoft.dao.daointerface.IImportDao" > <!--
	 * ��ѯ����ģ���б���� --> <select id="getImportTableListByDrmkdm"
	 * parameterType="string" resultType="com.zfsoft.dao.entities.ImportModel">
	 * select t1.* from ( select t1.drmkdm, t1.drbm,
	 * t1.drbzwmc,min(to_number(t1.xssx)) xssx from zfxg_dr_drbmpzb t1 where
	 * t1.drmkdm = #{drmkdm} group by t1.drmkdm, t1.drbm, t1.drbzwmc ) t1 order
	 * by to_number(t1.xssx) asc </select>
	 * 
	 * <!-- ��ѯ��֤���ֶ� �б�--> <select id="getValidateColumnListByDrmkdmAndDrbm"
	 * parameterType="com.zfsoft.dao.entities.ImportModel"
	 * resultType="com.zfsoft.dao.entities.ImportModel"> select t1.* from (
	 * select t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc,
	 * t1.drmbzdmc,t1.zdm,t1.zdmc,t1.zdlx,min(to_number(t1.xssx)) xssx from
	 * zfxg_dr_drbmpzb t1 where t1.drmkdm = #{drmkdm} and t1.drbm = #{drbm}
	 * group by t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc,
	 * t1.drmbzdmc,t1.zdm,t1.zdmc,t1.zdlx ) t1 order by to_number(t1.xssx) asc
	 * </select>
	 * 
	 * <!-- ��ѯ��ȡ����֤�б�--> <select id="getColumnValidateListByDrmkdmAndDrbm"
	 * parameterType="com.zfsoft.dao.entities.ImportModel"
	 * resultType="com.zfsoft.dao.entities.ImportModel"> select t1.*, t2.yzmc,
	 * t2.yzlmc,t2.yzsm,t2.yzlbm from zfxg_dr_drbmpzb t1 left join zfxg_dr_dryzb
	 * t2 on t1.dryzbh = t2.dryzbh where t1.drmkdm = #{drmkdm} and t1.drbm =
	 * #{drbm} order by to_number(t1.xssx) asc </select>
	 * 
	 * <!-- ��ѯ��ȡ����֤�б� <select id="getColumnListByDrmkdmAndDrbm"
	 * parameterType="com.zfsoft.dao.entities.ImportModel"
	 * resultType="com.zfsoft.dao.entities.ImportModel"> select t1.* from
	 * (select t1.drmkdm, t1.drbm, t1.zdm, t1.drmkmc, t1.zdmc, t1.drbzwmc,
	 * min(t1.xssx) xssx from (select t1.*, t2.yzmc, t2.yzlmc,t2.yzsm from
	 * zfxg_dr_drbmpzb t1 left join zfxg_dr_dryzb t2 on t1.dryzbh = t2.dryzbh
	 * where t1.drmkdm = #{drmkdm} and t1.drbm = #{drbm}) t1 group by t1.drmkdm,
	 * t1.drbm, t1.zdm, t1.drmkmc, t1.zdmc, t1.drbzwmc) t1 order by
	 * to_number(t1.xssx) asc </select>-->
	 * 
	 * <!-- ��������ҵ������ --> <insert id="batchInsertData" parameterType="hashMap">
	 * insert into ${tableName}( <foreach collection="columnList"
	 * item="itemColumn" index="index" open="" separator=" , " close=" " >
	 * ${itemColumn.zdm} </foreach> ) <foreach collection="dataList"
	 * item="itemData" index="index" separator=" from dual union all select"
	 * open="select" close=" from dual" > <foreach collection="itemData"
	 * item="data" index="index" open="" separator=" , " close=" " > <if
	 * test="data.zdlx == 'VARCHAR2'"> #{data.value,jdbcType=VARCHAR} </if> <if
	 * test="data.zdlx == 'VARCHAR'"> #{data.value,jdbcType=VARCHAR} </if> <if
	 * test="data.zdlx == 'DATE'"> #{data.value,jdbcType=DATE} </if> <if
	 * test="data.zdlx == 'NUMBER'"> <!-- jdbcTypeû��NUMBER�� -->
	 * #{data.value,jdbcType=VARCHAR} </if> <!-- �������ʹ˴�������չ --> </foreach>
	 * </foreach> </insert>
	 * 
	 * <!-- ��ѯ��ȡ����֤�б�--> <select id="getValidateValueList"
	 * parameterType="hashMap" resultType="hashMap"> select ${columnName} from
	 * ${tableName} </select>
	 * 
	 * <!-- ��ѯ��ȡ����֤����--> <select id="getValidateValueMap"
	 * parameterType="hashMap" resultType="hashMap"> select ${columnName}
	 * COLUMNNAME from ${tableName} </select>
	 * 
	 * <!-- ��ѯ�����ֶ� �б�--> <select id="getImportColumnListByDrmkdmAndDrbm"
	 * parameterType="com.zfsoft.dao.entities.ImportModel"
	 * resultType="com.zfsoft.dao.entities.ImportModel"> select t1.* from (
	 * select t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm,
	 * t1.zdmc,min(to_number(t1.xssx)) xssx,count(1) hbdrs from ( select
	 * t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm,
	 * t1.zdmc,t1.drmbzdmc,min(to_number(t1.xssx)) xssx from zfxg_dr_drbmpzb t1
	 * where t1.drmkdm = #{drmkdm} and t1.drbm = #{drbm} group by t1.drmkdm,
	 * t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc,t1.drmbzdmc
	 * 
	 * ) t1 group by t1.drmkdm, t1.drmkmc, t1.drbm, t1.drbzwmc, t1.zdm, t1.zdmc
	 * ) t1 order by to_number(t1.xssx) asc </select>
	 * 
	 * </mapper>
	 */
	
	

	/**
	 * ��ȡ���ֶμ��ֶγ��ȵ�����
	 * 
	 * @param parame
	 * @return
	 */
	public List<HashMap<String, String>> getTableColumns(String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append("select COLUMN_NAME,DATA_TYPE,DATA_LENGTH from USER_TAB_COLUMNS  ");
		sb.append(" where TABLE_NAME=? ");
		String[] input = {tableName.toUpperCase()};
		return dao.getListNotOut(sb.toString(), input);
	}
}

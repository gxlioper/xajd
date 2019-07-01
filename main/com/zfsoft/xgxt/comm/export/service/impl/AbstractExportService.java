package com.zfsoft.xgxt.comm.export.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.export.dao.ExportDao;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import common.newp.StringUtil;

/**
 * ���õ���ʵ��
 * 
 * @author Penghui.Qu
 * 
 */
public abstract class AbstractExportService extends
		SuperServiceImpl<ExportModel, ExportDao> implements IExportService {

	private ExportDao exportDao = new ExportDao();
	protected static final transient Logger log = Logger
			.getLogger(AbstractExportService.class);

	/**
	 * ���������ļ�
	 * 
	 * @throws Exception
	 */
	public File getExportFile(ExportModel model) throws Exception {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zfsoft.service.svcinterface.IExportService#getConfigList(com.zfsoft
	 * .dao.entities.ExportModel)
	 */
	public List<HashMap<String, String>> getConfigList(ExportModel model) {

		if (null == model || StringUtil.isNull(model.getDcclbh())
				|| StringUtil.isNull(model.getZgh())) {
			throw new SystemException("Nonlicet params !");
		}

		List<HashMap<String, String>> configList = null;

		String[] selectZd = model.getSelectCol();
		if (null != selectZd && selectZd.length > 0) {

			configList = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> map = null;
			for (int i = 0; i < selectZd.length; i++) {
				map = new HashMap<String, String>();
				map.put("zd", selectZd[i].split("@")[0]);
				map.put("zdmc", selectZd[i].split("@")[1]);
				map.put("zt", SELECT_ZT);
				configList.add(map);
			}

		} else {
			configList = exportDao.getExportConfig(model);
			// �������û�����ȡ���ĵ�������Ϊ�����ȡ��������
			if (null == configList || configList.size() == 0) {
				log.error("Export blank info:ID(" + model.getDcclbh()
						+ "),User(" + model.getZgh() + ")");

				// �������û�����ȡ���ĵ�������Ϊ�����ȡ��������
				model.setZgh(PUBLIC_CONFIG);
				configList = exportDao.getExportConfig(model);
			}
		}
		return configList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zfsoft.export.service.svcinterface.IExportService#saveExportConfig
	 * (com.zfsoft.dao.entities.ExportModel)
	 */
	public boolean saveExportConfig(ExportModel model) throws Exception {

		if (null == model || StringUtil.isNull(model.getDcclbh())
				|| StringUtil.isNull(model.getZgh())) {
			throw new SystemException("Nonlicet params !");
		}

		String selectZd = model.getSelectZd();
		String unselectZd = model.getUnselectZd();
		List<HashMap<String, String>> configList = new ArrayList<HashMap<String, String>>();

		// ѡ���ֶ����ݴ���
		if (!StringUtil.isNull(selectZd)) {

			String[] selectCol = selectZd.split(",");
			for (int i = 0; i < selectCol.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zt", SELECT_ZT);
				map.put("zgh", model.getZgh());
				map.put("dcclbh", model.getDcclbh());
				map.put("zd", selectCol[i].split("@")[0]);
				map.put("zdmc", selectCol[i].split("@")[1]);
				map.put("sfmrzd", "1");
				map.put("xssx", String.valueOf(i));
				configList.add(map);
			}
		}

		// δѡ���ֶ����ݴ���
		if (!StringUtil.isNull(unselectZd)) {
			String[] unselectCol = unselectZd.split(",");

			for (int i = 0; i < unselectCol.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zt", UNSELECT_ZT);
				map.put("zgh", model.getZgh());
				map.put("dcclbh", model.getDcclbh());
				map.put("zd", unselectCol[i].split("@")[0]);
				map.put("zdmc", unselectCol[i].split("@")[1]);
				map.put("sfmrzd", "1");
				map.put("xssx", String.valueOf(i));
				configList.add(map);
			}
		}

		List<HashMap<String, String>> defaultConfigList = exportDao
				.getExportConfig(model);

		// ���ڸ��û���Ӧ����ִ�и��£������������
		if (null != defaultConfigList && defaultConfigList.size() > 0) {
			exportDao.deleteConfig(model);
		}

		return exportDao.insertConfig(configList);
	}

}

package com.zfsoft.xgxt.base.export.service;

import java.io.File;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ExportConfigModel;
import com.zfsoft.xgxt.base.export.model.ExportModel;

/**
 * ���õ����ӿ�
 * 
 * @author Penghui.Qu
 * 
 */
public interface IExportService {

	static final String SHEET_NAME = "sheet1";
	static final String PUBLIC_CONFIG = "public";
	static final String SELECT_ZT = "1";// ����ѡ��״̬
	static final String UNSELECT_ZT = "0";// ����δѡ��״̬
	static final String TEMP_PATH_KEY = "export_temp_path";

	/**
	 * ��ȡ�����ļ�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public File getExportFile(ExportModel model) throws Exception;

	/**
	 * ��ѯ��������
	 * 
	 * @param model
	 * @return
	 */
	public List<ExportConfigModel> getConfigList(ExportModel model);

	/**
	 * ���浼������
	 * 
	 * @param model
	 * @return boolean
	 */
	public boolean saveExportConfig(ExportModel model);

}

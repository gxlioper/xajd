package com.zfsoft.xgxt.comm.export.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.comm.export.model.ExportModel;



/**
 * ���õ����ӿ�
 * @author Penghui.Qu
 *
 */
public interface IExportService {

	static final String SHEET_NAME = "sheet1";
	static final String PUBLIC_CONFIG = "public";
	static final String SELECT_ZT = "1";//����ѡ��״̬
	static final String UNSELECT_ZT = "0";//����δѡ��״̬
	static final String TEMP_PATH_KEY= "export_temp_path";
	
	/**
	 * ��ȡ�����ļ�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public File getExportFile(ExportModel model) throws Exception;

	
	
	
	/**
	 * 
	 * @����: ��ȡ�����ļ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-6 ����08:40:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param title 
	 * @param dataList
	 * @return
	 * File ��������
	 */
	public File getExportFile(Map<String,String> title ,List<HashMap<String,String>> dataList) throws Exception;
	
	
	
	
	
	/**
	 * ��ѯ��������
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getConfigList(ExportModel model);
	
	
	/**
	 * ���浼������
	 * @param model
	 * @return boolean
	 */
	public boolean saveExportConfig(ExportModel model) throws Exception ;
	
	public File getExportExcelFile(ExportModel model,QueryDataService queryDataService) throws Exception ;

	
}

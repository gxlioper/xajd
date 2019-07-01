package com.zfsoft.xgxt.base.export.dao;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ExportConfigModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * ���õ���
 * 
 * @author Penghui.Qu
 * 
 */
public interface IExportDao {

	/**
	 * ��ѯ��������
	 * 
	 * @param model
	 * @return
	 */
	public List<ExportConfigModel> getExportConfig(ExportModel model);

	/**
	 * ���뵼������
	 * 
	 * @param model
	 * @return
	 */
	public int insertConfig(List<HashMap<String, String>> list);

	/**
	 * ɾ���û���������
	 * 
	 * @param model
	 * @return
	 */
	public int deleteConfig(ExportModel model);
}

package xgxt.sjdrdc.check;

import java.util.List;

import xgxt.sjdrdc.ExportAndImportModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݵ�����ӿ�
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-04-06</p>
 */
public interface IimportCheck {

	/**
	 * ���excel�������ݣ��µĵ������඼����ʵ�ָýӿ� 
	 * @param filePath
	 * @param realTable
	 * @param mappingItems
	 * @return
	 */
	public List<String[]> checkExcelData(ExportAndImportModel model);
}

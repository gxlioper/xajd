/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-8 ����04:43:36 
 */
package com.zfsoft.xgxt.base.export.business.imp.dtjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-1-8 ����04:43:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtjsRule extends BusinessExtend {
	private final static String _YWKZ_JEYZ_KEY = "YWKZ_DTJS";

	private static DAO dao = DAO.getInstance();
	
	private static String SQL = "select * from XG_DTJS_JBSZB";
	
	private static HashMap<String , String> DMMCB = new HashMap<String, String>();
	
	public DtjsRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
		/*List<HashMap<String , String>> DMMCBS = dao.getListNotOut(SQL, new String[]{});
		for (HashMap<String, String> hashMap : DMMCBS) {
			String mc = hashMap.get("jdmc");
			String dm = hashMap.get("jddm");
			DMMCB.put(mc, dm);
		}*/
		if("13022".equals(Base.xxdm)){
			DMMCB.put("��������", "RTSQ");
			DMMCB.put("��Ա", "TY");
			DMMCB.put("�뵳����", "RDSQ");
			DMMCB.put("�뵳������������", "TTY");
			DMMCB.put("�뵳��������", "RDJJFZ");
			DMMCB.put("�뵳�������ӵ�У��ҵ", "DXJYSJ");
			DMMCB.put("��չ��������", "FZDXTY");
			DMMCB.put("��չ����", "FZDX");
			DMMCB.put("Ԥ����Ա", "YBDY");
			DMMCB.put("Ԥ����Ա��У��ҵ", "YBDYDXJY");
			DMMCB.put("��ʽ��Ա", "ZSDY");
			
			DMMCB.put("RTSQ", "01");
			DMMCB.put("TY", "02");
			DMMCB.put("RDSQ", "03");
			DMMCB.put("TTY", "04");
			DMMCB.put("RDJJFZ", "05");
			DMMCB.put("DXJYSJ", "06");
			DMMCB.put("FZDXTY", "07");
			DMMCB.put("FZDX", "08");
			DMMCB.put("YBDY", "09");
			DMMCB.put("YBDYDXJY", "10");
			DMMCB.put("ZSDY", "11");
		}else{
			DMMCB.put("��������", "RTSQ");
			DMMCB.put("��Ա", "TY");
			DMMCB.put("�뵳����", "RDSQ");
			DMMCB.put("�뵳��������", "RDJJFZ");
			DMMCB.put("����", "TTY");
			DMMCB.put("��չ����", "FZDX");
			DMMCB.put("��У��ҵʱ��", "DXJYSJ");
			DMMCB.put("Ԥ����Ա", "YBDY");
			DMMCB.put("��ʽ��Ա", "ZSDY");
			
			
			DMMCB.put("RTSQ", "01");
			DMMCB.put("TY", "02");
			DMMCB.put("RDSQ", "03");
			DMMCB.put("RDJJFZ", "04");
			DMMCB.put("TTY", "05");
			DMMCB.put("FZDX", "06");
			DMMCB.put("DXJYSJ", "07");
			DMMCB.put("YBDY", "08");
			DMMCB.put("ZSDY", "09");
		}
	
	}

	@Override
	protected String check(String message, List<ImportModel> data) {
		return this._YZTG;
	}

	@Override
	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		List<List<ImportModel>> formateSuccess = new ArrayList<List<ImportModel>>();
		
		for (List<ImportModel> list : success) {
			
			ImportModel xhModel = list.get(0);
			int j=0;
			if("13022".equals(Base.xxdm)){
				j=11;
			}
			else{
				j=9;
			}
			for (int i = 1; i <= j; i++) {
				List<ImportModel> importModels = new ArrayList<ImportModel>();
				importModels.add(xhModel); //ѧ��Model
				ImportModel sjModel = list.get(i);

				if(sjModel.getValue() == null || StringUtils.isNull(sjModel.getValue().toString())){
					continue;
				}
				
				importModels.add(sjModel);//�׶�ʱ��
				ImportModel jdModel = new ImportModel();
				jdModel.setValue(DMMCB.get(list.get(i).getZdm()));
				jdModel.setZdlx("VARCHAR2");
				jdModel.setZdm("jddm");
				importModels.add(jdModel);//�׶δ���
				formateSuccess.add(importModels);
			}
		}
		
		return super.formartData(formateSuccess, succeedExcelDataList, error);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.export.business.BusinessExtend#businessInsertData(com.zfsoft.xgxt.base.export.model.ImportModel, java.util.List)
	 */
	
	@Override
	public List<ImportModel> businessInsertData(ImportModel model,
			List<ImportModel> importColumnList) {
		List<ImportModel> columnList = new ArrayList<ImportModel>();
		columnList.add(importColumnList.get(0));
		ImportModel model1 = importColumnList.get(1);
		ImportModel model2 = importColumnList.get(2);
		model1.setZdm("kssj");
		model2.setZdm("jddm");
		columnList.add(model1);
		columnList.add(model2);
		importColumnList = columnList;
		return importColumnList;
	}
	
}

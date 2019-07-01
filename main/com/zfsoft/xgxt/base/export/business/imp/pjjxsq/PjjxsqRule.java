package com.zfsoft.xgxt.base.export.business.imp.pjjxsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * 奖项申请导入个性化
 */
public class PjjxsqRule extends BusinessExtend {
	
	private final static String _YWKZ_JEYZ_KEY = "YWKZ_PJJXSQ";

	public PjjxsqRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		return this._YZTG;
	}
	
	@Override
	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList, List<String[]> error) {
		List<List<ImportModel>> formateSuccess = new ArrayList<List<ImportModel>>();
		try {
			String currXn = CsszService.getPjzq().get("xn"); // 当前学年
			String currXq = CsszService.getPjzq().get("xq"); // 当年学期
			for (List<ImportModel> list : success) {
				List<ImportModel> importModels = new ArrayList<ImportModel>();
				// 学号
				ImportModel xhModel = list.get(0);
				// 奖项代码
				ImportModel xmdmModel = list.get(1);
				// 审批流程
				String splc = getSplc(String.valueOf(xmdmModel.getValue()));
				// 申请理由
				ImportModel sqlyModel = list.get(2);
				
				importModels.add(xhModel);
				importModels.add(xmdmModel);
				importModels.add(sqlyModel);
				// ========= 个性化字段（需要跟businessInsertData里的顺序一致） begin =============
				// 当前学年
				ImportModel xnModel = new ImportModel();
				xnModel.setValue(currXn);
				xnModel.setZdlx("VARCHAR2");
				xnModel.setZdm("xn");
				importModels.add(xnModel);
				// 当年学期
				ImportModel xqModel = new ImportModel();
				xqModel.setValue(currXq);
				xqModel.setZdlx("VARCHAR2");
				xqModel.setZdm("xq");
				importModels.add(xqModel);
				// 申请人
				ImportModel sqrModel = new ImportModel();
				sqrModel.setValue(xhModel.getValue());
				sqrModel.setZdlx("VARCHAR2");
				sqrModel.setZdm("sqr");
				importModels.add(sqrModel);
				// 审批流程
				ImportModel splcModel = new ImportModel();
				splcModel.setValue(splc);
				splcModel.setZdlx("VARCHAR2");
				splcModel.setZdm("splc");
				importModels.add(splcModel);
				// 当前项目代码
				ImportModel dqxmdmModel = new ImportModel();
				dqxmdmModel.setValue(xmdmModel.getValue());
				dqxmdmModel.setZdlx("VARCHAR2");
				dqxmdmModel.setZdm("dqxmdm");
				importModels.add(dqxmdmModel);
				// ========= 个性化字段（需要跟businessInsertData里的顺序一致） end =============
				formateSuccess.add(importModels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.formartData(formateSuccess, succeedExcelDataList, error);
	}
	
	@Override
	public List<ImportModel> businessInsertData(ImportModel model, List<ImportModel> importColumnList) {
		// ========= 个性化字段（需要跟formartData里的顺序一致） begin =============
		// 当前学年
		ImportModel xnModel = new ImportModel();
		xnModel.setZdm("xn");
		importColumnList.add(xnModel);
		// 当年学期
		ImportModel xqModel = new ImportModel();
		xqModel.setZdm("xq");
		importColumnList.add(xqModel);
		// 申请人
		ImportModel sqrModel = new ImportModel();
		sqrModel.setZdm("sqr");
		importColumnList.add(sqrModel);
		// 审批流程
		ImportModel splcModel = new ImportModel();
		splcModel.setZdm("splc");
		importColumnList.add(splcModel);
		// 当前项目代码
		ImportModel dqxmdmModel = new ImportModel();
		dqxmdmModel.setZdm("dqxmdm");
		importColumnList.add(dqxmdmModel);
		// ========= 个性化字段（需要跟formartData里的顺序一致） end =============
		return importColumnList;
	}
	
	private String getSplc(String xmdm){
		String sql = " select shlc splc from xg_pjpy_new_pjxmb where xmdm=? ";
		return DAO.getInstance().getOneRs(sql, new String[]{ xmdm }, "splc");
	}
}

package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class PjpyQtxxInit extends BasicService{
	
	/**
	 * 初始化结果查询信息
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initQtxxManage(RequestForm rForm,
			HttpServletRequest request) {
		
		DAO dao=DAO.getInstance();
		// 功能模块
		String gnmk = "pjpy";
		// 访问路径
		String path ="pjpy_qtxx_qtjl.do";
		
		// ========================输出字段 begin=========================	
		// 字段名
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","hjjls"};
		// 中文名
		String[]cn=new String[]{"","学年","学号","姓名","年级","班级","获奖数"};
		// 表头
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================输出字段 end=========================

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ===============通用设置 end ============
		
		rForm.setQtzdz(new String[]{});//加载其他数据

		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * 保存信息初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSaveBatch(BasicModel basicModel,
			QtxxSaveModel saveModel,
			HttpServletRequest request) {
		
		// 主键字段
		String []save={"xn","xh","jlnr"};
		// 主键字段
		String []pk={"xn","xh"};
		// 单个保存字段
		String []oneZd={"xn","xh"};
		// 批量保存字段
		String []arrayZd={"jlnr"};
		
		basicModel.setPk(pk);
	
		getModelValue(saveModel, request);
		
		basicModel.setPkValue(saveModel.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------表名------------
		basicModel.setTableName("xg_pjpy_qtjlb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
}

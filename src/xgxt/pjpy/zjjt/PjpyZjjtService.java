package xgxt.pjpy.zjjt;

import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jaxen.function.RoundFunction;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.xfjs.PjpyXfjsService;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.wjcf.zjcm.WjcfZjcmDAO;
import xgxt.wjcf.zjcm.WjcfZjcmModel;

public class PjpyZjjtService extends PjpyTyService {

	PjpyZjjtDAO dao = new PjpyZjjtDAO();
	
	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String[] colListCN = null;
		String[] colListEN = null;

		//操行分统计
		if ("cxftj".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学号", "姓名", "性别", "学年", "学期", "学院",
					"专业", "班级", "校区", "楼栋", "层数", "寝室号","加分项目数","加分合计","减分项目数","减分合计","处理总分","默认分","操行分" };

			colListEN = new String[] { "xh", "xm", "xb", "xn", "xqm", "xymc",
					"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh","jfsm","jffs","kfsm","kffs","clzf","mrf","cxf" };
		} else if("cxftjxn".equalsIgnoreCase(lx)){
			colListCN = new String[] { "学号", "姓名", "性别", "学年", "学院",
					"专业", "班级", "校区", "楼栋", "层数", "寝室号","加分项目数","加分合计","减分项目数","减分合计","处理总分","默认分","操行分" };

			colListEN = new String[] { "xh", "xm", "xb", "xn", "xymc",
					"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh","jfsm","jffs","kfsm","kffs","clzf","mrf","cxf" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 保存操行分
	 * 
	 * @author luojw
	 */
	public Boolean saveCxf(PjpyZjjtModel model, String realTable)
			throws Exception {

		String[] arrzd = new String[] { "cxbz", "fz", "jb1", "jb2", "jb3", "jjf", "pjxh", "rq" };
		String[] onezd = new String[] { "xn", "xq" };
		String pk = "pjxh||xn||xq";
		
		// 构建主键
		String[] jjf = model.getJjf();
		String[] pjxh = null;
		String[] pkValue = null;
		
		if (jjf != null && jjf.length > 0) {
			pjxh = new String[jjf.length];
			pkValue = new String[jjf.length];
			for (int i = 0; i < jjf.length; i++) {
				pjxh[i] = model.getXh();
				pkValue[i] = model.getXh() + model.getXn() + model.getXq();
			}
			model.setPjxh(pjxh);
		}
			
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		//执行操作保存数据
		boolean result = savePjpy(saveForm, model);
		
		return result;
	}
	
	/**
	 * 获得操行分统计列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getCxfTjList(PjpyTyForm model)
			throws Exception {
		
		// 统计数据
		List<HashMap<String, String>> tjList = dao.getCxfTjList(model);
		// 学号列表
		List<HashMap<String, String>> xhList = dao.getTjXhList(model);
		// 结果列表
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if (xhList != null && xhList.size() > 0) {
			
			for (int i = 0; i < xhList.size(); i++) {

				HashMap<String, String> xhMap = xhList.get(i);
				// 学号
				String xh = xhMap.get("xh");
				// 姓名
				String xm = xhMap.get("xm");
				// 性别
				String xb = xhMap.get("xb");
				// 学年
				String xn = xhMap.get("xn");
				// 学期
				String xq = xhMap.get("xq");
				String xqm = xhMap.get("xqm");
				// 默认值
				String mrz = xhMap.get("mrz");
				
				// 如果是按照学年统计
				if("xn".equalsIgnoreCase(model.getTjfs())){
					mrz = dao.getXnMrz(xn);
				}
				
				// 学院名称
				String xymc = xhMap.get("xymc");
				// 专业名称
				String zymc = xhMap.get("zymc");
				// 班级名称
				String bjmc = xhMap.get("bjmc");
				// 校区名称
				String xqmc = xhMap.get("xqmc");
				xqmc = Base.isNull(xqmc)?"":xqmc;
				// 楼栋名称
				String ldmc = xhMap.get("ldmc");
				ldmc = Base.isNull(ldmc)?"":ldmc;
				// 层数
				String cs = xhMap.get("cs");
				cs = Base.isNull(cs)?"":cs;
				// 寝室号
				String qsh = xhMap.get("qsh");
				qsh = Base.isNull(qsh)?"":qsh;
				// 总分
				float zf = Base.isNull(mrz) ? 0 : Float.parseFloat(mrz);
				int jfsm = 0;
				float jffs = 0;
				
				int kfsm = 0;
				float kffs = 0;

				if (tjList != null && tjList.size() > 0) {

					for (int j = 0; j < tjList.size(); j++) {

						HashMap<String, String> tjMap = tjList.get(j);

						// 评奖学号
						String pjxh = tjMap.get("xh");
						// 学年
						String pjxn = tjMap.get("xn");
						// 学期
						String pjxq = tjMap.get("xq");
						// 加减分
						String jjf = tjMap.get("jjf");
						// 分值
						float fz = Base.isNull(tjMap.get("fz")) ? 0 : Float
								.parseFloat(tjMap.get("fz"));
						
						if("xq".equalsIgnoreCase(model.getTjfs())){
							if (xh.equalsIgnoreCase(pjxh)
									&& xn.equalsIgnoreCase(pjxn)
									&& xq.equalsIgnoreCase(pjxq)) {
								if ("加分".equalsIgnoreCase(jjf)) {
									zf += fz;
									jfsm ++;
									jffs += fz;
								} else if ("减分".equalsIgnoreCase(jjf)) {
									zf -= fz;
									kfsm ++;
									kffs += fz;
								}
							}
						}else{
							if (xh.equalsIgnoreCase(pjxh)
									&& xn.equalsIgnoreCase(pjxn)) {
								if ("加分".equalsIgnoreCase(jjf)) {
									zf += fz;
									jfsm ++;
									jffs += fz;
								} else if ("减分".equalsIgnoreCase(jjf)) {
									zf -= fz;
									kfsm ++;
									kffs += fz;
								}
							}
						}
					}
				}

				// 操行分
				String cxf = String.valueOf(zf);
				String clzf = String.valueOf(jffs-kffs);
				
				String[] info = null;
				if("xq".equalsIgnoreCase(model.getTjfs())){
					info = new String[] { xh, xm, xb, xn, xqm, xymc, zymc,
						bjmc, xqmc, ldmc, cs, qsh,String.valueOf(jfsm),String.valueOf(jffs),String.valueOf(kfsm),String.valueOf(kffs),clzf,mrz,cxf };
				}else {
					info = new String[] { xh, xm, xb, xn, xymc, zymc,
							bjmc, xqmc, ldmc, cs, qsh,String.valueOf(jfsm),String.valueOf(jffs),String.valueOf(kfsm),String.valueOf(kffs),clzf,mrz,cxf };
				}
				
				list.add(info);
			}
		}
		return list;
	}
	
	/**
	 * 获得操行分统计列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getCxfTjInfo(PjpyTyForm model) throws Exception {

		// 结果列表
		ArrayList<String[]> list = getCxfTjList(model);

		// 分页
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
	
	
	
	/**
	 * 操行分统计
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxfTj(PjpyTyForm model,User user) throws Exception {
		
		if("xq".equalsIgnoreCase(model.getTjfs())){
			return dao.getCxftjByXq(model,user);
		}else{
			return dao.getCxftjByXn(model,user);
		}
	}
	
	
	
	/**
	 * 打印统计报表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printCxfTjbb(PjpyTyForm model, OutputStream os)
			throws Exception {

		// 设置标题
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);	

		List<HashMap<String, String>> topTr = null;
		
		if("xq".equalsIgnoreCase(model.getTjfs())){
			topTr = getTopTr("cxftj");
		}else{
			topTr = getTopTr("cxftjxn");
		}
		
		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}	
		}
		
		ArrayList<String[]> list = getCxfTjList(model);
		
		//填充内容
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				String[] info = list.get(i);
				
				if (info != null && info.length > 0) {
					
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i+1, info[j], wcf2));
					}
				}
			}
		}
				
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得操行分学号相关列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCxfXxList(PjpyTyForm model) {
		return dao.getCxfXxList(model);
	}
	
	/**
	 * 删除操行分
	 * 
	 * @author luojw
	 */
	public Boolean delCxf(PjpyZjjtModel model, String realTable)
			throws Exception {

		boolean result = false;

		String[] checkVal = model.getCheckVal();

		if (checkVal != null && checkVal.length > 0) {
			String pk = "id";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(checkVal);

			result = delPjpy(saveForm, model);

		}
		return result;
	}

	
	
	/**
	 * 初始化操行分
	 * @param model
	 * @return
	 */
	public boolean initCxf(PjpyTyForm model){
		
		try {
			return dao.initCxf(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

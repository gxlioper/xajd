package xgxt.pjpy.guizdx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.utils.ExcelMethods;

public class PjpyGuizdxService extends PjpyTyService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	PjpyGuizdxDAO dao = new PjpyGuizdxDAO();
	
	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if ("rssz_zy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学年", "年级", Base.YXPZXY_KEY +"名称", "专业名称", "类型", "名称",
					"设置人数", "专业代码", "学院代码", "部门类型", "奖学金代码", "设置类型" };
			colListEN = new String[] { "xn", "nj", "xymc", "zymc", "lxmc",
					"jxjmc", "szrs", "zydm", "xydm", "bmlx", "jxjdm", "szlx" };
		}
		
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 获得学院人数列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getXyRsList(PjpyTyForm model) {
		return dao.getXyRsList(model);
	}
	
	/**
	 * 保存评奖评优人数设置(未选择具体学院)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePjpyRssz(PjpyTyForm model, String table)
			throws Exception {

		List<HashMap<String, String>> xyrsList = getXyRsList(model);

		String[] arrzd = new String[] { "szbm", "sznj", "szrs" };
		String[] onezd = new String[] { "xn", "bmlx", "jxjdm", "szbl", "szlx" };

		String pk = "xn||xq||szlx||bmlx||szbm||sznj||jxjdm";
		String[] pkValue = null;

		// 学年
		String xn = model.getXn();
		// 学年
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "无" : xq;
		// 设置类型
		String szlx = model.getSzlx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// 部门类型
		String bmlx = model.getBmlx();
		// 部门年级
		String[] sznj = null;
		// 部门代码
		String[] szbm = null;
		// 设置比例
		String szbl = model.getSzbl();
		// 设置人数
		String[] szrs = null;
		// 奖学金代码
		String jxjdm = model.getJxjdm();

		// 构建主键
		if (xyrsList != null && xyrsList.size() > 0) {

			pkValue = new String[xyrsList.size()];
			sznj = new String[xyrsList.size()];
			szbm = new String[xyrsList.size()];
			szrs = new String[xyrsList.size()];

			for (int i = 0; i < xyrsList.size(); i++) {

				HashMap<String, String> map = xyrsList.get(i);

				// 学院代码
				String xydm = map.get("xydm");
				// 年级
				String nj = map.get("nj");
				// 学院人数
				String rs = map.get("num");

				pkValue[i] = xn + xq + szlx + bmlx + xydm + nj + jxjdm;
				szbm[i] = xydm;
				sznj[i] = nj;
				szrs[i] = String.valueOf(Math.round(Float.parseFloat(rs)
						* Float.parseFloat(szbl) / 100));
			}
		}

		model.setSzbm(szbm);
		model.setSzrs(szrs);
		model.setSznj(sznj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * 获得专业人数列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getZyRsList(PjpyTyForm model) {
		ArrayList<String[]> list = dao.getZyRsList(model);
		return list; //getResultList(list, model);
	}
	
	/**
	 * 保存评奖评优人数设置(专业)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePjpyRssz_Zy(PjpyTyForm model, String table)
			throws Exception {

		String[] arrzd = new String[] { "szbm", "sznj", "szrs" };
		String[] onezd = new String[] { "xn", "bmlx", "jxjdm" };

		String pk = "xn||xq||szlx||bmlx||szbm||sznj||jxjdm";
		String[] pkValue = null;

		// 学年
		String xn = model.getXn();
		// 学年
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "无" : xq;
		// 设置类型
		String szlx = model.getSzlx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// 部门类型
		String bmlx = model.getBmlx();
		bmlx = Base.isNull(bmlx) ? "zy" : bmlx;
		// 部门年级
		String[] sznj = null;
		// 部门代码
		String[] szbm = model.getSzbm();
		// 奖学金代码
		String jxjdm = model.getJxjdm();

		// 构建主键
		if (szbm != null && szbm.length > 0) {

			pkValue = new String[szbm.length];
			sznj = new String[szbm.length];

			for (int i = 0; i < szbm.length; i++) {

				// 年级
				String nj = model.getNj();

				pkValue[i] = xn + xq + szlx + bmlx + szbm[i] + nj + jxjdm;
				sznj[i] = nj;
			}
		}

		model.setBmlx(bmlx);
		model.setSznj(sznj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * 获得学院可分配的剩余人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getXySyRs(PjpyTyForm model) {
		return dao.getXySyRs(model);
	}
	
	/**
	 * 导出人数设置（专业）
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expRssz_Zy(PjpyTyForm model, OutputStream os)
			throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("人数设置", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充表头
		List<HashMap<String, String>> topTr = getTopTr("rssz_zy");

		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}
		
		// 奖学金金额统计列表
		ArrayList<String[]> list = getZyRsList(model);
		
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
}

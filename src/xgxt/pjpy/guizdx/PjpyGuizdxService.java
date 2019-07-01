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
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if ("rssz_zy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "�꼶", Base.YXPZXY_KEY +"����", "רҵ����", "����", "����",
					"��������", "רҵ����", "ѧԺ����", "��������", "��ѧ�����", "��������" };
			colListEN = new String[] { "xn", "nj", "xymc", "zymc", "lxmc",
					"jxjmc", "szrs", "zydm", "xydm", "bmlx", "jxjdm", "szlx" };
		}
		
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ���ѧԺ�����б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getXyRsList(PjpyTyForm model) {
		return dao.getXyRsList(model);
	}
	
	/**
	 * ��������������������(δѡ�����ѧԺ)
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

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "��" : xq;
		// ��������
		String szlx = model.getSzlx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// ��������
		String bmlx = model.getBmlx();
		// �����꼶
		String[] sznj = null;
		// ���Ŵ���
		String[] szbm = null;
		// ���ñ���
		String szbl = model.getSzbl();
		// ��������
		String[] szrs = null;
		// ��ѧ�����
		String jxjdm = model.getJxjdm();

		// ��������
		if (xyrsList != null && xyrsList.size() > 0) {

			pkValue = new String[xyrsList.size()];
			sznj = new String[xyrsList.size()];
			szbm = new String[xyrsList.size()];
			szrs = new String[xyrsList.size()];

			for (int i = 0; i < xyrsList.size(); i++) {

				HashMap<String, String> map = xyrsList.get(i);

				// ѧԺ����
				String xydm = map.get("xydm");
				// �꼶
				String nj = map.get("nj");
				// ѧԺ����
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
	 * ���רҵ�����б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getZyRsList(PjpyTyForm model) {
		ArrayList<String[]> list = dao.getZyRsList(model);
		return list; //getResultList(list, model);
	}
	
	/**
	 * ��������������������(רҵ)
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

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "��" : xq;
		// ��������
		String szlx = model.getSzlx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// ��������
		String bmlx = model.getBmlx();
		bmlx = Base.isNull(bmlx) ? "zy" : bmlx;
		// �����꼶
		String[] sznj = null;
		// ���Ŵ���
		String[] szbm = model.getSzbm();
		// ��ѧ�����
		String jxjdm = model.getJxjdm();

		// ��������
		if (szbm != null && szbm.length > 0) {

			pkValue = new String[szbm.length];
			sznj = new String[szbm.length];

			for (int i = 0; i < szbm.length; i++) {

				// �꼶
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
	 * ���ѧԺ�ɷ����ʣ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getXySyRs(PjpyTyForm model) {
		return dao.getXySyRs(model);
	}
	
	/**
	 * �����������ã�רҵ��
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expRssz_Zy(PjpyTyForm model, OutputStream os)
			throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("��������", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ����ͷ
		List<HashMap<String, String>> topTr = getTopTr("rssz_zy");

		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}
		
		// ��ѧ����ͳ���б�
		ArrayList<String[]> list = getZyRsList(model);
		
		//�������
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

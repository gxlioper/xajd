package xgxt.pjpy.nblg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyNblgZhszcpService {

	public PjpyNblgZhszcpDAO dao = PjpyNblgZhszcpDAO.getInstance();
	
	public static PjpyNblgZhszcpService service = new PjpyNblgZhszcpService();
	
	public static PjpyNblgZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * ��ѯ��ͷ
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> title(String type) throws Exception {
		return dao.title(type);
	}
	
	/**
	 * ��ѯ���
	 * 
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> result(String type, PjpyNblgModel model)
			throws Exception {
		return dao.result(type, model);
	}
	
	public String getDycjName(String params) throws Exception {
		if (StringUtils.isNull(params)) {
			return "��������ɼ�";
		} else {
			if ("pjpy_ddpycjb".equalsIgnoreCase(params)) {
				return "��������ɼ�";
			} else if("pjpy_xwjscjb".equalsIgnoreCase(params)) {
				return "��Ϊ��ʵ�ɼ�";
			} else if("pjpy_shyqjsb".equalsIgnoreCase(params)) {
				return "����԰����ʵ�ɼ�";
			} else {
				return "��������ɼ�";
			}
		}
	}
	
	public boolean save(PjpyNblgModel model, String params,
			HttpServletRequest request) throws Exception {
		if ("pjpy_ddpycjb".equalsIgnoreCase(params)) {
			return dao.ddpycjSave(model, request);
		} else if ("pjpy_xwjscjb".equalsIgnoreCase(params)) {
			return dao.xwjscjSave(model, request);
		} else if ("pjpy_shyqjsb".equalsIgnoreCase(params)) {
			return dao.shyqjsbSave(model, request);
		} else {
			return false;
		}
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao.getStuInfo(xh);
	}
	
	/**
	 * �����ɼ���ʾ��Ϣ
	 * @param pkValue
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> view(String pkValue, String params) throws Exception {
		if ("pjpy_ddpycjb".equalsIgnoreCase(params)) {
			return dao.ddpyView(pkValue, params);
		} else if ("pjpy_xwjscjb".equalsIgnoreCase(params)) {
			return dao.xwcjView(pkValue, params);
		} else if ("pjpy_shyqjsb".equalsIgnoreCase(params)) {
			return dao.shyqView(pkValue, params);
		} else {
			return null;
		}
	}
	
	/**
	 * �����ɼ��޸�
	 * @param pkValue
	 * @param model
	 * @param request
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean update(String pkValue, PjpyNblgModel model,
			HttpServletRequest request, String params) throws Exception {
		if ("pjpy_ddpycjb".equalsIgnoreCase(params)) {
			return dao.ddpyUpdate(pkValue, model, request);
		} else if ("pjpy_xwjscjb".equalsIgnoreCase(params)) {
			return dao.xwcjUpdate(pkValue, model, request);
		} else if ("pjpy_shyqjsb".equalsIgnoreCase(params)) {
			return dao.shyqUpdate(pkValue, model, request);
		} else {
			return false;
		}
	}
	
	public String delete(String[] keys, String realTable,
			HttpServletRequest request) throws Exception {
		return dao.delete(keys, realTable, request);
	}
	
	public boolean zhszcpAutoCount(String xn, String xydm) throws Exception {
		return dao.zhszcpAutoCount(xn, xydm);
	}
	
	/**
	 * �ۺϲ�����ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zhcpTitle() throws Exception {
		return dao.zhcpTitle();
	}
	
	/**
	 * �ۺϲ�����ѯ���
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zhcpResult(PjpyNblgModel model) throws Exception {
		return dao.zhcpResult(model);
	}
	
	public void zhszcpPrint(WritableWorkbook wwb, PjpyNblgModel model) throws Exception {
		List<String[]> rsList = dao.zhcpExp(model);
		HashMap<String, String> rsMap = dao.zhcpFzExp(model.getBjdm());
		WritableSheet ws = wwb.getSheet(0);
		
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.LEFT);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    ws.addCell(new Label(2, 1, rsMap.get("xymc"), wcfStyle));//�����ͷ X/Y
	    ws.addCell(new Label(5, 1, rsMap.get("zymc") + " " +rsMap.get("bjmc"), wcfStyle));//�����ͷ
	    ws.addCell(new Label(8, 1, rsMap.get("dt"), wcfStyle));//�����ͷ
		if (rsList != null && rsList.size() > 0) {
			String[] tmpList = new String[]{};
			for (int i=0;i<rsList.size();i++) {
				tmpList = rsList.get(i);//��ȡҪ����ĵ�������
				if (tmpList != null) {
					int k=0;
					for (int j=0;j<tmpList.length;j++) {
						ws.addCell(new Label(k,i+3,tmpList[j],wcfStyle));
			    		k++;
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
}

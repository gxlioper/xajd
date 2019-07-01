package xgxt.xszz.guizdx;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.jxgl.JxglTyService;
import xgxt.utils.CommonQueryDAO;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.XszzService;

public class XszzGuizdxService extends XszzService {

	XszzGuizdxDAO dao = new XszzGuizdxDAO();
	
	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String[] colListCN = null;
		String[] colListEN = null;

		//��ʳ����_����
		if ("fsbz_fp".equalsIgnoreCase(lx)) {
			
			colListCN = new String[] {"���", Base.YXPZXY_KEY+"����", "רҵ����" };
			colListEN = new String[] { "nd", "xymc", "zymc" };
		}
		// ��ʳ����_����
		else if ("fsbz_ff".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "�Ƿ�Ǽ�", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY+"����",
					"רҵ����", "�༶����", "���", "�·�", "רҵ�����䲹��", };
			colListEN = new String[] { "sfdj", "xh", "xm", "nj", "xymc",
					"zymc", "bjmc", "nd", "yf", "bzmc" };
		}
		// ��ʳ����_δ����
		else if ("fsbz_wff".equalsIgnoreCase(lx)) {
			colListCN = new String[] {  "ѧ��", "����","�Ա�", "�꼶", Base.YXPZXY_KEY+"����",
					"רҵ����", "�༶����", "δ�������", "δ�����·�", "רҵ�����䲹��", };
			colListEN = new String[] {"xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "nd","yf", "bzmc" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	/**
	 * ���רҵ�б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getZyList(XszzTyForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZyList(model, colList);
	}
	
	/**
	 * ��ø�ʳ������Ŀ�б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public List<HashMap<String, String>> getFsbzXmList(XszzTyForm model) {
		return dao.getFsbzXmList(model);
	}

	/**
	 * ���渱ʳ����רҵ��Ŀ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveFsbzZyxm(XszzTyForm model, String tableName)
			throws Exception {
		
		// ���
		String nd = model.getNd();
		// רҵ
		String[] arrZydm = model.getBzzy();
		// רҵ��Ŀ
		String[] zyxmList = model.getZyxmList();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		if (arrZydm != null && arrZydm.length > 0 && zyxmList != null
				&& zyxmList.length > 0) {
			for (int i = 0; i < arrZydm.length; i++) {
				
				String[] arrZyxm = zyxmList[i].split("!!@@!!");
				String zydm = arrZydm[i];
				
				//��ѡ�˲�������
				if (arrZyxm != null && arrZyxm.length > 0) {
					for (int j = 0; j < arrZyxm.length; j++) {
						String bzlx = arrZyxm[j];
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("nd", nd);
						map.put("zydm", zydm);
						map.put("bzlx", bzlx);
						
						list.add(map);
					}
				}
				//δ��ѡ��������
				else{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("nd", nd);
					map.put("zydm", zydm);
					map.put("bzlx", "");
					
					list.add(map);
				}
			}
		}
		
		return dao.saveFsbzZyxm(list, tableName);
	}
	
	/**
	 * ��ø�ʳ����ѧ�������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzXsffList(XszzTyForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFsbzXsffList(model, colList);
	}
	
	/**
	 * ��õ�ǰ�·�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public String getDqyf() {

		DAO dao = DAO.getInstance();

		// ��ǰʱ��
		String nowTime = dao.getNowTime("YYYYMMDD");
		// �·�
		String yf = "";

		if (!Base.isNull(nowTime) && nowTime.length() >= 8) {
			yf = nowTime.substring(4, 6);
		}
		return yf;
	}
	
	/**
	 * ɾ����ʳ����������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delFsbzFf(XszzTyForm model,String tableName) throws Exception {

		// ���
		String nd = model.getNd();
		// �·�
		String yf = model.getYf();
		// ��������
		String bzlx = model.getBzlx();
		// ҳ����ʾȫ��ѧ��
		String[] all_xh = model.getAll_xh();
		// ����
		String pk = "xh||nd||yf||bzlx";
		// /����ֵ
		String[] pkValue = null;
		// ��������
		if (all_xh != null && all_xh.length > 0) {
			pkValue = new String[all_xh.length];
			for (int i = 0; i < all_xh.length; i++) {
				pkValue[i] = all_xh[i] + nd + yf + bzlx;
			}
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		boolean flag = delXszz(saveForm, model);

		return flag;
	}
	
	/**
	 * ��ø�ʳ����δ�����б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzWffList(XszzTyForm model,String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFsbzWffList(model, colList);
	}
}

package xgxt.xtwh.xtwhOther;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;

public class XtwhOtherDAO extends DAO{
	/**
	 * �޸Ŀ���״̬
	 * @param model
	 * @author lr
	 * @throws Exception
	 */
	public Boolean updateKgzt(XtwhOtherForm model) throws Exception {

		boolean flag = false;
		// ��Ŀ����
		String[] xmdm = model.getXmdm();
		// ��Ŀ����
		String[] xmkg = model.getXmkg();

		StringBuffer sql = new StringBuffer();

		if (xmdm != null && xmdm.length > 0) {

			String[] arr_sql = new String[xmdm.length];

			for (int i = 0; i < xmdm.length; i++) {
				sql = new StringBuffer();

				sql.append("update xgxtgy_gnkgb set ");
				sql.append("kgzt = '" + xmkg[i] + "' ");
				sql.append("where gndm = '" + xmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = saveArrDate(arr_sql);
		}

		return flag;
	}

	/**
	 * ��ȡĬ�ϵ��б�
	 * @param lx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getDefaultList(String lx){
		
		String[] dm = null;
		String[] mc = null;

		if ("kgzt".equalsIgnoreCase(lx)) {
			//����״̬
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		}
		
		return arrayToList(dm, mc);
	}
	
	/**
	 * �ж�inputvalueֵ�Ƿ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-17 ����04:10:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isInputValueExist(String inputValue){
		StringBuffer sql = new StringBuffer();	
		sql.append("  select count(1) num from xg_rcsw_hczdb where zdmc = ?");
		String num = getInstance().getOneRs(sql.toString(), new String[]{inputValue}, "num");
		if(xgxt.utils.String.StringUtils.isNotNull(num) && !("0").equals(num)){
			return true;
		}else{
			return false;
		}
	}
}

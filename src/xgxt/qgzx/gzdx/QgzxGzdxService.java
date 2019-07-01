package xgxt.qgzx.gzdx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.basic.BasicDAO;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.qgzx.QgzxTyForm;
import xgxt.qgzx.QgzxTyService;
import xgxt.utils.CommonQueryDAO;

public class QgzxGzdxService extends QgzxTyService {

	QgzxGzdxDAO dao = new QgzxGzdxDAO();
	
	/**
	 * ������ʱ��λ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveLsgw(QgzxTyForm model, String table) throws Exception {

		String[] arrzd = new String[] { "lsgwmc", "lsgwsbsj", "qgxh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "gwsqsj" };

		String pk = "xn||nd||xq||lsgwmc||lsgwsbsj||qgxh";
		String[] pkValue = null;
		String[] lsgwmc = null;
		String[] lsgwsbsj = null;
		String[] qgxh = null;
		
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		xq = Base.isNull(xq) ? "��" : xq;
		// ���
		String nd = model.getNd();
		// ����ʱ��
		String gwsqsj = getNowTime("YYYYMMDD");
		// ��ʱ��λ����
		String[] gwmc = model.getLsgwmc();
		// ��ʱ��λ�걨ʱ��
		String[] sbsj = model.getLsgwsbsj();
		// �ڹ�ѧ��
		String[] checkVal = model.getPrimarykey_checkVal();

		// ��������
		if (checkVal != null && checkVal.length > 0 && gwmc != null
				&& gwmc.length > 0) {

			pkValue = new String[checkVal.length * gwmc.length];
			lsgwmc = new String[checkVal.length * gwmc.length];
			lsgwsbsj = new String[checkVal.length * gwmc.length];
			qgxh = new String[checkVal.length * gwmc.length];
			
			int n = 0;

			for (int i = 0; i < checkVal.length; i++) {

				// ѧ��
				String xh = checkVal[i];

				for (int j = 0; j < gwmc.length; j++) {
					pkValue[n] = xn + nd + xq + gwmc[j] + sbsj[j] + xh;
					lsgwmc[n] = gwmc[j];
					lsgwsbsj[n] = sbsj[j];
					qgxh[n] = xh;
					n++;
				}
			}
		}

		model.setLsgwmc(lsgwmc);
		model.setLsgwsbsj(lsgwsbsj);
		model.setQgxh(qgxh);
		model.setGwsqsj(gwsqsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return saveQgzx(saveForm, model);
	}
	
	/**
	 * ��ѯ��ͷ��Ϣ
	 * @param tableName
	 * @param outputValue
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, 
			                                      String[] outputValue){
		DAO dao = DAO.getInstance();
		String[] colCN = dao.getColumnNameCN(outputValue, tableName);
		return dao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * ��ѯ�ù�����ѧ����Ϣ�б�
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<String[]> queryYgbmxsList(QgzxTyForm model, String[] output){
		return dao.selectYgbmxsList(model, output);
	}
	
	/**
	 * ��ѯ�ù�����ѧ����Ϣ�б�(���˵�λ)
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<String[]> queryYgbmxs(QgzxTyForm model, String[] output){
		return dao.selectYgbmxs(model, output);
	}
	
	/**
	 * �����ڹ���ѧ���������ϱ���Ϣ
	 * @param model
	 * @return boolean
	 * */
	public boolean saveQgzxjjfzsb(QgzxTyForm model){
		return dao.saveQgzxjjfzsb(model);
	}
	
	/**
	 * ��ȡ��˲�ѯ��Ҫ�Զ�����ֶ�
	 * @param yhlx
	 * @return String
	 * */
	public String getCustomAudiColumn(String yhlx){
		StringBuilder sb = new StringBuilder();
		if (yhlx.equalsIgnoreCase("xy")) {
			sb.append(" (case when xxsh='δ���' then '' else 'disabled' end) disabled,");
			sb.append(" (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else if (yhlx.equalsIgnoreCase("fdy")){
			sb.append(" (case when xysh='δ���' then '' else 'disabled' end) disabled, ");
			sb.append(" (case when nvl(fdysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else{
			sb.append(" '' disabled, ");
			sb.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}
		
		return sb.toString();
	}
	
	/**
	 * ��ȡ�б�����
	 * @param int num
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int num){
		DAO dao = DAO.getInstance();
		return dao.getChkList(num);
	}
	
	/**
	 * 2010.9.3 qlj
	 * Υ�ʹ��ִ���
	 */
	public String getWjcs(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(xh)wjcs from wjcfb where xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"wjcs"});
		String wjcs="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			wjcs=hashMap.get("wjcs");
		}else{
			wjcs="0";
		}
		return wjcs;
	}
	
	/**
	 * 2010.9.9 qlj
	 * ���ݴ�ѧ�ۺϲ�����
	 * xnȡ��ǰ����XN
	 */
	public String getZcpm(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select bjpm zcpm from view_gzdx_zhszcp where xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"zcpm"});
		String zcpm="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			zcpm=hashMap.get("zcpm");
		}else{
			zcpm="û��������Ϣ";
		}
		return zcpm;
	}
	
	
	/**
	 * 2010.9.3 qlj
	 * �������Ŀ
	 */
	public String getBjgkms(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql="select count(xh)bjgkms from cjb where  cj<60  and xn=? and xh=?";
		List<HashMap<String,String>>list=dao.getList(sql,new  String[]{xn,xh}, new String[]{"bjgkms"});
		String bjgkms="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			bjgkms=hashMap.get("bjgkms");
		}else{
			bjgkms="0";
		}
		return bjgkms;
	}
	
	/**
	 * 2010.9.14 qlj
	 * ѧ��������ڹ���ѧ��λ
	 */
	public String getQgzxgw(String xh){
		DAO dao=DAO.getInstance();
		String sql="select * from qgzxsqb where xh=? and  zdgwdm is not null";
		StringBuilder zdgwdm=new StringBuilder();
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{xh}, new String[]{"zdgwdm"});
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				if(i!=0){
					zdgwdm.append("��");
					zdgwdm.append(hashMap.get("zdgwdm"));
				}else{
					zdgwdm.append(hashMap.get("zdgwdm"));
				}
			}
		}else{
			zdgwdm.append("��");
		}
		return zdgwdm.toString();
	}
	
	/**
	 * ��ȡ���˵�λ�б�
	 * 
	 */
	public List<HashMap<String,String>>getYrdwList(){
		DAO dao=DAO.getInstance();
		String sql="select yrdwdm dm,yrdwmc mc from yrdwdmb";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
}

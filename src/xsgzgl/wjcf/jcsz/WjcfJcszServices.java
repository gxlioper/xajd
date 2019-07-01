package xsgzgl.wjcf.jcsz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;

/**
 * 
* 
* �����ƣ�WjcfJcszServices 
* ��������Υ�ʹ��ֻ�������Services
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-19 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfJcszServices extends CommService {

	private WjcfJcszDao dao = new WjcfJcszDao();
	
	/**
	 * Υ�ʹ���  �������ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cflbdmCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cflbdmCx(model);
	}
	
	/**
	 * Υ�ʹ���  ������ӿ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbdmCx() throws Exception {
		return dao.cflbdmCx();
	}
	
	/**
	 * Υ�ʹ���  ������ƽӿ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbmcCx() throws Exception {
		return dao.cflbmcCx();
	}
	
	/**
	 * Υ�ʹ���   ����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmZj(WjcfJcszForm model) throws Exception {
		return dao.cflbdmZj(model);
	}
	
	/**
	 * Υ�ʹ���   �������޸�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmXg(WjcfJcszForm model) throws Exception {
		return dao.cflbdmXg(model);
	}
	
	/**
	 * Υ�ʹ���  ������ɾ��
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public boolean cflbdmSc(String cflbdm) throws Exception {
		if(StringUtils.isNotNull(cflbdm)){
			String[] cflbdms=cflbdm.split("@@");
			List<String[]> list=new ArrayList<String[]>();
			for(String str : cflbdms){
				list.add(new String[]{str});
			}
			return dao.cflbdmSc(list);
		}
		return false;
	}
	
	/**
	 * Υ�ʹ���  ������鿴
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cflbdmCk(String cflbdm) throws Exception {
		return dao.cflbdmCk(cflbdm);
	}
	
	
	/**
	 * Υ�ʹ���  ԭ������ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfyydmCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cfyydmCx(model);
	}
	
	/**
	 * Υ�ʹ���  ԭ�����ӿ�
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmCx() throws Exception {
		return dao.cfyydmCx();
	}
	
	/**
	 * Υ�ʹ���  ԭ�����ƽӿ�
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyymcCx() throws Exception {
		return dao.cfyymcCx();
	}
	
	/**
	 * Υ�ʹ���   ԭ���������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmZj(WjcfJcszForm model) throws Exception {
		return dao.cfyydmZj(model);
	}
	
	/**
	 * Υ�ʹ���   ԭ������޸�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmXg(WjcfJcszForm model) throws Exception {
		return dao.cfyydmXg(model);
	}
	
	/**
	 * Υ�ʹ���  ԭ�����ɾ��
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public boolean cfyydmSc(String cfyydm) throws Exception {
		if(StringUtils.isNotNull(cfyydm)){
			String[] cfyydms=cfyydm.split("@@");
			List<String[]> list=new ArrayList<String[]>();
			for(String str : cfyydms){
				list.add(new String[]{str});
			}
			return dao.cfyydmSc(list);
		}
		return false;
	}
	
	/**
	 * Υ�ʹ���  ԭ�����鿴
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfyydmCk(String cfyydm) throws Exception {
		return dao.cfyydmCk(cfyydm);
	}

	/**
	 * �����ࣺ�����б�ͷ��
	 * 
	 * @param model
	 *            ҵ��ģ��
	 * @return
	 */
	public String[] getTopTr(WjcfJcszForm model,String type) {
		String[] outTr = null;
		if("wjcflb".equals(type)){
			outTr = new String[] { "����", "�����", "�Ƿ������", "�Ƿ��������","������������" };
		}else if("wjcfyy".equals(type)){
			outTr = new String[] { "����" };
		}
		
		return outTr;

	}

	/**
	 * �����ࣺ��ʾ��ҳ���б�����������Ҳ�����ݲ�ѯ��������ֵ
	 * 
	 * @param model
	 *            ҵ��ģ��
	 * @return
	 */
	public String[] getColnumName(WjcfJcszForm model,String type) {
		String[] colnumName = null;
		if("wjcflb".equals(type)){
			colnumName = new String[] { "cflbdm", "cflbmc", "spl", "sfkss",
					"sfksqjc", "ssslgzr" };
		}else if("wjcfyy".equals(type)){
			colnumName = new String[] { "cfyymc" };
		}
		return colnumName;
	}
	
	/**
	 * ���洦��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssZj(WjcfCfssglForm model,InputStream is) throws Exception{
		if(model==null){
			return false;
		}
		return false;
	}
	
	
	/**
	 * �޸Ĵ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXg(WjcfCfssglForm model,InputStream is) throws Exception{
		
		return false;
	}
	
	/**
	 * ��������������ѯ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssjcsplCx(WjcfJcszForm model) throws Exception{
		return dao.ssjcsplCx(model);
	}
	
	/**
	 * ������������  ����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean ssjcsplBc(WjcfJcszForm model) throws Exception{
		boolean sc=dao.ssjcsplScSy();
		if(sc){
			if(model==null){
				return false;
			}
			return dao.ssjcsplBc(model);
		}
		return false;
	}
	
	/**
	 * ��ѯ��������ϱ��Ƿ����
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 */
	public boolean cxCfsbBycflbdm(String cflbdm) throws Exception{
		String cnt = dao.cxCfsbBycflbdm(cflbdm);
		return "0".equalsIgnoreCase(cnt) ? false : true;
	}
	
	/**
	 * ��ѯ������������Ƿ��ɾ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public String cxCflbdmsfksc(String pkValue) throws Exception{
		StringBuffer sql = new StringBuffer("select distinct b.cflbmc from xg_wjcf_wjcfsbb a," +
				"xg_wjcf_cflbdmb b where a.cflbdm=b.cflbdm and a.cflbdm in (");
		for (String s : pkValue.split("@@")) {
			sql.append("'");
			sql.append(s);
			sql.append("',");
		}
		sql = new StringBuffer(sql.toString().substring(0, sql.length()-1));
		sql.append(")");
		sql.append("union select distinct cflbmc from (select (select cflbdm from xg_wjcf_cflbdmb b where a.cflbmc=b.cflbmc) cflbdm,cflbmc from xg_wjcf_wjcfb a) where cflbdm in (");
		for (String s : pkValue.split("@@")) {
			sql.append("'");
			sql.append(s);
			sql.append("',");
		}
		sql = new StringBuffer(sql.toString().substring(0, sql.length()-1));
		sql.append(")");
		List<String> cflbmcList = dao.getList(sql.toString(), new String[]{}, "cflbmc");
		String rs = "";
		if (cflbmcList != null && cflbmcList.size() > 0) {
			for (String s : cflbmcList) {
				rs += s;
				rs += ",";
			}
		}
		return StringUtils.isNotNull(rs) ? rs.substring(0, rs.length()-1) : "";
	}
	
	/**
	 * Υ�ʹ������ �Զ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cflbdmExportCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cflbdmExportData(model);
	}
	
	/**
	 * Υ�ʹ���  ԭ������Զ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfyydmExportCx(WjcfJcszForm model)
			throws Exception {
		if(model==null){
			return null;
		}
		return dao.cfyydmExportCx(model);
	}
	
}

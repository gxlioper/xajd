package xsgzgl.qgzx.cssz;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * �ڹ���ѧ-��������-��������
 * 
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszService extends SuperServiceImpl<QgzxCsszForm,QgzxCsszDAO>{
//	public QgzxCsszDAO dao = new QgzxCsszDAO();

	/**
	 * �ṩ�ӿ� ��ó���׼
	 * 
	 * @return
	 */
	public String getCjbz() {
		HashMap<String, String> map = getCssz();
		String cjbz = map.get("cjbz");
		if (Base.isNull(cjbz)) {
			return "0";
		}
		return cjbz;
	}

	/**
	 * �ṩ�ӿ� ���ÿ�³������
	 * 
	 * @return
	 */
	public String getMyCjsx() {
		HashMap<String, String> map = getCssz();
		String cjsx = map.get("cjsx");
		if (Base.isNull(cjsx)) {
			return "0";
		}
		return cjsx;
	}

	/**
	 * �ṩ�ӿ� ��÷��ſ�ʼʱ��
	 * 
	 * @return
	 */
	public String getFfkssj() {
		HashMap<String, String> map = getCssz();
		String kssj = map.get("gzsqkssj");
		if (Base.isNull(kssj)) {
			return "20120901";
		}
		return kssj;
	}

	/**
	 * �ṩ�ӿ� ��÷��Ž���ʱ��
	 * 
	 * @return
	 */
	public String getFfjssj() {
		HashMap<String, String> map = getCssz();
		String jssj = map.get("gzsqjssj");
		if (Base.isNull(jssj)) {
			return "20120901";
		}
		return jssj;
	}

	/**
	 * �ṩ�ӿ� ��÷��Ž���ʱ��
	 * 
	 * @return
	 */
	public String getFfksyf() {
		HashMap<String, String> map = getCssz();
		String ksyf = map.get("ksyf");
		if (Base.isNull(ksyf)) {
			return "201209";
		}
		return ksyf;
	}

	/**
	 * �ṩ�ӿ� ��÷��Ž���ʱ��
	 * 
	 * @return
	 */
	public String getFfjsyf() {
		HashMap<String, String> map = getCssz();
		String jsyf = map.get("jsyf");
		return jsyf;
	}

	/**
	 * ��ò���������ϢMap(cjbz,cjsx)
	 * 
	 * @return
	 */
	public HashMap<String, String> getCssz() {
		return dao.getCssz();
	}

	public HashMap<String, String> getKfsqMap(){
		return dao.sfkfsq();
	}
	
	public HashMap<String, String> getSplcCssz() {
		return dao.getSplcCssz();
	}

	/**
	 * �������������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCssz(QgzxCsszForm model) throws Exception {
		boolean isok = false;
//		XsgwshService xs = new XsgwshService();
		// �������ú�������������
		if(StringUtil.isNull(model.getId())){
			model.setId(UniqID.getInstance().getUniqIDHash());
		}
		isok = dao.runUpdate(model);
//		if (isok && isClean(model)) {
//			isok = xs.resetRsKz();
//		}
		/*if(isok&&"1".equals(new QgzxCsszService().getCjffsh())){
			isok=dao.saveSplcCssz(model);
		}*/
		if(isok){
			isok=dao.saveSplcCssz(model);
		}
		return isok;
	}

	/**
	 * 
	 * @����:�Ƿ���Ҫ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-17 ����06:03:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 */
	public boolean isClean(QgzxCsszForm model) {
		Map<String, String> map = dao.getCssz();
		String rskzjb = map.get("rskzjb");
		//��������������˸��������
		return !rskzjb.equals(model.getRskzjb());
	}

	/**
	 * 
	 * @����:����������Χ�Ƿ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-13 ����03:21:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 */
	private boolean isChange(QgzxCsszForm model) {
		Map<String, String> map = dao.getCssz();
		if (null == map || map.size() <= 0 || null == model) {
			return true;
		}
		String rskzjb = map.get("rskzjb");
		String qxfw = map.get("qxfw");
		return StringUtils.isNull(rskzjb) || !rskzjb.equals(model.getRskzjb())
				|| !qxfw.equals(model.getQxfw());
	}
	
	/**
	 * @��������ȡ�������ñ�� "���ѻ�����ʽ" ֵ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return false��0,���껮�� true��1,���»���
	 * String ��������
	 */
	public String getJfhbfs() {
		return dao.getCsz("jfhbfs");
	}
	
	public String getCjffsh() {
		return dao.getCsz("cjffsh");
	}
	
	public String getSqkg() {
		return dao.getSqkg();
	}
	
	/**
	 * ������ڹ����淽��
	 */
	public boolean saveJcsz(QgzxCsszForm model) throws Exception {
		boolean isok = false;
		isok = dao.saveZjdxCssz(model);
		return isok;
	}

	public HashMap<String,String> getSplc(){
		return dao.getSplc();
	}
}

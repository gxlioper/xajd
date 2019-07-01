package xsgzgl.xsxx.general.xsxxgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * ѧ����Ϣ - ͨ�ù���init
 * lt
 * 2013-1-7
 */
public class XsxxtyglInit {
	
	/**
	 * ѧ����Ϣ��ѯ
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initZxsSearch(RequestForm rForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_tygl_cxzxs.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * ������У����Ϣ
	 * @param myForm
	 * @param request
	 * @return
	 */
	public String bcZxsxx(XsxxtyglForm myForm, HttpServletRequest request, User user,String sfxgFlag) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		
		HashMap<String, String> valueMap = service.getValueMap(request, service.getColumnByTable("xsxxb"));
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		String res="";
		String jgshen = request.getParameter("jgshen");
		String jgshi = request.getParameter("jgshi");
		String jgxian = request.getParameter("jgxian");	
		String hkshen = request.getParameter("hkshen");
		String hkshi = request.getParameter("hkshi");
		String hkxian = request.getParameter("hkxian");
		String syds   = request.getParameter("syds");
		String sydshi   = request.getParameter("sydshi");
		String sydx   = request.getParameter("sydx");
		if(null==myForm.getSfzx() && null==valueMap.get("sfzx")){
			myForm.setSfzx(xsxxMap.get("sfzx"));
			valueMap.put("sfzx", xsxxMap.get("sfzx"));
		}
		//�Ա�Ĵ���
		valueMap.put("xb", valueMap.get("xb").equals("��")?"1":"2");
		//����Ĵ���
		if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi) && StringUtils.isNotNull(jgxian) ) {
			valueMap.put("jg", jgxian);
		} else if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi)) {
			valueMap.put("jg", jgshi);
			valueMap.put("jgx", "");
		} else if (StringUtils.isNotNull(jgxian)) {
			valueMap.put("jg", jgxian);
		}else if (StringUtils.isNotNull(jgshen)) {
			valueMap.put("jg", jgshen);
			valueMap.put("jgshi", "");
			valueMap.put("jgx", "");
		} else {
			valueMap.put("jg", "");
			valueMap.put("jgs", "");
			valueMap.put("jgshi", "");
			valueMap.put("jgx", "");
		}
		//�������ڵصĴ���
		if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi) && StringUtils.isNotNull(hkxian) ) {
			valueMap.put("hkszd", hkxian);
		} else if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi)) {
			valueMap.put("hkszd", hkshi);
			valueMap.put("hkxian", "");
		} else if (StringUtils.isNotNull(hkxian)) {
			valueMap.put("hkszd", hkxian);
		}else if (StringUtils.isNotNull(hkshen)) {
			valueMap.put("hkszd", hkshen);
			valueMap.put("hkshi", "");
			valueMap.put("hkxian", "");
		} else {
			valueMap.put("hkszd", "");
			valueMap.put("hkshen", "");
			valueMap.put("hkshi", "");
			valueMap.put("hkxian", "");
		}
		//��Դ�صĴ���
		if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi) && StringUtils.isNotNull(sydx) ) {
			valueMap.put("syd", sydx);
		} else if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi)) {
			valueMap.put("syd", sydshi);
			valueMap.put("sydx", "");
		} else if (StringUtils.isNotNull(sydx)) {
			valueMap.put("syd", sydx);
		}else if (StringUtils.isNotNull(syds)) {
			valueMap.put("syd", syds);
			valueMap.put("sydshi", "");
			valueMap.put("sydx", "");
		} else {
			valueMap.put("syd", "");
			valueMap.put("syds", "");
			valueMap.put("sydshi", "");
			valueMap.put("sydx", "");
		}
		
		boolean flag = true;
		if (xsxxMap != null && !StringUtils.isNull(xsxxMap.get("xh"))) {//�޸�
			myForm.setValueMap(valueMap);
			
			//ѧ���û�����Ϣ����
			if ("stu".equalsIgnoreCase(user.getUserType())) {
			//	if("true".equals(sfxgFlag)){
					HashMap<String, String> csszMap = service.getCsszjg();
					//��������ı��洦��
					if (StringUtils.isNotNull(csszMap.get("shlid")) && !"wxsh".equalsIgnoreCase(csszMap.get("shlid"))) {
						valueMap = service.getValueMap(request, service.getColumnByTable("xg_xsxx_xxxgzdxgb"));
						
						//����Ĵ���
						if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi) && StringUtils.isNotNull(jgxian) ) {
							valueMap.put("jg", jgxian);
						} else if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi)) {
							valueMap.put("jg", jgshi);
							valueMap.put("jgx", "");
						} else if (StringUtils.isNotNull(jgxian)) {
							valueMap.put("jg", jgxian);
						}else if (StringUtils.isNotNull(jgshen)) {
							valueMap.put("jg", jgshen);
							valueMap.put("jgshi", "");
							valueMap.put("jgx", "");
						} else {
							valueMap.put("jg", "");
							valueMap.put("jgs", "");
							valueMap.put("jgshi", "");
							valueMap.put("jgx", "");
						}
						//�������ڵصĴ���
						if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi) && StringUtils.isNotNull(hkxian) ) {
							valueMap.put("hkszd", hkxian);
						} else if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi)) {
							valueMap.put("hkszd", hkshi);
							valueMap.put("hkxian", "");
						} else if (StringUtils.isNotNull(hkxian)) {
							valueMap.put("hkszd", hkxian);
						}else if (StringUtils.isNotNull(hkshen)) {
							valueMap.put("hkszd", hkshen);
							valueMap.put("hkshi", "");
							valueMap.put("hkxian", "");
						} else {
							valueMap.put("hkszd", "");
							valueMap.put("hkshen", "");
							valueMap.put("hkshi", "");
							valueMap.put("hkxian", "");
						}
						//��Դ�صĴ���
						if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi) && StringUtils.isNotNull(sydx) ) {
							valueMap.put("syd", sydx);
						} else if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi)) {
							valueMap.put("syd", sydshi);
							valueMap.put("sydx", "");
						} else if (StringUtils.isNotNull(sydx)) {
							valueMap.put("syd", sydx);
						}else if (StringUtils.isNotNull(syds)) {
							valueMap.put("syd", syds);
							valueMap.put("sydshi", "");
							valueMap.put("sydx", "");
						} else {
							valueMap.put("syd", "");
							valueMap.put("syds", "");
							valueMap.put("sydshi", "");
							valueMap.put("sydx", "");
						}
						
						HashMap<String, String> sqxxMap = service.getStuSqztMap(myForm.getXh()); 
						//��������������û���ͨ�����Ҳ��������״̬�У���Ҫ�����޸�
						if (StringUtils.isNotNull(sqxxMap.get("sqid"))) {
							flag = service.updateStusqxx(sqxxMap.get("sqid"), valueMap, xsxxMap,sqxxMap,sfxgFlag);
							if(flag){
								res=sqxxMap.get("sqid");
							}
						} else if("true".equals(sfxgFlag)){//Ҫ�������ӵĴ���
							 res = service.insertStusqxx(valueMap, xsxxMap, csszMap.get("shlid"),user.getUserType());
							if(!"".equals(res)){
								flag=true;
							}
						}else if("false".equals(sfxgFlag)){//Ҫ�������ӵĴ���
							 res = user.getUserName();
								
							}
					} else if("true".equals(sfxgFlag)){//������˵�ֱ���޸���ʽ��
						flag = service.updateInfo(myForm, "xsxxb");
						//�޸ĳɹ������xsfzxxb
						if (flag) {
							valueMap = service.getValueMap(request, service.getColumnByTable("xsfzxxb"));
							myForm.setValueMap(valueMap);
							//VALUEMAP��Ĭ���ֶ���ѧ�ţ����Ա����޸Ĺ���ѧ��������ֶβ��ܸ���xsfzxxb
							if (valueMap != null && valueMap.size() > 1) {
								flag = service.updateInfo(myForm, "xsfzxxb");
							}
						}
						res = user.getUserName();
					}
					else if("false".equals(sfxgFlag)){//������˵�ֱ���޸���ʽ��
						res = user.getUserName();
					}
			//	}
			} else {//��ʦ�û����޸ı���
				flag = service.updateInfo(myForm, "xsxxb");
				//�޸ĳɹ������xsfzxxb
				if (flag) {
					valueMap = service.getValueMap(request, service.getColumnByTable("xsfzxxb"));
					myForm.setValueMap(valueMap);
					//VALUEMAP��Ĭ���ֶ���ѧ�ţ����Ա����޸Ĺ���ѧ��������ֶβ��ܸ���xsfzxxb
					if (valueMap != null && valueMap.size() > 1) {
						flag = service.updateInfo(myForm, "xsfzxxb");
					}
					res=myForm.getValueMap().get("xh");
				}
			}
		} else {//������
			valueMap.put("sfzx", "��У");
			flag = service.saveInfo(valueMap);
			//���ӳɹ�����xsmmb,xsfzxxb,xszpb������������
			if (flag) {
				flag = service.saveXsqtxx(myForm, user);
			}
			res=myForm.getXh();
		}
		return res;
	}
	
	/**
	 * ����ѧ�������޸���Ϣ
	 * @param valueMap
	 * @param xsxxMap
	 * @return
	 */
	public boolean bcXssqxgxx(HashMap<String, String> valueMap, HashMap<String, String> xsxxMap) {
		boolean result = false;
		
		return result;
		
	}
	
	/**
	 * ѧ����Ϣ��ѯ
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initFzxsSearch(RequestForm rForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_tygl_cxfzxs.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}

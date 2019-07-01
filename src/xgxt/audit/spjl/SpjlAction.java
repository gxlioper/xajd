package xgxt.audit.spjl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.form.User;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������¼Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpjlAction extends BasicAction {
	
	
	//������¼����Manage
	public ActionForward spjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		HashMap<String, String> spjlMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addSpjl(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			result = service.modiSpjl(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			spjlMap = service.getSpjl(myForm);
		}

		request.setAttribute("rs", spjlMap);
		request.setAttribute("doType", doType);
		
		return mapping.findForward("spjlwh");
	}
	
	/**
	 * ����: ������¼�������
	 * @return
	 * @author ׯ��ΰ
	 * Jul 2, 2010
	 */
	public ActionForward spjlAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String beanName; 
		//����������̵Ĺ����ԣ�������Ҫ��beanName��������,Ҳ���������뵥�Ź���������
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		List<String[]> rs  = null;
		List<HashMap<String,String>> spbzList = new ArrayList<HashMap<String,String>>();
		List<String[]> spjlList = null;
		
		//String sqdh = request.getParameter("sqdh");
		//String splc = request.getParameter("splc");
		String spgw = request.getParameter("spgw");
		//String name = request.getParameter("name");
		//name = java.net.URLDecoder.decode(name,"UTF-8");
		//String actionName = request.getParameter("actionname");
		int spbzxh = 0; 
		String spbz = "",nspgw = "";
		try {
			spjlList  = service.getSpjlList(myForm);
			//myForm.setDjlx(sybSplc.getDjlx()); //ע�뵥������
			spbzList = service.getSpbzList(myForm);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		boolean thtag = true;
		List<HashMap<String,String>> thbzList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < spbzList.size(); i++) {
			map = spbzList.get(i);
			if(spgw.equals(map.get("spgw"))){
				thtag = false;
				spbz = map.get("id");
				if(i<spbzList.size()-1){
					map = spbzList.get(i+1);
					//spbzxh = Integer.valueOf(map.get("xh"));
					nspgw = map.get("spgw");
				}else{
					nspgw = "3"; //�Ѿ������������λ��ͨ���������������
				}
				break;
			}
			if(thtag){
				thbzList.add(map);
			}
		}
		//�е�СΣ��
		map = new HashMap<String,String>();
		map.put("spgw", "1");
		map.put("spgwmc", "�˻�������");
		thbzList.add(map);
		
		request.setAttribute("rs", myForm);
		request.setAttribute("spbzList", spbzList);
		request.setAttribute("thbzList", thbzList);
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spbz", spbz);
		request.setAttribute("spgw", spgw);
		request.setAttribute("nspgw", nspgw);
		request.setAttribute("name", myForm.getName());
		request.setAttribute("actionname", myForm.getActionName());
		return mapping.findForward("audit");
	}
	
	
	/**
	 * ����: �������̡���ȡ�����
	 * ȡ�������Ҫ�ж�����״̬ʱ���Ǹø�λ����һ��λ
	 * 
	 * @return
	 * @throws IOException
	 * @author ׯ��ΰ
	 * Jul 2, 2010
	 */
	public ActionForward spjlCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		//sqdh = request.getParameter("sqdh");
		//String splc = request.getParameter("splc");
		String spgw = request.getParameter("spgw"); //ҳ���¼������λ
		String actionName = request.getParameter("actionname");
		String spzt = new String(); //��Ҫ��ȥ��������ǰһ����
		List<HashMap<String,String>> spbzList = new ArrayList<HashMap<String,String>>();
		try {
			
			spbzList = service.getSpbzList(myForm);
			HashMap<String,String> map = new HashMap<String,String>();
			for (int i = 0; i < spbzList.size(); i++) {
				map = spbzList.get(i);
				if(spgw.equals(map.get("spgw"))){
					if(i>0){
						map = spbzList.get(i-1);
						spzt = map.get("spgw");
					}else{
						spzt = spgw; //���������Ӧ�ò�������������
					}
					break;
				}
			}
			/****
			String serviseName = actionName.substring(actionName.indexOf("_")+1, actionName.length())+"Service";
			BillService billService = DynamicBeanFactory.getBillService(serviseName);
			billService.saveAuditFlow(sqdh, spzt, null);
			***/
			result = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		return mapping.findForward("audit");
	}
	
	
	
	/**
	 * ����: �������̡������棨��ˣ�
	 * ͬʱ��Ҫ�޸������¼�����ö�̬����bean��ʽ
	 * @return
	 * @throws IOException
	 * @author ׯ��ΰ
	 * Jul 2, 2010
	 */

	public ActionForward spjlSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpjlService service = new SpjlService();
		SpjlForm myForm = (SpjlForm) form;
		String doType = request.getParameter("doType");
		String tid = request.getParameter("id");
		boolean result = false;
		String sqdh = myForm.getDjh();
		String spbz = request.getParameter("spbz");
		String nspgw = request.getParameter("nspgw");
		String actionName = request.getParameter("actionname");
		String spzt = new String();
		User user = getUser(request);// �û�����
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			myForm.setSpbz(spbz);
			myForm.setSpr(user.getUserName());//����������
			myForm.setSprq(date);
			if(tid != null && !"".equals(tid)){
				service.modiSpjl(myForm); 
			}else{
				service.addSpjl(myForm);
			}
			//�ж��Ƿ�ͨ����ˣ���ͨ������Ҫ���´���
			if("0".equals(myForm.getSftg())){ //ͨ��
				spzt = nspgw;
			}else{
				spzt = myForm.getThgw();
			}
			//�˴���Ҫ��̬����ServiceBean��ͬʱ��Ҫ��ȡ��һ��λ���޸���Ӧ���뵥��¼
			/***
			String serviseName = actionName.substring(actionName.indexOf("_")+1, actionName.length())+"Service";
			BillService billService = DynamicBeanFactory.getBillService(serviseName);
			billService.saveAuditFlow(sqdh, spzt, map);
			***/
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		return mapping.findForward("audit");
	}

}

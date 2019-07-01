package xgxt.mdqr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

import com.zfsoft.basic.BasicService;

public class MdqrService {

	/**
	 * method getXmlbList ��ȡ��Ŀ����б� return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmlbList(String gnmk) {
		MdqrDAO dao = new MdqrDAO();
		return dao.getXmlbList(gnmk);
	}

	/**
	 * method getXmList ��ȡ��Ŀ�б� return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmList(String xmlbdm) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("dm", "");
		hashMap.put("mc", "----��ѡ��----");
		list.add(hashMap);
		list.addAll(dao.getXmList(xmlbdm));
		return list;
	}
	
	/**
	 * ������������Ȩ�޻�ȡ�б�
	 * method getXmList ��ȡ��Ŀ�б� return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmListBySzqx(String xmlbdm,String yhqx) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("dm", "");
		hashMap.put("mc", "----��ѡ��----");
		list.add(hashMap);
		list.addAll(dao.getXmList(xmlbdm,yhqx));
		return list;
	}
	
	/**
	 * ������������Ȩ�޻�ȡ�б�
	 * method getXmList ��ȡ��Ŀ�б� return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmListByShqx(String xmlbdm,String yhqx) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String shjb="";
		if("xx".equalsIgnoreCase(yhqx)){
			shjb=" and (shjb='xxsh' or shjb='xyxsh') ";
		}else if("xy".equalsIgnoreCase(yhqx)){
			shjb=" and (shjb='xysh' or shjb='xyxsh') ";
		}
		
		hashMap.put("dm", "");
		hashMap.put("mc", "----��ѡ��----");
		list.add(hashMap);
		list.addAll(dao.getXmListByShqx(xmlbdm,shjb));
		return list;
	}
	
	/**
	 * method getXmList ��ȡ��Ŀ�б� return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmShList(String xmdm,String mdqr) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.addAll(dao.getXmShList(xmdm,mdqr));
		return list;
	}

	/**
	 * �����������ý��
	 * 
	 * @throws Exception
	 *             ���������������� method mdsz return void
	 */
	public void mdsz(HttpServletRequest request, MdqrForm myForm)
			throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();

		// �������ݲ��� �ı���
		String realTable = "mdqr_xmnrb";

		// ��ǰҳ��ѧ������(��ɾ��������ҳ��Ϣɾ��ʱ)
		String[] xh = myForm.getSave_xh();
		// ��ѡ����Ϣ(ֵ��ѧ��)
		String[] cbv = myForm.getPrimarykey_cbv();
		// ��Ŀ

		String xm = myForm.getSave_xmdm();
		// ��Ŀ���
		String xmlbdm = myForm.getSave_xmlbdm();
		
		String sbsj= myForm.getSave_sbsj();

		String xn = myForm.getXn();

		String xq = myForm.getXq();

		String nd = myForm.getNd();
		
		String []isDis=myForm.getIsDisable();

		String[] pkValue = new String[xh.length];
		// �жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		//��������
		String pk ="";
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn";
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn||xq";
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||nd";
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||sbsj";
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh";
		}
		
		
		String[] arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","nd" };
		for (int i = 0; i < xh.length; i++) {
			//�ж������Ƿ�Ϊֻ��,ֻɾ����ֻ������
			if(!"disabled".equalsIgnoreCase(isDis[i])){
				if("xn".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + xn;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn"};
				}else if("xq".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + xn + xq;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq"};
				}else if("nd".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + nd;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "nd" };
				}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + sbsj;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh" };
				}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i];
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh"};
				}
			}
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		MdqrModel model = new MdqrModel();
		if (cbv != null) {
			String[] xnArr = new String[cbv.length];
			String[] xqArr = new String[cbv.length];
			String[] ndArr = new String[cbv.length];
			String[] xmArr = new String[cbv.length];
			String[] xmlbArr = new String[cbv.length];
			String[] sbsjArr = new String[cbv.length];
			for (int i = 0; i < cbv.length; i++) {
				
					xmArr[i] = xm;
					xmlbArr[i] = xmlbdm;
					sbsjArr[i] =sbsj;
					if("xn".equalsIgnoreCase(getSzzq(xm))){
						xnArr[i] = xn;
					}else if("xq".equalsIgnoreCase(getSzzq(xm))){
						xnArr[i] = xn;
						xqArr[i] = xq;
					}else if("nd".equalsIgnoreCase(getSzzq(xm))){
						ndArr[i] = nd;
					}
				
			}
			
			model.setXh(cbv);
			model.setSbsj(sbsjArr);
			model.setXmdm(xmArr);
			model.setXmlbdm(xmlbArr);
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
				model.setXq(xqArr);
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				model.setNd(ndArr);
			}
		}
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}

	/**
	 * ��������ȷ�Ͻ��
	 * 
	 * @throws Exception
	 * ����ȷ���������� method mdsz return void
	 */
	public void mdqr(HttpServletRequest request, MdqrForm myForm)
			throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();

		// �������ݲ��� �ı���
		String realTable = "mdqr_xmnrb";

		// ��ǰҳ��ѧ������(��ɾ��������ҳ��Ϣɾ��ʱ)
		String[] xh = myForm.getSave_xh();
		// ��ѡ����Ϣ(ֵ��ѧ��)
		String[] cbv = myForm.getPrimarykey_cbv();
		// ��Ŀ
		String[] xysh =myForm.getXysh();
		
		String[] xxsh =myForm.getXxsh();
		
		String[] sbsj=myForm.getSbsjArr();
		
		String xm = myForm.getQueryequals_xmdm();
		// ��Ŀ���
		String xmlbdm = myForm.getQueryequals_xmlbdm();

		String xn = myForm.getXn();

		String xq = myForm.getXq();

		String nd = myForm.getNd();

		String[] pkValue = new String[xh.length];
		// �жϲ����Ƿ�ɹ�
		boolean reslut = false;

//		��������
		String pk ="";
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn";
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn||xq";
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||nd";
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||sbsj";
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh";
		}
		
		
		String[] arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","nd" };
		for (int i = 0; i < xh.length; i++) {
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + xn;
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + xn + xq;
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + nd;
			}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + sbsj[i];
			}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i];
			}
		}
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn","mdqr","xysh","xxsh"};
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","mdqr","xysh","xxsh"};
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "nd","mdqr","xysh","xxsh" };
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh","mdqr","xysh","xxsh" };
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh","mdqr","xysh","xxsh"};
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		MdqrModel model = new MdqrModel();
		if (cbv != null) {
			String[] xnArr = new String[xh.length];
			String[] xqArr = new String[xh.length];
			String[] ndArr = new String[xh.length];
			String[] xmArr = new String[xh.length];
			String[] xmlbArr = new String[xh.length];
			String[] xyshArr =new String[xh.length];
			String[] xxshArr =new String[xh.length];
			String[] sbsjArr =new String[xh.length];
			String[] mdqrArr = new String[xh.length];
			for(int j=0;j<xh.length;j++){
				if("xn".equalsIgnoreCase(getSzzq(xm))){
					xnArr[j] = xn;
				}else if("xq".equalsIgnoreCase(getSzzq(xm))){
					xnArr[j] = xn;
					xqArr[j] = xq;
				}else if("nd".equalsIgnoreCase(getSzzq(xm))){
					ndArr[j] = nd;
				}
				xmArr[j] = xm;
				xmlbArr[j] = xmlbdm;
				xyshArr[j] = xysh[j];
				xxshArr[j] = xxsh[j];
				sbsjArr[j] = sbsj[j];
				for (int i = 0; i < cbv.length; i++) {
					if(xh[j].equalsIgnoreCase(cbv[i])){
						
						mdqrArr[j] = "checked";
						break;
					}
				}
			}
			model.setXh(xh);
			model.setXmdm(xmArr);
			model.setXmlbdm(xmlbArr);
			model.setSbsj(sbsjArr);
			model.setXysh(xyshArr);
			model.setXxsh(xxshArr);
			model.setMdqr(mdqrArr);
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
				model.setXq(xqArr);
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				model.setNd(ndArr);
			}
		}
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}
	
	public void getXmxx(HttpServletRequest request, MdqrForm form)
			throws Exception {
		MdqrDAO dao = new MdqrDAO();
		BasicService basicService = new BasicService();
		// ��ѯ�����
		request.setAttribute("rs", dao.getXmxx(form));
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb", new String[] { "���", 
				"ѧ��", "����", "�꼶", Base.YXPZXY_KEY,"רҵ","�༶","�Ƿ���������" }));
	}
	
	public void getMdqrXx(HttpServletRequest request, MdqrForm form)
		throws Exception {
		MdqrDAO dao = new MdqrDAO();
		BasicService basicService = new BasicService();
		
		String [] topTr=new String[] {"���","ѧ��", "����", "�꼶","��������ʱ��",  Base.YXPZXY_KEY, "�༶","�Ƿ�ȷ��"};
		request.setAttribute("rsNum", dao.getMdqrXx(form).size());
		request.setAttribute("colsStr", topTr.length);
		request.setAttribute("rs", dao.getMdqrXx(form));
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmmdqrb", topTr));
	}
	
	public void getXx(HttpServletRequest request){
		
		BasicService basicService = new BasicService();
		
		String [] topTr=new String[] { "���","ѧ��", "����", "�꼶","��������ʱ��",  Base.YXPZXY_KEY, "�༶","�Ƿ�ȷ��"};
		List<String[]>list=new ArrayList<String[]>();

		request.setAttribute("rs", list);
		request.setAttribute("rsNum",0);
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmmdqrb", topTr));
		request.setAttribute("colsStr", topTr.length);
	}
	
	//���ز�ѯ������ֶ�
	public String[] getOutputArr(MdqrForm form){
		//��˼���
		String shjb=getXmShjb(form);
		String []outputColumn={"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xysh","xxsh"};
		//�ж���˼���   ѧԺ
		if("xysh".equalsIgnoreCase(shjb)){
			outputColumn=new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xysh"};
		}else if("xxsh".equalsIgnoreCase(shjb)){
			outputColumn=new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xxsh"};
		}
		return outputColumn;
	}
	
	/**
	 * disabled�����ж�
	 * @param form
	 * @return
	 */
	public void setDisabled(HttpServletRequest request,MdqrForm form){
		//��˼���
		String shjb=getXmShjb(form);
		if("xyxsh".equalsIgnoreCase(shjb)
			&& "xy".equalsIgnoreCase(form.getUserType())){
				request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled, ");
		}else{
			request.setAttribute("clientColumns", "'' disabled, ");
		}
		
	}
	
	//��ȡ��Ŀ��˼���
	public String  getXmShjb(MdqrForm form){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.getXmShjb(form);
		return hashMap.get("shjb");
	}
	
	/**
	 * ���ʱ�����ֵ
	 */
	public HashMap<String, String> getShValMap(MdqrForm form) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// ��˼���
		String shjb = getXmShjb(form);
		// ѧԺ�������
		if ("xysh".equalsIgnoreCase(shjb)) {
			hashMap.put("xysh", form.getShjg());
			// ѧУ�������
		} else if ("xxsh".equalsIgnoreCase(shjb)) {
			hashMap.put("xxsh", form.getShjg());
			// ѧԺ��� - ѧУ�������
		} else if ("xyxsh".equalsIgnoreCase(shjb)) {
			// ѧԺ�û�
			if ("xy".equalsIgnoreCase(form.getUserType())) {
				hashMap.put("xysh", form.getShjg());
				// ѧУ�û�
			} else if ("xx".equalsIgnoreCase(form.getUserType())
					|| "admin".equalsIgnoreCase(form.getUserType())) {
				hashMap.put("xxsh", form.getShjg());
			}
		}
		return hashMap;
	}
	
	/**
	 * ���ʱ�����ֵ
	 */
	public HashMap<String, String> getQrValMap(String mdqr) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// ��˼���
		hashMap.put("mdqr",mdqr );
		return hashMap;
	}

	/**
	 * ����ѡ�����Ŀ�����ж�
	 * @param xmdm
	 * @return
	 */
	public String checkMdSz(String xmdm){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.checkMdSz(xmdm);
		return hashMap.get("mdsz");
	}
	
	/**
	 * ����ѡ�����Ŀ�����ж�
	 * @param xmdm
	 * @return
	 */
	public String checkMdQr(String xmdm){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.checkMdQr(xmdm);
		return hashMap.get("mdqr");
	}
	
	/**
	 * ����ģ���ȡpath·��
	 * @param path(path·��),gnmk(����ģ��)
	 */
	public String getPath(String path,String gnmk){
		path+="&gnmk="+gnmk;
		return path;
	}
	
	/**
	 * ��ȡ�û�Ȩ��
	 * 
	 * @param
	 */
	public String getYhqx(MdqrForm form) {
		String yhqx = "";
		if ("true".equalsIgnoreCase(form.getIsFdy())) {
			yhqx = "fdy";
		} else if ("xy".equalsIgnoreCase(form.getUserType())) {
			yhqx = "xy";
		} else if ("xx".equalsIgnoreCase(form.getUserType())
				|| "admin".equalsIgnoreCase(form.getUserType())) {
			yhqx = "xx";
		}
		return yhqx;
	}
	
	/**
	 * ������Ŀ�����ȡ����
	 * 
	 */
	public String getSzzq(String xmdm){
		MdqrDAO dao =new MdqrDAO();
		HashMap<String,String>hashMap=dao.getSzzq(xmdm);
		String szzq=hashMap.get("szzq");
		return szzq;
	}
	
	/**
	 * ��ȡ��˼���
	 */
	public String getShjb(MdqrForm form){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.getShjb(form);
		String shjb=hashMap.get("shjb");
		return shjb;
	}
	
	/**
	 * �ж��Ƿ�ΪѧԺ,ѧУ�������
	 */
	public void checkShjb(HttpServletRequest request,MdqrForm form){
		
		//�ж��Ƿ񸨵�Ա
		if("ture".equalsIgnoreCase(form.getIsFdy())){
			request.setAttribute("annexTerm", " and 1=2 ");
		}
		if("xx".equalsIgnoreCase(form.getUserType())
				|| "admin".equalsIgnoreCase(form.getUserType())){
			if("xyxsh".equalsIgnoreCase(getShjb(form))){
				request.setAttribute("annexTerm", " and xysh='ͨ��' ");
			}
		}
	}
	
	/**
	 * ��ʼ���б�
	 */
	public void initXmList(HttpServletRequest request){
		//�걨����
		List<HashMap<String,String>>sbzqList=arrToList(new String[]{"ѧ��","ѧ��","���","������","��һ��"});
		//�걨����
		List<HashMap<String,String>>mdszList=arrToList(new String[]{"����Ա", Base.YXPZXY_KEY,"ѧУ","������"});
		//�걨����
		List<HashMap<String,String>>mdqrList=arrToList(new String[]{"����Ա", Base.YXPZXY_KEY,"ѧУ","������"});
		//�걨����
		List<HashMap<String,String>>shjbList=arrToList(new String[]{ Base.YXPZXY_KEY+"���","ѧУ���", Base.YXPZXY_KEY+"���-ѧУ���","�����"});
		//�걨����
		List<HashMap<String,String>>kfsbList=arrToList(new String[]{"����","�ر�"});
		//�걨����
		List<HashMap<String,String>>kfshList=arrToList(new String[]{"����","�ر�"});
		//�걨����
		List<HashMap<String,String>>kfqrList=arrToList(new String[]{"����","�ر�"});
		request.setAttribute("sbzqList", sbzqList);
		request.setAttribute("mdszList", mdszList);
		request.setAttribute("mdqrList", mdqrList);
		request.setAttribute("shjbList", shjbList);
		request.setAttribute("kfsbList", kfsbList);
		request.setAttribute("kfshList", kfshList);
		request.setAttribute("kfqrList", kfqrList);
	}
	
	/**
	 * ������ת��ΪhashMap
	 */
	public List<HashMap<String,String>>arrToList(String[]strArr){
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<strArr.length;i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			hashMap.put("dm", strArr[i]);
			hashMap.put("mc", strArr[i]);
			list.add(hashMap);
		}
		return list;
	}
	
	public boolean deleteXmxx(MdqrForm form) throws Exception{
		MdqrDAO dao=new MdqrDAO();
		return dao.deleteXmxx(form);
	}
	
	public MdqrForm setFormXx(MdqrForm form){
		form.setBjdm(form.getQueryequals_bjdm());
		form.setNj(form.getQueryequals_nj());
		form.setXh(form.getQuerylike_xh());
		form.setXm(form.getQuerylike_xm());
		form.setXydm(form.getQueryequals_xydm());
		form.setZydm(form.getQueryequals_zydm());
		form.setXmlbdm(form.getQueryequals_xmlbdm());
		form.setXmdm(form.getQueryequals_xmdm());
		form.setXn(form.getQueryequals_xn());
		form.setXq(form.getQueryequals_xq());
		form.setNd(form.getQueryequals_nd());
		return form;
	}
	
	/**
	 * ��ȡѧ������
	 * @param xqdm
	 * @return
	 */
	public String getXqmc(String xqdm){
		MdqrDAO dao=new MdqrDAO();
		String xqmc=dao.getXqmc(xqdm).get("xqmc");
		return xqmc;
	}
	
	//����ֶ�
	public String[]getOutPut(MdqrForm myForm){
		//��Ŀ����
		String zq=getSzzq(myForm.getQueryequals_xmdm());
		String[] outputColumn = { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "xmmc", "xmlbmc" };
		//�������ڷ��ؽ�����б�
		if("xn".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","xn", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}else if("xq".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","xn","xqmc", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}else if("nd".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","nd", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}
		return outputColumn;
	}
	
	public void getShTj(HttpServletRequest request,MdqrForm myForm){
		StringBuilder query=new StringBuilder();
		if(!"".equalsIgnoreCase(myForm.getKssj())
				&& myForm.getKssj()!=null){
			 query.append(" and sbsj>='"+myForm.getKssj()+"'");
		}
		
		if(!"".equalsIgnoreCase(myForm.getJssj())
				&& myForm.getJssj()!=null){
			 query.append(" and sbsj<='"+myForm.getJssj()+"'");
		}
		query.append("and exists (select xmlbdm from mdqr_xmlbb b where a.xmlbdm=b.xmlbdm and gnmk='"+myForm.getGnmk()+"') ");
		request.setAttribute("annexTerm", query.toString());
	}
	
	public MdqrForm initXmdm(MdqrForm myForm){
		ArrayList<HashMap<String,String>> list=(ArrayList<HashMap<String,String>>)getXmlbList(myForm.getGnmk());
		String xmlbdm=list.get(0).get("dm");
		ArrayList<HashMap<String,String>> xmlist=(ArrayList<HashMap<String,String>>)getXmShList("%",getYhqx(myForm));
		myForm.setXmdm(xmlist.get(0).get("dm"));
		myForm.setQueryequals_xmlbdm(xmlbdm);
		return myForm;
	}
}

package xgxt.xszz.comm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.CommService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.xszz.XszzTyForm;

import common.Globals;
import common.XszzXmdm;

public class XszzCommPrint extends DispatchAction {

	//б��
	public static String slashes;
	
	static {
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			slashes = "/";
		} else {
			slashes = "\\";
		}
	}
	
	/**
	 * ѧ������_��ӡ
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		// ѧУ����
		String xxdm = StandardOperation.getXxdm();
		// ��Ŀ����
		String xmdm = myForm.getSave_xmdm();
		myForm.setXmdm(xmdm);
		// ��Ŀ����
		String xmmc = service.getOneValue("xszz_zzxmb", "xmmc", "xmdm", xmdm);
		// ����ʱ��
		String sqsj = myForm.getSave_sqsj();
		myForm.setSqsj(sqsj);
		// ��Ŀ��
		String xmb = myForm.getXmb();
		// ѧ��
		String xh = myForm.getXh();
		if (Base.isNull(xh)) {
			xh = request.getParameter("save_xh");
			myForm.setXh(xh);
		}
		// ѧУ����
		String xxmc = Base.xxmc;
		// ��Ŀ���
		String xmlb = service.getOneValue("xszz_zzxmb", "xmlb", "xmdm", xmdm);
		// ��ת
		String forward = "/xszz/comm/print/";
		// ��ӡ������Ϣ
		HashMap<String, String> map = new HashMap<String, String>();
		// =================end==================

		// ================= ��Ŀ������Ϣ ==================
		String[] colList = service.getTableZd(xmb);
		HashMap<String, String> xmInfo = service.getXszzInfo(xmb,
				"xh||sqsj||xmdm", xh + sqsj + xmdm, colList);

		// ѧ��
		String xn = xmInfo.get("xn");
		myForm.setXn(xn);
		// ѧ��
		String xq = xmInfo.get("xq");
		myForm.setXq(xq);
		// ���
		String nd = xmInfo.get("nd");
		myForm.setNd(nd);

		map.put("xh", xh);
		map.put("xmlb", xmlb);
		map.put("xxmc", xxmc);
		map.put("xmmc", xmmc);

		// =================end==================

		map.putAll(xmInfo);

		// ================= ѧ��������Ϣ ==================
		getStuInfo(myForm, map);
		// =================end==================

		// ================= ��ͥ������� ==================
		getJtqkInfo(myForm, map, request);
		// =================end==================

		// ================= ��Ŀ������ ==================
		getXmxgInfo(myForm, map);
		// =================end==================

		// ================= ��Ŀ�����б�==================
		getXmfjqk(myForm, map, request);
		// =================end==================

		// ================= ������Ϣ==================
		getQtInfo(myForm, map);
		// =================end==================

		// ================= �ۺ����ʲ��� ==================
		getStuZcInfo(myForm, map);
		// =================end==================

		// ================= ��ȡ�������������ȼ� ==================
		getKnsInfo(myForm, map);
		// =================end==================

		// ================= ��ȡѧ���ɼ���� ==================
		getCjInfo(myForm, map, request);
		// =================end==================
		
		// ================= ѧ����������Ŀ��Ϣ==================
		getXszzxmInfo(myForm, map, request);
		// =================end==================
		
		// ================= �жϴ�ӡ���ֱ��� ==================
		map.put("xh", xh);
		map.put("xn", Base.currXn);
		map.put("xq", xq);
		map.put("nd", Base.currNd);
		

		
		// ��ȡ��ӦѧУjsp���ļ���
		HashMap<String, String> folderMap = Globals.getXszzFolderMap();
		String folder = folderMap.get(xxdm);
		folder = StringUtils.isNull(folder) ? "comm" : folder;
		forward += folder;

		// ������
		forward += "/" + xmb + ".jsp";

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();
		// ================= ����ʦ��ѧԺ���Ի� ==================
		if(Globals.XXDM_HZSFXY.equals(xxdm)){
			//����������yyyy-MM-dd����Ϊyyyy��MM��dd�ո�ʽ
			//��½����Ӣ��������ѧ��͡�ӭ�����ˡ���ѧ��
			if(XszzXmdm.XSZZ_HZSF_LHYY.equals(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equals(xmdm)){
				//SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdm = new SimpleDateFormat("yyyyMMdd");
				String xscsrq = map.get("csrq");
				if(xscsrq!=null && !"".equals(xscsrq)){
					Date date=sdm.parse(xscsrq);
					SimpleDateFormat sd = new SimpleDateFormat("yyyy��MM��dd��");
					String newcsrq = sd.format(date);
					map.put("csrq", newcsrq);
				}else{
					map.put("csrq", "    ��    ��    ��");
				}
			}
			//�����֤�ŷֽ�
			if(XszzXmdm.XSZZ_HZSF_GJZXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_GJLZJXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_GJJXJ.equals(xmdm)){
				String xssfzh = map.get("sfzh")==null || "".equals("sfzh")?"":map.get("sfzh");
				int sylen= 18-xssfzh.length();
				for (int i=0;i<xssfzh.length();i++){
					map.put("sfzh"+i, xssfzh.charAt(i)+"");
				}
				for (int i=0;i<sylen;i++){
					map.put("sfzh"+(xssfzh.length()+i), "");
				}
			}
			//���㼰���Ŀ��
			int jgkms = Integer.parseInt(map.get("yxkms")!=null?map.get("yxkms"):"0")+Integer.parseInt(map.get("lhkms")!=null?map.get("lhkms"):"0");
			map.put("jgkms", jgkms==0?null:jgkms+"");
			//�����ظ���ѧ�����ᰮ����ѧ���ж��ǳ������뻹���ٴ�����
			if(XszzXmdm.XSZZ_HZSF_YXTGZXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_SHAXZX.equals(xmdm)){
				List<HashMap<String,String>> sqList =service.getSfccsq(xh,xmdm);
				String ysq = request.getParameter("ysq");//�Ƿ��޸�
				if("yes".equals(ysq)){//������޸ģ��жϲ�������������
					if(null!=sqList && sqList.size()>1){
						map.put("sfzcsq", "�ٴ�����");
					}else{
						map.put("sfzcsq", "��������");
					}
				}else{//��������޸ģ��жϰ�����������
					if(null!=sqList && sqList.size()>0){
						map.put("sfzcsq", "�ٴ�����");
					}else{
						map.put("sfzcsq", "��������");
					}
				}
			}
		}
		// =================����ʦ��ѧԺ���Ի�end==================
		rForm.setRs(map);

		service.setRequestValue(rForm, request);

		// ��ȡ����ֵ
		setSpecialValue(myForm, request);
		// ===================end ====================

		// =================���ⱨ��·������ ====================
		forward = getSpecialForward(myForm, map, forward);
		// ===================end ====================
		
		

		// ======================2010.9.19 ����=================
		// ����·��
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		File file = new File(pro_path + forward);
		if (!file.exists()) {
			// �ļ������� ��ת��Ĭ���ļ����µĶ�Ӧ�ļ�
			forward = "/xszz/comm/print/comm/" + xmb + ".jsp";
		}
		// ======================end 2010.9.19 ����=================
		return new ActionForward(forward, false);
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param myForm
	 * @param xmb
	 * @param xh
	 * @param map
	 */
	public  void getStuInfo(XszzTyForm myForm, HashMap<String, String> map) {

		XsxxglService stuService = new XsxxglService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ѧ��
		String xh = myForm.getXh();

		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		// ����ѧԺ
		if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// ������ѧ����
			if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {
				String rxrq = stuInfo.get("rxrq");
				if (!Base.isNull(rxrq) && rxrq.length() > 4) {
					rxrq = rxrq.substring(0, 4);
					stuInfo.put("rxrq", rxrq);
				}
			}
		}

		map.putAll(stuInfo);
	}

	/**
	 * ��ü�ͥ���
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getJtqkInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = StandardOperation.getXxdm();
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ��Ŀ����
		String xmmc = map.get("xmmc");

		// ��ͥ�����Ϣ
		HashMap<String, String> jtInfo = service.getJtqkInfo(myForm);
		map.putAll(jtInfo);

		List<HashMap<String, String>> cyList = service.getJtgxList(myForm);
		//�ݴ�
		List<HashMap<String, String>> xscyList = new ArrayList<HashMap<String,String>>();

		// ��Ա��Ŀ
		int cyNum = (cyList != null && cyList.size() > 0) ? cyList.size() : 0;

		// ����ѧԺ
		if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// ��ͥ�������
			if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {

				// ������Ŀ
				int bkNum = cyNum > 6 ? 0 : 6 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					cyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
		}else  if (Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)) {//�ɶ�����ѧԺ
			
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {
			
				int zhs=5;
				cyList=getSpaceList(cyList, zhs);
				request.setAttribute("cyNum", String.valueOf(cyNum));
			}else if(XszzXmdm.XSZZ_TCZXJ.equalsIgnoreCase(xmmc)){
				int zhs=3;
				cyList=getSpaceList(cyList, zhs);
				cyNum=cyList.size();
				request.setAttribute("cyNum", String.valueOf(cyNum));
			}
				
		}
		
		// ����ʦ��ѧԺ
		else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {
			//  ������ѧ��-��ͥ�������
			if (XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)) {
				//ֻȡ5����ͥ��Ա��Ϣ
				if(cyNum > 5){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=5;
				}else{
					xscyList.addAll(cyList);
				}
				// ������Ŀ
				int bkNum = cyNum > 5 ? 0 : 5 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
			// ��ͥ�������
			if (XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)) {
				//ֻȡ6����ͥ��Ա��Ϣ
				if(cyNum > 6){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=6;
				}else{
					xscyList.addAll(cyList);
				}
				// ������Ŀ
				int bkNum = cyNum > 6 ? 0 : 6 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
			// �й��������к����з�����ѧ��������������
			if (XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)) {
				//ֻȡ4����ͥ��Ա��Ϣ
				if(cyNum > 4){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=4;
				}else{
					xscyList.addAll(cyList);
				}
				// ������Ŀ
				int bkNum = cyNum > 4 ? 0 : 4 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
		}
		
		request.setAttribute("cyNum", String.valueOf(cyNum));
		// ����ʦ��ѧԺ
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else{
			request.setAttribute("cyList", cyList);
		}
		
	}
	
	/**
	 * 
	 * @param cyList
	 * @param zhs
	 * @return
	 */
	public List<HashMap<String,String>>getSpaceList(List<HashMap<String,String>>cyList,int zhs){
		
		// ��ͥ��Ա��
		int cyNum=cyList.size();
		// ��������
		int bkNum = cyNum > zhs ? 0 :zhs - cyNum;
		for (int i = 0; i < bkNum; i++) {
			HashMap<String, String> space = new HashMap<String, String>();
			cyList.add(space);
		}
		
		return cyList;
	}
	

	/**
	 * �����Ŀ�����Ϣ
	 * 
	 * @param myForm
	 * @param map
	 * @throws Exception
	 */
	private void getXmxgInfo(XszzTyForm myForm, HashMap<String, String> map)
			throws Exception {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();

		// �Ǽ�ͥ��������������
		if (!XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)
				&& !XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

			HashMap<String, String> xmxgInfo = service.getXmInfo(myForm);

			// ����ѧԺ
			if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
				// ������ѧ����
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

					// ����Сдת��
					String zje = xmxgInfo.get("xmzzje");
					String xnje = xmxgInfo.get("xnje");

					zje = MoneyFormat.format(zje);
					xnje = MoneyFormat.format(xnje);

					xmxgInfo.put("zje", zje);
					xmxgInfo.put("xnje", xnje);

				}
			}

			map.putAll(xmxgInfo);
		}
	}

	/**
	 * �����Ŀ�ּ����
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getXmfjqk(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// ��Ŀ�ּ�����б�
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);

		int fjNum = (xmfjqkList != null && xmfjqkList.size() > 0) ? xmfjqkList
				.size() : 0;

		request.setAttribute("xmfjqkList", xmfjqkList);
		request.setAttribute("fjNum", String.valueOf(fjNum));
	}

	/**
	 * ���ѧ���۲�ɼ�
	 * 
	 * @param myForm
	 * @param map
	 */
	private void getStuZcInfo(XszzTyForm myForm, HashMap<String, String> map) {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ѧ��
		String xh = myForm.getXh();
		// ѧ��
		String xn = map.get("xn");

		// ����ѧԺ
		if (Globals.XXDM_MJXY.equals(xxdm)) {
			// ������־��ѧ��
			if (XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			}
		}
		//����ʦ��ѧԺ
		if (Globals.XXDM_HZSFXY.equals(xxdm)) {
			// ������־��ѧ��
			if (XszzXmdm.XSZZ_HZSF_GJLZJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				//�ɼ���Ϣ������
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			//���ҽ�ѧ��
			}else if (XszzXmdm.XSZZ_HZSF_GJJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				//�ɼ���Ϣ������
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			}
		}
	}

	/**
	 * �����������Ϣ
	 * 
	 * @param myForm
	 * @param map
	 */
	private void getKnsInfo(XszzTyForm myForm, HashMap<String, String> map) {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ѧ��
		String xh = myForm.getXh();
		// ѧ��
		String xn = map.get("xn");
		if("".equals(xn)){
			xn=Base.currXn;
		}
		// ѧ��
		String xq = map.get("xq");

		// ����������Ŀ
		if (!Base.isNull(xh) && !Base.isNull(xn)) {

			myForm.setPkValue(XszzXmdm.XSZZ_KNS);

			HashMap<String, String> rsKns = service.getXmxgInfo(myForm);

			String shjb = rsKns.get("shjb");
			String sqzq = rsKns.get("sqzq");

			String knjb = service.getKnjbForXh(shjb, sqzq, xn, xh, xq);

			map.put("knjb", knjb);
		}
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param myForm
	 * @param map
	 * @throws Exception
	 */
	private void getQtInfo(XszzTyForm myForm, HashMap<String, String> map)
			throws Exception {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ѧ��
		String xh = myForm.getXh();
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");

		// �й��ش�
		//if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {

			HashMap<String, String> qtInfo = service.getQtInfo(myForm);

			// ��������
			String yxms = qtInfo.get("yxms");
			yxms = Base.isNull(yxms) ? "0" : yxms;
			// ��������
			String lhms = qtInfo.get("lhms");
			lhms = Base.isNull(lhms) ? "0" : lhms;
			// ������������
			String jgysms = String.valueOf(Integer.parseInt(yxms)
					+ Integer.parseInt(lhms));
			// �༶����
			String bjrs = service.getBjrs(map);

			map.put("bjrs", bjrs);
			map.put("jgysms", jgysms);
			qtInfo.put("xh", xh);
			qtInfo.put("xn", xn);
			map.putAll(qtInfo);
		//}

	}
	
	/**
	 * ��óɼ������Ϣ
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getCjInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// ѧУ����
		String xxdm = StandardOperation.getXxdm();
		// ѧ��
		String xh = myForm.getXh();
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ��Ŀ��
		String xmb = myForm.getXmb();

		// ��������
		if (Globals.XXDM_NBCSZYJSXY.equals(xxdm)) {
			// ������ѧ����,ѧ�Ѽ���
			if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_XFJM.equalsIgnoreCase(xmdm)) {
				// �������Ŀ����
				request.setAttribute("bjgkm", service.getBjgs(xh));
				// ����Υ�ʹ���
				request.setAttribute("sfwj", service.isWj(myForm));
				// ��ѧ��³�ɼ�
				request.setAttribute("dycj", service.sxnDyf(myForm));
			}
		}

		// ���ݷ�خ�������϶�����һѧ�겻����ɼ���
		if (Globals.XXDM_GZFYZYJSXY.equals(xxdm)
				&& XszzXmdm.XSZZ_KNS.equals(xmdm)) {
			List<HashMap<String, String>> bjgcjList = service.getBjgcjByXn(
					Base.beforXn, xh);
			request.setAttribute("bjgcjList", bjgcjList);
		}

		// ====================���� made by ����JJ========================
		// ѧ���ɼ���Ϣ
		List<HashMap<String, String>> cjList = service.getXscjList(xxdm, xmb,
				myForm);

		int cjNum = 0;
		if (cjList != null && cjList.size() > 0) {
			cjNum = 0 == cjList.size() % 3 ? cjList.size() / 3
					: cjList.size() / 3 + 1;
		}
		request.setAttribute("cjList", cjList);
		request.setAttribute("cjNum", cjNum);

		// ѧϰ�ɼ����۲���Ϣ
		map = service.getXxcjZcxx(xxdm, xmb, map);

		// ====================endJ========================

	}

	/**
	 * �������������Ŀ��·��
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getSpecialForward(XszzTyForm myForm,
			HashMap<String, String> map, String forward) throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ��Ŀ���
		String xmlb = map.get("xmlb");
		// ��Ŀ����
		String xmmc = map.get("xmmc");
		
		// ���ϴ�ѧ������Ŀ
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_HKJXJ.equalsIgnoreCase(xmdm)) {// �ο���ѧ��
				forward = "/xszz/comm/print/hndx/hkjxj.jsp";
			} else if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) {// ������չ���𣨽�����ѧ��)
				forward = "/xszz/comm/print/hndx/jyfzjj.jsp";
			} else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {// �к�����
				forward = "/xszz/comm/print/hndx/zhydzxj.jsp";
			}
		}

		// �㽭�Ƽ�ѧԺ
		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) { // ��ά˹��ѧ��
				forward = "/xszz/comm/print/zjkj/zwszxj.jsp";
			}
		}

		// �й��ش�
		else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {// ��ͥ�������
				forward = "/xszz/comm/print/comm/jtqkdcb.jsp";
			} else if (XszzXmdm.XSZZ_GJJXJ.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)) {// ���ҽ�ѧ��,������ѧ�𣬹�����־��ѧ��
				forward = "/xszz/comm/print/zgdd/gj.jsp";
			} else if ("��ѧ��".equalsIgnoreCase(xmlb)) {// ��Ŀ���Ϊ��ѧ�����Ŀ
				forward = "/xszz/comm/print/zgdd/jxj.jsp";
			} else if ("��ѧ��".equalsIgnoreCase(xmlb)) {// ��Ŀ���Ϊ��ѧ�����Ŀ
				forward = "/xszz/comm/print/zgdd/zxj.jsp";
			} else {// ����
				forward = "/xszz/comm/print/zgdd/zxj.jsp";
			}
		} else if (Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {// ���ҵ��ѧ
			if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) { // ��ά˹��ѧ��
				forward = "/xszz/comm/print/tjgy/zwszxj.jsp";
			} else if (XszzXmdm.XSZZ_TJYDZXJ.equalsIgnoreCase(xmdm)) {// ����ƶ���ѧ����ѧ����
				forward = "/xszz/comm/print/tjgy/tjydzxj.jsp";
			} else if (XszzXmdm.XSZZ_TJRMZFJXJ.equalsIgnoreCase(xmdm)) {// ���������������ѧ��
				forward = "/xszz/comm/print/tjgy/tjrmzfjxj.jsp";
			} else if (XszzXmdm.XSZZ_TJHSZHBAZXJ.equalsIgnoreCase(xmdm)) {// ����к�ʮ�ֻᲩ����ѧ��
				forward = "/xszz/comm/print/tjgy/tjhszhbazxj.jsp";
			} else if (XszzXmdm.XSZZ_LDYZZJTJJKNXS.equalsIgnoreCase(xmdm)) {// �ϵ�Ա������ͥ��������ѧ��
				forward = "/xszz/comm/print/tjgy/ldyzzjtjjknxs.jsp";
			} else if (XszzXmdm.XSZZ_TJJYFZJJNSYHZXJ.equalsIgnoreCase(xmdm)) {// ����н�����չ���� ���ũ��������ѧ��
				forward = "/xszz/comm/print/tjgy/tjjyfzjjnsyhzxj.jsp";
			}
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){//�������ϴ�ѧ
			if (XszzXmdm.XSZZ_JTJJKNSRDB.equalsIgnoreCase(xmdm)) { // ��ͥ��������ѧ���϶������
				forward = "/xszz/comm/print/bjlh/jtjjknxsrd.jsp";
			}
		} else if (Globals.XXDM_ZJJSZYJSXY.equals(xxdm)){//�㽭����ְҵ����ѧԺ
			if (XszzXmdm.XSZZ_TSKNBZ.equals(xmdm)){//�������Ѳ���
				forward = "/xszz/comm/print/zjjs/tsknbz.jsp";
			}else if(XszzXmdm.XSZZ_ZTJTAXGYJ.equals(xmdm)){//���켯�Ű��Ĺ����
				forward = "/xszz/comm/print/zjjs/ztjtaxgyjj.jsp";
			}
		} else if (Globals.XXDM_NTHYZYJSXY.equals(xxdm)){
			if ("7001".equals(xmdm)){//�������Ѳ���
				forward = "/xszz/comm/print/nthy/lsknbz.jsp";
			}else if(XszzXmdm.XSZZ_XFJM.equals(xmdm)){//ѧ�Ѽ���
				forward = "/xszz/comm/print/nthy/xszz_xfjmb.jsp";
			}
		} else if(Globals.XXDM_GXLSXY.equalsIgnoreCase(xxdm)){//����´ɽѧԺ
			
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/xszz_gjjxjb.jsp";//���ҽ�ѧ��
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/gjlzjxj.jsp";//������־��ѧ��
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/gjzxj.jsp";//������ѧ���
			}else if(XszzXmdm.XSZZ_TJBB_GXLSXY_ZZQRMZFJXJB.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/zzqrmzfjxjb.jsp";//����������������ѧ�������
			}
			
		}else if(Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)){//�������ϴ�ѧ
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/xszz_gjjxjb.jsp";//���ҽ�ѧ��
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/gjlzjxj.jsp";//������־��ѧ��
			}else if(XszzXmdm.XSZZ_HZNY_XFHZXDKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/xfhzxdkdcsqb.jsp";//ѧ�Ѻ���ѧ������������
			}else if(XszzXmdm.XSZZ_JTQKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/jtqkdcb.jsp";//��ͥ��������
			}else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzny/xszz_knsb.jsp";// ��ͥ��������ѧ���϶������
			}
			
		} else if(Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)){//����ʦ��ѧԺ
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/xszz_gjjxjb.jsp";//���ҽ�ѧ��
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/gjlzjxj.jsp";//������־��ѧ��
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/gjzxj.jsp";//������ѧ��
			}else if(XszzXmdm.XSZZ_JTQKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/jtqkdcb.jsp";//��ͥ��������
			}else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsb.jsp";// ��ͥ��������ѧ���϶������
			}else if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_lhyy.jsp";//��½����Ӣ��������ѧ��
			}else if (XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_ynsr.jsp";//��ӭ�����ˡ���ѧ��
			}else if (XszzXmdm.XSZZ_HZSF_KNSMZPY.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsmzpyb.jsp";//��ͥ��������ѧ�����������
			}else if (XszzXmdm.XSZZ_HZSF_JJJZJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_jjjzj.jsp";//����������
			}else if (XszzXmdm.XSZZ_HZSF_YXTGZXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_szxssqb.jsp";//�����ظ���ѧ��
			}else if (XszzXmdm.XSZZ_HZSF_SHAXZX.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_szxssqb.jsp";//��ᰮ����ѧ
			}else if (XszzXmdm.XSZZ_HZSF_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsb.jsp";//��ͥ��������ѧ���϶������
			}else if (XszzXmdm.XSZZ_HZSF_BYSZXDK.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_byszxdkxxb.jsp";//��ҵ����ѧ����
			}
			
			
			else if (XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_zggszxdk.jsp";//�й��������к����з�����ѧ��������������
			}else if (XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/gjzxj.jsp";//������ѧ������������
			}else if (XszzXmdm.XSZZ_HZSF_GJLZJXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/gjlzjxj.jsp";//������־��ѧ������������
			}else if (XszzXmdm.XSZZ_HZSF_GJJXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_gjjxjb.jsp";//���ҽ�ѧ������������
			}else if (XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/jtqkdcb.jsp";//ѧ������ͥ��������
			}
			
			
			
		} else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)){//�ɶ�����ѧԺ
			//�����֤�ŷֽ�
			String xssfzh = map.get("sfzh");
			if(!Base.isNull(xssfzh)){
				int sylen= 18-xssfzh.length();
				for (int i=0;i<xssfzh.length();i++){
					map.put("sfzh"+i, xssfzh.charAt(i)+"");
				}
				for (int i=0;i<sylen;i++){
					map.put("sfzh"+(xssfzh.length()+i), "");
				}
			}
			//���㼰���Ŀ��
			int jgkms = Integer.parseInt(map.get("yxkms")!=null?map.get("yxkms"):"0")+Integer.parseInt(map.get("lhkms")!=null?map.get("lhkms"):"0");
			map.put("jgkms", jgkms==0?null:jgkms+"");
			
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/xszz_gjjxjb.jsp";//���ҽ�ѧ��
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/gjlzjxj.jsp";//������־��ѧ��
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/gjzxj.jsp";//������ѧ��
			}else if (XszzXmdm.XSZZ_TCZXJ.equalsIgnoreCase(xmmc)) { 
				forward = "/xszz/comm/print/cdty/tczxj.jsp";// �����ѧ��
			}
		}
		
		return forward;
	}

	/**
	 * ĳЩѧУ����Ŀ���һЩ���Ի�ֵ
	 * 
	 * @sjf
	 * @param myForm
	 * @param request
	 */
	private void setSpecialValue(XszzTyForm myForm, HttpServletRequest request) {
		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();

		if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) { // ������չ����
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// ���ϴ�ѧ
				String[] ids = new String[] { "szsheng", "szshi", "szxian",
						"xxsheng", "xxshi", "xxxian" };
				changeQx(request, ids);
			}
		} else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {// �к�����
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// ���ϴ�ѧ
				String[] ids = new String[] { "szsheng", "szshi", "szxian" };
				changeQx(request, ids);
			}
		} else if (XszzXmdm.XSZZ_XFHJ.equalsIgnoreCase(xmdm)) {// ѧ�ѻ���
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
				String[] ids = new String[] { "szsheng", "szshi", "szxian" };
				changeQx(request, ids);
			}
		}
	}

	/**
	 * ����ʡ����
	 * 
	 * @sjf
	 * @param request
	 * @param ids
	 */
	private void changeQx(HttpServletRequest request, String[] ids) {
		XszzCommService service = new XszzCommService();
		Map<String, String> rs = (Map<String, String>) request
				.getAttribute("rs");
		String[] dm = new String[ids.length];

		for (int i = 0; i < ids.length; i++) {
			dm[i] = rs.get(ids[i]);
		}

		List<HashMap<String, String>> list = service.getQxmc(dm, ids);

		for (Map<String, String> map : list) {
			request.setAttribute(map.get("id"), map.get("qxmc"));
		}
	}

	/**
	 * ���ݷ�خ����ͥ����������ѧ���϶���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printKnsrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzTyForm myForm = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();

		request.setAttribute("knsList", service.getKnsInfo(myForm.getXh()));
		return mapping.findForward("printKnsrd");
	}
	
	/**
	 * ѧ����������Ŀ���
	 * ����������Ŀ��������ѧ���������־��ѧ��ѧУ��ѧ��ȣ�
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 * @author honglin
	 * @2013-01-17
	 */
	private void getXszzxmInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {
		CommService service = new CommService();
		List<HashMap<String, String>> xszzList = service.getXszzList(myForm.getXh());
		List<HashMap<String, String>> zzList = new ArrayList<HashMap<String,String>>(); 
		// ѧУ����
		String xxdm = StandardOperation.getXxdm();
		
		int xszzNum = (xszzList != null && xszzList.size() > 0) ? xszzList.size() : 0;
		// ����ʦ��ѧԺ
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {
				// ������Ŀ
				int bkNum = xszzNum > 6 ? 0 : 6 - xszzNum;
				//ֻȡ6��������Ŀ��Ϣ
				if(xszzNum > 6){
					for (int i = 0; i < 6; i++) {
						zzList.add(xszzList.get(i));
					}
					xszzNum=6;
				}else{
					zzList.addAll(xszzList);
				}
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					zzList.add(space);
				}
				xszzNum += bkNum;
				request.setAttribute("xszzNum", String.valueOf(xszzNum));
				request.setAttribute("xszzbkNum", String.valueOf(bkNum));
				
		}
		request.setAttribute("xszzList", zzList);
	}
}

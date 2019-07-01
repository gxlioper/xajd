package xsgzgl.rcsw.zjbb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.db.GetSysData;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class RcswZjbbInit extends BasicService{
	
	RcswZjbbService service=new RcswZjbbService();
	/**
	 * ��ʼ��������У������Ϣ
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "rcsw";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("rcsw_zjbb_bbsz.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id","zjmc","lcmc","num"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);
		
		HashMap<String,String>valueMap=new HashMap<String,String>();
		valueMap.put("sqb", "xg_gygl_jqlxsqb");
		valueMap.put("shb", "xg_gygl_jqlxshb");
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		valueMap.put("ryb", "view_xsjbxx");
		model.setValueMap(valueMap);
		
		model.setSqbPk(new String[]{"id"});
		model.setShbPk(new String[]{"sqid"});
	
		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszSave(BasicModel model,
			HttpServletRequest request) {
		
	
		// �����ֶ�
		String []save={"lcid","zjmc"};
		
		// --------------����------------
		model.setTableName("xg_rcsw_zjbbszb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		model.setValueMap(valueMap);
	
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbszModi(BasicModel model,
			HttpServletRequest request) {
		
		String[]pk=new String[]{"id"};
		
		String pkValue=request.getParameter("id");
		// �����ֶ�
		String []save={"lcid","zjmc"};
		
		// --------------����------------
		model.setTableName("xg_rcsw_zjbbszb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);

		valueMap.put("id", pkValue);
		
		model.setValueMap(valueMap);
		
		model.setPk(pk);
		
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initBbsqSave(BasicModel model,
			HttpServletRequest request) {

		RcswZjbbForm myForm =new RcswZjbbForm();
		
		User user=model.getUser();
		
		String xmid=request.getParameter("xmid");
		
		myForm.setXmid(xmid);
		
		HashMap<String,String>zjbbMap=service.getBbszInfo(myForm);
		
		// �����ֶ�
		String []save={"id","xmid","xh","sqsj","sqly"};
		
		if("no".equalsIgnoreCase(zjbbMap.get("lcid"))){
			
			save=new String[]{"id","xmid","xh","sqsj","sqly","sqjg"};
		}
		
		// --------------����------------
		model.setTableName("xg_rcsw_zjbbsqb");
		// --------------��Ҫ�����ֵ--------------------		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		valueMap.put("id", GetSysData.getGuid());
		
		valueMap.put("sqsj", GetTime.getNowTime2());
		
		valueMap.put("xh", user.getUserName());
		
		valueMap.put("sqjg", "wsh");
		
		model.setValueMap(valueMap);
		
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initZjbbSh(BasicModel model,
			HttpServletRequest request) {
		
		User user=model.getUser();
		// --------------��Ҫ�����ֵ--------------------		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		valueMap.put("xmb", "xg_rcsw_zjbbszb");
		
		model.setValueMap(valueMap);
		
	}
	
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBbsqSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "rcsw";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("rcsw_zjbb_bbsq.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id","zjmc","shzt","shyj","sqsj"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBbshSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "rcsw";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("rcsw_zjbb_bbsh.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id", "xh", "xm", "nj","bjmc","sqsj", "shzt"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);
		
		HashMap<String,String>valueMap=new HashMap<String,String>();
		valueMap.put("sqb", "xg_gygl_jqlxsqb");
		valueMap.put("shb", "xg_gygl_jqlxshb");
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		valueMap.put("ryb", "view_xsjbxx");
		model.setValueMap(valueMap);
		
		model.setSqbPk(new String[]{"id"});
		model.setShbPk(new String[]{"sqid"});
	
		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	

	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBbjgSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "rcsw";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("rcsw_zjbb_bbjg.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id", "xh", "xm", "nj","bjmc","sqsj",
				"zjmc","sqjg" }; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);
		
		HashMap<String,String>valueMap=new HashMap<String,String>();
		valueMap.put("sqb", "xg_gygl_jqlxsqb");
		valueMap.put("shb", "xg_gygl_jqlxshb");
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		valueMap.put("ryb", "view_xsjbxx");
		model.setValueMap(valueMap);
		
		model.setSqbPk(new String[]{"id"});
		model.setShbPk(new String[]{"sqid"});
	
		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * ����ģ��·����ȡ��ͷ��Ϣ
	 * @param colList
	 * @param path
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String[] colList,User user, String path) {

		DAO dao=DAO.getInstance();
		
		String[] en = colList;
		
		String[] cn = null;
		
		String userType=user.getUserType();
		
		// --------------------��ѯʦ���� --------------------------
		if ("rcsw_zjbb_bbsz.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","֤������","�������","δ�����ɼ�¼��"}; 
		
		}else if("rcsw_zjbb_bbsq.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"","֤������","��˽���", "������" ,"����ʱ��"};  //����������
		}else if("rcsw_zjbb_bbsh.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "ѧ��", "����", "�꼶","�༶","����ʱ��", "���״̬"}; 
		}else if("rcsw_zjbb_bbjg.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "ѧ��", "����", "�꼶","�༶","����ʱ��",
					"����֤��","������"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}

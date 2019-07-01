package xsgzgl.gygl.jqlx;

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

public class GyglJqlxInit extends BasicService{
	
	GyglJqlxService service = new GyglJqlxService();
	/**
	 * ��ʼ��������У������Ϣ
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initLxszManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {
		
		XtwhShlcService shlcService =new XtwhShlcService();
		
		// ----------���ñ�ֻ����һ����¼ ���޸����ű� begin----------------
		// ����
		model.setPk(new String[]{"1"});
		// ����ֵ
		model.setPkValue(new String[]{"1"});
		// ----------���ñ�ֻ����һ����¼ ���޸����ű� end----------------
		
		// ����
		model.setTableName("xg_gygl_jqlxszb");
		
		// �������
		List<HashMap<String,String>>shlc=shlcService.getShlcByDjlx("gygl");
		
		request.setAttribute("shlcList", shlc);
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initLxszSave(BasicModel model,
			HttpServletRequest request) {
		
		// ����
		String [] pk={"1"};
		// ����
		String [] pkValue={"1"};
		
		// �����ֶ�
		String []save={"lcid","sqkg"};
		
		// --------------����------------
		model.setTableName("xg_gygl_jqlxszb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		model.setValueMap(valueMap);
		
		model.setPk(pk);
		
		model.setPkValue(pkValue);
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 * @throws Exception 
	 */
	public void initLxsqSave(BasicModel model,
			HttpServletRequest request) throws Exception {
		GyglJqlxForm myForm=new GyglJqlxForm();
		
		User user=model.getUser();
		// �����ֶ�
		String []save={"sqsj","kssj","jssj","sqly","lcid","sqjg"};
		
		// --------------����------------
		model.setTableName("xg_gygl_jqlxsqb");
		// --------------��Ҫ�����ֵ--------------------		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		valueMap.put("id", GetSysData.getGuid());
		
		valueMap.put("sqsj", GetTime.getNowTime2());
		
		valueMap.put("xh", user.getUserName());
		
		service.setJbszInfo(myForm);
		
		if("no".equalsIgnoreCase(myForm.getLcid())){
			
			valueMap.put("sqjg", "wxsh");
		}else {
		
			valueMap.put("sqjg", "wsh");
		}
		
		model.setValueMap(valueMap);
		
	}
	
	/**
	 * ��ʼ��������У���ñ���
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 */
	public void initJqlxSh(BasicModel model,
			HttpServletRequest request) {
		
		User user=model.getUser();
		// --------------��Ҫ�����ֵ--------------------		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		valueMap.put("xmb", "xg_gygl_jqlxszb");
		
		model.setValueMap(valueMap);
		
	}
	
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initLxsqSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("jqlx_lxsq.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id","sqsj","shzt","kssj","jssj"}; 
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
	public void initLxshSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("jqlx_lxsh.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id","xh","xm","nj","bjmc","sqsj","shzt"}; 
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
	public void initLxjgSearch(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("jqlx_lxjg.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList = {"id", "xh", "xm", "nj","bjmc", "yjzxsj",
				"sqsj", "sqjg" }; 
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
		if ("jqlx_lxsq.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","����ʱ��","Ŀǰ����", "Ԥ�ƿ�ʼʱ��",  "Ԥ�ƽ���ʱ��"}; 
		
		}else if("jqlx_lxsh.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"","ѧ��","����","�꼶","�༶","����","���״̬"}; 
		}else if("jqlx_lxjg.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"", "ѧ��", "����", "�꼶","�༶", "Ԥ��סУʱ��",
					"����ʱ��", "������"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}

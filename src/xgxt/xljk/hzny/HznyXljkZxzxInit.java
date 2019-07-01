package xgxt.xljk.hzny;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xgxt.xszz.whtl.ybbx.XszzYbbxService;
import xsgzgl.comm.BasicModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 ����04:34:46</p>
 */
public class HznyXljkZxzxInit {

	HznyXljkZxzxService service =new HznyXljkZxzxService();
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsSave(BasicModel basicModel,
			HttpServletRequest request) {

		// �����ֶ�
		String []save={"zgh","zxszg","jj","bz"};
		
		// --------------����------------
		basicModel.setTableName("xg_xljk_zxsxxb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxsModi(BasicModel basicModel,
			HttpServletRequest request) {

		XszzYbbxService service = new XszzYbbxService();

		String []save={"jj","zxszg","bz","zgh"};
		
		String []pk={"zgh"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_zxsxxb");
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsSave(BasicModel basicModel,
			HttpServletRequest request,User user) {

		// ��Ҫ������ֶ�
		String []save={"xh","tbgxxslb","sfzy","xswjbx","tbgxcs",
				"xyclgc","xsdqzk","bz","sbr","sbsj","sbrlxdh"};
	
		String userName=user.getUserName();
		
		// --------------����------------
		basicModel.setTableName("xg_xljk_tsxsxxb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		valueMap.put("sbr", userName);
		
		valueMap.put("sbsj", GetTime.getNowTime2());
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initTsxsModi(BasicModel basicModel,
			HttpServletRequest request) {

		XszzYbbxService service = new XszzYbbxService();

		String []save={"xh","tbgxxslb","sfzy","xswjbx","tbgxcs","xyclgc","xsdqzk","bz","sbrlxdh"};
		
		String []pk={"xh"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_tsxsxxb");
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}

	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxglManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
				.parseInt(showNum) + 1)};

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
	 * ������ ������ѯ��ѧ�����ʣ�
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxSave(BasicModel basicModel,
			HttpServletRequest request) {
	
		// ��Ҫ������ֶ�
		String []save=null;
		
		save=new String[]{"xh","zxwt"};
		
		
		// --------------����------------
		basicModel.setTableName("xg_xljk_zxzxb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxzxModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String userName=user.getUserName();
	
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// ѧ��ֻ���޸���ѯ��������Ϣ����ʦ�ظ��������޸ģ�
		if("stu".equalsIgnoreCase(userType)){
			
			save=new String[]{"guid","xh","zxwt"};
	
		}else {//��ʦ�ظ�����
			
			save=new String[]{"guid","xh","zgh","hfnr"};

		}
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_zxzxb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		if(!"stu".equalsIgnoreCase(userType)){//��ʦ�ظ�����
			
			valueMap.put("zgh", userName);
			
			valueMap.put("hfsj", GetTime.getNowTime2());
		}
		
		basicModel.setValueMap(valueMap);
	}
	
	
	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
	 * ������ ������ѯ��ѧ�����ʣ�
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxSave(BasicModel basicModel,
			HttpServletRequest request,User user) {
	
		// ��Ҫ������ֶ�
		String []save=null;
		
		save=new String[]{"xh","zgh","zxsj","zxnrjyj"};
		
		// --------------����------------
		basicModel.setTableName("xg_xljk_xszxfkb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		valueMap.put("xh", user.getUserName());
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXszxModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// ѧ��ֻ���޸���ѯ��������Ϣ����ʦ�ظ��������޸ģ�
		if("stu".equalsIgnoreCase(userType)){
			
			save=new String[]{"guid","zxnrjyj","zxsj"};
	
		}else {//��ʦ�ظ�����
			
			save=new String[]{"guid","zxsfk","fksj"};

		}
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_xszxfkb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		if(!"stu".equalsIgnoreCase(userType)){//��ʦ�ظ�����
			
			valueMap.put("fksj", GetTime.getNowTime2());
		}
		
		basicModel.setValueMap(valueMap);
	}
	

	/**
	 * ��ѯʦ�����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xljk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
	 * ������ ������ѯ��ѧ�����ʣ�
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlSave(BasicModel basicModel,
			HttpServletRequest request,User user) {
	
		// ��Ҫ������ֶ�
		String []save=null;
		
		save=new String[]{"xh","zgh","zxsj","zxnr","zxshf"};
		
		// --------------����------------
		basicModel.setTableName("xg_xljk_xlzxjlb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		valueMap.put("zgh", user.getUserName());
		
		basicModel.setValueMap(valueMap);
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxjlModi(BasicModel basicModel,
			HttpServletRequest request,User user) {

		String userType=user.getUserType();
		
		String []save=null;
		
		HashMap<String,String>valueMap= new HashMap<String,String>();
		
		// ѧ��ֻ���޸���ѯ��������Ϣ����ʦ�ظ��������޸ģ�
		
		save=new String[]{"guid","zxsj","zxnr","zxshf"};
		
		String []pk={"guid"};
	
		basicModel.setPk(pk);
	
		basicModel.setTableName("xg_xljk_xlzxjlb");
		
		valueMap.putAll(service.getValueMap(request, save));
		
		basicModel.setValueMap(valueMap);
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
		if ("hn_xljk_zxsgl.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","ְ����","����", "�Ա�",  "����","��ѯʦ�ʸ�"}; 
		
		}else if ("hn_xljk_tsxsgl.do".equalsIgnoreCase(path)) {
		
			cn= new String[] {"","ѧ��","����","�꼶","ѧԺ","רҵ","�༶"}; 
		
		}else if ("hn_xljk_zxzx.do".equalsIgnoreCase(path)) {
		
			if("stu".equalsIgnoreCase(userType)){
				
				cn= new String[] {"","ѧ��","����", "��ѯʱ��", "��ѯ����","�Ƿ�ظ�"};
			}else {
				
				cn= new String[] {"","ѧ��","����","�꼶","�༶", "��ѯʱ��","��ѯ����", "�Ƿ�ظ�","�Ƿ�����ѧ��"};
			}
		
		}else if ("hn_xljk_xszx.do".equalsIgnoreCase(path)) {
		
			
			cn= new String[] {"","��ѯʦ","��ѯʱ��", "��ѯ���ݼ����", "�Ƿ�ظ�","�Ƿ�������"};
			
		
		}else if("hn_xljk_xszx.do?searchType=zxs".equalsIgnoreCase(path)){
			
			cn= new String[] {"","��ѯʦ","��ѯʱ��","��ѯ���ݼ����","�Ƿ�ظ�", "�Ƿ���Ա������"};
			
		}else if("hn_xljk_xszx.do?searchType=admin".equalsIgnoreCase(path)){
			
			cn= new String[] {"","��ѯʦְ����","��ѯʦ����","��ѯ��ѧ��","��ѯ������","��ѯʱ��", "�Ƿ���"};
			
		}else if ("hn_xljk_zxjl.do".equalsIgnoreCase(path)) {
		
			if("stu".equalsIgnoreCase(userType)){
				
				cn= new String[] {"","ѧ��","����","�꼶","�༶","��ѯʱ��"};
			
			}else{
				
				cn= new String[] {"","ѧ��","����","�꼶","�༶","��ѯʱ��", "�Ƿ�����ѧ��"};
				
			}
		}

		return dao.arrayToList(en, cn);
	}
	
}

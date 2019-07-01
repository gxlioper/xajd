package xgxt.xszz.whtl.ybbx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicModel;

public class XszzYbbxInit {
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initYbbxcx(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "pjpy";
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
	public void initYbbxsh(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user) {

		// ����ģ��
		String gnmk = "xszz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList =new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh"};
		
		String userType=user.getUserType();
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			colList =new String[]{"pkValue","xh","xm", "nj",  "bjmc","xxsh"};
		}
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
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDgshInfo(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		// �����ֶ�
		String []save={"xn","xh"};
		// �����ֶ�
		String []pk={"xn","xh"};
		// ���������ֶ�
		String []oneZd={"xn","xh","czr","czsj"};
		// ���������ֶ�
		String []arrayZd={"bxje","mzyy","wzsj","ylyt","shje"};
		
		basicModel.setPk(pk);
	
		service.getModelValue(saveModel, request);
		
		// ---------------------�̶��ı���ֵ begin------------
		saveModel.setCzr(user.getUserName());
		
		saveModel.setCzsj(GetTime.getNowTime2());
	
		saveModel.setXn(Base.currXn);
		
		// ---------------------�̶��ı���ֵ end--------------
		basicModel.setPkValue(saveModel.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------����------------
		basicModel.setTableName("xg_xszz_ybbxxxb");
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
	public void initShInfo(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		basicModel.setPk(new String[]{"xn","xh"});
		
		String[]update=null;
		
		String userType=user.getUserType();
		if("xy".equalsIgnoreCase(userType)){
			
			update=new String[]{"xn","xh","xysh","xyshyj"};
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			update=new String[]{"xn","xh","xxsh","xxshyj"};
			
		}
		
		HashMap<String,String>valueMap=service.getValueMap(request, update);
		
		// ---------------------�̶��ı���ֵ begin------------
		if("xy".equalsIgnoreCase(userType)){
			
			valueMap.put("xyshsj", GetTime.getNowTime2());
			
			valueMap.put("xyshr", user.getUserName());
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			valueMap.put("xxshsj", GetTime.getNowTime2());
			
			valueMap.put("xxshr", user.getUserName());
			
			if("�˻�".equalsIgnoreCase(service.unicode2Gbk(valueMap.get("xxsh")))){
				valueMap.put("xysh","������");
				update=new String[]{"xn","xh","xysh","xxsh","xxshyj"};
			}
			
		}
		
		basicModel.setValueMap(valueMap);
		
		basicModel.setTableName("xg_xszz_ybbxsqb");
	}
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initPlsh(XszzYbbxSaveModel saveModel, BasicModel basicModel,
			HttpServletRequest request,User user) {

		XszzYbbxService service = new XszzYbbxService();

		basicModel.setPk(new String[]{"xn","xh"});
		
		service.getModelValue(basicModel, request);
		
		String[]update=null;
		
		String userType=user.getUserType();
		
		if("xy".equalsIgnoreCase(userType)){
			
			update=new String[]{"xysh","xyshyj"};
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			update=new String[]{"xxsh","xxshyj"};
			
		}
		
		HashMap<String,String>valueMap=service.getValueMap(request, update);
		
		// ---------------------�̶��ı���ֵ begin------------
		if("xy".equalsIgnoreCase(userType)){
			
			valueMap.put("xyshsj", GetTime.getNowTime2());
			
			valueMap.put("xyshr", user.getUserName());
			
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
			valueMap.put("xxshsj", GetTime.getNowTime2());
			
			valueMap.put("xxshr", user.getUserName());
			
			if("�˻�".equalsIgnoreCase(service.unicode2Gbk(valueMap.get("xxsh")))){
				valueMap.put("xysh","������");
			}
			
		}
		
		basicModel.setValueMap(valueMap);
		
		basicModel.setTableName("xg_xszz_ybbxsqb");
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
		
		if ("xszz_ybbx_cx.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"","ѧ��","����", "�꼶",  "�༶","ѧԺ���","ѧУ���"}; 
		
		}else if("xszz_ybbx_sh.do".equalsIgnoreCase(path)) {
			
			if("xy".equalsIgnoreCase(userType)){
			
				cn= new String[] {"","ѧ��","����", "�꼶",  "�༶","ѧԺ���"}; 
			}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			
				cn= new String[] {"","ѧ��","����", "�꼶",  "�༶","ѧУ���"}; 
			}
		}
		return dao.arrayToList(en, cn);
	}
}

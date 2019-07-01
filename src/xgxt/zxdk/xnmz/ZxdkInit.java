package xgxt.zxdk.xnmz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;

public class ZxdkInit {
	
	/**
	 * ��ѧ�����ѯ��Ϣ��ʼ
	 * @param request
	 * @author ������
	 * 
	 */
	public void initZxdkCx(RequestForm rForm, ZxdkForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "zxdk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zxdk_xnmz.do?method=zxdkcxManage";
		// ����ֶ�
		String[] colList = new String[] {"dis","pkValue","xh","xm","xb","nj","xymc","zymc","bjmc","shzt" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "cx");
		// �Ƿ��ѯ����(Ĭ�ϲ�ѯ)
		String search ="true";

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
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ���������Ϣ��ʼ
	 * @param request
	 * @author ������
	 * 
	 */
	public void initZxdkSh(RequestForm rForm, ZxdkForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "zxdk";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zxdk_xnmz.do?method=zxdkshManage";
		// ����ֶ�
		String[] colList = new String[] {"disabled","pkValue", "xh","xm","xb","nj","xymc","zymc","bjmc","shzt" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "sh");
		// �Ƿ��ѯ����(Ĭ�ϲ�ѯ)
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
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ����ģ�����ͻ�ȡ��ͷ
	 * @param lx
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getTopTr(String[]colList,String lx){
		
		DAO dao=DAO.getInstance();
		String[] cn = null;
		
		if("cx".equalsIgnoreCase(lx)){
			
			cn=new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","���״̬"};
		}else if("sh".equalsIgnoreCase(lx)){
			cn=new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","���״̬"};
		}
		
		return dao.arrayToList(colList, cn);
		
	}

}

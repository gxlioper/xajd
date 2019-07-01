package xgxt.xtwh.zpgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.zxdk.xnmz.ZxdkForm;

public class XtwhZpglInit {
	/**
	 * ��ѧ���������Ϣ��ʼ
	 * @param request
	 * @author ������
	 * 
	 */
	public void initXszpInfo(RequestForm rForm, XtwhZpglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xtwh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwhZpgl.do?method=zpglManage";
		// ����ֶ�
		String[] colList = new String[] {"xh","xm","sfzh","ksh","nj","xymc","zymc","bjmc","sfdrzp","sfdrxszp" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "xszp");
		// �Ƿ��ѯ����(Ĭ�ϲ�ѯ)
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
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
		
		//ѧ����Ƭ
		if("xszp".equalsIgnoreCase(lx)){
			
			cn=new String[]{"ѧ��","����","���֤��","������","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","������Ƭ״̬","������Ƭ״̬"};
		//��ʦ��Ƭ
		}else if("sh".equalsIgnoreCase(lx)){
			cn=new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","���״̬"};
		}
		
		return dao.arrayToList(colList, cn);
		
	}
}

package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class PjpyQtxxInit extends BasicService{
	
	/**
	 * ��ʼ�������ѯ��Ϣ
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initQtxxManage(RequestForm rForm,
			HttpServletRequest request) {
		
		DAO dao=DAO.getInstance();
		// ����ģ��
		String gnmk = "pjpy";
		// ����·��
		String path ="pjpy_qtxx_qtjl.do";
		
		// ========================����ֶ� begin=========================	
		// �ֶ���
		String[]en=new String[]{"pkValue","xn","xh","xm","nj","bjmc","hjjls"};
		// ������
		String[]cn=new String[]{"","ѧ��","ѧ��","����","�꼶","�༶","����"};
		// ��ͷ
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================����ֶ� end=========================

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

		// ===============ͨ������ end ============
		
		rForm.setQtzdz(new String[]{});//������������

		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * ������Ϣ��ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSaveBatch(BasicModel basicModel,
			QtxxSaveModel saveModel,
			HttpServletRequest request) {
		
		// �����ֶ�
		String []save={"xn","xh","jlnr"};
		// �����ֶ�
		String []pk={"xn","xh"};
		// ���������ֶ�
		String []oneZd={"xn","xh"};
		// ���������ֶ�
		String []arrayZd={"jlnr"};
		
		basicModel.setPk(pk);
	
		getModelValue(saveModel, request);
		
		basicModel.setPkValue(saveModel.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------����------------
		basicModel.setTableName("xg_pjpy_qtjlb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
	}
}

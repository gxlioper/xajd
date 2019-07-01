package xgxt.xsxx.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.dao.XsxxXjydglDAO;
import xgxt.xsxx.dao.XxcshglDAO;
import xgxt.xsxx.form.XsxxcshForm;
import xgxt.xtwh.common.service.XtlcglService;


public class XxcshglService {
	XxcshglDAO dao = new XxcshglDAO();
	
	/**
	 * ��ȡҪ��ʼ�����ݵ�ģ��
	 * */
	public List<HashMap<String, String>> getInitDataModule(){
		String[] mkmc = {"������Ϣ", "רҵ��Ϣ", "�༶��Ϣ", "ѧ����Ϣ"};
		String[] dyym = {"xxcshgl.do?method=xxbmcsh", "xxcshgl.do?method=zyxxcsh", "xxcshgl.do?method=bjxxcsh", 
						 "xxcshgl.do?method=xsxxcsh"};
		
		return dao.arrayToList(dyym, mkmc);
	}
	
	/**
	 * ��ȡ��ʼ�����
	 * */
	public boolean getCshFlag(String module){
		//TODO ���ù�����ɺ��޸Ĵ˴�
		return true;
	}
	
	/**
	 * ѧУ������Ϣ��ʼ�����ӽӿ�
	 * */
	public String xxbmLjjk(XsxxcshForm model){
		String message = "�����ɹ�";
		//TODO �ӿ�������ɺ��޸Ĵ˴�
		return message;
	}
	
	/**
	 * ѧУ������Ϣ���Ϊ��ȷ����
	 * */
	public String xxbmBjwzqsj(XsxxcshForm model){
		String message = "�����ɹ���";
		//��ѡ�е����ݲ��뵽ѧУ������ʽ����
		boolean result = dao.saveXxbmlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * ѧУ������Ϣȫ�����Ϊ��ȷ����
	 * */
	public String xxbmQbbjwzqsj(XsxxcshForm model){
		String message = "�����ɹ�";
		//��ȫ�����ݲ��뵽ѧУ������ʽ����
		boolean result = dao.saveAllXxbmlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * רҵ��Ϣ��ʼ�����ӽӿ�
	 * */
	public String zyxxLjjk(XsxxcshForm model){
		String message = "�����ɹ�";
		//TODO �ӿ�������ɺ��޸Ĵ˴�
		return message;
	}
	
	/**
	 * רҵ��Ϣ���Ϊ��ȷ����
	 * */
	public String zyxxBjwzqsj(XsxxcshForm model){
		String message = "�����ɹ���";
		//��ѡ�е����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveZyxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * רҵ��Ϣȫ�����Ϊ��ȷ����
	 * */
	public String zyxxQbbjwzqsj(XsxxcshForm model){
		String message = "�����ɹ�";
		//��ȫ�����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveAllZyxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	
	/**
	 * �༶��Ϣ��ʼ�����ӽӿ�
	 * */
	public String bjxxLjjk(XsxxcshForm model){
		String message = "�����ɹ�";
		//TODO �ӿ�������ɺ��޸Ĵ˴�
		return message;
	}
	
	/**
	 * �༶��Ϣ���Ϊ��ȷ����
	 * */
	public String bjxxBjwzqsj(XsxxcshForm model){
		String message = "�����ɹ���";
		//��ѡ�е����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveBjxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * �༶��Ϣȫ�����Ϊ��ȷ����
	 * */
	public String bjxxQbbjwzqsj(XsxxcshForm model){
		String message = "�����ɹ�";
		//��ȫ�����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveAllBjxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * ѧ����Ϣ��ʼ�����ӽӿ�
	 * */
	public String xsxxLjjk(XsxxcshForm model){
		String message = "�����ɹ�";
		//TODO �ӿ�������ɺ��޸Ĵ˴�
		return message;
	}
	
	/**
	 * ѧ����Ϣ���Ϊ��ȷ����
	 * */
	public String xsxxBjwzqsj(XsxxcshForm model){
		String message = "�����ɹ���";
		//��ѡ�е����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveXsxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
	
	/**
	 * ѧ����Ϣȫ�����Ϊ��ȷ����
	 * */
	public String xsxxQbbjwzqsj(XsxxcshForm model){
		String message = "�����ɹ�";
		//��ȫ�����ݲ��뵽רҵ��ʽ����
		boolean result = dao.saveAllXsxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "����ʧ�ܣ�";
		}
		return message;
	}
}

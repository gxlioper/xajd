package xgxt.xtwh;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;

public class XtwhService extends CommService {

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ³��
	 * 
	 */
	public void setXtwhOptionList(XtwhCommForm xtwhCommForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, xtwhCommForm);
		setList(model, rForm, request);
	}
	
	public String getYjzt(){
		XtwhDAO xtdao = new XtwhDAO();
		return xtdao.getYjzt();
	}
	

}

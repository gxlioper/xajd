package xgxt.xsxx.comm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ͨ��-service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxCommService extends CommService {

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setXsxxOptionList(XsxxCommForm commForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, commForm);
		setList(model, rForm, request);
	}
}

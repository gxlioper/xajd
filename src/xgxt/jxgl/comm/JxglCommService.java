package xgxt.jxgl.comm;

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
 * Description: ��ѵ����_ͨ��-service��
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

public class JxglCommService extends CommService {

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setJxglOptionList(JxglCommForm jxglCommForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, jxglCommForm);
		setList(model, rForm, request);
	}
}


package xgxt.pjpy.cqkjxy;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̼�����ѧΥ�ʹ���ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class PjpyCqkjxyActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fdysh;//����Ա���
	private String yesNo;//����Ƿ�ͨ��
	
	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	
}

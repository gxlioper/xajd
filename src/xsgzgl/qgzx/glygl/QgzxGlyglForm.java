package xsgzgl.qgzx.glygl;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-������-�����Ϣ����
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxGlyglForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//����
	private String yhm;//�û���
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	
	

}

package xgxt.comm.xtwh;

import org.apache.struts.action.ActionForm;

public class CommXtwhForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String yhm;// �û���

	private String tsyj;// ��ʾ���

	private String[] pic;// ��ݷ�ʽ��ͼƬ��

	public String getTsyj() {
		return tsyj;
	}

	public void setTsyj(String tsyj) {
		this.tsyj = tsyj;
	}

	public String[] getPic() {
		return pic;
	}

	public void setPic(String[] pic) {
		this.pic = pic;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
}

/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.xyfd.cssz;

import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * @className	�� CsszForm
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-15 ����05:49:12
 * @version 	V1.0 
 */

public class CsszForm extends ActionForm{
	
	private static final long serialVersionUID = -7412574192963714092L;
	private String id;
	private String lx;
	private String splc;
	
	private String[] pbs;
	
	private List<String[]> paramList;
	
	private String pb;//��־Ը��
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @param 		��id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @return		: the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @param 		��lx the lx to set
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-15 ����05:59:38 
	 * @param 		��splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String[] getPbs() {
		return pbs;
	}

	public void setPbs(String[] pbs) {
		this.pbs = pbs;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-16 ����03:12:12 
	 * @return		: the paramList
	 */
	public List<String[]> getParamList() {
		return paramList;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-16 ����03:12:12 
	 * @param 		��paramList the paramList to set
	 */
	public void setParamList(List<String[]> paramList) {
		this.paramList = paramList;
	}
	
	
	
}

package xgxt.xtwh.xtwhCriterion.filter;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������model</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-4-28</p>
 */

public class FilterModel {
	
	String userName;	//�û���
	String fwlj; 		//����·��
	String purview;	    //ҳ���ϱ�����Ѿ�ȡ����ӵ��Ȩ��id
	String operateBound;//��ѯ��Χ
	String substituteUser;//��ѯ��Χ
	String title;//ҳ�湦������
	String userType;//�û�����
	String gnmkdm;//����ģ�����
	String userRoles;//�û���ɫ��ƴ���ַ���
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubstituteUser() {
		return substituteUser;
	}
	public void setSubstituteUser(String substituteUser) {
		this.substituteUser = substituteUser;
	}
	public String getOperateBound() {
		return operateBound;
	}
	public void setOperateBound(String operateBound) {
		this.operateBound = operateBound;
	}
	public String getFwlj() {
		return fwlj;
	}
	public void setFwlj(String fwlj) {
		this.fwlj = fwlj;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
}

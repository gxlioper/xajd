package xsgzgl.szdw.general.dwwh;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_����ά��_ͨ��_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class DwwhModel {

	private String pk;// ����

	private String[] pkValue;// ����ֵ

	private String lx;// ����

	private String zgh;// ְ����

	private String xm;// ����

	private String xb;// �Ա�

	private String lxdh;// ��ϵ�绰

	private String bmdm;// ���Ŵ���

	private String zdm;// �����

	private String sfjryx;// �Ƿ����ԺУ

	private String njV;// �꼶

	private String xyV;// ѧԺ

	private String zyV;// רҵ

	private String bjV;// �༶

	private String checked_nj;// ѡ��״̬���꼶��

	private String checked_xy;// ѡ��״̬��ѧԺ��

	private String checked_zy;// ѡ��״̬��רҵ��

	private String checked_bj;// ѡ��״̬���༶��

	private String type;// ����

	private String[] bjdm;// �༶����

	private String dbqk;// �������

	private String ywjs;// ���޽�ʦ

	private String brdb;// ���˴���
	
	private String fdydbj; // ����Ա���༶��
	
	private String bzrdbj; // �����δ��༶��

	private String[] bjdm_other;// �����༶

	private String[] bjdm_my;// ���˰༶
	
	private String khyh;//��������
	
	private String hyzk;//����״��
	
	private String fdyzbs;//����Աֵ����
	
	private String fdyrzrq;//����Ա��ְ����
	
	private String zyjnzs;//ְҵ����֤��
	
	private String age;//����

	private String sydm;//��Ժ����  ��������Ի��ֶ�
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}

	/**
	 * @param khyhҪ���õ� khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzkҪ���õ� hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}

	/**
	 * @param fdyzbsҪ���õ� fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}

	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}

	/**
	 * @param fdyrzrqҪ���õ� fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}

	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}

	/**
	 * @param zyjnzsҪ���õ� zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param ageҪ���õ� age
	 */
	public void setAge(String age) {
		this.age = age;
	}


	public String[] getBjdm_other() {
		return bjdm_other;
	}

	public void setBjdm_other(String[] bjdmOther) {
		bjdm_other = bjdmOther;
	}

	public String[] getBjdm_my() {
		return bjdm_my;
	}

	public void setBjdm_my(String[] bjdmMy) {
		bjdm_my = bjdmMy;
	}

	public String getDbqk() {
		return dbqk;
	}

	public void setDbqk(String dbqk) {
		this.dbqk = dbqk;
	}

	public String getYwjs() {
		return ywjs;
	}

	public String getBrdb() {
		return brdb;
	}

	public void setBrdb(String brdb) {
		this.brdb = brdb;
	}

	public void setYwjs(String ywjs) {
		this.ywjs = ywjs;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChecked_nj() {
		return checked_nj;
	}

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public void setChecked_nj(String checkedNj) {
		checked_nj = checkedNj;
	}

	public String getChecked_xy() {
		return checked_xy;
	}

	public void setChecked_xy(String checkedXy) {
		checked_xy = checkedXy;
	}

	public String getChecked_zy() {
		return checked_zy;
	}

	public void setChecked_zy(String checkedZy) {
		checked_zy = checkedZy;
	}

	public String getChecked_bj() {
		return checked_bj;
	}

	public void setChecked_bj(String checkedBj) {
		checked_bj = checkedBj;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getSfjryx() {
		return sfjryx;
	}

	public void setSfjryx(String sfjryx) {
		this.sfjryx = sfjryx;
	}

	public String getNjV() {
		return njV;
	}

	public void setNjV(String njV) {
		this.njV = njV;
	}

	public String getXyV() {
		return xyV;
	}

	public void setXyV(String xyV) {
		this.xyV = xyV;
	}

	public String getZyV() {
		return zyV;
	}

	public void setZyV(String zyV) {
		this.zyV = zyV;
	}

	public String getBjV() {
		return bjV;
	}

	public void setBjV(String bjV) {
		this.bjV = bjV;
	}

	public String getBzrdbj() {
		return bzrdbj;
	}

	public void setBzrdbj(String bzrdbj) {
		this.bzrdbj = bzrdbj;
	}

	public String getFdydbj() {
		return fdydbj;
	}

	public void setFdydbj(String fdydbj) {
		this.fdydbj = fdydbj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-25 ����04:38:38 
	 * @return		: the sydm
	 */
	public String getSydm() {
		return sydm;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-25 ����04:38:38 
	 * @param 		��sydm the sydm to set
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}
	
}


package xgxt.pjpy.hzy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class HzyPjpyForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5866645887378037297L;
	private String queryStr;
	private String bmdm;
	private String hdmc;
	private String hdsj;
	private String hjmc;
	private String sqje;
	private String cjry;
	private String[] xm;
	private String[] bjmc;
	private String xyyj;
	private String xxyj;
	private String xscyj;
	private String zysj;
	private String xsrs;//ѧ������
	private String bzr;
	private String bzxm;//�೤����
	private String bjdm;//�༶
	private String xydm;
	private String zydm;
	private String nj;
	private String bj;
	private String tzs;
	private String bhgqs;
	private String bjry;
	private String yxdzbyj;
	private String ywcf;
	private String xn;
	private String jtch;
	private String tzbzysj;
	private String tzzzysj;
	private String ftys;//����Ա��
	private String yesNo;
	private String wjlj;//�ļ��ϴ�·��
	private String sqsj;//����ʱ��
	private String tzbmc;//��֧������
	private String tzbsj;//��֧�����
	private String tys;//��Ա��
    private String xq;//ѧ�ڴ���
    private String tzzmc;//����֧����
    private String tzzsj;//����֧���
    private String tzbs;//��֧����
    private String xjType;//�Ƚ�����
	public String[] cbv;//�����б�
    
	FormFile uploadFile;//�ϴ��ļ�
	
	public String getJtch() {
		return jtch;
	}

	public void setJtch(String jtch) {
		this.jtch = jtch;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getYwcf() {
		return ywcf;
	}

	public void setYwcf(String ywcf) {
		this.ywcf = ywcf;
	}

	public String getBhgqs() {
		return bhgqs;
	}

	public void setBhgqs(String bhgqs) {
		this.bhgqs = bhgqs;
	}

	public String getBjry() {
		return bjry;
	}

	public void setBjry(String bjry) {
		this.bjry = bjry;
	}

	public String getTzs() {
		return tzs;
	}

	public void setTzs(String tzs) {
		this.tzs = tzs;
	}

	public String getYxdzbyj() {
		return yxdzbyj;
	}

	public void setYxdzbyj(String yxdzbyj) {
		this.yxdzbyj = yxdzbyj;
	}

	public String getBjdm() {
		return bjdm;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXscyj() {
		return xscyj;
	}

	public void setXscyj(String xscyj) {
		this.xscyj = xscyj;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String[] getBjmc() {
		return bjmc;
	}

	public void setBjmc(String[] bjmc) {
		this.bjmc = bjmc;
	}

	public String[] getXm() {
		return xm;
	}

	public void setXm(String[] xm) {
		this.xm = xm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getCjry() {
		return cjry;
	}

	public void setCjry(String cjry) {
		this.cjry = cjry;
	}

	public String getHdmc() {
		return hdmc;
	}

	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}

	public String getHdsj() {
		return hdsj;
	}

	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	public String getSqje() {
		return sqje;
	}

	public void setSqje(String sqje) {
		this.sqje = sqje;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public String getBzxm() {
		return bzxm;
	}

	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}

	public String getXsrs() {
		return xsrs;
	}

	public void setXsrs(String xsrs) {
		this.xsrs = xsrs;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getFtys() {
		return ftys;
	}

	public void setFtys(String ftys) {
		this.ftys = ftys;
	}

	public String getTys() {
		return tys;
	}

	public void setTys(String tys) {
		this.tys = tys;
	}

	public String getTzbmc() {
		return tzbmc;
	}

	public void setTzbmc(String tzbmc) {
		this.tzbmc = tzbmc;
	}

	public String getTzbsj() {
		return tzbsj;
	}

	public void setTzbsj(String tzbsj) {
		this.tzbsj = tzbsj;
	}

	public String getWjlj() {
		return wjlj;
	}

	public void setWjlj(String wjlj) {
		this.wjlj = wjlj;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getTzbs() {
		return tzbs;
	}

	public void setTzbs(String tzbs) {
		this.tzbs = tzbs;
	}

	public String getTzzmc() {
		return tzzmc;
	}

	public void setTzzmc(String tzzmc) {
		this.tzzmc = tzzmc;
	}

	public String getTzzsj() {
		return tzzsj;
	}

	public void setTzzsj(String tzzsj) {
		this.tzzsj = tzzsj;
	}

	public String getXjType() {
		return xjType;
	}

	public void setXjType(String xjType) {
		this.xjType = xjType;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getTzbzysj() {
		return tzbzysj;
	}

	public void setTzbzysj(String tzbzysj) {
		this.tzbzysj = tzbzysj;
	}

	public String getTzzzysj() {
		return tzzzysj;
	}

	public void setTzzzysj(String tzzzysj) {
		this.tzzzysj = tzzzysj;
	}
}

package xgxt.zxdk.xnmz;

import xgxt.form.CommanForm;
import xgxt.form.User;

public class ZxdkForm extends CommanForm {
	
	
	public static ZxdkForm zxdkInitForm;
	
	private User user;//用户对象
	
	// --------------助学贷款设置 begin---------------
	private String guid;//编号
	
	private String sqkssj;//申请开始时间
	
	private String sqjssj;//申请结束时间
	
	private String shkssj;//审核开始时间
	
	private String shjssj;//审核结束时间
	
	private String splc;
	
	private String shgw;//审核岗位
	
	private String shjb;//审核级别
	
	// --------------助学贷款设置 end---------------
	
	// --------------申请信息---------------------
	private String shyj;//审核意见
	
	private String sqsj;//申请时间
	
	private String grwzdz;//个人网址地址
	
	private String dkkssj;//贷款开始时间
	
	private String dknx;//贷款年限
	
	private String lxrxm;// 联系姓名

	private String lxrjtzz;// 联系人家庭住址

	private String lxrgzdw;// 联系人工作单位

	private String lxrgddh;// 联系人固定电话

	private String lxryddh;// 联系人移动电话

	private String jzrxm; // '见证人姓名';

	private String jzrzjh; // '见证人证件号';

	private String jzrzjlxdm; // '见证人证件类型代码';

	private String jzrdz;// '见证人家庭地址';

	private String jzrgx;// '见证人与申请人关系';

	private String jzryb;// '见证人邮编';

	private String jzrdh;// '见证人电话';

	private String jzryj;// '见证人意见';
	
	private String jtysr;// '家庭月收入';
	
	private String onexnxfje;//第一学年学费贷款
	
	private String onexnqsfje;//第一学年寝室费贷款
	
	private String onexnshf;//第一学年生活费
	
	private String onexnzje;//第一学年总贷款
	
	private String twoxnxfje;//第二学年学费贷款
	
	private String twoxnqsfje;//第二学年寝室费贷款
	
	private String twoxnshf;//第二学年生活费贷款
	
	private String twoxnzje;//第二学年总贷款
	
	private String threexnxfje;//第三学年学费贷款
	
	private String threexnqsfje;//第三学年寝室费贷款
	
	private String threexnshf;//第三学年生活费贷款
	
	private String threexnzje;//第三学年总贷款
	
	private String fourxnxfje;//第四学年学费贷款
	
	private String fourxnqsfje;//第四学年寝室费贷款
	
	private String fourxnshf;//第四学年生活费贷款
	
	private String fourxnzje;//第四学年总贷款
	
	private String fivexnxfje;//第五学年学费贷款
	
	private String fivexnqsfje;//第五学年寝室费贷款
	
	private String fivexnshf;//第五学年生活费贷款
	
	private String fivexnzje;//第五学年总贷款
	
	private String fqxm;//父亲姓名
	
	private String fqsfzh;//父亲身份证号
	
	private String fqgzdw;//父亲工作单位
	
	private String fqlxfs;//父亲联系方式
	
	private String mqxm;//父亲姓名
	
	private String mqsfzh;//父亲身份证号
	
	private String mqgzdw;//父亲工作单位
	
	private String mqlxfs;//父亲联系方式
	
	
	private String dkzje;//贷款总金额
	
	static {
		
//		ZxdkService service = new ZxdkService();
//		zxdkInitForm =new ZxdkForm();
		//审批流程
//		zxdkInitForm.setSplc(service.getSzMap().get("splc"));
		
		//申请时间控制
//		zxdkInitForm.setSqkssj(service.getSzMap().get("sqkssj"));
//		zxdkInitForm.setSqjssj(service.getSzMap().get("sqjssj"));
//		zxdkInitForm.setShkssj(service.getSzMap().get("shkssj"));
//		zxdkInitForm.setShjssj(service.getSzMap().get("shjssj"));

	}
	
	
	public String getDkzje() {
		return dkzje;
	}

	public void setDkzje(String dkzje) {
		this.dkzje = dkzje;
	}

	public String getFivexnqsfje() {
		return fivexnqsfje;
	}

	public void setFivexnqsfje(String fivexnqsfje) {
		this.fivexnqsfje = fivexnqsfje;
	}

	public String getFivexnxfje() {
		return fivexnxfje;
	}

	public void setFivexnxfje(String fivexnxfje) {
		this.fivexnxfje = fivexnxfje;
	}

	public String getFivexnzje() {
		return fivexnzje;
	}

	public void setFivexnzje(String fivexnzje) {
		this.fivexnzje = fivexnzje;
	}

	public String getFourxnqsfje() {
		return fourxnqsfje;
	}

	public void setFourxnqsfje(String fourxnqsfje) {
		this.fourxnqsfje = fourxnqsfje;
	}

	public String getFourxnxfje() {
		return fourxnxfje;
	}

	public void setFourxnxfje(String fourxnxfje) {
		this.fourxnxfje = fourxnxfje;
	}

	public String getFourxnzje() {
		return fourxnzje;
	}

	public void setFourxnzje(String fourxnzje) {
		this.fourxnzje = fourxnzje;
	}

	public String getOnexnqsfje() {
		return onexnqsfje;
	}

	public void setOnexnqsfje(String onexnqsfje) {
		this.onexnqsfje = onexnqsfje;
	}

	public String getOnexnxfje() {
		return onexnxfje;
	}

	public void setOnexnxfje(String onexnxfje) {
		this.onexnxfje = onexnxfje;
	}

	public String getOnexnzje() {
		return onexnzje;
	}

	public void setOnexnzje(String onexnzje) {
		this.onexnzje = onexnzje;
	}

	public String getThreexnqsfje() {
		return threexnqsfje;
	}

	public void setThreexnqsfje(String threexnqsfje) {
		this.threexnqsfje = threexnqsfje;
	}

	public String getThreexnxfje() {
		return threexnxfje;
	}

	public void setThreexnxfje(String threexnxfje) {
		this.threexnxfje = threexnxfje;
	}

	public String getThreexnzje() {
		return threexnzje;
	}

	public void setThreexnzje(String threexnzje) {
		this.threexnzje = threexnzje;
	}

	public String getTwoxnqsfje() {
		return twoxnqsfje;
	}

	public void setTwoxnqsfje(String twoxnqsfje) {
		this.twoxnqsfje = twoxnqsfje;
	}

	public String getTwoxnxfje() {
		return twoxnxfje;
	}

	public void setTwoxnxfje(String twoxnxfje) {
		this.twoxnxfje = twoxnxfje;
	}

	public String getTwoxnzje() {
		return twoxnzje;
	}

	public void setTwoxnzje(String twoxnzje) {
		this.twoxnzje = twoxnzje;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJzrdh() {
		return jzrdh;
	}

	public void setJzrdh(String jzrdh) {
		this.jzrdh = jzrdh;
	}

	public String getJzrdz() {
		return jzrdz;
	}

	public void setJzrdz(String jzrdz) {
		this.jzrdz = jzrdz;
	}

	public String getJzrgx() {
		return jzrgx;
	}

	public void setJzrgx(String jzrgx) {
		this.jzrgx = jzrgx;
	}

	public String getJzrxm() {
		return jzrxm;
	}

	public void setJzrxm(String jzrxm) {
		this.jzrxm = jzrxm;
	}

	public String getJzryb() {
		return jzryb;
	}

	public void setJzryb(String jzryb) {
		this.jzryb = jzryb;
	}

	public String getJzryj() {
		return jzryj;
	}

	public void setJzryj(String jzryj) {
		this.jzryj = jzryj;
	}

	public String getJzrzjh() {
		return jzrzjh;
	}

	public void setJzrzjh(String jzrzjh) {
		this.jzrzjh = jzrzjh;
	}

	public String getJzrzjlxdm() {
		return jzrzjlxdm;
	}

	public void setJzrzjlxdm(String jzrzjlxdm) {
		this.jzrzjlxdm = jzrzjlxdm;
	}

	public String getLxrgddh() {
		return lxrgddh;
	}

	public void setLxrgddh(String lxrgddh) {
		this.lxrgddh = lxrgddh;
	}

	public String getLxrgzdw() {
		return lxrgzdw;
	}

	public void setLxrgzdw(String lxrgzdw) {
		this.lxrgzdw = lxrgzdw;
	}

	public String getLxrjtzz() {
		return lxrjtzz;
	}

	public void setLxrjtzz(String lxrjtzz) {
		this.lxrjtzz = lxrjtzz;
	}

	public String getLxrxm() {
		return lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLxryddh() {
		return lxryddh;
	}

	public void setLxryddh(String lxryddh) {
		this.lxryddh = lxryddh;
	}

	public String getJtysr() {
		return jtysr;
	}

	public void setJtysr(String jtysr) {
		this.jtysr = jtysr;
	}

	public String getFqgzdw() {
		return fqgzdw;
	}

	public void setFqgzdw(String fqgzdw) {
		this.fqgzdw = fqgzdw;
	}

	public String getFqlxfs() {
		return fqlxfs;
	}

	public void setFqlxfs(String fqlxfs) {
		this.fqlxfs = fqlxfs;
	}

	public String getFqsfzh() {
		return fqsfzh;
	}

	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getMqgzdw() {
		return mqgzdw;
	}

	public void setMqgzdw(String mqgzdw) {
		this.mqgzdw = mqgzdw;
	}

	public String getMqlxfs() {
		return mqlxfs;
	}

	public void setMqlxfs(String mqlxfs) {
		this.mqlxfs = mqlxfs;
	}

	public String getMqsfzh() {
		return mqsfzh;
	}

	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getGrwzdz() {
		return grwzdz;
	}

	public void setGrwzdz(String grwzdz) {
		this.grwzdz = grwzdz;
	}

	public String getDkkssj() {
		return dkkssj;
	}

	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}

	public String getDknx() {
		return dknx;
	}

	public void setDknx(String dknx) {
		this.dknx = dknx;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getFivexnshf() {
		return fivexnshf;
	}

	public void setFivexnshf(String fivexnshf) {
		this.fivexnshf = fivexnshf;
	}

	public String getFourxnshf() {
		return fourxnshf;
	}

	public void setFourxnshf(String fourxnshf) {
		this.fourxnshf = fourxnshf;
	}

	public String getOnexnshf() {
		return onexnshf;
	}

	public void setOnexnshf(String onexnshf) {
		this.onexnshf = onexnshf;
	}

	public String getThreexnshf() {
		return threexnshf;
	}

	public void setThreexnshf(String threexnshf) {
		this.threexnshf = threexnshf;
	}

	public String getTwoxnshf() {
		return twoxnshf;
	}

	public void setTwoxnshf(String twoxnshf) {
		this.twoxnshf = twoxnshf;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}

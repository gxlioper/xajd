package xgxt.audit.spgc;

public class AuditingModel {

	private String id;//关联ID
	
	private String shlcid;//审核流程
	
	private String xtgwid;//审核岗位
	
	private String shzt;//审核状态
	
	private String shsj;//审核时间
	
	private String shyj;//审核意见
	
	private String shr;//审核人
	
	private String sftj;//是否提交
	
	private String thgw;//退回岗位
	
	private String nextPost;//下一个审核岗位
	
	private String sqjlb;//申请记录表
	
	

	public String getSqjlb() {
		return sqjlb;
	}

	public void setSqjlb(String sqjlb) {
		this.sqjlb = sqjlb;
	}

	public String getNextPost() {
		return nextPost;
	}

	public void setNextPost(String nextPost) {
		this.nextPost = nextPost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSftj() {
		return sftj;
	}

	public void setSftj(String sftj) {
		this.sftj = sftj;
	}

	public String getShlcid() {
		return shlcid;
	}

	public void setShlcid(String shlcid) {
		this.shlcid = shlcid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getXtgwid() {
		return xtgwid;
	}

	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
}

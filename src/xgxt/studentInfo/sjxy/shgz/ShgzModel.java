package xgxt.studentInfo.sjxy.shgz;

public class ShgzModel {
	private String xh;    		 //学号
	private String ftwsh;     	 //分团委审核
	private String xtwshsj;     //校团委审核时间
	private String ftwshsj;     //分团委审核时间
	private String ftwshyj;     //分团委审核意见
	private String xtwshyj;     //校团委审核意见
	private String xtwsh;     	 //校团委审核
	private String[] rzkssj;	 //任职开始时间
	private String[] rzjssj;	//任职结束时间
	private String[] rzbm;		//任职部门
	private String[] zw;		//职务
	private String[] jdbm;		//鉴定部门
	private String[] hjkssj;	//获奖开始时间
	private String[] hjjssj;	//获奖结束时间
	private String[] jllb;		//奖励类别
	private String[] sjbm;		//授奖部门
	
	public String[] getHjjssj() {
		return hjjssj;
	}
	public void setHjjssj(String[] hjjssj) {
		this.hjjssj = hjjssj;
	}
	public String[] getHjkssj() {
		return hjkssj;
	}
	public void setHjkssj(String[] hjkssj) {
		this.hjkssj = hjkssj;
	}
	public String[] getJllb() {
		return jllb;
	}
	public void setJllb(String[] jllb) {
		this.jllb = jllb;
	}
	public String[] getRzjssj() {
		return rzjssj;
	}
	public void setRzjssj(String[] rzjssj) {
		this.rzjssj = rzjssj;
	}
	public String[] getRzkssj() {
		return rzkssj;
	}
	public void setRzkssj(String[] rzkssj) {
		this.rzkssj = rzkssj;
	}
	public String[] getSjbm() {
		return sjbm;
	}
	public void setSjbm(String[] sjbm) {
		this.sjbm = sjbm;
	}
	public String[] getJdbm() {
		return jdbm;
	}
	public void setJdbm(String[] jdbm) {
		this.jdbm = jdbm;
	}
	public String[] getRzbm() {
		return rzbm;
	}
	public void setRzbm(String[] rzbm) {
		this.rzbm = rzbm;
	}
	public String[] getZw() {
		return zw;
	}
	public void setZw(String[] zw) {
		this.zw = zw;
	}
	public String getFtwsh() {
		return ftwsh;
	}
	public void setFtwsh(String ftwsh) {
		this.ftwsh = ftwsh;
	}
	public String getFtwshsj() {
		return ftwshsj;
	}
	public void setFtwshsj(String ftwshsj) {
		this.ftwshsj = ftwshsj;
	}
	public String getFtwshyj() {
		return ftwshyj;
	}
	public void setFtwshyj(String ftwshyj) {
		this.ftwshyj = ftwshyj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXtwsh() {
		return xtwsh;
	}
	public void setXtwsh(String xtwsh) {
		this.xtwsh = xtwsh;
	}
	public String getXtwshyj() {
		return xtwshyj;
	}
	public void setXtwshyj(String xtwshyj) {
		this.xtwshyj = xtwshyj;
	}
	public String getXtwshsj() {
		return xtwshsj;
	}
	public void setXtwshsj(String xtwshsj) {
		this.xtwshsj = xtwshsj;
	}
}

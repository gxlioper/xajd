package xsgzgl.xtwh.wdgz;

/**
 * 
 * 待办工作模型
 * 
 * @author Penghui.Qu 2013-1-7
 */
public class Job {
	
	private String sqid;
	private String sqr;
	private String[] shr;
	private String shjd;
	private String url;
	private String xsUrl;
	private String gnmk;
	private String gznr;
	private String jdmc;

	private Job(){
	}
	
	/**
	 * 获取Job实例对象（有审核节点，需要推审核人待办工作）
	 * @param sqid
	 * @param sqr
	 * @param shjd
	 * @param url
	 * @param gnmk
	 * @param gznr
	 * @return
	 */
	public static Job getJobInstance(String sqid,String sqr,String shjd,String url,String xsUrl,String gnmk,
							  String gznr){
		Job job = new Job();
		job.setSqid(sqid);
		job.setSqr(sqr);
		job.setShjd(shjd);
		job.setXsUrl(xsUrl);
		//job.setShr(shr);
		job.setUrl(url);
		job.setGnmk(gnmk);
		job.setGznr(gznr);
		return job;
	}

	
	
	/**
	 * 获取JOB实例，用于审核后更新待办
	 * @param sqid
	 * @param shjd
	 * @param gnmk
	 * @return
	 */
	public static Job getJobInstance(String sqid,String shjd,String url,String gnmk){
		Job job = new Job();
		job.setSqid(sqid);
		//job.setSqr(sqr);
		job.setShjd(shjd);
		//job.setXsUrl(xsUrl);
		//job.setShr(shr);
		job.setUrl(url);
		job.setGnmk(gnmk);
//		job.setGznr(gznr);
		return job;
	}
	
	
	
	
	/**
	 * 获取Job实例，无审核节点，不需要推送审核人待办工作
	 * @param sqid
	 * @param gnmk
	 * @return
	 */
	public static Job getJobInstance(String sqid,String gnmk){
		Job job = new Job();
		job.setSqid(sqid);
		//job.setSqr(sqr);
		//job.setUrl(url);
		job.setGnmk(gnmk);
//		job.setGznr(gznr);
		return job;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String[] getShr() {
		return shr;
	}

	public void setShr(String[] shr) {
		this.shr = shr;
	}

	public String getShjd() {
		return shjd;
	}

	public void setShjd(String shjd) {
		this.shjd = shjd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getGznr() {
		return gznr;
	}

	public void setGznr(String gznr) {
		this.gznr = gznr;
	}

	public String getXsUrl() {
		return xsUrl;
	}

	public void setXsUrl(String xsUrl) {
		this.xsUrl = xsUrl;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

}

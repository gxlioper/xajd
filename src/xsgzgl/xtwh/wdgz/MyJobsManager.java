package xsgzgl.xtwh.wdgz;

/**
 * <b>我的工作<b>
 * <p>
 * 		学生（我的申请），老师（我的工作）<br>
 * 		
 * 		业务模块根据规则增加或删除待办工作。<br>
 * 		
 * 		本接口提供方法将待办工作添加或删除。
 * </p>
 * @author Penghui.Qu 2013-1-7
 * @version 1.0
 */
public interface MyJobsManager {

	
	/**
	 * 将待办工作放入待办工作集合（用于申请保存操作）
	 * @param job
	 * @throws Exception
	 */
	public void pushJob(Job job) throws Exception;
	
	
	
	/**
	 * ①将待办工作从待办工作集中移除
	 * ②将学生我的申请从审核进度状态更新
	 * @param job
	 * @throws Exception
	 */
	public void updateJob(Job job) throws Exception;
	
	
	
	/**
	 * 删除待办（用于业务模块取消申请操作）
	 * @param ywid
	 * @param gnmk
	 * @return
	 * @throws Exception
	 */
	public boolean removeJob(String[] ywid, String gnmk) throws Exception;
	
	
	
	/**
	 * 拷贝我的工作（为审批流提供接口）
	 * @param userName
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean copyWdgz(String[] userName, String gwid) throws Exception;
	
	
	/**
	 * 删除我的工作（为审批流提供接口）
	 * @param userName
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean delWdgz(String[] userName, String gwid) throws Exception;

	/** 
	 * @描述:撤销操作后回滚我的待办
	 * @作者：qilm
	 * @日期：2013-10-14 上午11:10:17
	 * @param ywid
	 * @param gwid
	 * void 返回类型 
	 * @throws 
	 */
	public void cancelJob(String ywid, String gwid);



	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-8 上午11:37:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param job
	 * void 返回类型 
	 * @throws 
	 */
	public void cancelJob(Job job);
}

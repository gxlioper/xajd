<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdblsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+ new Date().getTime());
				var hdxs = '${rs.hdxs}';
                if("课程" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("讲座" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("具体类型");
                    jQuery("#con_span").html("讲座介绍");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                }
                kcjbChange();
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdblsq" method="post" styleId="hdblsqshForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>活动补录申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								学年
							</th>
							<td width="35%">
								${rs.xn}
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								活动名称
							</th>
							<td width="35%">
								${rs.hdmc}
							</td>
							<th>活动时间</th>
							<td>
								${rs.hdsj}
							</td>
						</tr>
						<tr>
							<th>
								主办方
							</th>
							<td colspan="3">
									${rs.zbf}
							</td>
						</tr>
						<tr>
							<th width="15%">
								线上or线下活动
							</th>
							<td width="35%">
									${rs.xsxxlx}
							</td>
							<th>活动形式</th>
							<td>
									${rs.hdkclx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								活动性质
							</th>
							<td width="35%">
									${rs.hdxs}
							</td>
							<th>活动类型</th>
							<td>
									${rs.hdlxmc}
							</td>
						</tr>

						<tr name="zjrxx_tr">
							<th>
								主讲人姓名
							</th>
							<td>
									${rs.zjrxm}
							</td>
							<th >
								主讲人单位
							</th>
							<td >
									${rs.zjrdw}
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								主讲人职称
							</th>
							<td>
									${rs.zjrzc}
							</td>
							<th >
								主讲人职务
							</th>
							<td >
									${rs.zjrzw}
							</td>
						</tr>
						<tr>
							<th>
								讲座级别
							</th>
							<td colspan="3">
									${rs.jzjb}
							</td>

						</tr>
						<tr name="zjrxx_tr">
							<th>
								主讲人介绍
							</th>
							<td colspan="3">
									${rs.zjrjs}
							</td>

						</tr>

						<tr id="jzlxTr">
							<th width="15%">
								课程级别
							</th>
							<td width="35%">
								<html:hidden property="jzlx" styleId="jzlx"/>
									${rs.jzlxmc}
							</td>
							<th width="15%" id="zxkclxTh" style="display: none">
								自选课程类型
							</th>
							<td width="35%" id="zxkclxTd" style="display: none">
									${rs.zxkclxmc}
							</td>
						</tr>
						<tr>
							<th>
								活动标签
							</th>
							<td colspan="3">
									${rs.hdbqmc}
							</td>
						</tr>
						<tr>
							<th>
								能力标签
							</th>
							<td colspan="3">
									${rs.nlbqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								活动地点
							</th>
							<td width="35%">
								${rs.hddd}
							</td>
							<th>参加类型</th>
							<td>
								${rs.cjlx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								组队职务
							</th>
							<td width="35%">
								${rs.zdzw}
							</td>
							<th>活动职务</th>
							<td>
								${rs.hdzw}
							</td>
						</tr>
						<tr>
							<th width="15%">
								获得奖项
							</th>
							<td width="35%">
								${rs.hdjx}
							</td>
							<th>申请获得学分</th>
							<td>
								${rs.hdxf}
							</td>
						</tr>
			      		<tr>
							<th>
								附件
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fjpath" styleId="fjid"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>						
							</td>
						</tr>
						<tr>
							<th>
								<span id="con_span">活动内容及心得</span>
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
			      		</tr>						
					</tbody>
				 </table>			
			 <logic:notEqual value="无需审核" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
			</div>
			<div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


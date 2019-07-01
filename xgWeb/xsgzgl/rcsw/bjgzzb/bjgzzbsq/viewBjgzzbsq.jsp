<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+ new Date().getTime());
			});
	   </script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbsqgl" method="post" styleId="xsgzzbsqForm">
		<html:hidden property="sqid" styleId="sqid" />	
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>周报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">学年</th>
							<td width="32%">
								${model.xn}
							</td>
							<th width="18%">学期</th>
							<td width="32%">
								${model.xqmc}
							</td>
					    </tr>
					    <tr>
							<th>年级</th>
							<td>
								${model.nj}
							</td>
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.xymc}
							</td>
					    </tr>
					    <tr>
							<th>专业</th>
							<td>
								${model.zymc}
							</td>
							<th>班级</th>
							<td>
								${model.bjmc}
							</td>
					    </tr>
					    </tr>
							<th>周次</th>
							<td>
								${model.zcmc}
							</td>
							<th>周次起止日期</th>
							<td>
								${model.zcksjsrq}
							</td>
					    </tr>
					    <tr>
							<th>
								应到人数
							</th>
							<td>
								${model.ydrs}
							</td>
							<th>
								实到人数
							</th>
							<td>
								${model.sdrs}
							</td>
			      		</tr>
					    <tr>
							<th>
								请假人数
							</th>
							<td>
								${model.qjrs}
							</td>
							<th>
								无故未到学生人数
							</th>
							<td>
								${model.wdrs}
							</td>
			      		</tr>
			      		<tr>
							<th>填写人</th>
							<td colspan="3">
								${model.lrrxm}
							</td>
					    </tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									带班辅导员
								</th>
								<td id="dbfdy" colspan="3">
										${model.dbfdy }
								</td>
							</tr>
						</logic:equal>

					    <tr>
							<th>
								本周对学生进行<br />讲评的主要内容
							</th>
							<td colspan="3">
							    ${model.zynr}
							</td>
			      		</tr>
					    <tr>
							<th>
								本周学生存在的<br />主要问题
							</th>
							<td colspan="3">
							    ${model.zywt}
							</td>
			      		</tr>
					    <tr>
							<th>
								您认为较合理的<br />解决对策
							</th>
							<td colspan="3">
							    ${model.jjdc }
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								附件
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${model.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
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
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</table>
		</html:form>
	</body>
</html>


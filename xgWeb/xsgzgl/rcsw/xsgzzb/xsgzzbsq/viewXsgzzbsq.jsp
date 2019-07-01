<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<link rel="stylesheet" href="xsgzgl/rcsw/xsgzzb/css/xsgzzb.css" type="text/css" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
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
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.bmdmmc}
							</td>
							<th>填写人</th>
							<td>
								${model.lrrxm}
							</td>
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
								一周主要工作
							</th>
							<td colspan="3">
							    ${model.yzzygz}
							</td>
			      		</tr>
					    <tr>
							<th>
								下周重要工作
							</th>
							<td colspan="3">
							    ${model.xzzygz}
							</td>
			      		</tr>
					    <tr>
							<th>
								其他工作
							</th>
							<td colspan="3">
							    ${model.qtgz}
							</td>
			      		</tr>
					    <tr>
							<th>
								对学生工作的<br />意见和建议
							</th>
							<td colspan="3">
							    ${model.yj}
							</td>
			      		</tr>
					</tbody>
					<%--四川信息职业技术学院个性化--%>
				<logic:equal name="xxdm" value="13815">
					<logic:notEmpty name="yscfjlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>附件查看</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="tablehead">
							<td colspan="1" class="center">序号</th>
							<td colspan="1" class="center">上传文件类别</th>
							<td colspan="4" class="center">附件</th>
						</tr>
						<logic:iterate id="yscfj" name="yscfjlist">
							<tr>
								<td colspan="1" class="center">
									${yscfj.rownum}
								</td>
								<td colspan="1" class="center">
									${yscfj.wjlxmc}
								</td>
								<td colspan="4" class="center">
									<a href="rcsw_xsgzzb_xsgzzbsqgl.do?method=downloadFile&id=${yscfj.id}" >下载</a>
									${yscfj.fjmc}
								</td>
							</tr>
						</logic:iterate>
					</tbody>
					</logic:notEmpty>
				</logic:equal>
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


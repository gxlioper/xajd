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
		<script	type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_txhd_xmxxsqgl" method="post" styleId="xmxxsqForm">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<html:hidden property="sqid"  styleId="sqid"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								项目名称
							</th>
							<td width="34%">
								${data.xmmc}
							</td>
							<th width="16%">
								活动时间
							</th>
							<td width="34%" >
								${data.hdkssj}至${data.hdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								活动类别
							</th>
							<td width="34%">
								${hdlbmc}
							</td>
							<th width="16%">
								活动地点
							</th>
							<td width="34%" >
								${data.hddd}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请人数上限
							</th>
							<td width="34%" >
								${data.sqrssx}
							</td>
							<th width="16%">
								审核人数上限
							</th>
							<td width="34%" >
								${data.shrssx}
							</td>
							
						</tr>
						<tr>
						<th align="right" width="10%">
						承办单位
						</th>
						<td align="left" >
							${data.cbdw}
						</td>
						<th align="right" width="10%">
						活动规格
						</th>
						<td align="left" >
							${hdgg}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动目的及意义
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动方案
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
						<% if("13023".equals(xxdm)){ %>
						<tr>
							<th width="16%">
								授予学分
							</th>
							<td width="34%" >
								${data.syxf}
							</td>
							<th width="16%">
							</th>
							<td width="34%" >
							</td>
						</tr>
						<% } %>
						<tr>
							<th width="16%">
								活动说明
							</th>
							<td width="34%" colspan="3">
								${data.hdsm}
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
		</html:form>
	</body>
</html>


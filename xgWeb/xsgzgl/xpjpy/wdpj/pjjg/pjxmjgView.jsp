<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
		
	<body>
		<html:form action="/xpj_pjjg" method="post" styleId="pjxmjgForm" onsubmit="return false;">
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>评奖项目申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>评定周期</th>
							<td>
								${rs.xn }&nbsp;${rs.xqmc }
							</td>
					    </tr>
					    <tr>
							<th>项目类型</th>
							<td>
								${rs.xmlxmc }
							</td>
							<th>项目性质</th>
							<td>
								${rs.xmxzmc }
							</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>
								${rs.xmmc }
							</td>
					    
							<th>金额</th>
							<td>
								${rs.xmje }
							</td>
						</tr>
						<logic:equal value="10279" name="xxdm">
						<tr>
							<th>
								奖项名称
							</th>
							<td colspan="3">
								${rs.ylzd1 }
							</td>
						</tr>
						<tr>
							<th>
								评奖时间
							</th>
							<td colspan="3">
								${rs.ylzd3}
							</td>
						</tr>
						<tr>
							<th>
								主办单位
							</th>
							<td colspan="3">
								${rs.ylzd4 }
							</td>
						</tr>
						</logic:equal>
						<tr>
						<logic:equal value="13943" name="xxdm">
						<tr>
							<th>颁奖单位</th>
							<td colspan="3">
								${rs.bjdw }
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="ylzd5" styleId="fjid"/>
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
							申请时间
							</th>
							<td colspan="3">
								${rs.sqsj }
							</td>
					    </tr>
					    <%--徐州医药高等专科个性化字段--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
						   <th>
								曾受何奖励
						   </th>
						   <td colspan="3" style="word-break:break-all;">
						   	${rs.djjl}
						   </td>
						</tr>
						</logic:equal>
					    <tr>
							<th>
								<logic:equal value="70002" name="xxdm">
									主要事迹
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									申请理由
								</logic:notEqual>
							</th>
							<td colspan="3">
								${rs.sqly }
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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


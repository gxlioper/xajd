<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
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
		<script type="text/javascript">
			
	    </script>

	</head>
	<body>

		<html:form action="/rcsw_dxsylbx_ylbxsqgl" method="post"
			styleId="ylbxsqForm">
		
		<html:hidden property="ylsqid" styleId="ylsqid" />	
			
			<div style='tab;width:100%;height:180px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>参保状况信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					
							
								<table class="formList" width="100%" border="1">
									<thead>
										<tr style="height:30px">
											<th>学号</th>
											<th>姓名</th>
											<th>参保状况</th>
										</tr>
									</thead>
									
									<tr style="height:45px">
										<td align="center">
												${model.xh}
										</td>
										<td align="center">
												${xm}
										</td>
										<td  align="center">
											<logic:notEmpty name="cbzkmcsList">
												<logic:iterate name="cbzkmcsList" id="s"  indexId="i" >
													${s.cbzkmc}
													<br/>
												</logic:iterate>
											</logic:notEmpty>
											
											<logic:notEmpty name="qtcbzkval" >
												其它( ${qtcbzkval} )			   
											</logic:notEmpty>
										</td>
									</tr>	
								</table>
								</tbody>
								</table>
							
						<%--<tr>
							<td width="100%" height="30px">
								<logic:notEmpty name="cbzkmcsList">
									<logic:iterate name="cbzkmcsList" id="s"  indexId="i" >
										${s.cbzkmc}
										<br/>
									</logic:iterate>
								</logic:notEmpty>
								
								<logic:notEmpty name="qtcbzkval" >
									其它( ${qtcbzkval} )			   
								</logic:notEmpty>
							</td>
						</tr>		
					--%></tbody>
				</table>
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


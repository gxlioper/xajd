<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body>
		<html:form action="/cjWsfDfgz" method="post" styleId="DfgzForm">
			<div style='tab;width:100%;overflow:hidden;'>
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">
								ѧ��
							</th>
							<td width="32%">
								${rs.xn}
							</td>
							<th width="18%">
								ѧ��
							</th>
							<td width="32%">
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>�������</th>
					    	<td>
								${rs.ccny}
							</td>
							<th>����ά��ʱ��</th>
					    	<td>
								${rs.wwsj}
								  ��  
								${rs.wwzzsj } 
							</td>	
						</tr>
					</tbody>
				</table>
				<table align="center" class="formlist">
				<div class="formbox">
					<table id="dataTable" class="dateline">
						<thead>
							<tr>
								<td width="26%">������</td>
								<td width="26%">������ </td>
								<td width="16%">�Ƿ������ҵ��</td>
							</tr>
						</thead>
						<tbody class="pfzTbody">
							<logic:iterate id="i" name="pfzszList" indexId="index01">
								<tr>
									<td width="26%">${i.pfzmc }</td>
									<td width="26%">${i.ccbl }%</td>
									<td width="16%">
										<logic:equal value="0" name="i" property="bhbyb">
											<img src=<%=stylePath%>images/ico_shwtg01.gif />
										</logic:equal>
										<logic:equal value="1" name="i" property="bhbyb">
											<img src=<%=stylePath%>images/ico_shtg01.gif />
										</logic:equal>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</table>
		</div>
		</html:form>
	</body>
</html>


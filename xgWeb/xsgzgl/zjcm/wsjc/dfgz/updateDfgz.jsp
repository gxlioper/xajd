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
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/dfgz/js/dfgz.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body>
		<html:form action="/cjWsfDfgz" method="post" styleId="DfgzForm">
			<input type="hidden" name="xn" value="${rs.xn}" id="xn"/>
			<input type="hidden" name="xq" value="${rs.xq}" id="xq"/>
			<input type="hidden" name="ccny" value="${rs.ccny}" id="ccny"/>
			<input type="hidden" name="dfszid" value="${rs.dfszid}" id="dfszid"/>
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
							<th><span class="red">*</span>����ά��ʱ��</th>
					    	<td>
								<html:text property="wwsj" styleId="wwsj" value="${rs.wwsj}" style="width:70px;" onfocus="showCalendar('wwsj','y-mm-dd',true,'wwzzsj');" />
								  ��  
								<html:text property="wwzzsj" styleId="wwzzsj" value="${rs.wwzzsj}" style="width:70px;" onfocus="showCalendar('wwzzsj','y-mm-dd',false,'wwsj');" /> 
							</td>	
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" type="button"
									onclick="saveForm('update');return false;">
									�� ��
								</button>
								<button type="button" type="button"
									onclick="iFClose();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>


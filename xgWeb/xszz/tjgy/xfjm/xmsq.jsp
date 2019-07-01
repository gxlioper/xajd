<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/tjgy_xfjm" method="post">
			<logic:notEmpty name="xmsqInfo">
				<html:hidden property="doType" value="modify"/>
				<script defer>
					var sqtj = '${xmsqInfo.sqtj}'.split(',');
					for (var i = 0 ; i < sqtj.length; i++){
						jQuery('input[name=tjid][value='+sqtj[i]+']').attr('checked',true);
					}
				</script>
			</logic:notEmpty>
		
		
			<div class="tab" />
				<table class="formlist" width="93%">
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xh }
								<html:hidden property="xh" value="${rs.xh }"/>
								<html:hidden property="xmid" />
							</td>
							<th>
								����
							</th>
							<td>
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								�ֻ�
							</th>
							<td width="34%">
								<html:text property="sjhm" maxlength="15" onkeyup="value=value.replace(/[^\d]/g,'')" value="${xmsqInfo.sjhm }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>ѧ�Ѽ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:present name="xmtjList">
								<logic:iterate id="x" name="xmtjList" indexId="i">
									<%
										if (i != 0 && i %2 == 0){
									%>
									</tr>
									<tr>
									<%
										}
									 %>
									<td colspan="2">
										<input type="checkbox" name="tjid" value="${x.tjdm }"/>${x.tjmc }
									</td>
								</logic:iterate>
							</logic:present>
						</tr>
						
						<tr>
							<th>
								Ӧ��ѧ��
							</th>
							<td>
								<html:text property="yjxf" maxlength="10" onkeyup="checkInputNum(this)" value="${xmsqInfo.yjxf }"></html:text>
							</td>
							<th>
								����ѧ��
							</th>
							<td>
								<html:text property="jmxf" maxlength="10" onkeyup="checkInputNum(this)" value="${xmsqInfo.jmxf }"></html:text>
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����" id="buttonSave"
										onclick="saveUpdate('tjgy_xfjm.do?method=saveXmsq','');">
										��&nbsp;&nbsp;��
									</button>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										��&nbsp;&nbsp;��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if (t=="ok") {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			</script>
		</logic:present>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//����ѧ����ǰѧ���Ƿ���������Ŀ���ͨ��
				if ('xy' == '${userType}'){
					jQuery.post('tjgy_xfjm.do?method=checkSfktg',{xh:'${rs.xh }',xmid:'${xmsqInfo.xmid}'},function(data){
						
						if (data == 'false'){
							jQuery('#shtg').attr('disabled',true);
							jQuery('#shtg').attr('class','disabled');
							jQuery('#prompt').css('display','');
						}
						
					})
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/tjgy_xfjm" method="post">
			<html:hidden property="xh" value="${rs.xh }"/>
			<html:hidden property="xmid" value="${xmsqInfo.xmid}"/>
			<html:hidden property="xn" value="${xmsqInfo.xn}"/>
			<html:hidden property="pkValue" value="${xmsqInfo.xmid}${xmsqInfo.xh}${xmsqInfo.xn}"/>
		
			<logic:notEmpty name="xmsqInfo">
				<script defer>
					var sqtj = '${xmsqInfo.sqtj}'.split(',');
					for (var i = 0 ; i < sqtj.length; i++){
						jQuery('input[name=tjid][value='+sqtj[i]+']').attr('checked',true);
					}
				</script>
			</logic:notEmpty>
		
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" style="display:none" id="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ѧ����������ѧ�Ѽ�����Ŀ���ͨ����
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;overflow-x:0;height:550px"/>
				<table class="formlist" width="90%">
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
								${xmsqInfo.sjhm }
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
										<input type="checkbox" name="tjid" value="${x.tjdm }" disabled="disabled"/>${x.tjmc }
									</td>
								</logic:iterate>
							</logic:present>
						</tr>
						
						<tr>
							<th>
								Ӧ��ѧ��
							</th>
							<td>
								${xmsqInfo.yjxf }
							</td>
							<th>
								����ѧ��
							</th>
							<td>
								${xmsqInfo.jmxf }
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3" style="word-break:break-all;">
								${xmsqInfo.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="view" property="doType" name="xfjmForm">
							<logic:equal value="xy" name="userType">
								<tr>
									<th><bean:message key="lable.xb" />���</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="xyshyj" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.xyshyj }"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th><bean:message key="lable.xb" />���</th>
									<td>${xmsqInfo.xysh }</td>
									<th><bean:message key="lable.xb" />���ʱ��</th>
									<td>${xmsqInfo.xyshsj }</td>
								</tr>
								<tr>
									<th><bean:message key="lable.xb" />������</th>
									<td colspan="3" style="word-break:break-all;">
										${xmsqInfo.xyshyj }
									</td>
								</tr>
								<tr>
									<th>ѧУ���</th>
									<td>${xmsqInfo.xxsh }</td>
									<th>ѧУ���ʱ��</th>
									<td>${xmsqInfo.xxshsj }</td>
								</tr
								<tr>
									<th>ѧУ������</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="xyshyj" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.xxshyj }"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:notEqual>
						<logic:equal value="view" property="doType" name="xfjmForm">
							<tr>
								<th><bean:message key="lable.xb" />���</th>
								<td>${xmsqInfo.xysh }</td>
								<th><bean:message key="lable.xb" />���ʱ��</th>
								<td>${xmsqInfo.xyshsj }</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xb" />������</th>
								<td colspan="3" style="word-break:break-all;">
									${xmsqInfo.xyshyj }
								</td>
							</tr>
							<tr>
								<th>ѧУ���</th>
								<td>${xmsqInfo.xxsh }</td>
								<th>ѧУ���ʱ��</th>
								<td>${xmsqInfo.xxshsj }</td>
							</tr
							<tr>
								<th>ѧУ������</th>
								<td colspan="3" style="word-break:break-all;">
									${xmsqInfo.xxshyj }
								</td>
							</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" property="doType" name="xfjmForm">
										<button type="button" name="����" id="shtg" onclick="saveUpdate('tjgy_xfjm.do?method=saveXmdgsh&amp;shzt=ͨ��','');">
											ͨ&nbsp;&nbsp;��
										</button>
										<button type="button" name="����" id="shbtg" onclick="saveUpdate('tjgy_xfjm.do?method=saveXmdgsh&amp;shzt=��ͨ��','');">
											��ͨ��
										</button>
									</logic:notEqual>
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

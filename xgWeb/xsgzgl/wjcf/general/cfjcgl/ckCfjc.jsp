<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
		<script type="text/javascript">

		//����
		function fjxz(){
			var url="general_wjcf_cfjc_ajax.do?method=fjxz";
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
				
			
		}
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfjc_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<html:hidden property="xh" styleId="xh"  value="${rs.xh}" />
					<html:hidden property="cfId" styleId="cfId"  value="${wjsb.cfid}" />
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<html:hidden property="spgwId" styleId="spgwId" value="${spgw}" />
					<input type="hidden" name="fjmc" id="fjmc" value="${wjsb.fjmc}"/>
				<div  style="width:100%;height:620px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="18%">
							ѧ�ţ�
						</td>
						<td align="left" width="28%">
							${rs.xh}
						</td>
						<td align="right" width="20%">
							������
						</td>
						<td align="left" width="28%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
							<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							�꼶��
						</td>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />��
						</td>
							<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							רҵ��
						</td>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
							������ò��
						</td>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							${wjsb.xn}
						</td>
						<td align="right" width="20%">
							����ѧ�ڣ�
						</td>
						<td align="left" width="30%">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							����ԭ��
						</td>
						<td align="left">
							${wjsb.cfyymc}
							
						</td>
						<td align="right">
							�������
						</td>
						<td align="left">
							${wjsb.cflbmc}
						</td>
					</tr>
					<tr>
						<td align="right">
							Υ��ʱ�䣺
						</td>
						<td align="left">
							${wjsb.wjsj}
						</td>
						<td align="right">
							�������������ϻ򸽼���
						</td>
						<td align="left">
							<logic:notEmpty name="wjsb" property="fjmc">
								<a  href="#" onclick="fjxz();return false;" class="name">���ظ���</a>
							</logic:notEmpty>
						</td>
					</tr>
						<tr>
						<td align="right">
							Υ����ʵ������
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.wjssjg}
						</td>
					</tr>
						<tr>
						<td align="right">
							��ע��
						</td>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.bz}
						</td>
						</td>
					</tr>
					
					<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							��������ѧ��
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							��������ѧ�ڣ�
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							��������ʱ��
						</td>
						<td align="left" colspan="3">
							${cfss.sqsj}
						</td>
					</tr>
						<tr>
					<td align="right">
							������������
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfss.sqly}
						</td>
					</tr>
					<thead>
							<tr>
								<th colspan="4">
									<span>��������Ϣ</span>
								</th>
							</tr>
						</thead>
					<tr>
						<td align="right">
							���ֽ������ѧ��
						</td>
						<td align="left">
							${wjsb.xn}
						</td>
						<td align="right">
							���ֽ������ѧ�ڣ�
						</td>
						<td align="left">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
					<td align="right">
							���ֽ������ʱ��
						</td>
						<td align="left" colspan="3">
							${cfjc.sqsj}
						</td>
					</tr>
					<tr>
					<td align="right">
							���ֽ����������
						</td>
						<td align="left" colspan="3" style="word-break:break-all;width:100%">
							${cfjc.sqly}
						</td>
					</tr>
					
					
					<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>���ֽ��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
						<td colspan="5">
							<table class="formList" width="100%">
								
								<tbody align="center">
							<logic:notEmpty name="cfshList">
							<logic:iterate name="cfshList" id="s">
										<tr >
												<th>
												��λ����
												</th>
												<td >
												${s.gwmc}
												</td>
												<th>
												���״̬
												</th>
												<td >
												
												<logic:equal name="s" property="shzt" value="tg">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="wsh">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="btg">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="th">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="xcs">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
												</td>
												<th>
												���ʱ��
												</th>
											<td >
												${s.shsj}
											</td>
											<th>
												�����
											</th>
											<td>
												${s.shrxm}
											</td>
											</tr>
											<tr>
											<th>
												������
											</th>
											<td align="left" colspan="7" style="word-break:break-all;width:100%" >
												${s.shyj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
					</tbody>
					</table>
					</td>
					</tr>
					</tbody>
					</table>

					
				</table>
				</table>
				</div>
			
			<table width="100%" border="0" class="formlist" >
					
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</input>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

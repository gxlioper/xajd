<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
		<script type="text/javascript">

		//���� 
		function save(){
				refreshForm('general_wjcf_cfsb_ajax.do?method=updateCfsb');
			}


		function viewFj(obj){
			var url="general_wjcf_cfsb_ajax.do?method=fjxz&cfid="+obj;
			//refreshForm(url);
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			}

		//����
		function fjxz(){
			var url="general_wjcf_cfsb_ajax.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
		
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<html:hidden property="xh" value="${rs.xh}" />	
					<input type="hidden" name="fjmc" value="${wjsb.fjmc}"/>
					<html:hidden property="cfId" value="${wjsb.cfid}" />	
					<div  style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
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
						<th align="right" width="20%">
							ѧ�ţ�
						</th>
						<td align="left" width="30%">
							${rs.xh}
						</td>
						<th align="right" width="20%">
							������
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�Ա�
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							�꼶��
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />��
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							רҵ��
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�༶��
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							������ò��
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="80%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>�����ϱ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							����ѧ�꣺
						</th>
						<td align="left" width="30%">
							${wjsb.xn}
						</td>
						<th align="right" width="20%">
							����ѧ�ڣ�
						</th>
						<td align="left" width="30%">
							${wjsb.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							����ԭ��
						</th>
						<td align="left">
							${wjsb.cfyymc}
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${wjsb.cflbmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ��ʱ�䣺
						</th>
						<td align="left">
							${wjsb.wjsj}
						</td>
						<th align="right">
							�������������ϻ򸽼���
						</th>
						<td align="left">
							<logic:notEmpty name="wjsb" property="fjmc">
								<a  href="#" onclick="fjxz();return false;" class="name">���ظ���</a>
							</logic:notEmpty>
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ����ʵ������
						</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.wjssjg}
						</td>
					</tr>
						<tr>
						<th align="right">
							��ע��
						</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%" >
								${wjsb.bz}
						</td>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>���ܴ������</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td ><b>�������</b></td>
										<td ><b>����ԭ��</b></td>
										<td ><b>����ʱ��</b></td>
										<td><b>�����ĺ�</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand"> 
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
					</tbody>
					
					<thead>
							<tr>
								<th colspan="4">
									<span>������</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								
								<tbody align="center">
							<logic:notEmpty name="cfshList">
							<logic:iterate name="cfshList" id="s">
							
							
							<logic:notEmpty name="s" property="cfsj" >
										<tr  style="cursor:hand">
										<th>
										����û�
										</th>
										<td width="10%">
											${s.shrxm}
											</td>
											<th>
										���״̬
										</th>
											<td width="10%">
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
											<th >
										���ʱ��
										</th>
											<td width="10%" align="left">
												${s.shsj}
											</td>
											<th>
										����ʱ��
										</th>
											<td width="10%">
												${s.cfsj}
											</td>
											<th>
										�����ĺ�
										</th>
											<td width="10%">
												${s.cfwh}
											</td>
										</tr>
										<tr>
										<th>
										������
										</th>
										<td align="left" colspan="9" style="word-break:break-all;width:100%">
												${s.shyj}
											</td>
										</tr>
										</logic:notEmpty>
										<logic:empty name="s" property="cfsj" >
										
										<tr  style="cursor:hand">
										<th>
										����û�
										</th>
										<td width="15%">
											${s.shrxm}
											</td>
											<th>
										���״̬
										</th>
											<td width="15%">
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
											<th >
										���ʱ��
										</th>
											<td width="15%" colspan="5" align="left">
												${s.shsj}
											</td>
										</tr>
										<tr>
										<th>
										������
										</th>
										<td align="left"  colspan="9" style="word-break:break-all;width:100%">
												${s.shyj}
											</td>
										</tr>
										
										</logic:empty>
										
								</logic:iterate>
							</logic:notEmpty>
							
							
							
							<logic:empty name="cfshList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
					</tbody>
				
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
			<tfoot>
					<tr alignt="center">
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
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("δ���" != xxsh ) && (userType != "admin")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ϵ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/zgmsxy_xszz.do?method=gjzxdkshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function acount(){
			
			var xxdm = document.getElementById('xxdm').value;	
					
			if("10488"==xxdm){
				
				var dyxnzsf=$("dyxnzsf").value;
				var dyxnshf=$("dyxnshf").value;
				var dyxnxf=$("dyxnxf").value;
				if(dyxnzsf==null || dyxnzsf==""){
					dyxnzsf=0;
				}
				if(dyxnshf==null || dyxnshf==""){
					dyxnshf=0;
				}
				if(dyxnxf==null || dyxnxf==""){
					dyxnxf=0;
				}
				$("dyxnhj").innerHTML=eval(dyxnzsf)+eval(dyxnshf)+eval(dyxnxf);
			
				var dexnzsf=$("dexnzsf").value;
				var dexnshf=$("dexnshf").value;
				var dexnxf=$("dexnxf").value;
				if(dexnzsf==null || dexnzsf==""){
					dexnzsf=0;
				}
				if(dexnshf==null || dexnshf==""){
					dexnshf=0;
				}
				if(dexnxf==null || dexnxf==""){
					dexnxf=0;
				}
				$("dexnhj").innerHTML=eval(dexnzsf)+eval(dexnshf)+eval(dexnxf);
			
				var dsanxnzsf=$("dsanxnzsf").value;
				var dsanxnshf=$("dsanxnshf").value;
				var dsanxnxf=$("dsanxnxf").value;
				if(dsanxnzsf==null  || dsanxnzsf==""){
					dsanxnzsf=0;
				}
				if(dsanxnshf==null || dsanxnshf==""){
					dsanxnshf=0;
				}
				if(dsanxnxf==null || dsanxnxf==""){
					dsanxnxf=0;
				}
				$("dsanxnhj").innerHTML=eval(dsanxnzsf)+eval(dsanxnshf)+eval(dsanxnxf);
			
				var dsixnzsf=$("dsixnzsf").value;
				var dsixnshf=$("dsixnshf").value;
				var dsixnxf=$("dsixnxf").value;
				if(dsixnzsf==null || dsixnzsf==""){
					dsixnzsf=0;
				}
				if(dsixnshf==null  || dsixnshf==""){
					dsixnshf=0;
				}
				if(dsixnxf==null  || dsixnxf==""){
					dsixnxf=0;
				}
				$("dsixnhj").innerHTML=eval(dsixnzsf)+eval(dsixnshf)+eval(dsixnxf);
	
				var dwuxnzsf=$("dwuxnzsf").value;
				var dwuxnshf=$("dwuxnshf").value;
				var dwuxnxf=$("dwuxnxf").value;
				if(dwuxnzsf==null || dwuxnzsf==""){
					dwuxnzsf=0;
				}
				if(dwuxnshf==null  || dwuxnshf==""){
					dwuxnshf=0;
				}
				if(dwuxnxf==null  || dwuxnxf==""){
					dwuxnxf=0;
				}
				$("dwuxnhj").innerHTML=eval(dwuxnzsf)+eval(dwuxnshf)+eval(dwuxnxf);
		
			}
			
		}
		</script>
		</head>
	<body onload="checkWinType();document.all('buttonClose').focus();acount()">
		<html:form action="/zgmsxy_xszz.do?method=gjzxdkshSave" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��� - ������ѧ������� - �������</a>
				</p>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:510px">
			<table width="98%" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4">
							<span>������ѧ��������</span>
						</td>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="16%">
						ѧ��
					</th>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" />
						<input type="hidden" id="sfzh" name="sfzh"
							value="<bean:write name='rs' property="sfzh" />" />
					</td>
					<th width="16%">
						<div>
						  ����
						</div>
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�Ա�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>
						<div>
							����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							���֤��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<th>
						<div>
							���
						</div>
					</th>
					<td>
						<bean:write name="rs" property="nd"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
					<th>
						<div>
							רҵ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�༶
						</div>
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th>
						<div>
							�꼶
						</div>
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ϵ�绰
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
					<th>
						<div>
							��ͥ�ʱ�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥסַ
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
					</td>
					<th>
						<div>
							���׹�����λ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqgzdw"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<logic:equal value="10488" name="xxdm">
								����������
							</logic:equal>
							<logic:notEqual value="10488" name="xxdm">
								����������
							</logic:notEqual>
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqysr"/>
					</td>
					<th>
						<div>
							���׵绰
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqdh"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ĸ������
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
					</td>
					<th>
						<div>
							ĸ�׹�����λ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqgzdw"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<logic:equal value="10488" name="xxdm">
								ĸ��������
							</logic:equal>
							<logic:notEqual value="10488" name="xxdm">
								ĸ��������
							</logic:notEqual>
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqysr"/>
					</td>
					<th>
						<div>
							ĸ�׵绰
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqdh"/>
					</td>
				</tr>
				<logic:equal value="10488" name="xxdm">
				<tr>
					<th>
						<div>
							�������֤��
						</div>
					</th>
					<td>
							<bean:write name="rs" property="fqsfzh"/>
					</td>
					<th>
						<div>
							ĸ�����֤��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqsfzh"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
						<div>
							������
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sqje"/>
					</td>
					<th>
						<div>
							����ʱ��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>${rs.dkyh }</td>
					<th>��������</th>
					<td>${rs.dknx }</td>
				</tr>
				<tr>
					<th>�������еص�</th>
					<td colspan="3">${rs.dkyhdd }</td>
				</tr>
				<logic:equal value="10488" name="xxdm">
					<tr>
						<th>
							���������
						</th>
						<td colspan="3">
							<table width="100%" align="center">
								<tr>
									<th width="16%">
										<div align="center">
										ѧ��/��;
										</div>
									</th>
									<th width="16%">
										<div align="center">
										��һѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										�ڶ�ѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th>
									<th width="16%">
										<div align="center">
										����ѧ��
										</div>
									</th>
								</tr>
								<tr>
									<th>
										<div align="center">
											ѧ��
										</div>
									</th>
									<td>
										<bean:write name="rs" property="dyxnxf"/>
										<input type="hidden" name="dyxnxf"  maxlength="5" value="<bean:write name="rs" property="dyxnxf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dexnxf"/>
										<input type="hidden" name="dexnxf" maxlength="5" value="<bean:write name="rs" property="dexnxf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsanxnxf"/>
										<input type="hidden"  name="dsanxnxf" maxlength="5" value="<bean:write name="rs" property="dsanxnxf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsixnxf"/>
										<input type="hidden"  name="dsixnxf" maxlength="5"  value="<bean:write name="rs" property="dsixnxf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dwuxnxf"/>
										<input type="hidden"  name="dwuxnxf" maxlength="5"  value="<bean:write name="rs" property="dwuxnxf"/>"/>
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											ס�޷�
										</div>
									</th>
									<td>
										<bean:write name="rs" property="dyxnzsf"/>
										<input type="hidden" name="dyxnzsf"  maxlength="5" value="<bean:write name="rs" property="dyxnzsf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dexnzsf"/>
										<input type="hidden" name="dexnzsf" maxlength="5" value="<bean:write name="rs" property="dexnzsf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsanxnzsf"/>
										<input type="hidden" name="dsanxnzsf" maxlength="5" value="<bean:write name="rs" property="dsanxnzsf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsixnzsf"/>
										<input type="hidden" name="dsixnzsf" maxlength="5"  value="<bean:write name="rs" property="dsixnzsf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dwuxnzsf"/>
										<input type="hidden" name="dwuxnzsf" maxlength="5"  value="<bean:write name="rs" property="dwuxnzsf"/>"/>
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											�����
										</div>
									</th>
									<td>
										<bean:write name="rs" property="dyxnshf"/>
										<input type="hidden" name="dyxnshf" maxlength="5" value="<bean:write name="rs" property="dyxnshf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dexnshf"/>
										<input type="hidden" name="dexnshf" maxlength="5" value="<bean:write name="rs" property="dexnshf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsanxnshf"/>
										<input type="hidden" name="dsanxnshf" maxlength="5" value="<bean:write name="rs" property="dsanxnshf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dsixnshf"/>
										<input type="hidden"  name="dsixnshf" maxlength="5"  value="<bean:write name="rs" property="dsixnshf"/>"/>
									</td>
									<td>
										<bean:write name="rs" property="dwuxnshf"/>
										<input type="hidden"  name="dwuxnshf" maxlength="5"  value="<bean:write name="rs" property="dwuxnshf"/>"/>
									</td>
								</tr>
								<tr>
									<th>
										<div align="center">
											�ϼ�
										</div>
									</th>
									<td>
										<span id="dyxnhj"></span>
									</td>
									<td>
										<span id="dexnhj"></span>
									</td>
									<td>
										<span id="dsanxnhj"></span>
									</td>
									<td>
										<span id="dsixnhj"></span>
									</td>
									<td>
										<span id="dwuxnhj"></span>
									</td>
								</tr>
							</table>
						</td>
					
				</tr>
				<tr>
					<th>��֤������</th>
					<td>
						<bean:write name="rs" property="jzrxm"/>
					</td>
					<th>
						�������˹�ϵ
					</th>
					<td>
						<bean:write name="rs" property="yjzrgx"/>
					</td>
				</tr>
				<tr>
					<th>��֤����ϵ�绰</th>
					<td>
						<bean:write name="rs" property="jzrlxdh"/>
					</td>
					<th>
						��֤�˼�ͥסַ
					</th>
					<td>
						<bean:write name="rs" property="jzrzz"/>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
				<tr>
					<th>
						<div>
							��˽��
						</div>
					</th>
					<td>
						<html:select name="rs" property="xysh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
						<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
					</td>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />���
						</div>
					</th>
					<td>
						<bean:write name='rs' property="xysh" />
						<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
					</td>
					<th>
						<div>
							��˽��
						</div>
					</th>
					<td>
						<html:select name="rs" property="xxsh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
				<tr>
					<th>
						<div>
							������
						</div>
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='2'
								style="width:95%;word-break:break-all;" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value=""/>
						<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</th>
					<td colspan="3">
						<bean:write name='rs' property="xyshyj" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧУ������
						</div>
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='2'
								style="width:100%;word-break:break-all;" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="" />
						<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
					</td>
				</tr>
				</logic:equal>
				</tbody>
				
			</table>
			</div>
			<table width="98%" class="formlist">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">
<%--			        	"<span class="red">*</span>"Ϊ������</div>--%>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="yz()">�� ��</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
	</body>
</html>

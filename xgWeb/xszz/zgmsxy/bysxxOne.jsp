<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
		
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zgmsxy_xszz.do?method=bysshSave');
			$("buttonSave").disabled=true;
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/zgmsxy_xszz.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ������ - ��� - ��ҵ����Ϣ��� - �������</a>
				</p>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<div class="tab">
			<table width="98%" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>������ѧ�����ҵ��������Ϣ�ɼ���</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						ѧ��
					</th>
					<td>
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					
					<th>
						��ҵ����
					</th>
					<td>
						<div align="left">
							<bean:write name="rs" property="byny" />
						</div>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<th>�Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>

				<tr>
					<th>
						���֤��
					</th>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
					
					<th>
						��������
					</th>
					<td>
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<th>
						��ͥ��ַ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						<bean:write name="rs" property="fqxm" />
					</td>
					<th>
						������ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="fqdh" />
					</td>
				</tr>
				<tr>
					<th>
						���׹�����λ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="fqgzdw" />
					</td>
				</tr>
				<tr>
					<th>
						ĸ������
					</th>
					<td>
						<bean:write name="rs" property="mqxm" />
					</td>
					<th>
						ĸ����ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="mqdh" />
					</td>
				</tr>
				<tr>
					<th>
						ĸ�׹�����λ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mqgzdw" />
					</td>
				</tr>
				<tr>
					<th>
						�״α�ҵȥ��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="brjyqxhdw" />
					</td>
				</tr>
				<tr>
					<th>
						��ǰ������λ����ַ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="dqgzdwjdz" />
					</td>
				</tr>
				<tr>
					<th>
						��ǰ������λ�ʱ�
					</th>
					<td>
						<bean:write name="rs" property="dqgzdwyb" />
					</td>
					<th>
						��ǰ������λ�绰
					</th>
					<td>
						<bean:write name="rs" property="dqgzdwdh" />
					</td>
				</tr>
				<tr>
					<th>��ͥ�绰</th>
					<td><bean:write name="rs" property="jtgddh" /></td> 
					<th>�ֻ�</th>
					<td><bean:write name="rs" property="lxdh" /></td>
				</tr>
				<tr>
					<th>���估QQ��</th>
					<td colspan="3"><bean:write name="rs" property="brdzyxjdzlxfs" /></td>
				</tr>
				<tr>
					<th>
						��ϵ��ʽ������
					</th>
					<td colspan="3">
						<bean:write name="rs" property="lxfsbgqk" />
					</td>
				</tr>

				<logic:equal name="userType" value="xy">
					<tr>
						<th>
							<bean:message key="lable.xb" />���
						</th>
						<td colspan="3">
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</tr>					
					<tr>
						<th>
							<bean:message key="lable.xb" />������
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xyshyj" rows='3'
								style="width:100%;word-break:break-all;" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value=""/>
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<th>
							<bean:message key="lable.xb" />���
						</th>
						<td>
							<bean:write name='rs' property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
						<th>
							ѧУ���
						</th>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					
					<tr>
						<th>
							<bean:message key="lable.xb" />������
						</th>
						<td colspan="3">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<th>
							ѧУ������
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xxshyj" rows='3'
								style="width:100%;word-break:break-all;" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value=""/>
						</td>
					</tr>
				</logic:equal>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			        	<logic:notEqual name="userType" value="admin">
			        	<logic:equal name="rs" property="xxsh" value="δ���">
						<button type="button" onclick="yz()" id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="δ���">
						<button type="button" onclick="yz()" id="buttonSave" disabled="disabled">
							�� ��
						</button>
					</logic:notEqual>
			        </logic:notEqual>
			        <logic:equal name="userType" value="admin">
					<button type="button" onclick="yz()" id="buttonSave">
						�� ��
					</button>
					</logic:equal>
			            <button type="button" name="�ر�" onclick="window.close();return false;" id="buttonClose">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
<!--			<div class="buttontool" align="center">-->
<!--				<logic:notEqual name="userType" value="admin">-->
<!--					<logic:equal name="rs" property="xxsh" value="δ���">-->
<!--						<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--							id="buttonSave">-->
<!--							�� ��-->
<!--						</button>-->
<!--					</logic:equal>-->
<!--					<logic:notEqual name="rs" property="xxsh" value="δ���">-->
<!--						<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--							id="buttonSave" disabled="disabled">-->
<!--							�� ��-->
<!--						</button>-->
<!--					</logic:notEqual>-->
<!--				</logic:notEqual>-->
<!--				<logic:equal name="userType" value="admin">-->
<!--					<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--						id="buttonSave">-->
<!--						�� ��-->
<!--					</button>-->
<!--				</logic:equal>-->
<!--				&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--				<button type="button" class="button2"-->
<!--					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"-->
<!--					style="width:80px" id="buttonClose">-->
<!--					�� ��-->
<!--				</button>-->
<!--			</div>-->
		</html:form>
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
	</body>
</html>

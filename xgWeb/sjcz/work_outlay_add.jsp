<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function checkThenSubmit(url, pkFields) {
		var eles = pkFields.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
		if (confirm('ȷ��Ҫ�Ծ����������ݽ��б�����')) {
			document.forms[0].action = url;
			document.forms[0].submit();
			return true;
		}
		
	}
	</script>
</head>
	<body>
		<html:form action="/comm_pub" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
						<a>
							<%--��ɳ����--%>
							<logic:equal value="10827" name="xxdm">
								ѧ���幤 - ���ѹ��� - ��������
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								�ڹ���ѧ - ���ѹ��� - ��������
							</logic:notEqual>
						</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div class="tab">
		  		<table width="100%" border="0" class="formlist">
				<thead>
					<tr align="center">
						<th colspan="4">
							<span>��<bean:message key="lable.xsgzyxpzxy" />����λ�ڹ���������</span>
						</th>
					</tr>
				 </thead>
				 <tbody>
					<tr>
						<th>ѧ��</th>
						<td>
							<html:text name="rs" property="xn" styleId="xn" style="width: 90px" readonly="true"/>
						</td>						
						<th>���</th>					
						<td>
							<html:text name="rs" property="nd" styleId="nd" style="width: 90px" readonly="true"/>
						</td>						
					</tr>
					<tr>
						<th>ѧ��</th>
						<td>
							<html:text name="rs" property="xqmc" styleId="xqmc" style="width: 90px" readonly="true"/>
							<html:hidden name="rs" property="xq" styleId="xq" style="width: 90px"/>
						</td>
						<logic:equal value="11417" name="xxdm">
						<th><span class="red">*</span>������λ</th>
						<td>
							<html:select name="rs" property="yrdwdm" styleId="yrdwdm" style="width:180px" onchange="getGwDetInfo()">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
						</logic:equal>
					
						<logic:notEqual value="11417" name="xxdm">
						<th><span class="red">*</span>���˵�λ</th>
						<td>
							<html:select name="rs" property="yrdwdm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
						</logic:notEqual>
					</tr>
					<logic:present name="showbjlh">
						<tr>
							<td height="22" align="right">��������</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">��λ��</td>
							<td height="22" align="left"></td>
						</tr>
						<tr>
							<td height="22" align="right">������</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">�ѻ�����������</td>
							<td height="22" align="left"></td>
						</tr>
						<tr>
							<td height="22" align="right">��ʹ����</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">����</td>
							<td height="22" align="left"></td>
						</tr>
					</logic:present>
					<tr>
						<th><span class="red">*</span>�������</td>
						<td>
							<html:text name="rs" property="hbje" styleId="hbje" maxlength="8" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>(Ԫ)
						</td>
						<th><span class="red">*</span>��λ����</th>
						<td>
							<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="gwxzList" property="gwxzdm"
										labelProperty="gwxzmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" style="width:85%" rows="5" onblur="chLeng(this,'250')"></html:textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <logic:notPresent name="showbjlh">
							<button type="button" onclick="checkThenSubmit('/xgxt/work_outlay_add.do?dotype=add','xn-nd-xq-yrdwdm-hbje-gwxzdm');">����
							</button>
						</logic:notPresent>

						<logic:present name="showbjlh">
							<button type="button" onclick="if(identifyInt()){checkThenSubmit('/xgxt/work_outlay_add.do?dotype=add','xn-xq-yrdwdm-hbje-gwxzdm')};">����
							</button>
						</logic:present>
			          </div>
			        </td>
			      </tr>
			    </tfoot>	
				</table>
				</div>
			</logic:notEmpty>

			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("����ɹ�!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("����ʧ��!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

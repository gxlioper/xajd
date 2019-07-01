<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>		
	</head>
	<body onload="loadView()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ��Ԣ����Ա��Ϣ - ��Ԣ����Ա��Ϣά��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/lzxx_add" method="post">
		    <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			 <input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />	
		 	<input type="hidden" id="isView" name="isView"
				value="<bean:write name="isView" scope="request"/>" />			 	
		 	

			<!-- ��Ԣ����Ա��Ϣά�� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��Ԣ����Ա��Ϣά��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr valign="middle">
						<td align="right" width="15%">
							<font color="red">*</font>��Ԣ����Ա��
						</td>
						<td align="left">
							<html:select name="rs" property="yhm" style="width:150px"
								onchange="refreshForm('/xgxt/lzxx_add.do');">
								<html:option value=""></html:option>
								<html:options collection="yhList" property="yhm"
									labelProperty="xm" />
							</html:select>
						</td>
						<td align="right" width="25%">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<logic:equal value="modi" name="doType">
								<html:select name="rs" property="xn" style="width:100px" styleId="xn"
									disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<input type="hidden" name="xn" value="<bean:write name="rs" property="xn"/>"> 
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:select name="rs" property="xn" style="width:100px" styleId="xn"
									>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
	                 <tr valign="middle">
						<td align="right" width="25%">
							�����飺
						</td>
						<td align="left">
							<bean:write name='rs' property="zmc" />
						</td>
						<td align="right" width="25%">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<logic:equal value="modi" name="doType">
								<html:select name="rs" property="xq" style="width:100px" styleId="xq"
									disabled="true">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<input type="hidden" name="xq" value="<bean:write name="rs" property="xq"/>"> 
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:select property="xq" style="width:100px" styleId="xq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr valign="middle">
						<td align="right">
							�������ţ�
						</td>
						<td align="left">
							<bean:write name='rs' property="bmmc" />
						</td>
						<td align="right">
							У������
						</td>
						<td align="left">
							<logic:equal value="modi" name="doType">
								<html:select name="rs" property="xqdm" style="width:150px"
									disabled="true">
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
								<input type="hidden" name="xqdm" value="<bean:write name="rs" property="xqdm"/>"> 
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:select name="rs" property="xqdm" style="width:150px" onchange="getLdLb()"> 
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
							</logic:notEqual>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������λ��
						</td>
						<td align="left">
							<bean:write name='rs' property="dwmc" />
						</td>
						<td align="right" nowrap>
							<font color="red">*</font>¥������
						</td>
						<td align="left">
							<logic:equal value="modi" name="doType">
								<html:select name="rs" property="lddm" style="width:120px"
									disabled="true">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								<input type="hidden" name="lddm" value="<bean:write name="rs" property="lddm"/>"> 
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:select name="rs" property="lddm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
							</logic:notEqual>
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							����Ա�����ң�
						</td>
						<td align="left">
							<html:text name="rs" property="gzs" styleId="gzs" maxlength="25"></html:text>
						</td>
						<td align="right" nowrap>
							�������䣺
						</td>
						<td align="left">
							<html:text name="rs" property="dzyx" styleId="dzyx" maxlength="50"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="lxdh" styleId="lxdh" style="width:300px" maxlength="60"></html:text>
						</td>
						
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ע��<br>(��200��)
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" styleId="bz" cols="65" rows="6" ></html:textarea>
						</td>
						
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="dataSave()"
											style="width: 80px">
											��	��
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										��	��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</fieldset>
		</html:form>
<logic:equal value="ok" name="done">
<script language="javascript">
alert("�����ɹ���");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("����ʧ�ܣ�");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>

<script type="text/javascript">
 function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
 }
 function dataSave(){
    var dzyx = document.getElementById('dzyx').value;
	if(!isEmail(dzyx) && dzyx!=""){
		alert("��������ȷ�ĵ�������!");
		return false;
	}
	if($("bz").value.length>200){
	   alert("��ע�������ܴ���200�֣�");
	   return false;
	}
	if(IsNoEmpty('xn-xq-xqdm-lddm')){
        refreshForm('/xgxt/lzxx_add.do?doType=save');
    }
		
 }
 
 </script>
</html>
		

		

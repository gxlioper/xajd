<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
	<script type="text/javascript" >
	function yz(){
		if(checkSearchTj('kssj','jxxj')){
			dtjsCommonSave('szdw_zjlg.do?method=xxFkSq&doType=save','','','zgh-pxxm-kssj-jssj');
		}
	}
	</script>
</head>
<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>˼������ -  ��Ϣ���� - ����Ա�걨 - ��ѵ��Ϣ</a>
		</p>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				���ڲ�������ʱ���ڣ�--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdw_zjlg" method="post">
		<input type="hidden" id="xxfkdm" name="xxfkdm"
			value="<bean:write name = "xxfkdm" />" />	
		<input type="hidden" id="act" name="act"
			value="${act}" />
		<input type="hidden" id="pk" name="pk"
			value="<bean:write name = "pk"/>" />
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("��ͨ��ѧУ��ˣ��������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<div class="tab">
		<table class="formlist" width="90%" id="theTable">
			<thead><tr><th colspan="4"><span>��Ϣά��</span></th></tr></thead>
			<tbody>
			<logic:notPresent name="isSzdw">
			<tr>
				<th>
					��������
				</th>
				<logic:notEqual name="userType" value="admin" scope="session">
					<td>
						<input type = "hidden" name = "bmdm" value ="${bmdm }" />
						<html:select name = "rs" property="bmdm" styleId="bmdm" disabled="true" style="width:50%" >
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:notEqual>
				<logic:equal name="userType" value="admin" scope="session">
					<td>
						<html:select name = "rs" property="bmdm" styleId="bmdm" onchange="getFdyList()" style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:equal>
				<th>
					<font color="red">*</font>����Ա
				</th>
					<td width="34%">
						<html:select  property="zgh"  styleId="zgh" style="width:150px" onchange="refreshForm('szdw_zjlg.do?method=xxFkSq')" styleId="zgh">
								<html:option value=""></html:option>
								<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
						<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
					</td>
			</tr>
			</logic:notPresent>
			<logic:present name="isSzdw">
				<tr>
				<th>
					��������
				</th>
				<td>
					 <bean:write name="rs" property="bmmc" /> 
				</td>
				<th>
					<font color="red">*</font>����Ա
				</th>
					<td width="34%">
						<bean:write name="rs" property="zgh"/>
						<input type="hidden" name="zgh" id="zgh" value="<bean:write name="rs" property="zgh"/>" />
					</td>
			</tr>
			</logic:present>
			<tr>
				<th>
					�Ա�
				</th>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
				<th>
					�ϸ�����
				</th>
				<td>
					<bean:write name="rs" property="lxgzsj"/>
				</td>
			</tr>
			<tr>
				<th>
					����
				</th>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				<th>
					������ò
				</th>
				<td>
					<bean:write name="rs" property="zzmm"/>
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td>
					<bean:write name="rs" property="csrq"/>
				</td>
				<th>
					����
				</th>
				<td>
					<bean:write name="rs" property="sfmc"/>
				</td>
			</tr>
			<tr>
				<th>
					ְ��
				</th>
				<td>
					<bean:write name="rs" property="zwmc"/>
				</td>
				<th>
					��ҵԺУ
				</th>
				<td>
					<bean:write name="rs" property="byyx"/>
				</td>
			</tr>
			<tr>
				<th>
					ѧ��
				</th>
				<td>
					<bean:write name="rs" property="xl"/>
				</td>
				<th>
				</th>
				<td></td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>��ѵ��Ŀ
				</th>
				<td align="center" colspan="3">
					<html:text name="xxfkrs" property="pxxm" style="width:100%" maxlength="50" styleId="pxxm"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>��ʼʱ��
				</th>
				<td>
					<html:text name = "xxfkrs" property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;" readonly = "true"
								onclick="return showCalendar('kssj','y-mm-dd');" />
				</td>
				<th>
					<font color="red">*</font>����ʱ��
				</th>
				<td>
					<html:text name = "xxfkrs" property="jssj" styleId="jxxj"
								onblur="dateFormatChg(this)" style="cursor:hand;" readonly = "true"
								onclick="return showCalendar('jxxj','y-mm-dd');" />
				</td>
			</tr>
			<tr id = "jtnr">
				<th>
					��������
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="jtnr" rows='15' style="width: 95%;word-break:break-all;" onblur="chLeng(this,600)"/>
				</td>
			</tr>
			<tr id = "bz">
				<th>
					��ע
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="bz" rows='3' style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					 <logic:notEqual value="view" name="act">
						<button type="button" onclick="yz()">
							�ύ
						</button>
					 </logic:notEqual>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('����ɹ���');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�,��ȷ�ϸø���Ա�Ƿ�������');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
		alert(''+document.getElementById('msg').value);
	</script>
</logic:present>
</html>

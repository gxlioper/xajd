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
		<script type="text/javascript">
		function yz(){
		if(checkSearchTj('kssj','jssj')){
			dtjsCommonSave('szdw_zjlg.do?method=xxFkChek&doType=save','','','zgh');
			}
		}
		</script>
	</head>
<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>����Ա��ѵ��Ϣ</a>
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
		<input type="hidden" id="pkValue" name="pkValue"
			value="<bean:write name = "pkValue"/>" />
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
			<thead><tr><th colspan="4"><span>������Ϣά��</span></th></tr></thead>
			<tbody>
			<tr>
				<th>
						��������
				</th>
				<td align="left" width="34%">
						<bean:write name = "rs" property="bmmc"/>
						<input type = "hidden" name = "bmdm" value="<bean:write name = "rs" property="bmdm"/>" /> 
				</td>
				<th>
					ְ����
				</th>
					<td width="34%">
						<bean:write name = "rs" property="zgh"/>
						<input type = "hidden" name = "zgh" value="<bean:write name = "rs" property="zgh"/>" /> 
					</td>
			</tr>
			<tr>
				<th>
					����
				</th>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
				<th> 
					�Ա�
				</th>
				<td>
					<bean:write name="rs" property="xb"/>
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
					����������
				</th>
				<td>
					<bean:write name="rs" property="fdyzmc"/>
				</td>
			</tr>
			<logic:notEqual value="view" name="act">
			<tr>
				<th>
					���״̬
				</th>
				<td>
					<html:select name = "xxfkrs" property="shzt" styleId="shzt">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>	
				</td>
				<td align="center">
				</td>
				<td>
				</td>
			</tr>
			</logic:notEqual>
			<logic:equal value="view" name="act">
			<tr>
				<th>
					���״̬
				</th>
				<td>
					<bean:write name = "xxfkrs" property="shzt" />
				</td>
				<td align="center">
				</td>
				<td>
				</td>
			</tr>
			</logic:equal>
			<tr>
				<th>
					��ѵ��Ŀ
				</th>
				<td colspan="3">
					<bean:write name="xxfkrs" property="pxxm"/>
					<input type="hidden" name="pxxm" value="<bean:write name="xxfkrs" property="pxxm"/>" />
				</td>
			</tr>
			<tr>
				<th>
					��ʼʱ��
				</th>
				<td>
					<html:text name = "xxfkrs" property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;" readonly = "true"
								onclick="return showCalendar('kssj','y-mm-dd');" />
				</td>
				<th>
					����ʱ��
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
					<html:textarea name="xxfkrs" property="jtnr" rows='15' style="width: 95%;word-break:break-all;" onblur="chLeng(this,600)" readonly="true"/>
				</td>
			</tr>
			<tr id = "bz">
				<th>
						��ע
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="bz" rows='3' style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)" readonly="true"/>
				</td>
			</tr>
			</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						 	<logic:notEqual value="view" name="act">
							<button type="button" name="�ύ" onclick="yz()">�ύ</button>
						  </logic:notEqual>
						  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('��˳ɹ���');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('���ʧ��');
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

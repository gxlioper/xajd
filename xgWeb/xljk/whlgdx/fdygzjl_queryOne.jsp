<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">	
		function addly(){
			var zt = document.getElementById("zt").value;
			if(zt == ""){
				alert("�Բ�������д���⣡");
				return;
			}
			var nr = document.getElementById('nr').value = frames('eWebEditor1').getHTML();
			if(nr==""){
			 	alert("�Բ��𣬲��ܷ���ռ�¼��");
			 	return false;
		 	}
			document.forms[0].action = "/xgxt/whlgdx_xljk.do?method=fdygzjlpre&doType=add";
			document.forms[0].submit();
		}	
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>������ - <bean:message key="lable.xsgzyxpzxy" />���������� - ����Ա������¼ - ������¼ά��</a>
		</p>
	</div>
	<html:form action="/whlgdx_xljk.do?method=fdygzjlpre" method="post">
		<%--
			<input type="hidden" id="userOnLine" name="userOnLine"
				value="<bean:write name="userOnLine" scope="session"/>" />	
		--%>
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal"/>" />
		<input type="hidden" id="nr" name="nr"/>
		<div class="tab">	
		<table class="formlist" align="center" width="100%">
		<tbody>
			<tr style="height:22px"><%--
				<td align="center">
					�û��ʺţ�
				</td>
				<td >
					<html:text property="zgh" styleId="zt" name="rs" />
				</td>
				--%>
				<th align="right">
					<font color="red">*</font>���⣺
				</th>
				<td>
					<html:text style="width:99%" property="zt" styleId="zt" name="rs" />
				</td>
			</tr>	
			<tr>
				<th align=right width="100">
					<font color="red">*</font>�༭����
				</th>
				<td align=center>
					<input type="hidden" name="content1" value="<bean:write name="rs" property="nr"/>"/>		
					<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
						scrolling="no" width="100%" height="350"></iframe>
				</td>			
			</tr>
			<tr style="height:22px">
				<th align="right">
					��ע
				</th>
				<td>
					<html:textarea property="bz" styleId="bz" 
					style="width: 98%" styleClass="inputtext" rows="5" onblur="chLeng(this,'500')"></html:textarea>
				</td>
			</tr>	
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			      	  <logic:equal value="fdy" name="userType" scope="request">
			          		<button name="�ύ" onclick="addly();" id="buttonSave">�� ��</button>
			        </logic:equal>
			            <button name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<div id="tmpdiv"></div>
</body>
<logic:present name="ok">
	<logic:match value="ok" name="ok">
		<script language="javascript">
	   			alert("����ɹ���");
	   			dialogArgumentsQueryChick();;
	   			Close();
	        </script>
	</logic:match>
	<logic:match value="no" name="ok">
		<script language="javascript">
	        	alert("����ʧ�ܣ�");
	        </script>
	</logic:match>
</logic:present>
</html>

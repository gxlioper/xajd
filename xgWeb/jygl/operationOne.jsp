<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>��ҵ������Ϣϵͳ</title>
				<%
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	</head>
	<script language="javascript">
	function savejyxx(){
      	 var qybz = '';
      	 var dwmc ='';
      	 var sfjy ='' ;
      	 
      	 if (document.getElementById('qybz')) {
      	 	qybz = document.getElementById('qybz').value;
      	 }
      	 
      	 if (document.getElementById('dwmc')) {
      	 	dwmc = document.getElementById('dwmc').value;
      	 }
      	 
      	 if (document.getElementById('sfjy')) {
      	 	sfjy = document.getElementById('sfjy').value;
      	 }
      	 
		 document.forms[0].action = "/xgxt/jyxxlr.do?doType=save";
		 document.forms[0].submit();
		 BatAlert.showTips('�����ύ�����Ժ�...');
    }
	</script>
	
    <script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<body>
		<html:form action="/jyxxlr" method="post">
			<table width="100%" class="tbstyle">
				<tr>
					<td align="right">
						ѧ�ţ�
					</td>
					<td colspan="1">
						<html:text name="rs" property="xh" readonly="true"/>
					</td>
					<td align="right">
						������
					</td>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td>
						${rs.xb}
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td align="right">
						רҵ���ƣ�
					</td>
					<td>
						${rs.zymc}
					</td>
					<td align="right">
						�༶���ƣ�
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<%--<tr>
					<td align="right">
						��ȣ�
					</td>
					<td>
						${rs.nd}<input type="hidden" name="nd" value="${rs.nd}"/>
					</td>
					<td align="right">
						ѧ����Σ�
					</td>
					<td>
						${rs.xsccmc}
					</td>
				</tr>
				--%><logic:notEqual value="xy" name="userType">
					<tr>
					<td align="right">
						ǩԼ��־��
					</td>
					<td>
						<html:select name="rs" property="qybz" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="byqxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
						<td align="right">
							�Ƿ�ǩԼ��
						</td>
						<td>
							<html:select name="rs" property="sfqy" style="width:100px">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>	
				</tr>
				</logic:notEqual>
				
				<tr>
					<td align="right">
						��λ���ƣ�
					</td>
					<td>
						<html:text name="rs" property="dwmc"  maxlength="50" />
					</td>	
					<td align="right">
						��λ��ַ��
					</td>
					<td>
						<html:text name="rs" property="dwdz"  maxlength="100" />
					</td>
					
				</tr>
				<tr>
					<td align="right">
						רҵ�Կڣ�
					</td>
					<td>
						<html:select name="rs" property="zydk" style="width:100px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="right">
						�Ƿ��ҵ��
					</td>
					<td>
						<html:select name="rs" property="sfjy" style="width:100px">
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ҵ����
					</td>
					<td colspan="3">
						<html:select name="rs" property="jyqy" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="syshi" styleId="syshi"
								onchange="loadXian('syshi','syxian')">								
								<html:options collection="syshiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="syxian" styleId="syxian">								
								<html:options collection="syxianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
					</td>
				</tr>
				<logic:notEqual value="xy" name="userType">
				<tr>		
					<td align="right">
						��λ��ϵ�绰��
					</td>
					<td>
						<html:text name="rs" property="lxdh"  maxlength="50" />
					</td>
					<td align="right">
						��λ��ϵ�ˣ�
					</td>
					<td>
						<html:text name="rs" property="lxr"  maxlength="25" />
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div align="center">
				<logic:notEqual value="view" name="lx">
				<button class="button2" onclick="savejyxx()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button class="button2" onclick="Close();return false;" style="width:80px">
					�� ��
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="result">
			<logic:equal name="result" value="true">
				    <script>
                      alert("�ύ�ɹ���");
                      Close();
                      window.dialogArguments.document.getElementById('query_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
                      alert("����ʧ��!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
<script type="text/javascript">
<!--
	function countFs() {
		var xycpf = document.getElementById('xycpf').value;
		var xwbxf = document.getElementById('xwbxf').value;
		var tcbxf = document.getElementById('tcbxf').value;
		gzdxpjpy.countBxfZf(xycpf,xwbxf,tcbxf,function(data) {
			if (data != null) {
				document.getElementById("zhbxf").value=data[0];
				document.getElementById("zf").value=data[1];
			}
		});
	}	
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/gzdxPjpy" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰλ��:�������� - �۲���Ϣά�� - �ۺ����ʲ��� 
       		</div>
    	</div>
			
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�����޸�
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						${rs.xh }
				</td>
					<td align="right">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
						ѧҵ�����֣�
					</td>
					<td align="left">
						<html:text property="xycpf" styleId="xycpf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<td align="right">
						��Ϊ�����֣�
					</td>
					<td align="left">
						<html:text property="xwbxf" styleId="xwbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<td align="right">
						ͻ�������֣�
					</td>
					<td align="left">
						<html:text property="tcbxf" styleId="tcbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<td align="right">
						�ۺϱ��ַ֣�
					</td>
					<td align="left">
						<html:text property="zhbxf" styleId="zhbxf" onkeyup="ckinpdata(this)" style="width:90px" onblur="countFs()"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						�ۺ����ʲ����ܷ֣�
					</td>
					<td align="left">
						<html:text property="zf" styleId="zf" onkeyup="ckinpdata(this)" style="width:90px"></html:text>
					</td>
				</tr>
				<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							�����ְܷ༶������
						</td>
						<td align="left">
							${rs.bjpm }
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
			<logic:notEqual value="yes" name="view">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_gzdx_modiZhszcpxx.do?operType=save','xh-xn');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/zgkd/zgkd.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyzgkdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��Ϣά�� - ���ʲ���
       		</div>
    	</div>
			<input type="hidden" id="act" value="save"/>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�����޸�
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="15%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="35%">
						<html:text name='rs' property="xh"
								styleId="xh" readonly="true"/>
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
						Ʒ�²����÷֣�
					</td>
					<td align="left">
						<html:text property="pdcpdf" styleId="pdcpdf" onblur="ckinpdata(this);"></html:text>
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
						�γ�ѧϰ�ɼ������÷֣�
					</td>
					<td align="left">
						<html:text property="kcxxcjdf" styleId="kcxxcjdf" onblur="ckinpdata(this);"></html:text>
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
						���Ĳ����÷֣�
					</td>
					<td align="left">
						<html:text property="sxcpdf" styleId="sxcpdf" onblur="ckinpdata(this);"></html:text>
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
						�������ʲ����÷֣�
					</td>
					<td align="left">
						<html:text property="jbszcpdf" styleId="jbszcpdf" onblur="ckinpdatanum(this);" maxlength="5"></html:text>
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
						�������ʲ����ȼ���
					</td>
					<td align="left">
						<html:select property="jbszcpdj" styleId="jbszcpdj" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="szdjList" property="szdjdm" labelProperty="szdjmc"/>
						</html:select>
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
							��չ���ʲ����÷֣�
						</td>
						<td align="left">
							<html:text property="fzszcpdf" styleId="fzszcpdf" onblur="ckinpdatanum(this);" maxlength="5"></html:text>
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
			<logic:notEqual value="view" name="act">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_zgkd_zhszcpview.do?act=save','xh-xn');"
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
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��Ϣά�� - �ۺ����ʲ���
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����޸�
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="35%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true" />
				</td>
				<td align="right" width="18%">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left" width="32%">
					<html:select property="xn" styleId="xn" style="width:95px">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					�����ɼ���
				</td>
				<td align="left">
					<html:text property="zcj"  styleId="zcj" onkeyup="ckinpdata(this)" readonly="true" style="width:90px" maxlength="6"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					�����ɼ���
				</td>
				<td align="left">
					<html:text property="tcj" styleId="tcj" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					�����ɼ���
				</td>
				<td align="left">
					<html:text property="dcj"  styleId="dcj" onkeyup="ckinpdata(this)" style="width:90px"  maxlength="6"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					�����ɼ���
				</td>
				<td align="left">
					<html:text property="mcj"  styleId="mcj" onkeyup="ckinpdata(this)" style="width:90px"  maxlength="6"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					ѧ�ּ���
				</td>
				<td align="left">
					<html:text property="xf"  styleId="xf" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�����֣�
				</td>
				<td align="left">
					<html:text property="jlf"  styleId="jlf" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
				<td align="right">
					�ͷ��֣�
				</td>
				<td align="left">
					<html:text property="cff"  styleId="cff" onkeyup="ckinpdata(this)" style="width:90px" readonly="true" maxlength="6"></html:text>
				</td>
				
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="flag">
						<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_hxxy_zhszcpmodi.do?act=save','xn')"
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
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
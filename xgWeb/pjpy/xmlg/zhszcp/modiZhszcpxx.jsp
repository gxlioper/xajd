<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰλ��:�������� - ����ά�� - �ۺ����ʲ��� 
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
					<html:text property="xh" name="rs" readonly="true"></html:text>

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
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left">
						<html:select property="xq" style="width:90px" styleClass="select"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
						</html:select>
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
						�������ַ֣�
					</td>
					<td align="left">
						<html:text property="dcj" styleId="dcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						�������ַ֣�
					</td>
					<td align="left">
						<html:text property="zcj" styleId="zcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						������ַ֣�
					</td>
					<td align="left">
						<html:text property="tcj" styleId="tcj" onblur="ckinpdata(this)" style="width:90px"></html:text>
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
						�۲�ѧ��������
					</td>
					<td align="left">
						${rs.xqpm }
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
							�۲�ѧ��������
						</td>
						<td align="left">
							${rs.xnpm }
						</td>
				</tr>
				<tr>
						<td align="right">
							�۲�ѧ���ܷ֣�
						</td>
						<td align="left">
							${rs.xqzf }
						</td>
						<td align="right">
							�۲�ѧ���ܷ֣�
						</td>
						<td align="left">
							${rs.xnzf }
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual value="no" name="writable">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xmlg_modiZhszcpxx.do?operType=save','xh-xn-xq');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyynys" key="pjpy_ynys_jxjsh" />
       		</div>
    	</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						<html:text name='rs' property="xh"
								styleId="xh" readonly="true"/>
					</td>
					<td align="right" >
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
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
						��ѧ��:
					</td>
					<td align="left">
						<bean:write name="rs" property="jxjmc"/>
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
						˼��������ʷ�:
					</td>
					<td align="left">
						<bean:write name="rs" property="sxzzddszf"/>
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
						��ѧ�Ļ����ʷ�:
					</td>
					<td align="left">
						<bean:write name="rs" property="kxwhszf"/>
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
						�����������ʷ�:
					</td>
					<td align="left">
						<bean:write name="rs" property="sxlxszf"/>
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
						ʵ�������������ʷ�:
					</td>
					<td align="left">
						<bean:write name="rs" property="sjlxcxf"/>
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
							�ۺϲ����ܷ�:
						</td>
						<td align="left">
							<bean:write name="rs" property="zhszcpzf"/>
						</td>
				</tr>
				<tr>
						<td align="right">
							��ˣ�
						</td>
						<td align="left" colspan="3">
							<html:select property="yesNo" styleId="yesNo">
								<html:option value=""></html:option>
								<html:options collection="shList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
				</tr>
				<tr>
					<td colspan="" align="right">
						��������
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="yj" styleClass="inputtext"
						rows="4" styleId="yj" style="width:98%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save"
						onclick="refreshForm('ynys_jxjshsave.do');document.getElementById('btn_save').disabled=true;"
						style="width:80px"  >
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('xh').value, 800, 600)" style="width:90px"
					id="buttonQuery">
					�鿴ѧ����Ϣ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
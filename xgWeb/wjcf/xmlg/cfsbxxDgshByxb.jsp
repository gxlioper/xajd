<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfxmlgwh" method="post" >
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: Υ�ʹ��� - У����� - ���
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					ѧ�ţ�
				</td>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<td align="right" width="15%">
					ѧ�꣺
				</td>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					�������
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					����ԭ��
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					�����ĺţ�
				</td>
				<td align="left">
					<logic:equal value="xy" name="userType">
						${rs.cfwh }
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:text property="cfwh" styleId="cfwh" ></html:text>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					����ʱ�䣺
				</td>
				<td align="left">
					<logic:equal value="xy" name="userType">
						${rs.cfsj }
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<td align="right">
					���������<br/>�򸽼���
				</td>
				<td align="left" colspan="">
					<logic:notEmpty name="rs" property="fjsclj">
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }" target="_blank">����</a>
					</logic:notEmpty>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					��ˣ�
				</td>
				<td align="left" colspan="">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					
				</td>
				<td align="left" colspan="">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					Υ����ʵ��
				</td>
				<td align="left" colspan="3">
					<html:textarea rows="4" style="width:98%"
								property="bz" styleId="bz" name="rs" disabled="true"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				ϵԺ�����
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xyclyj" rows="4" style="width:98%" disabled="true">
							</html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				ѧ���������
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xxclyj" rows="4" style="width:98%" disabled="true">
							</html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				��˴��������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" rows="4" style="width:98%">
							</html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<logic:notEqual value="view" name="writable">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_xmlg_cfsbxxDgshByxb.do?operType=save','');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfzgkdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/wjcf_zgkd_gzjyadd.do" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: ��ǰλ�ã�Υ�ʹ��� - ���ٽ��� - ���ٽ�����¼
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="35%">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
						<button type="button" onclick="showTopWin('wjcf_wjstuinfo.do',730,600)"
						class="btn_01" id="buttonFindStu" >
						ѡ��
					</button>
				</td>
				<td align="right" width="15%">
					ѧ�꣺
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="xn" />
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
					<bean:write name="rs" property="xb" />
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
					<bean:write name="rs" property="nj" />
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
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					����ʱ�䣺
				</td>
				<td align="left">
					${rs.cfsj }
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
					�����ĺţ�
				</td>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left" colspan="3">
					${rs.bjmc }
				</td>
<%--				<td align="right">--%>
<%--					--%>
<%--				</td>--%>
<%--				<td align="left">--%>
<%--				--%>
<%--				</td>--%>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ʱ�䣺
				</td>
				<td align="left">
					<html:text property="sj" styleId="sj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sj','y-mm-dd');" readonly="true"></html:text>
				</td> 
				<td align="right">
					�ص㣺
				</td>
				<td align="left">
					<html:text property="dd" styleId="dd" style="width: 180px"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					̸��������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="thjg" rows="5" styleId="thjg" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					̸��С�᣺
				</td>
				<td align="left" colspan="3"><html:textarea property="thxj" styleId="thxj" style="width:98%" rows="4"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_zgkd_gzjyadd.do?act=save','xh-sj');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyzjjdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_zjjd_jxjsqdefault.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_jxjsq" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��д�����
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="30%">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
					</logic:notPresent>
				</td>
				<td align="right" width="16%">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
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
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
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
					ѧ�ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
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
					�ۺϲ����ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszcpzf" />
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
					�ۺϲ������Σ�
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszmc" />
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
					������ͳɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="dkzdcj" />
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
					�����ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="tycj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					���ʽ�����׼�ȼ���
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
				<td align="right">
					���������ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="dyzf" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶�����ȼ���
				</td>
				<td align="left">
					<html:select property="bjpddj" styleId="bjpddj" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="pdList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding:0;">
					<%@ include file="/pjpy/zjjd/yhkh.jsp"%>
				</td>
			</tr>
			<logic:notEqual name="userType" value="stu">
			<tr >
				<td align="right">
					����ϵ�����
				</td>
				<td align="left" colspan="3">
					<html:textarea property="szxyj" styleId="szxyj"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			<tr >
				<td align="right">
					��ע��
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" 
					style="width: 98%" styleClass="inputtext" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('zjjd_jxjsqsave.do','xh-jxjdm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
						<logic:notEmpty name="rs" property="xh">
							<logic:empty name="rs" property="zhszcpzf">
									<br/>
								<font color="red">�ۺϲ����ɼ���δ����,���ȵ�������!</font>
							</logic:empty>
							
						</logic:notEmpty>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</logic:present>
		<logic:present name="failinfo">
				<script>
					alert('����������ѧ������������');
				</script>
			</logic:present>
		<logic:present name="cjFlag">
			<script>
					alert('ѧ���ɼ������޸����γ̳ɼ�,���ȵ���ɼ���');
				</script>
		</logic:present>
	</html:form>
</body>

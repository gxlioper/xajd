<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyynyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_yxbyssq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_yxbyssq" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<tr>
					<th colspan="4" align="center">
					<div align="center" style="font-size:18px;font:'����' ">
						${tit }
					</div>
					</th>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
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
				<td align="right">
					ѧ�꣺
				</td>
				<td align="left">
					${xn }
					<input type="hidden" name="xn" id="xn" 
					value="${xn }">
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
					���壺
				</td>
				<td align="left">
					<html:text property="mz" styleId="mz" styleClass="inputtext">
					</html:text>
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
					������ò��
				</td>
				<td align="left">
					<html:text property="zzmm" styleId="zzmm" styleClass="inputtext">
					</html:text>
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
					����״����
				</td>
				<td align="left">
					<html:text property="jkzk" styleId="jkzk" styleClass="inputtext">
					</html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xy" />
				</td>
				<td align="right">
					��Դ�أ�
				</td>
				<td align="left">
					<html:text property="syd" styleId="syd" styleClass="inputtext">
					</html:text>
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
					�������£�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
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
					��ѧʱ�䣺
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					��ͥ��ϸ��ַ��
				</td>
				<td align="left" colspan="3">
					<html:text property="jtdz" styleId="jtdz" style="width:95%" styleClass="inputtext">
					</html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�����¼���
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yxsj" styleId="yxsj" rows="7"
					 style="width:95%" styleClass="inputtext"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶�����
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bjyj" styleId="bjyj" rows="4" 
					style="width:95%" styleClass="inputtext"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_yxbyssave.do','xh');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="window.open('ynys_yxbysprint.do')" style="width:80px">
						��ӡ����
					</button>
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
	</html:form>
</body>

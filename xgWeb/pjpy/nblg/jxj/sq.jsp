<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
<body>
	<html:form action="/pjpynblgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_nblg_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: �������� - ��ѧ������ - ��д�����
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
		<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
			<tr style="height:23px">
				<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="">
								<bean:write name='rs' property="xh" />
								<input type="hidden" id="xh" value="${rs.xh }"/>
							</td>
						</logic:equal>
				<td align="right">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					${rs.xn }
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
					�����ɼ�&nbsp;&nbsp;&nbsp;<br/>רҵ������
				</td>
				<td align="left">
					
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
					�ۺϲ���&nbsp;&nbsp;&nbsp;<br/>�༶������
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�������£�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					ѧ������&nbsp;&nbsp;&nbsp;<br/>Υ�ʹ��֣�
				</td>
				<td align="left">
					
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
					����&nbsp;&nbsp;&nbsp;<br/>�Ƿ��꣺
				</td>
				<td align="left">
					
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
					���
				</td>
				<td align="left">
					�ȼ�<br/>
					�ɼ�
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
					ѧ������&nbsp;&nbsp;&nbsp;<br/>��ȡ�����
				</td>
				<td align="left">
					
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
					���뽱ѧ�����
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<td align="right">
					��&nbsp;&nbsp;&nbsp;<br/>
					��&nbsp;&nbsp;&nbsp;<br/>
					ѧ&nbsp;&nbsp;&nbsp;<br/>
					��&nbsp;&nbsp;&nbsp;<br/>
					С&nbsp;&nbsp;&nbsp;<br/>
					��&nbsp;&nbsp;&nbsp;<br/>
				</td>
				<td align="left" colspan="3">
				
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nblg_jxjsq.do?act=save','xh-jxjdm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="window.open('pjpy_nblg_jxjprint.do?xh='+document.getElementById('xh').value)" 
						style="" id="buttonPrint">
						��ӡ����
					</button>
				</div>
				<!-- ������ʾ��Ϣ -->
			
	</html:form>
</body>

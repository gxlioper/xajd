<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
<body>
	<html:form action="/pjpyzgmswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
<%--		<input type="hidden" name="act" id="act" value="save"/>--%>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_zgms_kkjladd.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��Ϣά�� - ѧ���ɼ�ά��
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��ϸ��Ϣ
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					${rs.xh}
				</td>
				<td align="right">
					ѧ�꣺
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
					ѧ�ڣ�
				</td>
				<td align="left">
					${rs.xq }
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
					�γ�����:
				</td>
				<td align="left">
					${rs.kcmc }
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
					�γ�����:
				</td>
				<td align="left">
					${rs.kclx }
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
					�ɼ�:
				</td>
				<td align="left">
					${rs.zpcj2 }
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
					�����ɼ�:
				</td>
				<td align="left">
					${rs.bkcj2 }
				</td>
				
			</tr>
			<tr >
				<td align="right">
					���޳ɼ�:
				</td>
				<td align="left" colspan="3">
					${rs.cxcj2 }
				</td>
			</tr>
			<tr >
				<td align="right">
					��ע:
				</td>
				<td align="left" colspan="3" height="60px">
					${rs.bz }
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
	</html:form>
</body>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyxcxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkVal }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��� - ��ѧ�����
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
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
					ѧ�꣺
				</td>
				<td align="left" width="32%">
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
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					��ѧ��
				</td>
				<td align="left">
					${rs.jxjmc }
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
					������
				</td>
				<td align="left">
					${rs.jlje }
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
					����ְ��
				</td>
				<td align="left">
					${rs.drzw }
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
					��ˣ�
				</td>
				<td align="left">
					<html:select property="yesNo" styleId="yesNo" >
						<html:option value="δ���">δ���</html:option>
						<html:option value="ͨ��">ͨ��</html:option>
						<html:option value="��ͨ��">��ͨ��</html:option>
					</html:select>
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
					���гɼ���
				</td>
				<td align="left">
					${rs.cxcj }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					ƽ���ɼ���
				</td>
				<td align="left">
					${rs.pjcj }
				</td>
				<td align="right">
					ƽ���ɼ�������
				</td>
				<td align="left">
					${rs.pjcjpm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�ܳɼ���
				</td>
				<td align="left">
					${rs.zcj }
				</td>
				<td align="right">
					�ܳɼ�������
				</td>
				<td align="left">
					${rs.pm }
				</td>
			</tr>
			<tr>
				<td align="right">
					�������ɣ�
				</td>
				<td align="left" colspan="3">
					${rs.sqly }
				</td>
			</tr>
			<tr>
				<td align="right">
					��������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="6"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="act">
						<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_xcxy_jxjshone.do?act=save','')"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_cjprint" onclick="showTopWin('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
						ѧ���ɼ���
					</button>
				</div>
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
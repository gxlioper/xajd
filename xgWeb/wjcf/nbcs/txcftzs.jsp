<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/wjcfnbcswh.do" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�Υ�ʹ��� - �·��⴦��֪ͨ - ��д�⴦��֪ͨ
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��д�⴦��֪ͨ
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					ѧ�ţ�
				</td>
				<td align="left" width="30%">
					${rs.xh }
				</td>
				<td align="right" width="20%">
					ѧ�꣺
				</td>
				<td align="left" width="30%">
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
					�⴦�����
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
					�⴦��ԭ��
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
					Υ��ʱ�䣺
				</td>
				<td align="left">
					${rs.wjsj }
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
					�༶��
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					Υ����ʵ��
				</td>
				<td align="left" colspan="3">
					<html:textarea rows="9"  style="width:500px;overflow:auto" property="bz" styleId="bz" name="rs" readonly="true"></html:textarea>
				</td>
			</tr>
		<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>�ļ������
				</td>
				<td align="left" colspan="3">
					<font color="red">���ˣ������ˣ��Ƿ��Υ�����ɼ�ѧУ��������Υ����Ϊ����������磿</font>
					<br/>
					<html:select property="sfsb" styleId="sfsb">
						<html:option value="���">���</html:option>
						<html:option value="�����">�����</html:option>
					</html:select>
				</td>
			</tr>
		<tr style="height:23px">
				<td align="right">
					������������ɣ�
					<br/>
					<font color="red">������1000������</font>
				</td>
				<td align="left" colspan="3">
					<font color="red">��������ͬѧ������д�����������Ŀ������ϣ�</font><br/>
					<html:textarea property="sbsy" styleId="sbsy" style="width:500px" rows="6" onkeyup="checkLen(this,1000)"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notEqual value="true" name="rs" property="write">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_nbcs_txcftzs.do?operType=save','sfsb');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
			<logic:equal value="true" name="rs" property="write">
				<font color="red">��ز��������,�����ٽ��в���!</font><br/>
			</logic:equal>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

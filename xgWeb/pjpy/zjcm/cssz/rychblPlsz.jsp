<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js">

</script>
<body onload="dispconf('cpfw')">
	<html:form action="/pjpyZjcmCssz" method="post">
		
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �������� - ��������
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����ƺű�����������
					</td>
				</tr>
			</thead>
			<tr>
						<td align="right" width="35%">
							ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" styleId="xn"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xq" styleId="xq"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" styleId="nd"
								style="color:#666666" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>���ñ�����Χ��
						</td>
						<td align="left">
							<html:select property="cpfw" styleId="cpfw" style="width:120px" onchange="dispconf('cpfw')">
								<html:options collection="cpfwList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td>
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()"
							 styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>�����ƺţ�
						</td>
						<td align="left">
							<html:select property="jxjdm" styleId="jxjdm">
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							��������������
						</td>
						<td align="left">
							<input type="text" style="width:60px" name="jxjbl" id="jxjbl"
							onkeypress="return numInputValue(this,5,event)" maxlength="4" />
							<font color="red">% �������� 0 �� 100 ֮������֣�</font>
						</td>
					</tr>
		</table>
		<div class="buttontool" align="center">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_zjcm_rychblPlsz.do?act=save','cpfw-jxjdm')"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
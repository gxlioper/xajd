<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body>
	<html:form action="/pjpyynyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_jxjsqqry" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����޸�
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
				</td>
				<td align="right">
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
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
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
					����ְ��
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
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
					��ѧ������ѧ��
				</td>
				<td align="left">
					<html:select property="dnshjxj" styleId="dnshjxj">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
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
					
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="center" colspan="4">
					<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>��Ҫѧҵ�ɼ�</strong>
											</div>
										</div>
									</td>
								</tr>
					</table>
						<logic:present name="cjList"> 
					<div id="child2" style="display:none">
						<table width="95%" border="1" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									�γ�
								</td>
								<td align="center">
									�ɼ�
								</td>
								<td align="center">
									�γ�����
								</td>
								<td align="center">
									�Ƿ���޸Ŀ�
								</td>
							</tr>
							</thead>
						
							<logic:iterate name="cjList" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							
							<tr>
								<td align="center">
									רҵ��ƽ���ɼ�
								</td>
								<td align="center">
									�Ļ���ƽ���ɼ�
								</td>
								<td align="center">
									�ۺ����ʲ����ɼ�
								</td>
								<td align="center">
									�����ɼ������꼶
								</td>
							</tr>
							<tr>
								<td align="center">
									<bean:write name="cjpm" property="zykpjf" />
								</td>
								<td align="center">
									<bean:write name="cjpm" property="whkpjf" />
								</td>
								<td align="center">
									<bean:write name="zhfpm" property="zf" />
								</td>
								<td align="center">
									��&nbsp;<bean:write name="zhfpm" property="pm" />&nbsp;��
								</td>
							</tr>	
							
						</table>
				</div>
				</logic:present>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="center" colspan="4">
					<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>�������ĺ���Ʒ���</strong>
											</div>
										</div>
									</td>
								</tr>
					</table>
					<div id="child3" style="display:none">
						<table width="95%" border="1" class="tbstyle">
						<thead>
							<tr align="center">
								<td align="center">
									������
								</td>
								<td align="center">
									���Ļ���Ʒ����
								</td>
								<td align="center">
									�񽱼���
								</td>
								<td align="center">
									�񽱵ȼ�
								</td>
							</tr>
							</thead>
							<logic:iterate name="zpList" id="ss">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="ss" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					��Ҫ�¼�:
				</td>
				<td align="left" colspan="3">
					<html:textarea name = "rs" property="zysj" styleId="zysj" rows="3" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					����<bean:message key="lable.xsgzyxpzxy" />������:
				</td>
				<td align="left" colspan="3">
					<html:textarea name = "rs" property="ejyxyj" styleId="ejyxyj" rows="3" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>		
		<div class="buttontool" align="center">
					<logic:notPresent name="act">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('ynys_jxjmodisave.do','jxjdm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;
					</logic:notPresent>
				<button type="button" class="button2" id="btn_close"
				onclick="window.close();return false;"
				style="width:80px">
				�ر�
			</button>
				</div>
				<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
<script language="javascript"  src="js/wjdc/wjdc.js"></script>
<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
<script type="text/javascript">	

</script>
	<body onload="">
		<html:form action="/wjdc">
			<!-- ������ -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							ѧ�꣺
						</td>
						<td align="left" width="35%">
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<td align="right" width="15%">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq"/>
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="nd"/>
							${rs.nd }
						</td>
						<td align="right">
							����ʱ�䣺
						</td>
						<td align="left">
							<html:hidden name="rs" property="jlsj"/>
							${rs.jlsjmc }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					�ʾ���Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>�ʾ����ƣ�
							<br>
							<font color="red">(��50��)</font>
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" readonly="true"/>			
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							��ע��
							<br>
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz" readonly="true"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
										<tr style="height: 23px">
						<td align="right">
							���ַ�����
							<br>
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="wzjg" rows="5" styleId="wzjg" 
								readonly="true"
								style="width: 90%"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							ͼƬ������
							<br>
						</td>
						<td align="left" colspan="3">
							<!-- ����ͼƬ -->
							<logic:equal name="rs" property="hadTb" value="yes">
								<img 
									src="<%=request.getContextPath()%>/stuPic.jsp?lx=wjdc&xh=${rs.id }${rs.xh }"
									border="0" align="absmiddle" />
							</logic:equal>
							<!-- ����ͼƬ -->
							<logic:equal name="rs" property="hadTb" value="no">
								������ͼƬ����
							</logic:equal>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<button class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
							�� ��
					</button>
				</span>
			</div>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

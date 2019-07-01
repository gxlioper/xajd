<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script type="text/javascript">	
		function printBb(){
			var xh = $("xh").value;
			var xn = $("xn").value;
			var pkValue = xh+xn;
			window.open('gzdxJxgl.do?method=mhxPrint&pkValue='+pkValue);
		}
	</script>
	<body onload="setReadonly()">
		<html:form action="/gzdxJxgl">
		<%@ include file="/jxgl/hiddenValue.jsp"%>
		<input type="hidden" id="url" name="url" value="/xgxt/gzdxJxgl.do?method=mhxUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="lx" name="lx" value="${rs.xn }ѧ���ѵ����"/>
		<input type="hidden" id="zd" name="zd" value="xn"/>
		<input type="hidden" id="va" name="va" value="${rs.xn }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�⻺ѵ����(����ѧ��${rs.xn })
							<html:hidden name="rs" property="save_xn" styleId="xn"/>
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="30%">
						<!-- ��� -->
						<logic:equal name="mklx" value="sh">
							${rs.xh }
							<html:hidden name="rs" property="save_xh" styleId="xh"/>
						</logic:equal>
						<!-- ��� end-->
						<!-- ��� -->
						<logic:equal name="mklx" value="jg">
							<!-- ������ -->
							<logic:notEqual name="doType" value="add">
								${rs.xh }
								<html:hidden name="rs" property="save_xh" styleId="xh"/>
							</logic:notEqual>
							<!-- ������ end-->
							<!-- ���� -->
							<logic:equal name="doType" value="add">
								<html:text name='rs' property="save_xh" styleId="xh" readonly="true" />
								<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
							<!-- ���� end-->
						</logic:equal>
						<!-- ��� end-->
					</td>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						${rs.nj }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						������
					</td>
					<td align="left">
						${rs.xm }
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						${rs.zymc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						���֤�ţ�
					</td>
					<td align="left">
						${rs.sfzh }
					</td>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="20%">
						���᣺
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="jgshen" styleId="jgshen" onchange="loadShi('jgshen','jgshi','jgxian');" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="jgshi" styleId="jgshi"
							onchange="loadXian('jgshi','jgxian')" disabled="true">
							<html:options collection="jgshiList" property="shidm"
								labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="jgxian" styleId="jgxian" disabled="true">
							<html:options collection="jgxianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<font color="red">*</font>�������
					</td>
					<td align="left">
						<html:select name="rs" property="save_sqlx" style="" styleId="sqlx">
							<html:options collection="mhlxList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>����ʱ�䣺
					</td>
					<td align="left">
						<html:text name="rs" property="save_sqsj" styleId="sqsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('sqsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�������ӣ�
					</td>
					<td align="left">
						${rs.ldmc }
					</td>
					<td align="right">
						���Ӹ���Ա��
					</td>
					<td align="left">
						${rs.jsxm }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�������ɣ�<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="save_sqly" styleId="sqly" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ע��<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="save_bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��������<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:hidden name='rs' property="save_xysh"/>
						<html:textarea name='rs' property="save_xyshyj" styleId="xyshyj" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						ѧУ��������<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:hidden name='rs' property="save_xxsh"/>
						<html:textarea name='rs' property="save_xxshyj" styleId="xxshyj" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<!-- ��� -->
					<logic:equal name="mklx" value="sh">
						<!-- �ǲ鿴 -->
						<logic:notEqual name="doType" value="view">
							<button type="button" class="button2" 
									id="buttonSave" 
									onclick="shInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','sh','xh-xn','ͨ��','');"
									style="width: 80px">
								ͨ	��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" 
									id="buttonSave" 
									onclick="shInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','sh','xh-xn','��ͨ��','');"
									style="width: 80px">
								��ͨ��
							</button>
							&nbsp;&nbsp;
						</logic:notEqual>
						<!-- �ǲ鿴 end-->
					</logic:equal>
					<!-- ��� end-->
					<!-- ��� -->
					<logic:equal name="mklx" value="jg">
						<!-- �ǲ鿴 -->
						<logic:notEqual name="doType" value="view">
							<button type="button" class="button2" 
									id="buttonSave" 
									onclick="saveUpdate('/xgxt/gzdxJxgl.do?method=mhxUpdate&doType=save','sqlx-sqsj');"
									style="width: 80px">
								��	��
							</button>
							&nbsp;&nbsp;
						</logic:notEqual>
						<!-- �ǲ鿴 end-->
						<!-- ������ -->
						<logic:notEqual name="doType" value="add">
						<button type="button" class="button2" 
									id="buttonSave" 
									onclick="printBb();"
									style="width: 80px">
								��	ӡ
						</button>
						&nbsp;&nbsp;
						</logic:notEqual>
						<!-- ������ end-->
					</logic:equal>
					<!-- ��� end-->
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						��	��
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")){
						alert($("message").value);
					}
					if(opener){
						opener.document.getElementById("search_go").click();
	      				window.close();
      				}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

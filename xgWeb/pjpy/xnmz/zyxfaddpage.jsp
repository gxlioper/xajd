<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyxnmzwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
	<input type="hidden" id="zyV" name="zyV" value=""/>
    <input type="hidden" id="bjV" name="bjV" value=""/>
		
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �������� - ��������
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" >
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>�꼶:
				</td>
				<td align="left">
					<html:select property="nj" styleId="nj" onchange="initZyList();">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj" labelProperty="nj"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />:
				</td>
				<td align="left">
					<html:select property="xydm" styleId="xy" onchange="initZyList();">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>רҵ:
				</td>
				<td align="left">
					<html:select property="zydm" styleId="zy">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>�������ѧ�֣�
				</td>
				<td align="left" colspan="3">
					<html:text property="jd" styleId="jd" onblur="ckinpdata(this)" maxlength="6"></html:text>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('xnmz_zyxfsave.do','xn-nj-zydm-jd');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="window.close();return false;" 
						style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>

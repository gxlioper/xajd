<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyynys" key="pjpy_ynys_zhszcp" />
       		</div>
    	</div>
    	<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个增加
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"  />	
					</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
						思想政治与道德素质测评分：
					</td>
					<td align="left">
						<html:text property="sxzzddszf" styleId="sxzzddszf" onblur="ckinpdata(this);countZf();"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<td align="right">
						科学文化素质测评分：
					</td>
					<td align="left">
						<html:text property="kxwhszf" styleId="kxwhszf" onblur="ckinpdata(this);countZf();"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<td align="right">
						身心能力素质测试分：
					</td>
					<td align="left">
						<html:text property="sxlxszf" styleId="sxlxszf" onblur="ckinpdata(this);countZf();"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<td align="right">
						实践能力与创新素质分：
					</td>
					<td align="left">
						<html:text property="sjlxcxf" styleId="sjlxcxf" onblur="ckinpdata(this);countZf();"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						综合素质测评总分：
					</td>
					<td align="left">
						<html:text property="zhszcpzf" styleId="zhszcpzf" readonly="true" onblur="ckinpdata(this);"></html:text>
					</td>
				</tr>
				<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('ynys_zhszcpmodi.do','xh-xn');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
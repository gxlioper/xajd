<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>  
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<body>
	<html:form action="/jygl" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="userName" name="userName"value="${userName }" />
		<input type="hidden" id="message" value="${message }">
		<input type="hidden" name="pk" value="${pk }">
		<input type="hidden" name="url" id="url" value="/xgxt/jygl.do?method=ecfpUpdate">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：${title }
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<font color="red">*</font>学号：
				</td>
				<td width="34%">
					<html:text property="xh" value="${rs.xsxh }${rs.xh }" readonly="true"></html:text>
					<logic:notEqual value="stu" name="userType">
						<button onclick="showTopWin('/xgxt/jygl.do?method=jyglData',800,600);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</td>
				<td align="right" width="16%">
					姓名：
				</td>
				<td width="34%">${rs.name }${rs.xm }</td>
			</tr>
			<tr>
				<td align="right">
					年级：
				</td>
				<td>
					${rs.nd }${rs.nj }
				</td>
				<td align="right">
					专业：
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<td align="right">
					原单位：
				</td>
				<td>
					<html:select property="ydw" value="${rs.ydw }">
						<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
				<td align="right">原单位所在地：</td>
				<td>
					<html:select property="ydwszd" value="${rs.ydwszd }">
						<html:options collection="dwdzList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>二次分配单位：
				</td>
				<td>
					<html:select property="ecfpdw" value="${rs.ecfpdw }">
						<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
				<td align="right"><font color="red">*</font>二次分配单位所在地：</td>
				<td>
					<html:select property="ecfpdwszd" value="${rs.ecfpdwszd }">
						<html:options collection="dwdzList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>二次分配时间：
				</td>
				<td>
					<html:text property="ecfpsj" styleId="ecfpsj" value="${rs.ecfpsj }" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
				</td>
				<td align="right">二次分配人：</td>
				<td>
					<html:text property="gpr" value="${userName }" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					改派原因：
				</td>
				<td colspan="3">
					<html:textarea property="ecfpyy" value="${rs.ecfpyy }" style="width:85%" rows="5" onblur="checkLen(this,500);"></html:textarea>
				</td>
			</tr>
			<customTag:customTable  rsname="rs" nodeslist = "jyglActionForm" doType="updata" scope="request" />
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" name="doType">
				<logic:notEqual value="update" name="doType">
					<button class="button2" id="buttonSave" 
					onClick="saveUpdate('/xgxt/jygl.do?method=ecfpUpdate&doType=save','xh-ecfpsj-ecfpdw-ecfpdwszd');">
						保&nbsp;&nbsp;&nbsp;&nbsp;存
					</button>
				</logic:notEqual>
			</logic:notEqual>
			
			<logic:equal value="view" name="doType">
				<button class="button2" id="buttonSave" onClick="window.close();">
						关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</logic:equal>
			<logic:equal value="update" name="doType">
				<button class="button2" id="buttonSave" 
				onClick="saveUpdate('/xgxt/jygl.do?method=ecfpUpdate&doType=modify','xh-ecfpsj-ecfpdw-ecfpdwszd');">
						保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
			</logic:equal>
		</div>
	</html:form>
	<logic:present name="message">
		<script>
			alert('${message}');
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>

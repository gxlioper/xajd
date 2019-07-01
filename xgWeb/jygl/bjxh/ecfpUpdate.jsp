<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
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
				��ǰ����λ�ã�${title }
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td width="34%">
					<html:text property="xh" value="${rs.xsxh }${rs.xh }" readonly="true"></html:text>
					<logic:notEqual value="stu" name="userType">
						<button onclick="showTopWin('/xgxt/jygl.do?method=jyglData',800,600);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</logic:notEqual>
				</td>
				<td align="right" width="16%">
					������
				</td>
				<td width="34%">${rs.name }${rs.xm }</td>
			</tr>
			<tr>
				<td align="right">
					�꼶��
				</td>
				<td>
					${rs.nd }${rs.nj }
				</td>
				<td align="right">
					רҵ��
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<td align="right">
					ԭ��λ��
				</td>
				<td>
					<html:select property="ydw" value="${rs.ydw }">
						<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
				<td align="right">ԭ��λ���ڵأ�</td>
				<td>
					<html:select property="ydwszd" value="${rs.ydwszd }">
						<html:options collection="dwdzList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>���η��䵥λ��
				</td>
				<td>
					<html:select property="ecfpdw" value="${rs.ecfpdw }">
						<html:options collection="yrdwList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
				<td align="right"><font color="red">*</font>���η��䵥λ���ڵأ�</td>
				<td>
					<html:select property="ecfpdwszd" value="${rs.ecfpdwszd }">
						<html:options collection="dwdzList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>���η���ʱ�䣺
				</td>
				<td>
					<html:text property="ecfpsj" styleId="ecfpsj" value="${rs.ecfpsj }" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
				</td>
				<td align="right">���η����ˣ�</td>
				<td>
					<html:text property="gpr" value="${userName }" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					����ԭ��
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
						��&nbsp;&nbsp;&nbsp;&nbsp;��
					</button>
				</logic:notEqual>
			</logic:notEqual>
			
			<logic:equal value="view" name="doType">
				<button class="button2" id="buttonSave" onClick="window.close();">
						��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</logic:equal>
			<logic:equal value="update" name="doType">
				<button class="button2" id="buttonSave" 
				onClick="saveUpdate('/xgxt/jygl.do?method=ecfpUpdate&doType=modify','xh-ecfpsj-ecfpdw-ecfpdwszd');">
						��&nbsp;&nbsp;&nbsp;&nbsp;��
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

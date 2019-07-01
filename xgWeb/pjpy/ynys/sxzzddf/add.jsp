<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
	function updateYz(){
		  var xh = $("xh").value;
		  var xn = $("xn").value;
		  var tableName = window.dialogArguments.document.getElementById('realTable').value;
		    getSztzData.getInfoEx(tableName," xh||xn ",xh+xn," shzt='通过' ",function(str){
		         if(str){		         	
		            alert("该生该学年的提交信息已经审核通过，不能再操作！");		          
			        return false;
		         }else{
		             saveinfo('ynys_sxzzddszbsave.do','xh-xn');
		        }
    	     });
   	}
</script>
<body>
	<html:form action="/pjpyynyszhszcp" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/ynys_sxzzddszfAdd.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_szcpwh" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						思想政治与道德素质分增加
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<logic:notEqual name="userType" value="stu" scope="session">
					<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
					</logic:notEqual>
					<logic:equal name="userType" value="stu" scope="session">
					<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
					</logic:equal>
				</td>
				<td align="right">
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" styleClass="select">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					性别：
				</td>
				<td align="left">
				<bean:write name="rs" property="xb" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
		</table>
		<%@ include file="sxzzddf.jsp"%>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="updateYz()"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>

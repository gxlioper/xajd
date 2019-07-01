<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyZjsyzy.js'></script>
<script language="javascript">
function saveinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	document.getElementById('btn_save').disabled = true;
	refreshForm(url);
}
function chktj() {
	var xh = document.getElementById('xh').value;
	var jxjdm = document.getElementById('jxjdm').value;
	if (xh==''||jxjdm=='') {
		alert('带*号为必填项！');
		return;
	}
	pjpyZjsyzy.chksqtj(xh, function (data){
		if (!data) {
			alert('该生在评奖学年有部分必修课成绩不级格或有违纪记录，不符合申请条件！');
			return;
		} else {
			refreshForm('jxjsqsave.do');
		}
	});
	document.getElementById('btn_save').disabled=true;
}
function print() {
	if (document.getElementById('xh').value==''||document.getElementById('jxjdm').value=='') {
		alert('学号和奖学金为必修项！');
		return;
	}
	window.open('pjpy_hxxy_jxjprint.do?xh='+document.getElementById('xh').value + '&jxjdm='+document.getElementById('jxjdm').value)
}
</script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_whlghxxy_jxjsqdefault.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 奖学金申请 - 填写申请表
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
						填写申请表
					</td>
					<logic:present name="tj">
					<tr>
					<td colspan="4" align="center">
						<font color="red">该生在评奖学年有部分必修课成绩不级格或有违纪记录，不符合申请条件！</font>
					</td>
					</tr>
					</logic:present>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
					</logic:notPresent>
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
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
					学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					学期：
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					民族：
				</td>
				<td align="left">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td align="right">
					政治面貌：
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmmmc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					出生年月：
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					入学时间：
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					外语水平：
				</td>
				<td align="left">
					<html:text property="wysp" styleId="wysp" styleClass="inputtext" maxlength="50"></html:text>
				</td>
				<td align="right">
					联系电话：
				</td>
				<td align="left">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					担任工作情况：
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw" styleClass="inputtext" maxlength="100"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>奖学金：
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px" >
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					平均学分绩点：
				</td>
				<td align="left">
					<bean:write name="rs" property="xf" />
				</td>
				<td align="right"> 
					&#25490;&#21517;&#65306; 
				</td>
				<td align="left">
					<bean:write name="rs" property="xfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					综合素质测评：
				</td>
				<td align="left">
					<bean:write name="rs" property="zf" />
				</td>
				<td align="right"> 
					&#25490;&#21517;&#65306; 
				</td>
				<td align="left">
					<bean:write name="rs" property="zfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					单科最低成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="zdcj" />
				</td>
				<td align="right">
					德育：
				</td>
				<td align="left">
					<bean:write name="rs" property="dcj" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					奖励加分：
				</td>
				<td align="left">
					<bean:write name="rs" property="jlf" />
				</td>
				<td align="right">
					体质健康评定：
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
			</tr>
			<tr >
				<td align="right">
					申请理由：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" ${tj }
						onclick="chktj()"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="print()" style="width:80px"
						id="buttonClose">
						打 印
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>

<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<body">
	<script language="javascript"
		src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
	function doOperation(str){
		 if(str=='sh_part' || str=='sh_btg' || str=='sh_cx'){
		 	var array = document.getElementsByName('cbv');
		 	var flag = 0;
		 	for(var i=0;i<array.length;i++){
		 		if(array[i].checked==true){
		 			flag = 1;
		 			break;
		 		}
		 	}
		 	if(flag == 0){
		 		alert('请勾选批量审核的记录！');
		 		return false;
		 	}
		 }
		 if(str!='query'){
		 	document.getElementById(str).disabled=true;
		 }
         var act = document.getElementById('act');
         act.value = str;
         refreshForm('/xgxt/pjpy_ycsf_jxjrychsh.do');
	}
	function getXscj(obj){
		var pk = obj.getElementsByTagName("input")[0].value;
		var dm = obj.getElementsByTagName("input")[1].value;
		var lb = document.getElementById('lb').value;
		showTopWin('/xgxt/pjpy_ycsf_viewxskccj.do?pk='+pk+'&dm='+dm+'&lb='+lb,700,600);
	}
	function chkIsInteger(obj){
		obj.value = obj.value.replace(/[^(\d|.]/g,'');
		return true;
	}
	</script>
	<html:form action="/pjpyycsfwh.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 审核 - 上报审核
			</div>
		</div>
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName}" />
		<input type="hidden" name="realTable" id="realTable"
			value="" />
		<input type="hidden" name="userName" id="userName" value="${userName}" />
		<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		<input type="hidden" name="act" id="act" value=""/>
		<input type="hidden" name="xn" value="${form.xn}"/>
		<input type="hidden" name="xq" value="${form.xq}"/>
		<!-- 操作进度提示信息 -->
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<fieldset>
			<legend>
				查询条件
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							学年：
							<html:select property="xn" style="width:100px" styleId="xn"
								styleClass="select" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 学期：
							<html:select property="xq" styleId="xq" styleClass="xq"
								style="width:60px" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp; 年级：
							<html:select property="nj" styleId="nj" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; 学号：
							<html:text property="xh" styleId="xh" style="width:110px"></html:text>
							&nbsp;&nbsp; 姓名：
							<html:text property="xm" styleId="xm" style="width:110px"></html:text>
						</td>
						<td width="10" rowspan="4" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="doOperation('query');">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							奖项类别：
							<html:select property="lb" styleId="lb" onchange="refreshForm('pjpy_ycsf_jxjrychsh.do')">
								<html:option value="jxj">奖学金</html:option>
								<html:option value="rych">荣誉称号</html:option>
							</html:select>
							&nbsp;&nbsp; 
							奖项：
							<logic:equal value="jxj" name="lb">
									<html:select property="dm" styleId="dm" >
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
									</html:select>
							</logic:equal>
							<logic:equal value="rych" name="lb">
									<html:select property="dm" styleId="dm" >
										<html:option value=""></html:option>
										<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
									</html:select>
							</logic:equal> 
							<logic:notEqual value="yes" name="ahzyjsxy">
							&nbsp;&nbsp;
							学校审核：
								<html:select property="xxsh" styleId="xxsh">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							</logic:notEqual>
							&nbsp;&nbsp; 
							<bean:message key="lable.xsgzyxpzxy" />：
							<logic:equal value="xy" name="userType">
								<logic:equal value="yes" name="ahzyjsxy">
									<input type="hidden" name="xydm" value="<bean:write name="xydm"/>"/>
									<html:select property="xydm" style="width:200px"
								styleClass="select" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:equal>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<html:select property="xydm" style="width:200px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:notEqual>				
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>						
							专业：
							<html:select property="zydm" styleId="zy" style="width:180px"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
							<html:select property="bjdm" styleId="bj" style="width:200px"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							&nbsp;&nbsp;
							<logic:notEqual value="yes" name="ahzyjsxy">
							综测总分班级排名前(比例)：
							<html:text property="pm" styleId="pm" style="width:80px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>%
							</logic:notEqual>
						</td>
					</tr>
					<logic:notEqual value="yes" name="ahzyjsxy">
					<tr>
						<td align="left" nowrap> 
							学业总分班级排名前(比例)：
							<html:text property="xyzfpm" styleId="xyzfpm" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>%
							&nbsp;&nbsp;
							必修、选修单科成绩不低于：
							<html:text property="xycj" styleId="xycj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
							校选修不低于：
							<html:text property="xxxcj" styleId="xxxcj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
							&nbsp;&nbsp;
							阶段考核成绩不低于：
							<html:text property="jdcj" styleId="jdcj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
							&nbsp;&nbsp;
						</td>
					</tr>
					</logic:notEqual>
				</thead>
			</table>
		</fieldset>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				未找到任何记录！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息并审核；
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center" ondblclick="getXscj(this)">
							<td>
								<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="<bean:write name="v" />"/>
								</logic:iterate>
							</td>
							<logic:equal value="yes" name="ahzyjsxy">
							<logic:iterate id="v" name="s" offset="2" length="12">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							</logic:equal>
							<logic:notEqual value="yes" name="ahzyjsxy">
							<logic:iterate id="v" name="s" offset="2" length="13">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							</logic:notEqual>
						</tr>
					</logic:iterate>
				</table>
				<!-- 分页功能 -->
				<TABLE width="99%" id="rsTable1" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyYcsfxyActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</fieldset>
		</logic:notEmpty>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:notEqual value="stu" name="userType">
					<button class="button2" id="sh_part" onclick="doOperation('sh_part')"
						style="width:120px">
						批量审核通过
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="sh_all" onclick="doOperation('sh_all')"
						style="width:120px">
						全部审核通过
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="sh_btg" onclick="doOperation('sh_btg')"
						style="width:120px">
						批量审核不通过
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="sh_cx" onclick="doOperation('sh_cx')"
						style="width:120px">
						撤消审核
					</button>
					</logic:notEqual>
				</div>
			</center>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "98%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	<!-- 操作提示 -->
	<logic:present name="result">
		<logic:equal value="yes" name="result">
			<script>
				alert('操作成功!');
				document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert('操作失败!');
			</script>
		</logic:equal>
	</logic:present>
</body>

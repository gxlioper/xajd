<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<body onload="xyDisabled('xy');">
	<script language="javascript"
		src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
	//验证数据格式是否是数字
	function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			obj.value = '';
			return false;
		}
		return true;
	}
  	function changeView(id,col,tableTop){
  		if (document.getElementById(id)) {
		var array = document.getElementById(id).getElementsByTagName('tr');
		var v = 0;
		if(tableTop=='yes'){
			v = 1;
		}
		for(var i=v;i<array.length;i++){
			var xxsh = array[i].cells[col].innerText;
			xxsh = xxsh.replace(/(^\s*)|(\s*$)/g, "");
			if (xxsh!='未审核' && xxsh != '') {
				array[i].cells[col-2].getElementsByTagName("input")[0].readOnly=true;
				array[i].cells[col-1].getElementsByTagName("input")[0].readOnly=true;
			}
		}
		}
	}
	function getXscj(obj){
		var pk = obj.getElementsByTagName("input")[0].value;
		var array = pk.split('@');
		var str = '';
		for(var i=0;i<array.length;i++){
			str += array[i];
		}
		showOpenWindow('/xgxt/pjpy_ycsf_viewxskccj.do?psjd=yes&pk='+str,700,600);
	}
	
	</script>
	<html:form action="/pjpyycsfwh.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 信息维护 - 平时、阶段考核成绩维护
			</div>
		</div>
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName}" />
		<input type="hidden" name="userName" id="userName" value="${userName}" />
		<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
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
								styleClass="select">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; 学期：
							<html:select property="xq" styleId="xq" styleClass="xq"
								style="width:60px">
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
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="go" value="go" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="allNotEmpThenGo('pjpy_ycsf_pjjdkhcjwh.do');">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; 专业：
							<html:select property="zydm" style="width:175px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
							<html:select property="bjdm" style="175px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
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
					记录数：
					<bean:write name="rsNum" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息；</font><font color="red">查询条件中学年，学期为必选项；</font>
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="3">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center" ondblclick="getXscj(this)" 
							bgcolor="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>">
							<td>
								<!-- 删除用的主键 -->
								<input type="checkbox" id="cbv" name="cbv"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
									<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>/>
								<!-- 保存用的主键 -->
								<input type="hidden" id="pkvalue" name="pkvalue"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<!-- 学校审核后用的标志 -->	
								<input type="hidden" id="dis" name="dis"
									value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="3" length="6">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<td><logic:iterate id="v" name="s" offset="9" length="1">
									<input type="text" name="pjkhcj" id="pjkhcj" style="width: 50px" value="${v }" maxlength="5" onkeyup="ckinpdata(this)"/>
								</logic:iterate></td>
							<td><logic:iterate id="v" name="s" offset="10" length="1">
									<input type="text" name="jdkhcj" id="jdkhcj" style="width: 50px" value="${v }" maxlength="5" onkeyup="ckinpdata(this)"/>
								</logic:iterate></td>
								<td><logic:iterate id="v" name="s" offset="11" length="1">
									${v }
								</logic:iterate></td>
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
<%--		<logic:equal value="yes" name="writeAble" scope="request">--%>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" id="btn_add"
						onclick="refreshForm('pjpy_ycsf_pjjdkhcjSave.do');BatAlert.showTips('正在操作，请等待...');"
						style="width:80px">
						保存
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_add"
						onclick="deldata('pjpy_ycsf_pjjdkhcjDelete.do')"
						style="width:80px">
						删除
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_impdata" onclick="impAndChkData()"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_expdata" onclick="dataExport()"
						style="width:80px">
						导出数据
					</button>
				</div>
			</center>
<%--		</logic:equal>--%>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
			changeView('rsTable',9,'yes');
		</script>
	<!-- 操作提示 -->
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert('操作成功!');
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert('操作失败!');
			</script>
		</logic:equal>
	</logic:present>
</body>

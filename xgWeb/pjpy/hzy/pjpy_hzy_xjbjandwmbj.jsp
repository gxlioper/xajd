<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" defer="defer">
		   	if ($("defaultTitle")) {
		   		var mklx = $("defaultTitle").value+"m";
			 	if($(mklx)){
			 		$(mklx).parentNode.className = "ha";
			 	}
		   	}
		 </script>

	</head>

	<body style="" onload="xyDisabled('xy');liClass();">
		<html:form action="/pjpy_hzy_xjbjandwmbj" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 先进班级申请</a>
				</p>
			</div>

			<input type="hidden" id="titName" name="titName" value="" />
			<input type="hidden" id="defaultTitle"
				value="<bean:write name="defaultTitle" scope="request" />" />
			<input type="hidden" id="xxmc" name="xxmc"
				value="<bean:write name="xxmc" scope="session"/>" />
			<!-- 页签 -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<logic:iterate id="title" name="titleList">
							<li id="<bean:write name="title" property="cn" />">
								<a href="#" onclick="hzy_changePage(this);return false;"
									id="<bean:write name="title" property="en" />m"> <span><bean:write
											name="title" property="cn" />
								</span> </a>
							</li>
						</logic:iterate>
					</ul>
				</div>
			</div>
			<!-- 页签 end-->
			<div class="xxk" style="display : none">
				<logic:iterate id="title" name="titleList">
					<ul>
						<li id="<bean:write name="title" property="en" />l"
							class="xxk_off_l"></li>
						<li id="<bean:write name="title" property="en" />m"
							onclick="hzy_changePage(this)" class="xxk_off_m">
							&nbsp;
							<bean:write name="title" property="cn" />
							&nbsp;
						</li>
						<li id="<bean:write name="title" property="en" />r"
							class="xxk_off_r"></li>
					</ul>
				</logic:iterate>
			</div>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span><div id="mainTitle"></div>
							</span>
						</th>
					</tr>
				</thead>
			</table>

			<div id="items" style="display:none; position: absolute;">
				<input type="hidden" name="njV" id="njV" />
				<input type="hidden" name="xyV" id="xyV" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" name="userType" id="userType"
					value="${userType }" />
				<input type="hidden" name="userName" id="userName"
					value="${userName }" />
				<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
				<div class="tab">
					<table class=formlist>
						<thead>
							<tr>
								<th colspan="2">
									<span> <logic:equal name="defaultTitle" value="wmbj">
									文明班级
								</logic:equal> <logic:notEqual name="defaultTitle" value="wmbj">
									先进班级
								</logic:notEqual> </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj"
										onchange="initZyList();initBjList();" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList();" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList();"
										styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bj" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan=2 align="right">
									<button class="button2" onclick="hideItems()">
										确 定
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<tr>
						<th colspan="2" scope="col" width="26%">
							<div align="center">
								<font color="red">*</font>班级
							</div>
						</th>
						<td width="26%" scope="col">
							<html:text property="bjdm" readonly="true" onclick="showItems()"
								styleId="bjdm" />

						</td>
						<th width="26%" scope="col">
							<div align="center">
								班长
							</div>
						</th>
						<td width="24%" scope="col">
							<html:text property="bzxm" maxlength="10" value="${bjrs.bzxm }" />
						</td>
					</tr>
					<tr>
						<th colspan="2" scope="row">
							<div align="center">
								学生人数
							</div>
						</th>
						<td>
							<html:text property="xsrs" onblur="onlyNum(this)" maxlength="5"
								value="${bjrs.bjrs }" />
						</td>
						<th>
							<div align="center">
								<font color="red">*</font>班主任
							</div>
						</th>
						<td>
							<html:text property="bzr" styleId="bzr" maxlength="10"
								value="${bjrs.bzrxm }" />
						</td>
					</tr>
					<logic:equal value="12682" name="xxdm">
						<tr>
							<th colspan="2" scope="row">
								<div align="center">
									集体称号
								</div>
							</th>
							<td colspan="3">
								<html:text property="jtch" styleId="jtch" style="width:200px"
									maxlength="20" />
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="10863" name="xxdm">
						<tr>
							<th colspan="2">
								<div align="center">
									团支书
								</div>
							</th>
							<td>
								<html:text property="tzs" styleId="tzs"></html:text>
							</td>
							<th colspan="" scope="row">
								<div align="center">
									<font color="red">*</font>学年
								</div>
							</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th colspan="2" scope="row">
								<div align="center">
									班内有无不合格寝室
								</div>
							</th>
							<td>
								<html:radio property="bhgqs" value="0">有</html:radio>
								<html:radio property="bhgqs" value="1">无</html:radio>
							</td>
							<th>
								<div align="center">
									有无同学受过行政,党,团纪处分
								</div>
							</th>
							<td>
								<html:radio property="ywcf" value="0">有</html:radio>
								<html:radio property="ywcf" value="1">无</html:radio>
							</td>
						</tr>
						<tr>
							<th colspan="1" width="13%" scope="row">
								<p align="center">
									<br />
									班
									<br />
									级
									<br />
									集
									<br />
									体
									<br />
									荣
									<br />
									誉
									<br />
								</p>
							</th>
							<td colspan="4">
								<html:textarea property="bjry" styleId="bjry" rows="5"
									style="width:99%" onblur="chLeng(this,100)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<logic:equal value="10863" name="xxdm">
							<th width="13%" scope="row">
								<p align="center">
									主
									<br />
									要
									<br />
									成
									<br />
									绩
									<br />
									简
									<br />
									介
								</p>
							</th>
						</logic:equal>
						<logic:notEqual value="10863" name="xxdm">
							<th width="13%" scope="row" align="right">
								<p align="right">
									主
								</p>
								<p align="right">
									要
								</p>
								<p align="right">
									事
								</p>
								<p align="right">
									迹
								</p>
							</th>
						</logic:notEqual>
						<td colspan="4" scope="row">
							<html:textarea property="zysj" rows="7" cols="100"
								onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					<tr>
						<logic:notEqual value="10863" name="xxdm">
							<th width="13%" align="right" scope="row">
								系
								<br />
								部
								<br />
								意
								<br />
								见
							</th>
							<td colspan="4" scope="row">
								<html:textarea property="xyyj" rows="7" cols="100" value="同意推荐"
									onblur="chLeng(this,100)"></html:textarea>
							</td>
						</logic:notEqual>
					</tr>
				</table>
			</div>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="right">
							<logic:equal value="10863" name="xxdm">
								<button onclick="xjbjsave()">
									提交申请
								</button>
				   &nbsp;&nbsp;
				   	<button
									onclick="window.open('pjpy_nbzy_xjbjprint.do?pkValue=');">
									打印报表
								</button>
							</logic:equal>
							<logic:notEqual value="10863" name="xxdm">
								<button
									onclick="if (document.getElementById('bjdm').value=='' || document.getElementById('bzr').value=='') {alert('带*号内容为必填！');} else {refreshForm('/xgxt/pjpy_hzy_xjbjandwmbj.do?method=xjbjAndWmbjSq&doType=save&mkType='+document.getElementById('defaultTitle').value);BatAlert.showTips('正在操作，请等待...');this.disabled=true}">
									提交申请
								</button>
				   &nbsp;&nbsp;
				   	<button onclick="printTable()">
									打印报表
								</button>
							</logic:notEqual>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>

		</html:form>
	</body>
	<script type="text/javascript">
    window.onload= function(){
       //alert(document.getElementById('defaultTitle').value);
    	hzy_changePage(document.getElementById(document.getElementById('defaultTitle').value+'m'),"true");
    }
    function xjbjsave() {
    	if (document.getElementById('bjdm').value=='' || document.getElementById('bzr').value=='' 
    	|| document.getElementById('xn').value=='') {
    		alert('带*号为必填项!');
    		return;
    	} else {
    		refreshForm('/xgxt/pjpy_hzy_xjbjandwmbj_save.do?method=xjbjAndWmbjSqSave&mkType='+document.getElementById('defaultTitle').value);
    		BatAlert.showTips('正在操作，请等待...');
    	}
    }
 function printTable(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var titName = document.getElementById("titName");
	requestPath += "&titName="+titName.value;		
	document.forms[0].action = requestPath;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}    
  </script>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script type="text/javascript">
    alert("保存成功！");
  </script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script type="text/javascript">
    alert("保存失败！");
  </script>
		</logic:equal>
	</logic:present>
</html>

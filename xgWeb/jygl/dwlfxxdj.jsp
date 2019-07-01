<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function add() {
		var dwbh = document.getElementById("dwbh").value;
		var dwmc = document.getElementById("dwmc").value;
		var savetype = document.getElementById("savetype").value;
		if (dwbh == "") {
			alert("单位编号不能为空！");
			return false;
		}
		if (dwmc == "") {
			alert("单位名称名称不能为空！");
			return false;
		}
		//document.getElementById("buttonClose").click();
		document.forms[0].action = "dwlfxx.do?doType=save";
		document.forms[0].submit(); 
		if(savetype){
		BatAlert.showTips('正在修改，请稍侯...');
		}else{
		BatAlert.showTips('正在提交，请稍侯...');
		}
	}

	//exclude left and right space; 
	function trim(s) {
		return rtrim(ltrim(s));
	}
	//exclude left space; 
	function ltrim(s) {
		return s.replace(/^\s*/, "");
	}
	//exclude right space; 
	function rtrim(s) {
		return s.replace(/\s*$/, "");
	}

	function isEmail(s) {
		s = trim(s);
		var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i;
		return p.test(s);
	}
	function reinputagain(){
    		
            document.forms[0].action = "/xgxt/dwlfxx.do";
		 	document.forms[0].submit();
    }
    function showbtn(){
    	var btn = document.getElementById("showtype").value;
		 if("dnset" == btn){
		 	document.getElementById("buttonClose1").style.display='none';
		 	document.getElementById("buttonsave").style.display='none';
		 }
    }
</script>
	</head>
	<body onload="showbtn();">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位来访信息 - 单位来访信息登记</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" id="showtype" name="showtype" value="${dwtype}" />
			<input type="hidden" id="savetype" name=savetype value="${savetype}" />
			<html:hidden property="dwid" name="rs2" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位来访信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="add();" id="buttonSave">
										提 交
									</button>
									<button onclick="reinputagain()" id="buttonClose1">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>编号
							</th>
							<td width="35%">
								<html:text property="dwbh" name="rs2"></html:text>
							</td>
							<th width="15%">
								<font color="red">*</font>单位名称
							</th>
							<td width="35%">
								<html:text property="dwmc" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								单位电话
							</th>
							<td>
								<html:text property="dwdh" name="rs2"></html:text>
							</td>
							<th>
								单位传真
							</th>
							<td>
								<html:text property="dwcz" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								单位邮箱
							</th>
							<td>
								<html:text property="dwyx" name="rs2"></html:text>
							</td>
							<th>
								单位地址
							</th>
							<td>
								<html:text property="dwdz" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								单位邮编
							</th>
							<td>
								<html:text property="dwyb" name="rs2"></html:text>
							</td>
							<th>
								时间
							</th>
							<td>
								<html:text property="sj" name="rs2"
									onclick="return showCalendar('sj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								<html:text property="xm" name="rs2"></html:text>
							</td>
							<th>
								性别
							</th>
							<td>
								<html:text property="xb" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								部门
							</th>
							<td>
								<html:text property="bm" name="rs2"></html:text>
							</td>
							<th>
								职务
							</th>
							<td>
								<html:text property="zw" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								固定电话
							</th>
							<td>
								<html:text property="gddh" name="rs2"></html:text>
							</td>
							<th>
								移动电话
							</th>
							<td>
								<html:text property="yddh" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								电子邮件
							</th>
							<td>
								<html:text property="dzyx" name="rs2"></html:text>
							</td>
							<th>
								来访事宜
							</th>
							<td>
								<html:text property="lfsy" name="rs2"></html:text>
						</tr>
						<tr>
							<th>
								会谈地点
							</th>
							<td>
								<html:text property="ytdd" name="rs2"></html:text>
							</td>
							<th>
								人数
							</th>
							<td>
								<html:text property="rs" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								接待人
							</th>
							<td>
								<html:text property="jdr" name="rs2"></html:text>
							</td>
							<th>
								接待地点
							</th>
							<td>
								<html:text property="jddd" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								接待参与人1
							</th>
							<td>
								<html:text property="jdcyr1" name="rs2"></html:text>
							</td>
							<th>
								接待参与人2
							</th>
							<td>
								<html:text property="jdcyr2" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								接待参与人3
							</th>
							<td>
								<html:text property="jdcyr3" name="rs2"></html:text>
							</td>
							<th>
								接待参与人4
							</th>
							<td>
								<html:text property="jdcyr4" name="rs2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td>
								<html:text property="bz" name="rs2"></html:text>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="ok">
				<script>
		alert("提交成功！");
</script>
			</logic:equal>
			<logic:equal name="insert" value="no">
				<script>
	alert("重复提交！操作失败!");
</script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
		alert("更新成功！");
		window.close();
		dialogArgumentsQueryChick();

</script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
	alert("重复提交！操作失败!");
</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>


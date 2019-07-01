<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script type="text/javascript">
	function onShow(){
		var mklx = $("mklx").value;
		setReadonly();
		if(mklx == "sq"){
			$("yjField").style.display = "none";
		}
	}
	
	//保存申请
	function saveSqInfo(){
	
		var mklx = $("mklx").value;
		var bjsh = $("bjsh").value;
		var xysh = $("xysh").value;
		var xxsh = $("xxsh").value;
		
		
		if(mklx == "jg"){
			
			if(bjsh != "未审核" || xysh != "未审核" || xxsh != "未审核"){
				alert("至少有一级别已经审核该次申请，无法对申请信息进行修改，请确认!");
				return false;
			}
		}
		
		saveUpdate('/xgxt/hndxXszz.do?method=jjknssq&doType=save','xh-xn');
	}
	
	//审核
	function shSqInfo(shzt){

		var mklx = $("mklx").value;
		var xydm = $("xydm").value;
		var xn = $("xn").value;
		var xmmc = $("xmmc").value;
		var xmjb = $("knsjb").value;

		if(shzt == "tg"){//通过
		
			dwr.engine.setAsync(false);
			
			var tableName="hndx_xszz_xyrsb";
			var colList =["xmrs"];
			var pk = "xn||szxy||xmmc||xmjb";
			var pkValue = xn+xydm+xmmc+xmjb;
			var xmrs = "0";
			
			getXszzInfo.getXszzInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.xmrs !="" && data.xmrs != null){
						xmrs = data.xmrs;
					}
				}
			});
			
			var isBjsh = $("isBjsh").value;
			var userType = $("userType").value;
			var shzd = "";
			var xh = $("xh").value;
			var tgrs = "0";
			
			//班级
			if(isBjsh == "true"){
				shzd = "bjsh";
			}
			//学校
			else if(userType == "xx" || userType == "admin"){
				shzd = "xxsh";
			}
			//<bean:message key="lable.xsgzyxpzxy" />
			else if(userType == "xy"){
				shzd = "xysh";
			}

			getXszzInfo.getJjknsRs_Hndx(xn, xydm, xmjb,shzd, xh,function(data){
				if(data!=null){
					tgrs = data;
				}
			});
			
			dwr.engine.setAsync(true);	
			
			if(parseInt(tgrs) + 1 > parseInt(xmrs)){
				alert("该困难生级别的通过人数为"+xmrs+"人，\n现在已经超过上限，无法再审核通过，请确认！");
				return false;
			}
		}
		
		saveUpdate('/xgxt/hndxXszz.do?method=jjknsView&doType=sh&shzt='+shzt,'knsjb')
	}
	</script>
	<body onload="onShow()">
		<html:form action="/hndxXszz" enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" id="lx" name="lx" value="学生" />
			<input type="hidden" id="url" name="url"
				value="hndxXszz.do?method=jjknssq" />
			<input type="hidden" id="forward" name="forward"
				value="/hndxXszz.do?method=jjknssq" />
			<input type="hidden" id="isBjsh" name="isBjsh" value="${isBjsh }" />
			<html:hidden property="xmmc" value="经济困难生认定" />
			<html:hidden name="rs" property="xn" value="${rs.xn }" />
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：${title }
				</div>
			</div>
			<div align="center">
				<font size="4"><strong>${rs.xn }学年经济困难生认定</strong>
				</font>
			</div>
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="35%">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<!-- 申请 -->
							<logic:equal name="mklx" value="sq">
								<!-- 非学生 -->
								<logic:notEqual name="userType" value="stu">
									<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
							</logic:equal>
							<!-- 申请 end-->
							<!-- 结果 -->
							<logic:equal name="mklx" value="jg">
								<logic:equal name="doType" value="add">
									<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
							</logic:equal>
							<!-- 结果 end-->
						</td>
						<td align="right" width="15%">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
						<td align="right">
							民族：
						</td>
						<td align="left">
							${rs.mzmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							出生年月：
						</td>
						<td align="left">
							${rs.csrq }
						</td>
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							${rs.sfzh }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							${rs.zzmmmc }
						</td>
						<td align="right">
							邮政编码：
						</td>
						<td align="left">
							${rs.yzbm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							入学前户口：
						</td>
						<td align="left">
							${rs.hkxz }
						</td>
						<td align="right">
							手机号码：
						</td>
						<td align="left">
							${rs.sjhm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							户籍所在地：
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="hkshen" styleId="hkshen"
								onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="hkshi" styleId="hkshi"
								onchange="loadXian('hkshi','hkxian')" disabled="true">
								<html:options collection="hkshiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="hkxian" styleId="hkxian"
								disabled="true">
								<html:options collection="hkxianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					在校信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							年级：
						</td>
						<td align="left" width="35%">
							${rs.nj }
						</td>
						<td align="right" width="15%">
							校区：
						</td>
						<td align="left">
							${rs.xqmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" width="">
							<html:hidden name="rs" property="xydm" />
							${rs.xymc }
						</td>
						<td align="right" width="">
							楼栋：
						</td>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							专业：
						</td>
						<td align="left" width="">
							${rs.zymc }
						</td>
						<td align="right" width="">
							层数：
						</td>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							班级：
						</td>
						<td align="left" width="">
							${rs.bjmc }
						</td>
						<td align="right" width="">
							寝室号：
						</td>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							入学时间：
						</td>
						<td align="left" width="">
							${rs.rxrq }
						</td>
						<td align="right" width="">
							宿舍电话：
						</td>
						<td align="left">
							${rs.qsdh }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					家庭信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							家庭人数：
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="jtrs"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
						<td align="right" width="15%">
							家庭年总收入：
						</td>
						<td align="left">
							<html:text name="rs" property="jtnzsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							家庭收入主要来源：
						</td>
						<td align="left" width="">
							<html:text name="rs" property="jtsrly" maxlength="20" />
						</td>
						<td align="right" width="">
							家庭月总收入：
						</td>
						<td align="left">
							<html:text name="rs" property="jtyzsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							家庭电话：
						</td>
						<td align="left" width="">
							<html:text name="rs" property="jtdh"
								onkeypress="return onlyNum(this,20)" maxlength="20"
								style="ime-mode:disabled" />
						</td>
						<td align="right" width="">
							家庭人均月收入：
						</td>
						<td align="left">
							<html:text name="rs" property="jtrjsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							家庭负债情况：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="jtfzqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							家庭住址：
						</td>
						<td align="left" width="" colspan="3">
							<html:text name="rs" property="jtdd" maxlength="50"
								style="width: 90%" />
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					申请条件
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							是否孤儿：
						</td>
						<td align="left" width="35%">
							<html:select name="rs" property="sfge">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="15%">
							是否残疾：
						</td>
						<td align="left">
							<html:select name="rs" property="sfcj">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							是否低保户：
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfdb">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							是否烈士子女：
						</td>
						<td align="left">
							<html:select name="rs" property="sflszn">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							是否遭受自然灾害：
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfzrch">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							家庭成员是否长期患重病：
						</td>
						<td align="left">
							<html:select name="rs" property="sfjthb">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							是否有贫困证明：
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfpkzm">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							家中读大学人数：
							<br>
							（含学生本人）
						</td>
						<td align="left">
							<html:text name="rs" property="jtdxrs" maxlength="5" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							德育评定等级：
						</td>
						<td align="left" width="">
							<html:select name="rs" property="dydj">
								<html:options collection="dydjList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="">

						</td>
						<td align="left">

						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							学生陈述&nbsp;&nbsp;&nbsp;
							<br>
							家庭经济&nbsp;&nbsp;&nbsp;
							<br>
							基本情况：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="xsjtjjqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							本人获“奖”、“贷”、
							<br>
							“勤”、“助”、“免”、
							<br>
							“补”情况：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="brjdqzmbqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							其他情况：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="qtqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							贫困证明上传：
						</td>
						<td align="left" width="" colspan="3">
							<!-- 无上传文件 -->
							<logic:empty name="rs" property="scdz">
								<input type="file" name="uploadFile" style="width:60%" id="kk">
								&nbsp;&nbsp;
								<font color="red">(文件大小小于&lt;10M&gt;)</font>
							</logic:empty>
							<!-- 有上传文件 -->
							<logic:notEmpty name="rs" property="scdz">
								<html:hidden name="rs" property="scdz" />
								<a
									href="czxxDtjsDyxx.do?method=downLoadFile&dir=<bean:write name="rs" property="scdz" />"
									target="_blank" />点击下载</a>
								&nbsp;&nbsp;
								<a
									href="javascript:refreshForm('/xgxt/hndxXszz.do?method=jjknssq&doType=fileDel')" />点击删除</a>
							</logic:notEmpty>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset id="yjField">
				<legend>
					审核意见
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>困难生级别：
						</td>
						<td align="left" width="" colspan="3">
							<!-- 审核 -->
							<logic:equal name="mklx" value="sh">
								<html:select name="rs" property="knsjb" style="">
									<html:option value=""></html:option>
									<html:options collection="knsjbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</logic:equal>
							<!-- 审核 end-->
							<!-- 非审核 -->
							<logic:notEqual name="mklx" value="sh">
								<html:select name="rs" property="knsjb" style="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="knsjbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</logic:notEqual>
							<!-- 非审核 end-->
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							班级意见：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="bjsh" />
							<html:textarea name="rs" property="bjshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" width="">
							<bean:message key="lable.xsgzyxpzxy" />意见：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="xysh" />
							<html:textarea name="rs" property="xyshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" width="">
							学校意见：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="xxsh" />
							<html:textarea name="rs" property="xxshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 
					<!-- 申请 --> 
					<logic:equal name="mklx" value="sq">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="saveSqInfo()"
								style="width: 80px">
								保 存
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- 申请 end--> 
					<!-- 结果 --> 
					<logic:equal name="mklx" value="jg">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="saveSqInfo()"
								style="width: 80px">
								保 存
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- 结果 end--> 
					<!-- 审核 --> 
					<logic:equal name="mklx" value="sh">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="shSqInfo('tg')"
								style="width: 80px">
								通 过
							</button> 
							&nbsp;&nbsp;
							<button class="button2" id="buttonSave" onclick="shSqInfo('btg')"
								style="width: 80px">
								不通过
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- 审核 end--> 
					<!-- 非申请 --> 
					<logic:notEqual name="mklx" value="sq">
						<button class="button2" id="buttonClose" onclick="window.close();return false;"
							style="width: 80px" id="buttonClose">
							关 闭
						</button>
					</logic:notEqual> 
					<!-- 非申请 end--> 
					</span>
			</div>
			<logic:present name="fileUplod">
				<script>
					alert("上传文件过大，请确认");
				</script>
			</logic:present>
			<logic:notPresent name="fileUplod">
				<logic:present name="result">
					<script>
						if($("message") && $("message").value != ""){
							alert($("message").value);
							$("message").value = "";
							$("doType").value = "";
						}
						if(opener){
							opener.document.getElementById("search_go").click();
		      				window.close();
	      				}
					</script>
				</logic:present>
			</logic:notPresent>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

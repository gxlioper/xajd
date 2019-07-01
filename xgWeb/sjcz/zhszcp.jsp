<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript">
		function ckdata5(tmp,tp) {
			var tmps = document.getElementById(tmp).value;
			if (tp != '') {
			if (parseFloat(tmps) > parseFloat(tp)) {
				alert('此项成绩不能超过'+tp+'分！');
				document.getElementById(tmp).value = '';
				return;
			}
			}
		}
		// 通用计算总分
		function countzf() {
			var dcj = document.getElementById('dcj').value;
			var zcj = document.getElementById('zcj').value;
			var tcj = document.getElementById('tcj').value;
			if(dcj == null || dcj == '' || dcj == ' '){
				dcj = '0';
			}
			if(zcj == null || zcj == '' || zcj == ' '){
				zcj = '0';
			}
			if(tcj == null || tcj == '' || tcj == ' '){
				tcj = '0';
			}
			pjpyscjzJs.countZf(dcj,zcj,tcj,function (data) {
				if(parseFloat(data) == data) {
					data = Math.round(data * 100) / 100; 
				}
				document.getElementById('zpf').value=data;
			});
		}
	</script>
</head>
	<body onload="checkWinType();">
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyscjzJs.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="userOnLine" name="userOnLine" value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/zhszcp.jsp" />
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<logic:notPresent name="isgdby">
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>综合素质测评信息维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<html:text name='rs' property="xh" maxlength="20"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" >
								选择
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>年度
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学年
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<logic:notEqual value="13742" name="xxdm">
						<td align="right">
							<font color="red">*</font>学期
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						</logic:notEqual>
					</tr>
					<!-- 宁波理工 -->
					<logic:equal value="yes" name="shownbng">
						<tr>
						<td align="right">
							年级
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						
								<%--<td align="right">
									德成绩
								</td>
								<td align="left">
									<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countdcj()" maxlength="5" styleId="dcj"></html:text>
								</td>
							
					--%>
					<td align="right">
											德育评议成绩
										</td>
										<td align="left">
											<input type="text" id="ddcj" name="ddcj" value="<bean:write name="rs" property="ddcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('ddcj',10)"/>
										</td>
					</tr>
					<tr>
						<td align="right">
							
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						
								<%--<td align="right">
									智成绩
								</td>
										<td align="left">
									<input type="text" name="zcj" onblur="ckdata(this);"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" />
								</td>
					--%>
					<td align="right">
											行为纪实成绩
										</td>
										<td align="left">
											<input type="text" id="xwcj" name="xwcj" value="<bean:write name="rs" property="xwcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('xwcj',10)"/>
										</td>
					</tr>
					<tr>
						<td align="right">
							专业
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<%--<td align="right">
									体成绩
								</td>
								<td align="left">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);" styleId="tcj"></html:text>
								</td>
					--%>
					<td align="right">
											生活园区成绩
										</td>
										<td align="left">
											<input type="text" id="shqcj" name="shqcj" value="<bean:write name="rs" property="shqcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('shqcj',5)"/>
										</td>
					</tr>
					</logic:equal>
					<logic:notPresent name="shownbng">
						<tr>
						<td align="right">
							年级
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								德育积分
							</td>
							<td align="left">
								<html:text name="rs" property="dcj" styleId="dcj" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									德成绩
								</td>
								<td align="left">
									<html:text name="rs" property="xydykpf" styleId="xydykpf" style="width:90px"></html:text>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<logic:equal name="xxdm" value="10402" scope="session"><!--漳州师范 -->
								<td align="right">
									思想政治素质总积分
								</td>
								<td align="left">
									<html:text name="rs" property="dcj" style="width:90px"
										styleId="dcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
								</logic:equal>
								<logic:notEqual value="10402" name="xxdm" scope="session">
								<td align="right">
									
									<logic:equal value="12682" name="xxdm">
									行为规范
									</logic:equal>
									<logic:notEqual value="12682" name="xxdm">
										德成绩
									</logic:notEqual>
								</td>
								<td align="left">
									<logic:present name="isAHJG">
										<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countzf1();" maxlength="5" styleId="dcj"></html:text>
									</logic:present>
									<logic:notPresent name="isAHJG">
										<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countzf()" maxlength="5" styleId="dcj"></html:text>
									</logic:notPresent>
								</td>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								智育积分
							</td>
							<td align="left">
								<html:text name="rs" property="zcj" styleId="zcj" onblur="countCpzf()" maxlength="5" onblur="checkInputCj(this)"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									公寓德成绩
								</td>
								<td align="left">
									<html:text name="rs" property="gydykpf" styleId="gydykpf" style="width:90px"></html:text>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
							    <logic:equal name="xxdm" value="10402" scope="session"><!--漳州师范 -->
							    <td align="right">
									学习总积分
								</td>
								<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10402" scope="session">
								<td align="right">
									<logic:equal value="12682" name="xxdm">
									文化成绩
									</logic:equal>
									<logic:notEqual value="12682" name="xxdm">
										智成绩
									</logic:notEqual>
									
								</td>
									<logic:present name="isAHJG">
										<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" onblur="ckdata(this);countzf1();"/>
								</td>
									</logic:present>
									<logic:notPresent name="isAHJG">
										<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" onblur="ckdata(this);countzf()" maxlength="5" id="zcj" maxlength="5" />
								</td>
									</logic:notPresent>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							专业
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								体育积分
							</td>
							<td align="left">
								<html:text name="rs" property="tcj" styleId="tcj" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									智成绩
								</td>
								<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj"/>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<logic:equal name="xxdm" value="10402" scope="session"><!--漳州师范 -->
								<td align="right">
									智能素质总积分
								</td>
								<td align="left">
									<html:text name="rs" property="znszcj" style="width:90px"
										styleId="znszcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10402" scope="session">
								<td align="right">
									体育成绩
								</td>
								<td align="left">
								<logic:present name="isAHJG">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);countzf1();" styleId="tcj"></html:text>
								</logic:present>
								<logic:notPresent name="isAHJG">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);countzf()" styleId="tcj"></html:text>
								</logic:notPresent>
								</td>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					</logic:notPresent>
					<tr>
						<td align="right">
							班级
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<!-- 宁波理工 -->
						<logic:equal value="yes" name="shownbng">
							<td align="right">
								加权平均分	
							</td>
							<td align="left">
							<input type="text" name="kcjqpfj" id="kcjqpfj" style="width:90px" onblur="ckdata(this)" value="<bean:write name="rs" property="kcjqpfj"/>" maxlength="5"/>
							<input type="hidden" name="zhszcpzf" id="zhszcpzf" value=""/>
							</td>
						</logic:equal>
						<!-- 安徽建筑 -->
						<logic:present name="isAHJG">
							<td align="right">
								综合素质测评分	
							</td>
							<td align="left">
							<input type="text" name="zhszcpzf" id="cpf" style="width:90px" value="<bean:write name="rs" property="zhszcpzf"/>"/>
							</td>
						</logic:present>
						<logic:present name="showzszy">
							<td align="right">
								技能加分
							</td>
							<td align="left">
								<html:text name="rs" property="jnjf" styleId="jnf" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:present name="showhzy">
							<td align="right">
									体成绩
							</td>
							<td align="left">
								<html:text name="rs" property="tcj" styleId="tcj" maxlength="5" onblur="ckdata(this)" style="width:90px"></html:text>
							</td>
						</logic:present>
						
						<logic:equal name="xxdm" value="10402" scope="session"><!--漳州师范 -->
						<td align="right">
							体育素质总积分
						</td>
						<td align="left">
							<html:text name="rs" property="tcj" style="width:90px"
								styleId="tcj" maxlength="5"  onblur="checkInputCj(this)"/>
						</td>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10402" scope="session">
							<logic:notEqual value="10878" name="xxdm">
								<logic:notEqual value="12872" name="xxdm">
								
										<td align="right">
										总成绩
										</td>
										<td align="left">
											<html:text name="rs" property="zpf" readonly="true" styleId="zpf" maxlength="5" style="width:90px"></html:text>
										</td>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
					</tr>
					<logic:present name="showhzy">
						<tr>
							<td align="right">
							</td>
							<td>
							</td>
							<td align="right">
								学习创新
							</td>
							<td align="left">
								<html:text name="rs" property="gzxxcx" styleId="gzxxcx" style="width:90px"></html:text>
							</td>
						</tr>
					</logic:present>
					<logic:present name="showzszy">
						<tr>
							<td align="right">
								测评总分
							</td>
							<td align="left">
								<html:text name="rs" property="cpzf" styleId="cpf" readonly="true"/>
							</td>
						</tr>
					</logic:present>
					<logic:equal name="xxdm" value="10402" scope="session">
						<tr>
							<td align=right>加分</td>
							<td><html:text name="rs" property="jnjf" styleId="jnjf" onblur="checkInputCj(this)"/></td>
							<td align="right">
								测评总分
							</td>
							<td align="left">
								<html:text name="rs" property="cpzf" styleId="cpzf" readonly="true"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="yes" name="shownbng">
						<%--<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>德育成绩明细</strong>
											</div>
										</div>
									
							</td>
						</tr>
						--%><tr>
						<td colspan="4">
							<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										
										
									</tr>
									<tr style="width:20px">
										
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>综合素质成绩及排名</strong>
											</div>
										</div>
									
							</td>
						</tr>
						<tr>
						<td colspan="4">
							<div id="child5" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="height:20px">
										<td align="right" style="width:120px">
											综合素质测评总分
										</td>
										<td align="left">
											${zhszcpzf }
										</td>
										<td align="right">
											班级排名
										</td>
										<td align="left" style="width:150px">
											${zhpm }
										</td>
									</tr>
									<tr style="">
										<td align="right" style="width:120px">
											德育测评总分
										</td>
										<td align="left">
											${dcj }
										</td>
										<td align="right">
											班级排名
										</td>
										<td align="left" style="width:150px">
											${dcjpm }
										</td>
									</tr>
									<tr style="">
										<td align="right" style="width:120px">
											加权成绩
										</td>
										<td align="left">
											
										</td>
										<td align="right">
											专业排名
										</td>
										<td align="left" style="width:150px">
											${jpfpm }
										</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
					</logic:equal>
					<!-- 安徽建工 -->
					<logic:present name="isAHJG">
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child0.style.display=(document.all.child0.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>思想表现得分明细</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child0" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											中共正式党员
										</td>
										<td align="left">
											<html:text name="rs" property="zgdy" styleId="zgdy" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											中共预备党员
										</td>
										<td align="left">
											<html:text name="rs" property="zgybdy" styleId="zgybdy" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											所在班级是否先进班级
										</td>
										<td align="left">
											<html:text name="rs" property="xjbj" styleId="xjbj" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											所在宿舍是否文明宿舍
										</td>
										<td align="left">
											<html:text name="rs" property="wmss" styleId="wmss" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>社会工作得分明细</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child1" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											校学生会主席、副主席<br>团委委员、团总支副书记
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb1" styleId="xsgb1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											校学生会正副部长、系学生会正<br>副主席、团总支委员、校学工助理
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb2" styleId="xsgb2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											系学生会正副部长、<br>班长、团支部书记
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb3" styleId="xsgb3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											其他学生干部
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb4" styleId="xsgb4" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>英语计算机水平得分明细</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child3" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											大学英语四、六级
										</td>
										<td align="left">
											<html:text name="rs" property="wysp" styleId="wysp" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											国家计算机二级及以上
										</td>
										<td align="left">
											<html:text name="rs" property="jsjsp" styleId="jsjsp" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>校奖学金得分明细</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											学校一等奖学金
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj1" styleId="xjjxj1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											学校二等奖学金
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj2" styleId="xjjxj2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											学校三等奖学金
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj3" styleId="xjjxj3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>其他奖励得分明细</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child5" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											国家级奖励一等
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl1" styleId="gjjl1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											国家级奖励二等
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl2" styleId="gjjl2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											国家级奖励三等
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl3" styleId="gjjl3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											省级奖励一等
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl1" styleId="sjjl1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											省级奖励二等
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl2" styleId="sjjl2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											省级奖励三等
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl3" styleId="sjjl3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											校三好学生、<br>优秀学生干部、优秀团员(干)
										</td>
										<td align="left">
											<html:text name="rs" property="xjgr1" styleId="xjgr1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											各类积极分子
										</td>
										<td align="left">
											<html:text name="rs" property="xjgr2" styleId="xjgr2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
						<tr align="">
							<td align="right">量化标准总分</td>
							<td align="left"><html:text name="rs" property="zf" styleId="zf" styleClass="inputtext"></html:text></td>
							<td>&nbsp;<input type="hidden" name="zxq" id="zxq" value="<bean:write name="rs" property="zxq"/>"/> </td>
							<td>&nbsp;</td>
						</tr>
					</logic:present>
					<tr align="left">
						<td align="right">
							备注
							<br/>
							<font color="red">限制在1000字内&nbsp;&nbsp;</font>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:550px;overflow:auto" rows='5'  onkeyup="checkLen(this,1000)"/>
						</td>
					</tr>
					<tfoot>
					<tr><td colspan="4">
					<div class="btn">
						
					<logic:present name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd') && judgeChar())dataDoSave('xn-xq-xh');" id="buttonSave">
							保 存
						</button>
					</logic:present>
					<logic:notPresent name="showhzy">
						<logic:notEqual value="view" name="doType">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');" id="buttonSave">
							保 存
						</button>
						</logic:notEqual>
					</logic:notPresent>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="" onclick="Close();return false;"
						id="buttonClose">
						关 闭
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
					</tbody>
				</table>
				</div>
				</logic:notPresent>
				<!-- 广东白云<bean:message key="lable.xsgzyxpzxy" /> -->
				<logic:equal value="yes" name="isgdby">
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr>
							<td colspan="4" align="center">
								综合素质测评信息维护
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								选择
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>年度
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学年
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学期
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							专业
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							班级
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">操行成绩</td>
						<td align="left">
							<html:text name='rs' property="cxcj" styleId="cxcj" maxlength="6"></html:text>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							备注
						</td>
						<td colspan="3">ea<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
					<tfoot>
					<tr><td colspan="4">
					<div class="btn">
						
					<logic:present name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd') && judgeChar())dataDoSave('xn-xq-xh');" id="buttonSave">
							保 存
						</button>
					</logic:present>
					<logic:notPresent name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');" id="buttonSave">
							保 存
						</button>
					</logic:notPresent>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="" onclick="Close();return false;"
						id="buttonClose">
						关 闭
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
				</table>
				</logic:equal>
			
					
				
			</logic:notEmpty>

  			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
	</body>
	<script language="javascript">
	  //漳州师范的测评总分计算
	  initCpzf();
	  //宁波理工计算德育成绩
	  function countDycj(){
	  	var ddcj = document.getElementById('ddcj').value;
	  	var xwcj = document.getElementById('xwcj').value;
	  	var shqcj = document.getElementById('shqcj').value;
	  	if(ddcj == null || ddcj == "" || ddcj == " "){
			ddcj = '0';
			document.getElementById('dcj').value = "";
		}
		if(xwcj == null || xwcj == "" || xwcj == " "){
			xwcj = '0';
			document.getElementById('dcj').value = "";
		}
		if(shqcj == null || shqcj == "" || shqcj == " "){
			shqcj = '0';
			document.getElementById('dcj').value = "";
		}
		document.getElementById('dcj').value = parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100;
	  }
	  function countdcj(){
	  	var ddcj = document.getElementById('ddcj').value;
	  	var xwcj = document.getElementById('xwcj').value;
	  	var shqcj = document.getElementById('shqcj').value;
	  	var dcj = document.getElementById('dcj').value;
	  	if(ddcj == null || ddcj == "" || ddcj == " "){
			ddcj = '0';
			
		}
		if(xwcj == null || xwcj == "" || xwcj == " "){
			xwcj = '0';
			
		}
		if(shqcj == null || shqcj == "" || shqcj == " "){
			shqcj = '0';
			
		}
		var dcj1 = parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100;
		if (parseFloat(dcj) != 0 && parseFloat(dcj1) && parseFloat(dcj)!=parseFloat(dcj1)) {	
			alert('德育成绩计算有误，请确认！');
			document.getElementById('dcj').value=parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100 ;
		}
	  }
	</script>
</html>

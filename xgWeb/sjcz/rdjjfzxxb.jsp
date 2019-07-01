<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/szxx_PartyBuild.js"></script>
	<script language="javascript" src="js/dtjsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">
	function dyzz(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------转为预备党员时间---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "时间:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveYbdy()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function time(id){
	return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("请确定转为预备党员时间");
				return false;
			}
		}
		if(checkXnNd('xn','nd')){
			partyBuildDataDoSave('xn-xq-xh-pxkssj');
		}
	}
	</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			<%--<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>--%>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />			
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/rdjjfzxxb.jsp" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
					
					<thead>
						<tr>
							<th colspan="4"><span>入党积极分子维护</span></th>
						</tr>
					</thead>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				         <button type="button" class="button2" onclick="dataCanModi(true)" id="buttonModi">
							修 改
						</button>
						<button type="button" onclick="if(checkXnNd('xn','nd')&&checkbz())dataDoSave('xn-xq-xh-pxkssj');"
							id="buttonSave">保 存
						</button>
						<!-- 金华职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:equal name="xxdm" value="12061">
						<logic:equal name="doType" value="modi">
							<button type="button" onclick="dyzz()">
							转入预备党员
							</button>
					</logic:equal>
					</logic:equal>
					<button type="button" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
					 </div>
					 </td>
					</tr>
				 </tfoot>
					    
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
						  <logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?zzmm=rdjjfz',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									选择
								</button>
							</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
						      <td align="left">
							        <html:hidden name='rs' property="xh" styleId="xh" />
							        <bean:write name="rs" property="xh" />
						      </td>
					         </logic:equal>
							<th>
								<font color="red">*</font>年度
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" >${rs.xm }</html:text>
							</td>
							<th>
								<font color="red">*</font>学年
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								<font color="red">*</font>学期
							</th>
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
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								<font color="red">*</font>获得时间
							</th>
							<td align="left">
								<html:text name='rs' property="kssj" styleId="pxkssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxkssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<logic:equal name="xxdm" value="12957" scope="session">
								<th>
									是否培养后考察发展预备党员
								</th>
								<td align="left">
									<html:select name='rs' property="sfpydy" style="width:40px"
										styleId="sfpydy">
										<html:option value=""></html:option>
										<html:options collection="ynList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</logic:equal>
							<!-- 浙江传媒<bean:message key="lable.xsgzyxpzxy" /> -->
							<logic:equal name="xxdm" value="11647">
							<th>
								发展对象开始时间
							</th>
							<td align="left">
								<html:text name='rs' property="fzsj" styleId="fzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('fzsj','y-mm-dd');" />
							</td>
							</logic:equal>
							<!-- end浙江传媒<bean:message key="lable.xsgzyxpzxy" /> -->
							<logic:present name="showhzy">
								<th>
									推荐单位
								</th>
								<td align="left">
									<html:text name='rs' property="tjdw" styleId="dw" />
								</td>
							</logic:present>
							<!-- 浙江传媒<bean:message key="lable.xsgzyxpzxy" /> -->
							<logic:notEqual name="xxdm" value="11647">
								<logic:notPresent name="showhzy">
									<td id="theTd3" align="right">
									</td>
									<td id="theTd4" align="left">
									</td>
								</logic:notPresent>
							</logic:notEqual>
							<!-- end浙江传媒<bean:message key="lable.xsgzyxpzxy" /> -->
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<logic:equal name="xxdm" value="12957" scope="session">
								<th>
									思想汇报次数
								</th>
								<td align="left">
									<html:text name='rs' property="sxhbcs" styleId="sxhbcs"
										readonly="true" />
								</td>
							</logic:equal>
							<logic:present name="showhzy">
								<th>
									联系人1
								</th>
								<td align="left">
									<html:text name='rs' property="lxr1" styleId="lxrfirst" />
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">

								<td id="theTd1" align="right">
								</td>
								<td id="theTd2" align="left">
								</td>
							</logic:notPresent>

						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<logic:equal name="xxdm" value="12957" scope="session">
								<th>
									党建联系人
								</th>
								<td align="left">
									<html:text name='rs' property="lxr1" styleId="lxrfirst" />
								</td>

							</logic:equal>
							<logic:present name="showhzy">
								<th>
									联系人2
								</th>
								<td align="left">
									<html:text name='rs' property="lxr2" styleId="lxrsec" />
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<td id="theTd5" align="right">
								</td>
								<td id="theTd6" align="left">
								</td>
							</logic:notPresent>
						</tr>
						<logic:equal name="xxdm" value="12957" scope="session">
							<tr>
								<th>
									培养时间
								</th>
								<td align="left">
									<html:text name='rs' property="pysj" styleId="pysj" />
								</td>
								<th>
									培养次数
								</th>
								<td align="left">
									<html:text name='rs' property="pycs" styleId="pycs" />
								</td>
							</tr>
							<tr>
								<th>
									谈话次数
								</th>
								<td align="left">
									<html:text name='rs' property="thcs" styleId="thcs" />
								</td>
								<th>
								</th>
								<td align="left">
								</td>
							</tr>
						</logic:equal>
						<logic:present name="showhzy">
							<tr>
								<th>
									任职情况：
								</th>
								<td align="left">
									<html:text name='rs' property="rzqk" styleId="rs" />
								</td>
								<th>
								</th>
								<td align="left">
								</td>
							</tr>
						</logic:present>
							<tr>
								<th>
								学生层次
								</th>
								<td align="left">
									<html:select name='rs' property="xsccdm" style="width:90px">
													<html:option value=""></html:option>
													<html:options collection="xsccList" property="xsccdm"
														labelProperty="xsccmc" />
									</html:select>
								</td>
								<th>
								</th>
								<td align="left">
								</td>
							</tr>
							<logic:equal name="xxdm" value="12645">
						<tr>
							<th>
									列为积极分子时间
							</th>
							<td align="left">
								<html:text name='rs' property="lwjjfzsj" styleId="lwjjfzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('lwjjfzsj','y-mm-dd');" />
							</td>
							<th>
									列为计划发展对像时间
							</th>
							<td align="left">
								<html:text name='rs' property="lwjhfzdxdsj" styleId="lwjhfzdxdsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('lwjhfzdxdsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
									支部大会通过该人入党时间
							</th>
							<td align="left">
								<html:text name='rs' property="zbdhtggrrdsj" styleId="zbdhtggrrdsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zbdhtggrrdsj','y-mm-dd');" />
							</td>
							<th>
									培养人
							</th>
							<td align="left">
								<html:text name='rs' property="pyr" styleId="pyr"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
									发展类型
							</th>
							<td align="left">
								<html:text name='rs' property="fzlx" styleId="fzlx"  maxlength="50"/>
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						</logic:equal>
						<tr align="left">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' onblur="chLeng(this,'60')"/>
							</td>
						</tr>
						<logic:present name="shownblg">
						<tr align="left">
						<td align="center" colspan="4">
							<table width="100%" border="1" class="formlist">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>宿舍表现记录</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
						<tr>
					<td colspan="4">
							<div id="child2" style="display:none">
								<table width="100%" border="1" align="center" class="formlist">
						<tr align="left">
							<th>
								一学期宿舍表现
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx1' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								二学期宿舍表现
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx2' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								三学期宿舍表现
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx3' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								四学期宿舍表现
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx4' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</table>
						</div>
						
					</td>
					</tr>
						</logic:present>
						</tbody>
				</table>
				</div>
				</fieldset>
				<div id="tmpdiv1"></div>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
	<logic:equal name="xxdm" value="12957" scope="session">
	<script type="text/javascript">
	       var hideTdFlag = document.getElementById("lxrfirst");
	       //alert("adfasdfasdfasdfasdf");
	       if(hideTdFlag){         
		      document.getElementById("theTd1").style.display = 'none';
		      document.getElementById("theTd2").style.display = 'none';
		   	  document.getElementById("theTd3").style.display = 'none';
		   	  document.getElementById("theTd4").style.display = 'none';
		   	  document.getElementById("theTd5").style.display = 'none';
		   	  document.getElementById("theTd6").style.display = 'none';
		   }
	</script>
	</logic:equal>
</html>

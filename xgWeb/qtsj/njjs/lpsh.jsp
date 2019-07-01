<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
<!--	
		function saveData() {
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var flag = false;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						flag = true;
					}
				}
				if (flag){
					if (confirm('确定要保存所选择的数据吗？')){
							return true;
						}
				}else{
						alert("没有选择相应记录，请选择之后再进行操作！！");
						return false;
				}
		}
				
		function showShDiv() {
					var flg = saveData();
					if (!flg){
						return false;
					}
					
					$('tempDiv').style.display='';
					
					var d_width = document.body.clientWidth;
					var d_height = document.body.clientHeight ;
					var d_left = 0;
					var d_top = 0;
					var d_color = "#EEF4F9";
					var d_width_top = 350;
					var d_height_top = 200;
					var d_left_top = (d_width - d_width_top) / 2;
					var d_top_top = (d_height - d_height_top) / 2;
					var d_color_top = "#EEF4F9";
					
					dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
					dd_html += "</div>";
					dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
					dd_html += "<br/><br/>";
					dd_html += "<table width='300' class='tbstyle'>";
					dd_html += "<thead>";
					dd_html += "<tr height='30'>";
					dd_html += "<td colspan='2'>";
					dd_html += "--------------------请填写理赔金额--------------------";
					dd_html += "</td>";
					dd_html += "</tr>";
					dd_html += "</thead><tbody>";
					dd_html += "<tr><td align='right'><font color='red'>*</font>理赔金额:</td><td><input type='text' id='lpje' name='lpje' onblur='checkMoney(this)' maxlength='5'/></td></tr>";
					dd_html += "<tr><td align='center' colspan='2'><button class='button2'  onclick='saveShjg();'>确&nbsp;&nbsp;定</button> &nbsp;&nbsp;&nbsp;&nbsp;<button class='button2' onclick='closeDiv();'>关&nbsp;&nbsp;闭</button></td></tr></tbody></table>";
			
				tempDiv.innerHTML = dd_html;
		}
	
		function closeDiv(){
			$('tempDiv').style.display='none';
		}
		
		function saveShjg() {
			if (''==$('lpje').value) {
				alert('请把带*项填写完整!');
				return false;
			}
					
			document.forms[0].action = '/xgxt/bxxx.do?method=lpsh&doType=sh';
			document.forms[0].submit();
	}
//-->
</script>
	<body onload="xyDisabled('xy');">
		
		<html:form action="/bxxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			
			<logic:equal value="xy" name="userType" scope="session">
				<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
			</logic:equal>
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
								<td align="left" nowrap>
									年级：
									<html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
									&nbsp;&nbsp;
									学号：
									<html:text property="querylike_xh" maxlength="20" style="width:80px"></html:text>
									&nbsp;&nbsp;
									姓名：
									<html:text property="querylike_xm" maxlength="20" style="width:80px"></html:text>
									&nbsp;&nbsp;
									理赔项目:
									<html:select property="queryequals_lpxm" >
										<html:options collection="lpxmList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<td  rowspan="2"align="center">
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/bxxx.do?method=lpsh&doType=query')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<Td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="queryequals_bjdm"  styleId="bj" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</Td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="tempDiv"></div>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
											ondblclick="showInfo('/xgxt/bxxx.do?method=lpInfo','view','600','450')"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									   		<input type="hidden" value="<bean:write name="v" />">
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div>
						<br/><br/>
					</div>
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
					<button class="button2" onclick="showInfo('/xgxt/bxxx.do?method=lpInfo','sh','600','450')"
							style="width:80px">
							单个审核
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="showShDiv();"
							style="width:80px">
							通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="shformdata('/xgxt/bxxx.do?method=lpsh&shjg=不通过&doType=sh');"
							style="width:80px">
							不通过
					</button>
				</div>
				</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:equal value="true" name="result">
			<script>
			alert('操作成功');
			document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>

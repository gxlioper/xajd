<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		function checkTjxnxq(){
			var dd_html = "";
			dd_html += "<table width='240' class='formlist'>";
			dd_html += "<tbody>";
			dd_html += "<tr >";
			dd_html += "<th align='right' width='30%'>";
			dd_html += "<font color='red'>*</font>学年:";
			dd_html += "</th>";
			dd_html += "<td align='left'>";
			dd_html += "<select name='tjxn' id ='tjxn'>";
			dd_html += $('stjxn').innerHTML;
			dd_html += "</select>";
			dd_html += "</td>";
			dd_html += "</tr>";
			
			dd_html += "<tr>";
			dd_html += "<th align='right' width='30%'>";
			dd_html += "<font color='red'>*</font>学期:";
			dd_html += "</th>";
			dd_html += "<td align='left'>";
			dd_html += "<select name='tjxq' id ='tjxq'>";
			dd_html += $('stjxq').innerHTML;
			dd_html += "</select>";
			dd_html += "</td>";
			dd_html += "</tr>";
		
			dd_html += "<tr bgcolor='EEF4F9'>";
			dd_html += "<td colspan='2' align='right'>";
			dd_html += "<button  onclick='wsjctj();'>确定</button>";
			dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			dd_html += "<button  onclick='closeStuDiv()'>关闭</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table>";
			showTempDivForJs("请选择统计范围",dd_html,280,140);	
		}
		
		function closeStuDiv(){
			$('tempDiv').style.display='none';
			i = document.getElementsByTagName("select").length;
			
			for (j = 0; j < i; j++) {
				var obj=document.getElementsByTagName("select")[j];
				if(obj.id!="stjxn" && obj.id!="stjxq"){
					obj.style.visibility = "";
					obj.style.display = "";
				}
			}
		}
		function wsjctj(){
			var xn=$('tjxn').value;
			var xq=$('tjxq').value;
			if(xn=="" || xq==""){
				alert("请选择要统计的学年和学期！");
				return false;
			}
			var url="/xgxt/zjcmGygl.do?method=wstjManage&xn="+xn+"&xq="+xq;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
			closeStuDiv();
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="csh" name="csh" value="${csh }" />
			<html:select property="stjxn" style="display:none" styleId="stjxn">
				<html:options collection="xnList" property="xn" labelProperty="xn" />
			</html:select>												
			<html:select property="stjxq" style="display:none" styleId="stjxq" onchange="">
				<html:option value=""></html:option>
				<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
			</html:select>
			<!-- 隐藏域 end-->
			<!-- 页签 -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li class="ha">
							<a href="#"
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcManage')"
							 	id="${card.whdmb }">
								<span>查看</span>
							</a>
						</li>
						<li>
							<a href="#" 
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcUpdate')"
							 	id="${card.whdmb }">
								<span>录入</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 页签 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','update','600','480')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/zjcmGygl.do?method=wsjcManage','del')"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
						</li>
						</logic:equal>
						<logic:equal name="xxdm" value="13104">
							<a href="#" 
									onclick="checkTjxnxq()"
									class="btn_tj">统计</a>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('kssj','jssj')){allNotEmpThenGo('/xgxt/zjcmGygl.do?method=wsjcManage')}">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm" style="width: 100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="width: 100px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="cs" style="width: 100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="width: 100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="" styleId="xn" onchange="">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>												
								</td>
								<th>
									学期		
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									检查时间(开始)
								</th>
								<td>
									<html:text  property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 60%"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
								</td>
								<th>
									检查时间(结束)
								</th>
								<td>
									<html:text  property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 60%"
										onclick="return showCalendar('jssj','y-mm-dd');"/>		
								</td>
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									
									<logic:equal value="xy" name="userType" scope="session">
										<html:hidden value="${userDep }" property="xydm"/>
									</logic:equal>
									
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td>
										卫生检查等级
									</td>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','view','600','480')">
									<td align="center">
										<input type="checkbox" id="checkVal" name="checkVal" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
	</body>
</html>

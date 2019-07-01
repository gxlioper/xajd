<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>

		<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xysh='+curr_row.getElementsByTagName('input')[2].value
				+'&xxsh='+curr_row.getElementsByTagName('input')[3].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
		function isSh(){
			if(curr_row != null){
					var xysh = curr_row.getElementsByTagName('input')[2].value;
				var xxsh = curr_row.getElementsByTagName('input')[3].value;
				var userType = $('userType').value;
			
				if((userType == "fdy" && xysh == "通过") || (userType == "xy" && xxsh == "通过")){
					alert("您选择的学生已被上级审核通过，您无权修改");
				}else{
					modi('gdby_tygl.do?method=tyViewAndModi&doType=modi');
				}
			}else{
				alert('请选择要修改的数据行！');
			}
		}
	</script>
	</head>
	<body
		onload="choiceDisabled('xy','xy');choiceDisabled('stu','xh-xm-xy-zy-bj');">
		<html:form action="/guizhdx.do?method=zcfscx" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="userName" value="${userName }" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="wjcfDataExport('guizhdx.do?method=zcfsExp',600,400)"
									class="btn_dc"> 导出数据 </a>
							</li>
						</ul>
					</div>
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="8">
										<input type="hidden" name="go" value="a" />
										<div class="btn">
											<button class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/guizhdx.do?method=zcfscx&go=go')">
												查 询
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										年级
									</th>
									<td>
										<html:select property="queryequals_nj" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										学年
									</th>
									<td>
										<html:select property="queryequals_xn" styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学号
									</th>
									<td>
										<logic:equal value="stu" name="userType">
											<input type="hidden" name="querylike_xh" value="${xh }" />
										</logic:equal>
										<html:text property="querylike_xh" styleId="xh"></html:text>
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="querylike_xm" styleId="xm"></html:text>
									</td>
								</tr>

								<tr>
										<logic:equal value="xy" name="userType">
											<input type="hidden" name="queryequals_xydm"
												value="${userDep}">
										</logic:equal>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="queryequals_xydm" style="width:150px"
											styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>	
									<th>
										专业
									</th>
									<td>
										<html:select property="queryequals_zydm" style="width:150px"
											styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										班级
									</th>
									<td>
										<html:select property="queryequals_bjdm" style="width:150px"
											styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										<logic:equal value="fdy" name="userType">
											<input type="hidden" name="isFdy" value="true" />
										</logic:equal>
									</td>
									<th></th>
									<td></td>
								</tr>
							</tbody>
							</table>
							</div>
						</div>
						<div class="formbox">
					    <h3 class="datetitle_01">
					    <span>
					    	查询结果&nbsp;&nbsp;
					    	<logic:empty name="rs">
								<font color="red">未找到任何记录！</font> 
					 		 </logic:empty>
					    </span>
					    </h3>
							<logic:notEmpty name="rs">
							<div class="con_overlfow">
								 <table summary="" id="rsTable" class="dateline" width="100%">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>"
													 nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowMoreClick(this,'',event);" align="center"
											style="cursor:hand">
											<logic:iterate id="v" name="s" offset="0">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</tbody>
								</table>
								<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
								</div>
						</logic:notEmpty>
						</div>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>

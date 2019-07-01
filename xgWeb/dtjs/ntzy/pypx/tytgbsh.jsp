<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xh='+curr_row.getElementsByTagName('input')[1].value,700,500);
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
		
	function isSh(mk){
		if(curr_row != null){
			var xysh = curr_row.getElementsByTagName('input')[2].value;
			var xxsh = curr_row.getElementsByTagName('input')[3].value;
			var userType = $('userType').value;
			
			if((userType == "fdy" && xysh == "通过") || (userType == "xy" && xxsh == "通过")){
				alert("您选择的学生已被上级审核通过，您无权审核");
			}else{
				modi('ntzy_pypx.do?method=tytgbshone&doType=shone&mk='+$('mk').value);
			}
		}else{
				alert('请选择要审核的数据行！');
		}
	}
	</script>
	</head>
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="ntzy_pypx" method="post">
			<input type="hidden" id="mk" name="mk" value="${mk }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<div class="title">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em>
						<logic:equal value="yxty" name="mk"><a>优秀共青团员审核</a></logic:equal>
					<logic:equal value="yxtgb" name="mk"><a>优秀共青团干部审核</a></logic:equal>
					<logic:equal value="tzzgb" name="mk"><a>优秀团总支干部审核</a></logic:equal>
					<logic:equal value="xshgb" name="mk"><a>优秀学生会干部审核</a></logic:equal>
					</p>
				</div>
			</div>
			
			<fieldset>
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','ntzy_pypx.do?method=tytgbsh&shjg=tg&doType=sh&go=go&xm=yxty','sh')">通过</a></li>
						<li><a href="#" class="btn_xg" onclick="batchData('primarykey_cbv','ntzy_pypx.do?method=tytgbsh&shjg=btg&doType=sh&go=go&xm=yxtgb','sh')">不通过</a></li>
						<li><a href="#" class="btn_sh" onclick="isSh();">单个审核</a></li>
					</ul>
					</div>
					</div>
				</logic:equal>
				
				<div class="searchtab">
					<table width="100%" border="0">
	
				  <tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/ntzy_pypx.do?method=tytgbsh&go=go')">
		              		查 询
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		             		  <button type="button" class="btn_cz" id="btn_cz" onclick="location='pypxsh.do'">
		              			返回
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     		</tfoot>
		     		
					<tbody>
						<tr>
							<th>
								<input type="hidden" name="queryequals_mk" value="${mk }"/>
								<input type="hidden" name="queryequals_xn" value="${xn }"/>
								学年：</th>
								<td><select disabled="disabled">
									<option>${xn }</option>
								</select></td>
								<th>学号：</th>
								<td><html:text property="querylike_xh" styleId="xh"></html:text></td>
								<th>姓名：</th>
								<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							</tr>
							<tr>
						</tr>
				
						<tr>
							<th>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								<bean:message key="lable.xb" />：</th>
								<td><html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								<th>专业：</th>
								<td><html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>班级：</th>
								<td><html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
								
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="shzd" value="xysh"/>
									<input type="hidden" name="shsj" value="xyshsj"/>
								</logic:equal>
								<logic:equal value="xx" name="userType">
									<input type="hidden" name="queryequals_xysh" value="通过" />
									<input type="hidden" name="shzd" value="xxsh"/>
									<input type="hidden" name="shsj" value="xxshsj"/>
								</logic:equal>
						</tr>
					</tbody>
				</table>
				</div>
			</fieldset>
			
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
				<fieldset>
					<table class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_pypx.do?method=pypxview&doType=view&mk='+$('mk').value)"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="${v }"/>
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="4">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" indexId="index">
									<logic:equal name="index" value="7">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
									<logic:equal name="index" value="8">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pypxForm"></jsp:include>
			  <!--分页显示-->
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

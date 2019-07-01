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
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
		
		function isSh(){
		if(curr_row != null){
			var xysh = curr_row.getElementsByTagName('input')[1].value;
			var xxsh = curr_row.getElementsByTagName('input')[2].value;
			var userType = $('userType').value;
			
			if((userType == "fdy" && xysh == "通过") || (userType == "xy" && xxsh == "通过")){
				alert("您选择的学生已被上级审核通过，您无权审核");
			}else{
				modi('ntzy_pypx.do?method=yxtzbshone&doType=sh');
			}
		}else{
				alert('请选择要审核的数据行！');
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
	</script>
	</head>
	<body onload="choiceDisabled('xy','bmdm')">
		<html:form action="ntzy_pypx.do" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<logic:equal value="xx" name="userType">
				<input type="hidden" name="queryequals_xysh" value="通过"/>
			</logic:equal>
			
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_ssbm" value="${userDep }"/>
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>优秀团支部审核</a>
				</p>
			</div>

			<fieldset>
				<legend>
					查询条件
				</legend>
				
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','ntzy_pypx.do?method=yxtzbsh&shjg=tg&doType=sh&go=go','sh')">通过</a></li>
						<li><a href="#" class="btn_xg" onclick="batchData('primarykey_cbv','ntzy_pypx.do?method=yxtzbsh&shjg=btg&doType=sh&go=go','sh')">不通过</a></li>
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
		              		onclick="allNotEmpThenGo('/xgxt/ntzy_pypx.do?method=yxtzbsh&go=go')">
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
								<input type="hidden" name="queryequals_xn" value="${xn }"/>
								学年：</th>
								<td><select disabled="disabled">
									<option>${xn }</option>
								</select></td>
								<th>团支部名称：</th>
								<td><html:text property="querylike_tzbmc" styleId="tzbmc"></html:text></td>
								<th>申请人：</th>
								<td><html:text property="querylike_sqr" styleId="sqr"></html:text></td>
						</tr>
						<tr>
							<th>
							所在部门：</th>
							<td><html:select property="queryequals_ssbm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_pypx.do?method=yxtzbView&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" 
										id="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
									<logic:iterate id="v" name="s" offset="6" length="1">
										<input type="hidden" value="${v}"/>
									</logic:iterate>
									
									<logic:iterate id="v" name="s" offset="7" length="1">
										<input type="hidden" value="${v}"/>
									</logic:iterate>
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
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

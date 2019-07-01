<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
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
				alert("您选择的学生已被上级审核通过，您无权审核");
			}else{
				modi('ntzy_xyhd.do?method=xyhdshone&doType=shone');
			}
		}else{
				alert('请选择要审核的数据行！');
		}
	}
	</script>
	</head>
	<body onload="choiceDisabled('xy','ssbm');">
		<html:form action="/ntzy_xyhd.do?method=xyhdsh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','ntzy_xyhd.do?method=xyhdsh&shjg=tg&doType=sh&go=go','sh')">通过</a></li>
						<li><a href="#" class="btn_shbtg" onclick="batchData('primarykey_cbv','ntzy_xyhd.do?method=xyhdsh&shjg=btg&doType=sh&go=go','sh')">不通过</a></li>
						<li><a href="#" class="btn_sh" onclick="isSh();">单个审核</a></li>
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<th>申请单位</th>
							<td><html:text property="querylike_sqdw" styleId="sqdw"></html:text></td>
							<th>总负责人</th>
							<td><html:text property="querylike_zfzr" styleId="zfzr"></html:text></td>
							<th>参与人数</th>
							<td><html:text property="queryequals_cyrs" styleId="cyrs"></html:text></td>
						</tr>
				
						<tr>
							<th>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_ssbm" value="${userDep}" />
								</logic:equal>
								部门</th>
								
							<td><html:select property="queryequals_ssbm" style="width:150px"
									styleId="ssbm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="shzd" value="xysh"/>
								</logic:equal>
								<logic:equal value="xx" name="userType">
									<input type="hidden" name="queryequals_xysh" value="通过" />
									<input type="hidden" name="shzd" value="xxsh"/>
								</logic:equal>
							</td>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/ntzy_xyhd.do?method=xyhdsh&go=go')">
						查 询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重 置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
			
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
					<table width="99%" id="rsTable" class="dateline">
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
								ondblclick="modi('ntzy_xyhd.do?method=xyhdViewAndModi&doType=view')"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="6">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xyhdForm"></jsp:include>
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

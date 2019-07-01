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
		
		function print(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
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
				modi('ntzy_xyhd.do?method=xyhdViewAndModi&doType=modi');
			}
		}else{
				alert('请选择要修改的数据行！');
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
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_zj" onclick="location='ntzy_xyhd.do?method=xyhdsq';">增加</a></li>
					<li><a href="#" class="btn_xg" onclick="isSh();">修改</a></li>
					<li><a href="#" class="btn_sc" onclick="dataBatch('gdby_tygl.do?method=tycx&doType=del&go=go')">删除</a></li>
					<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
					<li><a href="#" class="btn_dc" onclick="wjcfDataExport('ntzy_xyhd.do?method=xyhdExp',600,400)">导出</a></li>
					<li><a href="#" class="btn_dy" onclick="print('ntzy_xyhd.do?method=xyhdPrint',600,400)">打印报表</a></li>								
				</ul>
			</div>
			</div>
			</logic:equal>
			<div class="searchtab">
				<table width="100%" border="0" class="">
					<tbody>
						<tr>
							<th>
								<logic:equal value="stu" name="userType">
									<input type="hidden" name="queryequals_sqr" value="${sessionScope.userName }"/>
								</logic:equal>
								申请单位</th>
							<td><html:text property="querylike_sqdw" styleId="sqdw"></html:text></td>
							<th>总负责人</th>
							<td><html:text property="querylike_zfzr" styleId="zfzr"></html:text></td>
							<th>参与人数</th>
							<td><html:text property="queryequals_cyrs" styleId="cyrs"></html:text></td>
						</tr>
						<tr>
							<th>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_ssbm" value="${userDep}"/>
								</logic:equal>
								部门</th>
							<td><html:select property="queryequals_ssbm" style="width:150px"
									styleId="ssbm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
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
						<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/ntzy_xyhd.do?method=xyhdcx&go=go')">
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

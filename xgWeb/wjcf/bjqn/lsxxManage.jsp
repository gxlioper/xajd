<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">		
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
	<body onload="choiceDisabled('xy','xy');choiceDisabled('stu','xh');">
		<html:form action="wjcf_bjqn" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="isFdy" value="${isFdy }" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
			</logic:equal>
			<logic:equal value="stu" name="userType">
				<input type="hidden" name="querylike_xh" value="${userName }"/>
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_sc" onclick="dataBatch('wjcf_bjqn.do?method=lsxxManage&doType=del&go=go')">删除</a></li>
							<li><a href="#" class="btn_dc" onclick="wjcfDataExport('wjcf_bjqn.do?method=dataExport',800,600)">导出</a></li>
						</ul>
					</div>
					</div>
				</logic:equal>
				
				<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th>年级</th>
							<td><html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select></td>
							<th>学号</th>
							<td><html:text property="querylike_xh" styleId="xh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							
						</tr>
						
						<tr>
							<th>学年</th>
							<td><html:select property="queryequals_xn" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select></td>
							<th>年度</th>
							<td>
								<html:select property="queryequals_nd">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="queryequals_xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
				
						<tr>
							<th><bean:message key="lable.xb" /></th>
							<td><html:select property="queryequals_xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select></td>
							<th>专业</th>
							<td><html:select property="queryequals_zydm" style="width:180px"
								styleId="zy" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
							<th>班级</th>
							<td><html:select property="queryequals_bjdm" style="width:180px"
								styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select></td>
						</tr>
					</tbody>
					<tfoot>
	        		<tr>
	          			<td colspan="6">
	            		<div class="btn">
	              		<input type="hidden" name="go" value="a" />
	              		<button type="button" class="btn_cx" id="search_go" 
	              		onclick="allNotEmpThenGo('/xgxt/wjcf_bjqn.do?method=lsxxManage&go=go')">
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
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="updateOrViewOne('wjcf_bjqn.do?method=lsxxUpdate&pkValue=','800','600');"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" id="pkV"
										 value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfBjqnForm"></jsp:include>
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

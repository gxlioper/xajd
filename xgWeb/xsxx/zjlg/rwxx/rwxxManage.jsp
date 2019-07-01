<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<%@ include file="/syscommon/pagehead_xg.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">	
	function delDwjl(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/zjlgXsxxRwxx.do?method=rwxxManage&doType=del";
				refreshForm(url);
			}
		}else{
			alert("请勾选要处理的数据");
			return false;
		}
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgXsxxRwxx" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/zjlgXsxxRwxx.do?method=rwxxUpdate&doType=add',800,600);">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="if(curr_row == null){alert('请选择要修改的数据！');return false;}showTopWin('/xgxt/zjlgXsxxRwxx.do?method=rwxxUpdate&doType=update&pk='+curr_row.getElementsByTagName('input')[0].value,800,600);">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="delDwjl()">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				<!-- 内容显示区开始 -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
					<!-- From内容 -->
<!--					<div style="float:left;">-->
<!--						<div class="listbox" style="width:155px;float:left">-->
<!--							<div class="menulist">-->
<!--							层start-->
<!--							<div class="menutitle">-->
<!--							     <h4 style="height:30px;line-height:30px;padding-left:40px;">部门列表</h4>-->
<!--							<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--							<ul>-->
<!--							  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--							</ul>-->
<!--							</div>-->
<!--							<script type="text/javascript">-->
<!--							-->
<!--							var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
<!--							//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
<!--	 						-->
<!--							</script>-->
<!--							</div>-->
<!--							层end-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--					<div style="float:right;width:630px;">-->
					<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<tr>
									<th>年级</th>
									<td><html:select property="nj" style="width:100px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select></td>
									<th>学号</th>
									<td><html:text property="xh" style="width:100px" maxlength="20"></html:text></td>
									<th>姓名</th>
									<td><html:text property="xm" style="width:100px" maxlength="20"></html:text>
									</td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td><html:select property="xydm" style="width:100px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
									<th>专业</th>
									<td><html:select property="zydm" style="width:100px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
									<th>班级</th>
									<td><html:select property="bjdm" style="width:100px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/zjlgXsxxRwxx.do?method=rwxxManage');">
										查询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重置
									 </button>
									</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>

					<div class="formbox">
						<h3 class="datetitle_01">
						    <span>
						    	查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						    </span>
						</h3>
						
<!--						<div style="overflow-x:auto;width:630px;">		-->
							<div class="con_overlfow">
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
<!--											<input type="checkbox"/>-->
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:empty name="rs">
									<%
									for(int i=0; i<11; i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>	
								 	</tr>
					
									<%}%>
					 			</logic:empty>
								<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);"
										ondblclick="showTopWin('/xgxt/zjlgXsxxRwxx.do?method=rwxxUpdate&doType=view&pk='+curr_row.getElementsByTagName('input')[0].value,800,600);"
										style="cursor:hand">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								<logic:lessEqual value="${pageSize}" name="rsNum">
									<%
									int rsNum = ((List)request.getAttribute("rs")).size();
									int pageSize = (Integer) request.getAttribute("pageSize");
									for(int i=0; i<(pageSize-rsNum); i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
								 	</tr>
									<%}%>
								</logic:lessEqual>
								</logic:notEmpty>
							</table>
							</div>
							<!--分页显示-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgXsxxForm"></jsp:include>
							<!--分页显示-->
<!--							<script type="text/javascript">-->
<!--								$('choose').className="hide";-->
<!--							</script>-->
							
						</div>
				</div>
			</div>
			</logic:empty>
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
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/zjlgXsxxRwxx.do?method=rwxxManage";-->
<!--</script>-->
	</body>
</html>

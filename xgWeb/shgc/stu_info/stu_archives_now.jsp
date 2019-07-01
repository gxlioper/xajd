<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
	//查询结果集
	  function searchRs(){
		allNotEmpThenGo('/xgxt/stu_archives_now.do')
	  }
	  
	  function commit(url){
		if(curr_row == null){
			alert("请选择您要操作的记录！");
			return false;
		}		
		var xh = curr_row.cells[1].innerText.trim();
				
		url += "&xh=";
		url += xh;
		showTopWin(url,550,700);			
	  }
	  
	  function checkSelect(){
	    	var RowsStr="!!";
	    	for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}	
			if (RowsStr=="!!"){
				alert("请选择要提交的记录！");
				return false;
			}
			return true;
	    }
	  
	  
	  function batch(url){
			var RowsStr="!!";
			var gdzldm = document.getElementById("select_gdzldm").value;	
			var ddqkdm = "";
			if(document.getElementById("ddqkdm")){
				ddqkdm =document.getElementById("ddqkdm").value;
			}
			if(gdzldm=="" || gdzldm==null)	{
				alert("请选择要提交的资料!");
				return false;
			}
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}	
			if (RowsStr=="!!"){
				alert("请选择要提交的记录！");
				return false;
			}
			
			if (!confirm("确定要批量设置所选记录？")){
				return false;
			}
			
			url += "&operPk=" + RowsStr;
			url += "&gdzldm=" + gdzldm;
			url += "&ddqkdm=" + ddqkdm;
			refreshForm(url);
			return true;
		}
	  
	  	function delZxsda(){
	  		var url = 'stu_archives_now.do?doType=del';
	  	
	  		var RowsStr="!!SplitOneSplit!!";  
	  		var checkbox = jQuery('input[name=pk]:checked');
	  		var pkValue;
	  		if (checkbox.length==0){  													
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			} else {
				refreshForm(url);
	  		}
	  	}
	  
	  
  	</script>

	</head>
	<body onload="check_user();">
		<html:form action="/stu_archives_history">
			<input type="hidden" value="stu_archives" id="realTable" />
			<input type="hidden" value="${userType}" id="userType" />
			<input type="hidden" value="view_stu_archives" id="tableName" />
			<input type="hidden" name="tableName" value="view_stu_archives" />
			<input type="hidden" name="realTable" value="stu_archives" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<!-- 高级查询 必须 -->
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep }" />
			<input type="hidden" id="path" name="searchModel.path"
				value="stu_archives_now.jsp" />


			<div id="gdzlDiv" style="display:none">
				<table width='350' class='formlist'>
					<thead>
						<tr>
							<th align='center' colspan="2">
								请选择提交的归档资料
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								归档资料
							</th>
							<td>
								<html:select property="gdzldm" style="width:150px"
									styleId="select_gdzldm">
									<html:option value=""></html:option>
									<html:options collection="zlList" property="gdzldm"
										labelProperty="gdzlmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class='btn'>
									<button class='button2'
										onclick="batch('business.do?method=datumCommit&doType=save&mk=dacx');return false;">
										确定
									</button>
									&nbsp;&nbsp;
									<button class='button2' onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			





			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 在校学生档案 - 在校学生档案查询</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/stu_archives_now.do?doType=add&type=add',800,600);return false;"
									class="btn_zj">增 加</a>
							</li>
							<li>
								<a href="#"
									onclick="familyUpdate('/xgxt/stu_archives_now.do?doType=update&xh=',800,600);return false;"
									class="btn_xg">修 改</a>
							</li>
							<li>
								<a href="#"
									onclick="delZxsda();return false;"
									class="btn_sc">删 除</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;"
									class="btn_dr">导入数据</a>
							</li>
							<li>
								<a href="#" class="btn_qx"
									onclick="choiceFields();return false;">导出设置</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc"
								onclick="configureExportData();return false;">导出数据</a>
						</li>
						<li>
							<a href="#"
								onclick="commit('stu_info_add.do?method=datumCommitSignle');return false;"
								class="btn_ccg">单个提交</a>
						</li>
						<li>
							<a href="#"
								onclick="if(checkSelect()){viewTempDiv('批量提交归档资料','gdzlDiv',300,200);};return false;"
								class="btn_ccg">批量提交</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
					<!-- new 版本 -->
					<logic:equal name="superSearch" value="yes">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
					</logic:equal>

					<!-- old 版本 -->
					<logic:equal name="superSearch" value="no">
						<!-- From内容 -->
						<!--				<div style="float:left;">-->
						<!--					<div class="listbox" style="width:155px;float:left">-->
						<!--						<div class="menulist">-->
						<!--						层start-->
						<!--						<div class="menutitle">-->
						<!--						    <h4 style="height:30px;line-height:30px;padding-left:40px;">部门列表</h4>-->
						<!--						<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
						<!--						<ul>-->
						<!--						  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
						<!--						</ul>-->
						<!--						</div>-->
						<!--						<script type="text/javascript">-->
						<!--						-->
						<!--						var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
						<!--						//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
						<!-- 						-->
						<!--						</script>-->
						<!--						</div>-->
						<!--						层end-->
						<!--						</div>-->
						<!--					</div>-->
						<!--				</div>	-->
						<!--				<div style="float:right;width:630px;">-->
						<!--查询条件-->
						<logic:notEqual value="student" name="userOnLine" scope="session">
							<div class="searchtab">
								<table width="100%" border="0">
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="btn">
													<input type="hidden" name="go" value="a" />
													<button class="btn_cx" id="search_go"
														onclick="allNotEmpThenGo('/xgxt/stu_archives_now.do')">
														查询
													</button>
													<button id="cz"
														onclick="czSearchCond('nj-xh-xm-xy-zy-bj-zddwmc-jyh');return false;">
														重置
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
												<html:select property="nj" styleId="nj"
													onchange="initZyList();initBj();" style="width:100px">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
													<bean:write name="njList" />
												</html:select>
											</td>
											<th>
												学号
											</th>
											<td>
												<html:text property="xh" style="width:100px" maxlength="20"></html:text>
											</td>
											<th>
												姓名
											</th>
											<td>
												<html:text property="xm" style="width:100px" maxlength="20"></html:text>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												<html:select property="xydm" style="width:100px"
													styleId="xy" onchange="initZyList();initBj();">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
											<th>
												专业
											</th>
											<td>
												<html:select property="zydm" style="width:100px"
													styleId="zy" onchange="initBj();">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
											<th>
												班级
											</th>
											<td>
												<html:select property="bjdm" style="width:100px"
													styleId="bj">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
										<!--福建工程-->
										<%@ include file="/xsxx/fjgcxy/zxxsdacx_fjgcxy.jsp"%>
									</tbody>
								</table>
							</div>
						</logic:notEqual>
					</logic:equal>

					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="blue">提示：双击可以查看详细信息;单击表头可以排序;</font>
							</span>
						</h3>


						<div class="con_overlfow">
							<!--  								tablenowrap-->
							<!--						 <div style="overflow-x:auto;width:630px;">-->
							<table summary="" class="dateline" width="100%" id="rsTable">
								<thead>
									<tr>
										<td>
											<input type="checkbox" disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="${tit.en}" onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="rs">
										<%
										for (int i = 0; i < 11; i++) {
										%>
										<tr>
												<td>
													&nbsp;
												</td>
											<logic:iterate id="tit" name="topTr" offset="1">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>

										<%
										}
										%>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
												ondblclick="familyUpdate('/xgxt/stu_archives_now.do?doType=update&view=true&xh=',800,600)">
												<td>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="checkbox" name="pk" value="${v }" />
													</logic:iterate>
												</td>
												<td>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="2">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										<logic:lessEqual value="${pageSize}" name="rsNum">
											<%
													int rsNum = ((List) request.getAttribute("rs")).size();
													int pageSize = (Integer) request
															.getAttribute("pageSize");
													for (int i = 0; i < (pageSize - rsNum); i++) {
											%>
											<tr>
												<td>
													<input type="checkbox" name="pkValue" disabled="true" />
												</td>
												<logic:iterate id="tit" name="topTr" offset="1">
													<td>
														&nbsp;
													</td>
												</logic:iterate>
											</tr>
											<%
											}
											%>
										</logic:lessEqual>
									</logic:notEmpty>
								</tbody>
							</table>
						</div>
						<!--分页显示-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>

					</div>
				</div>
			</div>
			</div>
			<logic:present name="have">
				<script>								  						  
					showGdzlInfo(500,400);
				</script>
			</logic:present>

			<logic:notEmpty name="result" scope="request">
				<logic:equal value="true" name="result" scope="request">
					<script>
					alert("操作成功！");
					Close();					
					document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result" scope="request">
					<script>
					alert("操作失败！");
				</script>
				</logic:equal>
			</logic:notEmpty>
			</div>
		</html:form>
	</body>
	<script type="text/javascript" src="js/xsxx/bmTree.js"></script>
	<script>
	url = "/xgxt/stu_archives_now.do";
</script>
</html>

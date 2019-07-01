<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<%@ include file="/syscommon/pagehead_xg.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script language="javascript">
	function searchRs(){
		allNotEmpThenGo('/xgxt/archives_apply_query.do');
	}
	</script>
</head>	
<body onload="check_user();">
	<html:form action="/stu_archives_apply" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType}" />
		<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
		<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
		<input type="hidden" id="act" name="act" value="${act}"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		
		  <!-- 高级查询 必须 -->
	     <input type="hidden" name="userName" id="userName" value="${userName }"/>
	     <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		<input type="hidden" id="path" name="searchModel.path" value="archives_apply_query.jsp"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${tips}</a>
			</p>
		</div>
			<div class="toolbox">
			  <logic:notEqual value="student" name="userOnLine">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:empty name="sh">	
						<li> <a href="#" onclick="viewAdd('/xgxt/stu_cgcj.do?doType=add','add')" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="if(curr_row != null){viewAdd('/xgxt/stu_cgcj.do?','modi');return true;}else{alert('请选择要修改的行！');return false;}" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="delMore('/xgxt/archives_apply_query.do?doType=del&pkValue=');" class="btn_sc">删 除</a> </li>
					</logic:empty>
					<logic:notEmpty name="sh">
						<li> <a href="#" onclick="showUpdateWindow('primarykey_cbv','archives_apply_query.do?doType=viewAuditing',800,550)" class="btn_sh">单个审核</a> </li>
						<li> <a href="#" onclick="batchData('primarykey_cbv','archives_apply_query.do?doType=plsh&shjg=通过','sh')" class="btn_shtg">批量通过</a> </li>
						<li> <a href="#" onclick="batchData('primarykey_cbv','archives_apply_query.do?doType=plsh&shjg=不通过','sh')" class="btn_shbtg">批量不通过</a> </li>
					</logic:notEmpty>
			    </ul>
			  </div>
			  </logic:notEqual>

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
<!--				</div>-->
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
											onclick="allNotEmpThenGo('/xgxt/archives_apply_query.do')">
											查询
									  </button>
						            </div>
						          </td>
						        </tr>
						      </tfoot>
							  <tbody>
						      	<tr>
						      		<th>年度</th>
									<td>
										<logic:empty name="sh">
											<html:select property="nd"  styleId="nd">
												<html:options collection="ndList" property="nd"
													labelProperty="nd" />
											</html:select>
										</logic:empty>
										<logic:notEmpty name="sh">
											<logic:equal value="10856" name="xxdm">
												<html:select property="nd" styleId="nd">
													<html:options collection="ndList" property="nd"
														labelProperty="nd" />
												</html:select>
											</logic:equal>
											<logic:notEqual value="10856" name="xxdm">
												<html:select property="nd" 
													 styleId="nd">
													<html:options collection="ndList" property="nd"
														labelProperty="nd" />
												</html:select>
											</logic:notEqual>
										</logic:notEmpty>
									</td>					
									<th>学号</th>
									<td>
										<html:text property="xh" maxlength="20" style="width:100px"/>
									</td>
									<th>姓名</th>
									<td>
										<html:text property="xm" style="width:100px"></html:text>
									</td>
								</tr>
								<tr>
						      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td>
										<logic:equal name="userType" value="xy" scope="session">
											<html:select property="xydm"style="width:100px"
												onchange="initZyList();initBj();"
												disabled="true" styleId="xy">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy" scope="session">
											<html:select property="xydm"  style="width:100px"
												onchange="initZyList();initBj();" styleId="xy">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>专业</th>
									<td>
										<html:select property="zydm" styleId="zy"
											onchange="initBj();" style="width:100px">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>班级</th>
									<td>
										<html:select property="bjdm"  styleId="bj" style="width:100px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							  </tbody>
							</table>
						</div>
						</logic:notEqual>
					</logic:equal>
						<div class="formbox">
						    <h3 class="datetitle_01">
						    <span>
						    	查询结果&nbsp;&nbsp;
						    	<font color="blue">双击一行可以显示详细信息；单击表头可以排序；</font>
						    </span>
						    </h3>
							   
						  
						  <div class="con_overlfow">
<!--						<div style="overflow-x:auto;width:630px;">-->
							
						  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
						    <thead>
						      <tr>
								<logic:empty name="sh">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="${tit.en}"
											onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</logic:empty>
								<logic:notEmpty name="sh">
									<td onclick="tableSort(this)">
										<input type="checkbox" disabled="true"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="10">
										<td id="${tit.en}"
											onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</logic:notEmpty>
						      </tr>
						    </thead>
						    <tbody>
								<logic:empty name="rs">
									<%
									for(int i=0; i<11; i++){
									%>
									<tr>	
										<logic:empty name="sh">
											<logic:iterate id="tit" name="topTr" offset="0">
											<td>
												&nbsp;
											</td>
											</logic:iterate>
										</logic:empty>	
										<logic:notEmpty name="sh">
												<td>
													<input type="checkbox" disabled="true"/>
												</td>
											<logic:iterate id="tit" name="topTr" offset="1" length="10">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</logic:notEmpty>		
								 	</tr>
					
									<%}%>
					 			</logic:empty>

								<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<logic:notEmpty name="sh">
										<tr onclick="rowOnClick(this)"
											style="cursor:hand;background-color:
												    <logic:iterate id="v" name="s" offset="11" length="1">
												    ${v}
												    </logic:iterate>
												     "
											ondblclick="showTopWin('archives_apply_query.do?doType=viewAuditing&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>',800,550)">
											
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="left">
													<input type="checkbox" value="${v}" name="primarykey_cbv"/>
												</td>
											</logic:iterate>
											
											<logic:iterate id="v" name="s" offset="1" length="10">
												<td align="left" style="cursor:hand">
													<input type="hidden" value="${v}" />
													${v}
												</td>
											</logic:iterate>
										</tr>
									</logic:notEmpty>
									<logic:empty name="sh">
										<tr onclick="rowMoreClick(this,'',event)" style="cursor:hand"
											ondblclick="viewAdd('','modi')">
											<logic:iterate id="v" name="s" offset="0">
												<td align="left" style="cursor:hand">
													<input type="hidden" value="${v}" />
													${v}
												</td>
											</logic:iterate>
										</tr>
									</logic:empty>
								</logic:iterate>
								<logic:lessEqual value="${pageSize}" name="rsNum">
									<%
									int rsNum = ((List)request.getAttribute("rs")).size();
									int pageSize = (Integer) request.getAttribute("pageSize");
									for(int i=0; i<(pageSize-rsNum); i++){
									%>
									<tr>
										<logic:empty name="sh">
											<logic:iterate id="tit" name="topTr" offset="0">
											<td>
												&nbsp;
											</td>
											</logic:iterate>
										</logic:empty>	
										<logic:notEmpty name="sh">
											<td>
												<input type="checkbox" disabled="true"/>
											</td>
											<logic:iterate id="tit" name="topTr" offset="1" length="10">
											<td>
												&nbsp;
											</td>
											</logic:iterate>
										</logic:notEmpty>											
								 	</tr>
									<%}%>
								</logic:lessEqual>
								</logic:notEmpty>
						    </tbody>
						</table>
						</div>
						<!--分页显示-->
						   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
						<!--分页显示-->
						</div>
					</div>
			</div>
		</div>	
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("删除成功");
						document.forms[0].search_go.click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("删除失败");							
					</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:present name="message">
				<script>
					alert("${message}");		
					document.forms[0].search_go.click();					
				</script>
			</logic:present>
			
	</html:form>
</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/archives_apply_query.do";-->
<!--</script>-->
</html>

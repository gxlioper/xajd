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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
</head>
	<body onload="check_user();">
			<html:form action="/stu_status_different" method="post">
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${tips}</a>
					</p>
				</div>
				<div class="toolbox">
				  <!--读写权-->
				  <logic:equal value="yes" name="writeAble">
				  <logic:empty name="sh">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="viewAdd('/xgxt/stu_cgcj.do?doType=add','add');" class="btn_zj">增 加</a> </li>
							<li> <a href="#" onclick="if(curr_row != null){viewAdd('/xgxt/stu_cgcj.do?','modi');return true;}else{alert('请选择要修改的行！');return false;};" class="btn_xg">修 改</a> </li>
							<li> <a href="#" onclick="if(curr_row != null){if(confirm('您确定删除该条信息吗？'))viewAdd('/xgxt/stu_cgcj.do','del');return true;}else{alert('请选择要删除的行！');return false;};" class="btn_sc">删 除</a> </li>
					  <logic:equal value="10822" name="xxdm">
					  <logic:equal value="stu_txsqb" name="realTable">
						<!---广东白云学院-->	
						<li> <a href="#" onclick="javascript:if(curr_row==null) alert('请选中要操作的行！'); else window.open('leaveSchool.do?method=printLeaveSchoolReportOfByxy&&xh='+curr_row.cells[1].innerText);" class="btn_dy">报表打印</a> </li>
					  </logic:equal>
					  </logic:equal>
					  </ul>
				  </div>
				  </logic:empty>						
				  </logic:equal>
				 </div>			
				<!-- 内容显示区开始 -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>	 
				  	<logic:notEqual value="student" name="user">				  
					<!-- From内容 -->
<!--					<div style="float:left;">-->
<!--						<div class="listbox">-->
<!--							<div class="menulist">-->
<!--							层start-->
<!--							<div class="menutitle">-->
<!--							    <h3>部门列表</h3>-->
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
						<table width="100%" border="0">
					      <tfoot>
					        <tr>						  
					          <td colspan="8">
					            <div class="btn">
								  	<input type="hidden" name="go" value="a" />
									<button id="search_go"
										onclick="allNotEmpThenGo('/xgxt/abroad_query.do')">
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
									<html:select property="nd" 
										disabled="false"
										onchange="refreshForm('/xgxt/abroad_query.do')" 
										styleId="nd"
										style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="sh">
									<html:select property="nd"  disabled="true"
										styleId="nd" style="width:100px">
										<html:option value="">
											<bean:write name="nd" />
										</html:option>
										<input type="hidden" name="nd" id="nd"
											value="<bean:write name="nd" />" />
									</html:select>
								</logic:notEmpty>
							</td>
							<logic:empty name="sh">
							<th>年级</th>
							<td>
								<html:select property="nj" style="width:100px"
									onchange="refreshForm('/xgxt/abroad_query.do')">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							</logic:empty>
	
							<logic:notEmpty name="sh">
							<th>年级</th>
							<td colspan="5">
								<html:select property="nj" style="width:100px"
									onchange="refreshForm('/xgxt/abroad_query.do')" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							</logic:notEmpty>
	
							<logic:empty name="sh">
							<th>学号</th>
							<td>
								<html:text property="xh" style="width:100px"/>						
							</td>
							<th>姓名</th>
							<td>
								<html:text property="xm" style="width:100px"/>
							</td>
							</logic:empty>
						  </tr>
						  <tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<html:select property="xydm" style="width:100px"
									onchange="refreshForm('/xgxt/abroad_query.do')" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>专业</th>
							<td>
								<html:select property="zydm" style="width:100px" styleId="zy"
									onchange="refreshForm('/xgxt/abroad_query.do')">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td>
								<html:select property="bjdm" style="width:100px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						  </tr>
						  </tbody>
						</table>
					</div>
				</logic:notEqual>

				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
				    </span>
				    </h3>			 
				
				<div class="con_overlfow">
<!--				<div style="overflow-x:auto;width:630px;">-->
				<table summary="" class="dateline" width="100%" id="rsTable">
				<thead>
			      <tr>					
			        <logic:empty name="sh">
						<logic:iterate id="tit" name="topTr" offset="1">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</logic:empty>
					<logic:notEmpty name="sh">
						<logic:iterate id="tit" name="topTr" offset="1" length="10">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
					</logic:iterate>
					</logic:notEmpty>
			      </tr>
			    </thead>

				<tbody>		
				<logic:notEmpty name="rs">
			      <logic:iterate name="rs" id="s">
					<logic:notEmpty name="sh">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="11" length="1">
						    <bean:write name="v"/>
						    </logic:iterate>
						     "
							ondblclick="viewAdd('','modi')">
							<logic:iterate id="v" name="s" offset="1" length="10">
								<td style="cursor:hand">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:notEmpty>
					<logic:empty name="sh">
						<tr onclick="rowOnClick(this)" style="cursor:hand"
							ondblclick="viewAdd('','modi')">
							<logic:iterate id="v" name="s" offset="1">
								<td style="cursor:hand">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:empty>
				</logic:iterate>
				</logic:notEmpty>
				<logic:lessEqual value="${pageSize}" name="rsNum">
					<%
					int rsNum = ((List)request.getAttribute("rs")).size();
					int pageSize = (Integer) request.getAttribute("pageSize");
					for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<logic:empty name="sh">
						<logic:iterate id="tit" name="topTr" offset="1">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
						</logic:empty>

						<logic:notEmpty name="sh">
							<logic:iterate id="tit" name="topTr" offset="1" length="10">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
						</logic:notEmpty>
				 	</tr>
					<%}%>
				</logic:lessEqual>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
			 	<!--分页显示-->
				  <script type="text/javascript">
					  $('choose').className="hide";
				  </script>
				</div>
		</div>
	</div>
	</html:form>
	</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/abroad_query.do";-->
<!--</script>-->
</html>

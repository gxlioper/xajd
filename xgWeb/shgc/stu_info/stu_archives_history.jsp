<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<%@ include file="/syscommon/pagehead_xg.ini"%>	
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript">
		function checkTimes(){
			var xxdm=$("xxdm").value;
			if(xxdm=="11355"){
				var blog=checkSearchTj('kssj','jssj');
				if(blog){
					allNotEmpThenGo('/xgxt/stu_archives_history.do');
				}
			}else{
				allNotEmpThenGo('/xgxt/stu_archives_history.do');
			}
		}	
		
	//查询结果集
	  function searchRs(){
	   checkTimes();
	  }
    </script>
</head>
<body onload="check_user();">
	<html:form action="/stu_archives_history">
		<input type="hidden" value="stu_history_lab" id="realTable" />
		<input type="hidden" value="${userType}" id="userType" name="userType" />
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>

		<!-- 高级查询 必须 -->
		   <input type="hidden" name="userName" id="userName" value="${userName }"/>
		   <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		   <input type="hidden" id="path" name="searchModel.path" value="stu_archives_history.jsp"/>
		   
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 学生档案 - 历届学生档案</a>
			</p>
		</div>
		<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="showTopWin('/xgxt/stu_archives_history.do?act=ljs_archive&doType=add&type=add',800,600);return false;" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="familyUpdate('/xgxt/stu_archives_history.do?act=ljs_archive&doType=update&xh=',800,600);return false;" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="delMore('/xgxt/stu_archives_history.do?doType=del&pkValue=');return false;" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a> </li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
					</logic:equal>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						<%-- <li> <a href="#" onclick="showExportDIV('/xgxt/expData.do')" class="btn_dc">导出数据</a> </li>					--%>
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
											onclick="checkTimes()">
											查询
									  </button>
									  <button id="cz"
										onclick="czSearchCond('nj-xh-xm-sfzh-xy-zy-bj-dwxz-bynf-jyh-zddwdz-zddwc-zdyy-kssj-jssj');return false;">
										重置
								    	</button>	
						            </div>
						          </td>
						        </tr>
						      </tfoot>
							  <tbody>
						      	<tr>
						      		<th>年级</th>
									<td>
										<html:select property="nj" styleId="nj"
											onchange="initZyList();initBj();" style="width:100px">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
											<bean:write name="njList" />
										</html:select>
									</td>
									<th>学号</th>
									<td>
										<html:text property="xh" style="width:100px"></html:text>
									</td>
									<th>姓名</th>
									<td>
										<html:text property="xm" style="width:100px"></html:text>
									</td>
								</tr>
								<tr>
						      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td>
										<html:select property="xydm" style="width:100px" styleId="xy"
											onchange="initZyList();initBj();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>专业</th>
									<td>
										<html:select property="zydm" style="width:100px" styleId="zy"
											onchange="initBj();">
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
								</tr>
								<tr>
						      		<th>身份证号</th>
									<td>
										<html:text property="sfzh" style="width:100px"></html:text>
									</td>
									<!--中国美术学院-->
									<logic:equal name="xxdm" value="10355">	
									<th>单位性质</th>
									<td>
										<html:text property="dwxz" style="width:100px"></html:text>
									</td>													
									<th>毕业时间</th>
									<td>
										<html:text property="bynf" style="width:100px"></html:text>
									</td>
									</logic:equal>
									<!--end中国美术学院-->
			
									<!--南宁职业技术学院-->
									<logic:equal name="xxdm" value="11355">
									<th>机要号</th>
									<td>
										<html:text property="jyh" style="width:100px"/>
									</td>
									</logic:equal>
									<!--end南宁职业技术学院-->
								</tr>
								<!--南宁职业技术学院-->
								<logic:equal name="xxdm" value="11355">
									<tr>
										<th>转档单位地址</th>
										<td>
											<html:text property="zddwdz" style="width:100px"/>
										</td>
										<th>转档单位名称</th>
										<td>
											<html:text property="zddwmc" style="width:100px"/>
										</td>
										<th>转档原因</th>
										<td>
											<html:text property="zdyy" style="width:100px"/>
										</td>
									</tr>
									<tr>
										<th>转移时间</th>
										<td colspan="5">
											<html:text property="kssj" styleId="kssj"  style="width:40px" 
												onclick="return showCalendar('kssj','y-mm-dd');" 
												onblur="dateFormatChg(this)" readonly="true" />
											——
											<html:text property="jssj" styleId="jssj"   style="width:40px"
												onclick="return showCalendar('jssj','y-mm-dd');" 
												onblur="dateFormatChg(this)" readonly="true" />
										</td>
									</tr>
								</logic:equal>
								<!--end南宁职业技术学院-->
							  </tbody>
							</table>
						</div>
						</logic:notEqual>	
						</logic:equal>	

						<div class="formbox">
						    <h3 class="datetitle_01">
						    <span>
						    	查询结果&nbsp;&nbsp;
						    	<logic:empty name="rs">
									<font color="red">未找到任何记录！</font> 
						 		 </logic:empty>
								<logic:notEmpty name="rs">
									
								</logic:notEmpty>
						    </span>
						    </h3>
							   
						 
<%--						  <div class="con_overlfow">--%>
<!--						  <div style="overflow-x:auto;width:630px;">-->
						  <table summary="" class="dateline" width="100%" id="rsTable">
						    <thead>
						      <tr>
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
									for(int i=0; i<11; i++){
									%>
									<tr>										
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
								 	</tr>
					
									<%}%>
					 			</logic:empty>
								<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="familyUpdate('/xgxt/stu_archives_history.do?act=ljs_archive&view=true&doType=update&xh=',800,600)">
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
										int rsNum = ((List)request.getAttribute("rs")).size();
										int pageSize = (Integer) request.getAttribute("pageSize");
										for(int i=0; i<(pageSize-rsNum); i++){
										%>
										<tr>
											<logic:iterate id="tit" name="topTr" offset="1">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
											
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
							<script type="text/javascript">
								$('choose').className="hide";
							</script>						
						
						</div>
<%--					</div>--%>
			</div>
		</div>	
		
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
	</html:form>
</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/stu_archives_history.do";-->
<!--</script>-->
</html>

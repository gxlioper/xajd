<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%--<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
	<%@ include file="/syscommon/head.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
			
	<script type='text/javascript' src='dwr/engine.js'></script> 
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/exportData.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
	<script type="text/javascript">
		function searchRs(){
			allNotEmpThenGo('moralCard.do?method=showStudents&doType=query');
		}
		
		function pdkld(){
			var checkBoxArr = jQuery("input[name='primarykey_cbv']");
			var temp = '';
			var flg = false;
			var pkValue = '';
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked){
					if (flg){
						temp += checkBoxArr[i].value;
						temp +=','
					} else {
						flg = true;
						pkValue = checkBoxArr[i].value;
					}
				}
			}
			
			if ('' == pkValue){
				alert('请勾选您要打印的记录!');
				return false;
			} else {
				var xhArr = temp.substring(0,temp.length-1);
				var shr = prompt("请输入审核人:","");
				shr = null !=shr ? shr : "";
				
				document.forms[0].action = 'moralCard.do?method=viewMoralCard&flg=ld&shr='+shr+'&xhArr='+xhArr+'&pkValue='+pkValue;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
		}
		//修改
		function showPdkEdit(){
			var len=jQuery("[name=primarykey_cbv]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_cbv]:checked").eq(0).val();
				var url="moralCard.do?method=dyddPage";
				url+="&pkValue="+pkValue;
				showDialog("德育等第维护",850,550,url);
			}else{	
				alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}
		//查看
		function showPdkView(){
			var len=jQuery("[name=primarykey_cbv]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_cbv]:checked").eq(0).val();
				var url="moralCard.do?method=viewMoralCard";
				url+="&pkValue="+pkValue;
				showDialog("查看品德卡",850,550,url);
			}else{	
				alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}
	</script>
</head>
<body>
	<html:form action="/moralCard" method="post">
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" id="realTable" name="realTable" value="xg_xsxx_dyddb"/>
		<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		<input type="hidden" id="path" name="searchModel.path" value="${path }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>

		<div class="toolbox">	
			  <!--按钮	-->
			  <div class="buttonbox">
			    <ul>
				<logic:equal value="yes" name="writeAble">
					<li> 
						<%--<a href="#" class="btn_sr" onclick="showUpdateWindow('primarykey_cbv','moralCard.do?method=dyddPage',600,400,false,'请先选择一个学生')">德育等第</a>--%>
						<a href="#" class="btn_sr" onclick="showPdkEdit()">德育等第</a>
					</li>
					<!--
					<li> 
						<a href="#" class="btn_sr" onclick="showUpdateWindow('primarykey_cbv','moralCard.do?method=jcjlPage',600,400,false,'请先选择一个学生')">奖惩记录</a>
					</li>
					-->
					<li> 
						<%--<a href="#" class="btn_ck" onclick="showUpdateWindow('primarykey_cbv','moralCard.do?method=viewMoralCard',800,600,true,'请先选择一个学生')">查看品德卡</a>--%>
						<a href="#" class="btn_ck" onclick="showPdkView()">查看品德卡</a>
					</li>
					
					<li> 
						<a href="#" class="btn_dr" onclick="impAndChkData()">德育等第导入</a>
					</li>
					</logic:equal>
					<li> 
						<a href="#" class="btn_dy" onclick="pdkld();">品德卡连打</a>
					</li>
				</ul>
			  </div>
			  <%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
		<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<td style="width:30px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="0">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum")
													- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxCommForm"></jsp:include>
				<!--分页显示-->
			</div>
	</html:form>

</body>
</html>

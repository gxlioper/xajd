<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script language="javascript">	
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
	      }
	    }
	    
	    function show(url){    	
	    	var xh = curr_row.cells[0].innerText;
	    	if($('xxdm').value=='11122'){
				url = 'stu_info_add.do?method=datumCommitSignle&doType=view';
			}
	    	url += "&xh=";
	    	url += xh;
	    	showTopWin(url);
	    }
	    
	    function print(){
			var xy = document.getElementById('xy').value;
			var nj = document.getElementById('nj').value;
			if(''==xy || null == xy || ''==nj || null == nj){
				alert("请选择<bean:message key="lable.xsgzyxpzxy" /> 和 年级");
				return false;
			}
			var url = '/xgxt/business.do?method=getGdzl&xy='+xy+'&nj='+nj;
			window.open(url);
		}
		
		//查询结果集
		function searchRs(){
		 	allNotEmpThenGo('business.do?method=datumQuerry')
		}
	</script>
</head>
<body onload="check_user();">
	<html:form action="/business.do" method="post">
		<input type="hidden" value="${userType}" id="userType" />
		<input type="hidden" name="xxdm" value="${xxdm}" id="xxdm"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		  <!-- 高级查询 必须 -->
	     <input type="hidden" name="userName" id="userName" value="${userName }"/>
	     <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
	  	<input type="hidden" id="path" name="searchModel.path" value="datumQuerry.jsp"/>
		
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 学生档案 - 提交归档资料查询</a>
			</p>
		</div>
		
		<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<li> <a href="#" onclick="print()" class="btn_dc">导出数据</a> </li>
			    </ul>
			  </div>
			  <!-- new 版本 -->
     <logic:equal name="superSearch" value="yes">
      <%@ include file="/comm/search/superSearchArea.jsp"%>
     </logic:equal>
     
     <!-- old 版本 -->
     <logic:equal name="superSearch" value="no">
			  <!--查询条件-->
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('business.do?method=datumQuerry')">
								查询
						  </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>年级</th>
						<td>
							<html:select property="nj" style="width:80px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<html:text property="xh" style="width:150px" maxlength="20"/>	
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" style="width:150px" maxlength="20"/>
						</td>
					</tr>
					<tr>
			      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" style="width:150px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<%--西北二民院--%>
					<logic:equal value="11407" name="xxdm">							
					<tr>
			      		<th>档案</th>
						<td>
							<html:select property="ssda">
								<html:option value="">全部档案</html:option>
								<html:option value="rxddqkdm">入学档案</html:option>
								<html:option value="zxddqkdm">在校档案</html:option>
								<html:option value="byddqkdm">毕业档案</html:option>
							</html:select>
						</td>
						<th>到档情况</th>
						<td>
							<html:select property="ddqkdm">
								<html:option value=""></html:option>
								<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>	
					<%--end西北二民院--%>
				  </tbody>
				</table>
			</div>
			</logic:equal>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
					<logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息;单击表头可以排序;</font>
					</logic:notEmpty>
			    </span>
			    </h3>
				   
			  <table summary="" class="dateline" width="100%" id="rsTable">
			    <thead>
			      <tr>
					<logic:iterate id="tit" name="topTr" offset="0">
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
							<logic:iterate id="tit" name="topTr" offset="0">
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
								ondblclick="show('stu_info_add.do?method=showDetailsInfoOfDatum')">
								<logic:iterate id="v" name="s" offset="0">
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
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			<!--分页显示-->			
	</html:form>
	
</body>
</html>


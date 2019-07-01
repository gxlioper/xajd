<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript">	
		function delHkzt(){
			var url = "studentHkzt.do?method=delHkztInfo&xh=";
			if(curr_row==null){
				alert("请选择要删除的记录！");
				return false;
			}else{
			if(confirm('您确定要删除所选的记录!')){
				url += curr_row.cells[0].innerText;
				refreshForm(url)
			}
			}
		}
		
		function expDate_hkzt(){
			document.forms[0].action = "/xgxt/studentHkzt.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//查询结果集
		function searchRs(){
		 allNotEmpThenGo('/xgxt/studentHkzt.do?method=hkztQuery')
		}
	</script>
</head>
<body onload="check_user();">
		<html:form action="/studentHkzt.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="tableName" value="view_hkzt" />
			<input type="hidden" name="realTable" value="hkztb" />
			
			<!-- 高级查询 必须 -->
		    <input type="hidden" name="userName" id="userName" value="${userName }"/>
		    <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		  	<input type="hidden" id="path" name="searchModel.path" value="stu_hkzt_query.jsp"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 档案管理 - 户籍管理 - 户籍信息查询</a>
				</p>
			</div>
			<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="showTopWin('studentHkzt.do?method=addHkztInfo',800,600);return false;" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="if(curr_row==null){alert('请选择您要修改的记录！')}else{showTopWin('studentHkzt.do?method=modHkztInfo&xh=' + curr_row.cells[0].innerText,800,600)};return false;" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="delHkzt();return false;" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a> </li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
					</logic:equal>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						<%--	<li> <a href="#" onclick="expDate_hkzt();return false;" class="btn_dc">导出数据</a> </li>--%>
			    </ul>
			  </div>
			  <!-- new 版本 -->
		     <logic:equal name="superSearch" value="yes">
		      <%@ include file="/comm/search/superSearchArea.jsp"%>
		     </logic:equal>
		     
		     <!-- old 版本 -->
		    <logic:equal name="superSearch" value="no">
			  <!--查询条件-->
			  <logic:notEqual value="student" name="userOnLine" scope="session">
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button type="button" class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/studentHkzt.do?method=hkztQuery')">
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
							<html:select property="nj" styleId="nj" style="width:80px"
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
								<bean:write name="njList" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<html:text property="xh" style="width:150px"></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" style="width:150px"></html:text>
						</td>
					</tr>
					<tr>
			      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBj();">
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
					<tr>
			      		<th>户口状态</th>
						<td>
							<html:select property="hkztdm" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="hkztList" property="hkztdm" labelProperty="hkztmc"/>
							</html:select>
						</td>
						<th>是否缴费</th>
						<td>
							<html:select property="sfjf" style="width:80px">
								<html:option value=""></html:option>
								<html:option value="已缴">已缴</html:option>
								<html:option value="未缴">未缴</html:option>											
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</tbody>
				  </table>
				</div>
			  </logic:notEqual>
			  </logic:equal>
			</div>
			<div class="formbox">
		    <h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息；点击表头可以排序！</font> 
		 		</logic:notEmpty>
		    </span>
		    </h3>
			   
		  <logic:notEmpty name="rs">
		  <div class="con_overlfow"> 
		  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
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
				<logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" 
						ondblclick="if(curr_row==null){alert('请选择您要修改的记录！')}else{showTopWin('studentHkzt.do?method=modHkztInfo&doType=view&xh=' + curr_row.cells[0].innerText,800,600)}">									
						<logic:iterate id="v" name="s" offset="0">									
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
		    </tbody>
		</table>
		<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=studentHkztForm"></jsp:include>
		<!--分页显示-->
		</div>
		</logic:notEmpty>
		</div>
		
		<logic:present name="result">
		<logic:equal value="true" name="result">
		<script>
			alert("操作成功！");
			document.getElementById("search_go").click();
		</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			alert("操作失败！");
		</logic:equal>
		</logic:present>
	</html:form>
</body>
</html>

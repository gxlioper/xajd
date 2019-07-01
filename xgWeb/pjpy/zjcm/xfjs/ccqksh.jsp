<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">	
	function delXsccqk(){
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("checkVal").length; i++){
	    	if(document.getElementsByName("checkVal")[i].checked){
	    		RowsStr+=document.getElementsByName("checkVal")[i].value+"!!";
	    	}
		}
		document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("请选择要删除的记录！");
			return false;
		}
		
		if (!confirm("确定要删除所选记录？")){
			return false;
		}
		document.forms[0].go.value = "go";
		refreshForm("/xgxt/pjpyxfjs.do?method=xsccqkSc");
	}
	
	function audiXsccqk(shjg){
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("checkVal").length; i++){
	    	if(document.getElementsByName("checkVal")[i].checked){
	    		RowsStr+=document.getElementsByName("checkVal")[i].value+"!!";
	    	}
		}
		document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("请选择要操作的记录！");
			return false;
		}
		
		if (!confirm("确定要操作所选记录？")){
			return false;
		}
		document.forms[0].go.value = "go";
		refreshForm("/xgxt/pjpyxfjs.do?method=ccqkAudi&shjg="+shjg);
	}
	
	function modiXsccqk(){
		var url = "/xgxt/pjpyxfjs.do?method=xsccqkXg&pk=";
		var pk="";	
			if(curr_row == null ){
					alert("请选择您要修改的记录行！\n单击一行即可!");
					return false;
				} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			
			
			showTopWin(url,800,600);	
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">		
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
	
	
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn}"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq}"/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" name="delPk" id="delPk" value=""/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			
			<logic:notEmpty name="msg">
				<br/><br/>
				<div align="center"><FONT color="red"><bean:write name="msg"/></FONT></div>
			</logic:notEmpty>

			<logic:empty name="msg">
			
			
			
			<div class="toolbox">
			  <!-- 按钮 -->
			  <logic:equal value="yes" name="writeAble">
			  <div class="buttonbox">
			    <ul>
			      <li> <a href="#" onclick="audiXsccqk('通过')" class="btn_shtg"> 通过</a> </li>
			      <li> <a href="#" onclick="audiXsccqk('待批复')" class="btn_sh"> 待批复</a> </li>
			    </ul>
			  </div>
			  </logic:equal>
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		            	<input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/pjpyxfjs.do?method=ccqksh')">
		              	查 询
		              </button>
		              <button type="button" class="btn_cz" id="btn_cz" 
		              	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			<tbody>
		      	<tr>
		      		<th>年级</th>
		      		<td>
		      			<html:select property="nj" style="width:160px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
		      		</td>
					<th>学年</th>
		      		<td>
		      			<html:select property="xn" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
		      		</td>
		      		<th>学期</th>
		      		<td>
		      			<html:select property="xq" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
		      		</td>
		        </tr>
		        <tr>
		      		<th>抽查日期</th>
		      		<td>
		      			<html:text property="ccrqks" style="width:80px" onclick="return showCalendar('ccrqks','y-mm-dd');" ></html:text>-
									<html:text property="ccrqjs" style="width:80px" onclick="return showCalendar('ccrqjs','y-mm-dd');"></html:text>
		      		</td>
					<th>检查类型</th>
		      		<td><html:select property="jclxdm" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="jclxList" property="jclxdm" labelProperty="jclxmc"/>
									</html:select></td>
		      		<th>检查用户</th>
		      		<td><html:select property="ccyhlx" style="width:160px">
									<html:option value=""></html:option>
									<html:option value="xx">学校</html:option>
									<html:option value="xy"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									</html:select></td>
		        </tr>
				 <tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
		      		<td>
		      			<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
		      		</td>
					<th>专业</th>
		      		<td><html:select property="zydm" style="width:160px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select></td>
		      		<th>班级</th>
		      		<td><html:select property="bjdm" style="width:160px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select></td>
		        </tr>
			
				<tr>
		      		<th>是否有人</th>
		      		<td>
		      			<html:select property="sfyr" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">是</html:option>
										<html:option value="0">否</html:option>
									</html:select>
		      		</td>
					<th>是否在规定时间内处理</th>
		      		<td><html:select property="sfzgdsjcl" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">是</html:option>
										<html:option value="0">否</html:option>
									</html:select></td>
		      		<th>是否处理</th>
		      		<td><html:select property="sfcl" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="1">是</html:option>
										<html:option value="0">否</html:option>
									</html:select></td>
		        </tr>
		        <tr>
		        	<th>学校审核</th>
		        	<td>
		        		<html:select property="xxsh" style="width:160px">
										<html:option value=""></html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="待批复">待批复</html:option>
										<html:option value="未审核">未审核</html:option>
									</html:select>
		        	</td>
		        </tr>
				</tbody>
			</table>
		  </div>
		  </div>
		  
		  <div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细并审核；单击表头可以排序</font>
							</logic:notEmpty>
			    </span>
			    </h3>
			   
			  <logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" align="" width="100%">
			    <thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
			    <tbody>
			      <logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showTopWin('pjpyxfjs.do?method=bjccqkshxx&pk='+this.cells[0].getElementsByTagName('input')[0].value)">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>	
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" >
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
			    </tbody>
			  </table>
			  </div>
			  <!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyXfjsForm"></jsp:include>
			  <!--分页显示-->
			  </logic:notEmpty>
			</div>		
				<div id="tmpdiv"></div>
			</logic:empty>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功！");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败！");
			</script>
		</logic:equal>
	</body>
</html>

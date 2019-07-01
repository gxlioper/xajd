<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("请选择一行要修改的记录！");
				return false;
			}
			var pkValue = curr_row.cells[1].innerText;
			showTopWin(url+pkValue,w,h);
		}
		
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if (!confirm("确定要删除所选记录？")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/qgzxZgdzdx.do" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType }"/>
		<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
		<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
		<input type="hidden" name="mes" id="mes" value="${mes}"/>
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="showTopWin('qgzxZgdzdx.do?method=showAddHmd','600','400')" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="modidata('qgzxZgdzdx.do?method=modiHmd&pkValue=','600','400')" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="del('qgzxZgdzdx.do?method=delHmd')" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					</logic:equal>
					<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do?tableName=view_qgzx_zgdzdx_hmdb');" class="btn_dc">导出数据</a> </li>
			    </ul>
			  </div>
			  <!--查询条件-->
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button type="button" class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=hmdgl');">
								查 询
						  </button>
						  &nbsp;&nbsp;&nbsp;&nbsp;
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
							<html:select property="nj" styleId="nj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>	
						</td>
						<th>学号</th>
						<td>
							<html:text property="xh" style="width:180px"></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" style="width:180px"></html:text>
						</td>
					</tr>
					<tr>
			      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:180px" 
							styleClass="select" styleId="xy" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:180px" styleId="zy"
							styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" 
								labelProperty="zymc"/>
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" style="width:180px" styleId="bj"
							styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序！</font>
				</logic:notEmpty>
			    </span>
			    </h3>
				   
			  <logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
						<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="0">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;" align="center" bgcolor="" ondblclick="modidata('qgzxZgdzdx.do?method=modiHmd&doType=view&pkValue=','500','400')">
							<td>
								<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="pk" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>							
							<logic:iterate id="v" name="s" offset="0">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
			</table>
			</div>
			<!--分页显示-->
		      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxZgdzdxForm"></jsp:include>
		 	<!--分页显示-->
			</logic:notEmpty>
			</div>			
			<div id="tmpdiv"></div>
	</html:form>

	<logic:present name="result">
		<logic:equal value="true" name="result">
		<script>
			alert('操作成功!');
			document.getElementById('search_go').click();
		</script>	
		</logic:equal>
		<logic:equal value="false" name="result">
		<script>
			alert(document.getElementById('mes').value);				
		</script>
	</logic:equal>	
	</logic:present>
		
</body>
</html>

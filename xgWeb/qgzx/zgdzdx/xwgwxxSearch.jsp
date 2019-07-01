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
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue);
		}
		
		function showDetail(url,w,h){
			if(curr_row == null){
					alert("请选择一行要查看的记录！");
					return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
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
				alert("请选择要批量设置的记录！");
				return false;
			}
			
			if (!confirm("确定要删除所选记录？")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
<body>
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
					<em>您的当前位置:</em><a>勤工助学 - 校外勤工助学 - 岗位信息查询</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
						 <li> <a href="#" onclick="refreshForm('qgzxZgdzdx.do?method=showGwxxb');" class="btn_zj">增 加</a> </li>
						 <li> <a href="#" onclick="modidata('qgzxZgdzdx.do?method=modiGwxx&pkValue=','500','400');" class="btn_xg">修改</a> </li>
						 <li> <a href="#" onclick="del('qgzxZgdzdx.do?method=delXwgwxx');" class="btn_sc">删除</a> </li>
						 <li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					  </logic:equal>
					  <li> <a href="#" onclick="dataExport('/xgxt/expData.do?tableName=qgzx_xwgwxxb');" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=gwglSearch')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>学年</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:80px">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>	
						</td>
						<th>年度</th>
						<td>
							<html:select property="nd" style="width:175px" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:175px" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>发布单位</th>
						<td>
							<html:select property="sqdwmc" style="width:175px" styleId="sqdwmc" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="sqdwmc" 
								labelProperty="sqdwmc"/>
							</html:select>	
						</td>
						<th>发布时间</th>
						<td>
							<html:text property="sqsj" onclick="return showCalendar('sqsj','y-mm-dd');" onblur="value=value.replace('-','').replace('-','')" styleId="sqsj"/>
						</td>
						<th></th>
						<td>
							
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
							<font color="blue">提示：双击记录可以查看详细信息，单击表头可以排序！</font> 
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
					<logic:iterate id="tit" name="topTr" offset="1">
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
						style="cursor:hand;" align="center" bgcolor="" 
						ondblclick="showDetail('qgzxZgdzdx.do?method=showGwxxDetail&pkValue=','600','500')">
						<td>
							<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
						</td>							
						<logic:iterate id="v" name="s" offset="1">
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(doType){
			if(curr_row != null){
				showTopWin('/xgxt/qgzxkh.do?method=xsgzkhModi&pkValue='+curr_row.getElementsByTagName('input')[0].value+"&doType="+doType,700,500);
				return true;
			}else{
				alert('请选择要修改的行！');
				return false;
			}
		}
		
		function del(){
			var RowsStr="!!";	
			var mes = "确定要操作所选记录？";
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url = "qgzxkh.do?method=delXsgzjl";
			refreshForm(url);
		}
		
		//数据导出
		function exportData(){
			var url = 'qgzxkh.do?method=expXsgzkh';
			var eleArr = ["nd","yf","nj","gwdm","xh","xm"];
			url += "&xydm=" + val("xy"); 
			url += "&zydm=" + val("zy");
			url += "&bjdm=" + val("bj");
			for(var i=0; i<eleArr.length;i++){
				url += "&" + eleArr[i] + "=" + val(eleArr[i]);
			}
			window.open(url)
		}
		
		function print(){
			if(curr_row != null){
				window.open('/xgxt/qgzxkh.do?method=printXskhb&pkValue='+curr_row.getElementsByTagName('input')[0].value);
			}else{
				alert('请选择要打印的记录行！');
				return false;
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
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
					<!--读写权-->
					<logic:equal value="yes" name="writeAble">
					<logic:notEqual value="stu" name="userType">
						<li> <a href="#" onclick="modi('modi');" class="btn_zj">录入数据</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					</logic:notEqual>
					</logic:equal>
					<!--end读写权-->
					<li> <a href="#" onclick="exportData();" class="btn_dc">导出数据</a> </li>
					<li> <a href="#" onclick="print();" class="btn_dy">打印考核表</a> </li>
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
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/qgzxkh.do?method=queryXsgzkh')">
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
						<html:select property="nd" styleId="nd" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<th>月份</th>
					<td>
						<html:select property="yf" styleId="yf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf" labelProperty="yf" />
						</html:select>
					</td>						
					<th>岗位</th>
					<td>
						<html:select property="gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwmcList" property="gwdm" labelProperty="gwdm" />
						</html:select>
					</td>				
				</tr>	
				<tr>					
		      		<th>年级</th>
					<td>
						<html:select property="nj"  styleId="nj"
							onchange="initZyList();initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th>学号</th>
					<td>
						<html:text property="xh" styleId="xh" style="width:180px"></html:text>
					</td>						
					<th>姓名</th>
					<td>
						<html:text property="xm" styleId="xm" style="width:180px"></html:text>
					</td>				
				</tr>					
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="zydm" styleId="zy" onchange="initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
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
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
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
						<tr onclick="rowMoreClick(this,'',event);"
							ondblclick="modi('view')" style="cursor:hand">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pkV"
										value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
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
				</tbody>			
			   </table>
			   </div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>

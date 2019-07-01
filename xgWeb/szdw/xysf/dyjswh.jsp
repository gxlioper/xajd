<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
		
		function isSh(){
			if(curr_row != null){
					var xysh = curr_row.getElementsByTagName('input')[2].value;
				var xxsh = curr_row.getElementsByTagName('input')[3].value;
				var userType = $('userType').value;
			
				if((userType == "fdy" && xysh == "通过") || (userType == "xy" && xxsh == "通过")){
					alert("您选择的学生已被上级审核通过，您无权修改");
				}else{
					modi('gdby_tygl.do?method=tyViewAndModi&doType=modi');
				}
			}else{
				alert('请选择要修改的数据行！');
			}
		}
		
		function choiceDisabled(ele) {
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if (document.getElementById(tmp[i])) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/xysf_dyjsgl.do?method=dyjswh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" name="isComm" value="true"/>
					
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_ck" onclick="modi('xysf_dyjsgl.do?method=dyjskcap',800,600);return false;">课程安排</a></li>
					<li><a href="#" class="btn_zj" onclick="showTopWin('xysf_dyjsgl.do?method=addDyjs',700,500);return false;">增加</a></li>
					<li><a href="#" class="btn_xg" onclick="modi('xysf_dyjsgl.do?method=dyjsViewAndModi&doType=modi',700,500);return false;">修改</a></li>
					<li><a href="#" class="btn_sc" onclick="dataBatch('xysf_dyjsgl.do?method=dyjswh&doType=del&go=go');return false;">删 除</a></li>
					<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入</a></li>
					<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a></li>
					<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>							
				</ul>
			</div>
			</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<th>职工号</th>
							<td><html:text property="querylike_zgh" styleId="zgh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th>负责部门</th>
							<td><html:select property="queryequals_bmdm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						
						<logic:equal value="xy" name="userType">
							<input type="hidden" name="queryequals_bmdm" value="${userDep }"/>
							<script type="text/javascript">
								choiceDisabled('bmdm');
							</script>
						</logic:equal>
					</tbody>
					
					<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/xysf_dyjsgl.do?method=dyjswh&go=go')">
		              		查 询
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     		</tfoot>
				</table>
				</div>
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('xysf_dyjsgl.do?method=dyjsViewAndModi&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							
							</tr>
						</logic:iterate>
					</table>
					<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dyjstForm"></jsp:include>
				<!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>

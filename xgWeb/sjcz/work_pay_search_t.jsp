<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function dateFormatChg(obj) {
			if (obj.tagName.toLowerCase() == "input") {
				obj.value = replaceChar(obj.value, "-", "").substring(0, 8);
			}
		}
		
		//数据导出
		function exportData(){
			var url = "qgzxcjff.do?method=expXscjffxx";				
			var elementArr = ["nd","yf","gwdm","xh","yrdwdm","fflx","ffsjks","ffsjjs","xxsh"];
			url += "&xydm=" + val("xy");
			url += "&zydm=" + val("zy");
			url += "&bjdm=" + val("bj");
			for(var i=0; i<elementArr.length; i++){
				if(ele(elementArr[i])){
					url += "&" + elementArr[i] + "=" + val(elementArr[i]);
				}
			}
			window.open(url);
		}
		
		//批量复制
		function batchModiXscjff(){
			var RowsStr="!!";	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}				
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			showTopWin("qgzxcjff.do?method=batchModiXscjff&pkV=" + RowsStr);
		}
		
		//dwr获取title名
		function getTitleName(){
			if($("path")){
				var path=$("path").value;
				cqkjFunc.getTitles(path,function(data){
					document.getElementById('titleName').innerHTML=data;
				});
			}
		}
		//统计Excel相关的两个方法
		function setPath(){
			$("path").value="/qgzx_gbmyg_execl.do";
		}
		
		function exportTj(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=gbmygbtj&doType=exp';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//工资发放表Excel
		function exportGzff(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=gbmygbtj&doType=gzff';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		// --------------2012.3.14 qlj 临时岗位酬金发放--------------------
		// -------------勤工助学 学生临时岗位酬金发放信息统计 begin---------------
		function lsgwTj(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=lsgwTj';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		// -------------勤工助学 学生临时岗位酬金发放信息统计 end---------------
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/work_pay_search_t" method="post">
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="path" id="path" value="${path}"/>
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="work_pay" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" name="userType" id="userType" value="${userType}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="userName" id="userName" value="${userName}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 酬金发放 - 酬金发放查询</a>
				</p>
			</div>
			<div class="toolbox">	
			<!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<!--浙江传媒学院-->
					<logic:equal value="11647" name="xxdm">
						<li> <a href="#" onclick="showTopWin('qgzxcjff.do?method=addXscjff',600,400);return false;" class="btn_zj">增加</a> </li>
						<li> <a href="#" onclick="batchModiXscjff();return false;" class="btn_fz">酬金复制</a> </li>
					</logic:equal>
					<!--end浙江传媒学院-->
					<li> <a href="#" onclick="deleteCjff();return false;" class="btn_sc">删 除</a> </li>
					<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a> </li>
					<li> <a href="#" onclick="exportData();return false;" class="btn_dc">导出数据</a> </li>
					<!-- 非广东建设职业技术学院 -->
					<logic:notEqual value="12741" name="xxdm">
					<li> <a href="#" onclick="setPath();exportTj();return false;" class="btn_dc">统计</a> </li>
					<li> <a href="#" onclick="setPath();exportGzff();return false;" class="btn_dc">工资发放表</a> </li>
					<!--  2012.3.14 qlj 临时岗位酬金发放 begin --> 
					<li> <a href="#" onclick="lsgwTj();return false;" class="btn_tj">临时岗位统计</a> </li>
					<!--   临时岗位酬金发放 end --> 
					</logic:notEqual>
					<!-- end非广东建设职业技术学院 -->
			    </ul>
			  </div>
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
								onclick="if(checkFfsjTime()){allNotEmpThenGo('/xgxt/work_pay_search_t.do')}">
							查询
						</button>
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重置
							 		</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
					<th>年度</th>
					<td>
						<html:select property="nd" style="width:180px" styleId="nd" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="nd" labelProperty="nd"/>
						</html:select>
					</td>	
					<th>月份</th>
					<td colspan="3">
						<html:select property="yf" style="width:180px"
							styleId="yf">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf"
								labelProperty="yf" />
						</html:select>
					</td>			
				</tr>
				<tr>
					<th>学号</th>
					<td>
						<html:text property="xh" style="width:180px" styleId="xh"></html:text>
					</td>	
					<th>姓名</th>
					<td colspan="3">
						<html:text property="xm" style="width:180px" styleId="xm"></html:text>
					</td>			
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" styleId="xy" style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
					</td>	
					<th>专业</th>
					<td>
						<html:select property="zydm" styleId="zy" style="width:180px"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px">
						<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
						</html:select>
					</td>			
				</tr>
				<tr>
					<th>用人单位</th>
					<td>
						<html:select property="yrdwdm" style="width:180px"
							styleId="yrdwdm" onchange="loadGwmcxx('gwdm','','nd','')">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>	
					<th>岗位名称</th>
					<td>
						<html:select property="gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdm"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>发放时间</th>
					<td>
						<html:text property="ffsjks" style="width:85px" styleId="ffsjks" onclick="return showCalendar('ffsjks','y-mm-dd');" readonly="true"/>-
						<html:text property="ffsjjs" style="width:85px" styleId="ffsjjs" onclick="return showCalendar('ffsjjs','y-mm-dd');" readonly="true"/>
					</td>			
				</tr>
				<%--中国地质大学--%>
				<logic:equal value="10491" name="xxdm">	
				<tr>
					<th>发放类型</th>
					<td>
						<html:select property="fflx" styleId="fflx" style="width:180px">
						<html:option value="月工资">月工资</html:option>
						<html:option value="临时岗工资">临时岗工资</html:option>
						<html:option value="补发工资">补发工资</html:option>
						</html:select>
					</td>	
					<th>学校审核</th>
					<td colspan="3">
						<html:select property="xxsh" style="width:180px">
							<html:option value=""></html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
							<html:option value="未审核">未审核</html:option>
						</html:select>
					</td>		
				</tr>	
				</logic:equal>	
				<%--end中国地质大学--%>	
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
					<font color="blue">提示：单击表头可以排序！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
				    <td>
						<input type="checkbox" id="all" name="all" onclick="chec()" />
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
						<tr onclick="rowOnClick(this)" style="cursor:hand">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" id="pkV" value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<td>
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="flag">
			<logic:equal value="true" name="flag">
				<script>
					alert('操作成功！');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="flag">
				<script>
					alert('操作失败！');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present> 
	</body>
</html>
		
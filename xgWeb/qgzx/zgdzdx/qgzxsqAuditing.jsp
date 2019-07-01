<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxZgdzdxFunc.js'></script>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("请选择一行要修改的记录！");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pkValue;
			showTopWin(url,w,h);
		}	
		
		//批量审核通过
		function qgzxsqshtg(){
			var xxdm = val('xxdm');
			if(xxdm == '11078'){
				//广州大学
				var pkString = "!!SplitOneSplit!!";
				var yesNo = yesNo;
				var url = "qgzxZgdzdx.do?method=saveQgzxshBatch";
				var gwxzdm = $("gwxzdm").value;
				var xxdm = $("xxdm").value;
				var xydm = $("xydm").value;
				var userType = $("userType").value;
				for (i=0; i<document.getElementsByName("pk").length; i++){ //连接字符串
					if(document.getElementsByName("pk")[i].checked){
			    		pkString += document.getElementsByName("pkV")[i].value +"!!SplitOneSplit!!";
			    	}
				}
				
				if(pkString == "!!SplitOneSplit!!"){
					alert('请选择要操作的记录！');
					return false;
				}
				
				url += "&pkString=";
				url += pkString;
				url += "&yesNo=通过";					
				if(gwxzdm=="002" && userType=="xx" 
					|| gwxzdm=="002" && userType=="admin" 
					|| gwxzdm=="001" && userType=="xy"){
					if (confirm("确定要批量操作吗？")) {
							showSelectGwDiv(url);	
					}
				}else{
					url+="&gwxzdm=";
					url+=gwxzdm;
					refreshForm(url);
				}					
			}else{
				qgzxAudiBatch('通过');
			}
		}		
		
		function auditing(url){	
			var xmdm = val("hidGw");
					
			url += "&gwdm=";
			url += val("txtGw");
			url += "&xmdm=";
			url += val("hidGw");
			if(xmdm == ""){
				alert("请指定岗位！");
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body onload="xyDisabled('xy')">
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>			
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 审核 - 勤工助学申请审核</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
						<logic:notEqual name="userType" value="xy">
							<li> <a href="#" onclick="qgzxsqshtg();" class="btn_shtg">审核通过</a> </li>
							<li> <a href="#" onclick="qgzxAudiBatch('不通过');" class="btn_shbtg">审核不通过 </a> </li>
					  </logic:notEqual>
					  <!-- 地质大学说学院用户进去看不到审核按钮,特修改-->
					  <logic:equal value="xy" name="userType">
					  <logic:equal value="10491" name="xxdm">
					  	<li> <a href="#" onclick="qgzxsqshtg();" class="btn_shtg">审核通过</a> </li>
						<li> <a href="#" onclick="qgzxAudiBatch('不通过');" class="btn_shbtg">审核不通过 </a> </li>
					  </logic:equal>
					  </logic:equal>
					  <!-- end 地质大学说学院用户进去看不到审核按钮,特修改-->
					  </logic:equal>

					  <!--非广州大学-->
					  <logic:notEqual value="11078" name="xxdm">
						<li> <a href="#" onclick="dataExport();" class="btn_dc">导出数据</a> </li>
					  </logic:notEqual>
					  <!--end非广州大学-->						
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
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=qgzxsqAudi')">
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
							<html:select property="nd" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>			
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>	
						</td>
					  </tr>	
					  <tr>
						<th>年级</th>
						<td>
							<html:select property="nj" style="width:90px"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
							</html:select>	
						</td>
						<th>学号</th>
						<td>
							<html:text property="xh"/>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm"/>	
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:175px" styleClass="select" styleId="xy" 
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:175px" styleClass="select" styleId="zy"
								onchange="initBj();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>			
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" style="width:175px" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>审核结果</th>
						<td>
							<logic:equal value="xx" name="userType">
								<html:select property="xxsh">
									<html:option value=""></html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>
							</logic:equal>
								
							<logic:equal value="admin" name="userType">
								<html:select property="xxsh">
									<html:option value=""></html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>
							</logic:equal>		
				
							<logic:notEqual value="xx" name="userType">
							<logic:notEqual value="admin" name="userType">
								<html:select property="xysh">
									<html:option value=""></html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="未审核">未审核</html:option>
								</html:select>	
							</logic:notEqual>
							</logic:notEqual>
						</td>

						<!--广州大学-->
						<logic:equal value="11078" name="xxdm">	
						<th>岗位性质</th>
						<td>
							<html:select property="gwxzdm" styleId="gwxzdm" styleClass="select">
								<html:options collection="gwxzList" property="gwxzdm" 
								labelProperty="gwxzmc"/>
							</html:select>			
						</td>
						</logic:equal>
						<!--end广州大学-->
						<!--非广州大学-->
						<logic:notEqual value="11078" name="xxdm">	
						<th></th>
						<td>
									
						</td>
						</logic:notEqual>
						<!--end非广州大学-->
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
							<font color="blue">提示：双击可以查看详细信息<logic:equal value="xx" name="userType">并审核</logic:equal><logic:equal value="admin" name="userType">并审核</logic:equal>，单击表头可以排序！</font> 
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
					<logic:iterate id="tit" name="topTr" offset="2">
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
							style="cursor:hand;" align="center" 
							bgcolor="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
							ondblclick="modidata('qgzxZgdzdx.do?method=showQgzxsqsh&pkValue=',800,600)">
							
							<logic:equal name="xxdm" value="11078">
							<td>
								<input type="hidden" id="pkV" name="pkV" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>  value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>							
							<logic:iterate id="v" name="s" offset="3">
								<td>
									${v}
								</td>
							</logic:iterate>
							</logic:equal>

							<logic:notEqual name="xxdm" value="11078">
							<td>
								<input type="hidden" id="pkV" name="pkV" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="pk" name="pk"  value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>							
							<logic:iterate id="v" name="s" offset="2">
								<td>
									${v}
								</td>
							</logic:iterate>
							</logic:notEqual>
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
				<logic:empty name="mes">
					<script>
						alert('操作成功!');
						document.getElementById('search_go').click();
					</script>	
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
						document.getElementById('search_go').click();
					</script>
				</logic:notEmpty>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:empty name="mes">
					<script>
						alert('操作失败!');
						document.getElementById('search_go').click();
					</script>	
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
						document.getElementById('search_go').click();
					</script>
				</logic:notEmpty>
		</logic:equal>	
		</logic:present>
	</body>
</html>

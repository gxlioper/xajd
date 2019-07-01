<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
    <script language="javascript" src="js/xgutil.js"></script>
    <script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function batch(yesNo){
			var pkString = "";
			var xhString = "";
			var xnString = "";
			var xqString = "";
			var zjkjPk = "";
			var userName = $('userName').value;
			var userType = $('userType').value;
			var xxdm = document.getElementById('xxdm').value;
			var number = 0;
			
			var mes = "确定要批量操作吗？";
			if(xxdm == "13022"){//浙江大学宁波理工学院
				mes = "上级审核通过的记录将不改变审核结果，确定要批量操作吗？";
			}	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
				var obj = document.getElementsByName("pkV")[i];
		    	if(document.getElementsByName("pkV")[i].checked){
		    		number += 1;
		    		pkString += document.getElementsByName("pkV")[i].value+"!!SplitOneSplit!!";
		    		xhString += document.getElementsByName("xhV")[i].value+"!!SplitOneSplit!!";
		    		zjkjPk += document.getElementsByName("xhV")[i].value + trim(obj.parentNode.parentNode.getElementsByTagName("td")[1].innerText) + trim(obj.parentNode.parentNode.getElementsByTagName("td")[2].innerText)+"!!SplitOneSplit!!";
		    	}
		    	
			}			
			if (pkString==""){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if(number <2){
				mes = "确定要操作选择的记录吗？";
				if(xxdm == "13022"){
					mes = "上级审核通过的记录将不改变审核结果，确定要操作选择的记录吗？";
				}
			}
			dwr.engine.setAsync(false);
			if(xxdm == "11057" && yesNo=="pass"){//浙江科技
				//如果已经有岗位审核通过，显示提示信息
				if(checkArrayEleRepeat(zjkjPk,"!!SplitOneSplit!!")){
					mes = "部分学生通过的岗位超过了1个，确定要继续操作吗？";
				}else{
					cqkjFunc.checkZjkjXsgw(pkString,zjkjPk,function(data){
						if(data != null && data != ''){
							mes = data + "确定继续操作吗？";
						}
					});
				}
				
			}
			if (confirm(mes)) {
				if(yesNo!=null && yesNo=="pass"){
					if(xxdm == "13742"){//宁波天一职业技术学院
						cqkjFunc.checkFprs(pkString,userType,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									alert("无法审核通过："+message);
									return false;
								}else{
									refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
								}						
							}
						});
					}else if(xxdm == "11057"){//浙江科技学院
						refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
					}else{
						refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
						
					}
				}else{
					refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
				}
			}
			dwr.engine.setAsync(true);
		}
		
		function checkStuOkNum(){
			var pk = document.getElementById("xmdm").value;
			cqkjFunc.isStuOkNumBeyondStandard(pk,function(data){
				
			});
			return data;
		}
		
		function auto_check_stu(){
			var pk = document.getElementById("xmdm").value;
			if($('xmdm').value == ''){ 
				alert('岗位名称不能为空，无法自动审核！');
				return;
			}else {
			if(confirm("您确定按岗位要求的条件自动审核吗？")){
				cqkjFunc.isStuOkNumBeyondStandard(pk,function(data){
					if(data == true){
						if(confirm('合格的学生人数已经超过岗位的需求人数!\n是否继续自动审核？\n' +
								'点击[确定]，系统会自动选择最先申请合格的学生\n' +
								 '点击[取消]，系统会列出所有合格的学生，手动审核')){		
							refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=3');
						}else{
							refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=2');
						}
					}else{
						refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=1');
					}				
				});
			 }
			}
		}		
	
	function chkPriseOne(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		if(xxdm == "11551"){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}		
		showTopWin(url, w, h);
}
}
	//跳转路径：
	function queryOne(xh) {
		var url = "/xgxt/stu_info_details.do?xh="+xh;
		showTopWin(url, 800, 600);
	}
	
	//打印审核通过学生名单
	function printStuinfo(){
		var url = "qgzxXcxy.do?method=printPassStu";
		var xn = document.getElementById('xn').value;
		var nd = document.getElementById('nd').value;
		var nj = document.getElementById('nj').value;
		var yrdwdm = document.getElementById('yrdwdm').value;
		var xydm = document.getElementById('xydm').value;
		var xmdm = document.getElementById('xmdm').value;
		
		url += "&xn=" + xn;
		url += "&nd=" + nd;
		url += "&nj=" + nj;
		url += "&yrdwdm=" + yrdwdm;
		url += "&xydm=" + xydm;
		url += "&xmdm=" + xmdm;
		
		window.open(url);
	}
	</script>
</head>
	<body <logic:equal value="11654" name="xxdm">onload="xyDisabled('xy');"</logic:equal> >
		<html:form action="/post_stu_check" method="post">
		
		<logic:equal value="11654" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
		</logic:equal>
		<logic:notEqual value="11654" name="xxdm" scope="session">
			<!-- 浙江交通职业技术学院 -->
			<logic:equal value="12036" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:equal>
			<!-- 浙江交通职业技术学院end -->
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>"/>
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
		</logic:notEqual>
		
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
			   
					<!-- 北京联合大学 -->
					<logic:present name="showbjlh">
						<li> <a href="#" onclick="refreshForm('/xgxt/postStuChkBatch.do')" class="btn_zj">录 用</a> </li>
					</logic:present>		
					<logic:notPresent name="showbjlh">
						<li> <a href="#" onclick="batch('pass')" class="btn_shtg">审核通过</a> </li>
						<li> <a href="#" onclick="batch('nopass')" class="btn_shbtg">审核不通过</a> </li>
					</logic:notPresent>
					<!-- 井冈山大学 -->
					<logic:equal value="10419" name="xxdm">
						<%-- 加参数zdsh 用于 在查询时加入自动审核的条件 --%>
						<li> <a href="#" onclick="auto_check_stu()" class="btn_sh">自动审核</a> </li>
					</logic:equal>
					<!-- end井冈山大学 -->
					<!--西昌学院-->
					<logic:equal value="10628#" name="xxdm">
						<li> <a href="#" onclick="printStuinfo()" class="btn_ck">通过学生</a> </li>
					</logic:equal>
					<!--end西昌学院-->
					 </logic:equal>
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
								onclick="refreshForm('/xgxt/post_stu_check.do?go=go')">
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
					<th>年级</th>
					<td>
						<html:select property="nj" style="width:180px" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
		      		<th>学年</th>
					<td>
						<html:select property="xn" style="width:180px;" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>年度</th>
					<td>
						<html:select property="nd" style="width:180px;" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
							styleId="nd">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>					
				</tr>
				<tr>
					
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<!-- 学院用户列表显示，非学院用户列表显示 -->
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()" disabled="true">
								<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xydm" id="xydm" value="<bean:write name="userDep"/>"/>
								</logic:notEqual>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</td>
					<th>专业</th>
					<td>
						<html:select property="zydm" style="width:180px" styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>用人单位</th>
					<td>
						<html:select property="yrdwdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>
		      		<th>岗位性质</th>
					<td>
						<html:select property="gwxz" styleId="gwxzdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm"
								labelProperty="gwxzmc" />
						</html:select>
					</td>
					<th>岗位名称</th>
					<td>
							<html:text property="xmdm" style="width:180px"></html:text>
					</td>					
				</tr>
				<!--浙江科技学院-->
				<logic:equal value="11057" name="xxdm">
				<tr>
					<th>用人单位审核</th>
					<td>
						<html:select property="xyyj" styleId="xyyj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
		      		<th>学校审核</th>
					<td>
						<html:select property="xxyj" styleId="xxyj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th></th>
					<td>
						
					</td>					
				</tr>
				</logic:equal>
				<!--end浙江科技学院-->
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
					 <font color="blue">记录数：${rsNum} &nbsp;&nbsp;提示：双击一行可查看详细信息，并可改变审核状态；蓝色表示通过，红色表示不通过，其他为未审核；</font>
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
					<logic:iterate id="tit" name="topTr" offset="3">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
			    		<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="0" length="1">
						    <bean:write name="v"/>
						    </logic:iterate> 
						     "
							ondblclick="chkPriseOne('/xgxt/postStuChkOne.do?act=view&pkVal=')">
							<td>
								<!-- 以下判断用于控制学校用户开始审核后,用人单位不能再进行审核(浙工大之江学院) -->
								<logic:equal value="13275" name="xxdm">
								<logic:equal value="xy" name="userType" scope="request">
									<logic:iterate id="v" name="s" offset="12" length="1">
										<logic:equal value="通过" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" disabled="disabled" />
											</logic:iterate>
										</logic:equal>
										<logic:equal value="不通过" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" disabled="disabled"/>
											</logic:iterate>
										</logic:equal>
										<logic:equal value="未审核" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
											</logic:iterate>
										</logic:equal>
										<logic:empty name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
											</logic:iterate>
										</logic:empty>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="request">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
									</logic:iterate>
								</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="13275" name="xxdm">
									<logic:equal value="12036" name="xxdm">
										<input type="checkbox" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> name="pkV" value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
									</logic:equal>
									<logic:notEqual value="12036" name="xxdm">
										<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox"  name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>
									</logic:notEqual>
								</logic:notEqual>
								
								<logic:iterate id="v" name="s" offset="6" length="1">
									<input type="hidden" name="xhV" value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3" length="4">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="7">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>				
				<logic:present name="showbjlh">
					<tfoot>
					<font color="red">通过审核的录用岗位：
					<logic:iterate id="tsgw" name="tsgwList">
					<bean:write name="tsgw" property="gwdm"/>
					<bean:write name="tsgw" property="rs"/>人
					</logic:iterate>
					</font>
					</tfoot>
				</logic:present>				
			   </table>
			   </div>
				<!--分页显示-->
				   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				<!--分页显示-->
			</logic:notEmpty>
		</div>	
		<logic:present name="showbjlh">
		<logic:equal value="true" name="result">
			<script>
				alert("成功录用！");					
			</script>
			<logic:notEmpty name="mes">
			<input id="mes" name="mes" value="${mes}" type="hidden"/>
			<script>
				alert(document.getElementById('mes').value);
				document.getElementById("search_go").click();
			</script>
			</logic:notEmpty>
			<script>
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
		<logic:notEmpty name="mes">
		<input id="mes" name="mes" value="${mes}" type="hidden"/>
		<script>
			alert(document.getElementById('mes').value);
			document.getElementById("search_go").click();
		</script>
		</logic:notEmpty>
		<logic:empty name="mes">
		<script>
			alert('操作失败!');
			document.getElementById("search_go").click();
		</script>
		</logic:empty>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("请选择要录用的学生！");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		</logic:present>
		
		<logic:notPresent name="showBjlh">
		<logic:present name="result">
			<logic:equal value="true" name="result">			
				<logic:notEmpty name="mes">
				<input id="mes" name="mes" value="${mes}" type="hidden"/>
				<script>
					alert(document.getElementById('mes').value);
				</script>
				</logic:notEmpty>
				<script>
					alert("操作成功！");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:notEmpty name="mes">
			<input id="mes" name="mes" value="${mes}" type="hidden"/>
			<script>
				alert(document.getElementById('mes').value);
			</script>
			</logic:notEmpty>
			<logic:empty name="mes">
			<script>
				alert('操作成功!');
			</script>
			</logic:empty>
		</logic:equal>
		</logic:present>
		</logic:notPresent>
		
		<div id="tmpdiv"></div>			
		</html:form>
		<!--页面最下面：-->
		<script language="javascript" defer="defer">
		var b = false;;
		if(document.getElementById('rsTable')){
			for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
		  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
		  			b = true;
		  			break;
		  		}
		  	}
		  	if (b) {
		  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
		  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
		  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
		  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
		  			xhTd.innerHTML = html_content;
		  		}
		  	}
		}
		</script>
</body>
</html>
                                                                                                   

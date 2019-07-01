<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>			
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function batch(yesNo){
			var pkString = "";
			var xhString = "";
			var userName = document.getElementById("userName").value;
			if(Rows[0]==null){
				alert('请选择要操作的记录！');
				return false;
			}
			for (i=0; i<Rows.length; i++){ //连接字符串
    			pkString +=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
    			xhString +=Rows[i].cells[5].innerText+"!!SplitOneSplit!!";
			}
			if (confirm("确定要批量操作吗？")) {
				if(yesNo!=null && yesNo=="pass"){
				cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
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
						    if($("chkpass")){$("chkpass").disabled=true;}
						}						
					}
				});
				}else{
					refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
                       if($("chknopass")){$("chknopass").disabled=true;}
				}
			}
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
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/post_stu_check" method="post">
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
				
			<logic:equal value="false" name="flag">
				<br/><br/>
				<center>现在不在可审核时间范围内，不可进行审核操作！</center>
			</logic:equal>	
				
			<logic:equal value="true" name="flag">	
			<logic:notEmpty name="errMsg">
				<br/><br/><div align="center"><font color="red"><b>${errMsg}</b></font></div>
			</logic:notEmpty>
			<logic:empty name="errMsg">	
			
			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					<!-- 北京联合大学 -->
					<logic:present name="showbjlh">
						<li> <a href="#" onclick="refreshForm('/xgxt/postStuChkBatch.do');" class="btn_sh">录 用</a> </li>
					</logic:present>
				    <logic:notPresent name="showbjlh">
						<li> <a href="#" onclick="batch('pass');" class="btn_shtg">审核通过</a> </li>
						<li> <a href="#" onclick="batch('nopass');" class="btn_shbtg">审核不通过</a> </li>
					</logic:notPresent>
					<!-- 井冈山大学 -->
					<logic:equal value="10419" name="xxdm">
					<%-- 加参数zdsh 用于 在查询时加入自动审核的条件 --%>
						<li> <a href="#" onclick="auto_check_stu();" class="btn_sh">自动审核</a> </li>
					</logic:equal>
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
									onclick="refreshForm('/xgxt/post_stu_check.do?go=go')">
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
							<html:select property="xn" style="width:120px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>								
						</td>
						<th>年度</th>
						<td>
							<html:select property="nd" style="width:90px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>		
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:90px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>			
						</td>
					  </tr>
					  <tr>
						<th>年级</th>
						<td>
							<html:select property="nj" style="width:90px;padding-left:80px" >
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
							<html:select property="xydm" style="width:230px" styleId="xy" value="${userDep}">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>用人单位</th>
						<td>
							<html:select property="yrdwdm" 
								onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>		
						</td>
						<th>岗位名称</th>
						<td>
							<html:text property="xmdm" style="width:180px"></html:text>
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
							<font color="blue">提示：双击可以查看详细信息并审核，单击表头可以排序！</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
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
						<tr onclick="rowMoreClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="0" length="1">
						    <bean:write name="v"/>
						    </logic:iterate>
						    "
							ondblclick="chkPriseOne('/xgxt/postStuChkOne.do?act=view&pkVal=')">
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<logic:present name="showbjlh">
										<input type="checkbox" name="gwxx" value="<bean:write name="v"/>"/>
									</logic:present>
									<logic:notPresent name="showbjlh">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:notPresent>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>					
			    </tbody>
				</table>
				<logic:present name="showbjlh">
					<table class="dateline">
					<tr>
						<td>
						<font color="red">通过审核的录用岗位：
						<logic:iterate id="tsgw" name="tsgwList">
						<bean:write name="tsgw" property="gwdm"/>
						<bean:write name="tsgw" property="rs"/>人
						</logic:iterate>
						</font>
						</td>
					</tr>
					</table>
				</logic:present>
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
<%--						document.getElementById("search_go").click();--%>
					</script>
					</logic:notEmpty>
					<script>
<%--						document.getElementById("search_go").click();--%>
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<logic:notEmpty name="mes">
				<input id="mes" name="mes" value="${mes}" type="hidden"/>
				<script>
					alert(document.getElementById('mes').value);
<%--					document.getElementById("search_go").click();--%>
				</script>
				</logic:notEmpty>
				<logic:empty name="mes">
				<script>
					alert('操作失败!');
<%--					document.getElementById("search_go").click();--%>
				</script>
				</logic:empty>
			</logic:equal>
			</logic:present>
			</logic:notPresent>
			
			<div id="tmpdiv"></div>
			</logic:empty>	
			</logic:equal>
		
		</html:form>		
	</body>
</html>
                                                                                                   

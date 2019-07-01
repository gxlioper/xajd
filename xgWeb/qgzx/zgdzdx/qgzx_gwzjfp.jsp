<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		/* ============ begin 骆嘉伟 =============== */ 
		function ch(){
			var gw = document.getElementById("gw").value;
			var sq = "";
			var ap = "";
			document.getElementById("sq").value = "";
			document.getElementById("ap").value = "";
			for(var i = 0;i < document.getElementById("sq").options.length; i++){
				if(document.getElementById("sq").options[i].value ==gw){
					document.getElementById("sq").options[i].selected = true;
					sq = document.getElementById("sq").options[i].text;
				}
			}
			
			for(var i = 0;i < document.getElementById("ap").options.length; i++){
				if(document.getElementById("ap").options[i].value ==gw){
					document.getElementById("ap").options[i].selected = true;
					ap = document.getElementById("ap").options[i].text;
				}
			}
			document.getElementById("kyrs").value = sq - ap;
		}
		
		function aa(url,w,h){
			if (curr_row == null) {
				
			}else{
				if (w==''||w==null||h==''||h==null){
					w=500;
					h=400;
				}
				var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				url += pkValue;
				showTopWin(url,w,h);
			}
		}
		/* ============ end 骆嘉伟 =============== */ 
	</script>
</head>
	<body onload="">
		<html:form action="/qgzxZgdzdx.do" method="post">
		<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>"/>
		<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>"/>
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>

		<logic:equal value="false" name="allow">
			<br/><br/>
			<center>现在不在可直接分配的时间之内,暂时不能直接分配!</center>
		</logic:equal>
			
		<logic:equal value="true" name="allow">
			<logic:notEqual value="stu" name="userType">
				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="gwzjfp();" class="btn_zj">分配岗位</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="refreshForm('qgzxZgdzdx.do?method=showExportPage');" class="btn_dc">导出数据</a> </li>
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
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=searchGwfp')">
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
							<html:select property="xn" style="width:90px" styleId="xn" 
								onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>年度</th>
						<td>
							<html:select property="nd" styleId="nd" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>			
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:60px" styleId="xq" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>年级</th>
						<td>
							<html:select property="nj" style="width:70px"
								onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>		
						</td>
						<th>学号</th>
						<td>
							<html:text property="xh" style="width:120px"></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" style="width:85px"></html:text>
						</td>
					  </tr>
					  <tr>
						<logic:equal value="xyhj" name="xyhjType">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th></th>
						<td>
									
						</td>
						<th></th>
						<td>
							
						</td>
						</logic:equal>

						<logic:notEqual value="xyhj" name="xyhjType">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>			
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" style="width:150px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						</logic:notEqual>
					  </tr>		
					  <tr>
						<th>是否有岗位</th>
						<td>
							<html:select property="sfygw" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="有岗">有岗</html:option>
								<html:option value="无岗">无岗</html:option>
							</html:select>
						</td>
						<th>岗位</th>
						<td>
							<!-- begin 骆嘉伟 2009/3/26 -->
							<html:select property="gwdm" styleId="gw" onchange="ch()" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="gwList" labelProperty="gwdm" property="gwdm"/>
							</html:select>			
						</td>
						<th>用人单位</th>
						<td>
							<html:select property="yrdwdm" style="width:150px" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>		
						</td>
					  </tr>	
					  <tr>
						<th>岗位已安排人数</th>
						<td>
							<html:select property="gwdm" styleId="ap" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="apList" labelProperty="num" property="gwdmgwsbsj"/>
							</html:select>
						</td>
						<th>岗位审批使用人数</th>
						<td>
							<html:select property="gwdm" styleId="sq" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="sqList" labelProperty="sqsyrs" property="gwdmgwsbsj"/>
							</html:select>		
						</td>
						<th>岗位空余量</th>
						<td>
							<input type="text" id="kyrs" disabled="true" style="width:150px"/>  
						</td>
					  </tr>	
					  <!-- end 骆嘉伟 2009/3/26 -->
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
					<tr onclick="rowOnClick(this);" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" value="${v}"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name="v"/>" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="10" length="1">
								<input type="hidden" value="<bean:write name="v"/>" name="sfyg" id="sfyg"/>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2" length="3">
							<td>
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="5">
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
			</logic:notEqual>
		</logic:equal>

		<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<logic:notEmpty name="mes">
				<input name="mes" id="mes" value="${mes}" />
				<script>
					alert(document.getElementById('mes').value);
				</script>
			</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
			</logic:empty>
		</logic:equal>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">	
		function checkUser()
		{	
			var user=document.getElementById("userType").value;
			if("xy"==user)
			{
				document.getElementById('xy').disabled=true;
			}
			else if("xx"==user)
			{
				document.getElementById('xy').disabled=false;
			}
		}
	</script>	
	</head>
	<body onload="checkUser()">
			<html:form action="/arrangeClass.do?method=arrangeInitializtion" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userSpceType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="xsjbxxlsb"
							scope="request" />
			<input type="hidden" id="delPk" name="delPk" value="pk" />	
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />	
				<div class="tab_cur">
					<p class="location" id="title_m">
						<em>您的当前位置:</em><a>
						分班编学号 - 基本设置 - 编排规则设置
						</a>
					</p>
				</div>
				
				 <div class="toolbox">
				 	<logic:equal value="yes" name="writeAble">
						 <div class="buttonbox">
						    <ul>
							<li> <a href="#" onclick="showTopWin('arrangeClass.do?method=classConf',600,400,false)" class="btn_fz"> 班级人数 </a> </li>
						    <li> <a href="#" onclick="showTopWin('arrangeClass.do?method=classCode',800,400,false)" class="btn_zt"> 班级代码规则 </a> </li>
							<li> <a href="#" onclick="showTopWin('arrangeClass.do?method=classSimpleName',800,400,false)" class="btn_ck"> 班级简称规则 </a> </li>
						    <li> <a href="#" onclick="showTopWin('arrangeClass.do?method=className',800,400,false)" class="btn_sh"> 班级名称规则 </a> </li>
						    <li> <a href="#" onclick="showTopWin('arrangeClass.do?method=leaningCode',800,500,false)" class="btn_qxsh"> 学号规则设置 </a> </li>
						    <li> <a href="#"onclick="showTopWin('arrangeClass.do?method=leaningCodeOrder',800,500,false)" class="btn_shtg"> 学号排序规则 </a> </li>
						    </ul>
						 </div>
					</logic:equal>	
				<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
								<tr>
							 		<td colspan="6" >
										<div class="btn">
					             			<input type="hidden" name="go" value="a" />
											<button class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/arrangeClass.do?method=arrangeInitializtion');">
												查询
											</button>
					                         &nbsp;&nbsp;&nbsp;&nbsp;
					                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
					              	           	 重 置
					                         </button>
					                    </div>
									</td>
								</tr>
						</tfoot>
						<tbody>
							<tr>
								<th align="left">
									年级
								</th>
								<td>
									<html:select property="nj" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xymc" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zymc" style="width:180px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zymc"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>									
									学号
								</th>
								<td>
									<html:text property="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" />
								</td>
								<td clospan="2">
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
			 		 <logic:notEmpty name="rs" >
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						 <table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="">									
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
						<script type="text/javascript">
									$('choose').className="hide";
						</script>					
				</logic:notEmpty>
				</div>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

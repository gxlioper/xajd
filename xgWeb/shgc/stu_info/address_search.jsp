<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
		
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script language="javascript">		
		function check_users()
		{
			var userType=document.all['userType'].value;			
			 if("stu"==userType)
			{
				document.getElementById('bjdm').disabled=true;
			}
		}
		
		function check_user_commUpdate()
		{
			var userType=document.all['userType'].value;
			 if("stu"==userType)
			{
				return false;
			}
			else
			{
				commUpdate('address_book.do?doType=view&bjdm=',600,400);
			}
		}
	</script>
</head>
	<body onload="check_users()">
			<html:form action="/address_book" method="post">
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />

				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${tips}</a>
					</p>
				</div>
				<div class="toolbox">
				  <!--读写权-->
				  <logic:equal value="yes" name="writeAble">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="showTopWin('address_book.do?doType=view',600,400);" class="btn_zj">增 加</a> </li>
							<li> <a href="#" onclick="commUpdate('address_book.do?doType=view&bjdm=',600,400);" class="btn_xg">修 改</a> </li>
							<li> <a href="#" onclick="commDel('address_book.do?doType=del&bjdm=');" class="btn_sc">删 除</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
							<li> <a href="#" onclick="dataExport();" class="btn_dc">导出数据</a> </li>
							<li> <a href="#" onclick="expTab('rsTable','班主任通讯录','');" class="btn_dy">打印列表</a> </li>
					   </ul>
				  </div>
			      </logic:equal>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/address_book.do')">
									查询
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
						<th>姓名</th>
						<td>
							<html:text property="xm" />
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<logic:equal name="userType" value="xy" scope="session">
								<html:select property="xydm" style="width:180px"
									onchange="initZyList();initBjList();"
									disabled="true" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xy" scope="session">
								<html:select property="xydm" style="width:180px"
									onchange="initZyList();initBjList();" styleId="xy">
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
							<html:select property="bjdm" styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  </tbody>
					</table>
				</div>
				</logic:notEqual>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
						 <logic:notEmpty name="rs">
							<font color="blue">提示：双击可查看详细信息；单击表头可以排序</font>
				 		</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
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
						<tr onclick="rowOnClick(this)" style="cursor:hand"
							ondblclick="check_user_commUpdate()">
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
			 	<!--分页显示-->
				  <script type="text/javascript">
					  $('choose').className="hide";
				  </script>
				</logic:notEmpty>
				</div>

				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<SCRIPT>
					alert("操作成功!");
					window.close();				 	
				 	document.getElementById("search_go").click();
				</SCRIPT>
				</logic:equal>
				<logic:equal value="false" name="result">
				<SCRIPT>
				 	alert("操作失败!");
				</SCRIPT>
				</logic:equal>
				</logic:notEmpty>
			</html:form>
	</body>
</html>

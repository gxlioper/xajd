<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script>   
		var mb = 0;			
		function printZS(mod){
			var url="";
			var mb=document.getElementById("mb").value;
			if(curr_row==null){
				 alert('请选择要打印的记录！');
				 return false;
			}
			var xh = curr_row.cells[0].innerText;
			var zmlx = curr_row.cells[7].innerText;
			window.open("certificatePrintAll.do?doType=printone&xh="+xh+"&mb="+mb + "&zmlx="+zmlx);
		}
	
		function check_users()
		{
			var user=document.all['userType'].value;
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
	<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
	</style>
</head>	
	<body onload="check_users()">
		<html:form action="/certificate_print.do" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />		
			<input type="hidden" value="${userType}" id="userType" name="userType"/>
			<input type="hidden" value="" id="nj" name="nj"/>
			<input type="hidden" value="a" id="xmdm" name="jxjdm"/>
			<input type="checkbox" style="display:none" id="chgMode"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips}</a>
				</p>
			</div>
			<div class="toolbox noprint">				 
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="printZS(0);" class="btn_dy">打印测试页</a> </li>
							<li> <a href="#" onclick="printZS(1);" class="btn_dy">单个打印</a> </li>
							<li> <a href="#" onclick=" viewTempDiv('提示信息','xxtsDiv',350,100);" class="btn_dy">证书连打</a> </li>
					   </ul>
				  </div>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/certificate_print.do')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>学号</th>
						<td>
							<html:text property="xh" styleId="xh" style="width:180px"></html:text>
						</td>
						<th>证明类型</th>
						<td>
							<html:select property="zmlx" styleId="zmlx" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="typeList" labelProperty="cn" property="en"/>	
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" styleId="xy"
								onchange="initZyList();initBjList();" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" styleId="zy"
								onchange="initBjList();" style="width:180px">
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
					  <tr>
						<th>选择模板</th>
						<td>
							<html:select property="mb" styleId="mb" style="width:180px">
								<html:option value=""></html:option>										
								<html:option value="2">中文模板</html:option>
								<html:option value="3">英文模板</html:option>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						<th></th>
						<td>
							
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
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody id="tabPri">
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;">
							<logic:iterate id="v" name="s">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				</logic:notEmpty>
				</div>
			<div id="xxtsDiv" style="display:none">
				<table class='formlist'>
					<tbody>
					<tr><td>此操作将连续打印显示的学生数据,确定要做此操作吗？</td></tr>
					</tbody>
					<tfoot>
					<tr>
						<td align='center'><button onclick='exePrint()'>确定</button>
						<button onclick="hiddenMessage(true,true);">关闭</button>
						</td>
					</tr>
					</tfoot>
					</table>
			</div>
			<div id="tempdiv"></div>
		</html:form>
	</body>
</html>

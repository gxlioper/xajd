<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
		function viewdetail(){
			if(curr_row==null){
				alertInfo('请选择要查看的记录！');
				return false;
			}
			var pk = curr_row.getElementsByTagName('input')[0].value;
			showTopWin('rcsw_nthy_xfqfgl.do?method=xfqfcxDetial&pk=' + pk,520,400);
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/rcsw_nthy_xfqfgl" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">	
			<!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
			    	<li>
						<a href="#" class="btn_dr" onclick="impAndChkData()" id="btn_dr">导入</a>
					</li>
					<li><a href="#" class="btn_dc" onclick="expData('rcsw_nthy_xfqfgl.do?method=xfqfcx&doType=expData');">导出</a></li>
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
								onclick="allNotEmpThenGo('rcsw_nthy_xfqfgl.do?method=xfqfcx')">
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
						<html:select property="queryequals_nj" onchange="initZyList();initBjList()" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>				
					<th>学号</th>
					<td>
						<logic:equal value="stu" name="userType" scope="session">
							<html:text property="querylike_xh" maxlength="20" style="width:180px" readonly="true"></html:text>
						</logic:equal>
						<logic:notEqual value="stu" name="userType" scope="session">
							<html:text property="querylike_xh" maxlength="20" style="width:180px"></html:text>
						</logic:notEqual>
					</td>	
					<th>姓名</th>
					<td>
						<html:text property="querylike_xm" maxlength="20" style="width:180px"></html:text>
					</td>	
				</tr>	
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="queryequals_xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<th>交费情况</th>
					<td>
						<html:select property="queryequals_sfqf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="sfqfList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th></th>
					<td></td>
				</tr>
			  </tbody>
			</table>				
		</div>
		</div>
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息，单击表头可以排序！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td><input type="checkbox"/>
			      		</td>
						<logic:iterate id="tit" name="topTr" offset="1" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="viewdetail();">
							<td>
								<input type="checkbox" id="cbv" name="primarykey_cbv" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="1">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xfqfglActionForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>

		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>

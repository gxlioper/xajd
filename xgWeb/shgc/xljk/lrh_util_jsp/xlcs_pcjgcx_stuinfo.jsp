<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script language="JavaScript">
		function stu_send(){
			var xh=curr_row.cells[0].innerText;
			var url=jQuery("#url").val();
			//window.dialogArguments.refreshForm(url+'&stu_id='+xh);
			//window.close();
			var api = frameElement.api;
			if (api){
				if (api.get('childDialog')){
					api.reload(api.get('parentDialog') ,url+'&stu_id='+xh);
				} else {
					var W = api.opener;
					W.location=gotoPath;			
				}
			} else if (parent.window){
				parent.window.document.location=gotoPath;						
			}
			iFClose();
		}
		function usercheck(){
			var userDep=document.all['userDep'].value;
			if(userDep!='010000'){
				document.getElementById('xy').disabled=true;
			}else{
				document.getElementById('xy').disabled=false;
			}
		}
		function lrh_xyDisabled() {
			if (document.forms[0].userType.value == "xx") {
					document.getElementById('xydm').disabled = false;
			}
		}
	</script>
</head>
	<body onload="lrh_xyDisabled()">
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
			<script language="javascript" src="/xgxt/dwr/engine.js"></script>
			<script language="javascript" src="/xgxt/dwr/util.js"></script>
			<script language="javascript" src="js/lrh_new_js.js"></script>
			<html:form action="/xljk_stu_info" method="post">
			<input type="hidden" id="url" name="url"
					value="<bean:write name="url" scope="request"/>" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
				<div class="tab_cur">
					<p class="location">
						<a>学生信息</a>
					</p>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			            	<input type="hidden" name="go" value="a" />
			              <button class="btn_cx" id="search_go" 
			              	onclick="refreshForm('/xgxt/xljk_stu_info.do?act=stu_util&doType=stu_info&go=go');">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
									<html:select property="nj" style="width:90px"
									onchange="refreshForm('/xgxt/xljk_stu_info.do?act=stu_util&doType=stu_info')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('/xgxt/xljk_stu_info.do?act=stu_util&doType=stu_info')" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('/xgxt/xljk_stu_info.do?act=stu_util&doType=stu_info')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="left" nowrap>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
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
							</tr>
						</tbody>
					</table>
				
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			   

				<logic:notEmpty name="rs">
						 <table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
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
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="stu_send()">
									<logic:iterate id="v" name="s">
											<td align=center nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xljk_xlxhhy_form"></jsp:include>
						<script type="text/javascript">
										$('choose').className="hide";
						</script>
						<div id="tmpdiv"></div>
						<logic:present name="different">
						  <script>
						  		showNewStuStatusInfo(500,400);
						  </script>
						</logic:present>
					</fieldset>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>

 <%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			
		<script type="text/javascript">
			function sendYhm(){
				if(window.dialogArguments.document.getElementById('yhm')){
					window.dialogArguments.document.getElementById('yhm').value = curr_row.getElementsByTagName('input')[0].value;
					Close();
					window.dialogArguments.document.getElementById('disbutton').click();
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			
			<html:form action="/yhwhManage" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>用户查询</a>
				</p>
			</div>
			<div class="searchtab">
			    <table width="100%" border="0">
			      <tbody>
			        <tr>
			          <th>用户名</th>
			          <td>
			          	<html:text property="yhm" styleId="yhm" styleClass="text_nor"></html:text>
			          </td>
			          <th>姓名</th>
			          <td>
			          	<html:text property="xm" styleId="xm" styleClass="text_nor" ></html:text>
			          </td>
			          <th></th>
			          <td></td>
			         </tr>
			         <tr>
			          <th>所在部门</th>
			          <td colspan="4">
			          	<html:select property="szbm" style="width:150px">
			          		<html:option value=""></html:option>
			          		<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
			          	</html:select>
			          </td>
			          <td>
			          	<div class="btn">
			           		<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=getYhInfo');">
								查 询
							</button>
			            </div>
			          </td>
			        </tr>
			      </tbody>
			    </table>
			  </div>
			  <!--带有滚动条表单 内容块start-->
			<logic:empty name="rs">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
						<font color="red">未找到任何记录！</font> 
			    </span>
			    </h3>
			 </logic:empty>
			<logic:notEmpty name="rs">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序；</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }" onclick="tableSort(this)">
									${tit }
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="sendYhm();">
							<td>								
								<input type="checkbox" name="primarykey_cbv" id="pkV"
									value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								
							</td>
							<logic:iterate id="v" name="s" offset="0" length="3">
								<td>${v }</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglForm"></jsp:include>
				<!--分页显示-->
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
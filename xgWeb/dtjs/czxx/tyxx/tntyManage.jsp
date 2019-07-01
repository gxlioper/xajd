<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	</script>
	</head>
	
	<body onload="xyDisabled('xy');">
		<html:form action="/czxxDtjsTyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			
			<logic:empty name="msg">
				<div class="rightcontent">
						<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<logic:equal name="xxdm" value="12317">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/czxxDtjsTyxx.do?method=tntyUpdate',600,480);">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/czxxDtjsTyxx.do?method=tntyUpdate','update','600','480')">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/czxxDtjsTyxx.do?method=tntyManage','del')">删除</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
								<li><a href="#" class="btn_shtg" onclick="sumitInfo('/xgxt/czxxDtjsTyxx.do?method=tntyManage&shjg=通过','auditing')">通过</a></li>
								<li><a href="#" class="btn_shbtg" onclick="sumitInfo('/xgxt/czxxDtjsTyxx.do?method=tntyManage&shjg=不通过','auditing')">不通过</a></li>
							</ul>
						</div>
						</logic:equal>
						
						<!-- 非常州信息 -->
						<logic:notEqual name="xxdm" value="12317">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/czxxDtjsTyxx.do?method=tntyUpdate',600,480);">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/czxxDtjsTyxx.do?method=tntyUpdate','update','600','480')">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/czxxDtjsTyxx.do?method=tntyManage','del')">删除</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
							</ul>
						</div>
						</logic:notEqual>
						</div>
						</logic:equal>
<!--						=============================================-->
						<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/czxxDtjsTyxx.do?method=tntyManage');">
		              		查 询
		              		</button>
		              		 &nbsp;&nbsp;&nbsp;&nbsp;
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
		              	<tbody>
		              	<tr>
		              	    <th>学年</th>
							<td><html:select property="xn" style="width: 120px" styleId="xn"
											onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
							</td>
		              	    <th>年度</th>
							<td><html:select property="nd" style="width: 60px" styleId="nd"
											onchange="">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select></td>
							<th>学期</th>
							<td><html:select property="xq" style="width: 60px" onchange="" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select></td>
							
		              	</tr>
		         		<tr>
							<th>年级</th>
		              		<td><html:select property="nj" style="width: 60px" onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select></td>			
							<th>学号</th>
		         			<td><html:text property="xh" style="" maxlength="20" styleId="xh"/></td>
		         			<th>姓名</th>
		         			<td><html:text property="xm" style="" maxlength="20" styleId="xm"/></td>
		         		</tr>
		         		<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td ><html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
							<th>专业</th>
							<td><html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
							</html:select></td>
						    <th>班级</th>
		         			<td><html:select property="bjdm" style="width: 230px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select></td>	
		             	</tr> 	
		              	</tbody>
						</table>
						</div>
						
<!--						=============================================-->
				<div class="formbox">
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
			  <table summary="" class="dateline" width="100%">
			    <thead>
			      <tr>
			        <td>
						<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
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
			      <logic:iterate name="rs" id="s" indexId="index">
				      <tr onclick="rowOnClick(this);" style="cursor:hand" 
						ondblclick="showInfo('/xgxt/czxxDtjsTyxx.do?method=tntyUpdate','view','600','480')">
					<td align="center">
						<input type="checkbox" id="checkVal" name="checkVal" 
							value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
					</td>
					<logic:iterate id="v" name="s" offset="1">
						<td align="left">
						<bean:write name="v" />
						</td>
					</logic:iterate>
				    </tr>
			      </logic:iterate>
			    </tbody>
			  </table>
			  <!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
			  <!--分页显示-->
			  </logic:notEmpty>
</div></div>	
</logic:empty></html:form>
					
<!--					====================================================-->
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<logic:notEmpty name="error">
			<input type="hidden" id="error" value="${error}"/>
			<script>
				alert(document.getElementById('error').value);
			</script>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

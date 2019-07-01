<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
     function viewInfo(){
        var xh = curr_row.getElementsByTagName("input")[0].value;
        url = "/xgxt/XsgyglDispatch.do?method=viewGyLsSj&xh=";
        url += xh;     
        showTopWin(url,"800","500");
     }
    
   	function choiceDisabled(ele) {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			if (document.getElementById(tmp[i])) {
				document.getElementById(tmp[i]).disabled = true;
			}
		}
	}
	</script>	
	</head>

	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 住宿历史数据	</a>
			</p>
		</div>
		<!-- 标题 end-->
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/XsgyglDispatch.do?method=xsGyGL_LsSj" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()" 
								class="btn_dc">导出</a>
						</li>
						</logic:equal>	
					</ul>
				</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=xsGyGL_LsSj')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
						<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" 
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="width:100px" maxlength="20"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:80px" maxlength="20"></html:text>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm"  styleId="xy" style="width: 200px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									
									<logic:equal value="xy" name="userType">
										<input type="hidden" name="xydm" value="${userDep }"/>
										<script type="text/javascript">
											choiceDisabled('xy');
										</script>
									</logic:equal>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"  styleId="zy" style="width: 200px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm"  styleId="bj" style="width: 200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>						
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" style="width:80px" styleId="xb">	
									    <html:option value=""></html:option>									
										<html:option value="男">男</html:option>
						                <html:option value="女">女</html:option>
									</html:select>
								</td>						
								<th>
									<logic:equal value="10491" name="xxdm"><!-- 中国地质大学 -->
									标志
									</logic:equal>
								</th>
								<td>
									<logic:equal value="10491" name="xxdm"><!-- 中国地质大学 -->
										<html:select property="xsbz"  styleId="xsbz">	
										    <html:option value=""></html:option>									
											<html:option value="本校生">本校生</html:option>
							                <html:option value="非本校生">非本校生</html:option>
										</html:select>
									</logic:equal>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->		
				<!-- 查询结果-->
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
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->

								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="viewInfo()">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>		
	</body>
</html>

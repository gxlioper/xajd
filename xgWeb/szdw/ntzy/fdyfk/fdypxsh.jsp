<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/ntzy_fdyfk.do?method=fdypxsh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div id="btn" class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','ntzy_fdyfk.do?method=fdypxsh&shjg=tg&doType=sh&go=go','sh')">通过</a></li>
						<li><a href="#" class="btn_shbtg" onclick="batchData('primarykey_cbv','ntzy_fdyfk.do?method=fdypxsh&shjg=btg&doType=sh&go=go','sh')"">不通过</a></li>
						<li><a href="#" class="btn_sh" onclick="modi('ntzy_fdyfk.do?method=fdypxshone&doType=shone');">单个审核</a></li>
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" border="0" class="">
					<tbody> 
						<tr>
							<th>职工号</th>
							<td><html:text property="querylike_zgh" styleId="zgh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th>所在部门</th>
							<td><html:select property="queryequals_bmdm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>培训范围</th>
							<td><html:select property="queryequals_pxfw" styleId="pxfw">
								<html:option value="">--请选择--</html:option>
								<html:options collection="pxfwList" property="xmmc" labelProperty="xmmc"/>
							</html:select>
							</td>
							<th>培训类型</th>
							<td><html:select property="queryequals_pxlx" styleId="pxlx">
								<html:option value="">--请选择--</html:option>
								<html:options collection="pxlxList" property="xmmc" labelProperty="xmmc"/>
							</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" 
						onclick="allNotEmpThenGo('/xgxt/ntzy_fdyfk.do?method=fdypxsh&go=go')">
						查 询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重 置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</div>
				
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
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
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
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_fdyfk.do?method=fdypxview&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" 
										id="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=fdypxForm"></jsp:include>
				<!--分页显示-->
			</logic:notEmpty>
			
			</div>
			
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>

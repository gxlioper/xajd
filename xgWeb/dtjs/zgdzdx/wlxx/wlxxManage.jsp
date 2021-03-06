<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">	
	
	function updateWlxx(type){
		if(type == "add"){
			showTopWin('/xgxt/zgddWlxx.do?method=wlxxUpdate&doType='+type,550,450)
		}else if(type == "edit" || type == "view"){
			if(curr_row == null){
				alert('请选择要修改的信息!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/zgddWlxx.do?method=wlxxUpdate&doType='+type+'&pk='+pk,550,450)
			}
		}else if(type == "del"){
			if(curr_row == null){
				alert('请选择要删除的信息!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				alert(pk);
				//if (confirm("删除学号为"+pk+"的值班人信息，确认吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
					//showTips('处理数据中，请等待......');
					//refreshForm('/xgxt/zgddWlxx.do?method=wlxxManage&doType='+type+'&pk='+pk)
				//}
			}
		}
	}

	</script>
	</head>
	<body>
		<html:form action="/zgddZbr" method="post">
			<input type="hidden" name="num" id="num"
				value="<bean:write name="rsNum"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="gfsxx" id="gfsxx" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="rightcontent">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
						<div class="buttonbox">

							<ul>
								<li>
									<a href="#" class="btn_zj" onclick="updateWlxx('add')">增加</a>
								</li>
								<li>
									<a href="#" class="btn_xg" onclick="updateWlxx('edit')">修改</a>
								</li>
								<li>
									<a href="#" class="btn_sc" onclick="updateWlxx('del')">删除</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()">导入</a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()">导出</a>
								</li>
							</ul>
						</div>
					</div>
				</logic:equal>

				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zgddWlxx.do?method=wlxxManage')">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:80px"
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
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
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
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									值班时间
								</td>
								<td>
									<html:text property="zbsj" styleId="zbsj" style="width:95%"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('zbsj','y-mm-dd');" />
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline" summary="">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" ondblclick="updateWlxx('view')">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
											</logic:iterate>
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
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=zgdzdxDtjsForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

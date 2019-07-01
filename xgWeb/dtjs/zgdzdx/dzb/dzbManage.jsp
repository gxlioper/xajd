<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">	
	
	function updateDzb(type){
		if(type == "add"){
			showTopWin('/xgxt/zgddDzb.do?method=dzbUpdate&doType='+type,550,450)
		}else if(type == "edit" || type == "view"){
			if(curr_row == null){
				alert('请选择要修改的信息!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/zgddDzb.do?method=dzbUpdate&doType='+type+'&pk='+pk,550,450)
			}
		}else if(type == "del"){
			if(curr_row == null){
				alert('请选择要删除的信息!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				if (confirm("确认要删除该党支部吗？\n点击\"确定\"，删除数据；点击\"取消\"，将放弃删除！")) {
					showTips('处理数据中，请等待......');
					refreshForm('/xgxt/zgddDzb.do?method=dzbManage&doType='+type+'&pk='+pk)
				}
			}
		}
	}

	function tj(tableName){
		$("tableName").value=tableName;
		dataExport();
	}
	</script>
	<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<html:form action="/zgddZbr" method="post">
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" name="gfsxx" id="gfsxx" value=""/>
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
								<li><a href="#" class="btn_zj" onclick="updateDzb('add')">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="updateDzb('edit')">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="updateDzb('del')">删除</a></li>
								<li><a href="#" class="btn_sh" onclick="tj('view_zgdd_dytj')">党员统计</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="tj('view_zgdd_dzbjsb')">导出</a></li>
							</ul>
						</div>
						</div>
					</logic:equal>
					
					<div class="searchtab">
					<table width="100%" border="0" class="">
						<tfoot>
		        		<tr>
		          			<td colspan="8">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/zgddDzb.do?method=dzbManage')">
								查 询
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
									<bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width:180px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" style="width:180px" styleId="xy" >
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>			
								</td>
							</tr>
						</tbody>
					</table>
					</div>
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
					    		查询结果&nbsp;&nbsp;
									<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						    </span>
						 </h3>
						<table width="100%" id="rsTable" class="dateline">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="updateDzb('view')">
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
			   			  <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zgdzdxDtjsForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
					   </script>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
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

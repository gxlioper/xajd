<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript">
	       function yqzbManageAdd(){
	            var url = "/xgxt/zgdzdx_Gygl.do?method=yqzbManageAdd";
	            showTopWin(url,"600","300");
	       }
	       function yqzbManageModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            url +="&method=yqzbManageModi";	            
	            showTopWin(url,"600","300");
	           }
	       }
	     function yqzbManageView(){	           
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            url +="&method=yqzbManageModi";	
	            url +="&view=true";            
	            showTopWin(url,"600","300");	          
	       }
	       
	       function yqzbManageDel(){
	           if (curr_row == null) {
		          alert("请选要删除的记录！\n单击一行记录即可");
		          return false;
	           } else {
	              if(confirm("确定要删除该记录？")){
	                 var url = "/xgxt/zgdzdx_Gygl.do?method=yqzbManageDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	                
	                 refreshForm(url);
	              }else{	 
	                 return false;           
	            
	              }
	          }
	       }
	       
	</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 园区管理 - 园区值班情况 </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl?method=lcManager" method="post">

			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
						<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" 
									onclick="yqzbManageAdd()"
									class="btn_zj">增加</a>
							</li>	
							<li>
								<a href="#"
									onclick="yqzbManageModi()"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="yqzbManageDel()"
									class="btn_sc">删除</a>
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
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=yqzbManage')">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									园区
								</th>
								<td>
									<html:select property="yqdm" style="width:150px" styleId="yqdm" 
										onchange="getYqLdList()">
										<html:options collection="yqList" property="dm"
											labelProperty="mc" />
									</html:select>												
								</td>
								<th>
									值班人
								</th>
								<td>
									<html:text  property="xm" style="width: 80px" styleId="xm"/>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									值班时间（开始）
								</th>
								<td>
									<html:text  property="rzrq" styleId="rzrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 80px"
										onclick="return showCalendar('rzrq','y-mm-dd');" />											
								</td>
								<th>
									值班时间（结束）
								</th>
								<td>
									<html:text  property="lzrq" styleId="lzrq"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 80px"
										onclick="return showCalendar('lzrq','y-mm-dd');"  />		
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
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
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="yqzbManageView()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
	</body>
</html>

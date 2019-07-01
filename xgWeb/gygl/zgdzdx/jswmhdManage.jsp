<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>	
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 精神文明建设 - 精神文明活动 </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl" method="post">	
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />	
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="jswmhdAdd()"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="jswmhdModi()"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="jswmhdDel()"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
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
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=jswmhdManage')">
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
									年份
								</th>
								<td>
									<html:select property="nd">
								    	<html:options collection="ndList" property="nd" labelName="nd" />
								    </html:select>											
								</td>
								<th>
									活动名称
								</th>
								<td>
									<html:text  property="hdmc" style="width: 150px" styleId="hdmc"/>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									主办单位
								</th>
								<td>
									<html:text  property="zbdw" style="" styleId="zbdw"/>										
								</td>
								<th>
									活动内容
								</th>
								<td>
									<html:text  property="hdnr" style="" styleId="hdnr"/>				
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									活动时间(开始)
								</th>
								<td>
		                            <html:text property="hdksrq" styleId="hdksrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('hdksrq','y-mm-dd');"/>						
								</td>
								<th>
									活动时间(结束)
								</th>
								<td>
									<html:text property="hdjsrq" styleId="hdjsrq"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('hdjsrq','y-mm-dd');"/>				
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
								<tr align="center" style="cursor:hand" >
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
								ondblclick="jswmhdView()">
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
	<script type="text/javascript">
		   function jswmhdAdd(){
	            var url = "/xgxt/zgdzdx_Gygl.do?method=jswmhdAdd";
	            showTopWin(url,"670","500");
	       }
	       function jswmhdModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="method=jswmhdModi";
	            url +="&pkValue=";	
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;  	                      
	            showTopWin(url,"650","550");
	           }
	       }	
	       function jswmhdView(){	      
	            url="/xgxt/zgdzdx_Gygl.do?method=jswmhdView&pkValue=";	           	
	            url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;                
	            showTopWin(url,"650","550");          
	       }
	       function jswmhdDel(){
	           if (curr_row == null) {
		          alert("请选要删除的记录！\n单击一行记录即可");
		          return false;
	           } else {
	              if(confirm("确定要删除该记录？")){
	                 var url = "/xgxt/zgdzdx_Gygl.do?method=jswmhdDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	                
	                 refreshForm(url);
	              }else{	 
	                 return false;           
	            
	              }
	          }
	       }
	</script>
	 <logic:present name="notDone">
                   <script language="javascript">
                       alert("该活动已经上级审核过，不能进行操作！");
                   </script>
			  </logic:present>
</html>

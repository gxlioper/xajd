<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript">
		 	function zmPrint(){
		       if(curr_row == null ){
				  alert("请选择要操作的数据！\n单击一行记录即可");
				  return false;
			    } 
		       	var xh = (curr_row.cells[0].getElementsByTagName("input"))[0].value;
		       	url = "/xgxt/outPutstuManager.do?print=wszm&xh="+xh;
		       	showTopWin(url,'700','600');
		       //	showTopWin("/xgxt/","600","700");
		    }
		</script>
	</head>
	<body>
		<html:form action="/outPutstuManager" method="post">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="title" value="${title }"/>
			<input type="hidden" name="writeAble" value="${writeAble }"/>
            <input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
            <input type="hidden" name="userName" value="${userName }"/>
            <logic:present name="isxy">
            	<script defer="defer">
            		$('xy').disabled=true;
            	</script>
            	<input type="hidden" name="xydm" value="${userDep }"/>
            </logic:present>	    			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="viewInfo('add','/xgxt/stuOutputInfo.do')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('modi','/xgxt/stuOutputInfo.do')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('del','/xgxt/outPutstuManager.do')"
									class="btn_sc">删除</a>
							</li>
							<logic:present name="isNCDXKJXY">
							<li>
								<a href="#"
									onclick="zmPrint()"
									class="btn_qb">外宿证明</a>
							</li>
							</logic:present>
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
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value ='go';refreshForm('/xgxt/outPutstuManager.do')">
											查询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
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
									<html:select  property="nj"  styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>学年</th>
								<td>
									<html:select  property="xn"  styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>学期</th>
								<td>
									<html:select  property="xq"  styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm"  styleId="xy" style="width : 150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"  styleId="zy" style="width : 150px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="bjdm"  styleId="bj" style="width : 150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									外住类型
								</th>
								<td>
									<html:select  property="wzlxdm"  styleId="wzlxdm">
										<html:option value=""></html:option>
										<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
									</html:select>												
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text  property="xh" style="width: 80px" styleId="xh"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									姓名	
								</th>
								<td>
									<html:text  property="xm" style="width: 80px" styleId="xm" maxlength="10"/>								
								</td>
								<th>
									外住开始日期
								</th>
								<td colspan="3">
									<html:text  property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
									--
									<html:text  property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
										onclick="return showCalendar('jssj','y-mm-dd');"/>	
								</td>
							</tr>
							<logic:present name="showxbemy">
							<!-- 第四行 -->
							<tr>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" styleId="xb">
							    	<html:option value=""></html:option>
							    	<html:option value="男">男</html:option>
							    	<html:option value="女">女</html:option>
							    </html:select>					
								</td>
								<th>
									民族
								</th>
								<td>
									<html:select property="mz"  styleId="mz">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>				
								</td>
								<th>
									籍贯
								</th>
								<td>
									<html:text property="jg" styleId="jg" style="width:80px"></html:text>
								</td>
							</tr>
							</logic:present>
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
									ondblclick="viewInfo('view','/xgxt/stuOutputInfo.do')">
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
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
							  <script type="text/javascript">
						      $('choose').className="hide";
						     </script>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	

		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
</html>

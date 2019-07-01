<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl.do?method=getSsinfo" method="post">
		<logic:empty name="errinfo">
			<logic:notEmpty name="view">
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="stu" name="userType">
				<div class="buttonbox">
					<ul>
						
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/zjcmGygl.do?method=wsjcView','update','600','480')"
									class="btn_xg">入  住</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/zjcmGygl.do?method=wsjcManage','del')"
									class="btn_sc">查看现住宿舍信息</a>
							</li>		
					
					</ul>
				</div>
				</logic:equal>
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
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');">
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
									校区
								</th>
								<td>
									<html:select property="xiaoqu" styleId="xiaoqu" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="xiaoQList" labelProperty="mc"
											property="dm" />
									</html:select>												
								</td>
								<th>
									楼栋名称
								</th>
								<td>
									<html:select property="lddm" styleId="lddm" style="width:100px" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ldList" labelProperty="mc"
											property="dm" />
									</html:select>
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="cs" styleId="cs" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=getSsinfo');" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="lcList" labelProperty="mc"
											property="dm" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									收费标准
								</th>
								<td>
									<html:select property="sfbz" styleId="sfbz" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="sfbzList" labelProperty="mc"
											property="dm" />
									</html:select>													
								</td>
								<th>
									
								</th>
								<td>
									
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
								<tr style="cursor:hand" onclick="rowOnClick(this);oneOption();" ondblclick="sendinfo()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center" nowrap>
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
			</logic:notEmpty>
			</logic:empty>
			 <logic:empty name="view">
			<script language="javascript">
				alert('只有博士生才能选宿舍,\n\n博士生中定向、委托的学生不能选宿舍！');
			</script>
		</logic:empty>
		<logic:notEmpty name="errinfo">
		<script type="text/javascript">
			alert('<bean:write name="errinfo"/>');
		</script>
	</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script language="javascript">
				alert('                       入住成功！！！\n请通过查看现住宿舍信息按钮查看您宿舍信息');
				document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script language="javascript">
				alert('入住失败！');
				document.getElementById('search_go').click();
			</script>
		</logic:equal>

	</body>
</html>

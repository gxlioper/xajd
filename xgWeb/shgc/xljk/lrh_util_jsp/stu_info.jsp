<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/js/function.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script language="javascript" src="/xgxt/dwr/engine.js"></script>
		<script language="javascript" src="/xgxt/dwr/util.js"></script>
		<script language="JavaScript">
		function stu_send(){
			var xh=curr_row.cells[0].innerText;
			window.dialogArguments.refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=Insert&stu_id='+xh);
			window.close();
		}
	</script>
	</head>
	<body>
		<center>
			<html:form action="/xljk_xlxhhy" method="post">
				<div class="toolbox">			
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info');">
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
									年级
								</th>
								<td>
									<html:select property="nj" style="width:90px"
										onchange="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>									
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px"
										onchange="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info')" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:180px"
										onchange="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('/xgxt/xljk_xlxhhy.do?act=xljk_xlxhhy&doType=stu_info')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" />
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
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="stu_send()">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xljk_xlxhhy_form"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
			<logic:present name="different">
			<script>
				showNewStuStatusInfo(500,400);
			</script>
			</logic:present>
			</html:form>
		</center>
	</body>
</html>

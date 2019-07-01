<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
			function sendData() {
				var url = window.dialogArguments.document.getElementById('url').value;
				var xh = curr_row.cells[0].getElementsByTagName("input")[0].value;
				window.dialogArguments.document.forms[0].action = url+'&xh='+xh;
				window.dialogArguments.document.forms[0].submit();
				window.close();
			}
		</script>

	</head>
	<body>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>毕业生信息</a>
				</p>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('jygl.do?method=jyglData&doType=query')">
										查询
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
									</button>

								</div>
							</td>
						</tr>
					</tfoot>

					<tbody>
						<tr>
							<th>
								学号
							</th>
							<td>
								<html:text property="querylike_xsxh" maxlength="20" />
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_name" style="width:70px" />
								<input id="cgxys" name="cgxys" value="${cgxys }" type="hidden" />
							</td>
							<th>
								学生类别
							</th>
							<td>
								<html:select property="queryequals_xslb" style="width:80px">
									<html:option value=""></html:option>
									<html:option value="专科生">
										专科生
									</html:option>
									<html:option value="本科生">
										本科生
									</html:option>
									<html:option value="研究生">
										研究生
									</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								入学年度
							</th>
							<td>
								<html:select property="queryequals_nd" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								毕业年度
							</th>
							<td>
								<select name="queryequals_bynd" style="width:100px">
									<option value=""></option>
									<option value="2007">
										2007
									</option>
									<option value="2008">
										2008
									</option>
									<option value="2009">
										2009
									</option>
									<option value="2010">
										2010
									</option>
									<option value="2011">
										2011
									</option>
									<option value="2012">
										2012
									</option>
									<option value="2013">
										2013
									</option>
									<option value="2014">
										2014
									</option>
									<option value="2015">
										2015
									</option>
								</select>
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="formbox" id="result">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以选中；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
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
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendData();">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<bean:write name="v" />
											<input type="hidden" value="<bean:write name="v"/>" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<script type="text/javascript">
				      $('choose').className="hide";
				     </script>

					</fieldset>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

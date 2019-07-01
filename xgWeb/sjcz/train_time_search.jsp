<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	</head>
	<html:form action="/rcsw_pwgl" method="post">
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable }" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" id="userName" name="userName"
			value="${userName }" />
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble" scope="request">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showTopWin('/xgxt/rcsw_pwgl.do?method=ccUpdate','800','600');"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#"
								onclick="showInfo('/xgxt/rcsw_pwgl.do?method=ccUpdate','update','800','600')"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a href="#"
								onclick="deletedata('/xgxt/rcsw_pwgl.do?method=cccx&doType=del');"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a>
						</li>
						<li>
							<a href="#"
								onclick="expData('/xgxt/rcsw_pwgl.do?method=cccx&doType=expData');"
								class="btn_dc"> 导出 </a>
						</li>
					</ul>
				</div>
			</logic:equal>

			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/rcsw_pwgl.do?method=cccx&doType=query')">
										查 询
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
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
								车次
							</th>
							<td>
								<html:select property="queryequals_cc" style="width:150px"
									styleId="cc">
									<html:option value=""></html:option>
									<html:options collection="ccList" property="cc"
										labelProperty="cc" />
								</html:select>
							</td>
							<th>
								起点站
							</th>
							<td>
								<html:select property="queryequals_qdz" style="width:150px"
									styleId="qdz">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
							<th>
								终点站
							</th>
							<td>
								<html:select property="queryequals_zdz" style="width:150px"
									styleId="zdz">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								停靠站
							</th>
							<td>
								<html:select property="querylike_tkz" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czmc"
										labelProperty="czmc" />
								</html:select>
							</td>
							<td colspan="4"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="formbox">
			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
						<font color="red">未找到任何记录！</font>
					</logic:empty> <logic:notEmpty name="rs">
						<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
					</logic:notEmpty> </span>
			</h3>

			<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" name="cb" onclick="selectAll()"
										style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td id="${tit.en}" onclick="tableSort(this)" nowrap>
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="showInfo('/xgxt/rcsw_pwgl.do?method=ccUpdate','view','800','600')"
									style="cursor:hand;">
									<td align=center>
										<input type="checkbox" id="cbv" name="primarykey_cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
			</logic:notEmpty>
		</div>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
		</logic:present>
	</html:form>
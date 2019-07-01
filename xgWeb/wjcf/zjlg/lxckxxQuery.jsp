<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
	function sendinfo() {
		var pkValue = curr_row.getElementsByTagName("input")[0].value;
		window.close();
		window.dialogArguments.document.forms[0].action="wjcf_zjlg_addLxckxx.do?pkValue=" + pkValue;
		window.dialogArguments.document.forms[0].submit();
		return;
	}
</script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 解除留校察看 - 数据维护</a>
			</p>
		</div>


		<html:form action="/wjcfzjlgwh.do" method="post">
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />

			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm"></html:text>
								</td>
								<th>
									留校察看解除时间
								</th>
								<td>
									<html:text property="lxcksj" styleId="lxcksj"
										style="width:100px"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('wjcf_zjlg_lxckxxquery.do')">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<br/>
								<font color="blue">提示：默认查询出来的数据是留校察看时间为当前时间的数据;也可以在输入留校察看时间进行查询(年、月、日均可)</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
						<table class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="9">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onmousemove="rowOnClick(this)" style="cursor:hand;"
										align="center" ondblclick="sendinfo()">
										<input type="hidden" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="1" length="9">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
	</html>
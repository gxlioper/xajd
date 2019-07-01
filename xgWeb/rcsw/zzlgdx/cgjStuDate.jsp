<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
function modi(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=cgjsq&type=modi&pkVal="+guid;
    document.forms[0].submit();
}
function del(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=cgjDate&go=del&guid="+guid;
    document.forms[0].submit();
}
function add() {
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=cgjsq";
    document.forms[0].submit();
}
function query(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=cgjQuery&pkVal="+guid;
    document.forms[0].submit();
}
</script>
</head>
		<body onload="xyDisabled('xy');">
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 申请 - 出国(境)信息</a>
			</p>
		</div>
	
		<html:form action="/zzlgdx_rcsw.do?method=cgjDate" method="post">
		
		<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>

						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
									<td nowrap>
										操作：<input  value="申请" name="button_print" class="btn_01"
													onclick="add();" />
									</td>
								</tr>
							</thead>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr style="cursor:hand;"
										id=<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>>
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="xysh" name="s" offset="7" length="1">
											<td align=center>
												<bean:write name="xysh" />
											</td>
										</logic:iterate>
										<logic:iterate id="xyshyj" name="s" offset="8" length="1">
											<td align=center>
												<bean:write name="xyshyj" />
											</td>
										</logic:iterate>
										<logic:iterate id="xxsh" name="s" offset="9" length="1">
											<td align=center>
												<bean:write name="xxsh" />
											</td>
										</logic:iterate>
										<logic:iterate id="xxshyj" name="s" offset="10" length="1">
											<td align=center>
												<bean:write name="xxshyj" />
											</td>
										</logic:iterate>
										<td align=center>
											<logic:equal name="xysh" value="未审核">
												<logic:equal name="xxsh" value="未审核">
													<a onclick="javascript:modi(this);">修改</a>&nbsp;/&nbsp;
													<a onclick="javascript:del(this);">删除</a>&nbsp;/&nbsp;
												</logic:equal>
											</logic:equal>
											<a onclick="javascript:query(this);">查看</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
						</table>
				</div>
			</div>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		jQuery(function(){
			for(var i=0;i<jQuery("#listSize").val();i++){
				var len=jQuery("#bz"+i).html().length;
				var bzValue=jQuery("#bz"+i).html();
				if(len>12){
					jQuery("#bz"+i).html(bzValue.substr(0,11)+"...");
					}
				}
			});
		
		function queryXmXh(){
			var url ="qgzx_cjgl.do?method=cjxxCkByxhxm";
			var parameter={
				"xh":	jQuery("#xh").val(),
				"pkValue":jQuery("#pkValue").val()
			};
			jQuery.ajaxSetup({
				 contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});
			jQuery("#tbody_zgryxx").load(
				url,
				parameter,
				function(){}
			);
			
		}
		
		//导出Excel
		function exportExcel(){
			window.top.location.href = "qgzx_cjgl.do?method=cjxxExportExcel&pkValue="+'${pkValue}';
		}
		
		</script>
	</head>
	<body>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%>
			<input type="hidden" name="listSize" id="listSize" value="${rs.listSize }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<div style="width:100%;height:495px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学年<logic:equal value="xq" name="cs" property="qgzq">学期
								</logic:equal>
							</th>
							<td width="34%">
								${rs.xn}<logic:equal value="xq" name="cs" property="qgzq">&nbsp;&nbsp;&nbsp;${rs.xqmc}
								</logic:equal>
							</td>
							<th width="16%">
								用人部门
							</th>
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>
								发放年月
							</th>
							<td>
								${rs.ffny}
							</td>
							<th>
								提交状态
							</th>
							<td>
								${rs.tjztmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>酬金明细信息</span>
							</th>
						</tr>
						<tr>
					<td colspan="6">
						<font>&nbsp;&nbsp;学号/姓名</font>
						<input type="text" id="xh" name="xh" maxleng="20">
						<font>
						<button type="button" onclick="queryXmXh();return false;" class="btn_01">查询</button>
						</font>
					</td>
				</tr>
					</thead>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="cjmxHtml">
					<tbody>
						<tr>
							<td>无相关记录！</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="cjmxHtml">
					<thead>
						<tr>
							<td width='2%'>行号</td>
							<td width='11%'>学号</td>
							<td width='9%'>姓名</td>
							<td width='14%'>岗位名称</td>
							<td width='10%'>岗位性质</td>
							<td width='7%'>工时</td>
							<td width='5%'>金额</td>
							<td width='13%'>银行卡号</td>
							<td width='15%'>备注</td>
							<td width='14'>酬金发放人</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.cjmxHtml }
					</tbody>
					</logic:notEmpty>
				</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="xxdm" value="12389">
										<button type="button" onclick="exportExcel();return false;">
											导出Excel
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/tjgzpz/js/fbglgzpztj.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				belowTable("below");
				szyl();
				var pzgzid=jQuery("#pzgzid").val();
				//分班处理
				if(pzgzid=="FBGZ_PJFP"){
					
				}
			});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/fbglgzpztj">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
		<input type="hidden" id="pzgzid" value="${data.gzpztj.pzgzid}"/>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="2">
								<span>规则配置</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							规则名称
						</th>
						<td align="left">
							${data.gzpztj.pzgzmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							规则类型
						</th>
						<td align="left">
							${data.gzpztj.gzmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							启用状态
						</th>
						<td align="left">
							${data.gzpztj.qyztmc}
						</td>
					</tr>
				</tbody>
			</table>
		  <logic:iterate id="s" property="gzpztjxx" name="data">
				<table width="100%" border="0" class="formlist" >
						<thead>
								<tr>
									<th colspan="1" align="right">
										<span name="tjgzmc">${s.tjgzmc}</span>
										<div style="margin-left: 400px; font-weight:blod;" name="yl">预览：<font name="gzyl" color="red"></font><input type="hidden" name="tjgzid" value="${s.tjgzid}"/></div>
									</th>
								</tr>
						</thead>
						<tr>
							<td align="left">
								<logic:iterate id="t" property="tjxx" name="s" indexId="i">
									<span style="font-weight: bold;">
									${t.tjxz}
									</span><br/>
								</logic:iterate>
							</td>
						</tr>
					
				</table>
			</logic:iterate>
		</div>
		<div style="height: 40px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关  闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>

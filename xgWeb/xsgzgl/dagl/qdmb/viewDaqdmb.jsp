<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body >
		<html:form action="/daqdmb" method="post"  onsubmit="return false;" styleId="form">
			<div style='width:100%;height:400px;overflow-y:auto;overflow-x:hidden'>
				<table  border="0" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="3">
								<span>档案清单模板</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
							档案清单<br>模板名称
							</th>
							<td  colspan="2" >
								${rs.daqdmb_mc}
							</td>
						</tr>
						<tr>
							<th>
							启用状态
							</th>
							<td colspan="2">
								${rs.qyztmc}
							</td>
						</tr>
						<tr>
							<th>
							绑定材料<br><span class="red">(从左向后排序)</span>
							</th>
						<td colspan="2">
							<div class="tab_box">
								<div class="demo_college" style="height: 260px; width: 100%;overflow-y:aoto;*position:relative;*z-index:1">
									<div>
											<logic:iterate name="clList" id="c" >
												<logic:equal name="c" property="zt" value="1">
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;">
															${c.daqdcl_mc}
														</label>
													</li>
												</logic:equal>
											</logic:iterate>
									</div>
								</div>
							</div>
						</td>
					
					</tr>
					</tbody>
				</table>
			</div>
			<table border="0" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>

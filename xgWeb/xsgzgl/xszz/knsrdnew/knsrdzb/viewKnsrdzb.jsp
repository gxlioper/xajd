<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function autoSetZbnr(){
				jQuery("input[name=fzlx]").each(function(){
					var fzlxh=jQuery(this).next("input[name=fzlxH]").val();
					if(fzlxh=="1"){
						jQuery(this).click();
						jQuery(this).attr("checked","checked");
						
					}
				});
			}
			
			jQuery(document).ready(function(){
				autoSetZbnr();
			});
		</script>
	</head>
	<body >
		<html:form action="/xg_xszz_knsrd_knzbgl" method="post" styleId="knsrdzbForm" >
		<input type='hidden'  name='zbid'  value="${model.zbid}"/>
			<div style="">
				<!-- 提示信息 end-->	
				<table width="100%" border="0" class="formlist">
					
					<tbody id="tbody_jbxx">
						<tr>
							<th colspan="2">
								<font class="red">*</font><span>名称</span>
							</th>
							<td colspan="2" style='width:250px'>
							${model.zbmc}
							</td>
						</tr>
					</tbody>
				</table>
				
				</div>
				<div style="width:100%;overflow-x:hidden;overflow-y:auto;">
				
				
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="7%">
								<img src="images/knsrd/jiahao.gif" />
							</td>
							<td width="15%">
								<font class="red">*</font>困难指标
							</td>
							<td width="10%">
								<font class="red">*</font>权重比例
							</td>
							<td width="68%" >
								<font class="red">*</font>指标内容
							</td>
							
						</tr>
						<tbody id="tbody_knsrdzb">
						<logic:notEmpty name="object">
							<logic:iterate name="object" id="s" indexId="i">
							
							<tr > 
								<td width="7%">
								<img src="images/knsrd/jianhao.gif" />
								</td>
								<td width="20%" >&nbsp;${s.key.sxmc}</td>
								<td width="10%">&nbsp;${s.key.qzbl}%</td>
								<td width="63%">
									<table width="100%" border="0" class="datelist" id="knsrdzbnr" style="margin:2px auto;">
										<thead>
											<tr>
												<td width="7%">
													<img src="images/knsrd/jiahao.gif" />
												</td>
												<td width="15%">
													<font class="red">*</font>指标内容
												</td>
												<td width="10%">
													区间分
												</td>
												<td width="68%" >
													<font class="red">*</font>分值
												</td>
												
											</tr>
											<tbody id="tbody_knsrdzbnr">
												<logic:iterate name="s" id="list" indexId="j" property="value">
													<tr id="knsrdzbxxnr">
														<td width="15%">
														 <img src="images/knsrd/jianhao.gif" />
														</td>
														<td width="50%">&nbsp;${list.nrmc }</td>
														<td width="10%">
															<input type='checkbox'  name="fzlx" disabled="disabled"/>
															<input type="hidden" name="fzlxH" value="${list.fzlx}"/>
															<input type="hidden" name="fzH" value="${list.fz}"/>
														</td>
														<td width="25%" id="knfz">
														&nbsp;${list.fz}
														</td>
													</tr>
												</logic:iterate>
											</tbody>
										</thead>
									</table>
								</td>
							</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</thead>
				</table>
				
				</div>
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="Close();return false;">
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


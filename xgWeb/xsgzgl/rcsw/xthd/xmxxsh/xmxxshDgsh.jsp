<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var sqid = jQuery("#sqid").val();
			var splc = jQuery("#splc").val();
			var shid = jQuery("#shid").val();
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			var url = "rcsw_txhd_xmxxshgl.do?method=xmxxshDgsh&type=save";
			ajaxSubFormWithFun("xmxxshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
		</script>
	</head>
<body>
	<html:form action="/rcsw_txhd_xmxxshgl" method="post" styleId="xmxxshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="splc" styleId="splc"/>
		<html:hidden property="shid" styleId="shid"/>
		<html:hidden property="sqr" styleId="sqr"/>
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								项目名称
							</th>
							<td width="34%">
								${data.xmmc}
							</td>
							<th width="16%">
								活动时间
							</th>
							<td width="34%" >
								${data.hdkssj}至${data.hdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								活动类别
							</th>
							<td width="34%">
								${hdlbmc}
							</td>
							<th width="16%">
								活动地点
							</th>
							<td width="34%" >
								${data.hddd}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请人数上限
							</th>
							<td width="34%" >
								${data.sqrssx}
							</td>
							<th width="16%">
								审核人数上限
							</th>
							<td width="34%" >
								${data.shrssx}
							</td>
							
						</tr>
						<tr>
						<th align="right" width="10%">
						承办单位
						</th>
						<td align="left" colspan="3" >
							${data.cbdw}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动目的及意义
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动方案
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							授予学分
						</th>
						<td width="34%" >
							${data.syxf}
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th width="16%">
							活动说明
						</th>
						<td width="34%" colspan="3">
							${data.hdsm}
						</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>审核历史</span>
					</th>
				</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审核信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<tr>
					<th>
						<font color="red">*</font>审核结果
					</th>
					<td colspan="3" id="shjgSpan">
						
					</td>
				</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xthd&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveShzt();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#pzjf").val('${rs.zd3}');
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.xmid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""||jQuery("#pzjf").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "qgzx_kycxsjxmsh.do?method=sjDgsh&type=save";
		ajaxSubFormWithFun("SjxmglForm",url,function(data){
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}
	
	</script>
</head>
<body>
	<html:form action="/qgzx_kycxsjxmsh" method="post" styleId="SjxmglForm">
		<html:hidden name="rs" property="xmid" styleId="xmid"/>
		<html:hidden name="rs" property="splcid" styleId="splcid"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								项目编号
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目属性
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								项目所属单位
							</th>
							<td width="30%">
								${rs.ssdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目负责人学号
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								项目负责人姓名
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								联系方式
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								计划参与人数
							</th>
							<td width="30%">
								${rs.jhcyrs}
							</td>
							
						</tr>
						<tr>
							<th>
								开始时间
							</th>
							<td width="30%" >
								${rs.kssj}
							</td>
							<th width="20%">
								结束时间
							</th>
							<td width="30%" >
								${rs.jssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								经费预算
							</th>
							<td width="30%">
								${rs.jfys}
							</td>
																								
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>项目岗位
								<a  style="text-align: left;" onclick="showYsmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
								</span>
								
								
							</th>
						</tr>
					</thead>
					<tbody class="gwTbody">
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">岗位类型</th>
										<th style="text-align: center;">岗位工作摘要</th>
										<th style="text-align: center;">参与总人数</th>
									</tr>
									<logic:present name="gwList">
										<logic:iterate id="z" name="gwList">
											<tr>
												<td>${z.gwlbmc }</td>
												<td>${z.gwgzzy }</td>
												<td>${z.zcyrs }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="gwList">
											<tr>
												<td colspan="5">未找到任何记录！</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
				 </table>
				
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>审批信息</span>
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
							<font color="red">*</font>批准经费
						</th>
						<td colspan="3">
							<html:text property="pzjf" styleId="pzjf" maxlength="100" value="${rs.zd3}" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3')"></html:text>
						</td>
					</tr>
			</tr>
			<tr>
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan" colspan="3">
							
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sjsh&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
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

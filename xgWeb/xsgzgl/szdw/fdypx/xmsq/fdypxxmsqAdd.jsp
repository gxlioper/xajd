<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				//jQuery("#but_save").click(function(){save("fdypxxmSq")});
				//jQuery("#but_close").click();
				//jQuery("#xzpxxm").load("/xgxt/xsgzgl/szdw/fdypx/xmwh/fdypxxmxzView.jsp");
				jQuery("#xzpx").click(xzpxxm);
			});
			function go_list(){
				refreshForm("szdw_fdypxxmsq.do?method=fdypxxmsqList");
			}

			function savePxsq(obj){
				if(yanzheng()){
					
					jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{xmdm:jQuery("#xmdm").val()},function(data){
						if(data.message!="true"){
							alertInfo(data.message);
						}else{
							//验证成功后才能进行保存
							if(obj == "submit"){
								url = "szdw_fdypxxmsq.do?method=fdypxxmSq&type=submit";
							}else{
								url = "szdw_fdypxxmsq.do?method=fdypxxmSq&type=save";
							}
							ajaxSubFormWithFun("demoForm",url,function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
								}});
							});
						}
					},'json');
					
				}
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								职工号
							</th>
							<td width="34%">
							${rs.zgh }
							</td>
							<th width="16%">
								专兼职
							</th>
							<td width="34%" >
								${rs.zjz }
							</td>
						</tr>
						<tr>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
							${rs.xm}
							</td>
							<th width="16%">
								性别
							</th>
							<td width="34%" >
								${rs.xbs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								民族
							</th>
							<td width="34%">
							${rs.mzmc}
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%" >
								${rs.zzmmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系电话
							</th>
							<td width="34%">
							${rs.yddh}
							</td>
							<th width="16%">
								电子邮箱
							</th>
							<td width="34%" >
								${rs.dzyx}
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="3">
							${rs.jtzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								思政工作时间
							</th>
							<td width="34%">
							${rs.szgzsj}
							</td>
							<th width="16%">
								到校工作时间
							</th>
							<td width="34%" >
								${rs.lxgzsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								学历
							</th>
							<td width="34%">
							${rs.xl}
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%" >
								${rs.sxzy}
							</td>
						</tr>
						<tr>
							<th>
								毕业院校
							</th>
							<td colspan="3">
							${rs.byyx}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导员培训项目申请</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx2">
						<tr>
							<td colspan="4"><button class="btn_common" id="xzpx" type="button">选择培训项目</button></td>
						</tr>
						<tr>
							<td colspan="4" id="xzpxxm">
						
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxxs">
						<tr>
							<th width="16%">
								申请理由
							</th>
							<td width="84%" colspan="3">
								<html:textarea property="sqly" styleId="sqly" name="model" style="width: 98%;height: 50px;" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="savePxsq('save');return false;" >
										保存草稿
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" onclick="savePxsq('submit');return false;" >
										提交申请
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										关闭
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


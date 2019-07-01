<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.zzywid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});
			function saveShzt(){
				var shzt = jQuery("#shjg").val();
				var shyj = jQuery("#shyj").val();
				
				if(jQuery("#shjg").val() == "0"){
					showAlertDivLayer("请选择审核状态！");
					return false;
				}
			
				if (jQuery.trim(shyj) == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
				var message;
				if(jQuery("#shjg").val() == "1"){
					message = "通过";
				}
				if(jQuery("#shjg").val() == "2"){
					message = "不通过";
				}
				if(jQuery("#shjg").val() == "3"){
					message = "退回";
				}
				showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
					var url = "ttgl_stglsh.do?method=stzzDgsh&type=save";
					ajaxSubFormWithFun("stglshForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
				}});
		
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="stglshForm" action="/ttgl_stglsh"
			enctype="multipart/form-data">
		<html:hidden name="model" property="zzywid" styleId="zzywid"/>
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>学生组织信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">学生组织全称</th>
						<td width="30%">
								${rs.stqc }
						</td>
						<th width="20%">学生组织简称</th>
						<td width="30%">
								${rs.stjc }
						</td>
					</tr>
					<tr>
						<th width="20%">成立时间</th>
						<td width="30%">
								${rs.sqsj}
						</td>
						<th width="20%">组织人数</th>
						<td width="30%">
								${rs.strs}
						</td>
					</tr>
					<tr>
						<th>学生组织邮箱</th>
						<td>
								${rs.styx}
						</td>
						<th>学生组织公众号</th>
						<td>
								${rs.gzh}
						</td>
					</tr>
					<tr>
						<th>指导老师</th>
						<td>
								${rs.zdlsxm }
						</td>
						<th>指导单位</th>
						<td>
								${rs.bmmc }
						</td>
					</tr>
					<tr>
						<th>办公室地址</th>
						<td colspan="3">
								${rs.bgsdz}
						</td>
					</tr>
					<tr>
						<th>学生组织经费来源</th>
						<td colspan="3">
							<logic:iterate id="item" collection="${xszzjflyList}">
								<input type="checkbox" name="jflyArray" value="${item.dm}" disabled>${item.mc}
							</logic:iterate>
						</td>
						<script type="text/javascript">
                            jQuery(function(){
                                var r = '${rs.jfly}';
                                var result = r.split(",");
                                for(var i=0;i<result.length;i++){
                                    jQuery("input[value='"+result[i]+"'").attr("checked","checked");
                                }
                            })
						</script>
					</tr>
					<tr>
						<th>组织性质</th>
						<td colspan="3" id="stlx">
								${rs.stlx }
						</td>
					</tr>
					<tr id="ndzzzt" style="display:none">
						<th>年度组织状态</th>
						<td colspan="3">
								${rs.xn}
								${rs.ndzzztmc}
						</td>
					</tr>
					<tr>
						<th>组织类别</th>
						<td colspan="3">
								${rs.zzlbmc}
						</td>
					</tr>
					<tr>
						<th>学生组织宗旨</th>
						<td colspan="3">
								${rs.stjs }
						</td>
					</tr>
					</tbody>
					<thead>
						<tr class="h">
							<th colspan="4">
								<span>学生组织负责人</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='10%' style="text-align:center">学号</th>
											<th width='10%' style="text-align:center">姓名</th>
											<th width='10%' style="text-align:center">书院</th>
											<th width='10%' style="text-align:center">学院</th>
											<th width='10%' style="text-align:center">专业</th>
											<th width='10%' style="text-align:center">班级</th>
											<th width='10%' style="text-align:center">分组</th>
											<th width='10%' style="text-align:center">电话</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="fzrxxInfo" indexId="index">
											<tr name='deltr'>
												<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
												<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
												<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
												<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
												<td style='text-align:center'><label name = 'fz'>负责人</label></td>
												<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span>团支书</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr class="h">
						<th colspan="7">
							<table width="100%" >
								<thead>
								<tr>
									<th width='10%' style="text-align:center">学号</th>
									<th width='10%' style="text-align:center">姓名</th>
									<th width='10%' style="text-align:center">书院</th>
									<th width='10%' style="text-align:center">学院</th>
									<th width='10%' style="text-align:center">专业</th>
									<th width='10%' style="text-align:center">班级</th>
									<th width='10%' style="text-align:center">分组</th>
									<th width='10%' style="text-align:center">电话</th>
								</tr>
								</thead>
								<tbody id="tablebody2">
								<logic:iterate id="i" name="tzsxxInfo" indexId="index">
									<tr name='deltr'>
										<td style='text-align:center'><input name='tzsxh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
										<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
										<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
										<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
										<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
										<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
										<td style='text-align:center'><label name = 'fz'>团支书</label></td>
										<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
						</th>
					</tr>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<input type="hidden" id="fjid" value="${filepath}"/>
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    var gid = jQuery('#fjid').val();
                                    jQuery.MultiUploader_q({
                                        gid : gid
                                    });
                                });
							</script>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>转正申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">转正申请时间</th>
							<td colspan="3">
								${rs.zzsqsj }
							</td>
						</tr>
						<tr>
							<th>转正申请理由</th>
							<td colspan="3">
								${rs.zzly }
							</td>
						</tr>
					</tbody>
				</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
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
					<font color="red">限200字</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsxxxg&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
					</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
			</div>
		</html:form>
	</body>
	<script type="text/javascript">
        jQuery(function(){
            var ndzzzt = jQuery("#ndzzzt");
            if(jQuery("#stlx").html().trim() == "学生社团"){
                ndzzzt.removeAttr('style')
            } else {
                ndzzzt.attr('style','display:none');
            }
        })
	</script>
</html>

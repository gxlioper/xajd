<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbDgsh&type=save&gzzblx=bj";
			ajaxSubFormWithFun("xsgzzbshForm",url,function(data){
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
	<html:form action="/rcsw_xsgzzb_xsgzzbshgl" method="post" styleId="xsgzzbshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<html:hidden name="model" property="lrr" styleId="lrr"/>
		
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>周报信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th width="18%">学年</th>
							<td width="32%">
								${model.xn}
							</td>
							<th width="18%">学期</th>
							<td width="32%">
								${model.xqmc}
							</td>
					    </tr>
					    <tr>
							<th>年级</th>
							<td>
								${model.nj}
							</td>
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.xymc}
							</td>
					    </tr>
					    <tr>
							<th>专业</th>
							<td>
								${model.zymc}
							</td>
							<th>班级</th>
							<td>
								${model.bjmc}
							</td>
					    </tr>
					    </tr>
							<th>周次</th>
							<td>
								${model.zcmc}
							</td>
							<th>周次起止日期</th>
							<td>
								${model.zcksjsrq}
							</td>
					    </tr>
					    <tr>
							<th>
								应到人数
							</th>
							<td>
								${model.ydrs}
							</td>
							<th>
								实到人数
							</th>
							<td>
								${model.sdrs}
							</td>
			      		</tr>
					    <tr>
							<th>
								请假人数
							</th>
							<td>
								${model.qjrs}
							</td>
							<th>
								无故未到学生人数
							</th>
							<td>
								${model.wdrs}
							</td>
			      		</tr>
			      		<tr>
							<th>填写人</th>
							<td colspan="3">
								${model.lrrxm}
							</td>
					    </tr>
					    <tr>
							<th>
								本周对学生进行<br />讲评的主要内容
							</th>
							<td colspan="3">
							    ${model.zynr}
							</td>
			      		</tr>
					    <tr>
							<th>
								本周学生存在的<br />主要问题
							</th>
							<td colspan="3">
							    ${model.zywt}
							</td>
			      		</tr>
					    <tr>
							<th>
								您认为较合理的<br />解决对策
							</th>
							<td colspan="3">
							    ${model.jjdc }
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								附件
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${model.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
			      		</tr>
					</tbody>
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgzzb&id=shyj" />
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

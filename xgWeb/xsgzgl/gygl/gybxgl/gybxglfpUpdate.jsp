<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script	type="text/javascript">
	var xxdm="${xxdm}";
		jQuery(function(){
			// 保存按钮
			jQuery('#buttonSave').click(function(){
				var fpbmflag = jQuery("#fpbmth").css("display");
				
				var mustfill = ""
				if(fpbmflag != "none"){
					if(jQuery("#fpbm").val() == ""){
						 showAlert("分配部门不能为空！");
						 return false;
					}
					if(xxdm == "12688"){
						if(jQuery("#fpxq").val() == ""){
							 showAlert("分配校区不能为空！");
							 return false;
						}
					}
				}
				
				saveData('gyglnew_gybxgl.do?method=gybxglfpUpdate&doType=save',mustfill);
			});
			
			// 关闭按钮
			jQuery('#buttonClose').click(function(){
				Close();
			});	
			
			jQuery("#fpzt").change(function(){
				changeFpzt();
			});
			
			changeFpzt();
		});
		
		function changeFpzt(){
			
			var fpzt = jQuery("#fpzt").val();
			if("1"==fpzt){
				jQuery("#fpbmth").show();
				jQuery("#fpbmtd").show();
				if(xxdm == "12688"){
					jQuery("#fpxqth").show();
					jQuery("#fpxqtd").show();
				}
				
			}else{
				jQuery("#fpbmth").hide();
				jQuery("#fpbmtd").hide();
				jQuery("#fpbm").val("");
				if(xxdm == "12688"){
					jQuery("#fpxqth").hide();
					jQuery("#fpxqtd").hide();
					jQuery("#fpxq").val("");
				}
			}
		}
		
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		
		#jydw{
			width:88%;
		}
		
		textarea {
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/gyglnew_gybxgl" method="post">
		<input type="hidden" name="xh" value="${param.xh }"/>
		<input type="hidden" name="pk" value="${param.pk }"/>
		
		<div class="tab" style="width:100%;height:480px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>学号</th>
					<td>
						${rs.xh }
					</td>
					<th>姓名</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc }
					</td>
					<th>班级</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
				<th>住宿寝室</th>
				<td>
					<logic:notEqual value="" name="rs" property="ldmc">
						${rs.ldmc}，
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="qsh">
						${rs.qsh}，
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="cwh">
						${rs.cwh}号床
					</logic:notEqual>
				</td>
				<th>寝室电话</th>
				<td>${rs.qsdh}</td>
				</tr>	
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>报修信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				
				<tr>
					<th>紧急程度</th>
					<td>
						${rs.jjcd }
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh }
					</td>
					
				</tr>
				<tr>
				<th>报修类别</th>
					<td>
						${rs.bxlbmc }
					</td>
				<th>报修类别子项</th>
					<td>
						${rs.bxlbzxmc }
					</td>
				<tr>
					<th>期望维修时间</th>
					<td colspan="3">
						${rs.qwwxsj_ks } - ${rs.qwwxsj_js }
					</td>
				</tr>
				<tr>
					<th>报修内容</th>
					<td colspan="3" style="word-break:break-all;">
						${rs.bxnr }
					</td>
				</tr>
				<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${rs.filepath}";
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
						<span>处理信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>分配状态</th>
					<td>
						<html:select property="fpzt" value="${rs.fpzt}" styleId="fpzt">
							<html:option value="0">未分配</html:option>
							<html:option value="1">已分配</html:option>
						</html:select>
					</td>
					<th style="display:none" id="fpbmth"><span>*</span>分配部门</th>
					<td style="display:none" id="fpbmtd">
						<html:select property="fpbm" styleId="fpbm" value="${rs.fpbm}"  style="width:200px">
							<html:option value=""></html:option>
							<html:options collection="bmlist" labelProperty="bmmc" property="bmdm"/>
						</html:select>
					</td>
				</tr>
				<!-- 苏州卫生职业技术学院个性化 -->
				<logic:equal value="12688" name="xxdm">
					<tr>
						<th style="display:none" id="fpxqth"><span>*</span>分配校区</th>
						<td style="display:none" id="fpxqtd">
							<html:select property="fpxq" styleId="fpxq" value="${rs.fpxq}"  style="width:200px">
								<html:option value=""></html:option>
								<html:options collection="xqlist" labelProperty="xqmc" property="dm"/>
							</html:select>
						</td>
					</tr>
				</logic:equal>
			</tbody>
		</div>
		<table class="formlist">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" id="buttonSave">保存</button>
			          	</logic:notEqual>
			            <button type="button" id="buttonClose">关闭</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			
			 showAlert(jQuery('#message').val(),{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
		</script>
	</logic:present>
</body>
</html>

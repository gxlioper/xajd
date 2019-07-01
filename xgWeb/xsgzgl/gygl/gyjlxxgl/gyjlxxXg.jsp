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
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script	type="text/javascript">
		function getJllbList(){
			var jldldm=$("jldldm").value;
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:jldldm},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);
					jQuery("#jllbdm").val(jQuery("#jllbdm_value").val());			
			},'json');
		}
				
		function save(){
			if(jQuery('#xn').val()==""){
				alertInfo("请选择违纪学年！");
				return false;
			}
			if(jQuery('#xq').val()==""){
				alertInfo("请选择违纪学期！");
				return false;
			}
			if(jQuery('select[name=jldldm]').val()==""){
				alertInfo("请填写纪律大类！");
				return false;
			}
			if(jQuery('select[name=jllbdm]').val()==""){
				alertInfo("请填写纪律类别！");
				return false;
			}
			if(jQuery('#wjsj').val()==""){
				alertInfo("请填写违纪时间！");
				return false;
			}
			
			//var str = jQuery("#xh").val()+"!!@@!!"+jQuery("#ywjsj").val();
			var yjllbdm = jQuery("#jllbdm_value").val();
			saveData('gyglnew_gyjlgl.do?method=gyjlxxXg&doType=update&yjllbdm='+yjllbdm,'');
		}

		function modiInit(){
			var jldldm = jQuery("#jldldm").val();
			if(jldldm!="" && jldldm!=null){
				jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:jldldm},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);			
				},'json');
			}
		}
		
		jQuery(function(){
			getJllbList();
		})

	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post" enctype="multipart/form-data">	
		<input type="hidden" id="ywjsj" name="ywjsj" value="${rs.wjsj }"/>
		<input type="hidden" id="jlsj" name="jlsj" value="${rs.wjsj }"/>
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="xh" name="xh" value="${rs.xh }"/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<input type="hidden" id="mklx" value="gygl"/>
		<input type="hidden" id="xxdm" value="${xxdm}" />
		<div class="tab" style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="100%">	
			<thead>
				<tr>
					<th colspan="4">
					<span>学生信息</span>
					</th>
					</tr>
			</thead>
			<tbody>
					<tr>
						<th align="right" width="20%">
							学号
						</th>
						<td id="xh" align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							年级
						</th>
							<td align="left">
							${rs.nj}
						</td>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							专业
						</th>
							<td align="left">
							${rs.zymc}
						</td>
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>
							楼栋名称
						</th>
						<td>
							${rs.ldmc }
						</td>
						<th>
						寝室号
						</th>
							<td>
								${rs.qsh }
							</td>
					</tr>
					<tr>
						<th>
							床位号
						</th>
						<td>
							${rs.cwh}
						</td>
						<th>
							寝室电话
						</th>
						<td>
							${rs.qsdh}
						</td>
					</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>
					<logic:equal value="11799" name="xxdm">
						公寓奖惩信息
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">
						公寓纪律信息
					</logic:notEqual>
					</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>违纪学年
				</th>
				<td align="left" width="30%">
					<html:select name="rs" property="xn" styleId="xn" style="width:140px">
					<html:options collection="xnList" labelProperty="xn" property="xn"/>
					</html:select>
				</td>
					<th align="right" width="20%">
						<font color="red">*</font>违纪学期
					</th>
				<td align="left" width="30%">
					<html:select name="rs" property="xq" styleId="xq" style="width:140px">
					<html:option value=""></html:option>
					<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>
					<logic:equal value="11799" name="xxdm">
						奖惩大类
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">
						纪律大类
					</logic:notEqual>				
				</th>
				<td align="left" width="30%">
					<html:select name="rs" property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList()">
					<html:option value=""></html:option>
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<th align="right" width="20%">
					<font color="red">*</font>
					<logic:equal value="11799" name="xxdm">
						奖惩类别
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">
						纪律类别
					</logic:notEqual>			
				</th>
				<td align="left" width="30%" >
					<input type="hidden" id="jllbdm_value" value="${rs.jllbdm}"/>
					<html:select name="rs" property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:option value=""></html:option>	
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
					<th width="20%">
						<font color="red">*</font>违纪时间				
					</th>
					<td align="left" width="30%">
						<input type="hidden" name="wjsj" id="wjsj" value="${rs.wjsj }"/>
						${rs.wjsj }
					</td>
			</tr>
			 
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th width="20%">
						附件上传				
					</th>
					<td align="left" colspan="4">
						<html:hidden name="rs" property="fileid" styleId="fileid" />
								<input type="file" id="filepath_f" en name="fileid"  />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fileid',
			
											eid : 'filepath_f'
										});
									});
								</script>
					</td>
				</tr>
			</logic:equal>
			<tr>
					<th>
						备注
						<br /><font color="red">(限制在1000字内)</font>
					</th>
					<td colspan="4">
						<html:textarea name="rs" property='bz' style="width:650px;" styleId="bz" rows='4' onblur="chLeng(this,500);"/>
					</td>
			</tr>
			</tbody>
			</table>
		</div>
			<table class="formlist" width="100%">
			<tfoot>
			      <tr style="height:22px">
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button"  name="提交" id="buttonSave" onclick="save();return false;">保存</button>
			            <button type="button"  name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			 </tfoot>
			</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			showAlert('${message }',{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
			
		</script>
	</logic:present>
</body>
</html>

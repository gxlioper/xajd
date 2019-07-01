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
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	
	<script	type="text/javascript">
		function getJllbList(obj){
			<logic:equal value="12309" name="xxdm">
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
				var option = "";
				for(var i = 0; i < data.length; i++){
					option +="<input type='checkbox' name='jllbdm'  value='"+data[i].jllbdm+"'/>"+data[i].jllbmc;
				}
				jQuery('#jllbtd').html("").html(option);			
		},'json');
			return false;
			</logic:equal>
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);			
			},'json');
		}	
		
		function save(){
			
			if(jQuery('#wjxh').val()==""){
				alertInfo("请选择学号！");
				return false;
			}
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
			var jllbdm="";
			//武昌首义学院 个性化 纪律类别多选
			if(jQuery("#xxdm").val()=="12309"){
				jQuery("input[name=jllbdm]:checked").each(function(){
					jllbdm += jQuery(this).val()+ "," ;
				});
				if(jllbdm==""){
					alertInfo("请填写纪律类别！");
					return false;
				}
			} else {
				jllbdm = jQuery('select[name=jllbdm]').val();
			}
			var str = jQuery("#wjxh").val()+"!!@@!!";
			var len=jQuery("input[type=checkbox][name=xhs]:checked").length;
			if(len>=1){
				var array = jQuery("input[type=checkbox][name=xhs]:checked");
				
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parents("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
			}
			saveData('gyglnew_gyjlgl.do?method=gyjlxxZj&doType=save&wjxh='+str+'&jllbdm='+jllbdm,'');
		}
		function changeTqs(){
			var obj = document.getElementById("tqs");
			if(obj.checked){
				getTqs();
				jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
				jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
				jQuery("#xs_body").css("display","");
			}else{
				jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
				jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
				jQuery("#xs_body").css("display","none");	
				}
			}

		function getTqs(){
			jQuery.ajaxSetup({async:false});
			var xh = jQuery("#wjxh").val();
			if(null==xh||""==xh){
				return false;
			}
			jQuery.post('gyglnew_gyjlgl.do?method=getTqs',{xh:xh},function(data){
				
				if(data!=null && data.length!=0){
					var tr="";
					for(var i = 0; i < data.length; i++){
						tr +="<tr><td><input name='xhs' type='checkbox' value='"+data[i].xh+"'/></td>";
						tr +="<td>"+data[i].cwh+"</td>";
						tr +="<td>"+data[i].xh+"</td>";
						tr +="<td>"+data[i].xm+"</td>";
						var gllx = data[i].gllx;
						if(data[i].gllx == 'null' || data[i].gllx == null){
							gllx = "";
						}
						tr +="<td>"+gllx+"</td>";
						tr +="<td>"+data[i].nj+"</td>";
						tr +="<td>"+data[i].xymc+"</td>";
						tr +="<td>"+data[i].zymc+"</td>";
						tr +="<td>"+data[i].bjmc+"</td></tr>";
					}
					jQuery('#tbody_tqs').empty().append(tr);
				}else{
					jQuery('#tbody_tqs').empty().append("<tr><td colspan='8'><b>无同寝室学生！</b></td></tr>");
				}	
			},'json');
			jQuery.ajaxSetup({async:true});
		}

		//传媒个性化获取请假信息
		function getQjInfo(){
			var xh = jQuery("#wjxh").val();
			var wjsj = jQuery("#wjsj").val();
			var xxdm = jQuery("#xxdm").val();
			if(null==xh||""==xh||null==wjsj||""==wjsj||"11647"!=xxdm){
				jQuery("#qjInfo").css("display","none"); //如果学号或者卫生检查为空，请假信息隐藏
				return false;
			}
			
			jQuery.ajaxSetup({async:false});
			jQuery.post('gyglnew_gyjlgl.do?method=getQjInfo',{xh:xh,wjsj:wjsj},function(data){
				if(data!=null && data.length!=0){
					jQuery("#qjInfo").css("display","");
					var tr="";
					for(var i = 0; i < data.length; i++){
						tr +="<tr><td>"+data[i].qjkssj+"</td>";
						tr +="<td>"+data[i].qjjssj+"</td>";
						tr +="<td>"+data[i].qjlx+"</td></tr>";
					}
					jQuery('#qjInfo table td').remove();
					jQuery('#qjInfo table').append(tr);
				}else{
					jQuery("#qjInfo").css("display","none"); //如果没有请假信息也隐藏
				}	
			},'json');
			jQuery.ajaxSetup({async:true});
		}

		function sendXxGy(){
			var tableName=$("tableName").value;
			var url = "gyjl_gyjlglnew_gyjlxscx.do";
			//showTopWin(url,800,600);
			showDialog("查询学生公寓信息", 820, 550, url);
		}

		function getStuInfo(){
			jQuery.ajaxSetup({async:false});
			var parameter = {};
			parameter["xh"]=escape(jQuery("#wjxh").val());
			var url="gyjl_gyjlglnew_ajax.do?method=getStuInfo";
			jQuery.getJSON(url,parameter,
					function(data){
						if(data!=null){
							jQuery("#xm").html(data.xm);
							jQuery("#nj").html(data.nj);
							jQuery("#xymc").html(data.xymc);
							jQuery("#zymc").html(data.zymc);
							jQuery("#bjmc").html(data.bjmc);
							jQuery("#ldmc").html(data.ldmc);
							jQuery("#qsh").html(data.qsh);
							jQuery("#cwh").html(data.cwh);
							jQuery("#ssdh").html(data.qsdh);
							changeTqs();
							getQjInfo();
						}
					}
				);
			jQuery.ajaxSetup({async:true});
		}

		//全选
		function selectAll(obj){ 
			jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
		}
		//重庆邮电大学个性化
		if("${xxdm}" == "13627"){
			//日期选择控制在今天和前一天
			jQuery(function(){
				jQuery("#wjsj").removeAttr("onclick");
				jQuery("#wjsj").bind("focus",function(){
					WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${currDay}',minDate:'${lastDay}',onpicked: function (dp) { getQjInfo() }}); 
				})
			})
		}
		
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post" enctype="multipart/form-data">	
		<input type="hidden" id="xh" name="xh"　value=""/>
		<input type="hidden" name="url" id="url" value="gyglnew_gyjlgl.do?method=gyjlxxZj"/>	
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx"/>	
		<input type="hidden" id="count" value=""/>
		<input type="hidden" id="mklx" value="gygl"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;" >
		<table class="formlist" border="0" width="100%">	
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
							<font color="red">*</font>学号
						</th>
						<td align="left" style="width:35%">
							<html:text  property="xh" styleId="wjxh" value="${rs.xh}"  
							 maxlength="20" readonly="true"/>
						<button type="button" id="btn_getStuInfo" onclick="getStuInfo();" style="display: none;"></button>
							<button type="button" onclick="sendXxGy()"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left" id="xm">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							年级
						</th>
							<td align="left" id="nj">
							${rs.nj}
						</td>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left" id="xymc">
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							专业
						</th>
							<td align="left"  id="zymc">
							${rs.zymc}
						</td>
						<th align="right">
							班级
						</th>
						<td align="left" id="bjmc">
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>
							楼栋名称
						</th>
						<td id="ldmc">
							${rs.ldmc }
						</td>
						<th>
							寝室号
						</th>
						<td id="qsh">
							${rs.qsh }
						</td>
					</tr>
					<tr>
						<th>
							床位号
						</th>
						<td id="cwh">
							${rs.cwh }
						</td>
						<th>
							寝室电话
						</th>
						<td id="ssdh">
							${rs.ssdh}
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
					<html:select property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList(this)">
					<html:option value=""></html:option>
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<logic:notEqual value="12309" name="xxdm">
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
					<html:select property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:option value=""></html:option>	
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
				</logic:notEqual>
			</tr>
			<logic:equal value="12309" name="xxdm">
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>纪律类别				
				</th>
				<td align="left" colspan="4" id="jllbtd">
					<logic:iterate name="jllbList" id="jllb"  indexId="i" >
						<label><input type="checkbox" name="jllbdm" value="${jllb.jllbdm}"/>${jllb.jllbmc}</label>&nbsp;&nbsp;
					</logic:iterate>
				</td>
			</tr>
			</logic:equal>
			<tr>
					<th width="20%">
						<font color="red">*</font>违纪时间				
					</th>
					<td align="left" width="30%">
						<html:text property="wjsj" styleId="wjsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd','','','','',function(){getQjInfo()})"  readonly="true"></html:text>
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th width="20%">
						附件上传				
					</th>
					<td align="left" colspan="4">
						<html:hidden property="fileid" styleId="fileid" />
								<input type="file" id="filepath_f" name="fileid" />
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
			<tbody id="qjInfo" style="display:none">
				<tr>
					<td colspan="4">
						<table width="99%" style="text-align: center;">
							<tr>
								<th style="text-align: center;background-color:#ee6461">请假开始时间</th>
								<th style="text-align: center;background-color:#ee6461">请假结束时间</th>
								<th style="text-align: center;background-color:#ee6461">请假类型</th>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
			<tr>
					<th>
						备注
						<br /><font color="red">(限制在1000字内)</font>
					</th>
					<td colspan="4">
						<html:textarea property='bz' style="width:98%" styleId="bz" rows='3' onblur="chLeng(this,1000);"/>
					</td>
			</tr>
			<logic:notEqual value="11647" name="xxdm">
				<tr>
					<td colspan="5">
<%--					<span>公寓纪律信息</span>--%>
						<b>同寝室</b><input id="tqs" type="checkbox" onclick="changeTqs();"/>
						<font color="blue">提示：显示同寝室学生，增加同寝室相同公寓纪律.</font>
						
					</td>
				</tr>
			</logic:notEqual>
			</tbody>
			</table>
				<table id="xs_body" style="display: none;" border="0" width="100%" class="datelist">
					<thead>
						<tr>
							<th>
								<input name="th" type="checkbox" onclick="selectAll(this);"/>
							</th>
							<th>
								床位号
							</th>
							<th>
								学号
							</th>
							<th>
								姓名
							</th>
							<th>
								类型
							</th>
							<th>
								年级
							</th>
							<th>
								<bean:message key="lable.xb" />名称
							</th>
							<th>
								专业名称
							</th>
							<th>
								班级名称
							</th>
							
						</tr>
					</thead>
					<tbody id="tbody_tqs">
					</tbody>
			</table>
		</div>
			<div style="height: 50px"></div>
			<div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
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
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript" defer="defer">
			alertInfo('${message}', function(){
				Close();
			});
			
		</script>
	</logic:present>
	
</body>
</html>

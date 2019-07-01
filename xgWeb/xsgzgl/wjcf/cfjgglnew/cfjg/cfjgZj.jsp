<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script language="javascript" src="xsgzgl/wjcf/cfjgglnew/cfjg/js/cfjg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">

		//保存 
		function save(){
			var xh = jQuery("#xh").val();
			var cfyymc = jQuery("#cfyydm").val();
			var cflbmc = jQuery("#cflbdm").val();
			var cflbdm = jQuery("#cflbdm").val();
			var cfwh = jQuery("#cfwh").val();
			var cfsj = jQuery("#cfsj").val();
			var wjsj = jQuery("#wjsj").val();
            var sdlx = jQuery("#sdlx").val();
            var filepath = jQuery("#filepath").val();
            var filepath4 = jQuery("#filepath4").val();
			if(""==xh||""==cfyymc||""==cflbmc||""==wjsj||cfwh==""||cfsj==""||filepath==""||filepath4==""){
				alertError("请将带*的项目填写完整！");
				return false;
			}
			if(jQuery("#xxdm").val()=="70002"){
				if(!checkNotNull('cfyj-wjssjg')){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
			}
			/*if(jQuery("#xxdm").val()=="12872"){
				if (jQuery(".MultiFile-label").length == 0){		
					return showAlert("请将带<font color='red'>*</font>的项目填写完整");;
				}
			}*/
            if(jQuery("#xxdm").val()=="12389"){
                if (sdlx == ""){
                    return showAlert("请将带<font color='red'>*</font>的项目填写完整");;
                }
            }
			if(""!=wjsj&&cfsj!=""){
				if(new Date(cfsj)<(new Date(wjsj))){
					alertError("处分时间不能小于违纪时间！");
					return false;
				}
			}
			
			var cfdqsj = jQuery("#cfdqsj");
			if(cfdqsj.length>0){
				if(cfdqsj.val()==""){
					showAlertDivLayer("请填写处分到期时间！");
					return false;
				}
			}
			
			if("12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfsbgl.do?method=checkExistCfwh", {
					cfwh:cfwh
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("该处分文号已存在，请修改！");
					return false;
					}
				}

			// 验证处分在结果库当中是否存在 （验证条件：学号、处分类别、处分时间
			jQuery.post("wjcf_cfsbgl.do?method=checkExistCfjg", {
				xh:xh,
				cflbdm:cflbdm,
				wjsj:wjsj
			}, function(data) {
				if(data ==null || data){
					showAlert("该学生在"+wjsj+"的违纪已在处分结果中存在！");
					return false;
				}else{
				     var url = "wjcf_cfjg.do?method=saveCfjgZj";
				      ajaxSubFormWithFun("cfjgForm",url,function(data){
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
			},"json");
		}
		
		//显示处分期限
		function showCfqxFlag(cflbdm){
			//对于青岛酒店管理职业技术学院屏蔽该功能
			if(${xxdm=='13011'}) return false;
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		
		jQuery(function(){
			
			jQuery("#cflbdm").change(function(){
				defaultCfdqsj();
			});
			
			jQuery("#cfsj").change(function(){
				defaultCfdqsj();
			});
			
			//乌海职业学院个性化
			//勾选了处分原因后，处分依据自动填充所选的处分原因内容
			if(${xxdm=="13915"}){
				jQuery("#cfyydm").change(function(){
					jQuery("#cfyj").val(jQuery(this).find("option:selected").text());
				});
			}
		});
		
		</script>
		
	</head>
	<body >
		<html:form action="/wjcf_cfjg" method="post" enctype='multipart/form-data' styleId="cfjgForm">
					<input type="hidden" name="url" id="url" value="wjcf_cfjg.do?method=cfjgZj">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="message" id="message" value="${message }">
						
				    <input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
				    <input type="hidden" id="xxdm" value="${xxdm}"/>
					<input type="hidden" name="doType" id="doType"  >
					<html:hidden property="fjmc" styleId="fjmc"/>	
				<div  style="width:100%;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息</span>
								</th>
							</tr>
						</thead>
						<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
						<thead>
							<tr>
								<th colspan="4">
									<span>处分上报信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="16%">
							<font color="red">*</font>处分学年
						</th>
						<td align="left" width="34%">
							<html:select property="xn" styleId="xn" style="width:140px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
						</td>
						<th align="right" width="16%">
							<font color="red">*</font>处分学期
						</th>
						<td align="left" width="34%">
							<html:select property="xq" styleId="xq" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>处分原因
						</th>
						<td align="left">
							<html:select property="cfyydm" styleId="cfyydm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th align="right">
							<font color="red">*</font>处分类别
						</th>
						<td align="left">
							<html:select property="cflbdm" styleId="cflbdm" style="width:140px" onchange="showCfqxFlag(this.value);">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="dm" labelProperty="mc"/>
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>违纪时间
						</th>
						<td align="left">
							<html:text property="wjsj" styleId="wjsj"
								style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd',true,'cfsj');" />
						</td>
						<th align="right">
							上报人
						</th>
						<td align="left">
							${sbbxm }
						</td>
					</tr>
					
					<logic:equal value="12686" name="xxdm">
					<tr>
						<th align="right">
							<font color="red">*</font>年度
						</th>
						<td align="left" colspan="3">
							<html:select property="nd" styleId="nd" style="width:140px" onchange="initCfwh(this.value)">
								<html:options collection="xnndList" labelProperty="nd" property="nd"/>
							</html:select>
						</td>
					</tr>
					</logic:equal>
					
					<tr>
						<th align="right">
							<font color="red">*</font>处分文号
						</th>
						<td align="left">
							<html:text property="cfwh" styleId="cfwh" maxlength="30" ></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>处分时间
						</th>
						<td align="left">
							<html:text property="cfsj" styleId="cfsj"
								style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd',false,'wjsj','','',defaultCfdqsj);" />
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>送达类型
						</th>
						<td align="left" colspan="3">
							<select id="sdlx" name="sdlx">
								<option value=""></option>
								<option value="直接送达">直接送达</option>
								<option value="留置送达">留置送达</option>
								<option value="邮寄送达">邮寄送达</option>
								<option value="公告送达">公告送达</option>
							</select>
						</td>
					</tr>

					<tr id="cffw_tr3">
					</tr>
					
					<tr>
						<th align="right">
							<logic:equal name="xxdm" value="70002">
									<font class="red">*</font>
								</logic:equal>处分建议
							<br />
							<font color="red"><B>(限1000字)</B>
							</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea styleId="cfyj" styleId="cfyj" property='cfyj' style="width:600px"
								rows='4'  onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font class="red">*</font>
							学生检讨书
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<input type="file" id="filepath_f" name="filepath" />
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery('#filepath_f').multiUploader({
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'filepath',
                                        maxcount : '1',
                                        eid : 'filepath_f'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							考场违纪记录单
						</th>
						<td colspan="3">
							<html:hidden property="filepath2" styleId="filepath2" />
							<input type="file" id="filepath_f2" name="filepath2" />
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery('#filepath_f2').multiUploader({
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'filepath2',
                                        maxcount : '1',
                                        eid : 'filepath_f2'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							夹带纸条
						</th>
						<td colspan="3">
							<html:hidden property="filepath3" styleId="filepath3" />
							<input type="file" id="filepath_f3" name="filepath3" />
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery('#filepath_f3').multiUploader({
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'filepath3',
                                        maxcount : '1',
                                        eid : 'filepath_f3'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>
							申辩会议记录
						</th>
						<td colspan="3">
							<html:hidden property="filepath4" styleId="filepath4" />
							<input type="file" id="filepath_f4" name="filepath4" />
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery('#filepath_f4').multiUploader({
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'filepath4',
                                        maxcount : '1',
                                        eid : 'filepath_f4'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							<logic:equal name="xxdm" value="70002">
									<font class="red">*</font>
								</logic:equal>违纪事实经过
							<br />
							<font color="red"><B>(限1000字)</B>
							</font>
						</th>
						<td align="left" colspan="3" >
								<html:textarea styleId="wjssjg"  property='wjssjg' style="width:600px"
								rows='4'  onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							备注
							<br />
							<font color="red"><B>(限1000字)</B>
							</font>
						</th>
						<td align="left" colspan="3" >
								<html:textarea  property='bz' style="width:600px"
								rows='4' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>已受处分情况</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>处分类别</b></td>
										<td ><b>处分原因</b></td>
										<td ><b>处分时间</b></td>
										<td ><b>处分文号</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height: 22px">
										<td colspan="6" align="center">没有违纪记录</td>
									</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
			
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"    onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stsq/js/stgl.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				xsbjfj();
				jQuery("#fzrlb").change(function(){
					jQuery("#stfzrxm").val("");
					jQuery("#stfzr").val("");
				});
				jQuery("#selfzr").click(function(){
					var flag = fzrlbpd(jQuery("#fzrlb").val());
					if(flag == false){
						showAlert("请先选择负责人类别！");
						flag = null;
						return false;
					}
				});

				//添加联动方法
				jQuery("#stlbdm").change(function(){
					var stlbdm = jQuery("#stlbdm").val();
					var html = getXmlblist(stlbdm);
					jQuery("#xmlbdm").html(html);
				});
				//jQuery("#zdlszc").html("<option value='' selected='selected'></option>"+jQuery("#zdlszc").html());
				jQuery("#ssbm").html("<option value=''></option>"+jQuery("#ssbm").html());
			});
			function showSelYm(url,title){
				showDialog(title, 770, 520, url,{close:function(){
					if (jQuery("#fzrlb")){
						jQuery('#fzrlb').attr('disabled',false);
					}
				}});
			}

			//自动弹出负责人类别判断
			function fzrlbpd(value){
				jQuery('#fzrlb').attr('disabled',true);
				if(value == '老师'){
					url = "stglStsq.do?method=getTea";
					title = "老师";
					showSelYm(url,title)
				}else if(value == '学生'){
					url = "stglStsq.do?method=getStu";
					title = "学生";
					showSelYm(url,title)
				}else{
					return false;
				}
			}
			//选择指导老师
			function selectzdls(){
				url = "stglStsq.do?method=getTea&flag=selzdls";
				title = "老师";
				showSelYm(url,title)
			}
			//选择学生显示班级
			function xsbjfj(){
				var fzrlb=jQuery("#fzrlb").val();
				if(fzrlb=="学生"){
					document.getElementById("bjmctr").style.display = "";
				}else{
					document.getElementById("bjmctr").style.display = "none";
					jQuery("#xymc").html("");
					jQuery("#bjmc").html("");
				}
			}


			//增加行
            function addRowDialog(){
                var url = "stglStsq.do?method=getTea&flag=selzdls";
                var title = "指导老师选择";
                showDialog(title, 770, 550, url);
            }

			//删除行
            function delRow(){
                var obj = jQuery("[name='chk']:checked").parent().parent();
                if(obj.length == 0){
                    showAlert("请先选择指导老师信息，再进行删除操作！");
                    return false;
                }
                jQuery(obj).remove();
                jQuery("[name='chkall']").removeAttr("checked");
            }
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/stglStsq" method="post" styleId="StsqForm" onsubmit="return false;">
			<html:hidden property="jtr" styleId="jtr" value="${jtr}"/>
			<input hidden id="xxdm"  value="${xxdm }"/>
			<html:hidden property="xhs" styleId="xhs" value=""/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团项目名称
							</th>
							<td colspan="3">
								<html:text property="stxmmc" styleId="stxmmc" maxlength="50" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团类别
							</th>
							<td width="30%">
								<html:select property="stlbdm" styleId="stlbdm"  style="width:152px;" >
									<html:options collection="stlbList" property="stlbdm"
										labelProperty="stlbmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>项目类别
							</th>
							<td width="30%">
								<html:select property="xmlbdm" styleId="xmlbdm" style="width:152px">
									<html:options collection="xmlbList" property="xmlbdm"
										labelProperty="xmlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>有效学年
							</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" value="${mryxxn}" style="width:152px;">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>挂靠单位
							</th>
							<logic:notEqual name="xxdm" value="12872">
								<td width="30%">
									<html:select property="gkdw" styleId="gkdw" style="width:152px" >
										<html:options collection="bmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</logic:notEqual>
							<logic:equal value="12872" name = "xxdm">
								<td width="30%">
									<html:select property="gkdw" styleId="gkdw" style="width:152px" >
										<html:option value="校团委">校团委</html:option>
										<html:option value="人文社科部">人文社科部</html:option>
										<html:options collection="gkdwList" property="mc" labelProperty="mc" />
									</html:select>
								</td>
							
							</logic:equal>
							
						</tr>
						<!--
						<tr>
							<th width="20%">
								社团开始时间
							</th>
							<td width="30%">
								<html:text property="kssj" style="width:148px"
									onclick="return showCalendar('kssj','ymmdd',true,'jssj');" styleId="kssj" ></html:text>
							</td>
							<th width="20%">
								社团结束时间
							</th>
							<td width="30%">
								<html:text property="jssj" style="width:148px"
									onclick="return showCalendar('jssj','ymmdd',false,'kssj');" styleId="jssj" ></html:text>
							</td>
						</tr>
						  -->
						<tr>
							<th width="20%">
								<font color="red">*</font>负责人类别
							</th>
							<td width="30%">
								<html:select property="fzrlb" style="width:152px"  styleId="fzrlb"  onchange="xsbjfj();">
									<option value='老师'>老师</option>
									<option value='学生'>学生</option>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>社团负责人
							</th>
							<td width="30%">
								<input type="text" name="stfzrxm" style="width:100px;" id="stfzrxm" readonly="true" maxlength="10"/>
								<html:hidden property="stfzr" styleId="stfzr" />
								<button class="btn_01" id="selfzr" type="button">选择</button>
							</td>
						</tr>
						<tr id="bjmctr">
							<th width="20%">
								负责人所在学院
							</th>
							<td width="30%" id="xymc">
							
							</td>
							<th width="20%">
								负责人所在班级
							</th>
							<td width="30%" id="bjmc">
							
							</td>
						</tr>

					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>指导老师</span>
								<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRowDialog();return false;">增加</button>
								<button type="button" onclick="delRow();return false;">删除</button>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
										<tr>
											<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
											<th width="30%" style="text-align:left;"><font color="red">*</font>指导老师姓名</th>
											<th width="20%" style="text-align:left;"><font color="red">*</font>所属部门</th>
											<th width="20%" style="text-align:left;">联系电话</th>
											<th width="20%" style="text-align:left;">职称</th>
										</tr>
								</table>
							</div>
						</td>

					</tr>
						<tr>
							<th width="20%">
								社团联系电话
							</th>
							<td width="30%" >
								<html:text property="lxdh" style="width:148px" styleId="lxdh" maxlength="20"></html:text>
							</td>
							<th width="20%">
								建团人
							</th>
							<td width="30%">
								${jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团成立时间
							</th>
							<td width="30%">
								<html:text property="stclsj"  style="width:148px"
									onclick="return showCalendar('stclsj','y-mm-dd');" styleId="stclsj" ></html:text>
							</td>
							<th width="20%">
								申请时间
							</th>
							<td width="30%">
								<html:text property="sqsj" value="${sqsj}" style="width:148px"
									onclick="return showCalendar('sqsj','y-mm-dd');" styleId="sqsj" ></html:text>
							</td>

						</tr>
						<logic:equal value="12872" name = "xxdm">
							<tr>
								<th width="20%">
									<font color="red">*</font>星级
								</th>
								<td width="30%">
									<html:select property="stxj" styleId="stxj" style="width:152px" >
										<html:options collection="stxjList" property="xj"
													  labelProperty="xj" />
									</html:select>
								</td>
								<th width="20%">
								</th>
								<td width="30%">
								</td>
							</tr>
						</logic:equal>
						<tr id="fjtr">
							<th>
								申请计划书
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
	                               <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'fj'
											});
										});
											
								</script>
								
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团简介
								</br><font color="red">(限500字)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="stsm" styleId="stsm" 
											   onkeyup="checkzsinput_yz(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团获奖情况
								</br><font color="red">(限500字)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="sthjqk" styleId="sthjqk" 
											   onkeyup="checkzsinput_yz(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveStsq('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveStsq('submit');">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


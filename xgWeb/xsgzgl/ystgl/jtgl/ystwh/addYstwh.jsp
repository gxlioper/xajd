<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/jtgl/ystwh/js/ystwh.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			
			jQuery("#fzrlb").change(function(){
				jQuery("#stfzrxm").val("");
				jQuery("#fzr").val("");
			});
			jQuery("#selfzr").click(function(){
				var flag = fzrlbpd(jQuery("#fzrlb").val());
				if(flag == false){
					showAlert("请先选择负责人类别！");
					flag = null;
					return false;
				}
			});
			//jQuery("#zdlszc").html("<option value=''></option>"+jQuery("#zdlszc").html());
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
				url = "szdw_fdyjtff.do?method=showFdysAnother";
				title = "老师";
				showSelYm(url,title)
			}else if(value == '学生'){
				url = "xsxx_xsgl.do?method=showStudentsNotF5";
				title = "学生";
				showSelYm(url,title)
			}else{
				return false;
			}
		}
		//选择指导老师
		function selectzdls(){
			url = "szdw_fdyjtff.do?method=showFdysNotF5";
			title = "老师";
			showSelYm(url,title)
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ystglYstwh" method="post" styleId="YstwhForm" onsubmit="return false;">
			<html:hidden property="jtr" styleId="jtr" value="${jtr}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>艺术团项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>艺术团名称
							</th>
							<td colspan="3">
								<html:text property="ystxmmc" styleId="ystxmmc" maxlength="50" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>艺术团类别
							</th>
							<td width="30%">
								<html:select property="ystlbdm" style="width:152px;" styleId="ystlbdm" onchange='changeYstlb()'>
								<option value=''></option>
									<html:options collection="ystlbList" property="ystlbdm"
										labelProperty="ystlbmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>项目类别
							</th>
							<td width="30%">
								<html:select property="xmlbdm" styleId="xmlbdm" style="width:152px;">
									<html:options collection="xmlbList" property="xmlbdm"
										labelProperty="xmlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>联系电话
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh" maxlength="16"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>挂靠单位
							</th>
							<td width="30%">
								<html:select property="gkdwdm" styleId="gkdwdm" style="width:152px;" >
									<html:options collection="bmList" property="gkdwdm"
										labelProperty="gkdwmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>负责人类别
							</th>
							<td width="30%">
								<html:select property="fzrlb" styleId="fzrlb" style="width:152px;" >
									<option value='老师'>老师</option>
									<option value='学生'>学生</option>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>负责人
							</th>
							<td width="30%">
								<input type="text" name="stfzrxm" style="width:100px;" id="stfzrxm" readonly="true" maxlength="10"/>
								<html:hidden property="fzr" styleId="fzr" />
								<button class="btn_01" id="selfzr" type="button">选择</button>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>指导老师
							</th>
							<td width="30%">
							<input type="text" name="zdlsxm" style="width:100px;" id="zdlsxm" readonly="true" maxlength="10"/>
								<html:hidden property="zdls" styleId="zdls" />
								<button class="btn_01" id="selzdls" type="button" onclick="selectzdls();">选择</button>
							</td>
							<th width="20%" id="zdlszc_th"`>
								指导老师职称
							</th>
							
							<td id="zdlszc_td" >
							<input type="text" name="zcmc" id="zcmc" readonly="true"/>
								<html:hidden property="zdlszc" styleId="zdlszc"/>
							</td>
						</tr>
						<tr>
							<th>
								指导老师联系方式
							</th>
							<td>
								<html:text property="zdlslxfs" readonly="true" style="width:148px" styleId="zdlslxfs" maxlength="20"></html:text>
							</td>
							<th>
								所属部门
							</th>
							<td>
								<html:select property="ssbm" disabled="true" style="width:148px;" styleId="ssbm" >
									<html:options property="dm" labelProperty="mc" collection="ssbmlist" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>艺术团成立时间
							</th>
							<td width="30%">
								<html:text property="ystclsj"  style="width:148px"
									onclick="return showCalendar(this.id,'y-mm-dd');" styleId="ystclsj" ></html:text>
							</td>
							<th width="20%">
								申请时间
							</th>
							<td width="30%">
								<html:text property="sqsj" value="${sqsj}" style="width:148px"
									onclick="return showCalendar('sqsj','y-mm-dd');" styleId="sqsj" ></html:text>
							</td>						
							
						</tr>
						<tr>
							<th>
								<font color="red">*</font>入团审核流程
							</th>
							<td colspan="3">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								艺术团简介
							    </br><font color="red">(限500字)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="ystjj" styleId="ystjj" 
											   onkeyup="checkzsinput_yz(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								艺术团获奖情况
								</br><font color="red">(限500字)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="ysthjqk" styleId="ysthjqk" 
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
								<button type="button" onclick="saveYstwh('save');">
										保存
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
	</body><html:hidden property="jtr" styleId="jtr" value="${jtr}" />
</html>


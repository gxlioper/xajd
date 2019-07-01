<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script	type="text/javascript">
		function getXmmcList(obj,count){
			jQuery.post('xsxwkhJcfsb.do?method=getXmxxList&t='+Math.random(),{rcxwlbdm:obj.value},function(data){
				var xwdl="<select name='xwdldmArr' id='xwdl"+count+"'  onchange='getXwxlList(this,"+count+")' style='width:110px'>";
				var options =xwdl+ "<option value=''></option>";
				for(var i = 0; i < data.length; i++){
					options +="<option value='"+data[i].rcxwlbdldm+"'>"+data[i].rcxwlbdlmc+"</option>";
				}
				options += "</select>"
				jQuery('#xwdl_td'+count).html(options);			
			},'json');
		}
		
		function saveForm(obj){
			
			//先验证学号
			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			if (jQuery.trim(xh) == ""){
				showAlert("请先选择学生！");
				return false;
			}

			//行为类别
			var xwlbdmArr = document.getElementsByName("xwlbdmArr");
			var lbzsCount=0;//全部
			var lbckCount=0;//已选
			jQuery.each(xwlbdmArr,function(i,e){
				lbzsCount++;
				var xwlb=jQuery(e).attr("id");
				var rcxwlb = document.getElementById(xwlb);
				var yxz = rcxwlb.options.selectedIndex;
				if(yxz>0){
					lbckCount++;
				}
			});
			if(lbzsCount!=lbckCount){
				showAlert("请先选择行为类别！");
				return false;
			}		
			var fzArray=jQuery('input[name=fzArray]');//评定分值
			var fzsfgdArr=jQuery('input[name=fzsfgdArr]');//分值是否固定，gd则不需要进行大小判断
			var zgfzArr=jQuery('input[name=zgfzArr]');//最高分值
			var flag=true;//是否为空标识
			var fsflag=true;//是否在范围内标识
			jQuery.each(fzArray,function(i,e){
				if(jQuery(e).val()==''){
					flag=false;
					return false;
				}
				var sfgd=jQuery(fzsfgdArr[i]).val();
				if(sfgd!=null && sfgd=="zdy"){
					var zgfz = jQuery(zgfzArr[i]).val();
					var fz = jQuery(e).val();
					var arr = zgfz.split("!!");
					if(arr[0]!=arr[1]){
						if(parseFloat(fz)<parseFloat(arr[0])||parseFloat(fz)>parseFloat(arr[1])){
							fsflag=false;
							return false;
						}
					}
				}
			});
			if(!flag){
				showAlert("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
				return false;
			}
			var fssjArr=jQuery('input[name=fssjArr]');
			var sjflag=true;
			jQuery.each(fssjArr,function(i,e){
				if(jQuery(e).val()==''){
					sjflag=false;
					return false;
				}
			});
			if(!sjflag){
				showAlert("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
				return false;
			}
			if (jQuery.trim(xn) == ""){
				showAlert("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
				return false;
			}
			if(xwxlStr==null || xwxlStr.length<=0){
				showAlert("请增加至少一条行为信息！");
				return false;
			}
			jQuery.post("rcsw_rcxwwhnew_rcxwxxwhgl.do?method=rcxwxxSfcf",{xh:xh,xn:xn,xq:xq,xwxlStr:xwxlStr.toString(),fssjStr:fssjStr.toString()},function(data){
					if(data["message"]!=null  && data["message"]!="无"){
						showAlert(data["message"]);
						return false;
					}else{
						var url = "";
						if(obj == 'save'){
							 url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=addXwxx&type=save";
						}
						if(obj == 'submit'){
							 url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=addXwxx&type=submit";
						}
					      ajaxSubFormWithFun("rcxwxxwhForm",url,function(data){
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
			},'json');
				
		  }
			//行号
			var row_count = 0;
			//增加行 
			function addRow() 
			{
				var xh = jQuery('#xh').val();
				if (jQuery.trim(xh) == ""){
					showAlert("请先选择学生！");
					return false;
				}
				var xmInfo = jQuery('#xmInfo'); 
				var firstTr = xmInfo.find('tbody>tr:first'); 
				var row = jQuery("<tr></tr>"); 
				var td1 = jQuery("<td></td>"); 
				td1.append(jQuery("<input type='checkbox' name='count' value='New'>"));
				row.append(td1);
				//项目类型
				var td2 = jQuery("<td id='lx'></td>");
				var lx="";
				lx="<select name='lxArr' onchange='getXmmcList(this,"+row_count+")' id='lx"+row_count+"' style='width:110px'>";
				lx+="<option value=''></option><option value='1'>奖励分</option><option value='1'>处罚分</option>";
				lx+="</select>";
				td2.append(jQuery(lx)); 
				row.append(td2);
				//项目名称
				var td3 = jQuery("<td id='xmmc_td"+row_count+"'></td>");
				var xmmc="<select name='mcArr' id='mc"+row_count+"' style='width:110px'></select>";
				td3.append(jQuery(xmmc))
				);
				row.append(td3);
				//分值
				var td4 = jQuery("<td></td>"); 
				td4.append(jQuery("<div id='fzDiv"+row_count+"'></div>"));
				row.append(td4);
				//申报时间
				var td5 = jQuery("<td></td>"); 
				td5.append(jQuery("<input type='text' name='sbsjArr' id='sbsj" + row_count + "' style=\"width:80px\" readonly='true' value='${nowTime }' onclick=\"return showCalendar(this.id,'yyyy-MM-dd')\"/>") 
				);
				//备注
				var td6 = jQuery("<td></td>"); 
				td6.append(jQuery("<div id='bzDiv"+row_count+"'><input type='text' name='bzArr' id='bz" + row_count + "' style=\"width:80px\" maxlength='100'/></div>") 
				);
				row.append(td5);	
				row.append(td6);
				xmInfo.append(row); 
				row_count++; 
			}
			
			
			//删除行
			function delRow() 
			{
				var checked = jQuery("input[type='checkbox'][name='count']"); 
				jQuery(checked).each(function(){ 
					var qx=jQuery(this).attr("id");
					if("qx"!=qx){
						if(jQuery(this).prop("checked")==true) //注意：此处判断不能用jQuery(this).prop("checked")==‘true'来判断。
						{ 
							jQuery(this).parent().parent().remove(); 
						}
					}
				}); 
			}

			function SelectAll() {
				var checkboxs=document.getElementsByName("count");
					for (var i=0;i<checkboxs.length;i++) {
						var e=checkboxs[i];
						if(i==0){
							e.checked=e.checked;
						}else{
							e.checked=!e.checked;
						}
					}
				}
		</script>
		
	</head>
	<body>
		

		<html:form action="/xsxwkhJcfsb" method="post" styleId="rcxwxxwhForm" onsubmit="return false">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwhnew/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									奖惩分申报信息&nbsp;&nbsp;
									<a onclick="addRow();" href="javascript:void(0);">
									 	<img src="images/knsrd/jiahao.gif" />
									 </a>
									 &nbsp;&nbsp;
									 <a onclick="delRow();" href="javascript:void(0);">
									 	<img src="images/knsrd/jianhao.gif" />
									 </a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td style="width: 5%"><input type='checkbox' id="qx" name='count' onclick="SelectAll();"></td>
												<td style="width: 10%"><font color="red">*</font>项目类型 </td>
												<td style="width: 10%"><font color="red">*</font>项目名称 </td>
												<td style="width: 10%"><font color="red">*</font>分值 </td>
												<td style="width: 7%"><font color="red">*</font><font color="blue">申报时间</font></td>
												<td style="width: 15%"><font color="blue">备注</font></td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
					    </tr>
					  
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									
									<button id="btn_tj" type="button" type="button" onclick="saveForm('submit');">
										提交申请
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>


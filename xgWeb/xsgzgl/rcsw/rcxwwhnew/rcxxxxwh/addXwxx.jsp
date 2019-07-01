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
		jQuery(function(){
			var xxdm = jQuery("#xxdm").val();
			if(xxdm == "10504"){
				jQuery("#btn_tj").hide();
			}
		});
		function getXwdlList(obj,count){
			jQuery.post('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=getXwdlList&t='+Math.random(),{rcxwlbdm:obj.value},function(data){
				var xwdl="<select name='xwdldmArr' id='xwdl"+count+"'  onchange='getXwxlList(this,"+count+")' style='width:110px'>";
				var options =xwdl+ "<option value=''></option>";
				for(var i = 0; i < data.length; i++){
					options +="<option value='"+data[i].rcxwlbdldm+"'>"+data[i].rcxwlbdlmc+"("+data[i].ssxymc+")"+"</option>";
				}
				options += "</select>";
				jQuery('#xwdl_td'+count).html(options);			
			},'json');
		}
		function getXwxlList(obj,count){
			jQuery.post('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=getXwxlList&t='+Math.random(),{rcxwlbdldm:obj.value},function(data){
				var xwxl="<select name='xwxldmArr' id='xwxl"+count+"'  onchange='getXwxlxx(this,"+count+")' style='width:110px'>";
				var options =xwxl+ "<option value=''></option>";
				for(var i = 0; i < data.length; i++){
					options +="<option value='"+data[i].rcxwlbxldm+"'>"+data[i].rcxwlbxlmc+"</option>";
				}
				options += "</select>"
				jQuery('#xwxl_td'+count).html(options);			
			},'json');
		}
		
		
		function saveForm(obj){
			
			//先验证学号
			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var rcxwlbdldm = jQuery("#rcxwlbdldm").val();
			var rcxwlbxldm = jQuery("#rcxwlbxldm").val();
			var rcxwlbdm = jQuery("#rcxwlbdm").val();

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
			//行为大类
			var xwdldmArr = document.getElementsByName("xwdldmArr");
			var dlzsCount=0;//全部
			var dlckCount=0;//已选
			jQuery.each(xwdldmArr,function(i,e){
				dlzsCount++;
				var xwdl=jQuery(e).attr("id");
				var rcxwdl = document.getElementById(xwdl);
				var yxz = rcxwdl.options.selectedIndex;
				if(yxz>0){
					dlckCount++;
				}
			});
			if(dlzsCount!=dlckCount){
				showAlert("请先选择行为大类！");
				return false;
			}		
			//行为小类
			var xwxldmArr = document.getElementsByName("xwxldmArr");
			var zsCount=0;//全部
			var ckCount=0;//已选
			jQuery.each(xwxldmArr,function(i,e){
				zsCount++;
				var xwdl=jQuery(e).attr("id");
				var rcxwdl = document.getElementById(xwdl);
				var yxz = rcxwdl.options.selectedIndex;
				if(yxz>0){
					ckCount++;
				}
			});
			if(zsCount!=ckCount){
				showAlert("请先选择行为小类！");
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
			if(!fsflag){
				showAlert("评定分值必须在分值范围内！");
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
			if (jQuery.trim(xq) == ""){
				showAlert("请先选择学期！");
				return false;
			}

			//行为小类+发生时间，是否重复
			var sfcfCount = 0;
			var sfcf = true;
			var xwxlStr = "";
			var fssjStr = "";
			jQuery.each(xwxldmArr,function(i,e){
				if(i==(xwxldmArr.length-1)){
					xwxlStr += jQuery(e).val();
					fssjStr += jQuery(fssjArr[i]).val();
				}else{
					xwxlStr +=jQuery(e).val()+"!!";
					fssjStr += jQuery(fssjArr[i]).val()+"!!";
				}
				
				jQuery.each(xwxldmArr,function(j,f){
					if(jQuery(e).val()+jQuery(fssjArr[i]).val()==jQuery(f).val()+jQuery(fssjArr[j]).val()){
						sfcfCount++;
					}
				});
				if(sfcfCount>1){
					sfcf=false;
				}else{
					sfcfCount=0;
				}
			});
			if(!sfcf){
				showAlert("行为记录信息有重复记录，请确认！");
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
		  
		  function showXwlbxx(){
			  showDialog("选择行为类别",800,400,"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=getXwlbList");
		  }
		//判断填写分值是否超过范围
		  function checkValue(obj){
				var fz=jQuery(obj).val();
				var rcxwlbzdfz=jQuery(obj).nextAll('[name=rcxwlbzdfz]').val();
				var rcxwlbzgfz=jQuery(obj).nextAll('[name=rcxwlbzgfz]').val();
				if(parseFloat(fz)<parseFloat(rcxwlbzdfz)||parseFloat(fz)>parseFloat(rcxwlbzgfz)){
					showAlertDivLayer("分值必须在分值范围内！");
					jQuery(obj).val('');
					return false;
				}
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
				td1.append(jQuery("<input type='checkbox' name='count' value='New'>") 
				);
				row.append(td1);
				//行为类别
				var td20 = jQuery("<td id='xwlb'></td>");
				var xwlb="";
				jQuery.ajax({
					url:"rcsw_rcxwwhnew_rcxwxxwhgl.do?method=getXwlbList&t="+Math.random(),
					dataType:"json",
					type:"post",
					data:{},
					async:false,
					success:function(data){
						xwlb="<select name='xwlbdmArr' onchange='getXwdlList(this,"+row_count+")' id='xwlb"+row_count+"' style='width:110px'>";
						xwlb+="<option value=''></option>";
						if(data!=null && data.length>0){
							for(var i=0;i<data.length;i++){
								xwlb+="<option value='"+data[i].rcxwlbdm+"'>"+data[i].rcxwlbmc+"</option>";
							}
						}
						xwlb+="</select>";
					}
				});
				td20.append(jQuery(xwlb) 
				);
				row.append(td20);
				//行为大类
				var td2 = jQuery("<td id='xwdl_td"+row_count+"'></td>");
				var xwdl="<select name='xwdldmArr' id='xwdl"+row_count+"' onchange='getXwxlList(this,"+row_count+")' style='width:110px'></select>";
				td2.append(jQuery(xwdl)
				);
				row.append(td2);
				//行为小类
				var td3 = jQuery("<td id='xwxl_td"+row_count+"'></td>");
				var xwxl="<select name='xwxldmArr' id='xwxl"+row_count+"' onchange='getXwxlxx(this,"+row_count+")' style='width:110px'></select>";
				td3.append(jQuery(xwxl)
				);
				row.append(td3);
				//分值类型
				var td4 = jQuery("<td></td>"); 
				td4.append(jQuery("<div id='fzlxDiv"+row_count+"'></div>") 
				);
				row.append(td4);
				//分值
				var td5 = jQuery("<td></td>"); 
				td5.append(jQuery("<div id='fzqjDiv"+row_count+"'></div>") 
				);
				row.append(td5);
				//评值说明
				var td6 = jQuery("<td></td>"); 
				td6.append(jQuery("<div id='pzsmDiv"+row_count+"'></div>") 
				);
				row.append(td6);
				//评定分值
				var td7 = jQuery("<td></td>"); 
				td7.append(jQuery("<div id='fzDiv"+row_count+"'><input type='text' name='fzArray' id='fz" + row_count + "' style=\"width:50px\" onkeyup=\"checkInputNum(this)\" maxlength='8'/></div>") 
				);
				row.append(td7);
				//给分理由
				var td8 = jQuery("<td></td>"); 
				td8.append(jQuery("<div id='gflyDiv"+row_count+"'><input type='text' name='gflyArr' id='gfly" + row_count + "' style=\"width:80px\" maxlength='100'/></div>") 
				);
				row.append(td8);			
				//发生时间
				var td9 = jQuery("<td></td>"); 
				td9.append(jQuery("<input type='text' name='fssjArr' id='fssj" + row_count + "' style=\"width:80px\" readonly='true' value='${nowTime }' onclick=\"return showCalendar(this.id,'yyyy-MM-dd')\"/>") 
				);
				row.append(td9);
				if("13022"==jQuery("#xxdm").val() || "12942"==jQuery("#xxdm").val() || "10699"==jQuery("#xxdm").val()){
				var fileTd = jQuery("<td></td>");
				var fileInput = jQuery("<input style='width:100px;' onchange='checkFileType(this)' type='file' name='lbfj"+row_count+"'/>");
				fileTd.append(fileInput);
				row.append(fileTd);
				}
				
				xmInfo.append(row); 
				row_count++; 
			}
			
			function checkFileType(obj){
				var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
				var types = ["png","gif","jpg","jpeg","zip","rar","pdf","txt","doc","docx","xls","xlsx"];
				if (jQuery.inArray(type, types) == -1){
					showAlert("您所选择的文件类型不允许上传。");
					obj.value="";
				}
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
			function getXwxlxx(obj,count){
				jQuery.post('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=getXwxlxx',{rcxwlbxldm:obj.value},function(data){
						if(data!=null && data.length>0){
							var fzlx = data[0].rcxwfzlxmc;
							var fzqj = data[0].fzqj;
							var pzsm = data[0].rcxwlbbz;
							var pzsmsj = data[0].rcxwlbbzsj;//缩减
							var fzsfgd = data[0].fzsfgd;
							var zgfz = data[0].rcxwlbzgfz;
							var zdfz = data[0].rcxwlbzdfz;
							jQuery("#fzlxDiv"+count).text(fzlx);
							jQuery("#fzqjDiv"+count).text(fzqj);
							jQuery("#pzsmDiv"+count).html(pzsmsj!=null&&pzsmsj!=""?pzsmsj:"").attr("title",pzsm!=null&&pzsm!=""?pzsm.replace(new RegExp("<br/>","gm"),"\n"):"");
							if(fzsfgd!=null && fzsfgd=="gd"){
								jQuery("#fzDiv"+count).html(fzqj+"<input type='hidden' name='fzArray' id='fz" + row_count + "' value='"+fzqj+"'/><input type='hidden' name='fzsfgdArr' id='fzsfgd" + row_count + "' value='"+fzsfgd+"'/><input type='hidden' name='zgfzArr' id='zgfz" + row_count + "' value=''/>");
							}else {
								jQuery("#fzDiv"+count).html("<input type='text' name='fzArray' id='fz" + row_count + "' style=\"width:50px\" onkeyup=\"checkInputNum(this)\" maxlength='8'/><input type='hidden' name='fzsfgdArr' id='fzsfgd" + row_count + "' value='"+fzsfgd+"'/><input type='hidden' name='zgfzArr' id='zgfz" + row_count + "' value='"+zdfz+"!!"+zgfz+"'/>");
							}
						}
				},'json');
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
		

		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl" method="post" styleId="rcxwxxwhForm" enctype="multipart/form-data">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="rcxwjlsj"/>
			
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
									<logic:equal value="10704" name="xxdm">
										综合测评类别信息&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										行为类别信息&nbsp;&nbsp;
									</logic:notEqual>
									<%-- 
									<a onclick="showXwlbxx();" 
									   href="javascript:void(0);">
									   <font color="blue">选择行为类别</font>	
									</a>
									 --%>
									<a onclick="addRow();" href="javascript:void(0);">
									 	<img src="images/knsrd/jiahao.gif" />
									 </a>
									 &nbsp;&nbsp;
									 <a onclick="delRow();" href="javascript:void(0);">
									 	<img src="images/knsrd/jianhao.gif" />
									 </a>
									 <logic:equal value="13022" name="xxdm">
									 <font color="blue">(限附件格式为：png,gif,jpg,jpeg,zip,rar,pdf,txt,doc,docx,xls,xlsx; 最大5M)</font>
									 </logic:equal>
									 <logic:equal value="12942" name="xxdm">
									 <font color="blue">(限附件格式为：png,gif,jpg,jpeg,zip,rar,pdf,txt,doc,docx,xls,xlsx; 最大5M)</font>
									 </logic:equal>
									<logic:equal value="10699" name="xxdm">
										<font color="blue">(限附件格式为：png,gif,jpg,jpeg,zip,rar,pdf,txt,doc,docx,xls,xlsx; 最大5M)</font>
									</logic:equal>
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
												<td style="width: 5%"><input type='checkbox' id="qx" name='count' value='New' onclick="SelectAll();"></td>
												<td style="width: 10%"><font color="red">*</font><logic:equal value="10704" name="xxdm">综合测评类别</logic:equal><logic:notEqual value="10704" name="xxdm">行为类别</logic:notEqual></td>
												<td style="width: 10%"><font color="red">*</font><logic:equal value="10704" name="xxdm">综合测评大类</logic:equal><logic:notEqual value="10704" name="xxdm">行为大类</logic:notEqual></td>
												<td style="width: 10%"><font color="red">*</font><logic:equal value="10704" name="xxdm">综合测评小类</logic:equal><logic:notEqual value="10704" name="xxdm">行为小类</logic:notEqual> </td>
												<td style="width: 7%">分值类型 </td>
												<td style="width: 7%">分值 </td>
												<td>评分说明 </td>
												<td style="width: 7%"><font color="red">*</font><font color="blue">评定分值</font></td>
												<td style="width: 15%"><font color="blue">给分理由</font></td>
												<td style="width: 7%"><font color="red">*</font><font color="blue">发生时间</font></td>

												<logic:equal value="13022" name="xxdm">
												<td style="width: 15%"><font color="blue">附件</font></td>
												</logic:equal>
												<logic:equal value="12942" name="xxdm">
												<td style="width: 15%"><font color="blue">附件</font></td>
												</logic:equal>
												<logic:equal value="10699" name="xxdm">
													<td style="width: 15%"><font color="blue">附件</font></td>
												</logic:equal>
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
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <%--<tr>
							<th><span class="red">*</span>行为记录大类</th>
							<td>
								<html:select property="rcxwlbdldm" styleId="rcxwlbdldm" style="width:130px" onchange="getXwlbList(this)">
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdldm" labelProperty="rcxwlbdlmc" />
								</html:select>
							</td>
							<th>
							<span class="red">*</span>行为记录类别</th>
							</th>
							<td>
								<html:select property="rcxwlbdm" styleId="rcxwlbdm" style="width:130px">
								<html:option value=""></html:option>	
								<html:options collection="xwlbList" property="rcxwlbdm" labelProperty="rcxwlbmc" />
								</html:select>
							</td>
					    </tr>
					    
					    <tr>
							<th ><span class="red">*</span>行为记录时间</th>
							<td colspan="3">
								<!--<html:text property="rcxwjlsj" styleId="rcxwjlsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
								--!><html:hidden property="rcxwjlsj" />
								<bean:write name="rcxwxxwhForm" property="rcxwjlsj"/>
							
							</td>							
					    </tr>
					    --%>
					    <tr>
							<th>
							   	备注	
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
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


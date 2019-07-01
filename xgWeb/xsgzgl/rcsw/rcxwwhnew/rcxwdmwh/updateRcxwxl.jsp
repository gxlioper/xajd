<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script language="javascript">
		
		function saveForm(){
			 // var rcxwlbxldm=jQuery("#rcxwlbxldm").val();
			 // if(rcxwlbxldm==""){
				//  showAlert("请输入行为类别代码！");
				//	return false;
			 // }
			  var xxdm = jQuery("#xxdm").val();
			  
			  var rcxwlbxlmc=jQuery("#rcxwlbxlmc").val();
			  if(jQuery.trim(rcxwlbxlmc)==""){
				  if(xxdm == '10704'){
					  showAlert("请输入综合测评小类名称！");	
				  }else{
					  showAlert("请输入行为小类名称！");
				  }
					return false;
			  }
			  var rcxwlbdm=jQuery("#rcxwlbdm").val();
			  if(jQuery.trim(rcxwlbdm)==""){
				  if(xxdm == '10704'){
					  showAlert("请选择所属综合测评类别！");	
				  }else{
					  showAlert("请选择所属行为类别！");
				  }				  
					return false;
			  }
			  var rcxwlbdldm=jQuery("#rcxwlbdldm").val();
			  if(jQuery.trim(rcxwlbdldm)==""){
				  if(xxdm == '10704'){
					  showAlert("请选择所属综合测评大类！");	
				  }else{
					  showAlert("请选择所属行为大类！");
				  }				  
					return false;
			  }
			  var rcxwfzlx=jQuery("#rcxwfzlx").val();
			  if(jQuery.trim(rcxwfzlx)==""){
				  showAlert("请选择分值类型！");
					return false;
			  }
			  
			  var rcxwlbzdfz=jQuery("#rcxwlbzdfz").val();
			  var rcxwlbzgfz=jQuery("#rcxwlbzgfz").val();
			  if(jQuery.trim(rcxwlbzdfz)=="" && jQuery.trim(rcxwlbzgfz)==""){
				  showAlert("请输入分值区间！");
					return false;
			  }
			  if(jQuery.trim(rcxwlbzdfz)!="" && jQuery.trim(rcxwlbzgfz)!=""){
				  if(parseFloat(rcxwlbzdfz)>parseFloat(rcxwlbzgfz)){
					  showAlert("最低分值不能大于最高分值！");
						return false;
			      }
				  if(parseFloat(rcxwlbzdfz)==parseFloat(rcxwlbzgfz)){
					  showAlert("分值不能相同！");
						return false;
			      }
			  }
			  
		     var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwxl&type=update";
		      ajaxSubFormWithFun("rcxwdmwhForm",url,function(data){
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
		  function changeRcxwlbdldm(obj){
			  jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=queryRcxwlbdlListByLbdm",{rcxwlbdm:obj.value},function(data){
				  var html = '';
				  if(data && data.length > 0){
					  for(var i = 0; i < data.length; i++){
                          if(0 == i) changeSsxy(data[0].rcxwlbdldm);
						html += '<option value="'+data[i].rcxwlbdldm+'" ';
						if(data[i].rcxwlbdldm == '${rs.rcxwlbdldm}'){
							html += ' selected="selected" ';
						}
						html += ' >'+data[i].rcxwlbdlmc+'</option>';
					  }
				  }
				jQuery("#rcxwlbdldm").html(html);
			  },'json');
		  }
        function changeSsxy(value){
            jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=getSsxy",{rcxwlbdldm:value},function(data){
                jQuery("#ssxyTd").html(data);
            });
        }
		  jQuery(function(){
			  jQuery("#rcxwlbdm").val('${rs.rcxwlbdm}');
			  jQuery("#rcxwlbdm").change();
		  });
		</script>
	</head>
	<body >
		<input type="hidden" id="xxdm" value="${xxdm}" />
		<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			<html:hidden property="rcxwlbxldm"/>
				<div class="open_win01">
					<table align="center" class="formlist">
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="10704" name="xxdm">
										综合测评小类名称
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										行为小类名称
									</logic:notEqual>
								</th>
								<td colspan="3">
									<input type="text" id="rcxwlbxlmc" name="rcxwlbxlmc" maxlength="100" style="width: 99%;" value="${rs.rcxwlbxlmc }"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="10704" name="xxdm">
										所属综合测评类别
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										所属行为类别
									</logic:notEqual>									
								</th>
								<td>
									<select name="rcxwlbdm" id="rcxwlbdm" style="width: 150px;" onchange="changeRcxwlbdldm(this);" >
										<logic:iterate id="rcxwlbByYhsq" name="rcxwlbListByYhsq" >
											<option value="${rcxwlbByYhsq.rcxwlbdm }" title="${rcxwlbByYhsq.rcxwlbmc }">${rcxwlbByYhsq.rcxwlbmc }</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									<span class="red">*</span>分值类型
								</th>
								<td>
									<html:select property="rcxwfzlx" styleId="rcxwfzlx" style="width:150px;">
										<html:option value="01">加分</html:option>
										<html:option value="02">减分</html:option>
									</html:select>
								</td>

							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="10704" name="xxdm">
										所属综合测评大类
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										所属行为大类
									</logic:notEqual>
								</th>
								<td >
									<select name="rcxwlbdldm" id="rcxwlbdldm" style="width: 150px;" onchange="changeSsxy(this.value);">
									</select>
								</td>
								<th>
									所属学院
								</th>
								<td id="ssxyTd">

								</td>
							</tr>
							<tr>

								<th>
									<span class="red">*</span>分值
								</th>
								<td colspan="3">
									<input type="text" name="rcxwlbzdfz" id="rcxwlbzdfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px" value="${rs.rcxwlbzdfz }"/>&nbsp; - &nbsp;
									<input type="text" name="rcxwlbzgfz" id="rcxwlbzgfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px" value="${rs.rcxwlbzgfz }"/>
									<span class="red">若只设任意1个值，则分值为固定值</span>
								</td>
							</tr>
							<tr>
								<th>评分说明<br/><span class="red">（限500字）</span></th>
								<td colspan="3">
									<textarea name="rcxwlbbz" id="rcxwlbbz" rows="4" cols="3" style="width:99%" onblur="checkLen(this,500);" >${rs.rcxwlbbz }</textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="saveForm();return false;">
											保 存
										</button>
										<button type="button" name="取消" onclick="iFClose();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			
		</html:form>
	</body>
</html>


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
            var xxdm="${xxdm}";
		function updateForm(){
			
			
			  var rcxwlbdlmc=jQuery("#rcxwlbmc").val();
			  if(jQuery.trim(rcxwlbdlmc)==""){
                  var msg = "请输入行为类别名称！";
                  if("13431" == xxdm) msg = "请输入加分类别名称！";
                  showAlert(msg);
					return false;
			  }
			  
			  var rcxwlbzdfz=jQuery("#rcxwlbzdfz").val();
			  var rcxwlbzgfz=jQuery("#rcxwlbzgfz").val();
			  if(jQuery.trim(rcxwlbzdfz)=="" && jQuery.trim(rcxwlbzgfz)==""){
                  var msg = "请输入行为类别分值区间！";
                  if("13431" == xxdm) msg = "请输入加分类别分值区间！";
                  showAlert(msg);
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
			  
			  var rcxwfzlx=jQuery("#rcxwfzlx").val();
			  if(jQuery.trim(rcxwfzlx)==""){
				  showAlert("请选择分值类型！");
					return false;
			  }
			  
			  var rcxwlbdlmc=jQuery("#rcxwlbdldm").val();
			  if(jQuery.trim(rcxwlbdlmc)==""){
                  var msg = "请选择所属行为大类！";
                  if("13431" == xxdm) msg = "请选择所属加分大类！";
                  showAlert(msg);
					return false;
			  }
			//allNotEmpThenGo('rcsw_rcxwwh_rcxwdmwhgl.do?method=rcxwdlManage&doType=add');
			
			 var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwlb&type=update";
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
		
		
	
		</script>
		
		
	</head>
	<body >
		<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			 <html:hidden property="rcxwlbdm"  styleId="rcxwlbdm"/>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:notEqual name="xxdm" value="13431">
										<span>行为类别名称</span>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13431">
										<span>加分类别名称</span>
									</logic:equal>
								</th>
								<td>
									<html:text property="rcxwlbmc" styleId="rcxwlbmc" maxlength="100" styleClass="text_nor" />
								</td>
								<th>
									<span class="red">*</span>
									<logic:notEqual name="xxdm" value="13431">
										<span>所属行为大类</span>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13431">
										<span>所属加分大类</span>
									</logic:equal>
								</th>
								<td>
									<html:select property="rcxwlbdldm" styleId="rcxwlbdldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="xwdlList" property="rcxwlbdldm" labelProperty="rcxwlbdlmc" />
									</html:select>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>分值类型
								</th>
								<td>
									<html:select property="rcxwfzlx" styleId="rcxwfzlx" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="01">加分</html:option>
										<html:option value="02">减分</html:option>
									</html:select>
								</td>
								<th>
									<span class="red">*</span>分值
								</th>
								<td>
									<html:text property="rcxwlbzdfz" styleId="rcxwlbzdfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"></html:text>&nbsp; - &nbsp;
									<html:text property="rcxwlbzgfz" styleId="rcxwlbzgfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"></html:text>
									<br/><span class="red">若只设任意1个值，则分值为固定值</span>
								</td>
							</tr>
							<tr>
								<th>评分说明<br/><span class="red">（限500字）</span></th>
								<td colspan="3">
									<html:textarea property="rcxwlbbz" styleId="rcxwlbbz" rows="4" cols="3" style="width:99%" onblur="checkLen(this,500);"></html:textarea>
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
										<button type="button" name="保存" onclick="updateForm();return false;">
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


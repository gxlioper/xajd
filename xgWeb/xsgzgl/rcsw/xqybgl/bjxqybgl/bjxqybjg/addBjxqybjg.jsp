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
		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>		
		<script	type="text/javascript">		
			
			function saveForm(type){
				
				var yf = jQuery("#yf").val();
				var bjmc = jQuery("#bjmc").val();
				var bygzkzqk = jQuery("#bygzkzqk").val();
				var xsgzrd = jQuery("#xsgzrd").val();
				var xssxdt = jQuery("#xssxdt").val();
				var xstsjgzjy = jQuery("#xstsjgzjy").val();	

				var checkids = "yf-bjmc-bygzkzqk-xsgzrd-xssxdt-xstsjgzjy";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}

				if (bygzkzqk.length > 1000){
					showAlert("本月工作开展情况最多1000字！");
					return false;
				}				 
				
				if (xsgzrd.length > 1000){
					showAlert("学生关注热点最多1000字！");
					return false;
				}				 
				
				if (xssxdt.length > 1000){
					showAlert("学生思想动态最多1000字！");
					return false;
				}
				
				if (xstsjgzjy.length > 1000){
					showAlert("学生诉求及工作建议最多1000字！");
					return false;
				}
				
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=addBjxqybjg&type="+type;
		      	ajaxSubFormWithFun("bjxqybjgForm",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybjggl" method="post" styleId="bjxqybjgForm">
			
			
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>月报信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
							<th align="right" width="20%">
									学年
							</th>
							<td align="left" width="30%" >
									${xn}
							</td>
							<th align="right" width="20%">
									学期
							</th>							
							<td align="left" width="30%">
									${xq}
							</td>
					    </tr>
					    <tr>
					    	<th align="right" width="20%">
									填写人
							</th>
							<td align="left" width="30%">
									${txr}						
							</td>
							<th align="right" width="20%">
								<span class="red">*</span>月份
							</th>
							<td >
									
								<html:text  property="yf" styleId="yf"   size="10"
									onclick="return showCalendar('yf','yyyy-MM');" 
									 readonly="true"></html:text>				    	
							</td>
					    </tr>
					   <tr>
							<th align="right" width="20%">
							<span class="red">*</span>班级
							</th>
							<td width="80%" colspan="3">
								<input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button" onclick="showDialog('请选择一个班级',800,500,'rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjManage');return false;">选择</button>
								<span id="bj_span"></span>
							</td>
					    </tr>
					    <tr>
							<th align="right" width="20%">
								<span class="red">*</span>本月工作开展情况
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bygzkzqk' style="width:98%" styleId="bygzkzqk" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>学生关注热点 
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xsgzrd' style="width:98%" styleId="xsgzrd" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>学生思想动态 
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xssxdt' style="width:98%" styleId="xssxdt" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>学生诉求及工作建议 
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xstsjgzjy' style="width:98%" styleId="xstsjgzjy" rows='5' onblur="checkLen(this,1000);"/>
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
										保存
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


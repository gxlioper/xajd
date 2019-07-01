<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		function saveForm(){
								
			var fxsj = jQuery("#fxsj").val();		
			if (jQuery.trim(fxsj) == ""){
				showAlert("请先选择返校时间！");
				return false;
			}
			var url = "rcsw_jqfxFxwh.do?method=addxsJqfx&type=save";
		    ajaxSubFormWithFun("rcsw_jqfxFxwhForm",url,function(data){
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
		  jQuery(function(){
		      if(jQuery("#fxzt").val() == "0"){
                  jQuery("#bz").val("");
                  jQuery("#fxsj").val("")
			  }
		  })
		</script>

	</head>
	<body>

		<html:form action="/rcsw_jqfxFxwh" method="post"
			styleId="rcsw_jqfxFxwhForm">
			<input type="hidden" id="xn" name="xn" value="${xn}"/>
			<input type="hidden" id="xq" name="xq" value="${xq}"/>
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="fxzt" styleId="fxzt"/>		
			<%--<div style="height:400px;overflow-x:hidden;overflow-y:auto;">	
			--%><table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>										
				
					<thead>
						<tr>
							<th colspan="4">
								<span>返校信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
							<th><span class="red"></span>学年/学期</th>
							<td>							
								${xn}/${xq}								 
							</td>
							<th><span class="red"></span>返校状态</th>
							<td>
								${fxztmc}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>返校时间</th>
							<td >
							<html:text property="fxsj" styleId="fxsj" style="width:130px" onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm')"  readonly="true"></html:text>
							</td>
							<th><span class="red"></span></th>
							<td>
							
							</td>
					    </tr>		
					    <tr>
							<th>备注<font color="red">&lt;限500字&gt;</font></th>
							<td colspan="4">
								<html:textarea rows="3" style="width:98%"  styleId="bz"  property="bz" onblur="chLeng(this,500)"/>
							</td>
					    </tr>					   					  
					</tbody>
					
					
				
				</table>	
				<%--</div>							
				--%><table width="100%" border="0" class="formlist" style="">
							<tfoot>
								<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										<logic:equal name="xxdm" value="13100">
											<input type="hidden" name="tbsj" id="tbsj" value="${tbsj}" />
										</logic:equal>
										保 存
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


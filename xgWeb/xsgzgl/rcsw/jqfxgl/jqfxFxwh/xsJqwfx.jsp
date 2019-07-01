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
						
			//先验证学号
			var wfxyy = jQuery("#wfxyy").val();	
			var sfqdlx = jQuery("#sfqdlx").val();
			var yjfxsj = jQuery("#yjfxsj").val();	
			if (jQuery.trim(wfxyy) == ""){
				showAlert("请先输入未返校原因！");
				return false;
			}
			if(sfqdlx == "是"){
				if(yjfxsj == ""){
					showAlert("请先输入预计返校日期！");
					return false;
				}
			}
			var url = "rcsw_jqfxFxwh.do?method=addxsJqwfx&type=save";
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
		function hid(){
			var sfqdlx = jQuery("#sfqdlx").val();
			if(sfqdlx == "否"){
				jQuery("#yjfxsj").val("");
				jQuery("#yjfxsjTh").hide();
				jQuery("#yjfxsj").hide();
			}else{
				jQuery("#yjfxsjTh").show();
				jQuery("#yjfxsj").show();
			}
		}
		jQuery(function(){
		    var now = new Date();
		    jQuery("#nowDate").val(now);
            if(jQuery("#fxzt").val() == "1"){
                jQuery("#bz").val("");
                jQuery("#yjfxsj").val("");
			}
			var sfqdlx = jQuery("#sfqdlx").val();
			if(sfqdlx == "否"){
				jQuery("#yjfxsj").val("");
				jQuery("#yjfxsjTh").hide();
				jQuery("#yjfxsj").hide();
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
			<table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>										
				</table>
				
			     <table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5" >
								<span>未返校信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>					
						<tr>
							<th width="16%">
								<font color="red"></font>返校状态
							</th>
							<td  id="shjgSpan" width="34%">
								${fxztmc}
							</td>
							<th  width="16%">
				        		<font color="red">*</font>未返校原因
				       		</th>
				     	    <td width="34%">
                                <html:select  property="wfxyy"  styleId="wfxyy">
                                    <html:option value=""></html:option>
                                    <html:options collection="wfxyyList" property="yydm" labelProperty="yymc" />
                                </html:select>
				       		</td>
						</tr>
				        <tr>
							<th>
								<font color="red"></font>是否取得联系
							</th>
							<td >
								<html:select  property="sfqdlx"  styleId="sfqdlx" style="width:100px;" onchange="hid();">
                                    <html:option value="是">是</html:option>
                                    <html:option value="否">否</html:option>
                                </html:select>
							</td>
							<th>
								<span  id="yjfxsjTh"><font color="red">*</font>预计返校日期</span>
							</th>
							<td id="yjfxsjTd">
								<html:hidden styleId="nowDate" property="nowDate" value="${nowDate}"/>
								<html:text property="yjfxsj" styleId="yjfxsj" style="width:130px;" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'nowDate',250,10);" />
							</td>
						</tr>
						 <tr>
							<th>
								备注<font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea rows="3" style="width:98%" styleId="bz"   property="bz" onblur="chLeng(this,500)"/>
							</td>
						</tr>
					</tbody>
					</table>
				<table width="100%" border="0" class="formlist" style="margin-top: 10px;">
						<tfoot>
							<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
									提交
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


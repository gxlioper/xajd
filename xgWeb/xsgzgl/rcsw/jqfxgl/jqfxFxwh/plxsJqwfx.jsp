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
		
		function saveForm1111(){
												
			var wfxyy = jQuery("#wfxyy").val();		
			if (jQuery.trim(wfxyy) == ""){
				showAlert("请先输入未返校原因！");
				return false;
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
		function hidTr(){
			if(jQuery("#sfqdlx").val() == "否"){
				jQuery("#yjfxsj").val("");
				jQuery("#yjfxsjTr").hide();
			}else{
				jQuery("#yjfxsjTr").show();
			}
		}

			
		function saveForm(){
				
			var wfxyy = jQuery("#wfxyy").val();	
			var sfqdlx = jQuery("#sfqdlx").val();	
			var yjfxsj = jQuery("#yjfxsj").val();	
			var bz = jQuery("#bz").val();
			if (jQuery.trim(wfxyy) == ""){
				showAlert("请先输入未返校原因！");
				return false;
			}	
			if(sfqdlx == "是"){
				if(jQuery.trim(yjfxsj) == ""){
					showAlert("请先输入预计返校时间！");
					return false;
				}
			}	
			var api = frameElement.api,W = api.opener;
			W.saveWfxplsh(wfxyy,sfqdlx,yjfxsj,bz);
			closeDialog();							
		}
		  
			
		
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
			<%--<div style="height:168px;overflow-x:hidden;overflow-y:auto;">									
			     --%><table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5" >
								<span>未返校信息</span>
							</th>
						</tr>
					</thead>					
					<tbody>											
						<tr>
       						<th width="40%">
				        		<font color="red">*</font>未报到原因
				       		</th>
				     	    <td coalpsn="3">
                                <html:select  property="wfxyy"  styleId="wfxyy" style="width:100px;">
                                    <html:option value=""></html:option>
                                    <html:options collection="wfxyyList" property="yydm" labelProperty="yymc" />
                                </html:select>
							</td>
				        </tr>
				        <tr>
       						<th >
				        		是否取得联系
				       		</th>
				     	    <td >
                                <html:select  property="sfqdlx"  styleId="sfqdlx" onchange="hidTr();" style="width:100px;">
                                    <html:option value="是">是</html:option>
                                    <html:option value="否">否</html:option>
                                </html:select>
							</td>
				        </tr>
				        <tr id="yjfxsjTr">
				        	<th >
				        		<font color="red">*</font>预计返校时间
				       		</th>
				     	    <td >
                                <%--<html:text styleId="yjfxsj" property="yjfxsj" 
                                	onclick="return showCalendar('yjfxsj','yyyy-MM-dd',true,'yjfxsj');"  readonly="true"></html:text>--%>
                                <html:text property="yjfxsj" styleId="yjfxsj" style="width:130px;" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm','','',250,10);"></html:text>	
							</td>
				        </tr>
				        <tr>
							<th>
								备注<font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea rows="3" style="width:98%"  property="bz" styleId="bz" onblur="chLeng(this,500)"/>
							</td>
						</tr>
					</tbody>
					</table>
			<%--</div>																	
			--%><table width="100%" border="0" class="formlist" style="margin-top: 10px;">
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


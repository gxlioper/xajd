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
				showAlert("��������δ��Уԭ��");
				return false;
			}
			var url = "rcsw_jqfxFxwh.do?method=addxsJqwfx&type=save";			
		    ajaxSubFormWithFun("rcsw_jqfxFxwhForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
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
			if(jQuery("#sfqdlx").val() == "��"){
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
				showAlert("��������δ��Уԭ��");
				return false;
			}	
			if(sfqdlx == "��"){
				if(jQuery.trim(yjfxsj) == ""){
					showAlert("��������Ԥ�Ʒ�Уʱ�䣡");
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
								<span>δ��У��Ϣ</span>
							</th>
						</tr>
					</thead>					
					<tbody>											
						<tr>
       						<th width="40%">
				        		<font color="red">*</font>δ����ԭ��
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
				        		�Ƿ�ȡ����ϵ
				       		</th>
				     	    <td >
                                <html:select  property="sfqdlx"  styleId="sfqdlx" onchange="hidTr();" style="width:100px;">
                                    <html:option value="��">��</html:option>
                                    <html:option value="��">��</html:option>
                                </html:select>
							</td>
				        </tr>
				        <tr id="yjfxsjTr">
				        	<th >
				        		<font color="red">*</font>Ԥ�Ʒ�Уʱ��
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
								��ע<font color="red">&lt;��500��&gt;</font>
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
									�ύ
									</button>				
									<button type="button" type="button" onclick="iFClose();">
									�� ��
									</button>
								</div>
							</td>
							</tr>
						</tfoot>
			</table>
		</html:form>
	</body>
</html>


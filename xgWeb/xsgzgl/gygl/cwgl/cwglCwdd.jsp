<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		function cwdd_submit(){
			if(!check("tsyy-tssj-xn-xq")){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}else{
				var idList = jQuery("#idList").val();

				var url = "gyglnew_cwgl.do?method=cwDd&go=go&doType=cwdd&idList="+encodeURI(encodeURI(idList));
			      ajaxSubFormWithFun("gyglnewCwglForm",url,function(data){
			    	 if(data["message"]=="对调成功！"){
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
		}

		/**
		 * 验证是否存在空项
		 * @param ids 要验证的控件id "-"分隔
		 * @return
		 */
		function check(ids){
			var id=ids.split("-");
			for(var i=0;i<id.length;i++){
				var lddm=jQuery("#"+id[i]).val();
				if(lddm==null||""==lddm){
					//alert(id[i]);
					return false;
				}
			}
			return true;
		}
		
		</script>
	</head>
	<body >

		<html:form styleId="gyglnewCwglForm" action="/gyglnew_cwgl" method="post">
			<input type="hidden" id="idList" name="idList" value="${idList}" />	
			
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>床位对调信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="16%">
									<span class="red">*</span>调整时间
								</th>
								<td>
									<html:text property="tssj" styleId="tssj" onkeypress="onlyBackSpace(this,event);" style="width:100px;"
										onclick="return showCalendar(this.id,'yyyy-MM-dd','','',210,10)" ></html:text>
								</td>
								<th align="right" width="16%">
									<span class="red">*</span>学年/学期
								</th>
								<td align="left">
									<html:select property="xn" styleId="xn" disabled="false">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
									</html:select>
									<html:select property="xq" styleId="xq" disabled="false" >
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr >
								<th >
									<span class="red">*</span>调整原因
								</th>
								<td colspan="3">
									<html:select property="tsyy" styleId="tsyy" >						
										<html:options collection="tsyyList" labelProperty="tzyymc" property="tzyydm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									备注
									<br /><font color="red">(限500字)</font>
								</th>
								<td colspan="3">
									<html:textarea property='bz' style="width:95%" styleId="bz" rows='4' onblur="checkLen(this,500);"/>
								</td>
							</tr>
							<tr>
								<th>
									注意
								</th>
								<td colspan="3">
									<div id="submit_bz" class="bz" style="color: red;"></div>
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
										<button type="button" name="保存"  onclick="cwdd_submit()">
											保存 
										</button>
										<button type="button" name="取消"  onclick="iFClose();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

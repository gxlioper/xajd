<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		//�رյ�����
		function divtmpclose() {
			parent.document.getElementById("tmpdiv1").innerHTML = "";
			try{
				parent.hiddenMessage(true,true);
			}catch(e){				
			}
		}
		function save(){
			var ssfj=jQuery("#ssfj").val();
			var jQssfjmc=jQuery("#ssfjmc");
			var ssfjmc="";
			if(ssfj!=""){
				ssfjmc=ssfj.substring((ssfj.lastIndexOf("\\")+1),ssfj.length);
				if (ssfjmc != null && ssfjmc != "") {
					var hz = ssfjmc.substr(ssfjmc.lastIndexOf(".")+1,ssfjmc.length);
					if (hz!='doc'&&hz!='rar'&&hz!='pdf'){
						alertError("�ϴ��ļ�����ֻ��Ϊ��doc,rar,pdf");
						return false;
					}
				}
				if(ssfjmc.length > 50){
					alertInfo("�ļ�������,���޸��ļ��������ϴ�");
					return false;
				}
				jQssfjmc.val(ssfj);
			}
			
			if (jQuery("#sqly").val()=="") {
				alertInfo("����д��������!");
				return false;
		 	}
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhXg&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhZj" method="post" enctype='multipart/form-data'>
			<input type="hidden" name="cfid" id="cfid" value="${cfid }" />
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="" />
			
		   	
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								֤�����ϻ򸽼�:
							</th>
							<td style="width:70%">
							<input type="file" name="ssfj" id="ssfj"/>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*��������:</font><br/>
								<font color="red">(��1000��)</font>
							</th>
							<td  style="width:70%">
								<html:textarea  property='sqly' styleId='sqly' style="width:99%"
								rows='8' value="${rs.sqly}" onblur="checkLen(this,1000)"/>
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
									</font>
								</div>
								<div class="btn">
									<button type="button"  class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
									<button type="button"  class="button2"  onclick="Close()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>
		</html:form>
		
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					 showAlert("����ʧ��!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("�����ɹ�!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>

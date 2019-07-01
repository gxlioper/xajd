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
		//关闭弹出层
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
					if (hz!='doc'&&hz!='rar'&&hz!='pdf'&&hz!='bmp'&&hz!='jpg'&&hz!='gif'&&hz!='png'){
						alertError("上传文件类型只能为：doc,rar,pdf,bmp,jpg,gif,png");
						return false;
					}
				}
				if(ssfjmc.length > 50){
					alertInfo("文件名过长,请修改文件名后再上传");
					return false;
				}
				jQssfjmc.val(ssfj);
			}
			
			if (jQuery("#sqly").val()=="") {
				alertInfo("请填写申诉理由!");
				return false;
		 	}
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhZj" method="post" enctype='multipart/form-data'>
			<input type="hidden" name="cfid" id="cfid" value="${cfid }" />
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="" />
			<%--<div class="tab_cur" >
					<p class="location">
						<em>您的当前位置:</em><a>${title } - 申诉 </a>
					</p>
			</div>--%>
			<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>提示：</span></h3>
		       <p>1<br/></p>
		   	</div>
		   	
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>申诉申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								证明材料或附件:
							</th>
							<td style="width:70%">
							<input type="file" name="ssfj" id="ssfj"/>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*申诉理由:</font><br/>
								<font color="red">(限1000字)</font>
							</th>
							<td  style="width:70%">
								<textarea onblur="checkLen(this,1000)" name="sqly" id="sqly" style="width:99%"
								rows='8'></textarea>
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
										保 存
									</button>
									<button type="button"  class="button2"  onclick="Close()">
										关 闭
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
					 showAlert("操作失败!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("操作成功!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>

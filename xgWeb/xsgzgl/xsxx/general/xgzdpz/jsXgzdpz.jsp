<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<style>.bold{font-weight: bold!important;}</style>
		<script type="text/javascript">

		//初始化
		function init(){
			var zdzds = jQuery("#zdzds").val();
			var btzds = jQuery("#btzds").val();
			if(zdzds!=""){
				var zdzd = zdzds.split(",");
				for(var i=0; i<zdzd.length; i++){
					var zd =  document.getElementById(zdzd[i]+"xucyzd");
					zd.checked = "checked";
					zdClick(zd);
				}
				}
			if(btzds!=""){
				var btzd = btzds.split(",");
				for(var i=0; i<btzd.length; i++){
					var zd =  document.getElementById(btzd[i]+"xucybt");
					zd.checked = "checked";
					btClick(zd);
				}
				}
			}
		
		//全部只读
		function qbzdClick(obj){
			var id = obj.id;
			var ids = id.split('xucy');
			var chec = document.getElementsByName(ids[0]+"xucyzd");
			for(var i=0; i<chec.length; i++){
				chec[i].checked = obj.checked;
				zdClick(chec[i]);
			}
			var qbbt =  document.getElementById(ids[0]+"xucyqbbt");
			if(obj.checked==true){
				qbbt.checked = "";
			}
			var zd = document.getElementsByName(ids[0]+"xucybt");
			for(var i=0; i<zd.length; i++){
				if(obj.checked==true){
					zd[i].checked = "";
				}
			}
			}

		//全部必填
		function qbbtClick(obj){
			var id = obj.id;
			var ids = id.split('xucy');
			var chec = document.getElementsByName(ids[0]+"xucybt");
			for(var i=0; i<chec.length; i++){
				chec[i].checked = obj.checked;
				btClick(chec[i]);
			}
			
			var qbzd =  document.getElementById(ids[0]+"xucyqbzd");
			if(obj.checked==true){
				qbzd.checked = "";
			}

			var zd = document.getElementsByName(ids[0]+"xucyzd");
			for(var i=0; i<zd.length; i++){
				if(obj.checked==true){
					zd[i].checked = "";
				}
			}
			}

		//只读
		function zdClick(obj){
			var id = obj.id;
			var ids = id.split('xucy');
			var bt =  document.getElementById(ids[0]+"xucybt");
			var zdmc = jQuery("#"+ids[0]+"xucyzdmc");
			var name= obj.name;
			var names = name.split('xucy');
			if(obj.checked==true){
				var zds = document.getElementsByName(name);
				var zss = jQuery("input[name="+name+"]:checked");
				if(zss.length==zds.length){
					var qbzd =  document.getElementById(names[0]+"xucyqbzd");
					qbzd.checked = "checked";
					}
				var qbbt =  document.getElementById(names[0]+"xucyqbbt");
				qbbt.checked = "";
				bt.checked = "";
				zdmc.attr("disabled","disabled");
				//zdmc.attr("class","");
				jQuery("#s_"+ids[0]).html("<font color='red'>(只读)</font>");
				jQuery("#x_"+ids[0]).html("");
				}else{
					var qbzd =  document.getElementById(names[0]+"xucyqbzd");
					qbzd.checked = "";
					zdmc.attr("disabled",false);
					jQuery("#s_"+ids[0]).html("<font color='green'>(可写)</font>");
					
				}
			}

		//必填
		function btClick(obj){
			var id = obj.id;
			var ids = id.split('xucy');
			var zd =  document.getElementById(ids[0]+"xucyzd");
			var zdmc = jQuery("#"+ids[0]+"xucyzdmc");
			var name= obj.name;
			var names = name.split('xucy');
			if(obj.checked==true){
				var bts = document.getElementsByName(name);
				var zss = jQuery("input[name="+name+"]:checked");
				if(zss.length==bts.length){
					var qbbt =  document.getElementById(names[0]+"xucyqbbt");
					qbbt.checked = "checked";
					}
				var qbzd =  document.getElementById(names[0]+"xucyqbzd");
				qbzd.checked = "";
				zd.checked = "";
				zdmc.attr("disabled",false);
				//zdmc.attr("class","bold");
				jQuery("#s_"+ids[0]).html("<font color='red'>(必填)</font>");
				jQuery("#x_"+ids[0]).html("<font color='red'>*</font>");
				}else{
					var qbbt =  document.getElementById(names[0]+"xucyqbbt");
					qbbt.checked = "";
					//zdmc.attr("class","");
					jQuery("#s_"+ids[0]).html("<font color='green'>(可写)</font>");
					jQuery("#x_"+ids[0]).html("");
				}
			}


		function save(){
			var zdjg = jQuery("#checkbox").find('.checkxucyzd');
			var btjg = jQuery("#checkbox").find('.checkxucybt');
			var zdzds = new Array();
			for (var i = 0;i<zdjg.length;i++){
				if(zdjg[i].checked){
					//zdzds+=zdjg[i].value+"!@!";
					zdzds[i] = zdjg[i].value;
					}
				}
				
			var btzds = new Array();
			for (var i = 0;i<btjg.length;i++){
				if(btjg[i].checked){
					//btzds+=btjg[i].value+"!@!";
					btzds[i] = btjg[i].value;
					}
				}
			var url = "general_xsxx_xgzdpzgl.do?method=bcXgzdsz&lb=tea";
			//refreshForm(url);

			jQuery.ajaxSetup({async:false});
			
			//参数
		 	var parameter = {
		 			"zdZd":zdzds,
		 			"btZd":btzds
		 			
			};
			
			jQuery.post(url,
				parameter,
				function(result){
					alertInfo(result);
					searchRs();
				}
			);
			jQuery.ajaxSetup({async:true});
			
			}
		
		
		</script>
	</head>
	<body onload="init()">
		<html:form action="/general_xsxx_xgzdpzgl" method="post">
		<input type="hidden" name="zdzds" id="zdzds" value="${zdzds}">
		<input type="hidden" name="btzds" id="btzds" value="${btzds}">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						只读：选中“只读”，则该字段不允许老师进行修改，只能查看<br/>
						必填：选中“必填”，则该字段在老师修改学生信息保存时会验证必填，否则无法保存
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div class="compTab">
				<div class="comp_title">
			      <ul>
			         <li ><a href="general_xsxx_xgzdpzgl.do?method=cxXgzdpz&lb=stu"><span>学生</span></a></li>
			         <li class="ha"><a><span>教师</span></a></li>
			      </ul>
			    </div>
			</div>	
			<table width="100%" border="0" class="formlist" id="checkbox">
			  <logic:present name="zdflList">
				  <logic:iterate id="zdfl" name="zdflList" >
				  <thead>
					  <tr>
		        		<th colspan="6">
		        		<span>${zdfl.flmc}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        		<input type="checkbox" class="cbvclass" style="cursor: pointer;" id="${zdfl.zdfl}xucyqbzd" 
		        			name="${zdfl.zdfl}"  onclick="qbzdClick(this);" value="" />全部只读
		        		 	&nbsp;&nbsp;&nbsp;&nbsp;
		        		<input type="checkbox" class="cbvclass" style="cursor: pointer;" id="${zdfl.zdfl}xucyqbbt"
		        			 name="${zdfl.zdfl}"  onclick="qbbtClick(this);" value="" />全部必填
		        		</th>
		        		</tr>
	        	</thead>
	        	<tr>
		       <%int n=1; %>
		        <logic:iterate id="zd" name="zdList" indexId="index">
		        	<logic:equal value="${zd.zdfl}" name="zdfl" property="zdfl">
	    						<th id="${zd.zd}xucyzdmc" >
		        		 	<span id="x_${zd.zd}" class="bold" >
					        </span>	
		        		 		${zd.zdmc}
		        		 	<span id="s_${zd.zd }" class="bold" >
					        	<font color='green'>(可写)</font>
					        </span>	
		        		 	</th>
		        		 	<td width="15%">
		        		 		<input type="checkbox" class="checkxucyzd" style="cursor: pointer;" id="${zd.zd}xucyzd" 
		        		 			name="${zdfl.zdfl}xucyzd" value="${zd.zd}" onclick="zdClick(this);" />只读
		        		 		<input type="checkbox" class="checkxucybt"  style="cursor: pointer;" id="${zd.zd}xucybt" 
		        		 			name="${zdfl.zdfl}xucybt" value="${zd.zd}"  onclick="btClick(this);"/>必填
		        		 	</td>
		        		 	<%if(n%3==0 ){%>
								<% out.print("</tr><tr>"); %>
							<%}%> 
							<% n++; %>
		        	</logic:equal>
		        </logic:iterate>
		        <%if((n-1)%3!=0) 
		        	for(int i=0;i<3-(n-1)%3;i++){
		        		 out.print("<th></th><td></td>"); 
		        	}
		        	out.print("</tr>"); 
		        %>
	        	</logic:iterate>
			  </logic:present>
			</table>
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！")
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！")
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

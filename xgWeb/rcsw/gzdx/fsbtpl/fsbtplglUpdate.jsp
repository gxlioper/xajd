<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function loadXsxx(){
			jQuery.post('rcsw_gzdx_fsbtplgl.do?method=loadXsxx',{xh:jQuery('#xh').val()},function(data){
				var xh = data.xh;
				if(xh!=undefined ){
					var xm = data.xm;
					//var xymc = data.xymc;
					//var zymc = data.zymc;
					var bjmc = data.bjmc;
												
					var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td align='right'>" + xh + 
								"</td><td align='right'>" + xm + "</td><td align='right'>" + bjmc + "</td></tr>";
					jQuery('#xsxx').append(option);		
					jQuery('#xstr').css({display:''});	
				}else{
					jQuery('#xstr').css({display:'none'});	
				}					
			},'json');
		}
		function loadXs(){
			//复选框选中学号
			var pks = window.dialogArguments.document.getElementsByName("pkValues");
			var xhs = window.dialogArguments.document.getElementsByName("xh");
			//查询的总数据集的 条数			
			var num = window.dialogArguments.document.getElementById("num").value;
			//查询的数据集的查询条件
			var searchTjstr = window.dialogArguments.document.getElementById("searchTjstr").value;
			
			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=xhs[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//未选中学生
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("当前查询结果中共有<font color='red'>"+num+"</font>个学生，可执行发放操作");
				if(num == "1"){//总数据集仅一条数据，加载学生信息
					jQuery('#xhstr').val(xhs[0].value);
					jQuery('#xh').val(xhs[0].value);
					loadXsxx();
				}
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//选中学生
				jQuery('#xhstr').val(RowsStr);
				jQuery('#xhtd').html("当前共选中<font color='red'>"+count+"</font>个学生，可执行发放操作");
				if(count=="1"){//选中一个学生，加载学生信息
					jQuery('#xh').val(RowsStr.replace("!!splitOne!!",""));
					loadXsxx();
				}
			}
		}
		function dataSave(url,mustFill){
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alertInfo("必填字段未填完整！");
					return false;
				}
			}
			$('buttonSave').disabled = "disabled";
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		jQuery(function(){
			showJe();
			
			jQuery("#btdm").change(function(){
				showJe();
			});	
		});

		function showJe(){
			var btdm = jQuery("#btdm").val();
			if(!btdm){
				jQuery("#span_btje").html("");
			}else {
				jQuery("#span_btje").html(jQuery("#" + btdm + "_je").val());
			}
		}
			
		</script>
		<style type="text/css">
			table{
				border-collapse:collapse;
			}
			
			table th{
				width:20%;
			}
			
			table td{
				width:30%;
			}
			
			table span{
				color:red;
			}
		</style>
	</head>
	<body onload="loadXs()">
		<html:form action="/rcsw_gzdx_fsbtplgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
			<input type="hidden" name="url" value="/rcsw_gzdx_fsbtgl.do?method=fsbtglUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			<input type="hidden" id="xhstr" name="xhstr"　value=""/>
			<input type="hidden" id="xh" name="xh"　value=""/>
			<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
			
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>补贴发放</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span>*</span>年度
								</th>
								<td>
									<html:select name="rs" property="nd">
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select>
								</td>
								<th>
									<span>*</span>月份
								</th>
								<td>
									<html:select name="rs" property="yf">
										<html:options collection="yfList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span>*</span>补贴项目
								</th>
								<td>
									<html:select name="rs" property="btdm" styleId="btdm">
										<html:options collection="fsbtList" property="dm" labelProperty="mc"/>
									</html:select>
									<logic:iterate id="fsbtMap" name="fsbtList">
										<input type="hidden" id="${fsbtMap.dm }_je" value="${fsbtMap.btje }"/>
									</logic:iterate>
								</td>
								<th>
									补贴金额
								</th>
								<td>
									<font id="span_btje"></font>
								</td>
							</tr>
							<tr>
								<th>
									经手人
								</th>
								<td>
									${user.realName }
									<input type="hidden" name="jsr" value="${user.realName }"/>
								</td>
								<th>
									发放时间
								</th>
								<td>
									<html:text property="ffsj" styleId="ffsj" onkeydown="onlyBackSpace(this,event);" 
									onblur="dateFormatChg(this);"
									onclick="return showCalendar('ffsj','y-mm-dd');" 
									style="cursor:hand" readonly="true"></html:text>
								</td>
							</tr>
						 </tbody>
						 
					     <thead>
							<tr>
								<th colspan="4"><span>发放学生</span></th>
							</tr>
						</thead>
						<tbody>
							<tr >
								<th width="16%">
									<font color="red">*</font>发放操作说明				
								</th>
								<td colspan="3" id="xhtd">
									
								</td>
							</tr>
							<tr id="xstr" style="display: none">
								<th width="16%">
									<font color="red">*</font>发放学生信息				
								</th>
								<td colspan="3" >
									<table id="xsxx" style="width: 100%">
										<tr>
											<th>学号</th><th>姓名</th><th><bean:message key="lable.xsgzyxpzxy" /></th>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>

						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								  <button type="button" id="buttonSave"
									onclick="dataSave('/xgxt/rcsw_gzdx_fsbtplgl.do?method=fsbtplglUpdate&doType=save','nd-yf-btdm')">
									保存
								</button>
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									关闭
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value, function(){
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   	window.dialogArguments.document.getElementById('search_go').click(); 
					}
				});
				
			</script>
		</logic:present>
	</body>
</html>

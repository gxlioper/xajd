<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				// ===== 获取高级查询条件 begin========
				var api = frameElement.api, W = api.opener;
				var html = "<div class='adv_filter' style='display: none;'>" + jQuery("div[class=adv_filter]", W.document).eq(0).html() + "</div>";
				html += "<div id='searchTjDiv' style='display: none;'>" + jQuery("#searchTjDiv", W.document).html() + "</div>";
				jQuery("#cxtjPlHidden").html(html);
				jQuery(function(){
					jQuery("[name='chk']").change(function(){
						if(this.value == "sfz"){
							jQuery("#mm1").val("");
							jQuery("#mm1").attr("readonly","readonly");
						}else{
							jQuery("#mm1").removeAttr("readonly");
						}
					})
				})
				// ===== 获取高级查询条件 end========
			});
			function cshMm() {
				/*
				if (jQuery("#mm1").val() != jQuery("#mm2").val()) {
					showAlert("确认密码不一致！");
					return false;
				}*/
				var bz = jQuery("[name='chk']:checked").val();
				if(bz == "sdsr"){
					if (!checkPsw(jQuery("#mm1").val())) {
						return false;
					}
				}
				
				/* 
				这段垃圾代码无用，直接注释
				获取高级查询条件，并隐藏，便于【根据勾选记录进行批量操作】使用
				var pksPlHidden = jQuery("#pksPlHidden", W.document).val();
				jQuery("#pksPlHidden").val(pksPlHidden);
				
				// 如果为空，那么【根据查询结果进行批量操作】
				if(pksPlHidden == ""){
					alert("0000");
					//setSearchTj();//设置高级查询条件
					
				}*/
				var url = "xsxx_xsxxgl.do?method=mmcshPl&type=update&bz="+jQuery("[name='chk']:checked").val();
				url = addSuperSearchParams_self(url);//设置高级查询参数
				showConfirm("确定要将${len }个学生的密码初始化吗？", {
					"okFun" : function() {
						ajaxSubFormWithFun("xsxxForm",url,function(data){
							showAlert(data["message"],{},{"clkFun":function(){
								closeDialog();
							}});
						});
					}
				});
			}

			//设置高级查询条件，导出方法中使用，重写方法
			function addSuperSearchParams_self(url){
				var map = getSuperSearch_self();
				if(url.indexOf("?") > -1){
					url += "&";
				}else{
					url += "?";
				}
				url += "&mhcx_lx=" + map.mhcx_lx;
				jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
				
				var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
				html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
				html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
				if (map.input_mhcx){
					html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
				}
				jQuery("form").eq(0).append(html);
				return url;
			}

			//重写方法
			function getSuperSearch_self(){
				var api = frameElement.api,W = api.opener;
				//模糊查询
				var input_mhcx ,mhcx_lx ,fashion,plcx_xh,plcx_xm;
				if(jQuery("#plcx_xh",W.document)){
					plcx_xh = jQuery("#plcx_xh",W.document).val();
				}
				if(jQuery("#plcx_xm",W.document)){
					plcx_xm = jQuery("#plcx_xm",W.document).val();
				}
				if(jQuery("#input_mhcx",W.document)){
					input_mhcx = jQuery("#input_mhcx",W.document).val();
				}
				
				if(jQuery("#mhcx_lx",W.document)){
					mhcx_lx = jQuery("#mhcx_lx",W.document).val();
				}

				//点击查询
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx.push(jytj[i]);
				}

				var tj_num = jQuery("#searchTjDiv input",W.document).length;
				for(var j=0;j<tj_num;j++){
					var obj = jQuery("#searchTjDiv input",W.document).eq(j);
					searchTj.push(obj.attr("name"));
					searchTjz.push(obj.val());
				}
				
			 	var map = {
			 		"plcx_xh":plcx_xh,
			 		"plcx_xm":plcx_xm,
					"input_mhcx":input_mhcx,
					"mhcx_lx":mhcx_lx,
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!"),
					"path":jQuery("#path").val()
				};
				return map;
			}
		</script>
	</head>

	<body>
	<html:form action="/xsxx_xsxxgl" method="post" styleId="xsxxForm">
		<div id="cxtjPlHidden" style="height: 0px;"></div>
		<input type="hidden" name="pks" id="pksPlHidden" value="" />
		<input type="hidden" name="path" id="path" value="${path}" />
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						提示：当前共选<font class='red'>${len }</font>个学生
					</td>
				</tr>
			</thead>
			<tbody>
				<tr height='30'>
					<th>
						<input type="radio" name="chk" value="sfz" checked="checked"/>
					</th>
					<td>	
							<logic:equal name="xxdm" value="11458">
									按“sdju”+身份证后六位，无身份证按“sdju”+6个0
							</logic:equal>
							<logic:notEqual name="xxdm" value="11458">
									按身份证后六位，无身份证按6个0
							</logic:notEqual>
					<%-- 
						<input type="password" name="mm1" id="mm1" class="text_nor"
							maxlength="20" />--%>
					</td>
				</tr>
				<tr height='30'>
					<th>
						<input type="radio" name="chk" value="sdsr"/>
					</th>
					<td>
					手动输入<input type="password" name="mm1" value="" readonly="readonly" id="mm1" class="text_nor"
							maxlength="20" />
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<span class="red">新密码长度为6~20位且不可为连续数字或相同字符</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn">
							<button type="button" onclick="cshMm();return false;">
								确定
							</button>
							&nbsp;&nbsp;
							<button type="button"
								onclick="closeDialog();return false;">
								关闭
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" defer="defer">
function addYhxx(){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxAdd";
			var eles = new Array("yhm_add","xm_add","kl_add","select_zdm_add","select_szbm_add","select_dwdm_add","select_qx_add");
			for (i = 0; i < eles.length; i++) {
			    if($(eles[i])!=null||$(eles[i])){
				    if ($(eles[i]).value == "") {
				    	alertInfo("请将带\"*\"号的项目输入完整！");
				    	return false;
				   }
				}
			}	

			if(jQuery("#kl_add").val() != jQuery("#kl_add_rep").val()){
				alertInfo("确认口令不一致！");
		    	return false;
			}

			if(!checkPsw(jQuery("#kl_add").val()))
				return false;
			
			var parameter = {};
			for (i = 0; i < eles.length; i++) {
				parameter[eles[i].replace("select_","").replace("_add","")] =escape(jQuery("#"+eles[i]).val());
			}
			jQuery.post(url,parameter,function(result){
				alertInfo(result+"！");
				hiddenMessage(true,true);
				searchRs();					
			});
		}

		//用户信息修改
		function updateYhxx(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxUpdate";
			var eles = new Array("yhm_update","xm_update","select_zdm_update","select_szbm_update","select_dwdm_update","select_qx_update");
		
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
		
			if(len==1){
				var parameter = {};
				if(act=="show"){
					jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
						jQuery("#yhm_v").val(data.yhm);
						jQuery("#yhm_update").val(data.yhm);
						jQuery("#xm_update").val(data.xm);
						jQuery("#select_zdm_update").val(data.zdm);
						jQuery("#select_szbm_update").val(data.szbm);
						jQuery("#select_dwdm_update").val(data.dwdm);
						jQuery("#select_qx_update").val(data.qx);
						
						viewTempDiv('用户信息修改','updateyhxx',350,380);			
					},'json');
				}else{	
					for (i = 0; i < eles.length; i++) {
					    if($(eles[i])!=null||$(eles[i])){
						    if ($(eles[i]).value == "") {
						    	alertInfo("请将带\"*\"号的项目输入完整！");
						    	return false;
						   }
						}
					}			
					for (i = 0; i < eles.length; i++) {
						parameter[eles[i].replace("select_","").replace("_update","")] =escape(jQuery("#"+eles[i]).val());
					}
					jQuery.post(url,parameter,function(result){
						alertInfo(result+"！");
						hiddenMessage(true,true);
						searchRs();		
					});
				}
			}else {				
				alertInfo("请勾选一条需要修改的数据！");
			}
		}

		//删除用户
		function deleteYhxx(){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxDelete";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					array[i] = escape(jQuery(this).val());
				});
				parameter["array_primarykey_checkVal"]=array.join('!!array!!');
				
				confirmInfo("确定要删除选中的记录吗?",function(ok){
					if(ok=="ok"){		
						jQuery.post(url,parameter,function(result){
							alertInfo(result+"！");
							searchRs();		
						});					
					}
				});
			}else{				
				alertInfo("请勾选需要删除的数据！");
			}
		}


		//密码初始化
		function mmcsh(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhmmCsh";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					array[i] = escape(jQuery(this).val());
				});
				var selectId=array.join('!!array!!');
				if(act == "show"){
					//viewTempDiv('密码初始化','cshYhmm',350, 230);
					showDialog('密码初始化',400,200,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectId+'&pathUrl=mmcsh');
						
				}else{
					if(jQuery("#mm1").val() != jQuery("#mm2").val()){
						alertInfo("确认口令不一致！");
				    	return false;
					}
					
					if(!checkPsw(jQuery("#mm1").val()))
						return false;
						
					var i = 0;
					var parameter = {};
					parameter["array_primarykey_checkVal"]=selectId;
					parameter["str_kl"]=escape(jQuery("#mm1").val());
					confirmInfo("确定要将选中的用户的密码初始化吗?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"！");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else{				
				// 重置
				jQuery("#pksPlHidden").val("");
				// 根据查询结果进行批量增加
				var ie = "ie";
				var parameter = {"ie":ie};
				jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
					parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
				});
				jQuery("#pksPlHidden").val(JSON.stringify(parameter));
				showDialog('密码初始化',360,200,'xtwh_qxgl_yhgl.do?method=yhglManage&pathUrl=mmcshPl&selectId='+jQuery("#max_record").html());			
			}
		}

		function setFdyxx(){
			jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getFdyxx",{yhm:jQuery("#yhm_add").val()},function(data){
				jQuery("#xm_add").val(data.xm);
				jQuery("#select_szbm_add").val(data.bmdm);		
			},'json');
		}

		//批量修改用户所属组，启用状态
		function yhfz(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhfz";			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			
			if(n>0){
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						array[i] = escape(jQuery(this).val());
					}
				});

				if(ifGly == "yes"){
					alertError("zf01为系统默认用户，不可修改所属组！");
				}else{				
					if(n == 1 && act == "show"){
						jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
							//viewTempDiv(' ','yhfz',360,230);
							var selectid=array.join('!!array!!');
							showDialog('用户分组管理',450,385,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectid+'&pathUrl=fz');			
							jQuery("#select_zdm_fz").val(data.zdm);
						},'json');									
					}else if(act == "show"){
						//viewTempDiv(' ','yhfz',360, 230);
						var selectid=array.join('!!array!!');
							showDialog('用户分组管理',450,385,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectid+'&pathUrl=fz');
						jQuery("#select_zdm_fz").val("");
					}else{
						if(jQuery("#select_zdm_fz").val() == ""){
							alertError("请选择所属组！");
							return false;
						}
						var i = 0;					
						parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						parameter["str_zdm"]=jQuery("#select_zdm_fz").val();
						parameter["str_qx"]=jQuery("[name=qx]:checked").val();
						confirmInfo("确定要修改选中的用户的所在组吗?",function(ok){
							if(ok=="ok"){
								jQuery.post(url,parameter,function(result){
									alertInfo(result+"！");
									hiddenMessage(true,true);
									searchRs();		
								});					
							}else{
								return false;
							}
						});
					}
				}
			}else{				
				alertInfo("请勾选需要修改分组的用户！");
			}
		}
</script>

<!-- 添加新用户层 -->
<div class="open_win01" style="display:none;" id="addYhxx" >
	<table width='80%' class='formlist'>				
		<thead>
			<tr>
				<th colspan="2">
					<span>用户信息增加</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width='25%'>
					<font color=red>*</font>用户名
				</th>
				<td>
					<input type='text' name='yhm_add' class='text_nor' id="yhm_add"  maxlength="20"
						onblur="checkInputIshz(this);checkExists('yhb','yhm',this,'button_user','span_user');setFdyxx();"/>
					<span id="span_user" style="display: none;color:red">用户名已存在！</span>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>姓名
				</th>
				<td>
					<input type='text' name='xm_add' id="xm_add" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>密码
				</th>
				<td>								
					<input type='password' name='kl_add' id='kl_add' class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>确认密码
				</th>
				<td>
					<input type='password' name='kl_add_rep' id="kl_add_rep" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>所属组
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_add">
						<html:option value="">--请选择--</html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<logic:notEqual value="10338" name="xxdm" scope="session">
				<tr>
					<th>
						<font color=red>*</font>所属单位
					</th>
					<td>
						<html:select property="dwdm" style="width:200px" styleId="select_dwdm_add">
							<html:option value="">--请选择--</html:option>
							<html:options collection="dwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<th>
					<font color=red>*</font>所属部门
				</th>
				<td>
					<html:select property="szbm" style="width:200px" styleId="select_szbm_add">
						<html:option value="">--请选择--</html:option>
						<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					启用状态
				</th>
				<td>
					<select id='select_qx_add' name='qx' style='width:152px;'>
						<option value='1'>
							启用
						</option>
						<option value='0'>
							停用
						</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项<br/>
					<span class="red">增加辅导员用户时请确保思政队伍中的辅导员信息与当前<br/>用户信息一致</span><br/>
					<span class="red">密码长度不得小于6位且不可为连续数字或相同字符</span>							
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div class='btn'>
						<button type="button"  id="button_user" onclick='addYhxx()'>
							保存
						</button>
						&nbsp;&nbsp;
						<button type="button"  onclick="hiddenMessage(true,true);return false;">
							关闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!-- 修改用户信息层 -->
<div class="open_win01" style="display:none;" id="updateYhxx" >
	<table width='80%' class='formlist'>
		<thead>
			<tr>
				<th colspan="2">
					<span>用户信息修改</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width='25%'>
					<font color=red>*</font>用户名
				</th>
				<td>
					<input type='text' name='yhm_v' class='text_nor' id="yhm_v" disabled="disabled"/>
					<input type='hidden' name='yhm_update' class='text_nor' id="yhm_update" value='' maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>姓名
				</th>
				<td>
					<input type='text' name='xm_update' id="xm_update" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>所属组
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_update">
						<html:option value="">--请选择--</html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<logic:notEqual value="10338" name="xxdm" scope="session">
				<tr>
					<th>
						<font color=red>*</font>所属单位
					</th>
					<td>
						<html:select property="dwdm" style="width:200px" styleId="select_dwdm_update">
							<html:option value="">--请选择--</html:option>
							<html:options collection="dwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<th>
					<font color=red>*</font>所属部门
				</th>
				<td>
					<html:select property="szbm" style="width:200px" styleId="select_szbm_update">
						<html:option value="">--请选择--</html:option>
						<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					启用状态
				</th>
				<td>
					<select id='select_qx_update' name='qx' style='width:152px;'>
						<option value='1'>
							启用
						</option>
						<option value='0'>
							停用
						</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项<br/><span class="red">增加辅导员用户时请确保思政队伍中的辅导员信息与当前<br/>用户信息一致</span>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div class='btn'>
						<button type="button"  id="button_user" onclick='updateYhxx()'>
							保存
						</button>
						&nbsp;&nbsp;
						<button type="button"  onclick="hiddenMessage(true,true);return false;">
							关闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!-- 密码初始化弹出层 -->
<div class="open_win01" style="display:none;" id="cshYhmm">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>密码初始化</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>新密码
				</th>
				<td>
					<input type="password" name="mm1" id="mm1" class="text_nor"  maxlength="10"/>
				</td>
			</tr>
			<tr height='30'>
				<th>
					<font color="red">*</font>确认密码
				</th>
				<td>
					<input type="password" name="mm2" id="mm2" class="text_nor" maxlength="10"/>
				</td>
			</tr>
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项<br/><span class="red">密码长度不得小于6位且不可为连续数字或相同字符</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="mmcsh()">
							确定
						</button>
						&nbsp;&nbsp;
						<button type="button"  onclick="hiddenMessage(true,true);return false;">
							关闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!-- 用户所属组修改弹出层 -->
<div class="open_win01" style="display:none;" id="yhfz">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>用户分组</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>所属组
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_fz">
						<html:option value=""></html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>			
			<tr height='30'>
				<th>
					启用状态
				</th>
				<td>
					<input type="radio" id="qx_qy" name="qx" value="1" checked="checked"/>启用
					<input type="radio" id="qx_bqy" name="qx" value="0" />停用
				</td>
			</tr>
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="yhfz()">
							确定
						</button>
						&nbsp;&nbsp;
						<button type="button"  onclick="hiddenMessage(true,true);return false;">
							关闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

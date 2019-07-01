<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">

		//困难生认定指标增加
		var count = 0;
		//增加
		function addTr(){
			var existNum = jQuery("input[name=existNum]").val();
			if(existNum != "0"){
				showAlertDivLayer("该指标困难生认定申请已使用，只可修改困难指标名称、权重比例、指标内容、分值！");
				return false;
			}
			tr = jQuery('#hidden_knsrdzb').html();
			tr = tr.replace(/sxmc!!@@!!id/i,'zbsj'+(count));
			tr = tr.replace(/qzbl!!@@!!onclick/i,'zbsj'+(count++));
			jQuery('#tbody_knsrdzb').append(tr);
			return false;
		}

		//指标内容增加
		var countZbnr = 0;
		//增加
		function addZbnr(obj){
			
			var existNum = jQuery("input[name=existNum]").val();
			
			if(existNum != "0"){
				showAlertDivLayer("该指标困难生认定申请已使用，只可修改困难指标名称、权重比例、指标内容、分值！");
				return false;
			}
			tr = jQuery('#hidden_knsrdzbnr').html();
			tr = tr.replace(/zbnr!!@@!!id/i,'zbsj'+(countZbnr));
			tr = tr.replace(/knfz!!@@!!onclick/i,'zbsj'+(countZbnr++));
			jQuery(obj).parents("#knsrdzbnr").find('#tbody_knsrdzbnr').append(tr);
			return false;
		}

		function xzknsfz(obj){
			if(jQuery(obj).is(":checked")){
				jQuery(obj).parents("#knsrdzbxxnr").find("#qjfz").show();
				jQuery(obj).parents("#knsrdzbxxnr").find("#knfz").hide();
			}else{
				jQuery(obj).parents("#knsrdzbxxnr").find("#qjfz").hide();
				jQuery(obj).parents("#knsrdzbxxnr").find("#knfz").show();
			}
		}

		//删除
		function delTr(obj){
			var existNum = jQuery("input[name=existNum]").val();
			if(existNum != "0"){
				showAlertDivLayer("该指标困难生认定申请已使用，只可修改困难指标名称、权重比例、指标内容、分值！");
				return false;
			}
			showConfirmDivLayer("您确定要删除该条记录吗？", {
				"okFun" : function() {
				  jQuery(obj).parents("tr").eq(0).remove();
				}
			});
		}
		
		function save123(){	
			var base=new Array();
			var i=1;
			var zbmc = jQuery("input[name=zbmc]").val();
			if(zbmc == ""){
				showAlertDivLayer("困难生认定指标名称为必填项不能为空！");
				return false;
			}
			var knsrdzbSfwk = false;//困难生认定指标设置是否为空
			var nrmcSfwk = false;//内容名称是否为空
			var fzSfwk = false;//内容名称是否为空
			var zbnrSfwk = false;//指标内容是否为空
			var sxmcSfwk = false;//困难指标是否为空
			var qzblSfwk = false;//权重比例是否为空
			
			jQuery("#tbody_knsrdzb>tr").each(function(){
				//判断指标内容为必填项不能为空跳出该循环
				if(nrmcSfwk == true ){
					//showAlertDivLayer("指标内容名称为必填项不能为空！");
					return false;
				}
				//分值为必填项不能为空跳出该循环
				if(fzSfwk == true ){
					//showAlertDivLayer("分值为必填项不能为空！");
					return false;
				}
				knsrdzbSfwk = true;
				var rdzb=new Object();
				var sxmc = jQuery(this).find("input[name=sxmc]").val();
				if(sxmc == ""){
					//showAlertDivLayer("困难指标为必填项不能为空！");
					sxmcSfwk = true;
					return false;
				}
				rdzb.sxmc
				var qzbl=jQuery(this).find("input[name=qzbl]").val();
				if(qzbl == ""){
					//showAlertDivLayer("权重比例为必填项不能为空！");
					qzblSfwk = true;
					return false;
				}
				rdzb.xssx=i;
				var zbnra=new Array();
				var xxnri=1;
				zbnrSfwk = false;//指标内容是否为空
				jQuery(this).find("#knsrdzbxxnr").each(function(){
					zbnrSfwk = true;
					var zbnr=new Object();
					var nrmc = jQuery(this).find("input[name=nrmc]").val();
					if(nrmc == ""){
						nrmcSfwk = true;
						return false;
					}
					zbnr.nrmc;
					if(jQuery(this).find("input[name=fzlx]").is(":checked")){
						zbnr.fzlx = "1";
						var fz = jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
						zbnr.fz;
					}else{
						zbnr.fzlx = "0";
						var fz = jQuery(this).find("input[name=knfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
						zbnr.fz;
					}
					zbnr.xssx=xxnri;
					zbnra[xxnri-1]=zbnr;
					xxnri++;
					
				});

				if(zbnrSfwk == false){
					return false;
				}
				
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;
			});
			if(knsrdzbSfwk == false){
				showAlertDivLayer("困难生认定指标设置不能为空！");
				return false;
			}	
			if(sxmcSfwk == true){
				showAlertDivLayer("困难指标为必填项不能为空！");
				return false;
			}
			if(qzblSfwk == true){
				showAlertDivLayer("权重比例为必填项不能为空！");
				return false;
			}
			//指标内容不能为空
			if(zbnrSfwk == false){
				showAlertDivLayer("指标内容不能为空！");
				return false;
			}
			//判断指标内容为必填项不能为空
			if(nrmcSfwk == true ){
				showAlertDivLayer("指标内容名称为必填项不能为空！");
				return false;
			}
			//分值为必填项不能为空
			if(fzSfwk == true ){
				showAlertDivLayer("分值为必填项不能为空！");
				return false;
			}
			var json=JSON.stringify(base);
			alert(json);
			return false;
			var url = "xg_xszz_knsrd_knzbgl.do?method=updateKnsrdzb&type=update";
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json,zbmc:zbmc},
				   success: function(msg){
				     alert( "Data Saved: " + msg );
				   }
			});
			
		}	

		function save(){	
			var base=new Array();
			var i=1;
			var zbmc = jQuery("input[name=zbmc]").val();
			if(zbmc == ""){
				showAlertDivLayer("困难生认定指标名称为必填项不能为空！");
				return false;
			}
			var knsrdzbSfwk = false;//困难生认定指标设置是否为空
			var knzbSfwk = false;//困难指标是否为空
			var qzblSfwk = false;//权重比例是否为空
			var knzbnrSfwk = false;//困难指标是否为空
			var nrmcSfwk = false;//内容名称是否为空
			var fzSfwk = false;//内容名称是否为空
			var existNum = jQuery("input[name=existNum]").val();
			jQuery("#tbody_knsrdzb>tr").each(function(){
				knsrdzbSfwk = true;
				var rdzb=new Object();
				rdzb.sxid=jQuery(this).find("input[name=sxid]").val();
				var sxmc = jQuery(this).find("input[name=sxmc]").val();
				rdzb.sxmc=jQuery(this).find("input[name=sxmc]").val();
				if(sxmc == ""){
					knzbSfwk = true;
					return false;
				}

				var qzbl=jQuery(this).find("input[name=qzbl]").val();
				rdzb.qzbl=jQuery(this).find("input[name=qzbl]").val();
				if(qzbl == ""){
					qzblSfwk = true;
					return false;
				}
				
				rdzb.xssx=i;
				var zbnra=new Array();
				var xxnri=1;
				knzbnrSfwk = false;
				jQuery(this).find("#knsrdzbxxnr").each(function(){
					knzbnrSfwk = true;
					var zbnr=new Object();
					zbnr.nrid = jQuery(this).find("input[name=nrid]").val();
					var nrmc=jQuery(this).find("input[name=nrmc]").val();
					zbnr.nrmc=jQuery(this).find("input[name=nrmc]").val();
					if(nrmc == ""){
						nrmcSfwk = true;
						return false;
					}
					if(jQuery(this).find("input[name=fzlx]").is(":checked")){
						zbnr.fzlx = "1";
						var fz = jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						zbnr.fz=jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
					}else{
						zbnr.fzlx = "0";
						var fz = jQuery(this).find("input[name=knfz]").val();
						zbnr.fz=jQuery(this).find("input[name=knfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
					}
					zbnr.xssx=xxnri;
					zbnra[xxnri-1]=zbnr;
					xxnri++;
				});

				//判断指标内容为必填项
				if(knzbnrSfwk == false){
					return false;
				}
				//判断内容名称为必填项
				if(nrmcSfwk == true){
					return false;
				}
				//判断分值名称为必填项
				if(fzSfwk == true){
					return false;
				}
				
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;
			});

			if(knsrdzbSfwk == false){
				showAlertDivLayer("困难生认定指标设置不能为空！");
				return false;
			}	
			if(knzbSfwk == true){
				showAlertDivLayer("困难指标为必填项不能为空！");
				return false;
			}
			if(qzblSfwk == true){
				showAlertDivLayer("权重比例为必填项不能为空！");
				return false;
			}

			if(knzbnrSfwk == false){
				showAlertDivLayer("困难生认定指标内容为必填项不能为空！");
				return false;
			}
			//判断指标内容为必填项不能为空
			if(nrmcSfwk == true ){
				showAlertDivLayer("指标内容为必填项不能为空！");
				return false;
			}
			//分值为必填项不能为空
			if(fzSfwk == true ){
				showAlertDivLayer("分值为必填项不能为空！");
				return false;
			}
			
			var json=JSON.stringify(base);
			
			var zbid = jQuery("input[name=zbid]").val();
			var url = "xg_xszz_knsrd_knzbgl.do?method=updateKnsrdzb&type=update";
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json,zbmc:zbmc,zbid:zbid,existNum:existNum},
				   success: function(data){
				    // alert( "Data Saved: " + msg );
				     if(data["message"]=="保存成功！" ){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
				   }
			});
		}
			
		function autoSetZbnr(){
			jQuery("input[name=fzlx]").each(function(){
				var fzlxh=jQuery(this).next("input[name=fzlxH]").val();
				var knfz=jQuery(this).next("input[name=fzlxH]").next("input[name=fzH]").val();
				if(fzlxh=="1"){
					var qjfz = knfz.split("-");
					jQuery(this).click();
					jQuery(this).parents("#knsrdzbxxnr").find("#qjfz").show();
					jQuery(this).parents("#knsrdzbxxnr").find("#knfz").hide();
					jQuery(this).parents("#knsrdzbxxnr").find("input[name=knqjksfz]").val(qjfz[0]);
					jQuery(this).parents("#knsrdzbxxnr").find("input[name=knqjjsfz]").val(qjfz[1]);
				}
			});
		}
		
		jQuery(document).ready(function(){
			autoSetZbnr();
		});
		</script>
		
		
	</head>
	<body >
		<html:form action="/xg_xszz_knsrd_knzbgl" method="post" styleId="knsrdzbForm" >
		<input type='hidden'  name='zbid'  value="${model.zbid}"/>
		<input type='hidden'  name='existNum'  value="${existNum}"/>
		<div id="divHead" style="width: 100%; height:80px;overflow: hidden;position: fixed; _position: absolute; top: 0;" >
			<div style="">
				<!-- 提示信息 end-->	
				<table width="100%" border="0" class="formlist" >
				
					<tbody id="tbody_jbxx">
						<tr>
							<th colspan="2">
								<font class="red">*</font><span>名称</span>
							</th>
							<td colspan="2" style='width:250px'>
								<logic:equal value="0" name="existNum">
								<input type='text'  name='zbmc' value='${model.zbmc}'/>
								</logic:equal>
								
								<logic:notEqual value="0" name="existNum">
									<span>${model.zbmc}</span>
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
				</table>
				
				</div>
			
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="7%">
							
								<img src="images/knsrd/jiahao.gif"  onclick="addTr();return false;"/>
							</td>
							<td width="23%">
								<font class="red">*</font>困难指标
							</td>
							<td width="10%">
								<font class="red">*</font>权重比例
							</td>
							<td width="60%" >
								<font class="red">*</font>指标内容
							</td>
						</tr>
						
					</thead>
				</table>
				</div>	
				
				<div id="divBody" style="width:100%;height:432px;border: 0px black solid; padding: 0px; margin: 0px; overflow: scroll;position: fixed; _position: absolute;top:80px">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
				<tbody id="tbody_knsrdzb">
						<logic:notEmpty name="object">
							<logic:iterate name="object" id="s" indexId="i">
							
							<tr > 
								<td width="7%">
								<img src="images/knsrd/jianhao.gif" onclick="delTr(this);return false;"/>
								</td>
								<td style="display:none"><input type='text' id='sxid' name='sxid' value="${s.key.sxid}"/></td>
								<td width="23%" ><input type='text' id='sxmc!!@@!!id' name='sxmc' style='width:160px' value="${s.key.sxmc}" maxlength="50"/></td>
								<td width="10%"><input type='text' id='qzbl' name='qzbl' size='5' maxlength='5' value="${s.key.qzbl}" style="width:30px" onblur="checkInt(this);"/>%</td>
								<td width="60%">
									<table width="100%" border="0" class="datelist" id="knsrdzbnr" style="margin:2px auto;">
										<thead>
											<tr>
												<td width="7%">
													<img src="images/knsrd/jiahao.gif" onclick="addZbnr(this);return false;"/>
												</td>
												<td width="23%">
													<font class="red">*</font>指标内容
												</td>
												<td width="10%">
													区间分
												</td>
												<td width="60%" >
													<font class="red">*</font>分值
												</td>
												
											</tr>
											<tbody id="tbody_knsrdzbnr">
												<logic:iterate name="s" id="list" indexId="j" property="value">
													<tr id="knsrdzbxxnr">
														<td width="15%">
														 <img src="images/knsrd/jianhao.gif" onclick="delTr(this);return false;"/>
														</td>
														<td style="display:none"><input type='text' id='nrid' name='nrid' value="${list.nrid}"/></td>
														<td width="50%"><input type='text' id='nrmc!!@@!!id' name='nrmc' style='width:150px' value="${list.nrmc }" maxlength="50"/></td>
														<td width="10%">
															<input type='checkbox' onclick="xzknsfz(this)" name="fzlx"/>
															<input type="hidden" name="fzlxH" value="${list.fzlx}"/>
															<input type="hidden" name="fzH" value="${list.fz}"/>
														</td>
														<td width="25%" id="knfz">

														<input type='text' id='knfz' name='knfz' size='10' maxlength='10' value="${list.fz}" onblur="checkInt(this);" style="width:30px"/>

														</td>
														<td width="25%" style="display:none" id="qjfz">

														<input type='text' id='knqjksfz' name='knqjksfz' size='2' maxlength='4' onblur="checkInt(this);" style="width:30px"/> -
														<input type='text' id='knqjjsfz' name='knqjjsfz' size='2' maxlength='4' onblur="checkInt(this);" style="width:30px"/>


														</td>
														
													</tr>
												</logic:iterate>
											</tbody>
										</thead>
									</table>
								</td>
							</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
				</table>
				
				</div>
				
				
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="save();return false;">
										保 存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
			<div style="display:none;">
				<table>
					<tbody id="hidden_knsrdzb">
						<tr>
							<td width="7%">
							<img src="images/knsrd/jianhao.gif" onclick="delTr(this);return false;"/>
							</td>
							<td style="display:none"><input type='text' id='sxid' name='sxid' value=""/></td>
							<td width="23%" ><input type='text' id='sxmc!!@@!!id' name='sxmc' style='width:160px' maxlength="50"/></td>
							<td width="10%"><input type='text' id='qzbl' name='qzbl' size='5' maxlength='5' onblur="checkInt(this);"/>%</td>
							<td width="60%">
								<table width="100%" border="0" class="datelist" id="knsrdzbnr" style="margin:2px auto;">
									<thead>
										<tr>
											<td width="7%">
												<img src="images/knsrd/jiahao.gif" onclick="addZbnr(this);return false;"/>
											</td>
											<td width="15%">
												<font class="red">*</font>指标内容
											</td>
											<td width="10%">
												区间分
											</td>
											<td width="68%" >
												<font class="red">*</font>分值
											</td>
											
										</tr>
										<tbody id="tbody_knsrdzbnr">
										</tbody>
									</thead>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div style="display: none;">
				<table >
					<tbody id="hidden_knsrdzbnr">
						<tr id="knsrdzbxxnr">
							
							<td width="15%">
							 <img src="images/knsrd/jianhao.gif" onclick="delTr(this);return false;"/>
							</td>
							<td style="display:none"><input type='text' id='nrid' name='nrid' value=""/></td>
							<td width="50%">
								<input type='text' id='nrmc!!@@!!id' name='nrmc'
									style='width: 150px' maxlength="50"/>
							</td>
							<td width="10%">
								<input type='checkbox' onclick="xzknsfz(this)" name="fzlx" />
							</td>
							<td width="25%" id="knfz">
							<input type='text' id='knfz' name='knfz' size='10' maxlength='10' style="width:30px" onblur="checkInt(this);"/>
							</td>
							<td width="25%" style="display:none" id="qjfz">
							<input type='text' id='knqjksfz' name='knqjksfz' size='2' maxlength='10' style="width:30px" onblur="checkInt(this);"/> -
							<input type='text' id='knqjjsfz' name='knqjjsfz' size='2' maxlength='10' style="width:30px" onblur="checkInt(this);"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</html:form>
	</body>
</html>


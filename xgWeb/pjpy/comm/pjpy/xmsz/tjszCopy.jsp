<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/comm/commFunction.js"></script>
		<script src="js/check.js"></script>
		<script>
			function setTjz(checkboxName,tjzid){
				var knjb = jQuery("input[name='"+checkboxName+"']");
				var temp = '';
				
				for (var i = 0 ; i < knjb.length ; i++){
					if (knjb[i].checked){
						temp+=knjb[i].value;
						temp+=',';
					}
				}
				temp = temp.substring(0,temp.length-1);
				jQuery(tjzid).val(temp);
			}
			
			function setQtxz(kcxzId,khfsId,qtxzId){
				jQuery(qtxzId).val('kcxz:'+jQuery(kcxzId).val()+','+'khfs:'+jQuery(khfsId).val());
			}
			
			
			
			//初始化困难生和前置项目的条件置
			jQuery(function(){
				var tsgs = jQuery('input[name="tsgs"]');
				jQuery.each(tsgs,function(i,n){
					if (n.value == 'kns' || n.value == 'qzxm'){
						var tr = jQuery(tsgs[i]).parents("tr:first");//找到父节点tr
					 	var box = jQuery("input[type='checkbox']", tr);//找到tr下的checkbox
						var tjz = jQuery("input[name='tjz']", tr).val().split(',');//找到tr下的条件值
						for (var c = 0 ; c < box.length; c++){
							if (jQuery.inArray(box[c].value,tjz) >-1){
								box[c].checked=true;
							}
						}
					}
				})
			})
			
			//保存条件设置
			function saveTjsz(type){

				
				if(type == "all"){
					var check_num = jQuery('input[name=ck_]').length;
					
					if(check_num == 0){
						alertError("请增加条件");
                        return false;
					}
					var tjz_num = jQuery('input[name=tjz]').length;
					for(var i=0;i<tjz_num;i++){
						var tjz = jQuery('input[name=tjz]')[i].value;
						if(tjz == ""){
							alertError("第"+(parseInt(i)+1)+"行条件值为空，请确认！");
							return false;
						}
					}
<%--					 if($('div_1')==null){;--%>
<%--                        alertInfo("请增加条件");--%>
<%--                        return false;--%>
<%--					 }--%>
                     if($('xmdm').value==""){
                         alertInfo("项目名称不能为空!");
                         return false;
                     }else{
                    	 if(checkInputValue()){
					       saveUpdate('pjpy_ty_tjsz.do?method=tjsz&doType=save','')
                    	 }
                     }
				}else{
					saveUpdate('pjpy_ty_tjsz.do?method=tjszUpdate&doType=save','')
				}
			}
			function checkInputValue(){
                 var num =document.getElementsByName("ck_").length;
                 for(var i=1;i<=num;i++){
                     if(document.getElementById("tjdm_"+i+"")!=null){
               	var tjdm_0 = document.getElementById("tjdm_"+i+"").value;
      				var tjfw_0= document.getElementById("tjfw_"+i+"").value;
      				for(var j=2;j<=num;j++){
          				if(document.getElementById("tjdm_"+j+"")!=null){
      					var tjdm_1 = document.getElementById("tjdm_"+j+"").value;
      					var tjfw_1 = document.getElementById("tjfw_"+j+"").value;
      					if(i==j){
      						continue;
      					}
      					else if((tjdm_0==tjdm_1)&&(tjfw_0==tjfw_1)){
                            alertInfo("有二行评奖条件和范围是一样的,请重新选择!");
                            return  false;
                       }
      				}
                       }
                     }
                 }
                 return true;
			}
			function tjzSz(value){
                   refresh('pjpy_ty_tjsz.do?method=tjsz&doType=save');
			}
			//增加加分项目
			var count=1;
			function addJfxm(){
				var div_ = "div_index";
				var divHtml = $(div_).innerHTML;

				divHtml +="<div id=\"div_"+count+"\"  >";
				divHtml +="<table width=\"100%\" indexId=\"index\" id=\"tab_"+count+"\">";
				divHtml +="<tr>";
				divHtml +="<td width=\"5%\">";
				divHtml +="<input type=\"checkbox\" name=\"ck_\" id=\"ck_"+count+"\"/>"
				divHtml +="</td>";
				divHtml +="<td width=\"25%\">"; 
				divHtml +="<select  id=\"tjdm_"+count+"\" name=\"tjdm\" style=\"width: 150px\" onchange=\"loadTjz(this.value,"+count+");loadTjgx(this.value,"+count+")\";\" ></select>"
				divHtml +="</td>";
				divHtml +="<td width=\"20%\">";
				divHtml +="<select id=\"tjgx_"+count+"\" name=\"tjgx\" style=\"width: 80px\"></select>"
				divHtml +="</td>";
				
				divHtml +="<td width=\"25%\"  id=\"input%_td_"+count+"\" style=\"\">";
				//divHtml +="<input type=\"text\" name=\"tjz\" id=\"in_tjz_"+count+"\"   style=\"width: 80px\"/>%";
				divHtml +="</td>";
				
				divHtml +="<td width=\"25%\">";
				divHtml +="<select id=\"tjfw_"+count+"\" name=\"tjfw\" style=\"width:150px\"></select>"	
				divHtml +="<input type=\"hidden\" name=\"qtxz\" id=\"qtxz\"/>"
				divHtml +="</td>";
				divHtml +="</tr>";
				divHtml +="</table>";	
				divHtml +="</div>";

			    //setTimeout("$('"+count_id+"').value = '"+count+"'",100);
				$(div_).innerHTML = divHtml;
				loadXian("",count);
				loadTjfw("",count);
				loadTjz($("tjdm_"+count+"").value,count);
				loadTjgx($("tjdm_"+count+"").value,count);
				count++;
			}
			
			//删除加分项目
			function delJfxm(count){
				var ch_name = "ck_";
				var num =  document.getElementsByName(ch_name).length;

				for(var i=(num-1);i>=0;i--){
					var obj = document.getElementsByName(ch_name)[i];
					var id = obj.id;
					
					if(obj.checked){
						var div_id = "div_"+id.replace("ck_","");
						if(div_id){
							$(div_id).outerHTML = "";
						}
					}
				}
			}
			
			//全选
			function allChoose(xmdm){
				
			}
			function loadTjgx(text,count){
				var xmdm  = $('xmdm').value;
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTjgx&xmdm='+xmdm+'&tjdm='+text,
					dataType:'json',
					success:function(data){
						jQuery("#tjgx_"+count+"").empty();
						
						for (var i = 0 ; i < data.length; i++){
							jQuery("#tjgx_"+count+"").append('<option value="'+data[i].en+'">'+data[i].cn+'</option>');
						}
					}
				});
			}
			function loadTjz(text,count){
				var xmdm  = $('xmdm').value;
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTjz&xmdm='+xmdm+'&tjdm='+text,
					dataType:'json',
					success:function(data){
						jQuery("#tjz_"+count+"").empty();
						if(data.length>0){
						for (var i = 0 ; i < data.length; i++){
							if(data[i].tsgs!=null){
								jQuery("#tab_"+count+" tr td:eq(3)").html();
								jQuery("#tab_"+count+" tr td:eq(3)").html("<input id=\"tjz_"+count+"\" name=\"tjz\" style=\"width:80px\"/>%");
								return ;
								//jQuery("#tab_"+count+" tr").each(function () {                
                               //       $(this).children('td').eq(3).html();
				               //       $(this).children('td').eq(3).html("<input/>");
                               //  });
								//$("sel_td_"+count+"").style.display="none";
								//$("input_td_"+count+"").style.display="none";
								//$("input%_td_"+count+"").style.display="";
							}else{
								//$("sel_td_"+count+"").style.display="";
								//$("input_td_"+count+"").style.display="none";
								//$("input%_td_"+count+"").style.display="none";
								//jQuery("#tab_"+count+" tr").each(function () {                
                                // $(this).children('td').eq(3).html();
				                // $(this).children('td').eq(3).html("<select id=\"tjz_"+count+"\"/>");
                                // });
							   
							}
							
						}
						jQuery("#tab_"+count+" tr td:eq(3)").html();
						jQuery("#tab_"+count+" tr td:eq(3)").html("<select  id=\"tjz_"+count+"\" name=\"tjz\" style=\"width: 80px\"></select>");
						  for (var i = 0 ; i < data.length; i++){
							  jQuery("#tjz_"+count+"").append('<option value="'+data[i].en+'">'+data[i].cn+'</option>');
						  }
						}else{
							jQuery("#tab_"+count+" tr td:eq(3)").html();
							jQuery("#tab_"+count+" tr td:eq(3)").html("<input id=\"tjz_"+count+"\" name=\"tjz\" style=\"width:80px\"/>");
							//jQuery("#tab_"+count+" tr").each(function () {                
                            //    $(this).children('td').eq(3).html();
			                //    $(this).children('td').eq(3).html("<input />%");
                          // });
							//$("sel_td_"+count+"").style.display="none";
							//$("input_td_"+count+"").style.display="";
							//$("input%_td_"+count+"").style.display="none";
						}
					}
				});
			}
			
			function loadTjfw(text,count){
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTjfw&xmdm='+text,
					dataType:'json',
					success:function(data){
						jQuery("#tjfw_"+count+"").empty();
						
						jQuery("#tjfw_"+count+"").append('<option value="all">全体</option>');
						for (var i = 0 ; i < data.length; i++){
							jQuery("#tjfw_"+count+"").append('<option value="'+data[i].xydm+'">'+data[i].xymc+'</option>');
						}
					}
				});
			}
			function loadXian(text,count){
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTjk&xmdm='+text,
					dataType:'json',
					async: false,
					success:function(result){
						jQuery("#tjdm_"+count+"").empty();
						//jQuery("#tjdm_"+count+"").append('<option value=""></option>');
						for (var i = 0 ; i < result.length; i++){
							jQuery("#tjdm_"+count+"").append('<option value="'+result[i].tjdm+'">'+result[i].tjmc+'</option>');
						}
					}
				});
			}
			
			function getXmtj(obj){
				 var xmdm = jQuery(obj).val();
				 var div_ = "div_index";
				 $(div_).innerHTML="";
				 count=1;
				 jQuery.post('pjpy_ty_tjsz.do?method=getTjList',{xmdm:xmdm},function(data){
				 	for (var i = 0 ; i < data.length ; i++){
				 		jQuery.ajaxSetup({async:false});
				 		addJfxm();
				 		
				 		loadXian(data[i].tjdm,i+1);
						jQuery('#tjdm_'+(i+1)).val(data[i].tjdm);
                       // alert(jQuery('#tjdm_'+(i+1)).value());

						//loadTjgx($("tjdm_"+(i+1)+"").value,i+1);
						//loadTjz($("tjdm_"+(i+1)+"").value,i+1);
						
						loadTjgx(data[i].tjdm,i+1);
				 		loadTjz(data[i].tjdm,i+1);
				 		//loadTjfw($("tjdm_"+(i+1)+"").value,i+1);
				 		
					
				 		
				 		
				 		
				 		
						jQuery('#tjgx_'+(i+1)).val(data[i].gx);
						jQuery('#tjz_'+(i+1)).val(data[i].tjz);
						jQuery('#tjfw_'+(i+1)).val(data[i].tjfw);
						jQuery.ajaxSetup({async:true});
						if((data.length)-i==1){
                                  break;
							}
				 	}
				 },'json')
			}
			
		</script>
	</head>

	<body>
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>项目设置-评奖条件设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
            <p>
				1.维护条件时, 不能有两条相同的记录,否则将保存失败！
			</p>
      	</div>
		 <!-- 流程按钮控制 -->
		<html:form action="/pjpy_ty_tjsz" method="post">
			<html:hidden property="pkValue"/>
			<input type="hidden" name="tjNum" id="tjNum" value="${tjNum }"/>
			<div class="tab">
				<table class="formlist">
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>项目名称
							</th>
							<td colspan="3">
								<html:select property="xmdm" style="width:200px" 
											 onchange="getXmtj(this)"
											 styleId="xmdm" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table class="dateline" style="width:100%">
									<tbody>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			<table class="formlist" border="0" align="center">
				<thead>
						<tr>
							<th colspan="4">
								<span>条件设置</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td colspan="4">
								<div style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">	
											<table width="100%">
												<tr>
													<td colspan="6">
														<span>
															<button type="button" onclick="addJfxm()" id="buttonAdd" style="width: 80px">
																增加条件
															</button>
															<button type="button" onclick="delJfxm()" id="buttonDel"   style="width: 80px">
																删除条件
															</button>
														</span>
													</td>
												</tr>
												<tr>
													<td colspan="6">
													
														<div>
															<div>
																<input type="hidden" name="hid_count_" id="hid_count_" value=""/>
																<table width="100%">
																	<thead>
																		<tr>
																			<td width="5%"><input type="checkbox" id="ch_all_" onclick=""/></td>
																			<td width="25%">条件名称</td>
																			<td width="20%">关系</td>
																			<td width="25%">条件值</td>
																			<td width="25%">范围</td>
																		</tr>
																	</thead>
																</table>
															   </div>
															   <div id="div_index" ></div>
																	<!-- <div id="div_">
																		<table width="100%"  >
																			<tr >
																				<td width="5%">
																					<input type="checkbox" name="ck_" id="ck_"/>
																					<input type="hidden" name="xmdm" value=""/>
																				</td>
																				<td width="25%">
														                               <select id="tjdm_${index }" name="tjdm" style="width: 80px"  onchange="loadTjgx(this.value)">
																							<logic:iterate name="tjkList" id="tjk">
																								<option value="${tjk.tjdm }">${tjk.tjmc }</option>
																							</logic:iterate>
																					 </select>
																				</td>
																				<td width="20%">
																						<select id="tjgx_${index }" name="tjgx" style="width: 80px">
																							<logic:iterate name="gxList" id="gx">
																								<option value="${gx.en }">${gx.cn }</option>
																							</logic:iterate>
																						</select>
																				</td>
																				<td width="25%">
																				    <input id="tjz_${index }" name="tjz" style="width: 80px"/>
																				</td>
																				<td width="25%">
																					<select id="tjfw_${index }"name="tjfw" style="width: 80px">
																						<option value="all">全体</option>
																						<logic:iterate name="tjfwList" id="tjfwRs">
																							<option value="${tjfwRs.xydm }">${tjfwRs.xymc }</option>
																						</logic:iterate>
																					</select>
																					<input type="hidden" name="qtxz" id="qtxz_${index }"/>
																				</td>
																			</tr>
																		</table>
																	</div> -->
														</div>
													</td>
												</tr>
											</table>
								</div>
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:equal value="update" name="doType">
										<button type="button" name="保 存" onclick="saveTjsz('update')">
											保 存
										</button>
									</logic:equal>
									<logic:notEqual value="update" name="doType">
										<button type="button" name="保 存" onclick="saveTjsz('all')">
											保 存
										</button>
									</logic:notEqual>
									<button type="button" name="关 闭" onclick="confirmInfo('本页面设置的项目条件还未保存，您确定要关闭吗?',function(){if(arguments[0]=='ok'){window.close();}})">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>
		</html:form>
		
		<input type="hidden" id="message" value="${message }"/>
		<input type="hidden" id="doType" value="${message }"/>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

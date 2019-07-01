<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
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
			
			
			
			//��ʼ����������ǰ����Ŀ��������
			jQuery(function(){
				var tsgs = jQuery('input[name="tsgs"]');
				jQuery.each(tsgs,function(i,n){
					if (n.value == 'kns' || n.value == 'qzxm'){
						var tr = jQuery(tsgs[i]).parents("tr:first");//�ҵ����ڵ�tr
					 	var box = jQuery("input[type='checkbox']", tr);//�ҵ�tr�µ�checkbox
						var tjz = jQuery("input[name='tjz']", tr).val().split(',');//�ҵ�tr�µ�����ֵ
						for (var c = 0 ; c < box.length; c++){
							if (jQuery.inArray(box[c].value,tjz) >-1){
								box[c].checked=true;
							}
						}
					}
				})
			})
			
			//������������
			function saveTjsz(type){
				if(type == "all"){
                    var check_num = jQuery('input[name=ck_]').length;
					if(check_num == 0){
						alertError("����������");
                        return false;
					}

					var tjz_num = jQuery('input[name=tjz]').length;
					for(var i=0;i<tjz_num;i++){
						var tjz = jQuery('input[name=tjz]')[i].value;
						if(tjz == ""){
							alertError("��"+(parseInt(i)+1)+"������ֵΪ�գ���ȷ�ϣ�");
							return false;
						}
					}
					 
                     if($('xmdm').value==""){
                         alertInfo("��Ŀ���Ʋ���Ϊ��!");
                         return false;
                     }else{
                    	 if(checkInputValue()){
                    		 confirmInfo("��Ŀ�����������������,�������ò��ɷ���,�����Ҫ�޸��뵽��Ŀ��������������ֱ���޸�,�Ƿ�Ҫ��������һ��������?",mbDownLoad);
                      	 }
                    	 
					     
                     }
				}else{
					saveUpdate('pjpy_ty_tjsz.do?method=tjszUpdate&doType=save','')
				}
			}
			function mbDownLoad(tag){
				if(tag=='ok'){
					refreshForm('pjpy_ty_tjsz.do?method=tjszFlow&doType=save')
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
                              alertInfo("�ж������������ͷ�Χ��һ����,������ѡ��!");
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
			//���Ӽӷ���Ŀ
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
				divHtml +="<select  id=\"tjdm_"+count+"\" name=\"tjdm\" style=\"width: 120px\" onchange=\"loadTjz(this.value,"+count+");loadTjgx(this.value,"+count+")\";\" ></select>"
				divHtml +="</td>";
				divHtml +="<td width=\"20%\">";
				divHtml +="<select id=\"tjgx_"+count+"\" name=\"tjgx\" style=\"width: 80px\"></select>"
				divHtml +="</td>";
				
				//divHtml +="<td width=\"25%\" id=\"sel_td_"+count+"\" style=\"\">";
				//divHtml +="<input type=\"text\" name=\"tjz\" id=\"tjz\" style=\"width: 80px\" onkeyup=\"checkInputNum(this)\" maxlength=\"5\"  />"
				//divHtml +="<select  id=\"tjz_"+count+"\" name=\"tjz\" style=\"width: 80px\"></select>"
				//divHtml +="</td>";
				
				//divHtml +="<td width=\"25%\"  id=\"input_td_"+count+"\" style=\"\">";
				//divHtml +="<input type=\"text\" name=\"tjz\" id=\"in_tjz_"+count+"\"   style=\"width: 80px\"/>";
				//divHtml +="</td>";
				
				divHtml +="<td width=\"25%\"  id=\"input%_td_"+count+"\" style=\"\">";
				//divHtml +="<input type=\"text\" name=\"tjz\" id=\"in_tjz_"+count+"\"   style=\"width: 80px\"/>%";
				divHtml +="</td>";
				
				divHtml +="<td width=\"25%\">";
				divHtml +="<select id=\"tjfw_"+count+"\" name=\"tjfw\" style=\"width:80px\"></select>"	
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
			
			//ɾ���ӷ���Ŀ
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
			
			//ȫѡ
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
						
						jQuery("#tjfw_"+count+"").append('<option value="all">ȫ��</option>');
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


			//��ʾ��������Ϣ
				function showHelpDiv(){

					if($("div_help")){
						if($("div_help").style.display == "none"){
							$("div_help").style.display = "";
						}else{
							$("div_help").style.display = "none";
						}
					}
					
				}
		</script>
	</head>

	<body>
	<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��������-��Ŀ����-������������</a>
				</p>
				
			<!-- ���߰��� -->
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			<!-- ���߰��� end -->
	  </div>
	<!-- ���̰�ť���� -->
		<div class="flow-steps">
			  <ol class="num5">
			    <li class="done" style = "width:19%"><span class="first">1. ��Ŀ�������</span></li>
			    <li class="done current-prev" style = "width:19%"><span>2. ��Ŀ������Χ����</span></li>
			    <li class="current" style = "width:19%"><span>3. ��Ŀ��������</span></li>
			    <li class="current-next" style = "width:19%"><span>4. ��Ŀʱ������</span></li>
			    <li class="last"><span>5. ��Ŀ��������</span></li>
			  </ol>
        </div>
        
        <!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��ģ�������������Ŀ�����á��޸ġ�ɾ���Ĺ��ܡ����������û�ý�ѧ��ĸ���������<br/>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/pjpy_ty_tjsz" method="post">
			<html:hidden property="pkValue"/>
			<input type="hidden" name="tjNum" id="tjNum" value="${tjNum }"/>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<div class="tab">
				<table class="formlist">
					<tbody>
						<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td colspan="3">
								<html:select property="pjxn" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td colspan="3">
								<html:select property="pjxq" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�������
							</th>
							<td colspan="3">
								<html:select property="pjnd" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td colspan="3">
								<html:select property="xmdm" style="width:200px" styleId="xmdm" onmouseover="" disabled="true">
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
								<span>��������</span>
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
																��������
															</button>
															<button type="button" onclick="delJfxm()" id="buttonDel"   style="width: 80px">
																ɾ������
															</button>
														</span>
													</td>
												</tr>
												<tr>
													<td colspan="6">
													
														<div id="div_index" >
															<div>
																<input type="hidden" name="hid_count_" id="hid_count_" value=""/>
																<table width="100%">
																	<tr>
																		<td width="5%"><input type="checkbox" id="ch_all_" onclick=""/></td>
																		<td width="25%">��������</td>
																		<td width="20%">��ϵ</td>
																		<td width="25%">����ֵ</td>
																		<td width="25%">��Χ</td>
																	</tr>
																</table>
															   </div>
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
																						<option value="all">ȫ��</option>
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
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal value="update" name="doType">
										<button type="button" name="�� ��" onclick="saveTjsz('update')">
											�� ��
										</button>
									</logic:equal>
									<logic:notEqual value="update" name="doType">
										<button type="button" name="��һ��" onclick="saveUpdate('pjpy_ty_sjsz.do?method=sjszFlow','')">
											   ����
										</button>
										<button type="button" name="�� ��" onclick="saveTjsz('all')">
											�� ��
										</button>
									</logic:notEqual>
									<button type="button" name="�� ��" onclick="winClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

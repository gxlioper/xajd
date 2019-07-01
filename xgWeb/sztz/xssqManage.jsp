<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function displayInfo(obj){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var tr = jQuery('tr',tbody);
				var text = "";
				
				for (var i = 1 ; i < tr.length ; i++){
					var style = jQuery(tr[i]).attr('style');
					
					if (style.trim() == ''){
						style = 'display:none';
						text = "չ��";
					} else {
						style = 'display:';
						text = "����";
					}
					jQuery(tr[i]).attr('style',style);
				}
				jQuery(obj).text(text);
			}
			
			function saveXssq(obj,index){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var xmid = jQuery('input[name=xmid]',tbody).val();
				var xh = jQuery('#xh').val();
				showTopWin('sztz.do?method=sztzXmsq&xmid='+xmid+'&xh='+xh,600,400);
				
				
			}


			function ckXssq(obj,index){
				var tbody = jQuery(obj).parents('tbody').eq(0);
				var xmid = jQuery('input[name=xmid]',tbody).val();
				var xh = jQuery('#xh').val();
				showTopWin('sztz.do?method=ckXmsq&xmid='+xmid+'&xh='+xh,600,400);
				
				
			}
			
			<%--
			jQuery(function(){
				if (jQuery('#display0')){
					jQuery('#display0').click();
				}
			})
			
			confirmInfo("������޷�������ȷ��������",function(t){
					if (t=='ok'){
						var tbody = jQuery(obj).parents('tbody').eq(0);
						var id = jQuery('input[name=id]',tbody).val();
						var xmid = jQuery('input[name=xmid]',tbody).val();
						var shlcid = jQuery('input[name=shlcid]',tbody).val();
						var cyjs = jQuery('input[type=radio]:checked[name=cyjs'+index+']',tbody).val();
						var sfcx = jQuery('input[type=radio]:checked[name=sfcx'+index+']',tbody).val();
						var cgms = jQuery('textarea[name=cgms]',tbody).val();
						var bz = jQuery('textarea[name=bz]',tbody).val();
						var shzt = jQuery('input[name=shzt]',tbody).val();
						
						var sztzModel = {
							id:id,
							xh:jQuery('#xh').val(),
							xmid:xmid,
							cyjs:encodeURI(cyjs),
							sfcx:encodeURI(sfcx),
							cgms:encodeURI(cgms),
							bz:encodeURI(bz),
							shlcid:shlcid,
							shzt:encodeURI(shzt)
						}
						
						jQuery.post('sztz.do?method=xmsqAjaxSave',sztzModel,function(data){
							jQuery('input[name=id]',tbody).val(data.id);
							alertInfo(data.message);
						},'json')
						
						if ('�˻�' == shzt){
							jQuery('input[name=shzt]',tbody).val('δ���');
							jQuery('#sm'+index).text('���״̬��δ���');
						}
					}
				});	
			
			--%>
			
			function initHxnl(obj){
				if ('' != jQuery(obj).val()){
					var params = {kmdm:jQuery(obj).val()};
					jQuery.ajaxSetup({async:false});
					jQuery.post('sztzAjax.do?method=initHxnl',params,function(data){
						jQuery('#hxnl').empty();
						
						jQuery('<option value=""></option>').appendTo('#hxnl');
						for (var i = 1 ; i < data.length; i++){
							var option="<option value='"+data[i].dm+"'>"+data[i].mc+"</option>";
								
							jQuery(option).appendTo('#hxnl');
						}
					},'json');
					
					jQuery('#hxnl').val('${sztzActionForm.queryequals_hxnl}');
					jQuery.ajaxSetup({async:true});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<logic:equal value="1" name="sqsjTag" >
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					����ѧ���걨ʱ����,����${cssz.sbkssj } �� ${cssz.sbjssj } ���걨��
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<script defer>
				jQuery('button[name=save]').attr('disabled',true);
				jQuery('button[name=save]').attr('class','disabled');
			</script>
		</logic:equal>
		<logic:equal value="2" name="sqsjTag" >
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��δ����ѧ���걨ʱ�䣬���ܽ���ѧ�����롣
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<script defer>
				jQuery('button[name=save]').attr('disabled',true);
				jQuery('button[name=save]').attr('class','disabled');
			</script>
		</logic:equal>
		<logic:equal value="0" name="sqsjTag">
		<html:form action="/sztz">
			<input type="hidden" id="url" name="url"
				value="/sztz.do?method=xssqManage" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-xymc-zymc-bjmc" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
								<p class="floatright normal">
									ѧ�꣺${xn } ѧ�ڣ�${xq } &nbsp;&nbsp;
								</p>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="35%">
								<logic:equal value="stu" name="userType">
									${stu.xh }
									<html:hidden property="xh" styleId="xh" value="${stu.xh}"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}"
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')" />
									<logic:notEqual value="modi" name="oper">
										<button onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th width="15%">
								����
							</th>
							<td width="35%">
								${stu.xm }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${stu.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${stu.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${stu.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${stu.bjmc }
							</td>
						</tr>
					</tbody>

					<logic:present name="xmList">
						<thead>
							<tr>
								<th colspan="4">
									<span>��Ŀ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" class="tdbox">
									<!-- �������� -->
									<div class="searchtab">
										<table width="100%" border="0" id="searchTab">
											<tfoot>
												<tr>
													<td colspan="6">
														<div class="btn">
															<button class="btn_cx" id="search_go"
																onclick="allNotEmpThenGo('sztz.do?method=xssqManage&nj='+${nj}+'')">
																�� ѯ
															</button>
															<button class="btn_cz" onclick="searchReset();return false;">
																�� ��
															</button>
														</div>
													</td>
												</tr>
											</tfoot>
											<tbody>
												<tr>
													<th>�꼶</th>
													<td>
														<logic:empty name="nj">
															<html:select property="queryequals_nj" styleId="nj" style="width:120px">
																<html:option value="">ȫ��</html:option>
																<html:options collection="njList" property="nj" labelProperty="nj" />
															</html:select>
														</logic:empty>
														<logic:notEmpty name="nj">
															<html:select property="queryequals_nj" styleId="nj" value="${nj }" disabled="true" style="width:120px">
																<html:option value="">ȫ��</html:option>
																<html:options collection="njList" property="nj" labelProperty="nj" />
															</html:select>
														</logic:notEmpty>
									            	</td>
									            	<th>������Ŀ</th>
													<td>
														<html:select property="queryequals_kmdm" onchange="initHxnl(this)" styleId="kmdm" style="width:120px">
															<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
														</html:select>
													</td>
													<th>��������</th>
													<td>
														<html:select property="queryequals_hxnl" styleId="hxnl" style="width:120px">
															
														</html:select>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<table width="100%" class="datelist">
										<logic:iterate id="x" name="xmList" indexId="i">
												<tbody>
												<tr class="tgsh">
													<td>
														��ţ�${i+1 }
													</td>
													<td>
														<input type="hidden" name="id" value="${x.id }" />
														<input type="hidden" name="xmid" value="${x.xmid }" />
														<input type="hidden" name="shlcid" value="${x.shlcid }" />
														<input type="hidden" name="shzt" value="${x.shzt}" />
														��Ŀ���ƣ�
														<a
															href="javascript:showTopWin('sztz.do?method=xmsbView&doType=view&pkValue=${x.xmid }','750','550');"
															class="pointer" style="color:#0A63BF"> ${x.xmmc } </a>
													</td>
													<td width="30%">
														<div id="sm${i }" style="color:red">��˽��:${x.shzt}</div>
													</td>
													<td>
														<div class="btn">
															<button name="save" onclick="saveXssq(this,${i })" id="save${i }">
																����
															</button>
															<%--
															<button onclick="displayInfo(this);" id="display${i}">
																չ��
															</button>
															--%>
															<logic:notEqual name="x" property="shzt" value="">
																<logic:notEqual name="x" property="shzt" value="��ͨ��">
																	<logic:notEqual name="x" property="shzt" value="�˻�">
																		<script defer="defer">
																			jQuery('#save${i}').attr('disabled',true);
																			jQuery('#save${i}').attr('class','disabled');
																		</script>
																		<button onclick="ckXssq(this,${i })" id="display${i}">
																			�鿴
																		</button>
																	</logic:notEqual>
																</logic:notEqual>
															</logic:notEqual>
															<logic:equal value="" name="x" property="shzt">
																<script defer="defer">
																	if (Number('${x.rssx}') <= Number('${x.ysqrs}')){
																		jQuery('#save${i}').attr('disabled',true);
																		jQuery('#save${i}').attr('class','disabled');
																		jQuery('#sm${i }').text('˵�����걨�����Ѵ�����!');
																	}
																</script>
															</logic:equal>
														</div>
													</td>
												</tr>
												
												<tr style="display:none">
													<td colspan="2">
														��Ŀ���ƣ� ${x.kmmc }
													</td>
													<td>
														�����������ƣ� ${x.hxnlmc }
													</td>
													<td>
														��Ŀ���ͣ� ${x.xmlxmc }
													</td>
												</tr>
												<tr style="display:none">
													<td colspan="2">
														�����ɫ��
														<logic:equal value="��֯" name="x" property="cyjs">
															<input type="radio" name="cyjs${i }" value="����" />
															����
															<input type="radio" name="cyjs${i }" value="��֯"
																checked="true" />
															��֯
														</logic:equal>
														<logic:notEqual value="��֯" name="x" property="cyjs">
															<input type="radio" name="cyjs${i }" value="����"
																checked="true" />
															����
															<input type="radio" name="cyjs${i }" value="��֯" />
															��֯
														</logic:notEqual>
													</td>
													<td>
														�Ƿ����ޣ�

														<logic:equal value="��" name="x" property="sfcx">
															<input type="radio" name="sfcx${i }" value="��"
																checked="true" />
															��
															<input type="radio" name="sfcx${i }" value="��" />
															��
														</logic:equal>

														<logic:notEqual value="��" name="x" property="sfcx">
															<input type="radio" name="sfcx${i }" value="��" />
															��
															<input type="radio" name="sfcx${i }" value="��"
																checked="true" />
															��
														</logic:notEqual>
													</td>
													<td>
														�������ޣ�${x.rssx }(��)
													</td>
													
												</tr>
												<tr style="display:none">
													<td align="right" width="10%">
														�ɹ�����
													</td>
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="cgms" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${x.cgms }"></html:textarea>
													</td>
												</tr>
												<tr style="display:none">
													<td align="right">
														��ע
													</td>
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="bz" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${x.bz }"></html:textarea>
													</td>
												</tr>
											</tbody>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</tbody>
					</logic:present>
				</table>
			</div>
		</html:form>
		</logic:equal>
	</body>
</html>

<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			function checkJxdm(obj){
				var value = jQuery(obj).val();
				if(value!=""){
					var jxdm = jQuery('input[name=jxdm][value='+value+']').length;
					if (jxdm > 1){
						ymPrompt.alert({message:'重复的项目奖项,请确认!',handler:function(m){
								if ('ok' == m){
									jQuery(obj).focus();
								}
							},maskAlpha:0.01
						});
					}
				}
			}
		
		
			function addTr(isMore){
				var trs = jQuery('tr',jQuery('#sljxTab')).length;
				var tr = "<tr><td><input type='checkbox'/></td>";
					tr+="<td> <input type='text' name='jxdm' maxlength='10' /> </td>";
					tr+="<td> <input type='text' name='jxmc' maxlength='30'/> </td>";
					tr+="<td> <input type='text' name='jxfs' maxlength='10' onblur='checkInputNum(this)'/> </td></tr>"
					
				if (isMore){
					var num = jQuery('#numAdd').val();
					if ('' != num){
						confirmInfo('您确定要生成'+num+'行吗?',function(t){
							if ('ok'==t){
								trs=Number(num)+trs;
								
								if (trs> 20){
									alertInfo('生成的行过多,项目奖项限20个以内!');
									return false;
								}
								for (var i = 0 ; i < num ; i++){
									jQuery('#sljxTab').append(tr);
								}
							}
						})
					}
				} else {
					if (trs> 20){
						alertInfo('生成的行过多,项目奖项限20个以内!');
						return false;
					}
					jQuery('#sljxTab').append(tr);
				}
			}
			
			function delTr(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				
				if (checkbox.length > 0){
					for (var i = 0 ; i < checkbox.length ; i++){
						jQuery(checkbox[i]).parents("tr").eq(0).remove();
					}
				} else {
					alertError('请选择您要删除的行!');
				}
			}
			
			function initHxnl(obj){
				var params = {kmdm:obj.value};
				jQuery.post('sztzAjax.do?method=initHxnl',params,function(data){
					jQuery('#hxnl').empty();
					
					for (var i = 0 ; i < data.length; i++){
						if(data[i].dm==""||data[i].dm==null){
							var option="<option value=''>"+data[i].mc+"</option>";
						}else{
							var option="<option value='"+data[i].dm+"'>"+data[i].mc+"</option>";
							}
						
						jQuery(option).appendTo('#hxnl');
					}
				},'json');
			}
			
			function selectAll(obj){
				jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
			}
			
			function saveXmsb(){
				var jxdms = document.getElementsByName("jxdm");
				for (var i = 0 ; i < jxdms.length; i++){
					if(jxdms[i].value==""){
						alertError("奖项代码不能为空，请确认！");
						return false;
						}
					for (var j = i+1 ; j < jxdms.length; j++){
						if((jxdms[i].value!="")&&(jxdms[j].value!="")&&jxdms[i].value==jxdms[j].value){
							alertError("奖项代码重复，请确认！");
							return false;
						}
						
					}
				}
				
				var flg = checkSjTj('jbkssj','举办开始时间','jbjssj','举办结束时间');
				var doType = jQuery('#doType').val();
				if (flg){
					if(doType=="update"){
						saveUpdate('sztz.do?method=xmsb&doType=update','xn-xq-kmdm-hxnl-xmlx-xmmc-shlcid-jcf-jbkssj-jbjssj-rssx')
						}else{
							saveUpdate('sztz.do?method=xmsb&doType=save','xn-xq-kmdm-hxnl-xmlx-xmmc-shlcid-jcf-jbkssj-jbjssj-rssx')
							}
					
				}
			}

			function iner(){
				var hiddenXn = jQuery('#hiddenXn').val();
				var hiddenXq = jQuery('#hiddenXq').val();
				if(jQuery('#xn').val()==null||jQuery('#xn').val()==""){
					jQuery('#xn').val(hiddenXn);
					}
				if(jQuery('#xq').val()==null||jQuery('#xq').val()==""){
					jQuery('#xq').val(hiddenXq);
					}
				}
			
		</script>
	</head>
	<body onload="jQuery('#xmxx').focus(); iner()">
		<html:form action="/sztz" method="post">
			<html:hidden property="id" value="${rs.id }" />
			<input type="hidden" id="hiddenXn" value="${xn}" />
			<input type="hidden" id="hiddenXq" value="${xq}"  />
			<input type="hidden" id="doType" value="${doType}"  />
			<logic:present name="rs">
				<logic:equal value="退回" property="shzt" name="rs">
					<!-- 是否退回后再次提交 -->
					<input type="hidden" name="flg" value="true"/>
				</logic:equal>
				<logic:notEqual value="退回" property="shzt" name="rs">
					<!-- 是否退回后再次提交 -->
					<input type="hidden" name="flg" value="false"/>
				</logic:notEqual>
			</logic:present>
			
			<div  style="width:100%;height:550px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead >
						<tr>
							<td colspan="4" >
								<span id="xmxx">
									项目信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select property="xn" value="${rs.xn }" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
			            	</td>
			            	<th width="16%">
			            		<font color="red">*</font>学期
							</th>
							<td width="34%">
								<html:select property="xq" value="${rs.xq }" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
			            	</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>年级
							</th>
							<td width="34%">
								<html:select property="nj" styleId="nj" value="${rs.nj }">
									<html:option value="">全部</html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
			            	</td>
			            	<th width="16%">
								<font color="red">*</font>院系
							</th>
							<td width="34%">
								<logic:empty name="xydm">
									<html:select property="xydm" style="width:180px" styleId="xydm" value="${rs.xydm }" >
										<html:option value="xj">校级</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="xydm">
									<html:select property="xydmXy" style="width:180px" styleId="xydmXy" value="${xydm }" disabled="true">
										<html:option value="xj">校级</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									<input type="hidden" id="xydm" name="xydm" value="${xydm }"/>
								</logic:notEmpty>
			            	</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>所属科目
							</th>
							<td>
								<html:select property="kmdm" onchange="initHxnl(this)" value="${rs.kmdm }">
									<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>核心能力
							</th>
							<td>
								<html:select property="hxnl" styleId="hxnl" value="${rs.hxnl }">
									<html:options collection="hxnlList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目类型
							</th>
							<td>
								<html:select property="xmlx" value="${rs.xmlx }">
									<html:options collection="xmlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<html:text property="xmmc" maxlength="50" value="${rs.xmmc }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核流程
							</th>
							<td>
								<logic:notPresent name="rs">
									<html:select property="shlcid" value="${rs.shlcid }">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
								</logic:notPresent>
								
								<logic:present name="rs">
									<html:select property="shlcid" value="${rs.shlcid }" disabled="true">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
									<html:hidden property="shlcid" value="${rs.shlcid }"/>
								</logic:present>
							</td>
							<th>
								<font color="red">*</font>基础分
							</th>
							<td>
								<html:text property="jcf" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="10" value="${rs.jcf }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>举办时间
							</th>
							<td>
								<html:text property="jbkssj" 
										   readonly="true"
										   styleId="jbkssj"
										   value="${rs.jbkssj }"
										   style="width:90px"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('jbkssj','y-mm-dd');"
								></html:text>
								至
								<html:text property="jbjssj"
										   readonly="true"
										   styleId="jbjssj"
										   style="width:90px"
										   value="${rs.jbjssj }"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('jbjssj','y-mm-dd');"
								></html:text>
							</td>
							<th><font color="red">*</font>人数上限</th>
							<td>
								<html:text styleId="rssx" property="rssx" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="5" value="${rs.rssx }"></html:text>
							</td>
							
						</tr>
						<tr>
							<th>
								主办方
							</th>
							<td>
								<html:text property="zbf" maxlength="50" style="width:85%" value="${rs.zbf }"></html:text>
							</td>
							<th>
								学时
							</th>
							<td>
								<html:text property="xs" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="2" value="${rs.xs }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								申报人
							</th>
							<td>
								<logic:empty name="sbr">
									${userNameReal }
								</logic:empty>
								<logic:notEmpty name="sbr">
									${rs.sbr }
								</logic:notEmpty>
								
								<html:hidden property="sbr" value="${userNameReal }"/>
							</td>
							<th>
								负责人
							</th>
							<td>
								<html:text property="fzr" maxlength="10" value="${rs.fzr }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注<font color="red">&lt;限400字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" onblur="checkLen(this,400)" style="width:85%" rows="5" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							
							<td colspan="4">
								<span>
									设立奖项
								</span>
							</td>
						</tr>
					</thead>
					
					<tbody>
							<tr>
								<td colspan="4">
									<!-- 查询得到的数据量显示区域 -->
									<button onclick="addTr();" class="btn_01">
										+
									</button>
									<button onclick="delTr();" class="btn_01">
										-
									</button>
									&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd" onkeyup="value=value.replace(/[^\d|.]/g,'')"
										style="width: 20px" onblur="addTr(true)" />
									&nbsp;行 
								</td>
							</tr>
							<tr>
								<td colspan="4">
									
									<table width="100%">
										<thead >
											<tr>
												<td  width="17.5px">
													<input type="checkbox" name="th" onclick="selectAll(this)"/>
												</td>
												<td>
													奖项代码
												</td>
												<td>
													奖项名称
												</td>
												<td>
													学分
												</td>
											</tr>
										</thead>
										<tbody id="sljxTab">
											<logic:present name="jxList">
												<logic:iterate id="j" name="jxList">
													<tr>
														<td>
															<input type="checkbox"/>
														</td>
														<td>
															<input type="text" name="jxdm" value="${j.jxdm }" maxlength="10"/>
														</td>
														<td>
															<input type="text" name="jxmc" value="${j.jxmc }" maxlength="30"/>
														</td>
														<td>
															<input type="text" name="jxfs" value="${j.jxfs }" maxlength="10" onblur='checkInputNum(this)'/>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>
									
								</td>
							</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="sztzActionForm" property="doType" >
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
								</logic:notEqual>
								<div class="btn">
									<logic:notEqual value="view" property="doType" name="sztzActionForm">
										<button onclick="saveXmsb()">
											保 存
										</button>
									</logic:notEqual>

									<button id="buttonSave" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
				</table>
			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>

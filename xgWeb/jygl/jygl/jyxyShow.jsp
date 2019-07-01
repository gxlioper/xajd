<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		 <script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var lsbm = jQuery('#lsbm');
				var zgdw = jQuery('#zgdw');
				
				lsbm.combobox({
					valueField:'dm',
  					textField:'mc',
					url:'jyglAjax.do?method=getLsbmOption',
					data:{url:'jyglAjax.do?method=getLsbmOption'}
				});
				
				zgdw.combobox({
					valueField:'dm',
						textField:'mc',
					url:'jyglAjax.do?method=getZgdwOption',
					data:{url:'jyglAjax.do?method=getZgdwOption'},
					onChange:function(n,o){
						setTimeout('setZgdw()',500);
					}
				});
			});
		
		
			//设置主管单位要保存的值 和名称
			function setZgdw(){	
				var text = jQuery('#zgdw').combobox('getText');
				var value = jQuery('#zgdw').combobox('getValue');
				
				jQuery('#zgdwmc').val(text);
				jQuery('#zgdwdm').val(value);
			}
			
			function saveForm(){
				var lsbm = jQuery('#lsbm').combobox('getValue');
				var dwxz = jQuery('#dwxz').combobox('getValue');
				if ('' == lsbm || '' == dwxz){
					alert("带\"*\"项目不能为空，请确认");
					return false;
				}
				
				changeSSX();
				
				if (jQuery('#bdkssj').value > jQuery('#bdjssj').value){
					alert('报到开始时间不能晚于结束时间')
				}else {
		            saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save',
		            'xh-jybz-dwdm-dwmc-zgdwdm-zgdwmc-yrdwszd-bdkssj-bdjssj-datddw');
		        }
		        return false;
			}
			
			function changeSSX(){
				var yrdwshen=jQuery("#yrdwshen option:selected").text();
				var yrdwshi=jQuery("#yrdwshi option:selected").text();
				var yrdwxian=jQuery("#yrdwxian option:selected").text();
				
				var bddqshen=jQuery("#bddqshen option:selected").text();
				var bddqshi=jQuery("#bddqshi option:selected").text();
				var bddqxian=jQuery("#bddqxian option:selected").text();
				
				if(yrdwshen=="" || yrdwshen=="----请选择----"){
					yrdwshen="　"
				}
				
				if(yrdwshi=="" || yrdwshi=="----请选择----"){
					yrdwshi="　"
				}
				
				if(yrdwxian=="" || yrdwxian=="----请选择----"){
					yrdwxian="　"
				}
				
				if(bddqshen=="" || bddqshen=="----请选择----"){
					bddqshen="　"
				}
				
				if(bddqshi=="" || bddqshi=="----请选择----"){
					bddqshi="　"
				}
				
				if(bddqxian=="" || bddqxian=="----请选择----"){
					bddqxian="　"
				}
				
				
				jQuery("#yrdwszd").val(yrdwshen+" "+yrdwshi+" "+yrdwxian);
				jQuery("#bddq").val(bddqshen+" "+bddqshi+" "+bddqxian);
				
			}
			
			function setJybz(text) {
				var xxdm = jQuery('#xxdm').val();
					
				if ( text <=21 || (22<text && text<=29 )|| (30<text && text<=56)) {
					jQuery('#jybz').val(2);
				}else  if (text == 70 || text==71) {
					jQuery('#jybz').val(5);
				}else  if (text == 72) {
					jQuery('#jybz').val(6);
				} else if ((72<text && text<=77) || text==90){
					jQuery('#jybz').val(3);
				} else if (text==80 || text==85) {
					jQuery('#jybz').val(4);
				} else {
					jQuery('#jybz').val(1);
				}
				
				if (xxdm == '13275') {
					if (text == 46){
						jQuery('#jybz').val(2);
					} else if (text == 75) {
						jQuery('#jybz').val(3);
					}
				}
			}
			
			function getYrdw(text){
				jQuery.ajax({
				  type:"POST",
				  dataType:"json",
				  url:"jyglAjax.do?method=getYrdwInfo&yrdwdm="+text,
				  success:function(data){
					if (null != data.dm){
						if (jQuery('#dwxz') && null != data.dwxzdm){
							jQuery('#dwxz').val(data.dwxzdm);
						}
						if (jQuery('#dwdm') && null != data.dm) {
							jQuery('#dwdm').val(data.dm);
						}
						if (jQuery('#dwmc') && null != data.mc) {
							jQuery('#dwmc').val(data.mc);
						}
						if (jQuery('#dwlxr') && null != data.dwlxr){
							jQuery('#dwlxr').val(data.dwlxr);
						}
						if (jQuery('#dwlxdh') && null != data.dwdh) {
							jQuery('#dwlxdh').val(data.dwdh);
						}
						if (jQuery('#hyfl') && null != data.hyfldm) {
							jQuery('#hyfl').val(data.hyfldm);
						}
						if (jQuery('#dwyb') && null != data.dwyb){
							jQuery('#dwyb').val(data.dwyb);
						}
						
						jQuery('#tempDwdm').val(text);
						//浙江省-父页的就就业标志也要变动
						setJybz(data.dwxzdm);
						
					} else {
						alert("没有维护的单位代码，请确认!");
						
						var text =jQuery('#tempDwdm').val();
						jQuery('#dwdm').val(text);
					}				      
				  }
			   });
		    }
		    
		    
			jQuery(function(){
				jQuery.ajaxSetup({async:false});
				//yrdwshen+" "+yrdwshi+" "+yrdwxian
				var yrdwszd=jQuery("#yrdwszd").val();
				//bddqshen+" "+bddqshi+" "+bddqxian
				var bddq=jQuery("#bddq").val();
				
				var yrdwarray=new Array();
				
				var yrdwshen=yrdwszd.split(" ")[0];
				var yrdwshi=yrdwszd.split(" ")[1];
				var yrdwxian=yrdwszd.split(" ")[2];
				
				var bddqshen=bddq.split(" ")[0];
				var bddqshi=bddq.split(" ")[1];
				var bddqxian=bddq.split(" ")[2];
				jQuery("#yrdwshen").val(jQuery("#yrdwshen option[text="+yrdwshen+"]").val());
				loadShi('yrdwshen','yrdwshi','yrdwxian');
				jQuery("#bddqshen").val(jQuery("#bddqshen option[text="+bddqshen+"]").val());
				loadShi('bddqshen','bddqshi','bddqxian');
				setTimeout(function(){
					jQuery("#yrdwshi").val(jQuery("#yrdwshi option[text="+yrdwshi+"]").val());
					jQuery("#bddqshi").val(jQuery("#bddqshi option[text="+bddqshi+"]").val());
				},1000);
				
				loadXian('yrdwshi','yrdwxian');
				loadXian('bddqshi','bddqxian');
				
				setTimeout(function(){
					jQuery("#yrdwxian").val(jQuery("#yrdwxian option[text="+yrdwxian+"]").val());
					jQuery("#bddqxian").val(jQuery("#bddqxian option[text="+bddqxian+"]").val());
				},1000);
				
				jQuery.ajaxSetup({async:true});
			})
			
				
		</script>
	</head>
	<body>
		<html:form action="/jygl.do" method="post" onsubmit="return false;">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh"/>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<input type="hidden" name="njV" id="njV"/>
			<input type="hidden" name="xyV" id="xyV"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" id="tempDwdm" value="${rs.save_dwdm }" />


			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							您的就业协议当前审核状态为<bean:message key="lable.xb" />审核“${rs.xysh }”，学校审核“${rs.xxsh }”。
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:present name="flg">
						<logic:equal value="true" name="flg">
							<script defer="defer">
							$('#buttonSave').disabled = true;
						</script>
						</logic:equal>
					</logic:present>
				</logic:notEmpty>

				<logic:present name="isBys">
					<logic:notEmpty name="isBys">
						<div class="prompt" id="div_help">
							<h3>
								<span>提示：</span>
							</h3>
							<p>
								${isBys}
							</p>
							<a class="close" title="隐藏"
								onclick="this.parentNode.style.display='none'"></a>
						</div>
						<script defer="defer">
						$('buttonSave').disabled = true;
					</script>
					</logic:notEmpty>
				</logic:present>
			</logic:equal>





			<!-- 学院审核状态为退回，修改后状态改为需重审 -->
			<logic:equal value="退回" name="rs" property="xysh">
				<html:hidden property="save_xysh" value="需重审" />
			</logic:equal>



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave"
										onclick="saveForm();">
										保存
									</button>

									<logic:equal value="stu" name="userType">
										<button type="reset">
											重置
										</button>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<button onclick="window.close();return false;">
											关闭
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="30%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%">
								性别
							</th>
							<td width="30%">
								${rs.xb}
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								学校
							</th>
							<td>
								${rs.xxmc }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								学历
							</th>
							<td>
								${rs.xl }
							</td>
							<th>
								生源地
							</th>
							<td>
								${rs.sydq }${rs.syds }${rs.sydx }
							</td>
						</tr>
						<tr>
							<th>
								入学年份
							</th>
							<td>
								${rs.rxnf }
							</td>
							<th>
								毕业年份
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
						<tr>
							<th>
								手机号码
							</th>
							<td>
								${rs.sjhm }
							</td>
							<th>
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								${rs.mzmc }
							</td>
							<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td>
								${rs.lxdh }
							</td>
							<th>
								邮编
							</th>
							<td>
								${rs.yzbh }
							</td>
						</tr>
						<tr>
							<th>
								联系地址
							</th>
							<td colspan="3">
								${rs.lxdz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>就业协议</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>就业标志
							</th>
							<td>
								<html:select property="save_jybz" name="rs" styleId="jybz"
									onchange="$('dwxz').value=''">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>用人单位名称
							</th>
							<td>
								<html:text property="save_dwmc" styleId="dwmc" name="rs"
									readonly="true" />
								<button
									onclick=
										"showTopWin('/xgxt/jygl.do?method=compayData', 800, 500);"
										id="buttonFindStu" class="btn_01">
									选择
								</button>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>用人单位代码
							</th>
							<td>
								<html:text property="save_dwdm" maxlength="20"
									onkeyup="if(13==event.keyCode){getYrdw(this.value);}" name="rs"
									styleId="dwdm"></html:text>
							</td>
							<th>
								<font color="red">*</font>用人单位性质
							</th>
							<td>
								<html:select property="save_dwxz" name="rs" styleId="dwxz" styleClass="easyui-combobox"
									onchange="setJybz(this.value);" style="width:200px">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>主管单位名称
							</th>
							<td>
								<html:hidden property="save_zgdwmc" styleId="zgdwmc" name="rs" />
								<html:select property="zgdw" 
											 value="${rs.zgdwdm }" 
											 styleId="zgdw" 
											 style="width: 200px;" 
											  >
									<html:options collection="zgdwList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>主管单位代码
							</th>
							<td>
								<html:text property="save_zgdwdm" name="rs" maxlength="10"
									readonly="true" styleId="zgdwdm"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>单位隶属部门
							</th>
							<td>
								<html:select property="save_lsbm" 
											 name="rs" 
											 styleId="lsbm" 
											 style="width: 200px;" 
											 >
									<html:options collection="lsbmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								用人单位所在地
							</th>
							<td colspan="3">
								<!--地址信息取标准码-->
								<logic:present name="yrdwssList">
									<html:select property="yrdwshen" styleId="yrdwshen"
										onchange="loadShi('yrdwshen','yrdwshi','yrdwxian');">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yrdwssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select property="yrdwshi" styleId="yrdwshi"
												onchange="loadXian('yrdwshi','yrdwxian')">
												<html:options collection="yrdwshiList" property="shidm"
													labelProperty="shimc" />
											</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select property="yrdwxian" styleId="yrdwxian">
										<html:options collection="yrdwxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									</logic:present>
									<!--end地址信息取标准码-->
									
										<html:hidden name="rs" property="save_yrdwszd" 
											styleId="yrdwszd" style="width:90%"  />
								
							</td>
						</tr>
						<tr>
							<th>
								报到地区
							</th>
							<td colspan="3">
								<!--地址信息取标准码-->
								<logic:present name="bddqssList">
									<html:select  property="bddqshen" styleId="bddqshen"
										onchange="loadShi('bddqshen','bddqshi','bddqxian');">
										<html:option value="">--请选择--</html:option>
										<html:options collection="bddqssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="bddqshi" styleId="bddqshi"
										onchange="loadXian('bddqshi','bddqxian')">
										<html:options collection="bddqshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select  property="bddqxian" styleId="bddqxian">
										<html:options collection="bddqxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									</logic:present>
									<!--end地址信息取标准码-->
								
									<html:hidden   property="save_bddq" name="rs"
										styleId="bddq" style="width:90%" />
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>报到开始时间
							</th>
							<td>
								<html:text property="save_bdkssj" name="rs" styleId="bdkssj"
									readonly="true"
									onclick="return showCalendar('bdkssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>报到结束时间
							</th>
							<td>
								<html:text property="save_bdjssj" name="rs" styleId="bdjssj"
									readonly="true"
									onclick="return showCalendar('bdjssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								下基层情况
							</th>
							<td>
								<html:select property="save_xjcqk" name="rs" style="width:200px">
									<html:options collection="xjcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>档案投递单位
							</th>
							<td>
								<html:text property="save_datddw" name="rs" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								档案投递地址
							</th>
							<td colspan="3">
								<html:text property="save_datddz" name="rs" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								户口迁移地址
							</th>
							<td colspan="3">
								<html:text property="save_hkqydz" name="rs" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								单位联系人
							</th>
							<td>
								<html:text property="save_dwlxr" styleId="dwlxr" name="rs"
									maxlength="25"></html:text>
							</td>
							<th>
								单位联系电话
							</th>
							<td>
								<html:text property="save_dwlxdh" styleId="dwlxdh" name="rs"
									maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								专业是否对口
							</th>
							<td>
								<html:select property="save_sfdk" value="是">
									<html:option value="">----请选择----</html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								就业行业
							</th>
							<td>
								<html:select property="save_jyhy" styleId="hyfl"
									style="width:200px" value="${rs.jyhy }">
									<html:options collection="hyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								就业备注一
							</th>
							<td colspan="3">
								<%--<html:text property="save_jybz1" style="width:80%"
									maxlength="150" value="${rs.jybz1 }"></html:text>
								--%><input type="text" id = "save_jybz1" name="save_jybz1" style="width:80%" maxlength="150" value="${rs.jybz1 }"/>
							</td>
						</tr>
						<tr>
							<th>
								就业备注二
							</th>
							<td colspan="3">
								<%--<html:text property="save_jybz2" style="width:80%"
									maxlength="150" value="${rs.jybz2 }"></html:text>
							--%><input type="text" id ="save_jybz2" name="save_jybz2" style="width:80%" maxlength="150" value="${rs.jybz2 }"/></td>
						</tr>
						<tr>
							<th>
								就业备注三
							</th>
							<td colspan="3">
								<%--<html:text property="save_jybz3" style="width:80%"
									maxlength="150" value="${rs.jybz3 }"></html:text>
							--%><input type="text" id ="save_jybz3" name="save_jybz3" style="width:80%" maxlength="150" value="${rs.jybz3 }"/></td>
						</tr>
					</tbody>
				</table>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>

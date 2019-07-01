<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
				
				jQuery(function(){
				
					var lsbm = jQuery('#lsbm');
					var zgdw = jQuery('#zgdw')
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
			
				if (jQuery('#bdkssj').value > jQuery('#bdjssj').value){
					alert('报到开始时间不能晚于结束时间')
				}else {
		            saveUpdate('/xgxt/jygl.do?method=jyxywh&doType=save',
		            'xh-jybz-dwdm-dwmc-zgdwdm-zgdwmc-yrdwszd-bdkssj-bdjssj-datddw');
		        }
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
						
						var text = jQuery('#tempDwdm').val();
						jQuery('#dwdm').val(text);
					}				      
				  }
			   });
		    }
			
		</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh" />
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="dm" id="dm" value="${dm }" />
			<input type="hidden" id="tempDwdm"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<logic:equal value="stu" name="userType" scope="session">
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
						$('#buttonSave').disabled = true;
					</script>
					</logic:notEmpty>
				</logic:present>
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
					<tbody>
						<tr>
							<th width="16%">
								<span class="red">*</span>学号
							</th>
							<td width="30%">
								<html:text property="save_xh" value="${rs.xh }" readonly="true" styleId="xh"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
										id="buttonFindStu" class="btn_01">
										选择
									</button>
								</logic:notEqual>
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
								${rs.sydq }
							</td>
						</tr>
						<tr>
							<th>
								户籍所在地
							</th>
							<td colspan="3">
								${rs.syds }${rs.sydx }
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
								家庭联系电话
							</th>
							<td>
								<logic:notEmpty name="rs">
								<logic:empty name="rs" property="kzx1">
									<html:text   property="save_kzx1" styleId="save_kzx1" value="${jtInfo.lxdh1 }" />
								</logic:empty>
								<logic:notEmpty name="rs" property="kzx1">
									<html:text  name="rs" property="save_kzx1" styleId="save_kzx1"  value="${rs.kzx1}" />
								</logic:notEmpty>
								</logic:notEmpty>
								<logic:empty name="rs">
									<html:text  property="save_kzx1" styleId="save_kzx1" value="${rs.lxdh1 }"/>
								</logic:empty>
							</td>
							<th>
								家庭联系地址
							</th>
							<td>
								<logic:notEmpty name="rs">
								<logic:empty name="rs" property="kzx2">
									<html:text  property="save_kzx2" styleId="save_kzx2"  value="${jtInfo.jtdz }"/>
								</logic:empty>
								<logic:notEmpty name="rs" property="kzx2">
									<html:text  property="save_kzx2" styleId="save_kzx2"  value="${rs.kzx2}"/>
								</logic:notEmpty>
								</logic:notEmpty>
								<logic:empty name="rs">
									<html:text  property="save_kzx2" styleId="save_kzx2" value="${rs.jtdz }"/>
								</logic:empty>
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
								<font color="red">*</font>就业类型
							</th>
							<td>
								<html:select property="save_jybz" styleId="jybz"
									onchange="$('dwxz').value=''" value="1">
									<html:options collection="jybzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>用人单位名称
							</th>
							<td>
								<html:text property="save_dwmc" styleId="dwmc" readonly="true" />
								<button
									onclick="showTopWin('/xgxt/jygl.do?method=compayData',800,500);"
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
									onkeyup="if(13==event.keyCode){getYrdw(this.value)}"
									styleId="dwdm"
									></html:text>
							</td>
							<th>
								<font color="red">*</font>用人单位性质
							</th>
							<td>
								<html:select property="save_dwxz" styleClass="easyui-combobox"
									onchange="setJybz(this);" styleId="dwxz"
									style="width:200px">
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
								<html:hidden property="save_zgdwmc" styleId="zgdwmc" />

								<html:select property="zgdw" 
											 styleClass="easyui-combobox"
											 style="width: 200px;"
											 styleId="zgdw"
											 >
									
								</html:select>

							</td>
							<th>
								<font color="red">*</font>主管单位代码
							</th>
							<td>
								<html:text property="save_zgdwdm" maxlength="10"
									styleId="zgdwdm" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>单位隶属部门
							</th>
							<td>
								<input type="hidden" id="team_lsbm" />
								<html:select property="save_lsbm" 
											 styleId="lsbm"
											 style="width: 200px;" 
											 >
								</html:select>
							</td>
							<th>
								工作岗位
							</th>
							<td>
								<html:text   property="save_kzx5" styleId="save_kzx3"  />
							</td>
						</tr>
						<tr>
							<th>
								报道证
							</th>
							<td>
								<html:select  property="save_kzx7">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
								
							</td>
							<th>
								薪资/月
							</th>
							<td>
								<html:text property="save_kzx8"  maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								信息登记号
							</th>
							<td>
								<html:text property="save_kzx4"  maxlength="50"></html:text>
							</td>
							<th>
								职业技能证书
							</th>
							<td>
								<html:text property="save_kzx6"  maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>用人单位所在地
							</th>
							<td colspan="3">
								<html:text property="save_yrdwszd" maxlength="100"
									styleId="yrdwszd" style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>报到地区
							</th>
							<td colspan="3">
								<html:text property="save_bddq" maxlength="100" styleId="bddq"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>报到开始时间
							</th>
							<td>
								<html:text property="save_bdkssj" styleId="bdkssj"
									readonly="true"
									onclick="return showCalendar('bdkssj','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>报到结束时间
							</th>
							<td>
								<html:text property="save_bdjssj" styleId="bdjssj"
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
								<html:select property="save_xjcqk" style="width:200px">
									<html:options collection="xjcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>档案投递单位
							</th>
							<td>
								<html:text property="save_datddw" maxlength="50" styleId="datddw"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								档案投递地址
							</th>
							<td colspan="3">
								<html:text property="save_datddz" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								户籍地址
							</th>
							<td colspan="3">
								<html:text property="save_kzx3"  maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								户口迁移地址
							</th>
							<td colspan="3">
								<html:text property="save_hkqydz" maxlength="100"
									style="width:80%"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								单位联系人
							</th>
							<td>
								<html:text property="save_dwlxr" styleId="dwlxr" maxlength="25"></html:text>
							</td>
							<th>
								单位联系电话
							</th>
							<td>
								<html:text property="save_dwlxdh" styleId="dwlxdh"
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
									style="width:200px">
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
								<html:text property="save_jybz1" style="width:80%"
									maxlength="150"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								就业备注二
							</th>
							<td colspan="3">
								<html:text property="save_jybz2" style="width:80%"
									maxlength="150"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								就业备注三
							</th>
							<td colspan="3"><html:text property="save_jybz3" style="width:80%"
									maxlength="150"></html:text>
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
									<button id="buttonSave"
										onclick="saveForm()">
										保存
									</button>
									<button type="reset">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
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

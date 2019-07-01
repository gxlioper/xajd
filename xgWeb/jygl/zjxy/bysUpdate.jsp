<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
			function setReadOnly(){
				jQuery('input',jQuery('#xsxxTab')).attr('readonly',true);
				jQuery('input',jQuery('#xsxxTab')).unbind('click').removeAttr('onclick');
				jQuery('input[type=radio]',jQuery('#xsxxTab')).attr('disabled',true);
				jQuery('select',jQuery('#xsxxTab')).attr('disabled',true);
			}
		
		
			function saveForm(id){
				var flg = true;
				var key = new Array();
				if(id!=""){
					key=id.split("-");
				}
			
				if(key.length > 0){
					for(var i=0;i<key.length;i++){
						if(jQuery('#'+key[i])){
							if(jQuery('#'+key[i]).val() == ""){
								alert('带"*"项不能为空，请确认');
								flg = false;
								break;
							}
						}
					}
				}
				if (flg){
				   //检查确认信息二是否有修改过？
					var yzbh = jQuery('#yzbh').val();
					var lxdz = jQuery('#lxdz').val();
					var zzmm = jQuery('#zzmm').val();
					var lxdh = jQuery('#lxdh').val();
					var sjhm = jQuery('#sjhm').val();
					var qq = jQuery('#qq').val();
					var dzyx = jQuery('#dzyx').val();
					var syshen = jQuery('#syshen').val();
					var syshi = jQuery('#syshi').val();
					var syxian = jQuery('#syxian').val();
				
					jQuery.ajaxSetup({async: false});
					jQuery.post('jyglAjax.do?method=getBysxx',{xh:jQuery('#xh').val()},function(data){
					
						if (data.yzbh.trim() != yzbh.trim() || data.lxdz.trim() != lxdz.trim() 
						 || data.zzmm.trim() != zzmm.trim() || data.lxdh.trim() != lxdh.trim() 
						 || data.sjhm.trim() != sjhm.trim() || data.dzyx.trim() != dzyx.trim()
						 || data.qq.trim() != qq.trim() || data.sydshen.trim() != syshen.trim()
						 || data.sydshi.trim() != syshi.trim() || data.sydxian.trim() != syxian.trim()){
						 	jQuery('#sfxg').val('是');
						 }
					},'json');
					refreshForm('jygl.do?method=bysUpdate&doType=save');
					jQuery.ajaxSetup({async: true});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${rs.pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<html:hidden property="save_xh" name="rs" styleId="xh" />
			<html:hidden property="save_sfxg" name="rs" styleId="sfxg" />

			<!-- 审核状态为通过或不通过的，学生用户不必再确认 -->
			<logic:equal value="stu" name="userType">
				<logic:notEmpty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>提示：</span>
						</h3>

						<logic:equal name="isQrsj" value="false">
							<p>
								<font color="red"> 现在不是毕业生信息确认时间,请于${cssz.bysqrkssj
									}日&nbsp;至&nbsp;${cssz.bysqrjssj }日内填写确认信息。</font>
							</p>
							<script defer="defer">
								jQuery('#buttonSave').attr('disabled',true);
								jQuery('#buttonSave').attr('class','disabled');
							</script>
						</logic:equal>
						<logic:notEqual name="isQrsj" value="false">
							<p>
								1:
								<font color="red"> 你所确认的信息在毕业就业环节中非常重要，请务必填写准确;</font>
								<br />
								2:
								<font color="red"> "确认信息一"不能修改，如有出入请及时与就业辅导员联系;</font>
								<br />
								3: 您的毕业信息当前审核结果：<bean:message key="lable.xb" />审核(${rs.xyshzt }),学校审核(${rs.shzt })。
							</p>
						</logic:notEqual>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none'"></a>
					</div>
					<logic:equal value="true" name="flg">
						<script defer="defer">
							jQuery('#buttonSave').attr('disabled',true);
							jQuery('#buttonSave').attr('class','disabled');
						</script>
					</logic:equal>
				</logic:notEmpty>
				<logic:empty name="rs">
					<div class="prompt">
						<h3>
							<span>系统提示：</span>
						</h3>
						<p>
							对不起，您未被上报为毕业生 ！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none'"></a>
					</div>

					<script defer="defer">
						$('buttonSave').disabled = true;
					</script>
				</logic:empty>
			</logic:equal>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<!-- 确认 -->
									<logic:equal value="update" name="doType">
										<logic:equal value="退回" name="rs" property="shzt">
											<html:hidden property="save_shzt" value="需重审" />
										</logic:equal>

										<button id="buttonSave"
											onclick="saveForm('yzbh-lxdz-zzmm-lxdh-sjhm-qq-dzyx-syshen');">
											确认
										</button>
									</logic:equal>

									<!-- 审核 -->
									<logic:equal value="sh" name="doType">
										<logic:equal value="xy" name="userType" scope="session">
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=通过&doType=save','');">
												通过
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=不通过&doType=save','');">
												不通过
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_xyshzt=退回&doType=save','');">
												退回
											</button>
										</logic:equal>
										<logic:notEqual value="stu" name="userType" scope="session">
										<logic:notEqual value="xy" name="userType" scope="session">
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=通过&doType=save','');">
												通过
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=不通过&doType=save','');">
												不通过
											</button>
											<button
												onclick="saveUpdate('jygl.do?method=bysUpdate&save_shzt=退回&doType=save','');">
												退回
											</button>
										</logic:notEqual>
										</logic:notEqual>
									</logic:equal>

									<logic:equal value="stu" name="userType">
										<button type="reset">
											重置
										</button>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<button id="buttonSave" onclick="window.close();return false;">
											关闭
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>确认信息一(学生基本情况)</span>
							</th>
						</tr>
					</thead>
					<tbody id="xsxxTab">
						<tr>
							<th width="16%">
								学生学号
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								学生姓名
							</th>
							<td width="34%">
								<html:text property="save_xm" maxlength="20" name="rs"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:radio property="save_xbdm" value="1" name="rs">男</html:radio>
								<html:radio property="save_xbdm" value="2" name="rs">女</html:radio>
							</td>
							<th>
								身份证号
							</th>
							<td>
								<html:text property="save_sfzh" maxlength="20" name="rs"
									onblur="chkSfzh(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								国标专业
							</th>
							<td>
								<html:hidden property="save_gbzydm" name="rs" />
								<html:select property="save_gbzydm" name="rs" disabled="true">
									<html:options collection="gbzyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								所属学校
							</th>
							<td>
								<html:hidden property="save_xxdm" name="rs" />
								<html:select property="save_xxdm" name="rs" style="width:200px"
									disabled="true">
									<html:options collection="xxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								所属
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td>
								<html:hidden property="save_xydm" name="rs" />
								<html:select property="save_xydm" name="rs" disabled="true">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								所属专业
							</th>
							<td>
								<html:hidden property="save_zydm" name="rs" />
								<html:select property="save_zydm" name="rs" disabled="true">
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								所属班级
							</th>
							<td>
								<html:hidden property="save_bjdm" name="rs" />
								<html:select property="save_bjdm" name="rs" disabled="true">
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>
								出生年月
							</th>
							<td>
								<html:text property="save_csrq" maxlength="20" name="rs"
									styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								民族名称
							</th>
							<td>
								<html:select property="save_mzdm" name="rs">
									<html:options collection="mzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								入学时间
							</th>
							<td>
								<html:text property="save_rxnf" name="rs" styleId="rxnf"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" readonly="true"></html:text>
							</td>
						</tr>

						<tr>

							<th>
								毕业时间
							</th>
							<td>
								<html:text property="save_bynf" name="rs" styleId="bynf"
									onclick="return showCalendar(this.id,'y-mm-dd');"
									onblur="dateFormatChg(this)" readonly="true"></html:text>
							</td>
							<th>
								招生类别
							</th>
							<td>
								<html:select property="save_zslbdm" name="rs">
									<html:options collection="zsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>

							<th>
								定向或委培单位
							</th>
							<td>
								<html:text property="save_dxhwp" maxlength="50" name="rs"></html:text>
							</td>
							<th>
								修业年限
							</th>
							<td>
								<html:text property="save_xz" maxlength="1" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								学历层次
							</th>
							<td>
								<html:select property="save_xlccdm" name="rs">
									<html:options collection="xlList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								培养方式
							</th>
							<td>
								<html:select property="save_pyfsdm" name="rs">
									<html:options collection="pyfsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>

							<th>
								是否在职
							</th>
							<td>
								<html:radio property="save_sfzz" value="是" name="rs">是</html:radio>
								<html:radio property="save_sfzz" value="否" name="rs">否</html:radio>
							</td>
							<th>
								是否师范
							</th>
							<td>
								<html:radio property="save_sfsf" value="是" name="rs">是</html:radio>
								<html:radio property="save_sfsf" value="否" name="rs">否</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								是否独立
							</th>
							<td>
								<html:radio property="save_sfdl" value="是" name="rs">是</html:radio>
								<html:radio property="save_sfdl" value="否" name="rs">否</html:radio>
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>确认信息二(毕业就业相关信息)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>邮政编码
							</th>
							<td>
								<html:text property="save_yzbh" maxlength="6" name="rs"
									styleId="yzbh" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								<font color="red">*</font>联系地址
							</th>
							<td>
								<html:text property="save_lxdz" maxlength="100" name="rs"
									styleId="lxdz"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>政治面貌
							</th>
							<td>
								<html:select property="save_zzmm" name="rs" styleId="zzmm">
									<html:options collection="zzmmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
								<html:text property="save_lxdh" maxlength="20" name="rs"
									onblur="checkPhone(this)" styleId="lxdh"></html:text>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>手机
							</th>
							<td>
								<html:text property="save_sjhm" maxlength="20" name="rs"
									onblur="checkPhone(this)" styleId="sjhm"></html:text>
							</td>
							<th>
								<font color="red">*</font>QQ号码
							</th>
							<td>
								<html:text property="save_qq" maxlength="11" name="rs"
									styleId="qq" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>Email邮箱
							</th>
							<td>
								<html:text property="save_dzyx" maxlength="50" name="rs"
									styleId="dzyx"
									onblur="if ('' != this.value)if (! isEmail(this.value) ){alert('非法邮箱地址!');this.focus();}"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th rowspan="2">
								<font color="red">*</font>生源地
							</th>
							<td colspan="3">
								<html:select property="save_sydshen" styleId="syshen"
									onchange="loadShi('syshen','syshi','syxian')" name="rs">
									<html:options collection="sydqList" property="dm"
										labelProperty="mc" />
								</html:select>
								<html:select property="save_sydshi" styleId="syshi" name="rs"
									onchange="loadXian('syshi','syxian')">
									<html:options collection="sydsList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select property="save_sydxian" styleId="syxian" name="rs">
									<html:options collection="sydxList" property="xiandm"
										labelProperty="xianmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								确认前生源地:${rs.ysyd };
								<font color="red">说明:"生源地"指学生参加高考时户籍所在地。</font>
							</td>
						</tr>
					</tbody>
					<tbody>
						<!-- 确认人信息 -->
						<logic:equal value="update" name="doType">
							<tr>
								<th>
									确认人
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_sfqr" value="是" />
								</td>
								<th>
									确认时间
								</th>
								<td>
									${now }
									<html:hidden property="save_qrrbh" value="${userName }" />
									<html:hidden property="save_qrsj" value="${now }" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="update" name="doType">
							<tr>
								<th>
									确认人
								</th>
								<td>
									${rs.qrr }
								</td>
								<th>
									确认时间
								</th>
								<td>
									${rs.qrsj }
								</td>
							</tr>
						</logic:notEqual>
					</tbody>
					<logic:equal value="sh" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<logic:equal value="xy" name="userType" scope="session">
							<tbody>
								<tr>
									<th>
										审核意见
										<br />
										<font color="red"><限500字>
										</font>
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
											onblur="checkLen(this,500)" style="width:80%" rows="5"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										审核人
									</th>
									<td>
										${userNameReal }
										<html:hidden property="save_xyshr" value="${userName }" />
									</td>
									<th>
										审核时间
									</th>
									<td>
										${now }
										<html:hidden property="save_xyshsj" value="${now }" />
									</td>
								</tr>
							</tbody>
						</logic:equal>

						<logic:notEqual value="xy" name="userType" scope="session">
							<tbody>
								<tr>
									<th>
										<bean:message key="lable.xb" />审核意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										${rs.xyshyj }
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xb" />审核人
									</th>
									<td>
										${rs.xyshr }
									</td>
									<th>
										<bean:message key="lable.xb" />审核时间
									</th>
									<td>
										${rs.xyshsj }
									</td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<th>
										学校审核意见
										<br />
										<font color="red"><限500字>
										</font>
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_shyj" value="${rs.shyj }"
											onblur="checkLen(this,500)" style="width:80%" rows="5"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										学校审核人
									</th>
									<td>
										${userNameReal }
										<html:hidden property="save_shr" value="${userName }" />
									</td>
									<th>
										学校审核时间
									</th>
									<td>
										${now }
										<html:hidden property="save_shsj" value="${now }" />
									</td>
								</tr>
							</tbody>
						</logic:notEqual>
					</logic:equal>

					<logic:equal value="view" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<bean:message key="lable.xb" />审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />审核人
								</th>
								<td>
									${rs.xyshr }
								</td>
								<th>
									<bean:message key="lable.xb" />审核时间
								</th>
								<td>
									${rs.xyshsj }
								</td>
							</tr>
						</tbody>
						<tbody>
							<tr>
								<th>
									学校审核意见
								</th>
								<td colspan="3">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									学校审核人
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									学校审核时间
								</th>
								<td>
									${rs.shsj }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				</table>
			</div>
		</html:form>
		<logic:equal value="stu" name="userType">
			<script defer>
				setReadOnly();
			</script>
		</logic:equal>
		<logic:equal value="xy" name="userType">
			<script defer>
				setReadOnly();
			</script>
		</logic:equal>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>

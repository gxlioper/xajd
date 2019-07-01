<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript">
		//照片上传
		function uploadStuPic(){
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh=${rs.xh }',//服务器端程序
			  secureuri:false,
			  fileElementId:'stuPic',//input框的ID
			  async: false,
			  dataType: 'json',//返回数据类型
			  success: function (data){//上传成功
				  jQuery('#stuImg').empty();
				  jQuery("#stuImg").html('<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }&flg=true" border="0" align="absmiddle" style="width:120px;height:160px" />').show();
			  }
			});
		}

		//检测保存学生信息
		function checkSaveXsxx(){

			var xm = jQuery("#xm").val();
			var xb = jQuery("input[name=xb]:checked").val();
			jQuery("#xb").val(xb);

			var jgshen = jQuery("#jgshen").val();
			var jgshi = jQuery("#jgshi").val();
			var jgxian = jQuery("#jgxian").val();

			var jg = jgshen + "/" + jgshi + "/" + jgxian;
			jQuery("#jg").val(jg);

			var sydshen = jQuery("#sydshen").val();
			var sydshi = jQuery("#sydshi").val();
			var sydxian = jQuery("#sydxian").val();
			
			var syd = sydshen + "/" + sydshi + "/" + sydxian;
			jQuery("#syd").val(syd);

			if(xm == ""){
				alertError("姓名不能为空，请确认");
				return false;
			}else if(xm == ""){
				alertError("性别不能为空，请确认");
				return false;
			}
			confirmInfo('将要执行保存操作，请您确认',saveXsxx);
		}

		//保存学生信息
		function saveXsxx(tag){

			if(tag == "ok"){
				jQuery("#addPic").html("");
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_xsxx_zxxs_ajax.do?method=saveXsxx";
	
			 	//创建一个json对象
				var parameter={};
				
				//指定获取的控件类型，进行循环
				var hid_obj=jQuery("input").each(function(){
					
					//获取表单控件name
					var name=jQuery(this).attr("name");
					//构建json对象
					parameter[name]=escape(jQuery(this).val());
				});
	
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
				
				<!-- 基本信息 -->
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jbxx')">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="24%">
								${rs.xh }
								<input type="hidden" name="str_xh"  id="xh" value="${rs.xh }" />
							</td>
							<th width="15%">
								<font color="red">*</font>姓名
							</th>
							<td width="25%">
								<input type="text" name="str_xm" style="width:180px" 
									id="xm" value="${rs.xm }" maxlength="16"/>
							</td>
							<td rowspan="6">
								<div id="stuImg">
									<img style="width:120px;height:160px" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0">
								</div>
								<br />
								<button type="button"
									onclick='tipsWindown("系统提示","id:addPic","380","130","true","","true","id");'
									style="width:100px" class="btn_01">
									上传照片
								</button>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>性别
							</th>
							<td>
								<input type="radio" name="xb" value="男"
									<logic:equal name="rs" property="xb" value="男">
									checked="checked"
									</logic:equal>
								/>男
								<input type="radio" name="xb" value="女"
									<logic:equal name="rs" property="xb" value="女">
									checked="checked"
									</logic:equal>
								/>女
								<input type="hidden" name="str_xb"  id="xb" value="${rs.xb }" />
							</td>
							<th>
								出生日期
							</th>
							<td>
								<input type="text" name="str_csrq" id="csrq" 
									style="width:180px" readonly
									onclick="showCalendar(this.id,'ymmdd')" 
									value="${rs.csrq}"/>
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								<input type="hidden" name="str_mz"  id="mz" value="${rs.mz }" />
								<html:select name="rs" property="mz" style="width:180px" onchange="$('mz').value=this.value"
									  onmouseover="">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<input type="hidden" name="str_zzmm"  id="zzmm" value="${rs.zzmm }" />
								<html:select name="rs" property="zzmm" style="width:180px" styleId="zzmm" onchange="$('zzmm').value=this.value"
								  onmouseover="">
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td>
								<input type="text" name="str_sfzh" 
									style="width:180px" 
									id="sfzh" value="${rs.sfzh }" maxlength="18"/>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								籍贯
							</th>
							<td colspan="3">
								<input type="hidden" name="str_jg"  id="jg" value="${rs.jg }" />
								<html:select name="rs" property="jgshen" styleId="jgshen"
									style="width:130px"   onmouseover=""
									onchange="loadShi('jgshen','jgshi','jgxian');">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
								</html:select>
								省
								<html:select name="rs" property="jgshi" 
									style="width:130px"   onmouseover=""
									styleId="jgshi" onchange="loadXian('jgshi','jgxian')">
									<html:options collection="jgshiList" property="shidm" labelProperty="shimc" />
								</html:select>
								市
								<html:select name="rs" property="jgxian" 
									style="width:130px"   onmouseover=""
									styleId="jgxian">
									<html:options collection="jgxianList" property="xiandm" labelProperty="xianmc" />
								</html:select>
								区/县
							</td>
						</tr>
						<tr>
							<th>
								来源地区(生源地)
							</th>
							<td colspan="3">
								<input type="hidden" name="str_syd"  id="syd" value="${rs.syd }" />
								<html:select name="rs" property="sydshen" styleId="sydshen"
									style="width:130px"   onmouseover=""
									onchange="loadShi('sydshen','sydshi','sydxian');">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
								</html:select>
								省
								<html:select name="rs" property="sydshi" 
									style="width:130px"   onmouseover=""
									styleId="sydshi" onchange="loadXian('sydshi','sydxian')">
									<html:options collection="syshiList" property="shidm" labelProperty="shimc" />
								</html:select>
								市
								<html:select name="rs" property="sydxian" 
									style="width:130px"   onmouseover=""
									styleId="sydxian">
									<html:options collection="syxianList" property="xiandm" labelProperty="xianmc" />
								</html:select>
								区/县
							</td>
						</tr>
					</tbody>
			    </table>
		   		<!-- 基本信息  end-->
		   		
		   		<!-- 在校信息 -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_zxxx')">
								<span>在校信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_zxxx">
						<tr>
							<th width="16%">
								年级
							</th>
							<td width="24%">
								${rs.nj }
							</td>
							<th width="15%">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td  colspan="2">
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
							<td colspan="2">
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								学制
							</th>
							<td>
								${rs.xz }
							</td>
							<th>
								
							</th>
							<td colspan="2">
								
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- 在校信息 end -->
			    
			    <!-- 其他信息 -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_qtxx')">
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_qtxx" style="display:none">
						<tr>
							<th width="16%">
								手机号码
							</th>
							<td width="24%">
								<input type="text" name="str_sjhm" id="sjhm" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.sjhm }" 
								/>
							</td>
							<th width="15%">
								QQ号码
							</th>
							<td  colspan="2">
								<input type="text" name="str_qqhm" id="qqhm" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.qqhm }" 
								/>
							</td>
						</tr>
						<tr>
							<th>
								电子邮箱
							</th>
							<td colspan="4">
								<input type="text" name="str_dzyx" id="dzyx"
									style="width:500px"
									value="${rs.dzyx}" maxlength="32"/>
							</td>
						</tr>
						<tr>
							<th>
								银行名称
							</th>
							<td>
								<input type="hidden" name="str_yhdm"  id="yhdm" value="${rs.yhdm }" />
								<html:select name="rs" property="yhdm"  style="width:180px;" onchange="$('yhdm').value=this.value"
									  onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm" labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								银行卡号
							</th>
							<td colspan="2">
								<input type="text" name="str_yhkh" id="yhkh" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.yhkh }" 
								/>
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- 其他信息 end -->
			    
			    <!-- 家庭信息 -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jtxx')">
								<span>家庭信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jtxx" style="display:none">
						<tr>
							<th width="16%">
								家庭电话
							</th>
							<td width="24%">
								<input type="text" name="str_lxdh1" id="lxdh1" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.lxdh1 }" 
								/>
							</td>
							<th width="15%">
								家庭邮编
							</th>
							<td  colspan="2">
								<input type="text" name="str_yb" id="yb" 
									onkeydown="return onlyNum(this,10)"
									onmousedown="return onlyNum(this,10)"
									maxlength="10" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.yb }" 
								/>
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="4">
								<input type="text" name="str_jtszd" id="jtszd" 
									style="width:500px"
									value="${rs.jtszd }" 
									maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<table width="100%">
									<!-- 家庭成员1 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>家庭成员1</span>
											</td>
										</tr>
										<tr>
											<th width="16%">
												姓名
											</th>
											<td width="24%">
												<input type="text" name="str_jtcy1_xm" id="jtcy1_xm" 
													style="width:180px" 
													value="${rs.jtcy1_xm }" maxlength="25"/>
											</td>
											<th width="15%">
												与本人关系
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_gx" id="jtcy1_gx" 
													style="width:180px" 
													value="${rs.jtcy1_gx}" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												出生日期
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_nl" id="jtcy1_nl" 
													style="width:180px" readonly
													onclick="showCalendar(this.id,'ymmdd')" 
													value="${rs.jtcy1_nl}"/>
											</td>
											<th width="">
												身份证号
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_sfzh" id="jtcy1_sfzh" 
													style="width:180px" 
													value="${rs.jtcy1_sfzh }" maxlength="18"/>
											</td>
										</tr>
										<tr>
											<th width="">
												民族
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy1_mz"  id="jtcy1_mz" value="${rs.jtcy1_mz }" />
												<html:select name="rs" property="jtcy1_mz" 
													style="width:180px"  onmouseover=""
													onchange="$('jtcy1_mz').value=this.value">
													<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
												</html:select>
											</td>
											<th width="">
												政治面貌
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy1_zzmm"  id="jtcy1_zzmm" value="${rs.jtcy1_zzmm }" />
												<html:select name="rs" property="jtcy1_zzmm" 
													style="width:180px"    onmouseover=""
													onchange="$('jtcy1_zzmm').value=this.value">
													<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="">
												职业
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_zy" id="jtcy1_zy" 
													style="width:180px" 
													value="${rs.jtcy1_zy }" maxlength="10"/>
											</td>
											<th width="">
												职务
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_zw" id="jtcy1_zw" 
													style="width:180px" 
													value="${rs.jtcy1_zw }" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												工作单位电话
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_lxdh1" id="jtcy1_lxdh1" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy1_lxdh1 }" 
												/>
											</td>
											<th width="">
												个人电话
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_lxdh2" id="jtcy1_lxdh2" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy1_lxdh2 }" 
												/>
											</td>
										</tr>
										<tr>
											<th width="">
												工作地址
											</th>
											<td width="" colspan="3">
												<input type="text" name="str_jtcy1_gzdz" id="jtcy1_gzdz" 
													style="width:500px" 
													value="${rs.jtcy1_gzdz }" maxlength="25"/>
											</td>
										</tr>
									</tbody>
									<!-- 家庭成员1 end-->
									
									<!-- 家庭成员2-->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>家庭成员2</span>
											</td>
										</tr>
										<tr>
											<th width="16%">
												姓名
											</th>
											<td width="24%">
												<input type="text" name="str_jtcy2_xm" id="jtcy2_xm" 
													style="width:180px" 
													value="${rs.jtcy2_xm }" maxlength="25"/>
											</td>
											<th width="15%">
												与本人关系
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_gx" id="jtcy2_gx" 
													style="width:180px" 
													value="${rs.jtcy2_gx}" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												出生日期
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_nl" id="jtcy2_nl" 
													style="width:180px" readonly
													onclick="showCalendar(this.id,'ymmdd')" 
													value="${rs.jtcy2_nl}"/>
											</td>
											<th width="">
												身份证号
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_sfzh" id="jtcy2_sfzh" 
													style="width:180px" 
													value="${rs.jtcy2_sfzh }" maxlength="18"/>
											</td>
										</tr>
										<tr>
											<th width="">
												民族
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy2_mz"  id="jtcy2_mz" value="${rs.jtcy2_mz }" />
												<html:select name="rs" property="jtcy2_mz" 
													style="width:180px"   onmouseover=""
													onchange="$('jtcy2_mz').value=this.value">
													<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
												</html:select>
											</td>
											<th width="">
												政治面貌
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy2_zzmm"  id="jtcy2_zzmm" value="${rs.jtcy2_zzmm }" />
												<html:select name="rs" property="jtcy2_zzmm" 
													style="width:180px"   onmouseover=""
													onchange="$('jtcy2_zzmm').value=this.value">
													<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="">
												职业
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_zy" id="jtcy2_zy" 
													style="width:180px" 
													value="${rs.jtcy2_zy }" maxlength="10"/>
											</td>
											<th width="">
												职务
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_zw" id="jtcy2_zw" 
													style="width:180px" 
													value="${rs.jtcy2_zw }" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												工作单位电话
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_lxdh1" id="jtcy2_lxdh1" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy2_lxdh1 }" 
												/>
											</td>
											<th width="">
												个人电话
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_lxdh2" id="jtcy2_lxdh2" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy2_lxdh2 }" 
												/>
											</td>
										</tr>
										<tr>
											<th width="">
												工作地址
											</th>
											<td width="" colspan="3">
												<input type="text" name="str_jtcy2_gzdz" id="jtcy2_gzdz" 
													style="width:500px" 
													value="${rs.jtcy2_gzdz }" maxlength="25"/>
											</td>
										</tr>
									</tbody>
									<!-- 家庭成员2 end-->
								</table>
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- 家庭信息 end -->
			    
		    </div>
		    <div>
		    	<table width="100%" border="0" class="formlist">
			    	<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="保存" onclick="checkSaveXsxx();">保存</button>
									<button type="button" name="关闭" onclick="Close();return false;">关 闭</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
			</div>
			
			<!-- 上传照片 -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
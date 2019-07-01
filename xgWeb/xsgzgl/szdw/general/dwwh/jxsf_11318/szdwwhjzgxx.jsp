<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript"
			src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" src="xsgzgl/szdw/general/dwwh/jxsf_11318/js/fdyxxwh.js"></script>
		<script language="javascript">
		/**
		 * 弹出警告框
		 */
		function showAlertDivLayer() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"系统提示",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:340,
				winPos:[180,160],
				height:160,
				//showMask:false,
				handler:clickFun
			});
			//setTimeout(function(){ymPrompt.doHandler();},3000);
		}
			//显示上传相片
			function showZpscDiv(){

				var zgh = jQuery("#zgh").val();
				
				if(zgh == ""){
					alertError("请先填写职工号！");
				}else{
					tipsWindown("上传照片","id:addPic","380","130","true","","true","id");
					//tipsWindownNew("上传照片","id:addPic",380,130);
				}
			}
			//上传照片
			function uploadTeaPic(){
				jQuery.ajaxSetup({async:false,dataType:'text'});
				
				var zgh = jQuery("#zgh").val();
				jQuery.ajaxFileUpload({
					  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//服务器端程序
					  secureuri:false,
					  fileElementId:'teaPic',//input框的ID
					  success:function(data,type){
						if (type=='success'){
							jQuery("#jszp").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
							jQuery("#zhaopian").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
							alertInfo(data);
						}
					  }
					});

				jQuery.ajaxSetup({async:true});
			}
			jQuery(function(){
				jQuery("#zhaopian").attr('src','<%=request.getContextPath()%>/teaPic.jsp?zgh=${model.zgh}&t='+new Date());
			});
		</script>
	</head>
	<body>

		<html:form action="/data_search" method="post" styleId="dwwh_form">
			<%--<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>--%>

			<div class="tab"
				style="width: 100%; height: 420px; overflow-x: hidden; overflow-y: auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>基本资料</span>
							</th>
						</tr>
					</thead>

					<tbody id="jbzl">
						<tr>
							<th align="right">
								<font color="red">*</font>工号
							</th>
							<td align="left" style="width: 25%">
								<input type="hidden" />
								<html:hidden property="zgh" name="model" styleId="zgh" />
								<html:text property="zgh" name="model" styleId="zgh"
									disabled="true" maxlength="20"></html:text>
							</td>
							<th align="right">
								<font color="red">*</font>姓名
							</th>
							<td align="left" style="width: 25%">
								<html:text property="xm" name="model" styleId="xm"
									maxlength="20"></html:text>
							</td>
							<th align="left" rowspan="5">
								<div align="center">
									<img
										src=""
										style="height: 133px; width: 100px;" border="0" id="zhaopian" />
								</div>
								<div align="center">
									<button type="button" onclick="showZpscDiv();"
										style="width: 100px" id="buttonSave">
										上传照片
									</button>
								</div>
							</th>
						</tr>
						<tr>
							<th align="right" width="15%">
								<font color="red">*</font>所在部门
							</th>
							<td align="left">
								<logic:equal value="true" name="flag">
									<html:select name='model' property="bmdm" styleId="bmdm"
										disabled="true" style="width:150px;">
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="flag">
									<html:select name='model' property="bmdm" styleId="bmdm"
										disabled="${xy}" style="width:150px;">
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								</logic:notEqual>
							</td>

							<th align="right">
								<font color="red">*</font>性别
							</th>
							<td align="left">
								<html:select property="xb" name="model" styleId="xb"
									style="width:150px;">
									<html:option value="1">男</html:option>
									<html:option value="2">女</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>身份证号
							</th>
							<td align="left">
								<html:text property="sfzh" name="model" styleId="sfzh"
									maxlength="18"></html:text>
							</td>
							<th align="right">
								<font color="red">*</font>出生日期
							</th>
							<td align="left">
								<html:text property="csrq" name="model" styleId="csrq"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('csrq','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>政治面貌
							</th>
							<td align="left">
								<html:select name='model' property="zzmm" styleId="zzmm"
									style="width:150px;">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>编制类型
							</th>
							<td align="left">
								<html:select name='model' property="kzzd11" styleId="kzzd11"
									style="width:150px;">
									<html:options collection="bzlxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>职称
							</th>
							<td align="left">
								<html:select property="zc" name="model" styleId="zc"
									style="width:150px;">
									<html:options collection="zcList" property="zcdm"
										labelProperty="zcmc" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>评聘时间
							</th>
							<td align="left">
								<html:text property="kzzd6" name="model" styleId="kzzd6"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kzzd6','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>职务
							</th>
							<td align="left">
								<html:select name='model' property="zw" styleId="zw"
									style="width:150px;">
									<html:options collection="zwList" property="zwdm"
										labelProperty="zwmc" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>任职时间
							</th>
							<td align="left" colspan="2">
								<html:text property="kzzd7" name="model" styleId="kzzd7"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kzzd7','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>任同职级时间
							</th>
							<td align="left">
								<html:text property="kzzd8" name="model" styleId="kzzd8"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kzzd8','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
							<th align="right">
								<font color="red">*</font>身份类型
							</th>
							<td align="left" colspan="2">
								<html:select name='model' property="kzzd16" styleId="kzzd16"
									style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="专职辅导员">专职辅导员</html:option>
									<html:option value="兼职辅导员">兼职辅导员</html:option>
									<html:option value="班主任">班主任</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="sfjtlx" style="display: none">
							<th id="sfjtlx_th" align="right">
								<font color="red">*</font>具体类型
							</th>
							<td id="sfjtlx_td" align="left">
								<html:select name='model' property="kzzd17" styleId="kzzd17"
									style="width:150px;">
									<html:options collection="sfjtlxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
							
							<th align="right">
								<font color="red">*</font>担任时间
							</th>
							<td id="drsj_td" align="left" colspan="2">
								<html:text property="kzzd12" name="model" styleId="kzzd12"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kzzd12','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								单位类别
							</th>
							<td align="left">
								<html:text property="dwlbdm" name="model" styleId="dwlbdm"
									maxlength="10"></html:text>
							</td>
							<th align="right">
								<font color="red">*</font>民族
							</th>
							<td align="left" colspan="2">
								<html:select name='model' property="mz" styleId="mz"
									style="width:150px;">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>最高学历
							</th>
							<td align="left">
								<html:select property="xl" name="model" styleId="xl"
									style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="大专">大专</html:option>
									<html:option value="本科">本科</html:option>
									<html:option value="研究生">研究生</html:option>
									<html:option value="博士生">博士生</html:option>
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>最高学历获得年份
							</th>
							<td align="left" colspan="2">
								<html:text property="kzzd20" name="model" styleId="kzzd20"
									maxlength="4"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>最高学位
							</th>
							<td align="left">
								<html:select property="xw" name="model" styleId="xw"
									style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="专科">专科</html:option>
									<html:option value="学士">学士</html:option>
									<html:option value="硕士">硕士</html:option>
									<html:option value="博士">博士</html:option>
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>最高学位获得年份
							</th>
							<td align="left"  colspan="2">
								<html:text property="kzzd13" name="model" styleId="kzzd13"
									maxlength="4"></html:text>
							</td>

						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>毕业院校
							</th>
							<td align="left">
								<html:text property="byyx" name="model" styleId="byyx"
									maxlength="15"></html:text>
							</td>
							<th align="right">
								<font color="red">*</font>所学专业
							</th>
							<td align="left" colspan="2">
								<html:text property="sxzy" name="model" styleId="sxzy"
									maxlength="15"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>籍贯
							</th>
							<td align="left">
								<html:select name='model' property="jgxs" styleId="jgxs"
									style="width:150px;">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>入校工作时间
							</th>
							<td align="left" colspan="2">
								<html:text property="lxgzsj" name="model" styleId="lxgzsj"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('lxgzsj','y-mm-dd');"
									readonly="true" style="cursor:hand "></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>研究课题
							</th>
							<td align="left">
								<html:text property="xsgzyjfx" name="model" styleId="xsgzyjfx"
									maxlength="20"></html:text>
							</td>
							<th align="right">
								岗位类别
							</th>
							<td align="left" colspan="2">
								<html:select name='model' property="gwlbdm" styleId="gwlbdm"
									style="width:150px;">
									<html:options collection="gwlbList" property="gwlbdm"
										labelProperty="gwlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>家庭住址
							</th>
							<td align="left" colspan="4">
								<html:text property="jtzz" name="model" styleId="jtzz"
									maxlength="50" style="width:500px;"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>主要职责
								<br />
								<font color="red">限200个字</font>
							</th>
							<td align="left" colspan="4">
								<html:textarea property="zyzz" name="model" styleId="zyzz"
									rows="3" cols="80"></html:textarea>

							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>获奖情况
								<br />
								<font color="red">限2000个字</font>
							</th>
							<td align="left" colspan="4">
								<html:textarea property="grhjqk" name="model" styleId="grhjqk"
									rows="3" cols="80"></html:textarea>

							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="5">
								<span>联系方式</span>
							</th>
						</tr>
					</thead>
					<tr>
						
						<th align="right">
							<font color="red">*</font>移动电话
						</th>
						<td align="left">
							<html:text property="yddh" name="model"
								onkeyup="checkInputData(this)" styleId="yddh" maxlength="15"></html:text>
						</td>
						<th align="right" >
							<font color="red">*</font>办公电话
						</th>
						<td align="left" colspan="2">
							<html:text property="bgdh" name="model" onblur="checkPhone(this)"
								styleId="bgdh" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>电子邮箱
						</th>
						<td align="left">
							<html:text property="dzyx" name="model" styleId="dzyx"
								maxlength="25"></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>QQ
						</th>
						<td align="left" colspan="2">
							<html:text property="kzzd3" name="model"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onblur="value=value.replace(/[^\d]/g,'')" styleId="kzzd3"
								maxlength="15"></html:text>
						</td>
					<tr>
						<th align="right">
							<font color="red">*</font>联系电话
						</th>
						<td align="left">
							<html:text property="lxdh" name="model" styleId="lxdh"
								maxlength="15"></html:text>
						</td>
						<th align="right">
							传真
						</th>
						<td align="left" colspan="2">
							<html:text property="cz" name="model" styleId="cz" maxlength="20"></html:text>
						</td>
					</tr>

					<tr>
						<th align="right">
							微信名
						</th>
						<td align="left">
							<html:text property="kzzd1" name="model" styleId="kzzd1"
								maxlength="25"></html:text>
						</td>
						<th align="right">
							微博名
						</th>
						<td align="left" colspan="4">
							<html:text property="kzzd2" name="model" styleId="kzzd2"
								maxlength="25"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							办公地点
						</th>
						<td align="left" colspan="4">
							<html:text property="bgdd" name="model" styleId="bgdd"
								maxlength="25" style="width:575px;"></html:text>
						</td>
					</tr>
					<tr>

						<th align="right">
							通讯地址
						</th>
						<td align="left" colspan="4">
							<html:text property="txdz" name="model" styleId="txdz"
								maxlength="25" style="width:575px;"></html:text>
						</td>
					</tr>

					<thead>
						<tr>
							<th colspan="5">
								<span>工作经历</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right">
							<font color="red">*</font>参加工作时间
						</th>
						<td align="left">
							<html:text property="cjgzrq" name="model" styleId="cjgzrq"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('cjgzrq','y-mm-dd');"
								readonly="true" style="cursor:hand "></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>参加学生工作时间
						</th>
						<td align="left" colspan="2">
							<html:text property="szgzsj" name="model" styleId="szgzsj"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('szgzsj','y-mm-dd');"
								readonly="true" style="cursor:hand "></html:text>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<font color="red">*</font>工作经历
							<br />
							<font color="red">限2000个字</font>
						</th>
						<td align="left" colspan="4">
							<html:textarea property="gzjl" name="model" styleId="gzjl"
								rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<thead>
						<tr>
							<th colspan="5">
								<span>其它</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right">
							评优评先
							<br />
							<font color="red">限2000个字</font>
						</th>
						<td align="left" colspan="4">
							<html:textarea property="kzzd4" name="model" styleId="kzzd4"
								rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							发表论文
							<br />
							<font color="red">限2000个字</font>
						</th>
						<td align="left" colspan="4">
							<html:textarea property="fblw" name="model" styleId="fblw"
								rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							参加培训
							<br />
							<font color="red">限300个字</font>
						</th>
						<td align="left" colspan="4">
							<html:textarea property="pxqk" name="model" styleId="pxqk"
								rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							备注
							<br />
							<font color="red">限2000个字</font>
						</th>
						<td align="left" colspan="4">
							<html:textarea property="bz" name="model" styleId="bz" rows="3"
								cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							附件信息
							<br />
							<font color="red">与上述项相关的附件</font>
						</th>
						<td align="left" colspan="4">
							<html:hidden name="model" property="kzzd19" styleId="kzzd19" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 5,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'kzzd19'
												});
										});
									</script>
						</td>
					</tr>
					</tbody>
					<%--
							 <tfoot>
						      <tr>
						        <td colspan="5"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
									<button type="button" name="保存" onclick="updateDwwh('update');" id="buttonSave">
										保 存
									</button>
									<logic:empty name="flag">
									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
									</logic:empty>
												           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
						--%>
				</table>
			</div>

			<!-- 完善教师信息 - 上传照片 -->
			<!-- 上传照片 -->
			<div id="addPic" style="display: none">
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
									<input type="file" id="teaPic" name="teaPic" style="width: 90%"
										onchange='uploadTeaPic();closeWindown();' />
									<br />
									<html:hidden property="zgh" name="model" styleId="zgh" />
									<span style="color: red">注：请上传jpg，gif，png，bmp 格式的文件，限 1
										M 。</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" name="保存" onclick="updateDwwh('update');" id="buttonSave">
									保 存
								</button>
								<logic:empty name="flag">
									<button type="button" name="关闭" onclick="Close();return false;"
										id="buttonClose">
										关 闭
									</button>
								</logic:empty>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

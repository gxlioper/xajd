<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
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
			jQuery(function(){
			
			//jQuery("img").attr('src',"/xgxt/teaPic.jsp?zgh=test");
				jQuery("#zhaopian").attr('src','<%=request.getContextPath() %>/teaPic.jsp?zgh=${model.zgh}&t='+new Date());
				// jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(4,2)+'-'+jQuery("#csrq").val().substr(6,2)));
			});
			
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
			function calAge(){
				jQuery("#age").val( calculateAges(jQuery("#csrq").val().substr(0,4)+'-'+jQuery("#csrq").val().substr(4,2)+'-'+jQuery("#csrq").val().substr(6,2)));
			}
		</script>
	</head>
	<body>
		
		<html:form action="/data_search" method="post" styleId="dwwh_form">
			<%--<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>--%>

					<div class="tab" style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="5"><span>基本资料</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
								<tr>
									<th align="right" ><font color="red">*</font>工号</th>
									<td align="left" style="width: 25%">
										<input type="hidden" />
										<html:hidden property="zgh" name="model" styleId="zgh"  />
										<html:text property="zgh" name="model" styleId="zgh" disabled="true" maxlength="20" ></html:text>
									</td>
									<th align="right"><font color="red">*</font>姓名</th>
									<td align="left" style="width: 25%">
										<html:text property="xm" name="model" styleId="xm" maxlength="20" ></html:text>
									</td>
									<th align="left" rowspan="5">
										<div align="center">
										<img src="" style="height:133px;width:100px;" border="0"   id="zhaopian"/>
										</div>
										<div align="center">
											<button type="button" onclick="showZpscDiv();" style="width:100px" id="buttonSave">上传照片</button>
										</div>
									</th>
							</tr>
							<tr>
								<th align="right" width="15%">
									<font color="red">*</font>所在部门
								</th>
								<td align="left">
									<logic:equal value="true" name="flag">
										<html:select name='model'  property="bmdm" styleId="bmdm"  disabled="true" style="width:150px;" >
											<html:options collection="bmList" property="bmdm" labelProperty="bmmc"  />
										</html:select>
									</logic:equal>
									<logic:notEqual value="true" name="flag">
										<html:select name='model'  property="bmdm" styleId="bmdm" disabled="${xy}" style="width:150px;" >
											<html:options collection="bmList" property="bmdm" labelProperty="bmmc"  />
										</html:select>
									</logic:notEqual> 
								</td>
								
								<th align="right">
									<font color="red">*</font>性别
								</th>
								<td align="left">
									<html:select property="xb" name="model" styleId="xb"  style="width:150px;">
										<html:option value="1">男</html:option>
										<html:option value="2">女</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								
								<th align="right">
									职称
								</th>
								<td align="left">
									<html:select name='model'  property="zc" styleId="zc"  style="width:150px;">
										<html:options collection="zcList" property="zcdm" labelProperty="zcmc" />
									</html:select>
								</td>
								<th align="right">
									政治面貌
								</th>
								<td align="left">
									<html:select name='model'  property="zzmm" styleId="zzmm"  style="width:150px;">
										<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									职务
								</th>
								<td align="left">
									<html:select name='model'  property="zw" styleId="zw"  style="width:150px;">
										<html:options collection="zwList" property="zwdm" labelProperty="zwmc" />
									</html:select>
								</td>
								<th align="right">
									出生日期
								</th>
								<td align="left">
									<html:text property="csrq" name="model" styleId="csrq" onblur="dateFormatChg(this);" onclick="return showCalendar('csrq','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
							</tr>
							<logic:equal value="10407" name="xxdm">
							<tr>
								
								<th align="right">
									专业技术职务
								</th>
								<td align="left">
									<html:text property="kzzd6" name="model" styleId="kzzd6" maxlength="10" ></html:text>
								</td>
								<th align="right">
									对口工作
								</th>
								<td align="left">
									<html:text property="kzzd7" name="model" styleId="kzzd7" maxlength="10" ></html:text>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th align="right">
									年龄
								</th>
								<td align="left" >
									<html:text property="age" name="model" styleId="age"  onclick="return calAge();" readonly="true" style="cursor:hand " ></html:text>
								</td>
								
								<th align="right">
									民族
								</th>
								<td align="left">
									<html:select name='model'  property="mz" styleId="mz"  style="width:150px;">
										<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									学历
								</th>
								<td align="left">
									<html:select property="xl" name="model"  styleId="xl"  style="width:150px;"> 
										<html:option value=""></html:option>
										<html:option value="大专">大专</html:option>
										<html:option value="本科">本科</html:option>
										<html:option value="研究生">研究生</html:option>
									</html:select>
								</td>
								
								<th align="right">
									学位
								</th>
								<td align="left" colspan="2">
									<html:select property="xw" name="model"  styleId="xw" style="width:150px;"> 
										<html:option value=""></html:option>
										<html:option value="学士">学士</html:option>
										<html:option value="硕士">硕士</html:option>
										<html:option value="博士">博士</html:option>
										<html:option value="博士后">博士后</html:option>
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th align="right">
									毕业院校
								</th>
								<td align="left">
									<html:text property="byyx" name="model" styleId="byyx" maxlength="15" ></html:text>
								</td>
								<th align="right">
									身份证号
								</th>
								<td align="left" colspan="2">
									<html:text property="sfzh" name="model" styleId="sfzh" maxlength="18" ></html:text>
								</td>
							</tr>
							         <tr>
								<th align="right">
									所学专业
								</th>
								<td align="left">
									<html:text property="sxzy" name="model" styleId="sxzy" maxlength="15" ></html:text>
								</td>
								<th align="right">
									籍贯
								</th>
								<td align="left" colspan="2">
										<html:select name='model'  property="jgxs" styleId="jgxs"  style="width:150px;">
											<html:option value=""></html:option>
											<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
										</html:select>
								</td>
							</tr>  
						    <tr>
								<th align="right">
									入校工作时间
								</th>
								<td align="left">
									<html:text property="lxgzsj" name="model" styleId="lxgzsj"  onblur="dateFormatChg(this)" onclick="return showCalendar('lxgzsj','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
								<th align="right">
									研究方向
								</th>
								<td align="left"  colspan="2">
									<html:text property="xsgzyjfx" name="model" styleId="xsgzyjfx" maxlength="20" ></html:text>
								</td>
								
							</tr>  
							<tr>
								<th align="right">
									岗位类别
								</th>
								<td align="left">
									<html:select name='model'  property="gwlbdm" styleId="gwlbdm"  style="width:150px;">
										<html:options collection="gwlbList" property="gwlbdm" labelProperty="gwlbmc" />
									</html:select>
								</td>
								<th align="right">
									职业技能证书
								</th>
								<td align="left"  colspan="2">
									<html:text property="zyjnzs" name="model" styleId="zyjnzs" maxlength="20" >
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									工资号
								</th>
								<td align="left">
									<html:text property="kzzd5" name="model" styleId="kzzd5" maxlength="20" ></html:text>
								</td>
								<th align="right">
									单位类别
								</th>
								<td align="left" colspan="2">
									<html:text property="dwlbdm" name="model" styleId="dwlbdm" maxlength="10" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
								          开户银行
								</th>
								<td align="left">
									<html:text property="khyh" name="model" styleId="khyh" maxlength="20" ></html:text>
								</td>
								<th align="right">
									银行账号
								</th>
								<td align="left"  colspan="2">
									<html:text property="yhzh" name="model" styleId="yhzh" maxlength="20" >
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
								         婚姻状况
								</th>
								<td align="left">
									<html:select name='model'  property="hyzk" styleId="hyzk"  style="width:150px;">
										<html:option value="已婚">已婚</html:option>
										<html:option value="未婚">未婚</html:option>
									</html:select>
								</td>
								<th align="right">
									辅导员值班室
								</th>
								<td align="left"  colspan="2">
									<html:text property="fdyzbs" name="model" styleId="fdyzbs" maxlength="20" >
									</html:text>
								</td>
							</tr>
						    <tr>
								<th align="right">
								         何时担任辅导员
								</th>
								<td align="left">
									<html:text property="fdyrzrq" name="model" styleId="fdyrzrq" maxlength="20" onblur="dateFormatChg(this)" onclick="return showCalendar('fdyrzrq','y-mm-dd');" readonly="true" style="cursor:hand " >
									</html:text>
								</td>
								<th align="right">家庭住址</th>
								<td align="left"  colspan="2">
									<html:text property="jtzz" name="model" styleId="jtzz" maxlength="50" style="width:250px;"  ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">主要职责
								<br/><font color="red">限200个字</font>
								</th>
								<td align="left" colspan="4">
									<html:textarea property="zyzz" name="model" styleId="zyzz" rows="3" cols="80" ></html:textarea>
									
								</td>
							</tr>
							<tr>
								<th align="right">获奖情况
								<br/><font color="red">限2000个字</font>
								</th>
								<td align="left" colspan="4">
									<html:textarea property="grhjqk" name="model" styleId="grhjqk" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<thead>
			    				<tr>
			        				<th colspan="5"><span>联系方式</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									联系电话
								</th>
								<td align="left">
									<html:text property="lxdh" name="model" styleId="lxdh" maxlength="15" ></html:text>
								</td>
								<th align="right">
									移动电话
								</th>
								<td align="left" colspan="2">
									<html:text property="yddh" name="model" onkeyup="checkInputData(this)" styleId="yddh" maxlength="15" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									办公电话
								</th>
								<td align="left">
									<html:text property="bgdh" name="model" onblur="checkPhone(this)" styleId="bgdh" maxlength="15" ></html:text>
								</td>
								<th align="right">
									传真
								</th>
								<td align="left" colspan="2">
									<html:text property="cz" name="model" styleId="cz" maxlength="20" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									电子邮箱
								</th>
								<td align="left">
									<html:text property="dzyx" name="model" styleId="dzyx" maxlength="25" ></html:text>
								</td>
								<th align="right">
									QQ
								</th>
								<td align="left" colspan="2">
									<html:text property="kzzd3" name="model" onkeyup="value=value.replace(/[^\d]/g,'') " onblur="value=value.replace(/[^\d]/g,'')" styleId="kzzd3" maxlength="15" ></html:text>
								</td>
								
							</tr>
							
							<tr>
								<th align="right">
									微信名
								</th>
								<td align="left">
									<html:text property="kzzd1" name="model" styleId="kzzd1" maxlength="25" ></html:text>
								</td>
								<th align="right">
									微博名
								</th>
								<td align="left" colspan="4">
									<html:text property="kzzd2" name="model" styleId="kzzd2" maxlength="25" ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									办公地点
								</th>
								<td align="left" colspan="4">
									<html:text property="bgdd" name="model" styleId="bgdd" maxlength="25" style="width:575px;" ></html:text>
								</td>
							</tr>
							<tr>
								
								<th align="right">
									通讯地址
								</th>
								<td align="left" colspan="4">
									<html:text property="txdz" name="model" styleId="txdz" maxlength="25" style="width:575px;" ></html:text>
								</td>
							</tr>
							
							<thead>
			    				<tr>
			        				<th colspan="5"><span>工作经历</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									参加工作日期
								</th>
								<td align="left">
									<html:text property="cjgzrq" name="model" styleId="cjgzrq"  onblur="dateFormatChg(this)" onclick="return showCalendar('cjgzrq','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
								<th align="right">
									思政工作时间
								</th>
								<td align="left" colspan="2">
									<html:text property="szgzsj" name="model" styleId="szgzsj"  onblur="dateFormatChg(this)" onclick="return showCalendar('szgzsj','y-mm-dd');" readonly="true" style="cursor:hand " ></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									工作经历
									<br/><font color="red">限2000个字</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="gzjl" name="model" styleId="gzjl" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<thead>
			    				<tr>
			        				<th colspan="5"><span>其它</span></th>
			        			</tr>
			   				 </thead>
							<tr>
								<th align="right">
									评奖评优
									<br/><font color="red">限2000个字</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="kzzd4" name="model" styleId="kzzd4" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									发表论文
									<br/><font color="red">限2000个字</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="fblw" name="model" styleId="fblw" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									参加培训
									<br/><font color="red">限300个字</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="pxqk" name="model" styleId="pxqk" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							<tr>
								<th align="right">
									备注
									<br/><font color="red">限2000个字</font>
								</th>
								<td align="left"  colspan="4">
									<html:textarea property="bz" name="model" styleId="bz" rows="3" cols="80" ></html:textarea>
								</td>
							</tr>
							</tbody><%--
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
						--%></table>
						</div>
						
			<!-- 完善教师信息 - 上传照片 -->
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
									<input type="file" id="teaPic" name="teaPic" style="width:90%"
										   onchange='uploadTeaPic();closeWindown();'
									/>
									<br/><html:hidden property="zgh" name="model" styleId="zgh"  />
									<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
						<table width="100%"  border="0" class="formlist">
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
			 </table>
	</html:form>
	</body>
</html>

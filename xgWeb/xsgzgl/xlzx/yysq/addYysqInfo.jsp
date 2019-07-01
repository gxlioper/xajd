<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/addYysqInfo.js"></script>
		<script type="text/javascript" >
		function saveYysqInfo(){
			var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
			if(jQuery("#xstell").val()=='' || jQuery("#yyzxzt").val()=='' || (jQuery("#sjddm").length > 0 && sjddm=='')){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
            if (jQuery("#xstxxx_tb").length > 0 ) {
                var checkId = 'dzyx-fqzy-fxl-mqzy-mxl-jtdz-zxmd';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
                    return false;
                }
                var hf = jQuery("input[type=radio][name='hf']:checked").length;
                var sfyzn = jQuery("input[type=radio][name='sfyzn']:checked").length;
                var sfdszn = jQuery("input[type=radio][name='sfdszn']:checked").length;
                var syd = jQuery("input[type=radio][name='syd']:checked").length;
                if(hf <=0 || sfyzn <=0 || sfdszn <=0 || syd <=0){
                    showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
                    return false;
				}
            }
            // 得到JSON对象
            //以下变量处理完全是出于两种预约模式区分的需要，防止变量有undifined
            var qssj = (!jQuery("#qssj").val()) ? "" : jQuery("#qssj").val();
            var jssj = (!jQuery("#jssj").val()) ? "" : jQuery("#jssj").val();
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//新增预约申请信息，将状态置为1预约中.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:qssj,
				jssj:jssj,
				sjddm:sjddm,
				yyzxzt:encodeURI(encodeURI(jQuery("#yyzxzt").val())),
				yyzxxq:encodeURI(encodeURI(jQuery("#yyzxxq").val()))
			};
            if (jQuery("#yytxxx_tb").length > 0 ) {
                var checkId = 'bczxwt-zxhzt';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
                    return false;
                }
                var qxztzt = jQuery("input[type=radio][name='qxztzt']:checked");
                var qxztjl = jQuery("input[type=radio][name='qxztjl']:checked");
                var qxztyy = jQuery("input[type=radio][name='qxztyy']:checked");
                var sczxhgb = jQuery("input[type=radio][name='sczxhgb']:checked");
                var zjzt = jQuery("input[type=radio][name='zjzt']:checked");
                if(qxztzt.length <=0 || qxztjl.length <=0 || qxztyy.length <=0 || sczxhgb.length <=0 || zjzt.length <=0){
                    showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
                    return false;
                }
                parameter["qxztzt"] = qxztzt.val();
                parameter["qxztjl"] = qxztjl.val();
                parameter["qxztyy"] = qxztyy.val();
                parameter["sczxhgb"] = sczxhgb.val();
                parameter["zjzt"] = zjzt.val();
                parameter["bczxwt"] = encodeURI(encodeURI(jQuery("#bczxwt").val()));
                parameter["zxhzt"] = encodeURI(encodeURI(jQuery("#zxhzt").val()));
            }

			var url = "xlzx_yysq.do?method=saveYysqInfo";
			showConfirm("确认保存预约信息？",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
                ajaxSubFormWithFun("form", "xlzx_xstxxx.do?method=save", function(data) {
                    if(data == true){
                        jQuery.post(url,parameter,function(data){
                            if(data == true){
                                showAlert("保存成功！",{},{"clkFun":function(){
                                    frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
                                    iFClose();
                                }});
                            }else{
                                showAlert("保存预约信息失败！",{},{"clkFun":function(){
                                }});
                            }
                        },'json');
					}else{
                        showAlert("保存预约信息失败！",{},{"clkFun":function(){
                        }});
                    }
                });
				jQuery.ajaxSetup({async:true});
			}});
		}	
		jQuery(function(){
			var flag = "${flag}";
			if(flag == 0 && ${xxdm} == 10026){
				jQuery("#buttonSave").hide();
			}

            jQuery("#div_zmr").hide();
            jQuery("input[type=radio][name=sfdszn]").click(function(){
                var sfdszn = jQuery("input[type=radio][name=sfdszn]:checked").val();
                if(sfdszn == '1'){
                    jQuery("#div_zmr").hide();
                }else{
                    jQuery("#div_zmr").show();
                }
            });
		});
		function checkEmail(obj) {
            var dzyx = jQuery(obj).val();
            if(!isEmail(dzyx) && dzyx!=""){
                showAlertDivLayer("请输入正确的电子邮箱！");
                return false;
            }
        }
		</script>
	</head>
	<body >
	
		<html:form styleId="form" action="/xlzx_yysq" method="post" >
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="xh" id="xh" value="${yysqInfo.xh}" />
			<input type="hidden" name="zgh" id="zgh" value="${yysqInfo.zgh}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yysqInfo.yyzxrq}" />

			<div style='height:550px;width:100%;overflow-y:auto;overflow-x:hidden'>

				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>

					<thead>
					<tr>
						<th colspan="4">
							<span>学生填写信息</span>
						</th>
					</tr>
					</thead>

					<logic:notEmpty name="xstxxx">
						<%@ include file="/xsgzgl/xlzx/yysq/viewXstxxx.jsp" %>
					</logic:notEmpty>
					<logic:empty name="xstxxx">
					<tbody id="xstxxx_tb">
						<tr>
							<th>
								<span class="red">*</span>婚否
							</th>
							<td >
								<label><input type="radio" name="hf" value="1" />是</label>
								<label><input type="radio" name="hf" value="0" />否</label>
							</td>
							<th>
								<span class="red">*</span>是否有子女
							</th>
							<td  >
								<label><input type="radio" name="sfyzn" value="1" />是</label>
								<label><input type="radio" name="sfyzn" value="0" />否</label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>独生子女
							</th>
							<td  >
								<label><input type="radio" name="sfdszn" value="1" />是</label>
								<label><input type="radio" name="sfdszn" value="0" />否</label>
								<div id="div_zmr" style="margin-left: 10px;float: right" >
									姊妹人：<input type="text" name="zmr" style="width: 80px;" id="zmr" onblur="checkLen(this,2)">
								</div>
							</td>
							<th>
								<span class="red">*</span>电子邮箱
							</th>
							<td  >
								<input type="text" name="dzyx" id="dzyx"
									   maxlength="30"  onblur="checkEmail(this)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>父亲职业
							</th>
							<td >
								<input type="text" name="fqzy" id="fqzy" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>父亲学历
							</th>
							<td  >
								<input type="text" name="fxl" id="fxl" onblur="checkLen(this,50)"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>母亲职业
							</th>
							<td >
								<input type="text" name="mqzy" id="mqzy" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>母亲学历
							</th>
							<td  >
								<input type="text" name="mxl" id="mxl" onblur="checkLen(this,50)"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>家庭地址
							</th>
							<td >
								<input type="text" name="jtdz" id="jtdz" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>生源地
							</th>
							<td>
								<label><input type="radio" name="syd" value="cs" />城市</label>
								<label><input type="radio" name="syd" value="xc" />县城</label>
								<label><input type="radio" name="syd" value="nc" />乡镇农村</label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>咨询目的
								<br />
								<font color="red">(限制在500字内)</font>
							</th>
							<td colspan="3">
							<textarea name="zxmd" id="zxmd" rows="4" cols=""
									  style="width: 98%" onblur="checkLen(this,500)"></textarea>
							</td>
						</tr>
					</tbody>
					</logic:empty>

				</table>
				<logic:equal value="1" name="sfscsq">
				<table width="100%" border="0" class="formlist" id="yytxxx_tb">
					<thead>
					<tr>
						<th colspan="2">
							<span>预约填写信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th rowspan="3" width="20%">一周的情绪状态</th>
							<td width="80%">
								<span class="red">*</span>总体：
								<label><html:radio property="qxztzt" value="hh">很好</html:radio></label>
								<label><html:radio property="qxztzt" value="jh">较好</html:radio></label>
								<label><html:radio property="qxztzt" value="yb">一般</html:radio></label>
								<label><html:radio property="qxztzt" value="jc">较差</html:radio></label>
								<label><html:radio property="qxztzt" value="hc">很差</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>焦虑：
								<label><html:radio property="qxztjl" value="yz">严重</html:radio></label>
								<label><html:radio property="qxztjl" value="jz">较重</html:radio></label>
								<label><html:radio property="qxztjl" value="y">有 &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztjl" value="qw">轻微</html:radio></label>
								<label><html:radio property="qxztjl" value="w">无</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>抑郁：
								<label><html:radio property="qxztyy" value="yz">严重</html:radio></label>
								<label><html:radio property="qxztyy" value="jz">较重</html:radio></label>
								<label><html:radio property="qxztyy" value="y">有 &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztyy" value="qw">轻微</html:radio></label>
								<label><html:radio property="qxztyy" value="w">无</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>上次咨询后的改变</th>
							<td>
								<label><html:radio property="sczxhgb" value="hmx">很明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="jmx">较明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="yb">一般</html:radio></label>
								<label><html:radio property="sczxhgb" value="jbmx">较不明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="bmx">不明显</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>对自己最近的状态</th>
							<td>
								<label><html:radio property="zjzt" value="hmy">很满意</html:radio></label>
								<label><html:radio property="zjzt" value="jmy">较满意</html:radio></label>
								<label><html:radio property="zjzt" value="yb">一般</html:radio></label>
								<label><html:radio property="zjzt" value="jbmc">较不满意</html:radio></label>
								<label><html:radio property="zjzt" value="bmy">不满意</html:radio></label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>本次咨询的问题
								<br />
								<font color="red">(限制在500字内)</font>
							</th>
							<td>
								<html:textarea property="bczxwt" styleId="bczxwt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>上次咨询后的生理、心理状态和社会功能
								<br />
								<font color="red">(限制在500字内)</font>
							</th>
							<td>
								<html:textarea property="zxhzt" styleId="zxhzt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
					</tbody>
				</table>
				</logic:equal>


				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>预约申请信息</span>
							</th>
						</tr>
					</thead>
						<tbody id="yysqInfo">
						<tr style="height:10px">
							<th  width="16%">
								预约咨询日期
							</th>
							<td width="34%">
									<span class="red"><B>${ yysqInfo.yyzxrq}</B></span>
							</td>
						 	<th width="16%">
								<logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal>预约咨询时段
							</th>
							<td width="34%">
								<logic:notEqual value="2" name="pbfs">
									<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${yysqInfo.qssj}" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" />&nbsp;至&nbsp;
									<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${yysqInfo.jssj}" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'qssj\\')}'})" readonly="true" />
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									<logic:notEqual value="10026" name="xxdm">
										<html:select styleId="sjddm" property="sjddm">
											<html:option value=""/>
											<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
										</html:select>
									</logic:notEqual>
									<logic:equal value="10026" name="xxdm">
										<logic:equal value="1" name="flag">
											<html:select styleId="sjddm" property="sjddm">
												<html:option value=""/>
												<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
											</html:select>
										</logic:equal>
										<logic:notEqual value="1" name="flag">
											<span style="color:red">当前无可预约的时间段</span>
										</logic:notEqual>
									</logic:equal>
								</logic:equal>
							</td>
						</tr>
						
						<tr style="height:10px">	
							<th>
								<span class="red">*</span>预留联系号码
							<td>
								<html:text property="xstell" styleId="xstell"  maxlength="11"  value="${ yysqInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
							</td>
							<th width="16%">
								学年 学期
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<span class="red">*</span>咨询主题
							</th>
							<td colspan="3">
								<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yysqInfo.yyzxzt}" />
							</td>
						</tr>
						<tr style="height:10px">
						<th>咨询概要<br/>
								<font color="red"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" 
									value="${ yysqInfo.yyzxxq}" onblur="chLengs(this,500);" rows='6' />
							</td>
						</tr>
			
					</tbody>
					</table>
				</div>
				<table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveYysqInfo();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		
		</html:form>
	</body>
</html>


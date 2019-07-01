<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdbljg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>

            jQuery(function(){
                var hdxs = jQuery("#hdxs").val();
                if("课程" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("讲座" == hdxs){
                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("具体类型");
                    jQuery("#con_span").html("讲座介绍");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                }
                kcjbChange();
            });

            function changeHdxs(){
                var hdxs = jQuery("#hdxs").val();
                if("课程" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                    JzInfoEmpty();
                }else if("讲座" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("具体类型");
                    jQuery("#con_span").html("讲座介绍");

                    jQuery("#jzlxTr").hide();
                    jQuery("#jzlx").val("");
                    jQuery("#zxkclx").val("");
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    JzInfoEmpty();
                    jQuery("#jzlxTr").hide();
                    jQuery("#jzlx").val("");
                    jQuery("#zxkclx").val("");

                }
            }
            function JzInfoEmpty(){
                var tr = jQuery("tr[name='zjrxx_tr']");
                tr.hide();
                tr.find("input").val("");
                tr.find("textarea").val("");
                tr.find("select").val("");
                jQuery("#lx_span").html("活动类型");
                jQuery("#con_span").html("活动内容及心得");
            }
            /**
             * 课程级别change
             */
            function kcjbChange(){
                var v = jQuery("#jzlx").val();
                if("003" == v){
                    jQuery("#zxkclxTh").removeAttr("style");
                    jQuery("#zxkclxTd").removeAttr("style");
                } else {
                    jQuery("#zxkclxTh").attr("style","display:none");
                    jQuery("#zxkclxTd").attr("style","display:none");
                }
            }

			var i = 0;
			function addRow(){
				i++;
				var tr = "<tr><td align='center'><input type='checkbox' name='ckr' /></td>" +
				"<td><select name='jdlxs'><option value='1'>学生</option><option value='2'>老师</option></select></td>" +
				"<td><input type='text' name='jdmcs' maxlength='20' style='width:80px'></td><td><input type='text' name='jdkssjs' id='kssjs"+i+"' maxlength='20' onfocus='showCalendar(\"kssjs"+i+"\",\"y-mm-dd\");' style='width:80px'></td><td><input type='text' name='jdjssjs' id='jssjs"+i+"' maxlength='20' onfocus='showCalendar(\"jssjs"+i+"\",\"y-mm-dd\");' style='width:80px'></td>"+
				"<td><select name='sfsljxs' style='width:80px'><option value='0'>否</option><option value='1'>是</option></select></td><td><select name='sfsldfs' style='width:80px'><option value='0'>否</option><option value='1'>是</option></select></td><td><select name='sfslxfs' style='width:80px'><option value='0'>否</option><option value='1'>是</option></select></td>";
				tr+="<td><select name='sftjs' style='width:80px'><option value='0'>否</option><option value='1'>是</option></select></td></tr>";
				jQuery("#jdsz").append(tr);
			}

			function delRow(){
				var checkboxs = jQuery("input[name='ckr']:checked");
				if(checkboxs.length > 0){
					jQuery(checkboxs).each(function(){
						jQuery(this).parents("tr").eq(0).remove();
					})
				}else{
					showAlertDivLayer("请勾选要删除的记录！");
					return false;
				}
			}

			//增加奖项
			function addRowJx(){
				var tr = "<tr><td align='center' style='width:10%'><input type='checkbox' name='ckrs' /></td>" +
				"<td style='width:30%'><input type='text' name='jxmcs' maxlength='20' style='width:100%'/></td>" +
				"<td style='width:60%'><input type='text' name='jxsms' maxlength='1000' style='width:100%'/></td></tr>";
				jQuery("#jxsz").append(tr);
			}

			//删除奖项
			function delRowJx(){
				var checkboxs = jQuery("input[name='ckrs']:checked");
				if(checkboxs.length > 0){
					jQuery(checkboxs).each(function(){
						jQuery(this).parents("tr").eq(0).remove();
					})
				}else{
					showAlertDivLayer("请勾选要删除的记录！");
					return false;
				}
			}

			//保存设置
			function saveSz(){
				var checkboxs = jQuery("[name='hdbqs']:checked").length;
				if(checkboxs < 1){
					showAlertDivLayer("请选择活动标签！");
					return false;
				}
                var hdxs = jQuery("#hdxs").val();
                var jzlx = jQuery("#jzlx").val();
                var ids = "zbf-hdlx-hddd-xsxxlx-hdkclx-hdxs-jzjb";
                if("讲座" == hdxs){
                    ids += "-zjrxm-zjrdw-zjrzc-zjrzw";
                }
                if(!checkNotNull(ids)){
                    showAlert("请将带<font color='red'>*</font>的项目填写完整");
                    return false;
                }
                var nlbqLen = jQuery("[name='nlbqs']:checked").length;
                if(nlbqLen > 3){
                    showAlert("能力标签最多只能选三个，请确认！");
                    return false;
                }
                if("课程" == hdxs){
                    if(jzlx == null || jzlx == ''){
                        showAlert("请选择课程级别！");
                        return false;
                    } else {
                        var zxkclx = jQuery("#zxkclx").val();
                        if(zxkclx == ""){
                            showAlert("请选择自选课程类型！");
                            return false;
                        }
                    }
                }

                if(jQuery(".MultiFile-label").length < 1){
                    showAlert("请上传附件");
                    return false;
                }

				var hdjdmcs = jQuery("[name='jdmcs']");
				var kssjs = jQuery("[name='jdkssjs']");
				var jssjs = jQuery("[name='jdjssjs']");

				var flg = true;
				
				jQuery(hdjdmcs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				
				jQuery(kssjs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}

				jQuery(jssjs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}

				if(trim(jQuery("#jdsz").html())== null || trim(jQuery("#jdsz").html()) == ""){
					showAlert("请设置活动阶段！");
					return false;
				}

				if(!!(jQuery("#jxsz"))){
					jQuery("[name='jxmcs']").each(function(){
						if(!!jQuery(this).val() == false){
							flg = false;
							return;
						}
					})
				}

				if(!flg){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}

				var url = "hdgl_hdxx.do?method=saveSz";
				ajaxSubFormWithFun("hdxxForm", url, function(data) {
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
		<html:hidden property="hdid" styleId="hdid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th  colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>活动名称</span>
						</th>
						<td width="35%">
								${data.hdmc}
						</td>
						<th width="15%">
							<span><font color="red">*</font>活动时间</span>
						</th>
						<td width="35%">
								${data.hdkssj} 至 ${data.hdjssj}
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>主办方
						</th>
						<td >
							<html:text property="zbf" styleId="zbf" maxlength="50"/>
						</td>
						<th width="15%">
							<font color="red">*</font>活动地点
						</th>
						<td width="35%">
							<html:text property="hddd" styleId="hddd" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>线上or线下活动</span>
						</th>
						<td width="35%">
							<html:select property="xsxxlx" styleId="xsxxlx"  style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:option value="线上">线上活动</html:option>
								<html:option value="线下">线下活动</html:option>
							</html:select>
						</td>
						<th width="15%">
							<span><font color="red">*</font>活动形式</span>
						</th>
						<td width="35%">
							<html:select property="hdkclx" styleId="hdkclx"  style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:option value="考核类">考核类（多阶段）</html:option>
								<html:option value="参与类">参与类（单阶段）</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>活动性质</span>
						</th>
						<td width="35%">
							<html:select property="hdxs" styleId="hdxs" onchange="changeHdxs()" style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:option value="活动">活动</html:option>
								<html:option value="课程">课程</html:option>
								<html:option value="讲座">讲座</html:option>
							</html:select>
						</td>
						<th>
							<font color="red">*</font><span id="lx_span">活动类型</span>
						</th>
						<td>
							<html:select property="hdlx" styleId="hdlx" style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:options collection="hdlxList" labelProperty="hdlxmc" property="hdlxdm"/>
							</html:select>
						</td>
					</tr>

					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>主讲人姓名
						</th>
						<td>
							<html:text property="zjrxm" styleId="zjrxm" maxlength="10"/>
						</td>
						<th >
							<font color="red">*</font>主讲人单位
						</th>
						<td >
							<html:text property="zjrdw" styleId="zjrdw" maxlength="20"/>
						</td>
					</tr>
					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>主讲人职称
						</th>
						<td>
							<html:select property="zjrzc" styleId="zjrzc" style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:options collection="zjrzcList" labelProperty="mc" property="dm"/>
							</html:select>
						</td>
						<th >
							<font color="red">*</font>主讲人职务
						</th>
						<td >
							<html:text property="zjrzw" styleId="zjrzw" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>活动级别

						</th>
						<td colspan="3">
							<html:select property="jzjb" styleId="jzjb" style="width:173px">
								<html:option value="">--请选择--</html:option>
								<html:option value="校级活动">校级活动</html:option>
								<html:option value="院级活动">院级活动</html:option>
								<html:option value="自选活动">自选活动</html:option>
							</html:select>
						</td>

					</tr>
					<tr name="zjrxx_tr">
						<th>
							主讲人介绍
							<br><font color="red">(限100字)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="2" property="zjrjs" styleId="zjrjs"
										   style="width:95%" onblur="checkLen(this,100);"/>
						</td>
					</tr>


					<tr id="jzlxTr">
						<th>
							<font color="red">*</font>课程级别
						</th>
						<td>
							<html:select property="jzlx" styleId="jzlx" style="width:173px" onchange="kcjbChange()">
								<html:option value="">---&nbsp;请选择课程级别&nbsp;---</html:option>
								<html:options collection ="jzlxList" property="jzlxdm" labelProperty="jzlxmc" />
							</html:select>
						</td>
						<th id="zxkclxTh" style="display: none;">
							<font color="red">*</font>自选课程类型
						</th>
						<td id="zxkclxTd" style="display: none;">
							<html:select property="zxkclx" styleId="zxkclx" style="width:173px">
								<html:option value="">---&nbsp;请选择自选课程类型&nbsp;---</html:option>
								<html:options collection ="zxckclxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color='red'>*</font>活动标签
						</th>
						<td colspan="3">
							<logic:iterate name="activityLabelList" id="bq">
								<%--<html:checkbox property="hdbqs" value="${bq.dm}">${bq.mc}</html:checkbox>--%>
								<label><html:multibox property="hdbqs" value="${bq.hdbqdm}"/>${bq.hdbqmc}</label>
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th>
							能力标签

						</th>
						<td colspan="3">
							<logic:iterate name="abilityLabelList" id="bq">
								<label><html:multibox property="nlbqs" value="${bq.nlbqdm}"/>${bq.nlbqmc}</label>
							</logic:iterate>
							<br><font color="red">（选择参加该活动能提升的综合能力，最多三项）</font>
						</td>
					</tr>
					<tr>
						<th>
							是否需要学生立项
						</th>
						<td >
							<html:select property="sflx" styleId="sflx" style="width:50%">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</td>
						<th>
							是否有第二课堂学分
						</th>
						<td >
							<html:select property="sfdektxf" styleId="sfdektxf" style="width:50%">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</td>
					</tr>

					<tr>
						<th>
							签到开始时间
						</th>
						<td >
							<html:text property="qdkssj" styleId="qdkssj"  style="width:120px;"
									   onfocus="showCalendar('qdkssj','yyyy-MM-dd HH:mm',true,'qdjssj');"/>
						</td>
						<th>
							签到结束时间
						</th>
						<td >
							<html:text property="qdjssj" styleId="qdjssj"
									   onfocus="showCalendar('qdjssj','yyyy-MM-dd HH:mm',false,'qdkssj');"/>
						</td>
					</tr>
					<tr>
						<th>
							签退开始时间
						</th>
						<td >
							<html:text property="qtkssj" styleId="qtkssj"  style="width:120px;"
									   onfocus="showCalendar('qtkssj','yyyy-MM-dd HH:mm',false,'qdkssj');"/>
						</td>
						<th>
							签退结束时间
						</th>
						<td >
							<html:text property="qtjssj" styleId="qtjssj"
									   onfocus="showCalendar('qtjssj','yyyy-MM-dd HH:mm',false,'qtkssj');"/>
						</td>
					</tr>

					<tr>
						<th>
							<span><font color="red">*</font>附件</span>
						</th>
						<td colspan="3">
							<html:hidden property="fjpath" styleId="fjpath"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 3,
                                        //后缀
                                        accept : 'png|gif|jpg|zip|rar|doc|docx',
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'fjpath'
                                    });
                                });
							</script>
						</td>
					</tr>
					</tbody>
					
					<thead>	
				    	<tr>
				        	<th colspan="4"><span>奖项设置</span>&nbsp;&nbsp;&nbsp;&nbsp;
				        	<logic:equal value="1" name="sfksz">
					        	<a href="#" onclick="addRowJx();"><img src="images/knsrd/jiahao.gif" alt="增加" /></a>&nbsp;
					        	<a href="#" onclick="delRowJx();"><img src="images/knsrd/jianhao.gif" alt="删除" /></a>&nbsp;
				        	</logic:equal>
				        </tr>
		    		</thead>
		    		
		    		<tbody>
		    			<tr>
	    					<td colspan="4">
	    						<table style="width:100%">
	    							<thead>
										<tr>
											<logic:equal value="1" name="sfksz">
												<td style="width:5%"></td>
											</logic:equal>
											<td style="width:20%">
												<logic:equal value="1" name="sfksz">
													<font color='red'>*</font>
												</logic:equal>
												奖项名称
											</td>
											<td style="width:75%">
												奖项说明
											</td>
										</tr>
									</thead>
									<tbody id="jxsz">
										<logic:equal value="1" name="sfksz">
											<logic:iterate id="hdjx" name="hdjxList">
												<tr>
													<td align='center' style="width:10%"><input type='checkbox' name='ckrs' /></td>
													<td style="width:30%">
														<input type="text" name="jxmcs" value="${hdjx.jxmc}" maxlength="30" style="width:100%"/>
													</td>
													<td style="width:60%">
														<input type='text' name='jxsms' maxlength='1000' value="${hdjx.jxsm}" style="width:100%"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>
										<logic:equal value="2" name="sfksz">
											<logic:iterate id="hdjx" name="hdjxList">
												<tr>
													<td style="width:100px">
														${hdjx.jxmc}
													</td>
													<td style="width:100px">
														${hdjx.jxsm}
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>
									</tbody>
	    						</table>
	    					</td>
		    				</tr>

		    		</tbody>
					
						<thead>	
					    	<tr>
					        	<th colspan="4"><span>阶段设置</span>&nbsp;&nbsp;&nbsp;&nbsp;
					        	<logic:equal value="1" name="sfksz">
						        	<a href="#" onclick="addRow();"><img src="images/knsrd/jiahao.gif" alt="增加" /></a>&nbsp;
						        	<a href="#" onclick="delRow();"><img src="images/knsrd/jianhao.gif" alt="删除" /></a>&nbsp;
					        	</logic:equal>
					        </tr>
		    			</thead>
		    			<tbody>
		    				<tr>
		    					<td colspan="4">
		    						<table style="width:100%">
		    							<thead>
											<tr>
												<logic:equal value="1" name="sfksz">
													<td style="width:5%"></td>
												</logic:equal>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													阶段类型
												</td>
												<td style="width:15%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													阶段名称
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">													
														<font color='red'>*</font>
													</logic:equal>
													开始时间
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													截止时间
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													设立奖项
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													设立打分
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													设立学分
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													是否阶段跳转
												</td>
											</tr>
										</thead>
										<tbody id="jdsz">
											<logic:equal value="1" name="sfksz">
												<logic:iterate id="hdjd" name="hdjdList">
													<tr>
														<td align='center'><input type='checkbox' name='ckr' /></td>
														<td>
															<select name='jdlxs'>
																<logic:equal name="hdjd" property="jdlx" value="1">
																	<option value='1'>学生</option>
																</logic:equal>
																<logic:equal name="hdjd" property="jdlx" value="2">
																	<option value='2'>老师</option>
																</logic:equal>
															</select>
														</td>
														<td>
															<input type='text' name='jdmcs' maxlength='20' value="${hdjd.jdmc}" style="width:80px"/>
														</td>
														<td>
															<input id="jdkssj_${hdjd.jdsx}" type="text" name="jdkssjs" maxlength='15' value="${hdjd.jdkssj}" onfocus="showCalendar('jdkssj_${hdjd.jdsx}','y-mm-dd')" style="width:80px" />
														</td>
														<td>
															<input id="jdjssj_${hdjd.jdsx}" type="text" name='jdjssjs' maxlength='15' value="${hdjd.jdjssj}" onfocus="showCalendar('jdjssj_${hdjd.jdsx}','y-mm-dd')" style="width:80px" />
														</td>
														<td>
															<select name='sfsljxs' style="width:80px">
																<logic:equal name="hdjd" property="sfsljx" value="0">
																	<option value='0' selected='selected'>否</option>
																	<option value='1'>是</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfsljx" value="1">
																	<option value='0'>否</option>
																	<option value='1' selected='selected'>是</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfsljx">
																	<option value='0'>否</option>
																	<option value='1'>是</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sfsldfs' style="width:80px">
																<logic:equal name="hdjd" property="sfsldf" value="0">
																	<option value='0' selected='selected'>否</option>
																	<option value='1'>是</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfsldf" value="1">
																	<option value='0'>否</option>
																	<option value='1' selected='selected'>是</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfsldf">
																	<option value='0'>否</option>
																	<option value='1'>是</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sfslxfs' style="width:80px">
																<logic:equal name="hdjd" property="sfslxf" value="0">
																	<option value='0' selected='selected'>否</option>
																	<option value='1'>是</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfslxf" value="1">
																	<option value='0'>否</option>
																	<option value='1' selected='selected'>是</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfslxf">
																	<option value='0'>否</option>
																	<option value='1'>是</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sftjs' style="width:80px">
																<option value='0' <logic:equal name="hdjd" property="sftj" value="0">selected='selected'</logic:equal>>否</option>
																<option value='1' <logic:equal name="hdjd" property="sftj" value="1">selected='selected'</logic:equal>>是</option>
															</select>
														</td>
													</tr>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="2" name="sfksz">
												<logic:iterate id="hdjd" name="hdjdList">
													<tr>
<%--														<td align='center'><input type='checkbox' name='ckr' /></td>--%>
														<td style="width:150px">
															<logic:equal name="hdjd" property="jdlx" value="1">
																学生
															</logic:equal>
															<logic:equal name="hdjd" property="jdlx" value="2">
																老师
															</logic:equal>
														</td>
														<td>
															${hdjd.jdmc}
														</td>
														<td style="width: 100px">
															${hdjd.jdkssj}
														</td>
														<td style="width: 100px">
															${hdjd.jdjssj}
														</td>
														<td style="width:100px">
															${hdjd.sfsljxmc}
														</td>
														<td style="width:100px">
															${hdjd.sfsldfmc}
														</td>
														<td style="width:100px">
															${hdjd.sfslxfmc}
														</td>
														<td style="width:100px">
															${hdjd.sftjmc}
														</td>
													</tr>
												</logic:iterate>
											</logic:equal>
										</tbody>
		    						</table>
		    					</td>
		    				</tr>
		    			</tbody>						
				 </table>			
				</div>
			  <div style="height:25px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveSz();">
									保存
								</button>	 			
								<button type="button" onclick="iFClose();">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


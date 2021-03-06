<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		function saveyyzxInfo(){
				if(!jQuery("tr[name=yyfkId]").is(":hidden")  && (jQuery("#zxrq").val()=='' || jQuery("[name=apzxs]:checked").length == 0)){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				
				if(!jQuery("tr[name=yysbyytr]").is(":hidden") && jQuery("#yysbyy").val()==''){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				//非预约成功的、所选择的预约不是预约成功的，已接待人数不能大于可接待人数。
				if('${pbfs}' != '2'){
					if(jQuery("#status").val()!="2" && jQuery("#yystatus").val()=="2" && parseInt(jQuery("#yjdrs").val())>=parseInt(jQuery("#kjdrs").val())){
						return showAlert("已达到日预约上限！");
					}
				}else{
			        if(jQuery("#sjddm").val() == ""){
			        	return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
					}
				}
				var zxurl='';
				var yyurl='';
				var zxParameter ={};
				var yyParameter ={};
				//预约反馈
				if(jQuery("#doType").val()=='yyfk'){
					yyurl = "xlzx_yysqnew.do?method=updateYysqInfo";
					zxurl = "xlzx_zxyyclnew.do?method=saveXlzxInfo";
					var zxUpurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
					//防止undifined
					var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
					var qssj = (!jQuery("#zxqssj").val()) ? "" : jQuery("#zxqssj").val();
					var jssj = (!jQuery("#zxjssj").val()) ? "" : jQuery("#zxjssj").val();
					zxParameter ={
				    	yyid:jQuery("#yyid").val(),
						zxrq:jQuery("#zxrq").val(),
						qssj:qssj,
						jssj:jssj,
						sjddm:sjddm,
						zxtell:jQuery("#zxtell").val(),
						//zxdz:jQuery("#zxdz").val(),
						zxdz:encodeURI(encodeURI(jQuery("#zxdz").val())),
						bz:encodeURI(encodeURI(jQuery("#bz").val())),
						apzxs:jQuery("[name=apzxs]:checked").val(),
						zxstatus: 1,//新增咨询信息，将状态置为1待咨询
						//bz:jQuery("#bz").val(),
						//xspjzt:1//选择咨询信息，学生评价状态1待评价
						id:jQuery("#zxid").val()//諮詢ID
					};
					
					showConfirm("确认保存信息？",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
						//预约成功
						if(jQuery("#yystatus").val()=="2"){
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val()}; //,zgh:jQuery("[name=apzxs]:checked").val()
							//预约状态设置
							jQuery.post(yyurl,yyParameter,function(result){
								if(result=="true"){
									if(jQuery("#zxid").val()!=""){

										//咨询信息更新至咨询表
										jQuery.post(zxUpurl,zxParameter,function(data){
											if(data == true){
														showAlert("保存成功！",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
															iFClose();
														}
													});
											}else{
												return showAlert("保存失败！");
											}
										},'json');
									}else{
										//咨询信息插入至咨询表
										jQuery.post(zxurl,zxParameter,function(data){
											if(data == true){
														showAlert("保存成功！",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
															iFClose();
														}
													});
											}else{
												return showAlert("保存失败！");
											}
										},'json');
									}
								}else{
									return showAlert("保存失败！");						
								}
							});

						//预约失败
						}else if(jQuery("#yystatus").val()=="5"){
							//预约失败，则删除咨询信息
							jQuery.post("xlzx_zxyyclnew.do?method=delZxInfoByYyid",{yyid:jQuery("#yyid").val()},function(result){
												});
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val(),yysbyy:jQuery("#yysbyy").val()};
							//预约状态设置
							jQuery.post(yyurl,yyParameter,function(data){
								if(data == "true"){
									showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
										iFClose();
									}});
								}else{
									return showAlert("保存失败！");						
								}
							});
						}
						jQuery.ajaxSetup({async:true});
					}});
				}

				//咨询反馈
				if(jQuery("#doType").val()=='zxfk'){
					
					zxParameter={};
					meetFlag();
					if(jQuery("#zxzt").val()==""||jQuery("#zxzt").val()=="1"){
						return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
					}
					//咨询状态取得判断
					if(jQuery("#xspjzt").val()=="2"){
						return showAlert("该咨询学生已经评价、无法修改咨询反馈！");
					}

					zxParameter["zxstatus"]=jQuery("#zxzt").val();//1待咨询2已咨询3未咨询
					if(jQuery("#zxzt").val()=="2"){
						zxParameter["xspjzt"]=1;
					}
					
					zxParameter["yyid"]=jQuery("#yyid").val();
					//zxParameter["zxsfk"]=jQuery("#zxsfk").val();
				
					zxurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
					
					showConfirm("确认保存信息？",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
							//咨询信息插入至咨询表	
							jQuery.post(zxurl,zxParameter,function(data){
								if(data == true){
											showAlert("保存成功！",{},{"clkFun":function(){
												frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yyfkManage");
												iFClose();
											}
										});
								}else{
									return showAlert("保存失败！");
								}
							},'json');
							jQuery.ajaxSetup({async:true});
					}});
				}
		}

		function showNewJdrs(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var id = jQuery("#id").val();
			var date = jQuery("#zxrq").val();
			//var pbdate = jQuery("#pbdate").val();
			jQuery.ajaxSetup({async:false});
			jQuery.post("xlzx_zxspb.do?method=getOkZxsList&xh="+xh+"&t="+Math.random(),{pbdate:date,id:id},function(data){
				jQuery("#zxsShowTd").html("");
				if(data["message"]){
					showAlert(data["message"],{},{"clkFun":function(){
					}});
				}else{
		  			var zxsxx = '<div style="overflow-y: scroll; height: 120px;">';
		  			jQuery.each( data , function(i, node){
						zxsxx += '<div style="margin: 5px 0px 5px 0px;">';
						zxsxx += '<input type="radio" name="apzxs" value="'+node.zgh+'" onclick="checkZxs(this)" ';
						var zgh = jQuery("#zgh").val();
						if(zgh == node.zgh){
							zxsxx += ' checked="checked" ';
						}
						zxsxx += ' >';
						zxsxx += '<span onclick="jQuery(this).parent().find(\':radio\').attr(\'checked\',true);checkZxs(this);" style="">';
						zxsxx += node.xm+'&nbsp;['+node.xb+']'+ ((node.bmmc == null)?'':('['+node.bmmc+']')) +'['+node.zgh+']';
						zxsxx += '<font color="#0000ff">['+node.kjdrsms+']</font><font color="#ff0000">[已预约'+node.yaprs+'人]</font>';
						if('${pbfs}' == '2' && '${xxdm}' == '10026'){
							zxsxx += "<font color='blue'>["+node.xqmc+"]</font>";
						}
						zxsxx += '</span>';
						zxsxx += '<span class="lxdh" style="display: none;">'+node.lxdh +'</span>';
						zxsxx += '<span class="address" style="display: none;">'+node.address +'</span>';
						zxsxx += '<span class="kjdrs" style="display: none;">'+node.kjdrs +'</span>';
						zxsxx += '<span class="yaprs" style="display: none;">'+node.yaprs +'</span>';
						zxsxx += '</div>';
		  			});
					zxsxx += '</div>';
		  			jQuery("#zxsShowTd").html(zxsxx);
				}
				var zxsChecked = jQuery("[name=apzxs]:checked").val();
				if(zxsChecked){
					jQuery("[name=apzxs]:checked").click();
				}else{
					jQuery("#zxtell").val("");
					jQuery("#zxdz").val("");
				}
				//jQuery("#zxrq").val(pbdate);
			},'json');
			jQuery.ajaxSetup({async:true});
		}
		function checkZxs(node){
			var lxdh = jQuery(node).parent().find(".lxdh").html();
			var address = jQuery(node).parent().find(".address").html();
			jQuery("#zxtell").val(lxdh=="null"?"":lxdh);
			jQuery("#zxdz").val(address=="null"?"":address);
			jQuery("#cnt").val(parseInt(jQuery("#cnt").val())+1);
			if('${pbfs}' == '2' && jQuery("#cnt").val() > 1 ){
				changeSjdSelect();
			}
		}

		function getYjdrs(){
			var count=0;
			var zgh = jQuery("#zgh").val();
		    var date =jQuery("#pbdate").val();
			var url = "xlzx_yysqnew.do?method=getZxsYjdrsByDate&zgh="+zgh+"&date="+date;
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,{},function(data){
				count = data;
			});
			jQuery.ajaxSetup({async:true});
			return count;
		}


		function addYyInfo(zgh){
			var pbdate = jQuery("#pbdate").val();
			showDialog("预约信息",640,260,"xlzx_yysqnew.do?method=addyyzxInfo&yyzxrq="+pbdate+"&zgh="+zgh);
		}

		function disabledView(){
			
			if(jQuery("#doType").val()=="view"){
				jQuery("input,select,text,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				});
			}
		}


		function init(){
			sfAgree();
			
			//jQuery("input[type='radio'][name='meet'][value='"+jQuery("#status").val()+"']").attr("checked",true);
			if(jQuery("#status").val()=="2"){
				//jQuery("input[type='radio'][name='meet']").attr("disabled",true);
			}
			meetFlag();
			if(jQuery("#doType").val()=="view"){

				jQuery("#btx").hide();
				jQuery("#buttonSave").hide();
			}else{
				<logic:equal name ="doType" value="yyfk">
				showNewJdrs();
				</logic:equal>
			}
		}

		function sfAgree(){
			if(jQuery("#status").val()=="2"){
				//jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();		
			}
			else if(jQuery("#status").val()=="5"){
			    jQuery("tr[name=yyfkId]").hide();
			    //jQuery("tr[name=yysbyytr]").show();
			    jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}else{
			 	jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").hide();		
				jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}
			
			if(jQuery("input[type='radio'][name='sfty'][value='1']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();
				jQuery("#yystatus").val("2");
			}else if(jQuery("input[type='radio'][name='sfty'][value='2']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").show();
				jQuery("#yystatus").val("5");
			}
		}

		function meetFlag(){
			if(jQuery("input[type='radio'][name='zxzt'][value='1']").prop("checked")==true){
				jQuery("#zxzt").val("1");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='2']").prop("checked")==true){
				jQuery("#zxzt").val("2");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='3']").prop("checked")==true){
				jQuery("#zxzt").val("3");
			}
		}

		//重写咨询日期触发事件
		function changezxrq(){
			showNewJdrs();
			if('${pbfs}' == '2' ){
				changeSjdSelect();
			}
		}

		//改变时间段下拉框option取值
		function changeSjdSelect(){
			var date = jQuery("#zxrq").val();
			var xh = jQuery("#xh").val();
			var zgh = jQuery("[name='apzxs']:checked").val();
			var json = {date:date,xh:xh,zgh:zgh};
			var url = "xlzx_zxspb.do?method=changeSjdSelect";
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				data:json,
				async: false,
				success:function(data){
					jQuery("#sjddm").empty();
					var optionhtml = "<option></option>";
					if(data.length > 0){
						jQuery(data).each(function(i,o){
							optionhtml += "<option value='"+o.sjddm+"'>"+o.sjdmc+"</option>";
						})
					}
					jQuery("#sjddm").html(optionhtml);
					jQuery("#sjddm").val("");
				}
			});
		}

		function showqtxx(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#qtxx").toggle();
		}
		</script>
		
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysqnew" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${yyzxInfo.yyzxrq}" />
			<input type="hidden" name="zxid" id="zxid" value="${yyzxInfo.zxid}" />
			<input type="hidden" name="id" id="id" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="zgh" id="zgh" value="${yyzxInfo.zgh}" />
			<input type="hidden" name="xh" id="xh" value="${yyzxInfo.xh}" />
			<input type="hidden" name="yjdrs" id="yjdrs" value="${yjdrs}" />
			<input type="hidden" id="cnt" value="0"/>
			<input type="hidden" name="kjdrs" id="kjdrs" value="${yyzxInfo.kjdrs}" />
			<div style='width:100%;height:405px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>学生填写信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xlzx/yysq/viewXstxxx.jsp" %>
				</table>
				<%--下面的这些项都是必填的，所以选择一个为空则都不出--%>
				<logic:notEmpty  name="yyzxInfo" property="qxztztmc">
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
								总体：${yyzxInfo.qxztztmc}
							</td>
						</tr>
						<tr>
							<td>
								焦虑：${yyzxInfo.qxztjlmc}
							</td>
						</tr>
						<tr>
							<td>
								抑郁：${yyzxInfo.qxztyymc}
							</td>
						</tr>
						<tr>
							<th >上次咨询后的改变</th>
							<td>
									${yyzxInfo.sczxhgbmc}
							</td>
						</tr>
						<tr>
							<th >对自己最近的状态</th>
							<td>
									${yyzxInfo.zjztmc}
							</td>
						</tr>
						<tr>
							<th>
								本次咨询的问题
							</th>
							<td>
									${yyzxInfo.bczxwt}
							</td>
						</tr>
						<tr>
							<th>
								上次咨询后的生理、心理状态和社会功能
							</th>
							<td>
									${yyzxInfo.zxhzt}
							</td>
						</tr>
						</tbody>
					</table>
				</logic:notEmpty>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>预约咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									姓名
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxm}
								</td>
								<th  width="16%">
									职工号
								</th>
								<td  width="34%">
									${yyzxInfo.zgh}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									性别
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									年龄
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									联系电话
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									所在部门
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
									
								</td>
							</tr>							
					</tbody>
					<logic:notEmpty name="yyzxInfo" property="yyzxzt">
						 <thead>
							<tr >
								<th colspan="4">
									<span>预约信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="yyzxInfo"> 
							<tr style="height:10px">
								<th>
									预约咨询日期
								</th>
								<td >
									<span class="red"><B>${ yyzxInfo.yyzxrq}</B></span>
								</td>
								 <th  width="16%">
									预约咨询时段
								</th>
								<td  width="34%" >
									<logic:equal value="2" name="pbfs">
										${yyzxInfo.sjdmc}
									</logic:equal>
									<logic:notEqual value="2" name="pbfs">
										${yyzxInfo.yyqssj}&nbsp;
										<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
												至&nbsp;${yyzxInfo.yyjssj}
										</logic:notEqual>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									预留联系号码
								</th>
								<td colspan="3">
									${ yyzxInfo.xstell}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									咨询主题
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxzt}
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									咨询概要<br/>
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxxq}
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr >
							<th colspan="4">
								<span>咨询安排信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									预约状态
								</th>
								<td colspan="3" width="34%">
									<input type="hidden" name="status" id="status" value="${yyzxInfo.status}">
									<input type="hidden" name="yystatus" id="yystatus" value="">
									<logic:equal name ="doType" value="yyfk">
										<input type="radio"  name="sfty"  value="1" id="yyfk_yycg"
										<logic:notEqual name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:notEqual>
											onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yycg">预约成功</label>
										<input type="radio"  name="sfty"  value="2" id="yyfk_yysb"
										<logic:equal name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:equal>
										onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yysb">预约失败</label>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.statusmc}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
											${yyzxInfo.statusmc}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<input type="radio"  name="sfty"  value="1" checked="true" onclick="sfAgree()"/>预约成功
												<input type="radio"  name="sfty"  value="2" onclick="sfAgree()"/>预约失败
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
									
								</td>
								
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>咨询安排日期
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxrq"  name="zxrq" 
										<logic:notEqual name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.zxrq}"
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.yyzxrq}"
										</logic:equal> onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${today}'})"  onchange="changezxrq();"/>
									</logic:equal>
									
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											<span class="red"><B>${yyzxInfo.zxrq}</B></span>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxrq}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxrq" styleId="zxrq"  value="${yyzxInfo.yyzxrq}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="changezxrq();"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal></logic:equal>咨询时段
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
											<logic:equal value="2" name="pbfs">
												
												<logic:notEmpty name="yyzxInfo" property="sjddmzx" >
													<html:select styleId="sjddm" property="sjddm"  value="${yyzxInfo.sjddmzx}">
														<html:option value=""></html:option>
														<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
											        </html:select>
												</logic:notEmpty>
												<logic:empty name="yyzxInfo" property="sjddmzx" >
													<html:select styleId="sjddm" property="sjddm"  value="${yyzxInfo.sjddm}">
													        <html:option value=""></html:option>
															<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
												     </html:select>
												</logic:empty>
											</logic:equal>
											<logic:notEqual value="2" name="pbfs">
												 <input type="text" id="zxqssj"  name="zxqssj"  style="width:30%"  
												<logic:notEqual name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.zxqssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.yyqssj}"
													</logic:equal>
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
											    <input type="text" id="zxjssj"  name="zxjssj" style="width:30%" 
												<logic:notEqual name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.zxjssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.yyjssj}"
													</logic:equal>					
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />
											</logic:notEqual>
										  
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual value="2" name="pbfs">
											<logic:notEqual name ="yyzxInfo" property="status" value="1">
												${yyzxInfo.zxqssj}&nbsp;
												<logic:notEmpty name="yyzxInfo" property="zxjssj">至&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="status" value="1">
												<logic:equal name ="doType"  value="view">
														${yyzxInfo.zxqssj}&nbsp;
														<logic:notEmpty name="yyzxInfo" property="zxjssj">至&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
												</logic:equal>
												<logic:notEqual name ="doType"  value="view">
													<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="${yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
													<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="${yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										<logic:equal value="2" name="pbfs">
											${yyzxInfo.sjdmczx}
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							
							<tr>
								<logic:equal name ="doType" value="yyfk">
									<logic:equal name ="doType"  value="view">
										<th>
											咨询师
										</th>
										<td colspan="3">
											 <a href = 'javascript:void(0);' style="color:#0f5dc2;text-decoration:underline;" onclick="showDialog('查看咨询师信息' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=${yyzxInfo.apzxs}')" >${yyzxInfo.apzxsxm}</a>
										</td>
									</logic:equal>
									<logic:notEqual name ="doType"  value="view">
										<th>
											<span class="red">*</span>咨询师
										</th>
										<td colspan="3" id="zxsShowTd">
											 
										</td>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual name ="doType" value="yyfk">
									<th>
										咨询师
									</th>
									<td colspan="3">
										 <a href = 'javascript:void(0);' style="color:#0f5dc2;text-decoration:underline;" onclick="showDialog('查看咨询师信息' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=${yyzxInfo.apzxs}')" >${yyzxInfo.apzxsxm}</a>
									</td>
								</logic:notEqual>
							</tr>
							
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									联系电话
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxtell"  name="zxtell"  
											<logic:notEqual name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.zxtell}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.lxdh}"
												</logic:equal>
											maxlength="11" onkeyup="checkInputData(this);"/>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxtell}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxtell}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxtell" styleId="zxtell"  value="${yyzxInfo.lxdh}"  maxlength="11" onkeyup="checkInputData(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId" >
								<th  width="16%">
									咨询地址
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxdz"  name="zxdz" style="width:400px" maxlength="50"
											<logic:notEqual name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.zxdz}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.address}"
												</logic:equal>
										  />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxdz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxdz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxdz" styleId="zxdz" maxlength="50" style="width:400px" value="${yyzxInfo.address}" />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									备注
									<logic:equal name ="doType" value="yyfk"><br/><font color="red"><B>(限500字)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name="doType" value="view"><br/><font color="red"><B>(限500字)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='bz' styleId="bz" 												
											value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxbz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxbz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='bz' styleId="bz" value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>	
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>预约失败原因
									<logic:equal name ="doType" value="yyfk"><br><font color="red"><B>(限500字)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name ="doType"  value="view"><br><font color="red"><B>(限500字)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.yysbyy}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.yysbyy}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							
					</tbody>
						<thead name = "zxxgInfo">
							<tr >
								<th colspan="4">
									<span>咨询相关信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxInfo" name = "zxxgInfo">
								<tr style="height:10px">
									<th  width="16%">
										<logic:equal name ="doType" value="zxfk"><span class="red">*</span></logic:equal>咨询状态
									</th>
									<td  width="34%" colspan="3">
										<input type="hidden" name="xspjzt" id="xspjzt" value="${yyzxInfo.xspjzt}">
										<input type="hidden" name="zxstatus" id="zxstatus" value="${yyzxInfo.zxzt}">
										<input type="hidden" name="zxzt" id="zxzt" value="">
										<logic:equal name ="doType" value="zxfk">
											<html:radio name="yyzxInfo" property="zxzt" value="2"/>已咨询
											<html:radio name="yyzxInfo" property="zxzt" value="3"/>未咨询</logic:equal>
										<logic:notEqual name ="doType" value="zxfk">
											${yyzxInfo.zxztmc}
										</logic:notEqual>
									</td>
								</tr>
								<tr style="height:40px">
									<th  width="16%">
										学生咨询评价
									</th>
									<td  width="34%" colspan="3">
											${yyzxInfo.xspj}
									</td>
								</tr>
						</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
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


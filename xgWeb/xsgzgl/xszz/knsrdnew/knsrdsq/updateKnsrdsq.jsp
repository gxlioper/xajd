<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script	type="text/javascript">
		function saveForm1(obj){	
			var base=new Array();
			var i=1;
			var base=new Array();
			var sqid = jQuery("#sqid").val();
			var jtqkFlag;
			var trMytrNum = 0; // 选择的指标数量
			jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
				var rdzb=new Object();
				rdzb.sxid=jQuery(this).find("input[name=sxid]").val();
				rdzb.jtqk=jQuery(this).find("textarea[name=jtqk]").val();
				var jtqkNum = 0;
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
					//var zbnr=new Object();
					if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
						jtqkNum ++ ;
					}
				});
				jtqkFlag = false;//判断具体困难情况是否为空
				if(jtqkNum > 0){
					trMytrNum++;
					var jtqk=jQuery(this).find("textarea[name=jtqk]").val();
					if(jtqk == ""){
						//showAlertDivLayer("勾选了指标内容项则相对应的具体困难情况必须录入！");
						jtqkFlag = true;
						return false;
					}
				}

				if(jtqkFlag == true){
					return false;
				}
				
				var zbnra=new Array();
				var xxnri=1;
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
					var zbnr=new Object();
					if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
						zbnr.nrid = jQuery(this).find("input[name=nrid]").val();
						zbnr.fz = jQuery(this).find("input[name=fzH]").val();
						zbnra[xxnri-1]=zbnr;
						xxnri++;
					}
					
				});
				
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;
				//return false;
			});

			if(jtqkFlag == true){
				showAlertDivLayer("勾选了指标内容项则相对应的具体困难情况必须录入！");
				return false;
			}
			// 如果"可选择认定指标"为1,那么,只能选择一个指标
			if('${jcszModel.kxzrdzb}' == '1' && trMytrNum > 1){
				showAlertDivLayer("只能选择一个指标！");
				return false;
			}
			var json=JSON.stringify(base);
			var url = "";
			if(obj == 'update'){
				url = "xg_xszz_knsrd_knsqgl.do?method=updateKnsrdsq&type=update";
			}else{
				url = "xg_xszz_knsrd_knsqgl.do?method=updateKnsrdsq&type=submit";
			}
			
			jQuery("#knsrdsqForm").ajaxSubmit({
				url:url,
				type:"post",
				dataType:"json",
				data:{json:json},
				success:function(data){
			 		 if(data["message"]=="保存成功！" ||data["message"]=="提交成功！" ){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
			 		
				}
			});	

			
		}	

		function autoSetZbnr(){
			var nrids = jQuery("#nrids").val();
			var arr=nrids.split(",");
			for(var i=0;i<arr.length;i++){
				jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
					jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
						if(jQuery(this).find("input[name=nrid]").val() == arr[i]){
							jQuery(this).find("input[name=zbnrid]").attr("checked","checked");
						}
					});
				});
			}
		}


		/**
		 * 加载家庭情况调查信息
		 * @param obj
		 * @return
		 */
		function showJtqk(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#t_jtqk").toggle();
		}
		
		jQuery(document).ready(function(){
			autoSetZbnr();

			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) != ""){
				jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
			}

			/****************调整一下高度*******************/
			var h = jQuery('.tz_gaodu_td').height();
			jQuery('.tz_gaodu_td').each(function(i){
				jQuery(this).find('#knsrdsqzbnr').css('height',jQuery(this).height() + 'px');
				jQuery(this).find('#jtqk').css('height',jQuery(this).find('#jtqk_td').height() + 'px');
			});
		/*****************调整一下高度******************/

			var isopen = jQuery("#isopen").val();
			var shzt = jQuery("#shzt").val();
			if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
				jQuery("#btn_submit").hide();
			}
		});
	
		</script>
		
	</head>
	<body>
		
		<html:form action="/xg_xszz_knsrd_knsqgl" method="post" styleId="knsrdsqForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<input type="hidden" value="${nrids}" id="nrids"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<html:hidden property="splc" styleId="splc"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/knsrdnew/comm/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况 <logic:notEqual value="" property="xh"
										name="knsrdsqForm">
										<a onclick="showJtqk(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
									</logic:notEqual> </span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">

								</div>
							</td>
						</tr>
					</tbody>
					
					
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定指标申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody name="tbody_knsrdzb">
						<logic:notEmpty name="object">
							<logic:iterate name="object" id="s" indexId="i">
								<table width="100%" border="0" class="formlist" name="knsrdzb"
									style="margin: 2px auto;">
									<tr name="mytr">
										<input type='hidden' id='sxid' name='sxid'
											value="${s.key.sxid}" />
										<td width="20%">
											${s.key.sxmc}
										</td>
										<td width="55%">
											<table width="100%" border="0" class="datelist"
												id="knsrdzbnr" style="margin: 2px auto;">
												<thead>
													<tr>
														<td width="7%">

														</td>
														<td width="93%" style="text-align: center">
															指标内容
														</td>
													</tr>
													<tbody name="tbody_knsrdzbnr">
														<logic:iterate name="s" id="list" indexId="j"
															property="value">
															<tr id="knsrdzbxxnr">
																<td width="7%">
																	<input type="hidden" name="nrid" value="${list.nrid}" />
																	<logic:notEqual value="10052" name="xxdm" >
																		<input type='checkbox' name="zbnrid" disabled="disabled"/>
																	</logic:notEqual>	
																	<logic:equal value="10052" name="xxdm" >
																		<input type='radio' name="zbnrid"  />
																	</logic:equal>
																	<!--<input type='checkbox' name="zbnrid" />
																	--><input type="hidden" name="fzlxH" value="${list.fzlx}" />
																	<input type="hidden" name="fzH" value="${list.fz}" />
																</td>
																<td width="50%">
																	${list.nrmc }
																</td>
															</tr>
														</logic:iterate>
													</tbody>
												</thead>
											</table>
										</td>

										<td width="25%" colspan="2" class="tz_gaodu_td">
											<table width="100%" border="0" class="datelist"
												id="knsrdsqzbnr" style="margin: 2px auto;">
												<thead>
													<tr>
														<td>
															具体困难情况（学生录入）
														</td>
													</tr>
													<tbody>
														<tr>
															<td id="jtqk_td">
																<textarea name='jtqk' id="jtqk" rows='5'
																	style="word-break: break-all; width: 98%"
																	onblur="chLeng(this,250);" >${s.key.jtqk}</textarea>
															</td>
														</tr>
													</tbody>
												</thead>
											</table>
										</td>

									</tr>
								</table>
							</logic:iterate>
						</logic:notEmpty>
					</tbody>
				</table>
			</div>
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm1('update');">
										保存草稿
									</button>
									
									<button type="button" id="btn_submit" type="button" onclick="saveForm1('submit');">
										提交申请
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
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


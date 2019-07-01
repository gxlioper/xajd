<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<style type="text/css">
			.OrgBox{
				font-size:12px;
				padding:5px 5px 5px 5px;
				clear:left;
				float:left;
				text-align:center;
				position:absolute;
				background:url(/xgxt/pictures/pjpy/zhcp/organization_bg.gif) repeat-x left top;
				width:90px;
				height:40px;
				border:#adc8dc 1px solid;
				border-width:1px 2px 2px 1px;
			}
			.OrgBox img{
				width:60px;
				height:40px;
			}
			.OrgBox div{
				padding:5px 0;
				color:#08487e;
				font-weight:bold;
			}
			.OrgBox div span{
				color:#08487e;
				font-weight:bold;
				}
			.OrgBox input{
				background:none;
				border:1px solid #adc8dc;
				padding:0;
				margin:0;
				text-align:center;
			}
		</style>
		<link rel="stylesheet" type="text/css" href="js/jquery/plugins/ringhtMenu/rightMenu.css" /> 
	       
	    <script type="text/javascript" src="js/jquery/plugins/ringhtMenu/contextMenu.js"></script>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/treeFrame1.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			setTimeout('initZcxm()',100);
			setTimeout('initRight()',1000);
		}
		
		//初始化综测项目
		function initZcxm(){
			//上级代码数组
			var sjdmArr=document.getElementsByName("sjdmArr");
			//综测级别
			var zcjb=sjdmArr.length-1;
			//综测项目
			var xmdm=document.getElementsByName("xmdm");
			var sjdm=document.getElementsByName("sjdm");
			var xmmc=document.getElementsByName("xmmc");
			var jjf=document.getElementsByName("jjf");
			
			//综测项目详细信息
			var xmdmArr=document.getElementsByName("xmdmArr");
			var bldmArr=document.getElementsByName("bldmArr");
			var blmcArr=document.getElementsByName("blmcArr");
			var blArr=document.getElementsByName("blArr");
			var xmsjdmArr=document.getElementsByName("xmsjdmArr");
			
			var node=new Array();
			for(i=0;i<xmdm.length;i++){
				node[i]=new OrgNode();
			}
			
			var str=new Array();
			for(i=0;i<xmdm.length;i++){
				var xmm=xmmc[i].value;
				//项目名称修改字段(用于修改综测名)
				var text ="<span id='span_xmmc_"+i+"'>";
					text+="<a href=\"#\" name=\"a_zcxm\" id=\"a_"+xmdm[i].value+"\"onclick=\"return false;\">";
					text+="<font color=\"blue\">"
					text+=xmm;
					text+="</font>";
					text+="</a>";
					text+="(";
					text+=jjf[i].value;
					text+=")";
<%--					text+="<br/>";--%>
<%--					text+="加减分：";--%>
<%--					if(jjf[i].value == "-"){--%>
<%--						text+="加分";--%>
<%--					}else{--%>
<%--						text+="减分";--%>
<%--					}--%>
					text+="</span>";
					text+="<input type='text' style='width:80px'";
					text+="name='zcxmmcArr' id='text_xmmc_"+i+"' value='"+xmm+"'";
					text+="style='display:none' onblur=\"checkXmmc('text_xmmc_"+i+"')\"/>";
					
				node[i].customParam.EmpName=text;
				str[i]="";
				for(j=0;j<xmdmArr.length;j++){
					if(xmdm[i].value==xmdmArr[j].value
						&& (xmsjdmArr[j].value==null || xmsjdmArr[j].value=="")){
						var bl=blArr[j].value;			
						str[i]+="<div style='height:14px'><span id='span_bl_"+j+"' ></span><input type='hidden' style='width:30px' name='zcblArr' id='text_bl_"+j+"' value='' style='display:none'></div>";
					}else if(xmdm[i].value==xmdmArr[j].value){
						var bl=blArr[j].value;
						if(bl==""){
							bl="0";
						}
						str[i]+="<div>"+blmcArr[j].value+"：<span id='span_bl_"+j+"'>"+bl+"</span><input type='text' style='width:30px;display:none' name='zcblArr' id='text_bl_"+j+"' value='"+blArr[j].value+"'  onkeydown=\"return onlyNum(this,3);\" onmousedown=\"return onlyNum(this,3);\">%</div>";
					}
				}
				node[i].customParam.inputV=str[i];
			}
			
			
			for(j=0;j<xmdm.length;j++){
				for(i=0;i<sjdmArr.length;i++){
					if(xmdm[j].value==sjdmArr[i].value){
						
						for(z=0;z<xmdm.length;z++){
							if(sjdm[z].value==xmdm[j].value){
								node[j].Nodes.Add(node[z]);						
								
							}
						}
					}
				}
			}
			
			var OrgShows=null;
			for(i=0;i<sjdm.length;i++){
				
				if(sjdm[i].value==null || sjdm[i].value==""){
					
					OrgShows=new OrgShow(node[i]);
					break;
				}
			}
			OrgShows.Top=150;
			OrgShows.Left=85;
			OrgShows.IntervalWidth=10;
			OrgShows.IntervalHeight=20;
		
			OrgShows.ShowType=1;
			OrgShows.BoxHeight=20;
			OrgShows.BoxTemplet="<div id=\"{Id}\" class=\"OrgBox\"><span>{EmpName}</span>{inputV}</div>"
			
			OrgShows.Run();
		}

		function initRight(){
			var imageMenuData = [
			    [{
			        text: "增加下级项目",
			        func:function(){
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	showAddZcxmDiv();
			        }
			    }, {
			        text: "修改本级项目",
			        func: function() {
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	showEditZcxmDiv();
			        }
			    }, {
			        text: "删除本级项目",
			        func: function() {
				    	var id = jQuery(this).attr('id');
			        	var xmdm = id.split("_")[1];
			        	jQuery("#czxm").val(xmdm);
			        	
			        	var mrxm = jQuery("#mrxm_"+xmdm).val();

			        	if(mrxm == "yes"){
							alertError("该项目为默认项目，不可删除，如有异议请联系相关责任人");
							return false;
			        	}else{
			        		confirmInfo('请您确认是否删除该项目?<br/>注：如果该项目拥有子项目的话，将会一并删除',deleteZcxm);
			        	}
			        }
			    }]
			];

			jQuery("a[name=a_zcxm]").smartMenu(imageMenuData, {name: "a"});
		}
		
		jQuery(function(){
			
			jQuery(this).not(jQuery("a[name=a_zcxm]")).mouseup(function(){
				
				jQuery("#smartMenu_a").attr("style","display:none");
			});
			

			onShow();

		})
		
		
		//显示增加项目DIV
		function showAddZcxmDiv(){
			
			jQuery("#input_xmmc").val("");
			jQuery("#hidden_xmmc").val("");
			jQuery("#input_jjf").val("+");
			//jQuery("#input_lrly").val("no");

			//-------------------比例赋值--------------------
			var i = 0;
			var bldm = new Array();				  
			jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
			
			for(var j=0;j<bldm.length;j++){
				$("input_bl_"+bldm[j]).disabled = false;
				jQuery("#input_bl_"+bldm[j]).val("");
			}
			//-------------------比例赋值 end--------------------
			
			$("btn_add_save").style.display = "";
			$("btn_edit_save").style.display = "none";
			tipsWindown("系统提示","id:div_zcxm","350","250","true","","true","id");
		}

		//显示修改项目DIV
		function showEditZcxmDiv(){
			
			var xmdm = jQuery("#czxm").val();//项目级别
			var xmmc = jQuery("#xmmc_"+xmdm).val();//项目名称	
			var xmjb = jQuery("#xmjb_"+xmdm).val();//项目级别
			var jjf = jQuery("#jjf_"+xmdm).val();//加减分	
			//var lrly = jQuery("#lrly_"+xmdm).val();//录入理由
			
			jQuery("#input_xmmc").val(xmmc);
			jQuery("#hidden_xmmc").val(xmmc);
			jQuery("#input_jjf").val(jjf);
			//jQuery("#input_lrly").val(lrly);

			//-------------------比例赋值--------------------
			var i = 0;
			var bldm = new Array();				  
			jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
	
			for(var j=0;j<bldm.length;j++){
				var bl_id = "bl_"+xmdm+"_"+bldm[j];
				var bl = jQuery("#"+bl_id).val();
				
				if(bl == ""){
					bl = "0";
				}

				if(xmjb == "1"){
					$("input_bl_"+bldm[j]).value="0";
					$("input_bl_"+bldm[j]).disabled = true;
				}else{
					$("input_bl_"+bldm[j]).disabled = false;
					jQuery("#input_bl_"+bldm[j]).val(bl);
				}		
			}
			//-------------------比例赋值 end--------------------
		
			$("btn_add_save").style.display = "none";
			$("btn_edit_save").style.display = "";
			tipsWindown("系统提示","id:div_zcxm","350","250","true","","true","id");
		}
		
		//检测保存综测项目（下一级别）
		function checkSaveNextZcxm(){
			var sjdm = jQuery("#czxm").val();
			var xmmc = jQuery("#input_xmmc").val();

			if(xmmc == ""){
				alertError("项目名称不能为空，请您确认");
				return false;
			}else{
				var xmmc_num = jQuery("input[name=xmmc]").length;
				for(var i=0;i<xmmc_num;i++){
					var obj=jQuery("input[name=xmmc]").eq(i);
					if(jQuery(obj).val() == xmmc){
						alertError("该项目已经存在，请确认");
						return false;
					}
				}
			}

			confirmInfo('您确定是否增加该综测项目?',saveNextZcxm);
		}

		//保存综测项目（下一级别）
		function saveNextZcxm(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_zcxm_ajax.do?method=saveNextZcxm";
				//上级代码
				var sjdm = jQuery("#czxm").val();
				//项目名称
				var xmmc = jQuery("#input_xmmc").val();
				//加减分
				var jjf = jQuery("#input_jjf").val();
				//录入理由
				//var lrly = jQuery("#input_lrly").val();
				//比例代码	
				var i = 0;
				var bldm = new Array();				  
				jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
				//比例
				i = 0;
				var bl = new Array();
				jQuery("input[name=bl]").each(function(){
					if(jQuery(this).val() !=""){
						bl[i] = jQuery(this).val();
					}else{
						bl[i] = "0";
					}	
					i++;
				});
				
				//参数
			 	var parameter = {
			 		"sjdm":sjdm,
			 		"xmmc":escape(xmmc),
			 		"jjf":jjf,
			 		//"lrly":lrly,
			 		"bldm":bldm.join("!!@@!!"),
			 		"bl":bl.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";						
						alertInfo(result,function(){goPjszZcxm();});
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//删除综测项目
		function deleteZcxm(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_zcxm_ajax.do?method=deleteZcxm";
				//项目代码
				var xmdm = jQuery("#czxm").val();
				
				//参数
			 	var parameter = {
			 		"xmdm":xmdm
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";						
						alertInfo(result,function(){goPjszZcxm();});
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//检测保存综测项目（修改本级别）
		function checkSaveEditZcxm(){
			var xmdm = jQuery("#czxm").val();
			var xmmc = jQuery("#input_xmmc").val();
			var hidden_xmmc = jQuery("#hidden_xmmc").val();
			if(xmmc == ""){
				alertError("项目名称不能为空，请您确认");
				return false;
			}else if(hidden_xmmc != xmmc){
				var xmmc_num = jQuery("input[name=xmmc]").length;
				for(var i=0;i<xmmc_num;i++){
					var obj=jQuery("input[name=xmmc]").eq(i);
					if(jQuery(obj).val() == xmmc){
						alertError("该项目已经存在，请确认");
						return false;
					}
				}
			}
			confirmInfo('您确定是否保存本次修改?',saveEditZcxm);
		}

		//保存综测项目（修改本级别）
		function saveEditZcxm(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_zcxm_ajax.do?method=saveEditZcxm";
				//项目代码
				var xmdm = jQuery("#czxm").val();
				//项目名称
				var xmmc = jQuery("#input_xmmc").val();
				//加减分
				var jjf = jQuery("#input_jjf").val();
				//录入理由
				//var lrly = jQuery("#input_lrly").val();
				//比例代码	
				var i = 0;
				var bldm = new Array();				  
				jQuery("input[name=bldm]").each(function(){bldm[i] = jQuery(this).val();i++;});
				//比例
				i = 0;
				var bl = new Array();
				jQuery("input[name=bl]").each(function(){
					if(jQuery(this).val() !=""){
						bl[i] = jQuery(this).val();
					}else{
						bl[i] = "0";
					}	
					i++;
				});
				
				//参数
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"xmmc":escape(xmmc),
			 		"jjf":jjf,
			 		//"lrly":lrly,
			 		"bldm":bldm.join("!!@@!!"),
			 		"bl":bl.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";						
						alertInfo(result,function(){goPjszZcxm();});
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 综测项目设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p >
				<span>
				1.本功能默认展示的是本评奖学年学期的数据。</br>
				1.如果您想为某项目增加下级项目，请执行<font color="blue">增加下级项目</font>。
				2.如果您想修改某项目的相关信息，请执行<font color="blue">修改本级项目</font>。<br/>
				3.如果您想删除某项目，请执行<font color="blue">删除本级项目</font>(系统提供的<font color="blue">默认项目</font>不可删除)。<br/>
				4.<font color="blue">(+)</font>代表该项目属于加分项，<font color="blue">(-)</font>则代表该项目是减分项。
				5.项目比例是指该项目分数在<font color="blue">上级项目</font>中所占的比例。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<logic:iterate name="zcxmList" id="zcxm">
				<input type="hidden" name="xmdm" id="xmdm_${zcxm.xmdm}" value="${zcxm.xmdm}" />
				<input type="hidden" name="xmmc" id="xmmc_${zcxm.xmdm}" value="${zcxm.xmmc}" />
				<input type="hidden" name="xmjb" id="xmjb_${zcxm.xmdm}" value="${zcxm.xmjb}" />
				<input type="hidden" name="sjdm" id="sjdm_${zcxm.xmdm}" value="${zcxm.sjdm}" />
				<input type="hidden" name="mrxm" id="mrxm_${zcxm.xmdm}" value="${zcxm.mrxm}" />
				<input type="hidden" name="jjf" id="jjf_${zcxm.xmdm}" value="${zcxm.jjf}" />
<%--				<input type="hidden" name="lrly" id="lrly_${zcxm.xmdm}" value="${zcxm.lrly}" />--%>
			</logic:iterate>

			<logic:iterate name="zcblList" id="zcbl">
				<input type="hidden" name="xmdmArr" id="xmdm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.xmdm}" />
				<input type="hidden" name="xmsjdmArr" id="sjdm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.sjdm}" />
				<input type="hidden" name="bldmArr" id="bldm_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.bldm}" />
				<input type="hidden" name="blmcArr" id="blmc_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.blmc}" />
				<input type="hidden" name="blArr" id="bl_${zcbl.xmdm}_${zcbl.bldm}" value="${zcbl.bl}" />
			</logic:iterate>
			
			<logic:iterate name="sjdmList" id="sjdm">
				<input type="hidden" name="sjdmArr" value="${sjdm.sjdm}" />
			</logic:iterate>
			
			<!-- 操作项目 -->
			<input type="hidden" id="czxm" />
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
								返回设置
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<div style="display:none">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				</div>
			</div>
			
			<!-- 综测项目设置弹出层 -->
			<div id="div_zcxm" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>综测项目设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									项目名称
								</th>
								<td width="">
									<input type="text" id="input_xmmc" maxlength="25"/>
									<input type="hidden" id="hidden_xmmc" maxlength="25"/>
								</td>
							</tr>
							<tr>
								<th>
									加减分
								</th>
								<td>
									<select id="input_jjf">
										<option value="+">加分</option>
										<option value="-">减分</option>
									</select>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th>--%>
<%--									是否需要维护理由--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<select id="input_lrly">--%>
<%--										<option value="no">否</option>--%>
<%--										<option value="yes">是</option>--%>
<%--									</select>--%>
<%--								</td>--%>
<%--							</tr>--%>
							<!-- 综测项目 -->
							<logic:iterate name="bldmList" id="blMap">
								<tr>
									<th>
										${blMap.blmc}
									</th>
									<td>
										<input type="hidden" name="bldm" id="input_bldm_${blMap.bldm}" value="${blMap.bldm}"/>
										<input type="text" name="bl" id="input_bl_${blMap.bldm}"
											onkeydown="return onlyNum(this,5)"
											onmousedown="return onlyNum(this,5)"
											maxlength="5" 
											style="width:50px;ime-mode:disabled"
										/>%<font color="red">(限制输入数字)</font>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  id="btn_add_save" onclick="checkSaveNextZcxm()">
											保 存
										</button>
										
										<button type="button"  id="btn_edit_save" onclick="checkSaveEditZcxm()">
											保 存
										</button>
										
										<button type="button"  onclick="closeWindown();return false;">
											关  闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 参评小组设置弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>		
		</html:form>
	</body>
</html>
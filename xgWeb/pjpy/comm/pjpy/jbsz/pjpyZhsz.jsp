<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<style> 
		.liucheng_xg_pj {
			background:url(<%=stylePath%>images/xg/liucheng_xg_pj.gif) no-repeat;
			height:393px;
			width:740px;
			margin:5px auto;
			position:relative
		}
	  </style>
		<script type="text/javascript">
      	//本页面所有操作,该禁用的禁用。
      	jQuery(function(){
      		jQuery.ajaxSetup({async:false});
      		jQuery.post('pjpyJbsz.do?method=getPjzqSfsz',{},function(data){
      			 if (data == '1'){
      			 	//周期设置禁用
      			 	plszDis(['pjpy_zqsz'],true);
      			 	setQtzc();
      			 } else {
      			 //如果周期设置是启用的，那么其它操作禁用（必须去做周期设置）
      			 	setNotZqszDisabled();
      			 }
      		});
      		jQuery.ajaxSetup({async:true});
      	});
      
      	function setQtzc(){
      		var xmsz = ['pjpy_sjsz','pjpy_tzsz','pjpy_jdsz','pjpy_tjsz','pjpy_rssz'];
      		jQuery.ajaxSetup({async:false});
      		//评奖人员库有么初始化？
      		jQuery.post('pjpyJbsz.do?method=getPjrySfwh',{},function(data){
      			//评奖人员么有初始化我要把下级操作禁用
      			if (data == '0'){
      				plszDis(['pjpy_ryqd','pjpy_zcsz','pjpy_xmwh'],true);
      				plszDis(xmsz,true);
      			} else {
      				//评奖人员有初始化过，根据综测设置操作看哪些需要禁用
      				jQuery.post('pjpyJbsz.do?method=getPjzcSfsz',{},function(data){
						if (data != '0') {
							//若综测设置有数据，评奖人员设置禁用
							plszDis(['pjpy_ryqd','pjpy_rycsh'],true);
							//主动权传递给评奖项目维护
							jQuery.post('pjpyJbsz.do?method=getPjxmSfwh',{},function(data){
				    			if (data != '0') {
									//维护了当前周期的评奖项目，综测设置禁用
									plszDis(['pjpy_zcsz'],true);
								} else {
									//当前周期没有维护评奖项目会怎么样呢？项目设置禁用
									plszDis(xmsz,true);
								}
				    		});
						} else {
							//综测设置没做，项目维护、项目设置禁用
							plszDis(['pjpy_xmwh'],true);
							plszDis(xmsz,true);
						}
		    		});
      			}
      		})
      		jQuery.ajaxSetup({async:true});
      	}
      	
        //开始新评奖
      	function startPjpy(obj){
      		var flg = jQuery('input',jQuery(obj)).val();
      	
      		if (flg == 'true') {
      			confirmInfo('您确定要进入新的评奖周期吗?',function(t){
      				if (t=='ok'){
      					jQuery.post('pjpyJbsz.do?method=startPjpy',{},function(data){
			      			if ('true' == data){
			      				plszDis(['pjpy_zqsz'],false);
			      				setNotZqszDisabled();
			      			}
			      		})
      				}
      			})
      		}
      	}
      
      	//非评奖周期设置项禁用
      	function setNotZqszDisabled(){
      		var pjszx = jQuery('a[id!=pjpy_zqsz][id!=pjpy_kspj]',jQuery('#pjsz'));
      				
			for (var i = 0 ; i < pjszx.length ; i++){
				var a = jQuery(pjszx[i]);
				var img = jQuery('img',a);
				var src = img.attr('src').replace('48-1','48-2');
				
				//把原来的图片节点删除，外套div标签是解决IE6下图片删除不掉
				jQuery('div',a).remove();
				//构建新的图片节点
				var html = '<div><img src="'+src+'"></div>';
				//把图片节点加入到a标签内的最前端
				jQuery(html).prependTo(a);
				jQuery('input',a).val('false');
			}
      	}
      	
      	function plszDis(arr,dis){
      		for (var i = 0 ; i < arr.length ; i++){
      			var a = jQuery('#'+arr[i]);
				var img = jQuery('img',a);
				var src = img.attr('src');
				//把原来的图片节点删除，外套div标签是解决IE6下图片删除不掉
				jQuery('div',a).remove();
				
				if(dis){
					src = src.replace('48-1','48-2');
					//流程环节置为不可用
					jQuery('input',a).val('false');
				} else {
					src = src.replace('48-2','48-1');
					//流程环节置为可用
					jQuery('input',a).val('true');
				}
				
				//构建新的图片节点
				var html = '<div><img src="'+src+'"></div>';
				//把图片节点加入到a标签内的最前端
				jQuery(html).prependTo(a);
				
			}
      	}
      	
      	//评奖评优人员初始化
      	function pjpyRycsh(obj){
      		var flg = jQuery('input',jQuery(obj)).val();
      		
      		if (flg == 'true'){
	      		jQuery.post('pjpyRyqd.do?method=pjpyRycsh',{},function(data){
	      			if ("true" == data){
	      				alertInfo('人员初始化成功!');
	      				plszDis(['pjpy_zcsz','pjpy_ryqd'],false);
	      			} else {
	      				alertInfo('人员初始化失败!');
	      			}
	      		});
      		} else {
      			alertInfo('请先设置评奖周期!');
      		}
      	}
      	
      	function pjlcLink(obj,src){
      		var flg = jQuery('input[type=hidden]',jQuery(obj)).val();
      		if (flg == 'true'){
      			document.location.href = src;
      		}
      	}
      	
      	
      	function displayDiv(obj,divId){
      		jQuery(obj).attr('style','z-index:999;position:relative');
      		//jQuery('#'+divId).attr('style','display:block');
      		$(divId).style.display='block';
      	}
      	
      	function hiddenDiv(obj,divId){
      		jQuery(obj).attr('style','z-index:998;position:');
      		//jQuery('#'+divId).attr('style','display:none');
      		$(divId).style.display='none';
      	}
      	
      </script>
	</head>

	<body>
		
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.新开始评奖的情况下，请先点击<font color="blue">开始新评奖</font>。</br>
				2.只有完成了<font color="blue">前一步</font>操作，才可以执行<font color="blue">下一步</font>操作。</br>
				3.如果您已经执行到某一步，发现之前的设置有想<font color="blue">修改</font>的话，必须<font color="blue">清空</font>本操作步骤的所有内容，<font color="blue">逐步退回</font>。</br>
				4.如果您想修改<font color="blue">评奖周期</font>的话，请直接点击<font color="blue">开始新评奖</font>。
				</span>
			</p>
		</div>
		<!-- 提示信息 end-->		

		<div class="liucheng_xg_pj" id="pjsz">
			<div onmouseover="displayDiv(this,'helpcon1')"
				 onmouseout="hiddenDiv(this,'helpcon1')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:12px; top:60px;">
					<a href="#" onclick="startPjpy(this);" id="pjpy_kspj"> 
						<div><img src="<%=stylePath%>images/blue/48-1/Function85.png" /></div>
						<p>
							开始新评奖
							<input type="hidden" value="true" />
						</p> 
					</a>
					<div class="explain_left" id="helpcon1" style="display:none;">
						<div class="explain_con">
							<h3>
								开始新评奖
							</h3>
							<ul>
								<li>
									上学期评奖已完成，进入新的评奖周期
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon2')"
				onmouseout="hiddenDiv(this,'helpcon2')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:182px; top:60px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_jbsz.do');return false;"
						id="pjpy_zqsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function37.png" /></div>
						<p>
							评奖周期设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon2" style="display:none;left:83px">
						<div class="explain_con">
							<h3>
								评奖周期设置
							</h3>
							<ul>
								<li>
									评奖学年:${pjxtszModel.pjxn }
								</li>
								<li>
									评奖学期:${pjxtszModel.pjxqmc }
								</li>
								<li>
									评奖年度:${pjxtszModel.pjnd }
								</li>
								<li>
									提示：控制本次评奖周期的时间，必须设置
								</li>
								<li>
									如果未设置的话，将取系统设置的当前时间为准
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div onmouseover="displayDiv(this,'helpcon3')"
				onmouseout="hiddenDiv(this,'helpcon3')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute; left:606px; top:60px;">
					<a href="#" onclick="pjlcLink(this,'zhcp_jbsz_jbsz.do');return false;"
						id="pjpy_zcsz"><div> <img
							src="<%=stylePath%>images/blue/48-1/Function19.png" /></div>
						<p>
							综测基本设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon3" style="display:none;right:84px">
						<div class="explain_con">
							<h3>
								综测基本设置
							</h3>
							<ul>
								<li>
									设置本评奖周期的综合素质测评分的组成结构，<br />
									并设置各个子项的比例，初始化学生综测信息。<br />
									注：若综测组成与上一周期未产生变化的话，请进入该模块后，操作复制项目即可。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon4')"
				onmouseout="hiddenDiv(this,'helpcon4')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute; left:385px; top:8px;">
					<a href="#"  id="pjpy_rycsh" onclick="return false;"> 
						<div>
						<img
							src="<%=stylePath%>images/blue/48-1/Function39.png" />
						</div>
						<p>
							评奖人员库初始化
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon4" style="display:none;right:97px;">
						<div class="explain_con">
							<h3>
								评奖人员库初始化
							</h3>
							<ul>
								<li>
									初始化操作执行后，将会同步实时的部门信息和学生信息进入评
									<br />
									奖人员库。然后可以对其进行调整，如果本周期不需要进行调整
									<br />
									仅执行初始化操作，那么进行项目设置即可。
								</li>
								<p class="btn" onclick="pjpyRycsh(jQuery('#pjpy_rycsh'));return false;">
					              <button type="button">初始化</button>
					            </p>	
							</ul>
						</div>
					</div>
				</div>
			</div>




			<div onmouseover="displayDiv(this,'helpcon5')"
				onmouseout="hiddenDiv(this,'helpcon5')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute; left:395px; top:95px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_ryqd.do');return false;"
						id="pjpy_ryqd"> 
						<div><img src="<%=stylePath%>images/blue/48-1/Function55.png" /></div>
						<p>
							评奖人员库确定
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon5" style="display:none;right:90px;">
						<div class="explain_con">
							<h3>
								评奖人员库确定
							</h3>
							<ul>
								<li>
									用于学生当前所在班级与参与评奖班级不一致时进行调整。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>




			<div onmouseover="displayDiv(this,'helpcon6')"
				onmouseout="hiddenDiv(this,'helpcon6')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:604px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_xmsz.do');return false;"
						id="pjpy_xmwh"> 
						<div>
							<img src="<%=stylePath%>images/blue/48-1/Function47.png" />
						</div>
						<p>
							项目维护
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon6" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								项目维护
							</h3>
							<ul>
								<li>
									维护当前评奖周期的评奖项目。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>



			<div onmouseover="displayDiv(this,'helpcon7')"
				onmouseout="hiddenDiv(this,'helpcon7')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute;left:16px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_sjsz.do');return false;" id="pjpy_sjsz">
						<div><img src="<%=stylePath%>images/blue/48-1/Function60.png" /></div>
						<p>
							时间设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon7" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								时间设置
							</h3>
							<ul>
								<li>
									用于设置评奖项目的申请和审核开关。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div onmouseover="displayDiv(this,'helpcon8')"
				onmouseout="hiddenDiv(this,'helpcon8')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute;left:121px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_tzfwsz.do');return false;"
						id="pjpy_tzsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function24.png" /></div>
						<p>
							调整设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon8" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								调整设置
							</h3>
							<ul>
								<li>
									用于设置代替的评奖项目。例如：某学生符合获得评奖"项目A",
									<br />
									但是该项目由于审核通过人数达到了设置人数,这时候就可以把
									<br/>
									该学生名额调整到其它项目。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>



			<div onmouseover="displayDiv(this,'helpcon9')"
				onmouseout="hiddenDiv(this,'helpcon9')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:224px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_jdsz.do');return false;"
						id="pjpy_jdsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function71.png" /></div>
						<p>
							兼得设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon9" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								兼得设置
							</h3>
							<ul>
								<li>
									用于设置评奖项目之间的兼得情况。例如：设置"项目A"与"项目B"不可兼得，
									<br />
									如果当前评奖周期学生获得了评奖"项目A"就不能再获得"项目B"。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon10')"
				onmouseout="hiddenDiv(this,'helpcon10')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:327px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_tjsz.do');return false;"
						id="pjpy_tjsz"><div><img
							src="<%=stylePath%>images/blue/48-1/Function56.png" /></div>
						<p>
							条件设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon10" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								条件设置
							</h3>
							<ul>
								<li>
									用于设置学生申请评奖项目需要满足的条件。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon11')"
				onmouseout="hiddenDiv(this,'helpcon11')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:420px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_rssz.do');return false;"
						id="pjpy_rssz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function25.png" /></div>
						<p>
							人数设置
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon11" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								人数设置
							</h3>
							<ul>
								<li>
									用于设置各<bean:message key="lable.xb" />可以获得某评奖项目的人数。
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

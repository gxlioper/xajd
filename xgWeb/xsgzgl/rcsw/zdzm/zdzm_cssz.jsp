<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript">

			jQuery(function(){
				changeSqkg();
				changeXzkg();

			});
		
			/**
			申请开关函数
			*/
			function changeSqkg(){
				var ksqkg = jQuery("[name=ksqkg]:checked").val();
				if("1"==ksqkg){
					jQuery('#kfsjTr, #splcTr').show();
				}else if("0"==ksqkg){
					jQuery('#kfsjTr, #splcTr').hide();
				}	
			}

			/**
			下载开关函数
			*/
			function changeXzkg(){
				var xzkg = jQuery("#xzkg").val();
				if("1"==xzkg){
					jQuery('#xzkgSpan').show();
				}else if("0"==xzkg){
					jQuery('#xzkgSpan').hide();
				}	
			}
			
			/**
			保存参数设置
			*/
			function saveCssz(){

				var ksqkg = jQuery("input[name='ksqkg']:checked").val();
				var ksqkssj = jQuery("input[name='ksqkssj']").val();
				var ksqjssj = jQuery("input[name='ksqjssj']").val();

				if(ksqkg == '1'){
					if(ksqkssj.length == 0 || ksqjssj.length == 0){
						showAlertDivLayer("请将必填项填写完整！");
						return false;
					}
				}
				
				var url = "rcsw_zdzm_csszgl.do?method=saveCssz";
				ajaxSubFormWithFun("rcswZdzmCsszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/rcsw_zdzm_csszgl" method="post" styleId="rcswZdzmCsszForm" >
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						申请开关：设置是否允许学生申请在读证明
						<br/>
						下载开关：设置是否允许学生下载在读证明申请表
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- 提示信息 end-->
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>申请开关设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>申请开关</th>
						<td>
						   
						   <logic:present name="sqkgList">
									<logic:iterate id="o" name="sqkgList" >
										<html:radio property="ksqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
							</logic:present>
						</td>
					</tr>
					<tr id="kfsjTr">
						<th>申请开放时间</th>
						<td>
							<html:text  property="ksqkssj" styleId="ksqkssj"   size="10"
									onclick="return showCalendar('ksqkssj','y-mm-dd',true,'ksqjssj');" 
									onblur="dateFormatChg(this)" readonly="true">
							</html:text>
								-
							<html:text  property="ksqjssj" styleId="ksqjssj"   size="10"
									onclick="return showCalendar('ksqjssj','y-mm-dd',false,'ksqkssj');" 
									onblur="dateFormatChg(this)" readonly="true">
							</html:text>
									
						</td>
					</tr>
					<tr  id="splcTr">
						<th><span class="red">*</span>审核流程</th>
						<td>
							<html:select property="splid" styleId="splid" disabled="false" >
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="2"><span>在读证明 下载设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>下载开关</th>
						<td>
							<html:select property="xzkg" styleId="xzkg"  onchange="changeXzkg();">
								<html:options collection="xzkgList" property="dm" labelProperty="mc" />
							</html:select>
							<span id="xzkgSpan">
							 <logic:present name="xzkzList">
									<logic:iterate id="o" name="xzkzList" >
										<html:radio property="kxzkz" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
							</logic:present>
							</span>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">			            
						<button type="button" class="button2" onclick="saveCssz();return false;"
							id="btn_save">
							保 存
						</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				
			</table>
		
				
		
			</div>
		</html:form>
	</body>
</html>

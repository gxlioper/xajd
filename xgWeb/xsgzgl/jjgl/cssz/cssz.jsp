<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript">
			/**
			保存参数设置
			*/
			function saveCssz(){

				var jjtdsqkg = jQuery("input[name='jjtdsqkg']:checked");
                var jjtdsplc = jQuery("#jjtdsplc").val();

                var tjjsqkg = jQuery("input[name='tjjsqkg']:checked");
                var tjjsplc = jQuery("#tjjsplc").val();

                var jjjssqkg = jQuery("input[name='jjjssqkg']:checked");
                var jjjssplc = jQuery("#jjjssplc").val();


				if(jjtdsqkg.length == 0 || jjtdsplc.length == 0 || tjjsqkg.length == 0 || tjjsplc.length == 0
                    || jjjssqkg.length == 0 || jjjssplc.length == 0){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				
				var url = "jjgl_cssz.do?method=saveCssz";
				ajaxSubFormWithFun("jjglCsszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
			
		</script>
	</head>
	<body >
		<html:form action="/jjgl_cssz" method="post" styleId="jjglCsszForm" >
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
						家教资格申请开关：用于控制开启关闭学生申请家教资格的开关；
						<br/>
						家教投递申请开关：用于控制开启关闭学生投递家教的开关；
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>家教投递参数设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>家教投递申请开关</th>
						<td>
						   <logic:present name="kgOptions">
								<logic:iterate id="o" name="kgOptions" >
									<html:radio property="jjtdsqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>

					<tr>
						<th width="40%">
							投递申请开放时间
						</th>
						<td>
							<html:text property="tdsqkssj" styleId="tdsqkssj"
									   onfocus="showCalendar('tdsqkssj','y-mm-dd',true,'tdsqjssj');" />
							&nbsp;至
							<html:text property="tdsqjssj" styleId="tdsqjssj"
									   onfocus="showCalendar('tdsqjssj','y-mm-dd',false,'tdsqkssj');" />
						</td>
					</tr>

					<tr>
						<th><span class="red">*</span>家教投递审批流程</th>
						<td>
							<html:select property="jjtdsplc" styleId="jjtdsplc" >
								<html:option value="">未设置</html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
				</tbody>

				<thead>
				<tr>
					<th colspan="2"><span>退家教参数设置</span></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th width="40%"><span class="red">*</span>退家教申请开关</th>
					<td>

						<logic:present name="kgOptions">
							<logic:iterate id="o" name="kgOptions" >
								<html:radio property="tjjsqkg" value="${o.dm}">${o.mc}</html:radio>
							</logic:iterate>
						</logic:present>
					</td>
				</tr>

				<tr>
					<th width="40%">
						退家教申请开放时间
					</th>
					<td>
						<html:text property="tjjsqkssj" styleId="tjjsqkssj"
								   onfocus="showCalendar('tjjsqkssj','y-mm-dd',true,'tjjsqjssj');" />
						&nbsp;至
						<html:text property="tjjsqjssj" styleId="tjjsqjssj"
								   onfocus="showCalendar('tjjsqjssj','y-mm-dd',false,'tjjsqkssj');" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>退家教审批流程</th>
					<td>
						<html:select property="tjjsplc" styleId="tjjsplc" >
							<html:option value="">未设置</html:option>
							<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
						</html:select>
					</td>
				</tr>
				</tbody>

				<thead>
				<tr>
					<th colspan="2"><span>家教结束参数设置</span></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>家教结束申请开关</th>
					<td>
						<logic:present name="kgOptions">
							<logic:iterate id="o" name="kgOptions" >
								<html:radio property="jjjssqkg" value="${o.dm}">${o.mc}</html:radio>
							</logic:iterate>
						</logic:present>
					</td>
				</tr>

				<tr>
					<th width="40%">
						家教结束申请开放时间
					</th>
					<td>
						<html:text property="jjjssqkssj" styleId="jjjssqkssj"
								   onfocus="showCalendar('jjjssqkssj','y-mm-dd',true,'jjjssqjssj');" />
						&nbsp;至
						<html:text property="jjjssqjssj" styleId="jjjssqjssj"
								   onfocus="showCalendar('jjjssqjssj','y-mm-dd',false,'jjjssqkssj');" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>家教结束审批流程</th>
					<td>
						<html:select property="jjjssplc" styleId="jjjssplc" >
							<html:option value="">未设置</html:option>
							<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
						</html:select>
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

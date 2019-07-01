<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jcsz/js/jcsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
  <body >
	<html:form action="/xszz_jcsz" method="post" styleId="xszzKnsJcszForm">
	<html:hidden property="rskzjb" styleId="rskzjb"/>
	<html:hidden property="rskznj" styleId="rskznj"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>时间设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							困难生认定周期
						</th>
						<td>
							<html:select property="rdxn" styleId="rdxn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
					
						<logic:equal value="xq" name="sqzq">
								<html:select property="rdxq" styleId="rdxq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
						</logic:equal>
						</td>
					</tr>
					<tr>
						<th width="40%"><span class="red">*</span>申请开关</th>
						<td>
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="sqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>申请开放时间</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"   size="10"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>审核流程</th>
						<td>
							<html:select property="splc" styleId="splc" disabled="false" onchange="initRskzjb()">
							<html:options collection="shlcList" property="splc"
								labelProperty="lcxx" />
						</html:select>
						</td>
					</tr>
				</tbody>
				
				<thead>
					<tr>
						<th colspan="2"><span>条件设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%">是否完成家庭情况调查才能申请</th>
						<td>
						
						   <logic:present name="kgList">
									<logic:iterate id="o" name="kgList" >
										<html:radio property="sfwcjtdc" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							
						</td>
					</tr>		
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">			            
						<logic:equal name="writeAble" value="yes">	
						<button type="button" class="button2" onclick="saveJcsz();return false;" style="width:80px"
							id="btn_save">
							保 存
						</button>
						</logic:equal>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
	</body>
</html>

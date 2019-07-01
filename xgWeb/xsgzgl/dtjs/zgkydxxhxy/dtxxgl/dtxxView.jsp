<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">		
		function saveDtxx(){
<%--			alert(jQuery('[name=kssj]').size());--%>
<%--			alert(jQuery('[name=jssj]').size());--%>
<%--			return false;--%>

			var curr_jddm=jQuery("#curr_jddm").val();
			if(curr_jddm==""){
				alertInfo("请选择学生党团进程！");
				return false;
			}
			if(jQuery("#kssj_"+curr_jddm).val()==""){
				alertInfo("请选择"+jQuery("#span_jdmc_"+curr_jddm).html()+"的开始时间");
				return false;
			}
			refreshForm('dtjs_dtxxgl.do?method=dtxxUpdate&doType=update');
		}

		function show(){
			var jdsx = jQuery('#jdsx').val();
			jQuery('[name=sj]').css({display:'none'})
			for(var i=1;i<=jdsx;i++){
				jQuery('#sj_'+i).css({display:''})
			}
			jQuery('[name=bq]').css({color:'gray'});
			jQuery('#bq_'+jdsx).css({color:'black'});
			jQuery("#curr_jddm").val(document.getElementById("td_jdmc_"+jdsx).getElementsByTagName("input")[0].value);
		}
	</script>
</head>
<body>
	<html:form action="/dtjs_dtxxgl" method="post">	
		<input type="hidden" id="jdsx" value="${dqjd.jdsx}"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>退宿信息增加</a>
			</p>
		</div>		
		--%>
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>学号				
					</th>
					<td width="34%">
						<html:text property="xh" styleId="xh" readonly="true" value="${rs.xh }" />
					</td>
					<th width="16%">
						姓名				
					</th>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<th width="16%">
						性别			
					</th>
					<td>
						${rs.xb}
					</td>
					<th width="16%">
						政治面貌
					</th>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<th width="16%">
						年级				
					</th>
					<td>
						${rs.nj}
					</td>
					<th width="16%">
						<bean:message key="lable.xsgzyxpzxy" />			
					</th>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<th width="16%">
						专业			
					</th>
					<td>						
						${rs.zymc}
					</td>
					<th width="16%">
						班级			
					</th>
					<td>
						${rs.bjmc}
					</td>
				</tr>
			</tbody>						
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生党团发展进程</span>
					</th>
				</tr>
			</thead>		
			<tbody>
				<tr>
					<td colspan="4">
					
					<div class="Join_party">
						<ul>
						<logic:iterate name="jdList" id="s">
					        <li>
								<div class="lc_bg">
					        		<div id="td_jdmc_${s.jdsx}" class="text">
					        		    <input type="hidden" name="jddm" value="${s.jddm}"/>
					        			<p id="span_jdmc_${s.jddm}" class="Process">${s.jdmc}</p>
					        			<p name="sj" id="sj_${s.jdsx}" class="time">
					        				${s.kssj}
											<logic:equal value="是" name="s" property="sfszjssj">
												~
												${s.jssj}
											</logic:equal>
					        			</p>
					        		</div>
					        	</div>
								<logic:notPresent name="s" property="isLast">
					        	<span class="Arrow"></span>
					        	</logic:notPresent>
					        </li>
					    </logic:iterate>
						</ul>
					</div>
<%--					<table class="formlist">--%>
<%--						<tr>						--%>
<%--							<logic:iterate name="jdList" id="s">--%>
<%--							<td align="center" id="td_jdmc_${s.jdsx}">--%>
<%--								<input type="hidden" name="jddm" value="${s.jddm}"/>--%>
<%--								<a name="bq" id="bq_${s.jdsx}" >--%>
<%--									<span id="span_jdmc_${s.jddm}">${s.jdmc}</span>--%>
<%--								</a>--%>
<%--							</td>--%>
<%--							</logic:iterate>--%>
<%--						</tr>--%>
<%--						<tr>						--%>
<%--							<logic:iterate name="jdList" id="s">--%>
<%--							<td align="center">--%>
<%--								<div name="sj" id="sj_${s.jdsx}" style="display: none">--%>
<%--									${s.kssj}--%>
<%--									<br/>	--%>
<%--									<logic:equal value="是" name="s" property="sfszjssj">--%>
<%--										${s.jssj}--%>
<%--									</logic:equal>										--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							</logic:iterate>--%>
<%--						</tr>			--%>
<%--					</table>--%>
					</td>									
				</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>其它信息</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						所属党支部			
					</th>
					<td width="34%">
						${other.zd6 }
					</td>
					<th width="16%">
					</th>
					<td width="34%">
					</td>
				</tr>
				
			</tbody>	
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button"  name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	
	<script>
	show();
	</script>
</body>
</html>

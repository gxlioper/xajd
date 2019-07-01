<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/calendar/calendar.js"></script>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/xlzxcl.js"></script>
	  <script type="text/javascript">
	  jQuery(function(){
		  	//设置该生问题类型
	  		var gswtlx = jQuery("#gswtlx").val().split(",");
	  		jQuery.each( gswtlx, function(index, value){
	  		   jQuery("input[name='wtlxarray'][value='"+value+"']").attr("checked",true);
	  		});
	  	    //设置该生其它问题类型
	  		var qtwtlx = new Array();
	  		qtwtlx[0] = jQuery("#qtwtlxStr").val();
	  		jQuery.each( qtwtlx, function(index, value){
	  		   if("qtwtlxarray"==value){
	  			 jQuery("#qtwtlx").css("display",""); 
	  		   }
	  		   jQuery("input[name='qtwtlxarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		//设置该生其它问题类型名称
	  		var qtwtlxstr = '${qtwtlxstr}';
	  		jQuery("#qtwtlx").val(qtwtlxstr);
	  	    //设置咨询师来访者对咨询的接受程度
	  		var jscd = jQuery("#jscd").val().split(",");
	  		jQuery.each( jscd, function(index, value){
  			   if("qt"==value){
	  			 jQuery("#qtjscd").css("display",""); 
	  		   }
	  		   jQuery("input[name='jscdarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		//设置访者心理问题严重程度评估
	  		var yzcdpg = jQuery("#yzcdpg").val().split(",");
	  		jQuery.each( yzcdpg, function(index, value){
	  		   if("qt"==value){
	  			 jQuery("#qtyzcdpg").css("display",""); 
	  		   }
	  		   jQuery("input[name='yzcdpgarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		if('2' == jQuery("input[name='sfyyxczx']:checked").val()){  //是否预约下次咨询     2：是    则显示选择日期输入框
	  			jQuery("#xczxsj").css("display","");
	  		}
	  });
	  </script>
  </head>
  
  <body>
    <html:form action="/xljk_xlzxcl" method="post" styleId="xlzxclForm">
    	<html:hidden property="gswtlx" styleId="gswtlx" value="${gswtlx }"/>
    	<html:hidden property="qtwtlxStr" styleId="qtwtlxStr" value="${qtwtlx }"/>
    	<html:hidden property="jscd" styleId="jscd" value="${jscd }"/>
    	<html:hidden property="yzcdpg" styleId="yzcdpg" value="${yzcdpg }"/>
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
			<logic:present name="xsyysqForm" property="sqid">
	    		<thead>
					<tr>
						<th colspan="4">
							<span>心理咨询预约信息</span>
						</th>
					</tr>
				</thead>
				<tbody id="xlzxyyxxviewtbody" style="display: none;">
					<tr>
						<th width="20%">
							咨询预约说明
						</th>
						<td colspan="3" width="80%">
							${zxyysm }
						</td>
					</tr>
					<tr>
						<th>
							预约咨询时间
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxsj"/>
						</td>
					</tr>
					<tr>
						<th>
							联系号码
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="xslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							问题类型
						</th>
						<td colspan="3">
							${wtlxMcStr }
						</td>
					</tr>
					<tr>
						<th>
							问题简要描述
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxzt" filter="false"/>
						</td>
					</tr>
					<tr>
						<th>
							其他备注
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxxq" filter="false"/>
						</td>
					</tr>
				</tbody>
				<tbody>
					<td colspan="4" style="height: 10px;padding: 0px;" align="center">
						<div class="more--item_bottom">
							<p>
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'xlzxyyxxviewtbody');return false">&nbsp;&nbsp;展开</a>
							</p>
						</div>
					</td>
				</tbody>
			</logic:present>
			<%--没有预约反馈的情况下不显示 --%>
			<logic:present name="xsyysqForm" property="yyzt">
				<%--预约中的情况下不显示 --%>
				<logic:notEqual value="1" name="xsyysqForm" property="yyzt">
					<%--预约中（学生取消）的情况下不显示 --%>
					<logic:notEqual value="3" name="xsyysqForm" property="yyzt">
						<thead>
							<tr>
								<th colspan="4">
									<span>咨询安排信息</span>
								</th>
							</tr>
						</thead>
					</logic:notEqual>
				</logic:notEqual>
			</logic:present>
			<logic:equal value="2" name="xsyysqForm" property="yyzt">
				<tbody id="zxapxxviewtbody" style="display: none;">
					<tr>
						<th width="20%">
							预约状态
						</th>
						<td width="30%">
							${yyztmc }
						</td>
						<th width="20%">
							安排咨询师
						</th>
						<td width="30%">	
							${zxsxm }
						</td>
					</tr>
					<tr>
						<th>
							咨询安排日期
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zzaprq"/>
						</td>
						<th>
							咨询时段
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zxsdkssj"/>
							至
							<bean:write name="xlzxclForm" property="zxsdjssj"/>
						</td>
					</tr>
					<tr>
						<th>
							联系电话
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							咨询地址
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxdz"/>
						</td>
					</tr>
					<tr>
						<th>
							备注
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="bz" filter="false"/>
						</td>
					</tr>
				</tbody>
				<tbody>
					<td colspan="4" style="height: 10px;padding: 0px;" align="center">
						<div class="more--item_bottom">
							<p>
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'zxapxxviewtbody');return false">&nbsp;&nbsp;展开</a>
							</p>
						</div>
					</td>
				</tbody>
			</logic:equal>
			<%--预约成功（学生取消）显示 --%>
			<logic:equal value="4" name="xsyysqForm" property="yyzt">
				<tbody>
					<tr>
						<th width="20%">
							预约状态
						</th>
						<td width="30%">
							${yyztmc }
						</td>
						<th width="20%">
							安排咨询师
						</th>
						<td width="30%">	
							${zxsxm }
						</td>
					</tr>
					<tr>
						<th>
							咨询安排日期
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zzaprq"/>
						</td>
						<th>
							咨询时段
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zxsdkssj"/>
							至
							<bean:write name="xlzxclForm" property="zxsdjssj"/>
						</td>
					</tr>
					<tr>
						<th>
							联系电话
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							咨询地址
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxdz"/>
						</td>
					</tr>
					<tr>
						<th>
							备注
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="bz" filter="false"/>
						</td>
					</tr>
				</tbody>
			</logic:equal>
			<%--预约失败显示 --%>
			<logic:equal value="5" name="xsyysqForm" property="yyzt">
				<tbody>
					<tr>
						<th width="20%">
							预约状态
						</th>
						<td colspan="3" width="80%">
							${yyztmc }
						</td>
					</tr>
					<tr>
						<th width="20%">
							预约失败原因
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xsyysqForm" property="yysbyy" filter="false"/>
						</td>
					</tr>
				</tbody>
			</logic:equal>
			<thead>
				<tr>
					<th colspan="4">
						<span>咨询反馈信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%">
						<span class="red">*</span>咨询状态
					</th>
					<td colspan="3" width="80%">
						<html:radio property="zxzt" value="1">已咨询</html:radio>
						<html:radio property="zxzt" value="2">未咨询</html:radio>
						
						<html:hidden property="zxid" styleId="zxid"/>
					</td>
				</tr>
				<tr>
					<th width="20%">
						咨询日期
					</th>
					<td width="30%">
						<html:text  property="zxrq"
									onclick="return showCalendar('zxrq','yyyy-MM-dd');"
									styleClass="text_nor" styleId="zxrq" readonly="true" />
					</td>
					<th width="20%">
						咨询时段
					</th>
					<td width="30%">
						<html:text  property="zxkssj"
									onclick="return showCalendar('zxkssj','HH:mm');"
									styleClass="text_nor" styleId="zxkssj" readonly="true" style="width:65px;" />
						至
						<html:text  property="zxjssj"
									onclick="return showCalendar('zxjssj','HH:mm');"
									styleClass="text_nor" styleId="zxjssj" readonly="true" style="width:65px;" />
					</td>
				</tr>
				<tr>
					<th>
						来访者主诉<br/>
						<span class="red">(限500字)</span>
					</th>
					<td colspan="3">
						<html:textarea property="lfzzs" styleId="lfzzs" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						咨询过程及主要<br/>的心理互动<br/>
						<span class="red">(限500字)</span>
					</th>
					<td colspan="3">
						<html:textarea property="xlhd" styleId="xlhd" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						咨询后的总结<br/>
						<span class="red">(限500字)</span>
					</th>
					<td colspan="3">
						<html:textarea property="zxzj" styleId="zxzj" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						该生问题类型
					</th>
					<td colspan="3">
						<logic:iterate name="xlwtList" id="xlwtxx" > 
							<html:checkbox property="wtlxarray" value="${xlwtxx.lxdm}">${xlwtxx.lxmc}</html:checkbox>
						</logic:iterate>
						<br/>
						<html:checkbox property="qtwtlxarray" value="qtwtlxarray" onclick="jQuery('#qtwtlx').toggle()">其它</html:checkbox>
						<html:text property="qtwtlx" styleId="qtwtlx" styleClass="text_nor" style="display:none;"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						咨询师来访者对<br/>
						咨询的接受程度
					</th>
					<td colspan="3">
						<html:checkbox property="jscdarray" value="hph">很配合</html:checkbox>
						<html:checkbox property="jscdarray" value="ybph">一般配合</html:checkbox>
						<html:checkbox property="jscdarray" value="za">阻碍</html:checkbox>
						<html:checkbox property="jscdarray" value="qt" onclick="jQuery('#qtjscd').toggle()">其它</html:checkbox>
						<html:text property="qtjscd" styleId="qtjscd" styleClass="text_nor" style="display:none;" value="${qtjscd}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						访者心理问题<br/>
						严重程度评估
					</th>
					<td colspan="3">
						<html:checkbox property="yzcdpgarray" value="hyz">很严重</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="bjyz">比较严重</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="ybcd">一般程度</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="jq">较轻</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="bswt">不是问题</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="qt" onclick="jQuery('#qtyzcdpg').toggle()">其它</html:checkbox>
						<html:text property="qtyzcdpg" styleId="qtyzcdpg" styleClass="text_nor" style="display:none;" value="${qtyzcdpg}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						是否需要转介
					</th>
					<td>
						<html:radio property="sfxyzj" value="1">否</html:radio>
						<html:radio property="sfxyzj" value="2">是</html:radio>
					</td>
					<th>
						是否预约下次咨询
					</th>
					<td>
						<html:radio property="sfyyxczx" value="1" onclick="jQuery('#xczxsj').hide();">否</html:radio>
						<html:radio property="sfyyxczx" value="2" onclick="jQuery('#xczxsj').show();">是</html:radio>
						<html:text  property="xczxsj"
									onclick="return showCalendar('xczxsj','yyyy-MM-dd');"
									styleClass="text_nor" styleId="xczxsj" readonly="true" style="display:none;"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div class="btn">
							<button id="submit_button" type="button"  onclick="zxFkAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
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

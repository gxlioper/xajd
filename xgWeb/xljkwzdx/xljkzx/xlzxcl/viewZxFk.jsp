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
		  	//���ø�����������
	  		var gswtlx = jQuery("#gswtlx").val().split(",");
	  		jQuery.each( gswtlx, function(index, value){
	  		   jQuery("input[name='wtlxarray'][value='"+value+"']").attr("checked",true);
	  		});
	  	    //���ø���������������
	  		var qtwtlx = new Array();
	  		qtwtlx[0] = jQuery("#qtwtlxStr").val();
	  		jQuery.each( qtwtlx, function(index, value){
	  		   if("qtwtlxarray"==value){
	  			 jQuery("#qtwtlx").css("display",""); 
	  		   }
	  		   jQuery("input[name='qtwtlxarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		//���ø�������������������
	  		var qtwtlxstr = '${qtwtlxstr}';
	  		jQuery("#qtwtlx").val(qtwtlxstr);
	  	    //������ѯʦ�����߶���ѯ�Ľ��̶ܳ�
	  		var jscd = jQuery("#jscd").val().split(",");
	  		jQuery.each( jscd, function(index, value){
  			   if("qt"==value){
	  			 jQuery("#qtjscd").css("display",""); 
	  		   }
	  		   jQuery("input[name='jscdarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		//���÷��������������س̶�����
	  		var yzcdpg = jQuery("#yzcdpg").val().split(",");
	  		jQuery.each( yzcdpg, function(index, value){
	  		   if("qt"==value){
	  			 jQuery("#qtyzcdpg").css("display",""); 
	  		   }
	  		   jQuery("input[name='yzcdpgarray'][value='"+value+"']").attr("checked",true);
	  		});
	  		if('2' == jQuery("input[name='sfyyxczx']:checked").val()){  //�Ƿ�ԤԼ�´���ѯ     2����    ����ʾѡ�����������
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
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
			<logic:present name="xsyysqForm" property="sqid">
	    		<thead>
					<tr>
						<th colspan="4">
							<span>������ѯԤԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xlzxyyxxviewtbody" style="display: none;">
					<tr>
						<th width="20%">
							��ѯԤԼ˵��
						</th>
						<td colspan="3" width="80%">
							${zxyysm }
						</td>
					</tr>
					<tr>
						<th>
							ԤԼ��ѯʱ��
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxsj"/>
						</td>
					</tr>
					<tr>
						<th>
							��ϵ����
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="xslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td colspan="3">
							${wtlxMcStr }
						</td>
					</tr>
					<tr>
						<th>
							�����Ҫ����
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxzt" filter="false"/>
						</td>
					</tr>
					<tr>
						<th>
							������ע
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
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'xlzxyyxxviewtbody');return false">&nbsp;&nbsp;չ��</a>
							</p>
						</div>
					</td>
				</tbody>
			</logic:present>
			<%--û��ԤԼ����������²���ʾ --%>
			<logic:present name="xsyysqForm" property="yyzt">
				<%--ԤԼ�е�����²���ʾ --%>
				<logic:notEqual value="1" name="xsyysqForm" property="yyzt">
					<%--ԤԼ�У�ѧ��ȡ����������²���ʾ --%>
					<logic:notEqual value="3" name="xsyysqForm" property="yyzt">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ѯ������Ϣ</span>
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
							ԤԼ״̬
						</th>
						<td width="30%">
							${yyztmc }
						</td>
						<th width="20%">
							������ѯʦ
						</th>
						<td width="30%">	
							${zxsxm }
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��������
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zzaprq"/>
						</td>
						<th>
							��ѯʱ��
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zxsdkssj"/>
							��
							<bean:write name="xlzxclForm" property="zxsdjssj"/>
						</td>
					</tr>
					<tr>
						<th>
							��ϵ�绰
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��ַ
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxdz"/>
						</td>
					</tr>
					<tr>
						<th>
							��ע
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
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'zxapxxviewtbody');return false">&nbsp;&nbsp;չ��</a>
							</p>
						</div>
					</td>
				</tbody>
			</logic:equal>
			<%--ԤԼ�ɹ���ѧ��ȡ������ʾ --%>
			<logic:equal value="4" name="xsyysqForm" property="yyzt">
				<tbody>
					<tr>
						<th width="20%">
							ԤԼ״̬
						</th>
						<td width="30%">
							${yyztmc }
						</td>
						<th width="20%">
							������ѯʦ
						</th>
						<td width="30%">	
							${zxsxm }
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��������
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zzaprq"/>
						</td>
						<th>
							��ѯʱ��
						</th>
						<td>
							<bean:write name="xlzxclForm" property="zxsdkssj"/>
							��
							<bean:write name="xlzxclForm" property="zxsdjssj"/>
						</td>
					</tr>
					<tr>
						<th>
							��ϵ�绰
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��ַ
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="zxdz"/>
						</td>
					</tr>
					<tr>
						<th>
							��ע
						</th>
						<td colspan="3">
							<bean:write name="xlzxclForm" property="bz" filter="false"/>
						</td>
					</tr>
				</tbody>
			</logic:equal>
			<%--ԤԼʧ����ʾ --%>
			<logic:equal value="5" name="xsyysqForm" property="yyzt">
				<tbody>
					<tr>
						<th width="20%">
							ԤԼ״̬
						</th>
						<td colspan="3" width="80%">
							${yyztmc }
						</td>
					</tr>
					<tr>
						<th width="20%">
							ԤԼʧ��ԭ��
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
						<span>��ѯ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%">
						<span class="red">*</span>��ѯ״̬
					</th>
					<td colspan="3" width="80%">
						<html:radio property="zxzt" value="1">����ѯ</html:radio>
						<html:radio property="zxzt" value="2">δ��ѯ</html:radio>
						
						<html:hidden property="zxid" styleId="zxid"/>
					</td>
				</tr>
				<tr>
					<th width="20%">
						��ѯ����
					</th>
					<td width="30%">
						<html:text  property="zxrq"
									onclick="return showCalendar('zxrq','yyyy-MM-dd');"
									styleClass="text_nor" styleId="zxrq" readonly="true" />
					</td>
					<th width="20%">
						��ѯʱ��
					</th>
					<td width="30%">
						<html:text  property="zxkssj"
									onclick="return showCalendar('zxkssj','HH:mm');"
									styleClass="text_nor" styleId="zxkssj" readonly="true" style="width:65px;" />
						��
						<html:text  property="zxjssj"
									onclick="return showCalendar('zxjssj','HH:mm');"
									styleClass="text_nor" styleId="zxjssj" readonly="true" style="width:65px;" />
					</td>
				</tr>
				<tr>
					<th>
						����������<br/>
						<span class="red">(��500��)</span>
					</th>
					<td colspan="3">
						<html:textarea property="lfzzs" styleId="lfzzs" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						��ѯ���̼���Ҫ<br/>��������<br/>
						<span class="red">(��500��)</span>
					</th>
					<td colspan="3">
						<html:textarea property="xlhd" styleId="xlhd" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						��ѯ����ܽ�<br/>
						<span class="red">(��500��)</span>
					</th>
					<td colspan="3">
						<html:textarea property="zxzj" styleId="zxzj" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						������������
					</th>
					<td colspan="3">
						<logic:iterate name="xlwtList" id="xlwtxx" > 
							<html:checkbox property="wtlxarray" value="${xlwtxx.lxdm}">${xlwtxx.lxmc}</html:checkbox>
						</logic:iterate>
						<br/>
						<html:checkbox property="qtwtlxarray" value="qtwtlxarray" onclick="jQuery('#qtwtlx').toggle()">����</html:checkbox>
						<html:text property="qtwtlx" styleId="qtwtlx" styleClass="text_nor" style="display:none;"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						��ѯʦ�����߶�<br/>
						��ѯ�Ľ��̶ܳ�
					</th>
					<td colspan="3">
						<html:checkbox property="jscdarray" value="hph">�����</html:checkbox>
						<html:checkbox property="jscdarray" value="ybph">һ�����</html:checkbox>
						<html:checkbox property="jscdarray" value="za">�谭</html:checkbox>
						<html:checkbox property="jscdarray" value="qt" onclick="jQuery('#qtjscd').toggle()">����</html:checkbox>
						<html:text property="qtjscd" styleId="qtjscd" styleClass="text_nor" style="display:none;" value="${qtjscd}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						������������<br/>
						���س̶�����
					</th>
					<td colspan="3">
						<html:checkbox property="yzcdpgarray" value="hyz">������</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="bjyz">�Ƚ�����</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="ybcd">һ��̶�</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="jq">����</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="bswt">��������</html:checkbox>
						<html:checkbox property="yzcdpgarray" value="qt" onclick="jQuery('#qtyzcdpg').toggle()">����</html:checkbox>
						<html:text property="qtyzcdpg" styleId="qtyzcdpg" styleClass="text_nor" style="display:none;" value="${qtyzcdpg}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ���Ҫת��
					</th>
					<td>
						<html:radio property="sfxyzj" value="1">��</html:radio>
						<html:radio property="sfxyzj" value="2">��</html:radio>
					</td>
					<th>
						�Ƿ�ԤԼ�´���ѯ
					</th>
					<td>
						<html:radio property="sfyyxczx" value="1" onclick="jQuery('#xczxsj').hide();">��</html:radio>
						<html:radio property="sfyyxczx" value="2" onclick="jQuery('#xczxsj').show();">��</html:radio>
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
								�� ��
							</button>
							<button type="button" name="�� ��" onclick="iFClose();">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</html:form>
  </body>
</html>

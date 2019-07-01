<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" contentType="text/html; charset=GBK"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String datestr = sdf.format(new Date());
	sdf = new SimpleDateFormat("HH-mm");
	String timestr = sdf.format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/calendar/calendar.js"></script>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/yyzxfk.js"></script>
	  <script type="text/javascript">
	  	function changeYyztPanel(type){
	  		if(type == "2"){//ԤԼ�ɹ�
	  			jQuery("#yysbpanel").css("display","none");
	  			jQuery("#yycgpanel").css("display","");
	  		}else if(type == "5"){//ԤԼʧ��
	  			jQuery("#yycgpanel").css("display","none");
	  			jQuery("#yysbpanel").css("display","");
	  		}
	  	}
	  	jQuery(function(){
	  		var type = '${xsyysq.yyzt}';
	  		changeYyztPanel(type);
	  		jQuery("input[name='yyzt'][value='"+type+"']").attr("checked",true);
	  		
	  		var zxs = '${zxs}';
	  		jQuery("input[name='zxs'][value='"+zxs+"']").attr("checked",true);
	  	});
	  	
	  	function getZxsByYysj(yysjs,node){
	  		var yysj;
	  		if(yysjs=='null'){
	  			yysj = jQuery(node).val();
	  		}else{
	  			yysj = yysjs;
	  		}
	  		// ��ѯ��������
	  		var zzaprq = jQuery("#zzaprq").val();
	  		var bolzzaprq = false;
	  		if(zzaprq != ""){
	  			bolzzaprq = true;
	  		}
	  		jQuery.post("xljk_yyzxfk.do?method=getZxsxxAllByYysjList",{yysj: yysj},function(data){
	  			jQuery("#zxsShowTd").html("");
	  			jQuery.each( data , function(i, node){
	  				var zxsxx = '<div style="margin: 5px 0px 5px 0px;">';
		  			zxsxx +='<input type="radio" name="zxs" value="'+node.zgh+'" onclick="checkZxs(this)">';
		  			zxsxx +='<span onclick="jQuery(this).parent().find(\':radio\').attr(\'checked\',true);checkZxs(this);" style="cursor: pointer;">';
		  			zxsxx +=node.xm+'&nbsp;['+node.xb+']['+node.bmmc+']['+node.zgh+']['+node.kjdrsms+']';
					if(bolzzaprq)
					{
						zxsxx += '['+zzaprq+'&nbsp;�Ѱ���'+node.yaprs+'��]';
					}		  			
		  			zxsxx +='</span><span class="lxdh" style="display: none;">'+node.lxdh+'</span>';
		  			zxsxx +='<span class="address" style="display: none;">'+node.address+'</span>';
		  			zxsxx +='<span class="kjdrs" style="display: none;">'+node.kjdrs+'</span>';
		  			zxsxx +='<span class="yaprs" style="display: none;">'+node.yaprs+'</span></div>';
		  			jQuery("#zxsShowTd").append(jQuery(zxsxx));
	  			});
			},'json');
	  	}
	  </script>
  </head>
  
  <body>
  <html:form action="/xljk_yyzxfk" method="post" styleId="yyzxfkForm">
    <table width="100%" border="0" class="formlist">
    	<thead>
			<tr>
				<th colspan="4">
					<span>ѧ����Ϣ</span>
				</th>
			</tr>
		</thead>
		<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
   		<thead>
			<tr>
				<th colspan="4">
					<span>������ѯԤԼ��Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="20%">
					ԤԼ��ѯʱ��
				</th>
				<td colspan="3">
					<bean:write name="xsyysq" property="yyzxsj"/>
					<html:hidden property="sqid" name="xsyysq"/>
				</td>
			</tr>
			<tr>
				<th>
					��ϵ����
				</th>
				<td colspan="3">
					<bean:write name="xsyysq" property="xslxdh"/>
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
					ԤԼ��ѯʦ
				</th>
				<td colspan="3">
					${yyzxsxm }
				</td>
			</tr>
			<tr>
				<th>
					�����Ҫ����
				</th>
				<td colspan="3">
					<bean:write name="xsyysq" property="yyzxzt" filter="false"/>
				</td>
			</tr>
			<tr>
				<th>
					������ע
				</th>
				<td colspan="3">
					<bean:write name="xsyysq" property="yyzxxq" filter="false"/>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="4">
					<span>��ѯ������Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>
					<span class="red">*</span>ԤԼ״̬
				</th>
				<td>
					<html:radio property="yyzt" value="2" onclick="changeYyztPanel('2')">ԤԼ�ɹ�</html:radio>
					<html:radio property="yyzt" value="5" onclick="changeYyztPanel('5')">ԤԼʧ��</html:radio>
				</td>
				<th>
					ԤԼ��ѯ��ʽ
				</th>
				<td colspan="3">
					<html:text property="yyzxfs" styleId="yyzxfs" styleClass="text_nor" maxlength="16"/>
				</td>
			</tr>
		</tbody>
		<tbody id="yysbpanel">
			<th>
				<span class="red">*</span>ԤԼʧ��ԭ��<br/>
				<span class="red">(��500��)</span>
			</th>
			<td colspan="3">
				<html:textarea property="yysbyy" styleId="yysbyy" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
			</td>
		</tbody>
		<tbody id="yycgpanel">
			<tr>
				<th>
					<span class="red">*</span>��ѯ��������
				</th>
				<td>
					<html:text  property="zzaprq"
								onclick="return showCalendar('zzaprq','yyyy-MM-dd',false,'zzaprqzxrq');"
								styleClass="text_nor" styleId="zzaprq" readonly="true" />
					<html:hidden property="zxid" styleId="zxid"/>
					<input type="hidden" id="zzaprqzxrq" value="<%=datestr %>"/>
				</td>
				<th>
					��ѯʱ��
				</th>
				<td>
					<html:text  property="zxsdkssj"
								onclick="return showCalendar('zxsdkssj','HH:mm',false,'zxsdkssjzxsj');"
								styleClass="text_nor" styleId="zxsdkssj" readonly="true" style="width:65px;" />
					��		
					<html:text  property="zxsdjssj"
								onclick="return showCalendar('zxsdjssj','HH:mm',false,'zxsdkssjzxsj');"
								styleClass="text_nor" styleId="zxsdjssj" readonly="true" style="width:65px;" />
				    <input type="hidden" id="zxsdkssjzxsj" value="<%=timestr %>"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>��ѯʦ
				</th>
				<td colspan="3" id="zxsShowTd">
					<div style="overflow-y: scroll; height: 120px;">
						<logic:iterate name="zxslist" id="zxs" >
							<div style="margin: 5px 0px 5px 0px;">
								<html:radio property="zxs" value="${zxs.zgh }" onclick="checkZxs(this)" >
									<span onclick="jQuery(this).parent().find(':radio').attr('checked',true);checkZxs(this);" style="cursor: pointer;">
									${zxs.xm }&nbsp;[${zxs.xb }][${zxs.bmmc }][${zxs.zgh }][${zxs.kjdrsms }]
									<logic:notEmpty name="zxs" property="yyrq">[ ${zxs.yyrq} &nbsp;�Ѱ���${zxs.yaprs }��]</logic:notEmpty>
									</span>
									<span class="lxdh" style="display: none;">${zxs.lxdh }</span>
									<span class="address" style="display: none;">${zxs.address }</span>
									<span class="kjdrs" style="display: none;">${zxs.kjdrs }</span>
									<span class="yaprs" style="display: none;">${zxs.yaprs }</span>
								</html:radio>
							</div>
						</logic:iterate>
					</div> 
				</td>
			</tr>
			<tr>
				<th>
					��ϵ�绰
				</th>
				<td colspan="3">
					<html:text property="zxslxdh" styleId="zxslxdh" styleClass="text_nor" maxlength="16"/>
				</td>
			</tr>
			<tr>
				<th>
					��ѯ��ַ
				</th>
				<td colspan="3">
					<html:text property="zxdz" styleId="zxdz" styleClass="text_nor" style="width:500px;" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<th>
					��ע<br/>
					<span class="red">(��500��)</span>
				</th>
				<td colspan="3">
					<html:textarea property="bz" styleId="bz" style="width:99%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<div class="bz">"<span class="red">*</span>"Ϊ������</div>
					<div class="btn">
						<button id="submit_button" type="button"  onclick="opYyzxfkAction();">
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

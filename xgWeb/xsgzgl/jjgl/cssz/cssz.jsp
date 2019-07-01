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
			�����������
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
					showAlertDivLayer("�뽫��������д������");
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
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						�ҽ��ʸ����뿪�أ����ڿ��ƿ����ر�ѧ������ҽ��ʸ�Ŀ��أ�
						<br/>
						�ҽ�Ͷ�����뿪�أ����ڿ��ƿ����ر�ѧ��Ͷ�ݼҽ̵Ŀ��أ�
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>�ҽ�Ͷ�ݲ�������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>�ҽ�Ͷ�����뿪��</th>
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
							Ͷ�����뿪��ʱ��
						</th>
						<td>
							<html:text property="tdsqkssj" styleId="tdsqkssj"
									   onfocus="showCalendar('tdsqkssj','y-mm-dd',true,'tdsqjssj');" />
							&nbsp;��
							<html:text property="tdsqjssj" styleId="tdsqjssj"
									   onfocus="showCalendar('tdsqjssj','y-mm-dd',false,'tdsqkssj');" />
						</td>
					</tr>

					<tr>
						<th><span class="red">*</span>�ҽ�Ͷ����������</th>
						<td>
							<html:select property="jjtdsplc" styleId="jjtdsplc" >
								<html:option value="">δ����</html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
							</html:select>
						</td>
					</tr>
				</tbody>

				<thead>
				<tr>
					<th colspan="2"><span>�˼ҽ̲�������</span></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th width="40%"><span class="red">*</span>�˼ҽ����뿪��</th>
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
						�˼ҽ����뿪��ʱ��
					</th>
					<td>
						<html:text property="tjjsqkssj" styleId="tjjsqkssj"
								   onfocus="showCalendar('tjjsqkssj','y-mm-dd',true,'tjjsqjssj');" />
						&nbsp;��
						<html:text property="tjjsqjssj" styleId="tjjsqjssj"
								   onfocus="showCalendar('tjjsqjssj','y-mm-dd',false,'tjjsqkssj');" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>�˼ҽ���������</th>
					<td>
						<html:select property="tjjsplc" styleId="tjjsplc" >
							<html:option value="">δ����</html:option>
							<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
						</html:select>
					</td>
				</tr>
				</tbody>

				<thead>
				<tr>
					<th colspan="2"><span>�ҽ̽�����������</span></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>�ҽ̽������뿪��</th>
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
						�ҽ̽������뿪��ʱ��
					</th>
					<td>
						<html:text property="jjjssqkssj" styleId="jjjssqkssj"
								   onfocus="showCalendar('jjjssqkssj','y-mm-dd',true,'jjjssqjssj');" />
						&nbsp;��
						<html:text property="jjjssqjssj" styleId="jjjssqjssj"
								   onfocus="showCalendar('jjjssqjssj','y-mm-dd',false,'jjjssqkssj');" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>�ҽ̽�����������</th>
					<td>
						<html:select property="jjjssplc" styleId="jjjssplc" >
							<html:option value="">δ����</html:option>
							<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
						</html:select>
					</td>
				</tr>
				</tbody>

				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">			            
						<button type="button" class="button2" onclick="saveCssz();return false;"
							id="btn_save">
							�� ��
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

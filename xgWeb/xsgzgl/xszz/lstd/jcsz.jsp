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
            function saveJcsz(){
                var sqkssj = jQuery("#sqkssj").val();
                var sqjssj = jQuery("#sqjssj").val();
                if(sqkssj == "" || sqkssj == null || typeof sqkssj == 'udafined'){
                    showAlertDivLayer("��ѡ��ʼʱ��!");
                    return false;
				}
                if(sqjssj == "" || sqjssj == null || typeof sqjssj == 'udafined'){
                    showAlertDivLayer("��ѡ�����ʱ��!");
                    return false;
                }
                var splc = jQuery("#splc").val();
                if (splc == "" || splc == null){
                    showAlertDivLayer("��ѡ���������!");
                    return false;
                }

                var url = "xszz_lstd.do?method=jcsz&type=save";
                ajaxSubFormWithFun("lstdJcszForm",url,function(data){
                    showAlertDivLayer(data["message"]);
                });

            }
		</script>
	</head>
  <body >
	<html:form action="/xszz_lstd" method="post" styleId="lstdJcszForm">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>ʱ������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>���뿪��ʱ��</th>
						<td>
							<input  name="sqkssj" id="sqkssj" size="10" value="${model.sqkssj}"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									 readonly="readonly">
								-
								<input  name="sqjssj" id="sqjssj" size="10" value="${model.sqjssj}"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									 readonly="readonly">
									
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>�������</th>
						<td>
							<select name="splc" id="splc">
								<logic:iterate id="i" name="shlcList">
									<option value="${i.splc}" <logic:equal name="model" property="splc" value="${i.splc}">selected="selected"</logic:equal>>${i.lcxx}</option>
								</logic:iterate>
							</select>
						</td>
					</tr>
				</tbody>
			</table>

			<table width="100%" border="0" class="formlist">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">			            
						<button type="button" class="button2" onclick="saveJcsz();return false;" style="width:80px"
							id="btn_save">
							�� ��
						</button>
						&nbsp;&nbsp;
						<%--<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none"
							id="buttonClose">
							�� ��
						</button>--%>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

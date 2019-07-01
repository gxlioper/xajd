<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
	jQuery(function() {//������ũ�Ѹ��Ի�
		var xxdm = jQuery("#xxdm").val();
		if("12872"==xxdm){
			var  myselect=document.getElementById("qjlxid");
			var index=myselect.selectedIndex ;
			//var store = $("#qjlxid option:selected").text();
			var store = myselect.options[index].text;
			if(store==("����")){			
				jQuery("#fjbt").append("<font color='red' id = 'fjbtlx'>*</font>");
			}else{
				jQuery("#fjbtlx").remove();
			}
		}
		if ("12688" == xxdm) {
			jQuery("#qjts").change(function() { 
			  var qjts = jQuery("#qjts").val();
			  if(Number(qjts)>1){
			  jQuery('input:checkbox').attr("checked",false);
		  	  jQuery("#jcTr_12688").hide();
			  }else{
			  	jQuery("#jcTr_12688").show();
			  }
			}); 
		}
		
		if ("12727" == xxdm) {
			jQuery("#qjts").change(function() { 
			  var qjts = jQuery("#qjts").val();
			  if(Number(qjts)>=1){
			  	jQuery("#jcTr_12727").hide();
			  }else{
			  	jQuery("#jcTr_12727").show();
			  }
			}); 
			jQuery('input:checkbox').click(function() {
				var b = false;
				var qjjc = document.getElementsByName("mdd");
				for ( var i = 0; i < qjjc.length; i++) {
					if (qjjc[i].checked) {
						b = true;
					}
				}
				if (b == false) {
					jQuery("#qjts").attr("readonly", false);
				} else {
					jQuery("#qjts").val("0.5");
					jQuery("#qjts").attr("readonly", true);
				}
			});
		}
		if(xxdm == "11998"){
			 var date = new Date();
			 var hour =date.getHours();
			 if(hour<6 || hour >22){
				 jQuery(".kzan").hide();
				 jQuery("#tt").show();
			 }
		}
	});

	var _initQjmx = function(){
		var days;
		var kssj = jQuery("#kssj").val();
		var jssj = jQuery("#jssj").val();
		
		if (kssj == "" || jssj == ""){
			return false;
		}
		
		jQuery("#qjmxTbody").empty();
		var xxdm = jQuery("#xxdm").val();
		//if(xxdm == "12861"){
		//	 days = dateDiffNotWithHours(kssj,jssj)+1;
		//}else{
			 days = dateDiff(jssj,kssj)+1;
		//}
		
		for (var i = 0; i < days; i++){
			var nextDate = getNextDate(kssj,i);
			var rInput = jQuery("<input type='hidden' name='mxrq' value='"+nextDate+"'/>");
			var td = jQuery("<td align='center'>"+nextDate+"</td>");
			var tr = jQuery("<tr></tr>");
			td.append(rInput);
			tr.append(td);
			
			var qjxmCount = jQuery("#qjmxTr th").size()-1;
			
			for (var j = 0; j < qjxmCount; j++){
				var cbox = jQuery("<input checked='checked' name='mxxm"+i+"' type='checkbox' value='"+jQuery("#qjmxTr th").eq(j+1).attr("xmdm")+"'/>");
				var td = jQuery("<td align='center'></td>");
				td.append(cbox);
				tr.append(td);
			}
			
			jQuery("#qjmxTbody").append(tr);
		}

	    if (xxdm == "12861"){
		    var qjtsnum = dateDiffWithHours(kssj,jssj);
		    jQuery("#qjts").val(qjtsnum);
		    jQuery("#qjtstd").html(qjtsnum+" ��");
	    }
	};

	function checkAll(obj) {
		var checked = jQuery(obj).prop("checked");
		jQuery("#qjmxTbody :input").attr("checked", checked);
	}
	function showstore(){//fjbt   store
		var  myselect=document.getElementById("qjlxid");
		var index=myselect.selectedIndex ;
		//var store = $("#qjlxid option:selected").text();
		var store = myselect.options[index].text;
		if(store==("����")){			
			jQuery("#fjbt").append("<font color='red' id = 'fjbtlx'>*</font>");
		}else{
			jQuery("#fjbtlx").remove();
		}
	}
	function commCallBack() {
		var xxdm = jQuery("#xxdm").val();
		var kssj = jQuery("#kssj").val();
		var jssj = jQuery("#jssj").val();

		if (kssj == "" || jssj == "") {
			return false;
		}
		if (xxdm == "70002" || xxdm=="11998") {
			var qjtsnum = dateDiffFixed2(kssj, jssj);
			jQuery("#qjts").val(qjtsnum);
			jQuery("#qjtstd").html(qjtsnum + " ��");
		}
	}
</script>
		
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjsq"
			enctype="multipart/form-data">
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<input type="hidden" id = "usertype" value = "${usertype}"/>
			<html:hidden property="ssxydm" styleId="ssxydm" value="${jbxx.xydm}"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
			<div style="" id="div_help" class="prompt">
				<h3>
					<span>��ܰ��ʾ��</span>
				</h3>
				<p>
					<!-- �����ݴ�ѧ������ʦ����ѧ-->
					<logic:notEqual name="xxdm" value="10351">
					<logic:notEqual name="xxdm" value="10718">
					<span style="color: red;"> ����ڼ�Ҫע����˰�ȫ���Ծ�����ط����Ϸ��ϵ���ƭ�� <br />
						��绰/����֪ͨ�����Σ�������ͬ������ </span>
					</logic:notEqual>
					</logic:notEqual>
					<!-- ���ݴ�ѧ -->
					<logic:equal name="xxdm" value="10351">
					<span style="color: red;"> ����ڼ�Ҫע����˰�ȫ���Ծ�����ط����Ϸ��ϵ���ƭ�� <br />
						��绰/����֪ͨ����Ա������Աͬ������ </span>
					</logic:equal>
					<!-- ����ʦ����ѧ -->
					<logic:equal name="xxdm" value="10718">
					<span style="color: red;"> ����ڼ�Ҫע����˰�ȫ������ط����Ϸ��ϵ���ƭ�� <br />
						�ύ���������뼰ʱ��ϵ����Ա������Աͬ�����������������У��ʱ���١� </span>
					</logic:equal>
				</p>
				<a onclick="this.parentNode.style.display='none';" title="����"
					class="close"></a>
			</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ
								<logic:equal value="10511" name="xxdm">
								&nbsp;&nbsp;
									<a onclick="selectQjkc();" 
									   href="javascript:void(0);">
									   <font color="blue"><u>ѡ����ٿγ�</u></font>	
									</a>
								</logic:equal>
								</span>
								
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
							    <logic:notEqual value="12861" name="xxdm">
									<span class="red">*</span>
								</logic:notEqual>
								�������
							</th>
							<td align="left" >
							    <logic:equal value="12861" name="xxdm">
							    	<span id="qjtstd"></span>
							        <input type="hidden" name="qjts" id="qjts"/>
								</logic:equal>
								<logic:equal value="70002" name="xxdm">
									<span id="qjtstd"></span>
									<input type="hidden" name="qjts" id="qjts"/>
								</logic:equal>
								<logic:equal value="11998" name="xxdm">
									<span id="qjtstd"></span>
									<input type="hidden" name="qjts" id="qjts"/>
								</logic:equal>
								<logic:notEqual value="12861" name="xxdm">
									<logic:notEqual value="70002" name="xxdm">
										<logic:notEqual value="11998" name="xxdm">
										   <html:text property="qjts" styleId="qjts" style="width:120px;"
												maxlength="4" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>
											&nbsp;&nbsp;��&nbsp;&nbsp;
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th align="right">
								<span class="red">*</span>�������
							</th>
							<td align="left">
								<logic:equal value="12872" name="xxdm">
									<html:select property="qjlxid" styleId="qjlxid" disabled="false" onchange="showstore()" 
										style="width:125px;">
										<html:options collection="qjlxList" property="qjlxid"
											labelProperty="qjlxmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="12872" name="xxdm">
									<html:select property="qjlxid" styleId="qjlxid" disabled="false" 
										style="width:125px;">
										<html:options collection="qjlxList" property="qjlxid"
											labelProperty="qjlxmc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>��ٽ���
							</th>
							<td align="left" >
							   <html:text property="qjjs" styleId="qjjs" style="width:120px;" maxlength="5" onkeyup="checkInputData(this);"></html:text>
								&nbsp;&nbsp;��&nbsp;&nbsp;
							</td>
							<th align="right">
								<span class="red">*</span>�Ƿ���У
							</th>
							<td align="left">
								<html:select property="sflx" styleId="sflx" disabled="false" style="width:125px;">
									<html:options collection="isnotList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>��ٿ�ʼʱ��
							</th>
							<logic:equal value="1" name="qjsjxsgz">
							    <td align="left">
									<logic:equal value="true" name="qjmxEnable" >
										<html:text property="kssj" styleId="kssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'jssj','','',_initQjmx);" />
									</logic:equal>
									<logic:notEqual value="true" name="qjmxEnable">
										<html:text property="kssj" styleId="kssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'jssj','','',commCallBack);" />
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:notEqual value="1" name="qjsjxsgz">
								<td align="left">
									<logic:equal value="true" name="qjmxEnable" >
										<html:text property="kssj" styleId="kssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'jssj','','',_initQjmx);" />
									</logic:equal>
									<logic:notEqual value="true" name="qjmxEnable">
										<html:text property="kssj" styleId="kssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'jssj','','',commCallBack);" />
									</logic:notEqual>
								</td>
							</logic:notEqual>
							
							<th align="right">
								<span class="red">*</span>��ٽ���ʱ��
							</th>
							<logic:equal value="1" name="qjsjxsgz">
							   <td align="left">
									<logic:equal value="true" name="qjmxEnable" >
										<html:text property="jssj" styleId="jssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'kssj','','',_initQjmx);" />
									</logic:equal>
									<logic:notEqual value="true" name="qjmxEnable">
										<html:text property="jssj" styleId="jssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'kssj','','',commCallBack);" />
									</logic:notEqual>
								</td>
							</logic:equal>
							
							<logic:notEqual value="1" name="qjsjxsgz">
								<td align="left">
									<logic:equal value="true" name="qjmxEnable" >
										<html:text property="jssj" styleId="jssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'kssj','','',_initQjmx);" />
									</logic:equal>
									<logic:notEqual value="true" name="qjmxEnable">
										<html:text property="jssj" styleId="jssj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'kssj','','',commCallBack);" />
									</logic:notEqual>
								</td>
							</logic:notEqual>
						</tr>
						<logic:equal name="xxdm" value="12727">
							<tr id="jcTr_12727">
								<th align="right" width="10%">
									��ٽڴ�
								</th>
								<td colspan="3">
									<input type="checkbox" name="mdd" value="��һ��" >��һ��</input>
									<input type="checkbox" name="mdd" value="�ڶ���" >�ڶ���</input>
									<input type="checkbox" name="mdd" value="������" >������</input>
									<input type="checkbox" name="mdd" value="���Ľ�" >���Ľ�</input>
									<input type="checkbox" name="mdd" value="�����" >�����</input>
									<br/>
									<input type="checkbox" name="mdd" value="������" >������</input>
									<input type="checkbox" name="mdd" value="���߽�" >���߽�</input>
									<input type="checkbox" name="mdd" value="�ڰ˽�" >�ڰ˽�</input>
									<input type="checkbox" name="mdd" value="�ھŽ�" >�ھŽ�</input>
									<input type="checkbox" name="mdd" value="��ʮ��" >��ʮ��</input>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="12688">
							<tr id="jcTr_12688">
								<th align="right" width="10%">
									��ٽڴ�
								</th>
								<td colspan="3">
									<input type="checkbox" name="mdd" value="��һ��" >��һ��</input>
									<input type="checkbox" name="mdd" value="�ڶ���" >�ڶ���</input>
									<input type="checkbox" name="mdd" value="������" >������</input>
									<input type="checkbox" name="mdd" value="���Ľ�" >���Ľ�</input>
									<input type="checkbox" name="mdd" value="�����" >�����</input>
									<br/>
									<input type="checkbox" name="mdd" value="������" >������</input>
									<input type="checkbox" name="mdd" value="���߽�" >���߽�</input>
									<input type="checkbox" name="mdd" value="�ڰ˽�" >�ڰ˽�</input>
									<input type="checkbox" name="mdd" value="�ھŽ�" >�ھŽ�</input>
									<input type="checkbox" name="mdd" value="��ʮ��" >��ʮ��</input>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="70002">
							<th align="right">
								<span class="red">*</span>У��У��
							</th>
							 <td align="left">
							 	<html:select property="xnxw" styleId="xnxw" style="width:125px">
							 		<html:option value=""></html:option>
							 		<html:option value="У��">У��</html:option>
							 		<html:option value="У��">У��</html:option>
							 	</html:select>
							 </td>
							 <th></th>
							 <td></td>
						</logic:equal>
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th>��ٿγ�</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>�γ�����</td>
											<td>�ο���ʦ����</td>
											<td>�ο���ʦ��ϵ��ʽ</td>
										</tr>
									</thead>
									<tbody id="qjkc"></tbody>
								</table>
							</td>
						</tr>
						</logic:equal>
						
						<logic:equal value="true" name="qjmxEnable" >
							</tbody>
							<thead>
								<tr>
									<th colspan="4">
										<span>�����ϸ���</span>
										<label>
											<input type="checkbox" onclick="checkAll(this);" checked="checked"/>ȫѡ/ȡ��ȫѡ
										</label>
									</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td colspan="4">
									<table style="width:100%;">
										<thead >
											<tr id="qjmxTr">
												<th style="text-align: center;">����</th>
												<logic:iterate id="q" name="qjxmList">
													<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
												</logic:iterate>
											</tr>
										</thead>
										<tbody id="qjmxTbody">
										
										</tbody>
									</table>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�ҳ��绰&nbsp;
							</th>
							<td colspan="3">
								<html:text property="jzdh" styleId="jzdh" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10695">
							<tr>
								<th align="right">
									<span class="red">*</span>�໤������
								</th>
								<td>
									<html:text property="jhrxm" styleId="jhrxm" maxlength="10"></html:text>
								</td>
								<th align="right">
									<span class="red">*</span>�໤����ϵ��ʽ
								</th>
								<td>
									<html:text property="jhrlxfs" styleId="jhrlxfs" maxlength="15"></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									<span class="red">*</span>��ͨ����
								</th>
								<td>
									<html:select property="jtgj" styleId="jtgj">
										<html:options collection="dmList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
								<th align="right">
									<span class="red">*</span>Ŀ�ĵ�
								</th>
								<td>
									<html:text property="mdd" styleId="mdd" maxlength="20"></html:text>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�������&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="qjsy" styleId="qjsy"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							
							<th align="right" width="10%" >
								<span id = "fjbt"></span>������Ϣ	
							</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'filepath'
												});
										});
									</script>
						</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button class="kzan" type="button"
										onclick="save('qjsq.do?method=add&type=save','xh-qjts-qjlxid-kssj-jssj-qjsy');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button class="kzan" type="button"
										onclick="save('qjsq.do?method=tj&type=save','xh-qjts-qjlxid-kssj-jssj-qjsy');return false;"
										id="buttonSave">
										�ύ����
									</button>
									<label id="tt" style="color:red;display:none">��ٿ���ʱ��Ϊ06:00-22:00,���н����������绰��ϵ����Ա��</label>
									<button type="button" onclick="iFClose();" id="buttonClose">
										�� ��
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

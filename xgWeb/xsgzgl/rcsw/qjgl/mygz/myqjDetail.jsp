<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/rcsw/qjgl/myqjDetail.js"></script>
				<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
				<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
		var j=0;
		function uploadPic(){
			var qjclPath=$("qjclPath").value;
			var path=qjclPath.split(".")[1];
			if(path!="jpg"){
				alertError("��֧��.jpg��ʽ��ͼƬ�ļ�,��ȷ�ϣ�");
				return false;
			}
			jQuery.ajaxFileUpload({
			  url:'rcsw_qjgl.do?method=uploadQjcl',//�������˳���
			  secureuri:false,
			  fileElementId:'qjclPath',//input���ID
			  async: false,
			  dataType: 'json',//������������
			  success: function (data){//�ϴ��ɹ�
			  	jQuery('#qjclImg').empty();
			  	$("qjcl").value=data;
			  	var html = '<img src="<%=request.getContextPath()%>/qjclPic.jsp?id='+data+'&flg=';
			  		html+= (++j);
			  		html+= '" border="0" align="absmiddle" style="width:800px" />'
			  	
			  	jQuery("#qjclImg").html(html).show();
			  }
			});
		}
		
		//�����������
		function saveQjsq(tag){
			var xh=jQuery("#xh").val();
			var qjlx=jQuery("input:checked").val();
			if(xh==""){
				showAlert("��ѡ��ѧ��");
				return false;
			}
			if(tag == "ok"){
				//��ʼʱ��
				var kssj = $("kssj").value;
				if(kssj == ""){
					alertError("��ٿ�ʼʱ�䲻��Ϊ�գ���ȷ��");
					return false;
				}
				
				//����ʱ��
				var jssj = $("jssj").value;
				if(jssj == ""){
					alertError("��ٽ���ʱ�䲻��Ϊ�գ���ȷ��");
					return false;
				}
				
				if(parseInt(kssj) > parseInt(jssj)){
					alertError("����ʼʱ�䡿�������ڡ�����ʱ�䡿����ȷ��");
					return false;
				}
				//��������
				var sqts = $("sqts").value;
				if(null==sqts||sqts == ""){
					alertError("������������Ϊ�գ���ȷ��");
					return false;
				}
				
				//
				if(null==qjlx||qjlx == ""){
					alertError("������Ͳ���Ϊ�գ���ȷ��");
					return false;
				}
				
				//ѧ��
				var xh = $("xh").value;
				//���ID
				var qjid = $("qjid").value;
				//��ϵ�绰
				var lxdh = $("lxdh").value;
				//��ͥ�绰
				var jtdh = $("jtdh").value;
				//��ͥ��ַ
				var jtdz = $("jtszd").value;
				//��������
				var sqly = $("sqly").value;
				//��ע
				var bz = $("bz").value;
				//��ע
				var qjcl = $("qjcl").value;

				var qjlx = jQuery('input[name=qjlx]:checked').val(); 
				
				var url="rcsw_qjgl.do?method=saveQjsq";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//����
			 	var parameter = {
					"qjid":qjid,
					"xh":xh,
					"sqts":sqts,
					"kssj":kssj,
					"jssj":jssj,	
					"lxdh":lxdh,
					"jtdh":escape(jtdh),
					"jtdz":escape(jtdz),
					"sqly":escape(sqly),
					"bz":escape(bz),
					"qjcl":escape(qjcl),
					"qjlx":escape(qjlx)
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result=="����ɹ�"){
						showAlert(result,{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}else{
						alertInfo(result);
					}
				});
			}
		}
		
		//������������
		function setLxmc(){
			
			var sqts=$("sqts").value;
			var qjlx=jQuery("[name=qjlx]:checked").eq(0).val();
			
			var url="rcsw_qjgl.do?method=getQjxm";
			
			if(sqts!="" && qjlx!=""){
				//����
			 	var parameter = {
					"sqts":sqts,
					"qjlx":qjlx
				};
				
				jQuery.post(url,parameter,function(data){
					var select=jQuery("<select id='qjid' name='qjid'></select>");
					if(data != ""){
						//var qjxm = result.split("!!@@!!")[0];
						//var id = result.split("!!@@!!")[1];
						//$("qjid").value=id;
						jQuery.each(data,function(i,e){
						var option = jQuery('<option></option>');
						    option.append(e["lxmc"]);
						    option.attr("value",e["id"]);
						    select.append(option);
						});
						jQuery("#btn_bc").attr("disabled",false);
						jQuery("#p_qjxm").html(select);
						
					}else{
						//$("qjid").value="";
						$("btn_bc").disabled="true";
						$("p_qjxm").innerHTML="<font color=\"red\">��������������������δָ��������̣��޷����룬��ȷ��</font>";
					}
				},'json');
			}
		}
		
		
		</script>
	</head>
	<body onload="">

		<!-- ���� -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>--%>
		<!-- ���� end-->

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
					1.��ϵ�绰����ͥ�绰����ͥ��ַȡ��ѧ����Ϣ���������������Ļ��������޸ġ�</br>
					2.����д<font color="blue">��������</font>���������¼����������Զ���������͡�</br>
					3.���û����һ�����������¼�����������ƥ�䣬��<font color="blue">�޷�����</font>������ϵ�����ʦȷ�ϡ�
			</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/rcsw_qjgl" method="post"  enctype="multipart/form-data">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="qjcl" styleId="qjcl" />
			<html:hidden name="rs" property="id" styleId="id" />
			<input type="hidden" id="url" name="url"
				value="rcsw_qjgl.do?method=myqjDetail" />
		
			<!-- ������ end-->

			<!-- ѧ��������Ϣ -->
			<div style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							ѧ��
						</th>
						<td width="30%">
							
							<html:hidden name="rs" property="id" styleId="id"/>
							<!-- 
							<html:hidden name="rs" property="qjid" styleId="qjid"/>
							-->
							<logic:equal name="userType" value="stu">
								<html:hidden name="rs" property="xh" styleId="xh"/>
								${rs.xh }
							</logic:equal> 
							<logic:notEqual name="userType" value="stu">
								<html:text name="rs" property="xh" styleId="xh" readonly="readonly"/>
								<button type="button" onclick="showDialog('��ѡ��һ��ѧ��',680,550,'xsxx_xsgl.do?method=showStudents&goto=rcsw_qjgl.do?method=myqjDetail');return false;" class="btn_01" >
									ѡ��
								</button>
							</logic:notEqual> 
						</td>
						<th width="20%">
							����
						</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							${rs.xb }
						</td>
						<th>
							�༶
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>
							��ϵ�绰
						</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this)"
								maxlength="20" style="ime-mode:disabled;" />
						</td>
						<th>
							��ͥ�绰
						</th>
						<td>
							<html:text name="rs" property="jtdh" styleId="jtdh"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this)"
								maxlength="20" style="ime-mode:disabled;" />
						</td>
					</tr>
					<tr>
						<th>
							��ͥ��ַ
						</th>
						<td colspan="3">
							<html:text name="rs" property="jtszd" styleId="jtszd"
								style="width: 545px" maxlength="50" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- ѧ��������Ϣ end-->

			<!-- ��ٻ�����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>���������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							<font color="red">*</font>��ٿ�ʼʱ��
						</th>
						<td width="30%">
							<html:text name="rs" property="kssj" styleId="kssj"
								onclick="return showCalendar('kssj','ymmdd');" readonly="true" />
						</td>
						<th width="20%">
							<font color="red">*</font>��ٽ���ʱ��
						</th>
						<td width="30%">
							<html:text name="rs" property="jssj" styleId="jssj"
								onclick="return showCalendar('jssj','ymmdd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>��������
						</th>
						<td width="30%">
							<html:text name="rs" property="sqts" styleId="sqts"
								onkeyup="checkInputNum(this)" onblur="checkInputNum(this);setLxmc()"
								maxlength="5" style="width:50px;ime-mode:disabled;" />��
						</td>
						<th width="20%">
							<font color="red">*</font>�������
						</th>
						<td width="30%">
							<logic:iterate name="qjlxList" id="qjlx" indexId="index">
								<html:radio property="qjlx"  styleId="qjlx_${index }" value="${qjlx.dm}" />${qjlx.mc}
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th width="20%">
							�������
						</th>
						<td width="30%" colspan="3">
							<p id="p_qjxm">
								<font color="blue">��¼����ϣ�����������</font>
							</p>
						</td>
					</tr>
					<tr>
						<th>
							��������
							<br />
							<font color="red">(����¼��500��)</font>						
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="sqly"  
								styleId="sqly" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							��ע
							<br />
							<font color="red">(����¼��500��)</font>	
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="bz"  
								styleId="bz" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
				</tbody>
				
				<tbody>
					
					<tr>
						<th>
						       �����ϴ�
						</th>
						<td colspan="3"> 
							<input type="file" id="qjclPath" name="qjclPath" style="width:60%"  onchange='uploadPic();'/>
							&nbsp;&nbsp;
							<font color="red">(�ļ���СС��&lt;10M&gt;)</font>
							<div>
								<font color="blue">Ŀǰ��δ��֤�����ϣ�����"���"ѡ���ļ��ϴ�<br/>
								ע:��֧��.jpg��ʽ��ͼƬ�ļ�</font>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4"> 
						<div id="qjclImg" style="display:none">
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- ��ٻ�����Ϣ end-->

			

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- ���� -->
								<button type="button"  onclick="saveQjsq('ok');" id="btn_bc">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- �ر�-->
								<button type="button"  onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

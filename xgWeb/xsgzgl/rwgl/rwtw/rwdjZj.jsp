<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
			<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script>
		
			//ѡ��
			function sendXx(){
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
				url = "rwgl_rwtwgl_ajax.do?method=xsxxCk";
				jQuery.getJSON(url,parameter,
					function(data){
						if(data!=null){
							jQuery("#xm").text(data.xm);
							jQuery("#xb").text(data.xb);
							jQuery("#nj").text(data.nj);
							jQuery("#xymc").text(data.xymc);
							jQuery("#zymc").text(data.zymc);
							jQuery("#bjmc").text(data.bjmc);
							jQuery("#zzmmmc").text(data.zzmmmc);
							jQuery("#mzmc").text(data.mzmc);
							jQuery("#csrq").text(data.csrq);
							jQuery("#sfzh").text(data.sfzh);
							jQuery("#sjhm").text(data.sjhm);
							jQuery("#jtszd").text(data.jtszd);
							jQuery("#jtdh").text(data.jtdh);
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
			//����
			function rwdjBc(){
				if(jQuery.trim(jQuery("#xh").val())==""){
			 		alertInfo("ѧ�Ų���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#rwxn").val())==""){
			 		alertInfo("����ѧ�겻��Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#rwsj").val())==""){
			 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#sg").val())==""){
			 		alertInfo("��߲���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#tz").val())==""){
			 		alertInfo("���ز���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#zysl").val())==""){
			 		alertInfo("������������Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#yysl").val())==""){
			 		alertInfo("������������Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
								
				//if(jQuery.trim(jQuery("#rwd").val())==""){
			 	//	alertInfo("����ز���Ϊ��!",function(tag){
			 	//		if(tag=="ok"){
			 	//			return false;
			 	//		}
			 	//	});
			 	//	return false;
				//}
				
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
				parameter["rwsj"]=escape(jQuery("#rwsj").val());
				parameter["rwdwd"]=escape(jQuery("#rwdwd").val());
				parameter["rwxn"]=escape(jQuery("#rwxn").val());
				parameter["rwfs"] = escape(jQuery("#rwfs").val());//������鷽ʽ
				parameter["sg"] = escape(jQuery("#sg").val());
				parameter["tz"] = escape(jQuery("#tz").val());
				parameter["zysl"] = escape(jQuery("#zysl").val());
				parameter["yysl"] = escape(jQuery("#yysl").val());
				parameter["rwd"] = escape(jQuery("#rwd").val());
				url = "rwgl_rwtwgl_ajax.do?method=rwdjBc&doType=add";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						if("����ɹ�"==result){
							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
						}else{
							alertInfo(result,function(tag){
				     			if(tag=="ok"){
				     				return false;
				     			}
				     		});
				     		return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:405px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" maxlength="16" styleClass="text_nor" readonly="true" style="width:130px"/>
								<button type="button" onclick="showDialog('',800,550,'rwgl_rwtwgl.do?method=zxxsCx&goto=rwgl_rwtwgl.do?method=rwdjZj');return false;" class="btn_01">
									ѡ��
								</button>
								<input type="hidden" id="getStuInfo" onclick="sendXx();"/>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								<font id="xm">${rs.xm}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								<font id="xb">${rs.xb}</font>
							</td>
							<th width="16%">
								�꼶
							</th>
							<td width="34%">
								<font id="nj">${rs.nj}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								<font id="xymc">${rs.xymc}</font>
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								<font id="zymc">${rs.zymc}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								<font id="bjmc">${rs.bjmc}</font>
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%">
								<font id="zzmmmc">${rs.zzmmmc}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<font id="mzmc">${rs.mzmc}</font>
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<font id="csrq">${rs.csrq}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���֤��
							</th>
							<td width="34%">
								<font id="sfzh">${rs.sfzh}</font>
							</td>
							<th width="16%">
								�ֻ�����
							</th>
							<td width="34%">
								<font id="sjhm">${rs.sjhm}</font>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ͥ��ַ
							</th>
							<td width="34%">
								<font id="jtszd">${rs.jtszd}</font>
							</td>
							<th width="16%">
								��ͥ�绰
							</th>
							<td width="34%">
								<font id="jtdh">${rs.jtdh}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%"><span class="red">*</span>ѧ��</th>
							<td width="34%">
								<html:select  property="rwxn" styleId="rwxn" style="width:155px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>����ʱ��
							</th>
							<td width="34%">
								<html:text property="rwsj" styleId="rwsj" maxlength="10" onclick="return showCalendar('rwsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red"></font>�����
							</th>
							<td width="34%">
								<!-- ���ݴ�ѧ -->
								<logic:equal name="xxdm" value="10351">	
									<html:select  property="rwdwd" styleId="rwdwd" style="width:155px">
										<html:options collection="rwtjList" labelProperty="mc" property="dm"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									<html:text property="rwd" styleId="rwd" maxlength="120" styleClass="text_nor" ></html:text>
								</logic:notEqual>
							</td>
							<th width="16%">
								<font color="red">*</font>���鷽ʽ
							</th>
							<td width="34%">
								<html:select  property="rwfs" styleId="rwfs" style="width:155px">
									<html:options collection="rwfsList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>���</th>
							<td width="34%">
								<html:text property="sg" styleId="sg" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>����</th>
							<td width="34%">
								<html:text property="tz" styleId="tz" maxlength="10" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>��������</th>
							<td width="34%">
								<html:text property="zysl" styleId="zysl" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>��������</th>
							<td width="34%">
								<html:text property="yysl" styleId="yysl" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="rwdjBc()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>


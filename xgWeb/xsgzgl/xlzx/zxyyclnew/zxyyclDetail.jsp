<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		function saveyyzxInfo(){
				if(!jQuery("tr[name=yyfkId]").is(":hidden")  && jQuery("#zxrq").val()==''){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				
				if(!jQuery("tr[name=yysbyytr]").is(":hidden") && jQuery("#yysbyy").val()==''){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				//��ԤԼ�ɹ��ġ���ѡ���ԤԼ����ԤԼ�ɹ��ģ��ѽӴ��������ܴ��ڿɽӴ�������
				if(jQuery("#status").val()!="2" && jQuery("#yystatus").val()=="2" && parseInt(jQuery("#yjdrs").val())>=parseInt(jQuery("#kjdrs").val())){
					return showAlert("�Ѵﵽ��ԤԼ���ޣ�");
				}
				var zxurl='';
				var yyurl='';
				var zxParameter ={};
				var yyParameter ={};
				//ԤԼ����
				if(jQuery("#doType").val()=='yyfk'){
					yyurl = "xlzx_yysqnew.do?method=updateYysqInfo";
					zxurl = "xlzx_zxyyclnew.do?method=saveXlzxInfo";
					var zxUpurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";

					zxParameter ={
				    	yyid:jQuery("#yyid").val(),
						zxrq:jQuery("#zxrq").val(),
						qssj:jQuery("#zxqssj").val(),
						jssj:jQuery("#zxjssj").val(),
						zxtell:jQuery("#zxtell").val(),
						zxdz:jQuery("#zxdz").val(),
						zxstatus: 1,//������ѯ��Ϣ����״̬��Ϊ1����ѯ
						bz:jQuery("#bz").val(),
						//xspjzt:1//ѡ����ѯ��Ϣ��ѧ������״̬1������
						id:jQuery("#zxid").val()//�JԃID
					};
					
					showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
						//ԤԼ�ɹ�
						if(jQuery("#yystatus").val()=="2"){
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val()};
							//ԤԼ״̬����
							jQuery.post(yyurl,yyParameter,function(result){
								if(result=="true"){
									if(jQuery("#zxid").val()!=""){

										//��ѯ��Ϣ��������ѯ��
										jQuery.post(zxUpurl,zxParameter,function(data){
											if(data == true){
														showAlert("����ɹ���",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yysqManage");
															iFClose();
														}
													});
											}else{
												return showAlert("����ʧ�ܣ�");
											}
										},'json');
									}else{
										//��ѯ��Ϣ��������ѯ��
										jQuery.post(zxurl,zxParameter,function(data){
											if(data == true){
														showAlert("����ɹ���",{},{"clkFun":function(){
															frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yysqManage");
															iFClose();
														}
													});
											}else{
												return showAlert("����ʧ�ܣ�");
											}
										},'json');
									}
								}else{
									return showAlert("����ʧ�ܣ�");						
								}
							});

						//ԤԼʧ��
						}else if(jQuery("#yystatus").val()=="5"){
							//ԤԼʧ�ܣ���ɾ����ѯ��Ϣ
							jQuery.post("xlzx_zxyyclnew.do?method=delZxInfoByYyid",{yyid:jQuery("#yyid").val()},function(result){
												});
							yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val(),yysbyy:jQuery("#yysbyy").val()};
							//ԤԼ״̬����
							jQuery.post(yyurl,yyParameter,function(data){
								if(data == "true"){
									showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yysqManage");
										iFClose();
									}});
								}else{
									return showAlert("����ʧ�ܣ�");						
								}
							});
						}
						jQuery.ajaxSetup({async:true});
					}});
				}

				//��ѯ����
				if(jQuery("#doType").val()=='zxfk'){
					
					zxParameter={};
					meetFlag();
					if(jQuery("#zxzt").val()==""||jQuery("#zxzt").val()=="1"){
						return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
					}
					//��ѯ״̬ȡ���ж�
					if(jQuery("#xspjzt").val()=="2"){
						return showAlert("����ѯѧ���Ѿ����ۡ��޷��޸���ѯ������");
					}

					zxParameter["zxstatus"]=jQuery("#zxzt").val();//1����ѯ2����ѯ3δ��ѯ
					if(jQuery("#zxzt").val()=="2"){
						zxParameter["xspjzt"]=1;
					}
					
					zxParameter["yyid"]=jQuery("#yyid").val();
					//zxParameter["zxsfk"]=jQuery("#zxsfk").val();
				
					zxurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
					
					showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
							//��ѯ��Ϣ��������ѯ��	
							jQuery.post(zxurl,zxParameter,function(data){
								if(data == true){
											showAlert("����ɹ���",{},{"clkFun":function(){
												frameElement.api.opener.refreshForm("xlzx_yysqnew.do?method=yysqManage");
												iFClose();
											}
										});
								}else{
									return showAlert("����ʧ�ܣ�");
								}
							},'json');
							jQuery.ajaxSetup({async:true});
					}});
				}
		}

		function showNewJdrs(obj){
			if(delValidate()){
				var url = "xlzx_yysqnew.do?method=yyzxDetail&doType=yyfk&id="+jQuery("#yyid").val()+"&zxrq="+jQuery("#zxrq").val();
				refreshForm(url);
			}
			
		}

		function delValidate(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var zgh = jQuery("#zgh").val();
			var date = jQuery("#zxrq").val();
			var pbdate = jQuery("#pbdate").val();
			jQuery.ajaxSetup({async:false});
			jQuery.post("xlzx_zxspb.do?method=getSfkyFlag&xh="+xh,{pbdate:date,zgh:zgh},function(data){
				if(data["message"]==""){
					flag = true;
				}else{
					showAlert(data["message"],{},{"clkFun":function(){
						jQuery("#zxrq").val(pbdate);
						flag = false;
					}});
				}
			},'json');
			jQuery.ajaxSetup({async:true});
			return flag;
		}

		function getYjdrs(){
			var count=0;
			var zgh = jQuery("#zgh").val();
		    var date =jQuery("#pbdate").val();
			var url = "xlzx_yysqnew.do?method=getZxsYjdrsByDate&zgh="+zgh+"&date="+date;
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,{},function(data){
				count = data;
			});
			jQuery.ajaxSetup({async:true});
			return count;
		}


		function addYyInfo(zgh){
			var pbdate = jQuery("#pbdate").val();
			showDialog("ԤԼ��Ϣ",640,260,"xlzx_yysqnew.do?method=addyyzxInfo&yyzxrq="+pbdate+"&zgh="+zgh);
		}

		function disabledView(){
			
			if(jQuery("#doType").val()=="view"){
				jQuery("input,select,text,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				});
			}
		}


		function init(){
			sfAgree();
			
			//jQuery("input[type='radio'][name='meet'][value='"+jQuery("#status").val()+"']").attr("checked",true);
			if(jQuery("#status").val()=="2"){
				//jQuery("input[type='radio'][name='meet']").attr("disabled",true);
			}
			meetFlag();
			if(jQuery("#doType").val()=="view"){

				jQuery("#btx").hide();
				jQuery("#buttonSave").hide();
			}
		}

		function sfAgree(){
			if(jQuery("#status").val()=="2"){
				//jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();		
			}
			else if(jQuery("#status").val()=="5"){
			    jQuery("tr[name=yyfkId]").hide();
			    //jQuery("tr[name=yysbyytr]").show();
			    jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}else{
			 	jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").hide();		
				jQuery("thead[name=zxxgInfo]").hide();	
				jQuery("tbody[name=zxxgInfo]").hide();
			}
			
			if(jQuery("input[type='radio'][name='sfty'][value='1']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").show();
				jQuery("tr[name=yysbyytr]").hide();
				jQuery("#yystatus").val("2");
			}else if(jQuery("input[type='radio'][name='sfty'][value='2']").prop("checked")==true){
				jQuery("tr[name=yyfkId]").hide();
				jQuery("tr[name=yysbyytr]").show();
				jQuery("#yystatus").val("5");
			}
		}

		function meetFlag(){
			if(jQuery("input[type='radio'][name='zxzt'][value='1']").prop("checked")==true){
				jQuery("#zxzt").val("1");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='2']").prop("checked")==true){
				jQuery("#zxzt").val("2");
			}else if(jQuery("input[type='radio'][name='zxzt'][value='3']").prop("checked")==true){
				jQuery("#zxzt").val("3");
			}
		}
		
		</script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysqnew" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${yyzxInfo.yyzxrq}" />
			<input type="hidden" name="zxid" id="zxid" value="${yyzxInfo.zxid}" />
			<input type="hidden" name="id" id="id" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="zgh" id="zgh" value="${yyzxInfo.zgh}" />
			<input type="hidden" name="xh" id="xh" value="${yyzxInfo.xh}" />
			<input type="hidden" name="yjdrs" id="yjdrs" value="${yjdrs}" />
			<input type="hidden" name="kjdrs" id="kjdrs" value="${yyzxInfo.kjdrs}" />
			<div style='width:100%;height:520px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									����
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxm}
								</td>
								<th  width="16%">
									ְ����
								</th>
								<td  width="34%">
									${yyzxInfo.zgh}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									�Ա�
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									����
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									��ϵ�绰
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									���ڲ���
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
									
								</td>
							</tr>							
					</tbody>
					<logic:notEmpty name="yyzxInfo" property="yyzxzt">
						 <thead>
							<tr >
								<th colspan="4">
									<span>ѧ��ԤԼ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="yyzxInfo"> 
							<tr style="height:10px">
								<th>
									ԤԼ��ѯ����
								</th>
								<td >
									<span class="red"><B>${ yyzxInfo.yyzxrq}</B></span>
								</td>
								 <th  width="16%">
									ԤԼ��ѯʱ��
								</th>
								<td  width="34%" >
									${yyzxInfo.yyqssj}&nbsp;
									<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
											��&nbsp;${yyzxInfo.yyjssj}
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									Ԥ����ϵ����
								</th>
								<td >
									${ yyzxInfo.xstell}
								</td>
								<th>
									ѧ��ѧ��
								</th>
								<td >
									${ yyzxInfo.xn}&nbsp;&nbsp;${ yyzxInfo.xqmc}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									��ѯ����
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxzt}
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									��ѯ��Ҫ<br/>
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxxq}
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									ԤԼ״̬
								</th>
								<td  width="34%">
									<input type="hidden" name="status" id="status" value="${yyzxInfo.status}">
									<input type="hidden" name="yystatus" id="yystatus" value="">
									<logic:equal name ="doType" value="yyfk">
										<input type="radio"  name="sfty"  value="1" id="yyfk_yycg"
										<logic:notEqual name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:notEqual>
											onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yycg">ԤԼ�ɹ�</label>
										<input type="radio"  name="sfty"  value="2" id="yyfk_yysb"
										<logic:equal name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:equal>
										onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yysb">ԤԼʧ��</label>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.statusmc}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
											${yyzxInfo.statusmc}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<input type="radio"  name="sfty"  value="1" checked="true" onclick="sfAgree()"/>ԤԼ�ɹ�
												<input type="radio"  name="sfty"  value="2" onclick="sfAgree()"/>ԤԼʧ��
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
									
								</td>
								
								<td colspan="2">
									<logic:equal name="yyzxInfo" property="kjdrs" value="">
										<span class="blue">����ѯʦ�����ѳɹ�ԤԼ</span><span class="red">${yjdrs}</span><span class="blue">��,������</span>
									</logic:equal>
									<logic:notEqual name="yyzxInfo" property="kjdrs" value="">
										<span class="blue">����ѯʦ�����ѳɹ�ԤԼ</span><span class="red">${yjdrs}</span><span class="blue">��,����</span><span class="red">${yyzxInfo.kjdrs}</span><span class="blue">��</span>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>��ѯ��������
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxrq"  name="zxrq" 
										<logic:notEqual name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.zxrq}"
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.yyzxrq}"
										</logic:equal> onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="showNewJdrs(this);"/>
									</logic:equal>
									
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											<span class="red"><B>${yyzxInfo.zxrq}</B></span>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxrq}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxrq" styleId="zxrq"  value="${yyzxInfo.yyzxrq}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="showNewJdrs(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
								<th  width="16%">
									��ѯʱ��
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										   <input type="text" id="zxqssj"  name="zxqssj"  style="width:30%"  
												<logic:notEqual name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.zxqssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.yyqssj}"
													</logic:equal>
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
											<input type="text" id="zxjssj"  name="zxjssj" style="width:30%" 
												<logic:notEqual name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.zxjssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.yyjssj}"
													</logic:equal>					
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
												${yyzxInfo.zxqssj}&nbsp;
												<logic:notEmpty name="yyzxInfo" property="zxjssj">��&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
													${yyzxInfo.zxqssj}&nbsp;
													<logic:notEmpty name="yyzxInfo" property="zxjssj">��&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="${yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
												<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="${yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ϵ�绰
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxtell"  name="zxtell"  
											<logic:notEqual name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.zxtell}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.lxdh}"
												</logic:equal>
											maxlength="11" onblur="checkInputData(this);"/>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxtell}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxtell}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxtell" styleId="zxtell"  value="${yyzxInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId" >
								<th  width="16%">
									��ѯ��ַ
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxdz"  name="zxdz" style="width:400px" maxlength="50" 
											<logic:notEqual name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.zxdz}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.address}"
												</logic:equal>
										  />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxdz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxdz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxdz" styleId="zxdz" maxlength="50" style="width:400px" value="${yyzxInfo.address}" />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									��ע
									<logic:equal name ="doType" value="yyfk"><br/><font color="red"><B>(��500��)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name="doType" value="view"><br/><font color="red"><B>(��500��)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='bz' styleId="bz" 												
											value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxbz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxbz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='bz' styleId="bz" value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>	
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>ԤԼʧ��ԭ��
									<logic:equal name ="doType" value="yyfk"><br><font color="red"><B>(��500��)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name ="doType"  value="view"><br><font color="red"><B>(��500��)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.yysbyy}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.yysbyy}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							
					</tbody>
						<thead name = "zxxgInfo">
							<tr >
								<th colspan="4">
									<span>��ѯ�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxInfo" name = "zxxgInfo">
								<tr style="height:10px">
									<th  width="16%">
										<logic:equal name ="doType" value="zxfk"><span class="red">*</span></logic:equal>��ѯ״̬
									</th>
									<td  width="34%" colspan="3">
										<input type="hidden" name="xspjzt" id="xspjzt" value="${yyzxInfo.xspjzt}">
										<input type="hidden" name="zxstatus" id="zxstatus" value="${yyzxInfo.zxzt}">
										<input type="hidden" name="zxzt" id="zxzt" value="">
										<logic:equal name ="doType" value="zxfk">
											<html:radio name="yyzxInfo" property="zxzt" value="2"/>����ѯ
											<html:radio name="yyzxInfo" property="zxzt" value="3"/>δ��ѯ</logic:equal>
										<logic:notEqual name ="doType" value="zxfk">
											${yyzxInfo.zxztmc}
										</logic:notEqual>
									</td>
								</tr>
								<tr style="height:40px">
									<th  width="16%">
										ѧ����ѯ����
									</th>
									<td  width="34%" colspan="3">
											${yyzxInfo.xspj}
									</td>
								</tr>
						</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
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


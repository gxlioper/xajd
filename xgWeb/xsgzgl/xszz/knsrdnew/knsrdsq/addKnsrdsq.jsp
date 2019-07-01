<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script	type="text/javascript">
		function saveForm1(obj){	
			var base=new Array();
			var i=1;
			var xh = jQuery("#xh").val();
			var zbid = jQuery("#zbid").val();
			var jtqkFlag;
			if(xh == ''){
				showAlertDivLayer("��ѡ��һ��ѧ����");
				return false;
			}
			var trMytrNum = 0; // ѡ���ָ������
			jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
				var rdzb=new Object();
				rdzb.sxid=jQuery(this).find("input[name=sxid]").val();
				rdzb.jtqk=jQuery(this).find("textarea[name=jtqk]").val();
				//�жϾ�����������Ƿ�Ϊ��
				var jtqkNum = 0;
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
					var zbnr=new Object();
					if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
						jtqkNum ++ ;
					}
				});
				jtqkFlag = false;//�жϾ�����������Ƿ�Ϊ��
				if(jtqkNum > 0){
					trMytrNum++;
					var jtqk=jQuery(this).find("textarea[name=jtqk]").val();
					if(jtqk == ""){
						//showAlertDivLayer("��ѡ��ָ�������������Ӧ�ľ��������������¼�룡");
						jtqkFlag = true;
						return false;
					}
				}

				//�Ƿ񵯳���ͥ���������дҳ��
				var openJtqk = jQuery("#openJtqk").val();

				if ("true" == openJtqk){
					var xh = jQuery("#xh").val();

					showAlertDivLayer("������д��ͥ��������",{},{"clkFun":function(){
						editJtqk();
					}});
					return false;
				}
				
				if(jtqkFlag == true){
					return false;
				}
				//�жϾ�����������Ƿ�Ϊ��
				var zbnra=new Array();
				var xxnri=1;
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
					var zbnr=new Object();
					if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
						zbnr.nrid = jQuery(this).find("input[name=nrid]").val();
						zbnr.fz = jQuery(this).find("input[name=fzH]").val();
						zbnra[xxnri-1]=zbnr;
						xxnri++;
					}
				});
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;
			});
			if(jtqkFlag == true){
				showAlertDivLayer("��ѡ��ָ�������������Ӧ�ľ��������������¼�룡");
				return false;
			}
			// ���"��ѡ���϶�ָ��"Ϊ1,��ô,ֻ��ѡ��һ��ָ��
			if('${jcszModel.kxzrdzb}' == '1' && trMytrNum > 1){
				showAlertDivLayer("ֻ��ѡ��һ��ָ�꣡");
				return false;
			}
			var json=JSON.stringify(base);
			var url = "";
			if(obj == "save"){
				url = "xg_xszz_knsrd_knsqgl.do?method=addKnsrdsq&type=save";
			}else{
				url = "xg_xszz_knsrd_knsqgl.do?method=addKnsrdsq&type=submit";
			}
			 
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json,xh:xh},
				   success:function(data){
				 		 if(data["message"]=="����ɹ���" ||data["message"]=="�ύ�ɹ���" ){
				    		 showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
					}
			});
		}	

		/**
		 * ���ؼ�ͥ���������Ϣ
		 * @param obj
		 * @return
		 */
		function showJtqk(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#t_jtqk").toggle();
		}

		
		/**
		 * �������϶��������༭��ͥ���
		 * @return
		 */
		function editJtqk(){
			var xh = jQuery("#xh").val();
			showDialog('��ͥ�������',780,500,'xszz_jtqkdc.do?method=dcxxModify&type=update&xh='+xh,{
				close:function(){
					reloadWindow();
				}
			});
		}

		/**
		 * ˢ���������϶��������
		 * @return
		 */
		function reloadWindow(){
			var xh = jQuery("#xh").val();
			document.location.href="xg_xszz_knsrd_knsqgl.do?method=addKnsrdsq&xh="+xh;
		}

		jQuery(function(){

			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) != ""){
				jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
			}

			/****************����һ�¸߶�*******************/
				var h = jQuery('.tz_gaodu_td').height();
				jQuery('.tz_gaodu_td').each(function(i){
					jQuery(this).find('#knsrdsqzbnr').css('height',jQuery(this).height() + 'px');
					jQuery(this).find('#jtqk').css('height',jQuery(this).find('#jtqk_td').height() + 'px');
				});
			/*****************����һ�¸߶�******************/
		});
		

		</script>
		
	</head>
	<body>
		<input type="hidden" id="openJtqk" value="${openJtqk }" />
		<html:form action="/xg_xszz_knsrd_knsqgl" method="post" styleId="knsrdsqForm" onsubmit="return false">
			<div class="prompt">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p >
						��ѡ��ָ�������������Ӧ�ľ����������Ҳ����¼��
					</p>
					<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/knsrdnew/comm/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ��� <logic:notEqual value="" property="xh"
										name="knsrdsqForm">
										<a onclick="showJtqk(this);" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
										|
										<a onclick="editJtqk();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">�༭��ͥ���</font>
										</a>
									</logic:notEqual> </span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">

								</div>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶�ָ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody name="tbody_knsrdzb">
						<logic:notEmpty name="object">
							<logic:iterate name="object" id="s" indexId="i">
								<table width="100%" border="0" class="formlist" name="knsrdzb"
									style="margin: 2px auto;">
									<tr name="mytr">
										<input type='hidden' id='sxid' name='sxid'
											value="${s.key.sxid}" />
										<td width="20%">
											${s.key.sxmc}
										</td>
										<td width="55%">
											<table width="100%" border="0" class="datelist"
												id="knsrdzbnr" style="margin: 2px auto;">
												<thead>
													<tr>
														<td width="7%">

														</td>
														<td width="93%" style="text-align: center">
															ָ������
														</td>
													</tr>
													<tbody name="tbody_knsrdzbnr">
														<logic:iterate name="s" id="list" indexId="j"
															property="value">
															<tr id="knsrdzbxxnr">
																<td width="7%">
																	<input type="hidden" name="nrid" value="${list.nrid}" />
																	<logic:notEqual value="10052" name="xxdm" >
																		<input type='checkbox' name="zbnrid" />
																	</logic:notEqual>
																	<logic:equal value="10052" name="xxdm" >
																		<input type='radio' name="zbnrid" />
																	</logic:equal>
																	<input type="hidden" name="fzlxH" value="${list.fzlx}" />
																	<input type="hidden" name="fzH" value="${list.fz}" />
																</td>
																<td width="50%">
																	${list.nrmc }
																</td>
															</tr>
														</logic:iterate>
													</tbody>
												</thead>
											</table>
										</td>

										<td width="25%" colspan="2" class="tz_gaodu_td">
											<table width="100%" border="0" class="datelist" 
												id="knsrdsqzbnr" style="margin: 2px auto;">
												<thead>
													<tr>
														<td>
															<font color="red">*</font>�������������ѧ��¼�룩
														</td>
													</tr>
													<tbody>
														<tr>
															<td id="jtqk_td">
																<textarea name='jtqk' id="jtqk" rows='5'
																	style="word-break: break-all; width: 98%; height: 100%"
																	onblur="chLeng(this,250);"></textarea>
															</td>
														</tr>
													</tbody>
												</thead>
											</table>
										</td>

									</tr>
								</table>
							</logic:iterate>
						</logic:notEmpty>
					</tbody>
				</table>
			</div>
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm1('save');">
										����ݸ�
									</button>
									
									<button type="button" type="button" onclick="saveForm1('submit');">
										�ύ����
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
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


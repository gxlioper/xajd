<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			displayYwmkxx();	
		}
		//��ʾ��ҵ��ģ���б�����
		function displayYwmkxx() {
			
			var url = "xsxx_tygl.do?method=xsYwmklb";
			var xh = jQuery('#xh').val();
			var tdarray = jQuery(".ywmkclass");
			if (tdarray != null && tdarray.length > 0) {
				jQuery(tdarray).each(function(i,n) {
					var td_mkmc = jQuery(n).attr("id");
					var parameter = {
						"gnmk":escape(td_mkmc.replace("td_","")),
						"xh":xh
					};
					jQuery("#"+td_mkmc).load(url,parameter,function(){
						
					});
				});
			}
		}

		//��ʾTBODY���� 
		function displayTbody(tbodyId) {
			if (document.getElementById(tbodyId)) {
				document.getElementById(tbodyId).style.display=(document.getElementById(tbodyId).style.display==''?'none':'');
			}
		}
		//��ʾ�ϴ���Ƭ
		function showZpscDiv(){
	
			var xh = jQuery("#xh").val();
			
			if(xh == ""){
				alert("������дѧ�ţ�");
			}else{
				tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");
			}
		}

		
		$(function(){
	$(".college_title").toggle(function(){
		$(this).siblings().hide();
		$(this).children(".up").attr("class","down").text("չ��");
	return false;
	},function(){
		$(this).siblings().show();
		$(this).children(".down").attr("class","up").text("����");
	})
	$(".college_title a:eq()").toggle(function(){
		$(this).siblings().hide();
		$btn.attr("class","down");
		$btn.text("չ��");
	return false;
	},function(){
		$(".demo_college .con").show();
		$btn.attr("class","up");
		$btn.text("����");
	})
	$(".demo_college li .college_li").hover(function(){
		$(this).next().show();
		$(this).parent().css("position","relative")
	},function(){
		$(this).next().hide();
		$(this).parent().css("position","")
	})
	$(function(){
	$(".list_xxxx li").hover(function(){
		$(this).children(".list_xxxx_downmenu").show();
		$(this).css("position","relative")
	},function(){
		$(this).children(".list_xxxx_downmenu").hide();
		$(this).css("position","")
	})
	$(".list_xxxx_downmenu").hover(function(){
		$(this).show();
		$(this).prev().attr("class","hover")
	},function(){
		$(this).hide();
		$(this).prev().removeClass("hover")
	});
})
})
		//�ƶ����λ��
		function moveTable(tabid) {
			jQuery("#"+tabid+"_a").click(function(){
				jQuery("#"+tabid).prependTo("#demo_xxxx");
				var tbody = jQuery("#"+tabid).find("tbody").attr("id");
				document.getElementById(tbody).style.display="";
				return false;
			});
			
		}
		
		jQuery(function(){
			onShow();
			});

		//��ӡ����
		function printZxsxx(){
			var xh = jQuery('#xh').val();;
			var url="xsxx_tygl.do?method=printJsp";
			url+="&xh="+xh;
			window.open(url);
		}
		
		</script>
	</head>
	<body>

		<html:form action="/xsxx_tygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- ������� -->
			<div style="height: 50px;">
				<div id="position-fixed" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx">${rs.xm } ��ѧ�ţ� ${rs.xh }��</span>
						<span class="wxts">��ܰ���ѣ� <span>�����������
								���Կ��ٶ�λ������Ҫ�鿴����Ϣ</span>
						</span>
					</div>
					<!-- ��ϸ��Ϣ��λ -->
					<div class="position_xxxx after" id="position-fixed">
						<ul class="list_xxxx">
							<li>
								<a href="#tab_jbl" class="smooth">ѧ����Ϣ</a>
								<div class="list_xxxx_downmenu" style="display: none">
									<dl>
										<dd id="tab_jbxx_a"
											style="text-align: left; text-indent: 1em;">
											<a href="#tab_jbxx" class="smooth">������Ϣ</a>
										</dd>
										<dd id="tab_lxfs_a"
											style="text-align: left; text-indent: 1em;">
											<a href="#tab_lxfs" class="smooth">��ϵ��ʽ</a>
										</dd>
										<dd id="tab_jtcyxx_a"
											style="text-align: left; text-indent: 1em;">
											<a href="#tab_jtcyxx" class="smooth">��ͥ��Ա��Ϣ</a>
										</dd>
										<dd id="tab_qtxx_a"
											style="text-align: left; text-indent: 1em;">
											<a href="#tab_qtxx" class="smooth">������Ϣ</a>
										</dd>
										<dd id="tab_szxx_a"
											style="text-align: left; text-indent: 1em;">
											<a href="#tab_szxx" class="smooth">˼����Ϣ</a>
										</dd>
									</dl>
								</div>
							</li>
							<!-- ҵ��ģ������ -->
							<logic:present name="ckList">
								<logic:equal value="true" name="mksize">
									<logic:iterate id="ckobj" name="ckList" indexId="index"
										offset="0" length="6">
										<li id="tab_${ckobj.gnmk}_a">
											<!-- һ��ҵ��ģ�� -->
											<a href="#tab_${ckobj.gnmk}" class="smooth">${ckobj.gnmk }</a>
											<!-- ����ģ��˵� -->
											<logic:iterate id="ywlb" name="ywlbList">
												<logic:equal value="${ywlb.dm}" name="ckobj" property="gnmk">
													<div class="list_xxxx_downmenu" style="display: none">
														<dl>
															<logic:iterate id="y" name="ywlb" property="xsmc">
																<dd id="tab_jbxx_a"
																	style="text-align: left; text-indent: 1em;">
																	<a href="#tab_${y }">${y }</a>
																</dd>
															</logic:iterate>
														</dl>
													</div>
												</logic:equal>
											</logic:iterate>
										</li>
									</logic:iterate>
									<li id="tab_more_a">
										<a href="#tab_more">����</a>
										<div class="list_xxxx_downmenu" style="display: none">
											<dl>
												<logic:iterate id="ckobj" name="ckList" indexId="index"
													offset="6">
													<dd id="tab_jbxx_a"
														style="text-align: left; text-indent: 1em;">
														<a href="#tab_${ckobj.gnmk }" class="smooth">${ckobj.gnmk }</a>
													</dd>
												</logic:iterate>
											</dl>
										</div>
									</li>
								</logic:equal>
								<logic:notEqual value="true" name="mksize">
									<logic:iterate id="ckobj" name="ckList">
										<li id="tab_${ckobj.gnmk}_a">
											<!-- һ��ҵ��ģ�� -->
											<a href="#tab_${ckobj.gnmk}" class="smooth">${ckobj.gnmk }</a>
											<!-- ����ģ��˵� -->
											<logic:iterate id="ywlb" name="ywlbList">
												<logic:equal value="${ywlb.dm}" name="ckobj" property="gnmk">
													<div class="list_xxxx_downmenu" style="display: none">
														<dl>
															<logic:iterate id="y" name="ywlb" property="xsmc">
																<dd id="tab_jbxx_a"
																	style="text-align: left; text-indent: 1em;">
																	<a href="#tab_${y }">${y }</a>
																</dd>
															</logic:iterate>
														</dl>
													</div>
												</logic:equal>
											</logic:iterate>
										</li>
									</logic:iterate>
								</logic:notEqual>
							</logic:present>

						</ul>
					</div>
				</div>
			</div>
			<!-- ������ű� -->
			<script type="text/javascript">
jQuery(function(){
	
jQuery('#position-fixed').find(".list_xxxx li").hover(function(){

		jQuery(this).children(".list_xxxx_downmenu").show();
		jQuery(this).css("position","relative")
	},function(){
		jQuery(this).children(".list_xxxx_downmenu").hide();
		jQuery(this).css("position","")
	})
	jQuery('#position-fixed').find(".list_xxxx_downmenu").hover(function(){
		jQuery(this).show();
		jQuery(this).prev().attr("class","hover")
	},function(){
		jQuery(this).hide();
		jQuery(this).prev().removeClass("hover")
	})
	jQuery(".list_xxxx_downmenu dd").live('click',function(){
		jQuery(this).parent().parent().hide();
	})
})
</script>
			<!-- ������Ϣ -->
			<logic:equal value="stu" name="userType">
				<div class="demo_xxxx" style="margin-top: 20px;" id="demo_xxxx">
			</logic:equal>
			<logic:notEqual value="stu" name="userType">
				<div class="demo_xxxx" style="margin-top: 20px;" id="demo_xxxx">
			</logic:notEqual>
			
			<h3 class="college_title" style="margin-bottom:5px;">
				<span class="title_name" id="tab_jbl">ѧ����Ϣ</span>
			</h3>
			
			<table width="100%" border="0" style="margin-bottom: 5px"
				class="formlist" id="tab_jbxx">
				<!-- ѧ��������Ϣ begin-->
				
				<thead>
					<tr onclick="" style="cursor: hand;">
						<th colspan="5">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>

				<tbody id="hi_jbxx">
					<tr>
						<th width="13%">
							ѧ��
						</th>
						<td width="30%">
							${rs.xh }
							<input type="hidden" name="xh" id="xh" value="${rs.xh }" />
						</td>
						<th width="13%">
							����
						</th>
						<td>
							${rs.xm }
						</td>
						<td rowspan="5" class="nohover"
							style="vertical-align: middle; text-align: left; width: 115px;">
							<div id="stuImg" class="open_img"
								style="margin-left: 0px; margin-top: 0px; height: 130px">
								<img style="width: 100px; height: 130px;"
									src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}" border="0">
							</div>
						</td>
					</tr>
					<tr>
						<th width="13%">
							�Ա�
						</th>
						<td width="30%">
							${rs.xb }
						</td>
						<th width="13%">
							��������
						</th>
						<td>
							${rs.csrq }
						</td>
					</tr>

					<tr>
						<th width="13%">
							�꼶
						</th>
						<td width="30%">
							${rs.nj }
						</td>
						<th>
							ѧ��(��)
						</th>
						<td colspan="">
							${rs.xz }
						</td>
					</tr>
					<tr>
						<th>

							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							${rs.xymc }
						</td>
						<th>
							������ò
						</th>
						<td>
							${rs.zzmmmc }
						</td>
					</tr>
					<tr>
						<th>
							רҵ
						</th>
						<td>
							${rs.zymc }
						</td>

						<th>
							����
						</th>
						<td>
							${rs.mzmc }
						</td>

					</tr>
					<tr>
						<th>
							�༶
						</th>
						<td colspan="">

							${rs.bjmc }
						</td>
						<th>
							ѧ���춯
						</th>
						<td align="left" colspan="2">
							${rs.zd2 }
						</td>
					</tr>

					<tr>
						<th>
							��ѧʱ��
						</th>
						<td colspan="">
							${rs.rxrq }
						</td>
						<th>
								��ҵʱ��
							</th>
							<td colspan="2">
							${rs.bysj }
							</td>
							</tr>
						<tr>
							<th>
							���֤������
							</th>
							<td align="left" >
								�������֤
							</td>
						<th>
							���֤��
						</th>
						<td align="left" colspan="2">
							${rs.sfzh}
						</td>
					</tr>
						<tr>
							<th>
							����
							</th>
							<td align="left" >
								${rs.gj}
							</td>
							<th>
							������
							</th>
							<td colspan="2">
							${rs.csd}
							</td>
						</tr>
					<tr>
					
						<th>
							����
						</th>
						<td colspan="4">
							${rs.jgmc }
						</td>
					</tr>

					<tr>
						<th>
							�������ڵ�
						</th>
						<td colspan="4">
							${rs.hkszdmc }
						</td>
					</tr>
					<tr>
						<th>
							��Դ��(�߿�ʱ�������ڵ�)
						</th>
						<td colspan="4">
							${rs.sydqmc }
						</td>
					</tr>
				</tbody>

			</table>
				<a name="tab_lxfs"></a>
			<table width="100%" style="margin-bottom: 5px" border="0"
				class="formlist" id="tab_lxfs">
				<!-- ѧ����ϵ��ʽ begin-->
				<thead>
					<tr onclick="" style="cursor: hand;">
						<th colspan="5">
							<span>��ϵ��ʽ</span>
						</th>
					</tr>
				</thead>
				<tbody id="hi_lxfs">
					<tr>
						<th width="13%">
							��ϵ�绰
						</th>
						<td colspan="" width="30%">
							${rs.sjhm }

						</td>
						<th width="13%">
							��������
						</th>
						<td align="left" colspan="2">
							${rs.dzyx }
						</td>
					</tr>
					<tr>
						<th>
							QQ����
						</th>
						<td>
							${rs.qqhm }
						</td>
						<th>
							��ͥ�绰
						</th>
						<td align="left" colspan="2">
							${rs.jtdh }
						</td>
					</tr>
					<tr>
						<th>
							��ͥ�ʱ�
						</th>
						<td>
							${rs.jtyb }
						</td>
						<th>
							��ͥ��ַ
						</th>
						<td align="left" colspan="2">
							${rs.jtszd }
						</td>
					</tr>
					<tr>
							<th>
								סַ�ʱ�
							</th>
							<td>
							${rs.zd1 }
							</td>
							<th>
							��סַ
							</th>
							<td align="left" colspan="2">
							${rs.xwzsxxdz }
							</td>
							</tr>
				</tbody>
			</table>


			<table style="margin-bottom: 5px" width="100%" border="0"
				class="formlist" id="tab_jtcyxx">
				<!-- ѧ����ͥ��Ա��Ϣ begin-->
				<thead>
					<tr onclick="" style="cursor: hand;">
						<th colspan="5">
							<span>��ͥ��Ա��Ϣ</span>
						</th>
					</tr>
				</thead>

				<tbody id="hi_jtcyxx">
					<tr>
						<th width="13%">
							<div align="center">
								����
							</div>
						</th>
						<th>
							<div align="center">
								�뱾�˹�ϵ
							</div>
						</th>
						<th>
							<div align="center">
								������λ����ַ
							</div>
						</th>
						<th>
							<div align="center">
								��λ�绰
							</div>
						</th>
						<th>
							<div align="center">
								���˵绰
							</div>
						</th>
					</tr>
					<tr>
						<td align="center">
							${rs.jtcy1_xm }
						</td>
						<td align="center">
							${rs.jtcy1_gx }
						</td>
						<td align="center">
							${rs.jtcy1_gzdz }
						</td>
						<td align="center">
							${rs.jtcy1_lxdh2 }
						</td>
						<td align="center">
							${rs.jtcy1_lxdh1}
						</td>
					</tr>
					<tr>
						<td align="center">
							${rs.jtcy2_xm }
						</td>
						<td align="center">
							${rs.jtcy2_gx }
						</td>
						<td align="center">
							${rs.jtcy2_gzdz }
						</td>
						<td align="center">
							${rs.jtcy2_lxdh2 }
						</td>
						<td align="center">
							${rs.jtcy2_lxdh1}
						</td>
					</tr>
					<tr>
						<td align="center">
							${rs.jtcy3_xm }
						</td>
						<td align="center">
							${rs.jtcy3_gx }
						</td>
						<td align="center">
							${rs.jtcy3_gzdz }
						</td>
						<td align="center">
							${rs.jtcy3_lxdh2 }
						</td>
						<td align="center">
							${rs.jtcy3_lxdh1}
						</td>
					</tr>
				</tbody>

			</table>
			<div>
				<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="tab_qtxx">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx">
						<tr>
							<th>
								��������
							</th>
							<td align="left">
								${rs.yhmc }
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								${rs.yhkh}
							</td>
						</tr>
						<tr>
							<th width="13%">
								����ƴ��
							</th>
							<td width="30%">
								${rs.xmpy }
							</td>
							<th width="13%">
								������
							</th>
							<td align="left" colspan="2">
								${rs.cym }
							</td>

						</tr>
						<tr>
							<th>
								���(cm)
							</th>
							<td align="left">
								${rs.sg }
							</td>
							<th>
								����(kg)
							</th>
							<td colspan="2">
								${rs.tz}
							</td>
						</tr>
						<tr>
							<th>
								�س�
							</th>
							<td>
								${rs.tc }
							</td>
							<th>
								����״��
							</th>
							<td colspan="2">
								${rs.jkzk }
							</td>
						</tr>
						<tr>
							<th>
								ѧ�����
							</th>
							<td colspan="">
								${rs.pyccmc }
							</td>
							<th>
								�Ƿ��߶���
							</th>
							<td align="left" colspan="2">
								${rs.sfzd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td>
								${rs.kslbmc }
							</td>
							<th>
								��ѧ��ʽ
							</th>
							<td colspan="2">
								${rs.rxfsmc }
							</td>
						</tr>
						<tr>
							<th>
								������ʽ
							</th>
							<td>
								${rs.pyfsmc }
							</td>
							<th>
								��������
							</th>
							<td colspan="2">
								${rs.zd5}
							</td>
						</tr>
						
						<tr>
							<th>
								�߿�������
							</th>
							<td>
							${rs.ksh}
									</td>
									<th>
								���б�ҵѧУ
							</th>
							<td colspan="2">
								${rs.rxqdw}
							</td>
							</tr>
							
							<tr>
							<th>
								����״̬
							</th>
							<td>
							${rs.sfjh }
									</td>
									<th>
								�ڽ�����
							</th>
							<td colspan="2">
								${rs.zjdm }
							</td>
							</tr>
							
							<tr>
							<th>
								Ѫ��
							</th>
							<td>
							${rs.xx}
									</td>
									<th>
								��ͥ�ṹ
							</th>
							<td colspan="2">
								${rs.jtcygc }
							</td>
							</tr>
							
							<tr>
							<th>
								�˳�����
							</th>
							<td>
							${rs.ccqj}
									</td>
									<th>
								���ΰ�ɲ�
							</th>
							<td colspan="2">
								${rs.zw}
							</td>
							</tr>
						
						<tr>
							<th>
								��ע
							</th>
							<td colspan="4">
								<div style="word-break: break-all; width: 97%">
									${rs.bz }
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="tab_szxx">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>˼����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx">

						<tr>
							<th width="13%">
								<div align="center">
									����Ա����
								</div>
							</th>
							<th>
								<div align="center">
									����Աְ����
								</div>
							</th>
							<th>
								<div align="center">
									����Ա��ϵ�绰
								</div>
							</th>

						</tr>
						<logic:notEmpty name="fdyList">
							<logic:iterate id="fdy" name="fdyList">
								<tr>
									<td align="center">
										${fdy.xm }
									</td>
									<td align="center">
										${fdy.zgh }
									</td>
									<td align="center">
										${fdy.lxdh }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fdyList">
							<tr>
								<td colspan="3" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
						<tr>
							<th width="13%">
								<div align="center">
									����������
								</div>
							</th>
							<th>
								<div align="center">
									������ְ����
								</div>
							</th>
							<th>
								<div align="center">
									��������ϵ�绰
								</div>
							</th>

						</tr>
						<logic:notEmpty name="bzrList">
							<logic:iterate id="bzr" name="bzrList">
								<tr>
									<td align="center">
										${bzr.xm }
									</td>
									<td align="center">
										${bzr.zgh }
									</td>
									<td align="center">
										${bzr.lxdh }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bzrList">
							<tr>
								<td colspan="3" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
			</div>
			<!--  ѧ����Ϣend -->
			<!-- ҵ��ģ����ϸ��Ϣ�б� -->
			<logic:present name="ckList">
				<logic:iterate id="ckmxobj" name="ckList">
					<table width="100%" border="0" style="margin-bottom: 5px"
						class="formlist" id="tab_${ckmxobj.gnmk }">
				
						<h3 class="college_title" style="margin-bottom:5px;">
							<span class="title_name">${ckmxobj.gnmk }</span>
						</h3>
						
						<tbody id="hi_${ckmxobj.gnmk }">
							<tr>
								<td colspan="5" class="ywmkclass" id="td_${ckmxobj.gnmk }"
									style="padding: 2px 0px 0px 0px;border: none;"></td>
							</tr>
						</tbody>
					</table>
				</logic:iterate>
			</logic:present>

			<logic:notPresent name="sfxsgban">
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="�ر�" onclick="Close()" type="button"
										id="buttonClose">
										�� ��
									</button>
									<button type="button" name="ѧ���ǼǱ��ӡ" onclick="printZxsxx();return false;" id="btn_dy">ѧ����Ϣ��ӡ</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<script type="text/javascript">
		            jQuery(".smooth").click(function(){
		                var href = jQuery(this).attr("href");
		                var pos = jQuery(href).offset().top;
		                jQuery("html,body").animate({scrollTop: pos-70}, 400);
		                return false;
		            });
	       		 </script>
			</logic:notPresent>
			</div>
			</html:form>
				
	</body>
</html>
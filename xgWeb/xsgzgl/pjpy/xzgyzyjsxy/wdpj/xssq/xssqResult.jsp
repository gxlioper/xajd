<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʾ�ҵ�������ϸ
		function showLnzcInfo(pkValue){
		
			var ie = "ie";
			
			
			var url="general_pjpy_wdpj_ajax.do?method=showLnzcInfo";
			
			//��������
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
				"pkValue":pkValue
			};
		  	
			jQuery("#div_detail").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_detail","400","386","true","","true","id");
			});
		}
		
		function showWdpjView(xmdm){
		
			var ie = "ie";
			
			var url="general_pjpy_wdpj_ajax.do?method=showWdpjView";
			
			//��������
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
				"xmdm":xmdm
			};
		  	
			jQuery("#div_detail").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_detail","600","380","true","","true","id");
			});
		}
		</script>
	</head>
	<body onload="" ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��ҳ��չʾ�����������������Լ���������<font color="blue">�������</font>��<font color="blue">�۲���</font>��<br/>
				<logic:equal name="ckxx" value="pjjg">
				2.�������������<font color="blue">�鿴</font>��������ʾ<font color="blue">��ϸ������������</font>��<br/>
				</logic:equal>
				<logic:equal name="ckxx" value="zcjg">
				2.�����ʷ�۲��<font color="blue">�鿴</font>��������ʾ<font color="blue">��ϸ���ۺϷ����</font>��<br/>
				</logic:equal>
				</span>
			</p>
			<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ�����
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
			</div>
			
			<div class="tab" style="">
				<table class="formlist" width="99%">
				
					<logic:equal name="ckxx" value="pjjg">
						<!-- �������� begin -->
						<thead onclick="hiddenMk('mk_xmxx')">
							<tr style="height:22px" style="cursor:hand">
								<th colspan="6">
									<span>����������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="mk_xmxx">
							<tr>
								<th width="12%">
									<div align="center" width="12%">
										��Ŀ����
									</div>
								</th>
								<th width="12%">
									<div align="center" width="12%">
										��Ŀ����
									</div>
								</th>
								<th  width="12%">
									<div align="center" width="12%">
										��Ŀ����
									</div>
								</th>
								<th  width="35%">
									<div align="center">
										�������
									</div>
								</th>
								<th width="8%">
									<div align="center" width="8%">
										��ý��
									</div>
								</th>
								<th width="8%">
									<div align="center" width="8%">
										����
									</div>
								</th>
							</tr>
							<logic:notEmpty name="xssqByZqInfo">
								<logic:iterate name="xssqByZqInfo" id="bzqRs">
									<tr align="center">
										<td>
											${bzqRs.xmmc }
										</td>
										<td>
											${bzqRs.xmlx }
										</td>
										<td>
											${bzqRs.xmxz }
										</td>
										<td>
											${bzqRs.shlc }
										</td>
										<td>
											${bzqRs.xmje }
										</td>
										<td>
											<a href="#" onclick="showWdpjView('${bzqRs.xmdm}');return false;"><font color="blue">�鿴</font></a>
										</td>
									</tr>
								</logic:iterate>						
							</logic:notEmpty>
							<logic:empty name="zcxxByZqInfo">
								<tr>
									<td align="center" style="vertical-align: center;height:100px" colspan="6">
										
										����������Ϣ
									</td>
								</tr>
							</logic:empty>
						</tbody>
						<!-- �������� end -->
					</logic:equal>
					<logic:equal name="ckxx" value="zcjg">
					<!-- �����۲� begin -->
					<thead onclick="hiddenMk('mk_bczc')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>�����۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_bczc">
						<tr>
							<td colspan="6">
							<div style="overflow-x :auto;overflow-y:hidden;width:780px;height:100px" >
								<table class="formlist" width="100%"  >
									<logic:notEmpty name="zcxxByZqInfo">
										<logic:iterate name="zcxxByZqInfo" id="zcxxByzq">
										<tr>
											<logic:iterate name="zcxxByzq" id="zcxx">
												<td>
												${zcxx}
												</td>
											</logic:iterate>	
										</tr>
										</logic:iterate>						
									</logic:notEmpty>
									<logic:empty name="zcxxByZqInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												�����۲���Ϣ
											</td>
										</tr>
									</logic:empty>
								</table>
							</div>
							</td>
						</tr>
					</tbody>
					<!-- �����۲� end -->
					</logic:equal>
					
					<logic:equal name="ckxx" value="pjjg">
					<!-- ��ʷ���� begin -->
					<thead onclick="hiddenMk('mk_lsxx')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>��ʷ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lsxx" >
						<tr>
							<td colspan="6">
								<table class="formlist" width="100%" >
									<logic:notEmpty name="xssqInfo">
										<tr>
										<td>
											<div align="center">
												���ʱ��
											</div>
										</td>
										<td>
											<div align="center">
												��Ŀ����
											</div>
										</td>
										<td>
											<div align="center">
												��Ŀ����
											</div>
										</td>
										<td>
											<div align="center">
												��ý��
											</div>
										</td>
									</tr>
									<logic:iterate name="xssqInfo" id="xssq">
										<tr>
											<td>
												<div align="center">
													${xssq.hdsj}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmmc}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmlx}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmje}
												</div>
											</td>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="xssqInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												����ʷ������Ϣ
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<!-- ��ʷ���� end -->
					</logic:equal>
					
					<logic:equal name="ckxx" value="zcjg">
					<!-- ��ʷ�۲� begin -->
					<thead onclick="hiddenMk('mk_lszc')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>��ʷ�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lszc" >
						<tr>
							<td colspan="6">
								<table class="formlist" width="100%">
									<logic:notEmpty name="xszcInfo">
										<logic:iterate name="xszcInfo" id="xszc" indexId="titIndex" >
										<tr>
											<logic:iterate name="xszc" id="zcxx" length="5" indexId="index">
												
												<logic:notEqual name="titIndex" value="0">
													<logic:equal name="index" value="4">
													<td>
													<a href="#" onclick="showLnzcInfo('${zcxx}');return false;"><font color="blue">�鿴</font></a>
													</td>
													</logic:equal>
												</logic:notEqual>
												<logic:equal name="titIndex" value="0">
													<logic:equal name="index" value="4">
													<td>
													${zcxx}
													</td>
													</logic:equal>
												</logic:equal>
												
												
												<logic:notEqual name="index" value="4">
												<td>
												${zcxx}
												</td>
												</logic:notEqual>
											</logic:iterate>	
										</tr>
										</logic:iterate>						
									</logic:notEmpty>
									<logic:empty name="xszcInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												����ʷ�۲���Ϣ
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<!-- ��ʷ�۲� end -->
					</logic:equal>
				</table>
			</div>
			
			<div id="div_detail"  style="display:none">
				
			</div>
			<!-- �ҵ����������� end-->
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
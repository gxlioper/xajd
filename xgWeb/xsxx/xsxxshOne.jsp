<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>	
	<script>
		function initXgzd(){
			//�Ѿ��޸Ĺ����ֶα�Ϊ��ɫ
			var yxgzd = val('yxgzd');
			var yxgzdArr = yxgzd.split('@!!@');
			for(var i=0; i<yxgzdArr.length; i++){
				if(yxgzdArr[i] != null && yxgzdArr[i] != ''){					
					document.getElementById("f_"+yxgzdArr[i]).className = "red";					
				}
			}
		}
	</script>
</head>
	<body onload="initXgzd()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="yxgzd" value="${xgzd}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>		
			<div class="tab">	
			<table class="formlist" id="rsTable">
				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('jbxxB').style.display=(document.getElementById('jbxxB').style.display==''?'none':'');document.getElementById('jbxxT').style.display=(document.getElementById('jbxxT').style.display==''?'none':'')">
							<span>������Ϣ�޸�</span>
						</th>
					</tr>			
					<tr id="jbxxT">
						<th width="15%">
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
						<th width="15%">
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
					</tr>
				</thead>
				<tbody id="jbxxB">
					<tr>
						<th id="f_xh">ѧ��</th>
						<td>
							<bean:write name="newValue" property="xh"/>		 
							<html:hidden property="xh" name="oldValue"/>							
						</td>
						<td>
							<bean:write name="oldValue" property="xh"/>
						</td>
						<th id="f_xm">����</th>
						<td>
							<bean:write name="newValue" property="xm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xm"/>
						</td>
					</tr>
					<tr>
						<th id="f_xb">�Ա�</th>
						<td>
							<bean:write name="newValue" property="xb"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xb"/>
						</td>
						<th id="f_csrq">��������</th>
						<td>
							<bean:write name="newValue" property="csrq"/>
						</td>
						<td>
							<bean:write name="oldValue" property="csrq"/>
						</td>
					</tr>
					<tr>
						<th id="f_mz">����</th>
						<td>
							${newValue.mzmc}							
						</td>
						<td>
							${oldValue.mzmc}
						</td>
						<th id="f_zzmm">������ò</th>
						<td>
							${newValue.zzmmmc}
						</td>
						<td>
							${oldValue.zzmmmc}
						</td>
					</tr>
					<tr>
						<th id="f_sfzh">���֤��</th>
						<td>
							<bean:write name="newValue" property="sfzh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzh"/>
						</td>
						<th id="f_pycc">
							�������
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyccmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyccmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pycc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pycc"/>
							</td>
						</logic:notEqual>									
					</tr>
					<tr>
						<th id="f_jg">
							����
						</th>			
						<td>
							<bean:write name="newValue" property="jg"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jg"/>
						</td>
						<th id="f_syd">
							��Դ����
						</th>
						<td>
							<bean:write name="newValue" property="syd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="syd"/>
						</td>				
					</tr>
				</tbody>

				<thead>			
				<tr>
					<th colspan="6" onclick="document.getElementById('xjxxB').style.display=(document.getElementById('xjxxB').style.display==''?'none':'');document.getElementById('xjxxT').style.display=(document.getElementById('xjxxT').style.display==''?'none':'')">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>			
				<tr id="xjxxT">
					<th>
						�ֶ���
					</th>
					<th>
						�޸ĺ�
					</th>
					<th>
						�޸�ǰ
					</th>
					<th>
						�ֶ���
					</th>
					<th>
						�޸ĺ�
					</th>
					<th>
						�޸�ǰ
					</th>
				</tr>
				</thead>

				<tbody id="xjxxB">
					<tr>
						<th id="f_nj">�꼶</th>
						<td>
							<bean:write name="newValue" property="nj"/>
						</td>			
						<td>
							<bean:write name="oldValue" property="nj"/>
						</td>
						<th id="f_xz">ѧ��(��)</th>
						<td>
							<bean:write name="newValue" property="xz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xz"/>
						</td>				
					</tr>
		
					<tr>
						<th id="f_xydm"><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="newValue" property="xymc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="xymc"/>
						</td>
						<th id="f_sfzc">�Ƿ�ע��</th>
						<td>
							<bean:write name="newValue" property="sfzc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzc"/>
						</td>				
					</tr>
					
					<tr>
						<th id="f_zydm">רҵ</th>
						<td>
							<bean:write name="newValue" property="zymc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="zymc"/>
						</td>
						<th id="f_sfzd">�Ƿ��߶�</th>
						<td>
							<bean:write name="newValue" property="sfzd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzd"/>
						</td>
					</tr>
					<tr>
						<th id="f_bjdm">�༶</th>
						<td>
							<bean:write name="newValue" property="bjmc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="bjmc"/>
						</td>
						<th id="f_sfzx">�Ƿ���У</th>
						<td>
							<bean:write name="newValue" property="sfzx"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sfzx"/>
						</td>
					</tr>	
					<tr>
						<th id="f_xjztm">ѧ��״̬</th>
						<td>
							<bean:write name="newValue" property="xjztm"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="xjztm"/>
						</td>
					    <th id="f_sfbys">�Ƿ��ҵ��</th>
						<td>
							<bean:write name="newValue" property="sfbys"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="sfbys"/>
						</td>
					</tr>
					<tr>
						<th id="f_rxrq">��ѧʱ��</th>
						<td>
							<bean:write name="newValue" property="rxsj"/>
							${newValue.rxrq }
						</td> 
						<td>
							<bean:write name="oldValue" property="rxsj"/>
							${oldValue.rxrq }
						</td>
					    <th id="f_sfyby">�Ƿ��ҵ</th>
						<td>
							<bean:write name="newValue" property="sfyby"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="sfyby"/>
						</td>
					</tr>
					<tr>
						<th id="f_byny">��ҵʱ��</th>
						<td>
							<bean:write name="newValue" property="byny"/>
						</td> 
						<td>
							<bean:write name="oldValue" property="byny"/>
						</td>
					    <th></th>
						<td>
							
						</td> 
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('lxfsxxB').style.display=(document.getElementById('lxfsxxB').style.display==''?'none':'');document.getElementById('lxfsxxT').style.display=(document.getElementById('lxfsxxT').style.display==''?'none':'')">
							<span>��ϵ��ʽ��֤��</span>
						</th>
					</tr>			
					<tr id="lxfsxxT" style="display: none">
						<th>
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
						<th>
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
					</tr>
				</thead>

				<tbody id="lxfsxxB" style="display: none">
					<tr>
						<th id="f_lxdh">
							��ϵ�绰
						</th>
						<td>
							<bean:write name="newValue" property="lxdh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="lxdh"/>
						</td>
						<th id="f_sjhm">
							�ֻ�����
						</th>			
						<td>
							<bean:write name="newValue" property="sjhm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sjhm"/>
						</td>
					</tr>
					<tr>
						<th id="f_qqhm">
							QQ����
						</th>
						<td>
							<bean:write name="newValue" property="qqhm"/>
						</td>
						<td>
							<bean:write name="oldValue" property="qqhm"/>
						</td>
						<th id="f_dzyx">
							��������
						</th>			
						<td>
							<bean:write name="newValue" property="dzyx"/>
						</td>
						<td>
							<bean:write name="oldValue" property="dzyx"/>
						</td>
					</tr>
					<tr>
						<th id="f_yhdm">
							��������
						</th>
						<td>
							<bean:write name="newValue" property="yhmc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="yhmc"/>
						</td>
						<th id="f_yhkh">
							���п���
						</th>			
						<td>
							<bean:write name="newValue" property="yhkh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="yhkh"/>
						</td>
					</tr>
					<tr>
						<th id="f_kh">
							һ��ͨ��
						</th>
						<td>
							<bean:write name="newValue" property="kh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="kh"/>
						</td>
						<th>
							
						</th>			
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('qtxxB').style.display=(document.getElementById('qtxxB').style.display==''?'none':'');document.getElementById('qtxxT').style.display=(document.getElementById('qtxxT').style.display==''?'none':'')">
							<span>������Ϣ</span>
						</th>
					</tr>			
					<tr id="qtxxT" style="display: none">
						<th>
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
						<th>
							�ֶ���
						</th>
						<th>
							�޸ĺ�
						</th>
						<th>
							�޸�ǰ
						</th>
					</tr>
				</thead>

				<tbody id="qtxxB" style="display: none">
					<tr>
						<th id="f_xmpy">����ƴ��</th>
						<td>
							<bean:write name="newValue" property="xmpy"/>				
						</td>			
						<td>
							<bean:write name="oldValue" property="xmpy"/>
						</td>
						<th id="f_cym">������</th>
						<td>
							<bean:write name="newValue" property="cym"/>
						</td>
						<td>
							<bean:write name="oldValue" property="cym"/>
						</td>
					</tr>
					
					<tr>
						<th id="f_sg">���(cm)</th>
						<td>
							<bean:write name="newValue" property="sg"/>
						</td>
						<td>
							<bean:write name="oldValue" property="sg"/>
						</td>
						<th id="f_tz">����(kg)</th>
						<td>
							<bean:write name="newValue" property="tz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="tz"/>
						</td>
					</tr>
					<tr>
						<th id="f_tc">�س�</th>
						<td>
							<bean:write name="newValue" property="tc"/>
						</td>
						<td>
							<bean:write name="oldValue" property="tc"/>
						</td>
						<th id="f_kslb">
							�������
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="kslbmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="kslbmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="kslb"/>
							</td>
							<td>
								<bean:write name="oldValue" property="kslb"/>
							</td>
						</logic:notEqual>						
					</tr>			
					<tr>
						<th id="f_rxfs">
							��ѧ��ʽ
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="rxfsmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="rxfsmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="rxfs"/>
							</td>
							<td>
								<bean:write name="oldValue" property="rxfs"/>
							</td>
						</logic:notEqual>
						
						<th id="f_pyfs">
							������ʽ
						</th>
						<logic:equal value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyfsmc"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyfsmc"/>
							</td>
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							<td>
								<bean:write name="newValue" property="pyfs"/>
							</td>
							<td>
								<bean:write name="oldValue" property="pyfs"/>
							</td>
						</logic:notEqual>						
					</tr>
					<tr>
						<th id="f_hkszd">
							�������ڵ�
						</th>
						<td>
							<bean:write name="newValue" property="hkszd"/>
						</td>
						<td>
							<bean:write name="oldValue" property="hkszd"/>
						</td>
						<th>
							
						</th>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</tbody>

				<thead>			
					<tr>
						<th colspan="6" onclick="document.getElementById('jtjbxxB').style.display=(document.getElementById('jtjbxxB').style.display==''?'none':'');document.getElementById('jtjbxxT').style.display=(document.getElementById('jtjbxxT').style.display==''?'none':'')">
							<span>��ͥ��Ϣ</span>
						</th>
					</tr>	
				</thead>

				<tbody id="jtjbxxB" style="display: none">
					<tr ><td colspan="6" width="100%">
						<table class="datalist" width="100%">	
							<thead>	
								<tr id="jtjbxxT" style="display: none">
									<th width="15%">
										�ֶ���
									</th>
									<th>
										�޸ĺ�
									</th>
									<th>
										�޸�ǰ
									</th>
									<th width="15%">
										�ֶ���
									</th>
									<th>
										�޸ĺ�
									</th>
									<th>
										�޸�ǰ
									</th>
								</tr>
							</thead>
							<tbody>		
								<tr>
									<th id="f_jtszd">
									��ͥ��ַ
									</th>
									<td>
										<bean:write name="newValue" property="jtszd"/>
									</td>
									<td>
										<bean:write name="oldValue" property="jtszd"/>
									</td>
									<th>
									</th>
									<td>
									</td>
									<td>
									</td>
								</tr>		
								<tr>
									<th id="f_lxdh1">
										��ͥ�绰
									</th>
									<td>
										<bean:write name="newValue" property="lxdh1"/>
									</td>
									<td>
										<bean:write name="oldValue" property="lxdh1"/>
									</td>
									<th id="f_yb">
										��ͥ�ʱ�
									</th>
									<td>
										<bean:write name="newValue" property="yb"/>
									</td>
									<td>
										<bean:write name="oldValue" property="yb"/>
									</td>			
								</tr>
								<tr>
									<th id="f_jjzk">
										��ͥ�������
									</th>
									<td>
										<bean:write name="newValue" property="jjzk"/>
									</td>
									<td>
										<bean:write name="oldValue" property="jjzk"/>
									</td>
									<th>
										
									</th>
									<td>
										
									</td>
									<td>
										
									</td>			
								</tr>		
							</tbody>
						</table>
	
						<table class="datalist" width="100%">
							<thead>
								<tr title="���չ��������">
									<td colspan="6" style="cursor:hand" align="center"
										onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
											ѧ����ͥ��Ա��Ϣ1
									</td>
								</tr>				
							</thead>
							<tbody>
								<tr id="jt1" style="display: none">
									<td colspan="6">					
										<table width="100%" class="tbstyle">
											<thead>	
												<tr>
													<th width="15%">
														�ֶ���
													</th>
													<th>
														�޸ĺ�
													</th>
													<th>
														�޸�ǰ
													</th>
													<th width="15%">
														�ֶ���
													</th>
													<th>
														�޸ĺ�
													</th>
													<th>
														�޸�ǰ
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<th id="f_jtcy1_xm">
														��ͥ��Ա1����
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_xm"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_xm"/>
													</td>
													<th id="f_jtcy1_gx">
														�������ͥ��Ա1��ϵ
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_gx"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_gx"/>
													</td>
												</tr>
											
												<tr>
													<th id="f_jtcy1_nl">
														��ͥ��Ա1��������
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_nl"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_nl"/>
													</td>
													<th id="f_jtcy1_sfzh">
														��ͥ��Ա1���֤��
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_sfzh"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_sfzh"/>
													</td>
												</tr>
											
												<tr>
													<th id="f_jtcy1_mz">
														��ͥ��Ա1����
													</th>
													<td>
														${newValue.jtcy1_mzmc}
													</td>
													<td>
														${oldValue.jtcy1_mzmc}
													</td>
													<th id="f_jtcy1_zzmm">
														��ͥ��Ա1������ò
													</th>
													<td>
														${newValue.jtcy1_zzmmmc}
													</td>
													<td>
														${oldValue.jtcy1_zzmmmc}
													</td>
												</tr>
											
												<tr>
												<th id="f_jtcy1_zy">
													��ͥ��Ա1ְҵ
												</th>
												<td>
													<bean:write name="newValue" property="jtcy1_zy"/>
												</td>
												<td>
													<bean:write name="oldValue" property="jtcy1_zy"/>
												</td>
												<th id="f_jtcy1_zw">
													��ͥ��Ա1ְ��
												</th>
												<td>
													<bean:write name="newValue" property="jtcy1_zw"/>
												</td>
												<td>
													<bean:write name="oldValue" property="jtcy1_zw"/>
												</td>
												</tr>
												
												<tr>
													<th id="f_jtcy1_lxdh1">
														��ͥ��Ա1������λ�绰
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_lxdh1"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_lxdh1"/>
													</td>
													<th id="f_jtcy1_lxdh2">
														��ͥ��Ա1���˵绰
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_lxdh2"/>
													</td>
													<td>
														<bean:write name="oldValue" property="jtcy1_lxdh2"/>
													</td>			
												</tr>
											
												<tr>
													<th id="f_jtcy1_gzdz">
														��ͥ��Ա1������ַ
													</th>
													<td>
														<bean:write name="newValue" property="jtcy1_gzdz"/>
													</td>	
													<td>
														<bean:write name="oldValue" property="jtcy1_gzdz"/>
													</td>	
													<td></td>	
													<td></td>	
													<td></td>
												</tr>	
										</tbody>
									</table>
								</td>
							</tr>
						</table>

						<table class="datalist" width="100%">
							<thead>
								<tr title="���չ��������">
									<td colspan="6" style="cursor:hand" align="center"
										onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
											ѧ����ͥ��Ա��Ϣ2
									</td>
								</tr>				
							</thead>
							<tbody>
								<tr id="jt2" style="display: none">
								<td colspan="6">					
								<table width="100%" class="tbstyle">	
								<thead>	
									<tr>
										<th width="15%">
											�ֶ���
										</th>
										<th>
											�޸ĺ�
										</th>
										<th>
											�޸�ǰ
										</th>
										<th width="15%">
											�ֶ���
										</th>
										<th>
											�޸ĺ�
										</th>
										<th>
											�޸�ǰ
										</th>
									</tr>
								</thead>		
								<tbody>		
								<tr>
								<th id="f_jtcy2_xm">
									��ͥ��Ա2����
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_xm"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_xm"/>
								</td>
								<th id="f_jtcy2_gx">
									�������ͥ��Ա2��ϵ
								</th>

								<td>
									<bean:write name="newValue" property="jtcy2_gx"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_gx"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_nl">
									��ͥ��Ա2��������
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_nl"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_nl"/>
								</td>
								<th id="f_jtcy2_sfzh">
									��ͥ��Ա2���֤��
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_sfzh"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_sfzh"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_mz">
									��ͥ��Ա2����
								</th>
								<td>
									${newValue.jtcy2_mzmc}
								</td>
								<td>
									${oldValue.jtcy2_mzmc}
								</td>
								<th id="f_jtcy2_zzmm">
									��ͥ��Ա2������ò
								</th>
								<td>
									${newValue.jtcy2_zzmmmc}
								</td>
								<td>
									${oldValue.jtcy2_zzmmmc}
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_zy">
									��ͥ��Ա2ְҵ
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_zy"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_zy"/>
								</td>
								<th id="f_jtcy2_zw">
									��ͥ��Ա2ְ��
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_zw"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_zw"/>
								</td>
								</tr>
									
								<tr>
								<th id="f_jtcy2_lxdh1">
									��ͥ��Ա2������λ�绰
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_lxdh1"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_lxdh1"/>
								</td>
								<th id="f_jtcy2_lxdh2">
									��ͥ��Ա2���˵绰
								</th>
								<td>
									<bean:write name="newValue" property="jtcy2_lxdh2"/>
								</td>
								<td>
									<bean:write name="oldValue" property="jtcy2_lxdh2"/>
								</td>
								</tr>
								
								<tr>
								<th id="f_jtcy2_gzdz">
									��ͥ��Ա2������ַ
								</th>	
								<td>
									<bean:write name="newValue" property="jtcy2_gzdz"/>
								</td>		
								<td>
									<bean:write name="oldValue" property="jtcy2_gzdz"/>
								</td>	
								<td>		
								</td>
								<td>
								</td>
								<td>
								</td>
								</tr>
								</tbody>
								</table>
								</td>
								</tr>
							</tbody>
						</table>
					<table class="datalist" width="100%">
						<thead>
							<tr title="���չ��������">
								<td colspan="6" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</td>
							</tr>				
						</thead>
						<tbody>
						<tr id="jt3" style="display: none">
						<td colspan="6">					
						<table width="100%" class="tbstyle">
						<thead>	
							<tr>
								<th width="15%">
									�ֶ���
								</th>
								<th>
									�޸ĺ�
								</th>
								<th>
									�޸�ǰ
								</th>
								<th width="15%">
									�ֶ���
								</th>
								<th>
									�޸ĺ�
								</th>
								<th>
									�޸�ǰ
								</th>
							</tr>
						</thead>			
						<tbody>									
						<tr>
							<th id="f_jtcy3_xm">
								��ͥ��Ա3����
							</th>
							<td>
								<bean:write name="newValue" property="jtcy3_xm"/>
							</td>
							<td>
								<bean:write name="oldValue" property="jtcy3_xm"/>
							</td>
							<th id="f_jtcy3_gx">
								�������ͥ��Ա3��ϵ
							</th>
							<td>
								<bean:write name="newValue" property="jtcy3_gx"/>
							</td>
							<td>
								<bean:write name="oldValue" property="jtcy3_gx"/>
							</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_nl">
							��ͥ��Ա3��������
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_nl"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_nl"/>
						</td>
						<th id="f_jtcy3_sfzh">
							��ͥ��Ա3���֤��
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_sfzh"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_sfzh"/>
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_mz">
							��ͥ��Ա3����
						</th>
						<td>
							${newValue.jtcy3_mzmc}
						</td>
						<td>
							${oldValue.jtcy3_mzmc}
						</td>
						<th id="f_jtcy3_zzmm">
							��ͥ��Ա3������ò
						</th>
						<td>
							${newValue.jtcy3_zzmmmc}
						</td>
						<td>
							${oldValue.jtcy3_zzmmmc}
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_zy">
							��ͥ��Ա3ְҵ
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_zy"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_zy"/>
						</td>
						<th id="f_jtcy3_zw">
							��ͥ��Ա3ְ��
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_zw"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_zw"/>
						</td>			
						</tr>
							
						<tr>
						<th id="f_jtcy3_lxdh1">
						    ��ͥ��Ա3������λ�绰
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_lxdh1"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_lxdh1"/>
						</td>
						<th id="f_jtcy3_lxdh2">
							��ͥ��Ա3���˵绰
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_lxdh2"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_lxdh2"/>
						</td>
						</tr>
						
						<tr>
						<th id="f_jtcy3_gzdz">
							��ͥ��Ա3������ַ
						</th>
						<td>
							<bean:write name="newValue" property="jtcy3_gzdz"/>
						</td>
						<td>
							<bean:write name="oldValue" property="jtcy3_gzdz"/>
						</td>	
						<td>		
						</td>
						<td>
						</td>
						<td>
						</td>
						</tr>	
						</tbody>
						</table>
						</td>
						</tr>
						</tbody>
					</table>
					</td></tr>	
				</tbody>
			
			
			
			<tfoot>
				
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������; ��ɫ�����־���ֶ��޸Ĺ�</div>
		          <div class="btn">
		            <logic:equal value="yes" name="writeAble">
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=ͨ��')">
						ͨ �� 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=��ͨ��')">
						��ͨ�� 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="Close();return false;">
						�� �� 
					</button>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		</div>	
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");		
					Close();		
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//�����������
		function saveJbsz(){
			saveUpdate("/xgxt/pjpyJbsz.do?method=pjjbsz&doType=save","pjxn-pjxq-pjnd");
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��������-�����ۺ�����-������������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
			
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.������������Ǳ���������ʱ�䷶Χ��Ӱ�쵽֮���<font color="blue">��������</font>����������á�</br>
				2.���赱ǰΪ2011-2012ѧ�꣬���������Ľ���Ϊ2010-2011ѧ�꣬��ô���������ｫѧ������Ϊ<font color="blue">2010-2011</font>��</br>
				3.�۲�����ʱ���ȡ�Ա����á�</br>
				4.������Ŀ�������õ����ʱ�䣨����ĳѧ��ĳɼ��������ȡ�Ա����á�</br>
				5.����Ѿ�����˱���������ô�������޸ģ����ǽ���<font color="blue">��ʼ������</font>������
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none;">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>������������</p>
						</a>   	
					</div>
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<html:form action="/pjpyJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab" style="height:768px">		
				<!-- ����ʱ������ -->
				<table class="formlist" border="0" align="center" style="width: 100%;display: none">
					<!-- �������䷽ʽ -->
					<thead>
						<tr>
							<th colspan="2">
								<span>�������䷽ʽ</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<logic:iterate name="rsfpfsList" id="fpfs" indexId="index">
						<tr>
							<logic:equal name="index" value="0">
							<th align="right" width="30%" rowspan="${fpfsNum }">
								<font color="red">*</font>���䷽ʽ
							</th>
							</logic:equal>
							<td align="left">							
								<html:radio name="rs" property="rsszfs" value="${fpfs.en }"/>${fpfs.cn }
							</td>
						</tr>
						</logic:iterate>
					</tbody>
					<!-- ���������� -->
					<thead>
						<tr>
							<th colspan="2">
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<logic:notEmpty name="tjkNum">
							<tr>
								<th align="right" width="30%">
									������ѡ��
								</th>
								<td align="left">
									<div style="height: 200px;overflow:scroll;overflow-x:hidden">			
									<table>
										<tr>
											<logic:iterate name="tjkList" id="tjk" indexId="index">
												<td width="25%" height="35px">
													<logic:notEmpty name="tjk" property="tjdm">
														<input type="checkbox" name="tjdm" id="${tjk.tjdm }" value="${tjk.tjdm }"
														<logic:equal name="tjk" property="sfqy" value="yes">checked="true"</logic:equal>
														/>${tjk.tjmc }</br>
														<logic:notEqual name="tjk" property="tjms" value="��">
														��ע��${tjk.tjms }��
														</logic:notEqual>
													</logic:notEmpty>
												</td>
												<%if((index.intValue()+1)%4==0){%>
													<% out.print("</tr>"); %>
												<%}%> 
											</logic:iterate>
										</tr>
									</table>
									</div>		
								</td>
							</tr>
						</logic:notEmpty>
						<logic:empty name="tjkNum">
							<tr>
								<th align="right" width="30%">
									������ѡ��
								</th>
								<td align="left">
									��������Ԥ������������ϵ���������
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th align="right" width="30%">
								<font color="red">*</font>����ѧ��
							</th>
							<td align="left" width="70%">
								<html:select name="rs" property="pjxn" styleId="pjxn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>����ѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="pjxq" styleId="pjxq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>�������
							</th>
							<td align="left">
								<html:select name="rs" property="pjnd" styleId="pjnd">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveJbsz()" id="buttonSave">
										�� ��
									</button>
									
									<button type="button" style="display:none" onclick="refreshForm('pjpyJbsz.do?method=pjpyZhsz')" id="buttonBack">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
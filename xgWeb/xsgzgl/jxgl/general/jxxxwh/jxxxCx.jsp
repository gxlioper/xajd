<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxxx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��ѵ��Ϣά����Ҫ����ÿ�ξ�ѵ��<font color="blue">��������</font>��<font color="blue">��ѵ����</font>�Լ���ѵ��Ϣ��<font color="blue">����</font>��<font color="blue">ֹͣ</font>״̬��<br/>
				2.ÿ��<font color="blue">ֻ������һ��</font>��ѵ��Ϣ����ѡһ����¼<font color="blue">�޸�����</font>��������ѵ��Ϣ����<font color="blue">ֹͣ</font>��<br/>
				3.��ѡһ����¼����<font color="blue">��ѵ����</font>ά�������в�ѵ��Ա���޸ġ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/jxgl_jxxxwh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_zj" onclick="jxxxZj();return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="jxxxModi('update');return false;">�޸�</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="jxxxSc();return false;">ɾ��</a>
						</li>
						<li>
							<a href="#" class="btn_ck" onclick="jxxxModi('cxmd');return false;">��ѵ��������</a>
						</li>
						<li>
							<a href="#" class="btn_shtg" onclick="jxxxModi('start');return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_shbtg" onclick="jxxxModi('stop');return false;">ֹͣ</a>
						</li>
						<li>
							<a href="#" class="btn_dr" onclick="drCxmd();return false;">�����ѵ����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxxxwhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��ѵ����
							</th>
							<td>
								<input type="hidden" name="jxid" id="jxid" value=""/>
								<input type="text" name="jxmc" id="jxmc" value="" maxlength="50" style="width: 150px;"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ʼʱ��
							</th>
							<td>
								<input type="text" id="kssj" name="kssj" style="width: 150px;"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����ʱ��
							</th>
							<td>
								<input type="text" id="jssj" name="jssj" style="width: 150px;"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�μ��꼶
							</th>
							<td>
								<select id="cjnj" name="cjnj" style="width: 150px;"></select>
							</td>
						</tr>
						<tr>
							<th>
								��ѵ״̬
							</th>
							<td>
								<input type="radio" name="jxzt" value="start" checked="checked"/>����
								<input type="radio" name="jxzt" value="stop"/>ֹͣ
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
									<button type="button" name="����" onclick="jxxxDivSave();return false;">
										����
									</button>
									<button type="button" name="�ر�" onclick="closeWindown();return false;">
										�ر�
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
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
<%--		<script type='text/javascript' src="js/comm/message.js"></script>--%>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
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
				1.��ѵ��������μӱ��ξ�ѵ��<font color="blue">��ѵ��Ա</font>��<br/>
				2.������У��<font color="blue">�꼶</font>�Լ�<font color="blue">���컺ѵ����ѵ</font>��ѧ���Զ����ɾ�ѵ������<br/>
				3.ͨ��<font color="blue">ȡ����ѵ�ʸ񣬻�ѵ����ѵ����ѵ</font>�Ĳ������������е�����
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/jxgl_jxxxwh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="ysfxm" id="ysfxm" value="no"/>
			<input type="hidden" name="ysfhx" id="ysfhx" value="yes"/>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="refreshForm('jxgl_jcxxwh_jxxxwh.do');return false;">����</a>
							<logic:equal name="rs" property="jxzt" value="start">
								<a href="#" class="btn_zj" onclick="jxmdsc();return false;">��������</a>
								<a href="#" class="btn_sc" onclick="jxxx('sc');return false;">ȡ����ѵ�ʸ�</a>
								<a href="#" class="btn_xg" onclick="jxxx('hx');return false;">��ѵ</a>
								<a href="#" class="btn_xg" onclick="jxxx('mx');return false;">��ѵ</a>
								<a href="#" class="btn_xg" onclick="jxmdModi('cx');return false;">��ѵ</a>
							</logic:equal>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							<a href="#" class="btn_ck" onclick="viewJxxx();return false;">�鿴</a>
							
							<logic:equal name="xxdm" value="14073">
								<li>
									<a href="javascript:void(0);" onclick="printHx();return false;" class="btn_dy">��ѵ�����</a>
									<a href="javascript:void(0);" onclick="printMx();return false;" class="btn_dy">��ѵ�����</a>
								</li>
							</logic:equal>
						</li>
					</ul>
				</div>
				
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
			<div id="tempDiv" style="display:none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��ѵ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								<span class="red">*</span>�μ��꼶
							</th>
							<td>
								<logic:iterate id="cjnj" name="cjnjList">
									<logic:equal name="rs" property="cjnj" value="${cjnj.cjnj}">
										<input type="checkbox" name="cjnj" value = "${cjnj.cjnj}" onclick="getJxrs()" checked="checked"/>${cjnj.cjnj}
									</logic:equal>
									<logic:notEqual name="rs" property="cjnj" value="${cjnj.cjnj}">
										<input type="checkbox" name="cjnj" value = "${cjnj.cjnj}" onclick="getJxrs()"/>${cjnj.cjnj}
									</logic:notEqual>
								</logic:iterate>
								
							</td>
						</tr>
						<tr>
							<th>
								���컺ѵѧ��
							</th>
							<td>
								<input type="radio" name="sfhx" value="yes" onclick="getJxrs()" checked="checked"/>��
								<input type="radio" name="sfhx" value="no" onclick="getJxrs()"/>��
							</td>
						</tr>
						<tr>
							<th>
								������ѵѧ��
							</th>
							<td>
								<input type="radio" name="sfmx" value="yes" onclick="getJxrs()"/>��
								<input type="radio" name="sfmx" value="no" onclick="getJxrs()" checked="checked"/>��
							</td>
						</tr>
						<tr>
							<th>
								��ѡ�꼶δ��ѵ����
							</th>
							<td>
								<span id="njrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								���컺ѵ����
							</th>
							<td >
								 <span id="hxrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								������ѵ����
							</th>
							<td >
							  <span id="mxrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								���ξ�ѵ�Ѳ�ѵ����
							</th>
							<td>
								<font id="cjrs"></font>
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
									<button type="button" id="bcBtn" name="����" onclick="jxmdDivSave();return false;">
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
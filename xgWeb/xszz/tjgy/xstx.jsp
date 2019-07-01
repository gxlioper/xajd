<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	   <%@ include file="/syscommon/pagehead_V4.ini"%>
	   <script type="text/javascript" src="js/check.js"></script>
	   <script type="text/javascript" src="js/xszz/tjgy_xxcj.js"></script>
  </head>
  
  <body>
  		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="stu" property="xh">
			<logic:present name="rs">
				<logic:notEqual value="" name="rs" property="xysh">
					<div class="prompt">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							��ǰ�����¼�����״̬Ϊ<bean:message key="lable.xb" />���"${rs.xysh }",ѧУ���"${rs.xxsh }"��
						</p>
						<a class="close" title="����"
						   onclick="this.parentNode.style.display='none'"></a>
						   
						 <logic:notEqual value="δ���" name="rs" property="xysh">
							 <logic:notEqual value="�˻�" name="rs" property="xysh">
					 			<script defer>
					 				jQuery('#saveButton').attr('class','disabled');
					 				jQuery('#saveButton').attr('disabled',true);
					 			</script>
							 </logic:notEqual>
						 </logic:notEqual>
					</div>
				</logic:notEqual>
			</logic:present>
		</logic:present>
		
  		<html:form action="/xxcj" method="post">
  			<html:hidden property="xn" value="${xn }"/>
  			<html:hidden property="xq" value="${xq }"/>
  			<input type="hidden" id="url" name="url" value="/xxcj.do?method=xstx" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
  		
  			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate('xxcj.do?method=xstx&doType=save','xh')" id="saveButton">
										�� ��
									</button>

									<button type="button" type="reset">
										�� ��
									</button>
									<logic:equal value="add" name="xxcjForm" property="doType">
										<button type="button" onclick="refreshForm('xxcj.do?method=xxcjcx')">
											����
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody name="xsxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<logic:equal value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')"/>	
									<logic:notEqual value="modi" name="oper">
										<button type="button" onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>						
								</logic:notEqual>		
							</td>
							<th width="16%">����</th>
							<td width="34%">
								${stu.xm }
							</td>
						</tr>
						<tr>
							<th>���֤��</th>
							<td>
								${stu.sfzh }
							</td>
							<th>�Ƿ���ѧ��</th>
							<td>${stu.xjztm }</td>
						</tr>
						<tr>
							<th>רҵ��</th>
							<td>${stu.zydm }</td>
							<th>רҵ�༶</th>
							<td>${stu.bjmc }</td>
						</tr>
						<tr>
							<th>��ѧ�꼶</th>
							<td>${stu.nj }</td>
							<th>�Ա�</th>
							<td>${stu.xb }</td>
						</tr>
						<tr>
							<th>����</th>
							<td>${stu.mzmc }</td>
							<th>������ò</th>
							<td>${stu.zzmmmc }</td>
						</tr>
						<tr>
							<th>ѧ�����</th>
							<td>
								${stu.pycc }
							</td>
							<th>��ҵ����</th>
							<td>${stu.byny }</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td>${stu.xz }</td>
							<th>����</th>
							<td>${stu.jg }</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ɼ���Ϣ</span>
								<p class="floatright normal">
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_pkx.doc">ƶ��������</a>&nbsp;&nbsp;
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_dzxb.doc"> ������������</a>&nbsp;&nbsp;
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_zdjb.doc">�ش󼲲�</a>
								</p>
							</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody1');return false">��һ����(������Ϣ)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody1" style="display:none">
						<tr>
							<th>ѧ��</th>
							<td>
								<html:text property="xf" onblur="checkInputNum(this)" maxlength="10" name="rs"></html:text>
							</td>
							<th>��ʿ��Ů</th>
							<td>
								<html:radio property="lszn" value="1" name="rs"></html:radio>��
								<html:radio property="lszn" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�¶�</th>
							<td>
								<html:radio property="ge" value="1" name="rs"></html:radio>��
								<html:radio property="ge" value="0" name="rs"></html:radio>��
							</td>
							<th>�ͱ�</th>
							<td>
								<html:radio property="db" value="1" name="rs"></html:radio>��
								<html:radio property="db" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<html:radio property="tkjz" value="1" name="rs"></html:radio>��
								<html:radio property="tkjz" value="0" name="rs"></html:radio>��
							</td>
							<th>�Ÿ�</th>
							<td>
								<html:radio property="yf" value="1" name="rs"></html:radio>��
								<html:radio property="yf" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						
						<tr>
							<th>�м�ѧ��</th>
							<td>
								<html:radio property="cjxs" value="1" name="rs"></html:radio>��
								<html:radio property="cjxs" value="0" name="rs"></html:radio>��
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
								   onclick="showTbody(this,'myTbody2');return false">�ڶ�����(������������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody2" style="display:none">
						<tr>
							<th>��������</th>
							<td>
								<html:radio name="rs" property="dbsx" value="1" onclick="setDq(this,'dbpk','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="dbsx" value="0" onclick="setDq(this,'dbpk','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
							<th>��������</th>
							<td>
								<html:radio name="rs" property="dbcz" value="1" onclick="setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="dbcz" value="0" onclick="setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ƶ������</th>
							<td>
								<html:radio name="rs" property="dbpk" value="1" onclick="setDq(this,'dbsx','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="dbpk" value="0" onclick="setDq(this,'dbsx','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
							<th>�в�����</th>
							<td>
								<html:radio name="rs" property="zbsx" value="1" onclick="setDq(this,'zbpk','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="zbsx" value="0" onclick="setDq(this,'zbpk','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�в�����</th>
							<td>
								<html:radio name="rs" property="zbcz" value="1" onclick="setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="zbcz" value="0" onclick="setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
							<th>�в�����ƶ������</th>
							<td>
								<html:radio name="rs" property="zbpk" value="1" onclick="setDq(this,'zbsx','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
								<html:radio name="rs" property="zbpk" value="0" onclick="setDq(this,'zbsx','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<html:radio name="rs" property="xbsx" value="1" onclick="setDq(this,'xbpk','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
								<html:radio name="rs" property="xbsx" value="0" onclick="setDq(this,'xbpk','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
							</td>
							<th>��������</th>
							<td>
								<html:radio name="rs" property="xbcz" value="1" onclick="setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
								<html:radio name="rs" property="xbcz" value="0" onclick="setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ƶ������</th>
							<td>
								<html:radio name="rs" property="xbpk" value="1" onclick="setDq(this,'xbsx','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
								<html:radio name="rs" property="xbpk" value="0" onclick="setDq(this,'xbsx','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>��
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody3');return false">��������(��ͥ��Ϣ-�������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody3" style="display:none">
						<tr>
							<th>˫�׼�ͥ</th>
							<td>
								<html:radio name="rs" property="sqjt" value="1" onclick="checkDqxx(this)"></html:radio>��
								<html:radio name="rs" property="sqjt" value="0" onclick="checkDqxx(this)"></html:radio>��
							</td>
							<th>�����븸��</th>
							<td>
								<html:radio name="rs" property="dqlfy" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqlfy" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>������ĸ��</th>
							<td>
								<html:radio name="rs" property="dqlmy" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqlmy" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>����������</th>
							<td>
								<html:radio name="rs" property="dqlqt" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqlqt" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td>
								<html:radio name="rs" property="dqwfy" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqwfy" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>������ĸ��</th>
							<td>
								<html:radio name="rs" property="dqwmy" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqwmy" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td>
								<html:radio name="rs" property="dqwqt" value="1" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio name="rs" property="dqwqt" value="0" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>����������</th>
							<td>
								<html:radio name="rs" property="fyfqt" value="1" onclick="checkFyfqt(this)"></html:radio>��
								<html:radio name="rs" property="fyfqt" value="0" onclick="checkFyfqt(this)"></html:radio>��
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody4');return false">���Ĳ���(��ͥ��Ϣ-��ĸ�������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody4" style="display:none">
						<tr>
							<th>��ĸ����ũ</th>
							<td>
								<html:radio name="rs" property="fmwn" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fmwn" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>����ĸũ</th>
							<td>
								<html:radio name="rs" property="fgmn" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fgmn" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ĸ����ũ</th>
							<td>
								<html:radio name="rs" property="mgfn" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="mgfn" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>��ĸ������</th>
							<td>
								<html:radio name="rs" property="fmgz" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fmgz" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ�¸�</th>
							<td>
								<html:radio name="rs" property="fgmxg" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fgmxg" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>ĸ�����¸�</th>
							<td>
								<html:radio name="rs" property="mgfxg" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="mgfxg" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��ĸ�¸��޹���</th>
							<td>
								<html:radio name="rs" property="fmxg" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fmxg" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>��ĸ�¸�һ����ҵ</th>
							<td>
								<html:radio name="rs" property="fmxgyfjy" value="1" onclick="setFmgz(this);"></html:radio>��
								<html:radio name="rs" property="fmxgyfjy" value="0" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��ͥ��������</th>
							<td>
								<html:radio name="rs" property="jtjjqt" value="1"></html:radio>��
								<html:radio name="rs" property="jtjjqt" value="0"></html:radio>��
							</td>
							<th>�������޹������</th>
							<td>
								<html:radio name="rs" property="ywgzqk" value="1"></html:radio>��
								<html:radio name="rs" property="ywgzqk" value="0"></html:radio>��
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody5');return false">���岿��(��ͥ��Ϣ-��ĸ�������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody5" style="display:none">
						<tr>
							<th>��ĸ��������</th>
							<td>
								<html:radio name="rs" property="fmstlh" value="1" onclick="setFmst(this);"></html:radio>��
								<html:radio name="rs" property="fmstlh" value="0" onclick="setFmst(this);"></html:radio>��
							</td>
							<th>��������ƫ��</th>
							<td>
								<html:radio name="rs" property="fqstcpc" value="1" onclick="setQm(this,'fqstjc','fcj')"></html:radio>��
								<html:radio name="rs" property="fqstcpc" value="0" onclick="setQm(this,'fqstjc','fcj')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸�������ƫ��</th>
							<td>
								<html:radio name="rs" property="dqfqstpc" value="1" onclick="setQm(this,'dqfqstjc','dqfc')"></html:radio>��
								<html:radio name="rs" property="dqfqstpc" value="0" onclick="setQm(this,'dqfqstjc','dqfc')"></html:radio>��
							</td>
							<th>ĸ������ƫ��</th>
							<td>
								<html:radio name="rs" property="mqstpc" value="1" onclick="setQm(this,'mqstjc','mcj')"></html:radio>��
								<html:radio name="rs" property="mqstpc" value="0" onclick="setQm(this,'mqstjc','mcj')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ������ƫ��</th>
							<td>
								<html:radio name="rs" property="dqmqstpc" value="1" onclick="setQm(this,'dqmqstjc','dqmc')"></html:radio>��
								<html:radio name="rs" property="dqmqstpc" value="0" onclick="setQm(this,'dqmqstjc','dqmc')"></html:radio>��
							</td>
							<th>�������弫��</th>
							<td>
								<html:radio name="rs" property="fqstjc" value="1" onclick="setQm(this,'fqstcpc','fcj')"></html:radio>��
								<html:radio name="rs" property="fqstjc" value="0" onclick="setQm(this,'fqstcpc','fcj')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸������弫��</th>
							<td>
								<html:radio name="rs" property="dqfqstjc" value="1" onclick="setQm(this,'dqfqstpc','dqfc')"></html:radio>��
								<html:radio name="rs" property="dqfqstjc" value="0" onclick="setQm(this,'dqfqstpc','dqfc')"></html:radio>��
							</td>
							<th>ĸ�����弫��</th>
							<td>
								<html:radio name="rs" property="mqstjc" value="1" onclick="setQm(this,'mqstpc','mcj')"></html:radio>��
								<html:radio name="rs" property="mqstjc" value="0" onclick="setQm(this,'mqstpc','mcj')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ�����弫��</th>
							<td>
								<html:radio name="rs" property="dqmqstjc" value="1" onclick="setQm(this,'dqmqstpc','dqmc')"></html:radio>��
								<html:radio name="rs" property="dqmqstjc" value="0" onclick="setQm(this,'dqmqstpc','dqmc')"></html:radio>��
							</td>
							<th>��ĸ���ǲм���</th>
							<td>
								<html:radio name="rs" property="fmcj" value="1" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')"></html:radio>��
								<html:radio name="rs" property="fmcj" value="0" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸���</th>
							<td>
								<html:radio name="rs" property="dqfc" value="1" onclick="setQm(this,'dqfqstpc','dqfqstjc')"></html:radio>��
								<html:radio name="rs" property="dqfc" value="0" onclick="setQm(this,'dqfqstpc','dqfqstjc')"></html:radio>��
							</td>
							<th>����</th>
							<td>
								<html:radio name="rs" property="fcj" value="1" onclick="setQm(this,'fqstcpc','fqstjc','fmcj')"></html:radio>��
								<html:radio name="rs" property="fcj" value="0" onclick="setQm(this,'fqstcpc','fqstjc','fmcj')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ��</th>
							<td>
								<html:radio name="rs" property="dqmc" value="1" onclick="setQm(this,'dqmqstpc','dqmqstjc')"></html:radio>��
								<html:radio name="rs" property="dqmc" value="0" onclick="setQm(this,'dqmqstpc','dqmqstjc')"></html:radio>��
							</td>
							<th>ĸ��</th>
							<td>
								<html:radio name="rs" property="mcj" value="1" onclick="setQm(this,'mqstpc','mqstjc','fmcj')"></html:radio>��
								<html:radio name="rs" property="mcj" value="0" onclick="setQm(this,'mqstpc','mqstjc','fmcj')"></html:radio>��
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody6');return false">��������(��ͥ��Ϣ-��ĸ�������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody6" style="display:none">
						<tr>
							<th>��50����</th>
							<td>
								<html:radio name="rs" property="fwsx" value="1" onclick="setQm(this,'fwls','flqs','qsys')"></html:radio>��
								<html:radio name="rs" property="fwsx" value="0" onclick="setQm(this,'fwls','flqs','qsys')"></html:radio>��
							</td>
							<th>��51-60</th>
							<td>
								<html:radio name="rs" property="fwls" value="1" onclick="setQm(this,'fwsx','flqs','qsys')"></html:radio>��
								<html:radio name="rs" property="fwls" value="0" onclick="setQm(this,'fwsx','flqs','qsys')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��61-70</th>
							<td>
								<html:radio name="rs" property="flqs" value="1" onclick="setQm(this,'fwsx','fwls','qsys')"></html:radio>��
								<html:radio name="rs" property="flqs" value="0" onclick="setQm(this,'fwsx','fwls','qsys')"></html:radio>��
							</td>
							<th>��71����</th>
							<td>
								<html:radio name="rs" property="qsys" value="1" onclick="setQm(this,'fwsx','fwls','flqs')"></html:radio>��
								<html:radio name="rs" property="qsys" value="0" onclick="setQm(this,'fwsx','fwls','flqs')"></html:radio>��
							</td>
						</tr>
						<tr>
							
							<th>ĸ45����</th>
							<td>
								<html:radio name="rs" property="mswyx" value="1" onclick="setQm(this,'msww','mwlw','mlls')"></html:radio>��
								<html:radio name="rs" property="mswyx" value="0" onclick="setQm(this,'msww','mwlw','mlls')"></html:radio>��
							</td>
							<th>ĸ45-55</th>
							<td>
								<html:radio name="rs" property="msww" value="1" onclick="setQm(this,'mswyx','mwlw','mlls')"></html:radio>��
								<html:radio name="rs" property="msww" value="0" onclick="setQm(this,'mswyx','mwlw','mlls')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ĸ56-65</th>
							<td>
								<html:radio name="rs" property="mwlw" value="1" onclick="setQm(this,'mswyx','msww','mlls')"></html:radio>��
								<html:radio name="rs" property="mwlw" value="0" onclick="setQm(this,'mswyx','msww','mlls')"></html:radio>��
							</td>
							<th>ĸ66����</th>
							<td>
								<html:radio name="rs" property="mlls" value="1" onclick="setQm(this,'mswyx','msww','mwlw')"></html:radio>��
								<html:radio name="rs" property="mlls" value="0" onclick="setQm(this,'mswyx','msww','mwlw')"></html:radio>��
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody7');return false">���߲���(��ͥ��Ϣ-�����������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody7" style="display:none">
						<tr>
							<th>��������(��)</th>
							<td>
								<html:text name="rs" property="sylrs" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="isZero(this);" maxlength="2"></html:text>
							</td>
							<th>������������</th>
							<td>
								<html:radio name="rs" property="lrsth" value="1" onclick="setDb(this,'lrstc')"></html:radio>��
								<html:radio name="rs" property="lrsth" value="0" onclick="setDb(this,'lrstc')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ϲ�</th>
							<td>
								<html:radio name="rs" property="lrstc" value="1" onclick="setDb(this,'lrsth')"></html:radio>��
								<html:radio name="rs" property="lrstc" value="0" onclick="setDb(this,'lrsth')"></html:radio>��
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody8');return false">�ڰ˲���(��ͥ��Ϣ-�ֵܽ������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody8" style="display:none">
<%--						<tr>--%>
<%--							<th>�����ѧ</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjzx" value="1" onclick="setQm(this,'gjwn','gjdg','gjgz')"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjzx" value="0" onclick="setQm(this,'gjwn','gjdg','gjgz')"></html:radio>��--%>
<%--							</td>--%>
<%--							<th>�����ũ</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjwn" value="1" onclick="setQm(this,'gjzx','gjdg','gjgz')"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjwn" value="0" onclick="setQm(this,'gjzx','gjdg','gjgz')"></html:radio>��--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>����</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjdg" value="1" onclick="setQm(this,'gjzx','gjwn','gjgz')"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjdg" value="0" onclick="setQm(this,'gjzx','gjwn','gjgz')"></html:radio>��--%>
<%--							</td>--%>
<%--							<th>��㹤��</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjgz" value="1" onclick="setQm(this,'gjzx','gjwn','gjdg')"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjgz" value="0" onclick="setQm(this,'gjzx','gjwn','gjdg')"></html:radio>��--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>�������ϲ�</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjstc" value="1"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjstc" value="0"></html:radio>��--%>
<%--							</td>--%>
<%--							<th>�������</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjqt" value="1"></html:radio>��--%>
<%--								<html:radio name="rs" property="gjqt" value="0"></html:radio>��--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th>�����ѧ</th>
							<td>
								<html:radio name="rs" property="gjzx" value="1" ></html:radio>��
								<html:radio name="rs" property="gjzx" value="0" ></html:radio>��
							</td>
							<th>�����ũ</th>
							<td>
								<html:radio name="rs" property="gjwn" value="1" ></html:radio>��
								<html:radio name="rs" property="gjwn" value="0" ></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<html:radio name="rs" property="gjdg" value="1" ></html:radio>��
								<html:radio name="rs" property="gjdg" value="0" ></html:radio>��
							</td>
							<th>��㹤��</th>
							<td>
								<html:radio name="rs" property="gjgz" value="1" ></html:radio>��
								<html:radio name="rs" property="gjgz" value="0" ></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�������ϲ�</th>
							<td>
								<html:radio name="rs" property="gjstc" value="1"></html:radio>��
								<html:radio name="rs" property="gjstc" value="0"></html:radio>��
							</td>
							<th>�������</th>
							<td>
								<html:radio name="rs" property="gjqt" value="1"></html:radio>��
								<html:radio name="rs" property="gjqt" value="0"></html:radio>��
							</td>
						</tr>
					<!-- 
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody9');return false">�ھŲ���</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody9" style="display:none">
					 -->	
						<tr>
							<th>�����ѧ</th>
							<td>
								<html:radio name="rs" property="dmcx" value="1" onclick="setQm(this,'dmzxx','dmgz','dmdx')"></html:radio>��
								<html:radio name="rs" property="dmcx" value="0" onclick="setQm(this,'dmzxx','dmgz','dmdx')"></html:radio>��
							</td>
							<th>������Сѧ</th>
							<td>
								<html:radio name="rs" property="dmzxx" value="1" onclick="setQm(this,'dmcx','dmgz','dmdx')"></html:radio>��
								<html:radio name="rs" property="dmzxx" value="0" onclick="setQm(this,'dmcx','dmgz','dmdx')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���ö�����</th>
							<td>
								<html:radio name="rs" property="dmgz" value="1" onclick="setQm(this,'dmcx','dmzxx','dmdx')"></html:radio>��
								<html:radio name="rs" property="dmgz" value="0" onclick="setQm(this,'dmcx','dmzxx','dmdx')"></html:radio>��
							</td>
							<th>���ö���ѧ</th>
							<td>
								<html:radio name="rs" property="dmdx" value="1" onclick="setQm(this,'dmcx','dmzxx','dmgz')"></html:radio>��
								<html:radio name="rs" property="dmdx" value="0" onclick="setQm(this,'dmcx','dmzxx','dmgz')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ϲ�</th>
							<td>
								<html:radio name="rs" property="dmstc" value="1"></html:radio>��
								<html:radio name="rs" property="dmstc" value="0"></html:radio>��
							</td>
							<th>���òм���</th>
							<td>
								<html:radio name="rs" property="dmcj" value="1"></html:radio>��
								<html:radio name="rs" property="dmcj" value="0"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�����������</th>
							<td>
								<html:radio name="rs" property="dmqt" value="1"></html:radio>��
								<html:radio name="rs" property="dmqt" value="0"></html:radio>��
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<!--   
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody10');return false">�ھŲ���(��ͥ��Ϣ-�������)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody10" style="display:none">
						<tr>
							<th>��Ȼ�ֺ�</th>
							<td>
								<html:radio name="rs" property="zrzh" value="1"></html:radio>��
								<html:radio name="rs" property="zrzh" value="0"></html:radio>��
							</td>
							<th>�ش󼲲�</th>
							<td>
								<html:radio name="rs" property="zdjb" value="1"></html:radio>��
								<html:radio name="rs" property="zdjb" value="0"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ͻ���¼�</th>
							<td>
								<html:radio name="rs" property="tfsj" value="1"></html:radio>��
								<html:radio name="rs" property="tfsj" value="0"></html:radio>��
							</td>
							<th>Ƿծ���</th>
							<td>
								<html:radio name="rs" property="qzqk" value="1"></html:radio>��
								<html:radio name="rs" property="qzqk" value="0"></html:radio>��
							</td>
						</tr>
					</tbody>
					 -->
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
				</script>
			</logic:present>
			
  		</html:form>
  </body>
</html>

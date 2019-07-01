<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	   <%@ include file="/syscommon/pagehead_V4.ini"%>
	   <script type="text/javascript" src="js/check.js"></script>
	   <script type="text/javascript" src="js/xszz/tjgy_xxcj.js"></script>
	   <script>
	   	function checkNumber(obj,id){
			hideMsgDiv(id);
			checkInputNum(obj);
			if(obj.value>100){
				obj.focus();
			}
		}
	   </script>
  </head>
  
  <body>
  		<html:form action="/xxcj" method="post">
  			<html:hidden property="xh" value="${rs.xh }"/>
  			<html:hidden property="xn" value="${xn }"/>
  			<html:hidden property="xq" value="${xq }"/>
  			<input type="hidden" id="url" name="url" value="/xxcj.do?method=xstx" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
  			<input type="hidden" name="pkValue" value="${rs.pkValue }"/>
  			
  			<logic:equal value="xy" name="userType">
  				<logic:equal value="�˻�" property="xxsh" name="rs">
  					<input type="hidden" name="xxsh" value="������"/>
  				</logic:equal>
  				<logic:notEqual value="�˻�" property="xxsh" name="rs">
  					<input type="hidden" name="xxsh" value="${rs.xxsh }"/>
  				</logic:notEqual>
  			</logic:equal>
  		
  			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate('xxcj.do?method=xxcjDgsh&doType=save','')">
										�� ��
									</button>

									<button type="button" onclick="window.close();return false;">
										�� ��
									</button>
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
							<th width="16%">ѧ��</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">����</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>���֤��</th>
							<td>
								${rs.sfzh }
							</td>
							<th>�Ƿ���ѧ��</th>
							<td>${rs.xjztm }</td>
						</tr>
						<tr>
							<th>רҵ��</th>
							<td>${rs.zydm }</td>
							<th>רҵ�༶</th>
							<td>${rs.bjmc }</td>
						</tr>
						<tr>
							<th>��ѧ�꼶</th>
							<td>${rs.nj }</td>
							<th>�Ա�</th>
							<td>${rs.xb }</td>
						</tr>
						<tr>
							<th>����</th>
							<td>${rs.mzmc }</td>
							<th>������ò</th>
							<td>${rs.zzmmmc }</td>
						</tr>
						<tr>
							<th>ѧ�����</th>
							<td>
								<logic:present name="rs">
									<logic:equal value="4" name="rs" property="xl">
										����
									</logic:equal>
									<logic:notEqual value="4" name="rs" property="xl">
										ר��
									</logic:notEqual>
								</logic:present>
							</td>
							<th>��ҵ����</th>
							<td></td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td>${rs.xz }</td>
							<th>����</th>
							<td>${rs.sydsmc }</td>
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
								<html:radio property="sqjt" value="1" onclick="checkDqxx(this)" name="rs"></html:radio>��
								<html:radio property="sqjt" value="0" onclick="checkDqxx(this)" name="rs"></html:radio>��
							</td>
							<th>�����븸��</th>
							<td>
								<html:radio property="dqlfy" value="1" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqlfy" value="0" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>������ĸ��</th>
							<td>
								<html:radio property="dqlmy" value="1" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqlmy" value="0" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>����������</th>
							<td>
								<html:radio property="dqlqt" value="1" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqlqt" value="0" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td>
								<html:radio property="dqwfy" value="1" name="rs" onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqwfy" value="0" name="rs" onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>������ĸ��</th>
							<td>
								<html:radio property="dqwmy" value="1" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqwmy" value="0" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td>
								<html:radio property="dqwqt" value="1" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
								<html:radio property="dqwqt" value="0" name="rs"  onclick="checkDqfyxx(this)"></html:radio>��
							</td>
							<th>����������</th>
							<td>
								<html:radio property="fyfqt" value="1" name="rs" onclick="checkFyfqt(this)"></html:radio>��
								<html:radio property="fyfqt" value="0" name="rs" onclick="checkFyfqt(this)"></html:radio>��
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
								<html:radio property="fmwn" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fmwn" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>����ĸũ</th>
							<td>
								<html:radio property="fgmn" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fgmn" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ĸ����ũ</th>
							<td>
								<html:radio property="mgfn" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="mgfn" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>��ĸ������</th>
							<td>
								<html:radio property="fmgz" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fmgz" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ�¸�</th>
							<td>
								<html:radio property="fgmxg" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fgmxg" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>ĸ�����¸�</th>
							<td>
								<html:radio property="mgfxg" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="mgfxg" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��ĸ�¸��޹���</th>
							<td>
								<html:radio property="fmxg" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fmxg" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
							<th>��ĸ�¸�һ����ҵ</th>
							<td>
								<html:radio property="fmxgyfjy" value="1" name="rs" onclick="setFmgz(this);"></html:radio>��
								<html:radio property="fmxgyfjy" value="0" name="rs" onclick="setFmgz(this);"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��ͥ��������</th>
							<td>
								<html:radio property="jtjjqt" value="1" name="rs"></html:radio>��
								<html:radio property="jtjjqt" value="0" name="rs"></html:radio>��
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
								<html:radio property="fmstlh" value="1" onclick="setFmst(this);" name="rs"></html:radio>��
								<html:radio property="fmstlh" value="0" onclick="setFmst(this);" name="rs"></html:radio>��
							</td>
							<th>��������ƫ��</th>
							<td>
								<html:radio property="fqstcpc" value="1" onclick="setQm(this,'fqstjc','fcj')" name="rs"></html:radio>��
								<html:radio property="fqstcpc" value="0" onclick="setQm(this,'fqstjc','fcj')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸�������ƫ��</th>
							<td>
								<html:radio property="dqfqstpc" value="1" onclick="setQm(this,'dqfqstjc','dqfc')" name="rs"></html:radio>��
								<html:radio property="dqfqstpc" value="0" onclick="setQm(this,'dqfqstjc','dqfc')" name="rs"></html:radio>��
							</td>
							<th>ĸ������ƫ��</th>
							<td>
								<html:radio property="mqstpc" value="1" onclick="setQm(this,'mqstjc','mcj')" name="rs"></html:radio>��
								<html:radio property="mqstpc" value="0" onclick="setQm(this,'mqstjc','mcj')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ������ƫ��</th>
							<td>
								<html:radio property="dqmqstpc" value="1" onclick="setQm(this,'dqmqstjc','dqmc')" name="rs"></html:radio>��
								<html:radio property="dqmqstpc" value="0" onclick="setQm(this,'dqmqstjc','dqmc')" name="rs"></html:radio>��
							</td>
							<th>�������弫��</th>
							<td>
								<html:radio property="fqstjc" value="1" onclick="setQm(this,'fqstcpc','fcj')" name="rs"></html:radio>��
								<html:radio property="fqstjc" value="0" onclick="setQm(this,'fqstcpc','fcj')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸������弫��</th>
							<td>
								<html:radio property="dqfqstjc" value="1" onclick="setQm(this,'dqfqstpc','dqfc')" name="rs"></html:radio>��
								<html:radio property="dqfqstjc" value="0" onclick="setQm(this,'dqfqstpc','dqfc')" name="rs"></html:radio>��
							</td>
							<th>ĸ�����弫��</th>
							<td>
								<html:radio property="mqstjc" value="1" onclick="setQm(this,'mqstpc','mcj')" name="rs"></html:radio>��
								<html:radio property="mqstjc" value="0" onclick="setQm(this,'mqstpc','mcj')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ�����弫��</th>
							<td>
								<html:radio property="dqmqstjc" value="1" onclick="setQm(this,'dqmqstpc','dqmc')" name="rs"></html:radio>��
								<html:radio property="dqmqstjc" value="0" onclick="setQm(this,'dqmqstpc','dqmc')" name="rs"></html:radio>��
							</td>
							<th>��ĸ���ǲм���</th>
							<td>
								<html:radio property="fmcj" value="1" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')" name="rs"></html:radio>��
								<html:radio property="fmcj" value="0" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���׸���</th>
							<td>
								<html:radio property="dqfc" value="1" onclick="setQm(this,'dqfqstpc','dqfqstjc')" name="rs"></html:radio>��
								<html:radio property="dqfc" value="0" onclick="setQm(this,'dqfqstpc','dqfqstjc')" name="rs"></html:radio>��
							</td>
							<th>����</th>
							<td>
								<html:radio property="fcj" value="1" onclick="setQm(this,'fqstcpc','fqstjc')" name="rs"></html:radio>��
								<html:radio property="fcj" value="0"  name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����ĸ��</th>
							<td>
								<html:radio property="dqmc" value="1" onclick="setQm(this,'dqmqstpc','dqmqstjc')" name="rs"></html:radio>��
								<html:radio property="dqmc" value="0" onclick="setQm(this,'dqmqstpc','dqmqstjc')" name="rs"></html:radio>��
							</td>
							<th>ĸ��</th>
							<td>
								<html:radio property="mcj" value="1" onclick="setQm(this,'mqstpc','mqstjc')" name="rs"></html:radio>��
								<html:radio property="mcj" value="0" name="rs"></html:radio>��
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
								<html:radio property="fwsx" value="1" onclick="setQm(this,'fwls','flqs','qsys')" name="rs"></html:radio>��
								<html:radio property="fwsx" value="0" onclick="setQm(this,'fwls','flqs','qsys')" name="rs"></html:radio>��
							</td>
							<th>��51-60</th>
							<td>
								<html:radio property="fwls" value="1" onclick="setQm(this,'fwsx','flqs','qsys')" name="rs"></html:radio>��
								<html:radio property="fwls" value="0" onclick="setQm(this,'fwsx','flqs','qsys')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��61-70</th>
							<td>
								<html:radio property="flqs" value="1" onclick="setQm(this,'fwsx','fwls','qsys')" name="rs"></html:radio>��
								<html:radio property="flqs" value="0" onclick="setQm(this,'fwsx','fwls','qsys')" name="rs"></html:radio>��
							</td>
							<th>��71����</th>
							<td>
								<html:radio property="qsys" value="1" onclick="setQm(this,'fwsx','fwls','flqs')" name="rs"></html:radio>��
								<html:radio property="qsys" value="0" onclick="setQm(this,'fwsx','fwls','flqs')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							
							<th>ĸ45����</th>
							<td>
								<html:radio property="mswyx" value="1" onclick="setQm(this,'msww','mwlw','mlls')" name="rs"></html:radio>��
								<html:radio property="mswyx" value="0" onclick="setQm(this,'msww','mwlw','mlls')" name="rs"></html:radio>��
							</td>
							<th>ĸ45-55</th>
							<td>
								<html:radio property="msww" value="1" onclick="setQm(this,'mswyx','mwlw','mlls')" name="rs"></html:radio>��
								<html:radio property="msww" value="0" onclick="setQm(this,'mswyx','mwlw','mlls')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ĸ56-65</th>
							<td>
								<html:radio property="mwlw" value="1" onclick="setQm(this,'mswyx','msww','mlls')" name="rs"></html:radio>��
								<html:radio property="mwlw" value="0" onclick="setQm(this,'mswyx','msww','mlls')" name="rs"></html:radio>��
							</td>
							<th>ĸ66����</th>
							<td>
								<html:radio property="mlls" value="1" onclick="setQm(this,'mswyx','msww','mwlw')" name="rs"></html:radio>��
								<html:radio property="mlls" value="0" onclick="setQm(this,'mswyx','msww','mwlw')" name="rs"></html:radio>��
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
								<html:text property="sylrs" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="isZero(this);" maxlength="2" name="rs"></html:text>
							</td>
							<th>������������</th>
							<td>
								<html:radio property="lrsth" value="1" name="rs" onclick="setDb(this,'lrstc')"></html:radio>��
								<html:radio property="lrsth" value="0" name="rs" onclick="setDb(this,'lrstc')"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ϲ�</th>
							<td>
								<html:radio property="lrstc" value="1" name="rs" onclick="setDb(this,'lrsth')"></html:radio>��
								<html:radio property="lrstc" value="0" name="rs" onclick="setDb(this,'lrsth')"></html:radio>��
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
						<tr>
							<th>�����ѧ</th>
							<td>
<%--								<html:radio property="gjzx" value="1" onclick="setQm(this,'gjwn','gjdg','gjgz')" name="rs"></html:radio>��--%>
<%--								<html:radio property="gjzx" value="0" onclick="setQm(this,'gjwn','gjdg','gjgz')" name="rs"></html:radio>��--%>
								<html:radio property="gjzx" value="1"  name="rs"></html:radio>��
								<html:radio property="gjzx" value="0"  name="rs"></html:radio>��
							</td>
							<th>�����ũ</th>
							<td>
<%--								<html:radio property="gjwn" value="1" onclick="setQm(this,'gjzx','gjdg','gjgz')" name="rs"></html:radio>��--%>
<%--								<html:radio property="gjwn" value="0" onclick="setQm(this,'gjzx','gjdg','gjgz')" name="rs"></html:radio>��--%>
								<html:radio property="gjwn" value="1"  name="rs"></html:radio>��
								<html:radio property="gjwn" value="0"  name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
<%--								<html:radio property="gjdg" value="1" onclick="setQm(this,'gjzx','gjwn','gjgz')" name="rs"></html:radio>��--%>
<%--								<html:radio property="gjdg" value="0" onclick="setQm(this,'gjzx','gjwn','gjgz')" name="rs"></html:radio>��--%>
								<html:radio property="gjdg" value="1"  name="rs"></html:radio>��
								<html:radio property="gjdg" value="0"  name="rs"></html:radio>��
							</td>
							<th>��㹤��</th>
							<td>
<%--								<html:radio property="gjgz" value="1" onclick="setQm(this,'gjzx','gjwn','gjdg')" name="rs"></html:radio>��--%>
<%--								<html:radio property="gjgz" value="0" onclick="setQm(this,'gjzx','gjwn','gjdg')" name="rs"></html:radio>��--%>
								<html:radio property="gjgz" value="1"  name="rs"></html:radio>��
								<html:radio property="gjgz" value="0"  name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�������ϲ�</th>
							<td>
								<html:radio property="gjstc" value="1" name="rs"></html:radio>��
								<html:radio property="gjstc" value="0" name="rs"></html:radio>��
							</td>
							<th>�������</th>
							<td>
								<html:radio property="gjqt" value="1" name="rs"></html:radio>��
								<html:radio property="gjqt" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						
						<tr>
							<th>�����ѧ</th>
							<td>
								<html:radio property="dmcx" value="1" onclick="setQm(this,'dmzxx','dmgz','dmdx')" name="rs"></html:radio>��
								<html:radio property="dmcx" value="0" onclick="setQm(this,'dmzxx','dmgz','dmdx')" name="rs"></html:radio>��
							</td>
							<th>������Сѧ</th>
							<td>
								<html:radio property="dmzxx" value="1" onclick="setQm(this,'dmcx','dmgz','dmdx')" name="rs"></html:radio>��
								<html:radio property="dmzxx" value="0" onclick="setQm(this,'dmcx','dmgz','dmdx')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>���ö�����</th>
							<td>
								<html:radio property="dmgz" value="1" onclick="setQm(this,'dmcx','dmzxx','dmdx')" name="rs"></html:radio>��
								<html:radio property="dmgz" value="0" onclick="setQm(this,'dmcx','dmzxx','dmdx')" name="rs"></html:radio>��
							</td>
							<th>���ö���ѧ</th>
							<td>
								<html:radio property="dmdx" value="1" onclick="setQm(this,'dmcx','dmzxx','dmgz')" name="rs"></html:radio>��
								<html:radio property="dmdx" value="0" onclick="setQm(this,'dmcx','dmzxx','dmgz')" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>��������ϲ�</th>
							<td>
								<html:radio property="dmstc" value="1" name="rs"></html:radio>��
								<html:radio property="dmstc" value="0" name="rs"></html:radio>��
							</td>
							<th>���òм���</th>
							<td>
								<html:radio property="dmcj" value="1" name="rs"></html:radio>��
								<html:radio property="dmcj" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>�����������</th>
							<td>
								<html:radio property="dmqt" value="1" name="rs"></html:radio>��
								<html:radio property="dmqt" value="0" name="rs"></html:radio>��
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
								<html:radio property="zrzh" value="1" name="rs"></html:radio>��
								<html:radio property="zrzh" value="0" name="rs"></html:radio>��
							</td>
							<th>�ش󼲲�</th>
							<td>
								<html:radio property="zdjb" value="1" name="rs"></html:radio>��
								<html:radio property="zdjb" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ͻ���¼�</th>
							<td>
								<html:radio property="tfsj" value="1" name="rs"></html:radio>��
								<html:radio property="tfsj" value="0" name="rs"></html:radio>��
							</td>
							<th>Ƿծ���</th>
							<td>
								<html:radio property="qzqk" value="1" name="rs"></html:radio>��
								<html:radio property="qzqk" value="0" name="rs"></html:radio>��
							</td>
						</tr>
					</tbody>
					 -->
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.xb" />��д����</span>
							</th>
						</tr>
					</thead>
					<tbody name="xyxx">
						<tr>
							<th>
								y1
							</th>
							<td>
								<div class="pos" style="z-index:5" >
								 <html:text property="y1" onfocus="displayMsgDiv('msg_div1')"  onblur="checkNumber(this,'msg_div1')" maxlength="10" name="rs"></html:text>
								 <div id="msg_div1" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					              </div>
					            </div>
							</td>
							<th>
								y2
							</th>
							<td>
								<div class="pos" style="z-index:5" >
									<html:text property="y2" onfocus="displayMsgDiv('msg_div2')"  onblur="checkNumber(this,'msg_div2')" maxlength="10" name="rs"></html:text>
									<div id="msg_div2" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
						</tr>
						<tr>
							<th>
								y3
							</th>
							<td>
								<div class="pos" style="z-index:4" >
								<html:text property="y3" onfocus="displayMsgDiv('msg_div3')" onblur="checkNumber(this,'msg_div3')" maxlength="10" name="rs"></html:text>
								<div id="msg_div3" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
							<th>
								y4
							</th>
							<td>
								<div class="pos" style="z-index:4" >
								<html:text property="y4" onfocus="displayMsgDiv('msg_div4')" onblur="checkNumber(this,'msg_div4')" maxlength="10" name="rs"></html:text>
								<div id="msg_div4" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
						</tr>
						<tr>
							<th>
								y5
							</th>
							<td>
								<div class="pos" style="z-index:3" >
								<html:text property="y5" onfocus="displayMsgDiv('msg_div5')" onblur="checkNumber(this,'msg_div5')" maxlength="10" name="rs"></html:text>
								<div id="msg_div5" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
							<th>
								y6
							</th>
							<td>
								<div class="pos" style="z-index:3" >
								<html:text property="y6" onfocus="displayMsgDiv('msg_div6')" onblur="checkNumber(this,'msg_div6')" maxlength="10" name="rs"></html:text>
								<div id="msg_div6" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
						</tr>
						<tr>
							<th>
								y7
							</th>
							<td>
								<div class="pos" style="z-index:2" >
									<html:text property="y7" onfocus="displayMsgDiv('msg_div7')" onblur="checkNumber(this,'msg_div7')" maxlength="10" name="rs"></html:text>
									<div id="msg_div7" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
							<th>
								y8
							</th>
							<td>
								<div class="pos" style="z-index:2" >
									<html:text property="y8" onfocus="displayMsgDiv('msg_div8')"  onblur="checkNumber(this,'msg_div8')" maxlength="10" name="rs"></html:text>
									<div id="msg_div8" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
						</tr>
						<tr>
							<th>
								y9
							</th>
							<td>
								<div class="pos" style="z-index:1" >
								<html:text property="y9" onfocus="displayMsgDiv('msg_div9')" onblur="checkNumber(this,'msg_div9')" maxlength="10" name="rs"></html:text>
									<div id="msg_div9" class="hide">
						              <div class="prompcon">
						                <p>����������0-100֮�������</p>
						              </div>
					               </div>
					            </div>
							</td>
							<th>
								z
							</th>
							<td>
								<html:text property="z" onblur="checkInputNum(this)" maxlength="10" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>��Ȼ�ֺ�</th>
							<td>
								<html:radio property="zrzh" value="1" name="rs"></html:radio>��
								<html:radio property="zrzh" value="0" name="rs"></html:radio>��
							</td>
							<th>�ش󼲲�</th>
							<td>
								<html:radio property="zdjb" value="1" name="rs"></html:radio>��
								<html:radio property="zdjb" value="0" name="rs"></html:radio>��
							</td>
						</tr>
						<tr>
							<th>ͻ���¼�</th>
							<td>
								<html:radio property="tfsj" value="1" name="rs"></html:radio>��
								<html:radio property="tfsj" value="0" name="rs"></html:radio>��
							</td>
							<th>Ƿծ���</th>
							<td>
								<html:radio property="qzqk" value="1" name="rs"></html:radio>��
								<html:radio property="qzqk" value="0" name="rs"></html:radio>��
							</td>
						</tr>
					</tbody>
					
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<logic:equal value="xy" name="userType">
						<tbody name="shxx">
							<tr>
								<th><bean:message key="lable.xb" />���</th>
								<td>
									<html:select property="xysh">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th><bean:message key="lable.xb" />������</th>
								<td>
									<html:select property="xyshyj">
										<html:option value="">��ѡ��</html:option>
										<html:option value="A">A</html:option>
										<html:option value="B">B</html:option>
										<html:option value="C">C</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xb" />�����</th>
								<td>
									${userNameReal }
									<html:hidden property="xyshr" value="${userNameReal }"/>
								</td>
								<th>���ʱ��</th>
								<td>
									${systime }
									<html:hidden property="xyshsj" value="${systime }"/>
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<tbody name="shxx">
							<tr>
								<th><bean:message key="lable.xb" />���</th>
								<td>
									${rs.xysh }
								</td>
								<th><bean:message key="lable.xb" />������
								</th>
								<td>
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xb" />�����</th>
								<td>
									${rs.xyshr }
								</td>
								<th>���ʱ��</th>
								<td>
									${rs.xyshsj }
								</td>
							</tr>
							
							<tr>
								<th>ѧУ���</th>
								<td>
									<html:select property="xxsh">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>m</th>
								<td>
									${rs.m }
								</td>
							</tr>
							<tr>
								<th>ѧУ�����</th>
								<td>
									${userNameReal }
									<html:hidden property="xxshr" value="${userNameReal }"/>
								</td>
								<th>���ʱ��</th>
								<td>
									${systime }
									<html:hidden property="xxshsj" value="${systime }"/>
								</td>
							</tr>
							<tr>
								<th>ѧУ������
									<br/>
									<font color="red">
										&lt;��400��&gt;
									</font>
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property="xxshyj" style="width:95%" rows="5" onblur="checkLen(this,400)"></html:textarea>
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:present>
			
  		</html:form>
  </body>
</html>

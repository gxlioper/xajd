<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="xsxx" style="display: none">
	<table align="center" class="formlist breakword">
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_xjxx');">
					<span>ѧ����Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_xjxx">
			<tr>
				<th width="15%">
					�꼶
				</th>
				<td width="25%">
					${rs.nj}
				</td>
				<th width="15%">
					ѧ��(��)
				</th>
				<td colspan="2" width="45%">
					${rs.xz}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
					${rs.xymc}
				</td>
				<th>
					�Ƿ�ע��
				</th>
				<td colspan="2">
					${rs.sfzc}
				</td>
			</tr>
			<tr>
				<th>
					רҵ
				</th>
				<td>
					${rs.zymc}
				</td>
				<th>
					�Ƿ��߶���
				</th>
				<td colspan="2">
					${rs.sfzd}
				</td>
			</tr>
			<tr>
				<th>
					<%--�й����ʴ�ѧ --%>
					<logic:equal value="10491" name="xxdm">��ǰ</logic:equal>
					�༶
				</th>
				<td>
					${rs.bjmc}
				</td>
				<th>
					�Ƿ���У
				</th>
				<td colspan="2">
					${rs.sfzx}
				</td>
			</tr>
			<tr>
				<th>
					ѧ��״̬
				</th>
				<td>
					${rs.xjztm}
				</td>
				<th>
					�Ƿ��ҵ��
				</th>
				<td colspan="2">
					${rs.sfbys}
				</td>
			</tr>
			<tr>
				<th>
					��ѧʱ��
				</th>
				<td align="left">
					${rs.rxrq}
				</td>
				<th>
					�Ƿ��ҵ
				</th>
				<td colspan="2">
					${rs.sfyby}
				</td>
			</tr>
			<logic:notEqual name="xxdm" value="11057">
				<logic:notEqual name="xxdm" value="12752">
					<tr>
						<th>
							��ҵʱ��
						</th>
						<td>
							${rs.byny}
						</td>
						<th>
							У����Ϣ
						</th>
						<td colspan="2">
							${rs.yxdm}
						</td>
					</tr>
				</logic:notEqual>
			</logic:notEqual>
			<logic:equal name="xxdm" value="70001">
			<!-- �����й�ó��ʦѧԺ -->
				<tr>
						<th>
							�Ƿ�������У
						</th>
						<td colspan="4">
							${rs.sfzblx}
						</td>
					</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="11057">
				<tr>
					<th>
						��ҵʱ��
					</th>
					<td>
						${rs.byny}
					</td>
					<th>
						��ҵ֤��
					</th>
					<td colspan="2">
						${rs.byzh }
					</td>
				</tr>
				<tr>
					<th>
						ѧλ֤��
					</th>
					<td colspan="4">
						${rs.xwzsbh }
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="12752">
				<tr>
					<th>
						��ҵʱ��
					</th>
					<td>
						${rs.byny}
					</td>
					<th>
						������
					</th>
					<td colspan="2">
						${rs.ksh }
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="12578">
				<!-- �㶫����ְҵ����ѧԺ -->
				<tr>
					<th>
						ѧ����
					</th>
					<td>
						${rs.xjh}
					</td>
					<th>
						��������
					</th>
					<td colspan="2">
						${rs.hkxz }
					</td>
				</tr>
				<tr>
					<th>
						����������ò����
					</th>
					<td>
						${rs.jrzzmmrq}
					</td>
					<th>
						�Ƿ���
					</th>
					<td colspan="2">
						${rs.sfhq }
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
						������
					</th>
					<td colspan="2">
						${rs.csds }${rs.csdshi }${rs.csdxian }
					</td>
				</tr>
			</logic:equal>
		</tbody>

		<!--ѧ���춯(����ѧ���춯ѡ���yyp)-->
		<%--<%@ include file="/xsxx/common/xsxx_xjyd.jsp"%>--%>
		
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_lxfs');">
					<span>��ϵ��ʽ��֤��</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_lxfs">
			<tr>
				<th width="15%">
					<!-- ��ְҵ����ѧԺ -->
					<logic:equal name="xxdm" value="12061">
					�̺�
					</logic:equal>
					<!-- end��ְҵ����ѧԺ -->
					<logic:notEqual name="xxdm" value="12061">
					�̶��绰
					</logic:notEqual>
				</th>
				<td width="25%">
					${rs.lxdh}
				</td>
				<th width="15%">
					�ֻ�����
				</th>
				<td width="45%" colspan="2">
					${rs.sjhm}
				</td>
			</tr>
			<tr style="display: none;">
				<th>
					������
				</th>
				<td>
					${rs.ssbh}
				</td>
				<th>
					���ҵ绰
				</th>
				<td colspan="2">
					${rs.qsdh}
				</td>
			</tr>
			<tr>
				<th>
					QQ����
				</th>
				<td>
					${rs.qqhm}
				</td>
				<th>
					��������
				</th>
				<td colspan="2">
					${rs.lxdzxx}
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td>
					${rs.yhmc}
				</td>
				<th>
					���п���
				</th>
				<td colspan="2">
					${rs.yhkh}
				</td>
			</tr>
			<tr>
				<th>
					����Ա��Ϣ
				</th>
				<td colspan="4">
					${rs.fdyxx }
				</td>
			</tr>
			<tr>
				<th>
					��������Ϣ
				</th>
				<td colspan="4">
					${rs.bzrxx }
				</td>
			</tr>
			<tr>
<%--				<th>--%>
<%--					���������--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					${rs.zlbzrxm}--%>
<%--				</td>--%>
				<th>
					һ��ͨ��
				</th>
				<td colspan="4">
					<!--����ѧԺ-->
					<logic:equal value="10628#" name="xxdm">
							${rs.ykth}
						</logic:equal>
					<logic:notEqual value="10628#" name="xxdm">
							${rs.kh}
						</logic:notEqual>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtxx');">
					<span>ѧ����ͥ��Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtxx">
			<th width="10%">��ͥ�绰</th>
			<td width="30%">
				${rs.jtdh }
			</td>	
			<th width="10%">��������</th>
			<td colspan="2">
				${rs.yb }
			</td>
			<tr>
				<th>��ͥ��ַ</th>
				<td colspan="4">
					${rs.jtszd }
				</td>
			</tr>
			<tr>
				<th>��ͥ����״��</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jjzk }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy1');">
					<span>ѧ����ͥ��Ա��Ϣ1</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy1">
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy1_xm }
				</td>	
				<th width="10%">�뱾�˹�ϵ</th>
				<td colspan="2">
					${rs.jtcy1_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">��������</th>
				<td width="30%">
					${rs.jtcy1_nl }
				</td>
				<th width="10%">���֤��</th>
				<td colspan="2">
					${rs.jtcy1_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy1_mzmc }
				</td>	
				<th width="10%">������ò</th>
				<td colspan="2">
					${rs.jtcy1_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">ְҵ</th>
				<td width="30%">
					${rs.jtcy1_zy }
				</td>	
				<th width="10%">ְ��</th>
				<td colspan="2">
					${rs.jtcy1_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">������λ�绰</th>
				<td width="30%">
					${rs.jtcy1_lxdh2 }
				</td>	
				<th width="10%">���˵绰</th>
				<td colspan="2">
					${rs.jtcy1_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>������ַ</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy1_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy2');">
					<span>ѧ����ͥ��Ա��Ϣ2</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy2">
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy2_xm }
				</td>	
				<th width="10%">�뱾�˹�ϵ</th>
				<td colspan="2">
					${rs.jtcy2_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">��������</th>
				<td width="30%">
					${rs.jtcy2_nl }
				</td>
				<th width="10%">���֤��</th>
				<td colspan="2">
					${rs.jtcy2_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy2_mzmc }
				</td>	
				<th width="10%">������ò</th>
				<td colspan="2">
					${rs.jtcy2_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">ְҵ</th>
				<td width="30%">
					${rs.jtcy2_zy }
				</td>	
				<th width="10%">ְ��</th>
				<td colspan="2">
					${rs.jtcy2_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">������λ�绰</th>
				<td width="30%">
					${rs.jtcy2_lxdh2 }
				</td>	
				<th width="10%">���˵绰</th>
				<td colspan="2">
					${rs.jtcy2_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>������ַ</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy2_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy3');">
					<span>ѧ����ͥ��Ա��Ϣ3</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy3">
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy3_xm }
				</td>	
				<th width="10%">�뱾�˹�ϵ</th>
				<td colspan="2">
					${rs.jtcy3_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">��������</th>
				<td width="30%">
					${rs.jtcy3_nl }
				</td>
				<th width="10%">���֤��</th>
				<td colspan="2">
					${rs.jtcy3_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">����</th>
				<td width="30%">
					${rs.jtcy3_mzmc }
				</td>	
				<th width="10%">������ò</th>
				<td colspan="2">
					${rs.jtcy3_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">ְҵ</th>
				<td width="30%">
					${rs.jtcy3_zy }
				</td>	
				<th width="10%">ְ��</th>
				<td colspan="2">
					${rs.jtcy3_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">������λ�绰</th>
				<td width="30%">
					${rs.jtcy3_lxdh2 }
				</td>	
				<th width="10%">���˵绰</th>
				<td colspan="2">
					${rs.jtcy3_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>������ַ</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy3_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_qtxx');">
					<span>������Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_qtxx">
			<tr>
				<th width="15%">
					����ƴ��
				</th>
				<td width="25%">
					${rs.xmpy}
				</td>
				<th width="15%">
					������
				</th>
				<td align="left" colspan="2" width="45%">
					${rs.cym}
				</td>

			</tr>
			<tr>
				<th>
					���(cm)
				</th>
				<td align="left">
					${rs.sg}
				</td>
				<th>
					����(kg)
				</th>
				<td colspan="2">
					${rs.tz}
				</td>
			</tr>
			<logic:equal name="xxdm" value="12862">
				<tr>
					<th>
						��Χ(cm)
					</th>
					<td align="left">
						${rs.xsxw}
					</td>
					<th>
						Ь�ӳ���(��)
					</th>
					<td colspan="2">
						${rs.xzcm}
					</td>
				</tr>
			</logic:equal>

			<logic:notEqual value="10491" name="xxdm">
				<tr>
					<th>
						�س�
					</th>
					<td>
						${rs.tc}
					</td>
					<th>
						�������
					</th>
					<td align="left" colspan="2">
						<logic:equal value="new" name="edition">
							${rs.kslbmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.kslb}
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						��ѧ��ʽ
					</th>
					<td align="left">
						<logic:equal value="new" name="edition">
							${rs.rxfsmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.rxfs}
						</logic:notEqual>
					</td>
					<th>
						������ʽ
					</th>
					<td align="left" colspan="2">
						<logic:equal value="new" name="edition">
							${rs.pyfsmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.pyfs}
						</logic:notEqual>
					</td>
				</tr>
			</logic:notEqual>
			<!-- �Ͼ���ʦ -->
			<logic:equal name="xxdm" value="8001">
				<tr>
					<th>
						��������
					</th>
					<td>
						${rs.hkxz}
					</td>
					<th>
						��ѧǰ�Ļ��̶�
					</th>
					<td colspan="2">
						${rs.rxqwhcd}
					</td>
				</tr>
				<tr>
					<th>
						ע��˳�����
					</th>
					<td>
						${rs.zcsxhm}
					</td>
					<th></th>
					<td colspan="2">

					</td>
				</tr>
			</logic:equal>

			<%--��ɽʦ��ѧԺ--%>
			<logic:equal value="10649" name="xxdm">
				<tr>
					<th>
						��Уǰ�ı�ҵѧУ
					</th>
					<td colspan="4">
						${rs.rxqdw}
					</td>
				</tr>
			</logic:equal>
			<%--end��ɽʦ��ѧԺ--%>

			<!-- ���� ����ѧ�麣ѧԺ -->
			<logic:equal value="13675" name="xxdm" scope="session">
				<tr>
					<th>
						�Ƿ񱨵�
					</th>
					<td>
						${rs.sfbd}
					</td>
					<th>
						�ɷ����
					</th>
					<td colspan="2">
						${rs.jfqk }
					</td>
				</tr>
			</logic:equal>
			<!-- end���� ����ѧ�麣ѧԺ -->
			<%--���Ϲ�ҵ��ѧ--%>
			<logic:equal value="11535" name="xxdm">
				<tr>
					<th>
						�����Ƿ�����
					</th>
					<td>
						${rs.dasfyl}
					</td>
					<th>
						��������ԭ��
					</th>
					<td colspan="2">
						${rs.daylyy}
					</td>
				</tr>
			</logic:equal>

			<%-- ��ɳ����--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<th>
						��ҵ֤����״̬
					</th>
					<td colspan="4">
						${rs.byzffztmc}
					</td>
				</tr>
			</logic:equal>
			<%--�������ϴ�ѧ--%>
			<logic:equal value="11417" name="xxdm">
				<tr>
					<th>
						�Ƿ��ڷ�У
					</th>
					<td>
						${rs.sfzfx}
					</td>
					<th>
						�ڽ���Ϣ
					</th>
					<td colspan="2">
						${rs.zjmc}
					</td>
				</tr>
			</logic:equal>
			<%--end�������ϴ�ѧ--%>
			<%--������ְͨҵ����ѧԺ--%>
			<logic:equal value="12752" name="xxdm">
				<tr>
					<th>
						������
					</th>
					<td>
						${rs.dah}
					</td>
					<th>
						ҽ�Ʊ��պ�
					</th>
					<td colspan="2">
						${rs.ylbxh}
					</td>
				</tr>
			</logic:equal>
			<%--end������ְͨҵ����ѧԺ--%>

			<!--���ְͨҵѧԺ-->
			<logic:equal value="12883" name="xxdm">
				<tr>
					<td colspan="4">
						<%@ include file="/xsxx/xxjlxxb.jsp"%>
					</td>
				</tr>
			</logic:equal>
			<!--end���ְͨҵѧԺ-->

			<%--����ְҵ����ѧԺ--%>
			<logic:equal value="11355" name="xxdm">
				<tr>
					<th>
						������
					</th>
					<td>
						${rs.ksh}
					</td>
					<th></th>
					<td colspan="2">

					</td>
				</tr>
			</logic:equal>
			<%--end����ְҵ����ѧԺ--%>
			<%--�人����ѧ����ѧԺ--%>
			<logic:equal value="1049701" name="xxdm">
				<tr>
					<th>
						�ʱ�
					</th>
					<td>
						${rs.yb}
					</td>
					<th>
						�˳�����
					</th>
					<td colspan="2">
						${rs.ccqj}
					</td>
				</tr>
			</logic:equal>
			<%--��������ѧԺ--%>
			<logic:equal value="10690" name="xxdm">
				<tr>
					<th>
						ְ��
					</th>
					<td colspan="4">
						${rs.zw}
					</td>
				</tr>
			</logic:equal>
			<%--end��������ѧԺ--%>
			<%--��ɳ����ְҵ����ѧԺ--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<th>
						����Ǩ��ʱ��
					</th>
					<td>
						${rs.hkqcsj}
					</td>
					<th>
						�����Ƿ���У
					</th>
					<td colspan="2">
						${rs.dasfzx}
					</td>
				</tr>
			</logic:equal>
			<%--end��ɳ����ְҵ����ѧԺ--%>
			<!--����ѧԺ-->
			<logic:equal value="10628#" name="xxdm">
				<tr>
					<th>
						������
					</th>
					<td align="left" colspan="4">
						${rs.kh}
					</td>
				</tr>
			</logic:equal>
			<!--end����ѧԺ-->
			<%--�й����ʴ�ѧ --%>
			<logic:equal value="10491" name="xxdm">
				<tr>
					<th>
						�س�
					</th>
					<td>
						${rs.tc}
					</td>
					<th>
						�������
					</th>
					<td align="left" colspan="2">
						${rs.kslb}
					</td>
				</tr>
				<tr>
					<th>
						��ѧ�꼶
					</th>
					<td>
						${rs.rxnj}
					</td>
					<th>
						��ѧ��ʽ
					</th>
					<td colspan="2">
						${rs.rxfs}
					</td>
				</tr>
				<tr>
					<th>
						ѧ�����
					</th>
					<td>
						${rs.xslbmc}
					</td>
					<th>
						ѧ������
					</th>
					<td colspan="2">
						${rs.xslxmc}
					</td>
				</tr>
				<tr>
					<th>
						������ʽ
					</th>
					<td>
						${rs.pyfs}
					</td>
					<th>
						������
					</th>
					<td colspan="2">
						${rs.ksh}
					</td>
				</tr>
				<tr>
					<th>
						��ҵ��ѧ
					</th>
					<td>
						${rs.rxqdw}
					</td>
					<th>
						�춯���
					</th>
					<td colspan="2">
						${rs.ydlbmc}
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="4" id="bz" class="breakword">
						${rs.bz}
					</td>
				</tr>
			</logic:equal>
			<%--end�й����ʴ�ѧ --%>
			<%--������������ѧԺ--%>
			<logic:equal value="11626" name="xxdm">
				<tr>
					<th>
						ԭ��ҵѧԺ
					</th>
					<td>
						${rs.rxqdw}
					</td>
					<th>
						ԭ��ҵѧԺ�ʱ�
					</th>
					<td colspan="2">
						${rs.rxqdwyb}
					</td>
				</tr>
				<tr>
					<th>
						ԭ��ҵѧԺͨ�ŵ�ַ
					</th>
					<td colspan="4">
						${rs.rxqdwdz}
					</td>
				</tr>
				<tr>
					<th>
						���н׶α������
					</th>
					<td colspan="4">
						${rs.gzbx}
					</td>
				</tr>
			</logic:equal>
			<%--��������ѧԺ--%>
			<logic:equal value="10388" name="xxdm">
				<tr>
					<th>
						�Ƿ�۰�̨��
					</th>
					<td>
						${rs.sfgat}
					</td>
					<th>
						�Ƿ���������
					</th>
					<td colspan="2">
						${rs.sfssmz}
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
						��ҵ��ѧ
					</th>
					<td colspan="2">
						${rs.rxqdw}
					</td>
				</tr>
			</logic:equal>
			<tr>
				<th>
					�������ڵ�
				</th>
				<td>
					${rs.hkszd}
				</td>
				<th>�Ƿ������</th>
				<td colspan="2">
					${rs.sfhq }
				</td>
			</tr>
			<tr>
				<th>
					��ͥ��ַ
				</th>
				<td colspan="4">
					${rs.jtszd}
				</td>
			</tr>
			<tr>
				<th>
					��ͥ�绰
				</th>
				<td>
					${rs.jtdh}
				</td>
				<logic:notEqual value="10657" name="xxdm">
					<logic:notEqual name="xxdm" value="11654">
						<th>
							����״��
						</th>
						<td colspan="2">
							${rs.jkzk }
						</td>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11654">
						<th>
							���޲�ʷ
						</th>
						<td colspan="2">
							${rs.jkzk }
						</td>
					</logic:equal>
				</logic:notEqual>
				<logic:equal name="xxdm" value="10657">
					<th></th>
					<td></td>
				</logic:equal>
			</tr>
			<tr>
				<th>
					��ע
				</th>
				<td colspan="4" style="word-break:break-all;width:99%">
					${rs.bz}
				</td>
				
			</tr>
		</tbody>
	</table>
</div>

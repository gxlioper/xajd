<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			//ѧ����λ����
			function xsgwsq(obj){
				obj.parentNode.parentNode.click();
				var url = "qgzx_gwsqwh.do?method=xssqUpdate";
				url += "&gwdmsbsj="+curr_row.getElementsByTagName('input')[0].value;
				//showTopWin(url,800,600);
				refreshForm(url);
			}
			
			function bksq() {
				alert("ֻ�����ѣ�ƶ�������ſ�������ø�λ��");
				return false;
			}
			function hmd() {
				alert("����Ȩ����ø�λ������ϵ����Ա��");
				return false;
			}
		</script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
				1.��������г���ǰѧ�꣬ѧ�ڣ�����·�������ʱ���ڵ���Ŀ��</br>
				<logic:equal name="userType" value="stu">
				2.��������������Ŀ����������������Ŀ<font color="blue">��������</font>������ɲ鿴��ϸ��</br>
				
				3.��������������Ŀ������<font color="blue">����</font>��������չʾҳ����д����������ݡ�</br>
				4.������Ѿ�������ĳ��Ŀ�����Ҹ���Ŀ��δ����һ��λ��ˣ����Ե��<font color="blue">�޸�</font>��
				</logic:equal>
				<logic:notEqual name="userType" value="stu">
				2.���������ĳѧ������ĳ��Ŀ������<font color="blue">����</font>����չʾҳ������ѡѧ������ɲ�����</br>
				</logic:notEqual>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/qgzx_gwsqwh" method="post">
			<!-- ��������� -->
			<input type="hidden" name="hmd" id="hmd" value="${hmd }"/>
			
			<div class="toolbox">
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select property="xn" style="width: 150px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="xq" style="width: 150px" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
							<th>���</th>
							<td>
								<html:select property="nd" style="width: 150px" styleId="nd" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��λ����</th>
							<td>
								<html:select  property="gwxz" style="width:150px" styleId="gwxz">
							<html:option value=""></html:option>	
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc" />
						</html:select>
							</td>	
							<th>���˵�λ</th>
							<td>
								<html:select  property="sqdw" styleId="sqdw" style="width:150px">
							<html:options collection="yrdwList" property="dm" labelProperty="mc" />
						</html:select>
							</td>
							<th>������ʼʱ��</th>
							<td>
								<html:text property="gzksrq" styleId="gzksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width: 145px"
							onclick="return showCalendar('gzksrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>��������ʱ��</th>
							<td>
								<html:text property="gzjsrq" styleId="gzjsrq"
							onblur="dateFormatChg(this);" style="cursor:hand;width: 145px"
							onclick="return showCalendar('gzjsrq','y-mm-dd');" />
							</td>
							<th></th>
							<td>
								
							</td>
							<th></th>
							<td>
							
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="act" value="go" />
									<button type="button" id="search_go" onclick="if (checkSearchTj('gzksrq','gzjsrq')) {refreshForm('qgzx_gwsqwh_xssq.do')}">
										�� ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr style="cursor:hand" align="left">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="${tit.en }/>"
										onclick="tableSort(this)">&nbsp;
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ѧ���û�����ҳ�� begin -->
						<logic:equal value="stu" name="userType"> 
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand;" align="left">
								<td>
									<input type="hidden" value="${s.pk }" />
									${s.gwxzmc }	
								</td>
								<td>
									${s.yrdwmc }
								</td>
								<td>
									${s.gwdm}
								</td>
								<td>
									${s.sqsyrs }
								</td>
								<td>
									${s.sqsyknss}
								</td>
								<td>
									${s.sqjssj}
								</td>
								<td>
									${s.shzt}
								</td>
								<td>
									<logic:equal value="ksq" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											��&nbsp;&nbsp;&nbsp;&nbsp;��
										</button>
									</logic:equal>
									<logic:equal value="xg" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											��&nbsp;&nbsp;&nbsp;&nbsp;��
										</button>
									</logic:equal>
									<logic:equal value="bksq" name="s" property="cz">
										<button type="button" class="btn_01" onclick="bksq()" style="width:80px;height: 20px">
											��������
										</button>
									</logic:equal>
									<logic:equal value="hmd" name="s" property="cz">
										<button type="button" class="btn_01" onclick="hmd()" style="width:80px;height: 20px">
											��������
										</button>
									</logic:equal>
									<logic:equal value="ck" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											��&nbsp;&nbsp;&nbsp;&nbsp;��
										</button>
									</logic:equal>
								</td>
							</tr>
						</logic:iterate>
						</logic:equal>
						<!-- end -->
						
						<!-- ��ѧ���û�����ҳ��     begin -->
						<logic:notEqual value="stu" name="userType">
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand;" align="left">
								<td>
									<input type="hidden" value="${s.pk }" />
									${s.gwxzmc }	
								</td>
								<td>
									${s.yrdwmc }
								</td>
								<td>
									${s.gwdm}
								</td>
								<td>
									${s.sqsyrs }
								</td>
								<td>
									${s.sqsyknss}
								</td>
								<td>
									${s.sqjssj}
								</td>
								<td>
									<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
										��&nbsp;&nbsp;&nbsp;&nbsp;��
									</button>
								</td>
							</tr>
						</logic:iterate>
						</logic:notEqual>
						<!-- end -->
					</table>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
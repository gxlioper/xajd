<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script type="text/javascript" src="wjcf/csmz/csmzJs/csmzJs.js"></script>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
		function bbprint() {
			var xxdm = document.getElementById('xxdm').value;
			if ('13022'==xxdm) {
				var url = 'wjcf_nblg_cxprint.do';
				var pk = document.getElementById('pk').value;
				var xh = document.getElementById('xh').value;
				if (pk==null || pk=='') {
					url +='?xh=';
					url += xh;
				} else {
					url +='?pk=';
					url += pk;
				}
				
				window.open(url);
			} else {
				//expAppTab('rsTable','ѧ��������������','')
			}
		}
		
		function xscfcx() {
			
				var url = 'wjcf_nblg_cdtyWjcx.do?pkValue=';
				
				var pk = $("xh").value+$("cfwh").value+$("cfsj").value
				
				url += pk;
				window.open(url);
			
		}
	</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a> 
				<logic:notEqual value="10827" name="xxdm">
						<logic:notEqual value="13022" name="xxdm">
							Υ�ʹ��� -
								<logic:equal value="10827" name="xxdm">
									���
								</logic:equal>
							<logic:notEqual value="10827" name="xxdm">������ֹ��� - ���</logic:notEqual>����
						</logic:notEqual>
					</logic:notEqual>
						<logic:equal value="13022" name="xxdm">
						Υ�ʹ��� - ����쿴������ - ��д�����
					</logic:equal>
				</a>
			</p>
		</div>


		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="pk" value="${pk }" id="pk" />
		<html:form action="/wjcfcsmzwh" method="post">
			<logic:empty name="rs">
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url"
					value="/csmz_cxcfsqdefault.do" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkVal }" />
				<input type="hidden" id="tabType" name="tabType" value="wjcf_cxcfb" />


				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>��д�����</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" id="btn_save"
											onclick="savesqinfo('xh','cfwh','savecxcfsqinfo.do')">
											�ύ����
										</button>
										<logic:equal value="13022" name="xxdm">
										<button type="button" onclick="bbprint()">
											��ӡ/Ԥ��
										</button>
										</logic:equal>
										<logic:equal name="print" value="true">
											<button type="button" class="button2" onclick="xscfcx()">
												�������ֵǼǱ�
											</button>
										</logic:equal>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
								<tr >
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<th width="15%">
											<font color="red">*</font>ѧ��
										</th>
										<td align="left" width="25%">
											<html:text name='rs' property="xh" styleId="xh"
												onkeypress="autoFillStuInfo(event.keyCode,this);"
												maxlength="20" />
											<logic:notEqual value="domodi" name="modi">
												<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
													class="btn_01" id="buttonFindStu">
													ѡ��
												</button>
											</logic:notEqual>
										</td>
									</logic:equal>
									<logic:equal name="userOnLine" value="student" scope="session">
										<th width="15%">
											<font color="red">*</font>ѧ��
										</th>
										<td align="left" width="25%">
											<input type="text" id="xh" name="xh"
												value="<bean:write name='rs' property="xh"  />"
												readonly="true" />
										</td>
									</logic:equal>
									<th width="15%">
										<font color="red">*</font> �����ļ���
									</th>
									<td align="left" width="30%">
										<html:text name='rs' property="cfwh" styleId="cfwh"
											readonly="true" />
										<button type="button" onclick="wjcfInfoTo('cx')" class="button2"
											id="buttonFindWjcf">
											ѡ��
										</button>
									</td>

								</tr>
								<tr >
									<th align="right">
										����
									</th>
									<td align="left">
										<html:text name='rs' property="xm" readonly="true" />
									</td>
									<th>
										ѧ��
									</th>
									<td align="left">
										<html:text name='rs' property="xn" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										�Ա�
									</th>
									<td align="left">
										<html:text name='rs' property="xb" readonly="true" />
									</td>
									<th>
										ѧ��
									</th>
									<td align="left">
										<html:text name='rs' property="xq" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										�꼶
									</th>
									<td align="left">
										<html:text name='rs' property="nj" readonly="true" />
									</td>
									<th>
										����ʱ��
									</th>
									<td align="left">
										<html:text name='rs' property="cfsj" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<html:text name='rs' property="xymc" readonly="true" />
									</td>
									<th>
										�������
									</th>
									<td align="left" colspan="">
										<html:text name='rs' property="cflbmc" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										רҵ
									</th>
									<td align="left">
										<html:text name='rs' property="zymc" readonly="true" />
									</td>
									<th>
										��������
									</th>
									<td align="left" colspan="">
										<html:text name='rs' property="cfyymc" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										�༶
									</th>
									<td align="left">
										<html:text name='rs' property="bjmc" readonly="true" />
									</td>
									<th>
										����ʱ��
									</th>
									<td align="left">
										<html:text name='rs' property="cxsj" readonly="true"></html:text>
									</td>
								</tr>
								<tr>
									<th>
										��������<br />
										����ʵ����
									</th>
									<td align="left" colspan="3">
										<html:textarea property="bz" styleId="bz"
											styleClass="inputtext;" rows="8" style="width:100%;word-break:break-all;"></html:textarea>
									</td>
								</tr>
								</tbody>
							</table>
						</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("�ύ�ɹ���");                
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("�ύʧ��,�����˴��ֳ����������!");                   
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="no" name="isHG">
				<script>
                      alert("���������������ϸ񣬴���������һ���������!");                   
                    </script>
			</logic:equal>
		</html:form>
</html>

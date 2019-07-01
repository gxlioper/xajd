<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/yysqDetail.js"></script>
		<script type="text/javascript">	
			function query(){
				document.location.href="xlzx_yysq.do?method=yysqDetail&xxxq="+jQuery("#xxxq").val()+"&date="+jQuery("#pbdate").val();
			}
		</script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysq" method="post">
		<!--  <input type="hidden" id="url" name="url" value="xlzx_yysq.do?method=yysqDetail" /> -->
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${zxspbInfo.pbdate}" />
			<logic:equal name="xxdm" value="10026">
			<logic:notEqual name="doType" value="view">	
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">У��</th>
						<td>
							<html:select property="xxxq" styleId="xxxq" style="width:150px;">
								<html:option value="">---��ѡ��У��---</html:option>
								<html:options collection="xqList" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			</logic:notEqual>
			</logic:equal>	
			<div style='width:100%;overflow-y:hidden;overflow-x:hidden'>
				<table width="100%" border="1" class="formlist">
					<logic:notEqual name="doType" value="view">	
						<thead>
							<tr >
								<th colspan="5">
									<span>ԤԼ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxspbInfo">
							<tr style="height:10px">
								<th  width="20%">
									ԤԼ��ѯ����
								</th>
								<td width="20%" >
									<span class="red"><B>${zxspbInfo.pbdate}</B></span>
								</td>
								<th width="20%">
									ѧ��ѧ��
								</th>
								<td width="40%" colspan="2">
									${currxn}&nbsp;&nbsp;${currxq}
								</td>
							</tr>
							<logic:notEmpty name="zxspbInfo" property="bz">
								<tr style="height:50px">
									 <th>
										ԤԼ˵��
									</th>
									<td  colspan="4">
										${zxspbInfo.bz}
									</td>
								</tr>
							</logic:notEmpty>
						</tbody>
					</logic:notEqual>
					<thead>
						<tr >
							<th colspan="5">
								<span>ԤԼ��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
						<logic:iterate name="zxsInfoList" id="zxsList" indexId="index">
							<input type="hidden" name="kjdrs" id="kjdrs${index}" value="${zxsList.kjdrs}" />
							<input type="hidden" name="yjdrs" id="yjdrs${index}"  value="${zxsList.yjdrs}" />
							<tr style="height:10px">
								<logic:equal name="xxdm" value="10026">
									<td align="left" rowspan="7" >
								</logic:equal>
								<logic:notEqual name="xxdm" value="10026">
									<td align="left" rowspan="6" >
								</logic:notEqual>
									<div align="center"><img src="<%=request.getContextPath() %>/teaPic.jsp?zgh=${zxsList.zgh}" style="height:133px;" border="0"   id="zhaopian"/>
									<logic:notEqual name="doType" value="view">
										<br><br><button  name="buttonSave" id="buttonSave${index}" onclick="addYyInfo('${zxsList.zgh}');return false;"  style="display:none">ԤԼ</button>
									</logic:notEqual>
									</div>
									</button>
								</td>
								<th >
									����
								</th>
								<td >
									${zxsList.xm}
								</td>
								<td colspan="2">
									<logic:equal name="zxsList" property="kjdrs" value="">
										<span class="blue">����ѯʦ�����ѳɹ�ԤԼ</span><span class="red">${zxsList.yjdrs}</span><span class="blue">��,������</span>
									</logic:equal>
									<logic:notEqual name="zxsList" property="kjdrs" value="">
										<span class="blue">����ѯʦ������<logic:notEqual name="pbfs" value="2">�ɹ�</logic:notEqual>ԤԼ</span><span class="red">${zxsList.yjdrs}</span><span class="blue">��,����</span><span class="red">${zxsList.kjdrs}</span><span class="blue">��</span>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									�Ա�
								</th>
								<td>
									${zxsList.xb }
								</td>
								<th width="20%">
									����
								</th>
								<td>
									${zxsList.age}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									��ϵ�绰
								</th>
								<td >
									${zxsList.lxdh }
								</td>
								<th>
									���ڲ���
								</th>
								<td >
									${zxsList.bmmc }
								</td>
							</tr>
							<logic:equal name="xxdm" value="10026">
								<tr style="height:10px">
									<th>
										У��<br/>
									</th>
									<td colspan="3">
										${zxsList.xqmc }
									</td>
								</tr>
							</logic:equal>
							<tr style="height:10px">
								<th >
									��ѯ��ַ
								</th>
								<td   colspan="3">
									${zxsList.address }
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									��ְ����<br/>
								</th>
								<td colspan="3">
									${zxsList.zxszg }
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									���<br/>
								</th>
								<td colspan="3">
									${zxsList.zxsjj }
								</td>
							</tr>
							<tr style="height:10px"><td colspan="5"></td></tr>
						</logic:iterate>
					</tbody>
					<logic:equal name="doType" value="view">
						<logic:notEmpty name="yysqInfo">
							<thead>
								<tr >
									<th colspan="5">
										<span>ѧ��ԤԼ��Ϣ</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr style="height:10px">
									<th  width="20%">
										ԤԼ��ѯ����
									</th>
									<td width="20%" >
										<span class="red"><B>${zxspbInfo.pbdate}</B></span>
									</td>
									<th width="20%">
										ѧ��ѧ��
									</th>
									<td width="40%" colspan="2">
										${currxn}&nbsp;&nbsp;${currxq}
									</td>
								</tr>
								<tr>
									<th>
										ԤԼ��ѯʱ��
									</th>
									<td>
										<logic:notEqual value="2" name="pbfs">
											${yysqInfo.qssj}
										<logic:notEqual  name="yysqInfo" property="jssj" value="">
											 &nbsp;��&nbsp;${yysqInfo.jssj}
										</logic:notEqual>
										</logic:notEqual>
										<logic:equal value="2" name="pbfs">
											${yysqInfo.sjdmc}
										</logic:equal>
										
									<th>
										Ԥ����ϵ����
									</th>
									<td  colspan="2">
											${yysqInfo.xstell}
									</td>
								</tr>
								<tr style="height:10px">
									<th>
										��ѯ����
									</th>
									<td colspan="4">
											${yysqInfo.yyzxzt}
									</td>
								</tr>
								<tr style="height:50px">
									<th>��ѯ��Ҫ<br/>
									</th>
									<td colspan="4">
										${yysqInfo.yyzxxq}
									</td>
								</tr>
							</tbody>
						</logic:notEmpty>
					  </logic:equal>
					</table>
				</div>
				<!--
				 <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button id="btn_fh" onclick="returnPage();return false;">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			   -->
		</html:form>
	</body>
</html>


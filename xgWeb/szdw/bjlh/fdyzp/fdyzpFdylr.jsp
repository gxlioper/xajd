<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script language="javascript">
	function saveFdylr(obj,doType){
		var xmlx = document.getElementsByName("xmlx");
		var wcqk = document.getElementsByName("wcqk");
		for(var i=0;i<xmlx.length;i++){
			if(xmlx[i].value=="��ѡ��"){
				if(wcqk[i].value==""){
					alert("��ѡ������������ܿգ�");
					return false;
				}
			}
		}	

		var msg= "��ȷ��Ҫ������";
		if(doType=="tj"){
			msg="ȷ���ύ���ύ����Ϣ�������޸ģ�";
		}
		
		confirmInfo(msg,function(data){
			if("ok"==data){
				showTips('���������У���ȴ�......');
				refreshForm("/xgxt/bjlh_fdyzp.do?method=fdyzpFdylr&doType="+doType);
				obj.disabled=true;
			}
		});
	}
</script>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
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
					��д��Ϣ��������桱��ɱ�����Ϣ�����ɼ����޸ģ���ȷ�����޸�ʱ��������ύ������������ύ����Ϣ�����޸ģ�
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								ѧ�꣺${rs.xn}
								&nbsp;&nbsp;
								<html:hidden property="xn" value="${rs.xn}"/>
								���ڲ��ţ�${rs.bmmc}
								&nbsp;&nbsp;
								����Ա������${rs.xm }
								<html:hidden property="yhm" value="${rs.zgh}"/>
							</td>
						</tr>
					</tbody>									
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ϣ</span>
							</th>
						</tr>
						<tr>
							<th>
								������Ŀ
							</th>
							<th width="10%">
								��Ŀ����
							</th>
							<th>
								������
							</th>
							<th>
								��ע
							</th>
						</tr>
					</thead>
					<tbody width="100%" >
						<logic:present name="xmList">
							<logic:iterate id="j" name="xmList">
								<tr>
									<td width="180px" style="word-break:break-all">
										<html:hidden name="j" property="xmid" />
										<html:hidden name="j" property="zpbid" />
										<html:hidden name="j" property="xmlx" />
										${j.khxm }
									</td>									
									<td width="60px">
										${j.xmlx}
										<logic:equal name="j" property="xmlx" value="��ѡ��">
										<font class="red">*</font>
										</logic:equal>
									</td>
									<td width="360px">
										<html:text name="j" property="wcqk" style="width:98%" maxlength="50"/>
									</td>
									<td width="180px" style="word-break:break-all">
										${j.bz }
									</td>
								</tr>
							</logic:iterate>
						</logic:present>

					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="4">
								<div class="btn">
									<logic:notEqual name="rs" property="sftj" value="��">
										<button type="button" class="button2" onclick="saveFdylr(this,'save');" id="buttonSave">
											����
										</button>
										<button type="button" class="button2" onclick="saveFdylr(this,'tj');" id="buttonSave">
											�ύ
										</button>
									</logic:notEqual>
									<button type="button" name="����" type="reset">�� ��</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
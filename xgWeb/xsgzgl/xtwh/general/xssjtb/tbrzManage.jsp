<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		//�鿴ʧ������
		function ckSbsj(obj){
			var jcb = jQuery('#jcb_'+obj+'').val();	
			var jcsj = 	jQuery('#jcsj_'+obj+'').val();	
			showTopWin("xtwh_xssjtb.do?method=ckSbsj&jcb="+jcb+'&jcsj='+jcsj,800,500);
			}

		function sjTb(){
			confirmInfo("���β���������м����ͬ��<bean:message key="lable.xb" />��רҵ���༶��ѧ����Ϣ���ݡ�ȷ��Ҫ��ʼͬ����?",function(tag){
				if(tag=="ok"){
				hiddenMessage(true,true);
				BatAlert.showTips('����ͬ����������Ҫ"1"���ӣ����Ե�...');
				jQuery.post('xtwh_xssjtb.do?method=xssjTb',
						function(data){
						if(data==true){
							alertInfo('ͬ���ɹ���',function(m){
								if ('ok'==m) {
									document.getElementById('search_go').click();
								}
							});
							}else{
								alertError("ͬ��ʧ�ܣ�");
								return false;
								}
					},'json')
				}
			});
			}

		function tbCsh(){
			confirmInfo("���β�������ɾ��ͬ���쳣�����Լ�ͬ����־��Ϣ��ȷ��Ҫ��ʼ����?",function(tag){
				if(tag=="ok"){
				hiddenMessage(true,true);
				BatAlert.showTips('���ڳ�ʼ�������Ե�...');
				jQuery.post('xtwh_xssjtb.do?method=cshsjTb',
						function(data){
						if(data==true){
							alertInfo('�����ɹ���',function(m){
								if ('ok'==m) {
									document.getElementById('search_go').click();
								}
							});
							}else{
								alertError("����ʧ�ܣ�");
								return false;
								}
					},'json')
				}
			});
			}
	
		</script>
	</head>
	<body >
		<html:form action="/xtwh_xssjtb" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="sjTb();return false;" class="btn_sz">
								�ֹ�ͬ�� </a>
						</li>
						<li>
							<a href="#" onclick="tbCsh();return false;" class="btn_sz">
								ɾ��ͬ����־ </a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						
						<tbody>
							<tr>
								<th>
									�����ʼʱ��
								</th>
								<td>
									<html:text property="jckssj" styleId="jckssj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" readonly="readonly"></html:text>-
									<html:text property="jcjssj" styleId="jcjssj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" readonly="readonly"></html:text>
								</td>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xtwh_xssjtb.do?method=tbrzManage')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
			</div>
				<div class="formbox" id="div_rs">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						</span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
					<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" indexId="index" offset="0">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
									<input type = 'hidden' id = 'jcb_${index}' name="jcb"
										value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
									/>
									<input type = 'hidden' id = 'jcsj_${index}' name="jcsj"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
									/>
									</td>
									<td align="left">
										<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>
										�˴�Ӧͬ��<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>����
										�ɹ�<logic:iterate id="v" name="s" offset="4" length="1">${v}</logic:iterate>����
										ʧ��<a href="javascript:ckSbsj('${index}')"  class="name" ><logic:iterate id="v" name="s" offset="5" length="1">${v}</logic:iterate></a>��
									</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					</logic:notEmpty>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xssjtbForm"></jsp:include>
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

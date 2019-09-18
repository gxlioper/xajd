<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
	<script type="text/javascript">
		function selectStu() {
            var goto = encodeURIComponent('${gotopath}');
            showDialog('��ѡ��һ��ѧ��', 800, 500, 'xsxx_xsgl.do?method=showStudentsForFdyy&goto=' + goto);
        }

        function bjgcj() {
		    var xh = jQuery("#xh").val();
		    var xn = jQuery("#xn").val();
		    var xq = jQuery("#xq").val();
		    if(xh==null||xh==""){
                showAlertDivLayer("����ѡ��ѧ��");
		        return false;
			}
		    if(xn==null||xn==""){
		        return false;
			}
            if(xq==null||xq==""){
                return false;
            }

            jQuery.post("xyfd_gzaljl.do?method=bjgkc", {
                    xn : xn,xq : xq,xh : xh
                },
                function(data) {
					if(data["status"]=="ok"){
                        jQuery("#bjgkcTbody").empty();
                        var html = "";
                        var bjgkclist = data["bjgkclist"];
                        for(var i=0;i<bjgkclist.length;i++){
                            var cxck = bjgkclist[i]["cxckmc"]==null?"":bjgkclist[i]["cxckmc"];
                            html += "<tr align='center'><td>"+bjgkclist[i]["kcmc"]+"</td><td>"+bjgkclist[i]["cj"]+"</td><td>"+bjgkclist[i]["xf"]
								+"</td><td>"+cxck+"</td><td>"+bjgkclist[i]["ksrq"]+"</td></tr>";
						}
						jQuery("#bjgkcTbody").html(html);
					}else {
					    jQuery("#bjgkcTbody").empty();
                        jQuery("#bjgkcTbody").html("<tr align='center'><td colspan='5'>δ�ҵ��κμ�¼��</td></tr>");
					}
                }, 'json');
        }

        function saveGzal() {
            var checkId = 'xh-xn-xq-jdrq-tbbz-jdyy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("�뽫��������д������");
                return false;
            }
            var url = 'xyfd_gzaljl.do?method=saveGzal';
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
	</script>

</head>
<body style="width:100%">
<html:form action="/xyfd_gzaljl" method="post" styleId="demoForm">
	<div style='width:100%;height: 500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="6">
					<span>ѧ���������</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="10%"><span class="red">*</span>ѧ������</th>
				<td width="20%">
					<input type="hidden" id="xh" name="xh" value="${xsxx.xh}" readonly="readonly"/>
					<input type="text" id="xm" name="xm" value="${xsxx.xm}" readonly="readonly" ondblclick="selectStu()" placeholder="˫��ѡ��ѧ��"/>
				</td>
				<th width="10%">�Ա�</th>
				<td width="20%">
					${xsxx.xbmc}
				</td>
				<th width="10%">ѧԺ</th>
				<td width="20%">
					${xsxx.xymc}
				</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>
					${xsxx.zybjmc}
				</td>
				<th>����</th>
				<td>
					${xsxx.ss}
				</td>
				<th>�ֻ�</th>
				<td>
					${xsxx.sjhm}
				</td>
			</tr>
			<tr>
				<th>��ͥ��ַ</th>
				<td colspan="5">
					${xsxx.jtdz}
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<table style="width: 100%">
						<thead>
							<tr>
								<th style="text-align: center;"><label class="red">*</label>ѧ��</th>
								<td colspan="2">
									<html:select property="xn" styleId="xn" onchange="bjgcj()" >
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xnList" property="xn"
													  labelProperty="xn" />
									</html:select>
								</td>
								<th style="text-align: center;"><label class="red">*</label>ѧ��</th>
								<td>
									<html:select property="xq" styleId="xq" onchange="bjgcj()" >
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xqList" property="xqdm"
													  labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th style="width: 30%;text-align: center;">������γ�</th>
								<th style="width: 15%;text-align: center;">����</th>
								<th style="width: 15%;text-align: center;">ѧ��</th>
								<th style="width: 15%;text-align: center;">��������</th>
								<th style="width: 25%;text-align: center;">����ʱ��</th>
							</tr>
						</thead>
						<tbody id="bjgkcTbody">

						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="6">
						<span>��ϵ�˻������</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr><%--  ��ֹ����쳣--%></tr>
				<%
					List<HashMap<String,String>> fdyxx = (List<HashMap<String,String>>)request.getAttribute("fdyxx");
					if(fdyxx!=null){
					for(int i=0;i<fdyxx.size();i++){
				%>
				<tr>
					<th>����Ա</th>
					<td><%=fdyxx.get(i).get("xm")==null?"":fdyxx.get(i).get("xm")%></td>
					<th>���ڲ���</th>
					<td><%=fdyxx.get(i).get("bmmc")==null?"":fdyxx.get(i).get("bmmc")%></td>
					<th>��ϵ�绰</th>
					<td><%=fdyxx.get(i).get("lxdh")==null?"":fdyxx.get(i).get("lxdh")%></td>
				</tr>
				<%
					}
					}
				%>
				<%
					List<HashMap<String,String>> bzrxx = (List<HashMap<String,String>>)request.getAttribute("bzrxx");
					if(bzrxx!=null){
					for(int i=0;i<bzrxx.size();i++){
				%>
				<tr>
					<th>������</th>
					<td><%=bzrxx.get(i).get("xm")==null?"":bzrxx.get(i).get("xm")%></td>
					<th>���ڲ���</th>
					<td><%=bzrxx.get(i).get("bmmc")==null?"":bzrxx.get(i).get("bmmc")%></td>
					<th>��ϵ�绰</th>
					<td><%=bzrxx.get(i).get("lxdh")==null?"":bzrxx.get(i).get("lxdh")%></td>
				</tr>
				<%
					}
					}
				%>

			</tbody>
			<thead>
				<tr>
					<th colspan="6">
						<span>�����Ľ�</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th colspan="1" style="text-align: center">˳��</th>
				<th colspan="2" style="text-align: center">���ݲο�</th>
				<th colspan="3" style="text-align: center">�����¼</th>
			</tr>
			<tr>
				<td>
					������γ̷���
				</td>
				<td colspan="2">
					��1����ѧ��������γ̵�������<br>
					��2����ѧ����ѧ������<br>
					��3��������γ�ѧ���ۺϡ�����ѧ������ռ������<br>
					��4��������γ����ͺ����ʣ�<br>
					��5���Ƿ�Ϊ��ߡ����ѧ�ֿγ̣�<br>
					��6��������γ�֮�������ԣ�
				</td>
				<td colspan="3">
					<textarea name="bjgkcfx" id="bjgkcfx" style="width: 100%;height: 100%;resize:none;"
							  onblur="check()" placeholder="������20~500���ַ���"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					������ԭ�����
				</td>
				<td colspan="2">
					��1��ѧϰ̬���Ƿ������<br>
					��2����ѧϰ�Ƿ�����Ȥ��<br>
					��3��ѧϰʱ�䰲���Ƿ����<br>
					��4��ѧϰ�����Ƿ���أ�<br>
					��5������ʱ���Ƿ����ó�֣�<br>
					��6������ְ�Ƿ���ࣻ<br>
					��7��ѧϰ�����Ƿ񲻵���<br>
					��8���Խ�ʦ���ڿη����Ƿ���ͬ��<br>
					��9�������γ̵�ѧϰ�Ƿ����᣻<br>
					��10���Ƿ���������磻<br>
					��11���Ƿ������ԭ��<br>
					��12���Ƿ��й����ͼ�¼��<br>
					��13���Ƿ����Ƿ�Ĳ�����γ̹��أ�
				</td>
				<td colspan="3">
					<textarea name="bjgyyfx" id="bjgyyfx" style="width: 100%;height: 100%;resize:none;"
							  onblur="check()" placeholder="������20~500���ַ���"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					�Ľ���ʩ
				</td>
				<td colspan="2">
					��1��ѧ���Լ�׼����β��Ⱥ͸Ľ���<br>
					��2��ѧϰ������Ŭ���̶ȡ�ʱ�䱣֤��<br>
					��3���Ƿ���ٲ���Ҫ�Ļ��<br>
					��4���Ƿ�׼����ɻ���С�飻<br>
					��5���Ƿ��������ޡ��������ޣ�<br>
					��6���Ƿ�׼���ƶ��걸��ѧϰ�ƻ���<br>
					��7�����Ⱥ͸Ľ��ľ��彨�����ʩ��<br>
					��8���Ƿ��֪ѧ�����ظ��ҳ���<br>
					��9���ԣ��п��ܣ���ѧ���桢��ѧ����ʶ��̬�ȣ�
				</td>
				<td colspan="3">
					<textarea name="gjcs" id="gjcs" style="width: 100%;height: 100%;resize:none;"
							  onblur="check()" placeholder="������20~500���ַ���"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					����ԭ�����
				</td>
				<td colspan="2">
					��1����û��ѧϰ֮������ѣ�<br>
					��2�����Ѳ�����ԭ��<br>
					��3���������أ�<br>
					��4���Ը��˼ʽ��������<br>
					��5���������أ�ѧ�ѡ�������Ƿ�����Դ���Ƿ���������<br>
					��6����ͥ��������ĸְҵ����ͥ���룩��<br>
				</td>
				<td colspan="3">
					<textarea name="qtyyfx" id="qtyyfx" style="width: 100%;height: 100%;resize:none;"
							  onblur="check()" placeholder="������20~500���ַ���"></textarea>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>��������</th>
				<td>
					<input type="text" id="jdrq" name="jdrq" onclick="showCalendar('jdrq','yyyy-MM-dd');" readonly="readonly"/>
				</td>
				<th><span class="red">*</span>�ر��ע</th>
				<td colspan="3">
					<input type="text" id="tbbz" name="tbbz"/>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>����ԭ��</th>
				<td colspan="5">
					<textarea name="jdyy" id="jdyy" style="width: 100%;height: 100%;resize:none;" ></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
		<div style="width: 100%;height: 50px;position:fixed;bottom:0;">
			<table width="100%" border="0" class="formlist">
				<tfoot>
				<tr>
					<td colspan="4" >
						<div class="bz">"<span class="red">*</span>"Ϊ������</div>
						<div class="btn">
							<button type="button" type="button" onclick="saveGzal();return false;" >
								�����¼
							</button>
							<button type="button" name="�� ��" onclick="iFClose();">
								�� ��
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

 
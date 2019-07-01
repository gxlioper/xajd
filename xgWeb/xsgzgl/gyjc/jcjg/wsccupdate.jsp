<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcjg/js/ccrc.js"></script>

	</head>

	<script >
        jQuery(function($){
            selectOption();
            selectOptionGr();
		});

        function optionsList() {
            var trHtml = '<tr><td colspan="2"><select name = "ztpj"><logic:iterate id="i" name="optionsList"><option value="${i.dm}">${i.xsxh}、${i.mc}</option></logic:iterate></select></td><td colspan="2"><a href="javascript:void(0)" onclick=dele(this);>删除</></td></tr>';
            jQuery("#ztpj").append(trHtml);

        }
        function GroptionsList(obj) {
            var trHtml = '<tr><td colspan="2"><select name = "grpj_'+obj+'"><logic:iterate id="i" name="GroptionsList"><option value="${i.dm}">${i.xsxh}、${i.mc}</option></logic:iterate></select></td><td colspan="2"><a href="javascript:void(0)" onclick=dele(this);>删除</></td></tr>';
            jQuery("#"+obj+"").parent().parent().append(trHtml);

        }

        function dele(obj) {
			jQuery(obj).parent().parent().empty();
        }
        function selectOption() {
            var ztpjs=jQuery('#ztpjs').val();
            for(var i = 0; i <= ztpjs; i++){
                var dm=jQuery('#dm_'+i+'').val();
                jQuery("#ztpj_"+i+" option").each(function () {
                    if(dm==(jQuery(this).val())){
                        jQuery(this).prop('selected',true);
                    }
                });
            }
        }

        function selectOptionGr() {
            var cwhs=jQuery('#cwhs').val();
            var grpjs=jQuery('#grpjs').val();
            var arr = cwhs.split(",");
            var grpjArr = grpjs.split(",");
            for(var i = 0; i < arr.length; i++){//床号

				for(var j = 0; j<=grpjArr[i];j++ ){//每个评价
                    var dm=jQuery('#dm_'+arr[i]+'_'+j+'').val();
                    jQuery("#grpj_"+arr[i]+"_"+j+" option").each(function () {
                        if(dm==(jQuery(this).val())){
                            jQuery(this).prop('selected',true);
                        }
                    });
				}


			}


        }





	</script>
	<body>
		<html:form action="/gyjc_wsjc" method="post" styleId="WsjcForm" onsubmit="return false;">
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>寝室信息
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<input type="hidden" name="lddm" id="lddm" value="${qsxx.lddm}">
			<input type="hidden" name="ccid" id="ccid" value="${jcxx.ccid}">
			<input type="hidden" name="qsh" id="qsh" value="${qsxx.qsh}">
			<input type="hidden" id="ztpjs" value="${ztpjs}">
			<input type="hidden" id="cwhs" value="${cwhs}">
			<input type="hidden" id="grpjs" value="${grpjs}">

			    <tr>
					<th>楼栋</th>
					<td id="ldmc" width="30%">${qsxx.ldmc}</td>
					<th width="20%">层号</th>
					<td id="ch">${qsxx.ch}</td>
				</tr>
				<tr>
					<th width="20%">寝室</th>
					<td id="qshTd" width="30%">${qsxx.qsh}</td>
					<th width="20%">学院</th>
					<td id="xymc">${qsxx.xymc}</td>
				</tr>

				<tr>
					<th width="20%">检查时间</th>
					<td id="jcsj" width="30%">${jcxx.jcsj}</td>
					<th width="20%">评价等级</th>
					<td id="pjdj">${jcxx.pjdj}</td>
				</tr>
				<tr>
					<th width="20%">检查人</th>
					<td id="jcr" width="30%">${jcxx.xm}</td>
					<th width="20%"></th>
					<td ></td>
				</tr>

			</tbody>

				<thead>
				<tr>
					<th colspan="4">
					<span>整体卫生评价
					</span>
						<a class="name" href="javascript:;" onclick="optionsList();">增加检查项</a>
					</th>
				</tr>

				</thead>
			<tbody  id="ztpj">
				<logic:iterate id="i" name="ztpjList" indexId="ind">
					<tr class='jcxmTr'>
						<td colspan="2">
							<input type="hidden" id="dm_${ind}" value="${i.dm}"/>
							<select id = "ztpj_${ind}" name = "ztpj">
								<%--<option selected="selected" value="${i.dm}">${i.xsxh}、${i.mc}</option>--%>
								<logic:iterate id="a" name="optionsList">
									<option value="${a.dm}">${a.xsxh}、${a.mc}</option>
								</logic:iterate>
							</select>
							<%--<html:select property="dm" styleId="dm"--%>
										 <%--style="width:94%">--%>
								<%--<html:options collection="optionsList" property="dm"--%>
											  <%--labelProperty="mc" />--%>
							<%--</html:select>--%>
						</td>
						<td colspan="2">
							<a href="javascript:void(0)" onclick=dele(this);>删除</a></td>
						</td>

					</tr>
				</logic:iterate>

			</tbody>





			<thead >
			<tr>
				<th colspan="4">
					<span>个人卫生评价
					</span>

				</th>
			</tr>
			</thead>
			<logic:iterate id="i" name="grpjModelList">
			<tbody>
			<tr>
			<th colspan="4" id="${i.cwh}"><div align="left" style="width: 140px;float: left ">${i.cwh}号床 &nbsp;&nbsp;${i.xm}</div><a class="name" href="javascript:;" style="float: left;"  onclick="GroptionsList(${i.cwh});">增加检查项</a></th>
					<input type="hidden" name="a" value="${i.cwh}"/>
			</tr>
				<logic:iterate id="a" name="i" property="pjList" indexId="ind">
				<tr class='jcxmTr'>
					<input type="hidden" id="dm_${i.cwh}_${ind}" value="${a.pjdm}">
					<td colspan="2">
						<select name = "grpj_${i.cwh}" id="grpj_${i.cwh}_${ind}">
							<%--<option selected="selected" value="${a.pjdm}">${a.xsxh}、${a.mc}</option>--%>
							<logic:iterate id="b" name="GroptionsList">
								<option value="${b.dm}">${b.xsxh}、${b.mc}</option>
							</logic:iterate>
						</select>
					</td>
					<td colspan="2">
						<a href="javascript:void(0)" onclick=dele(this);>删除</a></td>
					</td>



				</tr>

			</logic:iterate>
		</tbody>
			</logic:iterate>




			<thead>
			<tr>
				<th colspan="4">
					<span>其他信息
					</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>

				<th align="right" width="10%" >
					<span id = "fjbt"></span>附件信息
				</th>
				<td colspan="3">
					<html:hidden property="fjid" styleId="fjid" value="${qsxx.fjid}" />
					<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
					<script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'fjid'
                            });
                        });
					</script>
				</td>
			</tr>
			</tbody>


		</table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveWscc();return false;">
									修 改
								</button>
								<button type="button" onclick="Close();return false;">
									关 闭
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
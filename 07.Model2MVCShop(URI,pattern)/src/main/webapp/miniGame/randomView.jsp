<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<title>�̴ϰ���!</title>
<script type="text/javascript">
<!--
function fncRandom() {
	document.random.submit();
}
-->
</script>
<body>
<form name="random" method="post" action="/purchase/random">

1~5�� ���� ���ڰ� �����Ǿ����ϴ�.<hr>
���󰡴� ���ڸ� �����ּ���.<br>
���� : 3��

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���� ����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="gameNumber" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���� ����Ʈ</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="gamePoint" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" />
		</td>
	</tr>
	<tr class="ct_list_pop">
		<td align="center">
			<a href="javascript:fncRandom();">����!</a>
		</td>
		</tr>
		
		
	
	
	
	
	
	
	<tr>
	<td align="center">
			<a href="/purchase/addPoint">������!</a>
		</td>
	</tr>
			</table>
			</form>
</body>
</html>
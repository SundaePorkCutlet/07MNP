<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<title>미니게임!</title>
<script type="text/javascript">
<!--
function fncRandom() {
	document.random.submit();
}
-->
</script>
<body>
<form name="random" method="post" action="/purchase/random">

1~5의 랜덤 숫자가 생성되었습니다.<hr>
예상가는 숫자를 적어주세요.<br>
배당률 : 3배

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">예상 숫자</td>
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
		<td width="104" class="ct_write">배팅 포인트</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="gamePoint" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" />
		</td>
	</tr>
	<tr class="ct_list_pop">
		<td align="center">
			<a href="javascript:fncRandom();">배팅!</a>
		</td>
		</tr>
		
		
	
	
	
	
	
	
	<tr>
	<td align="center">
			<a href="/purchase/addPoint">돈벌기!</a>
		</td>
	</tr>
			</table>
			</form>
</body>
</html>
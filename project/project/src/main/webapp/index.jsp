<!-- <html>
<body>
<h2>Hello Word!</h2>
<a href="servlets/Login?xxx=123&&yyy=789">GET</a>
</body>
</html> -->


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8" />
<title>Insert title here</title>
</head>
<body>
	<a href="servlets/Login">click here</a>
	<hr />

	<form action="servlets/Login" method="post" >
		id:<input type="text" name="id" value="34E7C9D859EAA926243CC75F69C6524E"/><br/>
		nickname:<input type="text" name="nickname" value="忽如一夜春风来"/><br/>
		platform:<input type="text" name="platform" value="QQ"/><br/>
		province:<input type="text" name="province" value="河南"/><br/>
		gender:<input type="text" name="gender" value="男"/><br/>
		city:<input type="text" name="city" value="信阳"/><br/>
		auth:<input type="text" name="auth" value="3"/><br/>
		time:<input type="text" name="time" value="1491650363897"/><br/>
		useIcon:<input type="text" name="useIcon" value=" http://qzapp.qlogo.cn/qzapp/100371282/34E7C9D859EAA926243CC75F69C6524E/100"/><br/>
		<input type="submit" value="commit" />
	</form>
	<!-- <form action="servlets/Login" method="post">
		account:<input type="text" name="username" /><br /> 
		password:<input type="password" name="password" /><br /> 
		sex:<input type="radio" name="sex" checked="checked" value="male">
		male <input type="radio" name="sex" value="female">female<br /> 
		interest:<input type="checkbox" name="hobby" value="eat" />
		eat<input type="checkbox" name="hobby" value="sleep" />
		sleep<input type="checkbox" name="hobby" value="programm" />
		programm<br/><input type="submit" value="commit" />
	</form> -->
</body>
</html>


<b>teste</b><p>
<i>teste</i><p>
<strike>teste</strike><p>


<bloquote><h2>RUN APPLICATION</h2>
mvn clean package <br>
mvn spring-boot:run <br>

<br>
<h2>API COLLECTION</h2></blockquote>
https://www.getpostman.com/collections/bbe9ad4ab0c48a18b4e0 <br>

<br>
<h2>DATABASE MySQL</h2></blockquote>
CREATE DATABASE IF NOT EXISTS tesouro;<br><br>
CREATE TABLE user  (<br>
&nbsp;&nbsp;&nbsp; id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,<br>
&nbsp;&nbsp;&nbsp; name VARCHAR(20),<br>
&nbsp;&nbsp;&nbsp; description VARCHAR(20)<br>
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;<br>

Примеры использования библиотеки смотрите в тестах исходного кода.
Для добавления зависимости с помощью Maven надо добавить в pom.xml следующий текcт:
```
<repositories>
  <repository>
    <id>SctpClient-mvn-repo</id>
    <url>https://raw.github.com//Historicteam/SctpClient/mvn-repo/</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>by.ostis.mihas</groupId>
    <artifactId>sctp-client</artifactId>
    <version>0.1.1</version>
  </dependency>
</dependencies>
```
Также можете скачать jar файл с mvn-repo.

## SctpClient
Библиотека позовляет взаимодействовать с sc-machine по средствам синхронных и асинхронных sctp команд.
## Подключение
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
    <version>1.0.0</version>
  </dependency>
</dependencies>
```
Также можете скачать jar файл с ветки mvn-repo.
## Поддержка
Если у вас возникли сложности или вопросы по использованию данной библиотеки,  напишите на электронную почту <natashkinsasha@gmail.com>.
## Документация
Пользовательскую документацию можно получить по [ссылке](https://drive.google.com/file/d/0Bw9b3yfujsCxNHBoQmdsZWxoLVU/view?usp=sharing).Документацию API можно получить из исходных кодов пакета или с помощью утилиты Doxygen.

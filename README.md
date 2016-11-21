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
    <version>2.1-beta</version>
  </dependency>
</dependencies>
```
Также можете скачать jar файл с ветки mvn-repo.
## Поддержка
Если у вас возникли сложности или вопросы по использованию данной библиотеки,  напишите на электронную почту <natashkinsasha@gmail.com>.
## Документация
Пользовательскую документацию можно получить по [ссылке](https://drive.google.com/file/d/0Bw9b3yfujsCxQ2pabEdFZVZNX3M/view?usp=sharing)(в разработке). Вот [ссылка](https://drive.google.com/file/d/0Bw9b3yfujsCxNHBoQmdsZWxoLVU/view?usp=sharing) на старую документацию. Документацию API можно получить из исходных кодов пакета или с помощью утилиты Doxygen.

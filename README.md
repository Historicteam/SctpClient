## SctpClient
Библиотека позовляет взоиможейстовать с sc-machine по средствам синхронных и асинхронных sctp команд.
## Подключение
Для добавления зависимости с помощью Maven надо добавить в pom.xml следующий текcт:
```
<repositories>
  <repository>
    <id>tasks.SctpClient-mvn-repo</id>
    <url>https://raw.github.com//Historicteam/tasks.SctpClient/mvn-repo/</url>
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
    <version>0.1.4</version>
  </dependency>
</dependencies>
```
Также можете скачать jar файл с ветки mvn-repo.
## Поддержка
Если у вас возникли сложности или вопросы по использованию данной библиотеки,  напишите на электронную почту <natashkinsasha@gmail.com>.
## Документация
Пользовательскую документацию можно получить по [ссылке](./docs/index.md).Документацию API можно получить из исходных кодов пакета или с помощью утилиты Doxygen.

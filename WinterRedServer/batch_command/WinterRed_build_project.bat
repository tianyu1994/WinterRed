set WORKSPACE=C:\ty\coding\ICBC\WinterRed-temp\WinterRedServer

::building WinterRedServer project
cd %WORKSPACE%\
call mvn clean package -Dmaven.test.skip=true -U 2>nohup.log
copy %WORKSPACE%\target\WinterRedServer.jar C:\ty\coding\ICBC\WinterRed-temp\WinterRedServer\
echo biulded WinterRedServer and output WinterRedServer.jar
exit
set WORKSPACE=C:\ty\coding\ICBC\WinterRed-temp\WinterRedServer
cd %WORKSPACE%\

call mvn package -Dmaven.test.skip=true -U 2>nohup.log
copy %WORKSPACE%\target\WinterRedServer.jar C:\ty\coding\ICBC\WinterRed-temp\WinterRedServer\
::call java -jar WinterRedServer.jar 
pause
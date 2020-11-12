echo off & setlocal EnableDelayedExpansion
set port=8500
set pid=0
echo port: %port%
for /f "tokens=5" %%m in ('netstat -aon ^| findstr "%port"') do (
	set pid=%%m
)

if "%pid%" == "0" (
	echo port: %port% is free
) else (
	echo port: %port% is killed
	taskkill /f /pid %pid%
)
set pid=0
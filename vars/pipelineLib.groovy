def restoreNuget(){
	bat label: 'Restore nuget', script: "%JENKINS_HOME%\\tools\\commands\\nuget-restore.bat"
}
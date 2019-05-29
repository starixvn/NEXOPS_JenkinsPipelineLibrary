def restoreNuget(){
	bat label: 'Restore nuget', script: "%JENKINS_HOME%\\tools\\commands\\nuget-restore.bat"
}

def msBuild(String solutionName){
	bat label: "MsBuild", script: "MsBuild ${solutionName} /t:Rebuild /p:DebugType=Full /nologo /v:minimal"
}

def generateCoverageForNetCore(){
	bat label: "Generate C# Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\NetCoreCoverage.ps1\" \"%WORKSPACE%\""
}
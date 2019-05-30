def restoreNuget(){
	bat label: 'Restore nuget', script: "%JENKINS_HOME%\\tools\\commands\\nuget-restore.bat"
}

def msBuild(String solutionName){
	bat label: "MsBuild", script: "MsBuild ${solutionName} /t:Rebuild /p:DebugType=Full /nologo /v:minimal"
}

def xUnitForNetCore(){
	bat label: "Generate C# Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\NetCoreCoverage.ps1\" \"%WORKSPACE%\""
}

def beginSonarQubeForMsBuild(String key, String name, String additionalParams){
	bat label: "Prepare SonarQube", script: "\"${tool 'SonarScanner for MSBuild'}\\MSBuild.SonarQube.Runner.exe\" begin /k:${key} /n:\"${name}\" /v:%BUILD_NUMBER% ${additionalParams}"
}

def endSonarQubeForMsBuild(){
	bat label: "Run SonarQube Analysis", script: "\"${tool 'SonarScanner for MSBuild'}\\MSBuild.SonarQube.Runner.exe\" end"
}
def restoreNuget(){
	bat label: 'Restore nuget', script: "%JENKINS_HOME%\\tools\\commands\\nuget-restore.bat"
}

def msBuild(String solutionName){
	bat label: "MsBuild", script: "MsBuild ${solutionName} /t:Rebuild /p:DebugType=Full /nologo /v:minimal"
}

def msBuild16(String solutionName){
	bat label: "MsBuild", script: "\"C:\\Program Files (x86)\\Microsoft Visual Studio\\2019\\BuildTools\\MSBuild\\Current\\Bin\\MSBuild.exe\" ${solutionName} /t:Rebuild /p:DebugType=Full /nologo /v:minimal"
}

def xUnitForNetCore(){
	bat label: "Generate C# Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\NetCoreCoverage.ps1\" \"%WORKSPACE%\""
}

def xUnitForNetFramework(){
	bat label: "Generate C# Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\xUnitCodeCoverage.ps1\" \"%WORKSPACE%\""
}

def nunit(){
	bat label: "Generate C# Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\nunitCodeCoverage.ps1\" \"%WORKSPACE%\""
}

def beginSonarQubeForMsBuild(String key, String name, String additionalParams){
	bat label: "Prepare SonarQube", script: "\"${tool 'SonarScanner for MSBuild'}\\SonarScanner.MSBuild.exe\" begin /k:${key} /n:\"${name}\" /v:\"%BRANCH_NAME% [%BUILD_NUMBER%]\" ${additionalParams}"
}

def endSonarQubeForMsBuild(){
	bat label: "Run SonarQube Analysis", script: "\"${tool 'SonarScanner for MSBuild'}\\SonarScanner.MSBuild.exe\" end"
}

def deployByRocketor(String locationIds, String branchName, String commitHash, String redmineIds, String userToken){
	bat label: "Rocketor Deployment", script: "\"%JENKINS_HOME%\\tools\\RocketorPublish\\Fanex.Rocketor.Publish.exe\" --locationids=\"${locationIds}\" --branchname=\"${branchName}\" --commithash=${commitHash} --redmineids=\"${redmineIds}\" --token=${userToken} --workspace=\"%WORKSPACE%\""
}

def tsqlCoverage(String dataSource,String database){
	bat label: "TSQL Coverage", script: "powershell -file \"%JENKINS_HOME%\\tools\\Commands\\SQLCoverage.ps1\" \"%WORKSPACE%\" \"${dataSource}\" \"${database}\""
}

def generateSonarQubeReport(String key){
	bat label:"SonarQube Report", script: "\"%JENKINS_HOME%\\tools\\SonarQube.ReportGenerator\\Fanex.SonarQube.ReportGenerator.exe\" --projectkey=${key} --workspace=\"%WORKSPACE%\""
}

def tsJest(String rootTestPath){
	bat label: "Generate ts Coverage", script: "\"%JENKINS_HOME%\\tools\\Commands\\ts-jest-coverage.cmd\" \"${rootTestPath}\""
}